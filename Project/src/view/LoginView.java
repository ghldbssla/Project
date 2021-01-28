package view;

import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

import dao.Session;
import dao.UserDAO;
import dto.UserDTO;

public class LoginView {
	public LoginView() {
		Scanner sc = new Scanner(System.in);
		UserDAO udao = new UserDAO();
		
		//���̵�, ��й�ȣ �Է�
		System.out.print("���̵� : ");
		String userid = sc.next();
		System.out.print("��й�ȣ : ");
		String userpw = sc.next();
		
		//session ��ü�� user �Ѱ��ֱ�
		UserDTO session = udao.login(userid,userpw);
		
		//�α��� ����
		if(session==null) {
			//�Է��� ���̵�� ��й�ȣ�� inputdata�� ����
			HashMap<String, String> inputdata = new HashMap<String, String>();
			inputdata.put(userid, userpw);
			Collection<String> inputvalue = inputdata.values();

			//logincheck���� ã�� �����ϴ� ���̵�� ��й�ȣ�� ��� set
			HashMap<String, String> existdata = udao.logincheck(userid, userpw);
			Set<String> key = existdata.keySet();
			Collection<String> value = existdata.values();
			
			if (!key.equals(inputdata.keySet())) {
				System.out.println("�������� �ʴ� ���̵��Դϴ�.\n");
				new LoginFailView();
			}
			else if (!value.equals(inputvalue)) {
				System.out.println("��й�ȣ�� �߸��Է��ϼ̽��ϴ�.\n");
				new LoginFailView();
			}
			
//			if (udao.idfail(userid, userpw)) {
//				System.out.println("�������� �ʴ� ���̵��Դϴ�.");
//				new LoginFailView();
//			}
//			if(udao.pwfail(userid, userpw)) {
//				System.out.println("��й�ȣ�� �߸��Է��ϼ̽��ϴ�.");
//				new LoginFailView();
//			}
//			if(!udao.idfail(userid, userpw)&!udao.pwfail(userid, userpw)) {
//				System.out.println("�������� �ʴ� ���̵�� ��й�ȣ�Դϴ�.");
//				new LoginFailView();
//			}
		}
		
		//�α��� ����
		else {
			Session.put("session_id", session.getUserid());
			System.out.println(userid+"�� �α��� ����!\n");
			new MainView();
		}
	}
}
