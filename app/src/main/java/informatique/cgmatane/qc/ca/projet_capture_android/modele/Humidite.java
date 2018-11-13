package informatique.cgmatane.qc.ca.projet_capture_android.modele;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;

public class Humidite
{
    private long moyenne;
    private long maximum;
    private long minimum;

    protected DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss", Locale.CANADA);
    private String date;

//    public static final SimpleDateFormat formatDate = new SimpleDateFormat("yyyy/MM/dd - HH:mm", Locale.CANADA_FRENCH);


    public Humidite(long moyenne, long maximum, long minimum, String date) {
        this.moyenne = moyenne;
        this.maximum = maximum;
        this.minimum = minimum;
        this.date = date;
    }

    public long getMoyenne() {
        return moyenne;
    }

    public void setMoyenne(long moyenne) {
        this.moyenne = moyenne;
    }

    public long getMaximum() {
        return maximum;
    }

    public void setMaximum(long maximum) {
        this.maximum = maximum;
    }

    public long getMinimum() {
        return minimum;
    }

    public void setMinimum(long minimum) {
        this.minimum = minimum;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
