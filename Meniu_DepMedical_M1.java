
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;

public class Meniu_DepMedical_M1 extends JPanel {
	private Image img_back = new ImageIcon(LogareSucces.class.getResource("res/M1_Back.png")).getImage()
			.getScaledInstance(790, 630, Image.SCALE_SMOOTH);
	private JTable table;
	private MySQL_Connect conexiuneDB;
	private Connection con;
	Statement selectStatement = null, stmt = null, selectStatement1 = null, selectStatement2 = null, stmt2 = null;
	ResultSet rs = null, rs1 = null, rs2 = null;
	private JTextField ziOrar;
	private JTextField zi1perioada;
	private JTextField zi2perioada;

	public Meniu_DepMedical_M1(String user) {
		setSize(790, 630);
		setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(134, 32, 529, 293);
		add(scrollPane);

		table = new JTable();
		table.setRowSelectionAllowed(false);
		scrollPane.setViewportView(table);

		Button buton_Orar = new Button("Afisare Orar");
		buton_Orar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					conexiuneDB = new MySQL_Connect();
					con = conexiuneDB.getConnection();
					selectStatement = con.createStatement();
					selectStatement.execute(
							"SELECT os.zi, SUBTIME(os.ora_s,'02:00:00') AS 'Ora Start', SUBTIME(os.ora_f,'02:00:00') AS 'Ora Final', os.unitate_med AS 'Unitate Medicala' FROM orar_specific os, utilizator u WHERE os.iduser=u.idutilizator AND u.nume_utilizator ='"
									+ user
									+ "' AND MONTH(zi) = MONTH(CURRENT_DATE()) AND YEAR(zi) = YEAR(CURRENT_DATE()) ORDER BY os.zi ASC;");
					rs = selectStatement.getResultSet();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					con.close();

				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		buton_Orar.setBounds(158, 354, 155, 59);
		add(buton_Orar);

		Button buton_Concediu = new Button("Afisare Concediu");
		buton_Concediu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					conexiuneDB = new MySQL_Connect();
					con = conexiuneDB.getConnection();
					selectStatement2 = con.createStatement();
					selectStatement2.execute(
							"SELECT c.inceput_concediu AS 'Inceput concediu', c.sfarsit_concediu AS 'Sfarsit concediu' FROM concediu c, utilizator u WHERE c.iduser = u.idutilizator AND u.nume_utilizator ='"
									+ user + "'");
					rs2 = selectStatement2.getResultSet();
					table.setModel(DbUtils.resultSetToTableModel(rs2));
					con.close();

				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		buton_Concediu.setBounds(435, 354, 155, 59);
		add(buton_Concediu);

		ziOrar = new JTextField();
		ziOrar.setName("");
		ziOrar.setToolTipText("YYYY-MM-DD");
		ziOrar.setBounds(225, 472, 147, 43);
		add(ziOrar);
		ziOrar.setColumns(10);

		Button butonZiSpecifica = new Button("Orar zi specifica");
		butonZiSpecifica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String zi = ziOrar.getText();
				try {
					conexiuneDB = new MySQL_Connect();
					con = conexiuneDB.getConnection();
					selectStatement = con.createStatement();
					selectStatement.execute(
							"SELECT os.zi, SUBTIME(os.ora_s,'02:00:00') AS 'Ora Start', SUBTIME(os.ora_f,'02:00:00') AS 'Ora Final', os.unitate_med AS 'Unitate Medicala' FROM orar_specific os, utilizator u WHERE os.iduser=u.idutilizator AND u.nume_utilizator ='"
									+ user + "' AND os.zi='" + zi + "';");
					rs = selectStatement.getResultSet();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					con.close();

				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		butonZiSpecifica.setBounds(409, 472, 155, 43);
		add(butonZiSpecifica);
		
		zi1perioada = new JTextField();
		zi1perioada.setToolTipText("Data inceput");
		zi1perioada.setBounds(225, 558, 147, 49);
		add(zi1perioada);
		zi1perioada.setColumns(10);
		
		zi2perioada = new JTextField();
		zi2perioada.setToolTipText("Data final");
		zi2perioada.setColumns(10);
		zi2perioada.setBounds(409, 558, 147, 49);
		add(zi2perioada);
		
		Button butonZiInterval = new Button("Orar interval specific");
		butonZiInterval.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String zi1 = zi1perioada.getText();
				String zi2 = zi2perioada.getText();
				try {
					conexiuneDB = new MySQL_Connect();
					con = conexiuneDB.getConnection();
					selectStatement = con.createStatement();
					selectStatement.execute(
							"SELECT os.zi, SUBTIME(os.ora_s,'02:00:00') AS 'Ora Start', SUBTIME(os.ora_f,'02:00:00') AS 'Ora Final', os.unitate_med AS 'Unitate Medicala' FROM orar_specific os, utilizator u WHERE os.iduser=u.idutilizator AND u.nume_utilizator ='"
									+ user + "' AND os.zi between '" + zi1 + "' and '" + zi2 + "' ORDER BY os.zi ASC;");
					rs = selectStatement.getResultSet();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					con.close();

				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		butonZiInterval.setBounds(598, 558, 155, 49);
		add(butonZiInterval);
		
		JLabel lblOrarPerioada = new JLabel("Orarul pe perioada");
		lblOrarPerioada.setForeground(Color.WHITE);
		lblOrarPerioada.setFont(new Font("DialogInput", Font.BOLD, 14));
		lblOrarPerioada.setHorizontalAlignment(SwingConstants.CENTER);
		lblOrarPerioada.setBounds(31, 551, 161, 56);
		add(lblOrarPerioada);
		
		JLabel lblOrarulZiSpecifica = new JLabel("Orarul pe o zi specifica");
		lblOrarulZiSpecifica.setHorizontalAlignment(SwingConstants.CENTER);
		lblOrarulZiSpecifica.setForeground(Color.WHITE);
		lblOrarulZiSpecifica.setFont(new Font("DialogInput", Font.BOLD, 14));
		lblOrarulZiSpecifica.setBounds(10, 459, 205, 56);
		add(lblOrarulZiSpecifica);

		JLabel lbBackground = new JLabel("");
		lbBackground.setBounds(0, 0, 790, 630);
		lbBackground.setIcon(new ImageIcon(img_back));
		add(lbBackground);
	}
}
