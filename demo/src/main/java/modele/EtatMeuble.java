package modele;

public enum EtatMeuble {
    NEUF("Neuf"),
    USAGÉ("Usagé"),
    ENDOMMAGÉ("Endommagé");

    private String nom;

    private EtatMeuble(String nom) {
        this.nom = nom;
    }

    public String toString() {
        return nom;
    }
}