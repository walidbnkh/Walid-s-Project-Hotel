package modele;

public class Suite_norml extends Ch_double {
    public static double prix = 220;

    public static double getPrix() {
        return prix;
    }

    public Suite_norml(int num, int etage, float surface, EtatChambre etat) {
        super(num, etage, surface, etat);

    }

    public Suite_norml(int num, int etage, float surface) {
        super(num, etage, surface);
    }

    public Suite_norml(int num, int etage, float surface, boolean avecBalcon, EtatChambre etat) {
        super(num, etage, surface, avecBalcon, etat);

    }

    public Suite_norml(int num, int etage, float surface, boolean avecBalcon) {
        super(num, etage, surface, avecBalcon);
    }

    public Suite_norml(int num, int etage, float surface, boolean avecBalcon, CaractsMeubles... M) {
        super(num, etage, surface, avecBalcon, M);
    }

    public Suite_norml(int num, int etage, float surface, boolean avecBalcon, Meuble... M) {
        super(num, etage, surface, avecBalcon, M);
    }

}
