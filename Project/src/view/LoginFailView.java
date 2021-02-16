package view;

import java.util.Scanner;

import dao.UserDAO;

public class LoginFailView {
	public static int cnt=0;
	public LoginFailView(int page) {
		Scanner sc = new Scanner(System.in);
		UserDAO udao = new UserDAO();
		
		while(true) {
			System.out.println("1. �ٽ� �õ��ϱ�\n2. ���̵� ã��\n3. ��й�ȣ �缳��\n4. ������");
			int choice = sc.nextInt();
			
			if (choice==4) {break;}
			
			switch(choice) {
			case 1 :
				while(true) {
					cnt++;
					if(cnt>3) {
						//���� ��й�ȣ 4���̻� Ʋ���� ȸ�������ϰڳİ� ����
						System.out.println("ȸ�������Ͻðڽ��ϱ�?\n1. ��\n2. �ƴϿ�");
						int subchoice = sc.nextInt();
						if(subchoice==1) {
							System.out.println("ȸ������â�Դϴ�.");
							new JoinView();
						}else {
							//�ƴϿ� ������ cnt�ʱ�ȭ �����ְ� �α��� �ٽ� �õ��ϱ�
							cnt=0;
							new LoginView(page);
						}
					}else {
						new LoginView(page);
					}
				}
			case 2 :
				//���̵� ã��
				System.out.println("1. �̸��Ϸ� ã��\n2. �ڵ�����ȣ�� ã��");
				choice=sc.nextInt();
				if (choice==1) {
					System.out.println("�̸����� �Է��ϼ���.");
					String userinfo = sc.next();
					//sql���� �� idx�̸� ����
					udao.find_id(0, userinfo);	
				}
				else if (choice==2) {
					System.out.println("�ڵ�����ȣ�� �Է��ϼ��� (���ڸ� �Է��ϼ���).");
					String userinfo = sc.next();
					udao.find_id(1, userinfo);
				}
				break;
			case 3 :
				//��й�ȣ �缳��
				System.out.print("���̵�  : ");
				String userid = sc.next();
				udao.moveTopw(userid);
				break;
			default: 
				System.out.println("�߸� �Է��ϼ̽��ϴ�.");
			}
		}
	}
}