import java.util.Random;
import java.util.Scanner;

public class main {
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

            
            if (bouteille.isEstOuvert() && random.nextBoolean()) {
                Bouteille.vider() ;
            }
        }
    }
}

