package jFrames;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import candidati.Candidato;
import database.Inserimento;
import eccezioni.EsitoTeoriaException;
import eccezioni.VotoException;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

/**
 * Classe InserimentoEsitoTeoria
 * 
 * @author Gabriele Fortunato
 *
 */
@SuppressWarnings("serial")
public class InsVotoTeoria extends JFrame {
	private JTextField teoria;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				InsVotoTeoria frame = new InsVotoTeoria();
				frame.setVisible(true);
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

		try {
			JPanel contentPane;
			ArrayList<Candidato> candidati  = new ArrayList<>(database.Lettura.candidatoInserireTeoria());
			if (!candidati.isEmpty()) {
				setTitle(candidati.get(0).getCognome()+" "+candidati.get(0).getNome());
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				setBounds(cento, cento, 411, 279);
				contentPane = new JPanel();
				contentPane.setBorder(new EmptyBorder(cinque, cinque, cinque, cinque));
				setContentPane(contentPane);
				contentPane.setLayout(null);
				
				JLabel lblEsitoTeoria = new JLabel("Esito teoria: ");
				lblEsitoTeoria.setBounds(27, 79, centotrentaquattro, sedici);
				contentPane.add(lblEsitoTeoria);
				
				teoria = new JTextField();
				teoria.setColumns(dieci);
				teoria.setBounds(158, 76, duecentoquaranta, ventidue);
				contentPane.add(teoria);
				JButton btnConferma = new JButton("CONFERMA");
				btnConferma.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String theory = teoria.getText();
						String name = candidati.get(0).getNome();
						String surname = candidati.get(0).getCognome();
						Inserimento.inserisciEsitoTeoria(name,surname,theory);
						dispose();
					}
				});
				btnConferma.setBounds(158, 139, 240, 25);
				contentPane.add(btnConferma);
			}
		} catch (VotoException | EsitoTeoriaException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

}
