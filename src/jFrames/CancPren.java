package jFrames;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import database.Cancellazione;

/**
 * Classe CancellazionePrenotazione
 * 
 * @author Gabriele Fortunato
 *
 */
@SuppressWarnings("serial")
public class CancPren extends JFrame {

	private final int cinque = 2;
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
				InsPren frame = new InsPren();
				frame.setVisible(true);
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
				String n = nome.getText();
				String cg = cognome.getText();
				Cancellazione.cancellaCandidato(n,cg);
				dispose();
			}
		});
		cog.setBounds(duecentotrenta, centosessantotto, centosettantaquattro, venticinque);
		contentPane.add(cog);
	}
}

