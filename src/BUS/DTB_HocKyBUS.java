package BUS;

import java.util.ArrayList;

import DAO.DTB_HocKyDAO;
import DTO.DTB_HocKyDTO;

public class DTB_HocKyBUS {
    private ArrayList<DTB_HocKyDTO> dsdtb ;
    public DTB_HocKyBUS(int i1)
    {
        list();
    }
    
    public DTB_HocKyBUS()
    {
        
    }
    public DTB_HocKyDTO get(String id)
    {
        for(DTB_HocKyDTO dtb : dsdtb )
        {
            if(dtb.getHocSinhID().equals(id))
            {
                return dtb;
            }
        }
        return null;
    }
    
    public DTB_HocKyDTO getByNamhocid(String id)
    {
        for(DTB_HocKyDTO dtb : dsdtb )
        {
            if(dtb.getNamHocID().equals(id))
            {
                return dtb;
            }
        }
        return null;
    }
    public DTB_HocKyDTO getByHockyid(String id)
    {
        for(DTB_HocKyDTO dtb : dsdtb )
        {
            if(dtb.getHocKyID().equals(id))
            {
                return dtb;
            }
        }
        return null;
    }
    //overloading
    public DTB_HocKyDTO get(String idhs, String idnam, String idhk){
        
        for(DTB_HocKyDTO x : dsdtb)
        {
            if( (x.getNamHocID().equals(idnam)) && (x.getHocSinhID().equals(idhs)) && (x.getHocKyID().equals(idhk))){
                return x;
            }
        }

        return null;
    }
        
    public void list()
    {
        DTB_HocKyDAO dtbDATA = new DTB_HocKyDAO();
        dsdtb = new ArrayList<>();
        dsdtb = dtbDATA.list();
    }

    public void delete(DTB_HocKyDTO s)
    {
        for(int i = 0 ; i < dsdtb.size() ; i++)
        {
            if(dsdtb.get(i).getHocSinhID().equals(s.getHocSinhID())&&
            dsdtb.get(i).getNamHocID().equals(s.getNamHocID())&&
            dsdtb.get(i).getHocKyID().equals(s.getHocKyID()))
            {
                dsdtb.remove(s);
                DTB_HocKyDAO dtbDATA = new DTB_HocKyDAO();
                dtbDATA.delete(s);
                return;
            }
        }
    }
    public void add(DTB_HocKyDTO dtb)
    {
        dsdtb.add(dtb);
        DTB_HocKyDAO dtbDATA = new DTB_HocKyDAO();
        dtbDATA.add(dtb);
    }

    public void set(DTB_HocKyDTO s){
        boolean found = false;
        for(int i = 0 ; i < dsdtb.size() ; i++)
        {
            if(dsdtb.get(i).getHocSinhID().equals(s.getHocSinhID()) &&
            dsdtb.get(i).getNamHocID().equals(s.getNamHocID()) &&
            dsdtb.get(i).getHocKyID().equals(s.getHocKyID()))
            {
                dsdtb.set(i, s);
                DTB_HocKyDAO dtbDATA = new DTB_HocKyDAO();
                dtbDATA.set(s);
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
        for(DTB_HocKyDTO dtb : dsdtb)
        {
            if(dtb.getHocSinhID().equals(id))
            {
                return true;
            }
        }
        return false;
    }

    //thay datatype thanh String khi tim bang diem
    public ArrayList<DTB_HocKyDTO> search(String id,String monhocid,String hockyid, String diem)
    {
        ArrayList<DTB_HocKyDTO> search = new ArrayList<>();
        id = id==null?id = "": id;
        monhocid = (monhocid==null || monhocid.equals("Tất cả"))?monhocid = "": monhocid;
        hockyid = (hockyid==null || hockyid.equals("Tất cả"))?hockyid = "": hockyid;
        diem = (diem==null || diem.equals("Tất cả"))?diem = "": diem;
        
        for(DTB_HocKyDTO dtb : dsdtb)
        {
            if( dtb.getHocSinhID().contains(id) &&
                dtb.getHocKyID().contains(hockyid) &&
                String.valueOf(dtb.getDiemTrungBinh()).contains(diem))
            {
                search.add(dtb);
            }
        }
        return search;
    }

    public ArrayList<DTB_HocKyDTO> getList() {
        return dsdtb;
    }
    public static void main(String[] args) {
        DTB_HocKyBUS bus = new DTB_HocKyBUS();
        bus.get("HSK242", "20242025", "1");
    }
}
