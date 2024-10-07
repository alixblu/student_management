/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DTO.LopDTO;
import java.util.ArrayList;

import DAO.LopDAO;


public class LopBUS {
    private ArrayList<LopDTO> dslop ;
    public LopBUS(int i1)
    {
        list();
    }
    
    public LopBUS()
    {
        list();
    }
    public LopDTO get(String id)
    {
        for(LopDTO lop : dslop )
        {
            if(lop.getLopID().equals(id))
            {
                return lop;
            }
        }
        return null;
    }
    public LopDTO getByName(String tenlop)
    {
        for(LopDTO lop : dslop )
        {
            if(lop.getTenLop().equals(tenlop))
            {
                return lop;
            }
        }
        return null;
    }
    
    public void list()
    {
        LopDAO lopDAO = new LopDAO();
        dslop = new ArrayList<>();
        dslop = lopDAO.list();
    }
    public void add(LopDTO lop)
    {
        dslop.add(lop);
        LopDAO lopDAO = new LopDAO();
        lopDAO.add(lop);
    }

    public void delete(String id)
    {
        for(LopDTO lop : dslop )
        {
            if(lop.getLopID().equals(id))
            {
                dslop.remove(lop);
                LopDAO lopDAO = new LopDAO();
                lopDAO.delete(lop);
                return;
            }
        }
    }
    public void set(LopDTO s)
    {
        for(int i = 0 ; i < dslop.size() ; i++)
        {
            if(dslop.get(i).getLopID().equals(s.getLopID()))
            {
                dslop.set(i, s);
                LopDAO lopDAO = new LopDAO();
                lopDAO.set(s);
                return;
            }
        }
    }
    public boolean check(String id)
    {
        for(LopDTO lop : dslop)
        {
            if(lop.getLopID().equals(id))
            {
                return true;
            }
        }
        return false;
    }
    public ArrayList<LopDTO> search(String id,String tenlop)
    {
        ArrayList<LopDTO> search = new ArrayList<>();
        id = id==null ?id = "": id;
        tenlop = (tenlop==null || tenlop.equals("Tất cả"))?tenlop = "": tenlop;
        for(LopDTO lop : dslop)
        {
            if( lop.getLopID().contains(id) && 
                lop.getTenLop().contains(tenlop))
            {
                search.add(lop);
            }
        }
        return search;
    }

    public ArrayList<LopDTO> search(String hoten)
    {
        ArrayList<LopDTO> search = new ArrayList<>();
        hoten = hoten.isEmpty()?hoten = "": hoten;
        for(LopDTO lop : dslop)
        {
            if( lop.getTenLop().contains(hoten))
            {
                search.add(lop);
            }
        }
        return search;
    }
        
    public LopDTO searchById(String id)
    {
        LopDTO lop = null;
        for(LopDTO x : dslop)
        {
            if( lop.getLopID()==id){
                lop = x;
            }
        }
        return lop;
    }
    
    public ArrayList<LopDTO> getList() {
        return dslop;
    }

    public ArrayList<String> list_TenLop()
    {
        ArrayList<String> arr = new LopDAO().list_Tenlop();
        return arr;
    }

    public ArrayList<String> list_TenLop(String khoi)
    {
        ArrayList<String> arr = new LopDAO().list_Tenlop(khoi);
        return arr;
    }

    public ArrayList<LopDTO> list_lop()
    {
        ArrayList<LopDTO> listlop = new LopDAO().list();
        return listlop;
    }
    
    
    public String getIdByCondString(String tenlop){
        return new LopDAO().getIdByCondition(tenlop);
    }

    public static void main(String[] args) {
        // Create an instance of LopBUS
        LopBUS lopBUS = new LopBUS();
        ArrayList<LopDTO> lop = lopBUS.getList();
        for (LopDTO lopDTO : lop) {
            System.out.println(lopDTO.getTenLop());
        }
    }
    
}
