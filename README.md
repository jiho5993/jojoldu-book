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

## Mustache
css를 header에, js는 footer에 두는 이유는 **페이지 로딩 속도를 높이기 위해서**이다. html은 위에서부터 코드가 실행되기 때문에 head가 다 실행되고나서 body가 실행된다.  
특히 js의 용량이 크면 클수록 body 부분의 실행이 늦어지기 때문에 js는 body 하단에 두어 화면이 다 그려진 뒤에 호출하는 것이 좋다.
1. `{{>layout/header}}`
    * 현재 머스테치 파일을 기준으로 다른 파일을 가져옴
2. `{{#userName}}`
    * 머스테치는 다른 언어와 같은 if문을 제공하지 않음
    * true/false 여부만 판단
    * 그래서 머스테치에서는 항상 최종값을 넘겨줘야 함
    * userName이 있다면 userName을 노출시키도록 구성
3. `{{^userName}}`
    * 머스테치에서 해당 값이 존재하지 않는 경우에 ^를 사용
    * userName이 없다면 로그인 버튼을 노출시키도록 구성

## Spring Security
### method
1. `@EnableWebSecurity`
    * Spring Security 설정들을 홀성화
2. `.csrf().disable().headers().frameOptions().disable()`
    * h2-console 화면을 사용하기 위해 해당 옵션들을 disable
3. `authorizeRequests`
    * URL별 권한 관리를 설정하는 옵션의 시작점
    * authorizeRequests가 선언되어야만 antMatchers 옵션 사용 가능
4. `antMatchers`
    * 권한 관리 대상을 지정하는 옵션
    * URL, HTTP 메소드 별로 관리 가능
    * "/" 등 지정된 URL들은 permitAll() 옵션을 통해 전체 열람 권한을 주었음
    * "/api/v1/**" 주소를 가진 API는 USER 권한을 가진 사람만 가능하도록 했음
5. `anyRequest`
    * 설정된 값들 이외 나머지 URL들을 나타냄
    * 여기서는 authenticated()을 추가하여 나머지 URL들은 모두 인증된 사용자들에게만 허용
    * 인증된 사용자 즉, 로그인한 사용자들을 말함
6. `logout().logoutSuccessUrl("/")`
    * 로그아웃 기능에 대한 여러 설정의 진입점
    * 로그아웃 성공시 "/" 주소로 이동
7. `oauth2Login`
    * OAuth 2 로그인 기능에 대한 여러 설정의 진입점
8. `userInfoEndpoint`
    * OAuth 2 로그인 성공 이후 사용자 정보를 가져올 때의 설정들을 담당
9. `userService`
    * 소셜 로그인 성공 시 후속 조치를 짆ㅇ할 UserService 인터페이스의 구현체를 등록함
    * 리소스 서버(즉, 소셜 서비스들)에서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능을 명시
10. `a href="/logout"`
    * 스프링 시큐리티에서 기본적으로 제공하는 로그아웃 URL
    * 컨트롤러를 만들 필요가 없음
11. `a href="/oauth2/authorization/google`
    * 스프링 시큐리티에서 기본적으로 제공하는 로그인 URL
    * 로그아웃 URL과 마찬가지로 컨트롤러를 만들 필요가 없음
    
### 기존 테스트에 시큐리티 적용으로 문제가 되는 부분들
test소스에서의 JUnit 테스트들이 시큐리티 적용한 뒤 오류가 나는 이유는  
기존에는 바로 API를 호출할 수 있어 테스트 코드 역시 바로 API를 호출하도록 구성하였다.  
하지만, 시큐리티 옵션이 활성화되면 인증된 사용자만 API를 호출할 수 있기 때문에 테스트에 오류가 생긴다.
기존의 API 테스트 코드들이 모두 인증데 대한 권한을 받지 못하였으므로, 테스트 코드마다 인증한 사용자가 호출한 것처럼 작동하도록 해야한다.