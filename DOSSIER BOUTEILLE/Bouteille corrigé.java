package exercices.niv1;

public class Bouteille {

    // Attributs

    private String liquid;
    private int quantity; // en cl
    private boolean isOpen;
    private boolean isEmpty;


    // Constructeur

    public Bouteille(String liquid, int quantity, boolean isOpen, boolean isEmpty) {


        this.liquid = liquid;
        this.quantity = quantity;
        this.isOpen = isOpen;

        if(quantity <= 0) {
            this.isEmpty = true;
            this.quantity = 0;
            System.out.println("La quantité doit être supérieure à 0 ! La bouteille est vide.");

        } else {
            this.isEmpty = false;
        }

    }

    public void ouvrir() {
        if (!isOpen) {
            isOpen = true;
            System.out.println("La bouteille est maintenant ouverte.");
        } else {
            System.out.println("La bouteille est déjà ouverte.");
        }
    }


    public void infoBouteille() {
        System.out.println("Bouteille de " + liquid + " : " + quantity + " cl, Ouverte : " + (isOpen ? "Oui" : "Non") + ", Vide : " + (isEmpty ? "Oui" : "Non"));
    }

    public int boire(int quantityABoire) {
        if (isOpen && !isEmpty) { /* Vérification si la bouteille est ouverte et non vide */
            if (quantityABoire <= quantity) { // Vérification si la quantité à boire est inférieure ou égale à la quantité restante
                quantity -= quantityABoire;
                System.out.println("Vous avez bu " + quantityABoire + " cl de " + liquid + ".");
                if (quantity == 0) {
                    isEmpty = true;
                    System.out.println("La bouteille est maintenant vide.");
                }
            } else {
                System.out.println("Il n'y a pas assez de liquid dans la bouteille pour boire " + quantityABoire + " cl donc la bouteille sera vidée.");
                vider();
            }
        } else if (isEmpty) {
            System.out.println("La bouteille est vide, vous ne pouvez pas boire.");
        } else {
            System.out.println("La bouteille n'est pas ouverte, vous devez d'abord l'ouvrir.");
        }

        return quantity; // Retourne la quantité restante dans la bouteille
    }

    public void fermer() {
        if (isOpen) {
            isOpen = false;
            System.out.println("La bouteille est maintenant fermée.");
        } else {
            System.out.println("La bouteille est déjà fermée.");
        }
    }

    // Méthode pour vider la bouteille aléatoirement
    public void vider() {
        if (!isEmpty) {
            quantity = 0;
            isEmpty = true;

        } else {
            System.out.println("La bouteille est déjà vide, rien à vider.");
        }
    }

    // Getters et Setters si nécessaire

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        if (quantity <= 0) {
            isEmpty = true;
            System.out.println("La quantité doit être supérieure à 0 ! La bouteille est vide.");
        } else {
            isEmpty = false;
        }
        System.out.println("La quantité de la bouteille a été mise à jour.");
    }


    public boolean isOpen() {

        return this.isOpen;

    }
    
    public boolean isEmpty() {

        return this.isEmpty;

    }
}
