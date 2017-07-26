package jFrames;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import candidati.Candidato;
import candidati.Progetto;
import database.Inserimento;
import eccezioni.VotoNonValidoException;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;

/**
 * Classe InserimentoEsitoProgetto
 * 
 * @author Gabriele Fortunato
 *
 */
public class InserimentoEsitoProgetto extends JFrame {


	private final int cinque = 5;
	private final int dieci = 10;
	private final int sedici = 16;
	private final int ventidue = 22;
	private final int venticinque = 25;
	private final int trentasette = 37;
	private final int trentanove = 39;
	private final int quaranta = 40;
	private final int sessanta = 60;
	private final int sessantatre = 63;
	private final int ottantanove = 89;	
	private final int novantadue = 92;
	private final int cento = 100;
	private final int centonove = 109;
	private final int centoventidue = 122;
	private final int centoventicinque = 125;
	private final int centotrentaquattro = 134;
	private final int centosessantuno = 161;
	private final int centosessantotto = 168;
	private final int centosettantaquattro = 174;
	private final int duecentootto = 208;
	private final int duecentotrenta = 230;
	private final int trecento = 300;
	private final int quattrocentocinquanta = 450;
	private final int cinquecentoventinove = 529;
	private final int settecentoquarantuno = 741;
	
	private JPanel contentPane;
	private JTextField nomecandidato;
	private JTextField libreria;
	private JTextField test;
	private JTextField votoMain;
	private JTextField cognomecandidato;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InserimentoEsitoProgetto frame = new InserimentoEsitoProgetto();
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
	public InserimentoEsitoProgetto() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(cento, cento, settecentoquarantuno, cinquecentoventinove);
		setTitle("INSERIMENTO VOTO PROGETTO");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(cinque, cinque, cinque, cinque));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCognome = new JLabel("Cognome candidato:");
		lblCognome.setBounds(quaranta, centonove, centosessantuno, sedici);
		contentPane.add(lblCognome);
		
		JLabel lblNomeCandidato = new JLabel("Nome candidato:");
		lblNomeCandidato.setBounds(trentanove, centoventicinque, centotrentaquattro, sedici);
		contentPane.add(lblNomeCandidato);
		
		JLabel lblVotoLibreria = new JLabel("Voto libreria:");
		lblVotoLibreria.setBounds(40, 187, 101, 16);
		contentPane.add(lblVotoLibreria);
		
		JLabel lblVotoTest = new JLabel("Voto test:");
		lblVotoTest.setBounds(40, 207, 77, 16);
		contentPane.add(lblVotoTest);
		
		JLabel lblNewLabel = new JLabel("Voto main: ");
		lblNewLabel.setBounds(40, 225, 77, 16);
		contentPane.add(lblNewLabel);
		
		cognomecandidato = new JTextField();
		cognomecandidato.setBounds(208, 106, 116, 22);
		contentPane.add(cognomecandidato);
		cognomecandidato.setColumns(10);
		
		nomecandidato = new JTextField();
		nomecandidato.setBounds(208, 122, 116, 22);
		contentPane.add(nomecandidato);
		nomecandidato.setColumns(10);
		
		libreria = new JTextField();
		libreria.setBounds(duecentootto, 187, 31, ventidue);
		contentPane.add(libreria);
		libreria.setColumns(dieci);
		
		test = new JTextField();
		test.setBounds(duecentootto, 204, 31, ventidue);
		contentPane.add(test);
		test.setColumns(dieci);
		
		votoMain = new JTextField();
		votoMain.setBounds(duecentootto, 225, 31, ventidue);
		contentPane.add(votoMain);
		votoMain.setColumns(dieci);
		
		JButton btnConferma = new JButton("CONFERMA");
		btnConferma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String cognome = cognomecandidato.getText();
					String nome = nomecandidato.getText();
					int libr = Integer.parseInt(libreria.getText());
					int text = Integer.parseInt(test.getText());
					int fmain = Integer.parseInt(votoMain.getText());
					Candidato c = new Candidato(nome,cognome);
					Progetto p = new Progetto(libr,text,fmain);
					try {
						Inserimento.inserisciEsitoProgetto(c,p);
					} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
							| SQLException e) {
						Logger.getLogger("Connessione al database non riuscita");
					}
					dispose();
				} catch (VotoNonValidoException e) {
					Logger.getLogger("Voto non valido");
				}
			}
		});
		btnConferma.setBounds(595, 444, 116, 25);
		contentPane.add(btnConferma);
	}
}
