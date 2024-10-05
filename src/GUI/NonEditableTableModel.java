package GUI;

import javax.swing.table.DefaultTableModel;

public class NonEditableTableModel extends DefaultTableModel {

    public NonEditableTableModel(Object[] columnNames, int rowCount, int editableColumnIndex) {
        super(columnNames, rowCount);

    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
