# 커피 스퀘어드
자신의 홈카페를 공유하는 앱
> 2021.03.12 ~ 13에 진행된 해커톤

## 백엔드 기술 스택
- Spring boot
- Spring Web MVC
- Spring Data JPA
- QueryDSL

## 백엔드 서버 사용법
```shell
./gradlew clean build

java -jar homecafe-api/build/libs/homecafe-api.jar
```

## 팀원
- 리암 (AOS Developer)
- 조이 (AOS Developer)
- 윌 (Backend Developer)
- 해피 (Designer)

## 프로젝트 설명
<img src="/images/1.png"></img>
<img src="/images/2.png"></img>
<img src="/images/3.png"></img>
<img src="/images/4.png"></img>
<img src="/images/5.png"></img>
<img src="/images/6.png"></img>
<img src="/images/7.png"></img>
<img src="/images/8.png"></img>

### 아쉬운 점
- 혼자서 백엔드를 약 2일동안 개발해야 되는 짧은 개발 기간으로 인해 처음으로 단위 테스트 및 통합 테스트를 작성하지 않고 프로젝트를 해봤는데,
개발이 매우 빠르게 진행되는 것 같지만, 변경 시 기존에 영향을 테스트 하는 등 그만큼 리소스가 증가했다고 생각함.
- 기능 개발시, 적당한 단위 테스트를 작성하면서 개발하는 것이 오히려 전체적으로 생산성을 높여준다고 생각함.
- 프로젝트를 같이 진행하는 입장으로써, 짧은 기간이다보니 다 같이 열정을 가지고 으쌰으쌰 하는 분위기는 좋았지만, 한편으로 개발자로써 짧은 기간에 코드 품질보다는 기능 개발을 하는 해커톤이 그다지 좋다고는 생각되지 않음.
  (이번에 차라리 Spring WebFlux나 써볼 걸 그랬나ㅎㅎ)
