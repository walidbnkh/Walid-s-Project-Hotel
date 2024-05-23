package modele;

// Définition d'une exception personnalisée pour les erreurs d'adresse e-mail
public class InvalidEmailException extends Exception {
    public InvalidEmailException(String message) {
        super(message);
    }
}