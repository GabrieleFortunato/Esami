package jFrames;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import candidati.Candidato;
import database.Inserimento;
import eccezioni.EsitoTeoriaException;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.naming.NamingException;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;

/**
 * Classe InserimentoEsitoTeoria
 * 
 * @author Gabriele Fortunato
 *
 */
@SuppressWarnings("serial")
public class InsVotoTeoria extends JFrame {
	
	private final int cinque = 5;
	private final int dieci = 10;
	private final int sedici = 16;
	private final int ventidue = 22;
	private final int ventiquattro = 24;
	private final int venticinque = 25;
	private final int cinquantatre = 53;
	private final int cinquantasei = 56;
	private final int ottantadue = 82;	
	private final int ottantacinque = 85;
	private final int cento = 100;
	private final int centoundici = 111;
	private final int centoquattordici = 114;
	private final int centotrentaquattro = 134;
	private final int centottanta = 180;
	private final int centottantasette = 187;
	private final int duecentoquaranta = 240;
	private final int trecento = 300;
	private final int quattrocentocinquanta = 450; 
	
	private JTextField cognome;
	private JTextField nome;
	private JTextField teoria;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InsVotoTeoria frame = new InsVotoTeoria();
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
	public InsVotoTeoria() {
		JPanel contentPane;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(cento, cento, quattrocentocinquanta, trecento);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(cinque, cinque, cinque, cinque));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCognomeCandidato = new JLabel("Cognome candidato: ");
		lblCognomeCandidato.setBounds(ventiquattro, cinquantasei, centotrentaquattro, sedici);
		contentPane.add(lblCognomeCandidato);
		
		JLabel lblNomeCandidato = new JLabel("Nome candidato: ");
		lblNomeCandidato.setBounds(ventiquattro, ottantacinque, centotrentaquattro, sedici);
		contentPane.add(lblNomeCandidato);
		
		JLabel lblEsitoTeoria = new JLabel("Esito teoria: ");
		lblEsitoTeoria.setBounds(ventiquattro, centoquattordici, centotrentaquattro, sedici);
		contentPane.add(lblEsitoTeoria);
		
		cognome = new JTextField();
		cognome.setBounds(centottanta, cinquantatre, duecentoquaranta, ventidue);
		contentPane.add(cognome);
		cognome.setColumns(dieci);
		
		nome = new JTextField();
		nome.setColumns(dieci);
		nome.setBounds(centottanta, ottantadue, duecentoquaranta, ventidue);
		contentPane.add(nome);
		
		teoria = new JTextField();
		teoria.setColumns(dieci);
		teoria.setBounds(centottanta, centoundici, duecentoquaranta, ventidue);
		contentPane.add(teoria);
		
		JButton btnConferma = new JButton("CONFERMA");
		btnConferma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String name = nome.getText();
					String surname = cognome.getText();
					String theory = teoria.getText();
					Candidato c = new Candidato(name,surname,theory);
					Inserimento.inserisciEsitoTeoria(c);
					dispose();
				} catch (EsitoTeoriaException | NamingException | SQLException e1) {
					// TODO Auto-generated catch block
					
				}
			}
		});
		btnConferma.setBounds(centottanta, centottantasette, duecentoquaranta, venticinque);
		contentPane.add(btnConferma);
	}

}
