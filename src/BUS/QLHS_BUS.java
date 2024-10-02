package BUS;

import java.util.ArrayList;

import DAO.*;
import DTO.HocSinhDTO;

public class QLHS_BUS {
    private ArrayList<HocSinhDTO> dshs;

    public QLHS_BUS() {
    }

    public void listHS() {
        QLHS_DAO hsDAO = new QLHS_DAO();

        dshs = new ArrayList<>();
        dshs = hsDAO.list();
    }

    // theem
    public void addHS(HocSinhDTO hs) {
        dshs.add(hs);
        QLHS_DAO hsDAO = new QLHS_DAO();
        hsDAO.Add(hs);
    }

    // xoa
    public void deleteHS(String mahs) {
        for (HocSinhDTO hs : dshs) {
            if (hs.getHocSinhID().equals(mahs)) {
                // dshs.remove(mahs);
                QLHS_DAO hsDAO = new QLHS_DAO();
                hsDAO.delete(mahs);
                return;
            }
        }
    }

    // get/set
    public void updateHS(HocSinhDTO s) {
        for (int i = 0; i < dshs.size(); i++) {
            if (dshs.get(i).getHocSinhID().equals(s.getHocSinhID())) {
                dshs.set(i, s);
                QLHS_DAO hsDAO = new QLHS_DAO();
                hsDAO.Update(s);
                return;
            }
        }
    }

    public HocSinhDTO getHS(String mahs) {
        for (HocSinhDTO hs : dshs) {
            if (hs.getHocSinhID().equals(mahs)) {
                return hs;
            }
        }
        return null;
    }

    // kiem tra ma
    public boolean checkMaHS(String mahs) {
        QLHS_DAO hsDao = new QLHS_DAO();
        dshs = new ArrayList<>();
        dshs = hsDao.checkMaHS();
        for (HocSinhDTO hs : dshs) {
            System.out.println(hs.getHocSinhID());
            if (hs.getHocSinhID().equals(mahs)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<HocSinhDTO> getList() {
        return dshs;
    }

    public Integer CountHS() {
        QLHS_DAO hs = new QLHS_DAO();
        Integer count = hs.CountHS();
        return count;
    }

    public static String getRole(String username) {
        QLHS_DAO dao = new QLHS_DAO();

        return dao.getRole(username);
    }

    public boolean checkSDT(String sdt){
        QLHS_DAO hs = new QLHS_DAO();
        return hs.checkSDT(sdt);
    }
}
