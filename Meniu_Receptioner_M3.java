
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Meniu_Receptioner_M3 extends JPanel {
	private JTextField id_medic;
	private JTextField nume_p;
	private JTextField prenume_p;
	private JTextField ora;
	private JTextField zi;
	private JTextField durata;
	// Conexiune Baza de date
	private MySQL_Connect conexiuneDB;
	private Connection con;
	Statement selectStatement = null;
	ResultSet rs = null, rs2 = null;

	public Meniu_Receptioner_M3(String user) {
		setBackground(new Color(135, 206, 235));
		setSize(790, 630);
		setLayout(null);

		JLabel lblProgramare = new JLabel("Inregistrare Programare");
		lblProgramare.setFont(new Font("DialogInput", Font.BOLD, 18));
		lblProgramare.setBounds(272, 48, 280, 44);
		add(lblProgramare);

		JLabel lblNewLabel = new JLabel("ID Medic");
		lblNewLabel.setFont(new Font("DialogInput", Font.BOLD, 16));
		lblNewLabel.setBounds(75, 168, 148, 44);
		add(lblNewLabel);

		JLabel lblNumePacient = new JLabel("Nume Pacient");
		lblNumePacient.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNumePacient.setBounds(75, 246, 148, 44);
		add(lblNumePacient);

		JLabel lblPrenumePacient = new JLabel("Prenume Pacient");
		lblPrenumePacient.setFont(new Font("Dialog", Font.BOLD, 16));
		lblPrenumePacient.setBounds(75, 320, 148, 44);
		add(lblPrenumePacient);

		JLabel lblOra = new JLabel("Ora");
		lblOra.setFont(new Font("DialogInput", Font.BOLD, 16));
		lblOra.setBounds(75, 393, 148, 44);
		add(lblOra);

		JLabel lblZi = new JLabel("Zi");
		lblZi.setFont(new Font("Dialog", Font.BOLD, 16));
		lblZi.setBounds(75, 469, 148, 44);
		add(lblZi);

		JLabel lblDurata = new JLabel("Durata");
		lblDurata.setFont(new Font("DialogInput", Font.BOLD, 16));
		lblDurata.setBounds(75, 539, 148, 44);
		add(lblDurata);

		id_medic = new JTextField();
		id_medic.setBounds(251, 168, 148, 44);
		add(id_medic);
		id_medic.setColumns(10);

		nume_p = new JTextField();
		nume_p.setColumns(10);
		nume_p.setBounds(251, 246, 148, 44);
		add(nume_p);

		prenume_p = new JTextField();
		prenume_p.setColumns(10);
		prenume_p.setBounds(251, 320, 148, 44);
		add(prenume_p);

		ora = new JTextField();
		ora.setColumns(10);
		ora.setBounds(251, 393, 148, 44);
		add(ora);

		zi = new JTextField();
		zi.setColumns(10);
		zi.setBounds(251, 469, 148, 44);
		add(zi);

		durata = new JTextField();
		durata.setColumns(10);
		durata.setBounds(251, 539, 148, 44);
		add(durata);

		Button butonProgramare = new Button("Fa programare");
		butonProgramare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					conexiuneDB = new MySQL_Connect();
					con = conexiuneDB.getConnection();
					selectStatement = con.createStatement();
					String sql = "INSERT INTO programare(id_medic,nume_pacient,prenume_pacient,ora,zi,durata) VALUES ('" +id_medic+"','"+nume_p+"','" + prenume_p + "', '" + ora + "','" + zi + "','" + durata + "';";
					selectStatement.executeUpdate(sql);
					JOptionPane.showMessageDialog(butonProgramare, "Inregistrarea a fost cu succes");
					con.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		butonProgramare.setBounds(536, 503, 165, 64);
		add(butonProgramare);

	}

}
