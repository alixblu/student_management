package DTO;

public class QLPhanCongDTO {
    private String magv;
    private String tengv;
    private String lop;
    // private String mon;

    public QLPhanCongDTO(String magv, String tengv, String lop) {
        this.magv = magv;
        this.tengv = tengv;
        this.lop = lop;
        // this.mon = mon;
    }

    public String getMagv() {
        return magv;
    }

    public void setMagv(String magv) {
        this.magv = magv;
    }

    public String getTengv() {
        return tengv;
    }

    public void setTengv(String tengv) {
        this.tengv = tengv;
    }

    public String getLop() {
        return lop;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }

    // public String getMon() {
    //     return mon;
    // }

    // public void setMon(String mon) {
    //     this.mon = mon;
    // }

}
