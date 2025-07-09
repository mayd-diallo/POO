package exoobje;

public class Main {
    // Attributs (caractéristiques du garçon)
    private String prenom;
    private int age;
    private String couleurDesYeux;

    // Constructeur
    public Main(String prenom, int age, String couleurDesYeux) {
        this.prenom = prenom;
        this.age = age;
        this.couleurDesYeux = couleurDesYeux;
    }

    // Méthodes (comportements du garçon)
    public void sePresenter() {
        System.out.println("Bonjour, je m'appelle " + prenom + ", j'ai " + age + " ans et j'ai les yeux " + couleurDesYeux + ".");
    }

    public void jouer(String jeu) {
        System.out.println(prenom + " joue à " + jeu + ".");
    }

    // Méthode main pour tester la classe
    public static void main(String[] args) {
        // Création d'une instance de Garcon
        Main monGarcon = new Main("Lucas", 10, "bleus");
        
        // Appel des méthodes
        monGarcon.sePresenter();
        monGarcon.jouer("football");
        
        // Création d'un autre garçon
        Main autreGarcon = new Main("Thomas", 8, "verts");
        autreGarcon.sePresenter();
        autreGarcon.jouer("aux Lego");
    }
}

