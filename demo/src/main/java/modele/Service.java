package modele;

public class Service {
    private int quantite=1;
    private Services service;

    
    public Service(int quantite, Services service) {
        this.quantite = quantite;
        this.service = service;
    }
    public Service(Services service) {
        this.service = service;
    }

    public int getQuantite() {
        return quantite;
    }
    public Services getService() {
        return service;
    }
    
    
}
