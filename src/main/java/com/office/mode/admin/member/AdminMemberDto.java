package com.office.mode.admin.member;

public class AdminMemberDto {
    private String am_id;
    private String am_pw;
    private String am_name;
    private String am_auth_code;
    private String am_reg_date;
    private String am_mod_date;

    public AdminMemberDto () { }

    public AdminMemberDto(String am_id, String am_pw) {
        this.am_id = am_id;
        this.am_pw = am_pw;
    }

    public AdminMemberDto(String am_id, String am_pw, String am_name, String am_auth_code) {
        this.am_id = am_id;
        this.am_pw = am_pw;
        this.am_name = am_name;
        this.am_auth_code = am_auth_code;
    }

    public String getAm_name() {
        return am_name;
    }

    public void setAm_name(String am_name) {
        this.am_name = am_name;
    }

    public String getAm_auth_code() {
        return am_auth_code;
    }

    public void setAm_auth_code(String am_auth_code) {
        this.am_auth_code = am_auth_code;
    }

    public String getAm_id() {
        return am_id;
    }

    public void setAm_id(String am_id) {
        this.am_id = am_id;
    }

    public String getAm_pw() {
        return am_pw;
    }

    public void setAm_pw(String am_pw) {
        this.am_pw = am_pw;
    }

    public String getAm_reg_date() {
        return am_reg_date;
    }

    public void setAm_reg_date(String am_reg_date) {
        this.am_reg_date = am_reg_date;
    }

    public String getAm_mod_date() {
        return am_mod_date;
    }

    public void setAm_mod_date(String am_mod_date) {
        this.am_mod_date = am_mod_date;
    }
}
