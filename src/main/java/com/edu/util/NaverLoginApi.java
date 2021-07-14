package com.edu.util;

import com.github.scribejava.core.builder.api.DefaultApi20;

public class NaverLoginApi extends DefaultApi20 {

	@Override
	public String getAccessTokenEndpoint() {
		// 토큰체크용 Endpoint는 네이버의 Rest-Api URL을 명시합니다.
		//(예, 중복ID체크URL /id_check?~)
		return "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code";
	}

	@Override
	protected String getAuthorizationBaseUrl() {
		// 인증체크용 RestAPI URL
		return "https://nid.naver.com/oauth2.0/authorize";
	}
	//싱글톤 인스턴스 객체를 생성하기 위해서 인스턴스 홀더 클래스 상수(static,final)변수를 생성합니다.
	private static class InstanceHolder{
		private static final NaverLoginApi INSTANCE = new NaverLoginApi();
	}
	public static NaverLoginApi instance() {
		// 싱글톤으로 인스턴트 객체를 생성하는 방식: 객체를 1회만 생성하기 위해서
		return InstanceHolder.INSTANCE;
	}

}
