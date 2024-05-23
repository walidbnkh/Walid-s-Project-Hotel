package modele;

public class CaractsMeubles { 
    private Meuble meuble;
    private EtatMeuble etat;
    
    public CaractsMeubles(Meuble meuble, EtatMeuble etat) {
        this.meuble = meuble;
        this.etat = etat;
    }
    public CaractsMeubles(Meuble meuble) {
        this.meuble = meuble;
        this.etat = EtatMeuble.NEUF;
    }

    public Meuble getMeuble() {
        return meuble;
    } 
    
    public EtatMeuble getEtat() {
        return etat;
    }
    public void setEtat(EtatMeuble etat) {
        this.etat = etat;
    }
    
   
    
}
