package GUI;

import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.*;

import BUS.*;
import DTO.*;

public class TTTK_GV extends JPanel {
    private JTextField tf3, tf4, tf5, tf6, tf7, tf8, tf9, tf10;
    private JLabel jl2, jl7, jl8, jl9, jl10, jl11, jl12, jl13, jl14;
    private JPanel rightPanel;
    private String username;
    private int width, height;
    GiaoVienBUS gvbus = new GiaoVienBUS();
    MonHocBUS mhbus = new MonHocBUS(1);
    LopBUS Lopbus = new LopBUS(1);
    PhanCongBUS pcbus = new PhanCongBUS(1);

    public TTTK_GV(int width, int height, String username) throws SQLException {
        this.width = width;
        this.height = height;
        this.username = username;  // Lưu trữ username
        this.setSize(new Dimension(width, height));
        this.setBackground(Color.red);
        this.setLayout(new BorderLayout());

        rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setPreferredSize(new Dimension(850, 670));
        rightPanel.setBackground(new Color(180, 204, 227));

        addComponentsToPanel();
        this.add(rightPanel, BorderLayout.CENTER);
        loaddatatoPanel();
        this.setVisible(true);

    }

    private void addComponentsToPanel() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 30, 0, 30);

        jl7 = createLabel("Mã giáo viên:", gbc, 0, 2);
        tf3 = createTextField(gbc, 1, 2);

        jl8 = createLabel("Họ tên:", gbc, 0, 3);
        tf4 = createTextField(gbc, 1, 3);

        jl9 = createLabel("Giới tính:", gbc, 0, 4);
        tf5 = createTextField(gbc, 1, 4);

        jl10 = createLabel("Ngày sinh:", gbc, 0, 5);
        tf6 = createTextField(gbc, 1, 5);

        jl11 = createLabel("Điện thoại:", gbc, 0, 6);
        tf7 = createTextField(gbc, 1, 6);

        jl12 = createLabel("Phân môn:", gbc, 0, 7);
        tf8 = createTextField(gbc, 1, 7);

      
        lockTextFields();
    }
    private JTextArea createTextArea(GridBagConstraints gbc, int x, int y) {
        JTextArea textArea = new JTextArea();
        textArea.setFont(textArea.getFont().deriveFont(Font.BOLD, 18));
        textArea.setBackground(Color.WHITE);
        textArea.setLineWrap(true); // Cho phép wrap dòng
        textArea.setWrapStyleWord(true); // Wrap từ nguyên một từ
        textArea.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(300, 60)); // Kích thước của JScrollPane
        gbc.gridx = x;
        gbc.gridy = y;
        rightPanel.add(scrollPane, gbc);
        return textArea;
    }
    private JLabel createLabel(String text, GridBagConstraints gbc, int x, int y) {
        JLabel label = new JLabel(text);
        label.setPreferredSize(new Dimension(150, 40));
        label.setFont(label.getFont().deriveFont(Font.BOLD, 18));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.anchor = GridBagConstraints.WEST;
        rightPanel.add(label, gbc);
        return label;
    }

    private JTextField createTextField(GridBagConstraints gbc, int x, int y) {
        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(300, 30));
        textField.setFont(textField.getFont().deriveFont(Font.BOLD, 18));
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setBackground(Color.WHITE);
        gbc.gridx = x;
        gbc.gridy = y;
        rightPanel.add(textField, gbc);
        return textField;
    }

    private void lockTextFields() {
        tf3.setEditable(false);
        tf4.setEditable(false);
        tf5.setEditable(false);
        tf6.setEditable(false);
        tf7.setEditable(false);
        tf8.setEditable(false);
    }

    public void loaddatatoPanel() {
        ArrayList<GiaoVienDTO> dsgv = gvbus.getList();
        
        for (GiaoVienDTO gv : dsgv) {
            String idgv = gv.getMaGV();
            if (username.equals(idgv)) {
                tf3.setText(idgv);
                tf4.setText(gv.getTenGV());
                tf6.setText(gv.getNamSinh());
                tf5.setText(gv.getGioiTinh());
                tf7.setText(gv.getDienThoai());
                tf8.setText(gv.getphanMon());
            }
        }
    }

    public static void main(String[] args) throws SQLException {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 800);
        TTTK_GV panel = new TTTK_GV(1000, 800, "GV2");
        frame.add(panel);
        frame.setVisible(true);
    }
}
