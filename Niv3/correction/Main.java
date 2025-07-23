import java.util.Scanner;

import javafx.scene.web.HTMLEditorSkin.Command;

public class Main {
      public static final String[][] instructions =
     {{"1. Démarrer la voiture","D","30"},
      {"2. Continuer tout droit","A-V","30"},
      {"3. Ralentir puis tourner à gauche (vous êtes prioritaire)","CV-R-TG","30"},
      {"4. Continuer tout droit (Il y a une voiture sur l'intersection à droite, vous n'êtes pas prioritaire)","CV-R-F-STOP-A","30"},
      {"5. Attention, il y a un dos d'âne !","R","30"},
      {"6. Continuer tout droit","A","30"},
      {"7. Il y a un feu rouge, quand il passera au vert, tourner à droite sur l'avenue des Champs-Élysées","R-V-F-STOP-CV-A-TD","50"},
      {"8. Continuer tout droit","A-V","50"},
      {"9. Au rond point tourner à gauche","R-TD-CV-TG","50"},
      {"10. Continuer tout droit","A","50"},
      {"11. Attention, des piétons traversent","R-F-STOP-A","50"},
      {"12. Continuer tout droit","A-V","50"},
      {"13. Il y a un feu orange, ralentir, attendre que le feu soit rouge et tourner à gauche sur la voie d'insertion du Périphérique","R-F-STOP-CV-A-TG","50"},
      {"14. Continuer tout droit","A-V","90"},
      {"15. Vous êtes sur la voie de droite, une voiture devant vous est trop lente, dépasser la voiture","CV-VG-A-VD","90"},
      {"16. Attention, il y a un bouchon, veuillez ralentir , prévenir les autres conducteurs à l'arrière, puis s'arrêter","CV-R-FD-F-STOP","90"},
      {"17. Insérer vous dans la voie de droite, pour prendre la sortie A6","A-CV-TD","70"},
      {"18. Continuer tout droit, vous rendrez dans un tunnel","A-V-FC","110"},
      {"19. Continuer tout droit sur 10 km","A-V","110"},
      {"20. Vous êtes sur la voie de droite, un véhicule de police cherche à vous dépasser, laisser passer le véhicule en vous débordant sur la gauche","CV-VG-A-VD","110"},
      {"21. Continuer tout droit","A-V","110"},
      {"22. Prenez la sortie en direction de Orly","A-CV-TD","70"},
      {"23. Continuer tout droit ","A-V","50"},
      {"24. Tournez à droite en direction de l'aéroport d'Orly ","R-V-TD-A","50"},
      {"25. Continuer tout droit après le rond-point","R-TD-A","50"},
      {"26. Virer à droite pour entrer dans le parking","R-V-TD","50"},
      {"27. Garez la voiture en marche arrière","MA","30"},
      {"28. Couper le moteur","S","30"}
        };

    

    public static void main(String[] args) {

        // Ici, vous pouvez créer des instances de la classe Voiture et simuler le test de conduite
        // Par exemple :
        Car maVoiture = new Car("Toyota", "Corolla", 150, 120);
        Road road = new Road();
        int statut = 0;
        Scanner scanner = new Scanner(System.in);
        MainRoad:while(statut != 2) {

            for (String[] instruction : instructions) {

                statut = 0;
                road.setSpeedLimit(Integer.parseInt(instruction[2])); // Mettre à jour la limitation de vitesse

               while(statut == 0) {
                                road.setInstruction(instruction[0]);
            System.out.println("\n\n\n**********************DEBUT INSTRUCTION DU MONITEUR*************************** " );

                statut = Command.executeCommand(maVoiture, road, instruction[1], scanner);
            System.out.println("**********************FIN INSTRUCTION DU MONITEUR*************************** " );
            }

            
            if(statut == 2) {
                System.out.println("Le test de conduite est terminé.");
                            road.setTheEnd(true);
                break MainRoad; // Sortir de la boucle principale si le test est terminé


            }
            


            }




        }
            road.setTheEnd(true);


    }
      
} 

