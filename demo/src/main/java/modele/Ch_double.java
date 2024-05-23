package modele;

public class Ch_double extends Chambre {
    public Ch_double(int num, int etage, float surface, EtatChambre etat) {
        super(num, etage, surface, etat);

    }

    public Ch_double(int num, int etage, float surface) {
        super(num, etage, surface);
    }

    public Ch_double(int num, int etage, float surface, boolean avecBalcon, EtatChambre etat) {
        super(num, etage, surface, avecBalcon, etat);

    }

    public Ch_double(int num, int etage, float surface, boolean avecBalcon) {
        super(num, etage, surface, avecBalcon);
    }

    public Ch_double(int num, int etage, float surface, boolean avecBalcon, CaractsMeubles... M) {
        super(num, etage, surface, avecBalcon, M);
    }

    public Ch_double(int num, int etage, float surface, boolean avecBalcon, Meuble... M) {
        super(num, etage, surface, avecBalcon, M);
    }

    public static double prix = 140;

    public static double getPrix() {
        return prix;
    }

}
