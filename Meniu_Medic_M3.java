import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import java.awt.Button;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

public class Meniu_Medic_M3 extends JPanel {
	private JTable table;
	// Conexiune Baza de date
	private MySQL_Connect conexiuneDB;
	private Connection con;
	Statement selectStatement = null, selectStatement2 = null,stmt = null;
	ResultSet rs = null, rs2 = null , rs3 = null;
	private JTextField idraport;
	private JTextField idAsistent;
	private JTextField nume_p;
	private JTextField prenume_p;
	private JTextField simptome;
	private JTextField recomandari;
	private JTextField parafat;
	private JTextField diagnostic;
	private JTable table_1;

	public Meniu_Medic_M3(String user) {
		setSize(790, 630);
		setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 790, 630);
		add(tabbedPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 191, 255));
		tabbedPane.addTab("Programari", null, panel, null);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(108, 47, 542, 272);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		Button butonP = new Button("Vezi programari");
		butonP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					conexiuneDB = new MySQL_Connect();
					con = conexiuneDB.getConnection();
					selectStatement = con.createStatement();
					selectStatement.execute("SELECT pr.idprogramare,pr.nume_pacient,pr.prenume_pacient,pr.ora,pr.zi,pr.durata FROM programare pr , medic m , utilizator u WHERE pr.id_medic = m.idmedic AND m.idmedic = u.idutilizator AND pr.zi = CURDATE() AND u.nume_utilizator = '"+ user+ "' ORDER BY pr.ora ASC;");
					rs = selectStatement.getResultSet();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					con.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		butonP.setBounds(250, 348, 234, 82);
		panel.add(butonP);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(135, 206, 250));
		tabbedPane.addTab("Rapoarte Medicale", null, panel_1, null);
		panel_1.setLayout(null);
		
		Button butonRapoarte = new Button("Vezi Rapoarte");
		butonRapoarte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					conexiuneDB = new MySQL_Connect();
					con = conexiuneDB.getConnection();
					selectStatement = con.createStatement();
					selectStatement.execute(
							"SELECT r.* FROM raport r , medic m , utilizator u WHERE r.id_medic_consultatie = m.idmedic AND m.idmedic = u.idutilizator AND u.nume_utilizator = '"
									+ user + "';");
					rs = selectStatement.getResultSet();
					table_1.setModel(DbUtils.resultSetToTableModel(rs));
					con.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		butonRapoarte.setBounds(371, 391, 146, 58);
		panel_1.add(butonRapoarte);
		
		Button butonAdauga = new Button("Adauga Raport");
		butonAdauga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					conexiuneDB = new MySQL_Connect();
					con = conexiuneDB.getConnection();
					selectStatement2 = con.createStatement();
					selectStatement2
							.execute("SELECT idutilizator FROM utilizator WHERE nume_utilizator='" + user + "';");
					rs2 = selectStatement2.getResultSet();
					rs2.next();
					int id_medic = rs2.getInt("idutilizator");
					stmt = con.createStatement();
					stmt.execute("SELECT idprogramare FROM programare WHERE nume_pacient ='"+nume_p.getText()+"' AND prenume_pacient='"+prenume_p.getText()+"' AND zi = CURDATE();");
					rs3 = stmt.getResultSet();
					rs3.next();
					int id_p = rs3.getInt("idprogramare");
					selectStatement = con.createStatement();
					String sql = "INSERT INTO raport(nume_pacient,prenume_pacient,id_asistent,id_medic_consultatie,data_consultatiei,simptome,diagnostic,recomandari,idprogramare,parafat) VALUES ('" + nume_p.getText() + "','" + prenume_p.getText() + "','" + Integer.parseInt(idAsistent.getText()) + "','"
							+ id_medic + "',CURDATE(),'" + simptome.getText() + "','"+diagnostic.getText()+"','"+recomandari.getText()+"','"+id_p+"','0')";
					selectStatement.executeUpdate(sql);
					rs = selectStatement.getResultSet();
					JOptionPane.showMessageDialog(butonAdauga, "Raportul a fost adaugat cu succes");
					con.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		butonAdauga.setBounds(559, 391, 139, 58);
		panel_1.add(butonAdauga);
		
		Button butonParafa = new Button("Pune parafa");
		butonParafa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					conexiuneDB = new MySQL_Connect();
					con = conexiuneDB.getConnection();
					selectStatement = con.createStatement();
					selectStatement.execute("UPDATE raport SET parafat=1 WHERE idraport='"+Integer.parseInt(idraport.getText())+"'");
					JOptionPane.showMessageDialog(butonParafa, "Parafa a fost pusa!");
					con.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		butonParafa.setBounds(566, 476, 132, 58);
		panel_1.add(butonParafa);
		
		JLabel lblNewLabel = new JLabel("Id Raport");
		lblNewLabel.setForeground(new Color(255, 250, 250));
		lblNewLabel.setFont(new Font("DialogInput", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 62, 103, 36);
		panel_1.add(lblNewLabel);
		
		JLabel lblIdAsistent = new JLabel("Id Asistent");
		lblIdAsistent.setForeground(new Color(255, 250, 250));
		lblIdAsistent.setFont(new Font("DialogInput", Font.BOLD, 14));
		lblIdAsistent.setBounds(10, 128, 103, 36);
		panel_1.add(lblIdAsistent);
		
		JLabel lblNumePacient = new JLabel("Nume Pacient");
		lblNumePacient.setForeground(new Color(255, 250, 250));
		lblNumePacient.setFont(new Font("DialogInput", Font.BOLD, 14));
		lblNumePacient.setBounds(10, 192, 103, 36);
		panel_1.add(lblNumePacient);
		
		JLabel lblPrenumePacient = new JLabel("Prenume Pacient");
		lblPrenumePacient.setForeground(new Color(255, 250, 250));
		lblPrenumePacient.setFont(new Font("DialogInput", Font.BOLD, 14));
		lblPrenumePacient.setBounds(10, 260, 120, 36);
		panel_1.add(lblPrenumePacient);
		
		JLabel lblSimptome = new JLabel("Simptome");
		lblSimptome.setForeground(new Color(255, 250, 250));
		lblSimptome.setFont(new Font("DialogInput", Font.BOLD, 14));
		lblSimptome.setBounds(10, 332, 103, 36);
		panel_1.add(lblSimptome);
		
		JLabel lblRecomandari = new JLabel("Recomandari");
		lblRecomandari.setForeground(new Color(255, 250, 250));
		lblRecomandari.setFont(new Font("DialogInput", Font.BOLD, 14));
		lblRecomandari.setBounds(10, 457, 103, 36);
		panel_1.add(lblRecomandari);
		
		JLabel lblParafat = new JLabel("Parafat");
		lblParafat.setForeground(new Color(255, 250, 250));
		lblParafat.setFont(new Font("DialogInput", Font.BOLD, 14));
		lblParafat.setBounds(10, 515, 103, 36);
		panel_1.add(lblParafat);
		
		idraport = new JTextField();
		idraport.setColumns(10);
		idraport.setBounds(126, 62, 133, 41);
		panel_1.add(idraport);
		
		idAsistent = new JTextField();
		idAsistent.setColumns(10);
		idAsistent.setBounds(123, 128, 133, 41);
		panel_1.add(idAsistent);
		
		nume_p = new JTextField();
		nume_p.setColumns(10);
		nume_p.setBounds(123, 192, 133, 41);
		panel_1.add(nume_p);
		
		prenume_p = new JTextField();
		prenume_p.setColumns(10);
		prenume_p.setBounds(140, 260, 133, 41);
		panel_1.add(prenume_p);
		
		simptome = new JTextField();
		simptome.setColumns(10);
		simptome.setBounds(126, 327, 133, 41);
		panel_1.add(simptome);
		
		recomandari = new JTextField();
		recomandari.setColumns(10);
		recomandari.setBounds(126, 456, 133, 41);
		panel_1.add(recomandari);
		
		parafat = new JTextField();
		parafat.setEditable(false);
		parafat.setColumns(10);
		parafat.setBounds(126, 514, 133, 41);
		panel_1.add(parafat);
		
		JLabel lblDiagnostic = new JLabel("Diagnostic");
		lblDiagnostic.setForeground(new Color(255, 250, 250));
		lblDiagnostic.setFont(new Font("DialogInput", Font.BOLD, 14));
		lblDiagnostic.setBounds(10, 391, 103, 36);
		panel_1.add(lblDiagnostic);
		
		diagnostic = new JTextField();
		diagnostic.setColumns(10);
		diagnostic.setBounds(126, 391, 133, 41);
		panel_1.add(diagnostic);
		
		Button butonModifica = new Button("Modifica Raport");
		butonModifica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					conexiuneDB = new MySQL_Connect();
					con = conexiuneDB.getConnection();
					stmt = con.createStatement();
					stmt.execute("SELECT parafat FROM raport WHERE idraport ='" + Integer.parseInt(idraport.getText()) + "';");
					rs2 = stmt.getResultSet();
					rs2.next();
					int rap = rs2.getInt("parafat");
					if(rap==0)
					{
					selectStatement = con.createStatement();
					selectStatement.execute("UPDATE raport SET simptome = '" +simptome.getText() +"', diagnostic='"+diagnostic.getText()+ "', recomandari='"+recomandari.getText()+"' WHERE idraport='"+Integer.parseInt(idraport.getText())+"'");
					JOptionPane.showMessageDialog(butonModifica, "Raportul a fost modificat cu succes!");
					}
					else
					JOptionPane.showMessageDialog(butonModifica, "Raportul nu poate fi modificat deoarece a fost pusa parafa", "Eroare", 0);
					con.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		butonModifica.setBounds(371, 476, 139, 58);
		panel_1.add(butonModifica);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(332, 43, 423, 315);
		panel_1.add(scrollPane_1);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);

	}
}
