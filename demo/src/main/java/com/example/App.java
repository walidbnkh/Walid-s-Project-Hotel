package com.example;

import java.time.LocalDate;

import modele.*;

public class App {
    public static void main(String[] args)

    {
        Ch_simple cs = new Ch_simple(20, 1, 10);

        Client c;
        try {
            c = new Client("123", "MEZEMATE", "Islam", LocalDate.of(2002, 4, 8), GroupSanguin.O_POSITIF,
                    "mohamed@gmail.com", "0605000102", Allergie.ALLERGIE_AUX_PIQURES_D_INSECTES);
            Reservation r = new Reservation(LocalDate.now(), LocalDate.of(2024, 4, 13), LocalDate.of(2024, 4, 1), cs,
                    c);

            Sejour s;
            s = new Sejour(r);
            s.addService(Services.PARKING);
            s.addService(Services.PARKING);

            s.addService(Services.PISCINE);
            s.addService(Services.RESTAURATION);
            s.addService(Services.RESTAURATION);
            s.addService(Services.RESTAURATION);
            s.addService(Services.WIFI);

            System.out.println(s.facture());

        } catch (InvalidPhoneNumberException | InvalidEmailException e) {
            e.printStackTrace();

        }

        catch (SejourNoCommencerException e) {
            System.out.println(
                    "hh");
        } catch (ServiceNoAddException e) {
            System.out.println(
                    "ee");
        }

    }
}
