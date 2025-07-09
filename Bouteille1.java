package exercices.Niv1;

import java.util.Random;
import java.util.Scanner;

public class Bouteille1 {
    
    private String liquide;
    private int quantite; // en cl
    private boolean estOuvert;
    private boolean estVide;

    public Bouteille1(String liquide, int quantite) {
        this.liquide = liquide;
        this.quantite = quantite;
        this.estOuvert = false;
        this.estVide = (quantite <= 0);
    }

    public void ouvrir() {
        if (estVide) {
            System.out.println("La bouteille est déjà vide, impossible de l'ouvrir.");
            return;
        }
        if (!estOuvert) {
            estOuvert = true;
            System.out.println("Vous avez ouvert la bouteille de " + liquide + ".");
        } else {
            System.out.println("La bouteille est déjà ouverte.");
        }
    }

    public void boire(int quantiteBue) {
        if (!estOuvert) {
            System.out.println("Vous devez d'abord ouvrir la bouteille.");
            return;
        }
        if (estVide) {
            System.out.println("La bouteille est vide, impossible de boire.");
            return;
        }
        if (quantiteBue > quantite) {
            System.out.println("Vous ne pouvez pas boire plus que ce qu'il y a dans la bouteille.");
            return;
        }
        quantite -= quantiteBue;
        System.out.println("Vous avez bu " + quantiteBue + " cl de " + liquide + ".");
        if (quantite <= 0) {
            estVide = true;
            System.out.println("La bouteille est maintenant vide.");
        }
    }

    public void fermer() {
        if (estOuvert) {
            estOuvert = false;
            System.out.println("Vous avez fermé la bouteille.");
        } else {
            System.out.println("La bouteille est déjà fermée.");
        }
    }

    public void vider() {
        if (!estVide) {
            quantite = 0;
            estVide = true;
            System.out.println("Oups! La bouteille s'est vidée accidentellement.");
        }
    }

    // Getters
    public String getLiquide() {
        return liquide;
    }

    public int getQuantite() {
        return quantite;
    }

    public boolean isEstOuvert() {
        return estOuvert;
    }

    public boolean isEstVide() {
        return estVide;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        Bouteille bouteille = new Bouteille("Coca", 50); // Exemple: bouteille de Coca de 50 cl

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Ouvrir la bouteille");
            System.out.println("2. Boire");
            System.out.println("3. Fermer la bouteille");
            System.out.println("4. Quitter");
            System.out.print("Choisissez une action: ");
            int choix = scanner.nextInt();

            switch (choix) {
                case 1:
                    bouteille.ouvrir();
                    break;
                case 2:
                    if (bouteille.isEstOuvert() && !bouteille.isEstVide()) {
                        System.out.print("Combien de cl voulez-vous boire ? ");
                        int quantiteBue = scanner.nextInt();
                        bouteille.boire(quantiteBue);
                    } else if (!bouteille.isEstOuvert()) {
                        System.out.println("La bouteille est fermée, ouvrez-la d'abord.");
                    } else {
                        System.out.println("La bouteille est vide.");
                    }
                    break;
                case 3:
                    bouteille.fermer();
                    break;
                case 4:
                    System.out.println("Au revoir !");
                    scanner.close();
                    return;
                default:
                    System.out.println("Choix invalide.");
            }

            // Bonus: vérifier si la bouteille est ouverte et non fermée
            if (bouteille.isEstOuvert() && random.nextBoolean()) {
                Bouteille.vider() ;
            }
        }
    }
}