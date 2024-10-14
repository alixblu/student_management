package GUI;

import DTO.MessageFromHsDTO;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.*;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.border.Border;

import com.toedter.calendar.JDateChooser;
import BUS.MessageFromHsBUS;

public class MessageFromHs extends JPanel implements ActionListener {

    private JTextField jf_ngaygui, jf_tieude;
    JTextArea jf_noidung;
    private JLabel lbl_ngaygui, lbl_tieude, lbl_noidung;
    private JPanel pHead, pTable;
    private Integer width, height;
    private JTable t;
    private JScrollPane scrollpane;
    private JPanel psearch;
    private JComboBox cb_trangthai;
    private JButton btn_update_status;
    private JLabel img_student;
    private JLabel anh_the;

    private MessageFromHsBUS mBUS;
    private ArrayList<MessageFromHsDTO> dsyk;
    private JButton btnReset;
    private JButton btnSearch;
    private JLabel trangthai;
    private JLabel ngayketthuc;
    private JLabel ngaybatdau;
    private DefaultTableModel model;
    private TableRowSorter sorter;
    private JComboBox trangthaiBox;
    private JDateChooser chonbd;
    private JDateChooser chonkt;

    public MessageFromHs(Integer width, Integer height) {
        this.width = width;
        this.height = height;
        init();
        btnReset.addActionListener(this);
        btnSearch.addActionListener(this);
        btn_update_status.addActionListener(this);
    }

    public void init() {

        this.setSize(new Dimension(width, height));
        // this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        JPanel p1 = JSearch();
        p1.setPreferredSize(new Dimension(0, 40));
        p1.setBackground(new Color(99, 116, 198)); // Light blue background

        JPanel p2 = JHeader();
        p2.setPreferredSize(new Dimension(0, 200));
        p2.setBackground(new Color(180, 204, 227)); // Light cyan background

        JPanel p3 = new JPanel();
        p3.setPreferredSize(new Dimension(0, 300));
        p3.add(init_table());
        p3.setBackground(new Color(240, 255, 255)); // Azure background

        this.add(p1, BorderLayout.NORTH);
        this.add(p2, BorderLayout.CENTER);
        this.add(p3, BorderLayout.SOUTH);
    }

    public JPanel JSearch() {
        psearch = new JPanel();
        psearch.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));

        ngaybatdau = new JLabel("Ngày bắt đầu");
        chonbd = new JDateChooser();
        chonbd.setPreferredSize(new Dimension(100, 25));
        chonbd.getDateEditor().setEnabled(false); 

        ngayketthuc = new JLabel("Ngày kết thúc");
        chonkt = new JDateChooser();
        chonkt.setPreferredSize(new Dimension(100, 25));
        chonkt.getDateEditor().setEnabled(false); 
        
        trangthai = new JLabel("Trạng thái");
        String[] cbtrangthai = { "All", "Chưa xem", "Đã xem", "Ghi nhận", "Xấu" };
        trangthaiBox = new JComboBox<>(cbtrangthai);
        trangthaiBox.setPreferredSize(new Dimension(100, 25));

        btnSearch = new JButton("Tìm kiếm");
        btnSearch.setBackground(new Color(255, 222, 89));
        btnReset = new JButton("Reset");
        btnReset.setBackground(new Color(217,217,217));
        psearch.add(ngaybatdau);
        psearch.add(chonbd);
        psearch.add(ngayketthuc);
        psearch.add(chonkt);
        psearch.add(trangthai);
        psearch.add(trangthaiBox);
        psearch.add(btnSearch);
        psearch.add(btnReset);

        return psearch;
    }

    public JPanel JHeader() {
        pHead = new JPanel();
        pHead.setLayout(null);

        Border border = BorderFactory.createLineBorder(Color.GRAY, 2);

        lbl_ngaygui = new JLabel("Ngày gửi:");
        lbl_ngaygui.setBounds(20, 40, 60, 30);

        jf_ngaygui = new JTextField();
        jf_ngaygui.setBounds(80, 40, 100, 30);
        jf_ngaygui.setEditable(false);
        jf_ngaygui.setFont(jf_ngaygui.getFont().deriveFont(java.awt.Font.BOLD, 12f));
        jf_ngaygui.setForeground(Color.blue);
        jf_ngaygui.setBorder(border);

        lbl_tieude = new JLabel("Tiêu đề:");
        lbl_tieude.setBounds(210, 40, 100, 30);

        jf_tieude = new JTextField();
        jf_tieude.setBounds(260, 40, 300, 30);
        jf_tieude.setEditable(false);
        jf_tieude.setForeground(Color.red);
        jf_tieude.setFont(jf_tieude.getFont().deriveFont(java.awt.Font.BOLD, 12f));
        jf_tieude.setBorder(border);

        lbl_noidung = new JLabel("Nội dung:");
        lbl_noidung.setBounds(20, 80, 100, 30);

        jf_noidung = new JTextArea();
        jf_noidung.setBounds(20, 110, 540, 150);
        jf_noidung.setEditable(false);
        jf_noidung.setFont(jf_noidung.getFont().deriveFont(java.awt.Font.BOLD, 12f));
        jf_noidung.setBorder(border);

        cb_trangthai = new JComboBox<>(new String[] { "Chưa đọc", "Đã đọc", "Tích cực", "Xấu" });
        cb_trangthai.setBounds(590, 40, 100, 30);
        cb_trangthai.setBorder(border);

        btn_update_status = new JButton("Update");
        btn_update_status.setBackground(new Color(0, 151, 178));
        btn_update_status.setBounds(710, 40, 95, 30);

        img_student = new JLabel();
        img_student.setBounds(630, 110, 130, 150);
        img_student.setBackground(Color.BLACK);

        img_student.setOpaque(true);
        anh_the = new JLabel("Ảnh thẻ:");
        anh_the.setBounds(630, 80, 50, 30);

        pHead.add(lbl_ngaygui);
        pHead.add(jf_ngaygui);
        pHead.add(lbl_tieude);
        pHead.add(jf_tieude);
        pHead.add(cb_trangthai);
        pHead.add(btn_update_status);
        pHead.add(lbl_noidung);
        pHead.add(jf_noidung);
        pHead.add(img_student);
        pHead.add(anh_the);

        return pHead;
    }

    public JScrollPane init_table() {
        String[] header = { "Mã học sinh", "Tên học sinh", "Thời gian gửi", "Tiêu đề", "Nội dung", "Trạng thái",
        };
        mBUS = new MessageFromHsBUS();
        mBUS.list();
        dsyk = mBUS.getList();
        Object[][] rowData = new Object[dsyk.size()][header.length];
        for (int i = 0; i < dsyk.size(); i++) {
            MessageFromHsDTO yk = dsyk.get(i);
            rowData[i][0] = yk.getIdnguoigui();
            rowData[i][1] = yk.getTenhs();
            rowData[i][2] = yk.getThoigianyk();
            rowData[i][3] = yk.getTieudeyk();
            rowData[i][4] = yk.getNoidungyk();
            rowData[i][5] = yk.getTrangthai();
        }

        model = new DefaultTableModel(rowData, header);
        t = new JTable(model);
        t.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        t.getTableHeader().setBackground(new Color(70, 130, 180)); // Steel blue header
        t.getTableHeader().setForeground(Color.WHITE);
        t.setSelectionBackground(new Color(100, 149, 237)); // Cornflower blue selection
        t.setGridColor(new Color(173, 216, 230)); // Light blue grid lines
        scrollpane = new JScrollPane(t);
        scrollpane.setPreferredSize(new Dimension(846, 295));
        t.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    tableMouseClicked(evt);
                } catch (ParseException ex) {
                    Logger.getLogger(MessageFromHs.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        return scrollpane;
    }

    private void tableMouseClicked(java.awt.event.MouseEvent evt) throws ParseException {
        int row = t.getSelectedRow();
        String thoigian = (String) t.getValueAt(row, 2);
        String tieude = String.valueOf(t.getValueAt(row, 3));
        String noidung = String.valueOf(t.getValueAt(row, 4));
        String trangthai = String.valueOf(t.getValueAt(row, 5));
        String mahs = String.valueOf(t.getValueAt(row, 0));
        jf_ngaygui.setText(thoigian);
        jf_tieude.setText(tieude);
        jf_noidung.setText(noidung);
        cb_trangthai.setSelectedItem(trangthai);
        String img = null;
        System.out.println(mahs);
        img = mBUS.getIMG(mahs);
        if (img != null) {
            String path = "/image/Avatar/" + img;
            java.net.URL imgHS = getClass().getResource(path);
            ImageIcon orgIcon_HS = new ImageIcon(imgHS);
            Image scaleImg_HS = orgIcon_HS.getImage().getScaledInstance(img_student.getWidth(), img_student.getHeight(),
                    Image.SCALE_SMOOTH);

            ImageIcon scaledImage_HS = new ImageIcon(scaleImg_HS);
            img_student.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 0, 127), 4, false));

            img_student.setIcon(scaledImage_HS);
        } else {
            img_student.setIcon(null);
        }
    }

    public void deleteFields() {
        jf_ngaygui.setText("");
        jf_tieude.setText("");
        jf_noidung.setText("");
        cb_trangthai.setSelectedItem("Chưa đọc");
        chonbd.setDate(null);
        chonkt.setDate(null);
        img_student.setIcon(null);
        img_student.setBorder(null);
    }

    public void filterTable() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String[] header = { "Mã học sinh", "Tên học sinh", "Thời gian gửi", "Tiêu đề", "Nội dung", "Trạng thái" };

        String dateStart = null;
        String dateEnd = null;

        try {
            if (chonbd.getDate() != null) {
                dateStart = sdf.format(chonbd.getDate());
            }
            if (chonkt.getDate() != null) {
                dateEnd = sdf.format(chonkt.getDate());
            }
        } catch (NullPointerException e) {
            System.err.println("Date selection is null: " + e.getMessage());
            return;
        }

        if (dateStart != null && dateEnd != null) {
            try {
                Date startDate = sdf.parse(dateStart);
                Date endDate = sdf.parse(dateEnd);
                if (startDate.after(endDate)) {
                    JOptionPane.showMessageDialog(this, "Ngày bắt đầu không được lớn hơn", "Lỗi",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (ParseException e) {
                System.err.println("Error parsing dates: " + e.getMessage());
                return;
            }
        }

        String trangthai = (String) trangthaiBox.getSelectedItem();

        ArrayList<String> thoigian = new ArrayList<>();
        thoigian.add(dateStart);
        thoigian.add(dateEnd);
        thoigian.add(trangthai.equals("All") ? null : trangthai);

        try {
            mBUS.getListSearch(thoigian);
            dsyk = mBUS.getList();
        } catch (Exception e) {
            System.err.println("Error fetching search results: " + e.getMessage());
            return;
        }

        Object[][] rowData = new Object[dsyk.size()][header.length];
        for (int i = 0; i < dsyk.size(); i++) {
            MessageFromHsDTO yk = dsyk.get(i);
            rowData[i][0] = yk.getIdnguoigui();
            rowData[i][1] = yk.getTenhs();
            rowData[i][2] = yk.getThoigianyk();
            rowData[i][3] = yk.getTieudeyk();
            rowData[i][4] = yk.getNoidungyk();
            rowData[i][5] = yk.getTrangthai();
            System.out.println(yk.getIdnguoigui());
        }

        DefaultTableModel model = (DefaultTableModel) t.getModel();
        model.setDataVector(rowData, header);
    }

    public void resetFields() {
        deleteFields();
        String[] header = { "Mã học sinh", "Tên học sinh", "Thời gian gửi", "Tiêu đề", "Nội dung", "Trạng thái",
        };
        mBUS = new MessageFromHsBUS();
        mBUS.list();
        dsyk = mBUS.getList();
        Object[][] rowData = new Object[dsyk.size()][header.length];
        for (int i = 0; i < dsyk.size(); i++) {
            MessageFromHsDTO yk = dsyk.get(i);
            rowData[i][0] = yk.getIdnguoigui();
            rowData[i][1] = yk.getTenhs();
            rowData[i][2] = yk.getThoigianyk();
            rowData[i][3] = yk.getTieudeyk();
            rowData[i][4] = yk.getNoidungyk();
            rowData[i][5] = yk.getTrangthai();
        }
        DefaultTableModel model = (DefaultTableModel) t.getModel();
        model.setDataVector(rowData, header);
    }

    public void updateTrangThai() {
        String noidung = jf_noidung.getText();
        if (noidung.equals("")) {
            JOptionPane.showMessageDialog(this, "Hãy chọn ý kiến để thay đổi");
            return;
        }
        int selectedRow = t.getSelectedRow();
        String newStatus = (String) cb_trangthai.getSelectedItem();
        String idnguoigui = (String) t.getValueAt(selectedRow, 0);

        if (selectedRow != -1) {
            t.setValueAt(newStatus, selectedRow, 5);
        }

        int result = JOptionPane.showConfirmDialog(this,
                "Bạn có chắc muốn cập nhật trạng thái này",
                "Xác nhận",
                JOptionPane.YES_NO_OPTION,

                JOptionPane.QUESTION_MESSAGE);
        if (result == JOptionPane.YES_OPTION) {
            ArrayList<String> uptt = new ArrayList<>();
            uptt.add(idnguoigui);
            uptt.add(noidung);
            uptt.add(newStatus);

            mBUS.update(uptt);
            JOptionPane.showMessageDialog(this, "Thay đổi trạng thái thành công", "Cập nhật",
                    JOptionPane.DEFAULT_OPTION);
        } else if (result == JOptionPane.NO_OPTION) {
            System.out.println("Bạn chọn không đồng ý sửa");
        }

    }

    public static void main(String[] args) throws SQLException {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(850, 670);
        MessageFromHs panel = new MessageFromHs(850, 670);
        frame.add(panel);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnReset) {
            resetFields();
        } else if (e.getSource() == btnSearch) {
            filterTable();
        } else if (e.getSource() == btn_update_status) {
            updateTrangThai();
        }
    }
}
