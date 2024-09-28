package DTO;

public class PhanCongDTO {
    private String giaoVienID;
    private String lopID;
    // private String monHocID;
    // Add other attributes as needed

    // Constructor
    public PhanCongDTO(String giaoVienID, String lopID) {
        this.giaoVienID = giaoVienID;
        this.lopID = lopID;
        // this.monHocID = monHocID;
       // Initialize other attributes
    }

    // Getters and Setters

    public String getGiaoVienID() {
        return giaoVienID;
    }

    public void setGiaoVienID(String giaoVienID) {
        this.giaoVienID = giaoVienID;
    }

    public String getLopID() {
        return lopID;
    }

    public void setLopID(String lopID) {
        this.lopID = lopID;
    }

    // public String getMonHocID() {
    //     return monHocID;
    // }

    // public void setMonHocID(String monHocID) {
    //     this.monHocID = monHocID;
    // }

    // Override toString method to display all attributes
    @Override
    public String toString() {
        return "PhanCongDTO{" +
                "giaoVienID='" + giaoVienID + '\'' +
                ", lopID='" + lopID + '\'' +
                // ", monHocID='" + monHocID + '\'' +
                // Include other attributes here
                '}';
    }
}
