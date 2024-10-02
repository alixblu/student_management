package BUS;

import java.util.ArrayList;

import DAO.QLHS_GV_DAO;
import DTO.HocSinhDTO;

public class QLHS_GV_BUS {
    private ArrayList<HocSinhDTO> dshs_gv;

    public void listHS_GV() {
        QLHS_GV_DAO hs_gvDAO = new QLHS_GV_DAO();

        dshs_gv = new ArrayList<>();
        dshs_gv = hs_gvDAO.list();
    }

    public ArrayList<HocSinhDTO> getList() {
        return dshs_gv;
    }
}
