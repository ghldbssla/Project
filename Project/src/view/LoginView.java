package view;

import java.util.Scanner;

import dao.Session;
import dao.UserDAO;
import dto.UserDTO;

public class LoginView {
	public LoginView() {
		boolean loginflag = false;
		Scanner sc = new Scanner(System.in);
		UserDAO udao = new UserDAO();
		System.out.print("���̵� : ");
		String userid = sc.next();
		System.out.print("��й�ȣ : ");
		String userpw = sc.next();
		if(false) {
			loginflag = false;
			//���̵� ��й�ȣ �Է½� �α��� ���н� 1)�������� �ʴ� ���̵��Դϴ� 2)�߸��� ��й�ȣ�Դϴ� ��� �߰� �ϰ����
			//������ login�޼ҵ带 boolean ������ �ٲ��ָ� session�� ������.
			System.out.println("�α��� ���� / �ٽ� �õ����ּ���.");
			while(true) {
				System.out.println("1. �ٽ� �õ��ϱ�/n2. ���̵� ã��/n3. ��й�ȣ �缳��/n4. ������");
				int choice = sc.nextInt();
				if (choice==4) {break;}
				switch(choice) {
				case 1 :
					//���̵��??? ����õ������ϸ� ������ũ??
					//cnt �̿��ؼ�?
					new LoginView();
					break;
				case 2 :
					//���̵� ã��
					System.out.println("1. �̸��Ϸ� ã��/n2. �ڵ�����ȣ�� ã��");
					choice=sc.nextInt();
					if (choice==1) {
						System.out.print("�̸����� �Է��ϼ���");
						String userinfo = sc.next();
						udao.find_id(choice, userinfo);	
					}
					else if (choice==2) {
						System.out.print("�ڵ�����ȣ�� �Է��ϼ��� (���ڸ� �Է��ϼ���)");
						String userinfo = sc.next();
						udao.find_id(choice, userinfo);
					}
					break;
				case 3 :
					//��й�ȣ �缳��
					System.out.println("���̵�  : ");
					userid = sc.next();
					if(udao.checkid(userid)) {
						System.out.println("�缳���� ��й�ȣ�� �Է��ϼ��� : ");	
						userpw=sc.next();
					}
					else {
						System.out.println("�������� �ʴ� ���̵��Դϴ�.");
					}
					break;
				default: 
					System.out.println("�߸� �Է��ϼ̽��ϴ�.");
				}
			}			
		}else {
			loginflag = true;
			System.out.println("�α��� ����!");
		}
	}
}
