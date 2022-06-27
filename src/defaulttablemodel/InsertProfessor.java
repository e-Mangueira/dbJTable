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

public class InsertProfessor extends JFrame {

	private static final long serialVersionUID = 1L;
	private DefaultTableModel model = new DefaultTableModel();
	private JPanel backgroundPanel;
    private JButton btSave;
    private JButton btClear;
    private JLabel lbName;
    private JLabel lbDepartment;
    private JLabel lbPhone;
    private JLabel lbEmail;
    private JTextField txName;
    private JTextField txDepartment;
    private JTextField txPhone;
    private JTextField txEmail;

    public InsertProfessor(DefaultTableModel md) {
        super("Professors");
        createWindow();
        model = md;
    }

    public void createWindow() {
        btSave = new JButton("Save");
        btClear = new JButton("Clear");
        lbName = new JLabel("  Name  ");
        lbDepartment = new JLabel("  Department ");
        lbPhone = new JLabel("  Phone  ");
        lbEmail = new JLabel("  Email  ");
        txName = new JTextField(10);
        txDepartment = new JTextField();
        txPhone = new JTextField();
        txEmail = new JTextField();

        backgroundPanel = new JPanel();
        backgroundPanel.setLayout(new GridLayout(4, 2, 2, 4));
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
        btSave.addActionListener(new BtSaveListener());
        btClear.addActionListener(new BtClearListener());
    }

    private class BtSaveListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
        	Professor p = new Professor();
            p.setName(txName.getText());
            p.setDepartment(txDepartment.getText());
            p.setPhone(txPhone.getText());
            p.setEmail(txEmail.getText());
            
            ProfessorDao dao = new ProfessorDao();
            dao.insert(p);
            ProessorList.search(model);
            
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

