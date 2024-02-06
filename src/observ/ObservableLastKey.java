package observ;

public interface ObservableLastKey {
	public void enregistrerObservateur(ObservateurLastKey observateur);
    public void supprimerObservateur(ObservateurLastKey observer);
    public void notifierObservateurs();
}
