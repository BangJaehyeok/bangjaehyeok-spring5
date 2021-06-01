package com.edu.controller;

//외부 라이브러리(모듈) 사용 = import
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 이 클래스는 MVC웹프로젝트를 최초로 생성시 자동으로 생성되는 클래스
 * 웹브라우저의 요청사항을 view단(jsp)에 연결시켜주는 클래스 @Controller.
 * 스프링에서 관리하는 클래스를 스프링빈(bean;콩) = 스프링빈을 명시 @Controller 애노테이션
 * Beans 그래프로 프로젝트의 규모를 확인할 수 있다.
 * 스프링이 관리하는 클래스(=스프링 빈)는 파일 아이콘에 S가 붙는다.
 */

@Controller
public class HomeController {
	//스프링빈(클래스)에서는 로거로 디버그를 합니다. = 로거객체를 만든다.
	//로그중 slf4j(Spring Log For Java)
	//private Logger logger = Logger.
	//private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * 사용자요청(웹브라우저)을 받아서=@RequestMapping 인터페이스를 사용해서 메서드명을 스프링이 구현합니다. 라우터(router)
	 * return 값으로 view(jsp)를 선택해서 작업한 결과를 변수에 담아서 화면에 전송 후  결과를 렌더링(표시)합니다.
	 * 폼 자료전송시 post(자료숨김), get(자료노출-쿼리스트링, ?있는자료전송)
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String homepage(Model model) {//콜백 메서드, 자동실행됨.	
		String jspVar = "@서비스(DB)에서 처리한 결과";
		model.addAttribute("jspObject", jspVar );//home.jsp파일로 자료를 전송(스프링)하는 기능 : model 인터페이스객체
		return "home";//확장자가 생략 .jsp가 생략됨.
	}
	
}
