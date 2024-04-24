package member.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
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
		System.out.println("중복체크 작동됨 : selectIdChk : " + userid);
		String result = "";

		// 일치하는 아이디의 갯수를 찾아야 하므로
		int cnt = memberService.selectIdChk(userid);
		if (cnt == 0) { // 일치하는 아이디의 개수가 0이면
			result = "ok";
		}
		return result;
	}

	/* 로그인 페이지 호출 */
	@RequestMapping("login.do")
	public String login() {
		return "member/login";
	}

	/* 로그인 로직 */
	@RequestMapping("loginProc.do")
	@ResponseBody
	public String loginProc(MemberVO vo, HttpSession session) throws Exception {
		String message = "";
		System.out.println("로그인버튼 작동됨 : loginProc");
		
		int cnt = memberService.selectIdChk(vo.getUserid());
		if (cnt == 0) { // 아이디가 없을 때
			message = "x";
		} else {
			int cnt2 = memberService.loginProc(vo);
			if (cnt2 == 0) {
				message = "wrong"; // 아이디 또는 패스워드 오류
			} else { // 로그인 성공
				// vo객체에서 로그인을 성공한 유저의 아이디를 가져와 sessionId에 set한다.
				// 이후 sessionId = 로그인에 성공한 유저의 아이디
				System.out.println("로그인성공");
				session.setAttribute("sessionId", vo.getUserid());
				message = "ok";
			}
		}
		return message;
	}
	
	//로그아웃
	@RequestMapping("logout.do")
	public String logout(HttpSession session) {
		//회원 아이디를 세션에서 없앰으로써 로그아웃을 하고 메인 페이지로 이동
		session.removeAttribute("sessionId");
		return "member/main";
	}

	//메인화면
	@RequestMapping("main.do")
	public String main() {
		return "member/main";
	}
	
	/*
	 * 전체 회원 목록 조회하기 24.04.24
	 */
	//회원목록
	@RequestMapping("memberList.do")
	public String selectMemberList(Model model) throws Exception{
		
		List<?> list = memberService.selectMemberList();
		System.out.println("회원 리스트 : " + list);
		model.addAttribute("memberList", list);
		return "member/memberList";
	}

}
