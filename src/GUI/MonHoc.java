package GUI;



import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.Icon;
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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;
import com.toedter.calendar.JDateChooser;

import BUS.ChangeAcc_BUS;
import DTO.MonHocDTO;
import BUS.MonHocBUS;
import DAO.MonHocDAO;
import DTO.Account_DTO;
import java.awt.GridLayout;

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
import java.awt.Color;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

public final class MonHoc extends JPanel implements MouseListener, ActionListener {
    private String mmh, tmh;
    private JButton btnThem, btnXoa, btnSua, btnFind, btnReset, btnExpExcel;
    private DefaultTableModel tblmodel;
    private JScrollPane scrollpane;
    JTextField[] tf;
    JButton[] buttons;
    JTable t;
    int width, height;
    private JComboBox<String> searchselectBox;
    private final Border raisedBevel = BorderFactory.createRaisedBevelBorder();
    Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
    private Color defaultColor;
    private JTextField JsearchText;
    DefaultTableModel model;
    TableRowSorter<DefaultTableModel> sorter;
    JDateChooser dateChooser;
    
    JComboBox<String> genderComboBox;
    MonHocBUS mhBUS=new MonHocBUS();
    private static String pathAnhdd = "";
   // ChangeAcc_BUS accBUS = new ChangeAcc_BUS();
    public MonHoc(int width, int height) throws SQLException {
        this.width = width;
        this.height = height;
        this.setSize(width, height);
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
//gui
    public void init() throws SQLException {
        //main
        //Color myColor = Color.RED;
        Color myColor = new Color(99, 116, 198);
        //top
        Color searchPanel = new Color(180, 204, 227);
       //Color searchPanel = Color.RED;

        this.setLayout(new BorderLayout());
        JPanel p3 = SearchHocSinh();
        // p3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0,0,0) , 4
        // , true));
        p3.setPreferredSize(new Dimension(0, 60));
        p3.setBackground(searchPanel);

        JPanel p1 = JMonHoc();
        p1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 4, true));
        p1.setBackground(myColor);
        p1.setPreferredSize(new Dimension(0, 0));

        JPanel p2 = new JPanel();
        p2.setLayout(new FlowLayout(1, 0, 0));
        p2.add(initTable());
        p2.setPreferredSize(new Dimension(0, 400));
        p2.setBackground(Color.gray);

        this.add(p1, BorderLayout.CENTER);
        this.add(p2, BorderLayout.SOUTH);
        this.add(p3, BorderLayout.NORTH);
        this.setSize(new Dimension(width, height));
        this.setVisible(true);
        tf[0].setEnabled(false);
        btnSua.setEnabled(false);
        
    }
//tìm kiếm, 
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
        String searchOption[] = { "Mã Môn Học", "Tên Môn Học" };
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
        //màu của chức năng
        //Color myColor = Color.RED;
        Color myColor = new Color(99, 116, 198);
        JPanel Pchucnang = new JPanel();
        Pchucnang.setLayout(new GridLayout(3,2,15,15));

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

    //xắp xep vị trí
    public JPanel JMonHoc() {
        JPanel PMonHoc = new JPanel();
        PMonHoc.setLayout(null);
        String[] arrMonHoc = { "Mã Môn", "Tên Môn", };
        int length = arrMonHoc.length;
        tf = new JTextField[length];
        buttons = new JButton[length];
        PMonHoc.setLayout(null);
        int toadoXbutton = 10;
        int toadoYbutton = 10;
        int toadoXTextfield = 150;
        int toadoYTextfield = 10;
        int x = 230;
        int y = 15;
        for (int i = 0; i < arrMonHoc.length; i++) {
            buttons[i] = new JButton(arrMonHoc[i]);
            buttons[i].setBounds(toadoXbutton +25, toadoYbutton + 40, 100, 30);
            buttons[i].setHorizontalAlignment(JButton.CENTER);
            buttons[i].setName("btn" + i);
            
            toadoYbutton = toadoYbutton + 35;
            PMonHoc.add(buttons[i]);

             {
                tf[i] = new JTextField();
                tf[i].setBounds(toadoXTextfield +25, toadoYTextfield +40, 220, 30);
                tf[i].setFont(new Font("Arial", Font.BOLD, 12));
                tf[i].setBorder(border);
                tf[i].setName("text" + i);
                toadoYTextfield = toadoYTextfield + 35;
                PMonHoc.add(tf[i]);
            }
            y = y + 35;
        }
        x = x + 180;
        JPanel Pchucnang = JChucnang();
        Pchucnang.setBounds(510, 20, 290, 150);
        PMonHoc.add(Pchucnang);
        PMonHoc.setPreferredSize(new Dimension(x, y));
        return PMonHoc;
    }
//main
    public  JScrollPane initTable() throws SQLException {
        t = new JTable();
        t.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        scrollpane = new JScrollPane(t);
        scrollpane.setPreferredSize(new Dimension(846, 440));
        String[] header = { "Mã môn học", "Tên Môn Học."};
               
        if (mhBUS.getList() == null)
            mhBUS.list();
        ArrayList<MonHocDTO> mh = mhBUS.getList();
        if (mh.isEmpty()) {
            System.out.println("No data found");
        } else {
            System.out.println("Data loaded successfully");
        }

        Object[][] rowData = new Object[mh.size()][2];
        for (int i = 0; i < mh.size(); i++) {
            MonHocDTO monhoc = mh.get(i);
            rowData[i][0] = monhoc.getMonHocID();
            rowData[i][1] = monhoc.getTenMonHoc();
           
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
    	Integer countHS = +mhBUS.CountMH() + 1;
    	String monhocID = "MH" + String.valueOf(countHS);
        String TenMonHoc = tf[1].getText();
        MonHocDTO monhoc = new MonHocDTO(monhocID, TenMonHoc);
        mhBUS.addMH(monhoc);
        Object[] rowData = { monhocID, TenMonHoc };
        tblmodel.addRow(rowData);
        clearTextFields();
    }

    public void deleteRow() {
        int row = t.getSelectedRow();
        if (row != -1) {
            tblmodel.removeRow(row);
        }
        String MonHocid = tf[0].getText();
        mhBUS.deleteMH(MonHocid);
        clearTextFields();
    }

    public void updateRow() {
        String MonHocid = tf[0].getText();
        String TenMonHoc = tf[1].getText();
        MonHocDTO monhoc =new MonHocDTO(MonHocid, TenMonHoc);
        mhBUS.updateMH(monhoc);
        Object[] rowData = { MonHocid,TenMonHoc };
        int row = t.getSelectedRow();
        tblmodel.removeRow(row);
        tblmodel.addRow(rowData);
        clearTextFields();
    }

    public void clearTextFields() {
        tf[0].setText("");
        tf[1].setText("");
        
    }

    public boolean checkEmpty() {
//        boolean isEmpty = tf[0].getText().isEmpty() ||
    	 boolean isEmpty=tf[1].getText().isEmpty();
        return isEmpty ;
    }

    private void tableMouseClicked(java.awt.event.MouseEvent evt) throws ParseException {
        int row = t.getSelectedRow();
        mmh = (String) t.getValueAt(row, 0);
        tmh = (String.valueOf(t.getValueAt(row, 1)));
        tf[0].setText(mmh);
        tf[1].setText(tmh);
    }

    public void btnAdd_actionPerformed() {
        String tenmh = tf[1].getText();
    	Integer countHS = +mhBUS.CountMH() + 1;
    	String monhocID = "MH" + String.valueOf(countHS);
//    	tf[0].setText(monhocID);
    	
        if (checkEmpty()) {
            JOptionPane.showMessageDialog(this, "Hãy điền đầy đủ tên môn học", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (tenmh.matches("[a-zA-Z0-9\\s]+")) {
            System.out.println("...");
        } else {
            JOptionPane.showMessageDialog(this, "Tên môn học không được chứa kí tự đặc biệt!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        
        int result = JOptionPane.showConfirmDialog(this,"Bạn có chắc muốn Thêm môn học này","Xác nhận",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (result == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(this,
                    "Thêm thành công",
                    "Chức năng thêm",
                    JOptionPane.INFORMATION_MESSAGE);
            System.out.println("Ban chon them");
            tf[0].requestFocus();
           // autoCreateAccount();
            addRow();
        }
        
    }

    public void btnDelete_actionPerformed() {
        String monhoc = tf[0].getText();
        System.out.println(monhoc);
        if (monhoc.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Hãy nhập ID Môn Học cần xóa", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (mhBUS.checkMH(monhoc) == false) {
            JOptionPane.showMessageDialog(this, "Không tồn tại ID này", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int result = JOptionPane.showConfirmDialog(this,
                "Bạn có chắc muốn xóa môn học này",
                "Xác nhận",
                JOptionPane.YES_NO_OPTION,

                JOptionPane.QUESTION_MESSAGE);
        if (result == JOptionPane.YES_OPTION) {
            System.out.println("Ban chon dong  y xóa");
            deleteRow();
        } else if (result == JOptionPane.NO_OPTION) {
            System.out.println("Bạn chọn không đồng ý xóa");
        }
    }

    public void btnSua_actionPerformed() {
        String mamh = tf[0].getText();
        if (mamh.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Hãy nhập ID môn học cần sửa", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (mhBUS.checkMaMH(mamh) == false) {
            JOptionPane.showMessageDialog(this, "Không tồn tại ID này", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        

        int result = JOptionPane.showConfirmDialog(this,
                "Bạn có chắc muốn sửa học sinh này",
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
        String searchText = JsearchText.getText().trim();
        String selectedOption = (String) searchselectBox.getSelectedItem();

        if (searchText.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Vui lòng nhập thông tin tìm kiếm",
                    "Thông báo",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        model = (DefaultTableModel) t.getModel();
        sorter = new TableRowSorter<>(model);
        t.setRowSorter(sorter);

        // Debugging output
        System.out.println("searchText: " + searchText);
        System.out.println("selectedOption: " + selectedOption);

        if (selectedOption.equals("Mã Môn Học")) {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText.toUpperCase(), 0));
        } else if (selectedOption.equals("Tên Môn Học")) {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText, 1));
        }
    }
//----------------------------------------------------------------------------------------
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
            Sheet sheet = workbook.createSheet("DanhSachMonHoc");
            Row headerRow = sheet.createRow(0); // Header row at index 0
            String[] headers = { "STT", "Mã môn học", "Tên môn học" };

            // Creating header cells
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
            }

            ArrayList<MonHocDTO> dsmh = mhBUS.getList();
            for (int i = 0; i < dsmh.size(); i++) {
                Row row = sheet.createRow(i + 1); // Data rows start from index 1

                MonHocDTO monhoc = dsmh.get(i);
                System.out.println(monhoc.getTenMonHoc());
                row.createCell(0).setCellValue(i + 1);
                row.createCell(1).setCellValue(monhoc.getMonHocID());
                row.createCell(2).setCellValue(monhoc.getTenMonHoc());
                
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

    // public void autoCreateAccount() {
    //     accBUS = new ChangeAcc_BUS();
    //     String username = tf[0].getText();
    //     String password = tf[5].getText();
    //     Account_DTO acc = new Account_DTO(username, password);
    //     accBUS.Add(acc);
    // }
    // public void autoCreateAccount() {
    //     //accBUS = new ChangeAcc_BUS();
    //     accBUS = new ChangeAcc_BUS();
    //     String username = tf[0].getText();
    //     String password = tf[5].getText();
    //     Account_DTO acc = new Account_DTO(username, password);
    //     accBUS.Add(acc);
    // }

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
            System.out.println("ban da click vao tim kiem");
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
        MonHoc panel = new MonHoc(850, 670);
        frame.add(panel);
        frame.setVisible(true);
    }
}