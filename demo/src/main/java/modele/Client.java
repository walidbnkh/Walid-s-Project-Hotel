package modele;

import java.time.*;
import java.util.*;

public class Client {

    private String nn;
    private String nom;
    private String prenom;
    private String telephone;

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    private String email;

    public boolean setEmail(String email) {
        if (isValidPhoneNumber(telephone)) {
            this.email = email;
            return true;
        } else
            return false;
    }

    private LocalDate dateNaissence;
    private GroupSanguin gs;

    private ArrayList<Allergie> allergies = new ArrayList<Allergie>();
    private TreeMap<LocalDate, Reservation> reservations = new TreeMap<LocalDate, Reservation>();

    public Client(String nn, String nom, String prenom, String telephone, String email, LocalDate dateNaissence,
            GroupSanguin gs, ArrayList<Allergie> allergies)
            throws InvalidPhoneNumberException, InvalidEmailException {
        if (!isValidPhoneNumber(telephone)) {
            throw new InvalidPhoneNumberException("Numéro de téléphone invalide : " + telephone);
        }
        if (!isValidEmail(email)) {
            throw new InvalidEmailException("Adresse e-mail invalide : " + email);
        }
        this.nn = nn;
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.email = email;
        this.dateNaissence = dateNaissence;
        this.gs = gs;
        this.allergies = allergies;
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        // Implémentation de la validation du numéro de téléphone
        return phoneNumber.matches("\\d{10}");
    }

    // Méthode pour vérifier la validité de l'adresse e-mail
    private boolean isValidEmail(String email) {
        // Implémentation de la validation de l'adresse e-mail
        return email.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}$");
    }

    public Client(String nn, String nom, String prenom, LocalDate dateNaissence, GroupSanguin gs,

            String email, String telephone, Allergie... allergies)
            throws InvalidPhoneNumberException, InvalidEmailException {
        if (!isValidPhoneNumber(telephone)) {
            throw new InvalidPhoneNumberException("Numéro de téléphone invalide : " + telephone);
        }
        if (!isValidEmail(email)) {
            throw new InvalidEmailException("Adresse e-mail invalide : " + email);
        }
        this.nn = nn;
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.email = email;
        this.dateNaissence = dateNaissence;
        this.gs = gs;
        for (Allergie allgr : allergies) {
            (this.allergies).add(allgr);
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

    public boolean hasAllergie(Allergie a) {
        return allergies.contains(a);
    }

    public String getNn() {
        return nn;
    }

    public LocalDate getDateNaissence() {
        return dateNaissence;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public GroupSanguin getGs() {
        return gs;
    }

    public String getEmail() {
        return email;
    }

    public String Email() {
        if (email == null)
            return "";
        return "\nEmail: " + email;
    }

    public String getTelephone() {
        return telephone;
    }

    public String Telephone() {
        if (telephone == null)
            return "";
        return "\nTéléphone: " + telephone;
    }

    public ArrayList<Allergie> getAllergies() {
        return allergies;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nn, nom, prenom, telephone, email, dateNaissence);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Client other = (Client) obj;
        return Objects.equals(nn, other.nn) &&
                Objects.equals(nom, other.nom) &&
                Objects.equals(prenom, other.prenom) &&
                Objects.equals(telephone, other.telephone) &&
                Objects.equals(email, other.email) &&
                Objects.equals(dateNaissence, other.dateNaissence);
    }

}
