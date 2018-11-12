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

    public static String URL_RAPPORTER_HUMIDITE = "http://54.39.145.59/projet-capture-serveur-php/humidites/jour/1534377600/1534463940";

    protected DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss", Locale.CANADA);


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

            System.out.print("MOYENNE " + moyenne);


            return new Humidite(moyenne, maximum, minimum, date);

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
