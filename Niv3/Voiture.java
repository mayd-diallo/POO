package Niv3;

import java.util.Scanner;
import java.util.Random;

public class Voiture {
    // Attributs
    private String marque;
    private String modele;
    private int puissance; // en cv
    private int vitesse; // en km/h
    private boolean moteurEnMarche;
    private boolean embrayageEngage;
    private boolean clignotantGauche;
    private boolean clignotantDroit;
    private boolean feuxCroisement;
    private boolean feuxWarning;
    private boolean essuieGlaces;
    private int vitesseLimite;
    private int pointsConduite;
    private boolean pluie;
    private Random random;
    
    // Constructeur
    public Voiture(String marque, String modele, int puissance) {
        this.marque = marque;
        this.modele = modele;
        this.puissance = puissance;
        this.vitesse = 0;
        this.moteurEnMarche = false;
        this.embrayageEngage = false;
        this.clignotantGauche = false;
        this.clignotantDroit = false;
        
        this.feuxCroisement = false;
        this.feuxWarning = false;
        this.essuieGlaces = false;
        this.vitesseLimite = 0;
        this.pointsConduite = 40; // Points de départ
        this.random = new Random();
        this.pluie = false;
    }
    
    // Méthodes de comportement
    public void demarrer() {
        if (!moteurEnMarche) {
            moteurEnMarche = true;
            System.out.println("La voiture a démarré.");
        } else {
            System.out.println("Le moteur est déjà en marche.");
        }
    }
    
    public void arreter() {
        if (moteurEnMarche) {
            if (vitesse == 0) {
                moteurEnMarche = false;
                System.out.println("La voiture s'est arrêtée.");
            } else {
                System.out.println("Impossible d'arrêter le moteur en mouvement.");
                perdrePoints(5, "Tentative d'arrêt du moteur en mouvement");
            }
        } else {
            System.out.println("Le moteur est déjà arrêté.");
        }
    }
    
    public void accelerer(int acceleration) {
        if (moteurEnMarche) {
            if (embrayageEngage) {
                vitesse += acceleration;
                if (vitesse > vitesseLimite) {
                    int exces = vitesse - vitesseLimite;
                    if (exces > 5) {
                        System.out.println("Excès de vitesse important ! -" + (exces-5)*2 + " points");
                        perdrePoints((exces-5)*2, "Excès de vitesse de " + exces + " km/h");
                    } else if (exces > 0) {
                        perdrePoints(2, "Petit excès de vitesse");
                    }
                }
                System.out.println("Vous accélérez. Vitesse actuelle: " + vitesse + " km/h");
            } else {
                System.out.println("Embrayage non engagé, impossible d'accélérer.");
            }
        } else {
            System.out.println("Le moteur est éteint, impossible d'accélérer.");
        }
    }
    
    public void decelerer(int deceleration) {
        vitesse = Math.max(0, vitesse - deceleration);
        System.out.println("Vous ralentissez. Vitesse actuelle: " + vitesse + " km/h");
    }
    
    public void freiner() {
        vitesse = 0;
        System.out.println("Vous freinez complètement. Vitesse: 0 km/h");
    }
    
    public void marcheArriere() {
        if (vitesse == 0) {
            vitesse = -5; // vitesse constante pour la marche arrière
            System.out.println("Vous engagez la marche arrière.");
        } else {
            System.out.println("Impossible d'engager la marche arrière en mouvement.");
        }
    }
    
    public void embrayer() {
        embrayageEngage = true;
        System.out.println("Embrayage engagé.");
    }
    
    public void debrayer() {
        embrayageEngage = false;
        System.out.println("Embrayage débrayé.");
    }
    
    public void virerGauche() {
        if (vitesse > 0) {
            if (clignotantGauche) {
                System.out.println("Vous virez à gauche.");
                clignotantGauche = false;
            } else {
                System.out.println("Vous n'avez pas mis votre clignotant gauche !");
                perdrePoints(2, "Clignotant gauche non activé");
            }
        } else {
            System.out.println("Impossible de virer à l'arrêt.");
        }
    }
    
    public void virerDroite() {
        if (vitesse > 0) {
            if (clignotantDroit) {
                System.out.println("Vous virez à droite.");
                clignotantDroit = false;
            } else {
                System.out.println("Vous n'avez pas mis votre clignotant droit !");
                perdrePoints(2, "Clignotant droit non activé");
            }
        } else {
            System.out.println("Impossible de virer à l'arrêt.");
        }
    }
    
    public void changerVoieGauche() {
        if (vitesse > 0) {
            if (clignotantGauche) {
                System.out.println("Vous changez de voie à gauche.");
                clignotantGauche = false;
            } else {
                System.out.println("Vous n'avez pas mis votre clignotant gauche !");
                perdrePoints(2, "Clignotant gauche non activé pour changement de voie");
            }
        } else {
            System.out.println("Impossible de changer de voie à l'arrêt.");
        }
    }
    
    public void changerVoieDroite() {
        if (vitesse > 0) {
            if (clignotantDroit) {
                System.out.println("Vous changez de voie à droite.");
                clignotantDroit = false;
            } else {
                System.out.println("Vous n'avez pas mis votre clignotant droit !");
                perdrePoints(2, "Clignotant droit non activé pour changement de voie");
            }
        } else {
            System.out.println("Impossible de changer de voie à l'arrêt.");
        }
    }
    
    public void controleVisibilite() {
        System.out.println("Vous contrôlez les rétroviseurs et angles morts.");
    }
    
    public void activerClignotantGauche() {
        clignotantGauche = true;
        clignotantDroit = false;
        System.out.println("Clignotant gauche activé.");
    }
    
    public void activerClignotantDroit() {
        clignotantDroit = true;
        clignotantGauche = false;
        System.out.println("Clignotant droit activé.");
    }
    
    public void activerFeuxCroisement() {
        feuxCroisement = true;
        System.out.println("Feux de croisement activés.");
    }
    
    public void desactiverFeuxCroisement() {
        feuxCroisement = false;
        System.out.println("Feux de croisement désactivés.");
    }
    
    public void activerEssuieGlaces() {
        essuieGlaces = true;
        System.out.println("Essuie-glaces activés.");
    }
    
    public void desactiverEssuieGlaces() {
        essuieGlaces = false;
        System.out.println("Essuie-glaces désactivés.");
    }
    
    public void activerFeuxWarning() {
        feuxWarning = true;
        System.out.println("Feux warning activés.");
    }
    
    public void desactiverFeuxWarning() {
        feuxWarning = false;
        System.out.println("Feux warning désactivés.");
    }
    
    public void klaxonner() {
        System.out.println("Tut tut !");
    }
    
    public void setVitesseLimite(int limite) {
        if (pluie) {
            this.vitesseLimite = limite - 10;
            System.out.println("Notification: Vous êtes sur une route limitée à " + limite + " km/h (réduit à " + this.vitesseLimite + " km/h à cause de la pluie)");
        } else {
            this.vitesseLimite = limite;
            System.out.println("Notification: Vous êtes sur une route limitée à " + this.vitesseLimite + " km/h");
        }
    }
    
    public void perdrePoints(int points, String raison) {
        pointsConduite -= points;
        System.out.println("Erreur: " + raison + " (-" + points + " points)");
        System.out.println("Points restants: " + pointsConduite);
        
        if (pointsConduite < 0) {
            System.out.println("Test de conduite échoué ! Le moniteur prend la main.");
            System.exit(0);
        }
    }
    
    public void checkPluie() {
        // 20% de chance qu'il pleuve
        pluie = random.nextInt(5) == 0;
        if (pluie) {
            System.out.println("Il commence à pleuvoir ! Activez vos essuie-glaces.");
            vitesseLimite -= 10;
        }
    }
    
    public void verifierEssuieGlaces() {
        if (pluie && !essuieGlaces) {
            perdrePoints(5, "Essuie-glaces non activés sous la pluie");
        }
    }
    
    public void verifierFeuxTunnel() {
        if (!feuxCroisement) {
            perdrePoints(2, "Feux de croisement non activés dans le tunnel");
        }
    }
    
    // Getters
    public int getPointsConduite() {
        return pointsConduite;
    }
    
    public int getVitesse() {
        return vitesse;
    }
    
    public boolean isPluie() {
        return pluie;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Voiture maVoiture = new Voiture("Peugeot", "208", 75);
        
        System.out.println("=== TEST DE CONDUITE ===");
        System.out.println("Bienvenue dans votre test de conduite à Paris.");
        System.out.println("Vous conduisez une " + maVoiture.marque + " " + maVoiture.modele + " de " + maVoiture.puissance + " cv.");
        System.out.println("Vous commencez avec 40 points. Vous devez finir avec au moins 30 points pour réussir.");
        System.out.println("Appuyez sur Entrée pour commencer...");
        scanner.nextLine();
        
        // Simulation du test de conduite
        try {
            // Étape 1
            maVoiture.setVitesseLimite(30);
            System.out.println("1. Démarrer la voiture");
            maVoiture.demarrer();
            maVoiture.embrayer();
            
            // Étape 2
            System.out.println("2. Continuer tout droit");
            maVoiture.accelerer(10);
            
            // Vérification aléatoire de pluie
            maVoiture.checkPluie();
            maVoiture.verifierEssuieGlaces();
            
            // Étape 3
            System.out.println("3. Ralentir puis tourner à gauche (vous êtes prioritaire)");
            maVoiture.decelerer(5);
            maVoiture.activerClignotantGauche();
            maVoiture.controleVisibilite();
            maVoiture.virerGauche();
            
            // Étape 4
            System.out.println("4. Continuer tout droit (Il y a une voiture sur l'intersection à droite, vous n'êtes pas prioritaire)");
            maVoiture.accelerer(5);
            
            // Étape 5
            System.out.println("5. Attention, il y a un dos d'âne !");
            if (maVoiture.getVitesse() > 20) {
                maVoiture.perdrePoints(2, "Vitesse trop élevée pour un dos d'âne");
            }
            maVoiture.decelerer(15);
            
            // ... Continuer avec les autres étapes du test ...
            // (Le code complet inclurait toutes les étapes du scénario)
            
            // Dernières étapes
            System.out.println("27. Garez la voiture en marche arrière");
            maVoiture.marcheArriere();
            
            System.out.println("28. Couper le moteur");
            maVoiture.arreter();
            
            // Résultat final
            if (maVoiture.getPointsConduite() >= 30) {
                System.out.println("Félicitations ! Vous avez réussi votre test de conduite avec " + 
                                  maVoiture.getPointsConduite() + "/40 points !");
            } else {
                System.out.println("Malheureusement, vous avez échoué avec seulement " + 
                                  maVoiture.getPointsConduite() + "/40 points.");
            }
            
        } catch (Exception e) {
            System.out.println("Une erreur est survenue: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
