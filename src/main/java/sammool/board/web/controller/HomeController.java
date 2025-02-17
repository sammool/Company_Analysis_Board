package sammool.board.web.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import sammool.board.domain.Report;
import sammool.board.paging.PagingResponse;
import sammool.board.paging.SearchDto;
import sammool.board.service.ReportService;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final ReportService reportService;

    @GetMapping("")
    public String home(@ModelAttribute("params") SearchDto params, Model model){
        log.info("params : {}", params);
        PagingResponse<Report> response = reportService.getAll(params);
        model.addAttribute("response", response);
        return "home/home";
    }
}
