package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import BUS.HocSinhBUS;
import BUS.LopBUS;
import BUS.NamHocBUS;
import BUS.PhanCongBUS;
import BUS.PhanLopBUS;
import BUS.ThongBaoBUS;
import DTO.CurrentDateTime;
import DTO.HocSinhDTO;
import DTO.LopDTO;
import DTO.NamHocDTO;
import DTO.PhanCongDTO;
import DTO.PhanLopDTO;
import DTO.ThongBaoDTO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class gv_guiTB extends JPanel{
    // JFrame f;
    JPanel mainPanel, background;
    JLabel[] label;
    // JRadioButton radioButtonHS, radioButtonLop;
    JCheckBox checkBoxLop, checkBoxHS;
    JTextField txtHeader, txtLop, txtHS;
    JTextArea txtContent;
    JButton btnGui;
    int width, height;
    private String magiaovien, idhs,idlop;
    CurrentDateTime currDate = new CurrentDateTime();
    int namhientai = currDate.getYear();
    ArrayList <HocSinhDTO> dshs;
    ArrayList<LopDTO> dslop;
    ArrayList<PhanLopDTO> dspl;
    ArrayList<PhanCongDTO> dspc;
    ArrayList<NamHocDTO> dsnh;
    PhanLopBUS plbus = new PhanLopBUS(1);
    LopBUS lopbus = new LopBUS(1);
    HocSinhBUS hsbus = new HocSinhBUS(1);
    PhanCongBUS pcbus = new PhanCongBUS(1);
    NamHocBUS nhbus = new NamHocBUS(1);
    ThongBaoBUS tbbus = new ThongBaoBUS();
    public gv_guiTB(int width, int height,String magiaovien) {
        this.magiaovien = magiaovien;
        this.width = width;
        this.height = height;
        // f = new JFrame();
        setLayout(new BorderLayout());
        setSize(width, height);
        
    
        background = new JPanel();
        background.setLayout(new BorderLayout());
    
        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(new Color(180, 204, 227));
    
        label = new JLabel[3];
        for (int i = 0; i < 3; i++) {
            label[i] = new JLabel();
            label[i].setFont(new Font("Arial", Font.BOLD, 16)); // Set font size and bold
        }
        label[0].setText("Gửi thông báo đến:");
        label[0].setBounds(100, 50, 200, 30); // Adjusted x coordinate
        label[1].setText("Tiêu đề:");
        label[1].setBounds(100, 100, 100, 30); // Adjusted x coordinate
        label[2].setText("Nhập nội dung thông báo:");
        label[2].setBounds(100, 150, 250, 30); // Adjusted x coordinate

        checkBoxLop = new JCheckBox("Lớp:");
        checkBoxLop.setOpaque(false);
        checkBoxLop.setBounds(270, 50, 150, 30); // Adjusted x coordinate
        checkBoxLop.setFont(new Font("Arial", Font.BOLD, 14));

        checkBoxHS = new JCheckBox("Học Sinh:");
        checkBoxHS.setOpaque(false);
        checkBoxHS.setBounds(520, 50, 150, 30); // Adjusted x coordinate
        checkBoxHS.setFont(new Font("Arial", Font.BOLD, 14));
        checkBoxHS.setEnabled(false);  // Initially disabled
    
        // radioButtonLop = new JRadioButton("Lớp:");
        // radioButtonLop.setOpaque(false);
        // radioButtonLop.setBounds(270, 50, 150, 30); // Adjusted x coordinate
        // radioButtonLop.setFont(new Font("Arial", Font.BOLD, 14));
    
        // radioButtonHS = new JRadioButton("Học Sinh:");
        // radioButtonHS.setOpaque(false);
        // radioButtonHS.setBounds(520, 50, 150, 30); // Adjusted x coordinate
        // radioButtonHS.setFont(new Font("Arial", Font.BOLD, 14));
        // radioButtonHS.setEnabled(false);
    
        // Group the radio buttons
        ButtonGroup buttonGroup = new ButtonGroup();
        // buttonGroup.add(radioButtonHS);
        // buttonGroup.add(radioButtonLop);
        // buttonGroup.add(checkBoxHS);
        // buttonGroup.add(checkBoxLop);
    
        txtHeader = new JTextField();
        txtHeader.setBounds(250, 100, 300, 30); // Adjusted x coordinate
        txtHeader.setFont(new Font("Arial", Font.BOLD, 14));
    
        txtContent = new JTextArea();
        txtContent.setLineWrap(true); // Enable text wrapping
        txtContent.setWrapStyleWord(true); // Wrap at word boundaries
        txtContent.setBounds(100, 200, 600, 300); // Adjusted x coordinate
        txtContent.setFont(new Font("Arial", Font.BOLD, 14));
    
        txtLop = new JTextField();
        txtLop.setEditable(false);
        txtLop.setBorder(BorderFactory.createLineBorder(new Color(52, 48, 128)));
        txtLop.setHorizontalAlignment(JTextField.CENTER);
        txtLop.setBounds(350, 50, 70, 30); // Adjusted x coordinate
    
        txtHS = new JTextField();
        txtHS.setEditable(false);
        txtHS.setBorder(BorderFactory.createLineBorder(new Color(52, 48, 128)));
        txtHS.setHorizontalAlignment(JTextField.CENTER);
        txtHS.setBounds(635, 50, 150, 30); // Adjusted x coordinate
    
        checkBoxHS.addActionListener(new CheckBoxHSListener());
    
        checkBoxLop.addActionListener(new CheckBoxLopListener());
    
        btnGui = new JButton("Gửi"); // Create button
        btnGui.setBounds(600, 550, 100, 30); // Adjusted x coordinate
        for (JLabel jLabel : label) {
            mainPanel.add(jLabel);
        }
        btnGui.addActionListener(new SendNotiBtnListener());
        mainPanel.add(checkBoxHS);
        mainPanel.add(checkBoxLop);
        mainPanel.add(txtHeader);
        mainPanel.add(txtContent);
        mainPanel.add(txtLop);
        mainPanel.add(txtHS);
        mainPanel.add(btnGui);
    
        background.add(mainPanel);
        add(background);
        setVisible(true);
    }
    
    

    // Method to create a panel containing a table
    private JPanel createLopTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());

        dspl = plbus.getList();
        dspc = pcbus.search(magiaovien, null);

        JTable table = new JTable();
        DefaultTableModel tblModel = new DefaultTableModel();
        table.setModel(tblModel);
        JScrollPane scrollPane = new JScrollPane(table);

        for (PhanCongDTO pc : dspc){
            String idlop = pc.getLopID();
            String tenlop = lopbus.get(idlop).getTenLop();

            String[] columnNames = {"ID Lớp", "Tên Lớp"};
            String[] rowData = new String[]{
                idlop, tenlop
            };
            tblModel.setColumnIdentifiers(columnNames);
            tblModel.addRow(rowData);
        }

        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
        }

    private JPanel createHSTablePanel(String lopid) {
        JPanel panel = new JPanel(new BorderLayout());

        dspc = pcbus.search(magiaovien, null);
        String idnam = nhbus.getByStartYear(namhientai).getNamHocID();
        
        JTable table = new JTable();
        DefaultTableModel tblModel = new DefaultTableModel();
        table.setModel(tblModel);
        JScrollPane scrollPane = new JScrollPane(table);
        
        // for (PhanCongDTO pc : dspc){
        //     String idlop = pc.getLopID();
            dspl = plbus.search(null,lopid,idnam );
            for (PhanLopDTO pl : dspl){
                String idhs = pl.getHocSinhID();
                String tenhs = hsbus.get(idhs).getTenHocSinh();

                String[] columnNames = {"ID HS", "Tên HS"};
                String[] rowData = new String[]{
                    idhs, tenhs
                };
                tblModel.setColumnIdentifiers(columnNames);
                tblModel.addRow(rowData);
            }
        // }

        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
        }

        private class CheckBoxLopListener implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkBoxLop.isSelected()) {
                    // Create a custom panel with a table
                    JPanel panel = createLopTablePanel();
                    // Show the panel in JOptionPane
                    int result = JOptionPane.showConfirmDialog(null, panel, "Chọn Lớp", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                    if (result == JOptionPane.OK_OPTION) {
                        JTable table = (JTable) ((JScrollPane) panel.getComponent(0)).getViewport().getView();
                        int selectedRow = table.getSelectedRow();
                        if (selectedRow != -1) { 
                            Object selectedID = table.getValueAt(selectedRow, 0);
                            Object selectedName = table.getValueAt(selectedRow, 1);
        
                            idlop = String.valueOf(selectedID);
                            txtLop.setText(String.valueOf(selectedName));
                            checkBoxHS.setEnabled(true);  // Enable checkBoxHS after selecting the class
                        }
                    }
                } else {
                    checkBoxHS.setEnabled(false);  // Disable checkBoxHS if no class selected
                    txtLop.setText("");
                    txtHS.setText("");
                    checkBoxHS.setSelected(false);;
                }
            }
        }
        
        private class CheckBoxHSListener implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkBoxHS.isSelected()) {
                    JPanel panel = createHSTablePanel(idlop);
                    int result = JOptionPane.showConfirmDialog(null, panel, "Chọn Học sinh", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                    if (result == JOptionPane.OK_OPTION) {
                        JTable table = (JTable) ((JScrollPane) panel.getComponent(0)).getViewport().getView();
                        int selectedRow = table.getSelectedRow();
                        if (selectedRow != -1) {
                            Object selectedID = table.getValueAt(selectedRow, 0);
                            Object selectedName = table.getValueAt(selectedRow, 1);
        
                            idhs = String.valueOf(selectedID);
                            txtHS.setText(String.valueOf(selectedName));
                        }
                    }
                }
                else{
                    txtHS.setText("");
                }
            }
        }
        
    private class SendNotiBtnListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String loaitb = "";
            if (checkBoxLop.isSelected()&& checkBoxHS.isSelected()) {
                loaitb = idhs;
            } else if( checkBoxLop.isSelected()) {
                loaitb = idlop;
            } else {
                JOptionPane.showMessageDialog(null, "Bạn chưa chọn đối tượng muốn gửi thông báo");
                return;
            }
            if (txtContent.getText().equals("") && txtHeader.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Thông báo không thể bỏ trống cả tiêu đề và nội dung");
                return;
            }
            ThongBaoDTO tb = new ThongBaoDTO(magiaovien, txtHeader.getText(), txtContent.getText(), String.valueOf(currDate.getdate()),loaitb);

            System.out.println("up thong bao to data");

            tbbus.add(tb);
            JOptionPane.showMessageDialog(null, "Thông báo đã được gửi");
            resetText();
        }
        public void resetText(){
            txtHS.setText("");
            txtLop.setText("");
            txtContent.setText("");
            txtHeader.setText("");
            checkBoxHS.setSelected(false);
            checkBoxLop.setSelected(false);

        }
    }
    // public static void main(String[] args){
    //     JFrame frame = new JFrame();
    //     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //     frame.setSize(850, 670);
    //     gv_guiTB panel = new gv_guiTB(850, 670,"GV3");
    //     frame.add(panel);
    //     frame.setVisible(true);
    // }
}
