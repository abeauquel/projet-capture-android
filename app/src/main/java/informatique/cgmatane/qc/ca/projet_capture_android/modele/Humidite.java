package informatique.cgmatane.qc.ca.projet_capture_android.modele;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Humidite
{
    private double moyenne;
    private double maximum;
    private double minimum;
    private LocalDateTime date;

    public static final DateTimeFormatter formatMinute = DateTimeFormatter.ofPattern("MM/dd/yyyy - HH:mm") ;
    public static final DateTimeFormatter formatJour = DateTimeFormatter.ofPattern("MM/dd/yyyy") ;
    public static final DateTimeFormatter formatSemaine = DateTimeFormatter.ofPattern("MM/dd/yyyy") ;
    public static final DateTimeFormatter formatMois = DateTimeFormatter.ofPattern("MM/yyyy") ;


    public Humidite(double moyenne, double maximum, double minimum, LocalDateTime date) {
        this.moyenne = moyenne;
        this.maximum = maximum;
        this.minimum = minimum;
        this.date = date;
    }

    public double getMoyenne() {
        return moyenne;
    }

    public void setMoyenne(double moyenne) {
        this.moyenne = moyenne;
    }

    public double getMaximum() {
        return maximum;
    }

    public void setMaximum(double maximum) {
        this.maximum = maximum;
    }

    public double getMinimum() {
        return minimum;
    }

    public void setMinimum(double minimum) {
        this.minimum = minimum;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
