220610 코드리뷰 및 qna 메모
	
	ol.source.cluster
		zoom lv 에 맞춰 map에 띄워진 feature 들이 중첩되어서 출력되는 경우
		해당 feature 들을 겹치게끔 할 수 있는데, 
		상기 기능이 cluster이다.
		ex. 네이버 지도 내 줌 아웃 하다보면 pin marker 들 겹치게 하는 기능
		
	.js .css ... lib file 주입 습관
		si 사업 특성상 고객사 혹은 제품 설치 환경에는 
		폐쇄망(인터넷 사용불가 환경)일 경우가 높다.
		
		해당 환경에는 통상적으로 정적리소스 파일을 넣어주던 방식인
		cdn 처리 방식, link href 방식 모두 정상적으로 처리가 어렵다.

		이에 따라, 활용하고자 하는 정적 리소스 파일의 공식 홈페이지 내 
		정적리소스를 직접 내려받아 path 처리해야 한다. 
		
		습관적으로 링크를 넣으면 정상적으로 구동이 절대 불가하다.
		
	뷰단에서도 로직을 분리해야 한다.
		뷰단에서는 화면(레이아웃, 골격 등)을 구성하는 로직이 있을 것이고,
		화면을 구성한 로직에서도 로직 내에서 화면 내 데이터를 만드는 로직이 있을 것이다.
		화면에 뿌려주는 데이터를 만드는 로직도 화면 구성 로직과 구분하는 것이 바람직하다.
		
		결론
			화면 구성 로직과 
			화면 데이터 로직을 구분하고 분리하자.
		
		하는 이유? 
			유지보수성
			협업의 용이함 
		
	F12 chrome devTools
		netword tab
			disabled cache 체크박스를 항상 체크해놓자
			캐시로 저장된 데이터를 지속적으로 새로 받을 수 있게 할 수 있음. 
			
			요청에 따라 캐시를 지속적으로 확인해야하는 개발자들에게 꿀같은 기능임.
			
		Fetch btn 
			비동기 통신, ajax, fetch 함수를 쓸 때, RestApi Test 를 할 때 
			response 값을 확인 할 수 있음.
		
		ctrl + R 
			devTools 화면에서 새로고침(ctrl + R)을 해도 view 화면 새로 고침 가능
			
	쿼리로 고민하지 말 것.
		테이블 내 특정 컬럼만 조회해도 되는 경우 
		모든 컬럼을 조회할 때 하단 코드처럼 하던가
			select (*) from (table); 
		특정 컬럼값들을 조회할 때, 하단 코드처럼 하게 되는데 
			select (co1, col2, ...) from (table);		
		조회하고자 하는 데이터들이 수십, 수백만 건 이라도 성능차이가 크지 않다.
		맘편하게 아스타로 조회하고, 조회한 데이터를 핸들링하자.

220609 코드 리뷰 및 qna 메모
	
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

220524

	[BoardEntity deleteYn Column]

		BoardEntity 내 컬럼을 
		char boardDeleteYn; 을 두면서 

		실제 db에서 
		delete query를 날리는 것이 아니라 
		update query를 날려 boardEntity 'N' 값을 'Y' 으로 바꾸고, 
		List<BoardEntity> boardList = boardService.findByBoardList();
		할때 특정 컬럼값을 'Y'인 것은 제외하고 'N'인 boardEntity 들만 조회하는 경우도 있는데 

		어느 상황에서 실제 db를 삭제하고,
		어느 상황에서 deleteYn 처리를 하는가?

			-> 데이터를 보존해야할 이유가 있거나, 
			기획 단계에 따라 차이가 있을 수 있다.

			가령 회원 탈퇴의 경우 
			회원 탈퇴시 회원탈퇴 관련 고지의 의무가 있는데,
			고지 내 데이터를 특정 기간동안 보존해야 할 의무가 있다면 
			특정 컬럼이 'Y'가 되고 난 이후 고지한 기간동안만 보관하고 삭제처리 할 수도 있다.

			필요없는 데이터라면 즉시 삭제 가능

		결론 : deleteYn 처리를 하는 것은 "기획 차이 혹은 데이터의 보존 여부" 차이 

	[update를 할 때 페이지 하나를 더 주는 것이 옳은가?]
	 == 옳다 그르다 할 순 없다. 이것도 기획차이

		-> 기존 소스 코드 상황
		(update.html을 새로 만들어 페이지를 주지 않고
		viewBoard.html 에서 preUpdate btn을 onclick했을때 
		기존 preUpdate btn, delete btn을 display: none처리를 하고 
		신규 goUpdate btn, cancel btn를 display: inline-block 처리.
		goUpdate btn을 onclick했을때 심어둔 .js 파일내 update() fetch(ajax) 동작)
		
			// 상기 상황에서의 개선 방향 
			동일한 값의 2개의 태그를 주고 
			각각의 태그 내 속성 style="display:none"; 을 컨트롤 하는 것이 아니라 

			viewBoard 할 때 
			값 자체를 input 태그 내 넣어두고 disabled를 설정한다.

			update를 할 때에는 disabled 를 해제하는 식도 가능하다.
			그렇다면 태그 하나를 더 안써도 된다. 

			(단, disabled를 했을때 bg-color가 달라지므로, 
			작업 공간의 bg에 맞춰 색만 똑같이 설정해주어야 한다.)
		
		그렇게도 처리 할 수 있다.
		코드에 정답은 없다. 
		기획에 맞춰서 작업하는 것이 좋다. 

	[html와 thymeleaf를 지향하는 이유]
	
		html이 아닌 .jsp로 할 수 있고, 
		코드를 더 복잡하게 짤 수 있지만 정말 간결하고 메서드당 기능 하나, 
		이런 식으로 코드를 작성하는 이유

		협업을 위해.
		내가 작성한 코드를 타인이 수정해야 하는 상황이라면?
		당장 타임리프의 태그를 쓰지 않는 이유도 
		태그 내 class, id 등 퍼블리셔 분의 작업 용이함을 위해.
		el은 최대한 걷어내고 기본에 충실하게 코드를 작성하는 것이 좋다. 
		순수한 코드 그 자체로 작성하는 것이 협업을 위해 옳을 수 있다.

	[builder 패턴] 

	[update query를 확인 후 피드백]
	
		간단한 쿼리라면 @query를 써서 처리할 수 있지만 쿼리가 복잡해질 경우 
		클래스로 처리하는 것이 옳을 수 있다. 

	JINJU(GIS 대시보드 개발)
		- jsp 개발 
		
	ULJU(GIS 대시보드 개발)
		- kotlin
		- thymeleaf
		- js, main
		- .yml

	[etc]

		1. VO / DAO
			VO == entity / domain 
			DAO == DAO / repository
			
			+ result/response
