import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTable;
import java.awt.Button;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Label;
import javax.swing.SwingConstants;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Meniu_Asistent_M3 extends JPanel {
	private JTable table;
	private JTextField idraport;
	private JTextField nume_p;
	private JTextField prenume_p;
	private JTextField simptome;
	private JTextField diagnostic;
	private JTextField recomandari;
	private JTextField parafat;
	// Conexiune Baza de date
	private MySQL_Connect conexiuneDB;
	private Connection con;
	Statement selectStatement = null, selectStatement2 = null, stmt = null;
	ResultSet rs = null, rs2 = null, rs3 = null;
	private JTextField idmedic;

	public Meniu_Asistent_M3(String User) {
		setBackground(new Color(135, 206, 250));
		setSize(790, 630);
		setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(313, 69, 452, 332);
		add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		Button butonRapoarte = new Button("Vezi Rapoarte");
		butonRapoarte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					conexiuneDB = new MySQL_Connect();
					con = conexiuneDB.getConnection();
					selectStatement = con.createStatement();
					selectStatement.execute(
							"SELECT r.* FROM raport r , asistent a , utilizator u WHERE r.id_asistent = a.idasistent AND a.idasistent = u.idutilizator AND u.nume_utilizator = '"
									+ User + "';");
					rs = selectStatement.getResultSet();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					con.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		butonRapoarte.setBounds(474, 437, 146, 58);
		add(butonRapoarte);

		JLabel lblNewLabel = new JLabel("Id Raport");
		lblNewLabel.setFont(new Font("DialogInput", Font.BOLD, 14));
		lblNewLabel.setForeground(new Color(255, 250, 250));
		lblNewLabel.setBounds(10, 69, 103, 36);
		add(lblNewLabel);

		idraport = new JTextField();
		idraport.setBounds(138, 69, 133, 41);
		add(idraport);
		idraport.setColumns(10);

		JLabel lblNumePacient = new JLabel("Nume Pacient");
		lblNumePacient.setForeground(new Color(255, 250, 250));
		lblNumePacient.setFont(new Font("DialogInput", Font.BOLD, 14));
		lblNumePacient.setBounds(10, 194, 103, 36);
		add(lblNumePacient);

		JLabel lblPrenumePacient = new JLabel("Prenume Pacient");
		lblPrenumePacient.setForeground(new Color(255, 250, 250));
		lblPrenumePacient.setFont(new Font("DialogInput", Font.BOLD, 12));
		lblPrenumePacient.setBounds(10, 254, 118, 36);
		add(lblPrenumePacient);

		JLabel lblSimptome = new JLabel("Simptome");
		lblSimptome.setForeground(new Color(255, 250, 250));
		lblSimptome.setFont(new Font("DialogInput", Font.BOLD, 14));
		lblSimptome.setBounds(10, 315, 103, 36);
		add(lblSimptome);

		JLabel lblDiagnostic = new JLabel("Diagnostic");
		lblDiagnostic.setForeground(new Color(255, 250, 250));
		lblDiagnostic.setFont(new Font("DialogInput", Font.BOLD, 14));
		lblDiagnostic.setBounds(10, 382, 103, 36);
		add(lblDiagnostic);

		nume_p = new JTextField();
		nume_p.setColumns(10);
		nume_p.setBounds(138, 193, 133, 41);
		add(nume_p);

		prenume_p = new JTextField();
		prenume_p.setColumns(10);
		prenume_p.setBounds(138, 253, 133, 41);
		add(prenume_p);

		simptome = new JTextField();
		simptome.setColumns(10);
		simptome.setBounds(138, 314, 133, 41);
		add(simptome);

		diagnostic = new JTextField();
		diagnostic.setColumns(10);
		diagnostic.setBounds(138, 381, 133, 41);
		add(diagnostic);

		JLabel lblRecomandari = new JLabel("Recomandari");
		lblRecomandari.setForeground(new Color(255, 250, 250));
		lblRecomandari.setFont(new Font("DialogInput", Font.BOLD, 14));
		lblRecomandari.setBounds(10, 459, 103, 36);
		add(lblRecomandari);

		JLabel lblParafat = new JLabel("Parafat");
		lblParafat.setForeground(new Color(255, 250, 250));
		lblParafat.setFont(new Font("DialogInput", Font.BOLD, 14));
		lblParafat.setBounds(10, 522, 103, 36);
		add(lblParafat);

		recomandari = new JTextField();
		recomandari.setColumns(10);
		recomandari.setBounds(138, 454, 133, 41);
		add(recomandari);

		parafat = new JTextField();
		parafat.setEditable(false);
		parafat.setColumns(10);
		parafat.setBounds(138, 517, 133, 41);
		add(parafat);

		JLabel lblNewLabel_1 = new JLabel("Rapoarte medicale");
		lblNewLabel_1.setForeground(new Color(106, 90, 205));
		lblNewLabel_1.setFont(new Font("DialogInput", Font.BOLD, 20));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(228, 0, 299, 58);
		add(lblNewLabel_1);

		Button butonAdauga = new Button("Adauga Raport");
		butonAdauga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					conexiuneDB = new MySQL_Connect();
					con = conexiuneDB.getConnection();
					selectStatement2 = con.createStatement();
					selectStatement2
							.execute("SELECT idutilizator FROM utilizator WHERE nume_utilizator='" + User + "';");
					rs2 = selectStatement2.getResultSet();
					rs2.next();
					int id_asistent = rs2.getInt("idutilizator");
					stmt = con.createStatement();
					stmt.execute("SELECT idprogramare FROM programare WHERE nume_pacient ='"+nume_p.getText()+"' AND prenume_pacient='"+prenume_p.getText()+"' AND zi = CURDATE();");
					rs3 = stmt.getResultSet();
					rs3.next();
					int id_p = rs3.getInt("idprogramare");
					selectStatement = con.createStatement();
					String sql = "INSERT INTO raport(nume_pacient,prenume_pacient,id_asistent,id_medic_consultatie,data_consultatiei,simptome,diagnostic,recomandari,idprogramare,parafat) VALUES ('" + nume_p.getText() + "','" + prenume_p.getText() + "','" + id_asistent + "','"
							+ Integer.parseInt(idmedic.getText()) + "',CURDATE(),'" + simptome.getText() + "','"+diagnostic.getText()+"','"+recomandari.getText()+"','"+id_p+"','0')";
					selectStatement.executeUpdate(sql);
					rs = selectStatement.getResultSet();
					JOptionPane.showMessageDialog(butonAdauga, "Raportul a fost adaugat cu succes");
					con.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		butonAdauga.setBounds(474, 533, 139, 48);
		add(butonAdauga);

		JLabel lblIdMedic = new JLabel("Id Medic");
		lblIdMedic.setForeground(new Color(255, 250, 250));
		lblIdMedic.setFont(new Font("DialogInput", Font.BOLD, 14));
		lblIdMedic.setBounds(10, 136, 103, 36);
		add(lblIdMedic);
		
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
		butonModifica.setBounds(310, 523, 139, 58);
		add(butonModifica);

		idmedic = new JTextField();
		idmedic.setColumns(10);
		idmedic.setBounds(138, 131, 133, 41);
		add(idmedic);

	}
}
