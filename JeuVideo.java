package Intro;


public class JeuVideo {
    private String nom;
    private double prix;
    private String plateforme;
    private int pegi;
    private int taille;
    private String genre;
    private boolean installe;
    private String version;

    // Constructeur
    public JeuVideo(String nom, double prix, String plateforme, int pegi, int taille, String genre, boolean installe, String version) {
        this.nom = nom;
        this.prix = prix;
        this.plateforme = plateforme;
        this.pegi = pegi;
        this.taille = taille;
        this.genre = genre;
        this.installe = installe;
        this.version = version;
    }

    // Méthodes
    public void jouer() {
        System.out.println(infoDuJeu());
        System.out.println(nom + " est en cours d'exécution...");
                System.out.println(" EA SPORT It's in the game...");
    }

    public void sauvegarder() {
        System.out.println("Sauvegarde du jeu " + nom+"Veuillez ne pas éteindre votre PC");
    }

    public void installer() {
        if (!installe) {
            installe = true;
                    System.out.println(infoDuJeu());
             System.out.println(nom + " est en cours d'exécution...");
            System.out.println(nom + " est maintenant installé.");
        } else {
            System.out.println(nom + " est déjà installé.");
        }
    }


    private String infoDuJeu(){

        String info="";

        info+="Nom du Jeu video :"+this.nom+"\n";
        info+="Prix du jeu :"+this.prix+" euros \n";
        info+="pegi : +"+this.pegi+"\n";
        info+="taille : "+this.pegi+"go \n";
        info+="genre : "+this.genre+"go \n";
        info+="installe : "+this.installe+"go \n";       
        info+="version : "+this.version+"go \n";
        
        return info;


    }

    public void mettreAJour() {
                System.out.println(infoDuJeu());
        System.out.println(nom + " est mis à jour vers la version " + version);
    }

    // Optionnel : Getters/Setters si tu veux y accéder ailleurs
}
