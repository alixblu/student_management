package DTO;
public class LopDTO {
    private String LopID;
    private String TenLop;

    public LopDTO(String lopID, String tenLop) {
        LopID = lopID;
        TenLop = tenLop;
    }

    public String getLopID() {
        return LopID;
    }

    public void setLopID(String lopID) {
        LopID = lopID;
    }

    public String getTenLop() {
        return TenLop;
    }

    public void setTenLop(String tenLop) {
        TenLop = tenLop;
    }
    public String toString() {
        return "LopDTO{" +
                "LopID='" + LopID + '\'' +
                ", TenLop='" + TenLop + '\'' +
                '}';
    }
}
