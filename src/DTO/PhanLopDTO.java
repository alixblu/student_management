package DTO;
public class PhanLopDTO {
    private String HocSinhID;
    private String LopID;
    private String NamHocID;

    public PhanLopDTO(String hocSinhID, String lopID, String idnam) {
        HocSinhID = hocSinhID;
        LopID = lopID;
        NamHocID = idnam;
    }

    public String getHocSinhID() {
        return HocSinhID;
    }

    public void setHocSinhID(String hocSinhID) {
        HocSinhID = hocSinhID;
    }

    public String getLopID() {
        return LopID;
    }

    public void setLopID(String lopID) {
        LopID = lopID;
    }

    public String getNamHocID() {
        return NamHocID;
    }

    public void setNamHocID(String namHocID) {
        NamHocID = namHocID;
    }

}
