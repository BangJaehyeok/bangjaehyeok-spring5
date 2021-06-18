package com.edu.dao;

import java.util.List;

import com.edu.vo.BoardVO;
import com.edu.vo.PageVO;

/**
 * 이 클래스는 boardMapper.xml을 접근하기위한 DataAccessObject입니다.
 * @author 방재혁
 *
 */
public interface IF_BoardDAO {
	public int countBoard(PageVO pageVO) throws Exception;
	//기본CRUD(아래)
	public void deleteBoard(int bno) throws Exception;
	public void updateBoard(BoardVO boardVO) throws Exception;
	public BoardVO readBoard(int bno)throws Exception;
	public void insertBoard(BoardVO boardVO) throws Exception;
	public List<BoardVO> selectBoard(PageVO pageVO) throws Exception;
	
}
