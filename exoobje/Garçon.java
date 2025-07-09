package exoobje;

public class Garçon {
    private String nom;
    private int age ;
    private String personne ;
    private double taille ;
    private String couleurdesyeux ;
    private int poids ;


public Garçon(String nom, int age, String personne, double taille, String couleurdesyeux, int poids) {
    this.nom = nom ;
    this.age = age ;
    this.personne = personne ;
    this.taille = taille ;
    this.couleurdesyeux =couleurdesyeux ;
    this.poids = poids ; 
}
public void parler(String message){
    
    System.out.println(nom + " dit " + message);
}


public void marcher( double distance){
    System.out.println( nom + " marcher sur" + distance + "metres");
}
public void feterAnniversaire() {
    age++;
System.out.println("joyeux anniversaire" + personne+"!tu a maintenant" + age + "ans");
}

}