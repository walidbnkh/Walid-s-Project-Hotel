package modele;

public class Suite_presidentionnelle extends Ch_double {
    private static double prix = 500;

    public static double getPrix() {
        return prix;
    }

    public Suite_presidentionnelle(int num, int etage, float surface, EtatChambre etat) {
        super(num, etage, surface, etat);

    }

    public Suite_presidentionnelle(int num, int etage, float surface) {
        super(num, etage, surface);
    }

    public Suite_presidentionnelle(int num, int etage, float surface, boolean avecBalcon, EtatChambre etat) {
        super(num, etage, surface, avecBalcon, etat);

    }

    public Suite_presidentionnelle(int num, int etage, float surface, boolean avecBalcon) {
        super(num, etage, surface, avecBalcon);
    }

    public Suite_presidentionnelle(int num, int etage, float surface, boolean avecBalcon, CaractsMeubles... M) {
        super(num, etage, surface, avecBalcon, M);
    }

    public Suite_presidentionnelle(int num, int etage, float surface, boolean avecBalcon, Meuble... M) {
        super(num, etage, surface, avecBalcon, M);
    }

}
