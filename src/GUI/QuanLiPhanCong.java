package GUI;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;
import com.toedter.calendar.JDateChooser;

import BUS.GiaoVienBUS;
import BUS.LopBUS;
import BUS.MonHocBUS;
import BUS.PhanCongBUS;
import BUS.QLPhanCongBUS;
import DAO.GiaoVienDAO;
import DTO.GiaoVienDTO;
import DTO.HocSinhDTO;
import DTO.LopDTO;
import DTO.MonHocDTO;
import DTO.PhanCongDTO;
import DTO.QLPhanCongDTO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author vhuyn
 */
public final class QuanLiPhanCong extends JPanel implements MouseListener, ActionListener {
    private JLabel lblMahs, lblTenhs, lblGioitinh, lblDiachi, lblimg;
    private JButton btnThem, btnXoa, btnSua, btnFind, btnReset, btnExpExcel;
    private DefaultTableModel tblmodel;
    private JScrollPane scrollpane;
    JTextField[] tf;
    JButton[] buttons;
    JTable t;
    int width, height;
    private JComboBox<String> searchselectBox;
    private JComboBox<String> searchselectBox1;
    private JComboBox<String> searchselectBox2;
    private final Border raisedBevel = BorderFactory.createRaisedBevelBorder();
    Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
    private Color defaultColor;
    private String searchText;
    private JTextField JsearchText;
    DefaultTableModel model;
    TableRowSorter<DefaultTableModel> sorter;
    JDateChooser dateChooser;
    JComboBox<String> phanlopComboBox;
    JComboBox<String> phanmonComboBox;
    private static String pathAnhdd = "";

    QLPhanCongBUS pcBUS = new QLPhanCongBUS();

    private String magv;
    private String tengiaovien;
    private String tenlop;
    private JComboBox phangiaoviencomboBox;

    private MonHocBUS mhBus;
    private GiaoVienBUS gvBus = new GiaoVienBUS();
    ArrayList<GiaoVienDTO> list_gv;

    private PhanCongBUS pcBUS2 = new PhanCongBUS();
    private LopBUS lopBUS = new LopBUS();

    public QuanLiPhanCong(int width, int height) throws SQLException {
        this.width = width;
        this.height = height;
        init();
        btnThem.addMouseListener(this);
        btnXoa.addMouseListener(this);
        btnSua.addMouseListener(this);
        btnThem.addActionListener(this);
        btnSua.addActionListener(this);
        btnXoa.addActionListener(this);
        btnFind.addActionListener(this);
        btnFind.addMouseListener(this);
        btnReset.addActionListener(this);
        JsearchText.addMouseListener(this);
        btnExpExcel.addActionListener(this);
        btnExpExcel.addMouseListener(this);
        phangiaoviencomboBox.addActionListener(this);
    }

    public void init() throws SQLException {

        Color myColor = new Color(99, 116, 198);
        Color searchPanel = new Color(180, 204, 227);
        this.setLayout(new BorderLayout());
        JPanel p3 = SearchHocSinh();
        // p3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0,0,0) , 4
        // , true));
        p3.setPreferredSize(new Dimension(0, 50));
        p3.setBackground(searchPanel);

        JPanel p1 = JHocsinh();
        p1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 4, true));
        p1.setBackground(myColor);
        p1.setPreferredSize(new Dimension(0, 0));

        JPanel p2 = new JPanel();
        p2.setLayout(new FlowLayout(1, 0, 0));
        p2.add(initTable());
        p2.setPreferredSize(new Dimension(0, 335));
        p2.setBackground(Color.gray);

        this.add(p1, BorderLayout.CENTER);
        this.add(p2, BorderLayout.SOUTH);
        this.add(p3, BorderLayout.NORTH);
        this.setSize(new Dimension(width, height));
        this.setVisible(true);
       
    }

    public JPanel SearchHocSinh() {
        Color imgSearchlbl = new Color(180, 204, 227);
        Color btnResets = new Color(52, 48, 128);

        JPanel JSearch = new JPanel();
        JSearch.setLayout(new FlowLayout(1, 10, 5));

        java.net.URL imageURL_Search = getClass().getResource("/image/search_qlhs.png");
        ImageIcon orgIcon_Search = new ImageIcon(imageURL_Search);
        Image scaleImg_Search = orgIcon_Search.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);

        JLabel imgSearch = new JLabel(new ImageIcon(scaleImg_Search));
        imgSearch.setBackground(imgSearchlbl);
        imgSearch.setPreferredSize(new Dimension(40, 40));

        JsearchText = new JTextField();
        JsearchText.setPreferredSize(new Dimension(200, 30));

        JLabel lblSearch = new JLabel("Tìm kiếm theo: ");
        lblSearch.setFont(new Font("arial", Font.BOLD, 14));

        // pcBUS.listTenmh();
        pcBUS.listTenlop(); 
        ArrayList<String> listlop = pcBUS.getTenLopList();
        // ArrayList<String> listmh = pcBUS.getTenMHList();

        String searchOption[] = { "None", "Mã giáo viên", "Họ và tên" };
        searchselectBox = new JComboBox<>(searchOption);

        searchselectBox1 = new JComboBox<>(listlop.toArray(new String[0]));

        // searchselectBox2 = new JComboBox<>(listmh.toArray(new String[0]));

        java.net.URL imageURL = getClass().getResource("/image/home.png");
        ImageIcon originalIcon = new ImageIcon(imageURL); // Tạo ImageIcon từ đường dẫn

        Image scaledImage = originalIcon.getImage().getScaledInstance(80, 40, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        btnReset = new JButton(scaledIcon);

        btnReset.setBackground(btnResets);
        btnReset.setForeground(Color.WHITE);
        Font font = new Font("Arial", Font.BOLD, 12);
        btnReset.setFont(font);
        btnReset.setPreferredSize(new Dimension(80, 40));
        // btnReset.setOpaque(true);

        JSearch.add(imgSearch);
        JSearch.add(JsearchText);
        JSearch.add(lblSearch);
        JSearch.add(searchselectBox);
        JSearch.add(searchselectBox1);
        // JSearch.add(searchselectBox2);
        JSearch.add(btnReset);

        return JSearch;

    }

    public JPanel JChucnang() {
        Color myColor = new Color(99, 116, 198);
        JPanel Pchucnang = new JPanel();
        Pchucnang.setLayout(new FlowLayout(0, 5, 5));

        java.net.URL imageURL_Add = getClass().getResource("/image/btnAdd.png");
        ImageIcon orgIcon = new ImageIcon(imageURL_Add);
        Image scaleImg = orgIcon.getImage().getScaledInstance(155, 40, Image.SCALE_SMOOTH);

        btnThem = new JButton(new ImageIcon(scaleImg));
        btnThem.setPreferredSize(new Dimension(155, 40));
        btnThem.setBorder(raisedBevel);

        java.net.URL imageURL_Del = getClass().getResource("/image/btnDelete.png");
        ImageIcon orgIcon_Del = new ImageIcon(imageURL_Del);
        Image scaleImg_Del = orgIcon_Del.getImage().getScaledInstance(155, 40, Image.SCALE_SMOOTH);

        btnXoa = new JButton(new ImageIcon(scaleImg_Del));
        btnXoa.setPreferredSize(new Dimension(155, 40));
        btnXoa.setBorder(raisedBevel);

        java.net.URL imageURL_Edit = getClass().getResource("/image/btnEdit.png");
        ImageIcon orgIcon_Edit = new ImageIcon(imageURL_Edit);
        Image scaleImg_Edit = orgIcon_Edit.getImage().getScaledInstance(155, 40, Image.SCALE_SMOOTH);

        btnSua = new JButton(new ImageIcon(scaleImg_Edit));
        btnSua.setPreferredSize(new Dimension(155, 40));
        btnSua.setBorder(raisedBevel);

        java.net.URL imageURL_Find = getClass().getResource("/image/btnsearch_qlhs1.png");
        ImageIcon orgIcon_Find = new ImageIcon(imageURL_Find);
        Image scaleImg_Find = orgIcon_Find.getImage().getScaledInstance(155, 40, Image.SCALE_SMOOTH);
        btnFind = new JButton(new ImageIcon(scaleImg_Find));
        btnFind.setPreferredSize(new Dimension(155, 40));
        btnFind.setBorder(raisedBevel);

        java.net.URL imageURL_ExpExcel = getClass().getResource("/image/export_excel.png");
        ImageIcon orgIcon_ExpExcel = new ImageIcon(imageURL_ExpExcel);
        Image scaleImg_ExpExcel = orgIcon_ExpExcel.getImage().getScaledInstance(230, 100, Image.SCALE_SMOOTH);
        btnExpExcel = new JButton(new ImageIcon(scaleImg_ExpExcel));
        btnExpExcel.setPreferredSize(new Dimension(155, 40));
        btnExpExcel.setBorder(raisedBevel);
        btnExpExcel.setBackground(myColor);

        Color c = new Color(204, 204, 255);
        // Pchucnang.setBackground(myColor);
        Pchucnang.setBackground(c);

        defaultColor = btnThem.getBackground();
        Pchucnang.add(btnThem);
        Pchucnang.add(btnXoa);
        Pchucnang.add(btnSua);
        Pchucnang.add(btnFind);
        Pchucnang.add(btnExpExcel);
        return Pchucnang;
    }

    public JPanel JHocsinh() {
        JPanel Phocsinh = new JPanel();
        Phocsinh.setLayout(null);
        // pcBUS.listTenmh();
        pcBUS.listTenlop();
        pcBUS.listMagv();
        ArrayList<String> listlop = pcBUS.getTenLopList();
        // ArrayList<String> listmh = pcBUS.getTenMHList();
        ArrayList<String> listmagv = pcBUS.getMaGVList();

        String[] arrphancong = { "Mã Giáo Viên", "Tên Giáo Viên","Môn học", "Phân Lớp"};

        int length = arrphancong.length;
        tf = new JTextField[length];
        buttons = new JButton[length];

        Phocsinh.setLayout(null);

        int toadoXbutton = 190;
        int toadoYbutton = 10;
        int toadoXTextfield = 330;
        int toadoYTextfield = 10;
        int x = 230;
        int y = 15;
        for (int i = 0; i < length; i++) {
            buttons[i] = new JButton(arrphancong[i]);
            buttons[i].setBounds(toadoXbutton, toadoYbutton, 120, 30);
            buttons[i].setHorizontalAlignment(JButton.CENTER);
            buttons[i].setName("btn" + i);

            toadoYbutton = toadoYbutton + 35 + 20;
            Phocsinh.add(buttons[i]);

            if (i == 0) {
                phangiaoviencomboBox = new JComboBox<>(listmagv.toArray(new String[0]));
                phangiaoviencomboBox.setBounds(toadoXTextfield, toadoYTextfield, 320, 30);
                Phocsinh.add(phangiaoviencomboBox);
                toadoYTextfield = toadoYTextfield + 35 + 20;
            } else if (i == 3) {
                phanlopComboBox = new JComboBox<>(listlop.toArray(new String[0]));
                phanlopComboBox.setBounds(toadoXTextfield, toadoYTextfield, 320, 30);
                Phocsinh.add(phanlopComboBox);
                toadoYTextfield = toadoYTextfield + 35 + 20;
            }
            else {
                tf[i] = new JTextField();
                tf[i].setBounds(toadoXTextfield, toadoYTextfield, 320, 30);
                tf[i].setFont(new Font("Arial", Font.BOLD, 12));
                tf[i].setBorder(border);
                tf[i].setName("text" + i);
                toadoYTextfield = toadoYTextfield + 35 + 20;
                Phocsinh.add(tf[i]);
            }
            y = y + 35;
        }
        x = x + 180;
        tf[1].setEditable(false);
        tf[1].setFocusable(false);
        tf[2].setEditable(false);
        tf[2].setFocusable(false);
        JPanel Pchucnang = JChucnang();
        Pchucnang.setBounds(660, 3, 165, y + 115);
        lblimg = new JLabel();
        lblimg.setBounds(0, 0, 180, y + 80);
        lblimg.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(5, 5, 5), 4, true));
        lblimg.setOpaque(true);
        lblimg.setBackground(Color.GRAY);
        lblimg.setOpaque(true);
        Phocsinh.add(lblimg);
        Phocsinh.add(Pchucnang);

        Phocsinh.setPreferredSize(new Dimension(x, y + 75));

        return Phocsinh;
    }

    public JScrollPane initTable() throws SQLException {

        t = new JTable();
        t.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        scrollpane = new JScrollPane(t);
        scrollpane.setPreferredSize(new Dimension(846, 370));
        String[] header = { "Mã giáo viên", "Tên Giáo Viên", "Môn dạy học", "Tên lớp"};

        Integer lenght = header.length;
        pcBUS.listPC();
        pcBUS2.list();

        // ArrayList<QLPhanCongDTO> dspc = pcBUS.getList();
        ArrayList<PhanCongDTO> dspc2 = pcBUS2.getList();
        ArrayList<LopDTO> list_lop = lopBUS.getList();
        list_gv = gvBus.getList();

        Object[][] rowData = new Object[dspc2.size()][lenght];
        for (int i = 0; i < dspc2.size(); i++) {
            PhanCongDTO pc2 = dspc2.get(i);
            rowData[i][0] = pc2.getGiaoVienID();
            for(int j = 0 ; j <list_gv.size(); j++){
                GiaoVienDTO gv = list_gv.get(j);
                if(pc2.getGiaoVienID().equals(gv.getMaGV())){
                    rowData[i][1] = gv.getTenGV();
                    rowData[i][2] = gv.getphanMon();
                }
            }
            for(int k = 0 ; k <list_lop.size(); k++){
                LopDTO lop = list_lop.get(k);
                if(pc2.getLopID().equals(lop.getLopID())){
                    rowData[i][3] = lop.getTenLop();
                }
            }

        }




        Font font = new Font("Arial", Font.BOLD, 12);
        Color title_color = new Color(31, 28, 77);
        t.getTableHeader().setBackground(title_color);
        t.getTableHeader().setForeground(Color.WHITE);
        t.getTableHeader().setFont(font);
        Color select = new Color(102, 178, 255);
        t.setSelectionBackground(select);

        tblmodel = new DefaultTableModel(rowData, header);
        t.setModel(tblmodel);
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

        return scrollpane;
    }

    public void addRow() {
        String magv = (String) phangiaoviencomboBox.getSelectedItem();
        String tengv = tf[1].getText();
        String tenmh = tf[2].getText();
        String tenlop = (String) phanlopComboBox.getSelectedItem();
        // String tenmon = (String) phanmonComboBox.getSelectedItem();
        Object rowdata[] = { magv, tengv, tenmh ,tenlop };

        QLPhanCongDTO pc = new QLPhanCongDTO(magv, tengv, tenlop);
        pcBUS.add(pc);

        tblmodel.addRow(rowdata);
        clearTextFields();
    }

    public void deleteRow() {
        int row = t.getSelectedRow();
        if (row != -1) {
            tblmodel.removeRow(row);
        }

        String magv = (String) phangiaoviencomboBox.getSelectedItem();
        // String tenmonhoc = (String) phanmonComboBox.getSelectedItem();
        String tenlop = (String) phanlopComboBox.getSelectedItem();
        QLPhanCongDTO pc = new QLPhanCongDTO(magv, "", tenlop);
        pcBUS.delete(pc);

        clearTextFields();
    }

    public void updateRow() {
        String magv = (String) phangiaoviencomboBox.getSelectedItem();
        String tengv = tf[1].getText();
        String tenmh = tf[2].getText();
        String tenlop = (String) phanlopComboBox.getSelectedItem();
        // String tenmon = (String) phanmonComboBox.getSelectedItem();
        Object[] rowData = { magv, tengv, tenmh ,tenlop };
        QLPhanCongDTO pc = new QLPhanCongDTO(magv, tengv, tenlop);
        if (pcBUS.checkExist(pc)) {
            JOptionPane.showMessageDialog(this, "Đã tồn tại sự phân công này", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        } else {
            System.out.println("lỗi");
        }

        pcBUS.update(pc);

        JOptionPane.showMessageDialog(this, "Sủa thành công", "Success", JOptionPane.DEFAULT_OPTION);

        int row = t.getSelectedRow();
        tblmodel.removeRow(row);
        tblmodel.addRow(rowData);
        clearTextFields();
    }

    public void clearTextFields() {
        phangiaoviencomboBox.setSelectedItem("None");
        phanlopComboBox.setSelectedItem("None");
        // phanmonComboBox.setSelectedItem("None");
        tf[1].setText("none");
        tf[2].setText("none");
    }

    public boolean checkEmpty() {
        if (phangiaoviencomboBox.getSelectedItem() == "None"
                || phangiaoviencomboBox.getSelectedItem().toString().isEmpty() || tf[1].getText().isEmpty()
                || phanlopComboBox.getSelectedItem() == "None"
                || phanlopComboBox.getSelectedItem().toString().isEmpty()) {
            return true;
        }
        return false;
    }

    private void tableMouseClicked(java.awt.event.MouseEvent evt) throws ParseException {
        int row = t.getSelectedRow();
        magv = (String) t.getValueAt(row, 0);
        tengiaovien = (String.valueOf(t.getValueAt(row, 1)));
        String tenmh = (String.valueOf(t.getValueAt(row, 2)));
        tenlop = (String.valueOf(t.getValueAt(row, 3)));
        // tenmon = (String.valueOf(t.getValueAt(row, 3)));

        phangiaoviencomboBox.setSelectedItem(magv);
        tf[1].setText(tengiaovien);
        tf[2].setText(tenmh);
        phanlopComboBox.setSelectedItem(tenlop);
        // phanmonComboBox.setSelectedItem(tenmon);
        String img = null;

        img = pcBUS.getIMG(magv);
        if (img != null) {
            String path = "/image/Avatar/" + img;
            java.net.URL imgHS = getClass().getResource(path);
            ImageIcon orgIcon_HS = new ImageIcon(imgHS);
            Image scaleImg_HS = orgIcon_HS.getImage().getScaledInstance(lblimg.getWidth(), lblimg.getHeight(),
                    Image.SCALE_SMOOTH);

            ImageIcon scaledImage_HS = new ImageIcon(scaleImg_HS);

            // Hiển thị hình ảnh trên JLabel
            lblimg.setIcon(scaledImage_HS);
        } else {
            lblimg.setIcon(null);
        }
    }

    public void btnAdd_actionPerformed() {
        if (checkEmpty()) {
            JOptionPane.showMessageDialog(this, "Hãy điền đầy đủ các thông tin", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
    
        // Lấy mã giáo viên và tên lớp từ giao diện
        String magv = (String) phangiaoviencomboBox.getSelectedItem();
        String tenlop = (String) phanlopComboBox.getSelectedItem();
    
        // Tạo đối tượng QLPhanCongDTO
        QLPhanCongDTO pc = new QLPhanCongDTO(magv, "", tenlop);
    
        // Kiểm tra sự tồn tại của phân công
        if (pcBUS.checkExist(pc)) {
            JOptionPane.showMessageDialog(this, "Đã tồn tại sự phân công này", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    
        // Xác nhận từ người dùng
        int result = JOptionPane.showConfirmDialog(this,
                "Bạn có chắc muốn thêm sự phân công này?",
                "Xác nhận",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
    
        if (result == JOptionPane.YES_OPTION) {
            // // Thực hiện thêm phân công
            // pcBUS.add(pc);
    
            // Hiển thị thông báo thêm thành công
            JOptionPane.showMessageDialog(this,
                    "Thêm thành công",
                    "Chức năng thêm",
                    JOptionPane.INFORMATION_MESSAGE);
    
            // Đặt focus lại vào một trường
            tf[1].requestFocus();
    
            // Cập nhật bảng (nếu có bảng hiển thị)
            addRow();
        }
    }
    

    public void clearSelectSearch() {
        phangiaoviencomboBox.setSelectedItem("None");
        phanlopComboBox.setSelectedItem("None");
        // phanmonComboBox.setSelectedItem("None");
    }

    public void btnDelete_actionPerformed() {
        if (checkEmpty()) {
            JOptionPane.showMessageDialog(this, "Hãy điền đầy đủ các thông tin", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        int result = JOptionPane.showConfirmDialog(this,
                "Bạn có chắc muốn xóa sự phân công này này",
                "Xác nhận",
                JOptionPane.YES_NO_OPTION,

                JOptionPane.QUESTION_MESSAGE);
        if (result == JOptionPane.YES_OPTION) {
            deleteRow();
        } else if (result == JOptionPane.NO_OPTION) {
            System.out.println("Bạn chọn không đồng ý xóa");
        }
    }

    public void btnSua_actionPerformed() {
        if (checkEmpty()) {
            JOptionPane.showMessageDialog(this, "Hãy điền đầy đủ các thông tin", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        int result = JOptionPane.showConfirmDialog(this,
                "Bạn có chắc muốn sửa sự phân công này",
                "Xác nhận",
                JOptionPane.YES_NO_OPTION,

                JOptionPane.QUESTION_MESSAGE);
        if (result == JOptionPane.YES_OPTION) {
            System.out.println("Ban chọn đồng ý sửa");
            updateRow();
        } else if (result == JOptionPane.NO_OPTION) {
            System.out.println("Bạn chọn không đồng ý sửa");
        }
    }

    public void btnFind_actionPerformed() {
        String selectedOption = (String) searchselectBox.getSelectedItem();
        String selectedLop = (String) searchselectBox1.getSelectedItem();
        
        model = (DefaultTableModel) t.getModel();
        sorter = new TableRowSorter<>(model);
        t.setRowSorter(sorter);
        
        ArrayList<RowFilter<Object, Object>> filters = new ArrayList<>();
        
        if (selectedOption.equals("Mã giáo viên")) {
            String searchText = JsearchText.getText().trim();
            
            
    
            if (searchText.isEmpty()) {
                JOptionPane.showMessageDialog(t, "Không được để trống ô tìm kiếm", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (!searchText.matches("[a-zA-Z0-9 ]+")) {
                JOptionPane.showMessageDialog(t, "Không được nhập ký tự đặc biệt", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
    
            filters.add(RowFilter.regexFilter("(?i)" + searchText, 0));
        }
        else if (selectedOption.equals("Họ và tên")) {
            String searchText = JsearchText.getText().trim();
            if (searchText.isEmpty()) {
                JOptionPane.showMessageDialog(t, "Không được để trống ô tìm kiếm", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (!searchText.matches("[a-zA-Z0-9 ]+")) {
                JOptionPane.showMessageDialog(t, "Không được nhập ký tự đặc biệt", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
            filters.add(RowFilter.regexFilter("(?i)" + searchText, 1)); // "(?i)" để không phân biệt hoa thường
        }
    
        if (!selectedLop.equals("None")) {
            filters.add(RowFilter.regexFilter(selectedLop, 3));
        }
        

        if(selectedLop.equals("None") && searchText.equals("") && selectedOption.equals("None")){
            JOptionPane.showMessageDialog(t, "Hãy chọn và nhập thông tin tìm kiếm tương ứng", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }


        RowFilter<Object, Object> combinedFilter;
        if (filters.size() > 0) {
            combinedFilter = RowFilter.andFilter(filters);
        } else {
            combinedFilter = null; // Không có bộ lọc nào
        }
        
        sorter.setRowFilter(combinedFilter);
        
        if (sorter.getViewRowCount() == 0) {
            JOptionPane.showMessageDialog(t, "Không tìm thấy dữ liệu phù hợp", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    

    public void exportExcel() throws IOException {
        if (tblmodel.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Bảng trống, không có dữ liệu để xuất.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return; 
        }
    
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Tập tin Excel", "xls");
        chooser.setFileFilter(filter);
        chooser.setDialogTitle("Lưu tệp");
        chooser.setAcceptAllFileFilterUsed(false);
        if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            String path = chooser.getSelectedFile().toString().concat(".xls");
    
            Workbook workbook = new HSSFWorkbook();
    
            Sheet sheet = workbook.createSheet("DanhSachPhanCong");
            Row headerRow = sheet.createRow(0);
            String[] headers = { "Mã giáo viên", "Tên Giáo Viên", "Môn dạy học", "Tên lớp" };
    
            // Create header cells
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
            }
    
            for (int i = 0; i < tblmodel.getRowCount(); i++) {
                Row row = sheet.createRow(i + 1);
    
                for (int j = 0; j < tblmodel.getColumnCount(); j++) {
                    Cell cell = row.createCell(j);
                    cell.setCellValue(tblmodel.getValueAt(i, j).toString());
                }
            }
    
            try (FileOutputStream fos = new FileOutputStream(path)) {
                workbook.write(fos);
            }
    
            JOptionPane.showMessageDialog(this, "Dữ liệu đã được xuất thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            Desktop.getDesktop().open(new File(path));
        }
    }
    

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == JsearchText) {
            clearTextFields();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {

        if (e.getSource() == btnThem) {
            btnThem.setBackground(Color.green);
        }
        if (e.getSource() == btnXoa) {
            btnXoa.setBackground(Color.green);
        }
        if (e.getSource() == btnSua) {
            btnSua.setBackground(Color.green);
        }
        if (e.getSource() == btnFind) {
            btnFind.setBackground(Color.green);
        }
        if (e.getSource() == btnExpExcel) {
            btnExpExcel.setBackground(Color.green);
        }

    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == btnThem) {
            btnThem.setBackground(defaultColor);
        } else if (e.getSource() == btnXoa) {
            btnXoa.setBackground(defaultColor);
        } else if (e.getSource() == btnSua) {
            btnSua.setBackground(defaultColor);
        } else if (e.getSource() == btnFind) {
            btnFind.setBackground(defaultColor);
        } else if (e.getSource() == btnExpExcel) {
            btnExpExcel.setBackground(defaultColor);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == phangiaoviencomboBox) {
            String magv = (String) phangiaoviencomboBox.getSelectedItem();
            String tengv = pcBUS.getName(magv);
            String tenmh = "";
            for(int i = 0 ; i <list_gv.size(); i++){
                GiaoVienDTO gv = list_gv.get(i);
                if(gv.getMaGV().equals(magv)){
                    tenmh = gv.getphanMon();
                }
            }
            tf[1].setText(tengv);
            tf[2].setText(tenmh);
        }

        if (e.getSource() == btnThem) {
            btnAdd_actionPerformed();

        } else if (e.getSource() == btnSua) {
            btnSua_actionPerformed();

        } else if (e.getSource() == btnXoa) {
            btnDelete_actionPerformed();

        } else if (e.getSource() == btnFind) {
            btnFind_actionPerformed();

        } else if (e.getSource() == btnReset) {
            JsearchText.setText("");
            clearTextFields();
            model = (DefaultTableModel) t.getModel();
            sorter = new TableRowSorter<>(model);
            t.setRowSorter(sorter);
            sorter.setRowFilter(RowFilter.regexFilter("", 0));
        } else if (e.getSource() == btnExpExcel) {
            try {
                exportExcel();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }

    }

    public static void main(String[] args) throws SQLException {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(850, 670);
        QuanLiPhanCong panel = new QuanLiPhanCong(850, 670);
        frame.add(panel);
        frame.setVisible(true);
    }
}
