package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.toedter.calendar.JDateChooser;

import BUS.ChangeAcc_BUS;
import BUS.ChiTietDiemBUS;
import BUS.HocSinhBUS;
import BUS.LopBUS;
import BUS.NamHocBUS;
import BUS.PhanLopBUS;
import BUS.QLHS_BUS;
import BUS.QLHS_GV_BUS;
import DTO.HocSinhDTO;
import DTO.LopDTO;
import DTO.NamHocDTO;
import DTO.PhanLopDTO;
import java.awt.Desktop;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author vhuyn
 */
public final class QuanLiHocSinh_GV extends JPanel implements MouseListener, ActionListener {
   
    

    private String mahs, hoten, gioitinh, diachi, namsinh, sodienthoai, img, tenlop;
    private JLabel lblMahs, lblTenhs, lblGioitinh, lblDiachi;
    public static JLabel lblimg;
    private JButton btnThem, btnXoa, btnSua, btnFind, btnReset, btnExpExcel;
    
    public static DefaultTableModel tblmodel;
    // private JTable tbl;
    private JScrollPane scrollpane;
    JTextField[] tf;
    JButton[] buttons;
    JTable t;
    int width, height, soKhoa;
    private JComboBox<String> searchselectBox;
    private final Border raisedBevel = BorderFactory.createRaisedBevelBorder();
    Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
    private Color defaultColor;
    private String searchText;
    private JTextField JsearchText;
    DefaultTableModel model;
    TableRowSorter<DefaultTableModel> sorter;
    JDateChooser dateChooser;
    JComboBox<String> genderComboBox;
    JComboBox<String> classComboBox;
    QLHS_BUS hsBUS = new QLHS_BUS();
    private static String pathAnhdd = "";
    NamHocBUS nhBUS = new NamHocBUS();
    ChangeAcc_BUS accBUS = new ChangeAcc_BUS();

    public QuanLiHocSinh_GV(int width, int height) throws SQLException {
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
    }

    public void init() throws SQLException {

        // this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Color myColor = new Color(99, 116, 198);
        Color searchPanel = new Color(180, 204, 227);
        this.setLayout(new BorderLayout());
        JPanel p3 = SearchHocSinh();
        
        p3.setPreferredSize(new Dimension(0, 60));
        p3.setBackground(searchPanel);

        JPanel p1 = JHocsinh();
        p1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 4, true));
        p1.setBackground(myColor);
        p1.setPreferredSize(new Dimension(0, 0));

        JPanel p2 = new JPanel();
        p2.setLayout(new BorderLayout());
        p2.add(initTable(), BorderLayout.SOUTH);
        p2.setPreferredSize(new Dimension(0, 295));
        p2.setBackground(Color.gray);

        this.add(p1, BorderLayout.CENTER);
        this.add(p2, BorderLayout.SOUTH);
        this.add(p3, BorderLayout.NORTH);
        this.setSize(new Dimension(width, height));
        this.setVisible(true);
        // this.setLocationRelativeTo(null);
        // this.setResizable(false);
    }

    public JPanel SearchHocSinh() {
        Color imgSearchlbl = new Color(180, 204, 227);
        Color btnResets = new Color(52, 48, 128);

        JPanel JSearch = new JPanel();
        JSearch.setLayout(new FlowLayout(1, 10, 5));

        java.net.URL imageURL_Search = getClass().getResource("/image/search_qlhs.png");
        ImageIcon orgIcon_Search = new ImageIcon(imageURL_Search);
        Image scaleImg_Search = orgIcon_Search.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);

        JLabel imgSearch = new JLabel(new ImageIcon(scaleImg_Search));
        imgSearch.setBackground(imgSearchlbl);
        imgSearch.setPreferredSize(new Dimension(50, 50));

        JsearchText = new JTextField();
        JsearchText.setPreferredSize(new Dimension(300, 40));

        JLabel lblSearch = new JLabel("Tìm kiếm theo: ");
        lblSearch.setFont(new Font("arial", Font.BOLD, 14));
        String searchOption[] = { "Mã học sinh", "Họ và tên","Lớp" };
        searchselectBox = new JComboBox<>(searchOption);

        java.net.URL imageURL = getClass().getResource("/image/home.png");
        ImageIcon originalIcon = new ImageIcon(imageURL); // Tạo ImageIcon từ đường dẫn

        // Chỉnh kích thước ảnh
        Image scaledImage = originalIcon.getImage().getScaledInstance(120, 40, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        btnReset = new JButton(scaledIcon);

        btnReset.setBackground(btnResets);
        btnReset.setForeground(Color.WHITE);
        Font font = new Font("Arial", Font.BOLD, 12);
        btnReset.setFont(font);
        btnReset.setPreferredSize(new Dimension(120, 40));
        // btnReset.setOpaque(true);

        JSearch.add(imgSearch);
        JSearch.add(JsearchText);
        JSearch.add(lblSearch);
        JSearch.add(searchselectBox);
        JSearch.add(btnReset);

        return JSearch;

    }

    public JPanel JChucnang() {
        Color myColor = new Color(99, 116, 198);
        JPanel Pchucnang = new JPanel();
        Pchucnang.setLayout(new FlowLayout(0, 5, 10));

        java.net.URL imageURL_Find = getClass().getResource("/image/btnsearch_qlhs1.png");
        ImageIcon orgIcon_Find = new ImageIcon(imageURL_Find);
        Image scaleImg_Find = orgIcon_Find.getImage().getScaledInstance(155, 40, Image.SCALE_SMOOTH);
        btnFind = new JButton(new ImageIcon(scaleImg_Find));
        btnFind.setPreferredSize(new Dimension(155, 40));
        btnFind.setBorder(raisedBevel);


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

        

        java.net.URL imageURL_ExpExcel = getClass().getResource("/image/export_excel.png");
        ImageIcon orgIcon_ExpExcel = new ImageIcon(imageURL_ExpExcel);
        Image scaleImg_ExpExcel = orgIcon_ExpExcel.getImage().getScaledInstance(230, 100, Image.SCALE_SMOOTH);
        btnExpExcel = new JButton(new ImageIcon(scaleImg_ExpExcel));
        btnExpExcel.setPreferredSize(new Dimension(155, 40));
        btnExpExcel.setBorder(raisedBevel);
        btnExpExcel.setBackground(myColor);

        Pchucnang.setBackground(myColor);
        defaultColor = btnThem.getBackground();
        
        Pchucnang.add(btnFind);
       // Pchucnang.add(btnThem);
        //Pchucnang.add(btnXoa);
        //Pchucnang.add(btnSua);
        //Pchucnang.add(btnExpExcel);
        return Pchucnang;
    }

    
    public JPanel JHocsinh() {
        JPanel Phocsinh = new JPanel();
        Phocsinh.setLayout(null);
        String[] arrHocsinh = { "Mã học sinh", "Tên học sinh", "Giới tính", "Năm sinh", "Địa chỉ", "Số điện thoại", "Chọn ảnh", "Lớp"};
        int length = arrHocsinh.length;
        tf = new JTextField[length];
        buttons = new JButton[length];
        Phocsinh.setLayout(null);
        int toadoXbutton = 190;
        int toadoYbutton = 10;
        int toadoXTextfield = 330;
        int toadoYTextfield = 10;
        int x = 230;
        int y = 15;
        
        for (int i = 0; i < arrHocsinh.length; i++) {
            if (i == 6) {
                buttons[i] = new JButton(arrHocsinh[i]);
                buttons[i].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        chooseImage();
                        
                    }
                });
                buttons[i].setBounds(toadoXbutton, toadoYbutton, 120, 30);
                buttons[i].setForeground(Color.RED);
                buttons[i].setHorizontalAlignment(JButton.CENTER);
                buttons[i].setName("btn" + i);
                
                Phocsinh.add(buttons[i]);
            } else {
                buttons[i] = new JButton(arrHocsinh[i]);
                buttons[i].setBounds(toadoXbutton, toadoYbutton, 120, 30);
                buttons[i].setHorizontalAlignment(JButton.CENTER);
                buttons[i].setName("btn" + i);
            }
    
            toadoYbutton = toadoYbutton + 35;
            Phocsinh.add(buttons[i]);
    
            if (i == 3) {
                dateChooser = new JDateChooser();
                dateChooser.setDateFormatString("dd/MM/yyyy");
                dateChooser.setBounds(toadoXTextfield, toadoYTextfield, 320, 30);
                Phocsinh.add(dateChooser);
                toadoYTextfield = toadoYTextfield + 35;
                dateChooser.setEnabled(false);
            } else if (i == 2) {
                String[] genders = { "Nam", "Nữ", "Khác" };
                genderComboBox = new JComboBox<>(genders);
                genderComboBox.setBounds(toadoXTextfield, toadoYTextfield, 320, 30);
                genderComboBox.setEnabled(false);
                Phocsinh.add(genderComboBox);
                toadoYTextfield = toadoYTextfield + 35;
            } else if (i == 7) {
                LopBUS lopbus = new LopBUS();
                ArrayList<String> classes = lopbus.list_TenLop();
                classComboBox = new JComboBox<>(classes.toArray(new String[0]));
                classComboBox.setBounds(toadoXTextfield, toadoYTextfield, 320, 30);
                classComboBox.setEnabled(false);
                Phocsinh.add(classComboBox);
                toadoYTextfield = toadoYTextfield + 35;
            } else {
                tf[i] = new JTextField();
                tf[i].setBounds(toadoXTextfield, toadoYTextfield, 320, 30);
                tf[i].setFont(new Font("Arial", Font.BOLD, 12));
                tf[i].setBorder(border);
                tf[i].setName("text" + i);
                //tf[i].setEnabled(false);
                tf[i].setDisabledTextColor(Color.BLACK);
                tf[i].setBackground(Color.WHITE);
                toadoYTextfield = toadoYTextfield + 35;
                Phocsinh.add(tf[i]);
                tf[0].setEditable(false);
            }
            y = y + 35;
        }
        x = x + 180;
        JPanel Pchucnang = JChucnang();
        Pchucnang.setBounds(660, 3, 250, y);
        Phocsinh.add(Pchucnang);
    
        lblimg = new JLabel();
        lblimg.setBounds(0, 0, 180, y-80);
        lblimg.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 4, true));
        lblimg.setOpaque(true);
        Phocsinh.add(lblimg);
        Phocsinh.setPreferredSize(new Dimension(x, y));
    
        return Phocsinh;
    }
    

    public JScrollPane initTable() throws SQLException {

        
        t = new JTable();
        t.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        scrollpane = new JScrollPane(t);
        scrollpane.setPreferredSize(new Dimension(846, 295));
        String[] header = { "Mã học sinh", "Họ và tên", "Giới tính", "Năm sinh", "Địa chỉ", "Số điện thoại",
                "Ảnh chân dung","Lớp","Năm học"};

        if (hsBUS.getList() == null)
            hsBUS.listHS();
        ArrayList<HocSinhDTO> hs = hsBUS.getList();
        Object[][] rowData = new Object[hs.size()][9];
        if (nhBUS.getList() == null){
            nhBUS.listNH();}
        ArrayList<NamHocDTO> nh = nhBUS.getList();
        for (int i = 0; i < nh.size(); i++) {
            NamHocDTO namhoc = nh.get(i);
            soKhoa = namhoc.getNamHocBatDau() - 2000;
        }
        for (int i = 0; i < hs.size(); i++) {
            HocSinhDTO student = hs.get(i);
            rowData[i][0] = student.getHocSinhID();
            rowData[i][1] = student.getTenHocSinh();
            rowData[i][2] = student.getGioiTinh();
            rowData[i][3] = student.getNgaySinh();
            rowData[i][4] = student.getDiaChi();
            rowData[i][5] = student.getDienThoai();
            rowData[i][6] = student.getIMG();
            PhanLopBUS phanlop = new PhanLopBUS();
            ArrayList<PhanLopDTO> dspl = phanlop.ds_phanlop();
            
            NamHocBUS nhbus = new NamHocBUS();
            ArrayList<NamHocDTO> listNH = nhbus.getList();
            for (PhanLopDTO phanLopDTO : dspl) {

                if(student.getHocSinhID().equals(phanLopDTO.getHocSinhID()))
                {
                    for (NamHocDTO namHocDTO : listNH) {
                        if(phanLopDTO.getNamHocID().equals(namHocDTO.getNamHocID()))
                        {
                            rowData[i][8] = namHocDTO.getNamHocBatDau()+"-"+namHocDTO.getNamHocKetThuc();
                        }
                    }
                    LopBUS lopbus = new LopBUS();
                    ArrayList<LopDTO> dslop = lopbus.list_lop();
                    for (LopDTO lop : dslop) {
                        if(phanLopDTO.getLopID().equals(lop.getLopID()))
                        {
                            rowData[i][7] = lop.getTenLop();
                            break;
                        }
                    }
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

        tblmodel = new DefaultTableModel(rowData, header) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Disables editing
            }
        };

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

    public void chooseImage() {
        JFileChooser fileChooser = new JFileChooser();
        // Thiết lập chế độ chỉ cho phép chọn file
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        // Hiển thị hộp thoại chọn file
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            // Lấy đường dẫn của tập tin hình ảnh được chọn
            String imagePath = fileChooser.getSelectedFile().getAbsolutePath();
            // Hiển thị đường dẫn trong JTextField
            String fileName = fileChooser.getSelectedFile().getName();
            pathAnhdd = fileName;
            tf[6].setText(fileName);

            // Tạo một ImageIcon từ đường dẫn hình ảnh
            ImageIcon imageIcon = new ImageIcon(imagePath);

            // Chỉnh kích thước của hình ảnh để phù hợp với JLabel
            Image image = imageIcon.getImage().getScaledInstance(lblimg.getWidth(), lblimg.getHeight(),
                    Image.SCALE_SMOOTH);

            // Tạo một ImageIcon mới từ hình ảnh đã được điều chỉnh kích thước
            ImageIcon scaledImageIcon = new ImageIcon(image);

            // Hiển thị hình ảnh trên JLabel
            lblimg.setIcon(scaledImageIcon);

        }
    }

    
    public void deleteRow() {
        int row = t.getSelectedRow();
        if (row != -1) {
            tblmodel.removeRow(row);
        }
        String hocSinhID = tf[0].getText();
        hsBUS.deleteHS(hocSinhID);
        clearTextFields();
    }

    public void updateRow() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = dateChooser.getDate();
        String dateString = sdf.format(date);

        // Lấy các giá trị từ các trường nhập
        String hocSinhID = tf[0].getText();
        String tenHocSinh = tf[1].getText();
        String gioiTinh = (String) genderComboBox.getSelectedItem();
        String ngaySinh = dateString;
        String soDienThoai = tf[4].getText();
        String diaChi = tf[5].getText();
        String IMG = tf[6].getText();

        HocSinhDTO hocSinh = new HocSinhDTO(hocSinhID, tenHocSinh, gioiTinh, ngaySinh, diaChi,
                soDienThoai);
        hocSinh.setIMG(IMG);

        // Gọi phương thức addHS() từ lớp QLHS_BUS để thêm học sinh vào cơ sở dữ liệu
        hsBUS.updateHS(hocSinh);

        Object[] rowData = { hocSinhID, tenHocSinh, gioiTinh, ngaySinh, diaChi, soDienThoai, IMG };

        int row = t.getSelectedRow();
        tblmodel.removeRow(row);
        tblmodel.addRow(rowData);
        clearTextFields();
    }

    public void clearTextFields() {
        tf[0].setText("");
        tf[1].setText("");
        genderComboBox.setSelectedItem(2);
        dateChooser.setDate(null);
        tf[4].setText("");
        tf[5].setText("");
        tf[6].setText("");
        lblimg.setIcon(null);
    }

    public boolean checkEmpty() {
        boolean isEmpty = 
                tf[1].getText().isEmpty() ||
                tf[4].getText().isEmpty() ||
                tf[5].getText().isEmpty();

        boolean isGenderEmpty = genderComboBox.getSelectedIndex() == -1;

        boolean isDateEmpty = dateChooser.getDate() == null;

        return isEmpty || isGenderEmpty || isDateEmpty;
    }

    private void tableMouseClicked(java.awt.event.MouseEvent evt) throws ParseException {
        int row = t.getSelectedRow();
        mahs = (String) t.getValueAt(row, 0);
        hoten = (String.valueOf(t.getValueAt(row, 1)));
        gioitinh = (String.valueOf(t.getValueAt(row, 2)));
        namsinh = (String.valueOf(t.getValueAt(row, 3)));
        diachi = (String.valueOf(t.getValueAt(row, 4)));
        sodienthoai = (String.valueOf(t.getValueAt(row, 5)));
        img = (String.valueOf(t.getValueAt(row, 6)));
        tenlop = (String.valueOf(t.getValueAt(row, 7)));
        
        tf[0].setText(mahs);
        tf[1].setText(hoten);
        genderComboBox.setSelectedItem(gioitinh);
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = sdf.parse(namsinh);
        dateChooser.setDate(date);
        
        tf[4].setText(diachi);
        tf[5].setText(sodienthoai);
        tf[6].setText(img);
        classComboBox.setSelectedItem(tenlop);
    
        // Kiểm tra xem trường img có trống không
        if (!img.isEmpty()) {
            // Đường dẫn tương đối tới ảnh
            String projectRootPath = System.getProperty("user.dir");
            String path = projectRootPath + "\\src\\image\\HocSinh\\" + img;
    
            // Kiểm tra tệp ảnh có tồn tại không
            File imgFile = new File(path);
            if (imgFile.exists()) {
                // Tạo ImageIcon từ file ảnh
                ImageIcon orgIcon_HS = new ImageIcon(path);
                Image scaleImg_HS = orgIcon_HS.getImage().getScaledInstance(lblimg.getWidth(), lblimg.getHeight(),
                        Image.SCALE_SMOOTH);
                ImageIcon scaledImage_HS = new ImageIcon(scaleImg_HS);
    
                // Hiển thị hình ảnh trên JLabel
                lblimg.setIcon(scaledImage_HS);
            } else {
                System.out.println("Ảnh không tồn tại: " + path);
                lblimg.setIcon(null); // Xóa ảnh nếu không tìm thấy
            }
        } else {
            lblimg.setIcon(null); // Xóa ảnh nếu không có ảnh nào
        }
    }
    

    public void btnAdd_actionPerformed() {
        ThemHocSinh themHS = new ThemHocSinh();
        Integer countHS = +hsBUS.CountHS() + 1;
        String hocSinhID = "HSK" + soKhoa + countHS;
        themHS.textField_mahs.setText(hocSinhID);

    }

   
    public void btnDelete_actionPerformed() {
        String mahs = tf[0].getText();
        System.out.println(mahs);
        if (mahs.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Hãy nhập ID học sinh cần xóa", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (hsBUS.checkMaHS(mahs) == false) {
            JOptionPane.showMessageDialog(this, "Không tồn tại ID này", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int result = JOptionPane.showConfirmDialog(this,
                "Bạn có chắc muốn xóa thành viên này",
                "Xác nhận",
                JOptionPane.YES_NO_OPTION,

                JOptionPane.QUESTION_MESSAGE);
        if (result == JOptionPane.YES_OPTION) {
            deleteRow();
            JOptionPane.showMessageDialog(this, "Bạn đã xóa thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

        } else if (result == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(this, "Xóa thất bại", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }

    public void btnSua_actionPerformed() {
        String mahs = tf[0].getText();

        if (mahs.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Hãy nhập ID học sinh cần sửa", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (hsBUS.checkMaHS(mahs) == false) {
            JOptionPane.showMessageDialog(this, "Không tồn tại ID này", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int result = JOptionPane.showConfirmDialog(this,
                "Bạn có chắc muốn sửa học sinh này",
                "Xác nhận",
                JOptionPane.YES_NO_OPTION,

                JOptionPane.QUESTION_MESSAGE);
        if (result == JOptionPane.YES_OPTION) {
            
            updateRow();
            JOptionPane.showMessageDialog(this, "Bạn đã sửa thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else if (result == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(this, "Bạn đã sửa thất bại", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void btnFind_actionPerformed() {
        searchText = JsearchText.getText().trim();
        String selectedOption = (String) searchselectBox.getSelectedItem();
        if (searchText.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Vui lòng nhập thông tin tìm kiếm",
                    "Thông báo",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        model = (DefaultTableModel) t.getModel();
        sorter = new TableRowSorter<>(model);
        t.setRowSorter(sorter);
        if (selectedOption.equals("Mã học sinh")) {
            sorter.setRowFilter(RowFilter.regexFilter(searchText.toUpperCase(), 0));
        } else if (selectedOption.equals("Họ và tên")) {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText, 1));
        }
        else if(selectedOption.equals("Lớp")){
            sorter.setRowFilter(RowFilter.regexFilter(searchText, 7));
        }
    }


    public void exportExcel() throws IOException {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Tập tin Excel", "xls");
        chooser.setFileFilter(filter);
        chooser.setDialogTitle("Lưu tệp");
        chooser.setAcceptAllFileFilterUsed(false);
        
        if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            // Lấy đường dẫn file và thêm .xls nếu chưa có
            String path = chooser.getSelectedFile().toString();
            if (!path.endsWith(".xls")) {
                path = path.concat(".xls");
            }
    
            Workbook workbook = new HSSFWorkbook();
            Sheet sheet = workbook.createSheet("DanhSachHocSinh");
            ArrayList<HocSinhDTO> ds_hs = new HocSinhBUS().getList();
        
            // Tạo hàng tiêu đề (Header row)
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Mã học sinh");
            headerRow.createCell(1).setCellValue("Họ tên");
            headerRow.createCell(2).setCellValue("Giới tính");
            headerRow.createCell(3).setCellValue("Năm sinh");
            headerRow.createCell(4).setCellValue("Địa chỉ");
            headerRow.createCell(5).setCellValue("Số điện thoại");
            headerRow.createCell(6).setCellValue("Ảnh chân dung");
            headerRow.createCell(7).setCellValue("Lớp");
            headerRow.createCell(8).setCellValue("Năm học");

            // Ghi dữ liệu từ danh sách cửa hàng vào các hàng tiếp theo
            int rowNum = 1;
            for (HocSinhDTO hocsinhdto: ds_hs) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(hocsinhdto.getHocSinhID());
                row.createCell(1).setCellValue(hocsinhdto.getTenHocSinh());
                row.createCell(2).setCellValue(hocsinhdto.getGioiTinh());
                row.createCell(3).setCellValue(hocsinhdto.getNgaySinh());
                row.createCell(4).setCellValue(hocsinhdto.getDiaChi());
                row.createCell(5).setCellValue(hocsinhdto.getDienThoai());
                row.createCell(6).setCellValue(hocsinhdto.getIMG());
                PhanLopDTO pl = new PhanLopBUS().get(hocsinhdto.getHocSinhID());
                ArrayList<LopDTO> dslop = new LopBUS().getList();
                for (LopDTO lopDTO : dslop) {
                    if(pl.getLopID().equals(lopDTO.getLopID()))
                    {
                        row.createCell(7).setCellValue(lopDTO.getTenLop());
                        break;
                    }else{
                        break;
                    }
                }
                NamHocDTO namhoc = new NamHocBUS().getNamHocByConditon(pl.getNamHocID());
                row.createCell(8).setCellValue(namhoc.getNamHocBatDau()+"-"+namhoc.getNamHocKetThuc());
            }
            // Ghi file Excel
            File file = new File(path);
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();

            try {
                FileOutputStream fos = new FileOutputStream(file);
                workbook.write(fos);
                // workbook.close();
                // fos.close();
                System.out.println("Excel file exported successfully to: " + path);
            } catch (IOException e) {
                e.printStackTrace();
                // Handle exception
            }
            JOptionPane.showMessageDialog(this, "IN THÀNH CÔNG");
            Desktop.getDesktop().open(file);
        }
    }
    


    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == JsearchText) {
            clearTextFields();
        }
        // throw new UnsupportedOperationException("Not supported yet."); // Generated
        // from
        // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // throw new UnsupportedOperationException("Not supported yet."); // Generated
        // from
        // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // throw new UnsupportedOperationException("Not supported yet."); // Generated
        // from
        // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseEntered(MouseEvent e) {

        if (e.getSource() == btnThem) {
            btnThem.setBackground(Color.red);
        }
        if (e.getSource() == btnXoa) {
            btnXoa.setBackground(Color.red);
        }
        if (e.getSource() == btnSua) {
            btnSua.setBackground(Color.red);
        }
        if (e.getSource() == btnFind) {
            btnFind.setBackground(Color.red);
        }
        if (e.getSource() == btnExpExcel) {
            btnExpExcel.setBackground(Color.green);
        }
        // throw new UnsupportedOperationException("Not supported yet."); // Generated
        // from
        // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
        // throw new UnsupportedOperationException("Not supported yet."); // Generated
        // from
        // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void actionPerformed(ActionEvent e) {
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
            // createExcel();
        }
    }

    
    public static void main(String[] args) throws SQLException {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(850, 670);
        QuanLiHocSinh_GV panel = new QuanLiHocSinh_GV(850, 670);
        frame.add(panel);
        frame.setVisible(true);
    }
}
