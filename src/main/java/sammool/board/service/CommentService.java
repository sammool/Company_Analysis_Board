package sammool.board.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import sammool.board.domain.Comment;
import sammool.board.mapper.CommentMapper;
import sammool.board.mapper.ReportMapper;
import sammool.board.web.form.CommentForm;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {
    private final CommentMapper commentMapper;
    private final ReportMapper reportMapper;

    public Comment save(CommentForm form){
        Comment comment = new Comment();
        comment.setContent(form.getContent());
        comment.setCreatedAt(form.getCreatedAt());
        comment.setUpdatedAt(form.getUpdatedAt());
        comment.setWriter(form.getWriter());;
        comment.setReportId(form.getReportId());
        commentMapper.save(comment);
        return comment;
    }

    public Comment getById(Long commentId){
        return commentMapper.getById(commentId);
    }

    public void delete(Long commentId){
        commentMapper.delete(commentId);
    }

    //report에 대한 댓글 리스트
    public List<Comment> getByReportId(Long reportId){
        return commentMapper.getByReportId(reportId);
    }
}
