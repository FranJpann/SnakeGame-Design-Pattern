package view;

public class EtatOver implements Etat{

	
	public EtatOver(ViewCommand vc) {
		vc.buttonPause.setEnabled(false);
		vc.buttonPlay.setEnabled(false);
		vc.buttonStep.setEnabled(false);
		vc.buttonRestart.setEnabled(true);
	}
	
	@Override
	public void restart() {
	}

	@Override
	public void play() {
	}

	@Override
	public void pause() {
	}

}
