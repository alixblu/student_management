package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.event.*;
import java.awt.print.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.TableRowSorter;
import java.util.List;
import java.util.ArrayList;
import java.awt.*;
import java.sql.*;

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

public class diemHS extends JPanel {
    private JPanel topPanel, radioPanel, dropdownPanel, selectPanel, btnPanel, diemPanel, loaiPanel;
    private JLabel b1, b2, b3, jl1, jl2, jl3, jl4;
    private JTextField tf1, tf2, tf3, tf4, tf1a, tf2a;
    private JComboBox<String> c1, c2, c3;
    private JLabel l1;
    private JButton filterBtn, printBtn;
    private JScrollPane scrollPane;
    private JTable t;
    private DefaultTableModel tblModel;
    private String mahocsinh;
    private String HKY;
    private String NH;
    boolean hasData = false; // Track if any data was added to the table

    ArrayList<HocSinhDTO> dshs;
    ArrayList<KQ_HocSinhCaNamDTO> dskq;
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
    DefaultTableModel model;
    TableRowSorter<DefaultTableModel> sorter;

    public diemHS(String mahocsinh) throws SQLException {
        this.mahocsinh = mahocsinh;
        this.NH = NH;
        this.HKY = HKY;
        setLayout(new BorderLayout());
        setSize(850, 670);

        topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.setPreferredSize(new Dimension(0, 100));
        topPanel.setBackground(new Color(180, 204, 227));

        selectPanel = new JPanel();
        selectPanel.setLayout(new BoxLayout(selectPanel, BoxLayout.Y_AXIS));
        selectPanel.setOpaque(false);

        l1 = new JLabel("Xem điểm theo:");
        l1.setFont(new Font("Arial", Font.BOLD, 20));
        l1.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 20));

        radioPanel = new JPanel();
        radioPanel.setOpaque(false);
        radioPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 0));

        // b1 = new JLabel("Môn học");
        b2 = new JLabel("Học kỳ");
        b3 = new JLabel("Năm học");
        b2.setFont(new Font(b2.getFont().getName(), Font.BOLD, 18));
        b3.setFont(new Font(b3.getFont().getName(), Font.BOLD, 18));

        // b1.setBackground(new Color(180, 204, 227));
        b2.setBackground(new Color(180, 204, 227));
        b3.setBackground(new Color(180, 204, 227));

        dropdownPanel = new JPanel();
        dropdownPanel.setOpaque(false);
        dropdownPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 0));
        dropdownPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        dshk = hkbus.getList();
        c2 = new JComboBox<>();
        List<HocKyDTO> dshk = hkbus.getList();
        for (HocKyDTO hk : dshk) {
            c2.addItem(hk.getTenHocKy());
        }

        dshk = hkbus.getList();
        c3 = new JComboBox<>();
        List<NamHocDTO> dsnh = nhbus.getList();
        for (NamHocDTO nh : dsnh) {
            String idnamhoc = nh.getNamHocID();
            c3.addItem(nhbus.get(idnamhoc).getNamHocBatDau() + "-" + nhbus.get(idnamhoc).getNamHocKetThuc());
        }

        btnPanel = new JPanel(new GridBagLayout());
        btnPanel.setPreferredSize(new Dimension(150, 0));
        btnPanel.setOpaque(false);

        filterBtn = new JButton("Lọc");
        filterBtn.setBackground(new Color(31, 28, 77));
        filterBtn.setForeground(Color.WHITE);
        filterBtn.setPreferredSize(new Dimension(70, 30));

        filterBtn.addActionListener(new ShowFilterListener());

        printBtn = new JButton("In");
        printBtn.setBackground(new Color(255, 87, 87));
        printBtn.setForeground(Color.WHITE);
        printBtn.setPreferredSize(new Dimension(70, 30));
        printBtn.addActionListener(new PrintListener());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        btnPanel.add(filterBtn, gbc);

        GridBagConstraints gbc1 = new GridBagConstraints();
        gbc1.insets = new Insets(10, 0, 0, 0);
        gbc1.gridx = 0;
        gbc1.gridy = 1;
        btnPanel.add(printBtn, gbc1);

        radioPanel.add(b2);
        radioPanel.add(b3);
        dropdownPanel.add(c2);
        dropdownPanel.add(c3);

        selectPanel.add(l1);
        selectPanel.add(radioPanel);
        selectPanel.add(dropdownPanel);

        topPanel.add(selectPanel, BorderLayout.CENTER);
        topPanel.add(btnPanel, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);
        setVisible(true);
        PanelDiem();
        add(diemPanel, BorderLayout.CENTER);
    }

    public void PanelDiem() throws SQLException {
        diemPanel = new JPanel();
        diemPanel.setLayout(new BoxLayout(diemPanel, BoxLayout.Y_AXIS)); // Change layout to BoxLayout with Y_AXIS
        diemPanel.setBackground(new Color(180, 204, 227));
        diemPanel.setPreferredSize(new Dimension(850, 0));
        diemPanel.setVisible(true);

        diemPanel.add(initTable());
        loaddatatoTable();
        diemPanel.add(PanelLoai());
    }

    public JPanel PanelLoai() {
        loaiPanel = new JPanel(new GridBagLayout());
        loaiPanel.setPreferredSize(new Dimension(850, 250));
        loaiPanel.setBackground(new Color(180, 204, 227));
        loaiPanel.setVisible(true);
        addComponentsToPanel();

        return loaiPanel;
    }

    private void addComponentsToPanel() {
        ArrayList<HocSinhDTO> dshs;
        ArrayList<KQ_HocSinhCaNamDTO> dskq;
        ArrayList<MonHocDTO> dsmon;
        ArrayList<ChiTietDiemDTO> dsct;
        ArrayList<HocKyDTO> dshk;
        ArrayList<DTB_HocKyDTO> dsdtb;
        ArrayList<NamHocDTO> dsnh;
        ArrayList<PhanLopDTO> dspl;
        ArrayList<LopDTO> dslop;
        dshs = hsbus.getList();
        dskq = kqbus.getList();
        dsmon = mhbus.getList();
        dsct = ctbus.getList();
        dsdtb = dtbbus.getList();
        dshk = hkbus.getList();
        dsnh = nhbus.getList();
        dspl = plbus.getList();

        String HKY = (String) c2.getSelectedItem(); // họcky1
        String NH = (String) c3.getSelectedItem(); // 2024-2025

        boolean hasData = false; // Track if any data was added to the table

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 0, 30);

        GridBagConstraints gbc1 = new GridBagConstraints();
        gbc1.insets = new Insets(15, 0, 0, 0);
        jl1 = createLabel("Điểm trung bình học kỳ:", gbc1, 0, 2);

        GridBagConstraints gbc1a = new GridBagConstraints();
        gbc1a.insets = new Insets(0, 215, 0, 0);
        tf1a = createTextField(gbc1a, 0, 2);
        tf1a.setPreferredSize(new Dimension(30, 25));
        tf1a.setBackground(new Color(180, 204, 227));
        tf1a.setBorder(BorderFactory.createLineBorder(new Color(180, 204, 227)));

        tf1 = createTextField(gbc, 1, 2);

        jl2 = createLabel("Điểm trung bình cả năm:", gbc1, 0, 3);

        GridBagConstraints gbc2a = new GridBagConstraints();
        gbc2a.insets = new Insets(0, 220, 0, 0);
        tf2a = createTextField(gbc2a, 0, 3);
        tf2a.setPreferredSize(new Dimension(120, 20));
        tf2a.setBackground(new Color(180, 204, 227));
        tf2a.setBorder(BorderFactory.createLineBorder(new Color(180, 204, 227)));

        tf2 = createTextField(gbc, 1, 3);

        jl3 = createLabel("Xếp loại học lực:", gbc1, 0, 4);
        tf3 = createTextField(gbc, 1, 4);

        jl4 = createLabel("Xếp loại hạnh kiểm:", gbc1, 0, 5);
        tf4 = createTextField(gbc, 1, 5);

        for (KQ_HocSinhCaNamDTO kq : dskq) {
            for (DTB_HocKyDTO dtbhk : dsdtb) {
                if (dtbhk.getHocSinhID().equals(mahocsinh)
                        && dtbhk.getHocKyID().equals(hkbus.getHocKyIDFromTenHocKy(HKY))
                        && dtbhk.getNamHocID().equals(nhbus.getByAcademicYear(NH))) {
                    tf1.setText(String.valueOf(dtbhk.getDiemTrungBinh()));
                    tf1a.setText("\"" + hkbus.getHocKyIDFromTenHocKy(HKY) + "\"");
                }
            }
            if (kq.getHocSinhID().equals(mahocsinh) && kq.getNamHocID().equals(nhbus.getByAcademicYear(NH))) {
                tf2.setText(String.valueOf(kq.getDiemTrungBinhNam()));
                tf3.setText(String.valueOf(kq.getHocLuc()));
                tf4.setText(String.valueOf(kq.getHanhKiem()));
                tf2a.setText("\"" + NH + "\"");
            }

        }

        lockTextFields();

    }

    private JLabel createLabel(String text, GridBagConstraints gbc, int x, int y) {
        JLabel label = new JLabel(text);
        label.setPreferredSize(new Dimension(400, 40));
        label.setFont(label.getFont().deriveFont(Font.BOLD, 18));
        label.setHorizontalAlignment(SwingConstants.LEFT); // Set horizontal alignment to LEFT
        label.setVerticalAlignment(SwingConstants.TOP); // Set vertical alignment to TOP
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.anchor = GridBagConstraints.WEST;
        loaiPanel.add(label, gbc);
        return label;
    }

    public String getNH() {
        ArrayList<KQ_HocSinhCaNamDTO> dskq;
        dskq = kqbus.getList();
        String NH = (String) c3.getSelectedItem(); // 2024-2025
        for (KQ_HocSinhCaNamDTO kq : dskq) {
            if (kq.getHocSinhID().equals(mahocsinh) && kq.getNamHocID().equals(nhbus.getByAcademicYear(NH))) {
                NH = kq.getNamHocID();
            }
        }
        return NH;
    }

    private JTextField createTextField(GridBagConstraints gbc, int x, int y) {
        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(100, 30));
        textField.setFont(textField.getFont().deriveFont(Font.BOLD, 18));
        textField.setHorizontalAlignment(JTextField.CENTER); // Set horizontal alignment to LEFT
        textField.setBackground(Color.WHITE);
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.anchor = GridBagConstraints.WEST;
        loaiPanel.add(textField, gbc);
        return textField;
    }

    private void lockTextFields() {
        tf1.setEditable(false);
        tf2.setEditable(false);
        tf3.setEditable(false);
        tf4.setEditable(false);
    }

    public JScrollPane initTable() throws SQLException {
        t = new JTable();
        t.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        scrollPane = new JScrollPane(t);

        tblModel = new DefaultTableModel();
        tblModel.addColumn("STT");
        tblModel.addColumn("Môn học");
        tblModel.addColumn("Điểm 15'");
        tblModel.addColumn("Điểm 1 tiết");
        tblModel.addColumn("Điểm học kì");
        tblModel.addColumn("Điểm TBM");

        t.setModel(tblModel);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < t.getColumnCount(); i++) {
            t.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        t.getColumnModel().getColumn(0).setPreferredWidth(50);
        t.getColumnModel().getColumn(1).setPreferredWidth(170);
        t.getColumnModel().getColumn(2).setPreferredWidth(170);
        t.getColumnModel().getColumn(3).setPreferredWidth(170);
        t.getColumnModel().getColumn(4).setPreferredWidth(170);
        t.getColumnModel().getColumn(5).setPreferredWidth(170);
        t.setRowHeight(40);

        JTableHeader header = t.getTableHeader();
        header.setPreferredSize(new Dimension(header.getPreferredSize().width, 40));
        header.setBackground(new Color(31, 28, 77));
        header.setForeground(Color.WHITE);
        header.setFont(header.getFont().deriveFont(Font.BOLD, 12));
        // diemPanel.add(scrollPane, BorderLayout.CENTER);
        return scrollPane;
    }

    public void loaddatatoTable() {
        tblModel.setRowCount(0); // Xóa dữ liệu hiện có
    
        dshs = hsbus.getList();
        dsmon = mhbus.getList();
        dsct = ctbus.getList(); // dsctd được sử dụng trong phương thức get()
        dshk = hkbus.getList();
        boolean hasData = false; // Cờ kiểm tra xem có dữ liệu hay không
    
        int stt = 1;
        String HKY = (String) c2.getSelectedItem(); // học kỳ
        String NH = (String) c3.getSelectedItem();  // năm học
        String mahs = mahocsinh;  // Bạn cần định nghĩa hoặc lấy mã học sinh từ đâu đó trước
        NamHocBUS nhbus = new NamHocBUS(1);
        String namhocid = nhbus.getCurrYearId(); // Lấy ID năm học hiện tại
        HocKyBUS hkbus = new HocKyBUS(1);
        String hockyid = hkbus.getHocKyIDFromTenHocKy(HKY);
        System.out.println("Học kỳ: " + HKY);
        System.out.println("Năm học: " + NH);
        System.out.println("Mã học sinh: " + mahs);
        System.out.println("Năm học ID: " + namhocid);
    
        // Lặp qua danh sách các môn học
        for (MonHocDTO mh : dsmon) {
            double diem15 = 0, diem1Tiet = 0, diemHocKy = 0;
    
            String monhocid = mh.getMonHocID();
            System.out.println("Đang xử lý môn học id: " + mh.getMonHocID());
    
            // Sử dụng phương thức get() để lấy ChiTietDiemDTO
            ChiTietDiemDTO chiTietDiem = ctbus.get(mahocsinh, namhocid, hockyid, monhocid);
            if (chiTietDiem != null) {
                diem15 = chiTietDiem.getDiem1();
                diem1Tiet = chiTietDiem.getDiem2();
                diemHocKy = chiTietDiem.getDiem3();
    
                System.out.println("Điểm 15 phút: " + diem15);
                System.out.println("Điểm 1 tiết: " + diem1Tiet);
                System.out.println("Điểm học kỳ: " + diemHocKy);
            } else {
                System.out.println("Không tìm thấy chi tiết điểm cho môn học: " + mh.getTenMonHoc());
            }
    
            // Nếu có bất kỳ điểm nào khác 0, thêm vào bảng
            if (diem15 != 0 || diem1Tiet != 0 || diemHocKy != 0) {
                double tbm = chiTietDiem.getDtbMon(); // Tính điểm trung bình môn
                String formattedTBM = String.format("%.1f", tbm); // Định dạng lại TBM
                System.out.println("Điểm trung bình môn: " + formattedTBM);
    
                // Thêm dòng vào bảng
                String[] rowData = new String[] {
                    String.valueOf(stt),
                    mh.getTenMonHoc(),
                    String.valueOf(diem15),
                    String.valueOf(diem1Tiet),
                    String.valueOf(diemHocKy),
                    formattedTBM
                };
                tblModel.addRow(rowData);
                stt++;  // Tăng số thứ tự
                hasData = true;  // Đánh dấu đã có dữ liệu
            }
        }
    
        // Nếu không có dữ liệu, hiển thị thông báo
        if (!hasData) {
            tblModel.setRowCount(0);
            // JOptionPane.showMessageDialog(null, "Không có dữ liệu!");
            System.out.println("Không có dữ liệu nào được thêm vào bảng.");
        }
    }
    
    

    private class PrintListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (tblModel.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Không có dữ liệu để in!");
            } else {
                JFrame pf = new JFrame();
                pf.setLayout(new BorderLayout()); // Sử dụng BorderLayout để xếp panel
                pf.setUndecorated(true);
                try {
                    TTTK_HS tt = new TTTK_HS(794, 300, mahocsinh); // Thiết lập kích thước cho panel thông tin
                    tt.getPanel();

                    pf.add(tt, BorderLayout.NORTH); // Đặt panel thông tin ở phía trên

                    diemPanel.setPreferredSize(new Dimension(794, 823)); // Thiết lập kích thước cho panel điểm
                    pf.add(diemPanel, BorderLayout.CENTER); // Đặt panel điểm ở phía dưới

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

    private class ShowFilterListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String HKY = (String) c2.getSelectedItem();
            String NH = (String) c3.getSelectedItem();
            // Cập nhật lại PanelDiem
            try {
                updatePanelDiem();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            // Update serial numbers after filtering
            updateSerialNumbers();
        }

        private void updateSerialNumbers() {
            for (int i = 0; i < tblModel.getRowCount(); i++) {
                tblModel.setValueAt(i + 1, i, 0); // Update the STT column
            }
        }
    }

    public void updatePanelDiem() throws SQLException {
        remove(diemPanel); // Xóa diemPanel khỏi diemHS
        PanelDiem(); // Gọi lại PanelDiem để cập nhật dữ liệu mới
        add(diemPanel, BorderLayout.CENTER); // Thêm diemPanel vào diemHS
        revalidate(); // Cập nhật giao diện
        repaint(); // Vẽ lại giao diện
    }

    public static void main(String[] args) {
    JFrame frame = new JFrame("Điểm Học Sinh");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(900, 700);
    frame.setLocationRelativeTo(null);

    try {
    diemHS testdiemPanel = new diemHS("HSK241");
    frame.add(testdiemPanel);
    } catch (SQLException e) {
    e.printStackTrace();
    }

    frame.setVisible(true);
    }
}
