package com.office.mode.admin.member;

import com.office.mode.Config;
import com.office.mode.user.member.UserMemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AdminMemberDao {

    final String CLASS_NAME = "[AdminMemberDao]";

    public AdminMemberDto adminSignIn(AdminMemberDto adminMemberDto) {

        System.out.println(CLASS_NAME.concat("adminSignIn()"));

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<AdminMemberDto> adminUserMemberDtos = new ArrayList<AdminMemberDto>();

        try {
            Class.forName(Config.DB_DRIVER_NAME);
            conn = DriverManager.getConnection(Config.DB_URL, Config.DB_USER_ID, Config.DB_USER_PW);
            String sql = "SELECT * FROM TBL_ADMIN_MEMBER WHERE AM_ID = ? AND AM_PW = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, adminMemberDto.getAm_id());
            pstmt.setString(2, adminMemberDto.getAm_pw());

            rs = pstmt.executeQuery();
            if (rs.next()) {
                AdminMemberDto dto = new AdminMemberDto();

                dto.setAm_id(rs.getString("am_id"));
                dto.setAm_pw(rs.getString("am_pw"));
                dto.setAm_reg_date(rs.getString("am_reg_date"));
                dto.setAm_mod_date(rs.getString("am_mod_date"));

                adminUserMemberDtos.add(dto);
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
        return adminUserMemberDtos.size() > 0
                ? adminUserMemberDtos.get(0)
                : null;
    }

    public String getAdminAuthCode() {
        System.out.println(CLASS_NAME.concat("adminAuthCheck"));

        String authCode = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            Class.forName(Config.DB_DRIVER_NAME);
            conn = DriverManager.getConnection(Config.DB_URL, Config.DB_USER_ID, Config.DB_USER_PW);
            String sql = "SELECT AUTH_CODE FROM TBL_ADMIN_AUTH_CODE WHERE AUTH_CODE_IS_ACTIVE = TRUE LIMIT 1";
            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                authCode = rs.getString("AUTH_CODE");
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
        return authCode;
    }

    public int adminNewMemberRegist(AdminMemberDto adminMemberDto) {
        System.out.println(CLASS_NAME.concat("adminAuthCheck"));

        Connection conn = null;
        PreparedStatement pstmt = null;
        int result = -1;

        try {
            Class.forName(Config.DB_DRIVER_NAME);
            conn = DriverManager.getConnection(Config.DB_URL,Config.DB_USER_ID,Config.DB_USER_PW);
            String sql = "INSERT INTO TBL_ADMIN_MEMBER(AM_ID, AM_PW, AM_NAME) VALUES(?,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, adminMemberDto.getAm_id());
            pstmt.setString(2, adminMemberDto.getAm_pw());
            pstmt.setString(3, adminMemberDto.getAm_name());
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
                ? AdminMemberService.SIGN_UP_SUCCESS
                : AdminMemberService.SIGN_UP_FAIL;
    }
}
