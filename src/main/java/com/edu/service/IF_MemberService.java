package com.edu.service;

import java.util.List;

import com.edu.vo.MemberVO;
import com.edu.vo.PageVO;

/**
 * 이 인터페이스는 회원관리에 관련된 Service의 명세를 모아놓은 파일.
 * 여기는 {구현내용}은 없음
 * @author 방재혁
 *
 */
public interface IF_MemberService {
	public List<MemberVO> selectMember(PageVO pageVO) throws Exception;
	//회원의 전체명수를 나타냅니다.
	public int countMember() throws Exception;
}
