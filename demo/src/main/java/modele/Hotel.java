package modele;

import java.util.*;
import java.time.*;

public class Hotel {

    private String nom;
    private int nbetoile;
    private String Adress;
    Set<Client> clients = new HashSet<Client>();
    Set<Chambre> chambres = new HashSet<Chambre>();
    ArrayList<Sejour> sejours = new ArrayList<Sejour>();
    ArrayList<Sejour> sejours_a = new ArrayList<Sejour>();

    public Hotel(String nom, String adress, int nbetoile) {
        this.nom = nom;
        Adress = adress;
        this.nbetoile = nbetoile;
    }

    public Hotel(String nom, String adress, int nbetoile, Set<Client> clients, Set<Chambre> chambres,
            ArrayList<Sejour> sejours) {
        this.nom = nom;
        Adress = adress;
        this.nbetoile = nbetoile;
        this.clients = clients;
        this.chambres = chambres;
        this.sejours = sejours;
    }

    public boolean addChambre(Chambre c) {

        if (!chambres.contains(c)) {
            chambres.add(c);
            return true;
        } else {
            return false;
        }

    }

    public Chambre getChambre(int num, int etage) {
        for (Chambre c : chambres) {
            if (c.getEtage() == etage && c.getNum() == num)
                return c;
        }
        return null;

    }

    public ArrayList<Chambre> getChambreDispo(LocalDate debut, LocalDate fin) {
        ArrayList<Chambre> chms = new ArrayList<Chambre>();

        for (Chambre c : chambres) {
            if (c.isDispo(debut, fin))
                chms.add(c);
        }
        return chms;

    }

    public ArrayList<Chambre> getChambreParEtat(EtatChambre etat) {
        ArrayList<Chambre> chms = new ArrayList<Chambre>();
        for (Chambre c : chambres) {
            if (c.getEtat() == etat)
                chms.add(c);
        }
        return chms;

    }

    public boolean addCient(Client c) {

        if (!clients.contains(c)) {
            clients.add(c);
            return true;
        } else {
            return false;
        }

    }

    public ArrayList<Client> getClient(String nom, String prenom, String telephone) {
        ArrayList<Client> clts = new ArrayList<Client>();
        for (Client c : clients) {
            if (c.getNom() == nom && c.getPrenom() == prenom && telephone == c.getTelephone())
                clts.add(c);
        }
        return clts;

    }

    public ArrayList<Client> getClient(String nom, String prenom, LocalDate dateNaissence) {
        ArrayList<Client> matchingClients = new ArrayList<>();

        for (Client c : clients) {
            // Vérifier si le nom, prénom et date de naissance correspondent
            if (c.getNom().equals(nom) && c.getPrenom().equals(prenom) && c.getDateNaissence().equals(dateNaissence)) {
                matchingClients.add(c);
            }
        }
        return matchingClients;
    }

    public ArrayList<Client> getClientByNN(String nn) {
        ArrayList<Client> matchingClients = new ArrayList<>();

        for (Client c : clients) {
            // Vérifier si le numéro national correspond
            if (c.getNn().equals(nn)) {
                matchingClients.add(c);
            }
        }
        return matchingClients;
    }

    public boolean addSejour(Sejour S) {

        if (!sejours.contains(S)) {
            sejours.add(S);
            return true;
        } else
            return false;

    }

    public void CleanSejour() {
        for (Sejour c : sejours) {
            if (c.getReservation() != null && c.getReservation().getFin().isAfter(LocalDate.now())) {
                sejours.remove(c);
                sejours_a.add(c);
            }
        }

    }

    public void addSejour_a(Sejour S) throws SejourExsitDejaException, SejourNnFiniException {
        if ((S.getReservation().getFin().isAfter(LocalDate.now())
                || S.getReservation().getFin().equals(LocalDate.now()))) {

            if (!sejours_a.contains(S)) {
                sejours_a.add(S);

            } else
                new SejourExsitDejaException();
        } else
            new SejourNnFiniException();

    }

    public List<Sejour> getSejour(LocalDate debut, LocalDate fin) {
        ArrayList<Sejour> matchingsejours = new ArrayList<Sejour>();

        for (Sejour s : sejours) {
            if (s.getReservation().getDebut().equals(debut) && s.getReservation().getFin().equals(fin)) {
                matchingsejours.add(s);
            }
        }
        return matchingsejours;

    }

    public List<Sejour> getSejour(LocalDate debut) {
        ArrayList<Sejour> matchingsejours = new ArrayList<Sejour>();

        for (Sejour s : sejours) {
            if (s.getReservation().getDebut().equals(debut)) {
                matchingsejours.add(s);
            }
        }
        return matchingsejours;

    }

    public List<Sejour> getSejour_a(LocalDate debut, LocalDate fin) {
        ArrayList<Sejour> matchingsejours = new ArrayList<Sejour>();

        for (Sejour s : sejours_a) {
            if (s.getReservation().getDebut().equals(debut) && s.getReservation().getFin().equals(fin)) {
                matchingsejours.add(s);
            }
        }
        return matchingsejours;

    }

    public List<Sejour> getSejour_a(LocalDate debut) {
        ArrayList<Sejour> matchingsejours = new ArrayList<Sejour>();

        for (Sejour s : sejours_a) {
            if (s.getReservation().getDebut().equals(debut)) {
                matchingsejours.add(s);
            }
        }
        return matchingsejours;

    }

    public String getNom() {
        return nom;
    }

    public String getAdress() {
        return Adress;
    }

    public int getNbetoile() {
        return nbetoile;
    }

    public ArrayList<Sejour> getSejours_a() {
        return sejours_a;
    }
}
