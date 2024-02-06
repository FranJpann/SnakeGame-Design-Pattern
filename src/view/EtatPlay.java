package view;

public class EtatPlay implements Etat{

	private ViewCommand vc;
	
	public EtatPlay(ViewCommand vc) {
		this.vc = vc;
	}
	
	@Override
	public void play() {
	}

	@Override
	public void pause() {
		vc.buttonPause.setEnabled(false);
		vc.buttonPlay.setEnabled(true);
		vc.buttonRestart.setEnabled(true);
		vc.buttonStep.setEnabled(true);
		vc.setEtat(new EtatPause(vc));
	}

	@Override
	public void restart() {
		this.vc.buttonPause.setEnabled(false);
		this.vc.buttonPlay.setEnabled(true);
		this.vc.buttonRestart.setEnabled(false);
		this.vc.buttonStep.setEnabled(true);
		vc.setEtat(new EtatRestart(vc));
	}
}
