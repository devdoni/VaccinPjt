package com.office.mode.admin.member;

import com.office.mode.Config;
import com.office.mode.admin.AdminConfig;
import com.office.mode.user.UserConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("*.adminmem")
public class AdminMemberController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String CLASS_NAME = "[AdminMemberController]";
        boolean isAdminLogin;

        String requestURI = request.getRequestURI();
        String contextPath = request.getContextPath();
        String command = requestURI.substring(contextPath.length());

        String nextPage = null;
        AdminMemberService adminMemberService = null;

        switch (command) {
            case AdminConfig.MEMBER_SIGN_IN:
                System.out.println(CLASS_NAME.concat(AdminConfig.MEMBER_SIGN_IN));

                nextPage = Config.BASIC_ADMIN_VIEW_PATH.concat("/member/sign_in_form").concat(Config.SUFFIX);

                break;

            case AdminConfig.MEMBER_SIGN_IN_CONFIRM:
                System.out.println(CLASS_NAME.concat(AdminConfig.MEMBER_SIGN_IN_CONFIRM));

                adminMemberService = new AdminMemberService();
                int resultForAdminSignIn = adminMemberService.adminSignIn(request, response);

                nextPage = Config.BASIC_ADMIN_VIEW_PATH.concat("/member/sign_in_result")
                        .concat(Config.SUFFIX).concat("?rst=" + resultForAdminSignIn);

                break;

            case AdminConfig.MEMBER_SIGN_OUT_CONFIRM:
                System.out.println(CLASS_NAME.concat(AdminConfig.MEMBER_SIGN_OUT_CONFIRM));

                adminMemberService = new AdminMemberService();
                adminMemberService.adminMemberSignOut(request, response);

                nextPage = Config.BASIC_ADMIN_VIEW_PATH.concat("/");

                break;

            case AdminConfig.MEMBER_SIGN_UP:
                System.out.println(CLASS_NAME.concat(AdminConfig.MEMBER_SIGN_UP));

                nextPage = Config.BASIC_ADMIN_VIEW_PATH.concat("/member/sign_up_form").concat(Config.SUFFIX);


                break;

            case AdminConfig.MEMBER_SIGN_UP_CONFIRM:
                System.out.println(CLASS_NAME.concat(AdminConfig.MEMBER_SIGN_UP_CONFIRM));

                adminMemberService = new AdminMemberService();
                int result = adminMemberService.adminNewMemberRegist(request, response);

                nextPage = Config.BASIC_ADMIN_VIEW_PATH.concat("/member/sign_up_result").concat(Config.SUFFIX).concat("?rst=" + result);

                break;

        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
        dispatcher.forward(request, response);
    }

    public static boolean adminloginedSessionCheck(HttpServletRequest request) {
        System.out.println("[UserMemberController] loginedSessionCheck()");

        HttpSession session = request.getSession();
        Object obj = session.getAttribute(Config.ADMIN_LOGINED_SESSION_ID);
        if (obj != null) {
            return true;
        } else {
            return false;
        }

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding(Config.ENCODING);
        doGet(request, response);
    }
}
