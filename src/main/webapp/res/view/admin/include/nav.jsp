<%@ page import="com.office.mode.Config" %>
<%@ page import="com.office.mode.user.UserConfig" %>
<%@ page import="com.office.mode.admin.AdminConfig" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <jsp:include page="../member/sign_out.jsp" />

<nav>
    <div class="nav_wrap">
        <a href="<%= request.getContextPath() %>/">홈</a>
        <%
            Object obj = session.getAttribute(Config.ADMIN_LOGINED_SESSION_ID);
            if (obj == null) {
        %>
        <a href="<%= request.getContextPath().concat(AdminConfig.MEMBER_SIGN_IN)%>">관리자 로그인</a>
        <a href="<%= request.getContextPath().concat(AdminConfig.MEMBER_SIGN_UP)%>">관리자 등록</a>
        <%
        } else {
        %>
        <a href="javascript:void(0)" onclick="signOutBtnClick()">관리자 로그아웃</a>
        <a href="<%= request.getContextPath().concat(AdminConfig.VACCIN_LIST) %>">모든 유저 백신 접종현황</a>
        <%
            }
        %>
        <a href="<%= request.getContextPath().concat(UserConfig.USER_MODE) %>">유저 홈</a>
        <button id="toggleMode">Light Mode</button>
    </div>
</nav>
