package informatique.cgmatane.qc.ca.projet_capture_android.accesseur;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import informatique.cgmatane.qc.ca.projet_capture_android.modele.Humidite;

public class HumiditeDAO {

    public static String URL_RAPPORTER_HUMIDITE = "http://localhost/station-meteo/humidite";
    protected DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss", Locale.CANADA);


    public Humidite rapporterHumidite(int numero)
    {
        String xml = ServiceWeb.consommerService(URL_RAPPORTER_HUMIDITE + numero);

        if(xml != null)
        {
			Document document = ServiceWeb.parserXML(xml);
            if(document == null) return null;
            Element element = document.getDocumentElement();
            String moyenne = ServiceWeb.lireBalise(element,"moyenne");
            String maximum = ServiceWeb.lireBalise(element,"maximum");
            String minimum = ServiceWeb.lireBalise(element,"minimum");
            String date = ServiceWeb.lireBalise(element,"date");

            Humidite humidite = new Humidite(moyenne,maximum, minimum, date);

			return humidite;

        }
        return null;
    }
}
