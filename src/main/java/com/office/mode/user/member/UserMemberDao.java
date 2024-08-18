package com.office.mode.user.member;

import com.office.mode.Config;
import com.office.mode.user.vaccin.dto.ScNumberDto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserMemberDao {

    final String CLASS_NAME = "[UserMemberDao]";

    public int isDuplicateCheck(String m_id) {
        System.out.println(CLASS_NAME.concat("isDuplicateCheck"));

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int result = -1;

        try {
            Class.forName(Config.DB_DRIVER_NAME);
            conn = DriverManager.getConnection(Config.DB_URL, Config.DB_USER_ID, Config.DB_USER_PW);
            String sql = "SELECT COUNT(*) AS CNT FROM TBL_USER_MEMBER WHERE M_ID = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, m_id);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                result = rs.getInt("CNT");
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
        return result > 0
                ? UserMemberService.ALREADY_IN_USE_ID
                : UserMemberService.NO_ID;
    }

    public int registNewMember(UserMemberDto userMemberDto) {
        System.out.println(CLASS_NAME.concat("registNewMember()"));

        Connection conn = null;
        PreparedStatement pstmt = null;
        int result = -1;

        try {
            Class.forName(Config.DB_DRIVER_NAME);
            conn = DriverManager.getConnection(Config.DB_URL,Config.DB_USER_ID,Config.DB_USER_PW);
            String sql = "INSERT INTO TBL_USER_MEMBER(M_ID, M_PW, M_MAIL, M_PHONE, SC_NUMBER) VALUES(?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userMemberDto.getM_id());
            pstmt.setString(2, userMemberDto.getM_pw());
            pstmt.setString(3, userMemberDto.getM_mail());
            pstmt.setString(4, userMemberDto.getM_phone());
            pstmt.setString(5, userMemberDto.getScNumberDto().getSc_number());
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
                ? UserMemberService.SIGN_UP_SUCCESS
                : UserMemberService.SIGN_UP_FAIL;
    }


    public UserMemberDto getUserMemberByMIdAndPw(UserMemberDto userMemberDto) {
        System.out.println(CLASS_NAME.concat("getUserMemberByMIdAndPw"));

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<UserMemberDto> userMemberDtos = new ArrayList<UserMemberDto>();

        try {
            Class.forName(Config.DB_DRIVER_NAME);
            conn = DriverManager.getConnection(Config.DB_URL, Config.DB_USER_ID, Config.DB_USER_PW);
            String sql = "SELECT * FROM TBL_USER_MEMBER WHERE M_ID = ? AND M_PW = ? AND M_IS_ACTIVE = TRUE";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userMemberDto.getM_id());
            pstmt.setString(2, userMemberDto.getM_pw());

            rs = pstmt.executeQuery();
            if (rs.next()) {
                UserMemberDto dto = new UserMemberDto();

                dto.setM_id(rs.getString("m_id"));
                dto.setM_pw(rs.getString("m_pw"));
                dto.setM_mail(rs.getString("m_mail"));
                dto.setM_phone(rs.getString("m_phone"));

                ScNumberDto scNumberDto = new ScNumberDto();
                scNumberDto.setSc_number(rs.getString("sc_number"));
                dto.setScNumberDto(scNumberDto);

                dto.setM_reg_date(rs.getString("m_reg_date"));
                dto.setM_mod_date(rs.getString("m_mod_date"));

                userMemberDtos.add(dto);
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
        return userMemberDtos.size() > 0
                ? userMemberDtos.get(0)
                : null;
    }

    public UserMemberDto getUserMemberDtoByMId(String loginedUserId) {
        System.out.println(CLASS_NAME.concat("getUserMemberDtoByMId"));
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<UserMemberDto> userMemberDtos = new ArrayList<UserMemberDto>();

        try {
            Class.forName(Config.DB_DRIVER_NAME);
            conn = DriverManager.getConnection(Config.DB_URL, Config.DB_USER_ID, Config.DB_USER_PW);
                String sql = "SELECT * FROM TBL_USER_MEMBER U JOIN TBL_SOCIAL_NUMBER S " +
                        "ON U.SC_NUMBER = S.SC_NUMBER " +
                        "WHERE M_ID = ?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, loginedUserId);
            rs = pstmt.executeQuery();

            while (rs.next()) {

                UserMemberDto dto = new UserMemberDto();

                dto.setM_id(rs.getString("m_id"));
                dto.setM_pw(rs.getString("m_pw"));
                dto.setM_mail(rs.getString("m_mail"));
                dto.setM_phone(rs.getString("m_phone"));

                ScNumberDto scNumberDto = new ScNumberDto();
                scNumberDto.setSc_number(rs.getString("sc_number"));
                scNumberDto.setSc_name(rs.getString("sc_name"));
                dto.setScNumberDto(scNumberDto);
                dto.setM_reg_date(rs.getString("m_reg_date"));
                dto.setM_mod_date(rs.getString("m_mod_date"));

                userMemberDtos.add(dto);

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
        return userMemberDtos.size() > 0
                ? userMemberDtos.get(0)
                : null;

    }

    public int updateMemberInfo(UserMemberDto userMemberDto) {
        System.out.println(CLASS_NAME.concat("updateMemberInfo"));

        Connection conn = null;
        PreparedStatement pstmt = null;
        int result = -1;

        try {
            Class.forName(Config.DB_DRIVER_NAME);
            conn = DriverManager.getConnection(Config.DB_URL,Config.DB_USER_ID,Config.DB_USER_PW);
            String sql = "UPDATE TBL_USER_MEMBER SET M_PW = ?, M_MAIL = ?, M_PHONE = ?, M_MOD_DATE = NOW() WHERE M_ID = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userMemberDto.getM_pw());
            pstmt.setString(2, userMemberDto.getM_mail());
            pstmt.setString(3, userMemberDto.getM_phone());
            pstmt.setString(4, userMemberDto.getM_id());
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
                ? UserMemberService.MODIFY_SUCCESS
                : UserMemberService.MODIFY_FAIL;
    }

    public int unActiveUserMemberByMId(String loginedUserId) {
        System.out.println(CLASS_NAME.concat("removeUserMemberByMId"));

        Connection conn = null;
        PreparedStatement pstmt = null;
        int result = -1;

        try {
            Class.forName(Config.DB_DRIVER_NAME);
            conn = DriverManager.getConnection(Config.DB_URL,Config.DB_USER_ID,Config.DB_USER_PW);
            String sql = "UPDATE TBL_USER_MEMBER SET M_IS_ACTIVE = FALSE WHERE M_ID = ? AND M_IS_ACTIVE = TRUE";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, loginedUserId);
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
                ? UserMemberService.MEMBER_DELETE_SUCCESS
                : UserMemberService.MEMBER_DELETE_FAIL;
    }
}
