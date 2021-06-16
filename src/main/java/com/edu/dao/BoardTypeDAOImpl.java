package com.edu.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.edu.vo.BoardTypeVO;

/**
 * 이 클래스는 게시판 생성관리 쿼리의 인터페이스를 구현메서드가 있는 클래스입니다.
 * @author 방재혁
 *
 */

@Repository
public class BoardTypeDAOImpl implements IF_BoardTypeDAO {
	//sqlSesseion 템플릿 의존성을 주입
	@Inject //자바8부터 신규로나온 애노테이션입니다.
	private SqlSession sqlSession;

	@Override
	public void deleteBoardType(String board_type) throws Exception {
		// 매퍼쿼리를 실행 sqlSession템플릿(틀)을 실행
		sqlSession.delete("boardTypeMapper.deleteBoardType", board_type);
		// 서식 sqlSession.~템플릿메서드("SQL쿼리위치",데이터객체변수);
	}

	@Override
	public void updateBoardType(BoardTypeVO boardTypeVO) throws Exception {
		// 아래 주석과 동일
		sqlSession.update("boardTypeMapper.updateBoardType", boardTypeVO);
		
	}

	@Override
	public BoardTypeVO readBoardType(String board_type) throws Exception {
		// 아래 주석과 동일
		return sqlSession.selectOne("boardTypeMapper.readBoardType", board_type);
	}

	@Override
	public void insertBoardType(BoardTypeVO boardTypeVO) throws Exception {
		// 아래 주석과 동일
		sqlSession.insert("boardTypeMapper.insertBoardType", boardTypeVO);
		
	}

	@Override
	public List<BoardTypeVO> selectedBoardType() throws Exception {
		// 매퍼쿼리를 실행 sqlSession템플릿(틀)을 실행
		return sqlSession.selectList("boardTypeMapper.selectBoardType");
	}

}
