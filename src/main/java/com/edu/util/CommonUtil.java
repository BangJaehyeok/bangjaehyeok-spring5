package com.edu.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.UUID;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.edu.dao.IF_BoardDAO;
import com.edu.service.IF_MemberService;
import com.edu.vo.MemberVO;

/**
 * 이 클래스는 프로젝트에서 공통으로 사용하는 유틸리티 기능을 모아놓은 클래스이다.
 * @author 방재혁
 * 컨트롤러 기능 @Controller(jsp와 바인딩이 필요할때는 필수 애노테이션)
 * 콤포넌트 @Component는 MVC가 아닌 기능들을 모아놓은 스프링빈 명시, 여기서는 jsp와 바인딩이 필요해서 사용못함
 */
@Controller
public class CommonUtil {
	//멤버변수생성(아래)
	private Logger logger = LoggerFactory.getLogger(CommonUtil.class);
	@Inject
	private IF_MemberService memberService;//스프링빈을 주입받아서(DI:의존성주입) 객체준비
	@Inject
	private IF_BoardDAO boardDAO;
	
	//첨부파일 업로드 또는 다운로드 또는 삭제,수정,인서트에 모두 사용될 저장경로 1개를 지정해서 전역으로 사용
		@Resource(name="uploadPath")
		private String uploadPath;//root-context 업로드경로 클래스빈의 id값을 받아서 String변수 입력
		public String getUploadPath() {
			return uploadPath;
		}
	//첨부파일 개별삭제 Ajax로 받아서 처리, @ResponseBody 사용
	@RequestMapping(value="/file_delete", method=RequestMethod.POST)
	@ResponseBody
	public String file_delete(@RequestParam("save_file_name")String save_file_name) {
		//Ajax는 예외처리를 스프링에 던지지않고, 직접 try~catch문으로 처리한다.
		String result = "";//Ajax로 보내는 값변수
		try {
			boardDAO.deleteAttach(save_file_name);//DB에서 삭제
			File target = new File(uploadPath + "/" + save_file_name);
			if(target.exists()) {
				target.delete();
			}
			result = "success";
		} catch (Exception e) {
			result = "fail:" + e.toString();
		}
		return result;
	}
	
	//다운로드 처리도 같은 페이지에서 결과값만 반환받는 @ResponseBody 사용
	@RequestMapping(value="/download", method=RequestMethod.GET)
	@ResponseBody
	public FileSystemResource download(@RequestParam("save_file_name")
	String save_file_name, @RequestParam("real_file_name")String real_file_name, 
			HttpServletResponse response) throws Exception {
		//FileSystemResource는 스프링 코어에서 제공하는 전용 파일처리클래스
		File file = new File(uploadPath + "/" + save_file_name);
		response.setContentType("application/download; utf-8");
		//아래아한글, ppt문서 등에서 한글이 깨지는 것을 방지하는 코드 추가(위 utf-8)
		real_file_name = URLEncoder.encode(real_file_name);//ie에서 URL한글일때 에러발생시 코드 추가
		response.setHeader("Content-Disposition", "attachment; filename=" + real_file_name);
		return new FileSystemResource(file);
	}
		
		
	//페이지 이동이 아닌 같은 페이지에 결과값만 반환하는 @ResponseBody가 필요.
	@RequestMapping(value="/image_preview", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<byte[]> imagePriview(@RequestParam("save_file_name") 
	String save_file_name, HttpServletResponse response) throws Exception {
		//파일을 입출력할때는 파일을 byte형식으로 입출력할때 발생되는 통로 스트림이 발생
		FileInputStream fis = null;//입력통로
		ByteArrayOutputStream baos = new ByteArrayOutputStream();//출력통로
		fis = new FileInputStream(uploadPath + "/" + save_file_name);
		int readCount = 0;
		byte[] buffer = new byte[1024];//임시저장소 크기 지정
		byte[] fileArray = null;//출력스트림결과 저장하는 공간
		//반복문 : 목적, fis 입력받는 save_file_name 바인드값(배열) -1일때 까지 반복
		while((readCount=fis.read(buffer)) != -1) {
			//입력통로fis에서 출력통로 baos보냅니다. 이유는 파일입출력은 byte단위로만 가능.
			baos.write(buffer, 0, readCount); //(rawData, 종료조건, 반복횟수)
			//결과는 baos에 누적된 결과가 들어갑니다.-jsp로 보내주면 됩니다.
		}
		fileArray = baos.toByteArray();//baos 객체들 byte[] 배열로 형변환합니다.
		fis.close();//메모리 초기화
		baos.close();;
		//fileArray값을 jsp로 보내주는 준비작업
		final HttpHeaders headers = new HttpHeaders();
		//크롬 개발자도구>네트워크>image_preview>헤더탭확인
		String ext = FilenameUtils.getExtension(save_file_name);
		//이미지 확장자에 따라서 매칭되는 헤더값이 변해야하지만, 이미지 미리보기가 정상으로 보입니다.
		switch(ext.toLowerCase()) {//선택조건 : 확장자를 소문자로 바꿔서 비교
		case "png":
			headers.setContentType(MediaType.IMAGE_PNG);
			break;//스위치문 빠져나가기
		case "jpg":
			headers.setContentType(MediaType.IMAGE_JPEG);
			break;			
		case "gif":
			headers.setContentType(MediaType.IMAGE_GIF);
			break;
		case "jpeg":
			headers.setContentType(MediaType.IMAGE_JPEG);
			break;
		case "bmp":
			headers.setContentType(MediaType.parseMediaType("image/bmp"));
			break;
		default:break;
		}
		
		return new ResponseEntity<byte[]>
		(fileArray,headers,HttpStatus.CREATED);//객체생성시 초기값(rawData,헤더정보,Http상태값)
	}
	
	//XSS 크로스사이트스크립트 방지용 코드로 파싱하는 메서드(아래)
	public String unScript(String data) {
		//if(data == null || data.trim().equals("")) { 옛날방식
		if(data.isEmpty()) { //현재방식
			return "";
		}
		
		String ret = data;
		ret = ret.replaceAll("<(S|s)(C|c)(R|r)(I|i)(P|p)(T|t)", "&lt;script");
        ret = ret.replaceAll("</(S|s)(C|c)(R|r)(I|i)(P|p)(T|t)", "&lt;/script");
        ret = ret.replaceAll("<(O|o)(B|b)(J|j)(E|e)(C|c)(T|t)", "&lt;object");
        ret = ret.replaceAll("</(O|o)(B|b)(J|j)(E|e)(C|c)(T|t)", "&lt;/object");
        ret = ret.replaceAll("<(A|a)(P|p)(P|p)(L|l)(E|e)(T|t)", "&lt;applet");
        ret = ret.replaceAll("</(A|a)(P|p)(P|p)(L|l)(E|e)(T|t)", "&lt;/applet");
        ret = ret.replaceAll("<(E|e)(M|m)(B|b)(E|e)(D|d)", "&lt;embed");
        ret = ret.replaceAll("</(E|e)(M|m)(B|b)(E|e)(D|d)", "&lt;embed");
        ret = ret.replaceAll("<(F|f)(O|o)(R|r)(M|m)", "&lt;form");
        ret = ret.replaceAll("</(F|f)(O|o)(R|r)(M|m)", "&lt;form");

		return ret;
		
	}
	

	//첨부파일이 이미지인지 아닌지 확인하는 데이터 생성
	private ArrayList<String> checkImgArray = new ArrayList<String>() {
		{
		add("gif");
		add("jpg");
		add("jpeg");
		add("png");
		add("bmp");
		}
	};
	
	public ArrayList<String> getCheckImgArray() {
		return checkImgArray;
	}

	//RestAPI 서버 맛보기 ID 중복체크(제대로 만들면 @RestController사용)
	@RequestMapping(value="/id_check", method=RequestMethod.GET)
	@ResponseBody //반환받는 값의 내용(body)만 반환하겠다는 것을 명시. 헤더,푸터 등은 제외.
	public String id_check(@RequestParam("user_id")String user_id) throws Exception {
		//중복아이디를 체크로지(아래)
		String memberCnt = "1"; //중복ID가 없을때, 기본값 0
		if(!user_id.isEmpty()) { //user_id가 공백이 아니라면,
			MemberVO memberVO = memberService.readMember(user_id);
			logger.info("디버그: " + memberVO);//공백을 전송해도 null이기 때문에 조건이 추가 필요
			if(memberVO == null) { //중복아이디가 존재하지 않으면 {}안을 실행
				memberCnt = "0";
			}
		}
	return memberCnt;//RestAPI는 값만 반환한다.
	}
	//파일 업로드 공통 메서드(AdminController에서 사용, 나중엔 사용자단 HomeController에서도 사용)
	public String fileUpload(MultipartFile file) throws IOException {
		// UUID클래스로 저장될 고유식별파일명을 생성 후 물리적으로 저장
		String realFileName = file.getOriginalFilename();
		// 폴더에 저장할 PK파일명을 생성(아래)
		UUID uid = UUID.randomUUID();//유니크 ID값 생성
		String saveFileName = uid.toString() + "." + StringUtils.getFilenameExtension(realFileName);
		//file의 MultipartFile 클래스형 객체. 클래스형자료(멤버변수,메서드...)는 직접 저장할 수 없다.
		//그래서, 바이트형으로 파싱(변환)해서 저장해야함. -> bit형 자료(0,1밖에 없음) 그래서 getBytes가 쓰임(아래)
		//자바자료형 정수 : byte(bit로 구성, 이진형 010101110)<short<int<long 실수형: float<double
		byte[] fileData = file.getBytes();//데이터를 bit형으로 파싱해서 저장
		File target = new File(uploadPath, saveFileName);
		FileCopyUtils.copy(fileData, target);//물리적으로 폴더에 저장됨.
		return saveFileName;//UUID로 생성된 식별값의 파일명
	}
}
