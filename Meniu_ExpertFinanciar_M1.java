

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.BadLocationException;

import com.mysql.cj.jdbc.CallableStatement;

import net.proteanit.sql.DbUtils;

public class Meniu_ExpertFinanciar_M1 extends JPanel {
	private Image img_back = new ImageIcon(LogareSucces.class.getResource("res/M1_Back.png")).getImage()
			.getScaledInstance(790, 630, Image.SCALE_SMOOTH);
	private Image img_back2 = new ImageIcon(LogareSucces.class.getResource("res/M1_Back.png")).getImage()
			.getScaledInstance(785, 591, Image.SCALE_SMOOTH);
	private JTable table;
	private JTextField nume;
	private JTextField prenume;
	private JTextField functie;
	private JTable table1;
	private JTextField nume1;
	private JTextField prenume1;
	private JTextField functie1;
	// Conexiune Baza de date
	private MySQL_Connect conexiuneDB;
	private Connection con;
	Statement selectStatement = null, selectStatement2 = null;
	ResultSet rs = null, rs2 = null;
	private JTextField data;
	private JTextField ora_inceput;
	private JTextField ora_sfarsit;
	private JTable table_1;
	private JTextField unitate;
	public Meniu_ExpertFinanciar_M1(String user) {
		
		setSize(790, 630);
		setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("DialogInput", Font.BOLD, 18));
		tabbedPane.setBackground(new Color(0, 204, 255));
		tabbedPane.setBorder(null);
		tabbedPane.setForeground(new Color(0, 102, 255));
		tabbedPane.setBounds(0, 0, 790, 630);
		add(tabbedPane);

		JPanel panel_cauta = new JPanel();
		panel_cauta.setFont(new Font("DialogInput", Font.BOLD, 18));
		panel_cauta.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("Cauta Angajat", null, panel_cauta, null);
		panel_cauta.setLayout(null);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(32, 34, 399, 326);
		panel_cauta.add(scrollPane_2);

		table = new JTable();
		scrollPane_2.setViewportView(table);
		// table.setModel(DbUtils.resultSetToTableModel(rs));

		JLabel lblNume = new JLabel("Nume");
		lblNume.setForeground(new Color(102, 0, 255));
		lblNume.setFont(new Font("DialogInput", Font.BOLD, 22));
		lblNume.setHorizontalAlignment(SwingConstants.CENTER);
		lblNume.setBounds(423, 34, 158, 50);
		panel_cauta.add(lblNume);

		JLabel lblPrenume = new JLabel("Prenume");
		lblPrenume.setForeground(new Color(102, 0, 255));
		lblPrenume.setFont(new Font("DialogInput", Font.BOLD, 22));
		lblPrenume.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrenume.setBounds(423, 117, 158, 50);
		panel_cauta.add(lblPrenume);

		JLabel lblFunctie = new JLabel("Functie");
		lblFunctie.setForeground(new Color(102, 0, 255));
		lblFunctie.setFont(new Font("DialogInput", Font.BOLD, 22));
		lblFunctie.setHorizontalAlignment(SwingConstants.CENTER);
		lblFunctie.setBounds(423, 206, 158, 50);
		panel_cauta.add(lblFunctie);

		nume = new JTextField();
		nume.setBounds(606, 34, 158, 50);
		panel_cauta.add(nume);
		nume.setColumns(10);

		prenume = new JTextField();
		prenume.setColumns(10);
		prenume.setBounds(606, 121, 158, 50);
		panel_cauta.add(prenume);

		functie = new JTextField();
		functie.setColumns(10);
		functie.setBounds(606, 210, 158, 50);
		panel_cauta.add(functie);

		try {
			conexiuneDB = new MySQL_Connect();
			con = conexiuneDB.getConnection();
			selectStatement = con.createStatement();
			selectStatement.execute("SELECT Nume,Prenume,functie AS 'Functie' FROM utilizator");
			rs = selectStatement.getResultSet();
			con.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		Button butonCauta = new Button("Cauta Angajat");
		butonCauta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String n = nume.getText();
					String p = prenume.getText();
					String f = functie.getText();
					conexiuneDB = new MySQL_Connect();
					con = conexiuneDB.getConnection();
					selectStatement = con.createStatement();
					if (f.equals("Medic") || f.equals("Asistent Medical") || f.equals("Receptioner"))
						selectStatement.execute(
								"SELECT Nume,Prenume,functie AS 'Functie' , CURDATE() ,os.ora_s AS 'Ora start', os.ora_f AS 'Ora final' FROM utilizator, orar_specific os WHERE Nume='"
										+ n + "' AND Prenume='" + p + "' AND functie='" + f
										+ "' AND os.zi = CURDATE();");
					else
						selectStatement.execute(
								"SELECT Nume,Prenume,functie AS 'Functie', CURDATE() ,og.ora_s AS 'Ora start', og.ora_f AS 'Ora final' FROM utilizator, orar_generic og WHERE Nume='"
										+ n + "' AND Prenume='" + p + "' AND functie='" + f
										+ "' AND og.ziua = CURDATE();");
					rs = selectStatement.getResultSet();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					con.close();

				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		butonCauta.setBounds(524, 298, 158, 50);
		panel_cauta.add(butonCauta);

		/*
		 * JLabel lblNewLabel = new
		 * JLabel("Introduceti ziua , ora de inceput si ora de sfarsit pentru a modifica orarul"
		 * ); lblNewLabel.setForeground(new Color(255, 255, 255));
		 * lblNewLabel.setFont(new Font("DialogInput", Font.BOLD, 14));
		 * lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		 * lblNewLabel.setBounds(62, 388, 650, 62); panel_cauta.add(lblNewLabel);
		 * 
		 * data = new JTextField(); data.setBounds(32, 485, 158, 50);
		 * panel_cauta.add(data); data.setColumns(10);
		 * 
		 * ora_inceput = new JTextField(); ora_inceput.setColumns(10);
		 * ora_inceput.setBounds(222, 485, 158, 50); panel_cauta.add(ora_inceput);
		 * 
		 * ora_sfarsit = new JTextField(); ora_sfarsit.setColumns(10);
		 * ora_sfarsit.setBounds(410, 485, 158, 50); panel_cauta.add(ora_sfarsit);
		 * 
		 * Button butonSchimba = new Button("Schimba Orar");
		 * butonSchimba.addActionListener(new ActionListener() { public void
		 * actionPerformed(ActionEvent e) { try { String n = nume.getText(); String p =
		 * prenume.getText(); String f = functie.getText(); conexiuneDB = new
		 * MySQL_Connect(); con = conexiuneDB.getConnection(); String query; //String
		 * ora_i = ora_inceput.getText(); //Date date1 = null; //date1 = (Date) new
		 * SimpleDateFormat("HH:mm:ss").parse(ora_i); //String ora_sf =
		 * ora_inceput.getText(); //Date date2 = null; //date2 = (Date) new
		 * SimpleDateFormat("HH:mm:ss").parse(ora_sf); java.sql.Time time = new
		 * java.sql.Time(Integer.parseInt(ora_inceput.getText(0, 2)),
		 * Integer.parseInt(ora_inceput.getText(3, 2)),
		 * Integer.parseInt(ora_inceput.getText(6, 2))); java.sql.Time time2 = new
		 * java.sql.Time(Integer.parseInt(ora_sfarsit.getText(0, 2)),
		 * Integer.parseInt(ora_sfarsit.getText(3, 2)),
		 * Integer.parseInt(ora_sfarsit.getText(6, 2))); SimpleDateFormat format = new
		 * SimpleDateFormat("yyyy-MM-dd"); java.util.Date utilDate =
		 * format.parse(data.getText()); java.sql.Date sqlDate = new
		 * java.sql.Date(utilDate.getTime());
		 * if(f.equals("Medic")||f.equals("Asistent Medical")||f.equals("Receptioner"))
		 * query = new
		 * String("UPDATE os SET ora_s = ? , ora_f = ? FROM orar_specific os INNER JOIN utilizator u ON os.zi = ? AND u.Nume = ? AND u.Prenume = ? AND u.functie = ? AND u.idutilizator = os.iduser"
		 * ); //query = new
		 * String("UPDATE os SET ora_s = ? , ora_f = ? FROM orar_specific os WHERE zi=? AND utilizator.Nume = ? AND utilizator.Prenume = ? AND utilizator.functie = ? AND utilizator.idutilizator = iduser;"
		 * ); else //query = new
		 * String("UPDATE orar_generic SET ora_s = ? , ora_f = ? WHERE ziua=? AND utilizator.Nume = ? AND utilizator.Prenume = ? AND utilizator.functie = ? AND utilizator.idutilizator = idAngajat;"
		 * ); query = new
		 * String("UPDATE og SET ora_s = ? , ora_f = ? FROM orar_generic og INNER JOIN utilizator u ON og.ziua = ? AND u.Nume = ? AND u.Prenume = ? AND u.functie = ? AND u.idutilizator = og.idAngajat;"
		 * ); PreparedStatement preparedStmt = con.prepareStatement(query);
		 * //preparedStmt.setTime(1,new Time(date1.getTime()));
		 * preparedStmt.setTime(1,time); preparedStmt.setTime(2,time2);
		 * preparedStmt.setDate(3, sqlDate); preparedStmt.setString(4, n);
		 * preparedStmt.setString(5, p); preparedStmt.setString(6, f);
		 * preparedStmt.executeUpdate();
		 * 
		 * selectStatement = con.createStatement();
		 * if(f.equals("Medic")||f.equals("Asistent Medical")||f.equals("Receptioner"))
		 * selectStatement.
		 * execute("SELECT Nume,Prenume,functie AS 'Functie',os.ora_s AS 'Ora start', os.ora_f AS 'Ora final' FROM utilizator, orar_specific os WHERE Nume='"
		 * + n + "' AND Prenume='" + p + "' AND functie='" + f + "';"); else
		 * selectStatement.
		 * execute("SELECT Nume,Prenume,functie AS 'Functie',og.ora_s AS 'Ora start', og.ora_f AS 'Ora final' FROM utilizator, orar_generic og WHERE Nume='"
		 * + n + "' AND Prenume='" + p + "' AND functie='" + f + "';"); rs =
		 * selectStatement.getResultSet();
		 * table.setModel(DbUtils.resultSetToTableModel(rs)); con.close();
		 * 
		 * } catch (SQLException | ParseException | NumberFormatException |
		 * BadLocationException e1) { e1.printStackTrace(); } } });
		 * butonSchimba.setBounds(612, 491, 152, 44); panel_cauta.add(butonSchimba);
		 */
		JLabel lblback1 = new JLabel("");
		lblback1.setBounds(0, 0, 785, 591);
		panel_cauta.add(lblback1);
		lblback1.setIcon(new ImageIcon(img_back2));

		JPanel panel_concediu = new JPanel();
		panel_concediu.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("Concediu", null, panel_concediu, null);
		panel_concediu.setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(32, 34, 356, 326);
		panel_concediu.add(scrollPane_1);

		table1 = new JTable();
		scrollPane_1.setViewportView(table1);

		JLabel lblNume1 = new JLabel("Nume");
		lblNume1.setForeground(new Color(102, 0, 255));
		lblNume1.setFont(new Font("DialogInput", Font.BOLD, 22));
		lblNume1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNume1.setBounds(423, 34, 158, 50);
		panel_concediu.add(lblNume1);

		JLabel lblPrenume1 = new JLabel("Prenume");
		lblPrenume1.setForeground(new Color(102, 0, 255));
		lblPrenume1.setFont(new Font("DialogInput", Font.BOLD, 22));
		lblPrenume1.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrenume1.setBounds(423, 117, 158, 50);
		panel_concediu.add(lblPrenume1);

		JLabel lblFunctie1 = new JLabel("Functie");
		lblFunctie1.setForeground(new Color(102, 0, 255));
		lblFunctie1.setFont(new Font("DialogInput", Font.BOLD, 22));
		lblFunctie1.setHorizontalAlignment(SwingConstants.CENTER);
		lblFunctie1.setBounds(423, 206, 158, 50);
		panel_concediu.add(lblFunctie1);

		nume1 = new JTextField();
		nume1.setBounds(606, 34, 158, 50);
		panel_concediu.add(nume1);
		nume1.setColumns(10);

		prenume1 = new JTextField();
		prenume1.setColumns(10);
		prenume1.setBounds(606, 121, 158, 50);
		panel_concediu.add(prenume1);

		functie1 = new JTextField();
		functie1.setColumns(10);
		functie1.setBounds(606, 210, 158, 50);
		panel_concediu.add(functie1);

		try {
			conexiuneDB = new MySQL_Connect();
			con = conexiuneDB.getConnection();
			selectStatement = con.createStatement();
			selectStatement.execute("SELECT Nume,Prenume,functie AS 'Functie' FROM utilizator");
			rs = selectStatement.getResultSet();
			table1.setModel(DbUtils.resultSetToTableModel(rs));
			con.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		Button butonConcedii = new Button("Arata concedii");
		butonConcedii.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String n = nume1.getText();
					String p = prenume1.getText();
					String f = functie1.getText();
					conexiuneDB = new MySQL_Connect();
					con = conexiuneDB.getConnection();
					selectStatement = con.createStatement();
					selectStatement.execute(
							"SELECT c.inceput_concediu AS 'Inceput Concediu', c.sfarsit_concediu AS 'Sfarsit concediu' from concediu c, utilizator u WHERE u.Nume='"
									+ n + "' AND u.Prenume='" + p + "' AND u.functie='" + f
									+ "' AND c.iduser = u.idutilizator;");
					rs = selectStatement.getResultSet();
					table1.setModel(DbUtils.resultSetToTableModel(rs));
					con.close();

				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		butonConcedii.setFont(new Font("DialogInput", Font.BOLD, 17));
		butonConcedii.setBounds(547, 305, 152, 57);
		panel_concediu.add(butonConcedii);

		JLabel lblback2 = new JLabel("");
		lblback2.setBounds(0, 0, 785, 591);
		panel_concediu.add(lblback2);
		lblback2.setIcon(new ImageIcon(img_back2));

		JPanel panel_orar = new JPanel();
		panel_orar.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("Orar Saptamanal", null, panel_orar, null);
		panel_orar.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(98, 98, 542, 164);
		panel_orar.add(scrollPane);

		table_1 = new JTable();
		scrollPane.setViewportView(table_1);

		JLabel lblUnitate = new JLabel("Unitate Medicala");
		lblUnitate.setForeground(new Color(255, 255, 255));
		lblUnitate.setFont(new Font("DialogInput", Font.BOLD, 16));
		lblUnitate.setBounds(98, 313, 172, 49);
		panel_orar.add(lblUnitate);

		unitate = new JTextField();
		unitate.setBounds(299, 313, 224, 49);
		panel_orar.add(unitate);
		unitate.setColumns(10);
		
				Button arataProgram = new Button("Arata Program");
				arataProgram.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							String umed = unitate.getText();
							conexiuneDB = new MySQL_Connect();
							con = conexiuneDB.getConnection();
							selectStatement2 = con.createStatement();
							selectStatement2.execute(
									"SELECT luni_s,luni_f,marti_s,marti_f,miercuri_s,miercuri_f,joi_s,joi_f,vineri_s,vineri_f FROM program,unitate_medicala um WHERE program.nrProgram = um.nrProgram AND um.denumire = '"+ umed+"';");
							rs = selectStatement2.getResultSet();
							table_1.setModel(DbUtils.resultSetToTableModel(rs));
							con.close();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
				});
				arataProgram.setBounds(560, 313, 151, 49);
				panel_orar.add(arataProgram);

		JLabel lblback3 = new JLabel("");
		lblback3.setBounds(0, 0, 785, 591);
		panel_orar.add(lblback3);
		lblback3.setIcon(new ImageIcon(img_back2));

		JLabel lbBackground = new JLabel("");
		lbBackground.setBounds(0, 0, 790, 630);
		lbBackground.setIcon(new ImageIcon(img_back));
		add(lbBackground);
	}
}

