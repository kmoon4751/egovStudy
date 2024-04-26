package member.service.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import member.service.MemberVO;

//DAO : Data Access Object
//실제로 데이터베이스와의 상호작용을 수행한다.
//데이터 엑세스 로직을 수행하는 DAO 클래스를 표시 / MemberDAO 를 빈으로 만든다.
@Repository("memberDAO")
public class MemberDAO extends EgovAbstractDAO {
// 경로 : src/main/resources/egovframework/sqlmap

	// 매개변수를 받아 쿼리문을 실행
	public String insertMember(MemberVO vo) {
		return (String) insert("memberDAO.insertMember", vo);
	}

	// 중복 체크 쿼리
	// 중복 체크 할 유저아이디를 파라미터로 받는다.
	public int selectIdChk(String userid) {
		// 쿼리를 실행할 부분
		return (int) select("memberDAO.selectIdChk", userid);
		/*
		 * (int) : 메소드가 반환하는 값의 형태 -> 중복되는 아이디 값을 카운트 해야하므로 
		 * select : 데이터베이스에서 쿼리를 실행하는 메소드
		 * "memberDAO.selectIdChk" : 실행할 쿼리를 식별하는데에 사용된다
		 *  memberDAO : SQL매너 XML파일에서 정의된 네임스페이스
		 *  selectIdChk : 쿼리의 id 
		 *  userid : 쿼리에 전달되는 파라미터로 중복 확인 할 아이디를 전달
		 */

	}
	
	//로그인 아이디 유무 및 패스워드 오류
	public int loginProc(MemberVO vo) {
		return (int) select("memberDAO.loginProc", vo);
	}

	
	// 가입한 멤버 조회하기
	public List<?> selectMemberList(){
		return list("memberDAO.selectMemberList");
	}

	//멤버 삭제
	public int delMember(String userid) {
		return (int) delete("memberDAO.delMember", userid);
	}

	//멤버 검색
	public List<?> findMember(String keyword) {
		return list("memberDAO.findMember", keyword);
	}

}

/*
 * insertMember 메서드 : 회원 정보를 데이터베이스에 저장하는 역할. 회원의 정보가 담겨있는 MemberVO 객체를 매개변수로
 * 받는다. insert 메서드를 호출하여 데이터베이스에 새로운 회원 정보를 삽입한다. 이때, SQL 매핑 파일에서 지정된 쿼리를 실행하여
 * 데이터베이스에 접근한다. 삽입이 성공하면 결과를 문자열로 반환.
 * 
 * insert("memberDAO.insertMember,vo") memberDAO.insertMember 는 실제 SQL 매핑 파일에서
 * 사용되는 쿼리의 ID를 나타낸다. 이 쿼리는 XML 파일에 정의되어 있어야 하며, 데이터베이스에 데이더를 삽입하는 INSERT 쿼리일 것.
 * vo 는 쿼리에 전달 될 파라미터로 MemberVO 객체를 나타낸다.
 */
