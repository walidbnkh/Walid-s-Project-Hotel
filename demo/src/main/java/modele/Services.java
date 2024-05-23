package modele;

public enum Services {
    RESTAURATION("Restauration", 50.0),
    WIFI("Wifi", 10.0),
    SERVICE_CHAMBRE("Service chambre", 20.0),
    PISCINE("Piscine", 30.0),
    SALLE_DE_FITNESS("Salle de fitness", 25.0),
    PARKING("Parking", 15.0),
    SPA("Spa", 40.0),
    SERVICE_DE_NAVETTE("Service de navette", 35.0),
    CONCIERGERIE("Conciergerie", 20.0);

    private String nom;
    private  double prix ;

    private Services(String nom, double prix) {
        this.nom = nom;
        this.prix = prix;
    }
    

    public String getNom() {
        return nom;
    }

    public  double getPrix() {
        return prix;
    }

    public  void setPrix(double prix) {
        this.prix = prix;
    }
}
