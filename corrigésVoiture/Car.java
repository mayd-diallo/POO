package corrigésVoiture;

public class Car {
    
    private String brand;
    private String model;
    private int maxSpeed;
    private int powerEngine;
    
    // Attributs pour l'état de la voiture
    private int currentSpeed; // Vitesse actuelle de la voiture
    private int currentGear; // Boite de vitesses actuelle (0 = Point mort, -1 = Marche arrière, 1-5 = Vitesses)
    private boolean isEngineOn; // Indique si le moteur est démarré
    private boolean isReverse; // Indique si la voiture est en marche arrière
    private boolean leftIndicator; // Indicateur gauche
    private boolean rightIndicator; // Indicateur droit
    private boolean headlights; // Feux de croisement
    private boolean wipers; // Essuie-glaces
    private boolean hazardLights; // Feux de détresse
    private int regime; // Régime moteur (0 = Normal, 1 = Sous-régime, 2 = Surrégime)

    public Car(String brand, String model, int maxSpeed, int powerEngine) {
        this.brand = brand;
        this.model = model;
        this.maxSpeed = maxSpeed;
        this.powerEngine = powerEngine;
        
        // Initialisation des attributs d'état
        this.currentSpeed = 0;
        this.currentGear = 0; // 0 = Point mort
        this.isEngineOn = false;
        this.isReverse = false;
        this.leftIndicator = false;
        this.rightIndicator = false;
        this.headlights = false;
        this.wipers = false;
        this.hazardLights = false;
        this.regime = 0; // Régime moteur normal
    }

    // Getters and setters for the attributes
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setRegime(int regime) {
        this.regime = regime;

    }

    public int getRegime() {
        return regime;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public int getPowerEngine() {
        return powerEngine;
    }

    public void setPowerEngine(int powerEngine) {
        this.powerEngine = powerEngine;
    }

    // Getters et setters pour les attributs d'état
    public int getCurrentSpeed() {
        return currentSpeed;
    }

    public void setCurrentSpeed(int currentSpeed) {
        this.currentSpeed = currentSpeed;
    }

    public int getCurrentGear() {
        return currentGear;
    }

    public void setCurrentGear(int currentGear) {
        this.currentGear = currentGear;
    }

    public boolean isEngineOn() {
        return isEngineOn;
    }

    public void setEngineOn(boolean isEngineOn) {
        this.isEngineOn = isEngineOn;
    }

    public boolean isReverse() {
        return isReverse;
    }

    public void setReverse(boolean isReverse) {
        this.isReverse = isReverse;
    }

    public boolean isLeftIndicator() {
        return leftIndicator;
    }

    public void setLeftIndicator(boolean leftIndicator) {
        this.leftIndicator = leftIndicator;
    }

    public boolean isRightIndicator() {
        return rightIndicator;
    }

    public void setRightIndicator(boolean rightIndicator) {
        this.rightIndicator = rightIndicator;
    }

    public boolean isHeadlights() {
        return headlights;
    }

    public void setHeadlights(boolean headlights) {
        this.headlights = headlights;
    }

    public boolean isWipers() {
        return wipers;
    }

    public void setWipers(boolean wipers) {
        this.wipers = wipers;
    }

    public boolean isHazardLights() {
        return hazardLights;
    }

    public void setHazardLights(boolean hazardLights) {
        this.hazardLights = hazardLights;
    }


    /**
     * Méthodes de comportement de la voiture
     * 
     ** Accelerer
     * Decelerer
     * Freiner
     * Marche arrière
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
     */

    // Méthodes de démarrage et arrêt
    public void startEngine() {
        if (!isEngineOn) {
            isEngineOn = true;
            System.out.println("Moteur démarré");
        } else {
            System.out.println("Le moteur est déjà démarré");
        }
    }

    public void stopEngine() {
        if (currentSpeed == 0) {
            isEngineOn = false;
            currentGear = 0;
            System.out.println("Moteur arrêté");
        } else {
            System.out.println("Impossible d'arrêter le moteur en mouvement");
        }
    }

    // Méthodes de vitesse
    public void speedUp(int increment) {
        if (isEngineOn && !isReverse) {
            if (currentSpeed + increment <= maxSpeed) {
                currentSpeed += increment;
                 setCurrentSpeed(currentSpeed);
                System.out.println("Accélération : vitesse actuelle " + currentSpeed + " km/h");
                lowOrHighRegime(currentGear);
            } else {
                currentSpeed = maxSpeed;
                setCurrentSpeed(currentSpeed);
                System.out.println("Vitesse maximale atteinte : " + maxSpeed + " km/h");
            }
        } else if (isReverse) {
            System.out.println("Impossible d'accélérer en marche arrière");
        } else {
            System.out.println("Le moteur doit être démarré pour accélérer");
        }
    }

    public void speedDown(int decrement) {
        if (currentSpeed - decrement >= 0) {
            currentSpeed -= decrement;
            System.out.println("Décélération : vitesse actuelle " + currentSpeed + " km/h");
            setCurrentSpeed(currentSpeed);
            lowOrHighRegime(currentGear);
        } else {
            System.out.println("Véhicule arrêté");
        }
    }

    public void brake() {
        if (currentSpeed > 0) {
            currentSpeed = Math.max(0, currentSpeed - 20);
            System.out.println("Freinage : vitesse actuelle " + currentSpeed + " km/h");
        } else {
            System.out.println("Le véhicule est déjà arrêté");
        }
    }

    public void resetIndicators() {
        leftIndicator = false;
        rightIndicator = false;
        System.out.println("Clignotants désactivés");
    }

    public void setLeftIndicator() {
        this.leftIndicator = true;
        this.rightIndicator = false; // Désactive le clignotant droit
        System.out.println("Clignotant gauche activé");
    }

    public void setRightIndicator() {
        this.rightIndicator = true;
        this.leftIndicator = false; // Désactive le clignotant gauche
        System.out.println("Clignotant droit activé");
    }

    public void stop() {
        currentSpeed = 0;
        System.out.println("Véhicule arrêté ou point mort");
    }

    public void lowOrHighRegime(int gear) {
        int regime;
       
        switch(gear) {
 
            case 1:
            // Vérification du régime moteur, sous-régime return 1, surrégime return 2
            // Régime moteur normal return 0

                System.out.println("Vitesse 1 détectée");
                
                if(currentSpeed > 30) {
                    regime = 2; // Surrégime
                } else {
                    regime = 0; // Régime normal
                }
                break;

            case 2:
                System.out.println("Vitesse 2 détectée");
                if(currentSpeed > 50) {
                    regime = 2; // Surrégime
                } else {
                    regime = 0; // Régime normal
                }
                break;
            case 3:
                System.out.println("Vitesse 3 dectectée");
                if(currentSpeed > 70) {
                    regime = 2; // Surrégime
                } else if(currentSpeed < 30) {
                    regime = 1; // Sous-régime
                } else {
                    regime = 0; // Régime normal
                }
                System.out.println("régime: " + regime );
                break;
            case 4:
            System.out.println("Vitesse 4 dectectée");
            System.out.println("vitesse actuelle: " + currentSpeed + " km/h");
                if(currentSpeed > 90) {
                    regime = 2; // Surrégime
                } else if(currentSpeed < 50) {
                    regime = 1; // Sous-régime
                } else {
                    regime = 0; // Régime normal
                }
                System.out.println("régime: " + regime );
                break;
            case 5:
                System.out.println("Vitesse 5 dectectée");
                System.out.println("vitesse actuelle: " + currentSpeed + " km/h");
                if(currentSpeed > 110) {
                    regime = 2; // Surrégime
                } else if(currentSpeed < 70) {
                    regime = 1; // Sous-régime
                } else {
                    regime = 0; // Régime normal
                }
                System.out.println("régime: " + regime );
                break;
            case 0: // Point mort
                regime = 0; // Point mort, pas de régime moteur
                break;
            case -1: // Marche arrière
                if(currentSpeed > 20) {
                    regime = 2; // Surrégime
                } else {
                    regime = 0; // Régime normal
                }
                System.out.println("Marche arrière engagée, régime: " + regime);
                break;
            default:
                regime = 0; // Vitesse non définie pour les autres vitesses
                System.out.println("Vitesse non définie, régime: " + regime);
                break;
        }

        switch(regime) {
            case 0:
                System.out.println("Régime moteur normal");
                
                break;
            case 1:
                System.out.println(Colors.error("Sous-régime moteur détecté"));
                break;
            case 2:
                System.out.println(Colors.error("Surrégime moteur détecté"));
                break;
            default:
                System.out.println("Régime moteur inconnu");
        }
        setRegime(regime);
      
    }

    // Méthodes de vitesse
    public void gearbox(int gear) {
        if (isEngineOn) {
            if (gear >= 0 && gear <= 5) {
                currentGear = gear;
                if (gear == 0) {
                    System.out.println("Point mort engagé");
                } else if (gear == 1 ) {

                    System.out.println("Vitesse 1 engagée");
                } else if (gear == 2) { 
                    System.out.println("Vitesse 2 engagée");
                } else if (gear == 3) {
                    System.out.println("Vitesse 3 engagée");
                } else if (gear == 4) {
                    System.out.println("Vitesse 4 engagée");
                } else if (gear == 5) {
                    System.out.println("Vitesse 5 engagée");
                } else if (gear == -1) {
                    System.out.println("Marche arrière engagée");
                } else {
                    System.out.println("Vitesse " + gear + " engagée");
                }
                setCurrentGear(gear);
                lowOrHighRegime(currentGear);
            } else {
                System.out.println("Vitesse invalide (0-5)");
            }
        } else {
            System.out.println("Le moteur doit être démarré");
        }
    }

    

    public void reverseGear() {
        if (isEngineOn && currentSpeed == 0) {
            isReverse = true;
            currentGear = -1;
            System.out.println("Marche arrière engagée");
        } else if (currentSpeed > 0) {
            System.out.println("Arrêtez le véhicule avant d'engager la marche arrière");
        } else {
            System.out.println("Le moteur doit être démarré");
        }
    }

    /*
    public void sortirMarcheArriere() {
        if (isReverse) {
            isReverse = false;
            currentGear = 0;
            System.out.println("Marche arrière désengagée");
        }
    } */

    // Méthodes de direction
    public void turnLeft() {
        if (isEngineOn && currentSpeed > 0) {
            System.out.println("Virage à gauche");
        } else {
            System.out.println("Le véhicule doit être en mouvement pour virer");
        }
    }

    public void turnRight() {
        if (isEngineOn && currentSpeed > 0) {
            System.out.println("Virage à droite");
        } else {
            System.out.println("Le véhicule doit être en mouvement pour virer");
        }
    }

    public void changeLaneLeft() {
        if (isEngineOn && currentSpeed > 0) {
            leftIndicator = true;
            System.out.println("Changement de voie à gauche - clignotant gauche activé");
        } else {
            System.out.println("Le véhicule doit être en mouvement");
        }
    }

    public void changeLaneRight() {
        if (isEngineOn && currentSpeed > 0) {
            rightIndicator = true;
            System.out.println("Changement de voie à droite - clignotant droit activé");
        } else {
            System.out.println("Le véhicule doit être en mouvement");
        }
    }

    // Méthodes de contrôle visibilité
    public void interiorVisibilityCheck() {
        System.out.println("Contrôle de visibilité intérieur effectué");
    }

    public void exteriorVisibilityCheck() {
        System.out.println("Contrôle de visibilité extérieur effectué");
    }

    public void blindSpotCheck() {
        System.out.println("Contrôle des angles morts effectué");
    }

    public void checkVisibility() {
        interiorVisibilityCheck();
        exteriorVisibilityCheck();
        blindSpotCheck();
        System.out.println("Vérification de la visibilité terminée");
    }

    // Méthodes de signalisation
    public void leftIndicator() {
        leftIndicator = !leftIndicator;
        rightIndicator = false; // Désactive le clignotant droit
        if (leftIndicator) {
            System.out.println("Clignotant gauche activé");
        } else {
            System.out.println("Clignotant gauche désactivé");
        }
    }

    public void rightIndicator() {
        rightIndicator = !rightIndicator;
        leftIndicator = false; // Désactive le clignotant gauche
        if (rightIndicator) {
            System.out.println("Clignotant droit activé");
        } else {
            System.out.println("Clignotant droit désactivé");
        }
    }

    public void headlights() {
        headlights = !headlights;
        if (headlights) {
            System.out.println("Feux de croisement allumés");
        } else {
            System.out.println("Feux de croisement éteints");
        }
    }

    public void wipers() {
        wipers = !wipers;
        if (wipers) {
            System.out.println("Essuie-glaces activés");
        } else {
            System.out.println("Essuie-glaces désactivés");
        }
    }

    public void hazardLights() {
        hazardLights = !hazardLights;
        if (hazardLights) {
            leftIndicator = false;
            rightIndicator = false;
            System.out.println("Feux de détresse activés");
        } else {
            System.out.println("Feux de détresse désactivés");
        }
    }

    public void horn() {
        System.out.println("BEEP BEEP! Klaxon activé");
    }

    // Méthode pour afficher l'état de la voiture
    public void displayStatus() {
        System.out.println("=== État de la voiture ===");
        System.out.println("Marque: " + brand + " " + model);
        System.out.println("Moteur: " + (isEngineOn ? "Démarré" : "Arrêté"));
        System.out.println("Vitesse: " + currentSpeed + " km/h");
        System.out.println("Vitesse engagée: " + (currentGear == -1 ? "Marche arrière" : 
                          currentGear == 0 ? "Point mort" : "Vitesse " + currentGear));
        System.out.println("Clignotant gauche: " + (leftIndicator ? "Activé" : "Désactivé"));
        System.out.println("Clignotant droit: " + (rightIndicator ? "Activé" : "Désactivé"));
        System.out.println("Feux de croisement: " + (headlights ? "Allumés" : "Éteints"));
        System.out.println("Essuie-glaces: " + (wipers ? "Activés" : "Désactivés"));
        System.out.println("Feux de détresse: " + (hazardLights ? "Activés" : "Désactivés"));
        System.out.println("========================");
    }

}
