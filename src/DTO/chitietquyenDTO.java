package DTO;

public class chitietquyenDTO {
    private String maquyen;
    private String machucnang;

    public chitietquyenDTO(String maquyen, String machucnang) {
        this.maquyen = maquyen;
        this.machucnang = machucnang;
    }

    public String getMaquyen() {
        return maquyen;
    }

    public void setMaquyen(String maquyen) {
        this.maquyen = maquyen;
    }

    public String getMachucnang() {
        return machucnang;
    }

    public void setMachucnang(String machucnang) {
        this.machucnang = machucnang;
    }

}
