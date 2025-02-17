//package sammool.board;
//
//import org.springframework.stereotype.Component;
//
//import jakarta.annotation.PostConstruct;
//import lombok.RequiredArgsConstructor;
//
//import java.time.LocalDate;
//
//import sammool.board.domain.Report;
//import sammool.board.domain.User;
//import sammool.board.mapper.ReportMapper;
//import sammool.board.mapper.UserMapper;
//
//@Component
//@RequiredArgsConstructor
//public class Init {
//
//    private final UserMapper userMapper;
//    private final ReportMapper reportMapper;
//
//    @PostConstruct
//    public void userSetting(){
//        User user = new User();
//        user.setEmail("sammool@naver.com");
//        user.setNickname("샘물");
//        user.setPassword("sa2003");
//        user.setUsername("박찬규");
//
//        userMapper.save(user);
//    }
//
//    @PostConstruct
//    public void reportSetting(){
//        User user = userMapper.getById(1L);
//        for(long i=1L; i<150L; i++){
//            Report report = new Report();
//            report.setUser(user);
//            report.setStockName("한화시스템");
//            report.setTitle(i + "번째 게시물");
//            report.setCreatedAt(LocalDate.now());
//
//            reportMapper.save(report);
//        }
//
//        for(long i=151L; i<300L; i++){
//            Report report = new Report();
//            report.setUser(user);
//            report.setStockName("코웨이");
//            report.setTitle(i + "번째 게시물");
//            report.setCreatedAt(LocalDate.now());
//
//            reportMapper.save(report);
//        }
//    }
//
//
//}