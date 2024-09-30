package BUS;

import DTO.GiaoVienDTO;

import java.io.File;
import java.util.ArrayList;

import DAO.GiaoVienDAO;

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
    public void setSubmit(String magv, int isSubmit){
        GiaoVienDAO dao = new GiaoVienDAO();
        dao.setSubmit(magv, isSubmit);
    }
    public int getSubmit(String magv){
        GiaoVienDAO dao = new GiaoVienDAO();
        return dao.getSubmit(magv);
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

    public String getIdMon(String magv){
        GiaoVienDAO gVienDAO = new GiaoVienDAO();
        return gVienDAO.getMonHocId(magv);
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
        GiaoVienDAO gvDAO = new GiaoVienDAO(); // Khởi tạo DAO một lần
    
        boolean isUpdated = false; // Cờ để kiểm tra xem có bản ghi nào được cập nhật hay không
        for (int i = 0; i < dsgv.size(); i++) {
            if (dsgv.get(i).getMaGV().equals(s.getMaGV())) {
                dsgv.set(i, s); // Cập nhật đối tượng trong danh sách
                gvDAO.Update(s); // Cập nhật cơ sở dữ liệu
                isUpdated = true; // Đánh dấu là đã cập nhật
                break; // Thoát vòng lặp sau khi tìm thấy và cập nhật
            }
        }
        
        // Nếu không tìm thấy giáo viên trong danh sách, in ra thông báo
        if (!isUpdated) {
            System.out.println("Không tìm thấy giáo viên có mã: " + s.getMaGV());
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
            System.out.println("PhanMon: " + gv.getphanMon());
            System.out.println("IMG: " + gv.getIMG());
            System.out.println("-------------------------------");
        }
    }
}
