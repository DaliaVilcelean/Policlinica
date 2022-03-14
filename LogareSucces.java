import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

public class LogareSucces {

	JFrame frameLogare;
	private String utilizator = new String("");
	private String nume = new String("");
	private String prenume = new String("");
	private String functie = new String("");
	private PaginaLogare windowMain;
	private JPanel panelMain;
	// Background si Logo
	private Image img_logo = new ImageIcon(LogareSucces.class.getResource("res/logo_logare.png")).getImage()
			.getScaledInstance(140, 130, Image.SCALE_SMOOTH);
	private Image img_back2 = new ImageIcon(LogareSucces.class.getResource("res/backMenu.jpg")).getImage()
			.getScaledInstance(340, 700, Image.SCALE_SMOOTH);
	private Image img_back3 = new ImageIcon(LogareSucces.class.getResource("res/backMenu2.jpg")).getImage()
			.getScaledInstance(850, 685, Image.SCALE_SMOOTH);
	private Image img_home = new ImageIcon(LogareSucces.class.getResource("res/home.png")).getImage().getScaledInstance(40, 40,
			Image.SCALE_SMOOTH);
	private Image img_signout = new ImageIcon(LogareSucces.class.getResource("res/exit.png")).getImage().getScaledInstance(40,
			40, Image.SCALE_SMOOTH);
	private Image img_administrare = new ImageIcon(LogareSucces.class.getResource("res/administrare_logo.png")).getImage().getScaledInstance(40,
			40, Image.SCALE_SMOOTH);
	private Image img_medical = new ImageIcon(LogareSucces.class.getResource("res/medical_logo.png")).getImage().getScaledInstance(55,
			53, Image.SCALE_SMOOTH);
	private Image img_resurse = new ImageIcon(LogareSucces.class.getResource("res/resurse_umane.png")).getImage().getScaledInstance(40,
			40, Image.SCALE_SMOOTH);
	private Image img_financiar = new ImageIcon(LogareSucces.class.getResource("res/financiar.png")).getImage().getScaledInstance(60,
			40, Image.SCALE_SMOOTH);
	/// Meniuri ~ VIEW-urile din MySQL
	private Meniu_Home meniu_Home;
	private Meniu_Financiar meniu_Financiar;
	private Meniu_Medical meniu_Medical;
	private Meniu_SignOut meniu_SignOut;
	private Meniu_Administrare meniu_Administrare;
	private Meniu_SuperAdministrare meniu_SuperAdministrare;
	private Meniu_DepMedical_M1 meniu_depMedical_M1;
	private Meniu_Inspector_M1 meniu_Inspector_M1;
	private Meniu_ExpertFinanciar_M1 meniu_ExpertFinanciar_M1;
	private Meniu_Medic_M3 meniu_Medic_M3;
	private Meniu_Asistent_M3 meniu_Asistent_M3;
	private Meniu_Receptioner_M3 meniu_Receptioner_M3;
	///

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogareSucces window = new LogareSucces();
					window.frameLogare.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public LogareSucces() {
		initialize();
	}

	public LogareSucces(String user, String nm, String prnm, String fct) {
		utilizator = user;
		nume = nm;
		prenume = prnm;
		functie = fct;
		initialize();
	}

	private void initialize() {
		frameLogare = new JFrame();
		frameLogare.setBounds(100, 100, 1178, 708);
		frameLogare.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameLogare.setResizable(false);
		frameLogare.setTitle("Policlinica");
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frameLogare.setLocation(dim.width / 2 - frameLogare.getSize().width / 2,
				dim.height / 2 - frameLogare.getSize().height / 2);
		frameLogare.getContentPane().setLayout(null);
		frameLogare.setIconImage(
				Toolkit.getDefaultToolkit().getImage(LogareSucces.class.getResource("/res/logo_app.jpg")));

		meniu_Home = new Meniu_Home(nume, prenume);
		meniu_Financiar = new Meniu_Financiar(utilizator);
		meniu_Medical = new Meniu_Medical(utilizator);
		meniu_SignOut = new Meniu_SignOut(utilizator);
		meniu_Administrare = new Meniu_Administrare(utilizator);
		meniu_SuperAdministrare = new Meniu_SuperAdministrare(utilizator);
		meniu_depMedical_M1 = new Meniu_DepMedical_M1(utilizator);
		meniu_Inspector_M1 = new Meniu_Inspector_M1(utilizator);
		meniu_ExpertFinanciar_M1 = new Meniu_ExpertFinanciar_M1(utilizator);
		meniu_Medic_M3 = new Meniu_Medic_M3(utilizator);
		meniu_Asistent_M3 = new Meniu_Asistent_M3(utilizator);
		meniu_Receptioner_M3 = new Meniu_Receptioner_M3(utilizator);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 336, 691);
		panel.setBorder(null);
		frameLogare.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lbLogo = new JLabel("");
		lbLogo.setBounds(92, 55, 138, 127);
		lbLogo.setIcon(new ImageIcon(img_logo));
		panel.add(lbLogo);

		JPanel panel_Home = new JPanel();
		panel_Home.addMouseListener(new PanelButtonMouseAdapter(panel_Home) {
			@Override
			public void mouseClicked(MouseEvent e) {
				menuClicked(meniu_Home);
			}
		});
		panel_Home.setBackground(new Color(135, 206, 250));
		panel_Home.setBorder(new MatteBorder(2, 2, 1, 2, (Color) new Color(0, 191, 255)));
		panel_Home.setBounds(43, 215, 258, 75);
		panel.add(panel_Home);
		panel_Home.setLayout(null);
		
		JLabel lbHomeIcon = new JLabel("");
		lbHomeIcon.setBounds(29, 11, 40, 40);
		lbHomeIcon.setIcon(new ImageIcon(img_home));
		panel_Home.add(lbHomeIcon);

		JLabel lbHome = new JLabel("Pagina Principala");
		lbHome.setBounds(63, 11, 185, 53);
		lbHome.setForeground(new Color(106, 90, 205));
		lbHome.setFont(new Font("DialogInput", Font.BOLD, 14));
		lbHome.setHorizontalAlignment(SwingConstants.CENTER);
		panel_Home.add(lbHome);

		JPanel panel_Resurse = new JPanel();
		panel_Resurse.addMouseListener(new PanelButtonMouseAdapter(panel_Resurse) {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(functie.equals("Medic")||functie.equals("Asistent Medical")||functie.equals("Receptioner"))
					menuClicked(meniu_depMedical_M1);
				if(functie.equals("Inspector Resurse Umane")||functie.equals("SuperAdministrator")||functie.equals("Administrator"))
					menuClicked(meniu_Inspector_M1);
				if(functie.equals("Expert financiar contabil"))
					menuClicked(meniu_ExpertFinanciar_M1);
			}
		});
		panel_Resurse.setBackground(new Color(135, 206, 250));
		panel_Resurse.setBorder(new MatteBorder(1, 2, 1, 2, (Color) new Color(0, 191, 255)));
		panel_Resurse.setBounds(43, 290, 258, 75);
		panel.add(panel_Resurse);
		panel_Resurse.setLayout(null);

		JLabel lblResurse = new JLabel("Gestiune Resurse Umane");
		lblResurse.setBounds(30, 11, 228, 53);
		lblResurse.setForeground(new Color(106, 90, 205));
		lblResurse.setHorizontalAlignment(SwingConstants.CENTER);
		lblResurse.setFont(new Font("DialogInput", Font.BOLD, 14));
		panel_Resurse.add(lblResurse);
		
		JLabel lbResurseUmane_Logo = new JLabel("");
		lbResurseUmane_Logo.setBounds(10, 15, 40, 40);
		lbResurseUmane_Logo.setIcon(new ImageIcon(img_resurse));
		panel_Resurse.add(lbResurseUmane_Logo);

		JPanel panel_Financiar = new JPanel();
		panel_Financiar.addMouseListener(new PanelButtonMouseAdapter(panel_Financiar) {
			@Override
			public void mouseClicked(MouseEvent e) {
				menuClicked(meniu_Financiar);
			}
		});
		panel_Financiar.setBackground(new Color(135, 206, 250));
		panel_Financiar.setBorder(new MatteBorder(1, 2, 1, 2, (Color) new Color(0, 191, 255)));
		panel_Financiar.setBounds(43, 365, 258, 75);
		panel.add(panel_Financiar);
		panel_Financiar.setLayout(null);

		JLabel lblFinanciar = new JLabel("Op. Financiar-contabile");
		lblFinanciar.setBounds(53, 11, 205, 53);
		lblFinanciar.setForeground(new Color(106, 90, 205));
		lblFinanciar.setHorizontalAlignment(SwingConstants.CENTER);
		lblFinanciar.setFont(new Font("DialogInput", Font.BOLD, 14));
		panel_Financiar.add(lblFinanciar);
		
		JLabel lbFinanciar = new JLabel("");
		lbFinanciar.setBounds(5, 15, 60, 40);
		lbFinanciar.setIcon(new ImageIcon(img_financiar));
		panel_Financiar.add(lbFinanciar);

		JPanel panel_Medical = new JPanel();
		panel_Medical.addMouseListener(new PanelButtonMouseAdapter(panel_Medical) {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (functie.equals("Inspector Resurse Umane") || functie.equals("Expert financiar contabil"))
					JOptionPane.showMessageDialog(meniu_Medical, "Nu aveti drept de acces !", "Acces interzis", 0);
				if(functie.equals("Medic"))
					menuClicked(meniu_Medic_M3);
				if(functie.equals("Asistent Medical"))
					menuClicked(meniu_Asistent_M3);
				if(functie.equals("Receptioner"))
					menuClicked(meniu_Receptioner_M3);
			}
		});
		panel_Medical.setBackground(new Color(135, 206, 250));
		panel_Medical.setBorder(new MatteBorder(1, 2, 2, 2, (Color) new Color(0, 191, 255)));
		panel_Medical.setBounds(43, 440, 258, 75);
		panel.add(panel_Medical);
		panel_Medical.setLayout(null);

		JLabel lblMedical = new JLabel("Modulul Medical");
		lblMedical.setBounds(53, 11, 195, 53);
		lblMedical.setBackground(new Color(147, 112, 219));
		lblMedical.setForeground(new Color(106, 90, 205));
		lblMedical.setHorizontalAlignment(SwingConstants.CENTER);
		lblMedical.setFont(new Font("DialogInput", Font.BOLD, 14));
		panel_Medical.add(lblMedical);
		
		JLabel lbMedicalLogo = new JLabel("");
		lbMedicalLogo.setBounds(21, 11, 55, 53);
		lbMedicalLogo.setIcon(new ImageIcon(img_medical));
		panel_Medical.add(lbMedicalLogo);

		JPanel panel_SignOut = new JPanel();
		panel_SignOut.addMouseListener(new PanelButtonMouseAdapter(panel_SignOut) {
			@SuppressWarnings("deprecation")
			@Override
			public void mouseClicked(MouseEvent e) {
				int aux = JOptionPane.showConfirmDialog(panelMain, "Sunteti sigur ca doriti sa va delogati?",
						"Delogare", 0);
				if (aux == 0) {
					windowMain = new PaginaLogare();
					windowMain.getFrame().show();
					frameLogare.dispose();
				}
			}
		});
		panel_SignOut.setBackground(new Color(135, 206, 250));
		panel_SignOut.setBorder(new MatteBorder(1, 2, 2, 2, (Color) new Color(0, 191, 255)));
		panel_SignOut.setBounds(43, 590, 258, 62);
		panel.add(panel_SignOut);
		panel_SignOut.setLayout(null);
		
		JLabel lbSignOutIcon = new JLabel("");
		lbSignOutIcon.setBounds(25, 13, 40, 40);
		lbSignOutIcon.setIcon(new ImageIcon(img_signout));
		panel_SignOut.add(lbSignOutIcon);

		JLabel lblSignOut = new JLabel("Delogare");
		lblSignOut.setBounds(75, 5, 162, 53);
		lblSignOut.setForeground(new Color(106, 90, 205));
		lblSignOut.setHorizontalAlignment(SwingConstants.CENTER);
		lblSignOut.setFont(new Font("DialogInput", Font.BOLD, 14));
		panel_SignOut.add(lblSignOut);

		JPanel panel_Administrare = new JPanel();
		panel_Administrare.addMouseListener(new PanelButtonMouseAdapter(panel_Administrare) {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (functie.equals("Administrator"))
					menuClicked(meniu_Administrare);
				else if (functie.equals("SuperAdministrator"))
					menuClicked(meniu_SuperAdministrare);
				else
					JOptionPane.showMessageDialog(meniu_Administrare, "Nu ai acces in aceasta sectiune!",
							"Acces interzis!", 0);
			}
		});
		panel_Administrare.setBorder(new MatteBorder(1, 2, 2, 2, (Color) new Color(0, 191, 255)));
		panel_Administrare.setBackground(new Color(135, 206, 250));
		panel_Administrare.setBounds(43, 515, 258, 75);
		panel.add(panel_Administrare);
		panel_Administrare.setLayout(null);

		JLabel lblAdministrare = new JLabel("Administrare");
		lblAdministrare.setBounds(53, 11, 195, 53);
		lblAdministrare.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdministrare.setForeground(new Color(106, 90, 205));
		lblAdministrare.setFont(new Font("DialogInput", Font.BOLD, 14));
		lblAdministrare.setBackground(new Color(147, 112, 219));
		panel_Administrare.add(lblAdministrare);
		
		JLabel lbAdministrareIcon = new JLabel("");
		lbAdministrareIcon.setBounds(25, 15, 40, 40);
		lbAdministrareIcon.setIcon(new ImageIcon(img_administrare));
		panel_Administrare.add(lbAdministrareIcon);

		JLabel lbBackgroundLeft = new JLabel("");
		lbBackgroundLeft.setBounds(0, 0, 336, 681);
		lbBackgroundLeft.setIcon(new ImageIcon(img_back2));
		panel.add(lbBackgroundLeft);

		panelMain = new JPanel();
		panelMain.setBounds(360, 25, 790, 630);
		frameLogare.getContentPane().add(panelMain);
		panelMain.setLayout(null);
		panelMain.setLayout(null);
		panelMain.add(meniu_Home);
		panelMain.add(meniu_Financiar);
		panelMain.add(meniu_Medical);
		panelMain.add(meniu_SignOut);
		panelMain.add(meniu_Administrare);
		panelMain.add(meniu_SuperAdministrare);
		panelMain.add(meniu_depMedical_M1);
		panelMain.add(meniu_Inspector_M1);
		panelMain.add(meniu_ExpertFinanciar_M1);
		panelMain.add(meniu_Receptioner_M3);
		panelMain.add(meniu_Asistent_M3);
		panelMain.add(meniu_Medic_M3);
		
		menuClicked(meniu_Home);

		JLabel lbAux = new JLabel("");
		lbAux.setBounds(330, 0, 844, 681);
		lbAux.setIcon(new ImageIcon(img_back3));
		frameLogare.getContentPane().add(lbAux);
	}

	public void menuClicked(JPanel panel) {
		meniu_Home.setVisible(false);
		meniu_Financiar.setVisible(false);
		meniu_Medical.setVisible(false);
		meniu_Administrare.setVisible(false);
		meniu_SuperAdministrare.setVisible(false);
		meniu_SignOut.setVisible(false);
		meniu_depMedical_M1.setVisible(false);
		meniu_Inspector_M1.setVisible(false);
		meniu_ExpertFinanciar_M1.setVisible(false);
		meniu_Receptioner_M3.setVisible(false);
		meniu_Medic_M3.setVisible(false);
		meniu_Asistent_M3.setVisible(false);

		panel.setVisible(true);
	}

	private class PanelButtonMouseAdapter extends MouseAdapter {
		JPanel panel;

		public PanelButtonMouseAdapter(JPanel panel) {
			this.panel = panel;
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			panel.setBackground(new Color(190, 250, 252));
		}

		@Override
		public void mouseExited(MouseEvent e) {
			panel.setBackground(new Color(135, 206, 255));
		}

		@Override
		public void mousePressed(MouseEvent e) {
			panel.setBackground(new Color(40, 234, 255));
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			panel.setBackground(new Color(190, 250, 252));
		}
	}
}
