package BUS;
import DTO.user;
import java.util.ArrayList;

import DAO.User_DAO;

public class User_BUS {
    private ArrayList<user> dsu;
    private User_DAO uDATA;
    public User_BUS(int i1) {
        list();
    }

    public User_BUS() {
        list();
    }

    public user get(String username) {
        for (user u : dsu) {
            if (u.getusername().equals(username)) {
                return u;
            }
        }
        return null;
    }
    
    public void list() {
        User_DAO uDATA = new User_DAO();
        dsu = new ArrayList<>();
        dsu = uDATA.list();
    }

    public void add(user u) {
        User_DAO uDATA = new User_DAO();
        uDATA.add(u);
    }

    public void delete(String username) {
        for (user u : dsu) {
            if (u.getusername().equals(username)) {
                dsu.remove(u);
                User_DAO uDATA = new User_DAO();
                uDATA.delete(username);
                return;
            }
        }
    }

    public void set(user s) {
        for (int i = 0; i < dsu.size(); i++) {
            if (dsu.get(i).getusername().equals(s.getusername())) {
                dsu.set(i, s);
                User_DAO uDATA = new User_DAO();
                uDATA.update(s);
                return;
            }
        }
    }

    public void updateuser(user s) {
        for (int i = 0; i < dsu.size(); i++) {
            if (dsu.get(i).getusername().equals(s.getusername())) {
                dsu.set(i, s);
                User_DAO uDATA = new User_DAO();
                uDATA.update(s);
                return;
            }
        }
    }

    public boolean check(String username) {
        // Cập nhật danh sách người dùng từ cơ sở dữ liệu
        list();
        
        for (user u : dsu) {
            if (u.getusername().equals(username)) {
                return true;
            }
        }
        return false;
    }
    

    public ArrayList<user> search(String username, String password, String role, String enable) {
        ArrayList<user> search = new ArrayList<>();
        username = (username == null) ? "" : username;
        password = (password == null) ? "" : password;
        role = (role == null) ? "" : role;
        enable = (enable == null) ? "" : enable;

        for (user u : dsu) {
            if (u.getusername().contains(username) &&
                u.getpassword().contains(password) &&
                u.getrole().contains(role) &&
                u.getenable().contains(enable)) {
                search.add(u);
            }
        }
        return search;
    }

    public user searchByusername(String username) {
        for (user u : dsu) {
            if (u.getusername().equals(username)) {
                return u;
            }
        }
        return null;
    }

    public ArrayList<user> getList() {
        return dsu;
    }
    public static void main(String[] args) {
        // Create an instance of User_BUS
        User_BUS userBUS = new User_BUS(1);

        // Call the search method with the specified parameters
        String usernameToSearch = "HS1";
        ArrayList<user> searchResult = userBUS.search(usernameToSearch, null, null, null);

        // Print the search result
        System.out.println("Search result for username: " + usernameToSearch);
        for (user hs : searchResult) {
            System.out.println("Username: " + hs.getusername());
            System.out.println("Password: " + hs.getpassword());
            System.out.println("Role: " + hs.getrole());
            System.out.println("Enable: " + hs.getenable());
            System.out.println(); // for readability
        }
    }
}
