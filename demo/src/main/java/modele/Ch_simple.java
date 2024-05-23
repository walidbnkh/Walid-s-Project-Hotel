package modele;

public class Ch_simple extends Chambre {
    private static double prix = 80;

    public Ch_simple(int num, int etage, float surface, EtatChambre etat) {
        super(num, etage, surface, etat);

    }

    public Ch_simple(int num, int etage, float surface) {
        super(num, etage, surface);
    }

    public Ch_simple(int num, int etage, float surface, boolean avecBalcon, EtatChambre etat) {
        super(num, etage, surface, avecBalcon, etat);

    }

    public Ch_simple(int num, int etage, float surface, boolean avecBalcon) {
        super(num, etage, surface, avecBalcon);
    }

    public Ch_simple(int num, int etage, float surface, boolean avecBalcon, CaractsMeubles... M) {
        super(num, etage, surface, avecBalcon, M);
    }

    public Ch_simple(int num, int etage, float surface, boolean avecBalcon, Meuble... M) {
        super(num, etage, surface, avecBalcon, M);
    }

    public static double getPrix() {
        return prix;
    }

}
