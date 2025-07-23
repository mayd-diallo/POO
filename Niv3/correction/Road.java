public class Road {
     // Attributs de la route
    private String instruction;
    private boolean isCorrect;
    private int speedLimit;
    private boolean isPedestrian;        // Si des passants traversent le passage piéton
    private boolean isLowCarFront;       // Si une voiture lente gêne le passage
    private boolean isRedLight;
    private boolean isOrangeLight;
    private boolean isGreenLight;
    private boolean isRaining;           // Événement aléatoire
    private int traffic;                 // 0:Bon, 1:modéré, 2:Dense, 3:bouchon
    private boolean isHighway;
    private boolean isParking;
    private boolean isTunnel; // Si la route est dans un tunnel
    private boolean isTheEnd; // Si c'est la fin du test de conduite
    // Constructeur
    public Road() {
        this.instruction = "";
        this.isCorrect = true;
        this.speedLimit = 50;
        this.isPedestrian = false;
        this.isLowCarFront = false;
        this.isRedLight = false;
        this.isOrangeLight = false;
        this.isGreenLight = true;
        this.isRaining = false;
        this.traffic = 1; // Trafic modéré par défaut
        this.isHighway = false;
        this.isParking = false;
        this.isTunnel = false;
        this.isTheEnd = false;
    }
    
    // Constructeur avec paramètres
    public Road(String instruction, boolean isCorrect, int speedLimit, boolean isPedestrian, 
                boolean isLowCarFront, boolean isRedLight, boolean isOrangeLight, 
                boolean isGreenLight, boolean isRaining, int traffic, boolean isHighway, 
                boolean isParking, boolean isTunnel, boolean isTheEnd) {
        this.instruction = instruction;
        this.isCorrect = isCorrect;
        this.speedLimit = speedLimit;
        this.isPedestrian = isPedestrian;
        this.isLowCarFront = isLowCarFront;
        this.isRedLight = isRedLight;
        this.isOrangeLight = isOrangeLight;
        this.isGreenLight = isGreenLight;
        this.isRaining = isRaining;
        this.traffic = traffic;
        this.isHighway = isHighway;
        this.isParking = isParking;
        this.isTunnel = isTunnel;
        this.isTheEnd = isTheEnd;

    }


    // Pluie aléatoire
    public void setRandomRain() {
        this.isRaining = Math.random() < 0.2; // 20% de chance qu'il pleuve
        if (this.isRaining && this.speedLimit > 50) {
            this.speedLimit -= 10; // Réduction de la limitation de vitesse en cas de pluie si la vitesse est supérieure à 50 km/h
            System.out.println("Il pleut, il faut mettre les essuie-glaces et la limitation de vitesse est réduite de 10 km/h.");
        }
    }
    
    // Getters et Setters
    public String getInstruction() {
        return instruction;
    }
    
    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }
    
    public boolean isCorrect() {
        return isCorrect;
    }

    public boolean isTheEnd() {
        return isTheEnd;
    }

    public void setTheEnd(boolean isTheEnd) {
        this.isTheEnd = isTheEnd;
    }
    
    public void setCorrect(boolean isCorrect) {
        this.isCorrect = isCorrect;
    }
    
    public int getSpeedLimit() {
        return speedLimit;
    }
    
    public void setSpeedLimit(int speedLimit) {
        this.speedLimit = speedLimit;
    }
    
    public boolean isPedestrian() {
        return isPedestrian;
    }
    
    public void setPedestrian(boolean isPedestrian) {
        this.isPedestrian = isPedestrian;
    }
    
    public boolean isLowCarFront() {
        return isLowCarFront;
    }
    
    public void setLowCarFront(boolean isLowCarFront) {
        this.isLowCarFront = isLowCarFront;
    }
    
    public boolean isRedLight() {
        return isRedLight;
    }
    
    public void setRedLight(boolean isRedLight) {
        this.isRedLight = isRedLight;
    }
    
    public boolean isOrangeLight() {
        return isOrangeLight;
    }
    
    public void setOrangeLight(boolean isOrangeLight) {
        this.isOrangeLight = isOrangeLight;
    }
    
    public boolean isGreenLight() {
        return isGreenLight;
    }
    
    public void setGreenLight(boolean isGreenLight) {
        this.isGreenLight = isGreenLight;
    }
    
    public boolean isRaining() {
        return isRaining;
    }

    public boolean isTunnel() {
        return isTunnel;
    }

    public void setTunnel(boolean isTunnel) {
        this.isTunnel = isTunnel;
    }

    public void setRaining(boolean isRaining) {
        this.isRaining = isRaining;
    }
    
    public int getTraffic() {
        return traffic;
    }
    
    public void setTraffic(int traffic) {
        if (traffic >= 0 && traffic <= 3) {
            this.traffic = traffic;
        } else {
            System.out.println("Valeur de trafic invalide. Doit être entre 0 et 3.");
        }
    }
    
    public boolean isHighway() {
        
        return isHighway;
    }
    
    public void setHighway(boolean isHighway) {
        this.isHighway = isHighway;
    }
    
    public boolean isParking() {
        return isParking;
    }
    
    public void setParking(boolean isParking) {
        this.isParking = isParking;
    }
    
    // Méthodes utilitaires
    public String getTrafficDescription() {
        switch (traffic) {
            case 0: return "Bon";
            case 1: return "Modéré";
            case 2: return "Dense";
            case 3: return "Bouchon";
            default: return "Inconnu";
        }
    }
    
    public String getLightStatus() {
        if (isRedLight) return "Rouge";
        if (isOrangeLight) return "Orange";
        if (isGreenLight) return "Vert";
        return "Éteint";
    }
    
    public void displayRoadStatus() {
        System.out.println("=== État de la route ===");
        System.out.println("Instruction: " + instruction);
        System.out.println("Correct: " + (isCorrect ? "Oui" : "Non"));
        System.out.println("Limitation de vitesse: " + speedLimit + " km/h");
        System.out.println("Piétons: " + (isPedestrian ? "Présents" : "Absents"));
        System.out.println("Voiture lente devant: " + (isLowCarFront ? "Oui" : "Non"));
        System.out.println("Feu: " + getLightStatus());
        System.out.println("Pluie: " + (isRaining ? "Oui" : "Non"));
        System.out.println("Trafic: " + getTrafficDescription());
        System.out.println("Autoroute: " + (isHighway ? "Oui" : "Non"));
        System.out.println("Zone de parking: " + (isParking ? "Oui" : "Non"));
        System.out.println("=======================");
    }
    
}

