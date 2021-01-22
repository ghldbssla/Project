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
		// --------������ �̹� Ų ����--------------------------------------------
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
            	if(f_u_l_l.equals("�˷��� ��Ÿ����")||f_u_l_l.equals("�� ��Ÿ��")||f_u_l_l.equals("�����")||f_u_l_l.equals("�� ���̸��̾�")) {
            		cnt++;
            		full+="����;";
            	}
            	if(cnt==3) {
            		arFull.add(full);
            		cnt=0;
            		full="";
            	}
            }
         }
		String[][] gen_dir_act = new String [500][3];
		// �ִϸ��̼�.�� ��Ÿ��.���.
		//arFull = [�ִϸ��̼�.��Ʈ ����.���̹� ����., ����.������ ü�̽�.���� �ι�Ʈ��., ���.�հ���.�常��., �׼�.��Ƽ ��Ų��.�� ����., ���/�θǽ�.���� ��Ƽ��.�˷� ����., ���.������.ƿ�� �ں���-���., ���.Ÿ���� �� �� ����.�䷱ ������������., ���.�뱸ġ �׷��.��ī��ġ ��Ÿ��., ���.������.������., ����.���̾� ���ɵ�.����Ʋ�� Ŀ����., ���.���̹̾� ����.���̾� ����., ���/�θǽ�.������.������., �ִϸ��̼�.�� ��Ÿ��.���., �ǿ��� �����ҿ��� ��ƾ ���.�ƻ�� �Ƹ޵�.����., �ɺ� �ڽ�Ʈ��.�ɺ� �ڽ�Ʈ��.�ڹ̵�., ���ȯ.������.����., ������.������.�ִϸ��̼�., ��ī�� ������.ī��Ű ���뽺��.���., �����.����ȭ.���., ������.���ֺ�.���., ����ƾ �̸�.ũ����ƾ ��Ʃ��Ʈ.��ť���͸�., �����.������.������., ���� �ֵ鷯.���� ���Ľ�.��ť���͸�., ������.������.�ڹ̵�., ���� ä�ø�.���� ä�ø�.���., ��ī�� ��Ʈ.���� ������.�ִϸ��̼�., �̽ô��� Ÿ��ġ.�̽�ī�� ����.��Ÿ��., ���ȭ.������.���/�θǽ�., ������.������.���/�θǽ�., ���� ��ÿ.�ٸ��� �ι���.�׼�., ũ�������� ���.�� ���̺�� ������.�ִϸ��̼�., ���� ���븮.����߸� �븮��Ű.��ť���͸�., �����.���.�̿��� ����., ��ī�߸� ��ȣ.���.���½�., ��� �ο�ī�̳�.���.ũ����Ƽ�� ������., ���� ���.���.�� �� ��ٸ�., �ȳ� ī����.�ִϸ��̼�.��ī�� ������., �ɱ���.���.��ӽ� ���͹�., ������ �ΰ�Ű.���.ŸŰŸ ������., ����Ű ��������.���.���� ��ÿ., ���� ������.���.�ɳ׽� �γʰ�., ���̽� ���÷�.���.����ī �ø���., Ž�� ���̽�ũ.���.�Ʊ׳���ī Ȧ����., ���ӽ� ����.���.������ ������Ʈ., ����Ÿ�� ��ī������.�׼�.����., ����.���.Ŭ�ε� ��յ�., ��Ʈ�� ��Ʃ��Ʈ.�ִϸ��̼�.��ī�� ������., �����Ͻ� ����.���.ô ������., �ֽ��� �귡ó.���.�����ҿ� ����., �縯�� ����긣.��ť���͸�.Ž�� ���̺�., �� �̽� �ٽ�Ű��.���.������., ��Ƽ�.���.�̼� ���帮., �θ� �ڸ���.��ť���͸�.�κ���., ������.���.��ȯ��., ����.�ִϸ��̼�.�˷��� ��Ÿ����., ���.���翵.������., ��ť���͸�.�������� �������.��ó�� ��Ų��., �̽��͸�.�ƴϽ� ����Ƽ.��� ����., ���.������.������., ���.��ī ���ƴٴϳ�.ƿ�� ����ư., ��ť���͸�.�귻Ʈ �з� �ִϾ�.�� �˴ٶ�., �ִϸ��̼�.���� �ø�.�ϼ���., ���.�� �̹���.���ī ����ġ., ���/�θǽ�.�Ͻ�Ÿ�� ���ν�.����߸� �����Ϸ�., ���.��Űī�� ��.�ϸ��� �̳���., ���/�θǽ�.������ ������.�� �ٳ�., ��ť���͸�.�̹���.������., �ִϸ��̼�.�߸���ġ ��ī��.��ī�ڿ� �������., �ִϸ��̼�.������� ����ġ��.Ÿī���� ������., ���.���� ��Ų.���� �ֹ�Ű�ƴ�., ���/�θǽ�.������ ĿƼ��.���� �۸���., �ڹ̵�.������.�Ź���., ���.�����.������., ���.���ܺ�.������., ���.��ϰ�.����., ���.������.������., ���.������ ��Ʈ.�� Ŭ���ø�., �׼�.��ũ ��Ŭ��.���� �Ǿ., �ִϸ��̼�.����� �ں�.�ÿ���., ���.���� �þƸ�.�Ƶ� �Ͽ���., ���.���̺�.�ڶ� �˰�., ������.�����ҿ� ����.���� ��Ʈ., ��ť���͸�.�����ں��� ��Ű.����ī �ٵ�., ���.���̺�� ������.�� �۵�., ���.�ǿ�Ʈ�� ����ÿ��.��ī �����ڸ�., �ִϸ��̼�.��ī�� ����.�̿͹� ����ī., ���.�輺��.�ڼ���., �ִϸ��̼�.��ī �ٹ̾�.���� ���ü��., ���.���� �þƸ�.ī���� ����., ��ť���͸�.����.����̵�., ���.�Ŀ� ���̴� ������.�ζ� ������., ���.���̸ӿ�.����., ���.���� �߽�����.�� ġ��., �����潺.�����ҿ� ����.�θ� �ڸ���., ����.���� ��ƾ ���̽�.��., ���.�� �ǿ��� �ٸ���.������ ����Ƹ�., ���.���� �߽�����.��� ������., ���.ȫ���.�����., ���.���Ŵ Ʈ����.���� ����������., ���/�θǽ�.������ ĿƼ��.�� �׷�Ʈ., ���.���ؿ�.�赵��., ���.������ ��Ʈ����.���ڸյ� ����ũ., ���.�� ����.�� ����., ���.Ÿ�� �����Ϸ�.�ȵ� �����., ������Ȳ.�� ���̸��̾�.��ť���͸�., ���ö.�ǿ���.���., ���̸ӿ�.����.�ִϸ��̼�., ���ƻ� �����Ű.Ÿ�� ī��.���., �հ���.�屹��.���., ���� ������.������ ��ġ.���., ���ϳ�.ũ����Ż.���., �����.������.���., �����ҿ� ����.���� ��Ʈ.���., ������.������.�ڹ̵�., �����ҿ� ����.�ĺ긮�� ��ġ��.���., ���̸ӿ�.����.��ť���͸�., �輺��.���ؿ�.���., ��Ƽ�� �ٿ��ͷ��.�Ҵ� ������ �� ���׷�.���., ũ����Ƽ�� ������.������ �ΰ�Ű.���., ���� �����ں�.�Ϻ� ����Ʋ.����., ������ ��ī��.��Ÿ ����Ÿī.���., �����ҿ� ����.�ǿ��� �ϳ���.���., ������.������.���., Ŭ�ε� ����.���帮 ����.�ڹ̵�., �κ� ���̳�.���� ũ����Ż.���., ������ �Ҵ�.�ȵ巹�� ���� ȣ��.���., ���̸ӿ�.����.������Ȳ., �귿 ������.�˸����¾� �귡��.���/�θǽ�., �طѵ� ���̽�.�� �ӷ���.��Ÿ��.]
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
		System.out.println("��ȭ ��� ������Ʈ �Ϸ�!");
		driver.close();
	}

}
