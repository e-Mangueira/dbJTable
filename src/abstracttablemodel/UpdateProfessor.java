package abstracttablemodel;


import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UpdateProfessor extends JFrame {

	private static final long serialVersionUID = 1L;
	private ProfessorTableModel model;
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
    Professor professor;
    private int lineSelecionada;

    public UpdateProfessor(ProfessorTableModel md, int id, int line) {
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
        lineSelecionada = line;  
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

        btSave.addActionListener(new UpdateProfessor.BtSalvarListener());
        btClear.addActionListener(new UpdateProfessor.BtLimparListener());
    }

    private class BtSalvarListener implements ActionListener {

	public void actionPerformed(ActionEvent e) {
		Professor p = new Professor();
		p.setId(Integer.parseInt(txId.getText()));
		p.setName(txName.getText());
		p.setDepartment(txDepartment.getText());
		p.setPhone(txPhone.getText());
		p.setEmail(txEmail.getText());

		ProfessorDao dao = new ProfessorDao();
		dao.update(p);

		model.updateProfessor(lineSelecionada, p);
		setVisible(false);
	}
}


    private class BtLimparListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            txName.setText("");
            txDepartment.setText("");
            txPhone.setText("");
            txEmail.setText("");
        }
    }
}

