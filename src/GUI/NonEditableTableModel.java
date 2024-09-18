package GUI;

import javax.swing.table.DefaultTableModel;

public class NonEditableTableModel extends DefaultTableModel {
    private int editableColumnIndex;

    public NonEditableTableModel(Object[] columnNames, int rowCount, int editableColumnIndex) {
        super(columnNames, rowCount);
        this.editableColumnIndex = editableColumnIndex;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return column == editableColumnIndex;
    }
}
