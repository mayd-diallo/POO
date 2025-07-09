package exercices.niv2;



/*  Exercice n°2 : 
 * 
 * Me créer en POO une classe Quizz avec les attributs suivants :
 * 
 * Question
 * Reponses (3 réponses dans un tableau)
 * Bonne réponse
 * Terminée (Vrai ou Faux)
 * 
 * Son comportement :
 * 
 * Afficher La Question et les réponses ( Utiliser les index pour afficher la question et les réponses correspondantes)
 * Répondre
 * Verdict (Bonne réponse = 1 point ou mauvaise réponse = 0 point , puis terminé = true)
 * Afficher la Bonne Réponse (Après avoir répondu si on a eu faux)
 * 
 * 
 * Diagramme UML et code Java exigés
 * 
 * 
 * Dans le main le but sera de créer des objets Quizz dynamiquement en fonction du nombres de questions créer dans un tableau ou une list avec leurs 3 réponses.
 * Vous aurez besoin d'une boucle 
 * 
 * 
 * Bonus : Créer la possibilité de mettre un minuteur qui arrête le quizz après la minute de votre choix, il faudra utiliser les localDatetime 
 * Par exemple vous avez défini 10 min de temps limite, si vous commencez le quiz à 10h10 alors à 10h20 le quiz est terminée
 * 
 * 
 */
public class Quizz {
    // Attributs
    private String question;
    private String[] reponses;
    private int bonneReponse;
    private boolean terminee;
    
    // Constructeur
    public Quizz(String question, String[] reponses, int bonneReponse) {
        this.question = question;
        this.reponses = reponses;
        this.bonneReponse = bonneReponse;
        this.terminee = false;
        
        // Validation des données
        if (reponses == null || reponses.length != 3) {
            throw new IllegalArgumentException("Il doit y avoir exactement 3 réponses");
        }
        if (bonneReponse < 0 || bonneReponse >= reponses.length) {
            throw new IllegalArgumentException("L'index de la bonne réponse est invalide");
        }
    }
    
    // Méthode pour afficher la question et les réponses
    public void afficherQuestion() {
        System.out.println("Question: " + question);
        System.out.println("Réponses possibles:");
        for (int i = 0; i < reponses.length; i++) {
            System.out.println((i + 1) + ". " + reponses[i]);
        }
    }
    
    // Méthode pour répondre à la question
    public int repondre(int reponseUtilisateur) {
        if (terminee) {
            System.out.println("Cette question a déjà été répondue.");
            return 0;
        }
        
        // Les indices affichés commencent à 1, donc on ajuste
        reponseUtilisateur -= 1;
        
        if (reponseUtilisateur == bonneReponse) {
            terminee = true;
            System.out.println("Bonne réponse !");
            return 1;
        } else {
            terminee = true;
            System.out.println("Mauvaise réponse.");
            afficherBonneReponse();
            return 0;
        }
    }
    
    // Méthode pour afficher la bonne réponse
    public void afficherBonneReponse() {
        System.out.println("La bonne réponse était: " + (bonneReponse + 1) + ". " + reponses[bonneReponse]);
    }
    
    // Getters
    public String getQuestion() {
        return question;
    }
    
    public String[] getReponses() {
        return reponses;
    }
    
    public int getBonneReponse() {
        return bonneReponse;
    }
    
    public boolean isTerminee() {
        return terminee;
    }
}