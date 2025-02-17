package sammool.board.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Collections;
import java.util.UUID;

import org.apache.tomcat.jni.FileInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import sammool.board.domain.Comment;
import sammool.board.domain.Fileinfo;

import java.io.File;
import java.io.IOException;

import sammool.board.domain.Report;
import sammool.board.domain.User;
import sammool.board.mapper.CommentMapper;
import sammool.board.mapper.ReportMapper;
import sammool.board.paging.Pagination;
import sammool.board.paging.PagingResponse;
import sammool.board.paging.SearchDto;
import sammool.board.web.form.CreateReportForm;
import sammool.board.web.form.EditForm;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReportService {

    // @Value("${file.dir}")
    // private String fileDir;

    private final ReportMapper reportMapper;
    private final CommentMapper commentMapper;

    @Transactional
    public Report createReport(User user, Fileinfo fileinfo, CreateReportForm form){
        Report report = new Report();
        report.setTitle(form.getTitle());
        report.setContent(form.getContent());
        report.setStockName(form.getStockName());
        report.setCreatedAt(LocalDate.now());
        report.setUser(user);
        report.setFileinfo(fileinfo);
        return report;
    }


    @Transactional
    public void save(Report report){
        reportMapper.save(report);
    }

    @Transactional
    public Report getReportById(Long reportId){
        List<Comment> comments = commentMapper.getByReportId(reportId);

        Report report = reportMapper.getReportById(reportId);
        report.setComments(comments);
        return report;
    }

    public void update(Long reportId, Fileinfo fileinfo, EditForm form){
        Report report = reportMapper.getReportById(reportId);
        report.setContent(form.getContent());
        report.setTitle(form.getTitle());
        report.setStockName(form.getStockName());
        report.setUpdatedAt(form.getUpdatedAt());
        report.setFileinfo(fileinfo);

        reportMapper.update(report);
    }

    public void delete(Long reportId){
        reportMapper.delete(reportId);
    }

    public PagingResponse<Report> getAll(SearchDto params){
        int count = reportMapper.count(params);
        if(count < 1){
            return new PagingResponse<>(Collections.emptyList() , null);
        }

        Pagination pagination = new Pagination(count, params);
        params.setPagination(pagination);

        List<Report> list = reportMapper.getAll(params);
        return new PagingResponse<>(list,pagination);
    }
    //조회수 증가
    @Transactional
    public void increaseHits(Long reportId){
        reportMapper.increaseHits(reportId);
    }

    //File
    @Transactional
    public Fileinfo saveFile(MultipartFile reportFile){
        if(reportFile.isEmpty()){
            log.info("파일이 없습니다");
            return null;
        } else{
            String orignalFileName = reportFile.getOriginalFilename();
            String storedFilename = UUID.randomUUID() + "_" + orignalFileName;
            String savePath = "C:\\Users\\sammo\\OneDrive\\문서\\uploads\\" + storedFilename;

            //File에 저장
            Fileinfo file = new Fileinfo(orignalFileName,storedFilename,savePath);

            try {
                reportFile.transferTo(new File(savePath));
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            reportMapper.saveFile(file);
            return file;
        }
    }

    public Fileinfo getFileByid(Long fileId){
        return reportMapper.getFileById(fileId);
    }



}
