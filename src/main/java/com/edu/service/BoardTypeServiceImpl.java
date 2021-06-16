package com.edu.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.edu.dao.IF_BoardTypeDAO;
import com.edu.vo.BoardTypeVO;

/**
 * 이 클래스는 DAO클래스에 접근하기 위한 서비스 클래스입니다.
 * @author 방재혁
 * 
 */
@Service //애노테이션을 붙여야지만 스프링빈으로 등록된다.
public class BoardTypeServiceImpl implements IF_BoardTypeService {
	@Inject
	private IF_BoardTypeDAO boardTypeDAO;
	
	@Override //부모super 인터페이스의 메서드를 상속받아서 재정의 override 한다.
	public void deleteBoardType(String board_type) throws Exception {
		// DAO클래스 객체를 이용해서 메서드를 호출(실행)
		boardTypeDAO.deleteBoardType(board_type);
	}

	@Override
	public void updateBoardType(BoardTypeVO boardTypeVO) throws Exception {
		// DAO클래스 객체를 이용해서 메서드를 호출(실행)
		boardTypeDAO.updateBoardType(boardTypeVO);
	}

	@Override
	public BoardTypeVO readBoardType(String board_type) throws Exception {
		// 실행결과반환 readBoardType(아래 실행에 필요한 변수 = 매개변수,파라미터,인자,아규먼트)
		return boardTypeDAO.readBoardType(board_type);
	}

	@Override
	public void insertBoardType(BoardTypeVO boardTypeVO) throws Exception {
		// TODO 아래 주석과 동일
		boardTypeDAO.insertBoardType(boardTypeVO);
		
	}

	@Override
	public List<BoardTypeVO> selectedBoardType() throws Exception {
		// DAO클래스 객체를 이용해서 메서드를 호출(실행)
		return boardTypeDAO.selectedBoardType();
	}

}
