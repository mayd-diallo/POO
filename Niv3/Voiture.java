package Niv3;


import java.util.Scanner;
import java.util.Random;

public class Voiture {
    // Attributs
    private String marque;
    private String modele;
    private int puissance; // en cv
    private int vitesse; // en km/h
    private boolean moteurAllume;
    private boolean embrayageEngage;
    private boolean clignotantGauche;
    private boolean clignotantDroit;
    private boolean feuxCroisement;
    private boolean feuxWarning;
    private boolean essuieGlaces;
    private boolean enMarcheArriere;
    private int pointsConduite;
    
    // Constructeur
    public Voiture(String marque, String modele, int puissance) {
        this.marque = marque;
        this.modele = modele;
        this.puissance = puissance;
        this.vitesse = 0;
        this.moteurAllume = false;
        this.embrayageEngage = false;
        this.pointsConduite = 40; // Points initiaux
    }
    
    // Méthodes de conduite
    public void demarrer() {
        
        ;
        if (!moteurAllume) {
            moteurAllume = true;
            System.out.println("Moteur démarré.");
        } else {
            System.out.println("Le moteur est déjà allumé.");
        }
    }
    
    public void arreter() {
        if (moteurAllume) {
            vitesse = 0;
            moteurAllume = false;
            System.out.println("Moteur arrêté.");
        } else {
            System.out.println("Le moteur est déjà éteint.");
        }
    }
    
    public void accelerer(int increment) {
        if (moteurAllume) {
            vitesse += increment;
            System.out.println("Accélération. Vitesse actuelle: " + vitesse + " km/h");
        } else {
            System.out.println("Impossible d'accélérer, le moteur est éteint.");
        }
    }
    
    public void decelerer(int decrement) {
        vitesse = Math.max(0, vitesse - decrement);
        System.out.println("Décélération. Vitesse actuelle: " + vitesse + " km/h");
    }
    
    public void freiner() {
        vitesse = 0;
        System.out.println("Freinage complet. Vitesse: 0 km/h");
    }
    
    public void embrayer() {
        embrayageEngage = true;
        System.out.println("Embrayage engagé (sensibilité: " + (puissance / 20) + "/5)");
    }
    
    public void debrayer() {
        embrayageEngage = false;
        System.out.println("Embrayage désengagé.");
    }
    
    public void virerAGauche() {
        System.out.println("Virage à gauche effectué.");
    }
    
    public void virerADroite() {
        System.out.println("Virage à droite effectué.");
    }
    
    public void changerVoieGauche() {
        if (!clignotantGauche) {
            perdrePoints(2);
            System.out.println("Attention: clignotant non activé!");
        }
        System.out.println("Changement de voie à gauche effectué.");
    }
    
    public void changerVoieDroite() {
        if (!clignotantDroit) {
            perdrePoints(2);
            System.out.println("Attention: clignotant non activé!");
        }
        System.out.println("Changement de voie à droite effectué.");
    }
    
    public void controleVisibilite() {
        System.out.println("Contrôle de visibilité effectué (rétroviseurs et angles morts).");
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
    
    public void desactiverClignotants() {
        clignotantGauche = false;
        clignotantDroit = false;
        System.out.println("Clignotants désactivés.");
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
        System.out.println("Tut tut!");
    }
    
    public void marcheArriere() {
        enMarcheArriere = true;
        System.out.println("Marche arrière engagée.");
    }
    
    public void finMarcheArriere() {
        enMarcheArriere = false;
        System.out.println("Marche arrière désengagée.");
    }
    
    public void perdrePoints(int points) {
        pointsConduite -= points;
        System.out.println(" Vous perdez " + points + " points. Total: " + pointsConduite + "/40");
    }
    
    // Getters
    public int getVitesse() {
        return vitesse;
    }
    
    public int getPointsConduite() {
        return pointsConduite;
    }
    
    public boolean isMoteurAllume() {
        return moteurAllume;
    }
    
    public boolean isEssuieGlacesActives() {
        return essuieGlaces;
    }
    
    public boolean isFeuxCroisementActives() {
        return feuxCroisement;
    }
}

public class TestConduiteParis {
    private static final int[] LIMITES = {30, 50, 70, 110};
    private static int limiteActuelle = LIMITES[0];
    private static boolean pluie = false;
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        Voiture voiture = new Voiture("Renault", "Clio", 90);
        
        System.out.println("=== TEST DE CONDUITE À PARIS ===");
        System.out.println("Conduite d'une " + voiture.getMarque() + " " + voiture.getModele() + " (" + voiture.getPuissance() + "cv)");
        System.out.println("Points initiaux: 40/40 - Minimum requis: 30/40");
        System.out.println("\nNotification: Limite de vitesse actuelle: " + limiteActuelle + " km/h");
        
        // Simulation aléatoire de pluie (30% de chance)
        if (random.nextInt(100) < 30) {
            pluie = true;
            limiteActuelle = Math.max(20, limiteActuelle - 10);
            System.out.println("\nIl commence à pleuvoir! Limite réduite à " + limiteActuelle + " km/h");
            System.out.println("Pensez à activer les essuie-glaces!");
        }
        
        // Instructions du parcours
        String[] instructions = {
            "1. Démarrer la voiture",
            "2. Continuer tout droit",
            "3. Ralentir puis tourner à gauche (prioritaire)",
            "4. Continuer tout droit (véhicule à droite - cédez le passage)",
            "5. Attention, dos d'âne!",
            "6. Continuer tout droit",
            "7. Feu rouge - Attendre vert puis tourner droite (Champs-Élysées)",
            "Notification: Limite à 50 km/h",
            "8. Continuer tout droit",
            "9. Rond-point - tourner à gauche",
            "10. Continuer tout droit", 
            "11. Piétons traversent",
            "12. Continuer tout droit",
            "13. Feu orange - ralentir et tourner gauche (Périphérique)",
            "Notification: Limite à 70 km/h (Périphérique)",
            "14. Continuer tout droit",
            "15. Dépasser véhicule lent (voie de droite)",
            "16. Bouchon - ralentir et avertir",
            "17. Insertion voie droite (sortie A6)",
            "18. Continuer vers tunnel",
            "Notification: Limite à 110 km/h",
            "19. Continuer tout droit (10 km)",
            "20. Laisser passer véhicule police",
            "21. Continuer tout droit",
            "22. Prendre sortie Orly",
            "Notification: Limite à 50 km/h",
            "23. Continuer tout droit",
            "24. Tourner droite (aéroport Orly)",
            "Notification: Limite à 30 km/h",
            "25. Continuer après rond-point",
            "26. Virer droite (parking)",
            "27. Stationnement marche arrière",
            "28. Couper le moteur"
        };
        
        // Exécution des instructions
        for (int i = 0; i < instructions.length; i++) {
            System.out.println("\n=== Étape " + (i+1) + "/28 ===");
            System.out.println(instructions[i]);
            System.out.print("Appuyez sur Entrée...");
            scanner.nextLine();
            
            // Vérification vitesse
            if (voiture.getVitesse() > limiteActuelle) {
                int excès = voiture.getVitesse() - limiteActuelle;
                int pointsPerdus = Math.min(5, 2 + excès / 5);
                System.out.println(" Excès de vitesse: " + excès + " km/h au dessus de la limite!");
                voiture.perdrePoints(pointsPerdus);
            }
            
            // Vérification pluie
            if (pluie && !voiture.isEssuieGlacesActives() && i > 5) {
                System.out.println(" Vous n'avez pas activé les essuie-glaces sous la pluie!");
                voiture.perdrePoints(5);
            }
            
            // Vérification points
            if (voiture.getPointsConduite() < 0) {
                System.out.println("\n TEST ÉCHOUÉ! Trop de points perdus.");
                System.out.println("Le moniteur reprend le volant.");
                return;
            }
            
            // Actions spécifiques
            switch(i) {
                case 0: voiture.demarrer(); break;
                case 2: 
                    voiture.decelerer(15); 
                    voiture.activerClignotantGauche();
                    voiture.controleVisibilite();
                    voiture.virerAGauche();
                    voiture.desactiverClignotants();
                    break;
                case 3:
                    System.out.print("Avez-vous cédé le passage? (o/n) ");
                    String reponse = scanner.nextLine();
                    if (!reponse.equalsIgnoreCase("o")) {
                        voiture.perdrePoints(3);
                        System.out.println(" Priorité non respectée!");
                    }
                    break;
                case 4:
                    if (voiture.getVitesse() > 15) {
                        System.out.println(" Ralentissement insuffisant pour le dos d'âne!");
                        voiture.perdrePoints(2);
                    }
                    voiture.decelerer(voiture.getVitesse() - 10);
                    break;
                case 6:
                    System.out.println("Feu rouge - Arrêt complet");
                    voiture.freiner();
                    System.out.println("Feu vert - Reprise de la marche");
                    voiture.activerClignotantDroit();
                    voiture.controleVisibilite();
                    voiture.virerADroite();
                    voiture.desactiverClignotants();
                    limiteActuelle = pluie ? LIMITES[1]-10 : LIMITES[1];
                    System.out.println("Nouvelle limite: " + limiteActuelle + " km/h");
                    break;
                case 8: 
                    voiture.activerClignotantGauche();
                    voiture.controleVisibilite();
                    voiture.virerAGauche();
                    voiture.desactiverClignotants();
                    break;
                case 10:
                    System.out.println("Arrêt pour piétons");
                    voiture.freiner();
                    break;
                case 12:
                    System.out.println("Feu orange - Ralentissement");
                    voiture.decelerer(20);
                    if (voiture.getVitesse() > 0) {
                        System.out.println(" Feu orange brûlé!");
                        voiture.perdrePoints(5);
                    }
                    voiture.activerClignotantGauche();
                    voiture.controleVisibilite();
                    voiture.virerAGauche();
                    voiture.desactiverClignotants();
                    limiteActuelle = pluie ? LIMITES[2]-10 : LIMITES[2];
                    System.out.println("Nouvelle limite: " + limiteActuelle + " km/h");
                    break;
                case 14:
                    voiture.activerClignotantGauche();
                    voiture.controleVisibilite();
                    voiture.changerVoieGauche();
                    voiture.accelerer(15);
                    voiture.activerClignotantDroit();
                    voiture.controleVisibilite();
                    voiture.changerVoieDroite();
                    voiture.desactiverClignotants();
                    break;
                case 15:
                    voiture.activerFeuxWarning();
                    voiture.decelerer(30);
                    voiture.freiner();
                    voiture.desactiverFeuxWarning();
                    break;
                case 17:
                    limiteActuelle = pluie ? LIMITES[3]-10 : LIMITES[3];
                    System.out.println("Nouvelle limite: " + limiteActuelle + " km/h");
                    if (!voiture.isFeuxCroisementActives()) {
                        System.out.println(" N'oubliez pas les feux de croisement dans le tunnel!");
                        voiture.perdrePoints(2);
                        voiture.activerFeuxCroisement();
                    }
                    break;
                case 19:
                    voiture.decelerer(20);
                    voiture.activerClignotantGauche();
                    voiture.controleVisibilite();
                    voiture.changerVoieGauche();
                    voiture.desactiverClignotants();
                    System.out.println("Véhicule prioritaire laissé passer");
                    break;
                case 22:
                    limiteActuelle = pluie ? LIMITES[1]-10 : LIMITES[1];
                    System.out.println("Nouvelle limite: " + limiteActuelle + " km/h");
                    break;
                case 24:
                    limiteActuelle = pluie ? LIMITES[0]-10 : LIMITES[0];
                    System.out.println("Nouvelle limite: " + limiteActuelle + " km/h");
                    break;
                case 26:
                    voiture.marcheArriere();
                    voiture.accelerer(5);
                    voiture.freiner();
                    voiture.finMarcheArriere();
                    break;
                case 27:
                    voiture.arreter();
                    break;
                default:
                    if (voiture.getVitesse() < limiteActuelle - 10) {
                        voiture.accelerer(5);
                    }
            }
            
            // Changement météo aléatoire
            if (random.nextInt(100) < 5) {
                pluie = !pluie;
                if (pluie) {
                    limiteActuelle = Math.max(20, limiteActuelle - 10);
                    System.out.println("\n La pluie commence! Nouvelle limite: " + limiteActuelle + " km/h");
                } else {
                    limiteActuelle += 10;
                    System.out.println("\n La pluie s'arrête. Nouvelle limite: " + limiteActuelle + " km/h");
                }
            }
        }
        
        // Résultat final
        System.out.println("\n=== TEST TERMINÉ ===");
        System.out.println("Points finaux: " + voiture.getPointsConduite() + "/40");
        if (voiture.getPointsConduite() >= 30) {
            System.out.println("FÉLICITATIONS! Vous avez réussi votre test de conduite!");
        } else {
            System.out.println(" DÉSOLÉ, vous avez échoué. Plus de pratique nécessaire.");
        }
        
        scanner.close();
    }
}
