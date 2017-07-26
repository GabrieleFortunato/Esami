package jFrames;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import candidati.Candidato;
import database.InserimentoNelDatabase;
import eccezioni.EsitoTeoriaEccezione;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;

/**
 * Classe InserimentoEsitoTeoria
 * 
 * @author Gabriele Fortunato
 *
 */
public class InserimentoEsitoTeoria extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
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
					InserimentoEsitoTeoria frame = new InserimentoEsitoTeoria();
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
	public InserimentoEsitoTeoria() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCognomeCandidato = new JLabel("Cognome candidato: ");
		lblCognomeCandidato.setBounds(24, 56, 134, 16);
		contentPane.add(lblCognomeCandidato);
		
		JLabel lblNomeCandidato = new JLabel("Nome candidato: ");
		lblNomeCandidato.setBounds(24, 85, 134, 16);
		contentPane.add(lblNomeCandidato);
		
		JLabel lblEsitoTeoria = new JLabel("Esito teoria: ");
		lblEsitoTeoria.setBounds(24, 114, 134, 16);
		contentPane.add(lblEsitoTeoria);
		
		cognome = new JTextField();
		cognome.setBounds(180, 53, 240, 22);
		contentPane.add(cognome);
		cognome.setColumns(10);
		
		nome = new JTextField();
		nome.setColumns(10);
		nome.setBounds(180, 82, 240, 22);
		contentPane.add(nome);
		
		teoria = new JTextField();
		teoria.setColumns(10);
		teoria.setBounds(180, 111, 240, 22);
		contentPane.add(teoria);
		
		JButton btnConferma = new JButton("CONFERMA");
		btnConferma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String name = nome.getText();
					String surname = cognome.getText();
					String theory = teoria.getText();
					Candidato c = new Candidato(name,surname,theory);
					InserimentoNelDatabase.inserisciEsitoTeoria(c);
					dispose();
				} catch (EsitoTeoriaEccezione e1) {
					Logger.getLogger("Connessione non riuscita");
				}
			}
		});
		btnConferma.setBounds(180, 187, 240, 25);
		contentPane.add(btnConferma);
	}

}
