package GUI;

// import DATABASE.MyConnection;
import DATABASE.MySQLConnect;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
//--------------------------------------------

public class TestDN extends JFrame {
    private JPanel jptop, jpleft, jpright, lll;
    private JLabel jltendangnhap, jlmatkhau;
    private JTextField jtf1;
    private JButton jbxacnhan;
    private JPasswordField jtf2;
    private String userName;
    private MySQLConnect mysql = new MySQLConnect();

    public TestDN() {
        init();
        this.setSize(1000, 600);
        this.setUndecorated(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void init() {

        this.setLayout(new BorderLayout());
        Font font1 = new Font("Arical", Font.BOLD, 25);
        Font font2 = new Font("Arical", Font.BOLD, 15);
        // màu backround
        Color my_color_top = new Color(78, 78, 234);
        Color my_color_west = new Color(52, 48, 128);

        // top
        jptop = new JPanel();
        jptop.setLayout(new BorderLayout());
        jptop.setBackground(my_color_top);
        jptop.setPreferredSize(new Dimension(0, 40));
        JLabel title_top = new JLabel("QUAN LY HOC SINH");
        title_top.setIcon(new javax.swing.ImageIcon(getClass().getResource("Students-icon.png")));
        jptop.add(title_top, BorderLayout.WEST);

        // right
        jpright = new JPanel();
        jpright.setLayout(new BorderLayout());
        JLabel anh1 = new JLabel();
        anh1.setIcon(new javax.swing.ImageIcon(getClass().getResource("imang_trangchu.jpg")));
        jpright.add(anh1);

        // west
        // log in
        jpleft = new JPanel();
        jpleft.setLayout(new BorderLayout());
        jpleft.setBackground(Color.yellow);
        jpleft.setPreferredSize(new Dimension(300, 500));

        JPanel k1 = new JPanel();
        k1.setBorder(BorderFactory.createLineBorder(my_color_west, 30));
        k1.setLayout(new BorderLayout());
        k1.setBackground(my_color_west);
        k1.setPreferredSize(new Dimension(0, 150));
        JLabel H1 = new JLabel("ĐĂNG NHẬP", JLabel.CENTER);
        H1.setFont(font1);
        H1.setForeground(Color.gray);
        k1.setBackground(my_color_west);
        JLabel H2 = new JLabel("Vui lòng đăng nhập để sử dụng hệ thống", JLabel.CENTER);
        k1.add(H1, BorderLayout.CENTER);
        k1.add(H2, BorderLayout.SOUTH);

        JPanel k2 = new JPanel();
        k2.setLayout(new BorderLayout());
        k2.setBackground(my_color_west);
        k2.setBorder(BorderFactory.createLineBorder(my_color_west, 10));

        JPanel hh1 = new JPanel();
        hh1.setPreferredSize(new Dimension(50, 0));
        hh1.setBackground(my_color_west);
        k2.add(hh1, BorderLayout.WEST);
        JPanel hh2 = new JPanel();
        hh2.setPreferredSize(new Dimension(50, 0));
        hh2.setBackground(my_color_west);
        k2.add(hh2, BorderLayout.EAST);
        JPanel k21 = new JPanel();
        k21.setBackground(my_color_west);
        k21.setLayout(new GridLayout(4, 1, 5, 5));
        k21.setPreferredSize(new Dimension(1000, 500));

        JPanel tdn = new JPanel();
        tdn.setLayout(new BorderLayout());
        tdn.setBackground(my_color_west);
        jltendangnhap = new JLabel("Tên đăng nhập");
        jltendangnhap.setForeground(Color.white);
        jltendangnhap.setFont(font2);
        tdn.add(jltendangnhap, BorderLayout.SOUTH);
        jtf1 = new JTextField();

        JPanel nmk = new JPanel();
        nmk.setLayout(new BorderLayout());
        nmk.setBackground(my_color_west);
        jtf1.setPreferredSize(new Dimension(50, 200));

        jlmatkhau = new JLabel("Nhập mật khẩu");

        jlmatkhau.setForeground(Color.white);
        jlmatkhau.setFont(font2);
        nmk.add(jlmatkhau, BorderLayout.SOUTH);
        jtf2 = new JPasswordField();

        k21.add(tdn);
        k21.add(jtf1);
        k21.add(nmk);
        k21.add(jtf2);
        k2.add(k21);

        JPanel k3 = new JPanel();
        k3.setLayout(null);
        k3.setBackground(my_color_west);
        k3.setPreferredSize(new Dimension(0, 250));

        jbxacnhan = new JButton("Xác nhận");
        // actionlistner
        jbxacnhan.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (jtf1.getText().equals("")) {
                    JOptionPane.showMessageDialog(jpright, "Bạn chưa nhập tài khoản");
                    return;
                }
                if (String.valueOf(jtf2.getPassword()).equals("")) {
                    JOptionPane.showMessageDialog(jpright, "Bạn chưa nhập mật khẩu");
                    return;
                }

                Connection con = mysql.getConnection();
                PreparedStatement ps;

                try {
                    // Sử dụng BINARY để so sánh phân biệt
                    ps = con.prepareStatement("SELECT * FROM user WHERE BINARY username = ? AND BINARY password = ?");
                    ps.setString(1, jtf1.getText());
                    ps.setString(2, String.valueOf(jtf2.getPassword()));

                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        System.out.println("Yes");
                        userName = jtf1.getText();
                        Text mf = new Text(userName);
                        mf.setVisible(true);
                        mf.setLocationRelativeTo(null);
                        TestDN.this.dispose();
                    } else {
                        System.out.println("NO");
                        JOptionPane.showMessageDialog(jpright, "Tài khoản không chính xác");
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        jbxacnhan.setForeground(Color.blue);
        jbxacnhan.setBackground(Color.gray);
        jbxacnhan.setBounds(60, 30, 100, 30);
        jbxacnhan.setToolTipText("Nhấn vào đây để xác nhận");
        k3.add(jbxacnhan);

        jpleft.add(k1, BorderLayout.NORTH);
        jpleft.add(k2, BorderLayout.CENTER);
        jpleft.add(k3, BorderLayout.SOUTH);

        // chuc nang tieu de
        ChucNangTieuDe cntd = new ChucNangTieuDe(this);
        jptop.add(cntd, BorderLayout.EAST);

        add(jptop, BorderLayout.NORTH);
        add(jpright, BorderLayout.CENTER);
        this.add(jpleft, BorderLayout.WEST);

    }

    public static void main(String[] args) {
        new TestDN();
    }
}
