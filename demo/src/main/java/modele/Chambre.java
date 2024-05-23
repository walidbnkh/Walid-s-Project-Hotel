package modele;

import java.util.*;

import java.time.*;

public abstract class Chambre {

    private int num;
    private int etage;
    private float surface;
    private boolean AvecBalcon;
    private EtatChambre etat;
    private TreeMap<LocalDate, Reservation> reservations = new TreeMap<LocalDate, Reservation>();

    private List<CaractsMeubles> meubles = new ArrayList<CaractsMeubles>();

    public Chambre(int num, int etage, float surface, EtatChambre etat) {
        this.num = num;
        this.etage = etage;
        this.surface = surface;
        this.etat = etat;
    }

    public Chambre(int num, int etage, float surface) {
        this.num = num;
        this.etage = etage;
        this.surface = surface;
        this.etat = EtatChambre.DISPONIBLE;
    }

    public Chambre(int num, int etage, float surface, boolean avecBalcon, EtatChambre etat) {
        this.num = num;
        this.etage = etage;
        this.surface = surface;
        AvecBalcon = avecBalcon;
        this.etat = etat;
    }

    public Chambre(int num, int etage, float surface, boolean avecBalcon) {
        this.num = num;
        this.etage = etage;
        this.surface = surface;
        AvecBalcon = avecBalcon;
        this.etat = EtatChambre.DISPONIBLE;
    }

    public Chambre(int num, int etage, float surface, boolean avecBalcon, CaractsMeubles... M) {
        this.num = num;
        this.etage = etage;
        this.surface = surface;
        AvecBalcon = avecBalcon;
        this.etat = EtatChambre.DISPONIBLE;
        for (CaractsMeubles mb : M) {
            (this.meubles).add(mb);
        }
    }

    public Chambre(int num, int etage, float surface, boolean avecBalcon, Meuble... M) {
        this.num = num;
        this.etage = etage;
        this.surface = surface;
        AvecBalcon = avecBalcon;
        this.etat = EtatChambre.DISPONIBLE;
        for (Meuble mb : M) {
            (this.meubles).add(new CaractsMeubles(mb));
        }
    }

    public boolean addMeuble(CaractsMeubles m) {
        return meubles.add(m);
    }

    public boolean addMeuble(Meuble m, EtatMeuble etat, int oc) {
        for (int i = 0; i < oc; i++) {
            if (!meubles.add(new CaractsMeubles(m, etat)))
                return false;
        }
        return true;
    }

    public boolean addMeuble(Meuble m, int oc) {
        for (int i = 0; i < oc; i++) {
            if (!meubles.add(new CaractsMeubles(m)))
                return false;
        }
        return true;
    }

    public boolean addMeuble(Meuble m, EtatMeuble etat) {
        if (!meubles.add(new CaractsMeubles(m, etat)))
            return false;
        return true;
    }

    public boolean addMeuble(Meuble m) {
        if (!meubles.add(new CaractsMeubles(m)))
            return false;
        return true;
    }

    public boolean supMeuble(CaractsMeubles m) {
        return meubles.remove(m);
    }

    public void supMeuble(Meuble m, EtatMeuble etat, int oc) {

        for (CaractsMeubles mb : meubles) {
            if (mb.getEtat() == etat && mb.getMeuble() == m && oc > 0) {
                meubles.remove(mb);
                oc = oc - 1;
                if (oc == 0)
                    break;
            }
        }

    }

    public void supMeuble(Meuble m, EtatMeuble etat) {

        for (CaractsMeubles mb : meubles) {
            if (mb.getEtat() == etat && mb.getMeuble() == m) {
                meubles.remove(mb);
            }
        }
    }

    public void supMeuble(Meuble m, int oc) {

        for (CaractsMeubles mb : meubles) {
            if (mb.getMeuble() == m && oc > 0) {
                meubles.remove(mb);
                oc = oc - 1;
                if (oc == 0)
                    break;
            }
        }

    }

    public void supMeuble(Meuble m) {

        for (CaractsMeubles mb : meubles) {
            if (mb.getMeuble() == m) {
                meubles.remove(mb);
            }
        }
    }

    public boolean AddReservation(Reservation r) throws ChevauchentReservationException {
        for (LocalDate date : reservations.keySet()) {
            Reservation res = reservations.get(date);
            if (res.equals(r))
                throw new ChevauchentReservationException();
            ;

        }
        reservations.put(r.getDebut(), r);
        return true;

    }

    public boolean isDispo(LocalDate d, LocalDate f) {
        Reservation r = new Reservation(d, f, null, null, null);
        for (LocalDate date : reservations.keySet()) {
            Reservation res = reservations.get(date);
            if (res.equals(r))
                return false;

        }

        return true;

    }

    public boolean SupReservation(LocalDate key) throws NoExistReservationException {
        if (reservations.remove(key) == null)
            throw new NoExistReservationException();
        else
            return true;
    }

    public boolean SupReservation(Reservation r) throws NoExistReservationException {
        if (reservations.remove(r.getDebut(), r))
            throw new NoExistReservationException();
        else
            return true;
    }

    public Reservation getReservation(LocalDate d) throws NoExistReservationException {
        if (reservations.get(d) == null)
            throw new NoExistReservationException();
        else
            return reservations.get(d);
    }

    private ArrayList<Reservation> getReservationConfirm(Boolean b) throws NoExistReservationException {
        ArrayList<Reservation> Lres = new ArrayList<Reservation>();
        for (Map.Entry<LocalDate, Reservation> entry : reservations.entrySet()) {
            if (entry.getValue().isConfirm() == b) {
                Lres.add(entry.getValue());
            }
        }
        return Lres;
    }

    public ArrayList<Reservation> getReservationConfirm() throws NoExistReservationException {
        return getReservationConfirm(true);
    }

    public ArrayList<Reservation> getReservationNoConfirm() throws NoExistReservationException {
        return getReservationConfirm(false);
    }

    public Sejour sejouractul() throws SejourNoCommencerException {

        for (Map.Entry<LocalDate, Reservation> entry : reservations.entrySet()) {
            Reservation r = entry.getValue();
            LocalDate d = r.getDebut();
            LocalDate f = r.getFin();
            if ((d.isBefore(LocalDate.now()) || d.equals(LocalDate.now()))
                    && (f.isAfter(LocalDate.now()) || f.equals(LocalDate.now())) && r.isConfirm()) {
                return r.getSejour();
            }
        }
        return null;

    }

    public List<Reservation> ReservationFuture() {
        ArrayList<Reservation> Lres = new ArrayList<Reservation>();

        for (Map.Entry<LocalDate, Reservation> entry : reservations.entrySet()) {
            Reservation r = entry.getValue();
            LocalDate d = r.getDebut();

            if (d.isAfter(LocalDate.now())) {
                Lres.add(r);
            }
        }
        return Lres;

    }

    public List<Reservation> ReservationPasse() {
        ArrayList<Reservation> Lres = new ArrayList<Reservation>();
        for (Map.Entry<LocalDate, Reservation> entry : reservations.entrySet()) {
            Reservation r = entry.getValue();
            LocalDate f = r.getFin();
            if (f.isBefore(LocalDate.now())) {
                Lres.add(r);
            }
        }
        return Lres;

    }

    public TreeMap<LocalDate, Reservation> getReservations() {
        return reservations;
    }

    public int getNum() {
        return num;
    }

    public int getEtage() {
        return etage;
    }

    public float getSurface() {
        return surface;
    }

    public EtatChambre getEtat() {
        return etat;
    }

    public boolean isAvecBalcon() {
        return AvecBalcon;
    }

    public void setDisponible() {
        this.etat = EtatChambre.DISPONIBLE;
    }

    public void setEN_ATTENTE_DE_NETTOYAGE() {
        this.etat = EtatChambre.EN_ATTENTE_DE_NETTOYAGE;
    }

    public void setHORS_SERVICE() {
        this.etat = EtatChambre.HORS_SERVICE;
    }

    public void setMAINTENANCE() {
        this.etat = EtatChambre.MAINTENANCE;
    }

    public static double getPrix() {
        return 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(num, etage);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Chambre other = (Chambre) obj;
        return Objects.equals(num, other.num) &&
                Objects.equals(etage, other.etage);
    }

}
