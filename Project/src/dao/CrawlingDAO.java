package dao;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import view.TheaterView;

public class CrawlingDAO {
	//cgvCode → 상영관 이름(T_NAME), city_view → 지역 이름(T_CITY) FROM THEATER TABLE
	public void find(String cgvCode, String city_view) {
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
		String[] areaNum = { "01", "02", "202", "12", "03", "11", "05", "204", "206" };
		
		for (int i = 0; i < areaName.length; i++) {
			if (city_view.equals(areaName[i])) {
				index = i;
				break;
			}
		}
		if (index == -1) {
			System.out.println("맞지 않는 정보입니다.\n다시 입력해주세요.");
			//이거 다른 방법 없을까?
			new TheaterView();
		}
		
		url = "http://www.cgv.co.kr/theaters/?areacode=" + areaNum[index];
		driver.get(url);
		try {Thread.sleep(1000);} catch (InterruptedException e) {;}
		
//		Xpath : /html/body/div[2]/div[3]/div[2]/div[1]/div/div[2]/ul/li[?]/div/ul/li[?]/a
		//--line 55 : 에러 뜸 
		WebElement cityname = driver.findElement(By.xpath("/html/body/div[2]/div[3]/div[2]/div[1]/div/div[2]/ul/li["
				+ (index + 1) + "]/div/ul/li[" + (Integer.parseInt(serial) - areaminus[index]) + "]/a"));
		cityname.click();
		
		driver.switchTo().frame("ifrm_movie_time_table");
		WebElement productList = driver.findElement(By.className("sect-showtimes"));
		
		List<WebElement> listTitle = productList.findElements(By.tagName("a"));
		//em문이 여러개인데 얘만 찾을려나?
		List<WebElement> listTime = productList.findElements(By.tagName("em"));
		ArrayList<String> title = new ArrayList<>();
		
		for (WebElement element : listTitle) {
			// 크롤링을 하면 영화 시간, 남은 좌석 이렇게 데이터가 끌어와져서  "석"을 포함한 데이터를 처리하는 코드
			if (!element.getText().contains("석")) {
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
				} else{
					System.out.println(title.get(cnt));
					System.out.println(time+"\n");
					title_find.add(title.get(cnt));
					time_find.add(time);
					//title_find,time_find,serial을 어디에다가 넣어야 할까?
				}
			}
		}
		driver.close();
		if (cnt==-1) {
			System.out.println("찾으시는 결과가 없습니다.\n다시 입력해 주세요.\n");
		} else {
			System.out.println("찾기 완료!");
		}
	}
	//====================================================================================================
	public void find2(String cgvCode, String city_view, String m_name) {
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
		} else {
			System.out.println("찾기 완료!");
		}
	}
}

