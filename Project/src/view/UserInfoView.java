package view;

import java.util.Scanner;

import dao.Session;
import dao.UserDAO;

public class UserInfoView {
	public UserInfoView(){
		Scanner sc = new Scanner(System.in);
		int choice = 0;
		UserDAO udao = new UserDAO();
		while(true) {
			System.out.println("1. �������� ����\n2. �����ϱ�\n3. ������\n4. ������ Ƽ�� ����\n5. ���� ����\n6. �ڷΰ���\n7. �α׾ƿ�");
			choice = sc.nextInt();
			if (choice==6) {break;
			}else if(choice==7) {
				//�α׾ƿ�
					System.out.println("�α׾ƿ� �մϴ�.");
					Session.put("session_id", null);
					new Index();
				}
			
			switch(choice) {
			case 1:
				//�������� ����
				new UserEditView();
				break;
			case 2:
				//�����ϱ�
				System.out.println("�����Ͻ� �ݾ��� �Է����ּ���.");
				int addMoney = sc.nextInt();
				String userId = Session.get("session_id");
				int currentM =udao.charge(addMoney,userId);
				if (currentM<0) {
					System.out.println("�������� �ٽ� �õ����ּ���.");
				}else {
					System.out.println(addMoney+"�� �����Ǿ����ϴ�.");
					System.out.println("���� �ܾ� : "+currentM+"��");
				}
				
				break;
			case 3:
				//������
				break;
			case 4:
				//������ Ƽ�� ����
				break;
			case 5:
				//���� ����
				break;
			}
		}
	}
}
