package informatique.cgmatane.qc.ca.projet_capture_android.accesseur;

import android.content.Context;
import android.content.SharedPreferences;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import informatique.cgmatane.qc.ca.projet_capture_android.VuePrincipale;
import informatique.cgmatane.qc.ca.projet_capture_android.modele.Humidite;

import static android.content.Context.MODE_PRIVATE;

public class HumiditeDAO {

    private static Connexion connexion;
    private static Humidite humidite;
    private static String url;

    /*public static String URL_RAPPORTER_HUMIDITE = "http://54.39.145.59/projet-capture-serveur-php/humidites/jour/1282164444/1566161244";*/


//    public static Humidite rapporterHumidite()
//    {
//        String xml = ServiceWeb.recuperationDonneMeteo(URL_RAPPORTER_HUMIDITE);
//
//        if(xml != null)
//        {
//			Document document = ServiceWeb.parserXML(xml);
//            if(document == null) return null;
//            Element element = document.getDocumentElement();
//            String moyenne = ServiceWeb.lireBalise(element,"moyenne");
//            String maximum = ServiceWeb.lireBalise(element,"maximum");
//            String minimum = ServiceWeb.lireBalise(element,"minimum");
//            String date = ServiceWeb.lireBalise(element,"date");
//
//            System.out.print("MOYENNE " + moyenne);
//
//
//            return new Humidite(moyenne, maximum, minimum, date);
//
//        }
//        return null;
//    }


    public static Humidite humiditeSelonURL() {
        connexion = new Connexion();
        try {
            DocumentBuilderFactory f =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder b = f.newDocumentBuilder();
            Document doc = b.parse(url);
            doc.getDocumentElement().normalize();
            Element element = doc.getDocumentElement();
            System.out.println("Root element: " +
                    doc.getDocumentElement().getNodeName());


            System.out.println("Moyenne : " + element.getElementsByTagName(connexion.CHAMP_MOYENNE).item(0).getTextContent());
            System.out.println("Date : " + element.getElementsByTagName(connexion.CHAMP_DATE).item(0).getTextContent());

            long moyenne = Long.parseLong(element.getElementsByTagName(connexion.CHAMP_MOYENNE).item(0).getTextContent());
            long max = Long.parseLong(element.getElementsByTagName(connexion.CHAMP_MAX).item(0).getTextContent());
            long min = Long.parseLong(element.getElementsByTagName(connexion.CHAMP_MIN).item(0).getTextContent());
            String date = element.getElementsByTagName(connexion.CHAMP_DATE).item(0).getTextContent();

            humidite = new Humidite(moyenne, max, min, date);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return humidite;
    }


    public static void modificationURL() throws ParseException {
        url = connexion.URL_BASE;
        Date dateJour = new Date();
        long tsi = dateJour.getTime()/1000;

        String echantillonnage = "jour";
        url += "/" + echantillonnage + "/" + ((tsi - (24 * 3600))) + "/" + (tsi);
        System.out.println(url);


    }
}
