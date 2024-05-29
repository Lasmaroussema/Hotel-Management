package interfaces;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;


import javax.swing.table.AbstractTableModel;



import java.sql.ResultSet;

public class MyTableModel extends AbstractTableModel {

    ArrayList<Object[]> data = new ArrayList<>();
    ResultSetMetaData rsmd;
    

    MyTableModel(ResultSet rs) {
        
        try {
            rsmd = rs.getMetaData();

            while (rs.next()) {
                Object[] ligne = new Object[rsmd.getColumnCount()];
                for (int i = 0; i < ligne.length; i++) {
                    ligne[i] = rs.getObject(i + 1);
                }
                data.add(ligne);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getColumnCount() {

        try {
            return rsmd.getColumnCount();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int getRowCount() {

        return data.size();
    }

    // public Object getID(int rowIndex) {

    //     return data.get(rowIndex)[2];
    // }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        return data.get(rowIndex)[columnIndex];
    }

    @Override
    public String getColumnName(int column) {
        try {
            return rsmd.getColumnName(column + 1);
        } catch (SQLException e) {

            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (getColumnName(columnIndex).equalsIgnoreCase("Cin")) {
            return false;
        } else {
            return false;
        }
    }

    public int ColumnNameToIndex(String ColumnName) {
        for (int i = 0; i < getColumnCount(); i++) {
            if (getColumnName(i).equals(ColumnName)) {
                return i;
            }
        }
        return -1;
    }

}