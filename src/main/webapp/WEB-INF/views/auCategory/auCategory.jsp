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
  
  	<div style="font-size: 50px; margin-top: 130px; text-align: center; font-weight: bold;">
		<span>Category</span>
	</div>
		
		<button type="button" value="추가" onClick="location.href='/javacafe/addCategory'" class="btn btn-lg btn-secondary" style="margin: 0 auto; text-align: center; display: block;
		margin-top: 100px; width: 250px;">추가</button>
		
		<button type="button" value="수정" onClick="location.href='/javacafe/modifyCategory'" class="btn btn-lg btn-secondary" style="margin: 0 auto; text-align: center; display: block;
		margin-top: 100px; width: 250px;">수정</button>


    <!-- FOOTER -->
  <footer class="blog-footer" style="transform: translateY(140%);">
  <p>Blog template built for <a href="https://getbootstrap.com/">Bootstrap</a> by <a href="https://twitter.com/mdo">@mdo</a>.</p>
  <p>
    <a href="#">Back to top</a>
  </p>
</footer>
</body>
</html>