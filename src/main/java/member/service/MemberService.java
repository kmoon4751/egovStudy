package member.service;

import java.util.List;

/*
인터페이스는 서비스 계층에서 사용된다.
회원 정보를 등록하는 기능을 정의하고, 이를 구현하는 클래스는 해당 인터페이스를 구현해야 한다.
인터페이스를 통해서 서비스 계층의 역할을 분리하고, 각 기능을 추상화하여
클라이언트 코드가 인터페이스만을 통해 접근할 수 있도록 한다.

인터페이스는 내부에 메소드 본체를 가질 수 없다.
필요한 메소드 헤더만 정의하고 이를 구현하는 클래스에서 본체를 구현할 수 있다.
*/
public interface MemberService {
	/* 회원등록을 처리 */
	public String insertMember(MemberVO vo) throws Exception;

	/* 아이디 중복 체크 */
	public int selectIdChk(String userid);

	/*로그인 로직*/
	public int loginProc(MemberVO vo);
	
	/*전체 회원 조회하기*/
	public List<?> selectMemberList();

	/*회원 삭제*/
	public int delMember(String userid);

	/*회원 검색*/
	public List<?> findMember(String keyword);

}


/*
1. insertMember 메소드 정의. 이 이름으로 메소드를 호출한다.
2. (MemberVO vo)
	MemberVO 타입의 객체를 매개변수로 받는다.
	즉, MemberVO 객체를 전달하여 이 메소드를 호출할 수 있다.
3. throws Exception
	insertMember 호출 시 Exception 을 던질 수 있다.
	즉, 메소드를 호출한 측에서는 Exception 을 처리해야 한다.
	
insertMember 메소드는 MemberVO 객체를 매개변수로 받아서 해당 회원을 데이터 베이스에 등록한다.
이때, 'vo' 변수는 전달된 회원 정보를 담고있는 'MemberVO' 객체를 가리키게 된다.
그 후 처리 결과를 문자열로 반환한다.
 */
