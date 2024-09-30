package BUS;

import java.util.ArrayList;

import DAO.KQ_HocSinhCaNamDAO;
import DTO.KQ_HocSinhCaNamDTO;

public class KQ_HocSinhCaNamBUS {
    private ArrayList<KQ_HocSinhCaNamDTO> dskq ;
    public KQ_HocSinhCaNamBUS()
    {
    }
    public KQ_HocSinhCaNamBUS(int i)
    {
        list();
    }
    
    public KQ_HocSinhCaNamDTO get(String idhs, String idnam){
        
            for(KQ_HocSinhCaNamDTO x : dskq)
            {
                if( (x.getNamHocID().equals(idnam)) && (x.getHocSinhID().equals(idhs))){
                    return x;
                }
            }

        return null;
    }
    public void list()
    {
        KQ_HocSinhCaNamDAO kq_kqDATA = new KQ_HocSinhCaNamDAO();
        dskq = new ArrayList<>();
        dskq = kq_kqDATA.list();
    }

    public void delete(KQ_HocSinhCaNamDTO s)
    {
        for(int i = 0 ; i < dskq.size() ; i++)
        {
            if(dskq.get(i).getHocSinhID().equals(s.getHocSinhID()) && dskq.get(i).getNamHocID().equals(s.getNamHocID()))
            {
                System.out.println("delete kqcn bus");
                dskq.remove(s);
                KQ_HocSinhCaNamDAO kq_kqDATA = new KQ_HocSinhCaNamDAO();
                kq_kqDATA.set(s);
                return;
            }
        }
    }

    public void add(KQ_HocSinhCaNamDTO kq)
    {
        dskq.add(kq);
        KQ_HocSinhCaNamDAO kq_kqDATA = new KQ_HocSinhCaNamDAO();
        kq_kqDATA.add(kq);
    }

    public void set(KQ_HocSinhCaNamDTO s){
        boolean found = false;
        for(int i = 0 ; i < dskq.size() ; i++)
        {
            if(dskq.get(i).getHocSinhID().equals(s.getHocSinhID()) && dskq.get(i).getNamHocID().equals(s.getNamHocID()))
            {
                System.out.println("set kqcn bus");
                dskq.set(i, s);
                KQ_HocSinhCaNamDAO kq_kqDATA = new KQ_HocSinhCaNamDAO();
                kq_kqDATA.set(s);
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
        for(KQ_HocSinhCaNamDTO kq : dskq)
        {
            if(kq.getHocSinhID().equals(id))
            {
                return true;
            }
        }
        return false;
    }

    //không có thuộc tính điểm trong tìm kiếm 
    public ArrayList<KQ_HocSinhCaNamDTO> search(String id,String idnamhoc, String hocluc, String hanhkiem, String ketqua)
    {
        ArrayList<KQ_HocSinhCaNamDTO> search = new ArrayList<>();
        id = id==null ?id = "": id;
        idnamhoc = idnamhoc==null?idnamhoc = "": idnamhoc;
        hocluc = (hocluc==null || hocluc.equals("Tất cả"))?hocluc = "": hocluc;
        hanhkiem = (hanhkiem==null || hanhkiem.equals("Tất cả"))?hanhkiem = "": hanhkiem;
        ketqua = (ketqua==null || ketqua.equals("Tất cả"))?ketqua = "": ketqua;

        for(KQ_HocSinhCaNamDTO kq : dskq)
        {
            if( kq.getHocSinhID().contains(id) && 
                kq.getNamHocID().contains(idnamhoc) &&
                kq.getHocLuc().contains(hocluc) &&
                kq.getHanhKiem().contains(hanhkiem) &&
                kq.getKetQua().contains(ketqua) )
                {
                    search.add(kq);
                }
        }
        return search;
    }

    public ArrayList<KQ_HocSinhCaNamDTO> getList() {
        return dskq;
    }

    
        public static void main(String[] args) {
            KQ_HocSinhCaNamBUS hsbus = new KQ_HocSinhCaNamBUS(1);
   
            // hsbus.list(); 

            // for (KQ_HocSinhCaNamDTO kq : hsbus.getList()) {
            //     System.out.println("HocSinhID: " + kq.getHocSinhID());
            //     System.out.println("HocLuc: " + kq.getHocLuc());
            //     System.out.println("HanhKiem: " + kq.getHanhKiem());
            //     // Add more fields if needed
            //     System.out.println("----------------------");
            // }
            System.out.println(hsbus.get("HS1", "giapthin").getHocLuc());
        }
    
}
