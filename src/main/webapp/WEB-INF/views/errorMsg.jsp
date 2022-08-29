<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>alretMsg</title>
</head>
<body>
   <script type="text/javascript">
      var message = "${msg}";
      alert(message);
      document.location.href =  window.history.back();	/* window.history.back() : 이전 페이지로 넘어간다 */ 
   </script>
</body>
</html>