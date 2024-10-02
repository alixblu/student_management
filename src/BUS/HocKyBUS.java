package BUS;

import java.util.ArrayList;
import DAO.HocKyDAO;
import DTO.HocKyDTO;

public class HocKyBUS {
    private ArrayList<HocKyDTO> dshk;

    public HocKyBUS(int i1) {
        list();
    }

    public HocKyBUS() {
    }

    public void list() {
        HocKyDAO hkDAO = new HocKyDAO();
        dshk = hkDAO.list();
    }

    public void add(HocKyDTO hk) {
        dshk.add(hk);
        HocKyDAO hkDAO = new HocKyDAO();
        hkDAO.add(hk);
    }

    public void set(HocKyDTO s) {
        for (int i = 0; i < dshk.size(); i++) {
            if (dshk.get(i).getHocKyID().equals(s.getHocKyID())) {
                dshk.set(i, s);
                HocKyDAO hkDAO = new HocKyDAO();
                hkDAO.set(s);
                return;
            }
        }
    }

    public HocKyDTO getByName(String tenhs) {
        for (HocKyDTO x : dshk) {
            if (x.getTenHocKy().equalsIgnoreCase(tenhs)) {
                return x;
            }
        }
        return null;
    }

    public String getHocKyIDFromTenHocKy(String tenHocKy) {
        if (dshk == null || dshk.isEmpty()) {
            System.out.println("Danh sách học kỳ trống!");
            return null;
        }

        for (HocKyDTO hk : dshk) {
            if (hk.getTenHocKy().equalsIgnoreCase(tenHocKy)) {
                System.out.println("Tìm thấy học kỳ: " + hk.getHocKyID());
                return hk.getHocKyID();
            }
        }

        System.out.println("Không tìm thấy học kỳ với tên: " + tenHocKy);
        return null;
    }

    public boolean check(String id) {
        for (HocKyDTO hk : dshk) {
            if (hk.getHocKyID().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<HocKyDTO> search(String id, String tenhk) {
        ArrayList<HocKyDTO> search = new ArrayList<>();
        id = (id == null) ? "" : id;
        tenhk = (tenhk == null || tenhk.equals("Tất cả")) ? "" : tenhk;
        for (HocKyDTO hk : dshk) {
            if (hk.getHocKyID().contains(id) && hk.getTenHocKy().contains(tenhk)) {
                search.add(hk);
            }
        }
        return search;
    }

    public HocKyDTO get(String id) {
        for (HocKyDTO x : dshk) {
            if (x.getHocKyID().equals(id)) {
                return x;
            }
        }
        return null;
    }

    public ArrayList<HocKyDTO> getList() {
        if (dshk == null) {
            list();
        }
        return dshk;
    }


    public static void main(String[] args) {
        HocKyBUS hocKyBUS = new HocKyBUS(1);
        
        // Populate the list
        hocKyBUS.list();

        // Search for "Học Kỳ 1"
        String tenHocKyToSearch = "Học Kỳ 1";
        ArrayList<HocKyDTO> searchResult = hocKyBUS.search(null, tenHocKyToSearch);
        
        // Print out search result
        for (HocKyDTO hocKy : searchResult) {
            System.out.println("HocKyID: " + hocKy.getHocKyID());
            System.out.println("TenHocKy: " + hocKy.getTenHocKy());
            System.out.println("-------------------------");
        }

        System.out.println(hocKyBUS.getByName("Học Kỳ 1").getHocKyID());
    }
}
