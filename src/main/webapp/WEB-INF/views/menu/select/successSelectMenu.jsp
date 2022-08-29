<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.net.URLEncoder"%>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.88.1">
    <title>JavaCafe</title>
	<link rel="canonical" href="https://getbootstrap.com/docs/5.1/examples/blog/">
	
	<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/main.css'/>"/>
    
    <style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }
    </style>

    
    <!-- Custom styles for this template -->
    <link href="https://fonts.googleapis.com/css?family=Playfair&#43;Display:700,900&amp;display=swap" rel="stylesheet">
  </head>
  <body>
    
<header class="blog-header py-3">
    <div class="row flex-nowrap justify-content-between align-items-center">
      <div class="col-4 text-center">
        <a class="blog-header-logo text-dark" href="/javacafe/home/homePage" style="margin-left: 50px;">JavaCafe</a>
      </div>
      <div class="col-4 d-flex justify-content-end align-items-center">
        <a class="btn btn-sm btn-outline-secondary" onClick="location.href='/javacafe/auCategory'" style="font-weight: bold; font-size: 16px; margin-left: 1250px;">Category(관리자)</a>
      </div>
      <div class="col-4 d-flex justify-content-end align-items-center">
        <a class="btn btn-sm btn-outline-secondary" onClick="location.href='/javacafe/auMenu'" style="font-weight: bold; font-size: 16px;">Menu(관리자)</a>
      </div>
    </div>
  </header>
  
  	<form action="successPayment" method="get">
  	<div style="font-size: 80px; font-weight: bold; text-align: center; margin-top: 50px;">
	<span >결제 하시겠습니까</span><br>
	</div>
	<div style="text-align: center; margin-top: 50px; font-size: 50px; font-weight: bold;">
	<c:forEach var="menu" items="${menuList}">
	<div style="text-align: center; font-size: 30px; padding-top: 10px;"><span style="font-size: 50px;">메뉴 사진</span><br>
	<img src="resources/img/${menu.imgName}.jpg" id="profileImg" name="file" style="width: 300px; height: 300px; margin-top: 20px; margin-bottom: 20px;" /><br></div>
	<label>메뉴 : ${menu.name}</label><br>
	<label>가격 : ${menu.price}</label><br>
	</c:forEach>
	</div>
	<button type="submit" class="btn-secondary" style="margin-top: 50px;width: 250px;">예</button>
	<button type="button" class="btn-secondary" onclick="location.href='/javacafe/home/homePage'" 
	style="margin-top: 20px;width: 250px;">아니요</button>
	</form>
	
	<!-- FOOTER -->
	<footer class="blog-footer" style="transform: translateY(40%);">
	  <p>Blog template built for <a href="https://getbootstrap.com/">Bootstrap</a> by <a href="https://twitter.com/mdo">@mdo</a>.</p>
	  <p>
	    <a href="#">Back to top</a>
	  </p>
	</footer>
</body>

	<!-- 스크립트 영역 -->
	<script type="text/javascript">
		// input 태그 (name이 file)를 가져옴 
		let fileTag = document.querySelector("input[name=file]");
		
		// 파일태그에 변화가 있을 때 실행될 함수 작성 
		fileTag.onchange = function () {
			
			let imgTag = document.querySelector("#profileImg");
			
		// 파일이 있는지 확인
		if(fileTag.files.length > 0) {
			// 파일을 선택한 경우 미리보기 생성 (이미지 태그 src에 데이터를 넣어주면 됨)
			let reader = new FileReader();
			
			// reader 읽어들이는 작업(onload)를 끝냈을 때 함수 실행, 읽어온 데이터를 함수의 파라미터로 줄 수 있음
			reader.onload = function (data) {
				console.log(data);
				imgTag.src = data.target.result;
			}
			
			reader.readAsDataURL(fileTag.files[0]);
		} else {
			// 취소 버튼 누를 경우
			imgTag.src = "";
		}
	}
	</script>
</html>