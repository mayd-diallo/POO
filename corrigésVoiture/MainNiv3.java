package corrigésVoiture;

import java.util.Scanner;

public class MainNiv3 {



    /*
     * Me créer en POO une classe Voiture :
     * 
     * 
     * Voiture :
     * 
     * Attribut :
     * Marque
     * Modele
     * Puissance (cv, la puissance aura une influence sur l'embrayage, plus la puissance est élevée, plus l'embrayage est sensible)
     * vitesse
     * 
     * Comportement :
     * 
     * Accelerer
     * Decelerer
     * Freiner
     * Arreter
     * Passer vitesse
     * Virer à gauche
     * Virer à droite
     * Changer de voie à gauche
     * Changer de voie à droite
     * Contrôle visibilité (Intérieur, extérieur, angles morts)
     * Clignotant à gauche
     * Clignotant à droite
     * Feu de croisement
     * Essuie-glace
     * Feu warning
     * Klaxonner
     * Marche arrière (On ira du principe que le conducteur manipule le volant pendant la marche arrière)
     * 
     * 
     * A l'aide d'un menu d'action, vous allez conduire une voiture, suivre des instructions d'un moniteur pour simuler un test de conduite.
     * Vous roulez sur Paris, vous devez suivre les instructions suivantes, vous devez respecter les limitations de vitesse et le code de la route:
     * 
     * Notification : Vous êtes sur une route limité à 30 km/h
     * 1. Démarrer la voiture
     * 2. Continuer tout droit
     * 3. Ralentir puis tourner à gauche (vous êtes prioritaire)
     * 4. Continuer tout droit (Il y a une voiture sur l'intersection à droite, vous n'êtes pas prioritaire)
     * 5. Attention, il y a un dos d'âne !
     * 6. Continuer tout droit
     * 7. Il y a un feu rouge, quand il passera au vert, tourner à droite sur l'avenue des Champs-Élysées
     * Notification : Vous êtes sur une route limité à 50 km/h
     * 8. Continuer tout droit
     * 9. Au rond point tourner à gauche
     * 10. Continuer tout droit 
     * 11. Attention, des piétons traversent
     * 12. Continuer tout droit
     * 13. Il y a un feu orange, ralentir, attendre que le feu soit rouge et tourner à gauche sur la voie d'insertion du Périphérique
     * Notification : Vous êtes sur le Périphérique, limité à 70 km/h
     * 14. Continuer tout droit
     * 15. Vous êtes sur la voie de droite, une voiture devant vous est trop lente, dépasser la voiture
     * 16. Attention, il y a un bouchon, veuillez ralentir , prévenir les autres conducteurs à l'arrière, puis s'arrêter
     * 17. Insérer vous dans la voie de droite, pour prendre la sortie A6
     * 18. Continuer tout droit, vous rendrez dans un tunnel
     * Notification : Vous êtes sur une route limité à 110 km/h.
     * 19. Continuer tout droit sur 10 km
     * 20. Vous êtes sur la voie de droite, un véhicule de police cherche à vous dépasser, laisser passer le véhicule en vous débordant sur la gauche
     * 21. Continuer tout droit
     * 22. Prenez la sortie en direction de Orly
     * Notification : Vous êtes sur une route limité à 50 km/h
     * 23. Continuer tout droit 
     * 24. Tournez à droite en direction de l'aéroport d'Orly 
     * Notification : Vous êtes sur une route limité à 30 km/h
     * 25. Continuer tout droit après le rond-point
     * 26. Virer à droite pour entrer dans le parking
     * 27. Garez la voiture en marche arrière
     * 28. Couper le moteur
     * Vous êtes arrivé(e) !!!
     * 
     * Si vous ne respectez pas le code de la route & les limitations de vitesse, le test de conduite a échoué et s'arrête, 
     * le moniteur prendra la main.
     * 
     * Bonus : Rajouter un système de points pour évaluer votre conduite, certaines erreurs ne sont pas éliminatoires mais vous feront perdre des points.
     * par exemple :
     * - Faire un excès de vitesse +5 km/h : -2 points
     * - Ne pas céder le passage : -3 points
     * - Brûler un feu orange : -5 points
     * - Ne pas laisser la priorité à droite : -3 points
     * - Ne pas mettre son clignotant : -2 point
     * - Ne pas contrôler les angles morts lors d'un changement de voie : -3 points
     * - Ne pas mettre de feu de croisement dans un tunnel : -2 points
     * - Ne pas activer les essuie-glaces en cas de pluie : -5 points
     * - Ne pas ralentir avant un dos d'âne : -2 points
     * 
     * Si vous arrivez à moins de 20 points, le test est terminé et vous avez échoué.
     * 
     * la pluie doit tomber aléatoirement pendant le test de conduite, si la pluie tombe, 
     * vous devez activer les essuie-glaces, et les limitations de vitesse sont réduites de 10 km/h.
     * 
     * Le test est sur 40 points, il faut au minimum 30 points pour réussir le test de conduite.
     * 
     */

     // Tableau des instructions avec les commandes attendues

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
        int nbInstruction=1;
        Scanner scanner = new Scanner(System.in);
        MainRoad:while(statut != 2) {

            for (String[] instruction : instructions) {

                statut = 0;
                road.setSpeedLimit(Integer.parseInt(instruction[2])); // Mettre à jour la limitation de vitesse

               while(statut == 0) {
                                road.setInstruction(instruction[0]);
            System.out.println("\n\n\n**********************DEBUT INSTRUCTION DU MONITEUR*************************** " );
                            
                statut = Command.executeCommand(maVoiture, road, instruction[1], scanner, nbInstruction);
                
            System.out.println("**********************FIN INSTRUCTION DU MONITEUR*************************** " );
            }
            nbInstruction++;
            
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