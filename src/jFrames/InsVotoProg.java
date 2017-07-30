package jFrames;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import database.Inserimento;
import database.Lettura;
import eccezioni.EsitoTeoriaException;
import eccezioni.VotoException;
import file.PrintOnFile;
import utility.Utility;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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
					InsVotoProg frame = new InsVotoProg();
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
	public InsVotoProg() {
		final int cinque = 5;
		final int dieci = 10;
		final int sedici = 16;
		final int ventidue = 22;
		final int venticinque = 25;
		final int trentuno = 31;
		final int trentanove = 39;
		final int quaranta = 40;
		final int settantasette = 77;
		final int cento = 100;
		final int centouno = 101;
		final int centosei = 106;
		final int centonove = 109;
		final int centosedici = 116;
		final int centoventidue = 122;
		final int centoventicinque = 125;
		final int centotrentaquattro = 134;
		final int centosessantuno = 161;
		final int centoottantasette = 187;
		final int duecentoquattro = 204;
		final int duecentosette = 207;
		final int duecentootto = 208;
		final int duecentoventicinque = 225;
		final int quattrocentoquarantaquattro = 444;
		final int cinquecentoventinove = 529;
		final int cinquecentonovantacinque = 595;
		final int settecentoquarantuno = 741;
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
		lblVotoLibreria.setBounds(quaranta, centoottantasette, centouno, sedici);
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
					Utility.scriviSuFile(cognome);
					Utility.scriviSuFile(nome);
					Utility.scriviSuFile(libr);
					Utility.scriviSuFile(text);
					Utility.scriviSuFile(fmain);
					FileReader a = new FileReader(nome+".txt");
					FileReader b = new FileReader(cognome+".txt");
					FileReader c1 = new FileReader(libr+".txt");
					FileReader d = new FileReader(text+".txt");
					FileReader e = new FileReader(fmain+".txt");
					Inserimento.inserisciEsitoProgetto(
							Utility.stringa(a),Utility.stringa(b),Utility.stringa(c1),
							Utility.stringa(d),Utility.stringa(e)
					);
					a.close();
					b.close();
					c1.close();
					d.close();
					e.close();
					new File(nome+".txt").delete();
					new File(cognome+".txt").delete();
					new File(libr+".txt").delete();
					new File(test+".txt").delete();
					new File(fmain+".txt").delete();
					PrintOnFile.printOnFile(Lettura.interrogati());
				} catch (NumberFormatException | InstantiationException | 
						IllegalAccessException | ClassNotFoundException | VotoException | 
						SQLException | IOException | EsitoTeoriaException e) {
					
				}
			}
		});
		btnConferma.setBounds(cinquecentonovantacinque, quattrocentoquarantaquattro, centosedici, venticinque);
		contentPane.add(btnConferma);
	}
}
