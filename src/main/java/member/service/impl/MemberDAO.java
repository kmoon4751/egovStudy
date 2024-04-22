package member.service.impl;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import member.service.MemberVO;

//DAO : Data Access Object
//실제로 데이터베이스와의 상호작용을 수행한다.
//데이터 엑세스 로직을 수행하는 DAO 클래스를 표시 / MemberDAO 를 빈으로 만든다.
@Repository("memberDAO")	
public class MemberDAO extends EgovAbstractDAO{

	//매개변수를 받아 쿼리문을 실행
	public String insertMember(MemberVO vo) {
		// src/main/resources/egovframework/sqlmap
		return (String) insert("memberDAO.insertMember",vo);
	}	//상속

	public String idChk(String userid) {
		return null;
	}
	

}


/*
insertMember 메서드 : 회원 정보를 데이터베이스에 저장하는 역할.
회원의 정보가 담겨있는 MemberVO 객체를 매개변수로 받는다.
insert 메서드를 호출하여 데이터베이스에 새로운 회원 정보를 삽입한다.
이때, SQL 매핑 파일에서 지정된 쿼리를 실행하여 데이터베이스에 접근한다.
삽입이 성공하면 결과를 문자열로 반환.

insert("memberDAO.insertMember,vo")
memberDAO.insertMember 는 실제 SQL 매핑 파일에서 사용되는 쿼리의 ID를 나타낸다.
이 쿼리는 XML 파일에 정의되어 있어야 하며, 데이터베이스에 데이더를 삽입하는 INSERT 쿼리일 것.
vo 는 쿼리에 전달 될 파라미터로 MemberVO 객체를 나타낸다.
*/
