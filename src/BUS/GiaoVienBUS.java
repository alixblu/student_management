package BUS;

import DTO.GiaoVienDTO;
import DAO.GiaoVienDAO;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class GiaoVienBUS {
    private ArrayList<GiaoVienDTO> dsgv;
    private GiaoVienDAO gvDAO;

    public GiaoVienBUS() {
        gvDAO = new GiaoVienDAO(); // Khởi tạo một lần
        listGV();
    }

    public void listGV() {
        dsgv = gvDAO.list();
    }

    public void addGV(GiaoVienDTO gv) {
        // Kiểm tra tính hợp lệ trước khi thêm
        if (checkMagv(gv.getMaGV()) || checkPhoneNumberExists(gv.getDienThoai())) {
            System.out.println("Mã giáo viên hoặc số điện thoại đã tồn tại.");
            return;
        }

        dsgv.add(gv);
        gvDAO.add(gv);
    }

    public void deleteGV(String idGV) {
        Iterator<GiaoVienDTO> iterator = dsgv.iterator();
        while (iterator.hasNext()) {
            GiaoVienDTO gv = iterator.next();
            if (gv.getMaGV().equals(idGV)) {
                iterator.remove(); // Sử dụng iterator để xóa
                gvDAO.delete(idGV);
                return;
            }
        }
    }

    public void resetSubmit() {
        gvDAO.resetSubmit();
    }

    public void setSubmit(String magv, int isSubmit) {
        gvDAO.setSubmit(magv, isSubmit);
    }

    public int getSubmit(String magv) {
        return gvDAO.getSubmit(magv);
    }

    public boolean checkMagv(String magv) {
        for (GiaoVienDTO gv : dsgv) {
            if (gv.getMaGV().equals(magv)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkPhoneNumberExists(String soDienThoai) {
        for (GiaoVienDTO giaovien : dsgv) {
            if (giaovien.getDienThoai().equals(soDienThoai)) {
                return true; // Nếu số điện thoại đã tồn tại
            }
        }
        return false; // Nếu số điện thoại chưa tồn tại
    }

    public String getIdMon(String magv) {
        return gvDAO.getMonHocId(magv);
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
        gvDAO.ImportExcelDatabase(file);
    }

    public Integer CountGV() {
        return gvDAO.CountGV();
    }

    public void updateGV(GiaoVienDTO s) {
        boolean isUpdated = false;
        for (int i = 0; i < dsgv.size(); i++) {
            if (dsgv.get(i).getMaGV().equals(s.getMaGV())) {
                dsgv.set(i, s); // Cập nhật đối tượng trong danh sách
                gvDAO.Update(s); // Cập nhật cơ sở dữ liệu
                isUpdated = true;
                break;
            }
        }
        
        if (!isUpdated) {
            System.out.println("Không tìm thấy giáo viên có mã: " + s.getMaGV());
        }
    }

    public static void main(String[] args) {
        GiaoVienBUS giaoVienBUS = new GiaoVienBUS();

        // Hiển thị tất cả giáo viên
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
