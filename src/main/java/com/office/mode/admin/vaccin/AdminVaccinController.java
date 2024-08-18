package com.office.mode.admin.vaccin;

import com.office.mode.Config;
import com.office.mode.admin.AdminConfig;
import com.office.mode.admin.member.AdminMemberController;
import com.office.mode.admin.member.AdminMemberService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("*.adminvac")
public class AdminVaccinController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String CLASS_NAME = "[AdminVaccinController]";
        boolean isAdminLogin;

        String requestURI = request.getRequestURI();
        String contextPath = request.getContextPath();
        String command = requestURI.substring(contextPath.length());

        String nextPage = null;
        AdminVaccinService adminVaccinService = null;

        switch (command) {
            case AdminConfig.VACCIN_LIST:
                System.out.println(CLASS_NAME.concat(AdminConfig.VACCIN_LIST));

                isAdminLogin = AdminMemberController.adminloginedSessionCheck(request);
                if (isAdminLogin) {
                    adminVaccinService = new AdminVaccinService();
                    adminVaccinService.getAllVaccinInjectionInfos(request, response);

                    nextPage = Config.BASIC_ADMIN_VIEW_PATH.concat("/vaccin/list").concat(Config.SUFFIX);

                } else {
                    nextPage = Config.BASIC_ADMIN_VIEW_PATH.concat("/member/sign_in_form").concat(Config.SUFFIX);
                }


        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        doGet(request, response);
    }
}
