package jFrames;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import candidati.Candidato;
import candidati.Progetto;
import database.InserimentoNelDatabase;
import eccezioni.VotoNonValidoEccezione;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;

/**
 * Classe InserimentoEsitoProgetto
 * 
 * @author Gabriele Fortunato
 *
 */
public class InserimentoEsitoProgetto extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nomecandidato;
	private JTextField libreria;
	private JTextField test;
	private JTextField main;
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
		setBounds(100, 100, 741, 529);
		setTitle("INSERIMENTO VOTO PROGETTO");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCognome = new JLabel("Cognome candidato:");
		lblCognome.setBounds(40, 109, 161, 16);
		contentPane.add(lblCognome);
		
		JLabel lblNomeCandidato = new JLabel("Nome candidato:");
		lblNomeCandidato.setBounds(39, 125, 134, 16);
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
		libreria.setBounds(208, 187, 31, 22);
		contentPane.add(libreria);
		libreria.setColumns(10);
		
		test = new JTextField();
		test.setBounds(208, 204, 31, 22);
		contentPane.add(test);
		test.setColumns(10);
		
		main = new JTextField();
		main.setBounds(208, 225, 31, 22);
		contentPane.add(main);
		main.setColumns(10);
		
		JButton btnConferma = new JButton("CONFERMA");
		btnConferma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String cognome = cognomecandidato.getText();
					String nome = nomecandidato.getText();
					int libr = Integer.parseInt(libreria.getText());
					int text = Integer.parseInt(test.getText());
					int fmain = Integer.parseInt(main.getText());
					Candidato c = new Candidato(nome,cognome);
					Progetto p = new Progetto(libr,text,fmain);
					InserimentoNelDatabase.inserisciEsitoProgetto(c,p);
					dispose();
				} catch (VotoNonValidoEccezione e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnConferma.setBounds(595, 444, 116, 25);
		contentPane.add(btnConferma);
	}
}
