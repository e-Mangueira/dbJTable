package defaulttablemodel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import abstracttablemodel.Professor;

public class UpdateProfessor extends JFrame {

	private static final long serialVersionUID = 1L;
	private DefaultTableModel model = new DefaultTableModel();
    private JPanel backgroundPanel;
    private JButton btSave;
    private JButton btClear;
    private JLabel lbName;
    private JLabel lbDepartment;
    private JLabel lbPhone;
    private JLabel lbEmail;
    private JLabel lbId;
    private JTextField txName;
    private JTextField txDepartment;
    private JTextField txId;
    private JTextField txPhone;
    private JTextField txEmail;
    defaulttablemodel.Professor professor;
    private int selectedLine;

    public UpdateProfessor(DefaultTableModel md, int id, int line) {
        super("Professors");
        createWindow();
        model = md;
        ProfessorDao dao = new ProfessorDao();
        professor = dao.getProfessorById(id);
        txId.setText(Integer.toString(professor.getId()));
        txName.setText(professor.getName());
        txDepartment.setText(professor.getDepartment());
        txPhone.setText(professor.getPhone());
        txEmail.setText(professor.getEmail());
        selectedLine = line;  
    }

    public void createWindow() {
        btSave = new JButton("Save");
        btClear = new JButton("Clear");
        lbName = new JLabel("  Name  ");
        lbDepartment = new JLabel("  Department  ");
        lbPhone = new JLabel("  Phone  ");
        lbEmail = new JLabel("  Email  ");
        lbId = new JLabel("  Id  ");
        txName = new JTextField();
        txDepartment = new JTextField();
        txPhone = new JTextField();
        txEmail = new JTextField();
        txId = new JTextField();
        txId.setEditable(false);

        backgroundPanel = new JPanel();
        backgroundPanel.setLayout(new GridLayout(5, 2, 2, 4));
        backgroundPanel.add(lbId);
        backgroundPanel.add(txId);
        backgroundPanel.add(lbName);
        backgroundPanel.add(txName);
        backgroundPanel.add(lbDepartment);
        backgroundPanel.add(txDepartment);
        backgroundPanel.add(lbPhone);
        backgroundPanel.add(txPhone);
        backgroundPanel.add(lbEmail);
        backgroundPanel.add(txEmail);
        backgroundPanel.add(btClear);
        backgroundPanel.add(btSave);

        getContentPane().add(backgroundPanel);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(300, 150);
        setVisible(true);

        btSave.addActionListener(new UpdateProfessor.BtSaveListener());
        btClear.addActionListener(new UpdateProfessor.BtClearListener());
    }

    private class BtSaveListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
        	Professor p = new Professor();
    		p.setId(Integer.parseInt(txId.getText()));
    		p.setName(txName.getText());
    		p.setDepartment(txDepartment.getText());
    		p.setPhone(txPhone.getText());
    		p.setEmail(txEmail.getText());

            ProfessorDao dao = new ProfessorDao();
            dao.update(p);
            
            model.removeRow(selectedLine);
            model.addRow(new Object[]{p.getId(), p.getName(), p.getDepartment(), p.getPhone(), p.getEmail()});
            setVisible(false);
        }
    }

    private class BtClearListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            txName.setText("");
            txDepartment.setText("");
            txPhone.setText("");
            txEmail.setText("");
        }
    }
}

