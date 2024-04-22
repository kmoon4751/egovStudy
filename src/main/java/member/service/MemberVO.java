package member.service;


//데이터를 담을 그릇
// VO : Value Object
public class MemberVO {
	private String userid;
	private String pass;
	private String name;
	private String gender;
	private String birth;
	private String tel;
	private String address;
	
	// 우클릭 - source
	// 데이터 캡슐화
	// 메소드가 호출 당하면 필드값을 반환하거나 수정한다.
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
}


/*

MemberVO 클래스의 객체를 사용하여 사용자 이름을 설정하고 가져오는 간단한 예시 및 설명

MemberVO member = new MemberVO();
member.setUsername("JohnDoe");
String username = member.getUsername();
System.out.println("사용자 이름: " + username); 
// 출력 결과: 사용자 이름: JohnDoe

1. MemberVO member = new MemberVO();
MemberVO 클래스에서 새로운 회원 객체를 생성 후 이를 member라는 변수에 할당
	-> 생성이유 : 새로운 회원 등록 / 회원 정보 수정 / 회원 검색 및 조회 / 다수의 회원 관리
이 시점에서 member 객체는 아직 데이터가 담겨있지 않은 상태

2. member.setUsername("JohnDoe");
member 객체의 .setUsername("JohnDoe") 메소드를 호출하여 사용자의 이름을 설정.
.setUsername("JohnDoe") 메소드는 매개변수로 전달된 JohnDoe 를 해당 member 객체의 사용자 이름 필드에 저장.

3. String username = member.getUsername();
 member 객체의 .getUsername() 메소드를 호출하여 사용자 이름을 가져온 뒤 String username 변수에 할당.
 
*/
