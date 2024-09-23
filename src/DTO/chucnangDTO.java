package DTO;

public class chucnangDTO {
    private String machucnang;
    private String tenchucnang;
    private String img;

    public chucnangDTO(String machucnang, String tenchucnang, String img) {
        this.machucnang = machucnang;
        this.tenchucnang = tenchucnang;
        this.img = img;
    }
    public String getMachucnang() {
        return machucnang;
    }
    public void setMachucnang(String machucnang) {
        this.machucnang = machucnang;
    }
    public String getTenchucnang() {
        return tenchucnang;
    }
    public void setTenchucnang(String tenchucnang) {
        this.tenchucnang = tenchucnang;
    }
    public String getImg() {
        return img;
    }
    public void setImg(String img) {
        this.img = img;
    }

    

}
