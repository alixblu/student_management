package GUI;

import javax.swing.*;

import BUS.HocSinhBUS;
import BUS.ThongBaoBUS;
import BUS.YKienBUS;
import DTO.CurrentDateTime;
import DTO.YKienDTO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class hs_gopykien extends JPanel {
    JLabel[] label;
    JTextField txtHeader;
    JTextArea txtContent;
    JButton btnGui;
    private String mahocsinh;
    int width, height;
    HocSinhBUS hsbus = new HocSinhBUS(1);
    YKienBUS ykbus = new YKienBUS(1);

    public hs_gopykien(int width, int height, String mahocsinh) {
        this.mahocsinh = mahocsinh;
        this.height = height;
        this.width = width;
        setSize(width, height);
        setLayout(null);
        setBackground(new Color(180, 204, 227));

        label = new JLabel[2];
        for (int i = 0; i < 2; i++) {
            label[i] = new JLabel();
            label[i].setFont(new Font("Arial", Font.BOLD, 16));
        }
        label[0].setText("Tiêu đề:");
        label[0].setBounds(150, 50, 100, 30);
        label[1].setText("Nhập ý kiến đóng góp:");
        label[1].setBounds(150, 100, 250, 30);

        txtHeader = new JTextField();
        txtHeader.setBounds(250, 50, 500, 30);
        txtHeader.setFont(new Font("Arial", Font.BOLD, 14));
        
        txtContent = new JTextArea();
        txtContent.setBounds(150, 150, 600, 300);
        txtContent.setFont(new Font("Arial", Font.BOLD, 14));
        txtContent.setLineWrap(true);
        txtContent.setWrapStyleWord(true);

        btnGui = new JButton("Gửi");
        btnGui.setBounds(700, 500, 100, 30);
        btnGui.addActionListener(new SendBtnListener());

        for (JLabel jLabel : label) {
            add(jLabel);
        }
        add(txtHeader);
        add(txtContent);
        add(btnGui);
    }

    public class SendBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            CurrentDateTime currDate = new CurrentDateTime();

            if (txtContent.getText().equals("") && txtHeader.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Thông báo không thể bỏ trống cả tiêu đề và nội dung");
                return;
            }
            if (txtContent.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Thông báo không được bỏ trống nội dung");
                return;
            }
            if (txtHeader.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Thông báo không được bỏ trống tiêu đề");
                return;
            }
            System.out.println("tao yk dto");
            YKienDTO yk = new YKienDTO(mahocsinh, txtHeader.getText(), txtContent.getText(), currDate.getdate(), hsbus.get(mahocsinh).getTenHocSinh());

            System.out.println("up yk to data");
            ykbus.add(yk);
            JOptionPane.showMessageDialog(null, "Ý kiến đã được gửi");
            resetText();
        }

        public void resetText() {
            txtContent.setText("");
            txtHeader.setText("");
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Góp Ý Kiến");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(850, 670);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        hs_gopykien panel = new hs_gopykien(850,670,"HS3");
        frame.add(panel);
        frame.setVisible(true);
    }
}
