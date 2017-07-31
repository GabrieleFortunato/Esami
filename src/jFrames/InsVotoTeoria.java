package jFrames;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import database.Inserimento;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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

		final int cinque = 5;
		final int dieci = 10;
		final int sedici = 16;
		final int ventidue = 22;
		final int ventiquattro = 24;
		final int venticinque = 25;
		final int cinquantatre = 53;
		final int cinquantasei = 56;
		final int ottantadue = 82;	
		final int ottantacinque = 85;
		final int cento = 100;
		final int centoundici = 111;
		final int centoquattordici = 114;
		final int centotrentaquattro = 134;
		final int centottanta = 180;
		final int centottantasette = 187;
		final int duecentoquaranta = 240;
		final int trecento = 300;
		final int quattrocentocinquanta = 450; 
		
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
					Inserimento.inserisciEsitoTeoria(name,surname,theory);
					dispose();
				} catch (NamingException | SQLException e1) {
					JOptionPane.showMessageDialog (
							null , "Impossibile inserire nel database l'esito della teoria"
					);
				}
			}
		});
		btnConferma.setBounds(centottanta, centottantasette, duecentoquaranta, venticinque);
		contentPane.add(btnConferma);
	}

}
