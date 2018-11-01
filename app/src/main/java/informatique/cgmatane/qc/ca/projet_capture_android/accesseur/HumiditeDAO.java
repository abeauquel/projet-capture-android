package informatique.cgmatane.qc.ca.projet_capture_android.accesseur;

import java.time.LocalDateTime;
import java.util.ArrayList;

import informatique.cgmatane.qc.ca.projet_capture_android.modele.Humidite;

public class HumiditeDAO {

    public ArrayList<Humidite> listerHumidite()
    {
        ArrayList<Humidite> listeHumidite = new ArrayList<Humidite>();

        listeHumidite.add(new Humidite(12.5,20,6, LocalDateTime.now()));
        listeHumidite.add(new Humidite(23.1,40.1,-1.3, LocalDateTime.now()));
        listeHumidite.add(new Humidite(5,10,0, LocalDateTime.now()));

        return listeHumidite;
    }
}
