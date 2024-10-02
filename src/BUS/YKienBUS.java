package BUS;

import DTO.YKienDTO;
import java.util.ArrayList;

import DAO.YKienDAO;

public class YKienBUS {
    private ArrayList<YKienDTO> dsYKien;

    public YKienBUS(int i) {
        list();
    }

    public YKienBUS() {
    }

    public void list() {
        YKienDAO yKienDAO = new YKienDAO();
        dsYKien = yKienDAO.list();
    }

    public ArrayList<YKienDTO> getList() {
        return dsYKien;
    }

    public YKienDTO get(String idnguoigui) {
        for (YKienDTO yk : dsYKien) {
            if (yk.getIdnguoigui().equals(idnguoigui)) {
                return yk;
            }
        }
        return null;
    }

    public void add(YKienDTO yk) {
        dsYKien.add(yk);
        YKienDAO yKienDAO = new YKienDAO();
        yKienDAO.add(yk);
    }

    public void set(YKienDTO yk) {
        for (int i = 0; i < dsYKien.size(); i++) {
            if (dsYKien.get(i).getIdnguoigui().equals(yk.getIdnguoigui())) {
                dsYKien.set(i, yk);
                YKienDAO yKienDAO = new YKienDAO();
                yKienDAO.set(yk);
                return;
            }
        }
    }

    public void delete(String idnguoigui) {
        for (YKienDTO yk : dsYKien) {
            if (yk.getIdnguoigui().equals(idnguoigui)) {
                dsYKien.remove(yk);
                YKienDAO yKienDAO = new YKienDAO();
                yKienDAO.delete(idnguoigui);
                return;
            }
        }
    }

    public static void main(String[] args) {
        YKienBUS yKienBUS = new YKienBUS();
        ArrayList<YKienDTO> dsYKien = yKienBUS.getList();

        for (YKienDTO yk : dsYKien) {
            System.out.println("ID Người gửi: " + yk.getIdnguoigui());
            System.out.println("Tiêu đề: " + yk.getTieudeyk());
            System.out.println("Nội dung: " + yk.getNoidungyk());
            System.out.println("Thời gian: " + yk.getThoigianyk());
            System.out.println("Tên học sinh: " + yk.getTenhs());
            System.out.println("Trạng thái: " + yk.getTrangthai());
            System.out.println();
        }
    }
}
