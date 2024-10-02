/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
 package GUI;

import BUS.HocSinhBUS;
import BUS.LopBUS;
import BUS.PhanLopBUS;
import DAO.HocSinhDAO;
import DTO.HocSinhDTO;
import DTO.LopDTO;
import DTO.PhanLopDTO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
/**
 *
 * @author PHUONG ANH
 */
public class ThanhToanHocPhi extends JPanel{
    
    JPanel feePanel, topPanel, midPanel;
    JTextField txtSearch, txthoten, txtlop, txtngaysinh, txtdienthoai, txtStatus;
    JLabel l1,l2;
    JButton btnSearch, btnConfirm;
    int width, height;
    HocSinhBUS dsHS = new HocSinhBUS(1);
    PhanLopBUS dsPL = new PhanLopBUS(1);
    LopBUS dsLop = new LopBUS(1);

    public ThanhToanHocPhi(int width, int height){
        this.width = width;
        this.height = height;

        this.setLayout(new BorderLayout());
        this.setSize(width,height);
                
        topPanel=new JPanel();
        topPanel.setPreferredSize(new Dimension(0,80));
        topPanel.setOpaque(true);
        l1=new JLabel("Nhập mã HS cần tìm:");
        txtSearch=new JTextField(6);
        btnSearch=new JButton("Tìm kiếm");
        Font textFieldFont = txtSearch.getFont();
        txtSearch.setFont(new Font(textFieldFont.getName(), Font.PLAIN, 18));
        Font buttonFont = btnSearch.getFont();
        btnSearch.setFont(new Font(buttonFont.getName(), Font.PLAIN, 16));
        txtSearch.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        btnSearch.setBackground(new Color(52,48,128));
        btnSearch.setForeground(Color.white);
        Font labelFont = l1.getFont();
        l1.setFont(new Font(labelFont.getName(), Font.PLAIN, 16));
        JPanel search=new JPanel(new FlowLayout(FlowLayout.CENTER));
        search.setBorder(new EmptyBorder(20, 10, 10, 10));
        search.add(l1);
        search.add(txtSearch);
        search.add(btnSearch);
        search.setOpaque(false);
        
        topPanel.add(search);
        
        midPanel=new JPanel();
        midPanel.setLayout(null);
        midPanel.setPreferredSize(new Dimension(0,0));
        midPanel.setBackground(new Color(180, 204, 227));
        
        JLabel hoten=new JLabel("Họ tên:");
        JLabel ngsinh=new JLabel("Ngày sinh:");
        JLabel lop=new JLabel("Lớp:");
        JLabel dt=new JLabel("Điện thoại:");
        JLabel hocphi=new JLabel("Trạng thái học phí hiện tại:");
        
        txthoten=new JTextField();
        txtngaysinh=new JTextField();
        txtlop=new JTextField();
        txtdienthoai=new JTextField();
        txtStatus=new JTextField();
        txthoten.setEditable(false);
        txtngaysinh.setEditable(false);
        txtlop.setEditable(false);
        txtdienthoai.setEditable(false);
        txtStatus.setEditable(false);
        
        txtSearch.setHorizontalAlignment(JTextField.CENTER);
        txthoten.setHorizontalAlignment(JTextField.CENTER);
        txtlop.setHorizontalAlignment(JTextField.CENTER);
        txtngaysinh.setHorizontalAlignment(JTextField.CENTER);
        txtdienthoai.setHorizontalAlignment(JTextField.CENTER);
        txtStatus.setHorizontalAlignment(JTextField.CENTER);

        hoten.setBounds(30,10,50,20);
        txthoten.setBounds(100,10,280,20);
        ngsinh.setBounds(30,50,80,20);
        txtngaysinh.setBounds(100,50,100,20);
        lop.setBounds(600,10,40,20);
        txtlop.setBounds(680,10,50,20);
        dt.setBounds(600,50,80,20);
        txtdienthoai.setBounds(680,50,80,20);
        hocphi.setBounds(270,90,150,20);
        txtStatus.setBounds(440,90,120,20);
        
        feePanel=new JPanel(new BorderLayout());
        feePanel.setPreferredSize(new Dimension(0,430));
        
        JPanel title=new JPanel(new FlowLayout(FlowLayout.CENTER));
        title.setBorder(new EmptyBorder(20, 0, 0, 0));
        l2=new JLabel("CHI TIẾT HỌC PHÍ");
        l2.setFont(new Font("Arial", Font.BOLD, 24));
        title.add(l2);
        
        JPanel midFee=new JPanel(new GridBagLayout());
        String[] a={"Học phí cơ bản:", "Phí Cơ sở Vật chất:", "Lệ phí thi:", "Phí Sinh hoạt Ngoại khóa:"};
        String []v={"1.500.000d", "200.000d", "50.000d", "100.000d"};
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 10, 10);
        for (int i = 0; i < 4; i++) {
            JLabel label = new JLabel(a[i]);
            label.setFont(new Font("Arial", Font.BOLD, 17));
            gbc.gridx = 0;
            gbc.gridy = i;
            midFee.add(label, gbc);
        }
        gbc.anchor = GridBagConstraints.EAST;
        for (int i = 0; i < 4; i++) {
            JLabel label = new JLabel(v[i]);
            label.setFont(new Font("Arial", Font.PLAIN, 17));
            gbc.gridx = 1;
            gbc.gridy = i;
            midFee.add(label, gbc);
        }
        
        JPanel b_Fee=new JPanel();
        b_Fee.setLayout(null);
        b_Fee.setPreferredSize(new Dimension(0,100));
        JLabel l=new JLabel("Tổng cộng:   1.850.000d");
        l.setBounds(150, 10, 300, 60);
        l.setFont(new Font("Arial", Font.BOLD, 20));
        l.setForeground(Color.red);
        btnConfirm=new JButton("XÁC NHẬN THANH TOÁN");
        btnConfirm.setFont(new Font("Arial", Font.BOLD, 15));
        btnConfirm.setBounds(500, 10, 220, 50);
        btnConfirm.setForeground(Color.white);

        b_Fee.add(l);
        b_Fee.add(btnConfirm);
        
        midPanel.add(hoten);
        midPanel.add(txthoten);
        midPanel.add(ngsinh);
        midPanel.add(txtngaysinh);        
        midPanel.add(lop);
        midPanel.add(txtlop);
        midPanel.add(dt);
        midPanel.add(txtdienthoai);
        midPanel.add(hocphi);
        midPanel.add(txtStatus);
        
        feePanel.add(title, BorderLayout.NORTH);
        feePanel.add(midFee, BorderLayout.CENTER);
        feePanel.add(b_Fee, BorderLayout.SOUTH);
        
        this.add(topPanel,BorderLayout.NORTH);
        this.add(midPanel,BorderLayout.CENTER);
        this.setVisible(true);

        
        btnSearch.addActionListener(new SearchBtnListener());
        btnConfirm.addActionListener(new ConfirmBtnListener());
    }
    
    
   

    private class SearchBtnListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

              String id = txtSearch.getText().trim().toUpperCase();
              
              try{
                  HocSinhDTO hs = null;
                  for (HocSinhDTO x : dsHS.getList()){
                      if(x.getHocSinhID().equals(id)){
                          hs = x;
                          txthoten.setText(hs.getTenHocSinh());
                          txtngaysinh.setText(hs.getNgaySinh());
                          txtdienthoai.setText(hs.getDienThoai());
                          txtStatus.setText(hs.getHocPhi());
                          if(x.getHocPhi().equals("Đã thanh toán")){
                                btnConfirm.setBackground(Color.lightGray);
                                btnConfirm.setEnabled(false);
                                btnConfirm.setForeground(Color.white);

                              }
                          else{
                            btnConfirm.setEnabled(true);
                            btnConfirm.setBackground(new Color(255,49,49));
                            btnConfirm.setForeground(Color.white);

                            feePanel.repaint();
                          }
                          
                          break;
                      }
                  }
                  if (hs!=null){
                      PhanLopDTO pl = null;
                      for(PhanLopDTO x: dsPL.getList()){
                          if(x.getHocSinhID().equals(id)  && x.getNamHocID().equals("giapthin")){
                              pl = x;
                              break;
                          }
                      }
                      
                      if(pl!=null){
                          LopDTO lop = null;
                          for (LopDTO x: dsLop.getList()){
                              if(x.getLopID().equals(pl.getLopID())){
                              lop = x;
                              txtlop.setText(lop.getTenLop());
                              add(feePanel,BorderLayout.SOUTH);

                              revalidate();
                              repaint();
                          
                              break;
                               }
                           }
                      }else{
                          JOptionPane.showMessageDialog(null, "Không tìm thấy Lớp");
                            }
                      
                  }else{
                      JOptionPane.showMessageDialog(null, "Không tìm thấy Học sinh ");
                    }
                    
                }catch(Exception ex) {
                    ex.printStackTrace();
                }
        }
    }
    
    private class ConfirmBtnListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            try{
                String id = txtSearch.getText().trim().toUpperCase();
                
                for (HocSinhDTO x : dsHS.getList()){
                      if(x.getHocSinhID().equals(id)){

                              int choice = JOptionPane.showConfirmDialog(null, "Xác nhận thanh toán cho tài khoản "+x.getHocSinhID()+" ?","", JOptionPane.OK_CANCEL_OPTION);
                          
                                if (choice == JOptionPane.OK_OPTION){
                                  x.setHocPhi("Đã thanh toán");
                                  HocSinhDAO updateHS = new HocSinhDAO();
                                  updateHS.set(x);
                                  txtStatus.setText(x.getHocPhi());
                                  JOptionPane.showMessageDialog(null, "Thanh toán thành công ");
                                  btnConfirm.setBackground(Color.lightGray);
                                  break;
                                }else if (choice == JOptionPane.CANCEL_OPTION || choice == JOptionPane.CLOSED_OPTION) {
                                    break;
        }
                                
                        }
                }
                
            }catch(Exception ex) {
                    ex.printStackTrace();
                }
        }
    }
    public static void main(String[] args)  {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(850, 670);
        ThanhToanHocPhi panel = new ThanhToanHocPhi(850, 670);
        frame.add(panel);
        frame.setVisible(true);
    }
}