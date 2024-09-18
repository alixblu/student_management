package BUS;

import java.util.ArrayList;

import DATA.ChiTietDiemDAO;
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
    public ChiTietDiemDTO get(String id)
    {
        for(ChiTietDiemDTO ctd : dsctd )
        {
            if(ctd.getHocSinhID().equals(id))
            {
                return ctd;
            }
        }
        return null;
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
    
    //overloading
    public ChiTietDiemDTO get(String idhs, String idnam,String idhk, String monhocid, int heso){
        
            for(ChiTietDiemDTO x : dsctd)
            {
                if( (x.getNamHocID().equals(idnam)) && (x.getHocSinhID().equals(idhs)) && (x.getHocKyID().equals(idhk)) && (x.getMonHocID().equals(monhocid)) && (x.getHeSoID()==heso)){
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
    
    public void delete(ChiTietDiemDTO s)
    {
        for(int i = 0 ; i < dsctd.size() ; i++)
        {
            if(dsctd.get(i).getHocSinhID().equals(s.getHocSinhID()) &&
            dsctd.get(i).getNamHocID().equals(s.getNamHocID()) &&
            dsctd.get(i).getMonHocID().equals(s.getMonHocID()) &&
            dsctd.get(i).getHeSoID()==(s.getHeSoID()))
            {
                dsctd.remove( s);
                ChiTietDiemDAO ctdDATA = new ChiTietDiemDAO();
                ctdDATA.delete(s);
                System.out.println("set chitietdiem-------");
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
            dsctd.get(i).getMonHocID().equals(s.getMonHocID()) &&
            dsctd.get(i).getHeSoID() == s.getHeSoID())
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
    public ArrayList<ChiTietDiemDTO> search(String id,String monhocid,String hockyid, String hesoid, String namhocid)
    {
        ArrayList<ChiTietDiemDTO> search = new ArrayList<>();
        id = id==null?id = "": id;
        monhocid = (monhocid==null || monhocid.equals("Tất cả"))?monhocid = "": monhocid;
        hockyid = (hockyid==null || hockyid.equals("Tất cả"))?hockyid = "": hockyid;
        hesoid = (hesoid==null || hesoid.equals("Tất cả"))?hesoid = "": hesoid;
        
        for(ChiTietDiemDTO ctd : dsctd)
        {
            if( ctd.getHocSinhID().contains(id) && 
                ctd.getMonHocID().contains(monhocid) &&
                ctd.getHocKyID().contains(hockyid) &&
                String.valueOf(ctd.getHeSoID()).contains(hesoid))
            {
                search.add(ctd);
            }
        }
        return search;
    }

    public ArrayList<ChiTietDiemDTO> getList() {
        return dsctd;
    }
}
