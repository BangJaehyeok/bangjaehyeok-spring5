package com.edu.aop;

import java.util.List;

import javax.inject.Inject;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.edu.service.IF_BoardTypeService;
import com.edu.vo.BoardTypeVO;

/**
 * 이 클래스는 AOP기능 중 @Aspec 과 @ControllerAdvice 로 구현됩니다.
 * @author 방재혁
 *
 */
@Aspect
@ControllerAdvice
public class AspectAdvice {
	@Inject
	private IF_BoardTypeService boardTypeService;
	
	//이 메서드는 컨트롤러로 입출력되는 메서드가 실행 전에 값을 생성해서 model객체에 담아서 jsp로 자료를 전송
	//위 @ControllerAdvice를 이용해서 컨트롤러의 모든 메서드가 실행되기 호출만되면 아래 메서드가 자동실행(콜백함수)
	@ModelAttribute("listBoardTypeVO")
	public List<BoardTypeVO> listBoardTypeVO() throws Exception{
		
		return boardTypeService.selectedBoardType();
	}
}
