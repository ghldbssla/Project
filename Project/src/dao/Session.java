package dao;

import java.util.HashMap;

public class Session {
	//세션에 저장할 정보들이 key-value 형식으로 저장될 HashMap을 세션으로 이용한다.
	private static HashMap<String, String> session = new HashMap<>();
	
	//스태틱 메소드들로 세팅하고 값을 가져온다.
	public static void put(String key,String value) {
		session.put(key, value);
	}
	public static String get(String key) {
		return session.get(key);
	}
}
