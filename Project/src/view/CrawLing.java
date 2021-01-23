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
		// --------������ �̹� Ų ����--------------------------------------------
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
				if (f_u_l_l.equals("�˷��� ��Ÿ����") || f_u_l_l.equals("�� ��Ÿ��") || f_u_l_l.equals("�����")
						|| f_u_l_l.equals("�� ���̸��̾�")) {
					cnt++;
					full += "����;";
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

		for (int i = 0; i < titleAr.size(); i++) {

			mdao.input(titleAr.get(i), ratesAr.get(i), genreAr.get(i), directorAr.get(i), actorAr.get(i));
		}
		System.out.println("���� ���� ������Ʈ �Ϸ�!");
		// --------------------------------------------------------------------------
		// url���� �󿵿����� Ŭ��, ���� ��������
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

		// full_s�� �����Ͱ� �帣, ����, ���� ������ ������ ������ 100�� �Ѱ� �������� ������ ��
		// ���� 34���� ��ȭ�� ���������� ����
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
				if (f_u_l_l_s.equals("������佽��� ���尡���꽺Ű") || f_u_l_l_s.equals("Ÿ���� ��Ÿ��")) {
					cnt++;
					full_s += "����;";
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
		for (int i = 0;  i< gen_dir_act_s.length; i++) {
				if(gen_dir_act_s[i][0]==null||gen_dir_act_s[i][1]==null||gen_dir_act_s[i][2]==null) {
					break;
				}
				genreAr_s.add(gen_dir_act_s[i][0]);
				directorAr_s.add(gen_dir_act_s[i][1]);
				actorAr_s.add(gen_dir_act_s[i][2]);
		}

		for (int i = 0; i < genreAr_s.size(); i++) {
			mdao.input_s(titleAr_s.get(i),genreAr_s.get(i),directorAr_s.get(i),actorAr_s.get(i));
		}

		System.out.println("��ȭ ��� ������Ʈ �Ϸ�!");
		driver.close();
	}

}
