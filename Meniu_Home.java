import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import javax.swing.JTextPane;

public class Meniu_Home extends JPanel {
	
	private Image img_back = new ImageIcon(LogareSucces.class.getResource("res/home_back.png")).getImage()
			.getScaledInstance(790, 630, Image.SCALE_SMOOTH);

	public Meniu_Home(String nume,String prenume) {
		setSize(790, 630);
		Icon imgIcon = new ImageIcon(this.getClass().getResource("res/robot.gif"));
		setLayout(null);
		JLabel label = new JLabel(imgIcon);
		label.setBounds(490, 0, 300, 300);
		add(label);
		
		JLabel lblmesaj = new JLabel("Bine ai venit " + nume + " " + prenume + "!");
		lblmesaj.setForeground(new Color(0, 255, 255));
		lblmesaj.setFont(new Font("DialogInput", Font.BOLD, 24));
		lblmesaj.setHorizontalAlignment(SwingConstants.CENTER);
		lblmesaj.setBounds(43, 34, 502, 63);
		add(lblmesaj);
		
		JLabel lbltxt1 = new JLabel("In partea stanga gasiti meniurile disponibile aplicatiei noastre .");
		lbltxt1.setForeground(new Color(224, 255, 255));
		lbltxt1.setFont(new Font("DialogInput", Font.BOLD, 18));
		lbltxt1.setBounds(27, 333, 740, 93);
		add(lbltxt1);
		
		JLabel lbltxt2 = new JLabel("Pentru a accesa unul dintre acestea doar apasati click pe meniul dorit!");
		lbltxt2.setForeground(new Color(224, 255, 255));
		lbltxt2.setFont(new Font("DialogInput", Font.BOLD, 17));
		lbltxt2.setBounds(39, 413, 740, 93);
		add(lbltxt2);
		
		JLabel lbBackground = new JLabel("");
		lbBackground.setBounds(0, 0, 790, 630);
		lbBackground.setIcon(new ImageIcon(img_back));
		add(lbBackground);
	}
}
