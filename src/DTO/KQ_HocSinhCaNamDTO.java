package DTO;
public class KQ_HocSinhCaNamDTO {

    private String HocSinhID;
    private String NamHocID;
    private String HocLuc;
    private String HanhKiem;
    private double DiemTrungBinhNam;
    private String KetQua;
    
    public KQ_HocSinhCaNamDTO(String hocSinhID, String namHocID) {
        this.HocSinhID = hocSinhID;
        this.NamHocID = namHocID;
        this.HocLuc = "";
        this.DiemTrungBinhNam = 0.0;
        this.KetQua = "";
        this.HanhKiem = "Tốt";
    }

    public String getHocSinhID() {
        return HocSinhID;
    }

    public void setHocSinhID(String hocSinhID) {
        HocSinhID = hocSinhID;
    }

    public String getNamHocID() {
        return NamHocID;
    }

    public void setNamHocID(String namHocID) {
        NamHocID = namHocID;
    }

    public String getHanhKiem() {
        return HanhKiem;
    }

    public void setHanhKiem(String hanhKiem) {
        HanhKiem = hanhKiem;
    }

    public String getHocLuc() {
        return HocLuc;
    }

    public void setHocLuc(String hocLuc) {
        HocLuc = hocLuc;
    }

    public double getDiemTrungBinhNam() {
        return DiemTrungBinhNam;
    }

    public void setDiemTrungBinhNam(double diemTrungBinhNam) {
        DiemTrungBinhNam = diemTrungBinhNam;
    }

    public String getKetQua() {
        return KetQua;
    }

    public void setKetQua(String ketQua) {
        KetQua = ketQua;
    }
    public String toString() {
        return "KQ_HocSinhCaNamDTO{" +
                "HocSinhID='" + HocSinhID + '\'' +
                ", NamHocID='" + NamHocID + '\'' +
                ", HocLuc='" + HocLuc + '\'' +
                ", HanhKiem='" + HanhKiem + '\'' +
                ", DiemTrungBinhNam=" + DiemTrungBinhNam +
                ", KetQua='" + KetQua + '\'' +
                '}';
    }
}