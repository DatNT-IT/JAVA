package Connects.tacGia;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

public class Cach2 extends AbstractTableModel {

    public final static boolean GENDER_MALE = true;

    public final static boolean GENDER_FEMALE = false;
    public final static String[] ls = {

            "mã", "tên"

    };

    public Object[][] values1 = {

            {

                    "Clay", "Ashworth",


            }
    };

    @Override
    public int getRowCount() {
        return 0;
    }

    @Override
    public int getColumnCount() {
        return 0;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if (aValue.toString().length() < 3) {

            JOptionPane.showMessageDialog(null, "không đc quá 3 kí tự cột 2");

            return;

        }

        values1[rowIndex][columnIndex] = aValue;

        fireTableCellUpdated(rowIndex, columnIndex);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 1;//chỉ đc sửa cột 1
    }
}
