package com.edu.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.edu.vo.AttachVO;
import com.edu.vo.BoardVO;
import com.edu.vo.PageVO;

/**
 * 이 클래스는 게시물 CRUD를 구현하는 DAO클래스입니다.
 * @author 방재혁
 *
 */
@Repository
public class BoardDAOImpl implements IF_BoardDAO {
	private Logger logger = LoggerFactory.getLogger(BoardDAOImpl.class);
	@Inject
	private SqlSession sqlSession;//sqlSession템플릿에는 insert,update()...등이 있다.
	@Override
	public void deleteAttachAll(Integer bno) throws Exception {
		//  sqlSession 템플릿을 사용해서 매퍼쿼리를 실행- 첨부파일
		sqlSession.delete("boardMapper.deleteAttachAll", bno);		
	}

	@Override
	public void deleteAttach(String save_file_name) throws Exception {
		// sqlSession 템플릿을 사용해서 매퍼쿼리를 실행- 첨부파일
		sqlSession.delete("boardMapper.deleteAttach", save_file_name);		
	}

	@Override
	public void updateAttach(AttachVO attachVO) throws Exception {
		// sqlSession 템플릿을 사용해서 매퍼쿼리를 실행- 메서드명은 update지만 실제쿼리는 insert
		sqlSession.insert("boardMapper.updateAttach", attachVO);
	}

	@Override
	public void insertAttach(AttachVO attachVO) throws Exception {
		// sqlSession 템플릿을 사용해서 매퍼쿼리를 실행- 첨부파일
		sqlSession.insert("boardMapper.insertAttach", attachVO);
	}

	@Override
	public List<AttachVO> readAttach(Integer bno) throws Exception {
		// sqlSession 템플릿을 사용해서 매퍼쿼리를 실행 - 첨부파일
		return sqlSession.selectList("boardMapper.readAttach", bno);
	}

	@Override
	public void updateViewCount(Integer bno) throws Exception {
		// sqlSession 템플릿을 사용해서 매퍼쿼리를 실행
		sqlSession.update("boardMapper.updateViewCount", bno);
	}

	@Override
	public int countBoard(PageVO pageVO) throws Exception {
		// sqlSession 템플릿을 사용해서 매퍼쿼리를 실행
		return sqlSession.selectOne("boardMapper.countBoard", pageVO);
	}

	@Override
	public void deleteBoard(int bno) throws Exception {
		// TODO sqlSession 템플릿을 사용해서 매퍼쿼리를 실행
		sqlSession.delete("boardMapper.deleteBoard", bno);
		
	}

	@Override
	public void updateBoard(BoardVO boardVO) throws Exception {
		// sqlSession 템플릿을 사용해서 매퍼쿼리를 실행
		sqlSession.update("boardMapper.updateBoard", boardVO);
		
	}

	@Override
	public BoardVO readBoard(int bno) throws Exception {
		// sqlSession 템플릿을 사용해서 매퍼쿼리를 실행
		return sqlSession.selectOne("boardMapper.readBoard", bno);
	}

	@Override
	public int insertBoard(BoardVO boardVO) throws Exception {
		// sqlSession 템플릿을 사용해서 매퍼쿼리를 실행 + 게시물 입력 후 반환값으로 bno를 받습니다.
		sqlSession.insert("boardMapper.insertBoard", boardVO);
		logger.info("디버그: " + boardVO.getBno());
		return boardVO.getBno();
	}

	@Override
	public List<BoardVO> selectBoard(PageVO pageVO) throws Exception {
		// TODO sqlSession 템플릿을 사용해서 매퍼쿼리를 실행
		return sqlSession.selectList("boardMapper.selectBoard", pageVO);
	}

}