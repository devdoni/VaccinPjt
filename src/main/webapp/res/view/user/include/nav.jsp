<%@ page import="com.office.mode.Config" %>
<%@ page import="com.office.mode.user.UserConfig" %>
<%@ page import="com.office.mode.admin.AdminConfig" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="../member/sign_out.jsp" />
<nav>
    <div class="nav_wrap">
        <a href="<%= request.getContextPath() %>/"> 홈</a>
        <a href="<%= request.getContextPath().concat(AdminConfig.ADMIN_HOME) %>">관리자 홈</a>
        <%
            if (session.getAttribute(Config.USER_LOGINED_SESSION_ID) == null) {
        %>
        <a href="<%= request.getContextPath().concat(UserConfig.USER_SIGN_UP) %>">회원가입</a>
        <a href="<%= request.getContextPath().concat(UserConfig.USER_SIGN_IN) %>">로그인</a>
        <%
            } else {
                %>
        <a href="#none" onclick="signOutBtnClick()">로그아웃</a>
        <a href="<%= request.getContextPath().concat(UserConfig.USER_MY_INFO) %>">내 정보</a>
        <a href="<%= request.getContextPath().concat(UserConfig.USER_VACCIN_REGIST)%>">내 백신 접종현황</a>
        <%
            }
        %>
        <button id="toggleMode">Light Mode</button>
    </div>
</nav>
