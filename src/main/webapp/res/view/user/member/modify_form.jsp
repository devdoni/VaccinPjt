<%@ page import="com.office.mode.user.UserConfig" %>
<%@ page import="com.office.mode.user.member.UserMemberDto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>백신 접종 현황 프로그램</title>

    <link href="<%= request.getContextPath() %>/res/css/user/signup.css" rel="stylesheet" type="text/css">
    <link href="<%= request.getContextPath() %>/res/css/user/include.css" rel="stylesheet" type="text/css">
    <script src="<%= request.getContextPath() %>/res/js/modeToggle.js"></script>
    <script src="<%= request.getContextPath() %>/res/js/user/member.js" type="text/javascript"></script>

</head>
<body>

    <jsp:include page="../include/header.jsp" />
    <jsp:include page="../include/nav.jsp" />

<section>
    <div class="section_wrap">
        <div class="article">
            <form action="<%= request.getContextPath().concat(UserConfig.USER_INFO_MODIFY_CONFIRM)%>" name="modify_form" method="post">
                <%
                    UserMemberDto userInfo = (UserMemberDto) request.getAttribute(UserConfig.LOGINED_USER_MEMBER_DTO);

                %>
                <table>
                    <tr>
                        <td>아이디</td>
                        <td>
                            <input type="text" name="m_id" value="<%= userInfo.getM_id() %>" readonly >
                        </td>
                    </tr>
                    <tr>
                        <td>비밀번호</td>
                        <td>
                            <input type="password" name="m_pw" value="<%= userInfo.getM_pw() %>" placeholder="비밀번호를 입력해주세요">
                        </td>
                    </tr>
                    <tr>
                        <td>메일</td>
                        <td>
                            <input type="email" name="m_mail" value="<%= userInfo.getM_mail() %>" placeholder="메일을 입력해주세요">
                        </td>
                    </tr>
                    <tr>
                        <td>전화번호</td>
                        <td>
                            <input type="text" name="m_phone" value="<%= userInfo.getM_phone() %>" placeholder="전화번호를 입력해주세요">
                        </td>
                    </tr>
                    <tr>
                        <td>주민등록번호</td>
                        <td>
                            <input type="text" name="sc_number" value="<%= userInfo.getScNumberDto().getSc_number() %>" readonly>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <input type="button" value="정보수정" onclick="modifyForm();">
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
