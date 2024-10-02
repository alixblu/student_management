package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import BUS.DoiMK_BUS;
import DTO.DoiMK_DTO;

public class DoiMK extends JPanel {
    private JTextField tfUsername;
    private JPasswordField tfNewPassword, tfConfirmPassword, tfOldPassword;
    private JPanel mainP;
    private JLabel jlUsername, jlOldPassword, jlNewPassword, jlConfirmPassword;
    private JButton okButton;
    private DoiMK_BUS doiMK_BUS;
    private String username;
    private int width, height;
    public DoiMK(int width, int height, String username) {
        this.width = width;
        this.height = height;
        this.username = username;
        doiMK_BUS = new DoiMK_BUS();

        this.setSize(new Dimension(width, height));
        this.setLayout(new BorderLayout());

        mainP = new JPanel(new GridBagLayout());
        mainP.setPreferredSize(new Dimension(width, height));
        mainP.setBackground(new Color(180, 204, 227));
        addComponentsToPanel();
        this.add(mainP, BorderLayout.CENTER);
        this.setVisible(true);
    }

    private void addComponentsToPanel() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 30, 10, 30); // Khoảng cách giữa các thành phần
    
        jlUsername = createLabel("Username:", gbc, 0, 2);
        tfUsername = createTextField(gbc, 1, 2);
        tfUsername.setText(username);
        tfUsername.setEditable(false);
        jlOldPassword = createLabel("Nhập mật khẩu cũ:", gbc, 0, 3);
        tfOldPassword = createPasswordField(gbc, 1, 3);
    
        jlNewPassword = createLabel("Nhập mật khẩu mới:", gbc, 0, 4);
        tfNewPassword = createPasswordField(gbc, 1, 4);
    
        jlConfirmPassword = createLabel("Nhập lại mật khẩu mới:", gbc, 0, 5);
        tfConfirmPassword = createPasswordField(gbc, 1, 5);
    
        okButton = new JButton("OK");
        okButton.setPreferredSize(new Dimension(80, 40));
        okButton.setFont(okButton.getFont().deriveFont(Font.BOLD, 18));
        okButton.setBackground(new Color(52, 48, 128));
        okButton.setForeground(Color.WHITE);
    
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        mainP.add(okButton, gbc);
    
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String confirmPassword = tfConfirmPassword.getText();
                handleChangePassword(confirmPassword);
            }
        });
    }

    private JLabel createLabel(String text, GridBagConstraints gbc, int x, int y) {
        JLabel label = new JLabel(text);
        label.setPreferredSize(new Dimension(250, 40));
        label.setFont(label.getFont().deriveFont(Font.BOLD, 18));
label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.anchor = GridBagConstraints.WEST;
        mainP.add(label, gbc);
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
        gbc.anchor = GridBagConstraints.EAST; // Căn lề trái trong lưới
        mainP.add(textField, gbc);
        return textField;
    }
    private JPasswordField createPasswordField(GridBagConstraints gbc, int x, int y) {
        JPasswordField textField = new JPasswordField();
        textField.setPreferredSize(new Dimension(300, 30));
        textField.setFont(textField.getFont().deriveFont(Font.BOLD, 18));
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setBackground(Color.WHITE);
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.anchor = GridBagConstraints.EAST; // Căn lề trái trong lưới
        mainP.add(textField, gbc);
        return textField;
    }

    private void handleChangePassword(String confirmPassword) {
        String username = tfUsername.getText();
        String oldPassword = tfOldPassword.getText();
        String newPassword = tfNewPassword.getText();
        if (oldPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mật khẩu cũ không được để trống!");
            return;
        }
        // Kiểm tra xem trường mật khẩu mới và mật khẩu nhập lại có bị trống không
        if (newPassword.isEmpty() || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mật khẩu mới và mật khẩu nhập lại không được để trống!");
            return;
        }
        if (newPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mật khẩu mới không được để trống!");
            return;
        }
        
        if (confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mật khẩu nhập lại không được để trống!");
            return;
        }
            // Kiểm tra xem mật khẩu mới có chứa khoảng trắng không
        if (newPassword.contains(" ")) {
            JOptionPane.showMessageDialog(this, "Mật khẩu không được chứa khoảng trắng.");
            return;
        }
        if (confirmPassword.contains(" ")) {
            JOptionPane.showMessageDialog(this, "Mật khẩu nhập lại không được chứa khoảng trắng.");
            return;
        }
            // Kiểm tra xem mật khẩu mới và mật khẩu nhập lại có khớp nhau không
        if (!newPassword.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "Mật khẩu mới và mật khẩu nhập lại không khớp!");
            return;
        }
        if (newPassword.equals(oldPassword)) {
            JOptionPane.showMessageDialog(this, "Mật khẩu mới và mật khẩu cũ không được trùng!");
            return;
        }
        if (newPassword.length() < 5) {
            JOptionPane.showMessageDialog(this, "Mật khẩu mới phải có ít nhất 5 ký tự!");
            tfUsername.setText(username);
            // tfOldPassword.setText("");
            tfNewPassword.setText("");
            tfConfirmPassword.setText("");
            return;
        }
        System.out.println("Đổi mật khẩu cho user: " + username);
    
        DoiMK_DTO doiMK_DTO = new DoiMK_DTO(username, oldPassword, newPassword);
        boolean isPasswordChanged = doiMK_BUS.changePassword(doiMK_DTO);
    
        if (isPasswordChanged) {
            JOptionPane.showMessageDialog(this, "Đổi mật khẩu thành công!");
        } else {
            JOptionPane.showMessageDialog(this, "Đổi mật khẩu thất bại. Vui lòng kiểm tra lại thông tin!");
            tfUsername.setText(username);
            // tfOldPassword.setText("");
            tfNewPassword.setText("");
            tfConfirmPassword.setText("");
            return;
        }
    
        // Clear input fields after attempting to change the password
        tfUsername.setText(username);
        tfOldPassword.setText("");
        tfNewPassword.setText("");
        tfConfirmPassword.setText("");
    }
    
    public static void main(String[] args) {
        // Tạo cửa sổ JFrame và thiết lập các thuộc tính cơ bản
        JFrame frame = new JFrame("Đổi Mật Khẩu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 700);
        frame.setLocationRelativeTo(null); // Đặt vị trí ở giữa màn hình

        // Tạo một đối tượng của DoiMK và thêm nó vào JFrame
        DoiMK doiMKPanel = new DoiMK(850, 670, "HSK241");
        frame.add(doiMKPanel);

        // Hiển thị JFrame
        frame.setVisible(true);
    }
}