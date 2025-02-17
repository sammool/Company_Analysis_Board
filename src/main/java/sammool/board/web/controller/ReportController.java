package sammool.board.web.controller;

import java.net.MalformedURLException;
import java.util.List;

import java.nio.charset.StandardCharsets;
import org.apache.tomcat.util.file.ConfigurationSource.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import sammool.board.domain.Fileinfo;
import sammool.board.domain.Report;
import sammool.board.domain.User;
import sammool.board.service.ReportService;
import sammool.board.web.SessionConst;
import sammool.board.web.form.CreateReportForm;
import sammool.board.web.form.EditForm;

@Slf4j
@Controller
@RequestMapping("/report")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/createReport")
    public String reportForm(@ModelAttribute("form") CreateReportForm form, HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session == null){
            return "redirect:/user/login";

        }
        return "report/reportForm";
    }

    //그러니까 파일을 먼저 저장하고 리포트 저장, 그리고 리포트에는 파일 아이디를 담을 수 있도록...
    @PostMapping("/createReport")
    public String createReport(@ModelAttribute("form") CreateReportForm form, HttpServletRequest request ){

        HttpSession session = request.getSession(false);

        User user = (User)session.getAttribute(SessionConst.LOGIN_USER);
        Fileinfo file = reportService.saveFile(form.getMultipartFile());
        Report report = reportService.createReport(user, file, form);

        log.info("리포트 파일:{}",report.getFileinfo());
        reportService.save(report);
        return "redirect:/";
    }

    //상세 페이지
    @GetMapping("/{reportId}")
    public String reportDetail(@PathVariable(value = "reportId") Long reportId,
                               @RequestParam(value = "page", required = false) Integer page,
                               @RequestParam(value = "keyword", required = false) String keyword,
                               @RequestParam(value = "searchType", required = false) String searchType,
                               Model model){

        Report report = reportService.getReportById(reportId);
        reportService.increaseHits(reportId);

        model.addAttribute("report", report);
        return "report/reportDetail";
    }

    @GetMapping("{reportId}/edit")
    public String editForm(@PathVariable(value = "reportId") Long reportId, Model model){
        Report report = reportService.getReportById(reportId);
        model.addAttribute("report", report);
        return "report/editForm";
    }

    @PostMapping("{reportId}/edit")
    public String edit(@PathVariable(value = "reportId") Long reportId,
                       @ModelAttribute("report") EditForm form,
                       @RequestParam(value = "multipartFile", required = false) MultipartFile multipartFile){

        //파일은 따로 받아옴
        Fileinfo fileinfo = reportService.saveFile(multipartFile);

        reportService.update(reportId, fileinfo, form);
        return  "redirect:/report/{reportId}";
    }

    @PostMapping("{reportId}/delete")
    public String delete(@PathVariable(value="reportId") Long reportId){

        reportService.delete(reportId);
        return  "redirect:/";
    }

    @GetMapping("/file/{fileId}")
    public ResponseEntity<UrlResource> downloadFile(@PathVariable(value = "fileId") Long fileId) throws MalformedURLException{
        Fileinfo file = reportService.getFileByid(fileId);

        UrlResource resource = new UrlResource("file:"+file.getUploadPath());
        String encodedUploadFileName = UriUtils.encode(file.getOriginalFilename(), StandardCharsets.UTF_8);
        String contentDisposition = "attachment; filename=\"" +encodedUploadFileName + "\"";

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .body(resource);
    }

}
