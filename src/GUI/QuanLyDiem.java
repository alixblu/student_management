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
import BUS.PhanLopBUS;
import DTO.ChiTietDiemDTO;
import DTO.DTB_HocKyDTO;
import DTO.HocKyDTO;
import DTO.HocSinhDTO;
import DTO.KQ_HocSinhCaNamDTO;
import DTO.LopDTO;
import DTO.MonHocDTO;
import DTO.NamHocDTO;
import DTO.PhanLopDTO;



/**
 *
 * @author PHUONG ANH
 */
public class QuanLyDiem extends JPanel{
    // private JFrame f;
    private JPanel topPanel, radioPanel, dropdownPanel, selectPanel, totalPanel, btnPanel, btnPanel2, contentPanel, detailPanel, main_detailPanel;
    private JLabel b1, b2, b3, b4, b5, b6;
    private JComboBox<String> optionLop, optionMon, optionHe, optionHocky, optionNam;
    private JTextField s, inputID, txtDiem1, txtDiem2, txtDiem3;
    private JLabel l1, l2;
    private JButton filterBtn, editBtn, delBtn;
    private NonEditableTableModel tblModel;
    private JScrollPane scrollPane;
    private JTable t;
    private int width, height;
    private static String outputID, outputHeid, outputMon, outputHK, outputNam, outputDTBmon, outputCN, outputTenHS, outputLop;

    ArrayList <HocSinhDTO> dshs;
    ArrayList <KQ_HocSinhCaNamDTO> dskq;
    ArrayList<MonHocDTO> dsmon;
    ArrayList<ChiTietDiemDTO> dsct;
    ArrayList<HocKyDTO> dshk;
    ArrayList<DTB_HocKyDTO> dsdtb;
    ArrayList<NamHocDTO> dsnh;
    ArrayList<PhanLopDTO> dspl;
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

        l1 = new JLabel("Hiển thị danh sách điểm theo ");
        l1.setFont(new Font("Arial", Font.BOLD, 20));
        l1.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 100));

        radioPanel = new JPanel();
        radioPanel.setOpaque(false);
        radioPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 0));

        b1 = new JLabel("Lớp");
        b2 = new JLabel("Môn học");
        b3 = new JLabel("Mã HS");
        b4 = new JLabel("Hệ điểm");
        b5 = new JLabel("Học kỳ");
        b6 = new JLabel("Năm học");

        JRadioButton dummyButton = new JRadioButton();
        dummyButton.setVisible(false);

        JLabel[] buttons = {b1, b2, b3, b4, b5, b6};        
        Color color = new Color(180, 204, 227);
        for (JLabel button : buttons) {
            button.setBackground(color);
        }

        dropdownPanel = new JPanel();
        dropdownPanel.setOpaque(false);
        dropdownPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));

        String[] c1 = {"Tất cả","10A1" ,"11A1", "12A1"};
        optionLop = new JComboBox<>(c1);
        String[] c2 = {"Tất cả","Toán", "Vật Lý", "Hóa Học", "Anh Văn"};
        optionMon = new JComboBox<>(c2);
        inputID = new JTextField(6);
        String[] c3 = {"Tất cả","(1): 15 phút", "(2): 1 tiết", "(3): Thi"};
        optionHe = new JComboBox<>(c3);
        String[] c4 = {"Tất cả","Học Kỳ 1", "Học Kỳ 2"};
        optionHocky = new JComboBox<>(c4);
        String[] c5 = {"Tất cả","2024-2025","2023-2024"};
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
        delBtn.setBackground(new Color(255,49,49));
        delBtn.setForeground(Color.WHITE);
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
///////////////
        main_detailPanel = new JPanel();
        main_detailPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 50));

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
/////////
        btnPanel.add(filterBtn,gbcExportBtn);

        totalPanel.add(l2);
        totalPanel.add(s);

        radioPanel.add(b3);
        radioPanel.add(b1);
        radioPanel.add(b2);
        radioPanel.add(b4);
        radioPanel.add(b5);
        radioPanel.add(b6);
        
        dropdownPanel.add(inputID);
        dropdownPanel.add(optionLop);
        dropdownPanel.add(optionMon);
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
        delBtn.addActionListener(new DelBtnListener());
    }
        
    public JScrollPane initTable() {
        t = new JTable();
        t.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        scrollPane = new JScrollPane(t);
        scrollPane.setPreferredSize(new Dimension(0, 320));
    
        String[] headers = {"ID", "Tên HS", "Lớp", "Môn Học", "Hệ Điểm", "Điểm", "Học Kỳ", "ĐiểmTB HK", "Năm Học", "ĐiểmTB NH"};
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
    dskq = kqbus.getList();
    dsmon = mhbus.getList();
    dsct = ctbus.getList();
    dsdtb = dtbbus.getList();
    dshk = hkbus.getList();
    dsnh = nhbus.getList();
    
    for (HocSinhDTO hs : dshs) {
        for (NamHocDTO nh : dsnh) {
            String idnamhoc = nh.getNamHocID();
            String idhs = hs.getHocSinhID();
            String idlop = plbus.get(idhs, idnamhoc) != null ? plbus.get(idhs, idnamhoc).getLopID() : "";

            for (HocKyDTO hk : dshk) {
                String idhk = hk.getHocKyID();
                for (MonHocDTO mh : dsmon) {
                    String idmon = mh.getMonHocID();
                    for (int heso = 1; heso < 4; heso++) {
                        String idHocKy = hk.getHocKyID();
                        String idNamHoc = nh.getNamHocID();
                        //String idDiemHocKy = ctbus.get(idhs, idNamHoc, idHocKy, idmon) != null ? String.valueOf(ctbus.get(idhs, idNamHoc, idHocKy, idmon).getDiem()) : "";
                        String idDiemTrungBinhHocKy = dtbbus.get(idhs, idNamHoc, idHocKy) != null ? String.valueOf(dtbbus.get(idhs, idNamHoc, idHocKy).getDiemTrungBinh()) : "";
                        String idDiemTrungBinhNam = kqbus.get(idhs, idNamHoc) != null ? String.valueOf(kqbus.get(idhs, idNamHoc).getDiemTrungBinhNam()) : "";
                        String tenl = lopbus.get(idlop)!=null?lopbus.get(idlop).getTenLop():"";
                        String[] rowData = new String[]{
                            idhs,
                            hsbus.get(idhs).getTenHocSinh(),
                            tenl,
                            mhbus.get(idmon).getTenMonHoc(),
                            String.valueOf(heso),
                            //idDiemHocKy,
                            hkbus.get(idhk).getTenHocKy(),
                            idDiemTrungBinhHocKy,
                            nhbus.get(idnamhoc).getNamHocBatDau() + "-" + nhbus.get(idnamhoc).getNamHocKetThuc(),
                            idDiemTrungBinhNam
                        };
                        tblModel.addRow(rowData);
                    }
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
            
            tblModel.fireTableDataChanged();
            int count = countUniqueIDs(tblModel);
            s.setText(String.valueOf(count));
            if (tblModel.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Không có dữ liệu ");
            }
        }
    }

    private void tableMouseClicked (java.awt.event.MouseEvent e) throws ParseException{
        int row = t.getSelectedRow();
        outputID = (String) t.getValueAt(row, 0);
        outputTenHS = (String )  t.getValueAt(row, 1);
        outputLop = (String) t.getValueAt(row, 2);
        outputMon = (String) t.getValueAt(row, 3);
        outputHeid = (String) t.getValueAt(row, 4);
        String diem = (String) t.getValueAt(row, 5);
        outputHK = (String) t.getValueAt(row,6);
        outputDTBmon = (String) t.getValueAt(row, 7);
        outputNam = (String) t.getValueAt(row,8);
        txtDiem1.setText(diem);
        outputCN = (String) t.getValueAt(row, 9);
    }

    private class EditBtnListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(outputID==null){
                JOptionPane.showMessageDialog(null, "Chưa chọn thông tin", "Error", JOptionPane.ERROR_MESSAGE);
            return;
            }
            String diem = txtDiem1.getText();
            if (diem.isEmpty()){
                JOptionPane.showMessageDialog(null, "Vui lòng nhập điểm", "Error", JOptionPane.ERROR_MESSAGE);
            return;
            }
            
            if (!(diem.matches("^-?\\d+(\\.\\d+)?$"))) {
                JOptionPane.showMessageDialog(null, "Sai format điểm, vui lòng nhập lại", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } 
            
            if (Float.parseFloat(diem) > 10.0 || Float.parseFloat(diem) < 0.0) {
                JOptionPane.showMessageDialog(null, "Quá số điểm tối đa hoặc số điểm âm, vui lòng nhập lại", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int result = JOptionPane.showConfirmDialog(null,
                "Bạn có chắc muốn sửa điểm của "+outputID,
                "Xác nhận",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

            if (result == JOptionPane.YES_OPTION){
                updateData();
            }
            else{
                return;
            }
        }


    }

    public void updateData(){
        System.out.println("update ................");
        String idhk= hkbus.getByName(outputHK).getHocKyID();
        System.out.println(idhk);

        String idmon = mhbus.getByName(outputMon).getMonHocID();
        System.out.println(idmon);

        String idnamhoc = nhbus.getByName(outputNam).getNamHocID();
        System.out.println(idnamhoc);

        String idhs = outputID;
        System.out.println(idhs);
        int idhe = Integer.parseInt(outputHeid);
        System.out.println(idhe);
        Float diemHK = (outputDTBmon==null ||outputDTBmon.equals(""))?-1:Float.parseFloat(outputDTBmon);//sử lý hàm set với -1
        System.out.println(diemHK);
        Float diemCanam = (outputCN==null ||outputCN.equals(""))?-1:Float.parseFloat(outputCN);
        System.out.println(diemCanam);
        Float diem = (txtDiem1.getText()==null ||txtDiem1.getText().equals(""))?-1:Float.parseFloat(txtDiem1.getText());
        System.out.println(diem);
        String tenhs = outputTenHS;
        String lop = outputLop;

        String hocluc = kqbus.get(idhs, idnamhoc)!=null?kqbus.get(idhs, idnamhoc).getHocLuc():"";//checknull
        String hanhkiem = kqbus.get(idhs, idnamhoc)!=null?kqbus.get(idhs, idnamhoc).getHanhKiem():"Tốt";//
        String ketqua = kqbus.get(idhs, idnamhoc)!=null?kqbus.get(idhs, idnamhoc).getKetQua():"";//
        
        KQ_HocSinhCaNamDTO diemnamhoc = new KQ_HocSinhCaNamDTO(idhs, idnamhoc, hocluc, hanhkiem, diemCanam, ketqua);

        ChiTietDiemDTO ctd = new ChiTietDiemDTO(idhs, idmon, idhk, idnamhoc);
        DTB_HocKyDTO dtb = new DTB_HocKyDTO(idhs, idhk, idnamhoc, diemHK);

        if(tinhdiemHK(idhs, idhk, idnamhoc)>0) {
            dtb.setDiemTrungBinh(tinhdiemHK(idhs, idhk, idnamhoc));
        }

        if(tinhDiemCN(idhs, idnamhoc)>0){
            diemnamhoc.setDiemTrungBinhNam(tinhDiemCN(idhs, idnamhoc));

            //update hocluc

            if(tinhDiemCN(idhs, idnamhoc)<5){
                diemnamhoc.setHocLuc("Yếu");
            }
            else if (tinhDiemCN(idhs, idnamhoc)<8.5 && tinhDiemCN(idhs, idnamhoc)>=7.0){
                diemnamhoc.setHocLuc("Khá");
            }
            else if (tinhDiemCN(idhs, idnamhoc)>=8.5){
                diemnamhoc.setHocLuc("Giỏi");
            }
            else{
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
Object[] rowData = {idhs, tenhs, lop, mhbus.get(idmon).getTenMonHoc(), idhe, diemString, hkbus.get(idhk).getTenHocKy(),
        diemHKString, outputNam, diemCanamString};

            
            int row = t.getSelectedRow();
            tblModel.removeRow(row);
            tblModel.addRow(rowData);
            ///check lai update db;
            System.out.println("check db------------");
            ctbus.set(ctd);
            System.out.println(ctd);
            dtbbus.set(dtb);
            System.out.println(dtb);
            kqbus.set(diemnamhoc);
            System.out.println(diemnamhoc);
        txtDiem1.setText("");
        JOptionPane.showMessageDialog(null, "Cập nhật thành công");
    //thay doi diem khi nhap du 
    //ham tinh diem
    //update diem
        resetOutput();
    }

    public void resetOutput() {
        outputID = null;
        outputHeid = null;
        outputMon = null;
        outputHK = null;
        outputNam = null;
        outputDTBmon = null;
        outputCN = null;
        outputTenHS = null;
        outputLop = null;
    }

    private class DelBtnListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(outputID==null){
                JOptionPane.showMessageDialog(null, "Chọn thông tin trước khi nhập điểm", "Error", JOptionPane.ERROR_MESSAGE);
            return;
            }

            int i = JOptionPane.showConfirmDialog(null, " Bạn có muốn xóa điểm này của"+outputID+" ?", "Confirmation", JOptionPane.YES_NO_OPTION);

            if (i == JOptionPane.YES_OPTION){

                deleteData();

                
                resetOutput();
            }
            else{
                return;
            }
        }
    }
    public void deleteData(){
        outputDTBmon=null;
        outputCN=null;
        System.out.println("delete()");
        String idhk= hkbus.getByName(outputHK).getHocKyID();
        System.out.println(idhk);

        String idmon = mhbus.getByName(outputMon).getMonHocID();
        System.out.println(idmon);

        String idnamhoc = nhbus.getByName(outputNam).getNamHocID();
        System.out.println(idnamhoc);

        String idhs = outputID;
        System.out.println(idhs);
        int idhe = Integer.parseInt(outputHeid);
        System.out.println(idhe);
        Float diemHK = (outputDTBmon==null ||outputDTBmon.equals(""))?-1:Float.parseFloat(outputDTBmon);//sử lý hàm set với -1
        System.out.println(diemHK);
        Float diemCanam = (outputCN==null ||outputCN.equals(""))?-1:Float.parseFloat(outputCN);
        System.out.println(diemCanam);
        Float diem = (txtDiem1.getText()==null ||txtDiem1.getText().equals(""))?-1:Float.parseFloat(txtDiem1.getText());
        System.out.println(diem);

        String hocluc = kqbus.get(idhs, idnamhoc)!=null?kqbus.get(idhs, idnamhoc).getHocLuc():"";//check null
        String hanhkiem = kqbus.get(idhs, idnamhoc)!=null?kqbus.get(idhs, idnamhoc).getHanhKiem():"Tốt";//
        String ketqua = kqbus.get(idhs, idnamhoc)!=null?kqbus.get(idhs, idnamhoc).getKetQua():"";//
        KQ_HocSinhCaNamDTO diemnamhoc = new KQ_HocSinhCaNamDTO(idhs, idnamhoc, hocluc, hanhkiem, diemCanam, ketqua);
        
        ChiTietDiemDTO ctd = new ChiTietDiemDTO(idhs, idmon, idhk, idnamhoc);
        DTB_HocKyDTO dtb = new DTB_HocKyDTO(idhs, idhk, idnamhoc, diemHK);
        
        System.out.println("check db delete------------");
            ctbus.delete(ctd);
            System.out.println(ctd);
            dtbbus.delete(dtb);
            System.out.println(dtb);
            kqbus.delete(diemnamhoc);
            System.out.println(diemnamhoc);
            
        Object[] rowData = {outputID, outputTenHS, outputLop, outputMon, outputHeid, "", outputHK,"", outputNam, ""};
        int row = t.getSelectedRow();
        tblModel.removeRow(row);
        tblModel.addRow(rowData);

        txtDiem1.setText("");
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

        public Float tinhdiemHK(String idhs, String idhk, String idnamhoc){
            Float dhk= (float) 0.0;
            
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
            
            Float dtbmon= (float) 0.0;
            Float tongmon= (float) 0.0;
            for (MonHocDTO mon : dsmon){
                dtbmon= (float) 0.0;
                for( int heso =1;heso <4;heso++){
                    String idmon = mon.getMonHocID();
                    if(ctbus.get(idhs, idnamhoc, idhk, idmon)==null) return (float) -1;
                    Float diem = (float) ((ctbus.get(idhs, idnamhoc, idhk, idmon)!=null) || 
                    (ctbus.get(idhs, idnamhoc, idhk, idmon).getDiem1()<0.0)?ctbus.get(idhs, idnamhoc, idhk, idmon).getDiem1():(float)-1);
                    if(String.valueOf(diem)!=null &&
                    diem>=(float)0.0){
                        System.out.println(mhbus.get(idmon));

                        System.out.println(" he so---"+heso);

                        tongmon += diem*heso;
                        System.out.println(tongmon);
                        
                    }
                }
                dtbmon += tongmon/6;
                tongmon= (float) 0.0;
                dhk += dtbmon;

            }
            dhk /=(dsmon.size());
            
            if (dhk==0.0) return (float) -1;

            return dhk;
        }
    public Float tinhDiemCN(String idhs, String idnh){

        if(tinhdiemHK(idhs, "1", idnh) > 0 && tinhdiemHK(idhs, "2", idnh)>0){
            return (float) (tinhdiemHK(idhs, "1", idnh) + tinhdiemHK(idhs, "2", idnh))/2;
        }
        return (float) -1;
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