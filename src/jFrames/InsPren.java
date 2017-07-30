package jFrames;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import candidati.Candidato;
import database.Inserimento;
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
 * Classe InserimentoTeoria
 * 
 * @author Gabriele Fortunato
 *
 */
@SuppressWarnings("serial")
public class InsPren extends JFrame {
	
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
	public InsPren() {
		final int cinque = 5;
		final int dieci = 10;
		final int sedici = 16;
		final int ventidue = 22;
		final int venticinque = 25;
		final int trentasette = 37;
		final int sessanta = 60;
		final int sessantatre = 63;
		final int ottantanove = 89;	
		final int novantadue = 92;
		final int cento = 100;
		final int centoventidue = 122;
		final int centosessantotto = 168;
		final int centosettantaquattro = 174;
		final int duecentotrenta = 230;
		final int trecento = 300;
		final int quattrocentocinquanta = 450;
		JPanel contentPane;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(cento,cento,quattrocentocinquanta,trecento);
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
					String cogn = cognome.getText();
					String nom = nome.getText();
					Candidato c = new Candidato(nom,cogn);
					Utility.scriviSuFile(c.getCognome());
					Utility.scriviSuFile(c.getNome());
					FileReader a = new FileReader(c.getNome()+".txt");
					FileReader b = new FileReader(c.getCognome()+".txt");
					Inserimento.inserisciPrenotazione(Utility.stringa(a),Utility.stringa(b));
					a.close();
					b.close();
					new File(c.getNome()+".txt").delete();
					new File(c.getCognome()+".txt").delete();
					dispose();
				} catch (InstantiationException | IllegalAccessException | 
						ClassNotFoundException | SQLException | IOException e1) {
				}
			}
		});
		cog.setBounds(duecentotrenta, centosessantotto, centosettantaquattro, venticinque);
		contentPane.add(cog);
	}
}
