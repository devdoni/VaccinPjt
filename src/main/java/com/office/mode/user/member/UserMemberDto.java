package com.office.mode.user.member;

import com.office.mode.user.vaccin.dto.ScNumberDto;

public class UserMemberDto {
    private String m_id;
    private String m_pw;
    private String m_mail;
    private String m_phone;
    private ScNumberDto scNumberDto;
    private String m_reg_date ;
    private String m_mod_date;

    public UserMemberDto(String m_id, String m_pw, String m_mail, String m_phone) {
        this.m_id = m_id;
        this.m_pw = m_pw;
        this.m_mail = m_mail;
        this.m_phone = m_phone;
    }

    public UserMemberDto(String m_id, String m_pw, String m_mail, String m_phone, ScNumberDto scNumberDto) {
        this.m_id = m_id;
        this.m_pw = m_pw;
        this.m_mail = m_mail;
        this.m_phone = m_phone;
        this.scNumberDto = scNumberDto;
    }

    public UserMemberDto() { }

    public String getM_id() {
        return m_id;
    }

    public void setM_id(String m_id) {
        this.m_id = m_id;
    }

    public String getM_pw() {
        return m_pw;
    }

    public void setM_pw(String m_pw) {
        this.m_pw = m_pw;
    }

    public String getM_mail() {
        return m_mail;
    }

    public void setM_mail(String m_mail) {
        this.m_mail = m_mail;
    }

    public String getM_phone() {
        return m_phone;
    }

    public void setM_phone(String m_phone) {
        this.m_phone = m_phone;
    }

    public ScNumberDto getScNumberDto() {
        return scNumberDto;
    }

    public void setScNumberDto(ScNumberDto scNumberDto) {
        this.scNumberDto = scNumberDto;
    }

    public String getM_reg_date() {
        return m_reg_date;
    }

    public void setM_reg_date(String m_reg_date) {
        this.m_reg_date = m_reg_date;
    }

    public String getM_mod_date() {
        return m_mod_date;
    }

    public void setM_mod_date(String m_mod_date) {
        this.m_mod_date = m_mod_date;
    }
}
