package view;

import javax.swing.*;
import observ.*;
import utils.AgentAction;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;


public class ViewSnakeGame extends JFrame implements Observateur, KeyListener, ObservableLastKey{
	private static final long serialVersionUID = 1L;
	
	private Dimension windowSize;
	public PanelSnakeGame panelSnakeGame;
	public PanelScore panelScore;
	
	public AgentAction lastKey;
	
	protected List<ObservateurLastKey> observateurs = new ArrayList<>();
	
	public ViewSnakeGame(PanelSnakeGame psg) {
		
		panelSnakeGame = psg;
		windowSize = new Dimension(panelSnakeGame.getSizeX()*30, panelSnakeGame.getSizeY()*30);
		
		this.setTitle("Snake Game");
		this.setSize(windowSize);
		
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Point positionCenter = ge.getCenterPoint();
		this.setLocation(positionCenter.x - windowSize.width / 2, positionCenter.x - windowSize.height / 2 - 550);
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.VERTICAL;
		
		this.add(panelSnakeGame);
		this.add(new PanelScore());
		this.setVisible(true);
		
		addKeyListener(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actualiser(int turn, boolean finalStop) {
		panelSnakeGame.repaint();
		if(finalStop) this.setTitle("GAME OVER !");
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		if(arg0.getKeyCode() == 38 && lastKey != AgentAction.MOVE_DOWN) {lastKey = AgentAction.MOVE_UP;}
		else if(arg0.getKeyCode() == 40 && lastKey != AgentAction.MOVE_UP) {lastKey = AgentAction.MOVE_DOWN;}
		else if(arg0.getKeyCode() == 37 && lastKey != AgentAction.MOVE_RIGHT) {lastKey = AgentAction.MOVE_LEFT;}
		else if(arg0.getKeyCode() == 39 && lastKey != AgentAction.MOVE_LEFT) {lastKey = AgentAction.MOVE_RIGHT;}
		
		notifierObservateurs();
	}

	@Override
	public void keyReleased(KeyEvent arg0) {}
	@Override
	public void keyTyped(KeyEvent arg0) {}

	public void enregistrerObservateur(ObservateurLastKey observateur){
		observateurs.add(observateur);
		notifierObservateurs();
	}
	
	public void supprimerObservateur(ObservateurLastKey observateur){
		observateurs.remove(observateur);
	}

	@Override
	public void notifierObservateurs() {
		for(int i = 0; i< observateurs.size(); i++) {
			ObservateurLastKey observateur = observateurs.get(i);
			observateur.updateLastKey(lastKey);
		}
	}
}
