package view;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

import dao.MovieDAO;
import dao.Session;
import dao.UserDAO;
import dto.UserDTO;

public class LoginView {
	public LoginView(int page) {
		Scanner sc = new Scanner(System.in);
		UserDAO udao = new UserDAO();
		MovieDAO mdao = new MovieDAO();
		
		//���̵�, ��й�ȣ �Է�
		System.out.print("���̵� : ");
		String userid = sc.next();
		System.out.print("��й�ȣ : ");
		String userpw = sc.next();
		
		//session ��ü�� user �Ѱ��ֱ�
		UserDTO session = udao.login(userid, userpw);
		
		//�α��� ����
		if(session==null) {
			String existid = udao.logincheck(userid);
			//���̵� x
			if(!existid.equals(userid)) {				
				System.out.println("�������� �ʴ� �����Դϴ�.");
				new LoginFailView(page);
			}//���̵� o
			else if (existid.equals(userid)) {
				System.out.println("��й�ȣ�� �߸��Է��ϼ̽��ϴ�.");
				new LoginFailView(page);
			}
		}
		
		//�α��� ����
		else {
			Session.put("session_id", session.getUserid());
			System.out.println(userid+"�� �α��� ����!\n");
			//�޾ƿ� ���ڰ��� ���� ��� �����ֱ�
			if(page==1) {
				//��ȭ����
				new TicketView();
			}
			else if(page==0) {
				//���κ�
				new MainView();
			}
			else if(page==-1) {
				//WatchListView()���� ��α��λ��·� ���ϱ� �����ϸ� �α��� ������ ���Ҽ� �ְ� �ϴ°�
				System.out.println("���ϱ⿡ �߰��� ��ȭ������ �Է����ּ���.");
				sc.nextLine();
				String title = sc.nextLine();
				mdao.create(title);	
				//**���ϱ� �Ϸ�! �� �Ŀ� �ٸ� �������� ����������ϳ�?
			}
		}
	}

}
