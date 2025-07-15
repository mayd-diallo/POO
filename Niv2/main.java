package Niv2;
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

        // Version avec minuteur (décommenter pour utiliser)
        // for (int i = 0; i < questions.length; i++) {
        //     questions[i] = new Quizz(questions[i].question, questions[i].reponses, 
        //                             questions[i].bonneReponse, 1); // 1 minute par question
        //     questions[i].commencer();
        // }

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



