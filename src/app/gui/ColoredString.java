package app.gui;

public class ColoredString {
    private String text;
    private StringPrintEffect color =StringPrintEffect.WHITE;
    private StringPrintEffect background =StringPrintEffect.BG_BLACK;
    private StringPrintEffect effect =StringPrintEffect.RESET;

    public void setBackground(StringPrintEffect background) {
        this.background = background;
    }

    public void setEffect(StringPrintEffect effect) {
        this.effect = effect;
    }


    public ColoredString(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return effect.Effect()+color.Effect()+background.Effect()+text+StringPrintEffect.RESET.Effect();
    }


    public void setColor(StringPrintEffect color) {
        this.color = color;
    }
}
