package com.office.mode.user.vaccin;

import com.office.mode.user.vaccin.dto.ScNumberDto;
import com.office.mode.user.vaccin.dto.VaccinLocationDto;
import com.office.mode.user.vaccin.dto.VaccinTypeDto;

public class UserVaccinDto {
    private int fvp_no;
    private String fvp_name;
    private ScNumberDto scNumberDto;
    private String fvp_mail;
    private String fvp_phone;
    private VaccinLocationDto vaccinLocationDto;
    private VaccinTypeDto vaccinTypeDto;
    private String fvp_reg_date;
    private String fvp_mod_date;

    public int getFvp_no() {
        return fvp_no;
    }

    public void setFvp_no(int fvp_no) {
        this.fvp_no = fvp_no;
    }

    public String getFvp_name() {
        return fvp_name;
    }

    public void setFvp_name(String fvp_name) {
        this.fvp_name = fvp_name;
    }

    public ScNumberDto getScNumberDto() {
        return scNumberDto;
    }

    public void setScNumberDto(ScNumberDto scNumberDto) {
        this.scNumberDto = scNumberDto;
    }

    public String getFvp_mail() {
        return fvp_mail;
    }

    public void setFvp_mail(String fvp_mail) {
        this.fvp_mail = fvp_mail;
    }

    public String getFvp_phone() {
        return fvp_phone;
    }

    public void setFvp_phone(String fvp_phone) {
        this.fvp_phone = fvp_phone;
    }

    public VaccinLocationDto getVaccinLocationDto() {
        return vaccinLocationDto;
    }

    public void setVaccinLocationDto(VaccinLocationDto vaccinLocationDto) {
        this.vaccinLocationDto = vaccinLocationDto;
    }

    public VaccinTypeDto getVaccinTypeDto() {
        return vaccinTypeDto;
    }

    public void setVaccinTypeDto(VaccinTypeDto vaccinTypeDto) {
        this.vaccinTypeDto = vaccinTypeDto;
    }

    public String getFvp_reg_date() {
        return fvp_reg_date;
    }

    public void setFvp_reg_date(String fvp_reg_date) {
        this.fvp_reg_date = fvp_reg_date;
    }

    public String getFvp_mod_date() {
        return fvp_mod_date;
    }

    public void setFvp_mod_date(String fvp_mod_date) {
        this.fvp_mod_date = fvp_mod_date;
    }
}
