package com.edu.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.edu.vo.MemberVO;
import com.edu.vo.PageVO;


/**
 * 이 클래스는 IF_memberDAO 인터페이스를 구현하는 클래스입니다.
 * implements 키워드로 상속을 받습니다.
 * 단, DAO기능의 구현클래스는 스프링빈으로 등록이 필요. 그래서, @Repository
 * @author 방재혁
 */
@Repository
public class MemberDAOImpl implements IF_MemberDAO {
	@Inject //SqlSession 스프링빈을 객체로 만든다.= 의존성을 주입한다.
	private SqlSession sqlSession;

	@Override //부모 인터페이스의 메서드를 상속해서 재정의합니다.
	public List<MemberVO> selectMember(PageVO pageVO) throws Exception {
		// SqlSession의 메서드를 이용해서 매퍼 쿼리를 사용
		List<MemberVO> listMember = sqlSession.selectList("memberMapper.selectMember", pageVO);
		return listMember;
	}

	@Override
	public int countMember() throws Exception {
		// SqlSession빈의 메서드를 이용해서 매퍼쿼리를 실행(아래)
		int totalCount = sqlSession.selectOne("memberMapper.countMember");
		return totalCount;
	}
}
