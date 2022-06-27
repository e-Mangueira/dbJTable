package abstracttablemodel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ProfessorList extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel backgroundPanel;
    private JPanel buttonsPanel;
    private JTable table;
    private JScrollPane scrollbar;
    private JButton btInsert;
    private JButton btDelete;
    private JButton btEdit;
    private ProfessorTableModel model;
    List<Professor> list;

    public ProfessorList() {
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
        search();

    }

    private void search() {
        ProfessorDao dao = new ProfessorDao();
        list = dao.getProfessors();
        model = new ProfessorTableModel(list);
        table.setModel(model);
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
                UpdateProfessor ip = new UpdateProfessor(model, idProfessor, selectedLine);
                ip.setVisible(true);
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

                model.removeProfessor(selectedLine);
            } else {
                JOptionPane.showMessageDialog(null, "You need to select a line");
            }
        }
    }

    public static void main(String[] args) {
        ProfessorList lc = new ProfessorList();
        lc.setVisible(true);
    }
}
