package BUS;

import DTO.PhanCongDTO;
import java.util.ArrayList;

import DAO.PhanCongDAO;

public class PhanCongBUS {
    private ArrayList<PhanCongDTO> dspc;

    public PhanCongBUS(int i1) {
        list();
    }

    public PhanCongBUS() {
    }

    public void list() {
        PhanCongDAO pcDAO = new PhanCongDAO();
        dspc = new ArrayList<>();
        dspc = pcDAO.list();
    }

    public void add(PhanCongDTO pc) {
        dspc.add(pc);
        PhanCongDAO pcDAO = new PhanCongDAO();
        pcDAO.add(pc);
    }

    public void delete(String hsid) {
        for (PhanCongDTO pc : dspc) {
            if (pc.getGiaoVienID().equals(hsid)) {
                dspc.remove(pc);
                PhanCongDAO pcDAO = new PhanCongDAO();
                pcDAO.delete(pc);
                return;
            }
        }
    }

    public void set(PhanCongDTO s) {
        for (int i = 0; i < dspc.size(); i++) {
            if (dspc.get(i).getGiaoVienID().equals(s.getGiaoVienID())) {
                dspc.set(i, s);
                PhanCongDAO pcDAO = new PhanCongDAO();
                pcDAO.set(s);
                return;
            }
        }
    }

    public PhanCongDTO get(String id) {
        PhanCongDTO pc = null;
        for (PhanCongDTO x : dspc) {
            if (x.getGiaoVienID().equals(id)) {
                pc = x;
            }
        }
        return pc;
    }

    public PhanCongDTO getByMonhocid(String id) {
        PhanCongDTO pc = null;
        for (PhanCongDTO x : dspc) {
            if (x.getGiaoVienID().equals(id)) {
                pc = x;
            }
        }
        return pc;
    }

    public PhanCongDTO get(String idgv, String idmh) {
        if (idgv == null) {
            return this.getByMonhocid(idmh);
        }
        if (idmh == null) {
            return this.get(idgv);
        }
        for (PhanCongDTO x : dspc) {
            // if ((x.getMonHocID().equals(idmh)) && (x.getGiaoVienID().equals(idgv))) {
            if ((x.getGiaoVienID().equals(idgv))) {
                return x;
            }
        }
        return null;
    }

    public boolean check(String id) {
        for (PhanCongDTO pc : dspc) {
            if (pc.getGiaoVienID().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<PhanCongDTO> search(String id, String idlop) {
        ArrayList<PhanCongDTO> search = new ArrayList<>();
        id = id == null ? id = "" : id;
        idlop = idlop == null ? idlop = "" : idlop;
        for (PhanCongDTO pc : dspc) {
            if (pc.getGiaoVienID().contains(id) &&
                    pc.getLopID().contains(idlop)
                    // pc.getMonHocID().contains(idmh)) {
            ){
                search.add(pc);
            }
        }
        return search;
    }

    public ArrayList<PhanCongDTO> search(String idlop) {
        ArrayList<PhanCongDTO> search = new ArrayList<>();
        idlop = idlop==null ? idlop = "" : idlop;
        for (PhanCongDTO pc : dspc) {
            if (pc.getLopID().contains(idlop)) {
                search.add(pc);
            }
        }
        return search;
    }

    public ArrayList<PhanCongDTO> getList() {
        return dspc;
    }

    // Method to print all attributes of the list
    public void printAllAttributes() {
        for (PhanCongDTO pc : dspc) {
            System.out.println(pc.toString());
        }
    }

    public static void main(String[] args) {
        PhanCongBUS phanCongBUS = new PhanCongBUS(1);

        // Display all PhanCongDTO
        
        for (PhanCongDTO pc : phanCongBUS.search("GV1", null)) {
            System.out.println(pc.toString());
        }
    }
}
