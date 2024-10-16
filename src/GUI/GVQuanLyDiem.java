/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;
import javax.swing.table.*;

import BUS.*;
import DTO.ChiTietDiemDTO;
import DTO.PhanCongDTO;
import DTO.PhanLopDTO;

/**
 *
 * @author PHUONG ANH
 */
public class GVQuanLyDiem extends JPanel {
    String magiaovien;
    private JPanel topPanel, radioPanel, dropdownPanel, selectPanel, totalPanel, btnPanel, btnPanel2, contentPanel,
            detailPanel, main_detailPanel;
    private JLabel b1, b3;
    private JComboBox<String> optionLop;
    private JTextField s, searchID, txtDiem1, txtDiem2, txtDiem3;
    private JLabel l1, l2;
    private JButton filterBtn, editBtn,submitBtn;
    private static NonEditableTableModel tblModel;
    TableRowSorter<NonEditableTableModel> sorter;
    private JScrollPane scrollPane;
    private JTable t;
    private static String outputID, outputMon, outputHK, outputNam;
    
    ArrayList<ChiTietDiemDTO> dsct;
    ArrayList<PhanLopDTO> dspl;
    ArrayList<PhanCongDTO> dspc;

    PhanCongBUS pcbus = new PhanCongBUS(1);
    PhanLopBUS plbus = new PhanLopBUS(1);
    LopBUS lopbus = new LopBUS(1);
    HocSinhBUS hsbus = new HocSinhBUS(1);
    MonHocBUS mhbus = new MonHocBUS(1);
    ChiTietDiemBUS ctbus = new ChiTietDiemBUS(1);
    HocKyBUS hkbus = new HocKyBUS(1);
    NamHocBUS nhbus = new NamHocBUS(1);
    GiaoVienBUS gvbus = new GiaoVienBUS();
    int width, height;

    public GVQuanLyDiem(int width, int height, String magiaovien) {
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

        l1 = new JLabel("DANH SÁCH ĐIỂM THEO PHÂN CÔNG");
        l1.setFont(new Font("Arial", Font.BOLD, 20));
        l1.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 105));

        radioPanel = new JPanel();
        radioPanel.setOpaque(false);
        radioPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 70, 0));

        b1 = new JLabel("Lớp");
        b3 = new JLabel("Mã HS");

        JLabel[] buttons = { b1, b3};
        Color color = new Color(180, 204, 227);
        for (JLabel button : buttons) {
            button.setBackground(color);
        }

        dropdownPanel = new JPanel();
        dropdownPanel.setOpaque(false);
        dropdownPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 36, 0));
        dspc = pcbus.search(magiaovien, null);
        ArrayList<String> c1 = new ArrayList<>();
        c1.add("Tất cả");
        for(PhanCongDTO pc: dspc){
            String tenlop = lopbus.get(pc.getLopID()).getTenLop();
            c1.add(tenlop);
        }
        optionLop = new JComboBox<>(c1.toArray(new String[0]));
        searchID = new JTextField(6);
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

        dropdownPanel.add(searchID);
        dropdownPanel.add(optionLop);

        selectPanel.add(l1);
        selectPanel.add(radioPanel);
        selectPanel.add(dropdownPanel);
        selectPanel.add(totalPanel);

        topPanel.add(selectPanel, BorderLayout.CENTER);
        topPanel.add(btnPanel, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);

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
        filterBtn.addActionListener(new FilterBtnListener());
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
                if(idhs == null){
                    JOptionPane.showMessageDialog(null, "Không có HS trong lớp được phân công");
                    return;
                }
                if(ctbus.get(idhs, idnamhoc, idhk, idmon) == null){
                    ChiTietDiemDTO diemmonhoc = new ChiTietDiemDTO(idhs, idmon,idhk,idnamhoc);
                    ctbus.set(diemmonhoc);
                }
                ChiTietDiemDTO diemmonhoc = ctbus.get(idhs, idnamhoc, idhk, idmon);
                //{"ID", "Tên HS", "Lớp", "Môn Học", "Điểm hệ 1", "Điểm hệ 2", "Điểm hệ 3","Học Kỳ","ĐTB môn HK", "Năm Học"};
                                
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

    private class FilterBtnListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String searchid = searchID.getText();
            String lopSelected = (String) optionLop.getSelectedItem();
            tblModel = (NonEditableTableModel) t.getModel();
            sorter = new TableRowSorter<>(tblModel);
            t.setRowSorter(sorter);
            if(searchid.isEmpty() && lopSelected.equals("Tất cả")) return;
            if(!searchid.isEmpty()){
                sorter.setRowFilter(RowFilter.regexFilter(searchid.toUpperCase(), 0));
            }
            if(!lopSelected.equals("Tất cả")){
                sorter.setRowFilter(RowFilter.regexFilter(lopSelected, 2));
            }

            int count = countUniqueIDs(tblModel);
            s.setText(String.valueOf(count));
            if (count == 0) {
                JOptionPane.showMessageDialog(null, "Không có dữ liệu ");
            }
        }
    
    }
    private int countUniqueIDs(DefaultTableModel model) {
        int count = 0;
        HashSet<String> uniqueIDs = new HashSet<>();
    
        // Iterate over visible (filtered) rows
        for (int i = 0; i < sorter.getViewRowCount(); i++) {
            int modelRow = sorter.convertRowIndexToModel(i); // Get actual row in the model
            String id = (String) model.getValueAt(modelRow, 0); // Assuming ID is in the first column
    
            if (!uniqueIDs.contains(id)) {
                uniqueIDs.add(id);
                count++;
            }
        }
        return count;
    }
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
            txtDiem1.setEditable(false);
            txtDiem2.setEditable(false);
            txtDiem3.setEditable(false);
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

    public void resetOutput() {
        outputID = null;
        outputMon = null;
        outputHK = null;
        outputNam = null;
        txtDiem1.setText("");
        txtDiem2.setText("");
        txtDiem3.setText("");
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(850, 670);
        GVQuanLyDiem panel = new GVQuanLyDiem(850, 670,"GV1");
        frame.add(panel);
        frame.setVisible(true);
    }
}