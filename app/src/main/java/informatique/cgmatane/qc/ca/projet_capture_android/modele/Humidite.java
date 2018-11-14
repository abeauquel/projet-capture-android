package informatique.cgmatane.qc.ca.projet_capture_android.modele;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class Humidite
{
    private String moyenne;
    private String maximum;
    private String minimum;

    protected DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss", Locale.CANADA);
    private String date;

//    public static final SimpleDateFormat formatDate = new SimpleDateFormat("yyyy/MM/dd - HH:mm", Locale.CANADA_FRENCH);


    public Humidite(String moyenne, String maximum, String minimum, String date) {
        this.moyenne = moyenne;
        this.maximum = maximum;
        this.minimum = minimum;
        this.date = date;
    }

    public String getMoyenne() {
        return moyenne;
    }

    public void setMoyenne(String moyenne) {
        this.moyenne = moyenne;
    }

    public String getMaximum() {
        return maximum;
    }

    public void setMaximum(String maximum) {
        this.maximum = maximum;
    }

    public String getMinimum() {
        return minimum;
    }

    public void setMinimum(String minimum) {
        this.minimum = minimum;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
