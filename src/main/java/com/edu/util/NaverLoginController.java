package com.edu.util;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;

/*
 * 이 클래스는 네이버RestAPI서버 URL을 생성하는 기능의 클래스입니다.
 * 방재혁
 */

//sns.properties 만드는 목적 : 로컬과 헤로쿠의 인증ID와 Secret을 소스에서 변경하기보단, 전역변수로 만들면 편함.
@PropertySource("classpath:properties/sns.properties")
@Controller //스프링빈으로 등록되면, 인젝션으로 객체를 사용가능하게됨.
public class NaverLoginController {
	@Value("${SnsClientID}")//스프링빈의 전역변수를 가져올때@Resource와 비교하면됨.
	private String CLIENT_ID;//properties에 있는 전역변수값을 클래스변수값으로 사용
	@Value("${SnsClientSecret}")
	private String CLIENT_SECRET;
	@Value("${SnsCallbackUrl}")
	private String REDIRECT_URL;//위 변수를 이용해서 만든 RestAPI URL이후 인증성공후 이동할 URL
	//아래는 네이버에서 지정한 상수값을 사용해야함.
	//대문자로 타이핑하는 변수는 파이널이거나 스태틱변수일때 사용. == 이 클래스에서만 사용(파이널), 끝내는 변수(스태틱)
	private final static String SESSION_STATE = "oauth_state";
	//네이버에서 제공해주는 이름, 이메일 정보를 가져올수 있는 RestAPI
	private final static String PROFILE_API_URL = "https://openapi.naver.com/v1/nid/me";
	
	//네이버에서 제공하는 인증URL구하는 메서드(사용자 로그인폼에 $url로 제공하게됨)
	public String getAuthorizationUrl(HttpSession session) {
		// 세션 유효성 검증을 위한 난수생성(아래)
		String state = generateRandomString();
		// 생성한 난수 state값을 session변수에 저장
		setSession(session,state);
		//Scribe가입을 담당하는 외부모듈(OAuth2.0서비스)에서 제공하는 인증URL생성
		OAuth20Service oauthService = new ServiceBuilder()
				.apiKey(CLIENT_ID)
				.apiSecret(CLIENT_SECRET)
				.callback(REDIRECT_URL)
				.state(state)
				.build(NaverLoginApi.instance());
		return oauthService.getAuthorizationUrl();
		//위 리턴값으로 네아로의 인증 URL이 생성됨.
	}

	private void setSession(HttpSession session, String state) {
		// httpsession 클래스에 데이터를 저장
		session.setAttribute("SESSION_STATE", state);
	}

	private String generateRandomString() {
		// 세션 유효성 검증을 위한 난수생성기
		return UUID.randomUUID().toString();
	}

	public OAuth2AccessToken getAccessToken(HttpSession session, String code, String state) throws IOException {
		// 네이버 인증 RestAPI에서 인증데이터인 토큰값 가져오기
		// (동작전)현재 컨트롤러에서 발생된 세션의 state값 (동작후)콜백URL로 요청시 발생한 난수값과 과 비교
		String sessionState = getSession(session);
		if(StringUtils.pathEquals(sessionState, state)) {
			//동작 전,후의 값이 같다면, 인증토큰을 구현합니다.
			OAuth20Service oauthService = new ServiceBuilder()
					.apiKey(CLIENT_ID)
					.apiSecret(CLIENT_SECRET)
					.callback(REDIRECT_URL)
					.state(state)
					.build(NaverLoginApi.instance());
			//Scribe pom의 외부모듈에서 제공하는 기능으로 토큰을 생성
			OAuth2AccessToken accessToken = oauthService.getAccessToken(code);
			//code는 네이버에서 반환값으로 주는 인증성공/실패
			return accessToken;
		}
		return null;
	}

	private String getSession(HttpSession session) {
		// http에서 session 값 가져오기
		return (String) session.getAttribute(SESSION_STATE);
	}

	public String getUserProfile(OAuth2AccessToken oauthToken) throws IOException {
		// 위 인증데이터인 토큰값으로 네이버에서 프로필내용 가져오기
		OAuth20Service oauthService = new ServiceBuilder()
				.apiKey(CLIENT_ID)
				.apiSecret(CLIENT_SECRET)
				.callback(REDIRECT_URL)
				.build(NaverLoginApi.instance());
		OAuthRequest request = new OAuthRequest
				(Verb.GET,PROFILE_API_URL,oauthService);//프로필 가져오는 객체생성
		oauthService.signRequest(oauthToken, request);
		Response response = request.send();//프로필 가져오는 객체 실행
		return response.getBody();
	}
}
