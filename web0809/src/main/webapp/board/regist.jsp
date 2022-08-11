<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 등록</title>
<style>
input[type='button'][ 0] {
	background: yellow;
}
</style>
<!-- include libraries(jQuery, bootstrap) -->
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"
	rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<!-- include summernote css/js -->
<link
	href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
<script>
	/*JQuery는 자바스크립트를 잘 모르거나. 혹은 자바스크립트를 이용하여 복잡한 로직을 작성하지 않고도 쉽게 결과를 내고 싶을때 사용한다.
	 * 주형 :(css의 선택자).메서드();                      
	 */

	$(document).ready(function() {
		$("#summernote").summernote();

		//버튼 들 중 (버튼 배열) 첫번째 0번째 버튼에 이벤트 구현
		$("#bt_regist").click(function() {
			//폼양식을 이용하여 서버에 전송!!!!!!!!			
			$("form").attr("action", "/web0809/board/regist");
			$("form").attr("method","post");
			$("form").submit();
		});
	});
</script>
</head>
<body>
	<form>
		<table width="60%" border="1px" align="center">
			<tr>
				<td>제목</td>
				<td><input type="text" name="title"></td>
			</tr>
			<tr>
				<td>작성자</td>
				<td><input type="text" name="writer"></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><textarea id="summernote" name="content"></textarea></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="button" value="글등록" id="bt_regist"> 
					<input type="button" value="글목록" onClick="location.href='/board/list.jsp'"">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
