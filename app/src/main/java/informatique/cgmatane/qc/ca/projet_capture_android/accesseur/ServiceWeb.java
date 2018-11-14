package informatique.cgmatane.qc.ca.projet_capture_android.accesseur;

import android.util.Log;

import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringBufferInputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import org.w3c.dom.Element;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class ServiceWeb {

    String xml = null ;

    protected String doInBackground(String...parametres){
        String url = parametres[0];
        String delimiteur = parametres[1];

        xml = recupererXML(url, delimiteur);

        if (null == xml){
            return null;
        }
        return xml;
    }

    private String recupererXML(String url, String delimiteur)
    {
        try {
            URL urlXML = new URL(url);
            InputStream flux = urlXML.openConnection().getInputStream();
            Scanner scanner = new Scanner(flux).useDelimiter(delimiteur);
            xml = scanner.next() + delimiteur;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return xml;
    }









    public static String lireBalise(Element element, String balise)
    {
        return element.getElementsByTagName(balise).item(0).getTextContent();
    }


    public static Document parserXML(String xml)
    {
        Document doc = null;
        DocumentBuilder parseur;
        try {
            parseur = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            doc = parseur.parse(new StringBufferInputStream(xml));
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return doc;
    }

    public static String recuperationDonneMeteo(String url)
    {
        String xml = null;
        try {
            URL urlServiceMeteo = new URL(url);
            HttpURLConnection serviceMeteo = (HttpURLConnection) urlServiceMeteo.openConnection();
            int responseCode = serviceMeteo.getResponseCode();
            InputStream fluxDonnee = serviceMeteo.getInputStream();

            Scanner lecteur = new Scanner(fluxDonnee).useDelimiter("\\A");
            xml = lecteur.hasNext() ? lecteur.next() : "";
            System.out.println(xml);
            return xml;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

//        BufferedReader in = null;
//        try {
//            URL urlService = new URL(url);
//
//            in = new BufferedReader(
//                    new InputStreamReader(
//                            url.openStream(),"UTF-8"));//in most cases there is utf 8
//
//            String inputLine;
//            StringBuilder builder = new StringBuilder();
//            while ((inputLine = in.readLine()) != null)
//                builder.append(inputLine);
//            String urlContent = builder.toString();
//            return urlContent;
//            // process your received data somehow
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (in != null) {
//                try {
//                    in.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return null;
    }

}
