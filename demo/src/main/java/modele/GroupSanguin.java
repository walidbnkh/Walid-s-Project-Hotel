package modele;

public enum GroupSanguin {
    A_POSITIF("A+"),
    B_POSITIF("B+"),
    O_POSITIF("O+"),
    AB_POSITIF("AB+"),
    A_NEGATIF("A-"),
    B_NEGATIF("B-"),
    O_NEGATIF("O-"),
    AB_NEGATIF("AB-");

    private String groupe;

    private GroupSanguin(String groupe) {
        this.groupe = groupe;
    }

    public String getGroupe() {
        return groupe;
    }
}
