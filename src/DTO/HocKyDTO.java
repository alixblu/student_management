package DTO;
public class HocKyDTO {
    private String HocKyID;
    private String TenHocKy;

    public HocKyDTO(String hocKyID, String tenHocKy) {
        HocKyID = hocKyID;
        TenHocKy = tenHocKy;
    }

    public String getHocKyID() {
        return HocKyID;
    }

    public void setHocKyID(String hocKyID) {
        HocKyID = hocKyID;
    }

    public String getTenHocKy() {
        return TenHocKy;
    }

    public void setTenHocKy(String tenHocKy) {
        TenHocKy = tenHocKy;
    }
    
}
