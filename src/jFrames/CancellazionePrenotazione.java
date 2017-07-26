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
public class CancellazionePrenotazione extends JFrame {

	private JPanel contentPane;
	private JTextField cognome;
	private JTextField nome;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InserimentoPrenotazione frame = new InserimentoPrenotazione();
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
	public CancellazionePrenotazione() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setTitle("INSERIMENTO PRENOTAZIONE");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCognomeCandidato = new JLabel("Cognome candidato: ");
		lblCognomeCandidato.setBounds(37, 63, 122, 16);
		contentPane.add(lblCognomeCandidato);
		
		JLabel lblNomeCandidato = new JLabel("Nome candidato: ");
		lblNomeCandidato.setBounds(37, 92, 122, 16);
		contentPane.add(lblNomeCandidato);
		
		cognome = new JTextField();
		cognome.setBounds(230, 60, 174, 22);
		contentPane.add(cognome);
		cognome.setColumns(10);
		
		nome = new JTextField();
		nome.setColumns(10);
		nome.setBounds(230, 89, 174, 22);
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
		cog.setBounds(230, 168, 174, 25);
		contentPane.add(cog);
	}
}

