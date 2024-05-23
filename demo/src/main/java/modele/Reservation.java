package modele;

import java.time.*;

public class Reservation implements Comparable<Reservation> {

    private LocalDate debut;
    private LocalDate fin;
    private LocalDate dateReservation;
    private boolean confirm = false;
    private Chambre chambre;
    private Client client;
    private Sejour sejour;

    public Sejour getSejour() throws SejourNoCommencerException {
        if (sejour == null)
            new SejourNoCommencerException();
        return sejour;
    }

    public void setSejour(Sejour sejour) throws SejourExsitDejaException {

        if (sejour != null)
            new SejourExsitDejaException();

        else {
            this.sejour = sejour;
            confirm = true;
        }
    }

    public Reservation(LocalDate debut, int nbJour, LocalDate dateReservation, Chambre chambre, Client client) {
        this.debut = debut;
        this.fin = debut.plusDays(nbJour);
        this.dateReservation = dateReservation;
        this.chambre = chambre;
        this.client = client;
    }

    public Reservation(LocalDate debut, LocalDate fin, LocalDate dateReservation, Chambre chambre, Client client) {
        this.debut = debut;
        this.fin = fin;
        this.dateReservation = dateReservation;
        this.chambre = chambre;
        this.client = client;
    }

    public LocalDate getDebut() {
        return debut;
    }

    public boolean isConfirm() {
        return confirm;
    }

    public LocalDate getFin() {
        return fin;
    }

    public LocalDate getDateReservation() {
        return dateReservation;
    }

    public Chambre getChambre() {
        return chambre;
    }

    public Client getClient() {
        return client;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Reservation))
            return false;
        Reservation r = (Reservation) obj;
        if ((debut.compareTo(r.getDebut()) <= 0 && fin.compareTo(r.getDebut()) >= 0)
                || (debut.compareTo(r.fin) <= 0 && fin.compareTo(r.fin) >= 0)) {
            return true;
        } else
            return false;
    }

    @Override
    public int compareTo(Reservation o) {
        if (this.equals(o)) {
            return 0;
        } else {
            if (debut.compareTo(((Reservation) o).debut) <= 0)
                return -1;
            else
                return 1;

        }

    }

    @Override
    public String toString() {

        return debut.toString();
    }

}
