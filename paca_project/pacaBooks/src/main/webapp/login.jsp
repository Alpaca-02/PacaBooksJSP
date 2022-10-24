<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

    <!-- 페이지 주소를 가져와 변수에 담음 -->
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>로그인 | PacaBooks</title>

    <!-- 파비콘 -->
    <link rel="shortcut icon" href="${contextPath}/images/paca_icon.ico">

    <link rel="stylesheet" href="${contextPath}/css/normalize.css">
    <link rel="stylesheet" href="${contextPath}/css/login.css">
</head>
<body>
    <div id="wrapper">
        <div id="logo">
            <a href="index.html"><img src="${contextPath}/images/title/PacaBooks-hor.png" alt=""></a>
        </div>
        <a href="join.html"><div id="createAccount">계정만들기</div></a>

        <!-- 로그인 창 영역 -->
        <div id="login">
            <h2 class="hidden">로그인</h2>
            <a href="index.html"><img src="${contextPath}/images/title.png" alt="title_logo"></a>
            <form action="${contextPath}/pacaUser/login.do" method="post" id="loginForm">
                <fieldset>
                    <legend>회원 로그인 폼</legend>
                    <!-- 아이디와 패스워드 입력창에 개별로 <p>를 주어서 층을 구분 -->
                    <!--아이디 입력창-->
                    <p>
                        <!-- name => 백엔드에 줄 데이터 이름 / placeholder => 힌트 / required = 필수 요구사항 -->
                        <input type="text" id="userId" name="id" placeholder="아이디, 이메일" required autofocus>
                    </p> 
                    <!--패스워드 입력창-->
                    <p>
                        <input type="password" id="userPw" name="pwd" placeholder="비밀번호" required>
                    </p>
                    <!-- 로그인 버튼 -->
                    <button type="submit">로그인</button>
                </fieldset>
            </form>
            <!-- 비밀번호 찾기 버튼-->
            <div id="findPassword">
                <a href="#" class="find">비밀번호를 모르겠어요</a>
            </div>
        </div>
        <img src="${contextPath}/images/alpaca/Alpaca.png" alt="AlpacaTitle" id="backPaca">
        <!-- 푸터 영역 -->
        <footer>
            <div id="copyright">
                <a href="#">&copy;PacaBooks</a>
            </div>
            <div id="support">
                <a href="#">이용약관</a>
                <a href="#">서포트</a>
            </div>
        </footer>
    </div>
</body>
</html>