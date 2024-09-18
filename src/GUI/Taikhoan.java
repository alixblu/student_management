package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import BUS.phanquyenBUS;
import DTO.phanquyenDTO;
import BUS.ChangeAcc_BUS;
import BUS.QLHS_BUS;
import BUS.User_BUS;
import BUS.phanquyenBUS;
// import BUS.user_BUS;
import DTO.user;
import DTO.Account_DTO;
import DTO.HocKyDTO;
import DTO.phanquyenDTO;
import DTO.user;

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

// import org.apache.poi.ss.usermodel.*;
// import org.apache.poi.xssf.usermodel.XSSFCell;
// import org.apache.poi.xssf.usermodel.XSSFRow;
// import org.apache.poi.xssf.usermodel.XSSFSheet;
// import org.apache.poi.xssf.usermodel.XSSFWorkbook;
// import org.apache.poi.common.io.FileOutputStream;
/*
* Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
* Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
*/
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
// import org.apache.poi.ss.usermodel.Row;
// import org.apache.commons.io.*;;

/**
 *
 * @author atuan
 */
public final class Taikhoan extends JPanel implements MouseListener, ActionListener {
    private String user, password, role, diachi, enable, sodienthoai, img;
    private JLabel lbluser, lblTenhs, lblrole, lblDiachi, lblimg;
    private JButton btnThem, btnXoa, btnSua, btnFind, btnReset, btnExpExcel;
    private DefaultTableModel tblmodel;
    // private JTable tbl;
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
    User_BUS uBUS = new User_BUS();
    private static String pathAnhdd = "";
    private JComboBox<String> roleComboBox;
    ChangeAcc_BUS accBUS = new ChangeAcc_BUS();
    phanquyenBUS pqbus = new phanquyenBUS();

    public Taikhoan(int width, int height) throws SQLException {
        this.width = width;
        this.height = height;
        this.setSize(width, height);
        init();
        
        // Adding action listeners
        btnThem.addActionListener(this);
        btnXoa.addActionListener(this);
        btnSua.addActionListener(this);
        btnFind.addActionListener(this);
        btnReset.addActionListener(this);
        btnExpExcel.addActionListener(this);
    
        // Adding mouse listeners
        btnThem.addMouseListener(this);
        btnXoa.addMouseListener(this);
        btnSua.addMouseListener(this);
        btnFind.addMouseListener(this);
        btnReset.addMouseListener(this);
        JsearchText.addMouseListener(this);
        btnExpExcel.addMouseListener(this);
    }
    

    public void init() throws SQLException {

        // this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Color myColor = new Color(99, 116, 198);
        Color searchPanel = new Color(180, 204, 227);
        this.setLayout(new BorderLayout());
        JPanel p3 = Timtaikhoan();
        // p3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0,0,0) , 4
        // , true));

        p3.setPreferredSize(new Dimension(0, 50));
        p3.setBackground(searchPanel);

        JPanel p1 = JTaikhoan();
        p1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 4, true));
        p1.setBackground(myColor);
        p1.setPreferredSize(new Dimension(0, 0));

        JPanel p2 = new JPanel();
        p2.setLayout(new FlowLayout(1, 0, 0));
        p2.add(initTable());
        p2.setPreferredSize(new Dimension(0, 430));
        p2.setBackground(Color.gray);

        this.add(p1, BorderLayout.CENTER);
        this.add(p2, BorderLayout.SOUTH);
        this.add(p3, BorderLayout.NORTH);
        this.setSize(new Dimension(width, height));
        this.setVisible(true);
        // this.setLocationRelativeTo(null);
        // this.setResizable(false);
    }

    public JPanel Timtaikhoan() {
        Color imgSearchlbl = new Color(255,222,89);
        Color btnResets = new Color(52, 48, 128);

        JPanel JSearch = new JPanel();
        JSearch.setLayout(new FlowLayout(1, 10, 5));

        btnFind = new JButton("Tìm kiếm");
        btnFind.setPreferredSize(new Dimension(100, 40));
        btnFind.setBackground(imgSearchlbl);
        btnFind.setVisible(true);

        JsearchText = new JTextField();
        JsearchText.setPreferredSize(new Dimension(300, 40));

        JLabel lblSearch = new JLabel("Tìm kiếm theo: ");
        lblSearch.setFont(new Font("arial", Font.BOLD, 14));
        String searchOption[] = { "Username", "Role" };
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

        JSearch.add(btnFind);
        JSearch.add(JsearchText);
        JSearch.add(lblSearch);
        JSearch.add(searchselectBox);
        JSearch.add(btnReset);

        return JSearch;

    }

    public JPanel JChucnang() {
        Color myColor = new Color(99, 116, 198);
        JPanel Pchucnang = new JPanel();
        Pchucnang.setLayout(new GridLayout(2, 2, 15, 15)); // Chia thành 2 hàng và 2 cột

        // Nút Thêm
        java.net.URL imageURL_Add = getClass().getResource("/image/btnAdd.png");
        ImageIcon orgIcon = new ImageIcon(imageURL_Add);
        Image scaleImg = orgIcon.getImage().getScaledInstance(155, 40, Image.SCALE_SMOOTH);
        btnThem = new JButton(new ImageIcon(scaleImg));
        btnThem.setPreferredSize(new Dimension(155, 40));
        btnThem.setBorder(raisedBevel);
        Pchucnang.add(btnThem); // Thêm nút vào panel

        // Nút Xóa
        java.net.URL imageURL_Del = getClass().getResource("/image/btnDelete.png");
        ImageIcon orgIcon_Del = new ImageIcon(imageURL_Del);
        Image scaleImg_Del = orgIcon_Del.getImage().getScaledInstance(155, 40, Image.SCALE_SMOOTH);
        btnXoa = new JButton("XÓA",new ImageIcon(scaleImg_Del));
        btnXoa.setForeground(Color.white);
        btnXoa.setPreferredSize(new Dimension(155, 40));
        btnXoa.setBorder(raisedBevel);
        Pchucnang.add(btnXoa); // Thêm nút vào panel

        // Nút Sửa
        java.net.URL imageURL_Edit = getClass().getResource("/image/btnEdit.png");
        ImageIcon orgIcon_Edit = new ImageIcon(imageURL_Edit);
        Image scaleImg_Edit = orgIcon_Edit.getImage().getScaledInstance(155, 40, Image.SCALE_SMOOTH);
        btnSua = new JButton(new ImageIcon(scaleImg_Edit));
        btnSua.setPreferredSize(new Dimension(155, 40));
        btnSua.setBorder(raisedBevel);
        Pchucnang.add(btnSua); // Thêm nút vào panel

        // Nút Tìm kiếm
        // java.net.URL imageURL_Find = getClass().getResource("/image/btnsearch_qlhs1.png");
        // ImageIcon orgIcon_Find = new ImageIcon(imageURL_Find);
        // Image scaleImg_Find = orgIcon_Find.getImage().getScaledInstance(155, 40, Image.SCALE_SMOOTH);
        // btnFind = new JButton(new ImageIcon(scaleImg_Find));
        // btnFind.setPreferredSize(new Dimension(155, 40));
        // btnFind.setBorder(raisedBevel);
        // Pchucnang.add(btnFind); // Thêm nút vào panel

        // Tạo một nút mới để Export
        java.net.URL imageURL_ExpExcel = getClass().getResource("/image/export_excel.png");
        ImageIcon orgIcon_ExpExcel = new ImageIcon(imageURL_ExpExcel);
        Image scaleImg_ExpExcel = orgIcon_ExpExcel.getImage().getScaledInstance(230, 100, Image.SCALE_SMOOTH);
        btnExpExcel = new JButton(new ImageIcon(scaleImg_ExpExcel));
        btnExpExcel.setPreferredSize(new Dimension(155, 40));
        btnExpExcel.setBorder(raisedBevel);
        btnExpExcel.setBackground(myColor);
        Pchucnang.add(btnExpExcel); // Thêm nút vào panel

        return Pchucnang;
    }

     public JPanel JTaikhoan() {
        JPanel Ptaikhoan = new JPanel();
        Ptaikhoan.setLayout(null);

        String[] arrTaikhoan = { "Username", "Password", "Role", "Trạng thái" };
        int length = arrTaikhoan.length;
        tf = new JTextField[length];
        buttons = new JButton[length];
        Ptaikhoan.setLayout(null);

        int toadoXbutton = 10;
        int toadoYbutton = 10;
        int toadoXTextfield = 150;
        int toadoYTextfield = 10;
        int x = 230;
        int y = 15;

        for (int i = 0; i < arrTaikhoan.length; i++) {
            buttons[i] = new JButton(arrTaikhoan[i]);
            buttons[i].setBounds(toadoXbutton, toadoYbutton +20, 120, 30);
            buttons[i].setHorizontalAlignment(JButton.CENTER);
            buttons[i].setName("btn" + i);
            toadoYbutton += 35;
            Ptaikhoan.add(buttons[i]);

            if (i != 2) {
                tf[i] = new JTextField();
                tf[i].setBounds(toadoXTextfield, toadoYTextfield +20, 320, 30);
                tf[i].setFont(new Font("Arial", Font.BOLD, 12));
                tf[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                tf[i].setName("text" + i);
                toadoYTextfield += 35;
                Ptaikhoan.add(tf[i]);
            } else {
                phanquyenBUS pqbus = new phanquyenBUS();
                pqbus.listquyen();
                ArrayList<phanquyenDTO> dsquyen = pqbus.getlistquyen();

                roleComboBox = new JComboBox<>();
                    for (phanquyenDTO pq : dsquyen) {
                        roleComboBox.addItem(pq.getMaquyen());
                    }
                roleComboBox.setBounds(toadoXTextfield, toadoYTextfield +20, 320, 30);
                Ptaikhoan.add(roleComboBox);
                toadoYTextfield += 35;
            }

            y += 35;
        }

        JPanel Pchucnang = JChucnang();
        Pchucnang.setBounds(510, 35, 290, y - 70);
        Pchucnang.setBackground(new Color(99, 116, 198));
        Ptaikhoan.add(Pchucnang);
        Ptaikhoan.setPreferredSize(new Dimension(x, y));
        return Ptaikhoan;
    }

    // Hàm giả tạo tạoPanelChucNang, thay thế với triển khai thực tế.
    public JScrollPane initTable() throws SQLException {
        t = new JTable();
        t.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        scrollpane = new JScrollPane(t);
        scrollpane.setPreferredSize(new Dimension(846, 430));
        String[] header = { "Username", "Password", "Role", "Trạng thái" };

        if (uBUS.getList() == null) {
            uBUS.list();
        }

        ArrayList<user> u = uBUS.getList();
        if (u.isEmpty()) {
            System.out.println("No data found");
        } else {
            System.out.println("Data loaded successfully");
        }

        Object[][] rowData = new Object[u.size()][4];
        for (int i = 0; i < u.size(); i++) {
            user user = u.get(i);
            rowData[i][0] = user.getusername();
            rowData[i][1] = user.getpassword();
            rowData[i][2] = user.getrole();
            rowData[i][3] = user.getenable();
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
                    Logger.getLogger(Taikhoan.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        return scrollpane;
    }

    public void addRow() {
        String username = tf[0].getText();
        String password = tf[1].getText();
        String role = (String) roleComboBox.getSelectedItem();
        String enable = tf[3].getText();

        Object[] rowData = { username, password, role, enable };
        tblmodel.addRow(rowData);
        clearTextFields();
    }
    

    public void deleteRow() {
        int row = t.getSelectedRow();
        if (row != -1) {
            tblmodel.removeRow(row);
        }
        String username = tf[0].getText();
        uBUS.delete(username);
        clearTextFields();
    }

    public void updateRow() {
        // Get the values from the text fields and combo box
        String username = tf[0].getText();
        String password = tf[1].getText();
        String role = (String) roleComboBox.getSelectedItem(); // Get the selected item from the combo box
        String enable = tf[3].getText();
    
        // Create a new user object with updated values
        user updatedUser = new user(username, password, role, enable);
    
        // Update the user in the database
        uBUS.updateuser(updatedUser);
    
        // Get the index of the selected row
        int row = t.getSelectedRow();
    
        // Update the values in the table model
        tblmodel.setValueAt(username, row, 0);
        tblmodel.setValueAt(password, row, 1);
        tblmodel.setValueAt(role, row, 2);
        tblmodel.setValueAt(enable, row, 3);
    
        // Clear the text fields
        clearTextFields();
    }
    

    public void clearTextFields() {
        tf[0].setText("");
        tf[1].setText("");
        // tf[2].setText("");
        tf[3].setText("");
        // tf[4].setText("");

    }

    public boolean checkEmpty() {
        boolean isEmpty = tf[0].getText().isEmpty() ||
                tf[1].getText().isEmpty() ||
                
                tf[3].getText().isEmpty();

        return isEmpty;
    }

    private void tableMouseClicked(java.awt.event.MouseEvent evt) throws ParseException {
        int row = t.getSelectedRow();
        user = (String) t.getValueAt(row, 0);
        password = (String.valueOf(t.getValueAt(row, 1)));
        role = (String.valueOf(t.getValueAt(row, 2)));
        enable = (String.valueOf(t.getValueAt(row, 3)));
    
        // Set text fields
        tf[0].setText(user);
        tf[1].setText(password);
        tf[3].setText(enable);
    
        // Set role combo box
        String selectedRole = (String) t.getValueAt(row, 2);
        int index = findIndexOfRole(selectedRole);
        if (index != -1) {
            roleComboBox.setSelectedIndex(index);
        }
    }
    
    private int findIndexOfRole(String selectedRole) {
        for (int i = 0; i < roleComboBox.getItemCount(); i++) {
            if (roleComboBox.getItemAt(i).equals(selectedRole)) {
                return i;
            }
        }
        return -1; // If not found
    }
    
    
    public void resetTable() {
        // Xóa toàn bộ dữ liệu trong bảng
        // int rowCount = tblmodel.getRowCount();
        // for (int i = rowCount - 1; i >= 0; i--) {
        //     tblmodel.removeRow(i);
        // }
        // Tải lại dữ liệu từ nguồn dữ liệu mới vào bảng

            // Khởi tạo lại bảng với dữ liệu mới
            clearTextFields();
            t.repaint(); // Cập nhật hiển thị của bảng
            t.setRowSorter(new TableRowSorter<>(tblmodel));
    }
    
    
    
    public void btnAdd_actionPerformed() {
        if (checkEmpty()) {
            JOptionPane.showMessageDialog(this, "Hãy điền đầy đủ các thông tin", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
    
        String user = tf[0].getText();
        System.out.println(user);
    
        if (uBUS.check(user) == true) {
            JOptionPane.showMessageDialog(this, "Username này đã tồn tại", "CHECK",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }    
        int result = JOptionPane.showConfirmDialog(this,
                "Bạn có chắc muốn Thêm tài khoản này",
                "Xác nhận",
                JOptionPane.YES_NO_OPTION,
    
                JOptionPane.QUESTION_MESSAGE);
        if (result == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(this,
                    "Thêm thành công",
                    "Chức năng thêm",
                    JOptionPane.INFORMATION_MESSAGE);
            System.out.println("Ban chon them");
            tf[0].requestFocus();
            autoCreateAccount();
            addRow();
        }
    }
    
    

    public void btnDelete_actionPerformed() {
        String user = tf[0].getText();
        System.out.println(user);
        if (user.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Hãy nhập username cần xóa", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (uBUS.check(user) == false) {
            JOptionPane.showMessageDialog(this, "Không tồn tại username này", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int result = JOptionPane.showConfirmDialog(this,
                "Bạn có chắc muốn xóa tài khoản này",
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
        String user = tf[0].getText();

        if (user.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Hãy nhập username cần sửa", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (uBUS.check(user) == false) {
            JOptionPane.showMessageDialog(this, "Không tồn tại username này", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int result = JOptionPane.showConfirmDialog(this,
                "Bạn có chắc muốn sửa tài khoản này",
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
        if (selectedOption.equals("Username")) {
            sorter.setRowFilter(RowFilter.regexFilter(searchText, 0));
        } else if (selectedOption.equals("Role")) {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText, 2));
        }
    }
    

    public void exportExcel() throws IOException {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Tập tin Excel", "xls");
        chooser.setFileFilter(filter);
        chooser.setDialogTitle("Lưu tệp");
        chooser.setAcceptAllFileFilterUsed(false);
        if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            String path = chooser.getSelectedFile().toString().concat(".xls");

            Workbook workbook = new HSSFWorkbook();
            Sheet sheet = workbook.createSheet("DanhSachTaikhoan");
            Row headerRow = sheet.createRow(0); // Header row at index 0
            String[] headers = { "STT", "username", "Password", "role", "enable"};

            // Creating header cells
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
            }

            ArrayList<user> dsu = uBUS.getList();
            for (int i = 0; i < dsu.size(); i++) {
                Row row = sheet.createRow(i + 1); // Data rows start from index 1

                user User = dsu.get(i);
                System.out.println(User.getusername());

                row.createCell(0).setCellValue(i + 1);
                row.createCell(1).setCellValue(User.getusername());
                row.createCell(2).setCellValue(User.getpassword());
                row.createCell(3).setCellValue(User.getrole());
                row.createCell(4).setCellValue(User.getenable());
            }

            // String path = "D:/Coding/N2_HK2/DAJAVA/java_nhom_9/Excel/hsss.xlsx";
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

    public void autoCreateAccount() {
        String username = tf[0].getText();
        String password = tf[1].getText();
        String role = (String) roleComboBox.getSelectedItem();
        String enable = tf[3].getText();
        
        // Kiểm tra xem thông tin người dùng nhập vào có hợp lệ không
        if(username.isEmpty() || password.isEmpty() || role.isEmpty() || enable.isEmpty()) {
            // Thông báo lỗi hoặc xử lý tùy ý
            return;
        }
        
        User_BUS accBUS = new User_BUS();
        user acc = new user(username, password, role, enable);
        accBUS.add(acc);
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
            btnFind.setBackground(new Color(200,175,73));
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
            btnFind.setBackground(new Color(255,222,89));
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
        }
        // Other button actions
        else if (e.getSource() == btnXoa) {
            btnDelete_actionPerformed();
        } else if (e.getSource() == btnSua) {
            btnSua_actionPerformed();
        } else if (e.getSource() == btnFind) {
            System.out.println("Button Find clicked");
            btnFind_actionPerformed();
        } else if (e.getSource() == btnReset) {
            System.out.println("Button Reset clicked");
            resetTable();
        } else if (e.getSource() == btnExpExcel) {
            try {
                System.out.println("Button Export clicked");
                exportExcel();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    

    public static void main(String[] args) {
        // Tạo cửa sổ JFrame và thiết lập các thuộc tính cơ bản
        JFrame frame = new JFrame("Tài khoản");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(850, 670);
        frame.setLocationRelativeTo(null); // Đặt vị trí ở giữa màn hình

        // Tạo một đối tượng của DoiMK và thêm nó vào JFrame
        try {
            Taikhoan doiMKPanel = new Taikhoan(850, 670);
            frame.add(doiMKPanel);
        } catch (Exception e) {
            // Xử lý lỗi ở đây, ví dụ: in ra thông báo lỗi
            System.err.println("Lỗi khi tạo đối tượng DoiMK: " + e.getMessage());
            // hoặc có thể throw lại exception để xử lý ở mức cao hơn
            e.printStackTrace();
        }

        // Hiển thị JFrame
        frame.setVisible(true);
    }
}
