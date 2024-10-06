
package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import com.toedter.calendar.JDateChooser;

import BUS.ChangeAcc_BUS;
import BUS.PhanLopBUS;
//------------------
import BUS.ThongBaoBUS;
import DTO.ThongBaoDTO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vhuyn
 */
public final class tb_hs extends JPanel implements MouseListener, ActionListener {
    private String idnguoigui, tieudetb, noidungtb, thoigiantb, loaitb;
    private DefaultTableModel tblmodel;
    private String username;
    private JScrollPane scrollpane;
    JTextField[] tf;
    JButton[] buttons;
    JTable t;
    int width, height;
    Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
    private JTextField JsearchText;
    DefaultTableModel model;
    TableRowSorter<DefaultTableModel> sorter;
    JDateChooser dateChooser;
    JComboBox<String> genderComboBox;
   
    ThongBaoBUS tbBUS=new ThongBaoBUS();
    ChangeAcc_BUS accBUS = new ChangeAcc_BUS();


    PhanLopBUS plBus = new PhanLopBUS(1);
    private ArrayList<ThongBaoDTO> filteredList;
    private ArrayList<ThongBaoDTO> hs;

    public tb_hs(int width, int height,String username) throws SQLException {
        this.width = width;
        this.height = height;
        this.username = username;
        System.out.println("day chinh la " + username);
        init();
        
    }

    public void init() throws SQLException {

        // this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Color myColor = new Color(99, 116, 198);
        Color searchPanel = new Color(180, 204, 227);
        this.setLayout(new BorderLayout());
        JPanel p3 = new JPanel();
        
        p3.setPreferredSize(new Dimension(0, 60));
        p3.setBackground(searchPanel);
        JLabel label = new JLabel("THÔNG BÁO");
        label.setFont(new Font("Arial", Font.BOLD, 30));
        p3.add(label);
        JPanel p1 = JHocsinh();
        p1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 4, true));
        p1.setBackground(myColor);
        p1.setPreferredSize(new Dimension(0, 0));

        JPanel p2 = new JPanel();
        p2.setLayout(new FlowLayout(1, 0, 0));
        p2.add(initTable());
        p2.setPreferredSize(new Dimension(0, 400));
        p2.setBackground(Color.gray);

        this.add(p1, BorderLayout.CENTER);
        this.add(p2, BorderLayout.SOUTH);
        this.add(p3, BorderLayout.NORTH);
        this.setSize(new Dimension(width, height));
        this.setVisible(true);
      
    }



    
    public JPanel JHocsinh() {
        JPanel Phocsinh = new JPanel();
        Phocsinh.setLayout(null);
        String[] arrHocsinh = { "ID người gửi ", "tiêu đề TB", "Nội dung TB", "thời gian TB"};
        int length = arrHocsinh.length;
        tf = new JTextField[length];
        buttons = new JButton[length];
        Phocsinh.setLayout(null);
        int toadoXbutton = 190;
        int toadoYbutton = 10;
        int toadoXTextfield = 330;
        int toadoYTextfield = 10;
        int x = 230;
        int y = 15;
        for (int i = 0; i < arrHocsinh.length; i++) {

            if (i == 6) {
                buttons[i] = new JButton(arrHocsinh[i]);
                buttons[i].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                       // chooseImage();
                    }
                });
                buttons[i].setBounds(toadoXbutton, toadoYbutton, 120, 30);
                buttons[i].setForeground(Color.RED);
                buttons[i].setHorizontalAlignment(JButton.CENTER);
                buttons[i].setName("btn" + i);
                Phocsinh.add(buttons[i]);
            } else {
                buttons[i] = new JButton(arrHocsinh[i]);
                buttons[i].setBounds(toadoXbutton, toadoYbutton, 120, 30);
                buttons[i].setHorizontalAlignment(JButton.CENTER);
                buttons[i].setName("btn" + i);
            }

            //bootom 35
            toadoYbutton = toadoYbutton +35;
            Phocsinh.add(buttons[i]);
            if (i == 2) {
                tf[i] = new JTextField();
                tf[i].setBounds(toadoXTextfield, toadoYTextfield, 320, 30);
                tf[i].setFont(new Font("Arial", Font.BOLD, 12));
                tf[i].setBorder(border);
                tf[i].setName("text" + i);
                toadoYTextfield = toadoYTextfield + 35;
                Phocsinh.add(tf[i]);
               
            } 
            
            else {
                tf[i] = new JTextField();
                tf[i].setBounds(toadoXTextfield, toadoYTextfield, 320, 30);
                tf[i].setFont(new Font("Arial", Font.BOLD, 12));
                tf[i].setBorder(border);
                tf[i].setName("text" + i);
                toadoYTextfield = toadoYTextfield + 35;
                Phocsinh.add(tf[i]);
            }
            y = y + 22;
        }
        x = x + 180;

        Phocsinh.setPreferredSize(new Dimension(x, y));

        return Phocsinh;
    }

    public JScrollPane initTable() throws SQLException {

        t = new JTable();
        t.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        scrollpane = new JScrollPane(t);
        scrollpane.setPreferredSize(new Dimension(846, 500));
            
        String[] header = { "ID người gửi", "tiêu đề TB", "Nội dung TB", "thời gian TB" };

        if (tbBUS.getList() == null) {
            tbBUS.list();
        }
        hs = tbBUS.getList();

        System.out.println("Danh sách thông báo: " + hs.size());

        filteredList = new ArrayList<ThongBaoDTO>();
        
        // plBus = new PhanLopBUS();
        for (ThongBaoDTO thongbao : hs) {
            System.out.println(thongbao.getLoaitb()); // In ra loaitb để kiểm tra
            if (thongbao.getLoaitb().equals(username)) {
                filteredList.add(thongbao);
            } 
            System.out.println(thongbao.getLoaitb());
            if(thongbao.getLoaitb().equals("HS")){
                filteredList.add(thongbao);
            }
            loaitb = thongbao.getLoaitb();
            if (plBus.KtraTB(loaitb, username) == true) {
                filteredList.add(thongbao);
            }
        }
        
        
        
        // Chuyển filteredList thành mảng hai chiều rowData
        Object[][] rowData = new Object[filteredList.size()][4];
        for (int i = 0; i < filteredList.size(); i++) {
            ThongBaoDTO thongbao = filteredList.get(i);
            rowData[i][0] = thongbao.getIdnguoigui();
            rowData[i][1] = thongbao.getTieudetb();
            rowData[i][2] = thongbao.getNoidungtb();
            rowData[i][3] = thongbao.getThoigiantb();
        }
        
     

        Font font = new Font("Arial", Font.BOLD, 12);
        Color title_color = new Color(31, 28, 77);
        t.getTableHeader().setBackground(title_color);
        t.getTableHeader().setForeground(Color.WHITE);
        t.getTableHeader().setFont(font);
        Color select = new Color(102, 178, 255);
        t.setSelectionBackground(select);

       

        tblmodel = new DefaultTableModel(rowData, header);
        t.setModel(tblmodel);
        t.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    tableMouseClicked(evt);
                } catch (ParseException ex) {
                    Logger.getLogger(QuanLiHocSinh.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        return scrollpane;
    }

    private void tableMouseClicked(java.awt.event.MouseEvent evt) throws ParseException {
        int row = t.getSelectedRow();
        
        idnguoigui= (String) t.getValueAt(row, 0);
        tieudetb = (String.valueOf(t.getValueAt(row, 1)));
        noidungtb = (String.valueOf(t.getValueAt(row, 2)));
        thoigiantb = (String.valueOf(t.getValueAt(row, 3)));
      

        tf[0].setText(idnguoigui);
        tf[1].setText(tieudetb);
        tf[2].setText(noidungtb);
        tf[3].setText(thoigiantb);
       
        // SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        // Date date = sdf.parse(thoigiantb);
        // dateChooser.setDate(date);

    }


    @Override
    public void mouseClicked(MouseEvent e) {
        
   
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {
      
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
    public static void main(String[] args) throws SQLException {
       JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(850, 670);
        tb_hs panel = new tb_hs(850, 670, "HSK242");
        frame.add(panel);
        frame.setVisible(true);
    }
}
