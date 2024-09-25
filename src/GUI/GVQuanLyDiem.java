/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import BUS.ChiTietDiemBUS;
import BUS.DTB_HocKyBUS;
import BUS.HocKyBUS;
import BUS.HocSinhBUS;
import BUS.KQ_HocSinhCaNamBUS;
import BUS.LopBUS;
import BUS.MonHocBUS;
import BUS.NamHocBUS;
import BUS.PhanCongBUS;
import BUS.PhanLopBUS;
import DTO.ChiTietDiemDTO;
import DTO.DTB_HocKyDTO;
import DTO.HocKyDTO;
import DTO.HocSinhDTO;
import DTO.KQ_HocSinhCaNamDTO;
import DTO.LopDTO;
import DTO.MonHocDTO;
import DTO.NamHocDTO;
import DTO.PhanCongDTO;
import DTO.PhanLopDTO;

/**
 *
 * @author PHUONG ANH
 */
public class GVQuanLyDiem extends JPanel {
    protected int StatusDiem = 0 ;
    String magiaovien;
    //private JFrame f;
    private JPanel topPanel, radioPanel, dropdownPanel, selectPanel, totalPanel, btnPanel, btnPanel2, contentPanel,
            detailPanel, main_detailPanel;
    private JLabel b1, b3, b4, b5, b6;
    private JComboBox<String> optionLop, optionHe, optionHocky, optionNam;
    private JTextField s, inputID, outputDiem;
    private JLabel l1, l2;
    private JButton filterBtn, editBtn, delBtn, sendBtn;
    private NonEditableTableModel tblModel;
    private JScrollPane scrollPane;
    private JTable t;
    private static String outputID, outputHeid, outputMon, outputHK, outputNam, outputDiemhk, outputDiemcanam,
            outputTenHS, outputLop;

    ArrayList<HocSinhDTO> dshs;
    ArrayList<KQ_HocSinhCaNamDTO> dskq;
    ArrayList<MonHocDTO> dsmon;
    ArrayList<ChiTietDiemDTO> dsct;
    ArrayList<HocKyDTO> dshk;
    ArrayList<DTB_HocKyDTO> dsdtb;
    ArrayList<NamHocDTO> dsnh;
    ArrayList<PhanLopDTO> dspl;
    ArrayList<LopDTO> dslop;
    ArrayList<PhanCongDTO> dspc;

    PhanLopBUS plbus = new PhanLopBUS(1);
    LopBUS lopbus = new LopBUS(1);
    HocSinhBUS hsbus = new HocSinhBUS(1);
    MonHocBUS mhbus = new MonHocBUS(1);
    ChiTietDiemBUS ctbus = new ChiTietDiemBUS(1);
    DTB_HocKyBUS dtbbus = new DTB_HocKyBUS(1);
    HocKyBUS hkbus = new HocKyBUS(1);
    KQ_HocSinhCaNamBUS kqbus = new KQ_HocSinhCaNamBUS(1);
    NamHocBUS nhbus = new NamHocBUS(1);
    PhanCongBUS pcbus = new PhanCongBUS(1);
    int width, height;

    public GVQuanLyDiem(int width, int height, String magiaovien) {
    //public GVQuanLyDiem(String magiaovien) {

        this.magiaovien = magiaovien;
        this.width = width;
        this.height = height;
        setLayout(new BorderLayout());
        setSize(850, 670);

        topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.setPreferredSize(new Dimension(0, 160));
        topPanel.setBackground(new Color(180, 204, 227));

        selectPanel = new JPanel();
        selectPanel.setLayout(new BoxLayout(selectPanel, BoxLayout.Y_AXIS));
        selectPanel.setOpaque(false);

        l1 = new JLabel("Hiển thị danh sách điểm theo ");
        l1.setFont(new Font("Arial", Font.BOLD, 20));
        l1.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 100));

        radioPanel = new JPanel();
        radioPanel.setOpaque(false);
        radioPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 70, 0));

        b1 = new JLabel("Lớp");

        b3 = new JLabel("Mã HS");
        b4 = new JLabel("Hệ điểm");
        b5 = new JLabel("Học kỳ");
        b6 = new JLabel("Năm học");
        JLabel[] buttons = { b1, b3, b4, b5, b6 };
        Color color = new Color(180, 204, 227);
        for (JLabel button : buttons) {
            button.setBackground(color);
        }

        dropdownPanel = new JPanel();
        dropdownPanel.setOpaque(false);
        dropdownPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 36, 0));

        String[] c1 = { "Tất cả", "10A1", "11A1", "12A1" };
        optionLop = new JComboBox<>(c1);

        inputID = new JTextField(6);
        String[] c3 = { "Tất cả", "(1): 15 phút", "(2): 1 tiết", "(3): Thi" };
        optionHe = new JComboBox<>(c3);
        String[] c4 = { "Tất cả", "Học Kỳ 1", "Học Kỳ 2" };
        optionHocky = new JComboBox<>(c4);
        String[] c5 = { "Tất cả", "2024-2025", "2023-2024" };
        optionNam = new JComboBox<>(c5);
        totalPanel = new JPanel();
        totalPanel.setOpaque(false);

        l2 = new JLabel("Tổng số học sinh trong danh sách:");
        s = new JTextField(4);
        s.setEditable(false);

        btnPanel = new JPanel(new GridBagLayout());
        btnPanel.setPreferredSize(new Dimension(150, 0));
        btnPanel.setOpaque(false);

        editBtn = new JButton("Sửa");
        editBtn.setPreferredSize(new Dimension(110, 30));
        editBtn.setBackground(new Color(0, 83, 22));
        editBtn.setForeground(Color.WHITE);

        delBtn = new JButton("Xóa");
        delBtn.setPreferredSize(new Dimension(110, 30));
        delBtn.setBackground(new Color(255, 49, 49));
        delBtn.setForeground(Color.WHITE);

        filterBtn = new JButton("Lọc");
        filterBtn.setPreferredSize(new Dimension(110, 30));
        filterBtn.setBackground(new Color(31, 28, 77));
        filterBtn.setForeground(Color.WHITE);

        sendBtn = new JButton("Gửi lên hệ thống");
        sendBtn.setPreferredSize(new Dimension(160, 30));
        sendBtn.setBackground(new Color(100, 100, 255)); // Customize the color as needed
        sendBtn.setForeground(Color.WHITE);

        detailPanel = new JPanel();
        detailPanel.setLayout(new BorderLayout());
        detailPanel.setOpaque(true);
        detailPanel.setPreferredSize(new Dimension(0, 120));

        btnPanel2 = new JPanel(new GridBagLayout());
        btnPanel2.setPreferredSize(new Dimension(150, 0));
        btnPanel2.setOpaque(false);
        GridBagConstraints gbcShowBtn = new GridBagConstraints();
        gbcShowBtn.gridx = 0;
        gbcShowBtn.gridy = 0;
        gbcShowBtn.insets = new Insets(5, 0, 5, 10);

        GridBagConstraints gbcExportBtn = new GridBagConstraints();
        gbcExportBtn.gridx = 0;
        gbcExportBtn.gridy = 1;
        gbcExportBtn.insets = new Insets(5, 0, 5, 10);

        GridBagConstraints gbcsendBtn = new GridBagConstraints();
        gbcsendBtn.gridx = 0;
        gbcsendBtn.gridy = 2; // Set it to row 2, just below filterBtn (which is in row 1)
        gbcsendBtn.insets = new Insets(5, 0, 5, 10);

        ///////////////
        main_detailPanel = new JPanel();
        main_detailPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 50));

        JLabel a1 = new JLabel("Nhập thay đổi điểm:    ");
        outputDiem = new JTextField(5);
        a1.setPreferredSize(new Dimension(200, 30));
        outputDiem.setPreferredSize(new Dimension(200, 30));
        Font font = a1.getFont();
        float fontSize = font.getSize() + 5; // Increase the font size by 5 points (adjust as needed)
        a1.setFont(font.deriveFont(fontSize));
        main_detailPanel.add(a1);
        main_detailPanel.add(outputDiem);
        detailPanel.add(main_detailPanel);

        /////////
        btnPanel.add(filterBtn, gbcExportBtn);
        btnPanel.add(sendBtn, gbcsendBtn);

        totalPanel.add(l2);
        totalPanel.add(s);

        radioPanel.add(b3);
        radioPanel.add(b1);
        radioPanel.add(b4);
        radioPanel.add(b5);
        radioPanel.add(b6);

        dropdownPanel.add(inputID);
        dropdownPanel.add(optionLop);
        dropdownPanel.add(optionHe);
        dropdownPanel.add(optionHocky);
        dropdownPanel.add(optionNam);

        selectPanel.add(l1);
        selectPanel.add(radioPanel);
        selectPanel.add(dropdownPanel);
        selectPanel.add(totalPanel);

        topPanel.add(selectPanel, BorderLayout.CENTER);
        topPanel.add(btnPanel, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);

        btnPanel2.add(delBtn, gbcExportBtn);
        btnPanel2.add(editBtn, gbcShowBtn);

        detailPanel.add(btnPanel2, BorderLayout.EAST);
        contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setOpaque(true);
        contentPanel.add(initTable(), BorderLayout.CENTER);
        loaddatatoTable();
        contentPanel.add(detailPanel, BorderLayout.NORTH);
        add(contentPanel);
        setVisible(true);

        checkStatus();
        filterBtn.addActionListener(new FilterBtnListener());
        
    }

    public void checkStatus() {
    // Remove all existing listeners before adding new ones
    removeAllListeners();

    if (StatusDiem == 1) {  // StatusDiem == 1, meaning points are already submitted
        outputDiem.setEditable(false);  // Disable editing
        // Add the same listener for both edit and delete buttons
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Không thể thay đổi điểm sau khi đã gửi lên hệ thống");
            }
        };
        
        // Add listener to edit and delete buttons
        editBtn.addActionListener(listener);
        delBtn.addActionListener(listener);

        // Disable the send button
        sendBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendBtn.setEnabled(false);
            }
        });

    } else {  // StatusDiem != 1, meaning points are still editable
        outputDiem.setEditable(true);  // Allow editing

        // Add specific listeners for edit, delete, and send actions
        editBtn.addActionListener(new EditBtnListener());
        delBtn.addActionListener(new DelBtnListener());

        sendBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(
                    null,
                    "Tài khoản này sẽ không thể thay đổi điểm sau khi gửi lên hệ thống!",
                    "Bạn có chắc muốn tiếp tục?",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.PLAIN_MESSAGE
                );

                if (result == JOptionPane.YES_OPTION) {
                    StatusDiem = 1;
                    checkStatus();  // Re-check status after submission
                }
            }
        });
    }
}

// Method to remove all ActionListeners from buttons
private void removeAllListeners() {
    // Remove all ActionListeners from edit button
    for (ActionListener al : editBtn.getActionListeners()) {
        editBtn.removeActionListener(al);
    }
    
    // Remove all ActionListeners from delete button
    for (ActionListener al : delBtn.getActionListeners()) {
        delBtn.removeActionListener(al);
    }

    // Remove all ActionListeners from send button
    for (ActionListener al : sendBtn.getActionListeners()) {
        sendBtn.removeActionListener(al);
    }
}


    public JScrollPane initTable() {
        t = new JTable();
        t.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        scrollPane = new JScrollPane(t);
        scrollPane.setPreferredSize(new Dimension(0, 320));

        String[] headers = { "ID", "Tên HS", "Lớp", "Môn Học", "Hệ Điểm", "Điểm", "Học Kỳ", "ĐiểmTB HK", "Năm Học",
                "ĐiểmTB NH" };
        int editableColumnIndex = 5; // Điểm column
        tblModel = new NonEditableTableModel(headers, 0, editableColumnIndex);
        t.setModel(tblModel);

        // Thiết lập kích thước cột
        t.getColumnModel().getColumn(0).setPreferredWidth(60);
        t.getColumnModel().getColumn(1).setPreferredWidth(180);
        t.getColumnModel().getColumn(2).setPreferredWidth(60);
        t.getColumnModel().getColumn(3).setPreferredWidth(70);
        t.getColumnModel().getColumn(4).setPreferredWidth(70);
        t.getColumnModel().getColumn(5).setPreferredWidth(80);
        t.getColumnModel().getColumn(6).setPreferredWidth(60);
        t.getColumnModel().getColumn(7).setPreferredWidth(60);
        t.getColumnModel().getColumn(8).setPreferredWidth(120);
        t.setRowHeight(40);
        JTableHeader header = t.getTableHeader();
        header.setPreferredSize(new Dimension(header.getPreferredSize().width, 40));
        header.setBackground(new Color(31, 28, 77));
        header.setForeground(Color.WHITE);
        header.setFont(header.getFont().deriveFont(Font.BOLD, 12));

        ((DefaultTableCellRenderer) t.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < tblModel.getColumnCount(); i++) {
            t.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        t.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    tableMouseClicked(evt);
                } catch (ParseException ex) {
                    Logger.getLogger(QuanLiHocSinh.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        return scrollPane;
    }

    public void loaddatatoTable() {
        tblModel.setRowCount(0);

        dshs = hsbus.getList();
        dskq = kqbus.getList();
        dsmon = mhbus.getList();
        dsct = ctbus.getList();
        dsdtb = dtbbus.getList();
        dshk = hkbus.getList();
        dsnh = nhbus.getList();
        String idmon = pcbus.get(magiaovien).getMonHocID();
        System.out.println("id mon GV :" + idmon);
        for (HocSinhDTO hs : dshs) {
            for (NamHocDTO nh : dsnh) {
                String idnamhoc = nh.getNamHocID();//2024202502
                String idhs = hs.getHocSinhID();
                String idlop = plbus.get(idhs, idnamhoc) != null ? plbus.get(idhs, idnamhoc).getLopID() : "";
                
                    for (int heso = 1; heso < 4; heso++) {
                        String idHocKy = nh.getHocKy();
                        String idNamHoc = nh.getNamHocID();
                        String idDiemHocKy = ctbus.get(idhs, idNamHoc, idHocKy, idmon, heso) != null
                                ? String.valueOf(ctbus.get(idhs, idNamHoc, idHocKy, idmon, heso).getDiem())
                                : "";
                        String idDiemTrungBinhHocKy = dtbbus.get(idhs, idNamHoc, idHocKy) != null
                                ? String.valueOf(dtbbus.get(idhs, idNamHoc, idHocKy).getDiemTrungBinh())
                                : "";
                        String idDiemTrungBinhNam = kqbus.get(idhs, idNamHoc) != null
                                ? String.valueOf(kqbus.get(idhs, idNamHoc).getDiemTrungBinhNam())
                                : "";
                        String tenl = lopbus.get(idlop) != null ? lopbus.get(idlop).getTenLop() : "";
                        String[] rowData = new String[] {
                                idhs,
                                hsbus.get(idhs).getTenHocSinh(),
                                tenl,
                                mhbus.get(idmon).getTenMonHoc(),
                                String.valueOf(heso),
                                idDiemHocKy,
                                hkbus.get(idHocKy).getTenHocKy(),
                                idDiemTrungBinhHocKy,
                                nhbus.get(idnamhoc).getNamHocBatDau() + "-" + nhbus.get(idnamhoc).getNamHocKetThuc(),
                                idDiemTrungBinhNam
                        };
                        tblModel.addRow(rowData);
                        
                    }
            }
        }
        tblModel.fireTableDataChanged();
        s.setText(String.valueOf(dshs.size()));
    }

    private class FilterBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            tblModel.setRowCount(0);
            String id_hs = inputID.getText().trim().toUpperCase();
            String monhoc = mhbus.get(pcbus.get(magiaovien).getMonHocID()).getTenMonHoc();
            String tenlop = (String) optionLop.getSelectedItem();

            // không có table hệ số =))))
            int hediem, i;
            String selectedItem = (String) optionHe.getSelectedItem();
            if (selectedItem.equals("Tất cả")) {
                hediem = 4;
                i = 1;
            } else {
                char secondChar = selectedItem.charAt(1);
                i = Character.getNumericValue(secondChar);
                hediem = i + 1;
            }

            String hocky = (String) optionHocky.getSelectedItem();
            String namhoc = (String) optionNam.getSelectedItem();

            dshs = hsbus.search(id_hs, null, null, null, null, null, null);
            dsnh = nhbus.search(null, namhoc);
            dslop = lopbus.search(null, tenlop);
            dshk = hkbus.search(null, hocky);
            dsmon = mhbus.search(null, monhoc);

            for (HocSinhDTO hs : dshs) {
                System.out.println("loc hs");

                for (NamHocDTO nh : dsnh) {

                    for (LopDTO lop : dslop) {

                        String idnamhoc = nh.getNamHocID();
                        String idhs = hs.getHocSinhID();
                        String idlop = lop.getLopID();
                        if (plbus.get(idhs, idnamhoc).getLopID().equals(idlop)) {
                            for (HocKyDTO hk : dshk) {

                                String idhk = hk.getHocKyID();
                                for (MonHocDTO mh : dsmon) {
                                    String idmon = mh.getMonHocID();
                                    for (int heso = i; heso < hediem; heso++) {
                                        String Diem = ctbus.get(idhs, idnamhoc, idhk, idmon, heso) != null
                                                ? String.valueOf(ctbus.get(idhs, idnamhoc, idhk, idmon, heso).getDiem())
                                                : "";
                                        String diemTrungBinhHocKy = dtbbus.get(idhs, idnamhoc, idhk) != null
                                                ? String.valueOf(dtbbus.get(idhs, idnamhoc, idhk).getDiemTrungBinh())
                                                : "";
                                        String diemTrungBinhNam = kqbus.get(idhs, idnamhoc) != null
                                                ? String.valueOf(kqbus.get(idhs, idnamhoc).getDiemTrungBinhNam())
                                                : "";
                                        System.out.println("IDHS: " + idhs);

                                        String[] rowData = new String[] {
                                                idhs,
                                                hsbus.get(idhs).getTenHocSinh(),
                                                lopbus.get(idlop).getTenLop(),
                                                mhbus.get(idmon).getTenMonHoc(),
                                                String.valueOf(heso),
                                                Diem,
                                                hkbus.get(idhk).getTenHocKy(),
                                                diemTrungBinhHocKy,
                                                nhbus.get(idnamhoc).getNamHocBatDau() + "-"
                                                        + nhbus.get(idnamhoc).getNamHocKetThuc(),
                                                diemTrungBinhNam
                                        };
                                        tblModel.addRow(rowData);
                                    }
                                }
                            }
                        }
                    }

                }
            }
            tblModel.fireTableDataChanged();
            int count = countUniqueIDs(tblModel);
            s.setText(String.valueOf(count));
            if (tblModel.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Không có dữ liệu ");
            }
        }
    }

    private void tableMouseClicked(java.awt.event.MouseEvent e) throws ParseException {
        checkStatus();

        outputDiem.setEditable(true);
        int row = t.getSelectedRow();
        outputID = (String) t.getValueAt(row, 0);
        outputTenHS = (String) t.getValueAt(row, 1);
        outputLop = (String) t.getValueAt(row, 2);
        outputMon = (String) t.getValueAt(row, 3);
        outputHeid = (String) t.getValueAt(row, 4);
        String diem = (String) t.getValueAt(row, 5);
        outputHK = (String) t.getValueAt(row, 6);
        outputDiemhk = (String) t.getValueAt(row, 7);
        outputNam = (String) t.getValueAt(row, 8);
        outputDiem.setText(diem);
        outputDiemcanam = (String) t.getValueAt(row, 9);

        String hockyid = outputHK.substring(outputHK.length() - 1);
        //sua diem hoc ky truoc
        if(!nhbus.isCurrentYear(outputNam, hockyid)){
            removeAllListeners();
            editBtn.addActionListener(new CannotEditListener());
            delBtn.addActionListener(new CannotEditListener());
            outputDiem.setEditable(false);
        }

    }

    private class EditBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (outputID == null) {
                JOptionPane.showMessageDialog(null, "Chưa chọn thông tin", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String diem = outputDiem.getText();
            if (diem.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập điểm", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!(diem.matches("^-?\\d+(\\.\\d+)?$"))) {
                JOptionPane.showMessageDialog(null, "Sai format điểm, vui lòng nhập lại", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (Float.parseFloat(diem) > 10.0 || Float.parseFloat(diem) < 0.0) {
                JOptionPane.showMessageDialog(null, "Quá số điểm tối đa hoặc số điểm âm, vui lòng nhập lại", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            int result = JOptionPane.showConfirmDialog(null,
                    "Bạn có chắc muốn sửa điểm của " + outputID,
                    "Xác nhận",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);

            if (result == JOptionPane.YES_OPTION) {
                updateData();
            } else {
                return;
            }
        }
    }
    private class CannotEditListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null, "Chỉ có thể thay đổi điểm ở Học kỳ, Năm học hiện tại");
        }
    }

    public void updateData() {
        System.out.println("update ................");
        String idhk = hkbus.getByName(outputHK).getHocKyID();
        System.out.println(idhk);

        String idmon = mhbus.getByName(outputMon).getMonHocID();
        System.out.println(idmon);

        String idnamhoc = nhbus.getByName(outputNam).getNamHocID();
        System.out.println(idnamhoc);

        String idhs = outputID;
        System.out.println(idhs);
        int idhe = Integer.parseInt(outputHeid);
        System.out.println(idhe);
        Float diemHK = (outputDiemhk == null || outputDiemhk.equals("")) ? -1 : Float.parseFloat(outputDiemhk);
        System.out.println(diemHK);
        Float diemCanam = (outputDiemcanam == null || outputDiemcanam.equals("")) ? -1
                : Float.parseFloat(outputDiemcanam);
        System.out.println(diemCanam);
        Float diem = (outputDiem.getText() == null || outputDiem.getText().equals("")) ? -1
                : Float.parseFloat(outputDiem.getText());
        System.out.println(diem);
        String tenhs = outputTenHS;
        String lop = outputLop;

        String hocluc = kqbus.get(idhs, idnamhoc) != null ? kqbus.get(idhs, idnamhoc).getHocLuc() : "";// checknull
        String hanhkiem = kqbus.get(idhs, idnamhoc) != null ? kqbus.get(idhs, idnamhoc).getHanhKiem() : "Tốt";//
        String ketqua = kqbus.get(idhs, idnamhoc) != null ? kqbus.get(idhs, idnamhoc).getKetQua() : "";//

        KQ_HocSinhCaNamDTO diemnamhoc = new KQ_HocSinhCaNamDTO(idhs, idnamhoc, hocluc, hanhkiem, diemCanam, ketqua);

        ChiTietDiemDTO ctd = new ChiTietDiemDTO(idhs, idmon, idhk, idhe, idnamhoc, diem);
        DTB_HocKyDTO dtb = new DTB_HocKyDTO(idhs, idhk, idnamhoc, diemHK);

        if (tinhdiemHK(idhs, idhk, idnamhoc) > 0) {
            dtb.setDiemTrungBinh(tinhdiemHK(idhs, idhk, idnamhoc));
        }

        if (tinhDiemCN(idhs, idnamhoc) > 0) {
            diemnamhoc.setDiemTrungBinhNam(tinhDiemCN(idhs, idnamhoc));

            // update hocluc

            if (tinhDiemCN(idhs, idnamhoc) < 5) {
                diemnamhoc.setHocLuc("Yếu");
            } else if (tinhDiemCN(idhs, idnamhoc) < 8.5 && tinhDiemCN(idhs, idnamhoc) >= 7.0) {
                diemnamhoc.setHocLuc("Khá");
            } else if (tinhDiemCN(idhs, idnamhoc) >= 8.5) {
                diemnamhoc.setHocLuc("Giỏi");
            } else {
                diemnamhoc.setHocLuc("Trung Bình");
            }
        }

        // Check if any diem value is -1
        boolean anyNegative = (diem == -1 || diemHK == -1 || diemCanam == -1);

        // Convert diem values to empty string if any is -1
        String diemString = (anyNegative && diem == -1) ? "" : String.valueOf(diem);
        String diemHKString = (anyNegative && diemHK == -1) ? "" : String.valueOf(diemHK);
        String diemCanamString = (anyNegative && diemCanam == -1) ? "" : String.valueOf(diemCanam);

        // Create rowData with adjusted diem values
        Object[] rowData = { idhs, tenhs, lop, mhbus.get(idmon).getTenMonHoc(), idhe, diemString,
                hkbus.get(idhk).getTenHocKy(),
                diemHKString, outputNam, diemCanamString };

        int row = t.getSelectedRow();
        tblModel.removeRow(row);
        tblModel.addRow(rowData);
        /// check lai update db;
        System.out.println("check db------------");
        ctbus.set(ctd);
        System.out.println(ctd);
        dtbbus.set(dtb);
        System.out.println(dtb);
        kqbus.set(diemnamhoc);
        System.out.println(diemnamhoc);
        outputDiem.setText("");
        JOptionPane.showMessageDialog(null, "Cập nhật thành công");
        resetOutput();
    }

    private class DelBtnListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (outputID == null) {
                JOptionPane.showMessageDialog(null, "Chọn thông tin trước khi nhập điểm", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            int i = JOptionPane.showConfirmDialog(null, " Bạn có muốn xóa điểm này của" + outputID + " ?",
                    "Confirmation", JOptionPane.YES_NO_OPTION);

            if (i == JOptionPane.YES_OPTION) {

                deleteData();
                resetOutput();
            } else {
                return;
            }
        }
    }

    public void deleteData() {
        outputDiemhk = null;
        outputDiemcanam = null;
        System.out.println("delete()");
        String idhk = hkbus.getByName(outputHK).getHocKyID();
        System.out.println(idhk);

        String idmon = mhbus.getByName(outputMon).getMonHocID();
        System.out.println(idmon);

        String idnamhoc = nhbus.getByName(outputNam).getNamHocID();
        System.out.println(idnamhoc);

        String idhs = outputID;
        System.out.println(idhs);
        int idhe = Integer.parseInt(outputHeid);
        System.out.println(idhe);
        Float diemHK = (outputDiemhk == null || outputDiemhk.equals("")) ? -1 : Float.parseFloat(outputDiemhk);// sử lý
                                                                                                               // hàm
                                                                                                               // set
                                                                                                               // với -1
        System.out.println(diemHK);
        Float diemCanam = (outputDiemcanam == null || outputDiemcanam.equals("")) ? -1
                : Float.parseFloat(outputDiemcanam);
        System.out.println(diemCanam);
        Float diem = (outputDiem.getText() == null || outputDiem.getText().equals("")) ? -1
                : Float.parseFloat(outputDiem.getText());
        System.out.println(diem);

        String hocluc = kqbus.get(idhs, idnamhoc) != null ? kqbus.get(idhs, idnamhoc).getHocLuc() : "";// check null
        String hanhkiem = kqbus.get(idhs, idnamhoc) != null ? kqbus.get(idhs, idnamhoc).getHanhKiem() : "Tốt";//
        String ketqua = kqbus.get(idhs, idnamhoc) != null ? kqbus.get(idhs, idnamhoc).getKetQua() : "";//
        KQ_HocSinhCaNamDTO diemnamhoc = new KQ_HocSinhCaNamDTO(idhs, idnamhoc, hocluc, hanhkiem, diemCanam, ketqua);

        ChiTietDiemDTO ctd = new ChiTietDiemDTO(idhs, idmon, idhk, idhe, idnamhoc, diem);
        DTB_HocKyDTO dtb = new DTB_HocKyDTO(idhs, idhk, idnamhoc, diemHK);

        System.out.println("check db delete------------");
        ctbus.delete(ctd);
        System.out.println(ctd);
        dtbbus.delete(dtb);
        System.out.println(dtb);
        kqbus.delete(diemnamhoc);
        System.out.println(diemnamhoc);

        Object[] rowData = { outputID, outputTenHS, outputLop, outputMon, outputHeid, "", outputHK, "", outputNam, "" };
        int row = t.getSelectedRow();
        tblModel.removeRow(row);
        tblModel.addRow(rowData);

        outputDiem.setText("");
        JOptionPane.showMessageDialog(null, "Cập nhật thành công");
        resetOutput();
    }

    private int countUniqueIDs(DefaultTableModel model) {
        int rowCount = model.getRowCount();
        int count = 0;
        HashSet<String> uniqueIDs = new HashSet<>();

        for (int i = 0; i < rowCount; i++) {
            String id = (String) model.getValueAt(i, 0); // Assuming ID is in the first column
            if (!uniqueIDs.contains(id)) {
                uniqueIDs.add(id);
                count++;
            }
        }
        return count;
    }

    public Float tinhdiemHK(String idhs, String idhk, String idnamhoc) {
        Float dhk = (float) 0.0;

        hkbus.get(idhk);

        nhbus.get(idnamhoc);
        hsbus.get(idhs);

        dshs = hsbus.getList();
        dskq = kqbus.getList();
        dsmon = mhbus.getList();
        dsct = ctbus.getList();
        dsdtb = dtbbus.getList();
        dshk = hkbus.getList();
        dsnh = nhbus.getList();

        Float dtbmon = (float) 0.0;
        Float tongmon = (float) 0.0;
        for (MonHocDTO mon : dsmon) {
            dtbmon = (float) 0.0;
            for (int heso = 1; heso < 4; heso++) {
                String idmon = mon.getMonHocID();
                if (ctbus.get(idhs, idnamhoc, idhk, idmon, heso) == null)
                    return (float) -1;
                Float diem = (ctbus.get(idhs, idnamhoc, idhk, idmon, heso) != null) ||
                        (ctbus.get(idhs, idnamhoc, idhk, idmon, heso).getDiem() < 0.0)
                                ? ctbus.get(idhs, idnamhoc, idhk, idmon, heso).getDiem()
                                : (float) -1;
                if (String.valueOf(diem) != null &&
                        diem >= (float) 0.0) {
                    System.out.println(mhbus.get(idmon));

                    System.out.println(" he so---" + heso);

                    tongmon += diem * heso;
                    System.out.println(tongmon);

                }
            }
            dtbmon += tongmon / 6;
            tongmon = (float) 0.0;
            dhk += dtbmon;

        }
        dhk /= (dsmon.size());

        if (dhk == 0.0)
            return (float) -1;

        return dhk;
    }

    public Float tinhDiemCN(String idhs, String idnh) {

        if (tinhdiemHK(idhs, "1", idnh) > 0 && tinhdiemHK(idhs, "2", idnh) > 0) {
            return (float) (tinhdiemHK(idhs, "1", idnh) + tinhdiemHK(idhs, "2", idnh)) / 2;
        }
        return (float) -1;
    }
    
    public void resetOutput() {
        outputID = null;
        outputHeid = null;
        outputMon = null;
        outputHK = null;
        outputNam = null;
        outputDiemhk = null;
        outputDiemcanam = null;
        outputTenHS = null;
        outputLop = null;
    }

    public static void main(String[] args) {
        new GVQuanLyDiem("GV2");
    }
}