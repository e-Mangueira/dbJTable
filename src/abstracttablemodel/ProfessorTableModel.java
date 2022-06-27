package abstracttablemodel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class ProfessorTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private static final int COL_ID = 0;
    private static final int COL_NAME = 1;
    private static final int COL_DEPARTMENT = 2;
    private static final int COL_PHONE = 3;
    private static final int COL_EMAIL = 4;
    List<Professor> lines;
    private String[] columns = new String[]{"Id", "Name", "Department", "Phone", "Email"};

    public ProfessorTableModel(List<Professor> professors) {
        this.lines = new ArrayList<>(professors);
    }

    public int getRowCount() {
        return lines.size();
    }

    public int getColumnCount() {
        return columns.length;
    }

    public String getColumnName(int columnIndex) {
        return columns[columnIndex];
    }

    public Class getColumnClass(int columnIndex) {
        if (columnIndex == COL_ID) {
            return Integer.class;
        }
        return String.class;
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public Object getValueAt(int row, int column) {

        Professor m = lines.get(row);

        if (column == COL_ID) {
            return m.getId();
        } else if (column == COL_NAME) {
            return m.getName();
        } else if (column == COL_DEPARTMENT) {
            return m.getDepartment();
        } else if (column == COL_PHONE) {
            return m.getPhone();
        } else if (column == COL_EMAIL) {
            return m.getEmail();
        }
        return "";
    }

    public void setValueAt(Object aValue, int row, int column) {
        Professor u = lines.get(row);
        if (column == COL_ID) {
            u.setId((Integer) aValue);
        } else if (column == COL_NAME) {
            u.setName(aValue.toString());
        } else if (column == COL_DEPARTMENT) {
            u.setDepartment(aValue.toString());
        } else if (column == COL_PHONE) {
            u.setPhone(aValue.toString());
        } else if (column == COL_EMAIL) {
            u.setEmail(aValue.toString());
        }
    }

    public Professor getProfessor(int indiceLinha) {
        return lines.get(indiceLinha);
    }

    public void addProfessor(Professor professor) {
        lines.add(professor);
        int ultimoIndice = getRowCount() - 1;
        fireTableRowsInserted(ultimoIndice, ultimoIndice);

    }

    public void updateProfessor(int indiceLinha, Professor marca) {
        lines.set(indiceLinha, marca);
        fireTableRowsUpdated(indiceLinha, indiceLinha);

    }

    public void removeProfessor(int indiceLinha) {
        lines.remove(indiceLinha);
        fireTableRowsDeleted(indiceLinha, indiceLinha);

    }
}
