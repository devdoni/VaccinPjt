<%@ page import="com.office.mode.user.UserConfig" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>백신 접종 현황 프로그램</title>

    <link href="<%= request.getContextPath() %>/res/css/user/include.css" rel="stylesheet" type="text/css">
    <link href="<%= request.getContextPath() %>/res/css/user/myinfo.css" rel="stylesheet" type="text/css">
    <script src="<%= request.getContextPath() %>/res/js/modeToggle.js"></script>

</head>
<body>

    <jsp:include page="../include/header.jsp" />
    <jsp:include page="../include/nav.jsp" />
    <jsp:include page="user_delete.jsp" />

<section>
    <div class="section_wrap">
        <div class="article">
            <a href="<%= request.getContextPath().concat(UserConfig.USER_INFO_MODIFY)%>"><div>정보 수정</div></a>
            <a href="#none" onclick="deleteBtnClick()"><div>회원 탈퇴</div></a>
        </div>
    </div>
</section>
    <jsp:include page="../include/footer.jsp" />

</body>
</html>
