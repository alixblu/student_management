package DTO;

public class HocSinhDTO {

    private String HocSinhID;
    private String TenHocSinh;
    private String GioiTinh;
    private String NgaySinh;
    private String DienThoai;
    private String DiaChi;
    private String HocPhi;
    private String IMG;

    public HocSinhDTO(){};
    
    public HocSinhDTO(String hocSinhID, String tenHocSinh, String gioiTinh, String ngaySinh, String dienThoai,
            String diaChi) {
        HocSinhID = hocSinhID;
        TenHocSinh = tenHocSinh;
        GioiTinh = gioiTinh;
        NgaySinh = ngaySinh;
        DiaChi = diaChi;
        DienThoai = dienThoai;
        HocPhi = "Chưa thanh toán";
        IMG = "";
    }

    public String getIMG() {
        return IMG;
    }

    public void setIMG(String IMG) {
        this.IMG = IMG;
    }

    public String getHocSinhID() {
        return HocSinhID;
    }

    public void setHocSinhID(String hocSinhID) {
        HocSinhID = hocSinhID;
    }

    public String getTenHocSinh() {
        return TenHocSinh;
    }

    public void setTenHocSinh(String tenHocSinh) {
        TenHocSinh = tenHocSinh;
    }

    public String getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        GioiTinh = gioiTinh;
    }

    public String getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        NgaySinh = ngaySinh;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public String getDienThoai() {
        return DienThoai;
    }

    public void setDienThoai(String dienThoai) {
        DienThoai = dienThoai;
    }

    public String getHocPhi() {
        return HocPhi;
    }

    public void setHocPhi(String hocPhi) {
        HocPhi = hocPhi;
    }
    @Override
    public String toString() {
        return "HocSinhDTO{" +
                "HocSinhID='" + HocSinhID + '\'' +
                ", TenHocSinh='" + TenHocSinh + '\'' +
                ", GioiTinh='" + GioiTinh + '\'' +
                ", NgaySinh='" + NgaySinh + '\'' +
                ", DienThoai='" + DienThoai + '\'' +
                ", DiaChi='" + DiaChi + '\'' +
                ", HocPhi='" + HocPhi + '\'' +
                ", IMG='" + IMG + '\'' +
                '}';
    }
}
