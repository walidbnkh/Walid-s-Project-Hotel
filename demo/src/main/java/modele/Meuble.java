package modele;

public enum Meuble {
    LIT("Lit"),
    TABLE_DE_CHEVET("Table de chevet"),
    TABLE("Table"),
    MINI_BAR("Mini bar"),
    REFRIGIRATEUR("Réfrigérateur"),
    TELEVISION("Télévision"),
    FAUTEUIL("Fauteuil");

    private String nom;

    private Meuble(String nom) {
        this.nom = nom;
    }

    public String toString() {
        return nom;
    }
}
