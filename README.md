<img src=http://image.kyobobook.co.kr/images/book/xlarge/602/x9788965402602.jpg alt="스프링 부트와 AWS로 혼자 구현하는 웹 서비스">

## Spring 웹 계층
* Web Layer
    * 흔히 사용하는 컨트롤러(@Controller)와 JSP/Freemarker 등의 뷰 템플릿 영역.
    * 이외에도 필터(@Filter), 인터셉터, 컨트롤러 어드바이스(@ControllerAdvice) 등 **외부 요청과 응답**에 대한 전반적인 영역.

* Service Layer
    * @Service에 사용되는 서비스 영역
    * 일반적으로 Controller와 Dao의 중간 영역에서 사용
    * @Transactional이 사용되어야 하는 영역

* Repository Layer
    * **Database**와 같이 데이터 저장소에 접근하는 영역
    * 기존에 개발하셨던 분들이라면 Dao(Data Access Object) 영역으로 이해하면 쉬움

* Dtos
    * Dto(Data Transfer Object)는 **계층 간에 데이터 교환을 위한 객체**를 이야기하며 Dtos는 이들의 영역
    * 예를 들어 뷰 템플릿 엔진에서 사용될 객체나 Repository Layer에서 결과로 넘겨준 객체를 말함

* Domain Model
    * 도메인이라 불리는 개발 대상을 모든 사람이 동일한 관점에서 이해할 수 있고 공유할 수 있도록 단순화시킨 것
    * 이를테면 택시 앱이라고 하면 배차, 탑승, 요금 등이 모두 도메인이 될 수 있음
    * @Entity가 사용된 영역 역시 도메인 모델
    * 다만, 무조건 데이터베이스의 테이블과 관계가 있어야만 하는 것은 아님
    * VO(Value Object)처럼 값 객체들도 이 영역에 해당하기 때문
    
## 비즈니스 로직
* 데이터베이스와 사용자 인터페이스 사이의 정보 교환을 처리하는 알고리즘을 설명하는 데 사용하는 비기술적 용어
* Domain Model에서 비즈니스 처리를 담당한다.