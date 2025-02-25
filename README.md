# 기업 분석 게시판 프로젝트

## 목적

증권사 리서치 페이지를 보며 애널리스트 뿐만 아니라 나와 같은 개인 투자자들도 각자의 리포트를 작성한 뒤 올릴 수 있는 게시판이 있다면 좋을 것 같다고 생각하여 기업 분석 게시판 프로젝트를 진행하게 되었습니다.
<br>

## 데이터베이스 구조

![image.png](https://github.com/sammool/Company_Analysis_Board/blob/main/src/main/resources/static/image/image%20(7).png?raw=true)

프로젝트에서는 다음과 같은 데이터베이스 구조를 구성하였습니다.
<br>

![image.png](https://github.com/sammool/Company_Analysis_Board/blob/main/src/main/resources/static/image/image%20(9).png?raw=true)

![image.png](https://github.com/sammool/Company_Analysis_Board/blob/main/src/main/resources/static/image/image%20(8).png?raw=true)

**개발 주요 사항**

- MVC패턴과 뷰 템플릿인 thymeleaf를 사용 하여 데이터를 화면에 나타냈습니다.

- 제목, 종목명, 작성자를 검색했을 때 동적으로 처리하기 위해 mybatis를 사용했습니다.

- multipartFile을 이용하여 파일 업로드를 구현하였습니다.
<br>
##어려웠던 부분과 극복한 점
- 어려웠던 점 : 초기에 ReportService, CommentService, FileService를 분리하여 설계하였지만 ReportService에서 CommentService와 FileService를 모두 의존하는 것이 복잡성을 증가시킨다는 생각이 들었습니다. 
단일 책임 원칙을 지키면서도 클래스 간의 의존성을 줄일 수 있는 방법이 무엇이 있을까 고민했습니다.
<br>
- 극복 방법 :  게시글을 수정하거나 삭제할 때 파일의 수정과 삭제도 함께 이루어지므로 같은 트랜잭션에서 관리하는 것이 자연스럽다고 생각했습니다. 따라서 FileService를 삭제한 뒤 ReportService에서 파일과 관련된 역할을 
수행하도록 하였습니다. ReporService의 역할이 늘어나긴 하지만 기능적으로 봤을 때 자연스러울 뿐만 아니라 의존성을 줄일 수 있었습니다.
