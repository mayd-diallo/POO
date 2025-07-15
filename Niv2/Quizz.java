package Niv2;
import java.time.LocalDateTime;
import java.time.Duration;
import java.util.Scanner;

public class Quizz {
    private String question;
    private String[] reponses;
    private int bonneReponse;
    private boolean terminee;
    private LocalDateTime debutQuizz;
    private Duration dureeLimite;

    // Constructeur sans minuteur
    public Quizz(String question, String[] reponses, int bonneReponse) {
        this.question = question;
        this.reponses = reponses;
        this.bonneReponse = bonneReponse;
        this.terminee = false;
        this.dureeLimite = null;
    }

    // Constructeur avec minuteur
    public Quizz(String question, String[] reponses, int bonneReponse, int minutesLimite) {
        this(question, reponses, bonneReponse);
        this.dureeLimite = Duration.ofMinutes(minutesLimite);
    }

    public void commencer() {
        if (dureeLimite != null) {
            this.debutQuizz = LocalDateTime.now();
        }
    }

    public boolean estTempsEcoule() {
        if (dureeLimite == null) {
            return false;
        }
        LocalDateTime maintenant = LocalDateTime.now();
        Duration tempsEcoule = Duration.between(debutQuizz, maintenant);
        return tempsEcoule.compareTo(dureeLimite) >= 0;
    }

    public void afficherQuestion() {
        System.out.println("\n" + question);
        for (int i = 0; i < reponses.length; i++) {
            System.out.println((i + 1) + ". " + reponses[i]);
        }
    }

    public int repondre(int reponse) {
        if (terminee || (dureeLimite != null && estTempsEcoule())) {
            System.out.println("Temps écoulé ou question déjà terminée !");
            return 0;
        }

        terminee = true;
        if (reponse == bonneReponse + 1) { // +1 car l'utilisateur choisit 1-3
            return 1;
        } else {
            return 0;
        }
    }

    public void afficherBonneReponse() {
        System.out.println("La bonne réponse était : " + (bonneReponse + 1) + ". " + reponses[bonneReponse]);
    }

    public boolean estTerminee() {
        return terminee || (dureeLimite != null && estTempsEcoule());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Création des questions
        Quizz[] questions = {
            new Quizz("Quelle est la capitale de la France ?", 
                     new String[]{"Londres", "Paris", "Berlin"}, 1),
            new Quizz("Combien font 2+2 ?", 
                     new String[]{"3", "4", "5"}, 1),
            new Quizz("Quel langage utilise 'System.out.println' ?", 
                     new String[]{"Python", "C++", "Java"}, 2)
        };

     
        int scoreTotal = 0;

        for (Quizz q : questions) {
            q.afficherQuestion();
            
            System.out.print("Votre réponse (1-3) : ");
            int reponse = scanner.nextInt();
            
            int points = q.repondre(reponse - 1); // -1 pour l'index 0-2
            scoreTotal += points;
            
            if (points == 0 && !q.estTempsEcoule()) {
                q.afficherBonneReponse();
            }
            
            if (q.estTempsEcoule()) {
                System.out.println("Temps écoulé !");
                break;
            }
        }

        System.out.println("\nScore final : " + scoreTotal + "/" + questions.length);
        scanner.close();
    }
}

