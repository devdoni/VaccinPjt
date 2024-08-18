package com.office.mode.user.vaccin;

import com.office.mode.Config;
import com.office.mode.user.member.UserMemberDto;
import com.office.mode.user.member.UserMemberService;
import com.office.mode.user.vaccin.dto.ScNumberDto;
import com.office.mode.user.vaccin.dto.VaccinLocationDto;
import com.office.mode.user.vaccin.dto.VaccinTypeDto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserVaccinDao {

    final String CLASS_NAME = "[UserVaccinDao]";

    public int vaccinRegistConfirm(UserVaccinDto userVaccinDto) {
        System.out.println(CLASS_NAME.concat("vaccinRegistConfirm"));

        Connection conn = null;
        PreparedStatement pstmt = null;
        int result = -1;

        try {
            Class.forName(Config.DB_DRIVER_NAME);
            conn = DriverManager.getConnection(Config.DB_URL, Config.DB_USER_ID, Config.DB_USER_PW);
            String sql = "INSERT INTO TBL_FLU_VACCIN_PERSON(" +
                    "FVP_NAME, " +
                    "SC_NUMBER, " +
                    "FVP_MAIL, " +
                    "FVP_PHONE, " +
                    "FV_LOCATION_NO, " +
                    "FV_TYPE_NO) " +
                    "VALUES(?, ?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userVaccinDto.getFvp_name());
            pstmt.setString(2, userVaccinDto.getScNumberDto().getSc_number());
            pstmt.setString(3, userVaccinDto.getFvp_mail());
            pstmt.setString(4, userVaccinDto.getFvp_phone());
            pstmt.setInt(5, userVaccinDto.getVaccinLocationDto().getFv_location_no());
            pstmt.setInt(6, userVaccinDto.getVaccinTypeDto().getFv_type_no());

            result = pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }

        }
        return result > 0
                ? UserVaccinService.VACCIN_REGIST_SUCCESS
                : UserVaccinService.VACCIN_REGIST_FAIL;
    }

    public List<UserVaccinDto> getMyVaccinIjtsByScNumber(UserMemberDto loginedUserMemberDto) {
        System.out.println(CLASS_NAME.concat("getMyVaccinIjtsByScNumber"));

        Connection conn             = null;
        PreparedStatement pstmt     = null;
        ResultSet rs                = null;
        List<UserVaccinDto> userVaccinDtos = new ArrayList<UserVaccinDto>();

        try {
            Class.forName(Config.DB_DRIVER_NAME);
            conn = DriverManager.getConnection(Config.DB_URL, Config.DB_USER_ID, Config.DB_USER_PW);
            String sql = "SELECT * FROM TBL_FLU_VACCIN_PERSON FVP " +
                    "JOIN TBL_SOCIAL_NUMBER SN " +
                    "ON FVP.SC_NUMBER = SN.SC_NUMBER " +
                    "JOIN TBL_FV_LOCATION FL " +
                    "ON FVP.FV_LOCATION_NO = FL.FV_LOCATION_NO " +
                    "JOIN TBL_FV_TYPE FT " +
                    "ON FVP.FV_TYPE_NO = FT.FV_TYPE_NO " +
                    "WHERE FVP.SC_NUMBER = ? " +
                    "ORDER BY FVP.FVP_NO DESC";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, loginedUserMemberDto.getScNumberDto().getSc_number());

            rs = pstmt.executeQuery();

            while (rs.next()) {
                UserVaccinDto userVaccinDto = new UserVaccinDto();
                ScNumberDto scNumberDto = new ScNumberDto();
                VaccinLocationDto vaccinLocationDto = new VaccinLocationDto();
                VaccinTypeDto vaccinTypeDto = new VaccinTypeDto();

                userVaccinDto.setFvp_no(rs.getInt("fvp.fvp_no"));
                userVaccinDto.setFvp_name(rs.getString("fvp.fvp_name"));
                userVaccinDto.setFvp_mail(rs.getString("fvp.fvp_mail"));
                userVaccinDto.setFvp_phone(rs.getString("fvp.fvp_phone"));

                scNumberDto.setSc_number(rs.getString("sn.sc_number"));
                scNumberDto.setSc_name(rs.getString("sn.sc_name"));
                scNumberDto.setSc_addr(rs.getString("sn.sc_addr"));
                scNumberDto.setSc_phone(rs.getString("sn.sc_phone"));
                scNumberDto.setSc_reg_date(rs.getString("sn.sc_reg_date"));
                scNumberDto.setSc_mod_date(rs.getString("sn.sc_mod_date"));

                userVaccinDto.setScNumberDto(scNumberDto);

                vaccinLocationDto.setFv_location_no(rs.getInt("fl.fv_location_no"));
                vaccinLocationDto.setFv_location_name(rs.getString("fl.fv_location_name"));
                vaccinLocationDto.setFv_location_addr(rs.getString("fl.fv_location_addr"));
                vaccinLocationDto.setFv_location_phone(rs.getString("fl.fv_location_phone"));
                vaccinLocationDto.setFv_location_manager(rs.getString("fl.fv_location_manager"));
                vaccinLocationDto.setFv_location_reg_date(rs.getString("fl.fv_location_reg_date"));
                vaccinLocationDto.setFv_location_mod_date(rs.getString("fl.fv_location_mod_date"));

                userVaccinDto.setVaccinLocationDto(vaccinLocationDto);

                vaccinTypeDto.setFv_type_no(rs.getInt("ft.fv_type_no"));
                vaccinTypeDto.setFv_type(rs.getString("ft.fv_type"));
                vaccinTypeDto.setFv_type_mnf_ctr(rs.getString("ft.fv_type_mnf_ctr"));
                vaccinTypeDto.setFv_type_mnf_date(rs.getString("ft.fv_type_mnf_date"));
                vaccinTypeDto.setFv_type_reg_date(rs.getString("ft.fv_type_reg_date"));
                vaccinTypeDto.setFv_type_mod_date(rs.getString("ft.fv_type_mod_date"));

                userVaccinDto.setVaccinTypeDto(vaccinTypeDto);
                userVaccinDto.setFvp_reg_date(rs.getString("fvp_reg_date"));
                userVaccinDto.setFvp_mod_date(rs.getString("fvp_mod_date"));

                userVaccinDtos.add(userVaccinDto);

            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }

        }
        return userVaccinDtos.size() > 0 ? userVaccinDtos : null;
    }
}
