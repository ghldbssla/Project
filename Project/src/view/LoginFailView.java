package view;

import java.util.Scanner;

import dao.UserDAO;

public class LoginFailView {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		UserDAO udao = new UserDAO();
		
		while(true) {
			System.out.println("1. �ٽ� �õ��ϱ�\n2. ���̵� ã��\n3. ��й�ȣ �缳��\n4. ������");
			int choice = sc.nextInt();
			
			if (choice==4) {break;}
			
			switch(choice) {
			case 1 :
				new LoginView();
				break;
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
				if(udao.checkid(userid)) {
					String newuserpw="";
					while(true) {
						//��й�ȣ ��밡������ üũ
						String checkpassView = "��밡���� ��й�ȣ�Դϴ�.";
						System.out.print("�缳���� ��й�ȣ�� �Է��ϼ��� : ");	
						newuserpw=sc.next();
						String checkpass = udao.checkPass(newuserpw);
						System.out.println(checkpass);
						if (checkpass.equals(checkpassView)) {
							break;
						}
					}
					udao.updatepw(userid, newuserpw);
				}
				else {
					System.out.println("�������� �ʴ� ���̵��Դϴ�.");
				}
				break;
			default: 
				System.out.println("�߸� �Է��ϼ̽��ϴ�.");
			}
		}
	}
}
