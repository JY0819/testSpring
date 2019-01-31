package com.kh.tsp.common;

import java.util.UUID;

public class CommonUtils {
	public static String getRandomString() {
		
		// 32비트의 랜덤 문자를 생성해주는 메소드
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
