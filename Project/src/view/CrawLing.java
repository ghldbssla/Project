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
			if (f_u_l_l.equals("�˷��� ��Ÿ����") || f_u_l_l.equals("�� ��Ÿ��") || f_u_l_l.equals("�����")
					|| f_u_l_l.equals("�� ���̸��̾�")) {
				i++;
				full += "����;";
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
			//"���� ���Դ�" ��� ��ȭ�� 2������ 2��° "���� ���Դ�"�� ����
			if(titleAr.get(i).equals("���� ���Դ�")) {
				if(cnt_m==1) {
					continue;
				}
				cnt_m++;
			}
			mdao.input(titleAr.get(i), ratesAr.get(i), genreAr.get(i), directorAr.get(i), actorAr.get(i));
		}
		System.out.println("���� ���� ������Ʈ �Ϸ�!");
		// --------------------------------------------------------------------------
//		// url���� �󿵿����� Ŭ��, ���� ��������
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
//		// full_s�� �����Ͱ� �帣, ����, ���� ������ ������ ������ 100�� �Ѱ� �������� ������ ��
//		// ���� 34���� ��ȭ�� ���������� ����
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
//				if (f_u_l_l_s.equals("������佽��� ���尡���꽺Ű") || f_u_l_l_s.equals("Ÿ���� ��Ÿ��")) {
//					cnt++;
//					full_s += "����;";
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
//		System.out.println("��ȭ ��� ������Ʈ �Ϸ�!");
		// -----------------------------------------------------
		// ����Ʈ��ȣ���� �������� ��ȭ�� ��Ұ� �ٸ�
		String[] areaNum = { "01", "02", "202", "12", "03", "11", "05", "204", "206" };
		String[] areaName = { "����", "���", "��õ", "����", "����/��û", "�뱸", "�λ�/���", "���", "����/����/����" };
		int cnt = 0;
		int num = 0;
		// for������ �������� ũ�Ѹ�����
		for (int i = 0; i < areaNum.length; i++) {
			// Ȥ�� �𸣴ϱ� sleep���� 1�� ���
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			url = "http://www.cgv.co.kr/theaters/?areacode=" + areaNum[i];// ����Ʈ �̸��� for������ �ݺ�����
			driver.get(url);

			// �󿵰� �̸� �� ��Ұ� �����ִ� ��� �κ�(li) ũ�Ѹ�
			List<WebElement> theater = driver.findElement(By.className("sect-city")).findElements(By.tagName("li"));

			// ����
			// ���� CGV
			// ���� CGV
			// ...
			// ���
			// null - ������ �ٲ�� null�� ũ�Ѹ���

			// Ȥ�� �𸣴ϱ� sleep���� 1�� ���
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			for (int j = 0; j < theater.size(); j++) {
				WebElement t = theater.get(j);
				String name = t.getText(); // -- �󿵰� �� ������ �� ũ�Ѹ���
				if (areaName.length - 1 != i) {

					// ���� �������� �Ѿ �� ũ�Ѹ��� ���߰� Ż�� (���￡�� ���� �ٲ���� �� ũ�Ѹ��� ����
					if (name.equals(areaName[i])) {
						break;
					}
				}
				// ���� �������� �Ѿ �� j fot���� �ٽ� 0������ ũ�Ѹ��� ��.
				// ���￡ �ִ� ������ �ٽ� ũ�Ѹ��� ������
				// ������ ���� �������� �Ѿ�� �� ���� �󿵰��� null�� �ٲ�
				// �� name�� null�� �ƴ϶�� cnt��� int ������ ī��Ʈ�� ���� ����
				// j�� cnt���� ���� Ŭ �� ũ�Ѹ��ϵ��� �ڵ��� ����.
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
		System.out.println("��ȭ�� ������ ������Ʈ �Ϸ�!");
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
		// --------������ �̹� Ų ����--------------------------------------------
		// ���̹��� ����
		// �����Ͽ��� ������ �߰��߰��� ���� ��� �Ѵ�.
		mdao.name_time_del();
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
			if (!element.getText().contains("��")) {
				// CGV�� �´� ���� ���ϴ� ��ȭ ����
				title.add(element.getText());
			}
		}
		int timeCnt = 0;
		int cnt = -1;
		for (WebElement element : listTime) {
			String time = element.getText();
			if (!time.contains("����")) {
				if (time.equals("") || time == null) {
					cnt++;
				} else{
					if(timeCnt==0) {
						//������ �� 
						System.out.println(cgvCode);
						System.out.println(title.get(cnt));
						System.out.println(time+"\n");
						
						mdao.name_time(cgvCode,title.get(cnt),time);
						timeCnt++;
					}else {
						
						//������ �� 
						System.out.println(cgvCode);
						System.out.println(title.get(cnt));
						System.out.println(time+"\n");
						
						mdao.name_time(cgvCode,title.get(cnt),time);
						
					}
					//title_find,time_find,serial�� ��𿡴ٰ� �־�� �ұ�?
				}
			}
		}
		driver.close();
		if (cnt==-1) {
			System.out.println("ã���ô� ����� �����ϴ�.\n�ٽ� �Է��� �ּ���.\n");
			new TheaterView();
		} else {
			System.out.println("ã�� �Ϸ�!");
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
			new MovieChoiceView();
		} else {
			System.out.println("ã�� �Ϸ�!");
		}
	}
}