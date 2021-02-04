package view;

import java.util.Scanner;

import dao.Session;
import dao.UserDAO;

public class UserEditView {
	public UserEditView() {
		UserDAO udao = new UserDAO();
		Scanner sc = new Scanner(System.in);
		boolean flag = false;
		while(true) {
			System.out.println("������ ����\n1. ��й�ȣ\n2. �̸���\n3. �ڵ��� ��ȣ\n4. �ּ�\n5. ȸ�� Ż��\n6. ���ư���");
			int choice = sc.nextInt();
			if(choice==6) {break;}
			switch(choice) {
			case 1:
				//��й�ȣ
				System.out.print("���ο� ��й�ȣ : ");
				String newData = sc.next();
				udao.modify(0,udao.encrypt(newData));
				break;
			case 2:
				//�̸���
				System.out.print("���ο� �̸��� : ");
				newData = sc.next();
				udao.modify(1,newData);
				break;
			case 3:
				//�ڵ��� ��ȣ
				System.out.print("���ο� ���� : ");
				newData = sc.next();
				udao.modify(2,newData);
				break;
			case 4:
				//�ּ�
				sc = new Scanner(System.in);
				System.out.print("���ο� ���� : ");
				newData = sc.nextLine();
				udao.modify(3,newData);
				break;
			case 5:
				//ȸ��Ż��
				int cnt=0;
				while(true) {
					System.out.println("1. ��й�ȣ �Է�\n2. ���ư���");
					choice = sc.nextInt();
					if(cnt==3) {cnt=0;}
					if(choice==2) {break;}
					switch(choice) {
					case 1:
						System.out.print("��й�ȣ ���Է� : ");
						String userpw = sc.next();
						if(udao.leaveId(userpw)) {
							System.out.println("�̿��� �ּż� �����մϴ�.");
							Session.put("session_id", null);
							new Index();
						}else {
							if (cnt==0) {
								System.out.println("��й�ȣ�� �߸��Ǿ����ϴ�. �ٽ� �õ��� �ּ���.");
							}else if(cnt<2){
								System.out.println("��й�ȣ�� �߸��Ǿ����ϴ�. ��й�ȣ�� �缳���Ͻðڽ��ϱ�?\n1. ��\t2. �ƴϿ�");
								choice=sc.nextInt();
								switch(choice) {
								case 1:
									System.out.print("���̵�  : ");
									String userid = sc.next();
									udao.moveTopw(userid);
									break;
								case 2:
									break;
								default:
									System.out.println("�߸� �Է��ϼ̽��ϴ�.");
									break;
								}
							}else if(cnt==2) {
								System.out.println("��й�ȣ�� 3�� Ʋ���̽��ϴ�. ��й�ȣ �缳��â���� ���ڽ��ϴ�.");
								System.out.print("���̵�  : ");
								String userid = sc.next();
								udao.moveTopw(userid);
								break;
							}
							cnt++;
						}
						break;
					}
				}
			}
		}
	}
}
