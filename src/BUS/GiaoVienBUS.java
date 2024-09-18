package BUS;

import DATA.GiaoVienDAO;
import DTO.GiaoVienDTO;

import java.io.File;
import java.util.ArrayList;

public class GiaoVienBUS {
    private ArrayList<GiaoVienDTO> dsgv;

    public GiaoVienBUS() {
        listGV();
    }

    public void listGV() {
        GiaoVienDAO gvDAO = new GiaoVienDAO();
        dsgv = gvDAO.list();
    }

    public void addGV(GiaoVienDTO gv) {
        dsgv.add(gv);
        GiaoVienDAO gvDAO = new GiaoVienDAO();
        gvDAO.add(gv);
    }

    public void deleteGV(String idGV) {
        for (GiaoVienDTO gv : dsgv) {
            if (gv.getMaGV().equals(idGV)) {
                dsgv.remove(gv);
                GiaoVienDAO gvDAO = new GiaoVienDAO();
                gvDAO.delete(idGV);
                return;
            }
        }
    }

    public boolean checkMagv(String magv) {
        GiaoVienDAO gvDao = new GiaoVienDAO();
        dsgv = gvDao.checkMagv();
        for (GiaoVienDTO gv : dsgv) {
            if (gv.getMaGV().equals(magv)) {
                return true;
            }
        }
        return false;
    }

    public GiaoVienDTO getGV(String magv) {
        for (GiaoVienDTO gv : dsgv) {
            if (gv.getMaGV().equals(magv)) {
                return gv;
            }
        }
        return null;
    }

    public ArrayList<GiaoVienDTO> searchGV(String magv, String tengv) {
        ArrayList<GiaoVienDTO> search = new ArrayList<>();
        magv = magv.isEmpty() ? "" : magv;
        tengv = tengv.isEmpty() ? "" : tengv;
        for (GiaoVienDTO gv : dsgv) {
            if (gv.getMaGV().contains(magv) && gv.getTenGV().contains(tengv)) {
                search.add(gv);
            }
        }
        return search;
    }

    public ArrayList<GiaoVienDTO> getList() {
        return dsgv;
    }

    public void ImportExcelDatabase(File file) {
        GiaoVienDAO gvDAO = new GiaoVienDAO();
        gvDAO.ImportExcelDatabase(file);
    }

    public Integer CountGV() {
        GiaoVienDAO gv = new GiaoVienDAO();
        return gv.CountGV();
    }

    public void updateGV(GiaoVienDTO s) {
        for (int i = 0; i < dsgv.size(); i++) {
            if (dsgv.get(i).getMaGV().equals(s.getMaGV())) {
                dsgv.set(i, s);
                GiaoVienDAO gvDAO = new GiaoVienDAO();
                gvDAO.Update(s);
                return;
            }
        }
    }

    public static void main(String[] args) {
        GiaoVienBUS giaoVienBUS = new GiaoVienBUS();

        // Display all teachers
        ArrayList<GiaoVienDTO> dsgv = giaoVienBUS.getList();
        for (GiaoVienDTO gv : dsgv) {
            System.out.println("MaGV: " + gv.getMaGV());
            System.out.println("TenGV: " + gv.getTenGV());
            System.out.println("GioiTinh: " + gv.getGioiTinh());
            System.out.println("NamSinh: " + gv.getNamSinh());
            System.out.println("DienThoai: " + gv.getDienThoai());
            System.out.println("IMG: " + gv.getIMG());
            System.out.println("-------------------------------");
        }
    }
}
