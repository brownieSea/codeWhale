/**
 * 
 */

function joinCheck() {
	if(document.joinForm.mid.value.length == 0) {
		alert("아이디를 입력해주세요");
		return false;
	}
	if(document.joinForm.mpw.value.length == 0) {
		alert("비밀번호를 입력해주세요");
		return false;
	}
	if(document.joinForm.mpw.value != document.joinForm.mpwCheck.value) {
		alert("비밀번호가 일치하지 않습니다. 다시 확인해주세요");
		return false;
	}
	if(document.joinForm.mname.value.length == 0) {
		alert("이름을 입력해주세요");
		return false;
	}
	if(document.joinForm.memail.value.length == 0) {
		alert("이메일을 입력해주세요");
		return false;
	}
	
	document.joinForm.submit();
}