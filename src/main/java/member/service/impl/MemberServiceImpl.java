package member.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import member.service.MemberService;
import member.service.MemberVO;

// 비지니스 로직을 수행하는 서비스 클래스를 표시 / 빈으로 등록
@Service("memberService")
public class MemberServiceImpl implements MemberService {

	@Resource(name = "memberDAO")
	public MemberDAO memberDAO;
	// memberDAO는 memberDAO의 이름을 가진 Bean객체에 의존성을 가지게 된다.

	//회원 등록 쿼리
	@Override
	public String insertMember(MemberVO vo) throws Exception {
		return memberDAO.insertMember(vo);
	}

	// 중복 확인 
	@Override
	public int selectIdChk(String userid) {
		return memberDAO.selectIdChk(userid);
	}
	
	

}

/*
 * 우리가 구현할 로직은 서비스에서 해당 서비스에 맞는 데이터를 불러오기 위해 DAO를 호출하는 것이다. 따라서 리턴으로 DAO에서 불러온
 * List 값을 반환한다.
 */