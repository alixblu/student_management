package DTO;
public class NamHocDTO {
    private String NamHocID;
    private int NamHocBatDau;
    private int NamHocKetThuc;

    public NamHocDTO(String namHocID, int namHocBatDau, int namHocKetThuc) {
        NamHocID = namHocID;
        NamHocBatDau = namHocBatDau;
        NamHocKetThuc = namHocKetThuc;
    }

    public String getNamHocID() {
        return NamHocID;
    }

    public void setNamHocID(String namHocID) {
        NamHocID = namHocID;
    }

    public int getNamHocBatDau() {
        return NamHocBatDau;
    }

    public void setNamHocBatDau(int namHocBatDau) {
        NamHocBatDau = namHocBatDau;
    }

    public int getNamHocKetThuc() {
        return NamHocKetThuc;
    }

    public void setNamHocKetThuc(int namHocKetThuc) {
        NamHocKetThuc = namHocKetThuc;
    }
    public String toString() {
        return "NamHocDTO{" +
                "NamHocID='" + NamHocID + '\'' +
                ", NamHocBatDau=" + NamHocBatDau +
                ", NamHocKetThuc=" + NamHocKetThuc +
                '}';
    }
}
