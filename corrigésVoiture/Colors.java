package corrigésVoiture;

class Colors {
    // Couleurs de texte
    public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";
    public static final String ORANGE = "\u001B[38;5;208m"; // Couleur orange (non standard ANSI)

    // Couleurs de fond
    public static final String BLACK_BG = "\u001B[40m";
    public static final String RED_BG = "\u001B[41m";
    public static final String GREEN_BG = "\u001B[42m";
    public static final String YELLOW_BG = "\u001B[43m";
    public static final String BLUE_BG = "\u001B[44m";
    public static final String PURPLE_BG = "\u001B[45m";
    public static final String CYAN_BG = "\u001B[46m";
    public static final String WHITE_BG = "\u001B[47m";
    
    // Styles
    public static final String BOLD = "\u001B[1m";
    public static final String UNDERLINE = "\u001B[4m";
    public static final String ITALIC = "\u001B[3m";
    
    // Méthodes utilitaires
    public static String colorize(String text, String color) {
        return color + text + RESET;
    }
    
    public static String success(String text) {
        return GREEN + "✓ " + text + RESET;
    }
    
    public static String error(String text) {
        return RED + "✗ " + text + RESET;
    }
    
    public static String warning(String text) {
        return YELLOW + "⚠ " + text + RESET;
    }
    
    public static String info(String text) {
        return CYAN + "ℹ " + text + RESET;
    }

    public static String help(String text) {
        return ORANGE + "ℹ " + text + RESET;
    }
    
    public static String header(String text) {
        return BOLD + BLUE + text + RESET;
    }
    
    public static String instruction(String text) {
        return PURPLE + BOLD + "📋 " + text + RESET;
    }
}