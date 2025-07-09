public class Main {
    public static void main(String[] args) {
        // Création d'un quizz
        String[] reponses = {
            "Paris",
            "Londres",
            "Berlin"
        };
        Quizz question1 = new Quizz("Quelle est la capitale de la France?", reponses, 0);
        
        // Affichage de la question
        question1.afficherQuestion();
        
        // Simulation de réponse (l'utilisateur choisit l'option 2)
        int score = question1.repondre(2);
        System.out.println("Score obtenu: " + score);
        
        // Si on essaie de répondre à nouveau
        score = question1.repondre(1);
        System.out.println("Score obtenu: " + score); // Affichera 0 car déjà terminée
    }
}