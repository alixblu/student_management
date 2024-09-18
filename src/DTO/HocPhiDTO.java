package DTO;

public class HocPhiDTO {
    private String idhs;
    private String idnh;
    private String thoigian;
    private int status;

    public HocPhiDTO(String idhs, String idnh, String thoigian, int status) {
        this.idhs = idhs;
        this.idnh = idnh;
        this.thoigian = thoigian;
        this.status = status;
    }

    // Getters and setters

    public HocPhiDTO() {
    }

    @Override
    public String toString() {
        return "HocPhiDTO{" +
                "idhs=" + idhs +
                ", idnh=" + idnh +
                ", thoigian='" + thoigian + '\'' +
                ", status='" + status + '\'' +
                '}';
    }




    public String getThoigian() {
        return thoigian;
    }

    public String getIdnh() {
        return idnh;
    }

    public void setIdnh(String idnh) {
        this.idnh = idnh;
    }

    public void setThoigian(String thoigian) {
        this.thoigian = thoigian;
    }


    public String getIdhs() {
        return idhs;
    }

    public void setIdhs(String idhs) {
        this.idhs = idhs;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
