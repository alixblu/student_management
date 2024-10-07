package GUI;

import javax.swing.*;

import BUS.ThongBaoBUS;
import DTO.CurrentDateTime;
import DTO.ThongBaoDTO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class admin_guiTB extends JPanel{
    // JFrame f;
    JPanel mainPanel;
    JLabel[] label;
    JCheckBox checkboxHS, checkboxGV;
    JTextField txtHeader;
    JTextArea txtContent;
    JButton btnGui;
    int width, height;
    ThongBaoBUS tbbus = new ThongBaoBUS();

    public admin_guiTB(int width, int height) {
        // f = new JFrame();
        this.width = width;
        this.height = height;
        setLayout(new BorderLayout());
        setSize(width, height);
        
        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(new Color(180, 204, 227));

        label = new JLabel[3];
        for (int i = 0; i < 3; i++) {
            label[i] = new JLabel();
            label[i].setFont(new Font("Arial", Font.BOLD, 16)); // Set font size and bold
        }
        label[0].setText("Gửi thông báo đến:");
        label[0].setBounds(150, 50, 200, 30);
        label[1].setText("Tiêu đề:");
        label[1].setBounds(150, 100, 100, 30);
        label[2].setText("Nhập nội dung thông báo:");
        label[2].setBounds(150, 150, 250, 30);

        checkboxHS = new JCheckBox("Học Sinh");
        checkboxHS.setOpaque(false);
        checkboxHS.setBounds(320, 50, 150, 30); // Adjusted position to the right
        checkboxHS.setFont(new Font("Arial", Font.BOLD, 14)); // Set font size and bold
        checkboxGV = new JCheckBox("Giáo Viên");
        checkboxGV.setOpaque(false);
        checkboxGV.setBounds(470, 50, 150, 30); // Adjusted position to the right
        checkboxGV.setFont(new Font("Arial", Font.BOLD, 14)); // Set font size and bold

        txtHeader = new JTextField();
        txtHeader.setBounds(300, 100, 300, 30);
        txtHeader.setFont(new Font("Arial", Font.BOLD, 14)); // Set font size and bold
        
        txtContent = new JTextArea();
        txtContent.setBounds(150, 200, 600, 300);
        txtContent.setFont(new Font("Arial", Font.BOLD, 14)); // Set font size and bold
        txtContent.setLineWrap(true); // Enable text wrapping
        txtContent.setWrapStyleWord(true); // Wrap at word boundaries
        
        btnGui = new JButton("Gửi");
        btnGui.setBounds(700, 550, 100, 30); // Adjusted position and size
        btnGui.setFont(new Font("Arial", Font.BOLD, 14)); // Set font size and bold
        btnGui.addActionListener(new SendNotiBtnListener());
        // Add components to main panel
        for (JLabel jLabel : label) {
            mainPanel.add(jLabel);
        }
        mainPanel.add(checkboxHS);
        mainPanel.add(checkboxGV);
        mainPanel.add(txtHeader);
        mainPanel.add(txtContent);
        mainPanel.add(btnGui);

        add(mainPanel);
        setVisible(true);
    }
    public class SendNotiBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String loaitb = "";
            CurrentDateTime currDate = new CurrentDateTime();
            
            if(checkboxHS.isSelected() && checkboxGV.isSelected()){
                loaitb = "ALL";
                ThongBaoDTO tb = new ThongBaoDTO("admin", txtHeader.getText(), txtContent.getText(),currDate.getdate(),loaitb);
                tbbus.add(tb);
            }
            else{
                if (checkboxHS.isSelected()) {
                    loaitb = "HS";
                    
                    ThongBaoDTO tb = new ThongBaoDTO("admin", txtHeader.getText(), txtContent.getText(),currDate.getdate(),loaitb);
                    tbbus.add(tb);
                }
                if (checkboxGV.isSelected()) {
                    loaitb = "GV";
                    
                    ThongBaoDTO tb = new ThongBaoDTO("admin", txtHeader.getText(), txtContent.getText(),currDate.getdate(),loaitb);
                    tbbus.add(tb);
                }
                if(loaitb.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Bạn chưa chọn đối tượng muốn gửi thông báo");
                    return;
                }
                
            }
            if (txtContent.getText().equals("") && txtHeader.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Thông báo không thể bỏ trống cả tiêu đề và nội dung");
                return;
            }
            if (txtContent.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Thông báo không được bỏ trống nội dung");
                return;
            }
            if (txtHeader.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Thông báo không được bỏ trống tiêu đề");
                return;
            }
            
            System.out.println("up thong bao to data");
            JOptionPane.showMessageDialog(null, "Thông báo đã được gửi");
            resetText();
        }
        public void resetText(){
            txtContent.setText("");
            txtHeader.setText("");
            checkboxGV.setSelected(false);
            checkboxHS.setSelected(false);
        }
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(850, 670);
        admin_guiTB panel = new admin_guiTB(850, 670);
        frame.add(panel);
        frame.setVisible(true);
    }
}
