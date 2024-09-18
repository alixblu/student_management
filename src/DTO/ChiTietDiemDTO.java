package DTO;

public class ChiTietDiemDTO {
    private String HocSinhID;
    private String MonHocID;
    private String HocKyID;
    private int HeSoID;
    private String NamHocID;
    private Float Diem;

    public ChiTietDiemDTO(String hocSinhID, String monHocID, String hocKyID, int HeSoid,String namhocid,  Float diem) {
        HocSinhID = hocSinhID;
        HocKyID = hocKyID;
        MonHocID = monHocID;
        HeSoID = HeSoid;
        NamHocID = namhocid;
        Diem = diem;
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

    public String getMonHocID() {
        return MonHocID;
    }

    public void setMonHocID(String monHocID) {
        MonHocID = monHocID;
    }

    public Float getDiem() {
        return Diem;
    }

    public void setDiem(Float diem) {
        Diem = diem;
    }


    public String getNamHocID() {
        return NamHocID;
    }

    public void setNamHocID(String namHocID) {
        NamHocID = namHocID;
    }

    public int getHeSoID() {
        return HeSoID;
    }

    public void setHeSoID(int heSoID) {
        HeSoID = heSoID;
    }
    public String toString() {
        return "ChiTietDiemDTO{" +
                "HocSinhID='" + HocSinhID + '\'' +
                ", MonHocID='" + MonHocID + '\'' +
                ", HocKyID='" + HocKyID + '\'' +
                ", HeSoID=" + HeSoID +
                ", NamHocID='" + NamHocID + '\'' +
                ", Diem=" + Diem +
                '}';
    }
}
