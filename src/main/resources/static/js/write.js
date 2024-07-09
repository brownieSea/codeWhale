/**
 * 
 */

function writeCheck() {
	if(document.writeForm.btitle.value.length == 0) {
		alert("제목을 입력해주세요");
		return false;
	}
	if(document.writeForm.bcontent.value.length == 0) {
		alert("내용을 입력해주세요");
		return false;
	}
	document.writeForm.submit();
}