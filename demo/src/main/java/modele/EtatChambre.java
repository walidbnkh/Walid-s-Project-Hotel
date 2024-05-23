package modele;

public enum EtatChambre {
  
    EN_ATTENTE_DE_NETTOYAGE("En attente de nettoyage"),
    MAINTENANCE("Maintenance"),
    HORS_SERVICE("Hors service"),
    DISPONIBLE("Disponible");
    private String nom;

    // Constructor privado
    private EtatChambre(String nom) {
        this.nom = nom;
    }

    public String toString() {
        return nom;
    }
}
