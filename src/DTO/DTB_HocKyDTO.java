package DTO;

public class DTB_HocKyDTO {
    private String HocSinhID;
    private String HocKyID;
    private String NamHocID;
    private Float DiemTrungBinh;

    public DTB_HocKyDTO(String hocSinhID, String hocKyID,String namhocid, Float diemTrungBinh) {
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

    public Float getDiemTrungBinh() {
        return DiemTrungBinh;
    }

    public void setDiemTrungBinh(Float diemTrungBinh) {
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
