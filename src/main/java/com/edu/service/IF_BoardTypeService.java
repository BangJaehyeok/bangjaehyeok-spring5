package com.edu.service;

import java.util.List;

import com.edu.vo.BoardTypeVO;

/**
 * 이 인터페이스는 DAO클래스를 접근하는 서비스 입니다.
 * 내용은 DAO와 똑같으나, 나중에 DAO 10개 -> 서비스 5개로 처리가 가능하게 되는 경우가 있다.
 * 스프링부트는 위 과정이 1개로 합쳐져 있어서 상대적으로 간단한 프로젝트에 부트를 사용한다.
 * @author 방재혁
 *
 */
public interface IF_BoardTypeService {
	//CRUD 메서드 명세만 생성(아래 5개)
	public void deleteBoardType(String board_type) throws Exception;
	public void updateBoardType(BoardTypeVO boardTypeVO) throws Exception; 
	public BoardTypeVO readBoardType(String board_type) throws Exception;
	public void insertBoardType(BoardTypeVO boardTypeVO) throws Exception;
	//BoardTypeVO 1개의 레코드를 저장한 클래스를 List<제네릭타입>형 다중레코드로 받습니다.
	public List<BoardTypeVO> selectedBoardType() throws Exception;
}
