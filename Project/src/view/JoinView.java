package view;

import java.util.Scanner;

import dao.UserDAO;
import dto.UserDTO;

public class JoinView {
	public JoinView() {
		// ���̵� ��й�ȣ �̸� ���� �ڵ�����ȣ �ּ� ��õ�� �ŷ���
		Scanner sc = new Scanner(System.in);
		UserDAO udao = new UserDAO();
		System.out.print("���̵� : ");
		String userid = sc.next();
		//���̵� üũ��°��� ��� �̹Ƿ� dao���ٰ� ������ �س���
		//View�ܿ����� �ܼ��ϰ� ȣ���Ͽ� ����ϱ⸸ �Ѵ�.
/*	private String userid;
	private String userpw;
	private String username;
	private String email;
	private String userphone;
	private String useraddr;
	private String bday;
	private int coupon;
	private int money;*/
		if (!udao.checkDup(userid)) {
			System.out.println("�ߺ��� ���̵� �ֽ��ϴ�. �ٽ� �õ��� �ּ���.");
		} else {
			System.out.print("��й�ȣ : ");
			String userpw = sc.next();
			System.out.print("�̸� : ");
			String username = sc.next();
			System.out.print("���� : ");
			String email = sc.next();
			System.out.print("�ڵ��� ��ȣ : ");
			String userphone = sc.next();
			System.out.print("�ּ� : ");
			sc.nextLine();
			String useraddr = sc.nextLine();
			System.out.print("�ڵ��� ��ȣ : ");
			String bday = sc.next();
			//������ 6���� �Ǳ� ������ join�̶�� �޼ҵ忡 �Ѱ��ַ��� �Ű�������
			//6���� �ʿ��ϴ�. ���� �ʹ� �����͸� �ѱ�� ����� ������
			//UserDTO�� �������ش�.
			UserDTO newUser = new UserDTO(userid, userpw, username, email, userphone, useraddr,bday);
			
		}

	}
}
