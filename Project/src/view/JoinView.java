package view;

import java.util.Scanner;

import dao.MovieDAO;
import dao.UserDAO;
import dto.UserDTO;


public class JoinView {
	public JoinView() {
		Scanner sc = new Scanner(System.in);
		UserDAO udao = new UserDAO();
		MovieDAO mdao = new MovieDAO();
		String userid;
		String userpw;
		String useremail;
		String bday;
		String checkpassView = "��밡���� ��й�ȣ�Դϴ�.";
		String checkemailView = "��밡���� �̸��� �Դϴ�.";
			
		//���̵�
		while(true) {
			System.out.print("���̵� : ");
			userid = sc.next();
			if (udao.checkID(userid)) {
				System.out.println("��밡���� ���̵��Դϴ�.");
				break;
			}else {
				System.out.println("�ߺ��� ���̵� �ֽ��ϴ�.");
			}
		}
		//��й�ȣ
		while(true) {
			System.out.print("��й�ȣ : ");
			userpw = sc.next();
			String checkpass = udao.checkPass(userpw);
			System.out.println(checkpass);
			if (checkpass.equals(checkpassView)) {
				break;
			}
		}

		//�̸�
		System.out.print("�̸� : ");
		String username = sc.next();
		//�̸���
		while (true) {
			
			System.out.print( "�̸��� : ");
			useremail = sc.next();
			if (!udao.dupEmail(useremail).equals("0")) {
				System.out.println("�����ϴ� �̸����Դϴ�.");
			}else {
				String checkemail = udao.checkEmail(useremail);
				System.out.println(checkemail);
				if (checkemail.equals(checkemailView)) {
					break;
				}
				
			}
			
		}
		//�ڵ��� ��ȣ
		System.out.print("�ڵ��� ��ȣ : ");
		String userphone = sc.next();
		//�ּ�
		System.out.print("�ּ� : ");
		sc.nextLine();
		String useraddr = sc.nextLine();
		//����
		while(true) {
			System.out.print("���� : (�� 19900502) ");
			bday = sc.next();
			if (bday.length()==8) {
				break;
			}else {
				System.out.println("�ٽ� �Է����ֽʽÿ�.");
			}
		}
		//����
		int usercoupon = 0;
		//����
		int usermoney = 0;
		
		UserDTO newUser = new UserDTO(userid, userpw, username, useremail, userphone, useraddr, bday, usermoney);
		udao.join(newUser);
		System.out.println("ȸ������ ����!");
		System.out.println(userid + "�� ������ ȯ���մϴ�.");
		String coupon_name = mdao.coupon();
		mdao.insertCoupon(userid, coupon_name);
		System.out.println("ȸ������ ������ "+coupon_name+"������ ���޵Ǿ����ϴ�.");
	}
}


