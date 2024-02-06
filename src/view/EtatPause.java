package view;

public class EtatPause implements Etat{

	private ViewCommand vc;
	
	public EtatPause(ViewCommand vc) {
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
