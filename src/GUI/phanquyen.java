package GUI;

import DTO.chitietquyenDTO;
import DTO.chucnangDTO;
import DTO.phanquyenDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.event.*;
import java.awt.print.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.TableRowSorter;
import java.util.List;
import java.util.ArrayList;
import java.awt.Font;
import java.sql.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
// import org.apache.poi.ss.usermodel.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import BUS.phanquyenBUS;

public class phanquyen extends JPanel implements ActionListener {

    private Integer width, height;
    private JTable t;
    private JScrollPane scrollpane;
    private JPanel psearch;
    private DefaultTableModel model;
    private ArrayList<phanquyenDTO> dsquyen;
    private ArrayList<chucnangDTO> dschucnang;
    private ArrayList<chitietquyenDTO> dschitietquyen;
    private phanquyenBUS pqBUS;
    private JButton btnAdd, btnEdit, btnDelete;

    public phanquyen(Integer width, Integer height) {
        this.width = width;
        this.height = height;
        init();
        btnAdd.addActionListener(this);
        btnEdit.addActionListener(this);
        btnDelete.addActionListener(this);
    }

    public void init() {
        this.setSize(new Dimension(width, height));
        this.setLayout(new BorderLayout());

        JPanel p1 = JSearch();
        p1.setPreferredSize(new Dimension(0, 110));
        p1.setBackground(new Color(99,116,198)); // Light blue background

        JPanel p3 = new JPanel();
        p3.setPreferredSize(new Dimension(0, 400));
        p3.add(init_table());
        p3.setBackground(new Color(	180, 204, 227)); // Azure background

        this.add(p1, BorderLayout.NORTH);
        this.add(p3, BorderLayout.CENTER);
    }

    public JPanel JSearch() {
        psearch = new JPanel();
        psearch.setLayout(new GridBagLayout());
        psearch.setPreferredSize(new Dimension(850, 100));

        // Create the label
        JLabel label = new JLabel("Chức năng phân quyền");
        label.setFont(new Font("Arial", Font.BOLD, 20));

        // Create the buttons
        btnAdd = new JButton("Thêm");
        btnAdd.setBackground(new Color(126, 217, 87));

        btnEdit = new JButton("Sửa");
        btnEdit.setBackground(new Color(200, 175, 73));

        btnDelete = new JButton("Xóa");
        btnDelete.setBackground(new Color(255, 49, 49)); // Corrected from btnAdd to btnDelete

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Add some padding

        // Add the label at the top and center
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3; // Span across 3 columns
        gbc.anchor = GridBagConstraints.CENTER;
        psearch.add(label, gbc);

        // Add the "Thêm" button
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        psearch.add(btnAdd, gbc);

        // Add the "Sửa" button
        gbc.gridx = 1;
        gbc.gridy = 1;
        psearch.add(btnEdit, gbc);

        // Add the "Xóa" button
        gbc.gridx = 2;
        gbc.gridy = 1;
        psearch.add(btnDelete, gbc);

        return psearch;
    }

    public JScrollPane init_table() {
        String[] header = { "Mã Quyền", "Tên Mã Quyền" };
        pqBUS = new phanquyenBUS();
        pqBUS.listquyen();
        dsquyen = pqBUS.getlistquyen();
        System.out.println(dsquyen.size());
        Object[][] rowData = new Object[dsquyen.size()][header.length];
        for (int i = 0; i < dsquyen.size(); i++) {
            phanquyenDTO quyen = dsquyen.get(i);
            rowData[i][0] = quyen.getMaquyen();
            rowData[i][1] = quyen.getTenquyen();
        }

        // model = new DefaultTableModel(rowData, header);
        model = new DefaultTableModel(rowData, header) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Disables editing
            }
        };
        t = new JTable(model);
        t.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        t.getTableHeader().setBackground(new Color(70, 130, 180)); // Steel blue header
        t.getTableHeader().setForeground(Color.WHITE);
        t.setSelectionBackground(new Color(100, 149, 237)); // Cornflower blue selection
        t.setGridColor(new Color(173, 216, 230)); // Light blue grid lines
        t.setRowHeight(50);
        Font headerFont = t.getTableHeader().getFont();
        t.getTableHeader().setFont(new Font(headerFont.getName(), Font.BOLD, 16));
        scrollpane = new JScrollPane(t);
        scrollpane.setPreferredSize(new Dimension(846, 400));
        
        t.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    tableMouseClicked(evt);
                } catch (ParseException ex) {
                    Logger.getLogger(phanquyen.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });

        return scrollpane;
    }

    private void tableMouseClicked(java.awt.event.MouseEvent evt) throws ParseException {
        int row = t.getSelectedRow();
    }

    public void deleteFields() {
        // Clear fields logic if needed
    }

    public static void main(String[] args) throws SQLException {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(850, 670);
        phanquyen panel = new phanquyen(850, 670);
        frame.add(panel);
        frame.setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAdd) {
            showAddDialog();
        } else if (e.getSource() == btnEdit) {
            int selectedRow = t.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một dòng để sửa.", "Cảnh báo",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                String maquyen = (String) t.getValueAt(selectedRow, 0);
                if ("admin".equals(maquyen)) {
                    JOptionPane.showMessageDialog(this, 
                        "Admin không được phép chỉnh sửa!", 
                        "Thông báo", 
                        JOptionPane.WARNING_MESSAGE);
                } else {
                    showEditDialog();
                }
            }
        } else if (e.getSource() == btnDelete) {
            int selectedRow = t.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một dòng để xóa.", "Cảnh báo",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                String maquyen = (String) t.getValueAt(selectedRow, 0);
                if ("admin".equals(maquyen)) {
                    JOptionPane.showMessageDialog(this, 
                        "Admin không được phép xóa!", 
                        "Thông báo", 
                        JOptionPane.WARNING_MESSAGE);
                } else {
                    int result = JOptionPane.showConfirmDialog(this, 
                            "Bạn có chắc xóa quyền này không?", 
                            "Xác nhận", 
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE);
                    if (result == JOptionPane.YES_OPTION) {
                        model.removeRow(selectedRow);
                        pqBUS.deleteQuyen(maquyen);
                        pqBUS.deleteChitietquyen(maquyen);
                        JOptionPane.showMessageDialog(this, "Xóa thành công");
                    }
                }
            }
        }
    }
    

    private void showAddDialog() {
        // Lấy kích thước màn hình
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int dialogWidth = (int) (screenSize.width * 0.5);
        int dialogHeight = (int) (screenSize.height * 0.5);

        JDialog addDialog = new JDialog();
        addDialog.setSize(dialogWidth, dialogHeight);
        addDialog.setLayout(new BorderLayout());
        addDialog.setModal(true);

        JPanel inputPanel = new JPanel(new FlowLayout(1, 10, 10));
        JLabel lblMaQuyen = new JLabel("Mã Quyền:");
        JTextField txtMaQuyen = new JTextField();
        txtMaQuyen.setPreferredSize(new Dimension(150, 25));
        JLabel lblTenQuyen = new JLabel("Tên Quyền:");
        JTextField txtTenQuyen = new JTextField();
        txtTenQuyen.setPreferredSize(new Dimension(150, 25));

        inputPanel.add(lblMaQuyen);
        inputPanel.add(txtMaQuyen);
        inputPanel.add(lblTenQuyen);
        inputPanel.add(txtTenQuyen);

        pqBUS.listchucnang();
        dschucnang = pqBUS.getlistchucnang();
        // System.out.println(dschucnang.size());

        int rows = (int) Math.sqrt(dschucnang.size());
        int cols = (int) Math.ceil((double) dschucnang.size() / rows);

        JPanel checkBoxPanel = new JPanel(new GridLayout(rows, cols));

        JCheckBox[] cbx = new JCheckBox[dschucnang.size()];
        for (int i = 0; i < dschucnang.size(); i++) {
            chucnangDTO cn = dschucnang.get(i);
            cbx[i] = new JCheckBox(cn.getTenchucnang());
            cbx[i].setActionCommand(cn.getMachucnang());
            checkBoxPanel.add(cbx[i]);
        }

        JButton btnSave = new JButton("Lưu");
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String maquyen = txtMaQuyen.getText();

              
                if (!maquyen.matches("[a-zA-Z0-9]+")) {
                    JOptionPane.showMessageDialog(null, "Mã quyền không được chứa khoảng trắng hoặc ký tự đặc biệt",
                            "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                } 

                String tenquyen = txtTenQuyen.getText();

                if (!tenquyen.matches("[\\p{L}\\s\\d]+")) {
                    JOptionPane.showMessageDialog(null, "Tên quyền không được chứa ký tự đặc biệt", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                


                dschitietquyen = new ArrayList<>();
                for (int i = 0; i < cbx.length; i++) {
                    if (cbx[i].isSelected()) {
                        chitietquyenDTO ctq = new chitietquyenDTO(maquyen, cbx[i].getActionCommand());
                        System.out.println(ctq.getMachucnang());
                        dschitietquyen.add(ctq);
                    }
                }

                if (!maquyen.isEmpty() && !tenquyen.isEmpty() && !dschitietquyen.isEmpty()) {
                    if (pqBUS.checkExist(maquyen)) {
                        JOptionPane.showMessageDialog(addDialog, "Mã quyền đã tồn tại", "Cảnh báo",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    phanquyenDTO newQuyen = new phanquyenDTO(maquyen, tenquyen);
                    dsquyen.add(newQuyen);
                    pqBUS.addQuyen(newQuyen);
                    for (int i = 0; i < dschitietquyen.size(); i++) {
                        System.out.println(dschitietquyen.size());
                        chitietquyenDTO ctq = dschitietquyen.get(i);
                        pqBUS.addChitietquyen(ctq);

                    }
                    model.addRow(new Object[] { maquyen, tenquyen });
                    addDialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(addDialog,
                            "Vui lòng nhập đủ thông tin và chọn ít nhất một chức năng.", "Lỗi",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        addDialog.add(inputPanel, BorderLayout.NORTH);
        addDialog.add(new JScrollPane(checkBoxPanel), BorderLayout.CENTER);
        addDialog.add(btnSave, BorderLayout.SOUTH);
        addDialog.setLocationRelativeTo(this);

        addDialog.setVisible(true);
    }

    private void showEditDialog() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int dialogWidth = (int) (screenSize.width * 0.5);
        int dialogHeight = (int) (screenSize.height * 0.5);

        JDialog editDialog = new JDialog();
        editDialog.setSize(dialogWidth, dialogHeight);
        editDialog.setLayout(new BorderLayout());
        editDialog.setModal(true);
        editDialog.setBackground(new Color(180,204,227));

        JPanel inputPanel = new JPanel(new FlowLayout(1, 10, 10));
        JLabel lblMaQuyen = new JLabel("Mã Quyền:");
        JTextField txtMaQuyen = new JTextField();
        txtMaQuyen.setPreferredSize(new Dimension(150, 25));
        txtMaQuyen.setEnabled(false);
        JLabel lblTenQuyen = new JLabel("Tên Quyền:");
        JTextField txtTenQuyen = new JTextField();
        txtTenQuyen.setPreferredSize(new Dimension(150, 25));

        inputPanel.add(lblMaQuyen);
        inputPanel.add(txtMaQuyen);
        inputPanel.add(lblTenQuyen);
        inputPanel.add(txtTenQuyen);

        int selectedRow = t.getSelectedRow();
        String maQuyen = (String) model.getValueAt(selectedRow, 0);
        String tenQuyen = (String) model.getValueAt(selectedRow, 1);
        txtMaQuyen.setText(maQuyen);
        txtTenQuyen.setText(tenQuyen);

        pqBUS.listchucnang();
        dschucnang = pqBUS.getlistchucnang();

        int rows = (int) Math.sqrt(dschucnang.size());
        int cols = (int) Math.ceil((double) dschucnang.size() / rows);

        JPanel checkBoxPanel = new JPanel(new GridLayout(rows, cols));

        JCheckBox[] cbx = new JCheckBox[dschucnang.size()];
        for (int i = 0; i < dschucnang.size(); i++) {
            chucnangDTO cn = dschucnang.get(i);
            cbx[i] = new JCheckBox(cn.getTenchucnang());
            cbx[i].setActionCommand(cn.getMachucnang());
            checkBoxPanel.add(cbx[i]);
        }

        pqBUS.listChiTietQuyen(maQuyen);
        dschitietquyen = new ArrayList<>();
        dschitietquyen = pqBUS.getListchitietquyen();
        pqBUS.listchucnang();
        dschucnang = new ArrayList<>();
        dschucnang = pqBUS.getlistchucnang();

        for (int i = 0; i < dschitietquyen.size(); i++) {
            chitietquyenDTO ctq = dschitietquyen.get(i);
            System.out.println(ctq.getMachucnang());
            System.out.println("so sánh");
            System.out.println(cbx[i].getActionCommand());

            for (int j = 0; j < dschucnang.size(); j++) {
                if (cbx[j].getActionCommand().equals(ctq.getMachucnang())) {
                    cbx[j].setSelected(true);
                }
            }
        }

        JButton btnSave = new JButton("Lưu");
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newmaquyen = txtMaQuyen.getText();
                String newtenquyen = txtTenQuyen.getText();
                dschitietquyen = new ArrayList<>();

                for (int i = 0; i < dschucnang.size(); i++) {
                    if (cbx[i].isSelected()) {
                        chitietquyenDTO ctq = new chitietquyenDTO(newmaquyen, cbx[i].getActionCommand());
                        System.out.println(ctq.getMachucnang());
                        dschitietquyen.add(ctq);
                    }
                }
                

                if (!newmaquyen.isEmpty() && !newtenquyen.isEmpty() && !dschitietquyen.isEmpty()) {
                    // if (pqBUS.checkExist(newmaquyen)) {
                    //     JOptionPane.showMessageDialog(editDialog, "Mã quyền đã tồn tại", "Cảnh báo",
                    //             JOptionPane.ERROR_MESSAGE);
                    //     return;
                    // }
                    if (!newmaquyen.matches("[\\p{L}\\s\\d]+")) {
                        JOptionPane.showMessageDialog(null, "Tên quyền không được chứa ký tự đặc biệt", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    
                    pqBUS.deleteQuyen(maQuyen);
                    pqBUS.deleteChitietquyen(maQuyen);

                    phanquyenDTO newQuyen = new phanquyenDTO(newmaquyen, newtenquyen);
                    dsquyen.add(newQuyen);
                    pqBUS.addQuyen(newQuyen);
                    for (int i = 0; i < dschitietquyen.size(); i++) {
                        System.out.println(dschitietquyen.size());
                        chitietquyenDTO ctq = dschitietquyen.get(i);
                        pqBUS.addChitietquyen(ctq);

                    }
                    model.removeRow(selectedRow);
                    model.addRow(new Object[] { newmaquyen, newtenquyen });
                    JOptionPane.showMessageDialog(editDialog, "Sửa quyền thành công");
                    editDialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(editDialog,
                            "Vui lòng nhập đủ thông tin và chọn ít nhất một chức năng.", "Lỗi",
                            JOptionPane.ERROR_MESSAGE);
                }
            }

        });

        editDialog.add(inputPanel, BorderLayout.NORTH);
        editDialog.add(new JScrollPane(checkBoxPanel), BorderLayout.CENTER);
        editDialog.add(btnSave, BorderLayout.SOUTH);
        editDialog.setLocationRelativeTo(this);

        editDialog.setVisible(true);
    }

}
