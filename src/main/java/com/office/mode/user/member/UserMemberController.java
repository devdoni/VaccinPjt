package com.office.mode.user.member;

import com.office.mode.Config;
import com.office.mode.user.UserConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("*.usermem")
public class UserMemberController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String CLASS_NAME = "[UserMemberController]";
        boolean isUserLogin;

        String requestURI = request.getRequestURI();
        String contextPath = request.getContextPath();
        String command = requestURI.substring(contextPath.length());

        String nextPage = null;
        UserMemberService userMemberService = null;
        switch (command) {
            case UserConfig.USER_SIGN_UP:
                System.out.println(CLASS_NAME.concat(UserConfig.USER_SIGN_UP));
                nextPage = Config.BASIC_USER_VIEW_PATH.concat("/member/sign_up_form").concat(Config.SUFFIX);
                break;
            case UserConfig.USER_SIGN_UP_CONFIRM:
                System.out.println(CLASS_NAME.concat(UserConfig.USER_SIGN_UP_CONFIRM));

                userMemberService = new UserMemberService();
                int result = userMemberService.registNewMember(request, response);

                nextPage = Config.BASIC_USER_VIEW_PATH.concat("/member/sign_up_confirm").concat(Config.SUFFIX).concat("?rst="+result);
                break;

            case UserConfig.USER_SIGN_IN:
                System.out.println(CLASS_NAME.concat(UserConfig.USER_SIGN_IN));

                nextPage = Config.BASIC_USER_VIEW_PATH.concat("/member/sign_in_form").concat(Config.SUFFIX);
                break;

            case UserConfig.USER_SIGN_IN_CONFIRM:
                System.out.println(CLASS_NAME.concat(UserConfig.USER_SIGN_IN_CONFIRM));

                userMemberService = new UserMemberService();
                int resultForSignIn = userMemberService.userSignIn(request, response);

                nextPage = Config.BASIC_USER_VIEW_PATH.concat("/member/sign_in_result")
                        .concat(Config.SUFFIX).concat("?rst="+resultForSignIn);

                break;

            case UserConfig.USER_SIGN_OUT_CONFIRM:
                System.out.println(CLASS_NAME.concat(UserConfig.USER_SIGN_OUT_CONFIRM));

                isUserLogin = UserloginedSessionCheck(request);
                if (isUserLogin) {
                    userMemberService = new UserMemberService();
                    userMemberService.memberSignOutConfirm(request, response);

                    nextPage = Config.BASIC_USER_VIEW_PATH.concat("/");
                } else {
                    nextPage = Config.BASIC_USER_VIEW_PATH.concat("/member/sign_in_form").concat(Config.SUFFIX);
                }

                break;

            case UserConfig.USER_MY_INFO:
                System.out.println(CLASS_NAME.concat(UserConfig.USER_MY_INFO));

                isUserLogin = UserloginedSessionCheck(request);
                if (isUserLogin) {
                    nextPage = Config.BASIC_USER_VIEW_PATH.concat("/member/myinfo").concat(Config.SUFFIX);
                } else {
                    nextPage = Config.BASIC_USER_VIEW_PATH.concat("/member/sign_in_form").concat(Config.SUFFIX);
                }

                break;

            case UserConfig.USER_INFO_MODIFY:
                System.out.println(CLASS_NAME.concat(UserConfig.USER_INFO_MODIFY));

                isUserLogin = UserloginedSessionCheck(request);
                if (isUserLogin) {
                    userMemberService = new UserMemberService();
                    userMemberService.userInfoModify(request, response);

                    nextPage = Config.BASIC_USER_VIEW_PATH.concat("/member/modify_form").concat(Config.SUFFIX);

                } else {
                    nextPage = Config.BASIC_USER_VIEW_PATH.concat("/member/sign_in_form").concat(Config.SUFFIX);
                }

                break;

            case UserConfig.USER_INFO_MODIFY_CONFIRM:
                System.out.println(CLASS_NAME.concat(UserConfig.USER_INFO_MODIFY_CONFIRM));

                isUserLogin = UserloginedSessionCheck(request);
                if (isUserLogin) {
                    userMemberService = new UserMemberService();
                    int resultForModify = userMemberService.userInfoModifyConfirm(request, response);

                    nextPage = Config.BASIC_USER_VIEW_PATH.concat("/member/modify_result").concat(Config.SUFFIX).concat("?rst="+resultForModify);

                } else {
                    nextPage = Config.BASIC_USER_VIEW_PATH.concat("/member/sign_in_form").concat(Config.SUFFIX);
                }

                break;

            case UserConfig.USER_INFO_DELETE_CONFIRM:
                System.out.println(CLASS_NAME.concat(UserConfig.USER_INFO_DELETE_CONFIRM));

                isUserLogin = UserloginedSessionCheck(request);
                if (isUserLogin) {
                    userMemberService = new UserMemberService();
                    int resultForDeletedUser = userMemberService.deleteUserMemberInfo(request, response);
                    nextPage = Config.BASIC_USER_VIEW_PATH.concat("/member/delete_result").concat(Config.SUFFIX).concat("?rst="+resultForDeletedUser);
                } else {
                    nextPage = Config.BASIC_USER_VIEW_PATH.concat("/member/sign_in_form").concat(Config.SUFFIX);
                }
                break;
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
        dispatcher.forward(request, response);
    }

    public static boolean UserloginedSessionCheck(HttpServletRequest request) {
        System.out.println("[UserMemberController] loginedSessionCheck()");

        HttpSession session = request.getSession();
        Object obj = session.getAttribute(Config.USER_LOGINED_SESSION_ID);
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
