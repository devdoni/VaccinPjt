package com.office.mode.user.vaccin;

import com.office.mode.Config;
import com.office.mode.user.UserConfig;
import com.office.mode.user.member.UserMemberController;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("*.uservac")
public class UserVaccinController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String CLASS_NAME = "[UserVaccinController]";
        boolean isUserLogin;

        String requestURI = request.getRequestURI();
        String contextPath = request.getContextPath();
        String command = requestURI.substring(contextPath.length());

        String nextPage = null;
        UserVaccinService userVaccinService = null;

        switch (command) {
            case UserConfig.USER_VACCIN_REGIST:
                System.out.println(CLASS_NAME.concat(UserConfig.USER_VACCIN_REGIST));

                isUserLogin = UserMemberController.UserloginedSessionCheck(request);
                if (isUserLogin) {
                    userVaccinService = new UserVaccinService();
                    userVaccinService.getMyVaccinIjectionRercods(request, response);
                    nextPage = Config.BASIC_USER_VIEW_PATH.concat("/vaccin/list").concat(Config.SUFFIX);
                } else {
                    nextPage = Config.BASIC_USER_VIEW_PATH.concat("/member/sign_in_form").concat(Config.SUFFIX);
                }

                break;

            case UserConfig.USER_VACCIN_REGIST_CONFIRM:
                System.out.println(CLASS_NAME.concat(UserConfig.USER_VACCIN_REGIST_CONFIRM));
                isUserLogin = UserMemberController.UserloginedSessionCheck(request);
                if (isUserLogin) {
                    userVaccinService = new UserVaccinService();
                    int resultForVaccinRegist = userVaccinService.userVaccinRegiet(request, response);

                    nextPage = Config.BASIC_USER_VIEW_PATH.concat("/vaccin/vaccin_result").concat(Config.SUFFIX).concat("?rst=" + resultForVaccinRegist);
                } else {
                    nextPage = Config.BASIC_USER_VIEW_PATH.concat("/member/sign_in_form").concat(Config.SUFFIX);
                }
                break;

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
