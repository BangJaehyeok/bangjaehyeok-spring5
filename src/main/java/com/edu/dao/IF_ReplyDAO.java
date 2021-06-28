package com.edu.dao;

import java.util.List;

import com.edu.vo.PageVO;
import com.edu.vo.ReplyVO;

/*
 * 이 인터페이스는 댓글쿼리에 입출력하는 메서드명만 존재합니다.
 * 방재혁
 */
public interface IF_ReplyDAO {
	public void deleteReplyAll(Integer bno) throws Exception;
	public void deleteReply(ReplyVO replyVO) throws Exception;
	public void updateReply(ReplyVO replyVO) throws Exception;
	public void replyCountUpdate(Integer bno, int count) throws Exception;
	public void insertReply(ReplyVO replyVO) throws Exception;//replyVO는 jsp폼에서 받습니다.
	public int countReply(Integer bno) throws Exception;
	public List<ReplyVO> selectReply(Integer bno, PageVO pageVO) throws Exception;
}
