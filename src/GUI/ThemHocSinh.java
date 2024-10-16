package GUI;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.swing.border.EmptyBorder;
import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.Image;
import BUS.LopBUS;
import BUS.NamHocBUS;
import BUS.PhanLopBUS;
import BUS.QLHS_BUS;
import DTO.Account_DTO;
import DTO.HocSinhDTO;
import DTO.PhanLopDTO;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import BUS.ChangeAcc_BUS;
import BUS.HocSinhBUS;
import java.util.Date;
import java.io.*;
import java.nio.file.*;

public class ThemHocSinh extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JTextField textField_mahs;
	public JTextField textField_diachi;
	public JTextField textField_sdt;
	public JTextField textField_chonanh;
	public JTextField textField_tenhs;
    public JComboBox<String> comboBox_khoi;
    public JComboBox<String> comboBox_lop;
    public JComboBox<String> comboBox_gioitinh;
    public JDateChooser dateChooser;
    public JLabel labelimg;
    private int namSinh=0;
    private int tuoi=15;
    private  HocSinhDTO hocSinh;
    private ChangeAcc_BUS accBUS;
    private ImageIcon imageIcon;
    private String pathAnhdd;
    String imagePath;
    String destinationFolder;
    String destinationPath;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new ThemHocSinh();
	}

	/**
	 * Create the frame.
	 */

    public HocSinhDTO getSinhDTO()
    {
        return hocSinh;
    }

	public ThemHocSinh() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize( 945, 449);
        this.setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel Jlabel_title = new JLabel("THÊM HỌC SINH");
		Jlabel_title.setBackground(new Color(255, 255, 255));
		Jlabel_title.setForeground(new Color(255, 0, 0));
		Jlabel_title.setHorizontalAlignment(SwingConstants.CENTER);
		Jlabel_title.setFont(new Font("Tahoma", Font.BOLD, 25));
		Jlabel_title.setBounds(345, 10, 224, 51);
		contentPane.add(Jlabel_title);
		
		JLabel lblNewLabel = new JLabel("Mã học sinh");
		lblNewLabel.setForeground(new Color(0, 0, 160));
		lblNewLabel.setBackground(new Color(240, 240, 240));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(10, 83, 120, 26);
		contentPane.add(lblNewLabel);
		
		textField_mahs = new JTextField();
		textField_mahs.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textField_mahs.setBounds(170, 84, 198, 26);
		contentPane.add(textField_mahs);
		textField_mahs.setColumns(10);
        textField_mahs.setEnabled(false);

		
		JLabel lblTnHcSinh = new JLabel("Tên học sinh");
		lblTnHcSinh.setForeground(new Color(0, 0, 160));
		lblTnHcSinh.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTnHcSinh.setBackground(UIManager.getColor("Button.background"));
		lblTnHcSinh.setBounds(10, 135, 120, 26);
		contentPane.add(lblTnHcSinh);
		
		textField_diachi = new JTextField();
		textField_diachi.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textField_diachi.setColumns(10);
		textField_diachi.setBounds(170, 245, 198, 26);
		contentPane.add(textField_diachi);
		
		JLabel lblNewLabel_1_1 = new JLabel("Địa chỉ");
		lblNewLabel_1_1.setForeground(new Color(0, 0, 160));
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_1.setBackground(UIManager.getColor("Button.background"));
		lblNewLabel_1_1.setBounds(10, 243, 76, 26);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Số điện thoại");
		lblNewLabel_1_2.setForeground(new Color(0, 0, 160));
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_2.setBackground(UIManager.getColor("Button.background"));
		lblNewLabel_1_2.setBounds(10, 290, 133, 36);
		contentPane.add(lblNewLabel_1_2);
		
		textField_sdt = new JTextField();
		textField_sdt.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textField_sdt.setColumns(10);
		textField_sdt.setBounds(170, 297, 198, 26);
		contentPane.add(textField_sdt);
		
		JLabel lblChnnh = new JLabel("Chọn ảnh");
		lblChnnh.setForeground(new Color(0, 0, 160));
		lblChnnh.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblChnnh.setBackground(UIManager.getColor("Button.background"));
		lblChnnh.setBounds(421, 78, 100, 36);
		contentPane.add(lblChnnh);
		
		textField_chonanh = new JTextField();
		textField_chonanh.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textField_chonanh.setColumns(10);
		textField_chonanh.setBounds(531, 85, 224, 26);
		contentPane.add(textField_chonanh);
		
		JButton button_chon = new JButton("Chọn");
		button_chon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});

		button_chon.setBackground(new Color(192, 192, 192));
		button_chon.setFont(new Font("Tahoma", Font.BOLD, 11));
		button_chon.setForeground(new Color(255, 0, 0));
		button_chon.setBounds(770, 84, 65, 26);
		contentPane.add(button_chon);
        button_chon.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
               chooseImage();
            }
        });
		
		JLabel lblGiiTnh = new JLabel("Khối");
		lblGiiTnh.setForeground(new Color(0, 0, 160));
		lblGiiTnh.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblGiiTnh.setBackground(UIManager.getColor("Button.background"));
		lblGiiTnh.setBounds(421, 132, 88, 32);
		contentPane.add(lblGiiTnh);
		
        String khoi [] = {"10", "11", "12"};
	    comboBox_khoi = new JComboBox<String>(khoi);
		comboBox_khoi.setFont(new Font("Tahoma", Font.PLAIN, 13));
		comboBox_khoi.setBounds(531, 138, 224, 24);
		contentPane.add(comboBox_khoi);
        comboBox_khoi.addActionListener(new ActionListener() {
           @Override
            public void actionPerformed(ActionEvent e)
            {
                String selected = comboBox_khoi.getSelectedItem().toString();
                LopBUS lopbus = new LopBUS();
                ArrayList<String> listlop = lopbus.list_TenLop(selected);
                comboBox_lop.removeAllItems();
                for (String string : listlop) {
                    comboBox_lop.addItem(string);
                }
            }
        });
		
        LopBUS lopbus = new LopBUS();
        ArrayList<String> listlop = lopbus.list_TenLop(comboBox_khoi.getSelectedItem()+"");
	    comboBox_lop = new JComboBox<String>();
        for (String string : listlop) {
            comboBox_lop.addItem(string);
        }
		comboBox_lop.setFont(new Font("Tahoma", Font.PLAIN, 13));
		comboBox_lop.setBounds(531, 190, 224, 26);
		contentPane.add(comboBox_lop);
		
		JLabel lblLp = new JLabel("Lớp");
		lblLp.setForeground(new Color(0, 0, 160));
		lblLp.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblLp.setBackground(UIManager.getColor("Button.background"));
		lblLp.setBounds(421, 183, 40, 36);
		contentPane.add(lblLp);
		
		JLabel lblNmSinh = new JLabel("Năm sinh");
		lblNmSinh.setForeground(new Color(0, 0, 160));
		lblNmSinh.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNmSinh.setBackground(UIManager.getColor("Button.background"));
		lblNmSinh.setBounds(421, 249, 88, 36);
		contentPane.add(lblNmSinh);
		
		JButton button_xacnhan = new JButton("Xác nhận");
		button_xacnhan.setForeground(new Color(255, 255, 255));
		button_xacnhan.setFont(new Font("Tahoma", Font.BOLD, 15));
		button_xacnhan.setBackground(new Color(0, 64, 128));
		button_xacnhan.setBounds(268, 347, 109, 43);
		contentPane.add(button_xacnhan);
        button_xacnhan.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                if(e.getSource()==button_xacnhan)
                {   
                    QLHS_BUS qlhsBUS = new QLHS_BUS();
                    if(textField_tenhs.getText().equals("")||textField_diachi.getText().equals("")||textField_sdt.getText().equals("")||dateChooser.getDate().toString().equals(""))
                    {
                        JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin", "Thông báo",  JOptionPane.ERROR_MESSAGE);
                        return ;
                    }
                    else if(checkTen(textField_tenhs.getText())==false)
                    {
                        JOptionPane.showMessageDialog(null, "Tên không hợp lệ", "Thông báo",  JOptionPane.ERROR_MESSAGE);
                        return ;
                    }
                    else if(checkSDT(textField_sdt.getText())==false)
                    {
                        JOptionPane.showMessageDialog(null, "Số điện thoại không hợp lệ!", "Thông báo",  JOptionPane.ERROR_MESSAGE);
                        return ;
                    }
                    else if(qlhsBUS.checkSDT(textField_sdt.getText())==false)
                    {
                        JOptionPane.showMessageDialog(null, "Số điện thoại đã tồn tại!", "Thông báo",  JOptionPane.ERROR_MESSAGE);
                        return ;
                    }
                    else if(checkDiaChi(textField_diachi.getText())==false)
                    {
                        JOptionPane.showMessageDialog(null, "Địa chỉ không hợp lệ", "Thông báo",  JOptionPane.ERROR_MESSAGE);
                        return ;
                    }
                    else if (checknamhoc()==false) {
                        JOptionPane.showMessageDialog(null, "Năm học không tồn tại", "Thông báo",  JOptionPane.ERROR_MESSAGE);
                        return ;
                    }
                    int result = JOptionPane.showConfirmDialog(null, 
                    "Bạn chắc chắn muốn thêm học sinh này?",
                    "Xác nhận",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            
                    if (result == JOptionPane.YES_OPTION)
                    {
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        Date date = dateChooser.getDate();
                        String dateString = sdf.format(date);
                
                        String HocSinhID = textField_mahs.getText();
                        String TenHocSinh = textField_tenhs.getText();
                        String GioiTinh = comboBox_gioitinh.getSelectedItem().toString();
                        String NgaySinh = dateString;
                        String DienThoai =  textField_sdt.getText();
                        String DiaChi = textField_diachi.getText();
                        String IMG = textField_chonanh.getText();
                        hocSinh = new HocSinhDTO(HocSinhID, TenHocSinh, GioiTinh, NgaySinh, DienThoai, DiaChi);
                        hocSinh.setIMG(IMG);
                        HocSinhBUS hsBUS = new HocSinhBUS();
                        hsBUS.add(hocSinh);
                        System.out.println("thêm học sinh thành công");
                        String tenlop = comboBox_lop.getSelectedItem().toString();
                        Calendar calendar = Calendar.getInstance();
                        // Lấy năm từ Calendar
                        int nam = calendar.get(Calendar.YEAR);
                        int namke = nam+1;
                        String manh = nam+""+namke;
                        String nienkhoa = nam+"-"+namke;
                        String idlop = new LopBUS().getIdByCondString(tenlop);
                        PhanLopDTO phanlop = new PhanLopDTO(hocSinh.getHocSinhID(), idlop, manh);
                        new PhanLopBUS().add(phanlop);
                        autoCreateAccount(hocSinh.getHocSinhID(), hocSinh.getDienThoai());
                        Object[] rowData = { hocSinh.getHocSinhID(), hocSinh.getTenHocSinh(), hocSinh.getGioiTinh(), hocSinh.getNgaySinh(),hocSinh.getDiaChi() , hocSinh.getDienThoai(), hocSinh.getIMG(),tenlop,nienkhoa  };
            
                        JOptionPane.showMessageDialog(null,
                        "Thêm thành công",
                        "Chức năng thêm",
                        JOptionPane.INFORMATION_MESSAGE);
                        QuanLiHocSinh.tblmodel.addRow(rowData);
                        if(!textField_chonanh.getText().equals(""))
                        {
                            luuanhkhixacnhan();
                        }
                        dispose();
                    } 
                    else if (result == JOptionPane.NO_OPTION) 
                    {
                        return;
                    }
                }
            }
        });
		
		JButton button_huybo = new JButton("Hủy bỏ");
		button_huybo.setForeground(new Color(255, 255, 255));
		button_huybo.setFont(new Font("Tahoma", Font.BOLD, 15));
		button_huybo.setBackground(Color.RED);
		button_huybo.setBounds(541, 347, 109, 43);
		contentPane.add(button_huybo);
        button_huybo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                if(e.getSource()==button_huybo)
                {
                    int result = JOptionPane.showConfirmDialog(null, 
                    "Bạn chắc chắn muốn hủy bỏ?",
                    "Xác nhận Hủy",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            
                    if (result == JOptionPane.YES_OPTION)
                    {
                        dispose();
                    } 
                    else if (result == JOptionPane.NO_OPTION) 
                    {
                        return;
                    }
                }
            }
        });
		
		textField_tenhs = new JTextField();
		textField_tenhs.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textField_tenhs.setColumns(10);
		textField_tenhs.setBounds(170, 135, 198, 26);
		contentPane.add(textField_tenhs);
		
		JLabel lblGiiTnh_1 = new JLabel("Giới tính");
		lblGiiTnh_1.setForeground(new Color(0, 0, 160));
		lblGiiTnh_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblGiiTnh_1.setBackground(UIManager.getColor("Button.background"));
		lblGiiTnh_1.setBounds(10, 185, 100, 32);
		contentPane.add(lblGiiTnh_1);
		
        String gioitinh[] = {"Nam","Nữ"};
		comboBox_gioitinh = new JComboBox<String>(gioitinh);
		comboBox_gioitinh.setFont(new Font("Tahoma", Font.PLAIN, 13));
		comboBox_gioitinh.setBounds(170, 191, 198, 26);
		contentPane.add(comboBox_gioitinh);
		
        dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("dd/MM/yyyy");
        dateChooser.setBounds(530, 249, 226, 26);
        dateChooser.getDateEditor().setEnabled(false);
        contentPane.add(dateChooser);
        dateChooser.getDateEditor().addPropertyChangeListener("date", new PropertyChangeListener() {

            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (dateChooser.getDate() != null) {
                    // Lấy Calendar từ JDateChooser
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(dateChooser.getDate());

                    // Lấy năm từ Calendar
                    namSinh = calendar.get(Calendar.YEAR);
                    
                    //Lấy ngày hiện tại
                    LocalDate currentDate = LocalDate.now();
        
                    // Lấy năm hiện tại
                    int currentYear = currentDate.getYear();
                    tuoi = currentYear-namSinh;
                    if(comboBox_khoi.getSelectedItem().toString().equals("10") && tuoi < 15)
                    {
                        JOptionPane.showMessageDialog(null, "Năm sinh không hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        dateChooser.setDate(null);
                    }
                    else if(tuoi>100)
                    {
                        JOptionPane.showMessageDialog(null, "Năm sinh không hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        dateChooser.setDate(null);
                    }
                    else if(comboBox_khoi.getSelectedItem().toString().equals("11") && tuoi < 16)
                    {
                        JOptionPane.showMessageDialog(null, "Năm sinh không hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        dateChooser.setDate(null);
                    }
                    else if(comboBox_khoi.getSelectedItem().toString().equals("12") && tuoi < 17)
                    {
                        JOptionPane.showMessageDialog(null, "Năm sinh không hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        dateChooser.setDate(null);
                    }
                }
            }
        });

       
        
        labelimg = new JLabel();
        labelimg.setBounds(770, 120, 120, 155);
        contentPane.add(labelimg);

        this.setResizable(false);
        this.setVisible(true);
	}

   
    public boolean checkSDT(String sdt) {
        // Biểu thức chính quy để kiểm tra số điện thoại: bắt đầu bằng số 0 và có đúng 10 chữ số.
        String regex = "^0[0-9]{9}$";
        
        // Kiểm tra xem sdt có khớp với biểu thức chính quy hay không
        return sdt.matches(regex);
    }
    
    public boolean checkTen(String ten) {
        // Updated regex with Unicode letter class and whitespace
        String regex = "^[\\p{L}\\s]+$";
        
        // Test against the regex
        return ten.matches(regex);
    }
    
    /*public boolean checkDiaChi(String tenDC) {
        // Biểu thức chính quy để kiểm tra địa chỉ: nếu chỉ có số thì phải có chữ cái, nếu không thì chỉ có chữ cái là được.
        String regex = "^(?=.*[a-zA-Z])([a-zA-Z0-9ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯăẠ-ỹ\\s,./-]+)$";
        
        // Kiểm tra xem địa chỉ có khớp với biểu thức chính quy hay không
        return tenDC.matches(regex);
    }*/


    public boolean checkDiaChi(String diaChi) {
        // Biểu thức chính quy cho phép chữ cái Unicode, số, khoảng trắng và các ký tự đặc biệt (,./-)
        String regex = "^[\\p{L}0-9\\s,./-]+$";
        
        // Kiểm tra xem địa chỉ có khớp với biểu thức chính quy hay không
        return diaChi.matches(regex);
    }
    
    
    
    public void chooseImage() {
        JFileChooser fileChooser = new JFileChooser();
        // Thiết lập chế độ chỉ cho phép chọn file
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        // Hiển thị hộp thoại chọn file
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            // Lấy đường dẫn của tập tin hình ảnh được chọn
            imagePath = fileChooser.getSelectedFile().getAbsolutePath();
            // Hiển thị đường dẫn trong JTextField
            String fileName = fileChooser.getSelectedFile().getName();
            System.out.println(fileName);
            pathAnhdd = fileName;
            textField_chonanh.setText(pathAnhdd);
            
            // Tạo một ImageIcon từ đường dẫn hình ảnh
            imageIcon = new ImageIcon(imagePath);
    
            // Chỉnh kích thước của hình ảnh để phù hợp với JLabel
            Image image = imageIcon.getImage().getScaledInstance(labelimg.getWidth(), labelimg.getHeight(),
                    Image.SCALE_SMOOTH);
                
            // Tạo một ImageIcon mới từ hình ảnh đã được điều chỉnh kích thước
            ImageIcon scaledImageIcon = new ImageIcon(image);
    
            // Hiển thị hình ảnh trên JLabel
            labelimg.setIcon(scaledImageIcon);
    
            // Lấy đường dẫn đến thư mục gốc của dự án
            String projectRootPath = System.getProperty("user.dir");
    
            // Đường dẫn tương đối đến thư mục image/HocSinh
            destinationFolder = projectRootPath + "\\src\\image\\HocSinh";
            destinationPath = destinationFolder + "\\" + fileName;
    
            // Kiểm tra và tạo thư mục HocSinh nếu chưa tồn tại
            File directory = new File(destinationFolder);
            if (!directory.exists()) {
                directory.mkdirs(); // Tạo thư mục nếu không tồn tại
            }

        }
    }
    
    public void luuanhkhixacnhan()
    {
        // Copy file ảnh
        try {
            Files.copy(Paths.get(imagePath), Paths.get(destinationPath), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Copy thành công: " + destinationPath);
        } catch (IOException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }

    public void autoCreateAccount(String username, String password) { 
        accBUS = new ChangeAcc_BUS();
        Account_DTO acc = new Account_DTO(username, password);
        accBUS.Add(acc);
    }

    public boolean checknamhoc()
    {
        Calendar calendar = Calendar.getInstance();
        int nam = calendar.get(Calendar.YEAR);
        ArrayList<String> listNamBD = new NamHocBUS().listNamBatDau();
        for (String string : listNamBD) {
            if(string.equals(nam+""))
            {
                return true;
            } 
        }
        return false;
    }
}

