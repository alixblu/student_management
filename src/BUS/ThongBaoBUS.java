package BUS;

import DATA.ThongBaoDAO;
import DTO.ThongBaoDTO;
import java.util.ArrayList;

public class ThongBaoBUS {
    private ArrayList<ThongBaoDTO> dsThongBao;

    public ThongBaoBUS() {
        list();
    }

    public void list() {
        ThongBaoDAO thongBaoDAO = new ThongBaoDAO();
        dsThongBao = thongBaoDAO.list();
    }

    public ArrayList<ThongBaoDTO> getList() {
        return dsThongBao;
    }
//thieu check nam trong get()
    public ThongBaoDTO get(String idnguoigui) {
        for (ThongBaoDTO tb : dsThongBao) {
            if (tb.getIdnguoigui().equals(idnguoigui)) {
                return tb;
            }
        }
        return null;
    }

    public void add(ThongBaoDTO tb) {
        dsThongBao.add(tb);
        ThongBaoDAO thongBaoDAO = new ThongBaoDAO();
        thongBaoDAO.add(tb);
    }

    public void set(ThongBaoDTO tb) {
        for (int i = 0; i < dsThongBao.size(); i++) {
            if (dsThongBao.get(i).getIdnguoigui().equals(tb.getIdnguoigui())) {
                dsThongBao.set(i, tb);
                ThongBaoDAO thongBaoDAO = new ThongBaoDAO();
                thongBaoDAO.set(tb);
                return;
            }
        }
    }

    public void delete(String idnguoigui) {
        for (ThongBaoDTO tb : dsThongBao) {
            if (tb.getIdnguoigui().equals(idnguoigui)) {
                dsThongBao.remove(tb);
                ThongBaoDAO thongBaoDAO = new ThongBaoDAO();
                thongBaoDAO.delete(idnguoigui);
                return;
            }
        }
    }

    public static void main(String[] args) {
        ThongBaoBUS thongBaoBUS = new ThongBaoBUS();
        ArrayList<ThongBaoDTO> dsThongBao = thongBaoBUS.getList();

        for (ThongBaoDTO tb : dsThongBao) {
            System.out.println("ID Người gửi: " + tb.getIdnguoigui());
            System.out.println("Tiêu đề: " + tb.getTieudetb());
            System.out.println("Nội dung: " + tb.getNoidungtb());
            System.out.println("Thời gian: " + tb.getThoigiantb());
            System.out.println("Loại: " + tb.getLoaitb());
            System.out.println();
        }
    }
}
