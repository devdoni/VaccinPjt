<%@ page import="com.office.mode.user.UserConfig" %>
<%@ page import="com.office.mode.admin.AdminConfig" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>백신 접종 현황 프로그램</title>

    <link href="<%= request.getContextPath() %>/res/css/admin/include.css" rel="stylesheet" type="text/css">
    <link href="<%= request.getContextPath() %>/res/css/user/signup.css" rel="stylesheet" type="text/css">
    <script src="<%= request.getContextPath() %>/res/js/modeToggle.js"></script>
    <script src="<%= request.getContextPath() %>/res/js/admin/member.js" type="text/javascript"></script>

</head>
<body>

    <jsp:include page="../include/header.jsp" />
    <jsp:include page="../include/nav.jsp" />

<section>
    <div class="section_wrap">
        <div class="article">
            <form action="<%= request.getContextPath().concat(AdminConfig.MEMBER_SIGN_UP_CONFIRM)%>" name="admin_signup_form" method="post">
                <table>
                    <tr>
                        <td>아이디</td>
                        <td>
                            <input type="text" name="am_id" placeholder="아이디를 입력해주세요">
                        </td>
                    </tr>
                    <tr>
                        <td>비밀번호</td>
                        <td>
                            <input type="password" name="am_pw" placeholder="비밀번호를 입력해주세요">
                        </td>
                    </tr>
                    <tr>
                        <td>관리자 이름</td>
                        <td>
                            <input type="text" name="am_name" placeholder="비밀번호를 입력해주세요">
                        </td>
                    </tr>
                    <tr>
                        <td>관리자 인증번호</td>
                        <td>
                            <input type="text" name="am_auth_code" placeholder="관리자 인증번호를 입력해주세요">
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <input type="button" value="회원가입" onclick="adminSignUpForm();">
                            <input type="reset" value="초기화">
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</section>
    <jsp:include page="../include/footer.jsp" />

</body>
</html>
