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
	}

	public CrawLing(String start) {
		String id = "webdriver.chrome.driver";
		String path = "C:/chromedriver.exe";
		int cnt_s = 0;
		MovieDAO mdao = new MovieDAO();
		mdao.delete();
		mdao.delete_soon();
		mdao.delete_cgv();
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
		List<WebElement> listFull = productList.findElements(By.className("link_txt"));

		ArrayList<String> titleAr = new ArrayList<>();
		ArrayList<String> ratesAr = new ArrayList<>();
		ArrayList<String> directorAr = new ArrayList<>();
		ArrayList<String> actorAr = new ArrayList<>();
		ArrayList<String> genreAr = new ArrayList<>();
		int count=0;
		for (WebElement element : listTitle) {
			String title = element.findElement(By.tagName("a")).getText();
			titleAr.add(title);
			count++;
			if(count==58) {
				count=0;
				break;
			}
		}
		for (WebElement element : listRates) {
			String rates = element.findElement(By.className("num")).getText();
			ratesAr.add(rates);
			count++;
			if(count==58) {
				count=0;
				break;
			}
		}
		String full = "";
		ArrayList<String> arFull = new ArrayList<>();
		for (int i = 0; i < listFull.size(); i++) {
			String f_u_l_l = listFull.get(i).findElement(By.tagName("a")).getText();

			full += f_u_l_l + ";";
			if (f_u_l_l.equals("알렉스 슈타더만") || f_u_l_l.equals("벤 스타센") || f_u_l_l.equals("김용진")
					|| f_u_l_l.equals("존 노이마이어")) {
				i++;
				full += "없음;";
			}
			if (i % 3 == 2) {
				arFull.add(full);
				full = "";
			}
			if (i == 174) {
				break;
			}
		}

		String[][] gen_dir_act = new String[500][3];
		String[] ar = new String[3];
		for (int i = 0; i < arFull.size(); i++) {
			ar = arFull.get(i).split(";");
			gen_dir_act[i] = ar;
		}
		for (int i = 0; i < gen_dir_act.length; i++) {
			if (gen_dir_act[i][0] == null || gen_dir_act[i][1] == null || gen_dir_act[i][2] == null) {
				break;
			}
			genreAr.add(gen_dir_act[i][0]);
			directorAr.add(gen_dir_act[i][1]);
			actorAr.add(gen_dir_act[i][2]);
		}
		int cnt_m=0;
		for (int i = 0; i < actorAr.size(); i++) {
			//"해피 투게더" 라는 영화가 2개여서 2번째 "해피 투게더"를 생략
			if(titleAr.get(i).equals("해피 투게더")) {
				if(cnt_m==1) {
					continue;
				}
				cnt_m++;
			}
			mdao.input(titleAr.get(i), ratesAr.get(i), genreAr.get(i), directorAr.get(i), actorAr.get(i));
		}
		System.out.println("현재 상영작 업데이트 완료!");
		// --------------------------------------------------------------------------
//		// url에서 상영예정작 클릭, 정보 가져오기
//		WebElement searchInput = driver.findElement(By.xpath("//*[@id=\"navi_movie\"]/li[2]/a/em"));
//		searchInput.click();
//
//		WebElement productList_s = driver.findElement(By.className("obj_section"));
//
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			
//		}
//
//		List<WebElement> listTitle_s = productList_s.findElements(By.className("tit"));
//		List<WebElement> listFull_s = productList_s.findElements(By.className("link_txt"));
//
//		ArrayList<String> titleAr_s = new ArrayList<>();
//		ArrayList<String> genreAr_s = new ArrayList<>();
//		ArrayList<String> directorAr_s = new ArrayList<>();
//		ArrayList<String> actorAr_s = new ArrayList<>();
//
//		// full_s은 데이터가 장르, 감독, 배우로 나오기 때문에 정보가 100개 넘게 가져오면 에러가 남
//		// 따라서 34개의 영화만 가져오도록 구현
//		for (WebElement element : listTitle_s) {
//			String title_s = element.findElement(By.tagName("a")).getText();
//			titleAr_s.add(title_s);
//			cnt_s++;
//			if (cnt_s == 34) {
//				cnt_s = 0;
//				break;
//			}
//		}
//		int cnt = 0;
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			;
//		}
//		String full_s = "";
//		ArrayList<String> arFull_s = new ArrayList<>();
//		for (WebElement element : listFull_s) {
//			String f_u_l_l_s = element.findElement(By.tagName("a")).getText();
//			cnt_s++;
//			if (cnt_s == 100) {
//				cnt_s = 0;
//				break;
//			}
//			if (cnt <= 3) {
//				cnt++;
//
//				full_s += f_u_l_l_s + ";";
//				if (f_u_l_l_s.equals("스브야토슬라브 포드가에브스키") || f_u_l_l_s.equals("타무라 코타로")) {
//					cnt++;
//					full_s += "없음;";
//				}
//			}
//			if (cnt == 3) {
//				arFull_s.add(full_s);
//				cnt = 0;
//				full_s = "";
//			}
//
//		}
//		String[][] gen_dir_act_s = new String[50][3];
//		for (int i = 0; i < arFull_s.size(); i++) {
//
//			gen_dir_act_s[i] = arFull_s.get(i).split(";");
//		}
//		for (int i = 0; i < gen_dir_act_s.length; i++) {
//			if (gen_dir_act_s[i][0] == null || gen_dir_act_s[i][1] == null || gen_dir_act_s[i][2] == null) {
//				break;
//			}
//			genreAr_s.add(gen_dir_act_s[i][0]);
//			directorAr_s.add(gen_dir_act_s[i][1]);
//			actorAr_s.add(gen_dir_act_s[i][2]);
//		}
//
//		for (int i = 0; i < genreAr_s.size(); i++) {
//			mdao.input_s(titleAr_s.get(i), genreAr_s.get(i), directorAr_s.get(i), actorAr_s.get(i));
//		}
//
//		System.out.println("영화 목록 업데이트 완료!");
		// -----------------------------------------------------
		// 사이트번호마다 보여지는 영화관 장소가 다름
		String[] areaNum = { "01", "02", "202", "12", "03", "11", "05", "204", "206" };
		String[] areaName = { "서울", "경기", "인천", "강원", "대전/충청", "대구", "부산/울산", "경상", "광주/전라/제주" };
		int cnt = 0;
		int num = 0;
		// for문으로 지역마다 크롤링해줌
		for (int i = 0; i < areaNum.length; i++) {
			// 혹시 모르니까 sleep으로 1초 대기
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			url = "http://www.cgv.co.kr/theaters/?areacode=" + areaNum[i];// 사이트 이름을 for문으로 반복해줌
			driver.get(url);

			// 상영관 이름 및 장소가 적혀있는 모든 부분(li) 크롤링
			List<WebElement> theater = driver.findElement(By.className("sect-city")).findElements(By.tagName("li"));

			// 서울
			// 강남 CGV
			// 강변 CGV
			// ...
			// 경기
			// null - 지역이 바뀌면 null로 크롤링함

			// 혹시 모르니까 sleep으로 1초 대기
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			for (int j = 0; j < theater.size(); j++) {
				WebElement t = theater.get(j);
				String name = t.getText(); // -- 상영관 및 지역을 다 크롤링함
				if (areaName.length - 1 != i) {

					// 다음 지역으로 넘어갈 때 크롤링을 멈추고 탈출 (서울에서 경기로 바뀌었을 때 크롤링이 멈춤
					if (name.equals(areaName[i])) {
						break;
					}
				}
				// 다음 지역으로 넘어갈 때 j fot문은 다시 0번부터 크롤링을 함.
				// 서울에 있는 곳부터 다시 크롤링을 시작함
				// 하지만 다음 지역으로 넘어갔을 땐 서울 상영관이 null로 바뀜
				// 즉 name이 null이 아니라면 cnt라는 int 변수로 카운트를 해준 다음
				// j가 cnt보다 값이 클 때 크롤링하도록 코딩을 만듦.
				if (!name.equals("") && name != null) {
					if (j >= cnt) {
						if (name.equals(areaName[0]) || name.equals(areaName[1]) || name.equals(areaName[2])
								|| name.equals(areaName[3]) || name.equals(areaName[4]) || name.equals(areaName[5])
								|| name.equals(areaName[6]) || name.equals(areaName[7]) || name.equals(areaName[8])) {
						} else {
							num++;
							if (num > 1) {
								mdao.insert_THEATER(num - 1, name, areaName[i]);
							}
						}
						cnt++;
					}
				}
			}
		}
		System.out.println("영화관 데이터 업데이트 완료!");
		driver.close();
	}
//=======================================================
//=======================================================
//=======================================================
	void find(String cgvCode, String city_view) {
		String id = "webdriver.chrome.driver";
		String path = "C:/chromedriver.exe";
		MovieDAO mdao = new MovieDAO();
		System.setProperty(id, path);
		ChromeOptions options = new ChromeOptions();
		String url = "";
		options.setCapability("ignoreProtectedModeSettings", true);
		options.addArguments("headless");
		WebDriver driver = new ChromeDriver(options);
		// --------구글을 이미 킨 상태--------------------------------------------
		// 네이버로 들어가라
		// 셀레니움은 느려서 중간중간에 쉬어 줘야 한다.
		mdao.name_time_del();
		int index = -1;
		String serial = mdao.count(cgvCode);
		int[] areaminus = { 0, 23, 68, 0, 76, 94, 101, 115, 133 };
		String[] areaName = { "서울", "경기", "인천", "강원", "대전/충청", "대구", "부산/울산", "경상", "광주/전라/제주" };
		for (int i = 0; i < areaName.length; i++) {
			if (city_view.equals(areaName[i])) {
				index = i;
				break;
			}
		}
		if (index == -1) {
			System.out.println("맞지 않는 정보입니다.\n다시 입력해주세요.");
			new TheaterView();
		}
		String[] areaNum = { "01", "02", "202", "12", "03", "11", "05", "204", "206" };
		url = "http://www.cgv.co.kr/theaters/?areacode=" + areaNum[index];// 사이트 이름을 for문으로 반복해줌
		driver.get(url);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			;
		}

//			Xpath : /html/body/div[2]/div[3]/div[2]/div[1]/div/div[2]/ul/li[?]/div/ul/li[?]/a
		WebElement cityname = driver.findElement(By.xpath("/html/body/div[2]/div[3]/div[2]/div[1]/div/div[2]/ul/li["
				+ (index + 1) + "]/div/ul/li[" + (Integer.parseInt(serial) - areaminus[index]) + "]/a"));
		cityname.click();

		driver.switchTo().frame("ifrm_movie_time_table");
		WebElement productList = driver.findElement(By.className("sect-showtimes"));

		List<WebElement> listTitle = productList.findElements(By.tagName("a"));
		List<WebElement> listTime = productList.findElements(By.tagName("em"));
		ArrayList<String> title = new ArrayList<>();

		for (WebElement element : listTitle) {
			if (!element.getText().contains("석")) {
				// CGV에 맞는 당일 상영하는 영화 제목
				title.add(element.getText());
			}
		}
		int timeCnt = 0;
		int cnt = -1;
		for (WebElement element : listTime) {
			String time = element.getText();
			if (!time.contains("상영중")) {
				if (time.equals("") || time == null) {
					cnt++;
				} else{
					if(timeCnt==0) {
						//오류가 남 
						System.out.println(cgvCode);
						System.out.println(title.get(cnt));
						System.out.println(time+"\n");
						
						mdao.name_time(cgvCode,title.get(cnt),time);
						timeCnt++;
					}else {
						
						//오류가 남 
						System.out.println(cgvCode);
						System.out.println(title.get(cnt));
						System.out.println(time+"\n");
						
						mdao.name_time(cgvCode,title.get(cnt),time);
						
					}
					//title_find,time_find,serial을 어디에다가 넣어야 할까?
				}
			}
		}
		driver.close();
		if (cnt==-1) {
			System.out.println("찾으시는 결과가 없습니다.\n다시 입력해 주세요.\n");
			new TheaterView();
		} else {
			System.out.println("찾기 완료!");
		}
	}



//====================================================================================================
//====================================================================================================
////====================================================================================================
	void find(String cgvCode, String city_view, String m_name) {
		String id = "webdriver.chrome.driver";
		String path = "C:/chromedriver.exe";
		MovieDAO mdao = new MovieDAO();
		System.setProperty(id, path);
		ChromeOptions options = new ChromeOptions();
		String url = "";
		options.setCapability("ignoreProtectedModeSettings", true);
		options.addArguments("headless");
		WebDriver driver = new ChromeDriver(options);
		// --------구글을 이미 킨 상태--------------------------------------------
		// 네이버로 들어가라
		// 셀레니움은 느려서 중간중간에 쉬어 줘야 한다.
		int index = -1;
		String serial = mdao.count(cgvCode);
		int[] areaminus = { 0, 23, 68, 0, 76, 94, 101, 115, 133 };
		String[] areaName = { "서울", "경기", "인천", "강원", "대전/충청", "대구", "부산/울산", "경상", "광주/전라/제주" };
		for (int i = 0; i < areaName.length; i++) {
			if (city_view.equals(areaName[i])) {
				index = i;
				break;
			}
		}
		if (index == -1) {
			System.out.println("맞지 않는 정보입니다.\n다시 입력해주세요.");
			new TheaterView();
		}
		String[] areaNum = { "01", "02", "202", "12", "03", "11", "05", "204", "206" };
		url = "http://www.cgv.co.kr/theaters/?areacode=" + areaNum[index];// 사이트 이름을 for문으로 반복해줌
		driver.get(url);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			;
		}

//			Xpath : /html/body/div[2]/div[3]/div[2]/div[1]/div/div[2]/ul/li[?]/div/ul/li[?]/a
		WebElement cityname = driver.findElement(By.xpath("/html/body/div[2]/div[3]/div[2]/div[1]/div/div[2]/ul/li["
				+ (index + 1) + "]/div/ul/li[" + (Integer.parseInt(serial) - areaminus[index]) + "]/a"));
		cityname.click();

		driver.switchTo().frame("ifrm_movie_time_table");
		WebElement productList = driver.findElement(By.className("sect-showtimes"));

		List<WebElement> listTitle = productList.findElements(By.tagName("a"));
		List<WebElement> listTime = productList.findElements(By.tagName("em"));
		ArrayList<String> title = new ArrayList<>();

		for (WebElement element : listTitle) {
			if ((!element.getText().contains("석"))&&(m_name.equals(element.getText()))) {
				// CGV에 맞는 당일 상영하는 영화 제목
				title.add(element.getText());
			}
		}
		int cnt = -1;
		ArrayList<String> title_find = new ArrayList<>();
		ArrayList<String> time_find = new ArrayList<>();
		for (WebElement element : listTime) {
			String time = element.getText();
			if (!time.contains("상영중")) {
				if (time.equals("") || time == null) {
					cnt++;
				} else if(cnt==0){
					System.out.println(title.get(0));
					System.out.println(time+"\n");
					title_find.add(title.get(0));
					time_find.add(time);
					//title_find,time_find,serial을 어디에다가 넣어야 할까?
				}
			}
		}
		driver.close();
		if (cnt==-1) {
			System.out.println("찾으시는 결과가 없습니다.\n다시 입력해 주세요.\n");
			new MovieChoiceView();
		} else {
			System.out.println("찾기 완료!");
		}
	}
}