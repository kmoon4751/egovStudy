package member.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import member.service.MemberService;
import member.service.MemberVO;

@Controller
public class MemberController {

	// memberService 는 이름이 memberService인 빈 객체에 의존성을 가진다.
	// MemberServiceImpl 에서 Service 어노테이션으로 등록한 빈
	@Resource(name = "memberService")
	public MemberService memberService;

	/* 페이지 호출 */
	@RequestMapping("memberJoin.do")
	public String memberJoin() {
		return "member/memberJoin";
	}

	// memberJoinSave.do 경로로 들어오는 요청을 처리하는 어노테이션
	// 회원 정보를 등록하는 메서드
	@RequestMapping("memberJoinSave.do")
	// 해당 메서드가 HTTP 응답 본문에 직접 데이터를 작성하고자 할 때 사용.
	// 이 메서드에서는 문자열 "ok"를 반환하여 클라이언트에게 성공 여부를 전달
	@ResponseBody
	public String insertMember(MemberVO vo) throws Exception {
		String message = "";

		String result = memberService.insertMember(vo);
		if (result == null) {
			message = "ok";
		}
		return message;
	}

	/* 중복체크 */
	@RequestMapping("idChk.do")
	@ResponseBody
	public String selectIdChk(String userid) throws Exception {
		System.out.println("USERID : " + userid);
		String result = "";
		
		//일치하는 아이디의 갯수를 찾아야 하므로
		int cnt = memberService.selectIdChk(userid);
		if (cnt == 0 ) {	// 일치하는 아이디의 개수가 0이면
			result = "ok";
		}
		return result;
	}

}
