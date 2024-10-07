
package GUI;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;
import com.toedter.calendar.JDateChooser;

import BUS.ChangeAcc_BUS;
//------------------
import BUS.ThongBaoBUS;
import DAO.ThongBaoDAO;
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
public  class NhanTB extends JPanel implements MouseListener, ActionListener {
    private String idnguoigui, tieudetb, noidungtb, thoigiantb, loaitb;
    private JButton btnThem, btnXoa, btnSua, btnFind, btnReset, btnExpExcel;
    private DefaultTableModel tblmodel;
    private String username;
    private JScrollPane scrollpane;
    JTextField[] tf;
    JButton[] buttons;
    JTable t;
    int width, height;
    private JComboBox<String> searchselectBox;
    private final Border raisedBevel = BorderFactory.createRaisedBevelBorder();
    Border border = BorderFactory.createLineBorder(Color.BLACK, 2);

    DefaultTableModel model;
    TableRowSorter<DefaultTableModel> sorter;
    JDateChooser dateChooser;
    JComboBox<String> genderComboBox;
   
    ThongBaoBUS tbBUS=new ThongBaoBUS();
    ArrayList<ThongBaoDTO> dstb = new ArrayList<>();
    private static String pathAnhdd = "";

    public NhanTB(int width, int height, String username) throws SQLException {
        this.width = width;
        this.height = height;
        this.username = username;
        init();
    }

    public void init() throws SQLException {

        Color myColor = new Color(99, 116, 198);
        Color searchPanel = new Color(180, 204, 227);
        this.setLayout(new BorderLayout());
        JPanel p3 = new JPanel();
        
        p3.setPreferredSize(new Dimension(0, 60));
        p3.setBackground(searchPanel);
        JLabel label = new JLabel("THÔNG BÁO");
        label.setFont(new Font("Arial", Font.BOLD, 30));
        p3.add(label);
        JPanel p1 = JPanelTB();
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
    public JPanel JPanelTB() {
        JPanel Phocsinh = new JPanel();
        Phocsinh.setLayout(null);
        String[] arr = { "ID người gửi ", "Tiêu đề","Nội dung", "Thời gian gửi"};
        int length = arr.length;
        tf = new JTextField[length];
        buttons = new JButton[length];
        Phocsinh.setLayout(null);
        int toadoXbutton = 190;
        int toadoYbutton = 10;
        int toadoXTextfield = 330;
        int toadoYTextfield = 10;
        int x = 230;
        int y = 15;
        for (int i = 0; i < arr.length; i++) {

            if (i == 6) {
                buttons[i] = new JButton(arr[i]);
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
                buttons[i] = new JButton(arr[i]);
                buttons[i].setBounds(toadoXbutton, toadoYbutton, 120, 30);
                buttons[i].setHorizontalAlignment(JButton.CENTER);
                buttons[i].setName("btn" + i);
            }

            //bootom 35
            toadoYbutton = toadoYbutton +35;
            Phocsinh.add(buttons[i]);
            if (i == 3) {
                tf[i] = new JTextField();
                tf[i].setBounds(toadoXTextfield, toadoYTextfield, 320, 30);
                tf[i].setFont(new Font("Arial", Font.BOLD, 12));
                tf[i].setBorder(border);
                tf[i].setName("text" + i);
                tf[i].setEditable(false);
                toadoYTextfield = toadoYTextfield + 35;
                Phocsinh.add(tf[i]);
            } 
           
            else {
                tf[i] = new JTextField();
                tf[i].setBounds(toadoXTextfield, toadoYTextfield, 320, 30);
                tf[i].setFont(new Font("Arial", Font.BOLD, 12));
                tf[i].setBorder(border);
                tf[i].setName("text" + i);
                tf[i].setEditable(false);
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
        
        // Set custom table model
        tblmodel = new DefaultTableModel();
        String[] headers = { "ID người gửi", "Tiêu đề", "Nội dung thông báo", "Thời gian được gửi" };
        for (String header : headers) {
            tblmodel.addColumn(header);
        }
        t.setModel(tblmodel);
        
        // Populate table with data
        dstb = tbBUS.search(username);
        for (ThongBaoDTO tb : dstb) {
            String[] rowData = new String[] {
                tb.getIdnguoigui(),
                tb.getTieudetb(),
                tb.getNoidungtb(),
                tb.getThoigiantb()
            };
            tblmodel.addRow(rowData);
    }
    
    Font font = new Font("Arial", Font.BOLD, 12);
    t.getTableHeader().setPreferredSize(new Dimension(100, 40));
    Color title_color = new Color(31, 28, 77);
    t.getTableHeader().setBackground(title_color);
    t.getTableHeader().setForeground(Color.WHITE);
    t.getTableHeader().setFont(font);
    Color select = new Color(102, 178, 255);
    t.setSelectionBackground(select);

    int[] columnWidths = { 60, 190, 400, 100 };  // Define desired widths for each column
    for (int i = 0; i < columnWidths.length; i++) {
        t.getColumnModel().getColumn(i).setPreferredWidth(columnWidths[i]);
    }
    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    centerRenderer.setHorizontalAlignment(JLabel.CENTER);
    for (int i = 0; i < t.getColumnCount(); i++) {
        t.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
    }
    // Add mouse listener for row click events
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
    }

    @Override
    public void mouseEntered(MouseEvent e) {

        if (e.getSource() == btnThem) {
            btnThem.setBackground(Color.red);
        }
        if (e.getSource() == btnXoa) {
            btnXoa.setBackground(Color.red);
        }
        if (e.getSource() == btnSua) {
            btnSua.setBackground(Color.red);
        }
        if (e.getSource() == btnFind) {
            btnFind.setBackground(Color.red);
        }
        if (e.getSource() == btnExpExcel) {
            btnExpExcel.setBackground(Color.green);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                JFrame frame = new JFrame("Thông Báo - GV");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(850, 670);
                NhanTB panel = new NhanTB(850, 670, "HSK242");
                frame.add(panel);
                frame.setVisible(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseClicked'");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mousePressed'");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseReleased'");
    }
}
