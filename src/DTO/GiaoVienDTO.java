package DTO;

public class GiaoVienDTO {
   private String maGV, TenGV,GioiTinh,IMG;
   private String NamSinh,DienThoai,Diachi, PhanMon;
   
    public GiaoVienDTO(String maGV, String tenGV, String gioiTinh, String iMG, String namSinh, String dienThoai, String phanMon,
        String diachi) {
    this.maGV = maGV;
    TenGV = tenGV;
    GioiTinh = gioiTinh;
    IMG = iMG;
    NamSinh = namSinh;
    DienThoai = dienThoai;
    PhanMon = phanMon;
    Diachi = diachi;
}
    public String getDiachi() {
        return Diachi;
    }
    public void setDiachi(String diachi) {
        Diachi = diachi;
    }
    public String getMaGV() {
        return maGV;
    }
    public void setphanMon(String phanMon) {
        Diachi = phanMon;
    }
    public String getphanMon() {
        return PhanMon;
    }
    public void setMaGV(String maGV) {
        this.maGV = maGV;
    }

    public String getTenGV() {
        return TenGV;
    }

    public void setTenGV(String tenGV) {
        TenGV = tenGV;
    }

    public String getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        GioiTinh = gioiTinh;
    }

    public String getIMG() {
        return IMG;
    }

    public void setIMG(String iMG) {
        IMG = iMG;
    }
    public String getNamSinh() {
        return NamSinh;
    }
    public void setNamSinh(String namSinh) {
        NamSinh = namSinh;
    }
    public String getDienThoai() {
        return DienThoai;
    }
    public void setDienThoai(String dienThoai) {
        DienThoai = dienThoai;
    }

    // public void setLocationRelativeTo(Object object) {
    // // TODO Auto-generated method stub
    // throw new UnsupportedOperationException("Unimplemented method
    // 'setLocationRelativeTo'");
    // }

}
