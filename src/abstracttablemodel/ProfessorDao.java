package abstracttablemodel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import defaulttablemodel.FactoryConnection;

public class ProfessorDao {

	private final String INSERT = "INSERT INTO PROFESSOR (NAME, DEPARTMENT, PHONE, EMAIL) VALUES (?,?,?,?)";
	private final String UPDATE = "UPDATE PROFESSOR SET NAME=?,  DEPARTMENT=?, PHONE=?, EMAIL=? WHERE ID=?";
	private final String DELETE = "DELETE FROM PROFESSOR WHERE ID =?";
	private final String LIST = "SELECT * FROM PROFESSOR";
	private final String LISTBYID = "SELECT * FROM PROFESSOR WHERE ID=?";
        private final String LISTBYNAMEPHONE = "SELECT * FROM PROFESSOR WHERE NAME=? AND PHONE=?";

	public void insert(Professor professor) {
		if (professor != null) {
			Connection conn = null;
			try {
				conn = FactoryConnection.getConexao();
				PreparedStatement pstm;
				pstm = conn.prepareStatement(INSERT);

				pstm.setString(1, professor.getName());
				pstm.setString(2, professor.getDepartment());
				pstm.setString(3, professor.getPhone());
				pstm.setString(4, professor.getEmail());

				pstm.execute();
				JOptionPane.showMessageDialog(null, "Professor registered successfully");
				FactoryConnection.closeConnection(conn, pstm);

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Error! Professor not registered in the database" + e.getMessage());
			}
		} else {
			System.out.println("Professor send by prameter is empty");
		}
	}

	public void update(Professor professor) {
		if (professor != null) {
			Connection conn = null;
			try {
				conn = FactoryConnection.getConexao();
				PreparedStatement pstm;
				pstm = conn.prepareStatement(UPDATE);

				pstm.setString(1, professor.getName());
				pstm.setString(2, professor.getDepartment());
				pstm.setString(3, professor.getPhone());
				pstm.setString(4, professor.getEmail());

				pstm.execute();
				JOptionPane.showMessageDialog(null, "Professor changed successfully");
				FactoryConnection.closeConnection(conn);

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Error! Professor has not been updated in the database" + e.getMessage());
			}
		} else {
			JOptionPane.showMessageDialog(null, "Professor send by prameter is empty");
		}
	}

	public void remove(int id) {
		Connection conn = null;
		try {
			conn = FactoryConnection.getConexao();
			PreparedStatement pstm;
			pstm = conn.prepareStatement(DELETE);

			pstm.setInt(1, id);

			pstm.execute();
			FactoryConnection.closeConnection(conn, pstm);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error! Professor has not been deleted from the database" + e.getMessage());
		}
	}

	public List<Professor> getProfessors() {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		ArrayList<Professor> professors = new ArrayList<Professor>();
		try {
			conn = FactoryConnection.getConexao();
			pstm = conn.prepareStatement(LIST);
			rs = pstm.executeQuery();
			while (rs.next()) {
				Professor professor = new Professor();

				professor.setId(rs.getInt("id"));
				professor.setName(rs.getString("name"));
				professor.setDepartment(rs.getString("department"));
				professor.setPhone(rs.getString("phone"));
				professor.setEmail(rs.getString("email"));
				professors.add(professor);
			}
			FactoryConnection.closeConnection(conn, pstm, rs);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error! Professors can't be listed" + e.getMessage());
		}
		return professors;
	}

	public Professor getProfessorById(int id) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Professor professor = new Professor();
		try {
			conn = FactoryConnection.getConexao();
			pstm = conn.prepareStatement(LISTBYID);
			pstm.setInt(1, id);
			rs = pstm.executeQuery();
			while (rs.next()) {
				professor.setId(rs.getInt("id"));
				professor.setName(rs.getString("name"));
				professor.setDepartment(rs.getString("department"));
				professor.setPhone(rs.getString("phone"));
				professor.setEmail(rs.getString("email"));
			}
			FactoryConnection.closeConnection(conn, pstm, rs);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error! Professors can't be listed" + e.getMessage());
		}
		return professor;
	}
        
        public Professor getProfessorByNameTel(Professor p) {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Professor professor = new Professor();
        try {
            conn = FactoryConnection.getConexao();
            pstm = conn.prepareStatement(LISTBYNAMEPHONE);
            pstm.setString(1, p.getName());
            pstm.setString(2, p.getPhone());
            rs = pstm.executeQuery();
            while (rs.next()) {
                professor.setId(rs.getInt("id"));
				professor.setName(rs.getString("name"));
				professor.setDepartment(rs.getString("department"));
				professor.setPhone(rs.getString("phone"));
				professor.setEmail(rs.getString("email"));
               
            }
            FactoryConnection.closeConnection(conn, pstm, rs);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error! Professors can't be listed" + e.getMessage());
        }
        return professor;
    }

}