package GUI;
import java.awt.GridLayout;

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

import BUS.ChangeAcc_BUS;
import BUS.GiaoVienBUS;
import BUS.KQ_HocSinhCaNamBUS;

import java.time.LocalDate;
import DTO.GiaoVienDTO;
import DTO.KQ_HocSinhCaNamDTO;
import DTO.NamHocDTO;
import BUS.NamHocBUS;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.Desktop;
import java.io.File;
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
public final class NamhocGUI extends JPanel implements MouseListener, ActionListener {
    private String manamhoc, namhocbatdau, namhocketthuc, hocky;
    private JButton btnThem, btnFind, btnReset, btnExpExcel , btnHK2;
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
    NamHocBUS nhBUS = new NamHocBUS();
    Object[][] rowData;

    ChangeAcc_BUS accBUS = new ChangeAcc_BUS();

    public NamhocGUI(int width, int height) throws SQLException {
        this.width = width;
        this.height = height;
        init();
        btnThem.addMouseListener(this);
        btnThem.addActionListener(this);
        btnFind.addActionListener(this);
        btnFind.addMouseListener(this);
        JsearchText.addMouseListener(this);
        btnExpExcel.addActionListener(this);
        btnExpExcel.addMouseListener(this);
        btnHK2.addMouseListener(this);
        btnHK2.addActionListener(this);
    }

    public void init() throws SQLException {

        // this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Color myColor = new Color(99, 116, 198);
        Color searchPanel = new Color(180, 204, 227);
        this.setLayout(new BorderLayout());
        JPanel p3 = SearchHocSinh();
        // p3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0,0,0) , 4
        // , true));
        p3.setPreferredSize(new Dimension(0, 60));
        p3.setBackground(searchPanel);

        JPanel p1 = JHocsinh();
        p1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 4, true));
        p1.setBackground(myColor);
        p1.setPreferredSize(new Dimension(0, 0));

        JPanel p2 = new JPanel();
        p2.setLayout(new FlowLayout(1, 0, 0));
        p2.add(initTable());
        p2.setPreferredSize(new Dimension(0, 380));
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
        String searchOption[] = { "Mã năm học", "Năm học bắt đầu", "Năm học kết thúc" };
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
        // Color myColor = Color.PINK;
        Color myColor = new Color(99, 116, 198);
        JPanel Pchucnang = new JPanel();
        Pchucnang.setLayout(new GridLayout(3,2,15,15));

        java.net.URL imageURL_Add = getClass().getResource("/image/btnAdd.png");
        ImageIcon orgIcon = new ImageIcon(imageURL_Add);
        Image scaleImg = orgIcon.getImage().getScaledInstance(155, 40, Image.SCALE_SMOOTH);

        btnThem = new JButton(new ImageIcon(scaleImg));
        btnThem.setPreferredSize(new Dimension(155, 40));
        btnThem.setBorder(raisedBevel);


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

      
        btnHK2 = new JButton("Tạo HK2 tương ứng");
        btnHK2.setPreferredSize(new Dimension(155, 40));
        btnHK2.setBorder(raisedBevel);
        btnHK2.setEnabled(false);



        Pchucnang.setBackground(myColor);
        defaultColor = btnThem.getBackground();
        Pchucnang.add(btnThem);
        Pchucnang.add(btnFind);
        Pchucnang.add(btnExpExcel);
        Pchucnang.add(btnHK2);
        return Pchucnang;
    }

    public JPanel JHocsinh() {
        JPanel Phocsinh = new JPanel();
        Phocsinh.setLayout(null);
        String[] arrHocsinh = { "Mã năm học", "Tên năm học", "Học kỳ" };
        int length = arrHocsinh.length;
        tf = new JTextField[length];
        buttons = new JButton[length];
        // Phocsinh.setLayout(null);
        int toadoXbutton = 10;
        int toadoYbutton = 10;
        int toadoXTextfield = 150;
        int toadoYTextfield = 10;
        int x = 250;
        int y = 15;
        for (int i = 0; i < arrHocsinh.length; i++) {
            buttons[i] = new JButton(arrHocsinh[i]);
            buttons[i].setBounds(toadoXbutton +25, toadoYbutton +30, 120, 30);
            buttons[i].setHorizontalAlignment(JButton.CENTER);
            buttons[i].setName("btn" + i);
            toadoYbutton = toadoYbutton + 35;
            Phocsinh.add(buttons[i]);
            {
                tf[i] = new JTextField();
                tf[i].setBounds(toadoXTextfield +35, toadoYTextfield +30, 220, 30);
                tf[i].setFont(new Font("Arial", Font.BOLD, 12));
                tf[i].setBorder(border);
                tf[i].setName("text" + i);
                tf[i].setEditable(false);
                tf[i].setForeground(Color.orange);
                tf[i].setBackground(Color.gray);
                toadoYTextfield = toadoYTextfield + 35;
                Phocsinh.add(tf[i]);
            }
            y = y + 35;
        }

        x = x + 180;
        JPanel Pchucnang = JChucnang();
        Pchucnang.setBounds(510, 25, 290, 150);
        Phocsinh.add(Pchucnang);

        Phocsinh.setPreferredSize(new Dimension(x, y));

        return Phocsinh;
    }

    public JScrollPane initTable() throws SQLException {

        t = new JTable();
        t.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        scrollpane = new JScrollPane(t);
        scrollpane.setPreferredSize(new Dimension(846, 400));
        String[] header = { "Mã năm học", "Năm bắt đầu", "Năm kết thúc" , "Học kỳ" };

        if (nhBUS.getList() == null)
            nhBUS.listNH();
        ArrayList<NamHocDTO> nh = nhBUS.getList();
        rowData = new Object[nh.size()][7];
        for (int i = 0; i < nh.size(); i++) {
            NamHocDTO namhoc = nh.get(i);
            rowData[i][0] = namhoc.getNamHocID();
            rowData[i][1] = namhoc.getNamHocBatDau();
            rowData[i][2] = namhoc.getNamHocKetThuc();
            rowData[i][3] = namhoc.getHocKy();
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
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

        return scrollpane;
    }


    public void addRow() {
        int NamBatDau = LocalDate.now().getYear();
        int NamKetThuc = NamBatDau + 1;
        String NamHocid = NamBatDau + "" + NamKetThuc;
        NamHocDTO namhoc = new NamHocDTO(NamHocid, NamBatDau, NamKetThuc, "1", 1);
        nhBUS.addNH(namhoc);
        //reset submit status
        GiaoVienBUS gvbus = new GiaoVienBUS();
        ArrayList<GiaoVienDTO> dsgv = gvbus.getList();
        for(GiaoVienDTO gv: dsgv){
            gvbus.setSubmit(gv.getMaGV(), 0);
        }
        Object[] rowData = { NamHocid, NamBatDau, NamKetThuc, 1 };
        tblmodel.addRow(rowData);
        clearTextFields();
    }

    public void updateEnable(){
        nhBUS.updateEnable();
    }

    public void clearTextFields() {
        tf[0].setText("");
        tf[1].setText("");
        tf[2].setText("");
        btnHK2.setEnabled(false);

    }


    private void tableMouseClicked(java.awt.event.MouseEvent evt) throws ParseException, SQLException {
        int row = t.getSelectedRow();
        manamhoc = (String) t.getValueAt(row, 0);
        namhocbatdau = (String.valueOf(t.getValueAt(row, 1)));
        namhocketthuc = (String.valueOf(t.getValueAt(row, 2)));
        hocky = (String.valueOf(t.getValueAt(row, 3)));

        tf[0].setText(manamhoc);
        tf[1].setText(namhocbatdau+"-"+namhocketthuc);
        tf[2].setText(hocky);

        
        if(nhBUS.ktraEnabel(manamhoc) == 0){
            btnHK2.setEnabled(false);
        }else if(nhBUS.ktraEnabel((manamhoc)) == 1 && tf[2].getText().equals("1") ){
            btnHK2.setEnabled(true);
        }
    }

    public void btnAdd_actionPerformed() {
        if (nhBUS.getList() == null)
            nhBUS.listNH();
        int namhoc1 = LocalDate.now().getYear();
        int namkt = namhoc1+1;
        System.out.println(namhoc1);
        ArrayList<NamHocDTO> nh = nhBUS.getList();
        for (int i = 0; i < nh.size(); i++) {
            NamHocDTO namhoc = nh.get(i);
            System.out.println(namhoc);
            if (namhoc1 == namhoc.getNamHocBatDau()) { 
                System.out.println("đã vào");
                JOptionPane.showMessageDialog(this, "Năm học "+namhoc1+"-"+namkt+" đã tồn tại", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
    
        int result = JOptionPane.showConfirmDialog(this,
                "Bạn có chắc muốn thêm " + namhoc1 +"-"+ namkt+" năm học này",
                "Xác nhận",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        if (result == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(this,
                    "Thêm thành công",
                    "Chức năng thêm",
                    JOptionPane.INFORMATION_MESSAGE);
            System.out.println("Bạn chọn thêm");
            tf[0].requestFocus();
            //reset submit GV 
            GiaoVienBUS gvbus = new GiaoVienBUS();
            gvbus.resetSubmit();
            updateEnable();
            addRow();
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

        if (selectedOption.equals("Mã năm học")) {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText.toUpperCase(), 0));
        } else if (selectedOption.equals("Năm học bắt đầu")) {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText, 1));
        } else if (selectedOption.equals("Năm học kết thúc")) {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText, 1));
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
            Sheet sheet = workbook.createSheet("DanhSachHocSinh");
            Row headerRow = sheet.createRow(0); // Header row at index 0
            String[] headers = { "Mã năm học", "Năm bắt đầu", "Năm kết thúc" , "Học kỳ" };

            // Creating header cells
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
            }
            ArrayList<NamHocDTO> dsnh = nhBUS.getList();
            for (int i = 0; i < dsnh.size(); i++) {
                Row row = sheet.createRow(i + 1); // Data rows start from index 1

                NamHocDTO namhoc = dsnh.get(i);
                // System.out.println(hocSinh.getDiaChi());

                row.createCell(0).setCellValue(namhoc.getNamHocID());
                row.createCell(1).setCellValue(namhoc.getNamHocBatDau());
                row.createCell(2).setCellValue(namhoc.getNamHocKetThuc());
                row.createCell(3).setCellValue(namhoc.getHocKy());
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
    

    public void btnHK2_actionPerformed() throws SQLException{
        KQ_HocSinhCaNamBUS ktcn = new KQ_HocSinhCaNamBUS(1);
        ArrayList<KQ_HocSinhCaNamDTO> dscn = ktcn.getList();
        String manamhoc = tf[0].getText();
        for(KQ_HocSinhCaNamDTO cn : dscn){
            System.out.println("đã vào");
            if(cn.getNamHocID().equals(manamhoc)){
                if(cn.getKetQua().equals("")){
                    JOptionPane.showMessageDialog(this, "Chưa kết thúc học kì 1 nên không tạo HK2");
                    return;
                }
            }
        }
        String ma2 = manamhoc.substring(0, manamhoc.length() - 2) + "02";
        if (nhBUS.ktraManh(ma2) == 1) {
            JOptionPane.showMessageDialog(t, "Học kỳ 2 của năm học " +tf[1].getText()+" dã được tạo");
            return;
        }
        
        int result = JOptionPane.showConfirmDialog(this,
                "Bạn muốn thêm HK2 cho năm học : " + tf[1].getText(),
                "Xác nhận",
                JOptionPane.YES_NO_OPTION,

                JOptionPane.QUESTION_MESSAGE);
        if (result == JOptionPane.YES_OPTION) {
            int NamBatDau = LocalDate.now().getYear();
            int NamKetThuc = NamBatDau + 1;
            String namhocid = tf[0].getText();
            Object[] rowData = { namhocid, NamBatDau, NamKetThuc, 2 };
            int row = t.getSelectedRow();
            tblmodel.removeRow(row);
            tblmodel.addRow(rowData);
            nhBUS.updateHocKy(namhocid);
            //reset submit GV
            GiaoVienBUS gvbus = new GiaoVienBUS();
            gvbus.resetSubmit();
            clearTextFields();
            btnHK2.setEnabled(false);
        } else if (result == JOptionPane.NO_OPTION) {
            System.out.println("Bạn chọn không thêm HK2");
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
        }
        else if (e.getSource() == btnFind) {
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
                e1.printStackTrace();
            }
        } else if (e.getSource() == btnHK2) {
            try {
                btnHK2_actionPerformed();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }

    }
    public static void main(String[] args) throws SQLException {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(850, 670);
        NamhocGUI panel = new NamhocGUI(850, 670);
        frame.add(panel);
        frame.setVisible(true);
    }
}
