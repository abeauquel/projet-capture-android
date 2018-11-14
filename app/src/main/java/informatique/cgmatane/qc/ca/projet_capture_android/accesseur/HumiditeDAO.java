package informatique.cgmatane.qc.ca.projet_capture_android.accesseur;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringBufferInputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

// https://stackoverflow.com/questions/32153318/httpclient-wont-import-in-android-studio
// import java.net.URLConnection;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import informatique.cgmatane.qc.ca.projet_capture_android.VuePrincipale;
import informatique.cgmatane.qc.ca.projet_capture_android.modele.Humidite;

import static android.content.Context.MODE_PRIVATE;

public class HumiditeDAO {

    private static Connexion connexion;
    private static Humidite humidite;
    private static String url;

//    public static String URL_RAPPORTER_HUMIDITE = "http://54.39.145.59/projet-capture-serveur-php/humidites/jour/1534377600/1534463940";

    protected static ServiceWeb serviceWeb;
    protected static String xml = "";
    protected static String delimiteur = "";

    public static Humidite humiditeSelonURL()
    {
        connexion = new Connexion();
        try
        {
            DocumentBuilderFactory f =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder b = f.newDocumentBuilder();
            Document doc = b.parse(url);
            doc.getDocumentElement().normalize();
            Element element = doc.getDocumentElement();
            System.out.println ("Root element: " +
                    doc.getDocumentElement().getNodeName());

            // https://stackoverflow.com/questions/9856195/how-to-read-an-http-input-stream
            BufferedReader lecteur = new BufferedReader(new InputStreamReader(connexion.getInputStream()));
            StringBuffer accumulateur = new StringBuffer();
            String ligne = "";
            while ((ligne = lecteur.readLine()) != null) accumulateur.append(ligne);
            xml = accumulateur.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.d("CORRECTIONERREUR", "XML :" + xml);

        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            InputSource src = new InputSource();
            src.setCharacterStream(new StringReader(xml));
            Document doc = builder.parse(src);

            long moyenne = Long.parseLong(doc.getElementsByTagName("moyenne").item(0).getTextContent());
            Log.d("CORRECTIONERREUR", "moyenne : " + moyenne);
            long max = Long.parseLong(doc.getElementsByTagName("max").item(0).getTextContent());
            long min = Long.parseLong(doc.getElementsByTagName("min").item(0).getTextContent());
//            String date = doc.getElementsByTagName(connexion.CHAMP_DATE).item(0).getTextContent();

            humidite = new Humidite(moyenne, max, min, "2018-02-25");

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        return humidite;

    }



    public static void modificationURL()
    {
        url = connexion.URL_BASE;
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.CANADA);
        Date dateJour = new Date();

        String echantillonnage = "heure";
        String dateDebut = dateFormat.format(dateJour);
        String dateFin = dateFormat.format(dateJour);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date dateDebutFormat = null;
        Date dateFinFormat = null;
        try {
            dateDebutFormat = new Date(sdf.parse(dateDebut).getTime());
            dateFinFormat = new Date(sdf.parse(dateFin).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        url += "/" + echantillonnage + "/" + (dateDebutFormat.getTime()/1000) + "/" + (dateFinFormat.getTime()/1000);

        System.out.println(url);


    }
}
