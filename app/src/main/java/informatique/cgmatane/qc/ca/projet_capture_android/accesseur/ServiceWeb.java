package informatique.cgmatane.qc.ca.projet_capture_android.accesseur;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringBufferInputStream;
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
            URLConnection serviceMeteo = urlServiceMeteo.openConnection();
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
    }

}
