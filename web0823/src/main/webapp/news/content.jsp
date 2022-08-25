<%@page import="com.aca.web0823.domain.News"%>
<%@page import="com.aca.web0823.news.model.NewsDAO"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%!NewsDAO newsDAO = new NewsDAO();%>
<%
	int news_id =  Integer.parseInt(request.getParameter("news_id"));
	News news = newsDAO.select(news_id);
	
	
%>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {font-family: Arial, Helvetica, sans-serif;}
* {box-sizing: border-box;}

input[type=text], select, textarea {
  width: 100%;
  padding: 12px;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
  margin-top: 6px;
  margin-bottom: 16px;
  resize: vertical;
}

input[type=button] {
  background-color: #04AA6D;
  color: white;
  padding: 12px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

input[type=button]:hover {
  background-color: #45a049;
}

.container {
  border-radius: 5px;
  background-color: #f2f2f2;
  padding: 20px;
}

#comments-list{
	border:1px solid red;
	overflow:hidden;
}
#comments-list *{
	float:left;
}
.title-style{width:80%;}
.writer-style{width:10%;}
.regdate-style{width:10%;}

</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
function showCommentsListByString(jsonArray){
	console.log("넘겨받은 데이터의 배열 크기는 ", jsonArray.length)
	var data = JSON.parse(jsonArray);
	console.log("json객체 수는 ",data.length);
	//div 안의 컨텐츠를 js의 dom을 이용하여 동적으로 출력해본다.
	var commentsList = document.getElementById("comments-list");
	//문자열로 취급하는 방법
	var tag="";
	for(var i = 0; i<data.length; i++){
		tag+="<div class=\"title-style\" id=\"title\">짜파게티 14% 개미들은 활짝</div>";
		tag+="<div class=\"writer-style\">리얼개미</div>";
		tag+="<div class=\"regdate-style\">등록날짜</div>"
	}
	console.log(tag);
	commentsList.innerHTML=tag;
	
	
	
	
	
}


function showCommentsListByDom(jsonArray){
	var data = JSON.parse(jsonArray);
	
	var commentsList =	document.getElementById("comments-list");
	//출력전에 
	commentsList.innerHTML="";
	
	
	//div안의 컨테츠 구성

	for(var i=0; i<data.length;i++){
		var json=data[i];//배열에 들어있는 한건의 댓글 객체 꺼내기
		var div1 = document.createElement("div"); //title-style
		var div2 = document.createElement("div"); //write-style
		var div3 = document.createElement("div"); //regdate-style
		//생성된 dom요소에 클래스 적용
		div1.className="title-style";
		div2.className="writer-style";
		div3.className="regdate-style";
		div1.innerText=json.detail;
		div2.innerText=json.author;
		div3.innerText=json.writeDate.substring(0,10);
		commentsList.appendChild(div1);
		commentsList.appendChild(div2);
		commentsList.appendChild(div3);
	}
}

function regist(){
	var detail = document.getElementsByName("detail")[0];
	var author = document.getElementsByName("author")[0];
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	      console.log("서버가 보내온 json 문자열은 : ",this.responseText);
	      showCommentsListByDom(this.responseText);
	    }
	  };
	xhttp.open("POST","/comments/regist");
	xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhttp.send("detail="+detail.value+"&author="+author.value+"&news_id=<%=news_id%>");
}
//비동기 방식으로 댓글 목록 가져오기
function getComments(){
	$.ajax({
		url:"/comments/list?news_id=<%=news_id%>",
		success:function(result){//result==responseText
			//alert(result);
			showCommentsListByDom(result);
		}
	});

}
function edit(){
	
}
function del(){
	if(confirm("삭제하시겠습니까?")){
		location.href="/news/delete?news_id=<%=news_id%>";
	}
}


//페이지가 로딩 될때 댓글 가져오기
function init(){
	getComments();

}

</script>
</head>
<body onLoad="init()">

<h3>뉴스기사 상세보기</h3>

<div class="container">
  <form name="form1">
    <input type="text"  name="title" value="<%=news.getTitle()%>">
    
    <input type="text" name="writer" value="<%=news.getWriter()%>">
    
    <textarea name="content" style="height:200px"><%=news.getContent()%></textarea>

    <input type="button" value="등록" onClick="regist();">
    <input type="button" value="목록" onClick="location.href='/news/list.jsp';">
    <input type="button" value="수정" onClick="edit();">
    <input type="button" value="삭제" onClick="del();">
  </form>
  <form name="form2">
  	<input type="text" name="detail" placeholder="댓글내용" style="width:75%">
  	<input type="text" name="author" placeholder="작성자" style="width:10%">
  	<input type="button" value="댓글 등록" onClick="regist()">
  </form>
  <div id="comments-list"></div>
  
</div>

</body>
</html>
