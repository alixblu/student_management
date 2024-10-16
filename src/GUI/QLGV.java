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
import java.beans.PropertyChangeListener;
import java.sql.SQLException;
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
import com.toedter.calendar.JDateChooser;
import java.util.Calendar;
import BUS.ChangeAcc_BUS;
import BUS.MonHocBUS;
import DTO.MonHocDTO;
import DTO.user;
import BUS.GiaoVienBUS;
import BUS.QLPhanCongBUS;
import BUS.User_BUS;
import DTO.Account_DTO;
import DTO.GiaoVienDTO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.swing.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author vhuyn
 */
public final class QLGV extends JPanel implements MouseListener, ActionListener {
    private String mahs, hoten, gioitinh, diachi, namsinh, sodienthoai, phanMon, img;
    private JLabel lblimg;
    private JButton btnThem, btnXoa, btnSua, btnFind, btnReset, btnExpExcel;
    private DefaultTableModel tblmodel;
    // private JTable tbl
    private JScrollPane scrollpane;
    JTextField[] tf;
    JButton[] buttons;
    JTable t;
    int width, height;
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
    private JComboBox<String> phanmonComboBox;
    private ArrayList<MonHocDTO> listmh;
    GiaoVienBUS gvBUS = new GiaoVienBUS();
    User_BUS userBus = new User_BUS();
    MonHocBUS mhBus = new MonHocBUS();
    private static String pathAnhdd = "";
    ChangeAcc_BUS accBUS = new ChangeAcc_BUS();
    QLPhanCongBUS pcBUS = new QLPhanCongBUS();
    private String imagePath;
    private String destinationPath;


    // Arraylist <MonHocDTO> = new MonHocDTO();
    public QLGV(int width, int height) throws SQLException {
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
        JPanel p3 = searchGV();

        p3.setPreferredSize(new Dimension(0, 60));
        p3.setBackground(searchPanel);

        JPanel p1 = JHocsinh();
        p1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 4, true));
        p1.setBackground(myColor);
        p1.setPreferredSize(new Dimension(0, 200));

        JPanel p2 = new JPanel();
        p2.setLayout(new FlowLayout(1, 0, 0));
        p2.add(initTable());
        p2.setPreferredSize(new Dimension(0, 280));
        p2.setBackground(Color.gray);

        this.add(p1, BorderLayout.CENTER);
        this.add(p2, BorderLayout.SOUTH);
        this.add(p3, BorderLayout.NORTH);
        this.setSize(new Dimension(width, height));
        this.setVisible(true);
        // this.setLocationRelativeTo(null);
        // this.setResizable(false);
    }

    public JPanel searchGV() {
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
        // imgSearch.setOpaque(true);
        // ArrayList<String> listlop = pcBUS.getTenLopList();

        // imgSearch.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0,
        // 0, 0), 4, true));

        JsearchText = new JTextField();
        JsearchText.setPreferredSize(new Dimension(300, 40));
        MonHocBUS mhBus = new MonHocBUS(1); // Khởi tạo với tham số cần thiết
        ArrayList<MonHocDTO> listmh = mhBus.getList();

        // Tạo danh sách tên môn học
        ArrayList<String> tenMonHocs = mhBus.getTenMonHocs();
        JLabel lblSearch = new JLabel("Tìm kiếm theo: ");
        lblSearch.setFont(new Font("arial", Font.BOLD, 14));
        String searchOption[] = { "Mã giáo viên", "Họ và tên" };
        searchselectBox = new JComboBox<>(searchOption);
        JComboBox<String> phanmonComboBox = new JComboBox<>(tenMonHocs.toArray(new String[0]));

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
        JSearch.add(phanmonComboBox); // this
        JSearch.add(btnReset);

        return JSearch;

    }

    public JPanel JChucnang() {
        Color myColor = new Color(99, 116, 198);
        JPanel Pchucnang = new JPanel();
        Pchucnang.setLayout(new FlowLayout(0, 5, 10));
        // Pchucnang.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0,
        // 0, 0), 4, true));

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

        Pchucnang.setBackground(myColor);
        defaultColor = btnThem.getBackground();
        Pchucnang.add(btnThem);
        Pchucnang.add(btnXoa);
        Pchucnang.add(btnSua);
        Pchucnang.add(btnFind);
        Pchucnang.add(btnExpExcel);
        return Pchucnang;
    }

    public int calculateAge(Date birthDate) {
        Calendar birthCal = Calendar.getInstance();
        birthCal.setTime(birthDate);
        int birthYear = birthCal.get(Calendar.YEAR);

        Calendar currentCal = Calendar.getInstance();
        int currentYear = currentCal.get(Calendar.YEAR);

        return currentYear - birthYear; // Đơn giản tính toán tuổi
    }

    public JPanel JHocsinh() {
        JPanel Phocsinh = new JPanel();
        Phocsinh.setLayout(null);

        pcBUS.listMagv();
        pcBUS.listTenlop();

        ArrayList<String> listlop = pcBUS.getTenLopList();
        ArrayList<MonHocDTO> listmh = mhBus.getList();
        ArrayList<String> listmagv = pcBUS.getMaGVList();

        String[] arrHocsinh = { "Mã giáo viên ", "Tên giáo viên", "Giới tính", "Năm sinh", "Địa chỉ", "Số điện thoại",
                "Phân môn", "Chọn ảnh" };
        int length = arrHocsinh.length;
        tf = new JTextField[length];
        buttons = new JButton[length];

        int toadoXbutton = 190;
        int toadoYbutton = 10;
        int toadoXTextfield = 330;
        int toadoYTextfield = 10;
        int x = 230;
        int y = 15;

        for (int i = 0; i < arrHocsinh.length; i++) {
            if (i == 7) {
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

            if (i == 6) {
                MonHocBUS mhBus = new MonHocBUS();
                ArrayList<String> tenMonHocs = mhBus.getTenMonHocs();
                phanmonComboBox = new JComboBox<>(tenMonHocs.toArray(new String[0]));
                phanmonComboBox.setBounds(toadoXTextfield, toadoYTextfield, 320, 30);
                Phocsinh.add(phanmonComboBox);
                toadoYTextfield = toadoYTextfield + 35;
            } else if (i == 3) {
                dateChooser = new JDateChooser();
                dateChooser.setDateFormatString("dd/MM/yyyy");
                dateChooser.setBounds(toadoXTextfield, toadoYTextfield, 320, 30);
                Phocsinh.add(dateChooser);

                // Kiểm tra tuổi hợp lệ khi người dùng thay đổi ngày sinh
                dateChooser.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
                    @Override
                    public void propertyChange(PropertyChangeEvent evt) {
                        if ("date".equals(evt.getPropertyName())) {
                            Date selectedDate = dateChooser.getDate();
                            if (selectedDate != null) {
                                // Gọi phương thức calculateAge từ lớp hiện tại
                                int age = QLGV.this.calculateAge(selectedDate); // Sử dụng tên lớp bên ngoài
                                if (!checkAgeValid(age)) {
                                    dateChooser.setDate(null); // Xóa ngày đã chọn
                                }
                            }
                        }
                    }
                });

                toadoYTextfield = toadoYTextfield + 35;
            } else if (i == 2) {
                String[] genders = { "Nam", "Nữ", "Khác" };
                genderComboBox = new JComboBox<>(genders);
                genderComboBox.setBounds(toadoXTextfield, toadoYTextfield, 320, 30);
                Phocsinh.add(genderComboBox);
                toadoYTextfield = toadoYTextfield + 35;
            } else {
                tf[i] = new JTextField();
                tf[i].setBounds(toadoXTextfield, toadoYTextfield, 320, 30);
                tf[i].setFont(new Font("Arial", Font.BOLD, 12));
                tf[i].setBorder(border);
                tf[i].setName("text" + i);
                toadoYTextfield = toadoYTextfield + 35;
                Phocsinh.add(tf[i]);
            }

            tf[0].setEditable(false);
            y = y + 35;
        }

        x = x + 180;
        JPanel Pchucnang = JChucnang();
        Pchucnang.setBounds(660, 3, 170, y);
        Phocsinh.add(Pchucnang);

        lblimg = new JLabel();
        lblimg.setBounds(0, 0, 180, y - 80);
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
        scrollpane.setPreferredSize(new Dimension(846, 310));
        String[] header = { "Mã giáo viên", "Họ và tên", "Giới tính", "Năm sinh", "Địa chỉ", "Số điện thoại",
                "Phân Môn",
                "Ảnh chân dung" };

        if (gvBUS.getList() == null)
            gvBUS.listGV();
        ArrayList<GiaoVienDTO> hs = gvBUS.getList();
        Object[][] rowData = new Object[hs.size()][8];
        for (int i = 0; i < hs.size(); i++) {
            GiaoVienDTO student = hs.get(i);
            rowData[i][0] = student.getMaGV();
            rowData[i][1] = student.getTenGV();
            rowData[i][2] = student.getGioiTinh();
            rowData[i][3] = student.getNamSinh();
            rowData[i][4] = student.getDiachi();
            rowData[i][5] = student.getDienThoai();
            rowData[i][6] = student.getphanMon();
            rowData[i][7] = student.getIMG();
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

    public boolean checkAgeValid(int age) {
        // Lấy năm sinh từ JDateChooser
        java.util.Date selectedDate = dateChooser.getDate();
        if (selectedDate == null) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn ngày sinh!");
            return false;
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(selectedDate);
        int yearOfBirth = cal.get(Calendar.YEAR);

        // Tính tuổi
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        age = currentYear - yearOfBirth;

        // Kiểm tra tuổi có nằm trong khoảng 20 đến 55
        if (age < 20 || age > 55) {
            JOptionPane.showMessageDialog(null, "Tuổi phải lớn hơn 20 và nhỏ hơn 55!");
            return false;
        }

        return true;
    }

    public void chooseImage() {
        JFileChooser fileChooser = new JFileChooser();
        // Thiết lập chế độ chỉ cho phép chọn file
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        // Hiển thị hộp thoại chọn file
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            // Lấy đường dẫn của tập tin hình ảnh được chọn
            imagePath = fileChooser.getSelectedFile().getAbsolutePath();
            // Hiển thị đường dẫn trong JTextField
            String fileName = fileChooser.getSelectedFile().getName();
            String pathAnhdd_1 = fileName;
            tf[7].setText(pathAnhdd_1);
            
            ImageIcon imageIcon = new ImageIcon(imagePath);
            
            Image image = imageIcon.getImage().getScaledInstance(lblimg.getWidth(), lblimg.getHeight(),
                    Image.SCALE_SMOOTH);
            ImageIcon scaledImageIcon = new ImageIcon(image);
            lblimg.setIcon(scaledImageIcon);

        }
    }

    public void luuanhkhixacnhan(String imgName, String imagePath) {
        String projectRootPath = System.getProperty("user.dir");
        String destinationFolder = projectRootPath + "\\src\\image\\GiaoVien";
        String destinationPath = destinationFolder + "\\" + imgName;
        File directory = new File(destinationFolder);
        if (!directory.exists()) {
            directory.mkdirs(); // Tạo thư mục nếu không tồn tại
        }
        File sourceFile = new File(imagePath); // Tệp ảnh nguồn
        File destinationFile = new File(destinationPath); // Tệp đích
    
        try {
            // Sao chép tệp, thay thế nếu tệp đã tồn tại
            Files.copy(sourceFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File đã được lưu thành công: " + destinationPath);
        } catch (IOException e) {
            System.out.println("Có lỗi khi lưu tệp: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public boolean addRow() {
        if (tf[1].getText().isEmpty() || tf[4].getText().isEmpty() || tf[5].getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin!");
            return false;
        }

        String soDienThoai = tf[5].getText();
        if (!isValidPhoneNumber(soDienThoai)) {
            JOptionPane.showMessageDialog(null, "Số điện thoại không hợp lệ. Phải bắt đầu từ 0 và đủ 10 số!");
            return false;
        }

        String tenGV = tf[1].getText();
        if (!isValidTeacherName(tenGV)) {
            JOptionPane.showMessageDialog(null,
                    "Tên giáo viên không hợp lệ. Chỉ được chứa chữ hoa, chữ thường và khoảng trắng!");
            return false;
        }

        phanmonComboBox.setEnabled(false);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = dateChooser.getDate();
        String dateString = sdf.format(date); 
    
        Integer countGV = gvBUS.CountGV() + 1;
        String giaovienID = "GV" + countGV;
        String gioiTinh = (String) genderComboBox.getSelectedItem();
        String phanMon = (String) phanmonComboBox.getSelectedItem();
        String IMG = tf[7].getText();
        String sdt = tf[5].getText();
    
        GiaoVienDTO giaovien = new GiaoVienDTO(giaovienID, tenGV, gioiTinh, IMG, dateString, soDienThoai, phanMon, tf[4].getText());
    
        user accgv = new user(giaovienID, sdt, "GV", "1"); // "GV" là vai trò giáo viên, "1" là mật khẩu mặc định
        gvBUS.addGV(giaovien);
        User_BUS userBUS = new User_BUS();
        userBUS.add(accgv);
    
        Object[] rowData = { giaovienID, tenGV, gioiTinh, dateString, tf[4].getText(), soDienThoai, phanMon, IMG };
        tblmodel.addRow(rowData);
        luuanhkhixacnhan(IMG,imagePath);
        clearTextFields();

        return true; // Trả về true để xác nhận rằng việc thêm thành công
    }
    
    
    private boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("0\\d{9}"); // Kiểm tra bắt đầu từ 0 và có đủ 10 số
    }

    private boolean isValidTeacherName(String name) {
        return name.matches("^[\\p{L}\\s]+$"); // Chỉ cho phép chữ và khoảng trắng
    }

    public void deleteRow() {
        int row = t.getSelectedRow();
        if (row != -1) {
            tblmodel.removeRow(row);
        }
        String hocSinhID = tf[0].getText();
        gvBUS.deleteGV(hocSinhID);
        clearTextFields();
    }

    
    public void updateRow(String giaovienID) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = dateChooser.getDate();

        if (date == null) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập ngày sinh hợp lệ.");
            return;
        }

        String dateString = sdf.format(date);

        String tenGiaoVien = tf[1].getText();
        String gioiTinh = (String) genderComboBox.getSelectedItem();
        String soDienThoai = tf[5].getText();
        String diaChi = tf[4].getText();
        String phanMon = (String) phanmonComboBox.getSelectedItem();
        String IMG = tf[7].getText();

        if (tenGiaoVien.isEmpty() || gioiTinh.isEmpty() || diaChi.isEmpty() || phanMon.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin.");
            return;
        }

        if (!isValidPhoneNumber(soDienThoai)) {
            JOptionPane.showMessageDialog(null, "Số điện thoại không hợp lệ. Phải bắt đầu từ 0 và đủ 10 số!");
            return;
        }

        if (!isValidTeacherName(tenGiaoVien)) {
            JOptionPane.showMessageDialog(null,
                    "Tên giáo viên không hợp lệ. Chỉ được chứa chữ hoa, chữ thường và khoảng trắng!");
            return;
        }

        GiaoVienDTO giaovien = new GiaoVienDTO(giaovienID, tenGiaoVien, gioiTinh, IMG, dateString, soDienThoai, phanMon,
                diaChi);

        gvBUS.updateGV(giaovien);

        int row = t.getSelectedRow();
        if (row != -1) {
            Object[] rowData = { giaovienID, tenGiaoVien, gioiTinh, dateString, diaChi, soDienThoai, phanMon, IMG };
            tblmodel.removeRow(row); // Xóa dòng cũ
            tblmodel.insertRow(row, rowData); // Chèn lại dòng mới
            clearTextFields(); // Xóa các trường nhập liệu sau khi cập nhật thành công
        } else {
            JOptionPane.showMessageDialog(null, "Không có hàng nào được chọn để cập nhật.");
        }
        luuanhkhixacnhan(IMG,imagePath);

    }

    public void clearTextFields() {
        phanmonComboBox.setEnabled(true);
        tf[0].setText("");
        tf[1].setText("");
        genderComboBox.setSelectedItem(null); // Thiết lập cho genderComboBox trống
        dateChooser.setDate(null);
        tf[4].setText("");
        tf[5].setText("");
        phanmonComboBox.setSelectedItem(null); // Thiết lập cho phanmonComboBox trống
        tf[7].setText("");
        lblimg.setIcon(null);
    }

    public boolean checkEmpty() {
        boolean isEmpty =
                // tf[0].getText().isEmpty() ||
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
        phanMon = (String.valueOf(t.getValueAt(row, 6)));
        img = (String.valueOf(t.getValueAt(row, 7)));

        tf[0].setText(mahs);
        tf[1].setText(hoten);
        genderComboBox.setSelectedItem(gioitinh);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = sdf.parse(namsinh);
        dateChooser.setDate(date);

        tf[4].setText(diachi);
        tf[5].setText(sodienthoai);
        phanmonComboBox.setSelectedItem(phanMon);
        tf[7].setText(img);

        // Hiển thị ảnh nếu có
        if (!img.isEmpty()) {
            String path = "/image/GiaoVien/" + img;
            java.net.URL imgHS = getClass().getResource(path);
            ImageIcon orgIcon_HS = new ImageIcon(imgHS);
            Image scaleImg_HS = orgIcon_HS.getImage().getScaledInstance(lblimg.getWidth(), lblimg.getHeight(),
                    Image.SCALE_SMOOTH);
            ImageIcon scaledImage_HS = new ImageIcon(scaleImg_HS);
            lblimg.setIcon(scaledImage_HS);
        } else {
            lblimg.setIcon(null);
        }

        phanmonComboBox.setEnabled(false);
    }

    public void btnAdd_actionPerformed() {
        if (checkEmpty()) {
            JOptionPane.showMessageDialog(this, "Hãy điền đầy đủ các thông tin", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!isValidTeacherInfo()) {
            return;
        }

        int result = JOptionPane.showConfirmDialog(this,
                "Bạn có chắc muốn Thêm giáo viên này?",
                "Xác nhận",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (result == JOptionPane.YES_OPTION) {
            addRow(); // Gọi phương thức addRow để thêm giáo viên
            JOptionPane.showMessageDialog(this, "Thêm thành công", "Chức năng thêm", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Đã hủy thao tác thêm giáo viên", "Thông báo",
                    JOptionPane.INFORMATION_MESSAGE);
        }

        tf[0].requestFocus(); // Đặt con trỏ vào trường ID giáo viên
    }

    private boolean isValidTeacherInfo() {
        String tenGiaoVien = tf[1].getText();
        String soDienThoai = tf[5].getText();
        String diaChi = tf[4].getText();
        String IMG = tf[7].getText();

        // Kiểm tra tính hợp lệ cho các trường thông tin cần thiết
        if (!isValidPhoneNumber(soDienThoai)) {
            JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ. Phải bắt đầu từ 0 và đủ 10 số!", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (gvBUS.checkPhoneNumberExists(soDienThoai)) {
            JOptionPane.showMessageDialog(this, "Số điện thoại này đã tồn tại!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!isValidTeacherName(tenGiaoVien)) {
            JOptionPane.showMessageDialog(this,
                    "Tên giáo viên không hợp lệ. Chỉ được chứa chữ hoa, chữ thường và khoảng trắng!", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!diaChi.matches("^[a-zA-Z0-9\\s\\.,\\-\\'/áàảãạăắằẳẵặâấầẩẫậéèẻẽẹêếềểễệíìỉĩịóòỏõọôốồổỗộơớờởỡợúùủũụưứừửữựýỳỷỹỵđĐ]+$")) {
            JOptionPane.showMessageDialog(null, "Địa chỉ có thể chứa các ký tự chữ, số, dấu cách, và dấu '/'!");
            return false;
        }
        
    
        return true; // Nếu tất cả các trường thông tin hợp lệ
    }

    private boolean isValidTeacherInfosua() {
        String tenGiaoVien = tf[1].getText();
        String soDienThoai = tf[5].getText();
        String diaChi = tf[4].getText();
        String IMG = tf[7].getText();

        // Kiểm tra tính hợp lệ cho các trường thông tin cần thiết
        if (!isValidPhoneNumber(soDienThoai)) {
            JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ. Phải bắt đầu từ 0 và đủ 10 số!", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        // Kiểm tra số điện thoại đã tồn tại (trừ số điện thoại hiện tại)
        System.out.println(tf[0].getText());
        GiaoVienDTO currentTeacher = gvBUS.getGV(tf[0].getText()); // Lấy giáo viên hiện tại
        if (currentTeacher != null && !currentTeacher.getDienThoai().equals(soDienThoai)) {
            if (gvBUS.checkPhoneNumberExists(soDienThoai)) {
                JOptionPane.showMessageDialog(this, "Số điện thoại này đã tồn tại!", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return false; // Đảm bảo rằng phương thức này là void
            }
        }

        if (!isValidTeacherName(tenGiaoVien)) {
            JOptionPane.showMessageDialog(this,
                    "Tên giáo viên không hợp lệ. Chỉ được chứa chữ hoa, chữ thường và khoảng trắng!", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (diaChi.isEmpty() || !diaChi.matches("^[a-zA-Z0-9\\s\\.,\\-\\'/áàảãạăắằẳẵặâấầẩẫậéèẻẽẹêếềểễệíìỉĩịóòỏõọôốồổỗộơớờởỡợúùủũụưứừửữựýỳỷỹỵđĐ]+$")) {
            JOptionPane.showMessageDialog(null, "Địa chỉ có thể chứa các ký tự chữ, số, dấu cách, và dấu '/'!");
            return false;
        }

        return true; // Nếu tất cả các trường thông tin hợp lệ
    }

    public void btnDelete_actionPerformed() {
        String magv = tf[0].getText();
        System.out.println(magv);
        if (magv.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Hãy nhập ID Giáo viên cần xóa", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (gvBUS.checkMagv(magv) == false) {
            JOptionPane.showMessageDialog(this, "Không tồn tại ID này", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int result = JOptionPane.showConfirmDialog(this,
                "Bạn có chắc muốn xóa thành viên này",
                "Xác nhận",
                JOptionPane.YES_NO_OPTION,

                JOptionPane.QUESTION_MESSAGE);
        if (result == JOptionPane.YES_OPTION) {
            System.out.println("Ban chon đồn ý xóa");
            deleteRow();
        } else if (result == JOptionPane.NO_OPTION) {
            System.out.println("Bạn chọn không đồng ý xóa");
        }
    }

    public void btnSua_actionPerformed() {
        String magv = tf[0].getText();

        if (magv.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Hãy nhập ID giáo viên cần sửa", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!gvBUS.checkMagv(magv)) {
            JOptionPane.showMessageDialog(this, "Không tồn tại ID này", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!isValidTeacherInfosua()) {
            return;
        }
        // Xác nhận sửa giáo viên
        int result = JOptionPane.showConfirmDialog(this,
                "Bạn có chắc muốn sửa giáo viên này?",
                "Xác nhận",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (result == JOptionPane.YES_OPTION) {
            updateRow(magv); // Gọi phương thức cập nhật thông tin giáo viên
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
        if (selectedOption.equals("Mã giáo viên")) {
            sorter.setRowFilter(RowFilter.regexFilter(searchText.toUpperCase(), 0));
        } else if (selectedOption.equals("Họ và tên")) {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText, 1));
        }
    }

    public void exportExcel() throws IOException {
        ArrayList<GiaoVienDTO> dsgv = gvBUS.getList();

        if (dsgv == null || dsgv.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Danh sách giáo viên rỗng, không thể xuất tệp!", "Thông báo",
                    JOptionPane.WARNING_MESSAGE);
            return; // Exit the method, no further execution
        }

        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Tập tin Excel", "xls");
        chooser.setFileFilter(filter);
        chooser.setDialogTitle("Lưu tệp");
        chooser.setAcceptAllFileFilterUsed(false);
        if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            String path = chooser.getSelectedFile().toString().concat(".xls");

            Workbook workbook = new HSSFWorkbook();
            Sheet sheet = workbook.createSheet("DanhSachHocSinh");
            Row headerRow = sheet.createRow(0);
            String[] headers = { "STT", "Mã giáo viên", "Họ và tên", "Giới Tính", "Năm Sinh", "Địa chỉ",
                    "Số điện thoại", "Phân môn", "Ảnh chân dung" };

            // Creating header cells
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
            }

            for (int i = 0; i < dsgv.size(); i++) {
                Row row = sheet.createRow(i + 1); // Data rows start from index 1

                GiaoVienDTO gv = dsgv.get(i);
                row.createCell(0).setCellValue(i + 1);
                row.createCell(1).setCellValue(gv.getMaGV());
                row.createCell(2).setCellValue(gv.getTenGV());
                row.createCell(3).setCellValue(gv.getGioiTinh());
                row.createCell(4).setCellValue(gv.getNamSinh());
                row.createCell(5).setCellValue(gv.getDiachi());
                row.createCell(6).setCellValue(gv.getDienThoai());
                row.createCell(7).setCellValue(gv.getphanMon());
                row.createCell(8).setCellValue(gv.getIMG());
            }

            File file = new File(path);
            if (file.exists()) {
                file.delete(); // Delete existing file if it exists
            }
            file.createNewFile(); // Create a new file

            try {
                FileOutputStream fos = new FileOutputStream(file);
                workbook.write(fos);
                System.out.println("Excel file exported successfully to: " + path);
            } catch (IOException e) {
                e.printStackTrace(); // Handle exception
            }

            JOptionPane.showMessageDialog(this, "IN THÀNH CÔNG");
            Desktop.getDesktop().open(file); // Open the file after saving
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
        QLGV panel = new QLGV(850, 670);
        frame.add(panel);
        frame.setVisible(true);
    }
}
