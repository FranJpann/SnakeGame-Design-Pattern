package view;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import observ.*;
import controller.AbstractController;

public class ViewCommand extends JFrame implements Observateur{
	private static final long serialVersionUID = 1L;
	
	private Dimension windowSize = new Dimension(600, 250);
	private JLabel labelTurn;
	private Etat etat;
	
	public JButton buttonRestart;
	public JButton buttonPlay;
	public JButton buttonStep;
	public JButton buttonPause;
	
	public void setEtat(Etat etat) {
		this.etat = etat;
	}
	
	public ViewCommand(AbstractController controller) {
		createUserFrame(controller);
	}
	
	@Override
	public void actualiser(int turn, boolean finalStop) {
		labelTurn.setText("Turn : "+turn);
		if(finalStop) onlyRestart();
	}
	
	public void onlyRestart() {
		this.etat = new EtatOver(this);
	}
	
	public void createUserFrame(AbstractController controller) {
		
		etat = new EtatPause(this);
		
		this.setTitle("Commande");
		this.setSize(windowSize);
		
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Point positionCenter = ge.getCenterPoint();
		this.setLocation(positionCenter.x - windowSize.width / 2, positionCenter.x - windowSize.height / 2 - 150);
		
		/*--------------*/
		/*	Panel haut	*/
		
		JPanel pannelHaut = new JPanel();
		
			/*		Boutons		*/
		
		buttonRestart = new JButton(new ImageIcon("icons/icon_restart.png"));
			buttonRestart.setEnabled(false);
		buttonPlay = new JButton(new ImageIcon("icons/icon_play.png"));
		buttonStep = new JButton(new ImageIcon("icons/icon_step.png"));
		buttonPause = new JButton(new ImageIcon("icons/icon_pause.png"));
			buttonPause.setEnabled(false);
			
		buttonRestart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evenement) {
				try {
					controller.restart();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		buttonPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evenement) {
				controller.play();
				etat.play();
			}
		});
		buttonStep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evenement) {
				controller.step();
			}
		});
		buttonPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evenement) {
				controller.pause();
				etat.pause();
			}
		});
			
		pannelHaut.add(buttonRestart);
		pannelHaut.add(buttonPlay);
		pannelHaut.add(buttonStep);
		pannelHaut.add(buttonPause);
		pannelHaut.setLayout(new GridLayout(1,4));
		
		/*--------------*/
		/*	Panel bas	*/
		
		JPanel pannelBas = new JPanel();
		
			JPanel pannelBasGauche = new JPanel();
				JLabel labelSlider = new JLabel("Number of turn per second", JLabel.CENTER);
				JSlider sliderSpeed = new JSlider(1, 10, 2);
					sliderSpeed.setMajorTickSpacing(1);
					sliderSpeed.setPaintTicks(true);
					sliderSpeed.setPaintLabels(true);
					
					sliderSpeed.addChangeListener(new ChangeListener() {
						public void stateChanged(ChangeEvent evenement) {
							controller.setSpeed((long)sliderSpeed.getValue());
						}
					});
					
				pannelBasGauche.add(labelSlider);
				pannelBasGauche.add(sliderSpeed);
				pannelBasGauche.setLayout(new GridLayout(2,1));
				
			labelTurn = new JLabel("Turn : 0", JLabel.CENTER);
			
			pannelBas.add(pannelBasGauche);
			pannelBas.add(labelTurn);
			pannelBas.setLayout(new GridLayout(1,2));
		
		/*--------------*/
			
		this.add(pannelHaut);
		this.add(pannelBas);
		this.setLayout(new GridLayout(2,1));
		this.setVisible(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}