package DTO;

import java.text.DecimalFormat;

public class ChiTietDiemDTO {
    private String HocSinhID;
    private String MonHocID;
    private String HocKyID;
    private String NamHocID;
    private Double Diem1;
    private Double Diem2;
    private Double Diem3;
    private Double dtbMon;
private static final DecimalFormat df = new DecimalFormat("0.00");
    
    public ChiTietDiemDTO(String hocSinhID, String monHocID, String hocKyID, String namHocID) {
        this.HocSinhID = hocSinhID;
        this.MonHocID = monHocID;
        this.HocKyID = hocKyID;
        this.NamHocID = namHocID;
        this.Diem1 = 0.0;
        this.Diem2 = 0.0;
        this.Diem3 = 0.0;
        this.dtbMon = 0.0;
    }


    public String getHocSinhID() {
        return HocSinhID;
    }


    public void setHocSinhID(String hocSinhID) {
        HocSinhID = hocSinhID;
    }


    public String getMonHocID() {
        return MonHocID;
    }


    public void setMonHocID(String monHocID) {
        MonHocID = monHocID;
    }


    public String getHocKyID() {
        return HocKyID;
    }


    public void setHocKyID(String hocKyID) {
        HocKyID = hocKyID;
    }


    public String getNamHocID() {
        return NamHocID;
    }


    public void setNamHocID(String namHocID) {
        NamHocID = namHocID;
    }


    public Double getDiem1() {
        return Diem1;
    }


    public void setDiem1(Double diem1) {
        Diem1 = diem1;
    }


    public Double getDiem2() {
        return Diem2;
    }


    public void setDiem2(Double diem2) {
        Diem2 = diem2;
    }


    public Double getDiem3() {
        return Diem3;
    }


    public void setDiem3(Double diem3) {
        Diem3 = diem3;
    }


    public Double getDtbMon() {
        return dtbMon;
    }
    
    
    public void setDtbMon(Double dtbMon) {
        this.dtbMon = dtbMon;
    }

    public void calDtbMon() {
        this.dtbMon = Double.parseDouble(df.format((this.Diem1*1 + this.Diem2*2 + this.Diem3*3) / 6));
    }

}
