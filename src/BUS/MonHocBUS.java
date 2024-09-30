package BUS;

import java.util.ArrayList;

import DAO.MonHocDAO;
import DTO.MonHocDTO;

public class MonHocBUS {
    private ArrayList<MonHocDTO> dsmh;

    // Constructor with parameter
    public MonHocBUS(int i1) {
        dsmh = new ArrayList<>(); // Khởi tạo danh sách
        list(); // Nạp danh sách môn học từ cơ sở dữ liệu
    }

    // Default constructor
    public MonHocBUS() {
        dsmh = new ArrayList<>(); // Khởi tạo danh sách
        list(); // Nạp danh sách môn học từ cơ sở dữ liệu
    }

    // Add a subject
    public void addMH(MonHocDTO mh) {
        if (dsmh != null) {
            dsmh.add(mh);
            MonHocDAO hsDAO = new MonHocDAO();
            hsDAO.add(mh);
        } else {
            System.out.println("Danh sách môn học chưa được khởi tạo.");
        }
    }

    // Delete a subject
    public void deleteMH(String mamh) {
        if (dsmh != null) {
            for (MonHocDTO mh : dsmh) {
                if (mh.getMonHocID().equals(mamh)) {
                    MonHocDAO hsDAO = new MonHocDAO();
                    hsDAO.delete(mamh);
                    dsmh.remove(mh); // Xóa môn học khỏi danh sách
                    System.out.println("Đã xóa môn học với ID: " + mamh);
                    return;
                }
            }
            System.out.println("Không tìm thấy môn học với ID: " + mamh);
        }
    }

    // Search by subject ID
    public MonHocDTO get(String id) {
        if (dsmh != null) {
            for (MonHocDTO mh : dsmh) {
                if (mh.getMonHocID().equals(id)) {
                    return mh;
                }
            }
        }
        return null;
    }

    // Update a subject
    public void updateMH(MonHocDTO s) {
        if (dsmh != null) {
            for (int i = 0; i < dsmh.size(); i++) {
                if (dsmh.get(i).getMonHocID().equals(s.getMonHocID())) {
                    dsmh.set(i, s);
                    MonHocDAO mhDAO = new MonHocDAO();
                    mhDAO.Update(s);
                    return;
                }
            }
        }
    }

    // Get subject by name
    public MonHocDTO getByName(String tenmh) {
        if (dsmh != null) {
            for (MonHocDTO mh : dsmh) {
                if (mh.getTenMonHoc().equals(tenmh)) {
                    return mh;
                }
            }
        }
        return null;
    }

    // Load the list of subjects from the database
    public void list() {
        MonHocDAO mhDAO = new MonHocDAO();
        dsmh = mhDAO.list(); // Lấy danh sách từ cơ sở dữ liệu
    }

    // Get list of subject names
    public ArrayList<String> getTenMonHocs() {
        ArrayList<String> tenMonHocs = new ArrayList<>();
        if (dsmh != null) { // Kiểm tra nếu dsmh không null
            for (MonHocDTO mh : dsmh) {
                if (mh != null) { // Kiểm tra nếu mh không null
                    tenMonHocs.add(mh.getTenMonHoc());
                }
            }
        }
        return tenMonHocs;
    }

    // Check if subject ID exists
    public boolean checkMaMH(String id) {
        list(); // Cập nhật danh sách môn học
        if (dsmh != null) {
            for (MonHocDTO mh : dsmh) {
                if (mh.getMonHocID().equals(id)) {
                    return true;
                }
            }
        }
        return false;
    }

    // Check if subject exists by ID
    public boolean checkMH(String id) {
        if (dsmh != null) {
            for (MonHocDTO mh : dsmh) {
                if (mh.getMonHocID().equals(id)) {
                    return true;
                }
            }
        }
        return false;
    }

    // Search for subjects based on ID and name
    public ArrayList<MonHocDTO> search(String id, String monhoc) {
        ArrayList<MonHocDTO> search = new ArrayList<>();
        id = (id == null) ? "" : id;
        monhoc = (monhoc == null || monhoc.equals("Tất cả")) ? "" : monhoc;

        if (dsmh != null) {
            for (MonHocDTO mh : dsmh) {
                if (mh.getMonHocID().contains(id) && mh.getTenMonHoc().contains(monhoc)) {
                    search.add(mh);
                }
            }
        }
        return search;
    }

    // Get the list of subjects
    public ArrayList<MonHocDTO> getList() {
        return dsmh;
    }

    public static void main(String[] args) {
        // Create an instance of MonHocBUS
        MonHocBUS monHocBUS = new MonHocBUS(1);

        // Get the list of subjects
        ArrayList<MonHocDTO> danhSachMonHoc = monHocBUS.getList();

        // Print the list of subjects
        if (danhSachMonHoc != null && !danhSachMonHoc.isEmpty()) {
            System.out.println("Danh sách môn học:");
            for (MonHocDTO mh : danhSachMonHoc) {
                System.out.println(mh.getMonHocID() + " - " + mh.getTenMonHoc());
            }
        } else {
            System.out.println("Không có môn học nào trong danh sách.");
        }

        // Search for a subject by name
        String tenMonHocCanTim = "Vật Lý";
        MonHocDTO monHocTimThay = monHocBUS.getByName(tenMonHocCanTim);

        // Check and print found subject information
        if (monHocTimThay != null) {
            System.out.println("Thông tin môn học tìm thấy:");
            System.out.println("ID: " + monHocTimThay.getMonHocID() + ", Tên: " + monHocTimThay.getTenMonHoc());
        } else {
            System.out.println("Không tìm thấy môn học với tên: " + tenMonHocCanTim);
        }
    }
    
    public Integer CountMH() {
        MonHocDAO mh = new MonHocDAO();
        Integer count = mh.CountMH();
        return count;
    }
}
