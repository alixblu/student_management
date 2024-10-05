package DTO;

public class DTB_HocKyDTO {
    private String HocSinhID;
    private String HocKyID;
    private String NamHocID;
    private double DiemTrungBinh;

    public DTB_HocKyDTO(String hocSinhID, String hocKyID,String namhocid, double diemTrungBinh) {
        HocSinhID = hocSinhID;
        HocKyID = hocKyID;
        NamHocID = namhocid;
        DiemTrungBinh = diemTrungBinh;
    }

    public String getHocSinhID() {
        return HocSinhID;
    }

    public void setHocSinhID(String hocSinhID) {
        HocSinhID = hocSinhID;
    }

    public String getHocKyID() {
        return HocKyID;
    }

    public void setHocKyID(String hocKyID) {
        HocKyID = hocKyID;
    }

    public double getDiemTrungBinh() {
        return DiemTrungBinh;
    }

    public void setDiemTrungBinh(double diemTrungBinh) {
        DiemTrungBinh = diemTrungBinh;
    }

    public String getNamHocID() {
        return NamHocID;
    }

    public void setNamHocID(String namHocID) {
        NamHocID = namHocID;
    }

    public String toString() {
        return "DTB_HocKyDTO{" +
                "HocSinhID='" + HocSinhID + '\'' +
                ", HocKyID='" + HocKyID + '\'' +
                ", NamHocID='" + NamHocID + '\'' +
                ", DiemTrungBinh=" + DiemTrungBinh +
                '}';
    }
}
