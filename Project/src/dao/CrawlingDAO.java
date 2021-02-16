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
	//cgvCode �� �󿵰� �̸�(T_NAME), city_view �� ���� �̸�(T_CITY) FROM THEATER TABLE
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
		// --------������ �̹� Ų ����--------------------------------------------
		// ���̹��� ����
		// �����Ͽ��� ������ �߰��߰��� ���� ��� �Ѵ�.
		
		int index = -1;
		String serial = mdao.count(cgvCode);
		int[] areaminus = { 0, 23, 68, 0, 76, 94, 101, 115, 133 };
		String[] areaName = { "����", "���", "��õ", "����", "����/��û", "�뱸", "�λ�/���", "���", "����/����/����" };
		String[] areaNum = { "01", "02", "202", "12", "03", "11", "05", "204", "206" };
		
		for (int i = 0; i < areaName.length; i++) {
			if (city_view.equals(areaName[i])) {
				index = i;
				break;
			}
		}
		if (index == -1) {
			System.out.println("���� �ʴ� �����Դϴ�.\n�ٽ� �Է����ּ���.");
			//�̰� �ٸ� ��� ������?
			new TheaterView();
		}
		
		url = "http://www.cgv.co.kr/theaters/?areacode=" + areaNum[index];
		driver.get(url);
		try {Thread.sleep(1000);} catch (InterruptedException e) {;}
		
//		Xpath : /html/body/div[2]/div[3]/div[2]/div[1]/div/div[2]/ul/li[?]/div/ul/li[?]/a
		//--line 55 : ���� �� 
		WebElement cityname = driver.findElement(By.xpath("/html/body/div[2]/div[3]/div[2]/div[1]/div/div[2]/ul/li["
				+ (index + 1) + "]/div/ul/li[" + (Integer.parseInt(serial) - areaminus[index]) + "]/a"));
		cityname.click();
		
		driver.switchTo().frame("ifrm_movie_time_table");
		WebElement productList = driver.findElement(By.className("sect-showtimes"));
		
		List<WebElement> listTitle = productList.findElements(By.tagName("a"));
		//em���� �������ε� �길 ã������?
		List<WebElement> listTime = productList.findElements(By.tagName("em"));
		ArrayList<String> title = new ArrayList<>();
		
		for (WebElement element : listTitle) {
			// ũ�Ѹ��� �ϸ� ��ȭ �ð�, ���� �¼� �̷��� �����Ͱ� ���������  "��"�� ������ �����͸� ó���ϴ� �ڵ�
			if (!element.getText().contains("��")) {
				// CGV�� �´� ���� ���ϴ� ��ȭ ����
				title.add(element.getText());
			}
		}
		
		int cnt = -1;
		ArrayList<String> title_find = new ArrayList<>();
		ArrayList<String> time_find = new ArrayList<>();
		for (WebElement element : listTime) {
			String time = element.getText();
			if (!time.contains("����")) {
				if (time.equals("") || time == null) {
					cnt++;
				} else{
					System.out.println(title.get(cnt));
					System.out.println(time+"\n");
					title_find.add(title.get(cnt));
					time_find.add(time);
					//title_find,time_find,serial�� ��𿡴ٰ� �־�� �ұ�?
				}
			}
		}
		driver.close();
		if (cnt==-1) {
			System.out.println("ã���ô� ����� �����ϴ�.\n�ٽ� �Է��� �ּ���.\n");
		} else {
			System.out.println("ã�� �Ϸ�!");
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
		// --------������ �̹� Ų ����--------------------------------------------
		// ���̹��� ����
		// �����Ͽ��� ������ �߰��߰��� ���� ��� �Ѵ�.
		int index = -1;
		String serial = mdao.count(cgvCode);
		int[] areaminus = { 0, 23, 68, 0, 76, 94, 101, 115, 133 };
		String[] areaName = { "����", "���", "��õ", "����", "����/��û", "�뱸", "�λ�/���", "���", "����/����/����" };
		for (int i = 0; i < areaName.length; i++) {
			if (city_view.equals(areaName[i])) {
				index = i;
				break;
			}
		}
		if (index == -1) {
			System.out.println("���� �ʴ� �����Դϴ�.\n�ٽ� �Է����ּ���.");
			new TheaterView();
		}
		String[] areaNum = { "01", "02", "202", "12", "03", "11", "05", "204", "206" };
		url = "http://www.cgv.co.kr/theaters/?areacode=" + areaNum[index];// ����Ʈ �̸��� for������ �ݺ�����
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
			if ((!element.getText().contains("��"))&&(m_name.equals(element.getText()))) {
				// CGV�� �´� ���� ���ϴ� ��ȭ ����
				title.add(element.getText());
			}
		}
		int cnt = -1;
		ArrayList<String> title_find = new ArrayList<>();
		ArrayList<String> time_find = new ArrayList<>();
		for (WebElement element : listTime) {
			String time = element.getText();
			if (!time.contains("����")) {
				if (time.equals("") || time == null) {
					cnt++;
				} else if(cnt==0){
					System.out.println(title.get(0));
					System.out.println(time+"\n");
					title_find.add(title.get(0));
					time_find.add(time);
					//title_find,time_find,serial�� ��𿡴ٰ� �־�� �ұ�?
				}
			}
		}
		driver.close();
		if (cnt==-1) {
			System.out.println("ã���ô� ����� �����ϴ�.\n�ٽ� �Է��� �ּ���.\n");
		} else {
			System.out.println("ã�� �Ϸ�!");
		}
	}
}

