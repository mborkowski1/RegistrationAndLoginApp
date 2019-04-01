<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Login Page</title>
        <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
        <link href="${contextPath}/resources/css/style.css" rel="stylesheet">
        <script defer src="${contextPath}/resources/js/jquery-3.3.1.min.js"></script>
        <script defer src="${contextPath}/resources/js/popper.min.js"></script>
        <script defer src="${contextPath}/resources/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="container">
            <form:form method="post" modelAttribute="userLoginForm" novalidate="novalidate">
                <h2 class="text-center">Log In</h2>

                <spring:bind path="username">
                    <div class="form-group">
                        <form:label path="username" for="username" cssClass="col-form-label">Username</form:label>
                        <form:input path="username" id="username" cssClass="form-control" cssErrorClass="form-control is-invalid" placeholder="Username" />
                        <form:errors path="username" cssClass="invalid-feedback" />
                    </div>
                </spring:bind>

                <spring:bind path="password">
                    <div class="form-group">
                        <form:label path="password" for="password" cssClass="col-form-label">Password</form:label>
                        <form:password path="password" id="password" cssClass="form-control" cssErrorClass="form-control is-invalid" placeholder="Password" />
                        <form:errors path="password" cssClass="invalid-feedback"/>
                    </div>
                </spring:bind>

                <button class="btn btn-lg btn-primary btn-block" type="submit">Log In</button>
                <h5 class="text-center"><a href="${contextPath}/register">Create an account</a></h5>
            </form:form>
        </div>
    </body>
</html>
