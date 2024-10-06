/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.event.*;
import java.awt.print.*;
import javax.swing.table.TableRowSorter;
import java.util.List;
import java.util.ArrayList;
import java.sql.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.*;
import javax.swing.table.TableRowSorter;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import BUS.HocSinhBUS;
import BUS.KQ_HocSinhCaNamBUS;
import BUS.LopBUS;
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

import java.util.HashSet;

/**
 *
 * @author PHUONG ANH
 */
public class ThongKe extends JPanel{
    private JFrame f;
    private JPanel topThongKe, selectPanel, radioPanel, dropdownPanel, totalPanel, btnPanel, contentThongKe;
    private JLabel l1, l2;
    private JLabel b0, b1, b2, b3, b4, b5;
    private JComboBox<String> optionLop, optionHL, optionHK, optionHP, optionNam, optionKQ;
    private JTextField s;
    private JButton showBtn, exportBtn, printBtn;
    private DefaultTableModel tblModel;
    TableRowSorter<DefaultTableModel> sorter;

    private JScrollPane scrollPane;
    private JTable t;
    int width, height;
    KQ_HocSinhCaNamBUS kqbus = new KQ_HocSinhCaNamBUS(1);
    HocSinhBUS hsbus = new HocSinhBUS(1);
    NamHocBUS nhbus = new NamHocBUS(1);
    LopBUS lopbus = new LopBUS(1);
    PhanLopBUS plbus = new PhanLopBUS(1);


    ArrayList<HocSinhDTO> dshs;
    ArrayList<KQ_HocSinhCaNamDTO> dskq;
    ArrayList<NamHocDTO> dsnh;
    ArrayList<LopDTO> dslop;


    public ThongKe(int width, int height) throws SQLException {
        // f = new JFrame();
        // this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setSize(new Dimension(width, height));
        // this.setLocationRelativeTo(null);
        // this.setResizable(false);

        topThongKe = new JPanel();
        topThongKe.setLayout(new BorderLayout());
        topThongKe.setPreferredSize(new Dimension(0, 150));
        topThongKe.setBackground(new Color(180, 204, 227));

        selectPanel = new JPanel();
        selectPanel.setLayout(new BoxLayout(selectPanel, BoxLayout.Y_AXIS));
        selectPanel.setOpaque(false);

        l1 = new JLabel("DANH SÁCH THỐNG KÊ                 ");
        l1.setFont(new Font("Arial", Font.BOLD, 20));
        l1.setBorder(new EmptyBorder(10, 0, 0, 0));
        radioPanel = new JPanel();
        radioPanel.setOpaque(false);

        b0 = new JLabel("Lớp");
        b1 = new JLabel("Học lực");
        b2 = new JLabel("Hạnh kiểm");
        b3 = new JLabel("Học phí");
        b4 = new JLabel("Năm học");
        b5 = new JLabel("Kết qủa");

        b0.setFont(new Font("Arial", Font.PLAIN, 16));
        b1.setFont(new Font("Arial", Font.PLAIN, 16));
        b2.setFont(new Font("Arial", Font.PLAIN, 16));
        b3.setFont(new Font("Arial", Font.PLAIN, 16));
        b4.setFont(new Font("Arial", Font.PLAIN, 16));
        b5.setFont(new Font("Arial", Font.PLAIN, 16));

        int topMargin = 10;
        int leftMargin = 25;
        int bottomMargin = 0;
        int rightMargin = 30;
        b1.setBorder(new EmptyBorder(topMargin, leftMargin, bottomMargin, rightMargin));
        b2.setBorder(new EmptyBorder(topMargin, leftMargin, bottomMargin, rightMargin));
        b3.setBorder(new EmptyBorder(topMargin, leftMargin, bottomMargin, rightMargin));
        b4.setBorder(new EmptyBorder(topMargin, leftMargin, bottomMargin, rightMargin));
        b5.setBorder(new EmptyBorder(topMargin, leftMargin, bottomMargin, rightMargin));

        dropdownPanel = new JPanel();
        dropdownPanel.setOpaque(false);

        dslop = lopbus.getList();
        ArrayList<String> c1 = new ArrayList<>();
        c1.add("Tất cả");
        for(LopDTO pc: dslop){
            String tenlop = lopbus.get(pc.getLopID()).getTenLop();
            c1.add(tenlop);
        }
        optionLop = new JComboBox<>(c1.toArray(new String[0]));

        String[] option1 = { "Tất cả", "Giỏi", "Khá", "Trung bình", "Yếu" };
        optionHL = new JComboBox<>(option1);
        String[] option2 = { "Tất cả", "Tốt", "Khá", "Trung bình", "Yếu" };
        optionHK = new JComboBox<>(option2);
        String[] option3 = { "Tất cả", "Đã thanh toán", "Chưa thanh toán" };
        optionHP = new JComboBox<>(option3);
        dsnh = nhbus.getList();
        ArrayList<String> c3 = new ArrayList<>();
        c3.add("Tất cả");
        for(NamHocDTO pc: dsnh){
            String tenlop = nhbus.get(pc.getNamHocID()).getNamHocBatDau()+"-"+nhbus.get(pc.getNamHocID()).getNamHocKetThuc();
            c3.add(tenlop);
        }
        optionNam = new JComboBox<>(c3.toArray(new String[0]));
        String[] option5 = { "Tất cả", "Lên Lớp", "Học Lại" };
        optionKQ = new JComboBox<>(option5);

        optionHL.setFont(new Font("Arial", Font.PLAIN, 14));
        optionLop.setFont(new Font("Arial", Font.PLAIN, 14));
        optionHK.setFont(new Font("Arial", Font.PLAIN, 14));
        optionHP.setFont(new Font("Arial", Font.PLAIN, 14));
        optionNam.setFont(new Font("Arial", Font.PLAIN, 14));
        optionKQ.setFont(new Font("Arial", Font.PLAIN, 14));

        totalPanel = new JPanel();
        totalPanel.setOpaque(false);

        l2 = new JLabel("Tổng số học sinh trong danh sách:");
        l2.setFont(new Font("Arial", Font.BOLD, 15));
        s = new JTextField(4);
        s.setEditable(false);
        s.setHorizontalAlignment(SwingConstants.CENTER);

        btnPanel = new JPanel(new GridBagLayout());
        btnPanel.setPreferredSize(new Dimension(120, 0));
        btnPanel.setOpaque(false);

        showBtn = new JButton("Hiển thị");
        showBtn.setPreferredSize(new Dimension(100, 30));
        showBtn.setBackground(new Color(31, 28, 77));
        showBtn.setForeground(Color.WHITE);

        exportBtn = new JButton("Xuất Excel");
        exportBtn.setPreferredSize(new Dimension(100, 30));
        exportBtn.setBackground(new Color(0, 83, 22));
        exportBtn.setForeground(Color.WHITE);

        printBtn = new JButton("In file");
        printBtn.setBackground(new Color(255, 87, 87));
        printBtn.setForeground(Color.WHITE);
        printBtn.setPreferredSize(new Dimension(100, 30));
        printBtn.addActionListener(new PrintListener());

        GridBagConstraints gbcShowBtn = new GridBagConstraints();
        gbcShowBtn.gridx = 0;
        gbcShowBtn.gridy = 0;
        gbcShowBtn.insets = new Insets(5, 0, 5, 10);
        btnPanel.add(showBtn, gbcShowBtn);

        GridBagConstraints gbcExportBtn = new GridBagConstraints();
        gbcExportBtn.gridx = 0;
        gbcExportBtn.gridy = 1;
        gbcExportBtn.insets = new Insets(5, 0, 5, 10);
        btnPanel.add(exportBtn, gbcExportBtn);

        GridBagConstraints gbcprintBtn = new GridBagConstraints();
        gbcprintBtn.gridx = 0;
        gbcprintBtn.gridy = 2;
        gbcprintBtn.insets = new Insets(5, 0, 5, 10);
        btnPanel.add(printBtn, gbcprintBtn);

        totalPanel.add(l2);
        totalPanel.add(s);
        radioPanel.add(b0);
        radioPanel.add(b1);
        radioPanel.add(b2);
        radioPanel.add(b3);
        radioPanel.add(b4);
        radioPanel.add(b5);

        dropdownPanel.add(optionLop);
        dropdownPanel.add(optionHL);
        dropdownPanel.add(optionHK);
        dropdownPanel.add(optionHP);
        dropdownPanel.add(optionNam);
        dropdownPanel.add(optionKQ);

        selectPanel.add(l1);
        selectPanel.add(radioPanel);
        selectPanel.add(dropdownPanel);
        selectPanel.add(totalPanel);

        topThongKe.add(selectPanel, BorderLayout.CENTER);
        topThongKe.add(btnPanel, BorderLayout.EAST);

        this.add(topThongKe, BorderLayout.NORTH);

        contentThongKe = new JPanel();
        contentThongKe.setLayout(new BorderLayout());
        contentThongKe.setOpaque(true);
        contentThongKe.add(initTable(), BorderLayout.NORTH);
        loaddatatoTable();

        this.add(contentThongKe, BorderLayout.CENTER);

        this.setVisible(true);

        showBtn.addActionListener(new ShowBtnListener());
        exportBtn.addActionListener(new expBtnListener());
    }

    public JScrollPane initTable() throws SQLException {
        t = new JTable();
        t.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        scrollPane = new JScrollPane(t);
        scrollPane.setPreferredSize(new Dimension(0, 520));

        String[] headers = { "ID", "Tên HS", "Giới tính", "Ngày sinh", "Điện thoại", "Lớp", "Hạnh Kiểm", "Học Lực",
                "Tình trạng học phí", "Năm Học", "Kết quả" };
        tblModel = new DefaultTableModel();
        for (String header : headers) {
            tblModel.addColumn(header);
        }
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
        return scrollPane;
    }

    public void loaddatatoTable(){
    tblModel.setRowCount(0);

    dshs = hsbus.getList();
    dslop = lopbus.getList();
    dsnh = nhbus.getList();    
    for(NamHocDTO nh:dsnh){
        String idnh = nh.getNamHocID();

        for(HocSinhDTO hs:dshs){
            String idhs = hs.getHocSinhID();
            
            for(LopDTO lop:dslop){
                String idlop = lop.getLopID();
    
                if(plbus.get(idhs, idnh, idlop)!=null){
                    KQ_HocSinhCaNamDTO kq = kqbus.get(idhs, idnh);

                    String[] rowData = new String[]{
                        idhs,
                        hs.getTenHocSinh(),
                        hs.getGioiTinh(),
                        hs.getNgaySinh(),
                        hs.getDienThoai(),
                        lop.getTenLop(),
                        kq.getHanhKiem(),
                        kq.getHocLuc(),
                        hs.getHocPhi(),
                        nh.getNamHocBatDau() +"-"+nh.getNamHocKetThuc(),
                        kq.getKetQua()
                    };
                    tblModel.addRow(rowData);
                }else continue;
            }
        }
    }
        s.setText(String.valueOf(dshs.size()));
    }

    public static void main(String[] args) throws SQLException {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(850, 670);
        ThongKe panel = new ThongKe(850, 670);
        frame.add(panel);
        frame.setVisible(true);
    }

    private class expBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (tblModel.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Bảng trống, không có dữ liệu để xuất.", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return; 
            }
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Tập tin Excel", "xls");
            chooser.setFileFilter(filter);
            chooser.setDialogTitle("Lưu tệp");
            chooser.setAcceptAllFileFilterUsed(false);

            if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                String path = chooser.getSelectedFile().toString();

                // Thêm phần mở rộng .xls nếu người dùng chưa nhập
                if (!path.toLowerCase().endsWith(".xls")) {
                    path += ".xls";
                }

                Workbook workbook = new HSSFWorkbook();
                Sheet sheet = workbook.createSheet("DanhSachHocSinh");
                Row headerRow = sheet.createRow(0); // Header row at index 0
                String[] headers = {"STT", "ID", "Tên HS", "Giới tính", "Ngày sinh", "Điện thoại", "Lớp", "Hạnh Kiểm", "Học Lực",
                "Tình trạng học phí", "Năm Học", "Kết quả" };

                // Creating header cells
                for (int i = 0; i < headers.length; i++) {
                    Cell cell = headerRow.createCell(i);
                    cell.setCellValue(headers[i]);
                }

                int rowCount = tblModel.getRowCount();
                for (int i = 0; i < rowCount; i++) {
                    Row row = sheet.createRow(i + 1); // Bắt đầu từ hàng thứ hai (vì hàng đầu tiên là tiêu đề)

                    row.createCell(0).setCellValue(i + 1);

                    for (int j = 0; j < tblModel.getColumnCount(); j++) {
                        Cell cell = row.createCell(j + 1); // Bắt đầu từ cột thứ hai (sau cột STT)
                        cell.setCellValue(tblModel.getValueAt(i, j).toString());
                    }
                }
                File file = new File(path);
                if (file.exists()) {
                    file.delete();
                }
                try {
                    file.createNewFile();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                try (FileOutputStream fos = new FileOutputStream(file)) {
                    workbook.write(fos);
                    JOptionPane.showMessageDialog(null, "Xuất Excel thành công!");
                    System.out.println("Excel file exported successfully to: " + path);
                    Desktop.getDesktop().open(file);
                } catch (IOException e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Có lỗi xảy ra khi xuất tệp Excel!");
                } finally {
                    try {
                        ((FileOutputStream) workbook).close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
            }
        }
    }

    private class ShowBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String lopSelected = (String) optionLop.getSelectedItem();
            String hocluc = (String) optionHL.getSelectedItem();
            String hanhkiem = (String) optionHK.getSelectedItem();
            String hocphi = (String) optionHP.getSelectedItem();
            String tennamhoc = (String) optionNam.getSelectedItem();
            String ketqua = (String) optionKQ.getSelectedItem();

            tblModel = (DefaultTableModel) t.getModel();
            sorter = new TableRowSorter<>(tblModel);
            t.setRowSorter(sorter);

            List<RowFilter<Object, Object>> filters = new ArrayList<>();

            if (!lopSelected.equals("Tất cả")) {
                filters.add(RowFilter.regexFilter(lopSelected, 5));
            }
            if (!hocluc.equals("Tất cả")) {
                filters.add(RowFilter.regexFilter(hocluc, 7));
            }
            if (!hanhkiem.equals("Tất cả")) {
                filters.add(RowFilter.regexFilter(hanhkiem, 6));
            }
            if (!hocphi.equals("Tất cả")) {
                filters.add(RowFilter.regexFilter(hocphi, 8));
            }
            if (!tennamhoc.equals("Tất cả")) {
                filters.add(RowFilter.regexFilter(tennamhoc, 9));
            }
            if (!ketqua.equals("Tất cả")) {
                filters.add(RowFilter.regexFilter(ketqua, 10));
            }

            // Apply combined filters
            if (!filters.isEmpty()) {
                sorter.setRowFilter(RowFilter.andFilter(filters));
            }



            int count = countUniqueIDs(tblModel);
            s.setText(String.valueOf(count));
            if (count == 0) {
                JOptionPane.showMessageDialog(null, "Không có dữ liệu ");
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
    }
    public JPanel getPanel() {
        return this;
    }
    private class PrintListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (tblModel.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Không có dữ liệu để in!");
            }else {
                JFrame pf = new JFrame();
                pf.setLayout(new BorderLayout()); // Sử dụng BorderLayout để xếp panel
                pf.setUndecorated(true);
                try {
                    ThongKe tk = new ThongKe(794, 300); // Thiết lập kích thước cho panel thông tin
                    tk.getPanel();

                    pf.add(tk, BorderLayout.CENTER); // Đặt panel thông tin ở phía trên
                    pf.setPreferredSize(new Dimension(794, 1123));
                    pf.pack(); // Đảm bảo phù hợp với nội dung của frame
                    pf.setVisible(true); // Hiển thị frame sau khi thêm cả hai panel
                    printPanel(pf);
                    pf.setVisible(false); // Hiển thị frame sau khi thêm cả hai panel

                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

            }
        }
    }

    private void printPanel(JFrame jframe) {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setJobName("Print Panel");
        JOptionPane.showMessageDialog(null, "Đây là nội dung in!");
        int choice = JOptionPane.showConfirmDialog(null, "Tiến hành in?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            job.setPrintable(new Printable() {
                @Override
                public int print(Graphics g, PageFormat pageFormat, int pageIndex) throws PrinterException {
                    if (pageIndex > 0) {
                        return NO_SUCH_PAGE;
                    }
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
                    g2d.scale(0.5, 0.5);
                    jframe.printAll(g2d);
    
                    return PAGE_EXISTS;
                }
            });
                    boolean doPrint = job.printDialog();
                if (doPrint) {
                    try {
                        job.print();
                    } catch (PrinterException e) {
                        e.printStackTrace();
                    }
                }
            System.out.println("User clicked Yes");
        } else {
            JOptionPane.showMessageDialog(null, "In đã bị hủy!"); // Show message to indicate printing canceled
            System.out.println("Thoát in!");
            return; 
        }
    }
    
}
