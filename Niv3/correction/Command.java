import java.util.Scanner;

public class Command {
     public static boolean checkCommand(String chainCommandExpected, Road road, String command, Car car, int i){
        boolean verdict = false;

        // Découper la chaîne de commande attendue en tableau
        String[] expectedCommands = chainCommandExpected.split("-");
        if(i >= expectedCommands.length) {
            System.out.println("Vous avez dépassé le nombre d'instructions attendues vous pouvez continuer à en tapant NX.");
            verdict = true; // Si l'index dépasse la longueur du tableau, retourner false
            return verdict;
        }
        // Cette méthode peut être utilisée pour vérifier si la commande saisie correspond à la chaîne attendue
        if (expectedCommands[i].equals(command)&& !command.equals("HELP")) {

            verdict = true;
        } 

        return verdict;
    }

    public static boolean checkIfYouCanContinue(boolean allowed) {
        if(!allowed) {
            System.out.println("Vous n'avez pas fini toutes les instructions, vous ne pouvez pas passer à l'étape suivante.");
            return false;
        }else{

                        System.out.println("Fin de l'étape, passons à l'étape suivante.");
                                return true;
        }


    }

    public static int executeCommand(Car car,Road road, String expectedCommands, Scanner keyboard) {

        String cmd = "";// Lire la commande depuis le scanner
        int value = 0;
        int i = 0; // Index pour suivre la commande attendue
        boolean next = false;
        boolean allowed = true; // Variable pour vérifier si la commande est autorisée

        // En prend la valeur de la commande attendue  A=10

        String[] values = StringtoTab(expectedCommands);

        executeCommand:while(i < values.length) {

            System.out.println("Instruction: " + road.getInstruction());
            System.out.println("Entrez votre commande (ou 'HELP' pour l'aide): ");

            System.out.print("Votre commande :");
       
        cmd = keyboard.nextLine().toUpperCase();
    
        // Vérifier si la commande contient une valeur (par exemple, A=10)

        if(cmd.contains("=")) {

            System.out.println("Commande avec valeur détectée: " + cmd);

            String[] parts = cmd.split("=");
            if(parts.length == 2) {
                cmd = parts[0].trim();
                try {
                    value = Integer.parseInt(parts[1].trim());
                } catch (NumberFormatException e) {
                    System.out.println("Valeur invalide pour la commande, utilisez un nombre entier.");
                    return 0; // Retourne 0 pour indiquer une erreur
                }
            } else {
                System.out.println("Format de commande invalide, utilisez 'COMMAND=VALUE'.");
                return 0; // Retourne 0 pour indiquer une erreur
            }

                        System.out.println("Commande splité: " + cmd);

        }     

        System.out.println("*************Début Action*************");
        switch (cmd.toUpperCase()) {
            // Commandes de moteur
            case "D":
                
                car.startEngine();
                break;
            case "S":
                car.stopEngine();
                break;
            
            // Commandes de vitesse
            case "A":
                if(value <= 0) {
                    System.out.println("La valeur d'accélération doit être supérieure à 0.");
                    return 0; // Continue la boucle pour demander une nouvelle commande
                }

                car.speedUp(value < 10 ? value : 10);

                break;
            case "R":
                if(value <= 0) {
                    System.out.println("La valeur de décélération doit être supérieure à 0.");
                    return 0; // Continue la boucle pour demander une nouvelle commande
                }
                car.speedDown(value < 10 ? value : 10);
                break;
            case "F":
                car.brake();
                break;
            case "STOP":
                car.stop();
                break;
            
            // Commandes de boîte de vitesses
            case "V":
                car.gearbox(value);
                break;
            case "MA":
                car.reverseGear();
                break;

            // Commandes de direction
            case "TG":
                car.turnLeft();
                break;
            case "TD":
                car.turnRight();
                break;
            case "VG":
                car.changeLaneLeft();
                break;
            case "VD":
                car.changeLaneRight();
                break;

            // Commandes de contrôle visibilité
            case "VI":
                car.interiorVisibilityCheck();
                break;
            case "VE":
                car.exteriorVisibilityCheck();
                break;
            case "AM":
                car.blindSpotCheck();
                break;

            // Commandes de signalisation
            case "CG":
                car.leftIndicator();
                break;
            case "CD":
                car.rightIndicator();
                break;
            case "FC":
                car.headlights();
                break;
            case "EG":
                car.wipers();
                break;
            case "FD":
                car.hazardLights();
                break;
            case "K":
                car.horn();
                break;

            // Commande d'affichage de l'état
            case "STATUS":
                car.displayStatus();
                break;

            // Commande d'aide
            case "HELP":
                displayHelp();
                break; // Continue la boucle pour demander une nouvelle commande
            
                case "INFO" :
                road.displayRoadStatus();
                break;
            // Commande inconnue
            default:
                System.out.println("Commande inconnue. Tapez 'HELP' pour voir la liste des commandes.");
                break;
        }
        System.out.println("*************Fin Action*************");
        // Vérifier si la commande saisie correspond à la chaîne attendue
        // (seulement si ce n'est pas NX ou HELP)
        if(!cmd.equals("HELP") && !cmd.equals("INFO")) {
            allowed = checkCommand(expectedCommands, road, cmd, car, i);

            i++;
        }

        if(car.getCurrentSpeed() > road.getSpeedLimit()) {
            System.out.println("Vous avez dépassé la limitation de vitesse de " + road.getSpeedLimit() + " km/h.");
            System.out.println("Le moniteur prend le volant, le test est terminé.");
            return 2; // Retourne 2 pour indiquer que le test est terminé
        }

                if(allowed==false) {
            System.out.println("Vous n'avez pas suivi la bonne instructions, le moniteur prend le volant, le test est terminé.");
            return 2; // Retourne 2 pour indiquer que le test est terminé
        }

        }

            next = checkIfYouCanContinue(allowed);
        

        if(next==true){ return 1; }// Retourner 1 pour indiquer que la commande a été exécutée avec succès
        else{ return 0; }

    }
    

    /**
     * Affiche l'aide pour les commandes disponibles
     * Cette méthode peut être appelée lorsque l'utilisateur entre "HELP"
     */

     public static String[] StringtoTab(String command) {
        if (command.contains("-")) {
            String[] parts = command.split("-");
            return parts;
        }
        return new String[]{command}; // Retourne le command d'origine si '=' n'est pas trouvé
    }

    public static void displayHelp() {
        System.out.println("=== GUIDE DES COMMANDES ===");
        System.out.println("Moteur:");
        System.out.println("  D - Démarrer le moteur");
        System.out.println("  S - Stopper le moteur");
        System.out.println();
        System.out.println("Vitesse:");
        System.out.println("  A=X - Accélérer (X km/h, défaut: 10)");
        System.out.println("  R=X - Réduire vitesse (X km/h, défaut: 10)");
        System.out.println("  F - Freiner");
        System.out.println("  STOP - Arrêter complètement");
        System.out.println();
        System.out.println("Boîte de vitesses:");
        System.out.println("  V=X - Passer la vitesse X (0-5)");
        System.out.println("  MA - Marche arrière");
        System.out.println();
        System.out.println("Direction:");
        System.out.println("  TG - Tourner à gauche");
        System.out.println("  TD - Tourner à droite");
        System.out.println("  VG - Changer de voie à gauche");
        System.out.println("  VD - Changer de voie à droite");
        System.out.println();
        System.out.println("Contrôle visibilité:");
        System.out.println("  VI - Visibilité intérieure");
        System.out.println("  VE - Visibilité extérieure");
        System.out.println("  AM - Angles morts");
        System.out.println("  CV - Vérification de la visibilité");
        System.out.println();
        System.out.println("Signalisation:");
        System.out.println("  CG - Clignotant gauche");
        System.out.println("  CD - Clignotant droit");
        System.out.println("  FC - Feux de croisement");
        System.out.println("  EG - Essuie-glaces");
        System.out.println("  FD - Feux de détresse");
        System.out.println("  K - Klaxonner");
        System.out.println();
        System.out.println("Autres:");
        System.out.println("  STATUS - Afficher l'état de la voiture");
        System.out.println("  HELP - Afficher cette aide");
        System.out.println("==========================");
    }
    
    
}

