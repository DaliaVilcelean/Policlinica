import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Image;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Button;
import javax.swing.JButton;
import javax.swing.border.MatteBorder;

public class PaginaLogare {

	private JFrame frame;
	//Conexiune Baza de date
	private MySQL_Connect conexiuneDB;
	private Connection con;
	Statement selectStatement = null;
	ResultSet rs = null;
	//Imagini background , logo etc.
	private Image img_background = new ImageIcon(PaginaLogare.class.getResource("res/background.jpg")).getImage()
			.getScaledInstance(1000, 700, Image.SCALE_SMOOTH);
	private Image img_user = new ImageIcon(PaginaLogare.class.getResource("res/user.png")).getImage().getScaledInstance(50, 50,
			Image.SCALE_SMOOTH);
	private Image img_password = new ImageIcon(PaginaLogare.class.getResource("res/password.png")).getImage()
			.getScaledInstance(70, 50, Image.SCALE_SMOOTH);
	//
	private JTextField username;
	private JPasswordField password;
	private LogareSucces windowLogare;
	
	public JFrame getFrame() {
		return frame;
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PaginaLogare window = new PaginaLogare();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public PaginaLogare() {
		initialize();
	}

	
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setBounds(100, 100, 1000, 700);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width / 2 - frame.getSize().width / 2,dim.height / 2 - frame.getSize().height / 2);
		frame.setTitle("Policlinica");
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(PaginaLogare.class.getResource("/res/logo_app.jpg")));
		frame.getContentPane().setLayout(null);
		
		username = new JTextField();
		username.setForeground(new Color(30, 144, 255));
		username.setFont(new Font("DialogInput", Font.BOLD, 20));
		username.setBounds(274, 284, 182, 43);
		frame.getContentPane().add(username);
		username.setColumns(10);
		
		password = new JPasswordField();
		password.setFont(new Font("DialogInput", Font.BOLD, 16));
		password.setBounds(274, 378, 182, 43);
		frame.getContentPane().add(password);
		
		JLabel lblUser = new JLabel("Utilizator");
		lblUser.setFont(new Font("DialogInput", Font.BOLD, 22));
		lblUser.setForeground(new Color(240, 255, 255));
		lblUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblUser.setBounds(97, 285, 155, 43);
		frame.getContentPane().add(lblUser);
		
		JLabel lblPass = new JLabel("Parol\u0103");
		lblPass.setHorizontalAlignment(SwingConstants.CENTER);
		lblPass.setForeground(new Color(240, 255, 255));
		lblPass.setFont(new Font("DialogInput", Font.BOLD, 22));
		lblPass.setBounds(97, 378, 155, 43);
		frame.getContentPane().add(lblPass);
		
		JLabel lbIconUser = new JLabel("");
		lbIconUser.setHorizontalAlignment(SwingConstants.CENTER);
		lbIconUser.setBounds(45, 275, 50, 52);
		lbIconUser.setIcon(new ImageIcon(img_user));
		frame.getContentPane().add(lbIconUser);

		JLabel lbIconPassword = new JLabel("");
		lbIconPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lbIconPassword.setBounds(45, 363, 60, 73);
		lbIconPassword.setIcon(new ImageIcon(img_password));
		frame.getContentPane().add(lbIconPassword);
		
		Button buttonLogare = new Button("Logare");
		buttonLogare.setBackground(new Color(106, 90, 205));
		buttonLogare.setForeground(new Color(0, 191, 255));
		buttonLogare.setFont(new Font("DialogInput", Font.BOLD, 24));
		buttonLogare.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					conexiuneDB = new MySQL_Connect();
					con = conexiuneDB.getConnection();
					selectStatement = con.createStatement();
					selectStatement.execute("SELECT * FROM utilizator");
					rs = selectStatement.getResultSet();
					int ok = 0;
					while (rs.next()) {
						if (username.getText().equals(rs.getString("nume_utilizator"))
								&& password.getText().equals(rs.getString("parola"))) {
							String u = username.getText();
							String nm = rs.getString("Nume");
							String prnm = rs.getString("Prenume");
							String fct = rs.getString("functie");
							windowLogare = new LogareSucces(u,nm,prnm,fct);
							windowLogare.frameLogare.setVisible(true);
							frame.dispose();
							ok = 1;
							break;
						}
					}
					con.close();
					if (ok == 0)
						JOptionPane.showMessageDialog(frame, "Datele introduse sunt incorecte!", "Eroare", 0);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		buttonLogare.setBounds(184, 454, 155, 43);
		frame.getContentPane().add(buttonLogare);
		
		Button buttonInregistrare = new Button("Creare cont");
		buttonInregistrare.setForeground(new Color(0, 191, 255));
		buttonInregistrare.setFont(new Font("DialogInput", Font.BOLD, 24));
		buttonInregistrare.setBackground(new Color(106, 90, 205));
		buttonInregistrare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				CreareCont windowCreareCont = new CreareCont();
				windowCreareCont.frameCreareCont.setVisible(true);
			}
		});
		buttonInregistrare.setBounds(177, 527, 175, 43);
		frame.getContentPane().add(buttonInregistrare);
		
		JLabel lblBackground = new JLabel("");
		lblBackground.setBounds(0, 0, 994, 671);
		lblBackground.setIcon(new ImageIcon(img_background));
		frame.getContentPane().add(lblBackground);
	}
}
