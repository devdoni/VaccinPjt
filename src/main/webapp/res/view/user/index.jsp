<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>백신 접종 현황 프로그램</title>


    <link href="<%= request.getContextPath() %>/res/css/index.css" rel="stylesheet" type="text/css">
    <script src="<%= request.getContextPath() %>/res/js/modeToggle.js"></script>

</head>
<body>

    <jsp:include page="include/header.jsp" />
    <jsp:include page="include/nav.jsp" />

<section>
    <div class="section_wrap">
        <div class="article">
            <h3>USER MODE</h3>
        </div>
    </div>
</section>
    <jsp:include page="include/footer.jsp" />

</body>
</html>
