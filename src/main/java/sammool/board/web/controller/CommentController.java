package sammool.board.web.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PathVariable;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import sammool.board.domain.Comment;
import sammool.board.domain.Report;
import sammool.board.domain.User;
import sammool.board.service.CommentService;
import sammool.board.service.ReportService;
import sammool.board.web.SessionConst;
import sammool.board.web.form.CommentForm;

@Slf4j
@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/report/{reportId}/addComment")
    public String addComment(@PathVariable("reportId") Long reportId ,
                             @ModelAttribute("commentForm") CommentForm commentForm,
                             HttpSession session){

        if(session.getAttribute(SessionConst.LOGIN_USER) == null){
            return "redirect:/user/login";
        }

        User user = (User) session.getAttribute(SessionConst.LOGIN_USER);
        commentForm.setReportId(reportId);
        commentForm.setWriter(user.getNickname());

        commentService.save(commentForm);

        return "redirect:/report/{reportId}";
    }

    @PostMapping("/report/{reportId}/{commentId}/delete")
    public String deleteComment(@PathVariable(value = "commentId") Long commentId,
                                @PathVariable(value = "reportId") Long reportId){
        commentService.delete(commentId);

        log.info("댓글이 삭제되었습니다");
        return "redirect:/report/{reportId}";
    }
}
