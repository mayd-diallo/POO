package Niv3;

import java.util.Scanner;

public class main {
    // Attributs
    private static String marque;
    private static String modele;
    private static int puissance;
    private static int vitesse;
    private static boolean moteurAllume = false;
    private static boolean embrayageEngage = false;
    private static boolean clignotantGauche = false;
    private static boolean clignotantDroit = false;
    private static boolean feuxCroisement = false;
    private static boolean warning = false;
    private static int limiteVitesse = 0;
    private static Scanner scanner = new Scanner(System.in);
    /*constructeur */
    public main(String marque, String modele, int puissance) {
        this.marque =marque;
        this.modele =modele;
        this.puissance = puissance;
        this.vitesse = 0;
        this.moteurAllume = false;
        this.limiteVitesse = 0;
    }

    public static void main(String[] args) {
        initialiserVoiture();
        demarrerTestConduite();
    }

    private static void initialiserVoiture() {
        marque = "Peugeot";
        modele = "208";
        puissance = 75;
        vitesse = 0;
    }

    private static void demarrerTestConduite() {
        System.out.println("TEST DE CONDUITE ");
        System.out.println("Notification : Vous êtes sur une route limitée à 30 km/h");
        limiteVitesse = 30;

        // Instructions du test
        executerInstruction(1, "Démarrer la voiture");
        executerInstruction(2, "Continuer tout droit");
        executerInstruction(3, "Ralentir puis tourner à gauche (vous êtes prioritaire)");
        executerInstruction(4, "Continuer tout droit (Il y a une voiture sur l'intersection à droite, vous n'êtes pas prioritaire)");
        executerInstruction(5, "Attention, il y a un dos d'âne !");
        executerInstruction(6, "Continuer tout droit");
        executerInstruction(7, "Il y a un feu rouge, quand il passera au vert, tourner à droite sur l'avenue des Champs-Élysées");
        limiteVitesse = 50;
        System.out.println("Notification : Vous êtes sur une route limitée à 50 km/h");
        executerInstruction(8, "Continuer tout droit");
        executerInstruction(9, "Au rond point tourner à gauche");
        executerInstruction(10, "Continuer tout droit");
        executerInstruction(11, "Attention, des piétons traversent");
        executerInstruction(12, "Continuer tout droit");
        executerInstruction(13, "Il y a un feu orange, ralentir, attendre que le feu soit rouge et tourner à gauche sur la voie d'insertion du Périphérique");
        limiteVitesse = 70;
        System.out.println("Notification : Vous êtes sur le Périphérique, limité à 70 km/h");
        executerInstruction(14, "Continuer tout droit");
        executerInstruction(15, "Vous êtes sur la voie de droite, une voiture devant vous est trop lente, dépasser la voiture");
        executerInstruction(16, "Attention, il y a un bouchon, veuillez ralentir, prévenir les autres conducteurs à l'arrière, puis s'arrêter");
        executerInstruction(17, "Insérer vous dans la voie de droite, pour prendre la sortie A6");
        executerInstruction(18, "Continuer tout droit, vous rendrez dans un tunnel");
        limiteVitesse = 110;
        System.out.println("Notification : Vous êtes sur une route limitée à 110 km/h.");
        executerInstruction(19, "Continuer tout droit sur 10 km");
        executerInstruction(20, "Vous êtes sur la voie de droite, un véhicule de police cherche à vous dépasser, laisser passer le véhicule en vous débordant sur la gauche");
        executerInstruction(21, "Continuer tout droit");
        executerInstruction(22, "Prenez la sortie en direction de Orly");
        limiteVitesse = 50;
        System.out.println("Notification : Vous êtes sur une route limitée à 50 km/h");
        executerInstruction(23, "Continuer tout droit");
        executerInstruction(24, "Tournez à droite en direction de l'aéroport d'Orly");
        limiteVitesse = 30;
        System.out.println("Notification : Vous êtes sur une route limitée à 30 km/h");
        executerInstruction(25, "Continuer tout droit après le rond-point");
        executerInstruction(26, "Virer à droite pour entrer dans le parking");
        executerInstruction(27, "Garez la voiture en marche arrière");
        executerInstruction(28, "Couper le moteur");
        
        System.out.println("Vous êtes arrivé(e) !!!");
    }

    private static void executerInstruction(int numero, String instruction) {
        System.out.println("\n" + numero + ". " + instruction);
        System.out.print("Appuyez sur Entrée pour exécuter...");
        scanner.nextLine();
        
        switch(numero) {
            case 1: demarrer(); break;
            case 2: continuerToutDroit(); break;
            case 3: ralentir(); virerGauche(); break;
            case 4: continuerToutDroit(); break;
            case 5: dosDAne(); break;
            case 6: continuerToutDroit(); break;
            case 7: feuRouge(); break;
            case 8: continuerToutDroit(); break;
            case 9: virerGauche(); break;
            case 10: continuerToutDroit(); break;
            case 11: pietons(); break;
            case 12: continuerToutDroit(); break;
            case 13: feuOrange(); break;
            case 14: continuerToutDroit(); break;
            case 15: depasser(); break;
            case 16: bouchon(); break;
            case 17: changerVoieDroite(); break;
            case 18: continuerToutDroit(); break;
            case 19: continuerToutDroit(); break;
            case 20: laisserPasser(); break;
            case 21: continuerToutDroit(); break;
            case 22: changerVoieDroite(); break;
            case 23: continuerToutDroit(); break;
            case 24: virerDroite(); break;
            case 25: continuerToutDroit(); break;
            case 26: virerDroite(); break;
            case 27: marcheArriere(); break;
            case 28: arreter(); break;
        }
        
        verifierVitesse();
    }

    // Comportements
    private static void demarrer() {
        moteurAllume = true;
        System.out.println("Vous avez démarré le moteur.");
    }

    private static void arreter() {
        moteurAllume = false;
        vitesse = 0;
        System.out.println("Vous avez coupé le moteur et arrêté la voiture.");
    }

    private static void accelerer() {
        if (moteurAllume) {
            vitesse += 10;
            System.out.println("Vous accélérez. Vitesse actuelle: " + vitesse + " km/h");
        } else {
            System.out.println("Le moteur n'est pas allumé !");
        }
    }

    private static void ralentir() {
        vitesse = Math.max(0, vitesse - 10);
        System.out.println("Vous ralentissez. Vitesse actuelle: " + vitesse + " km/h");
    }

    private static void freiner() {
        vitesse = 0;
        System.out.println("Vous freinez complètement. Vitesse: 0 km/h");
    }

    private static void marcheArriere() {
        vitesse = -5;
        System.out.println("Vous êtes en marche arrière.");
    }

    private static void embrayer() {
        embrayageEngage = true;
        System.out.println("Embrayage engagé.");
    }

    private static void debrayer() {
        embrayageEngage = false;
        System.out.println("Embrayage débrayé.");
    }

    private static void virerGauche() {
        System.out.println("Vous tournez à gauche.");
    }

    private static void virerDroite() {
        System.out.println("Vous tournez à droite.");
    }

    private static void changerVoieGauche() {
        System.out.println("Vous changez de voie vers la gauche.");
    }

    private static void changerVoieDroite() {
        System.out.println("Vous changez de voie vers la droite.");
    }

    private static void controleVisibilite() {
        System.out.println("Vous vérifiez les rétroviseurs et angles morts.");
    }

    private static void activerClignotantGauche() {
        clignotantGauche = true;
        System.out.println("Clignotant gauche activé.");
    }

    private static void activerClignotantDroit() {
        clignotantDroit = true;
        System.out.println("Clignotant droit activé.");
    }

    private static void desactiverClignotants() {
        clignotantGauche = false;
        clignotantDroit = false;
        System.out.println("Clignotants désactivés.");
    }

    private static void activerFeuxCroisement() {
        feuxCroisement = true;
        System.out.println("Feux de croisement activés.");
    }

    private static void activerWarning() {
        warning = true;
        System.out.println("Feux warning activés.");
    }

    private static void desactiverWarning() {
        warning = false;
        System.out.println("Feux warning désactivés.");
    }

    private static void klaxonner() {
        System.out.println("Pouet ! Pouet !");
    }

    private static void continuerToutDroit() {
        System.out.println("Vous continuez tout droit.");
    }

    // Méthodes pour les situations spécifiques
    private static void dosDAne() {
        ralentir();
        System.out.println("Vous passez le dos d'âne en douceur.");
    }

    private static void feuRouge() {
        freiner();
        System.out.println("Vous vous arrêtez au feu rouge...");
        System.out.print("Appuyez sur Entrée quand le feu passe au vert...");
        scanner.nextLine();
        System.out.println("Le feu est vert, vous repartez.");
        virerDroite();
    }

    private static void pietons() {
        freiner();
        System.out.println("Vous laissez passer les piétons...");
        System.out.print("Appuyez sur Entrée quand les piétons ont fini de traverser...");
        scanner.nextLine();
        System.out.println("Vous repartez.");
    }

    private static void feuOrange() {
        ralentir();
        System.out.println("Vous ralentissez pour le feu orange...");
        System.out.print("Appuyez sur Entrée quand le feu passe au rouge...");
        scanner.nextLine();
        System.out.println("Le feu est rouge, vous tournez à gauche.");
        virerGauche();
    }

    private static void depasser() {
        activerClignotantGauche();
        changerVoieGauche();
        accelerer();
        System.out.println("Vous dépassez la voiture lente.");
        changerVoieDroite();
        desactiverClignotants();
    }

    private static void bouchon() {
        activerWarning();
        ralentir();
        freiner();
        System.out.println("Vous êtes arrêté dans le bouchon.");
        desactiverWarning();
    }

    private static void laisserPasser() {
        activerClignotantGauche();
        System.out.println("Vous vous déportez légèrement à gauche pour laisser passer la police.");
        desactiverClignotants();
    }

    private static void verifierVitesse() {
        if (vitesse > limiteVitesse) {
            System.out.println("ERREUR : Vous roulez à " + vitesse + " km/h dans une zone limitée à " + limiteVitesse + " km/h !");
            System.out.println("Le moniteur prend la main. Test échoué !");
            System.exit(0);
        }
    }
}