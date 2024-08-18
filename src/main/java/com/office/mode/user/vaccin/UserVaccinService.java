package com.office.mode.user.vaccin;

import com.office.mode.Config;
import com.office.mode.user.UserConfig;
import com.office.mode.user.member.UserMemberDao;
import com.office.mode.user.member.UserMemberDto;
import com.office.mode.user.vaccin.dto.ScNumberDto;
import com.office.mode.user.vaccin.dto.VaccinLocationDto;
import com.office.mode.user.vaccin.dto.VaccinTypeDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public class UserVaccinService {
    final String CLASS_NAME = "[UserVaccinService]";

    UserVaccinDao userVaccinDao = new UserVaccinDao();
    UserMemberDao userMemberDao = new UserMemberDao();

    final static public int VACCIN_REGIST_SUCCESS = 20;
    final static public int VACCIN_REGIST_FAIL = 21;


    public int userVaccinRegiet(HttpServletRequest request, HttpServletResponse response) {
        System.out.println(CLASS_NAME.concat("userVaccinRegiet()"));

        HttpSession session = request.getSession();
        String loginedUserId = (String) session.getAttribute(Config.USER_LOGINED_SESSION_ID);

        UserMemberDto loginedUserMemberDto = userMemberDao.getUserMemberDtoByMId(loginedUserId);

        System.out.println("LoginedUserMemberDto ===>" + loginedUserMemberDto);

        String sc_name = loginedUserMemberDto.getScNumberDto().getSc_name();
        String fvp_mail = request.getParameter("fvp_mail");
        String fvp_phone = request.getParameter("fvp_phone");
        String sc_number = loginedUserMemberDto.getScNumberDto().getSc_number();
        int fv_type_no = Integer.parseInt(request.getParameter("fv_type_no"));
        int fv_location_no = Integer.parseInt(request.getParameter("fv_location_no"));

        UserVaccinDto userVaccinDto = new UserVaccinDto();
        userVaccinDto.setFvp_name(sc_name);
        userVaccinDto.setFvp_mail(fvp_mail);
        userVaccinDto.setFvp_phone(fvp_phone);

        ScNumberDto scNumberDto = new ScNumberDto();
        scNumberDto.setSc_number(sc_number);

        VaccinTypeDto vaccinTypeDto = new VaccinTypeDto();
        vaccinTypeDto.setFv_type_no(fv_type_no);

        VaccinLocationDto vaccinLocationDto = new VaccinLocationDto();
        vaccinLocationDto.setFv_location_no(fv_location_no);

        userVaccinDto.setScNumberDto(scNumberDto);
        userVaccinDto.setVaccinTypeDto(vaccinTypeDto);
        userVaccinDto.setVaccinLocationDto(vaccinLocationDto);

        int result = userVaccinDao.vaccinRegistConfirm(userVaccinDto);

        if (result == VACCIN_REGIST_SUCCESS) {
            System.out.println("VACCIN REGIST SUCCESS");


        } else if (result == VACCIN_REGIST_FAIL) {
            System.out.println("VACCIN REGIST FAIL");

        }
        return result;
    }

    public void getMyVaccinIjectionRercods(HttpServletRequest request, HttpServletResponse response) {
        System.out.println(CLASS_NAME.concat("userVaccinRegiet()"));

        HttpSession session = request.getSession();
        String loginedUserId = (String) session.getAttribute(Config.USER_LOGINED_SESSION_ID);
        UserMemberDto loginedUserMemberDto = userMemberDao.getUserMemberDtoByMId(loginedUserId);

        List<UserVaccinDto> userVaccinDtos = userVaccinDao.getMyVaccinIjtsByScNumber(loginedUserMemberDto);

        request.setAttribute(UserConfig.USER_VACCIN_INFOS, userVaccinDtos);

    }


}
