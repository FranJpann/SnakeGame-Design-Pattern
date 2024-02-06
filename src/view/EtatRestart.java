package view;

public class EtatRestart implements Etat{

	private ViewCommand vc;
	
	public EtatRestart(ViewCommand vc) {
		this.vc = vc;
	}
	
	@Override
	public void play() {
		this.vc.buttonPause.setEnabled(true);
		this.vc.buttonPlay.setEnabled(false);
		this.vc.buttonRestart.setEnabled(true);
		this.vc.buttonStep.setEnabled(true);
		vc.setEtat(new EtatPlay(vc));
	}

	@Override
	public void pause() {
		vc.setEtat(new EtatPause(vc));
	}

	@Override
	public void restart() {
	}

}
