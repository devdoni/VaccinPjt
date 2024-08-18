<%@ page import="com.office.mode.user.member.UserMemberService" %>
<%@ page import="com.office.mode.user.UserConfig" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>백신 접종 현황 프로그램</title>

    <link href="<%= request.getContextPath() %>/res/css/user/confirm.css" rel="stylesheet" type="text/css">
    <link href="<%= request.getContextPath() %>/res/css/user/include.css" rel="stylesheet" type="text/css">
    <script src="<%= request.getContextPath() %>/res/js/modeToggle.js"></script>

</head>
<body>

    <jsp:include page="../include/header.jsp" />
    <jsp:include page="../include/nav.jsp" />

<section>
    <div class="section_wrap">
        <div class="article">
            <%
                int result = Integer.parseInt(request.getParameter("rst"));
                if (result == UserMemberService.MEMBER_DELETE_SUCCESS) {
            %>
                <h3>회원탈퇴에 성공했습니다.</h3>
                <h3><a href="<%= request.getContextPath()%>/">홈으로 이동</a></h3>
            <%
                } else {
            %>
                <h3>회원탈퇴에 실패했습니다 관리자에게 문의해주세요.</h3>
                <h3>VacAdmin@gmail.com</h3>
                <h3><a href="<%= request.getContextPath()%>/">홈으로 이동</a></h3>
            <%
                }
            %>
        </div>
    </div>
</section>
    <jsp:include page="../include/footer.jsp" />

</body>
</html>
