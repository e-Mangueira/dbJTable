package simpleTable;

import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ProfessorsList extends JFrame {

	private static final long serialVersionUID = 1L;
	JPanel backgroundPanel;
    JTable table;
    JScrollPane scrollbar;
       
    Object [][] data = {
    		{"Sergio", "Computer Engeneering", "98 98122-2333", "sergio@gmail.com"},
            {"Maria", "Civil Engeneering", "98 98111-3202", "maria@gmail.com"},
            {"Davi", "Eletrical Engeneering", "98 98566-6073", "davi@gmail.com"},
            {"Marcos", "Math", "99 98294-8891", "marcos@gmail.com"},
            {"Joana", "Computer Engeneering", "99 98746-4477", "joana@gmail.com"},
            {"Nilton", "Eletrical Engeneering", "98 98355-5203", "nilton@gmail.com"}
    };
    
    String [] columns = {"Name", "Department", "Phone", "Email"}; 
    

    public ProfessorsList() {
        super ("Professors list");
    }
    
    public void createWindow(){
        
        backgroundPanel = new JPanel();
        backgroundPanel.setLayout(new GridLayout(1, 1));
        table = new JTable(data, columns);
        scrollbar = new JScrollPane(table);
        backgroundPanel.add(scrollbar); 
        
        getContentPane().add(backgroundPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 120);
        setVisible(true);
    }
    
    public static void main(String[] args) {
    	ProfessorsList pl = new ProfessorsList();
        pl.createWindow();
    }
}

