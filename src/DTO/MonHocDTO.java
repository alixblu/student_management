package DTO;
public class MonHocDTO {
    private String MonHocID;
    private String TenMonHoc;

    public MonHocDTO(String monHocID, String tenMonHoc) {
        MonHocID = monHocID;
        TenMonHoc = tenMonHoc;
    }

    public String getMonHocID() {
        return MonHocID;
    }

    public void setMonHocID(String monHocID) {
        MonHocID = monHocID;
    }

    public String getTenMonHoc() {
        return TenMonHoc;
    }

    public void setTenMonHoc(String tenMonHoc) {
        TenMonHoc = tenMonHoc;
    }
    public String toString() {
        return "MonHocDTO{" +
                "MonHocID='" + MonHocID + '\'' +
               ", TenMonHoc='" + TenMonHoc + '\'' +
                '}';
    }
}
