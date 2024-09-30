package BUS;

import java.util.ArrayList;

import DAO.phanquyenDAO;
import DTO.chitietquyenDTO;
import DTO.chucnangDTO;
import DTO.phanquyenDTO;

public class phanquyenBUS {

    ArrayList<phanquyenDTO> dsquyen;
    ArrayList<chucnangDTO> dschucnang;
    ArrayList<chitietquyenDTO> dschitietquyen;

    public void listquyen() {
        phanquyenDAO dao = new phanquyenDAO();
        dsquyen = new ArrayList<>();
        dsquyen = dao.listQuyen();
    }

    public void listChiTietQuyen(String maquyen) {
        phanquyenDAO dao = new phanquyenDAO();
        dschitietquyen = new ArrayList<>();
        dschitietquyen = dao.listChiTietQuyen(maquyen);
    }

    public void listchucnang() {
        phanquyenDAO dao = new phanquyenDAO();
        dschucnang = new ArrayList<>();
        dschucnang = dao.listChucNang();
    }

    public ArrayList<phanquyenDTO> getlistquyen() {
        return dsquyen;
    }

    public ArrayList<chucnangDTO> getlistchucnang() {
        return dschucnang;
    }

    public ArrayList<chitietquyenDTO> getListchitietquyen() {
        return dschitietquyen;
    }

    public void addQuyen(phanquyenDTO newQuyen) {
        phanquyenDAO dao = new phanquyenDAO();
        dao.addQuyen(newQuyen);
    }

    public boolean checkExist(String maquyen) {
        phanquyenDAO dao = new phanquyenDAO();
        return dao.checkExist(maquyen);
    }

    public void addChitietquyen(chitietquyenDTO ctq) {
        phanquyenDAO dao = new phanquyenDAO();
        dao.addChitietquyen(ctq);

    }

    // public ArrayList<chitietquyenDTO> getListchitietquyen() {
    // return dschitietquyen;
    // }

    public void deleteQuyen(String maquyen) {
        phanquyenDAO dao = new phanquyenDAO();
        dao.deleteQuyen(maquyen);
    }

    public void deleteChitietquyen(String maquyen) {
        phanquyenDAO dao = new phanquyenDAO();
        dao.deteleChitietQuyen(maquyen);
    }

    public static void main(String[] args) {
        phanquyenBUS phanquyenBUS = new phanquyenBUS();

        // Ensure the lists are initialized
        phanquyenBUS.listquyen();
        phanquyenBUS.listchucnang();

        // Print out all phanquyenDTO objects
        ArrayList<phanquyenDTO> allQuyen = phanquyenBUS.getlistquyen();
        for (phanquyenDTO quyen : allQuyen) {
            System.out.println("MaQuyen: " + quyen.getMaquyen());
            System.out.println("TenQuyen: " + quyen.getTenquyen());
            System.out.println("-------------------------");
        }

        // // Print out all chucnangDTO objects
        // ArrayList<chucnangDTO> allChucNang = phanquyenBUS.getlistchucnang();
        // for (chucnangDTO chucNang : allChucNang) {
        // System.out.println("MaChucNang: " + chucNang.getMaChucNang());
        // System.out.println("TenChucNang: " + chucNang.getTenChucNang());
        // System.out.println("-------------------------");
        // }
    }

}
