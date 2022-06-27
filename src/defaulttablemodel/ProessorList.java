package defaulttablemodel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ProessorList extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel backgroundPanel;
    private JPanel buttonsPanel;
    private JTable table;
    private JScrollPane scrollbar;
    private JButton btInsert;
    private JButton btDelete;
    private JButton btEdit;
    private DefaultTableModel model = new DefaultTableModel();

    public ProessorList() {
        super("Professors");
        createJTable();
        createWindow();
    }

    public void createWindow() {
        btInsert = new JButton("Insert");
        btDelete = new JButton("Delete");
        btEdit = new JButton("Edit");
        buttonsPanel = new JPanel();
        scrollbar = new JScrollPane(table);
        backgroundPanel = new JPanel();
        backgroundPanel.setLayout(new BorderLayout());
        backgroundPanel.add(BorderLayout.CENTER, scrollbar);
        buttonsPanel.add(btInsert);
        buttonsPanel.add(btEdit);
        buttonsPanel.add(btDelete);
        backgroundPanel.add(BorderLayout.SOUTH, buttonsPanel);

        getContentPane().add(backgroundPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 320);
        setVisible(true);
        btInsert.addActionListener(new BtInsertListener());
        btEdit.addActionListener(new BtEditListener());
        btDelete.addActionListener(new BtDeleteListener());
    }

    private void createJTable() {
        table = new JTable(model);
        model.addColumn("Id");
        model.addColumn("Name");
        model.addColumn("Department");
        model.addColumn("Pnone");
        model.addColumn("Email");
        table.getColumnModel().getColumn(0).setPreferredWidth(10);
        table.getColumnModel().getColumn(1).setPreferredWidth(120);
        table.getColumnModel().getColumn(1).setPreferredWidth(80);
        table.getColumnModel().getColumn(1).setPreferredWidth(120);
        search(model);
    }

    public static void search(DefaultTableModel model) {
        model.setNumRows(0);
        ProfessorDao dao = new ProfessorDao();

        for (Professor p : dao.getProfessors()) {
            model.addRow(new Object[]{p.getId(), p.getName(), p.getDepartment(), p.getPhone(), p.getEmail()});
        }
    }

    private class BtInsertListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            InsertProfessor ip = new InsertProfessor(model);
            ip.setVisible(true);
        }
    }

    private class BtEditListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            int selectedLine = -1;
            selectedLine = table.getSelectedRow();
            if (selectedLine >= 0) {
                int idProfessor = (int) table.getValueAt(selectedLine, 0);
                UpdateProfessor ic = new UpdateProfessor(model, idProfessor, selectedLine);
                ic.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "You need to select a line");
            }
        }
    }

    private class BtDeleteListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            int selectedLine = -1;
            selectedLine = table.getSelectedRow();
            if (selectedLine >= 0) {
                int idProfessor = (int) table.getValueAt(selectedLine, 0);
                ProfessorDao dao = new ProfessorDao();
                dao.remove(idProfessor);
                model.removeRow(selectedLine);
            } else {
                JOptionPane.showMessageDialog(null, "You need to select a line");
            }
        }
    }
    
    public static void main(String[] args) {
        ProessorList lc = new ProessorList();
        lc.setVisible(true);
    }
}

