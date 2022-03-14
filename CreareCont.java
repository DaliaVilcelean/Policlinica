import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.text.BadLocationException;

import com.mysql.cj.jdbc.CallableStatement;
import com.mysql.cj.util.StringUtils;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;

public class CreareCont {
	private Image img_background = new ImageIcon(CreareCont.class.getResource("res/creare_back.jpg")).getImage()
			.getScaledInstance(888, 780, Image.SCALE_SMOOTH);
	PaginaLogare windowStart;
	JFrame frameCreareCont;
	private MySQL_Connect conexiuneDB;
	private Connection con;
	private Statement selectStatement = null;
	private CallableStatement stmt = null;
	private ResultSet rs = null;
	private JTextField CNP;
	private JTextField Nume;
	private JTextField Prenume;
	private JTextField Nr_telefon;
	private JTextField Email;
	private JTextField IBAN;
	private JTextField nrContract;
	private JTextField DataAngajarii;
	private JTextField user;
	private JPasswordField parola;
	private int ok = 0;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreareCont window = new CreareCont();
					window.frameCreareCont.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CreareCont() {
		initialize();
	}

	private void initialize() {
		frameCreareCont = new JFrame();
		frameCreareCont.setBounds(100, 100, 894, 804);
		frameCreareCont.setResizable(false);
		frameCreareCont.setTitle("Policlinica");
		frameCreareCont.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameCreareCont
				.setIconImage(Toolkit.getDefaultToolkit().getImage(CreareCont.class.getResource("/res/logo_app.jpg")));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frameCreareCont.setLocation(dim.width / 2 - frameCreareCont.getSize().width / 2,
				dim.height / 2 - frameCreareCont.getSize().height / 2);
		frameCreareCont.getContentPane().setLayout(null);

		JLabel lblreg = new JLabel("Formular de \u00EEnregistrare");
		lblreg.setBounds(261, 11, 342, 68);
		lblreg.setForeground(new Color(255, 255, 255));
		lblreg.setFont(new Font("DialogInput", Font.BOLD, 24));
		lblreg.setHorizontalAlignment(SwingConstants.CENTER);
		frameCreareCont.getContentPane().add(lblreg);

		Icon imgIcon = new ImageIcon(this.getClass().getResource("res/register_gif.gif"));
		JLabel registerGIF = new JLabel(imgIcon);
		registerGIF.setBounds(599, 0, 106, 100);
		registerGIF.setHorizontalAlignment(SwingConstants.CENTER);
		frameCreareCont.getContentPane().add(registerGIF);

		JButton btnLogare = new JButton("Logare");
		btnLogare.setBounds(37, 702, 211, 49);
		btnLogare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameCreareCont.dispose();
				windowStart = new PaginaLogare();
				windowStart.getFrame().setVisible(true);
			}
		});
		btnLogare.setHorizontalTextPosition(SwingConstants.CENTER);
		btnLogare.setForeground(new Color(102, 102, 255));
		btnLogare.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnLogare.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 204, 0)));
		btnLogare.setBackground(new Color(255, 204, 0));
		frameCreareCont.getContentPane().add(btnLogare);

		CNP = new JTextField();
		CNP.setFont(new Font("DialogInput", Font.BOLD, 14));
		CNP.setBounds(224, 312, 170, 50);
		frameCreareCont.getContentPane().add(CNP);
		CNP.setColumns(10);

		JLabel lblCNP = new JLabel("CNP");
		lblCNP.setForeground(new Color(255, 215, 0));
		lblCNP.setFont(new Font("Dialog", Font.BOLD, 18));
		lblCNP.setHorizontalAlignment(SwingConstants.CENTER);
		lblCNP.setBounds(108, 310, 106, 50);
		frameCreareCont.getContentPane().add(lblCNP);

		Nume = new JTextField();
		Nume.setFont(new Font("DialogInput", Font.BOLD, 14));
		Nume.setColumns(10);
		Nume.setBounds(224, 215, 170, 50);
		frameCreareCont.getContentPane().add(Nume);

		Prenume = new JTextField();
		Prenume.setFont(new Font("DialogInput", Font.BOLD, 14));
		Prenume.setColumns(10);
		Prenume.setBounds(637, 215, 170, 50);
		frameCreareCont.getContentPane().add(Prenume);

		Nr_telefon = new JTextField();
		Nr_telefon.setFont(new Font("DialogInput", Font.BOLD, 14));
		Nr_telefon.setColumns(10);
		Nr_telefon.setBounds(637, 312, 170, 50);
		frameCreareCont.getContentPane().add(Nr_telefon);

		JLabel lblNume = new JLabel("Nume");
		lblNume.setHorizontalAlignment(SwingConstants.CENTER);
		lblNume.setForeground(new Color(255, 215, 0));
		lblNume.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNume.setBounds(108, 215, 106, 50);
		frameCreareCont.getContentPane().add(lblNume);

		JLabel lblPrenume = new JLabel("Prenume");
		lblPrenume.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrenume.setForeground(new Color(255, 215, 0));
		lblPrenume.setFont(new Font("Dialog", Font.BOLD, 18));
		lblPrenume.setBounds(505, 215, 106, 50);
		frameCreareCont.getContentPane().add(lblPrenume);

		JLabel lblNrtelefon = new JLabel("Nr.Telefon");
		lblNrtelefon.setHorizontalAlignment(SwingConstants.CENTER);
		lblNrtelefon.setForeground(new Color(255, 215, 0));
		lblNrtelefon.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNrtelefon.setBounds(497, 310, 106, 50);
		frameCreareCont.getContentPane().add(lblNrtelefon);

		Email = new JTextField();
		Email.setFont(new Font("DialogInput", Font.BOLD, 14));
		Email.setColumns(10);
		Email.setBounds(224, 413, 170, 50);
		frameCreareCont.getContentPane().add(Email);

		IBAN = new JTextField();
		IBAN.setFont(new Font("DialogInput", Font.BOLD, 14));
		IBAN.setColumns(10);
		IBAN.setBounds(637, 413, 170, 50);
		frameCreareCont.getContentPane().add(IBAN);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail.setForeground(new Color(255, 215, 0));
		lblEmail.setFont(new Font("Dialog", Font.BOLD, 18));
		lblEmail.setBounds(108, 413, 106, 50);
		frameCreareCont.getContentPane().add(lblEmail);

		JLabel lblIban = new JLabel("IBAN");
		lblIban.setHorizontalAlignment(SwingConstants.CENTER);
		lblIban.setForeground(new Color(255, 215, 0));
		lblIban.setFont(new Font("Dialog", Font.BOLD, 18));
		lblIban.setBounds(505, 411, 106, 50);
		frameCreareCont.getContentPane().add(lblIban);

		nrContract = new JTextField();
		nrContract.setFont(new Font("DialogInput", Font.BOLD, 14));
		nrContract.setColumns(10);
		nrContract.setBounds(224, 510, 170, 50);
		frameCreareCont.getContentPane().add(nrContract);

		JLabel lblNrcontract = new JLabel("Nr.Contract");
		lblNrcontract.setHorizontalAlignment(SwingConstants.CENTER);
		lblNrcontract.setForeground(new Color(255, 215, 0));
		lblNrcontract.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNrcontract.setBounds(108, 510, 106, 50);
		frameCreareCont.getContentPane().add(lblNrcontract);

		DataAngajarii = new JTextField();
		DataAngajarii.setFont(new Font("DialogInput", Font.BOLD, 14));
		DataAngajarii.setColumns(10);
		DataAngajarii.setBounds(637, 510, 170, 50);
		frameCreareCont.getContentPane().add(DataAngajarii);

		JLabel lblDataAngajarii = new JLabel("Data Angajarii");
		lblDataAngajarii.setHorizontalAlignment(SwingConstants.CENTER);
		lblDataAngajarii.setForeground(new Color(255, 215, 0));
		lblDataAngajarii.setFont(new Font("Dialog", Font.BOLD, 18));
		lblDataAngajarii.setBounds(482, 510, 129, 50);
		frameCreareCont.getContentPane().add(lblDataAngajarii);

		JComboBox functie = new JComboBox();
		functie.setBounds(369, 599, 263, 49);
		functie.setFont(new Font("Tahoma", Font.PLAIN, 20));
		functie.setBorder(null);
		functie.setOpaque(false);
		functie.setModel(new DefaultComboBoxModel(new String[] { "-Alege-", "SuperAdministrator", "Administrator",
				"Asistent Medical", "Medic", "Inspector Resurse Umane", "Expert financiar contabil", "Receptioner" }));
		frameCreareCont.getContentPane().add(functie);

		JLabel lblFunctie = new JLabel("Functie");
		lblFunctie.setHorizontalAlignment(SwingConstants.CENTER);
		lblFunctie.setForeground(new Color(255, 215, 0));
		lblFunctie.setFont(new Font("Dialog", Font.BOLD, 18));
		lblFunctie.setBounds(233, 598, 106, 50);
		frameCreareCont.getContentPane().add(lblFunctie);

		user = new JTextField();
		user.setFont(new Font("DialogInput", Font.BOLD, 14));
		user.setColumns(10);
		user.setBounds(224, 127, 170, 50);
		frameCreareCont.getContentPane().add(user);

		JLabel lblUser = new JLabel("User");
		lblUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblUser.setForeground(new Color(255, 215, 0));
		lblUser.setFont(new Font("Dialog", Font.BOLD, 18));
		lblUser.setBounds(108, 127, 106, 50);
		frameCreareCont.getContentPane().add(lblUser);

		parola = new JPasswordField();
		parola.setBounds(638, 127, 169, 49);
		frameCreareCont.getContentPane().add(parola);

		JLabel lblParola = new JLabel("Parola");
		lblParola.setHorizontalAlignment(SwingConstants.CENTER);
		lblParola.setForeground(new Color(255, 215, 0));
		lblParola.setFont(new Font("Dialog", Font.BOLD, 18));
		lblParola.setBounds(505, 127, 106, 50);
		frameCreareCont.getContentPane().add(lblParola);

		JButton btnInregistrare = new JButton("Inregistrare");
		btnInregistrare.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				int verificare = 1;
				String fct = functie.getSelectedItem().toString();
				if (parola.getText().equals("") || user.getText().equals("") || Email.getText().equals("")
						|| fct == "-Alege-" || Nume.getText().equals("") || Prenume.getText().equals("")
						|| CNP.getText().equals("") || IBAN.getText().equals("") || nrContract.getText().equals("")
						|| DataAngajarii.getText().equals("") || Nr_telefon.getText().equals("")) {
					verificare = 0;
					JOptionPane.showMessageDialog(frameCreareCont, "Nu ai introdus una dintre date!\nVerifica din nou!",
							"Eroare", 0);
				} else if (parola.getText().length() < 8) {
					verificare = 0;
					JOptionPane.showMessageDialog(frameCreareCont, "Parola trebuie sa contina minim 8 caractere",
							"Eroare", 0);
				} else if (StringUtils.isStrictlyNumeric(CNP.getText()) != true || CNP.getText().length() != 8) {
					verificare = 0;
					JOptionPane.showMessageDialog(frameCreareCont, "CNP-ul trebuie sa contina exact 8 cifre!", "Eroare",
							0);
				} else if (StringUtils.isStrictlyNumeric(Nr_telefon.getText()) != true
						|| Nr_telefon.getText().length() != 10) {
					verificare = 0;
					JOptionPane.showMessageDialog(frameCreareCont,
							"Numarul de telefon trebuie sa contina exact 10 cifre!", "Eroare", 0);
				} else if (StringUtils.isStrictlyNumeric(nrContract.getText()) != true) {
					verificare = 0;
					JOptionPane.showMessageDialog(frameCreareCont, "Nr. de contract trebuie sa contina doar cifre!",
							"Eroare", 0);
				} else if (!validPattern(DataAngajarii.getText())) {
					verificare = 0;
					JOptionPane.showMessageDialog(frameCreareCont, "Formatul pentru data este incorect!", "Eroare", 0);
				} else if (!validEmail(Email.getText())) {
					verificare = 0;
					JOptionPane.showMessageDialog(frameCreareCont, "Ai introdus o adresa de email incorecta!", "Eroare",
							0);
				} else if (Nume.getText().matches(".*\\d.*")) {
					verificare = 0;
					JOptionPane.showMessageDialog(frameCreareCont, "Numele nu poate contine cifre!", "Eroare", 0);
				} else if (Prenume.getText().matches(".*\\d.*")) {
					verificare = 0;
					JOptionPane.showMessageDialog(frameCreareCont, "Prenumele nu poate contine cifre!", "Eroare", 0);
				}
				int aux_iesire = -1;
				if (verificare == 1) {
					try {
						conexiuneDB = new MySQL_Connect();
						con = conexiuneDB.getConnection();
						selectStatement = con.createStatement();
						selectStatement.execute("SELECT * FROM utilizator");
						rs = selectStatement.getResultSet();
						ok = 1;
						while (rs.next()) {
							if (user.getText().equals(rs.getString("nume_utilizator")))
								ok = 0;
						}
						if (ok == 1) {
							if (functie.getSelectedItem().toString().equals("SuperAdministrator")) {
								String cod = JOptionPane.showInputDialog(frameCreareCont,
										"Introduceti codul unic dedicat SuperAdministratorilor", "Verificare", 3);
								int c1 = Integer.parseInt(cod);
								if (c1 != 28640088)
									JOptionPane.showMessageDialog(frameCreareCont,
											"Ai introdus gresit codul unic SuperAdminilor!", "Eroare", 0);
								else
								{
									int year = Integer.parseInt(DataAngajarii.getText(0, 4));
									java.sql.Date date1 = new java.sql.Date(year - 1900,
											Integer.parseInt(DataAngajarii.getText(5, 2)) - 1,
											Integer.parseInt(DataAngajarii.getText(8, 2)));
									stmt = (CallableStatement) con.prepareCall("{call creare_cont(?,?,?,?,?,?,?,?,?,?,?)}");
									stmt.setInt(1, Integer.parseInt(CNP.getText()));
									stmt.setString(2, Nume.getText());
									stmt.setString(3, Prenume.getText());
									stmt.setInt(4, Integer.parseInt(Nr_telefon.getText()));
									stmt.setString(5, Email.getText());
									stmt.setString(6, IBAN.getText());
									stmt.setInt(7, Integer.parseInt(nrContract.getText()));
									stmt.setDate(8, date1);
									stmt.setString(9, functie.getSelectedItem().toString());
									stmt.setString(10, user.getText());
									stmt.setString(11, parola.getText());

									stmt.executeUpdate();
									stmt.close();
									JOptionPane.showMessageDialog(frameCreareCont, "Contul a fost creat cu succes!", "Succes",
											4);
									aux_iesire = 0;
								}
							}
							else if (functie.getSelectedItem().toString().equals("Administrator")) {
								String cod2 = JOptionPane.showInputDialog(frameCreareCont,
										"Introduceti codul unic dedicat Administratorilor", "Verificare", 3);
								int c2 = Integer.parseInt(cod2);

								if (c2 != 44022881)
									JOptionPane.showMessageDialog(frameCreareCont,
											"Ai introdus gresit codul unic Adminilor!", "Eroare", 0);
								
								else
								{
									int year = Integer.parseInt(DataAngajarii.getText(0, 4));
									java.sql.Date date1 = new java.sql.Date(year - 1900,
											Integer.parseInt(DataAngajarii.getText(5, 2)) - 1,
											Integer.parseInt(DataAngajarii.getText(8, 2)));
									stmt = (CallableStatement) con.prepareCall("{call creare_cont(?,?,?,?,?,?,?,?,?,?,?)}");
									stmt.setInt(1, Integer.parseInt(CNP.getText()));
									stmt.setString(2, Nume.getText());
									stmt.setString(3, Prenume.getText());
									stmt.setInt(4, Integer.parseInt(Nr_telefon.getText()));
									stmt.setString(5, Email.getText());
									stmt.setString(6, IBAN.getText());
									stmt.setInt(7, Integer.parseInt(nrContract.getText()));
									stmt.setDate(8, date1);
									stmt.setString(9, functie.getSelectedItem().toString());
									stmt.setString(10, user.getText());
									stmt.setString(11, parola.getText());

									stmt.executeUpdate();
									stmt.close();
									JOptionPane.showMessageDialog(frameCreareCont, "Contul a fost creat cu succes!", "Succes", 3);
									aux_iesire = 0;
								}
							}
							else
							{
							int year = Integer.parseInt(DataAngajarii.getText(0, 4));
							java.sql.Date date1 = new java.sql.Date(year - 1900,
									Integer.parseInt(DataAngajarii.getText(5, 2)) - 1,
									Integer.parseInt(DataAngajarii.getText(8, 2)));
							stmt = (CallableStatement) con.prepareCall("{call creare_cont(?,?,?,?,?,?,?,?,?,?,?)}");
							stmt.setInt(1, Integer.parseInt(CNP.getText()));
							stmt.setString(2, Nume.getText());
							stmt.setString(3, Prenume.getText());
							stmt.setInt(4, Integer.parseInt(Nr_telefon.getText()));
							stmt.setString(5, Email.getText());
							stmt.setString(6, IBAN.getText());
							stmt.setInt(7, Integer.parseInt(nrContract.getText()));
							stmt.setDate(8, date1);
							stmt.setString(9, functie.getSelectedItem().toString());
							stmt.setString(10, user.getText());
							stmt.setString(11, parola.getText());

							stmt.executeUpdate();
							stmt.close();
							JOptionPane.showMessageDialog(frameCreareCont, "Contul a fost creat cu succes!", "Succes",
									4);
							aux_iesire = 0;
						}
						}
						if (ok == 0)
							JOptionPane.showMessageDialog(frameCreareCont, "User-ul exista deja!", "Eroare", 0);
						if(aux_iesire == 0) {
							frameCreareCont.dispose();
							windowStart = new PaginaLogare();
							windowStart.getFrame().setVisible(true);
						}
					} catch (SQLException | NumberFormatException | BadLocationException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnInregistrare.setHorizontalTextPosition(SwingConstants.CENTER);
		btnInregistrare.setForeground(new Color(102, 102, 255));
		btnInregistrare.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnInregistrare.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 204, 0)));
		btnInregistrare.setBackground(new Color(255, 204, 0));
		btnInregistrare.setBounds(369, 686, 211, 49);
		frameCreareCont.getContentPane().add(btnInregistrare);

		JLabel lbBackground = new JLabel("");
		lbBackground.setBounds(0, 0, 888, 776);
		lbBackground.setFont(new Font("DialogInput", Font.BOLD, 30));
		lbBackground.setIcon(new ImageIcon(img_background));
		frameCreareCont.getContentPane().add(lbBackground);
	}

	public static boolean validPattern(String s) {
		return s.matches("\\d{4}-\\d{2}-\\d{2}");
	}

	public static boolean validEmail(String s) {
		return s.matches(".*@.*\\..*");
	}
}
