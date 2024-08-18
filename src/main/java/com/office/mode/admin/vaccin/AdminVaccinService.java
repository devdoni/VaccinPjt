package com.office.mode.admin.vaccin;

import com.office.mode.admin.AdminConfig;
import com.office.mode.user.vaccin.UserVaccinDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public class AdminVaccinService {

    final String CLASS_NAME = "[AdminVaccinService]";

    AdminVaccinDao adminVaccinDao = new AdminVaccinDao();

    public void getAllVaccinInjectionInfos(HttpServletRequest request, HttpServletResponse response) {
        System.out.println(CLASS_NAME.concat("getAllVaccinInjectionInfos"));

       List<UserVaccinDto> userVaccinDtos = adminVaccinDao.getAllVaccinInjectionInfos();
        request.setAttribute(AdminConfig.ALL_VACCIN_INJECTION_INFOS, userVaccinDtos);

    }
}
