<%@ page import="com.office.mode.user.UserConfig" %>
<%@ page import="com.office.mode.user.vaccin.UserVaccinDto" %>
<%@ page import="java.util.List" %>
<%@ page import="com.office.mode.user.vaccin.dto.ScNumberDto" %>
<%@ page import="com.office.mode.user.vaccin.dto.VaccinLocationDto" %>
<%@ page import="com.office.mode.user.vaccin.dto.VaccinTypeDto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link href="<%= request.getContextPath()%>/res/css/user/list.css" rel="stylesheet" type="text/css">
    <link href="<%= request.getContextPath()%>/res/css/user/include.css" rel="stylesheet" type="text/css">
    <script src="<%= request.getContextPath()%>/res/js/user/vaccin.js" type="text/javascript"></script>
    <script src="<%= request.getContextPath()%>/res/js/modeToggle.js" type="text/javascript"></script>
</head>
<body>

<jsp:include page="../include/header.jsp" />
<jsp:include page="../include/nav.jsp" />

<section>
    <div class="section_wrap">
        <div class="article">
            <h3>VACCIN REGIST</h3>
            <form action="<%= request.getContextPath().concat(UserConfig.USER_VACCIN_REGIST_CONFIRM) %>" name="regist_form" method="post">
                <table class="regist">
                    <tr>
                        <td>접종자 메일</td>
                        <td>
                            <input type="email" name="fvp_mail">
                        </td>
                    </tr>
                    <tr>
                        <td>접종자 연락처</td>
                        <td>
                            <input type="text" name="fvp_phone">
                        </td>
                    </tr>
                    <tr>
                        <td>백신타입</td>
                        <td>
                            <select name="fv_type_no">
                                <option value="">-- 선택 --</option>
                                <option value="1">A타입</option>
                                <option value="2">B타입</option>
                                <option value="3">C타입</option>
                                <option value="4">F타입</option>
                                <option value="5">E타입</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>접종처</td>
                        <td>
                            <select name="fv_location_no">
                                <option value="">-- 선택 --</option>
                                <option value="1001">A접종처</option>
                                <option value="1002">B접종처</option>
                                <option value="1003">C접종처</option>
                                <option value="1004">D접종처</option>
                                <option value="1005">E접종처</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <input type="button" value="등록" onclick="registForm()">
                            <input type="reset" value="초기화">
                        </td>
                    </tr>
                </table>
            </form>
        </div>
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
                    List<UserVaccinDto> userVaccinDtos = (List<UserVaccinDto>) request.getAttribute(UserConfig.USER_VACCIN_INFOS);

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
