<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${user.name}님의 마이페이지</title>
    <%-- 파비콘 --%>
    <link rel="shortcut icon" href="images/paca_icon.ico">
    <%-- css 호출 --%>

    <link rel="stylesheet" href="css/normalize.css">
    <link rel="stylesheet" href="css/common.css">


    <%-- 스크립트 호출 --%>
    <script src="js/jquery-3.6.0.min.js"></script>
    <script src="js/index.js"></script>
    <script src="js/common.js"></script>

    <%-- 적응형 웹 스크립트--%>
    <script>
        let smartPhones = [
            'iphone','ipod','ipad','opera mini','opera mobi','nokia','android','webos','windows ce','blackberry','iemobile','sonyericsson'
        ];
        // // 기종 확인
        // let user = navigator.userAgent;
        // alert(user)

        // 스마트폰 구분
        for(let i in smartPhones) {
            // navigator.userAgent => 기기 정보  lowercase로 전부 소문자로 바꿔서 비교
            if(navigator.userAgent.toLowerCase().match(new RegExp(smartPhones[i]))){
                document.location='http://alpaca02.dothome.co.kr/mindex.html';
            }
        }
    </script>


    <%--웹폰트 링크 연결--%>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>

    
    <div id="wrapper">
        <%-- 헤더 영역 --%>
        <header>
            <%--로고, 검색, 아이콘 영역 시작 --%>
            <div id="titleOut">
                <div id="title">
                    <%-- 로고 영역 시작--%>
                    <h1><a href="index.html"><img src="images/title/PacaBooks-hor.png" alt="logo"></a></h1>
                    <%-- 로고 영역 종료 --%>
                    <%-- /////////////////////////////////////--%>
                    <%-- 검색 영역 시작 --%>
                    <div id="search">
                        <h2 class="hidden">책 검색</h2>
                        <form action="search.jsp" method="get" id="searchForm">
                            <fieldset>
                                <legend>검색창</legend>
                                <button type="submit"><i class="fa fa-search" aria-hidden="true"></i><span class="hidden">검색</span></button>
                                <input type="search" id="keyword" name="keyword" required>
                            </fieldset>
                        </form>
                    </div>

                    <%-- 검색 영역 종료 --%>
                    <%--/////////////////////////////////////--%>
                    <%-- 아이콘 영역 시작 --%>
                    <div id="mainIcon">
                        <h2 class="hidden">메인 아이콘</h2>
                        <ul>
                            <li class="notice"><i class="fa fa-bell" aria-hidden="true"></i><span class="hidden">알림</span></li>
                            <div class="myNotice">
                                <ul>
                                    <li>알림이 없습니다.</li>
                                </ul>
                            </div>
                            <li class="cart"><i class="fa fa-shopping-cart" aria-hidden="true"></i><span class="hidden">카트</span></li>
                            <div class="myCart">
                                <ul>
                                    <li>카트가 비어있습니다.</li>
                                </ul>
                            </div>
                            <%-- 마이페이지 세션 --%>
                            <li><a href="http://localhost:8090/pacaBooks/pacaUser/myPage.do"><i class="fa fa-user" aria-hidden="true"></i><span class="hidden">마이페이지</span></a></li>
                        </ul>
                    </div>
                    <%-- 아이콘 영역 종료 --%>
                </div>
            </div>
            <%--로고, 검색, 아이콘 영역 종료 --%>
            <%--//////////////////////////////////////////////////--%>
            <%-- 메뉴바 영역 시작 --%>
            <nav id="menuBar">
                <div class="dropDown">
                <h2><i class="fa fa-bars" aria-hidden="true"></i><span class="hidden">메뉴바</span></h2>
                    <div class="menuBar_drop">
                        <a href="#">소설</a>
                        <a href="#">에세이/시</a>
                        <a href="#">인문/사회/역사</a>
                        <a href="#">만화</a>
                    </div>
                </div>
            </nav>
            <%-- 메뉴바 영역 종료 --%>

        </header>
        <%-- 헤더 영역 종료 --%>
        
        <%-------------------------------------------------------------%>
        <section id="info">
        	<h2>내 정보</h2>
        

        </section>
        <%---------------------------------------------------------------%>

        <%-- footer 영역 시작 --%>
        <footer>
            <div id="footerInner">
                <%-- 상단 리스트 시작 --%>
                <ul>
                    <li><a href="#">회사소개</a></li>
                    <li><a href="#">이용약관</a></li>
                    <li><a href="#">개인정보처리방침</a></li>
                    <li><a href="#">제휴·제안</a></li>
                    <li><a href="#">광고센터</a></li>
                    <li><a href="#">채용정보</a></li>
                    <li><a href="#">서비스 전체보기</a></li>
                </ul>
                <%-- 상단 리스트 종료 --%>
                <%--////////////////////////////////////--%>
                <%-- 주소 영역 시작 --%>
                <address>
                    <span>서울시 종로구 123-12 한주빌딩 5층</span>
                    <span> · 대표전화: 02-123-4567 (발신자 부담전화)</span>
                    <span> · 서울특별시 통신판매업신고번호 : 제 123호 ▶<a href="#">사업자 정보확인</a></span>
                </address>
                <%--저작권 정보--%>
                <p class="copyright">copyright since &copy; 2022 <br>by PacaBooks CORPORATION ALL RIGHTS RESERVED</p>
                <%-- 주소 영역 종료 --%>
            </div>
        </footer>
        <%-- footer 영역 시작 --%>
    </div>
    <%-- wrapper 종료--%>
</body>
</html>