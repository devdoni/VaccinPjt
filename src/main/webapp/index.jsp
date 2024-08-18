<%@ page import="com.office.mode.user.UserConfig" %>
<%@ page import="com.office.mode.admin.AdminConfig" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>백신 접종 현황 프로그램</title>

    <link href="<%= request.getContextPath() %>/res/css/index.css" rel="stylesheet" type="text/css">
    <script src="<%= request.getContextPath() %>/res/js/modeToggle.js"></script>
</head>
<body>
<header>
    <div class="header_wrap">
        독감 백신 접종 현황 프로그램
    </div>
</header>

<nav>
    <div class="nav_wrap">
        <a href="<%= request.getContextPath().concat(AdminConfig.ADMIN_HOME) %>">관리자모드</a>
        <a href="<%= request.getContextPath().concat(UserConfig.USER_MODE) %>">유저모드</a>
        <button id="toggleMode">Light Mode</button>
    </div>
</nav>

<section>
    <div class="section_wrap">
        <div class="article">
            <h3>VACCIN INJECTION MANAGEMENT SERVICE HOME</h3>
        </div>
    </div>
</section>

<footer>
    <div class="footer_wrap">
        <p>OUR OFFICE COPYRIGHT 2024 ALL RIGHT RESERVED.</p>
    </div>
</footer>
</body>
</html>