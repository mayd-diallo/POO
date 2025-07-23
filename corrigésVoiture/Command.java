package corrigésVoiture;

import java.util.Scanner;


public class Command {

    public static boolean checkCommand(String chainCommandExpected, Road road, String command, Car car, int i){
        boolean verdict = false;

        // Découper la chaîne de commande attendue en tableau
        String[] expectedCommands = chainCommandExpected.split("-");
        if(i >= expectedCommands.length) {
            System.out.println(Colors.success("Vous avez dépassé le nombre d'instructions attendues vous pouvez continuer à en tapant NX."));
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
            System.out.println(Colors.warning("Vous n'avez pas fini toutes les instructions, vous ne pouvez pas passer à l'étape suivante."));
            return false;
        }else{
            System.out.println(Colors.success("Fin de l'étape, passons à l'étape suivante."));
            return true;
        }
    }

    public static int executeCommand(Car car,Road road, String expectedCommands, Scanner keyboard, int nbInstruction) {

        String cmd = "";// Lire la commande depuis le scanner
        int value = 0;
        int i = 0; // Index pour suivre la commande attendue
        boolean next = false;
        boolean allowed = true; // Variable pour vérifier si la commande est autorisée
         // Variable pour vérifier si la commande est inconnue
        // En prend la valeur de la commande attendue  A=10
        

        String[] commands = StringtoTab(expectedCommands);

        if(car.getRegime() > 0) {
            int vitesse;
            System.out.println(Colors.warning("Boite de vitesse : " + car.getCurrentGear()));

            switch (car.getRegime()) {
                case 1:
                    System.out.println(Colors.warning("Moniteur : Attention, vous êtes en sous-régime !"));
                    System.out.println(Colors.help("Veuillez choisir la bonne vitesse entre 1 et 5."));
                    vitesse=keyboard.nextInt();
                    keyboard.nextLine();
                    relatedCommand("V", car, road, vitesse);
                    return 0;
                case 2:
                    System.out.println(Colors.error("Moniteur : Attention, vous êtes en surrégime !"));
                    System.out.println(Colors.help("Veuillez choisir la bonne vitesse entre 1 et 5."));
                    vitesse=keyboard.nextInt();
                    keyboard.nextLine();
                    relatedCommand("V", car, road, vitesse);
                    return 0;
                default:
                    break;
            }
      
        }

        // Vérification de la pluie avant de commencer
       if(road.setRandomRain()&& car.isWipers()==false){
             System.out.println(Colors.warning("Moniteur : Il pleut, il faut mettre les essuie-glaces."));
            System.out.println(Colors.instruction("Appuyez sur E pour activer les essuie-glaces..."));
            cmd = keyboard.nextLine().toUpperCase(); // Attendre que l'utilisateur appuie sur Entrée
            relatedCommand(cmd, car, road, 0);
            penaltyCommand(cmd,"E",0,road);

            if(car.isWipers()==true)   {

                System.out.println(Colors.success("Les essuie-glaces sont activés."));
            }else{

                System.out.println(Colors.error("Les essuie-glaces ne sont pas activés,Le moniteur les active à votre place mais vous avez -5 points."));
                car.setWipers(true);
            }
        }

        while(i < commands.length) {

            boolean unknownCommand = false;

            System.out.println(Colors.instruction("Instruction: " + road.getInstruction()));
                        System.out.println(Colors.header("Entrez votre commande (ou 'HELP' pour l'aide): "));

            System.out.println("Limitation de vitesse actuelle: " + Colors.warning(road.getSpeedLimit() + " km/h"));
            System.out.println("Aide du moniteur: " + Colors.help(MonitorHelp(commands[i], true)));

            System.out.print( Colors.colorize("\nVitesse actuelle: " + car.getCurrentSpeed() + " km/h \n Points restants: " + road.getPoints() + " ", Colors.CYAN)+"\nVotre commande: ");

        cmd = keyboard.nextLine().toUpperCase();
    
        // Vérifier si la commande contient une valeur (par exemple, A=10)

        if(cmd.contains("=")) {

            System.out.println(Colors.info("Commande avec valeur détectée: " + cmd));

            String[] parts = cmd.split("=");
            if(parts.length == 2) {
                cmd = parts[0].trim();
                try {
                    value = Integer.parseInt(parts[1].trim());
                } catch (NumberFormatException e) {
                    System.out.println(Colors.error("Valeur invalide pour la commande, utilisez un nombre entier."));
                    return 0; // Retourne 0 pour indiquer une erreur
                }
            } else {
                System.out.println(Colors.error("Format de commande invalide, utilisez 'COMMAND=VALUE'."));
                return 0; // Retourne 0 pour indiquer une erreur
            }

            System.out.println(Colors.success("Commande splittée: " + cmd));

        }     

   
        // Vérifier si la commande est une commande liée
        unknownCommand = relatedCommand(cmd, car, road, value);

        boolean penalty = penaltyCommand(cmd, commands[i], nbInstruction, road);
                if(road.getPoints() < 25) {

            System.out.println(Colors.warning("Attention, vous avez moins de 25 points restants !"));
            if(road.getPoints() < 20) {
                System.out.println(Colors.error("Vous avez en-dessous de 20 points, le test est terminé."));
                return 2; // Retourne 2 pour indiquer que le test est terminé
            }
          
        }
        System.out.println(Colors.colorize("*************Fin Action*************\n\n\n", Colors.BOLD + Colors.RED));
        // Vérifier si la commande saisie correspond à la chaîne attendue
        // (seulement si ce n'est pas NX ou HELP)
        if(!cmd.equals("HELP") && !cmd.equals("INFO") && unknownCommand == false && penalty == false) {
            allowed = checkCommand(expectedCommands, road, cmd, car, i);

            i++;
        }

        if(car.getCurrentSpeed() > road.getSpeedLimit()) {
            System.out.println(Colors.error("Vous avez dépassé la limitation de vitesse de " + road.getSpeedLimit() + " km/h."));
            System.out.println(Colors.colorize("Le moniteur prend le volant, le test est terminé.", Colors.BOLD + Colors.RED_BG + Colors.WHITE));
            return 2; // Retourne 2 pour indiquer que le test est terminé
        }

        if(allowed==false) {
            System.out.println(Colors.error("Vous n'avez pas suivi la bonne instructions, le moniteur prend le volant, le test est terminé."));
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

     public static boolean relatedCommand(String cmd, Car car, Road road,int value) {

             System.out.println(Colors.colorize("*************Début Action*************\n\n\n", Colors.BOLD + Colors.GREEN));
        switch (cmd.toUpperCase()) {
            // Commandes de moteur
            case "D":
                
                car.startEngine();
                return false;
            case "S":
                car.stopEngine();
                return false;

            // Commandes de vitesse
            case "A":
                if(value <= 0) {
                    System.out.println(Colors.warning("La valeur d'accélération doit être supérieure à 0."));
                    return false; // Continue la boucle pour demander une nouvelle commande
                }

                car.speedUp(value > 1 ? value : 1);

                return false;
            case "R":
                if(value <= 0) {
                    System.out.println(Colors.warning("La valeur de décélération doit être supérieure à 0."));
                    return false; // Continue la boucle pour demander une nouvelle commande
                }
                car.speedDown(value > 1 ? value : 1);
                return false;
            case "F":
                car.brake();
                return false;
            case "STOP":
                car.stop();
                return false;

            // Commandes de boîte de vitesses
            case "V":
                car.gearbox(value);
                
                return false;
            case "MA":
                car.reverseGear();
                return false;

            // Commandes de direction
            case "TG":
                car.turnLeft();
                return false;
            case "TD":
                car.turnRight();
                return false;
            case "VG":
                car.changeLaneLeft();
                return false;

            case "VD":
                car.changeLaneRight();
                return false;

            // Commandes de contrôle visibilité
            case "VI":
                car.interiorVisibilityCheck();
                return false;
            case "VE":
                car.exteriorVisibilityCheck();
                return false;
            case "AM":
                car.blindSpotCheck();
                return false;

            case "CV":
                
                car.checkVisibility();
                return false;

            // Commandes de signalisation
            case "CG":
                car.leftIndicator();
                return false;
            case "CD":
                car.rightIndicator();
                return false;
            case "FC":
                car.headlights();
                return false;
            case "EG":
                car.wipers();
                return false;
            case "FD":
                car.hazardLights();
                return false;
            case "K":
                car.horn();
                return false;
            case "E":
                car.setWipers(true);
                return false;

            // Commande d'affichage de l'état
            case "STATUS":
                car.displayStatus();
                return false;
            

            // Commande d'aide
            case "HELP":
                displayHelp();
                return false; // Continue la boucle pour demander une nouvelle commande

            case "INFO" :
                road.displayRoadStatus();
                return false; // Continue la boucle pour demander une nouvelle commande
            // Commande inconnue
            default:
                System.out.println(Colors.error("Commande inconnue. Tapez 'HELP' pour voir la liste des commandes."));
                
                return true; // Continue la boucle pour demander une nouvelle commande

            
        }
     }

     public static String[] StringtoTab(String command) {
        if (command.contains("-")) {
            String[] parts = command.split("-");
            return parts;
        }
        return new String[]{command}; // Retourne le command d'origine si '=' n'est pas trouvé
    }

    public static String MonitorHelp(String command,boolean enableMonitorHelp) {
        
        if (!enableMonitorHelp) {
            return "Aide du moniteur désactivée.";
        }
        switch(command) {
            case "D":
                return "Démarrer le moteur";
            case "S":
                return "Stopper le moteur";
            case "A":
                return "Accélérer (X km/h, défaut: 10)";
            case "R":
                return "Réduire vitesse (X km/h, défaut: 10)";
            case "F":
                return "Freiner";
            case "STOP":
                return "Arrêter complètement";
            case "V":
                return "Passer la vitesse X (0-5)";
            case "MA":
                return "Marche arrière";
            case "TG":
                return "Tourner à gauche";
            case "TD":
                return "Tourner à droite";
            case "VG":
                return "Changer de voie à gauche";
            case "VD":
                return "Changer de voie à droite";
            case "VI":
                return "Visibilité intérieure";
            case "VE":
                return "Visibilité extérieure";
            case "AM":
                return "Angles morts";
            case "CV":
                return "Vérification de la visibilité";
            case "CG":
                return "Clignotant gauche";
            case "CD":
                return "Clignotant droit";
            case "FC":
                return "Feux de croisement";
            case "EG":
                return "Essuie-glaces";
            case "FD":
                return "Feux de détresse";
            case "K":
                return "Klaxonner";
            case "STATUS":
                return "Afficher l'état de la voiture";
            default:
                return "Erreur : Commande inconnue"; // Commande inconnue
        }
    }


    

    public static boolean compareCommands(String chosenCommand, String command, String expectedCommand) {
        // Cette méthode peut être utilisée pour comparer deux chaînes de commande
        return (!command.equals(chosenCommand)) && (expectedCommand.equals(chosenCommand));
    }

    public static boolean testPenaltyCommand( String chosenCommand, String command, String expectedCommand, int penalty,String errorMessage, Road road) {
        System.out.println("seconde Vérification de la commande : " + chosenCommand);
                 boolean verdict=false;
        
                 if (compareCommands(chosenCommand, command, expectedCommand)== true) {
                    
                    road.setPoints(road.getPoints() - penalty); // Réduire les points du road
                    System.out.println(Colors.error(errorMessage));
                    System.out.println("Pénalité ajoutée: " + penalty);
                    verdict = true; // Retourne true si la commande est incorrecte
                    System.out.println("résultat verdict: " + verdict);
                    return verdict;
                }else{

                return verdict; // Retourne false si la commande est incorrecte
                }
                

    }

    public static String getErrorMessage(String command) {

        switch(command) {
            case "D":
                return "Veuillez démarrer la voiture !";
            case "S":
                return "Veuillez stopper la voiture !";
            case "A":
                return "Veuillez accélérer !";
            case "R":
                return "Veuillez réduire la vitesse !";
            case "F":
                return "Veuillez freiner !";
            case "STOP":
                return "Veuillez arrêter complètement la voiture !";
            case "V":
                return "Veuillez passer à la vitesse X !";
            case "MA":
                return "Veuillez passer en marche arrière !";
            case "TG":
                return "Veuillez tourner à gauche !";
            case "TD":
                return "Veuillez tourner à droite !";
            case "VG":
                return "Veuillez changer de voie à gauche !";
            case "VD":
                return "Veuillez changer de voie à droite !";
            case "VI":
                return "Veuillez vérifier la visibilité intérieure !";
            case "VE":
                return "Veuillez vérifier la visibilité extérieure !";
            case "AM":
                return "Veuillez vérifier les angles morts !";
            case "CV":
                return "Veuillez vérifier la visibilité !";
            case "CG":
                return "Veuillez activer le clignotant gauche !";
            case "CD":  
                return "Veuillez activer le clignotant droit !";
            case "FC":
                return "Veuillez allumer les feux de croisement !";
            case "EG":
                return "Vous avez oublié les essuie-glaces, le moniteur les mets à votre place mais vous aurez -5 points !";

            default:
                return "Commande inconnue.";
        }   

    }

    public static int getPenalty(String command) {
        // Cette méthode peut être utilisée pour obtenir la pénalité associée à une commande
        switch(command) {
            case "D":
                return 0; // Pénalité pour démarrer le moteur
            case "S":
                return 30; // Pénalité pour stopper le moteur
            case "A":
                return 5; // Pénalité pour accélérer
            case "R":
                return 10; // Pénalité pour réduire la vitesse
            case "F":
                return 30; // Pénalité pour freiner
            case "STOP":
                return 40; // Pénalité pour arrêter complètement
            case "V":
                return 10; // Pénalité pour changer de vitesse
            case "MA":
                return 5; // Pénalité pour marche arrière
            case "TG":
                return 30; // Pénalité pour tourner à gauche
            case "TD":
                return 30; // Pénalité pour tourner à droite
            case "VG":
                return 20; // Pénalité pour changer de voie à gauche
            case "VD":
                return 20; // Pénalité pour changer de voie à droite
            case "VI":
                return 10; // Aucune pénalité pour visibilité intérieure
            case "VE":
                return 10; // Aucune pénalité pour visibilité extérieure
            case "AM":
                return 10; // Aucune pénalité pour angles morts 
            case "CV":
                return 10; // Aucune pénalité pour vérification de la visibilité
            case "CG":
                return 5; // Pénalité pour clignotant gauche
            case "CD":
                return 5; // Pénalité pour clignotant droit
            case "FC":
                return 10; // Pénalité pour feux de croisement
            case "EG":
                return 5; // Pénalité pour essuie-glaces
            case "FD":
                return 10; // Pénalité pour feux de détresse
            case "K":
                return 0; // Aucune pénalité pour klaxonner
            case "E":
                return 5; //Pénalité pour essuie-glaces
            default:
                return 0; // Aucune pénalité pour les autres commandes
        }
    }


    public static boolean penaltyCommand(String command,String expectedCommand,int indexInstruction,Road road) {

        System.out.println("Instruction n°" + indexInstruction + " Vérification penalité pour la commande : " + expectedCommand);
        boolean error = false;

        error=testPenaltyCommand( expectedCommand,command, expectedCommand, getPenalty(expectedCommand),getErrorMessage(expectedCommand),road);

  
        return error; // Aucune différence trouvée
    }

    public static void displayHelp() {
        System.out.println(Colors.header("=== GUIDE DES COMMANDES ==="));
        System.out.println(Colors.colorize("Moteur:", Colors.BOLD + Colors.YELLOW));
        System.out.println("  " + Colors.colorize("D", Colors.GREEN) + " - Démarrer le moteur");
        System.out.println("  " + Colors.colorize("S", Colors.RED) + " - Stopper le moteur");
        System.out.println();
        System.out.println(Colors.colorize("Vitesse:", Colors.BOLD + Colors.YELLOW));
        System.out.println("  " + Colors.colorize("A=X", Colors.GREEN) + " - Accélérer (X km/h, défaut: 10)");
        System.out.println("  " + Colors.colorize("R=X", Colors.YELLOW) + " - Réduire vitesse (X km/h, défaut: 10)");
        System.out.println("  " + Colors.colorize("F", Colors.RED) + " - Freiner");
        System.out.println("  " + Colors.colorize("STOP", Colors.RED) + " - Arrêter complètement");
        System.out.println();
        System.out.println(Colors.colorize("Boîte de vitesses:", Colors.BOLD + Colors.YELLOW));
        System.out.println("  " + Colors.colorize("V=X", Colors.BLUE) + " - Passer la vitesse X (0-5)");
        System.out.println("  " + Colors.colorize("MA", Colors.PURPLE) + " - Marche arrière");
        System.out.println();
        System.out.println(Colors.colorize("Direction:", Colors.BOLD + Colors.YELLOW));
        System.out.println("  " + Colors.colorize("TG", Colors.CYAN) + " - Tourner à gauche");
        System.out.println("  " + Colors.colorize("TD", Colors.CYAN) + " - Tourner à droite");
        System.out.println("  " + Colors.colorize("VG", Colors.BLUE) + " - Changer de voie à gauche");
        System.out.println("  " + Colors.colorize("VD", Colors.BLUE) + " - Changer de voie à droite");
        System.out.println();
        System.out.println(Colors.colorize("Contrôle visibilité:", Colors.BOLD + Colors.YELLOW));
        System.out.println("  " + Colors.colorize("VI", Colors.PURPLE) + " - Visibilité intérieure");
        System.out.println("  " + Colors.colorize("VE", Colors.PURPLE) + " - Visibilité extérieure");
        System.out.println("  " + Colors.colorize("AM", Colors.PURPLE) + " - Angles morts");
        System.out.println("  " + Colors.colorize("CV", Colors.PURPLE) + " - Vérification de la visibilité");
        System.out.println();
        System.out.println(Colors.colorize("Signalisation:", Colors.BOLD + Colors.YELLOW));
        System.out.println("  " + Colors.colorize("CG", Colors.GREEN) + " - Clignotant gauche");
        System.out.println("  " + Colors.colorize("CD", Colors.GREEN) + " - Clignotant droit");
        System.out.println("  " + Colors.colorize("FC", Colors.WHITE) + " - Feux de croisement");
        System.out.println("  " + Colors.colorize("EG", Colors.CYAN) + " - Essuie-glaces");
        System.out.println("  " + Colors.colorize("FD", Colors.RED) + " - Feux de détresse");
        System.out.println("  " + Colors.colorize("K", Colors.YELLOW) + " - Klaxonner");
        System.out.println();
        System.out.println(Colors.colorize("Autres:", Colors.BOLD + Colors.YELLOW));
        System.out.println("  " + Colors.colorize("STATUS", Colors.BLUE) + " - Afficher l'état de la voiture");
        System.out.println("  " + Colors.colorize("HELP", Colors.PURPLE) + " - Afficher cette aide");
        System.out.println(Colors.header("=========================="));
    }
    
    
}
