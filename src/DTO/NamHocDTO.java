package DTO;
public class NamHocDTO {
    private String NamHocID;
    private int NamHocBatDau;
    private int NamHocKetThuc;
    private String HocKy;
    private int enable;

    public NamHocDTO(String namHocID, int namHocBatDau, int namHocKetThuc, String hocKy, int enable) {
        NamHocID = namHocID;
        NamHocBatDau = namHocBatDau;
        NamHocKetThuc = namHocKetThuc;
        HocKy = hocKy;
        this.enable = enable;
    }

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }

    public NamHocDTO(String namHocID, int namHocBatDau, int namHocKetThuc, String hocKy) {
        NamHocID = namHocID;
        NamHocBatDau = namHocBatDau;
        NamHocKetThuc = namHocKetThuc;
        HocKy = hocKy;
    }

    public NamHocDTO(String namHocID, int namHocBatDau, int namHocKetThuc) {
        NamHocID = namHocID;
        NamHocBatDau = namHocBatDau;
        NamHocKetThuc = namHocKetThuc;
    }

    public String getNamHocID() {
        return NamHocID;
    }

    public void setNamHocID(String namHocID) {
        NamHocID = namHocID;
    }

    public int getNamHocBatDau() {
        return NamHocBatDau;
    }

    public void setNamHocBatDau(int namHocBatDau) {
        NamHocBatDau = namHocBatDau;
    }

    public int getNamHocKetThuc() {
        return NamHocKetThuc;
    }

    public void setNamHocKetThuc(int namHocKetThuc) {
        NamHocKetThuc = namHocKetThuc;
    }
    public String toString() {
        return "NamHocDTO{" +
                "NamHocID='" + NamHocID + '\'' +
                ", NamHocBatDau=" + NamHocBatDau +
                ", NamHocKetThuc=" + NamHocKetThuc +
                '}';
    }

    public String getHocKy() {
        return HocKy;
    }

    public void setHocKy(String hocKy) {
        HocKy = hocKy;
    }
}
