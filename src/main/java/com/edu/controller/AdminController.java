package com.edu.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.edu.service.IF_MemberService;
import com.edu.vo.MemberVO;
import com.edu.vo.PageVO;

/**
 * 이 클래스는 Admin관리자단을 접근하는 클래스
 * 변수 Object를 만들어서 jsp로 전송 <-> jsp 폼값을 받아서 Object로 처리
 * @author 김일국
 *
 */
@Controller
public class AdminController {
	//컨트롤러수정하면 자동로딩(auto컴파일)
	//디버그용 로그객체 생성(아래)
	private Logger logger = LoggerFactory.getLogger(AdminController.class);
	//이 메서드는 회원목록을 출력하는 jsp와 매핑이 됩니다.
	@Inject
	private IF_MemberService memberService;
	
	//아래 경로는 수정처리를 호출=DB를 변경처리함.
	@RequestMapping(value="/admin/member/member_update", method=RequestMethod.POST)
	public String updateMember() throws Exception {
		
		return null;
	}
	//아래 경로는 수정폼을 호출(렌더링만, 화면에 출력만.)
	@RequestMapping(value="/admin/member/member_update_form", method=RequestMethod.POST)
	public String updateMemberForm() throws Exception {
		
		return "/admin/member/member_update";//상대경로
	}
	@RequestMapping(value="/admin/member/member_delete", method=RequestMethod.POST)
	public String deleteMember(MemberVO memberVO) throws Exception {
		logger.info("디버그: " + memberVO.toString());
		//MemberVO memberVO(클래스형 변수) : String user_id(String형 변수)과 같은 방식이다. 즉, memberVO는 변수이고 MemberVO유형이라는 것이다.
		
		//이 메서드는 회원상세보기 페이지에서 삭제버튼클릭시 전송받은 memberVO값을 이용해서 삭제를 구현(아래)
		memberService.deleteMember(memberVO.getUser_id()); //삭제쿼리가 실행됨
		//return "admin/member/member_list";//삭제 후 이동할 jsp경로 설정
		//위 방식대로하면, 새로고침시 /admin/member/member_delete가 무한실행된다.
		//게시판 테러를 방지하기위해 쿼리를 작업 후 이동할 때는 redirect(다시 접속)라는 명령을 사용합니다.
		return "redirect:/admin/member/member_list"; //주의점 : redirect는 절대경로를 사용.
	}
	@RequestMapping(value="/admin/member/member_view", method=RequestMethod.GET)
	public String viewMemberForm(Model model, @RequestParam("user_id")String user_id, @ModelAttribute("pageVO")PageVO pageVO) throws Exception {
	//페이지진입시 받은 클래스 변수값 PageVO pageVO	
		/**
		 * 이 메서드는 리스트페이지에서 상세보기로 이동할때 1개의 레코드값을 보여주는 구현을 한다.
		 * Junit에서 테스트한 readMember 방식을 이용.
		 * 다른점은 JUnit에서는 식별자 ID를 강제로 지정했지만, 
		 * 이 메서드에서는 @RequestParam인터페이스를 이용해서 식별자값을 받음
		 */
		//위 출력값 memberVO 1개의 레코드를 model를 이용해서 member_view.jsp 보냅니다.(아래)
		model.addAttribute("memberVO", memberService.readMember(user_id));
		//아래 페이지 반환시(렌더링) @ModelAttribute("pageVO")에 의해서 pageVO변수값으로 jsp를 보낸다.
		return "admin/member/member_view";//상대경로 폴더파일위치
	}
	@RequestMapping(value="/admin/member/member_list", method=RequestMethod.GET)
	public String selectMember(@ModelAttribute("pageVO")PageVO pageVO,Model model) throws Exception {
	//이 메서드는 2개 객체(MemberList, pageVO)를 생성해서 model을 통해서 jsp로 보내는 기능수행. 
	//pageVO객체 (prev,next,startPage,endPage)
	//pageVO객체부터 로직이 필요 -> memberList구하는 쿼리변수가 만들어지기 때문에 이것부터 구현.
		if(pageVO.getPage() == null) {//jsp에서 전송값이 없을때만 초기값 입력
			pageVO.setPage(1);//초기값 1페이지 입력
		}
		//학습포인트 : calcPage()로직<변수(객체)값의 이동확인
		pageVO.setQueryPerPageNum(5);//memberList객체+endPage구할때 필요
		pageVO.setPerPageNum(5);//startPage구할때 - UI하단 페이지번호개수
		pageVO.setTotalCount(memberService.countMember(pageVO));
		//위 2개의 변수값을 이용해서 아래 setTotalCount 메서드에서 clacPage() 호출됨.
		//calcPage실행되면, prev, next, 변수값이 입력됩니다.
		List<MemberVO> listMember = memberService.selectMember(pageVO);
		//위 setPerPageNum 20이면 next가 false(비활성화), 5이면 next가 true(활성화)
		//100명의 회원에서는 하단 페이징 개수가 1...10 까지면 next가 false가 정상 입니다.
		logger.info("디버그" + pageVO.toString());
		//컨트롤러에서 jsp로 역방향으로 보내는 자료를 Model에 담아서 보내게 됩니다.
		model.addAttribute("listMember", listMember);
		//model.addAttribute("pageVO", pageVO);//나중에 @ModelAttribute로 대체
		return "admin/member/member_list";//jsp파일 상대경로
	}
	//URL요청 경로는 @RequestMapping 반드시 절대경로로 표시
	@RequestMapping(value="/admin", method=RequestMethod.GET)
	public String admin(Model model) throws Exception {//에러발생시 Exception클래스의 정보를 스프링으로 보내게 됩니다.		
		//아래 상대경로에서 /WEB-INF/views/폴더가 루트(생략prefix접두어) 입니다.
		//아래 상대경로 home.jsp에서 .jsp (생략suffix접미어) 입니다.
		return "admin/home";//리턴 경로=접근경로는 반드시 상대경로로 표시
	}
}