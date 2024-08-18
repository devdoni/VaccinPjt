<%@ page import="com.office.mode.admin.AdminConfig" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    function signOutBtnClick() {
        let result = confirm("정말로 로그아웃 하시겠습니까?");

        if (result) {
            window.location.href = "<%= request.getContextPath().concat(AdminConfig.MEMBER_SIGN_OUT_CONFIRM) %>";
        }
    }
</script>