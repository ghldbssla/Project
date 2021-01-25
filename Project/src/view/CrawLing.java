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
		int cnt_s = 0;
		MovieDAO mdao = new MovieDAO();
		mdao.delete();
		mdao.delete_soon();
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
		List<WebElement> listFull = productList.findElements(By.className("link_txt"));

//		List<WebElement> listDirector = productList.findElements(By.className("info_txt1"));

		ArrayList<String> titleAr = new ArrayList<>();
		ArrayList<String> ratesAr = new ArrayList<>();
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
		int cnt = 0;
		String full = "";
		ArrayList<String> arFull = new ArrayList<>();
		for (WebElement element : listFull) {
			String f_u_l_l = element.findElement(By.tagName("a")).getText();
			if (cnt <= 3) {
				cnt++;

				full += f_u_l_l + ";";
				if (f_u_l_l.equals("알렉스 슈타더만") || f_u_l_l.equals("벤 스타센") || f_u_l_l.equals("김용진")
						|| f_u_l_l.equals("존 노이마이어")) {
					cnt++;
					full += "없음;";
				}
				if (cnt == 3) {
					arFull.add(full);
					cnt = 0;
					full = "";
				}
			}
		}
		String[][] gen_dir_act = new String[500][3];
		for (int i = 0; i < arFull.size(); i++) {

			gen_dir_act[i] = arFull.get(i).split(";");
		}
		for (int i = 0; i < gen_dir_act.length; i++) {
			if (gen_dir_act[i][0] == null || gen_dir_act[i][1] == null || gen_dir_act[i][2] == null) {
				break;
			}
			genreAr.add(gen_dir_act[i][0]);
			directorAr.add(gen_dir_act[i][1]);
			actorAr.add(gen_dir_act[i][2]);
		}

		for (int i = 0; i < genreAr.size(); i++) {
			mdao.input(titleAr.get(i), ratesAr.get(i), genreAr.get(i), directorAr.get(i), actorAr.get(i));
		}
		System.out.println("현재 상영작 업데이트 완료!");
		// --------------------------------------------------------------------------
		// url에서 상영예정작 클릭, 정보 가져오기
		WebElement searchInput = driver.findElement(By.xpath("//*[@id=\"navi_movie\"]/li[2]/a/em"));
		searchInput.click();

		WebElement productList_s = driver.findElement(By.className("obj_section"));

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			;
		}

		List<WebElement> listTitle_s = productList_s.findElements(By.className("tit"));
		List<WebElement> listFull_s = productList_s.findElements(By.className("link_txt"));

		ArrayList<String> titleAr_s = new ArrayList<>();
		ArrayList<String> genreAr_s = new ArrayList<>();
		ArrayList<String> directorAr_s = new ArrayList<>();
		ArrayList<String> actorAr_s = new ArrayList<>();

		// full_s은 데이터가 장르, 감독, 배우로 나오기 때문에 정보가 100개 넘게 가져오면 에러가 남
		// 따라서 34개의 영화만 가져오도록 구현
		for (WebElement element : listTitle_s) {
			String title_s = element.findElement(By.tagName("a")).getText();
			titleAr_s.add(title_s);
			cnt_s++;
			if (cnt_s == 34) {
				cnt_s = 0;
				break;
			}
		}
		cnt = 0;
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			;
		}
		String full_s = "";
		ArrayList<String> arFull_s = new ArrayList<>();
		for (WebElement element : listFull_s) {
			String f_u_l_l_s = element.findElement(By.tagName("a")).getText();
			cnt_s++;
			if (cnt_s == 100) {
				cnt_s = 0;
				break;
			}
			if (cnt <= 3) {
				cnt++;

				full_s += f_u_l_l_s + ";";
				if (f_u_l_l_s.equals("스브야토슬라브 포드가에브스키") || f_u_l_l_s.equals("타무라 코타로")) {
					cnt++;
					full_s += "없음;";
				}
			}
			if (cnt == 3) {
				arFull_s.add(full_s);
				cnt = 0;
				full_s = "";
			}

		}
		String[][] gen_dir_act_s = new String[50][3];
		for (int i = 0; i < arFull_s.size(); i++) {

			gen_dir_act_s[i] = arFull_s.get(i).split(";");
		}
		for (int i = 0; i < gen_dir_act_s.length; i++) {
			if (gen_dir_act_s[i][0] == null || gen_dir_act_s[i][1] == null || gen_dir_act_s[i][2] == null) {
				break;
			}
			genreAr_s.add(gen_dir_act_s[i][0]);
			directorAr_s.add(gen_dir_act_s[i][1]);
			actorAr_s.add(gen_dir_act_s[i][2]);
		}

		for (int i = 0; i < genreAr_s.size(); i++) {
			mdao.input_s(titleAr_s.get(i), genreAr_s.get(i), directorAr_s.get(i), actorAr_s.get(i));
		}

		System.out.println("영화 목록 업데이트 완료!");
		// -----------------------------------------------------
		ArrayList<String> theater1 = new ArrayList<>();
		// 사이트번호마다 보여지는 영화관 장소가 다름
		String[] areaNum = { "01", "02", "202", "12", "03", "11", "05", "204", "206" };
		String[] areaName = { "서울", "경기", "인천", "강원", "대전/충청", "대구", "부산/울산", "경상", "광주/전라/제주" };
		cnt = 0;
		// for문으로 지역마다 크롤링해줌

		// 혹시 모르니까 sleep으로 1초 대기
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		url = "http://www.cgv.co.kr/theaters/?areacode=01";// 사이트 이름을 for문으로 반복해줌
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
		cnt=0;
		for (int j = 1; j < theater.size(); j++) {
			WebElement t = theater.get(j);
			String name = t.getText(); // -- 상영관 및 지역을 다 크롤링함
			if (cnt==30) {
				break;
			}
			cnt++;
			mdao.input(name);
		}
	}
}
