<%@ page import="com.office.mode.user.UserConfig" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    function deleteBtnClick() {
        let result = confirm("정말로 회원탈퇴를 하시겠습니까?");

        if (result) {
            window.location.href = "<%= request.getContextPath().concat(UserConfig.USER_INFO_DELETE_CONFIRM)%>";
        }
    }
</script>