
코드 리뷰 및 qna 메모
	
	암호화 해싱
		
		해싱 기본 개념 
		"password1234" ------------------------------------> "34SavAD@F23#BJKLF!..."
			(평문)		  단방향해싱			(다이제스트_digest)


		키 스트레칭
		"password1234" ------------------------------------> "34SavAD@F23#BJKLF!..."
			(평문)		  xN 회 해싱			(다이제스트_digest)
			하는 이유? 
			-> 브루트 포스 방지(무차별 대입 공격)
				-> 해싱 특성상 어떠한 값을 무작위로 넣다보면 언젠간(?) 맞을텐데 그것을 방지함. 
		
	jasypt
		개발자가 암호화에 대해 깊은 지식이 없더라도
		암호화를 할 수 있게 돕는 java lib

	datasource의 설정 트렌드?
		요즘은 datasource의 설정을 
		application context에서 하는 것이 아닌
		.properties(.yml)에서 하는 것이 트렌드.

	entity? vo? domain? dto?
		비슷한 듯, 같은 역할을 하듯 
		크고 작은 차이점이 있음.
		
			entity
			테이블과 1:1 매칭되는 객체로,
			가장 core 한 객체로 볼 수 있음.
			jpa에서 사용되는 개념.
				mybatis를 사용할 땐
				entity, vo, dto를 정확히 구분하여 개발하는 것이 
				생산성이 더 떨어진다고 보고 있음. 
			요청이나 응답에 사용되어서는 안되며 만약 @entity를 사용하여 
			entity 코드를 작성했다면 dto 객체를 만들어 
			requestDto / responseDto 객체로 계층 처리를 해야한다. 
			BoardEntity -> .toEntity
						
			vo
			값이나 특성을 지니고 있는 객체
			계산식 같은 메서드를 포함하고 있을 수 있음.
			get, set으로 컨트롤 할 수 있음
			
			dto
			계산식 등 로직을 갖고 있지 않다.
			계층간 이동만을 목적으로 하는 객체
			immutable 특성.
				
			domain
			final 클래스 사용 
			
		결론
			그동안 매개변수나, 리턴을 vo로 컨트롤 했다면
			환경에 따라 인자를 reqDto, 리턴을 resDto로 
			view -> controller -> service -> repository(abstract JpaRepository) -> db ctrl
	
	@Transactional
		service단에서 주로 사용되며,
		자동으로 crud / 오류 발생시 롤백 / 커밋 까지 해주는 어노테이션
			
	@Pathvariable
		@Requestmapping의 정의 부분과 
		method parameter부분에 정의하여 사용 가능
		
	만약 디펜전시의 버전을 내릴때, 다른 디펜전시 간의 호환성 오류가 확인될때?
		한 두개의 디펜전시를 손보는 경우라면 상황이 간단히 해결 되겠지만 
		몇십개의 디펜전시를 버전 확인 해야 하는 경우 
		답이 없다. 노가다 시작임. 
		하나씩 낮추거나 높이며 테스트 돌려봐야한다.
		화이팅.
	
	https://caniuse.com/
		브라우저 버전별로 라이브러리 등등 구동가능/불가 여부를 표상태로 알려준다.
		만약 내 코드가 환경에서 동작하지 않는다면 한번쯤 테스트 돌려보는 것도 좋을 것 같다.
	
	gis engine openlayers, leaflet
		현 openlayers 를 gis 할때 많이 사용하지만 
		leaflet 또한 많이 사용한다.
	
	webpack bundling
		.html 내 들어가는 .js 파일을 하나의 js 파일로 만드는 것을 
		모듈 번들링이라고 하는데 그중 보편적으로 사용하는 것이 webpack 
			-> 걍 여러 js 파일을 하나의 js 파일로 만드는 것
	
		하는 이유?
			웹 페이지 성능 최적화
			같은 타입의 파일을 묶어 요청/응답을 받기 때문에 네트워크 코스트가 줄게 된다.
			
		결론 
			웹페이지 성능 최적화를 위해
			여러 js 파일을 하나의 js 파일로 번들링 하는 것.
			(html, css, png, jpg, js, ...) 가능

220531

	과제 진행중 : react, typeScript 번들링 -> node.js / spring
	npm + typeScript
	webpack bundling
	(* bundling : 모듈화된 소스 코드를 브라우저에서 실행할 수 있는 파일로 한데 묶어 연결해주는 작업)
	
	react + typeScript 형태로 ol 띄워볼 것.

	현재 
	타입스크립트 감 잡는 중
	ts 감 잡고 ol 적용 예정.
	
	npm, yarn 설치 및 vite 구동 가능
	tsconfig 설정 하면서 감 잡는 중

	작업해보면서 npm 명령어를 사용해야 하는 일이 생겼는데,
	linux명령어에 대해 좀 감을 잡았다.
	(실수로 c/window/system32 에 연습용 tsproject를 생성하고 삭제하긴 했지만)
