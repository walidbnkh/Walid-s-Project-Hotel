package modele;

public enum Allergie {
    ALLERGIE_ALIMENTAIRE("Allergie alimentaire"),
    ALLERGIE_AUX_ACARIENS("Allergie aux acariens"),
    ALLERGIE_AUX_ANIMAUX_DOMESTIQUES("Allergie aux animaux domestiques"),
    ALLERGIE_AUX_MOISISSURES("Allergie aux moisissures"),
    ALLERGIE_AUX_PARFUMS_ET_PRODUITS_CHIMIQUES("Allergie aux parfums et produits chimiques"),
    ALLERGIE_AUX_PIQURES_D_INSECTES("Allergie aux piqûres d'insectes");

    private String nom;

    private Allergie(String nom) {
        this.nom = nom;
    }

    public String toString() {
        return nom;
    }
}