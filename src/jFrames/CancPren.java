package jFrames;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.naming.NamingException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import candidati.Candidato;
import database.Cancellazione;

/**
 * Classe CancellazionePrenotazione
 * 
 * @author Gabriele Fortunato
 *
 */
@SuppressWarnings("serial")
public class CancPren extends JFrame {

	private final int cinque = 5;
	private final int dieci = 10;
	private final int sedici = 16;
	private final int ventidue = 22;
	private final int venticinque = 25;
	private final int trentasette = 37;
	private final int sessanta = 60;
	private final int sessantatre = 63;
	private final int ottantanove = 89;	
	private final int novantadue = 92;
	private final int cento = 100;
	private final int centoventidue = 122;
	private final int centosessantotto = 168;
	private final int centosettantaquattro = 174;
	private final int duecentotrenta = 230;
	private final int trecento = 300;
	private final int quattrocentocinquanta = 450;

	private JTextField cognome;
	private JTextField nome;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InsPren frame = new InsPren();
					frame.setVisible(true);
				} catch (Exception e) {
					Logger.getLogger("Connessione non riuscita");
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CancPren() {
		JPanel contentPane;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(cento, cento, quattrocentocinquanta, trecento);
		setTitle("INSERIMENTO PRENOTAZIONE");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(cinque, cinque, cinque, cinque));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCognomeCandidato = new JLabel("Cognome candidato: ");
		lblCognomeCandidato.setBounds(trentasette, sessantatre, centoventidue, sedici);
		contentPane.add(lblCognomeCandidato);
		
		JLabel lblNomeCandidato = new JLabel("Nome candidato: ");
		lblNomeCandidato.setBounds(trentasette, novantadue, centoventidue, sedici);
		contentPane.add(lblNomeCandidato);
		
		cognome = new JTextField();
		cognome.setBounds(duecentotrenta, sessanta, centosettantaquattro, ventidue);
		contentPane.add(cognome);
		cognome.setColumns(dieci);
		
		nome = new JTextField();
		nome.setColumns(dieci);
		nome.setBounds(duecentotrenta, ottantanove, centosettantaquattro, ventidue);
		contentPane.add(nome);
		
		JButton cog = new JButton("CONFERMA");
		cog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String n = nome.getText();
					String cg = cognome.getText();
					Candidato c = new Candidato(n,cg);
					Cancellazione.cancellaCandidato(c);
					dispose();
				} catch (NamingException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		cog.setBounds(duecentotrenta, centosessantotto, centosettantaquattro, venticinque);
		contentPane.add(cog);
	}
}

