package model;

import java.util.ArrayList;
import java.util.List;

import observ.*;
import utils.AgentAction;

public abstract class Game implements Runnable, Observable, ObservateurLastKey{
	
	protected List<Observateur> observateurs = new ArrayList<>();
	protected int maxturn, turn;
	protected boolean isRunning = true;
	protected boolean finalStop = false;
	private Thread thread;
	private long time = 1000;
	
	public AgentAction lastKey;
	
	public Game(int maxturn) {
		this.maxturn = maxturn;
	}
	
	public void setTime(long time) {
		this.time = time;
	}
	
	public void init() {
		turn = 1;
		isRunning = false;
		notifierObservateurs();
		initializeGame();
	}
	
	public void step() {
		if(gameContinue() && turn < maxturn) {
			turn++;
			takeTurn();
			notifierObservateurs();
		}
		else {
			gameOver();
		}
	}
	
	public void pause() {
		isRunning = false;
	}
	
	public void run() {
		while(isRunning) {
			step();
			try {
				Thread.sleep(time);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void launch() {
		isRunning = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public abstract void initializeGame();
	public abstract void takeTurn();
	public abstract boolean gameContinue();
	public abstract void gameOver();
	
	public void enregistrerObservateur(Observateur observateur){
		observateurs.add(observateur);
		notifierObservateurs();
	}
	
	public void supprimerObservateur(Observateur observateur){
		observateurs.remove(observateur);
	}

	@Override
	public void notifierObservateurs() {
		for(int i = 0; i< observateurs.size(); i++) {
			Observateur observateur = observateurs.get(i);
			observateur.actualiser(turn, finalStop);
		}
	}
	
	public void updateLastKey(AgentAction lastKey) {
		this.lastKey = lastKey;
	}
}
