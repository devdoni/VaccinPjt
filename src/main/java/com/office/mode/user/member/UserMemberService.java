package com.office.mode.user.member;

import com.office.mode.Config;
import com.office.mode.user.UserConfig;
import com.office.mode.user.vaccin.dto.ScNumberDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class UserMemberService {

    final String CLASS_NAME = "[UserMemberService]";

    final static public int NO_ID								= 0;
    final static public int ALREADY_IN_USE_ID					= 1;
    final static public int SIGN_UP_SUCCESS                     = 2;
    final static public int SIGN_UP_FAIL                        = 3;
    final static public int SIGN_IN_FAIL						= 4;
    final static public int SIGN_IN_SUCCESS						= 5;
    final static public int MODIFY_SUCCESS						= 6;
    final static public int MODIFY_FAIL							= 7;
    final static public int MEMBER_DELETE_SUCCESS				= 8;
    final static public int MEMBER_DELETE_FAIL					= 9;
    final static public int MY_VACCINE_INJECTION_INFOS_SUCCESS	= 10;
    final static public int MY_VACCINE_INJECTION_INFOS_FAIL		= 11;
    UserMemberDao userMemberDao = new UserMemberDao();

    public int registNewMember(HttpServletRequest request, HttpServletResponse response) {
        System.out.println(CLASS_NAME.concat("registNewMember"));

        String m_id = request.getParameter("m_id");
        String m_pw = request.getParameter("m_pw");
        String m_mail = request.getParameter("m_mail");
        String m_phone = request.getParameter("m_phone");
        String sc_number = request.getParameter("sc_number");

        ScNumberDto scNumberDto = new ScNumberDto();
        scNumberDto.setSc_number(sc_number);

        UserMemberDto userMemberDto = new UserMemberDto();
        userMemberDto.setM_id(m_id);
        userMemberDto.setM_pw(m_pw);
        userMemberDto.setM_mail(m_mail);
        userMemberDto.setM_phone(m_phone);
        userMemberDto.setScNumberDto(scNumberDto);

        int result = userMemberDao.isDuplicateCheck(m_id);
        if (result == ALREADY_IN_USE_ID) {
            System.out.println(CLASS_NAME.concat(m_id + "는 중복된 아이디입니다. "));

            return ALREADY_IN_USE_ID;
        } else {
            System.out.println(CLASS_NAME.concat(m_id + "는 사용 가능한 아이디입니다"));

            result = userMemberDao.registNewMember(userMemberDto);
            if (result == SIGN_UP_SUCCESS) {
                System.out.println(CLASS_NAME.concat("SIGN UP SUCCESS"));
                return SIGN_UP_SUCCESS;

            } else {
                System.out.println(CLASS_NAME.concat("SIGN UP FAIL"));
                return SIGN_UP_FAIL;
            }
        }
    }

    public int userSignIn(HttpServletRequest request, HttpServletResponse response) {
        System.out.println(CLASS_NAME.concat("userSignIn"));

        String m_id = request.getParameter("m_id");
        String m_pw = request.getParameter("m_pw");
        UserMemberDto userMemberDto = new UserMemberDto();
        userMemberDto.setM_id(m_id);
        userMemberDto.setM_pw(m_pw);

        UserMemberDto signinedUserMemberDto = userMemberDao.getUserMemberByMIdAndPw(userMemberDto);
        if (signinedUserMemberDto == null) {
            System.out.println(CLASS_NAME.concat(m_id + "로그인 실패"));
            return SIGN_IN_FAIL;

        } else {
            System.out.println(CLASS_NAME.concat(m_id + "로그인 성공"));

            HttpSession session = request.getSession();
            session.setAttribute(Config.USER_LOGINED_SESSION_ID, signinedUserMemberDto.getM_id());
            session.setMaxInactiveInterval(60 * 60);

            return SIGN_IN_SUCCESS;
        }

    }

    public void memberSignOutConfirm(HttpServletRequest request, HttpServletResponse response) {
        System.out.println(CLASS_NAME.concat("memberSignOutConfirm"));
        HttpSession session = request.getSession();
        session.removeAttribute(Config.USER_LOGINED_SESSION_ID);

    }

    public void userInfoModify(HttpServletRequest request, HttpServletResponse response) {
        System.out.println(CLASS_NAME.concat("userInfoModify"));

        HttpSession session = request.getSession();
        String LoginedUserId = (String) session.getAttribute(Config.USER_LOGINED_SESSION_ID);

        UserMemberDto loginedUserMemberDto = userMemberDao.getUserMemberDtoByMId(LoginedUserId);
        request.setAttribute(UserConfig.LOGINED_USER_MEMBER_DTO, loginedUserMemberDto);

    }

    public int userInfoModifyConfirm(HttpServletRequest request, HttpServletResponse response) {
        System.out.println(CLASS_NAME.concat("userInfoModifyConfirm"));

        String m_id = request.getParameter("m_id");
        String m_pw = request.getParameter("m_pw");
        String m_mail = request.getParameter("m_mail");
        String m_phone = request.getParameter("m_phone");

        int result = userMemberDao.updateMemberInfo(new UserMemberDto(m_id, m_pw, m_mail, m_phone));
        if (result == UserMemberService.MODIFY_SUCCESS) {

            System.out.println("Modify Success");
        } else if (result == UserMemberService.MODIFY_FAIL){

            System.out.println("Modify Fail");
        }

        return result;
    }

    public int deleteUserMemberInfo(HttpServletRequest request, HttpServletResponse response) {
        System.out.println(CLASS_NAME.concat("deleteUserMemberInfo"));

        HttpSession session = request.getSession();
        String LoginedUserId = (String) session.getAttribute(Config.USER_LOGINED_SESSION_ID);

        int result = userMemberDao.unActiveUserMemberByMId(LoginedUserId);
        if (result == MEMBER_DELETE_SUCCESS) {
            System.out.println("Delete Success");

            session.removeAttribute(Config.USER_LOGINED_SESSION_ID);

        } else if (result == MEMBER_DELETE_FAIL) {
            System.out.println("Delete Fail");
        }
        return result;
    }

}
