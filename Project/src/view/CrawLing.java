package view;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import dao.MovieDAO;

public class CrawLing {


	public CrawLing() {
		MovieDAO mdao = new MovieDAO();
		mdao.delete();
		String id = "webdriver.chrome.driver";
		String path = "C:/chromedriver.exe";
		System.setProperty(id, path);
		ChromeOptions options = new ChromeOptions();
		options.addArguments("headless");
		String url = "https://movie.naver.com/movie/running/current.nhn";
		options.setCapability("ignoreProtectedModeSettings", true);
		WebDriver driver = new ChromeDriver(options);
		// --------구글을 이미 킨 상태--------------------------------------------
		driver.get(url);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		WebElement productList = driver.findElement(By.className("obj_section"));
		List<WebElement> listTitle = productList.findElements(By.className("tit"));
		List<WebElement> listRates = productList.findElements(By.className("star"));
		List<WebElement> listFull = productList.findElements(By.className("link_txt"));List<WebElement> listRuntime = productList.findElements(By.className("info_txt1"));
		
//		List<WebElement> listDirector = productList.findElements(By.className("info_txt1"));
		
		
		ArrayList<String> titleAr = new ArrayList<>();
		ArrayList<String> ratesAr = new ArrayList<>();
		ArrayList<String> runtimeAr = new ArrayList<>();
		ArrayList<String> directorAr = new ArrayList<>();
		ArrayList<String> genreAr = new ArrayList<>();
		ArrayList<String> actorAr = new ArrayList<>();
		
		for (WebElement element : listTitle) {
			String title = element.findElement(By.tagName("a")).getText();
			titleAr.add(title);
			}
		for (WebElement element : listRates) {
			String rates = element.findElement(By.className("num")).getText();
			ratesAr.add(rates);
		}
		int cnt=0;
		String full="";
		ArrayList<String> arFull = new ArrayList<>();
		for (WebElement element : listFull) {
            String f_u_l_l = element.findElement(By.tagName("a")).getText();
            if(cnt<=3) {
            	cnt++;
            	
            	full+=f_u_l_l+";";
            	if(f_u_l_l.equals("알렉스 슈타더만")||f_u_l_l.equals("벤 스타센")||f_u_l_l.equals("김용진")||f_u_l_l.equals("존 노이마이어")) {
            		cnt++;
            		full+="없음;";
            	}
            	if(cnt==3) {
            		arFull.add(full);
            		cnt=0;
            		full="";
            	}
            }
         }
		String[][] gen_dir_act = new String [500][3];
		// 애니메이션.벤 스타센.드라마.
		//arFull = [애니메이션.피트 닥터.제이미 폭스., 공포.제이콥 체이스.아지 로버트슨., 드라마.왕가위.장만옥., 액션.패티 젠킨스.갤 가돗., 멜로/로맨스.에릭 라티고.알랭 샤바., 드라마.문은주.틸다 코브햄-허비., 드라마.타마르 반 덴 도프.요런 셀데슬라흐츠., 드라마.노구치 테루오.사카구치 켄타로., 드라마.조성규.남보라., 공포.라이언 스핀델.케이틀린 커스터., 드라마.데이미언 셔젤.라이언 고슬링., 멜로/로맨스.진옥훈.유관정., 애니메이션.벤 스타센.드라마., 피에르 프랑소와즈 마틴 라발.아사드 아메드.서부., 케빈 코스트너.케빈 코스트너.코미디., 백승환.정영주.범죄., 박정배.이제훈.애니메이션., 신카이 마코토.카미키 류노스케.드라마., 장우진.서영화.드라마., 양재준.기주봉.드라마., 저스틴 켈리.크리스틴 스튜어트.다큐멘터리., 고두현.문현웅.스릴러., 유발 애들러.누미 라파스.다큐멘터리., 박윤진.박윤진.코미디., 찰리 채플린.찰리 채플린.드라마., 루카스 돈트.빅터 폴스터.애니메이션., 이시다테 타이치.이시카와 유이.판타지., 김용화.하정우.멜로/로맨스., 김종관.한지민.멜로/로맨스., 로저 미첼.줄리아 로버츠.액션., 크리스토퍼 놀란.존 데이비드 워싱턴.애니메이션., 스도 토모노리.스기야마 노리아키.다큐멘터리., 김용진.드라마.이와이 슌지., 나카야마 미호.드라마.김태식., 노니 부엔카미노.드라마.크리스티안 펫졸드., 폴라 비어.드라마.장 뤽 고다르., 안나 카리나.애니메이션.신카이 마코토., 심규혁.드라마.토머스 스터버., 프란츠 로고스키.드라마.타키타 요지로., 모토키 마사히로.드라마.로저 미첼., 수잔 서랜든.드라마.케네스 로너건., 케이시 애플렉.드라마.무니카 시멧츠., 탐벳 투이스크.드라마.아그네츠카 홀란드., 제임스 노턴.드라마.예스퍼 갠스란트., 구스타프 스카스가드.액션.당계례., 성룡.드라마.클로드 라롱드., 패트릭 스튜어트.애니메이션.신카이 마코토., 미즈하시 켄지.드라마.척 콘젤만., 애슐리 브래처.드라마.프랑소와 오종., 펠릭스 르페브르.다큐멘터리.탐라 데이비스., 장 미쉘 바스키아.드라마.이종필., 고아성.드라마.미셸 공드리., 로망 뒤리스.다큐멘터리.민병우., 민형식.드라마.이환경., 정우.애니메이션.알렉스 슈타더만., 드라마.임재영.신지수., 다큐멘터리.프레더릭 와이즈먼.리처드 도킨스., 미스터리.아니쉬 차간티.사라 폴슨., 드라마.정형석.송지인., 드라마.루카 구아다니노.틸다 스윈튼., 다큐멘터리.브렌트 밀러 주니어.션 알다란., 애니메이션.블레어 시몬스.하성용., 드라마.질 미무니.모니카 벨루치., 멜로/로맨스.니시타니 히로시.후쿠야마 마사하루., 드라마.츠키카와 쇼.하마베 미나미., 멜로/로맨스.조나단 데이턴.폴 다노., 다큐멘터리.이미해.차희재., 애니메이션.야마구치 히카루.나카자와 마사토모., 애니메이션.우시지마 신이치로.타카스기 마히로., 드라마.레반 아킨.레반 겔바키아니., 멜로/로맨스.리차드 커티스.도널 글리슨., 코미디.김정욱.신민재., 드라마.강대규.성동일., 드라마.윤단비.최정운., 드라마.김록경.하준., 드라마.김초희.강말금., 드라마.제니퍼 켄트.샘 클라플린., 액션.요크 샤클톤.가이 피어스., 애니메이션.시즈노 코분.시영준., 드라마.셀린 시아마.아델 하에넬., 드라마.오미보.코라 켄고., 스릴러.프랑소와 오종.마린 백트., 다큐멘터리.엘리자베스 샌키.제시카 바든., 드라마.데이비드 하인즈.조 퍼디., 드라마.피에트로 마르첼로.루카 마리넬리., 애니메이션.오카다 마리.이와미 마나카., 드라마.김성준.박성일., 애니메이션.안카 다미안.리지 브로체르., 드라마.셀린 시아마.카리자 투레., 다큐멘터리.이희섭.고양이들., 드라마.파우 초이닝 도르지.셰랍 도르지., 드라마.장이머우.공리., 드라마.오즈 야스지로.류 치슈., 서스펜스.프랑소와 오종.로망 뒤리스., 가족.찰스 마틴 스미스.밥., 드라마.장 피에르 다르덴.마리옹 꼬띠아르., 드라마.오즈 야스지로.사다 케이지., 드라마.홍상수.김민희., 드라마.요아킴 트리에.제시 아이젠버그., 멜로/로맨스.리차드 커티스.휴 그랜트., 드라마.봉준영.김도윤., 드라마.마르잔 사트라피.로자먼드 파이크., 드라마.빅 포니.빅 포니., 드라마.타케 마사하루.안도 사쿠라., 공연실황.존 노이마이어.다큐멘터리., 김상철.권오중.드라마., 장이머우.공리.애니메이션., 유아사 마사아키.타니 카논.드라마., 왕가위.장국영.드라마., 소피 데라스페.나에마 리치.드라마., 최하나.크리스탈.드라마., 김덕중.문혜인.드라마., 프랑소와 오종.마린 백트.드라마., 조은지.정수지.코미디., 프랑소와 오종.파브리스 루치니.드라마., 장이머우.공리.다큐멘터리., 김성민.최준원.드라마., 스티븐 바우터루드.소니 코프스 판 우테렌.드라마., 크리스티안 펫졸드.프란츠 로고스키.드라마., 마코 폰테코보.하비 케이틀.범죄., 미이케 다카시.쿠보타 마사타카.드라마., 프랑소와 오종.피에르 니네이.드라마., 정가영.정가영.드라마., 클로드 베리.오드리 토투.코미디., 로브 라이너.빌리 크리스탈.드라마., 마리아 소달.안드레아 베인 호픽.드라마., 장이머우.공리.공연실황., 브렛 설리반.알리스태어 브래머.멜로/로맨스., 해롤드 래미스.빌 머레이.판타지.]
		for (int i = 0; i < arFull.size(); i++) {
			
			gen_dir_act[i]=arFull.get(i).split(";");
		}
		for (int i = 0;  i< gen_dir_act.length; i++) {
				if(gen_dir_act[i][0]==null||gen_dir_act[i][1]==null||gen_dir_act[i][2]==null) {
					break;
				}
				genreAr.add(gen_dir_act[i][0]);
				directorAr.add(gen_dir_act[i][1]);
				actorAr.add(gen_dir_act[i][2]);
		}

		for (int i = 0; i < titleAr.size(); i++) {
			
			mdao.input(titleAr.get(i),ratesAr.get(i),genreAr.get(i),directorAr.get(i),actorAr.get(i));
		}
		System.out.println("영화 목록 업데이트 완료!");
		driver.close();
	}

}
