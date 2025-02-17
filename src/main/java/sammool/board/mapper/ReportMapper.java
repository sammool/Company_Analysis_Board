package sammool.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import sammool.board.domain.Fileinfo;
import sammool.board.domain.Report;
import sammool.board.paging.SearchDto;

@Mapper
public interface ReportMapper {
    void save(Report report);

    void update(Report report);

    void delete(Long reportId);

    List<Report> getAll(SearchDto params);

    Report getReportById(Long reportId);


    void saveFile(Fileinfo file);

    Fileinfo getFileById(Long fileId);

    int count(SearchDto params);

    @Update("UPDATE report SET hits = hits+1 WHERE id = #{reportId}")
    void increaseHits(@Param("reportId") Long reportId);
}


