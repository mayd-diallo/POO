package exercices.niv1;

/*  Exercice n°1 : 
 * 
 * Me créer en POO une classe Bouteille avec les attributs suivants :
 * 
 * Liquide (Eau,Coca, Jus d'orange)
 * quantité (en cl)
 * Est ouvert ( Oui ou non)
 * Est vide ( Oui ou non)
 * 
 * Son comportement ::
 * 
 * Ouvrir
 * Boire
 * Fermer
 * 
 * 
 * Diagramme UML et code Java exigés
 * 
 * 
 * 
 */
public class Bouteille {
    // Constantes pour les types de liquides
    public static final String EAU = "Eau";
    public static final String COCA = "Coca";
    public static final String JUS_ORANGE = "Jus d'orange";
    
    // Attributs
    private String liquide;
    private int quantite; // en centilitres (cl)
    private boolean estOuvert;
    private boolean estVide;
    
    // Constructeur
    public Bouteille(String liquide, int quantite) {
        // Validation du liquide
        if (!liquide.equals(EAU) && !liquide.equals(COCA) && !liquide.equals(JUS_ORANGE)) {
            throw new IllegalArgumentException("Type de liquide non valide");
        }
        
        // Validation de la quantité
        if (quantite <= 0) {
            throw new IllegalArgumentException("La quantité doit être positive");
        }
        
        this.liquide = liquide;
        this.quantite = quantite;
        this.estOuvert = false; // Par défaut, la bouteille est fermée
        this.estVide = false;   // Par défaut, la bouteille n'est pas vide
    }
    
    // Méthodes
    
    /**
     * Ouvre la bouteille si elle n'est pas déjà ouverte
     */
    public void ouvrir() {
        if (estVide) {
            System.out.println("Impossible d'ouvrir une bouteille vide");
            return;
        }
        
        if (estOuvert) {
            System.out.println("La bouteille est déjà ouverte");
        } else {
            estOuvert = true;
            System.out.println("Vous avez ouvert la bouteille de " + liquide);
        }
    }
    
    /**
     * Boire une certaine quantité de liquide
     * @param quantiteBoire la quantité à boire en cl
     */
    public void boire(int quantiteBoire) {
        if (!estOuvert) {
            System.out.println("Impossible de boire, la bouteille est fermée");
            return;
        }
        
        if (estVide) {
            System.out.println("La bouteille est déjà vide");
            return;
        }
        
        if (quantiteBoire <= 0) {
            System.out.println("La quantité à boire doit être positive");
            return;
        }
        
        if (quantiteBoire > quantite) {
            System.out.println("Vous buvez les " + quantite + "cl restants de " + liquide);
            quantite = 0;
            estVide = true;
        } else {
            quantite -= quantiteBoire;
            System.out.println("Vous buvez " + quantiteBoire + "cl de " + liquide);
            
            if (quantite == 0) {
                estVide = true;
            }
        }
    }
    
    /**
     * Ferme la bouteille si elle n'est pas déjà fermée
     */
    public void fermer() {
        if (!estOuvert) {
            System.out.println("La bouteille est déjà fermée");
        } else {
            estOuvert = false;
            System.out.println("Vous avez fermé la bouteille de " + liquide);
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
    
    @Override
    public String toString() {
        String etat = estOuvert ? "ouverte" : "fermée";
        String contenu = estVide ? "vide" : ("contient " + quantite + "cl de " + liquide);
        return "Bouteille " + etat + " - " + contenu;
    }
}