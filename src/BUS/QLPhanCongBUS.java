package BUS;

import DTO.QLPhanCongDTO;
import java.util.*;

import DAO.QLPhanCongDAO;

public class QLPhanCongBUS {

    private ArrayList<QLPhanCongDTO> dspc;

    private ArrayList<String> dsmagv;
    // private ArrayList<String> dstenmh;
    private ArrayList<String> dstenlop;

    public void listPC() {
        QLPhanCongDAO pc = new QLPhanCongDAO();
        dspc = new ArrayList<>();
        dspc = pc.list();

    }

    public void listMagv() {
        QLPhanCongDAO pc = new QLPhanCongDAO();
        dsmagv = new ArrayList<>();
        dsmagv = pc.getMaGV();

    }

    // public void listTenmh() {
    //     QLPhanCongDAO pc = new QLPhanCongDAO();
    //     dstenmh = new ArrayList<>();
    //     dstenmh = pc.getTenMonHoc();

    // }

    public void listTenlop() {
        QLPhanCongDAO pc = new QLPhanCongDAO();
        dstenlop = new ArrayList<>();
        dstenlop = pc.getTenLop();

    }

    public void update(QLPhanCongDTO upc) {
        QLPhanCongDAO pc = new QLPhanCongDAO();
        pc.Update(upc);
    }

    public void delete(QLPhanCongDTO dpc) {
        QLPhanCongDAO pc = new QLPhanCongDAO();
        pc.delete(dpc);
    }

    public void add(QLPhanCongDTO apc) {
        QLPhanCongDAO pc = new QLPhanCongDAO();
        pc.Add(apc);
    }

    public String getName(String magv) {
        QLPhanCongDAO pc = new QLPhanCongDAO();
        String tengv = pc.returnName(magv);
        return tengv;
    }

    public ArrayList<QLPhanCongDTO> getList() {
        return dspc;
    }

    public ArrayList<String> getMaGVList() {
        return dsmagv;
    }

    // public ArrayList<String> getTenMHList() {
    //     return dstenmh;
    // }

    public ArrayList<String> getTenLopList() {
        return dstenlop;
    }

    public String getIMG(String magv) {
        String img = null;
        QLPhanCongDAO dao = new QLPhanCongDAO();
        img = dao.getIMG(magv);
        return img;
    }

    public boolean checkExist(QLPhanCongDTO cpc) {
        System.out.println("đã vào");
        QLPhanCongDAO pc = new QLPhanCongDAO();
        if (pc.checkExist(cpc)) {
            return true;
        }
        return false;
    }

}
