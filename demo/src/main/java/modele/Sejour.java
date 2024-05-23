
package modele;

import java.awt.Desktop;
import java.io.*;

import java.time.*;
import java.time.format.*;
import java.time.temporal.ChronoUnit;
import java.util.*;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

public class Sejour {

    private Reservation reservation;

    public Reservation getReservation() {
        return reservation;
    }

    private ArrayList<Service> services = new ArrayList<Service>();

    public Sejour(Reservation reservation) throws SejourNoCommencerException {
        if (reservation.getDebut().equals(LocalDate.now()))
            this.reservation = reservation;
        else
            throw new SejourNoCommencerException();
    }

    public void addService(Service s) throws ServiceNoAddException {
        if (!services.add(s))
            throw new ServiceNoAddException();
    }

    public void addService(Services s) throws ServiceNoAddException {
        if (!services.add(new Service(s)))
            throw new ServiceNoAddException();
    }

    private Map<Services, Integer> mapingMap(ArrayList<Service> services) {
        Map<Services, Integer> mapService = new HashMap<Services, Integer>();

        for (Service service : services) {
            if (mapService.containsKey(service.getService())) {
                int q = mapService.get(service.getService());
                mapService.put(service.getService(), q + service.getQuantite());
            } else {
                mapService.put(service.getService(), service.getQuantite());
            }

        }
        return mapService;

    }

    private double prixChambre(Chambre c) {
        if (c instanceof Ch_simple)
            return Ch_simple.getPrix();
        if (c instanceof Ch_double)
            return Ch_double.getPrix();
        if (c instanceof Suite_norml)
            return Suite_norml.getPrix();
        if (c instanceof Suite_presidentionnelle)
            return Suite_presidentionnelle.getPrix();
        else
            return 0;

    }

    public Double facture() {
        double s = 0;
        Map<Services, Integer> mapService = new HashMap<Services, Integer>();
        mapService = mapingMap(services);

        String directoryName = "factures";
        LocalDate reservationDate = LocalDate.now();
        String dayDirectoryName = reservationDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        // Créer le dossier du jour s'il n'existe pas
        File dayDirectory = new File(directoryName + File.separator + dayDirectoryName);
        if (!dayDirectory.exists()) {
            dayDirectory.mkdirs();
        }

        String fileName = "facture_sejour_" + this + ".pdf";

        try {
            BaseColor clr = BaseColor.LIGHT_GRAY;

            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(dayDirectory + File.separator + fileName));
            document.open();
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 24, BaseColor.BLACK);

            // Titre de la facture
            Paragraph title = new Paragraph("FACTURE\n\n", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);

            document.add(title);
            Font font = FontFactory.getFont(FontFactory.TIMES, 10, BaseColor.DARK_GRAY);

            // Informations sur la réservation
            Paragraph reservationInfo = new Paragraph("Date de réservation: " + reservation.getDateReservation(), font);
            document.add(reservationInfo);

            // Informations sur le client
            Paragraph clientInfo = new Paragraph(
                    "Client: " + reservation.getClient().getNom() + " " + reservation.getClient().getPrenom()
                            + reservation.getClient().Email()
                            + reservation.getClient().Telephone(),
                    font);
            document.add(clientInfo);

            // Informations sur le séjour
            Paragraph stayInfo = new Paragraph(
                    "Dates de séjour: " + reservation.getDebut() + " - " + reservation.getFin()
                            + "\nLa chembre numéro: " + reservation.getChambre().getEtage()
                            + reservation.getChambre().getNum() + "\n\n\n",
                    font);
            document.add(stayInfo);

            // Création du tableau
            PdfPTable table = new PdfPTable(4); // 3 colonnes
            table.setWidthPercentage(90); // La largeur du tableau est 100% de la page

            Font dataFont = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, BaseColor.BLACK);
            Font data2Font = new Font(Font.FontFamily.HELVETICA, 9, Font.NORMAL, BaseColor.BLACK);
            Font data3Font = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.BLACK);

            // En-têtes du tableau
            PdfPCell cell1 = new PdfPCell(new Phrase("Service", dataFont));
            PdfPCell cell2 = new PdfPCell(new Phrase("Quantité", dataFont));
            PdfPCell cell3 = new PdfPCell(new Phrase("Prix unitaire", dataFont));
            PdfPCell cell4 = new PdfPCell(new Phrase("Coût", dataFont));

            cell1.setBackgroundColor(clr);
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell2.setBackgroundColor(clr);
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell3.setBackgroundColor(clr);
            cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell4.setBackgroundColor(clr);
            cell4.setHorizontalAlignment(Element.ALIGN_CENTER);

            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);
            table.addCell(cell4);

            // Ajout des lignes de données (ex. pour deux services fictifs)

            for (Map.Entry<Services, Integer> entry : mapService.entrySet()) {
                Services service = entry.getKey();
                int quantity = entry.getValue();

                PdfPCell serviceCell = new PdfPCell(new Phrase(service.toString(), data2Font));
                PdfPCell quantityCell = new PdfPCell(new Phrase(String.valueOf(quantity), data2Font));
                double prisU = service.getPrix(); // Calculate the total cost for the service
                PdfPCell prixUCell = new PdfPCell(new Phrase(String.format("%.2f €", prisU), data2Font));
                double cost = service.getPrix() * quantity; // Calculate the total cost for the service
                s = s + cost;
                PdfPCell costCell = new PdfPCell(new Phrase(String.format("%.2f €", cost), data3Font));

                // Set background color and center alignment for data cells
                serviceCell.setBackgroundColor(BaseColor.WHITE);
                serviceCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                quantityCell.setBackgroundColor(BaseColor.WHITE);
                quantityCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                prixUCell.setBackgroundColor(BaseColor.WHITE);
                prixUCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                costCell.setBackgroundColor(BaseColor.WHITE);
                costCell.setHorizontalAlignment(Element.ALIGN_CENTER);

                // Add data cells to the table
                table.addCell(serviceCell);
                table.addCell(quantityCell);
                table.addCell(prixUCell);
                table.addCell(costCell);

            }
            PdfPCell vide = new PdfPCell();
            vide.setBorder(0);
            table.addCell(vide);
            // Ajouter une ligne pour le coût total de la réservation sans détails sur les
            // services
            double totalCost = s + (ChronoUnit.DAYS.between(reservation.getDebut(), reservation.getFin()) + 1)
                    * prixChambre(reservation.getChambre()); // Calcul du coût total de la réservation
            PdfPCell totalCostLabelCell = new PdfPCell(
                    new Phrase("Coût de séjour (" + reservation.getDebut() + " - " + reservation.getFin() + ")",
                            dataFont));
            totalCostLabelCell.setColspan(2); // Fusionne sur 3 colonnes
            totalCostLabelCell.setBackgroundColor(clr);
            totalCostLabelCell.setHorizontalAlignment(Element.ALIGN_RIGHT);

            PdfPCell totalCostCell = new PdfPCell(new Phrase(
                    String.format("%.2f €",
                            (ChronoUnit.DAYS.between(reservation.getDebut(), reservation.getFin()) + 1)
                                    * prixChambre(reservation.getChambre())),
                    data3Font));
            totalCostCell.setBackgroundColor(BaseColor.WHITE);
            totalCostCell.setHorizontalAlignment(Element.ALIGN_CENTER);

            table.addCell(totalCostLabelCell);
            table.addCell(totalCostCell);

            table.addCell(vide);
            table.addCell(vide);

            PdfPCell totalLabelCell = new PdfPCell(
                    new Phrase("Total (HT)",
                            data3Font));
            totalLabelCell.setBackgroundColor(clr);
            totalLabelCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            PdfPCell totalCell = new PdfPCell(new Phrase(String.format("%.2f €", totalCost), data3Font));
            totalCell.setBackgroundColor(BaseColor.WHITE);
            totalCell.setHorizontalAlignment(Element.ALIGN_CENTER);

            table.addCell(totalLabelCell);
            table.addCell(totalCell);

            table.addCell(vide);
            table.addCell(vide);
            double tva = totalCost * 0.2;

            PdfPCell tvaLabelCell = new PdfPCell(
                    new Phrase("TVA(20%)",
                            data3Font));
            tvaLabelCell.setBackgroundColor(clr);
            tvaLabelCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            PdfPCell tvaCell = new PdfPCell(new Phrase(String.format("%.2f €", tva), data3Font));
            tvaCell.setBackgroundColor(BaseColor.WHITE);
            tvaCell.setHorizontalAlignment(Element.ALIGN_CENTER);

            table.addCell(tvaLabelCell);
            table.addCell(tvaCell);

            table.addCell(vide);
            table.addCell(vide);

            PdfPCell totalPLabelCell = new PdfPCell(
                    new Phrase("Total à payé",
                            data3Font));
            totalPLabelCell.setBackgroundColor(clr);
            totalPLabelCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            PdfPCell totalPCell = new PdfPCell(new Phrase(String.format("%.2f €", totalCost + tva), data3Font));
            totalPCell.setBackgroundColor(BaseColor.WHITE);
            totalPCell.setHorizontalAlignment(Element.ALIGN_CENTER);

            table.addCell(totalPLabelCell);
            table.addCell(totalPCell);
            // Ajouter le tableau au document
            document.add(table);

            Paragraph date = new Paragraph("\n\n\n\n               Le  " + LocalDate.now() + ",");
            document.add(date);

            // Ajouter plus d'informations sur les services, les coûts, etc.

            document.close();

            // Ouvrir automatiquement le fichier PDF
            File file = new File(dayDirectory + File.separator + fileName);
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(file);
            }
            return totalCost + tva;

        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Sejour other = (Sejour) obj;
        return Objects.equals(reservation, other.reservation);
    }
}
