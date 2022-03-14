import java.awt.Button;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import net.proteanit.sql.DbUtils;

public class Meniu_SuperAdministrare extends JPanel {
	private Image img_back = new ImageIcon(LogareSucces.class.getResource("res/admin_back.jpg")).getImage()
			.getScaledInstance(790, 630, Image.SCALE_SMOOTH);
	private JTable table;
	// Conexiune Baza de date
	private MySQL_Connect conexiuneDB;
	private Connection con;
	Statement selectStatement = null, stmt = null, selectStatement1 = null, selectStatement2 = null, stmt2 = null;
	ResultSet rs = null, rs1 = null, rs2 = null;

	public Meniu_SuperAdministrare(String user) {
		setSize(790, 630);
		setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 170, 770, 301);
		add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		Button butonAngajati = new Button("Afiseaza Angajati");
		butonAngajati.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					conexiuneDB = new MySQL_Connect();
					con = conexiuneDB.getConnection();
					selectStatement = con.createStatement();
					selectStatement.execute(
							"SELECT idutilizator, CNP, Nume, Prenume, Email, Numar_Telefon, IBAN, nr_contract, data_angajarii, functie, salar_neg, nr_ore, nume_utilizator, parola  FROM utilizator");
					rs = selectStatement.getResultSet();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					con.close();

				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		butonAngajati.setBounds(284, 91, 238, 48);
		add(butonAngajati);

		Button butonModificare = new Button("Modifica Angajat");
		butonModificare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					conexiuneDB = new MySQL_Connect();
					con = conexiuneDB.getConnection();
					selectStatement2 = con.createStatement();
					int nrcoloane = table.getColumnCount();
					int linie_edit = table.getSelectedRow();
					if (linie_edit != -1) {
						int id = (int) table.getValueAt(linie_edit, 0);
						int cnp = (int) table.getValueAt(linie_edit, 1);
						String n = (String) table.getValueAt(linie_edit, 2);
						String p = (String) table.getValueAt(linie_edit, 3);
						String em = (String) table.getValueAt(linie_edit, 4);
						int n_tel = (int) table.getValueAt(linie_edit, 5);
						String ib = (String) table.getValueAt(linie_edit, 6);
						int contr = (int) table.getValueAt(linie_edit, 7);
						String f = (String) table.getValueAt(linie_edit, 9);
						float sal = (float) table.getValueAt(linie_edit, 10);
						int ore = (int) table.getValueAt(linie_edit, 11);
						String u = (String) table.getValueAt(linie_edit, 12);
						String par = (String) table.getValueAt(linie_edit, 13);

						selectStatement2.execute("UPDATE utilizator SET idutilizator='" + id + "' ,CNP='" + cnp
								+ "' ,Nume='" + n + "' ,Prenume='" + p + "' ,Numar_telefon='" + n_tel + "' , Email='"
								+ em + "' , IBAN='" + ib + "' , nr_contract='" + contr + "' ,functie='" + f
								+ "' , salar_neg='" + sal + "' ,nr_ore='" + ore + "' ,nume_utilizator='" + u
								+ "' ,parola='" + par + "' WHERE idutilizator='" + id + "';");
					}
					stmt2 = con.createStatement();
					stmt2.execute(
							"SELECT idutilizator, CNP, Nume, Prenume, Email, Numar_Telefon, IBAN, nr_contract, data_angajarii, functie, salar_neg, nr_ore, nume_utilizator, parola  FROM utilizator");
					rs2 = stmt2.getResultSet();
					table.setModel(DbUtils.resultSetToTableModel(rs2));
					con.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		butonModificare.setBounds(137, 511, 170, 48);
		add(butonModificare);

		Button butonSterge = new Button("Sterge Angajat");
		butonSterge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					conexiuneDB = new MySQL_Connect();
					con = conexiuneDB.getConnection();
					selectStatement1 = con.createStatement();
					int aux = table.getSelectedRow();
					if (aux != -1) {
						int idu = (int) table.getValueAt(aux, 0);
						selectStatement1.execute("DELETE FROM utilizator WHERE idutilizator='" + idu + "';");
						stmt = con.createStatement();
						stmt.execute(
								"SELECT idutilizator, CNP, Nume, Prenume, Email, IBAN, nr_contract, data_angajarii, functie, salar_neg, nr_ore, nume_utilizator, parola  FROM utilizator");
						rs1 = stmt.getResultSet();
						table.setModel(DbUtils.resultSetToTableModel(rs1));
					}

				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		butonSterge.setBounds(463, 511, 170, 48);
		add(butonSterge);

		JLabel lbBackground = new JLabel("");
		lbBackground.setBounds(0, 0, 790, 630);
		lbBackground.setIcon(new ImageIcon(img_back));
		add(lbBackground);
	}
}
