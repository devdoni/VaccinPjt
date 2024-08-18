<%@ page import="com.office.mode.user.vaccin.UserVaccinDto" %>
<%@ page import="java.util.List" %>
<%@ page import="com.office.mode.user.vaccin.dto.ScNumberDto" %>
<%@ page import="com.office.mode.user.vaccin.dto.VaccinLocationDto" %>
<%@ page import="com.office.mode.user.vaccin.dto.VaccinTypeDto" %>
<%@ page import="com.office.mode.admin.AdminConfig" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link href="<%= request.getContextPath()%>/res/css/user/list.css" rel="stylesheet" type="text/css">
    <link href="<%= request.getContextPath()%>/res/css/admin/include.css" rel="stylesheet" type="text/css">
    <script src="<%= request.getContextPath()%>/res/js/user/vaccin.js" type="text/javascript"></script>
    <script src="<%= request.getContextPath()%>/res/js/modeToggle.js" type="text/javascript"></script>
</head>
<body>

<jsp:include page="../include/header.jsp" />
<jsp:include page="../include/nav.jsp" />

<section>
    <div class="section_wrap">
        <div class="article">
            <h3>VACCIN LIST</h3>

            <table class="list">
                <thead>
                    <tr>
                        <th>접종번호</th>
                        <th>접종자 이름</th>
                        <th>접종자 주민등록번호</th>
                        <th>접종자 메일</th>
                        <th>접종자 전화번호</th>
                        <th>접종처 번호</th>
                        <th>백신 번호</th>
                        <th>접종일</th>
                        <th>접종 수정일</th>
                    </tr>
                </thead>
                <tbody>
                <%
                    List<UserVaccinDto> userVaccinDtos = (List<UserVaccinDto>) request.getAttribute(AdminConfig.ALL_VACCIN_INJECTION_INFOS);

                    if (userVaccinDtos != null) {

                        for (int i = 0; i < userVaccinDtos.size(); i++) {
                            UserVaccinDto dto = userVaccinDtos.get(i);
                            ScNumberDto scDto = dto.getScNumberDto();
                            VaccinLocationDto vaccinLocDto = dto.getVaccinLocationDto();
                            VaccinTypeDto vaccinTypeDto = dto.getVaccinTypeDto();
                %>
                <tr>
                    <td><%=dto.getFvp_no()%></td>
                    <td><%=dto.getFvp_name()%></td>
                    <td><%=dto.getScNumberDto().getSc_number()%></td>
                    <td><%=dto.getFvp_mail()%></td>
                    <td><%=dto.getFvp_phone()%></td>
                    <td><%=dto.getVaccinLocationDto().getFv_location_no()%></td>
                    <td><%=dto.getVaccinTypeDto().getFv_type_no()%></td>
                    <td><%=dto.getFvp_reg_date()%></td>
                    <td><%=dto.getFvp_mod_date()%></td>
                </tr>
                <%
                        }
                %>
                <%
                    } else {
                %>
                    <tr>
                        <td colspan="9">
                            접종 기록이 없습니다.
                        </td>
                    </tr>
                <%
                }
                %>
                </tbody>
            </table>
        </div>
    </div>
</section>

<jsp:include page="../include/footer.jsp" />

</body>
</html>
