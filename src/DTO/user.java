package DTO;

public class user {
   private String username;
   private String password;
   private String role;
   private String enable;
   public user (String username, String password, String role, String enable) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.enable = enable;
    }

    public String getusername() {
        return username;
    }
    public String getpassword() {
        return password;
    }
    public String getrole() {
        return role;
    }
    public String getenable(){
        return enable;
    }

    public void setEnabled(String enable){
        this.enable = enable;
    }
    public void setusername(String username) {
        this.username = username;
    }
    public void setpassword(String password) {
        this.password = password;
    }
    public void setrole(String role) {
        this.role = role;
    }
   
}
