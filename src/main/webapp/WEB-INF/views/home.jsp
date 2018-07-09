<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Home Page</title>
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/style.css" rel="stylesheet">
    <script defer src="${contextPath}/resources/js/jquery-3.3.1.min.js"></script>
    <script defer src="${contextPath}/resources/js/popper.min.js"></script>
    <script defer src="${contextPath}/resources/js/bootstrap.min.js"></script>
</head>
<body>
    <div class="container" id="home">
        <h1>Welcome In Register And Login App</h1>
        <div id="links">
            <h5><a href="${contextPath}/register">Register</a></h5>
            <h5><a href="${contextPath}/login">Login</a></h5>
        </div>
    </div>
</body>
</html>
