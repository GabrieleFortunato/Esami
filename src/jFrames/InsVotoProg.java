package jFrames;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import candidati.Candidato;
import candidati.Progetto;
import database.Inserimento;
import database.Lettura;
import eccezioni.EsitoTeoriaException;
import eccezioni.VotoException;
import file.PrintOnFile;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
@SuppressWarnings("serial")
public class InsVotoProg extends JFrame {

	private final int cinque = 5;
	private final int dieci = 10;
	private final int sedici = 16;
	private final int ventidue = 22;
	private final int venticinque = 25;
	private final int trentuno = 31;
	private final int trentanove = 39;
	private final int quaranta = 40;
	private final int settantasette = 77;
	private final int cento = 100;
	private final int centosei = 106;
	private final int centonove = 109;
	private final int centosedici = 116;
	private final int centoventidue = 122;
	private final int centoventicinque = 125;
	private final int centotrentaquattro = 134;
	private final int centosessantuno = 161;
	private final int centoottantasette = 187;
	private final int duecentoquattro = 204;
	private final int duecentosette = 207;
	private final int duecentootto = 208;
	private final int duecentoventicinque = 225;
	private final int quattrocentoquarantaquattro = 444;
	private final int cinquecentoventinove = 529;
	private final int cinquecentonovantacinque = 595;
	private final int settecentoquarantuno = 741;
	
	
	private JTextField nomecandidato;
	private JTextField libreria;
	private JTextField test;
	private JTextField votoMain;
	private JTextField cognomecandidato;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			if (Lettura.daInterrogare().size()>0){
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							InsVotoProg frame = new InsVotoProg();
							frame.setVisible(true);
						} catch (Exception e) {
							Logger.getLogger("Connessione non riuscita");
						}
					}
				});
			} else {
				JOptionPane.showMessageDialog ( null, "Nessun candidato da interrogare" ) ;
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public InsVotoProg() {
		JPanel contentPane;
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
		lblVotoLibreria.setBounds(quaranta, centoottantasette, 101, sedici);
		contentPane.add(lblVotoLibreria);
		
		JLabel lblVotoTest = new JLabel("Voto test:");
		lblVotoTest.setBounds(quaranta, duecentosette, settantasette, sedici);
		contentPane.add(lblVotoTest);
		
		JLabel lblNewLabel = new JLabel("Voto main: ");
		lblNewLabel.setBounds(quaranta, duecentoventicinque, settantasette, sedici);
		contentPane.add(lblNewLabel);
		
		cognomecandidato = new JTextField();
		cognomecandidato.setBounds(duecentootto, centosei , centosedici, ventidue);
		contentPane.add(cognomecandidato);
		cognomecandidato.setColumns(dieci);
		
		nomecandidato = new JTextField();
		nomecandidato.setBounds(duecentootto, centoventidue, centosedici, ventidue);
		contentPane.add(nomecandidato);
		nomecandidato.setColumns(dieci);
		
		libreria = new JTextField();
		libreria.setBounds(duecentootto, centoottantasette, trentuno, ventidue);
		contentPane.add(libreria);
		libreria.setColumns(dieci);
		
		test = new JTextField();
		test.setBounds(duecentootto, duecentoquattro, trentuno, ventidue);
		contentPane.add(test);
		test.setColumns(dieci);
		
		votoMain = new JTextField();
		votoMain.setBounds(duecentootto, duecentoventicinque, trentuno, ventidue);
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
					Inserimento.inserisciEsitoProgetto(c,p);
					PrintOnFile.printOnFile(Lettura.interrogati());
					dispose();
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (EsitoTeoriaException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (VotoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnConferma.setBounds(cinquecentonovantacinque, quattrocentoquarantaquattro, centosedici, venticinque);
		contentPane.add(btnConferma);
	}
}
