package com.office.mode.admin.member;

import com.office.mode.Config;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AdminMemberService {
    final String CLASS_NAME = "[AdminMemberService]";

    AdminMemberDao adminMemberDao = new AdminMemberDao();
    final static public int SIGN_UP_SUCCESS                     = 2;
    final static public int SIGN_UP_FAIL                        = 3;
    final static public int SIGN_IN_FAIL						= 4;
    final static public int SIGN_IN_SUCCESS						= 5;

    public int adminSignIn(HttpServletRequest request, HttpServletResponse response) {
        System.out.println(CLASS_NAME.concat("adminSignIn()"));

        String am_id = request.getParameter("am_id");
        String am_pw = request.getParameter("am_pw");

        AdminMemberDto adminMemberDto = new AdminMemberDto(am_id, am_pw);

        AdminMemberDto loginedAdminMemberDto = adminMemberDao.adminSignIn(adminMemberDto);
        if (loginedAdminMemberDto == null) {
            System.out.println(am_id + "로그인 실패");
            return SIGN_IN_FAIL;
        } else {
            System.out.println(am_id + "로그인 성공");

            HttpSession session = request.getSession();
            session.setAttribute(Config.ADMIN_LOGINED_SESSION_ID, loginedAdminMemberDto.getAm_id());
            session.setMaxInactiveInterval(60 * 60);
            return SIGN_IN_SUCCESS;
        }
    }

    public void adminMemberSignOut(HttpServletRequest request, HttpServletResponse response) {
        System.out.println(CLASS_NAME.concat("adminMemberSignOut()"));

        HttpSession session = request.getSession();
        session.removeAttribute(Config.ADMIN_LOGINED_SESSION_ID);

    }

    public int adminNewMemberRegist(HttpServletRequest request, HttpServletResponse response) {
        System.out.println(CLASS_NAME.concat("adminNewMemberRegist()"));

        String am_id = request.getParameter("am_id");
        String am_pw = request.getParameter("am_pw");
        String am_name = request.getParameter("am_name");
        String am_auth_code = request.getParameter("am_auth_code");
        AdminMemberDto adminMemberDto = new AdminMemberDto(am_id, am_pw, am_name, am_auth_code);

        String authCode = adminMemberDao.getAdminAuthCode();

        if (am_auth_code.equals(authCode)) {
            System.out.println(CLASS_NAME.concat("Auth Code Check Success"));
            int result = adminMemberDao.adminNewMemberRegist(adminMemberDto);
            if (result == SIGN_UP_SUCCESS) {
                System.out.println(CLASS_NAME.concat("Admin Regist Success"));
                return SIGN_UP_SUCCESS;
            } else {
                System.out.println(CLASS_NAME.concat("Admin Regist Fail"));
                return SIGN_UP_FAIL;
            }

        } else {
            System.out.println(CLASS_NAME.concat("Auth Code Check Fail"));
            return SIGN_UP_FAIL;
        }
    }
}
