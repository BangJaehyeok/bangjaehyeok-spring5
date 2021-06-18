package com.edu.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.edu.dao.IF_BoardDAO;
import com.edu.vo.AttachVO;
import com.edu.vo.BoardVO;
import com.edu.vo.PageVO;

/**
 * 이 클래스는 DAO메서드를 호출하는 기능을 한다.
 * @author 방재혁
 *
 */
@Service //애노테이션을 붙이면 스프링빈으로 등록된다.
public class BoardServiceImpl implements IF_BoardService {
	@Inject
	private IF_BoardDAO boardDAO;
	@Override
	public List<AttachVO> readAttach(Integer bno) throws Exception {
		// 첨부파일 List형으로 조회 DAO호출
		return boardDAO.readAttach(bno);
	}

	@Override
	public int countBoard(PageVO pageVO) throws Exception {
		// 페이징처리시 PageVO의 totalCount변수에 사용될 값을 리턴값으로 받음.
		return boardDAO.countBoard(pageVO);
	}

	@Override
	public void deleteBoard(int bno) throws Exception {
		// 게시물 삭제할때, 3개의 메서드가 실행(댓글+첨부파일 삭제 -> 게시물이 삭제)
		// 트랜잭션이 필요한 순간. 작업순서 1.첨부파일삭제 2.게시물삭제시 에러O 삭제가 안됨.
		// 위와같은 상황을 방지하는 목적의 @Transantional 애노테이션을 사용합니다.
		// 댓글삭제도 *나중에 추가
		//특이사항 : 첨부파일 DB만 삭제해서 해결+실제업로드된 파일을 삭제가 필요 *나중에 추가
		boardDAO.deleteAttachAll(bno);
		boardDAO.deleteBoard(bno);
		
	}

	@Override
	public void updateBoard(BoardVO boardVO) throws Exception {
		// 첨부파일 처리 포함 게시물 업데이트 2개의 메서드 실행이 필요
		boardDAO.updateAttach(null);
		boardDAO.updateBoard(boardVO);
		
	}

	@Override
	public BoardVO readBoard(int bno) throws Exception {
		// 게시물 상세보기시 readBoard -> updateViewCount 2개의 메서드가 필요 그래서 트랜지션이 필요
		BoardVO boardVO = boardDAO.readBoard(bno);
		boardDAO.updateViewCount(bno);
		return boardVO;
	}

	@Override
	public void insertBoard(BoardVO boardVO) throws Exception {
		// [부모]게시물 insertBoard -> [자식]첨부파일 insertAttach 실행 트랜잭션이 필요
		//게시물 등록 + 반환값으로 bno 추가
		int bno = boardDAO.insertBoard(boardVO);
		//첨부파일 등록 : 1개 이상일때 가정해서 처리
		String[] save_file_names=boardVO.getSave_file_names();//폴더에 저장용 파일명들
		String[] real_file_names=boardVO.getReal_file_names();//UI용 배열 파일명들
		if(save_file_names == null) { return; }//리턴이 발생되면, 이후 실행안됨.
		//첨부파일이 null이 아닐때 아래가 진행됩니다.
		int index = 0;
		String real_file_name = "";//UI용 1개 파일명
		AttachVO attachVO = new AttachVO();
		for(String save_file_name:save_file_names) {//첨부파일 개수만큼 반복진행
			real_file_name = real_file_names[index];
			attachVO.setBno(bno);
			attachVO.setReal_file_name(real_file_name);
			attachVO.setSave_file_name(save_file_name);
			boardDAO.insertAttach(attachVO);
		}
	}

	@Override
	public List<BoardVO> selectBoard(PageVO pageVO) throws Exception {
		// DAO 1개 호출.
		return boardDAO.selectBoard(pageVO);
	}

}
