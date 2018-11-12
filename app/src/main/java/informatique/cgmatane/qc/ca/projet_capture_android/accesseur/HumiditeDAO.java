package informatique.cgmatane.qc.ca.projet_capture_android.accesseur;

import android.content.Context;
import android.content.SharedPreferences;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import informatique.cgmatane.qc.ca.projet_capture_android.VuePrincipale;
import informatique.cgmatane.qc.ca.projet_capture_android.modele.Humidite;

import static android.content.Context.MODE_PRIVATE;

public class HumiditeDAO {

    public static String URL_RAPPORTER_HUMIDITE = "http://localhost/station-meteo/humidites";

    protected DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss", Locale.CANADA);

    public static final String PREFS = "comp1";
    public static SharedPreferences pref1;
    public static final String moyenne = "moyenne";
    public static final String maximum = "maximum";
    public static final String minimum = "minimum";
    public static final String date = "date";

    public static Humidite rapporterHumidite()
    {
        String xml = ServiceWeb.recuperationDonneMeteo(URL_RAPPORTER_HUMIDITE);

        if(xml != null)
        {
			Document document = ServiceWeb.parserXML(xml);
            if(document == null) return null;
            Element element = document.getDocumentElement();
            String moyenne = ServiceWeb.lireBalise(element,"moyenne");
            String maximum = ServiceWeb.lireBalise(element,"maximum");
            String minimum = ServiceWeb.lireBalise(element,"minimum");
            String date = ServiceWeb.lireBalise(element,"date");

            Humidite humidite = new Humidite(moyenne, maximum, minimum, date);

			return humidite;

        }
        return null;
    }

    public void sauvegardeDonneeMeteo()
    {

//        SharedPreferences sharedPref = getContext().getSharedPreferences("informatique.cgmatane.qc.ca.projet_capture_android", MODE_PRIVATE);

//        Humidite humidite = rapporterHumidite();
//
//        VuePrincipale.enregistrerDonneeJour("moyenne", moyenne);
//
////        pref1.edit().putString(humidite.getMoyenne(), "value").commit();
    }
}
