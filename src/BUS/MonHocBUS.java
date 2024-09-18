package BUS;

import java.util.ArrayList;

import DATA.MonHocDAO;
import DTO.MonHocDTO;

public class MonHocBUS {
    private ArrayList<MonHocDTO> dsmh ;
    public MonHocBUS(int i1)
    {
        list();
    }
    
    public MonHocBUS()
    {
        
    }

    // theem
    public void addMH(MonHocDTO mh) {
        dsmh.add(mh);
        MonHocDAO hsDAO = new MonHocDAO();
        hsDAO.add(mh);
    }
   
    // xoa
//    public void deleteMH(String mamh) {
//         for (MonHocDTO mh : dsmh) {
//             if (mh.getMonHocID().equals(mamh)) {
//                 MonHocDAO mhDAO = new MonHocDAO();
//                 mhDAO.delete(mamh);
//                 dsmh.remove(mh); // Xóa môn học khỏi danh sách
//                 System.out.println("Đã xóa MonHoc với ID: " + mamh); // Thông báo đăng nhập
//                 return;
//             }
//         }
//         System.out.println("Không tìm thấy MonHoc với ID " + mamh + " trong danh sách."); // Thông báo đăng nhập nếu không tìm thấy
//     }
    public void deleteMH(String mamh) {
        for (MonHocDTO hs : dsmh) {
            if (hs.getMonHocID().equals(mamh)) {
                // dshs.remove(mahs);
                MonHocDAO hsDAO = new MonHocDAO();
                hsDAO.delete(mamh);
                return;
            }
        }
    }

    //search by id mon hoc
    public MonHocDTO get(String id)
    {
        for(MonHocDTO mh : dsmh )
        {
            if(mh.getMonHocID().equals(id))
            {
                return mh;
            }
        }
        return null;
    }
    // get/set
    public void updateMH(MonHocDTO s) {
        for (int i = 0; i < dsmh.size(); i++) {
            if (dsmh.get(i).getMonHocID().equals(s.getMonHocID())) {
                dsmh.set(i, s);
                MonHocDAO mhDAO = new MonHocDAO();
                mhDAO.Update(s);
                return;
            }
        }
    }
    public MonHocDTO getByName(String tenmh)
    {
        for(MonHocDTO mh : dsmh )
        {
            if(mh.getTenMonHoc().equals(tenmh))
            {
                return mh;
            }
        }
        return null;
    }
    public void list()
    {
        MonHocDAO mhDAO = new MonHocDAO();
        dsmh = new ArrayList<>();
        dsmh = mhDAO.list();
    }
  
     // kiem tra ma môn học
    // public boolean checkMaMH(String mamh) {
    //     MonHocDAO mhDao = new MonHocDAO();
    //     dsmh = new ArrayList<>();
    //     dsmh = mhDao.checkMaMH();
    //     for (MonHocDTO mh : dsmh) {
    //         System.out.println(mh.getMonHocID());
    //         if (mh.getMonHocID().equals(dsmh)) {
    //             return true;
    //        }
    //     }
    //     return false;
    // }

    public boolean checkMaMH(String id) {
        // Cập nhật danh sách người dùng từ cơ sở dữ liệu
        list();
        
        for (MonHocDTO mh : dsmh) {
            if (mh.getMonHocID().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkMH(String id)
    {
        for(MonHocDTO mh : dsmh)
        {
            if(mh.getMonHocID().equals(id))
            {
                return true;
            }
        }
        return false;
    }

    public ArrayList<MonHocDTO> search(String id,String monhoc)
    {
        ArrayList<MonHocDTO> search = new ArrayList<>();
        id = id==null?id = "": id;
        monhoc = (monhoc==null ||monhoc.equals("Tất cả"))?monhoc = "":monhoc;
        
        for(MonHocDTO mh : dsmh)
        {
            if( mh.getMonHocID().contains(id) &&
                mh.getTenMonHoc().contains(monhoc))
            {
                search.add(mh);
            }
        }
        return search;
    }

  
    public ArrayList<MonHocDTO> getList() {
        return dsmh;
    }
    public static void main(String[] args) {
        // Create an instance of MonHocBUS
        MonHocBUS monHocBUS = new MonHocBUS(1);
    
        // // Search for MonHocDTO with TenMonHoc="Toan"
        // String subjectToSearch = "Tất cả";
        // ArrayList<MonHocDTO> searchResult = monHocBUS.search(null, subjectToSearch);
    
        // // Print the search result
        // if (!searchResult.isEmpty()) {
        //     System.out.println("Search Result for Subject " + subjectToSearch + ":");
        //     for (MonHocDTO mh : searchResult) {
        //         System.out.println(mh);
        //     }
        // } else {
        //     System.out.println("No result found for Subject " + subjectToSearch);
        // }
        System.out.println(monHocBUS.getByName("Vật Lý").getMonHocID());
    }
    
}
