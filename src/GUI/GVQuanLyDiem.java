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
import BUS.GiaoVienBUS;
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
    String magiaovien;
    private JFrame f;
    private JPanel topPanel, radioPanel, dropdownPanel, selectPanel, totalPanel, btnPanel, btnPanel2, contentPanel,
            detailPanel, main_detailPanel;
            private JLabel b1, b3, b4, b5, b6;
    private JComboBox<String> optionLop, optionHe, optionHocky, optionNam;
    private JTextField s, inputID, txtDiem1, txtDiem2, txtDiem3;
    private JLabel l1, l2;
    private JButton filterBtn, editBtn, delBtn, submitBtn;
    private static NonEditableTableModel tblModel;
    private JScrollPane scrollPane;
    private JTable t;
    private static String outputID, outputMon, outputHK, outputNam;
    
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

    PhanCongBUS pcbus = new PhanCongBUS(1);
    PhanLopBUS plbus = new PhanLopBUS(1);
    LopBUS lopbus = new LopBUS(1);
    HocSinhBUS hsbus = new HocSinhBUS(1);
    MonHocBUS mhbus = new MonHocBUS(1);
    ChiTietDiemBUS ctbus = new ChiTietDiemBUS(1);
    DTB_HocKyBUS dtbbus = new DTB_HocKyBUS(1);
    HocKyBUS hkbus = new HocKyBUS(1);
    KQ_HocSinhCaNamBUS kqbus = new KQ_HocSinhCaNamBUS(1);
    NamHocBUS nhbus = new NamHocBUS(1);
    GiaoVienBUS gvbus = new GiaoVienBUS();
    int width, height;

    public GVQuanLyDiem(int width, int height, String magiaovien) {
    // public GVQuanLyDiem(String magiaovien) {

        this.magiaovien = magiaovien;
        this.width = width;
        this.height = height;
        new JFrame();
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

        submitBtn = new JButton("Gửi lên hệ thống");
        submitBtn.setPreferredSize(new Dimension(160, 30));
        submitBtn.setBackground(new Color(100, 100, 255)); 
        submitBtn.setForeground(Color.WHITE);

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

        GridBagConstraints gbcsubmitBtn = new GridBagConstraints();
        gbcsubmitBtn.gridx = 0;
        gbcsubmitBtn.gridy = 2; // Set it to row 2, just below filterBtn (which is in row 1)
        gbcsubmitBtn.insets = new Insets(5, 0, 5, 10);

        main_detailPanel = new JPanel();
        main_detailPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 50));  // Set layout with space between components

        // Labels for the text fields
        JLabel lblDiem1 = new JLabel("Điểm hệ 1: ");
        JLabel lblDiem2 = new JLabel("Điểm hệ 2: ");
        JLabel lblDiem3 = new JLabel("Điểm hệ 3: ");

        // Text fields for entering scores
        txtDiem1 = new JTextField(5);
        txtDiem2 = new JTextField(5);
        txtDiem3 = new JTextField(5);

        // Set preferred sizes for labels and text fields
        lblDiem1.setPreferredSize(new Dimension(100, 30));
        txtDiem1.setPreferredSize(new Dimension(200, 30));

        lblDiem2.setPreferredSize(new Dimension(100, 30));
        txtDiem2.setPreferredSize(new Dimension(200, 30));

        lblDiem3.setPreferredSize(new Dimension(100, 30));
        txtDiem3.setPreferredSize(new Dimension(200, 30));

        // Font settings for labels (optional, to increase the font size)
        Font labelFont = lblDiem1.getFont().deriveFont(Font.PLAIN, 16);
        lblDiem1.setFont(labelFont);
        lblDiem2.setFont(labelFont);
        lblDiem3.setFont(labelFont);

        // Add components to the panel, keeping them aligned horizontally
        main_detailPanel.add(lblDiem1);
        main_detailPanel.add(txtDiem1);

        main_detailPanel.add(lblDiem2);
        main_detailPanel.add(txtDiem2);

        main_detailPanel.add(lblDiem3);
        main_detailPanel.add(txtDiem3);



        detailPanel.add(main_detailPanel);

        /////////
        btnPanel.add(filterBtn, gbcExportBtn);
        btnPanel.add(submitBtn, gbcsubmitBtn);

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

        //btnPanel2.add(delBtn, gbcExportBtn);
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

        checkSubmit();
        // filterBtn.addActionListener(new FilterBtnListener());
    }

    public void checkSubmit() {
        // Remove all existing listeners before adding new ones
        removeAllListeners();
        
        if (gvbus.getSubmit(magiaovien) == 1) {  // isSubmit == 1, meaning points are already submitted
            txtDiem1.setEditable(false);
            txtDiem2.setEditable(false);
            txtDiem3.setEditable(false);
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

            submitBtn.setEnabled(false);
            

        } else {  // isSubmit != 1, meaning points are still editable
            if(checkEmptyGrade()){
                ActionListener listener = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ điểm trước khi gửi");
                    }
                };
                submitBtn.addActionListener(listener);
            }

            txtDiem1.setEditable(true);  // Allow editing
            txtDiem2.setEditable(true);
            txtDiem3.setEditable(true);
            
            removeAllListeners();
            // Add specific listeners for edit, delete, and send actions
            editBtn.addActionListener(new EditBtnListener());
            delBtn.addActionListener(new DelBtnListener());

            submitBtn.addActionListener(new ActionListener() {
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
                        gvbus.setSubmit(magiaovien, 1); ;
                        checkSubmit();  // Re-check status after submission
                    }
                    return;
                }
            });
        }
    }

    // Method to remove all ActionListeners from buttons
    private void removeAllListeners() {
        for (ActionListener al : editBtn.getActionListeners()) {
            editBtn.removeActionListener(al);
        }
        for (ActionListener al : delBtn.getActionListeners()) {
            delBtn.removeActionListener(al);
        }
        for (ActionListener al : submitBtn.getActionListeners()) {
            submitBtn.removeActionListener(al);
        }
    }

    public JScrollPane initTable() {
        t = new JTable();
        t.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        scrollPane = new JScrollPane(t);
        scrollPane.setPreferredSize(new Dimension(0, 320));

        String[] headers = {"ID", "Tên HS", "Lớp", "Môn Học", "Điểm hệ 1", "Điểm hệ 2", "Điểm hệ 3","Học Kỳ","ĐTB môn HK", "Năm Học"};
        int editableColumnIndex = 5; // Điểm column
        tblModel = new NonEditableTableModel(headers, 0, editableColumnIndex);
        t.setModel(tblModel);

        
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

    //check neu co diem chua nhap
    public static boolean checkEmptyGrade() {
        int dtbMonColumnIndex = 8; // 9th column, 0-based index
        for (int row = 0; row < tblModel.getRowCount(); row++) {
            Object value = tblModel.getValueAt(row, dtbMonColumnIndex);
            if (value instanceof Number && ((Number) value).doubleValue() == 0.0) {
                return true; // Found a 0.0 value in the 9th column
            }
        }
        return false; // No 0.0 value found
    }
    public void loaddatatoTable() {
        int sHS=0;
        tblModel.setRowCount(0);

        dspc = pcbus.search(magiaovien, null);
        if(dspc ==null) {
            JOptionPane.showMessageDialog(null, "TK chưa dc tạo phân công để nhập điểm");
            return;
        }
        String idnamhoc = nhbus.getCurrYearId();
        String idhk = nhbus.get(idnamhoc).getHocKy();
        if(idnamhoc == null){
            JOptionPane.showMessageDialog(null, "Hệ thống chưa được tạo năm học");
            return;
        }
        
        String idmon = gvbus.getIdMon(magiaovien);
        for(PhanCongDTO pc: dspc){
            String lopid = pc.getLopID();
            dspl = plbus.search(null, lopid, idnamhoc);
            for (PhanLopDTO pl: dspl){
                String idhs = pl.getHocSinhID();
//{"ID", "Tên HS", "Lớp", "Môn Học", "Điểm hệ 1", "Điểm hệ 2", "Điểm hệ 3","Học Kỳ","ĐTB môn HK", "Năm Học"};
                
                if(ctbus.get(idhs, idnamhoc, idhk, idmon) == null){
                    ChiTietDiemDTO diemmonhoc = new ChiTietDiemDTO(idhs, idmon,idhk,  idnamhoc);
                    System.out.println("add ctd");
                    ctbus.add(diemmonhoc);
                }
                ChiTietDiemDTO diemmonhoc = ctbus.get(idhs, idnamhoc, idhk, idmon);
                String[] rowData = new String[]{
                    idhs,
                    hsbus.get(idhs).getTenHocSinh(),
                    lopbus.get(lopid).getTenLop(),
                    mhbus.get(idmon).getTenMonHoc(),
                    String.valueOf(diemmonhoc.getDiem1()),
                    String.valueOf(diemmonhoc.getDiem2()),
                    String.valueOf(diemmonhoc.getDiem3()),
                    hkbus.get(idhk).getTenHocKy(),
                    String.valueOf(diemmonhoc.getDtbMon()),
                    nhbus.get(idnamhoc).getNamHocBatDau() + "-" + nhbus.get(idnamhoc).getNamHocKetThuc()
                };
                sHS++;
                tblModel.addRow(rowData);
            }
        }
        s.setText(String.valueOf(sHS));
    }

    // private class FilterBtnListener implements ActionListener {
    //     @Override
    //     public void actionPerformed(ActionEvent e) {
    //         tblModel.setRowCount(0);
    //         String id_hs = inputID.getText().trim().toUpperCase();
    //         String monhoc = gvbus.getIdMon(magiaovien);
    //         String tenlop = (String) optionLop.getSelectedItem();

    //         // không có table hệ số =))))
    //         int hediem, i;
    //         String selectedItem = (String) optionHe.getSelectedItem();
    //         if (selectedItem.equals("Tất cả")) {
    //             hediem = 4;
    //             i = 1;
    //         } else {
    //             char secondChar = selectedItem.charAt(1);
    //             i = Character.getNumericValue(secondChar);
    //             hediem = i + 1;
    //         }

    //         String hocky = (String) optionHocky.getSelectedItem();
    //         String namhoc = (String) optionNam.getSelectedItem();

    //         dshs = hsbus.search(id_hs, null, null, null, null, null, null);
    //         dsnh = nhbus.search(null, namhoc);
    //         dslop = lopbus.search(null, tenlop);
    //         dshk = hkbus.search(null, hocky);
    //         // dsmon = mhbus.search(null, monhoc);

    //         for (HocSinhDTO hs : dshs) {
    //             System.out.println("loc hs");

    //             for (NamHocDTO nh : dsnh) {

    //                 for (LopDTO lop : dslop) {

    //                     String idnamhoc = nh.getNamHocID();
    //                     String idhs = hs.getHocSinhID();
    //                     String idlop = lop.getLopID();
    //                     if (plbus.get(idhs, idnamhoc).getLopID().equals(idlop)) {
    //                         for (HocKyDTO hk : dshk) {

    //                             String idhk = hk.getHocKyID();
    //                             for (MonHocDTO mh : dsmon) {
    //                                 String idmon = mh.getMonHocID();
    //                                 for (int heso = i; heso < hediem; heso++) {
    //                                     String Diem = ctbus.get(idhs, idnamhoc, idhk, idmon, heso) != null
    //                                             ? String.valueOf(ctbus.get(idhs, idnamhoc, idhk, idmon, heso).getDiem())
    //                                             : "";
    //                                     String diemTrungBinhHocKy = dtbbus.get(idhs, idnamhoc, idhk) != null
    //                                             ? String.valueOf(dtbbus.get(idhs, idnamhoc, idhk).getDiemTrungBinh())
    //                                             : "";
    //                                     String diemTrungBinhNam = kqbus.get(idhs, idnamhoc) != null
    //                                             ? String.valueOf(kqbus.get(idhs, idnamhoc).getDiemTrungBinhNam())
    //                                             : "";
    //                                     System.out.println("IDHS: " + idhs);

    //                                     String[] rowData = new String[] {
    //                                             idhs,
    //                                             hsbus.get(idhs).getTenHocSinh(),
    //                                             lopbus.get(idlop).getTenLop(),
    //                                             mhbus.get(idmon).getTenMonHoc(),
    //                                             String.valueOf(heso),
    //                                             Diem,
    //                                             hkbus.get(idhk).getTenHocKy(),
    //                                             diemTrungBinhHocKy,
    //                                             nhbus.get(idnamhoc).getNamHocBatDau() + "-"
    //                                                     + nhbus.get(idnamhoc).getNamHocKetThuc(),
    //                                             diemTrungBinhNam
    //                                     };
    //                                     tblModel.addRow(rowData);
    //                                 }
    //                             }
    //                         }
    //                     }
    //                 }

    //             }
    //         }
    //         tblModel.fireTableDataChanged();
    //         int count = countUniqueIDs(tblModel);
    //         s.setText(String.valueOf(count));
    //         if (tblModel.getRowCount() == 0) {
    //             JOptionPane.showMessageDialog(null, "Không có dữ liệu ");
    //         }
    //     }
    // }

    private void tableMouseClicked(java.awt.event.MouseEvent e) throws ParseException {
        checkSubmit();

        txtDiem1.setEditable(true);
        txtDiem2.setEditable(true);
        txtDiem3.setEditable(true);
            
        int row = t.getSelectedRow();
        outputID = (String) t.getValueAt(row, 0);
        outputMon = (String) t.getValueAt(row, 3);
        String diem1 = (String) t.getValueAt(row, 4);
        String diem2 = (String) t.getValueAt(row, 5);
        String diem3 = (String) t.getValueAt(row, 6);
        outputHK = (String) t.getValueAt(row, 7);
        outputNam = (String) t.getValueAt(row, 9);
        txtDiem1.setText(diem1);
        txtDiem2.setText(diem2);
        txtDiem3.setText(diem3);

        String hockyid = outputHK.substring(outputHK.length() - 1);
        //khi sua diem o hoc ky truoc
        if(!nhbus.isCurrentSem(outputNam, hockyid)){
            removeAllListeners();
            editBtn.addActionListener(new CannotEditListener());
            delBtn.addActionListener(new CannotEditListener());
            txtDiem1.setEditable(false);
        }

    }
    private class CannotEditListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null, "Chỉ có thể thay đổi điểm ở Học kỳ, Năm học hiện tại");
        }
    }

    private class EditBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (outputID == null) {
                JOptionPane.showMessageDialog(null, "Chưa chọn thông tin", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String diem = txtDiem1.getText();
            String diem2 = txtDiem2.getText();
            String diem3 = txtDiem3.getText();
            if (diem.isEmpty() && diem2.isEmpty() && diem3.isEmpty() ) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn HS để nhập điểm", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!(diem.matches("^-?\\d+(\\.\\d+)?$")) 
                || !(diem2.matches("^-?\\d+(\\.\\d+)?$")) || !(diem3.matches("^-?\\d+(\\.\\d+)?$"))) {
                JOptionPane.showMessageDialog(null, "Sai format điểm, vui lòng nhập lại", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            if ( (Double.parseDouble(diem) > 10.0 || Double.parseDouble(diem) < 0.0)
                || (Double.parseDouble(diem2) > 10.0 || Double.parseDouble(diem2) < 0.0)
                || (Double.parseDouble(diem3) > 10.0 || Double.parseDouble(diem3) < 0.0) ) {
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
    
    public void updateData() {
        System.out.println("updateData()");

        String idhs = outputID;
        String idmon = mhbus.getByName(outputMon).getMonHocID();
        double diem1 = Double.parseDouble(txtDiem1.getText());
        double diem2 = Double.parseDouble(txtDiem2.getText());
        double diem3 = Double.parseDouble(txtDiem3.getText());
        String idhk = hkbus.getByName(outputHK).getHocKyID();
        String idnamhoc = nhbus.getByName(outputNam).getNamHocID();

        ChiTietDiemDTO updatectd = ctbus.get(idhs, idnamhoc, idhk, idmon);
        updatectd.setDiem1(diem1);
        updatectd.setDiem2(diem2);
        updatectd.setDiem3(diem3);
        updatectd.calDtbMon();
        ctbus.set(updatectd);

        JOptionPane.showMessageDialog(null, "Cập nhật thành công");
        loaddatatoTable();
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

                //deleteData();
                resetOutput();
            } else {
                return;
            }
        }
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

    public void resetOutput() {
        outputID = null;
        outputMon = null;
        outputHK = null;
        outputNam = null;
    }

    // public static void main(String[] args) {
    //     new GVQuanLyDiem("GV1");
    //     System.out.println(checkEmptyGrade());
    // }

}