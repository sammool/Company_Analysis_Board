package sammool.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import sammool.board.domain.Comment;

@Mapper
public interface CommentMapper {
    void save(Comment comment);
    void update(Comment comment);
    void delete(Long commentId);
    Comment getById(@Param("id") Long id);
    List<Comment> getByReportId(@Param("reportId") Long reportId);
}
