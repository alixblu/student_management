package BUS;

import java.util.ArrayList;

import DAO.ChiTietDiemDAO;
import DTO.ChiTietDiemDTO;


public class ChiTietDiemBUS {
    private ArrayList<ChiTietDiemDTO> dsctd ;
    public ChiTietDiemBUS(int i1)
    {
        list();
    }
    
    public ChiTietDiemBUS()
    {
        
    }

    public ChiTietDiemDTO getByNamhocid(String id)
    {
        for(ChiTietDiemDTO ctd : dsctd )
        {
            if(ctd.getNamHocID().equals(id))
            {
                return ctd;
            }
        }
        return null;
    }
    
    public ChiTietDiemDTO get(String idhs, String idnam,String idhk, String monhocid){
        
            for(ChiTietDiemDTO x : dsctd)
            {
                if( (x.getNamHocID().equals(idnam)) && (x.getHocSinhID().equals(idhs)) && (x.getHocKyID().equals(idhk)) && (x.getMonHocID().equals(monhocid))){
                    return x;
                }
            }
        
        return null;
    }

    public void list()
    {
        ChiTietDiemDAO ctdDATA = new ChiTietDiemDAO();
        dsctd = new ArrayList<>();
        dsctd = ctdDATA.list();
    }
    public ArrayList<String> getHocSinhIDs() {
        ArrayList<String> hocSinhIDs = new ArrayList<>();
    
        for (ChiTietDiemDTO ctd : dsctd) {
            if (!hocSinhIDs.contains(ctd.getHocSinhID())) {
                hocSinhIDs.add(ctd.getHocSinhID());
            }
        }
    
        return hocSinhIDs;
    }
    public ArrayList<String> getMonHocIDs() {
        ArrayList<String> monHocIDs = new ArrayList<>();
    
        for (ChiTietDiemDTO ctd : dsctd) {
            if (!monHocIDs.contains(ctd.getMonHocID())) {
                monHocIDs.add(ctd.getMonHocID());
            }
        }
    
        return monHocIDs;
    }
    public ArrayList<String> getHocKyIDs() {
        ArrayList<String> hocKyIDs = new ArrayList<>();
    
        for (ChiTietDiemDTO ctd : dsctd) {
            if (!hocKyIDs.contains(ctd.getHocKyID())) {
                hocKyIDs.add(ctd.getHocKyID());
            }
        }
    
        return hocKyIDs;
    }
    public ArrayList<String> getNamHocIDs() {
        ArrayList<String> namHocIDs = new ArrayList<>();
    
        for (ChiTietDiemDTO ctd : dsctd) {
            if (!namHocIDs.contains(ctd.getNamHocID())) {
                namHocIDs.add(ctd.getNamHocID());
            }
        }
    
        return namHocIDs;
    }
                
    public void delete(ChiTietDiemDTO s)
    {
        for(int i = 0 ; i < dsctd.size() ; i++)
        {
            if(dsctd.get(i).getHocSinhID().equals(s.getHocSinhID()) &&
            dsctd.get(i).getNamHocID().equals(s.getNamHocID()) &&
            dsctd.get(i).getMonHocID().equals(s.getMonHocID()))
            {
                dsctd.remove( s);
                ChiTietDiemDAO ctdDATA = new ChiTietDiemDAO();
                ctdDATA.delete(s);
                System.out.println("delete chitietdiem-------");
                return;
            }
        }
    }
        public void add(ChiTietDiemDTO ctd)
        {
            dsctd.add(ctd);
            ChiTietDiemDAO ctdDATA = new ChiTietDiemDAO();
            ctdDATA.add(ctd);
        }
    public void set(ChiTietDiemDTO s){
        boolean found = false;
        for(int i = 0 ; i < dsctd.size() ; i++)
        {
            if(dsctd.get(i).getHocSinhID().equals(s.getHocSinhID()) &&
            dsctd.get(i).getNamHocID().equals(s.getNamHocID()) &&
            dsctd.get(i).getMonHocID().equals(s.getMonHocID()))
            {
                dsctd.set(i, s);
                ChiTietDiemDAO ctdDATA = new ChiTietDiemDAO();
                ctdDATA.set(s);
                System.out.println("set chitietdiem-------");
                found = true;
                break;
            }
        }
        if (!found) {
            add(s);
        }
    }
    
    public boolean check(String id)
    {
        for(ChiTietDiemDTO ctd : dsctd)
        {
            if(ctd.getHocSinhID().equals(id))
            {
                return true;
            }
        }
        return false;
    }

    //không có thuộc tính điểm trong tìm kiếm (chuyen hesoid sang String)
    public ArrayList<ChiTietDiemDTO> search(String id,String monhocid,String hockyid, String namhocid)
    {
        ArrayList<ChiTietDiemDTO> search = new ArrayList<>();
        id = id==null?id = "": id;
        monhocid = (monhocid==null || monhocid.equals("Tất cả"))?monhocid = "": monhocid;
        hockyid = (hockyid==null || hockyid.equals("Tất cả"))?hockyid = "": hockyid;
        
        for(ChiTietDiemDTO ctd : dsctd)
        {
            if( ctd.getHocSinhID().contains(id) && 
                ctd.getMonHocID().contains(monhocid) &&
                ctd.getHocKyID().contains(hockyid))
            {
                search.add(ctd);
            }
        }
        return search;
    }

    public boolean isAnyDiemNull(String magv){
        ChiTietDiemDAO dao = new ChiTietDiemDAO();
        return dao.isAnyDiemNull(magv);
    }
    public ArrayList<ChiTietDiemDTO> getList() {
        return dsctd;
    }
    public static void main(String[] args) {
        ChiTietDiemBUS ctbus = new ChiTietDiemBUS(1); // Khởi tạo với dữ liệu
        String mahocsinh = "HSK241";  // Mã học sinh mẫu
        String namhocid = "20242025";  // Mã năm học mẫu
        String hockyid = "1";  // Mã học kỳ mẫu
        String monhocid = "MH1";  // Mã môn học mẫu
    
        ChiTietDiemDTO diem = ctbus.get(mahocsinh, namhocid, hockyid, monhocid);
        
        if (diem != null) {
            System.out.println("Điểm 15 phút: " + diem.getDiem1());
            System.out.println("Điểm 1 tiết: " + diem.getDiem2());
            System.out.println("Điểm học kỳ: " + diem.getDiem3());
        } else {
            System.out.println("Không tìm thấy chi tiết điểm cho học sinh " + mahocsinh);
        }
    }
    
}
