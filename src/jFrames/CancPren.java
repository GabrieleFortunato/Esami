package jFrames;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Logger;

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
		int cinque = 5;
		int dieci = 10;
		int sedici = 16;
		int ventidue = 22;
		int venticinque = 25;
		int trentasette = 37;
		int sessanta = 60;
		int sessantatre = 63;
		int ottantanove = 89;	
		int novantadue = 92;
		int cento = 100;
		int centoventidue = 122;
		int centosessantotto = 168;
		int centosettantaquattro = 174;
		int duecentotrenta = 230;
		int trecento = 300;
		int quattrocentocinquanta = 450;
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
				String n = nome.getText();
				String cg = cognome.getText();
				Candidato c = new Candidato(n,cg);
				try {
					Cancellazione.cancellaCandidato(c);
				} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | 
						SQLException e1) {
					Logger.getLogger("Connessione al database non riuscita");
				}
				dispose();
			}
		});
		cog.setBounds(duecentotrenta, centosessantotto, centosettantaquattro, venticinque);
		contentPane.add(cog);
	}
}

