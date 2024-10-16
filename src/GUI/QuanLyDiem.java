/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;
import java.awt.*;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.TableRowSorter;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import BUS.*;
import DTO.*;



/**
 *
 * @author PHUONG ANH
 */
public class QuanLyDiem extends JPanel{
    // private JFrame f;
    private JPanel topPanel, radioPanel, dropdownPanel, selectPanel, totalPanel, btnPanel, btnPanel2, contentPanel, detailPanel, main_detailPanel;
    private JLabel b1, b2, b3, b5, b6;
    private JComboBox<String> optionLop, optionMon, optionHocky, optionNam;
    private JTextField s, searchID, txtDiem1, txtDiem2, txtDiem3;
    private JLabel l1, l2;
    private JButton filterBtn, editBtn;
    private NonEditableTableModel tblModel;
    TableRowSorter<NonEditableTableModel> sorter;
    private JScrollPane scrollPane;
    private JTable t;
    private int width, height;
    private static String outputID, outputMon, outputHK, outputNam;

    ArrayList <HocSinhDTO> dshs;
    ArrayList <KQ_HocSinhCaNamDTO> dskq;
    ArrayList<MonHocDTO> dsmon;
    ArrayList<ChiTietDiemDTO> dsct;
    ArrayList<HocKyDTO> dshk;
    ArrayList<DTB_HocKyDTO> dsdtb;
    ArrayList<NamHocDTO> dsnh;
    ArrayList<LopDTO> dslop;
    
    PhanLopBUS plbus = new PhanLopBUS(1);
    LopBUS lopbus = new LopBUS(1);
    HocSinhBUS hsbus = new HocSinhBUS(1);
    MonHocBUS mhbus = new MonHocBUS(1);
    ChiTietDiemBUS ctbus = new ChiTietDiemBUS(1);
    DTB_HocKyBUS dtbbus = new DTB_HocKyBUS(1);
    HocKyBUS hkbus = new HocKyBUS(1);
    KQ_HocSinhCaNamBUS kqbus = new KQ_HocSinhCaNamBUS(1);
    NamHocBUS nhbus = new NamHocBUS(1);
    public QuanLyDiem(int width, int height) {
        // f = new JFrame();
        this.width = width;
        this.height = height;
        setLayout(new BorderLayout());
        setSize(width, height);

        topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.setPreferredSize(new Dimension(0, 160));
        topPanel.setBackground(new Color(180, 204, 227));

        selectPanel = new JPanel();
        selectPanel.setLayout(new BoxLayout(selectPanel, BoxLayout.Y_AXIS));
        selectPanel.setOpaque(false);

        l1 = new JLabel("BẢNG ĐIỂM    ");
        l1.setFont(new Font("Arial", Font.BOLD, 20));
        l1.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 100));

        radioPanel = new JPanel();
        radioPanel.setOpaque(false);
        radioPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 0));

        b1 = new JLabel("Lớp");
        b2 = new JLabel("Môn học");
        b3 = new JLabel("Mã HS");
        b5 = new JLabel("Học kỳ");
        b6 = new JLabel("Năm học");

        JRadioButton dummyButton = new JRadioButton();
        dummyButton.setVisible(false);

        JLabel[] buttons = {b1, b2, b3, b5, b6};        
        Color color = new Color(180, 204, 227);
        for (JLabel button : buttons) {
            button.setBackground(color);
        }

        dropdownPanel = new JPanel();
        dropdownPanel.setOpaque(false);
        dropdownPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));

        searchID = new JTextField(6);
        dslop = lopbus.getList();
        ArrayList<String> c1 = new ArrayList<>();
        c1.add("Tất cả");
        for(LopDTO pc: dslop){
            String tenlop = lopbus.get(pc.getLopID()).getTenLop();
            c1.add(tenlop);
        }
        optionLop = new JComboBox<>(c1.toArray(new String[0]));

        dsmon = mhbus.getList();
        ArrayList<String> c2 = new ArrayList<>();
        c2.add("Tất cả");
        for(MonHocDTO pc: dsmon){
            String tenlop = mhbus.get(pc.getMonHocID()).getTenMonHoc();
            c2.add(tenlop);
        }
        optionMon = new JComboBox<>(c2.toArray(new String[0]));
        String[] c4 = {"Tất cả","Học Kỳ 1", "Học Kỳ 2"};
        optionHocky = new JComboBox<>(c4);
        
        dsnh = nhbus.getList();
        ArrayList<String> c3 = new ArrayList<>();
        c3.add("Tất cả");
        for(NamHocDTO pc: dsnh){
            String tenlop = nhbus.get(pc.getNamHocID()).getNamHocBatDau()+"-"+nhbus.get(pc.getNamHocID()).getNamHocKetThuc();
            c3.add(tenlop);
        }
        optionNam = new JComboBox<>(c3.toArray(new String[0]));
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
        
        // delBtn = new JButton("Xóa");
        // delBtn.setPreferredSize(new Dimension(110, 30));
        // delBtn.setBackground(new Color(255,49,49));
        // delBtn.setForeground(Color.WHITE);
        filterBtn = new JButton("Lọc");
        filterBtn.setPreferredSize(new Dimension(110, 30));
        filterBtn.setBackground(new Color(31, 28, 77));
        filterBtn.setForeground(Color.WHITE);

        detailPanel = new JPanel();
        detailPanel.setLayout(new BorderLayout());
        detailPanel.setOpaque(true);
        detailPanel.setPreferredSize(new Dimension(0,120));

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

        main_detailPanel = new JPanel();
        main_detailPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 50));
        //main_detailPanel.setLayout(new GridLayout(3, 2, 20, 10));

        // Labels for the text fields
        JLabel lblDiem1 = new JLabel("Điểm hệ 1:");
        JLabel lblDiem2 = new JLabel("Điểm hệ 2:");
        JLabel lblDiem3 = new JLabel("Điểm hệ 3:");

        // Text fields for entering scores
        txtDiem1 = new JTextField(5);
        txtDiem2 = new JTextField(5);
        txtDiem3 = new JTextField(5);

        // Set preferred sizes for labels and text fields
        lblDiem1.setPreferredSize(new Dimension(90, 30));
        txtDiem1.setPreferredSize(new Dimension(180, 30));

        lblDiem2.setPreferredSize(new Dimension(90, 30));
        txtDiem2.setPreferredSize(new Dimension(180, 30));

        lblDiem3.setPreferredSize(new Dimension(90, 30));
        txtDiem3.setPreferredSize(new Dimension(180, 30));

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
        detailPanel.add(main_detailPanel); // Or another layout position

        btnPanel.add(filterBtn,gbcExportBtn);

        totalPanel.add(l2);
        totalPanel.add(s);

        radioPanel.add(b3);
        radioPanel.add(b1);
        radioPanel.add(b2);
        radioPanel.add(b5);
        radioPanel.add(b6);
        
        dropdownPanel.add(searchID);
        dropdownPanel.add(optionLop);
        dropdownPanel.add(optionMon);
        dropdownPanel.add(optionHocky);
        dropdownPanel.add(optionNam);

        selectPanel.add(l1);
        selectPanel.add(radioPanel);
        selectPanel.add(dropdownPanel);
        selectPanel.add(totalPanel);

        topPanel.add(selectPanel, BorderLayout.CENTER);
        topPanel.add(btnPanel, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);

        // btnPanel2.add(delBtn, gbcExportBtn);
        btnPanel2.add(editBtn, gbcShowBtn);
        
        detailPanel.add(btnPanel2, BorderLayout.EAST);
        contentPanel=new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setOpaque(true);
        contentPanel.add(initTable(), BorderLayout.CENTER);
        loaddatatoTable();
        contentPanel.add(detailPanel, BorderLayout.NORTH);
        add(contentPanel);
        setVisible(true);

        filterBtn.addActionListener(new FilterBtnListener());
        editBtn.addActionListener(new EditBtnListener());
        // delBtn.addActionListener(new DelBtnListener());
    }
        
    public JScrollPane initTable() {
        t = new JTable();
        t.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        scrollPane = new JScrollPane(t);
        scrollPane.setPreferredSize(new Dimension(0, 320));
    
        String[] headers = {"ID", "Tên HS", "Lớp", "Môn Học", "Điểm hệ 1", "Điểm hệ 2", "Điểm hệ 3","ĐTB môn HK","Học Kỳ","Điểm TB HK", "Năm Học", "Điểm TB CN"};
        int editableColumnIndex = 5; // Điểm column
        tblModel = new NonEditableTableModel(headers, 0, editableColumnIndex);
        t.setModel(tblModel);
        
        t.setRowHeight(40);
        JTableHeader header = t.getTableHeader();
        header.setPreferredSize(new Dimension(header.getPreferredSize().width, 40));
        header.setBackground(new Color(31, 28, 77));
        header.setForeground(Color.WHITE);
        header.setFont(header.getFont().deriveFont(Font.BOLD, 12));

        ((DefaultTableCellRenderer)t.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
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
    
    public void loaddatatoTable(){
    tblModel.setRowCount(0);

    dshs = hsbus.getList();
    dsmon = mhbus.getList();
    dshk = hkbus.getList();
    dslop = lopbus.getList();
    dsnh = nhbus.getList();    
    for(NamHocDTO nh:dsnh){
        String idnh = nh.getNamHocID();

        for(HocKyDTO hk:dshk){
            String idhk = hk.getHocKyID();

            for(LopDTO lop:dslop){
                String idlop = lop.getLopID();
                
                for(HocSinhDTO hs:dshs){
                    String idhs = hs.getHocSinhID();
        
                    if(plbus.get(idhs, idnh, idlop)!=null){                

                        for(MonHocDTO mh:dsmon){
                            String idmh = mh.getMonHocID();
                            if(ctbus.get(idhs, idnh, idhk, idmh) == null){
                                ChiTietDiemDTO diemmonhoc = new ChiTietDiemDTO(idhs, idmh,idhk,idnh);
                                ctbus.add(diemmonhoc);
                            }
                            if(dtbbus.get(idhs, idnh, idhk) == null){
                                DTB_HocKyDTO dtb = new DTB_HocKyDTO(idhs, idhk, idnh, 0.0);
                                dtbbus.add(dtb);
                            }
                            if(kqbus.get(idhs, idnh) == null){
                                KQ_HocSinhCaNamDTO kq = new KQ_HocSinhCaNamDTO(idhs, idnh);
                                kqbus.add(kq);
                            }
                            ChiTietDiemDTO diemmonhoc = ctbus.get(idhs, idnh, idhk, idmh);
                            DTB_HocKyDTO dtb = dtbbus.get(idhs, idnh, idhk);
                            KQ_HocSinhCaNamDTO kq = kqbus.get(idhs, idnh);

                            if(idhk.equals("1") && nhbus.isCurrentSem(nh.getNamHocBatDau()+"-"+nh.getNamHocKetThuc(), idhk) 
                            && diemmonhoc.getDtbMon()!=0){
                                dsct = ctbus.search(idhs, null, idhk, idnh);
                                double tong=0;
                                for(ChiTietDiemDTO ctd: dsct){
                                    tong+=ctd.getDtbMon();
                                    System.out.println("tinhs diem hk1, tong= "+ tong);
                                }
                                dtb.setDiemTrungBinh(tong/mhbus.CountMH());
                                dtbbus.set(dtb);
                            }

                            //tính điểm CN khi sang hk 2
                            if(idhk.equals("2") && nhbus.isCurrentSem(nh.getNamHocBatDau()+"-"+nh.getNamHocKetThuc(), idhk) 
                            && diemmonhoc.getDtbMon()!=0){
                                
                                dsct = ctbus.search(idhs, null, idhk, idnh);
                                double tong=0;
                                for(ChiTietDiemDTO ctd: dsct){
                                    tong+=ctd.getDtbMon();
                                }
                                dtb.setDiemTrungBinh(tong/mhbus.CountMH());
                                dtbbus.set(dtb);

                                double diemhk1 = dtbbus.get(idhs, idnh, "1").getDiemTrungBinh();
                                double diemhk2 = dtbbus.get(idhs, idnh, "2").getDiemTrungBinh();
                                kq.setDiemTrungBinhNam((diemhk1 + diemhk2)/2);
                                
                                //update hocluc
                                if(kq.getDiemTrungBinhNam()<5){
                                    kq.setHocLuc("Yếu");
                                }
                                else if (kq.getDiemTrungBinhNam()<8.5 && kq.getDiemTrungBinhNam()>=7.0){
                                    kq.setHocLuc("Khá");
                                }
                                else if (kq.getDiemTrungBinhNam()>=8.5){
                                    kq.setHocLuc("Giỏi");
                                }
                                else{
                                    kq.setHocLuc("Trung Bình");
                                }

                                //update ketqua
                                if(kq.getDiemTrungBinhNam()<5){
                                    kq.setKetQua("Học lại");
                                }
                                else kq.setHocLuc("Lên lớp");
                                kqbus.set(kq);
                            }

                            //{"ID", "Tên HS", "Lớp", "Môn Học", "Điểm hệ 1", "Điểm hệ 2", "Điểm hệ 3","ĐTB môn HK","Học Kỳ","Điểm TB HK", "Năm Học", "Điểm TB CN"};
                            String[] rowData = new String[]{
                                idhs,
                                hs.getTenHocSinh(),
                                lop.getTenLop(),
                                mh.getTenMonHoc(),
                                String.valueOf(diemmonhoc.getDiem1()),
                                String.valueOf(diemmonhoc.getDiem2()),
                                String.valueOf(diemmonhoc.getDiem3()),
                                String.valueOf(diemmonhoc.getDtbMon()),
                                hk.getTenHocKy(),
                                String.valueOf(dtb.getDiemTrungBinh()),
                                nh.getNamHocBatDau() +"-"+nh.getNamHocKetThuc(),
                                String.valueOf(kq.getDiemTrungBinhNam())
                            };
                            tblModel.addRow(rowData);
                        }
                    }else continue;
                }
            }
        }
    }
    
    tblModel.fireTableDataChanged();
    s.setText(String.valueOf(dshs.size()));
    }
    
    private class FilterBtnListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String searchid = searchID.getText();
            String lopSelected = (String) optionLop.getSelectedItem();
            String monSelected = (String) optionMon.getSelectedItem();
            String hkSelected = (String) optionHocky.getSelectedItem();
            String namSelected = (String) optionNam.getSelectedItem();

            tblModel = (NonEditableTableModel) t.getModel();
            sorter = new TableRowSorter<>(tblModel);
            t.setRowSorter(sorter);

            List<RowFilter<Object, Object>> filters = new ArrayList<>();

            if(!searchid.isEmpty()){
                filters.add(RowFilter.regexFilter(searchid.toUpperCase(), 0));
            }
            if (!lopSelected.equals("Tất cả")) {
                filters.add(RowFilter.regexFilter(lopSelected, 2));
            }
            if (!monSelected.equals("Tất cả")) {
                filters.add(RowFilter.regexFilter(monSelected, 3));
            }
            if (!hkSelected.equals("Tất cả")) {
                filters.add(RowFilter.regexFilter(hkSelected, 8));
            }
            if (!namSelected.equals("Tất cả")) {
                filters.add(RowFilter.regexFilter(namSelected, 10));
            }

            // Apply combined filters
            if (!filters.isEmpty()) {
                sorter.setRowFilter(RowFilter.andFilter(filters));
            }

            int count = countUniqueIDs(tblModel);
            s.setText(String.valueOf(count));

            if (count == 0) {
                JOptionPane.showMessageDialog(null, "Không có dữ liệu");
            }
        }
    }

    private void tableMouseClicked (java.awt.event.MouseEvent e) throws ParseException{
        removeAllListeners();

        editBtn.addActionListener(new EditBtnListener());
        int row = t.getSelectedRow();
        txtDiem1.setEditable(true);  // Allow editing
        txtDiem2.setEditable(true);
        txtDiem3.setEditable(true);
        outputID = (String) t.getValueAt(row, 0);
        outputMon = (String) t.getValueAt(row, 3);
        String diem1 = (String) t.getValueAt(row, 4);
        String diem2 = (String) t.getValueAt(row, 5);
        String diem3 = (String) t.getValueAt(row, 6);
        outputHK = (String) t.getValueAt(row,8);
        outputNam = (String) t.getValueAt(row,10);

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
    private void removeAllListeners() {
        for (ActionListener al : editBtn.getActionListeners()) {
            editBtn.removeActionListener(al);
        }
    }
    private class EditBtnListener implements ActionListener{

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

    public void updateData(){
        System.out.println("updateData()");

        String idhs = outputID;
        String idmon = mhbus.getByName(outputMon).getMonHocID();
        double diem1 = Double.parseDouble(txtDiem1.getText());
        double diem2 = Double.parseDouble(txtDiem2.getText());
        double diem3 = Double.parseDouble(txtDiem3.getText());
        String idhk = hkbus.getByName(outputHK).getHocKyID();
        String idnamhoc = nhbus.getByName(outputNam).getNamHocID();
        
        ChiTietDiemDTO updatectd = new ChiTietDiemDTO(idhs, idmon, idhk, idnamhoc);
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

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(850, 670);
        QuanLyDiem panel = new QuanLyDiem(850, 670);
        frame.add(panel);
        frame.setVisible(true);
    }
}