/**
 * 
 */

function loginCheck() {
	if(document.loginForm.mid.value.length == 0) {
		alert("아이디를 입력해주세요");
		return false;
	}
	if(document.loginForm.mpw.value.length == 0) {
		alert("비밀번호를 입력해주세요");
		return false;
	}
	document.loginForm.submit();
}