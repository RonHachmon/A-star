package app.gui;

public enum StringPrintEffect {

    RESET("\u001B[0m"),
    BG_BLACK("\u001B[48m"),
    BG_WHITE("\u001B[47m"),
    BG_YELLOW("\u001B[43m"),
    BG_GREEN("\u001B[42m"),
    BG_RED("\u001B[41m"),
    BOLD("\u001B[1m"),
    BLUE("\u001B[34m"),
    WHITE("\u001B[37m"),
    YELLOW("\u001B[33m"),
    GREEN("\u001B[32m"),
    RED("\u001B[31m");



    private final String stringEffect;

    StringPrintEffect(String stringEffect) {
        this.stringEffect = stringEffect;

    }
    public String Effect()
    {
        return stringEffect;
    }
}
