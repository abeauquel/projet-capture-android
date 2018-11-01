package informatique.cgmatane.qc.ca.projet_capture_android.controleur;

import informatique.cgmatane.qc.ca.projet_capture_android.VuePrincipale;

public class Controleur
{
    private VuePrincipale vueHumidite;
    private static Controleur instance = null;


    private Controleur() {}

    public static Controleur getInstance() {
        if (null == instance) {
            instance = new Controleur();
        }
        return instance;
    }

//    public void activerVues(NavigateurDesVues navigateurDesVues) {
//
//        this.navigateurDesVues = navigateurDesVues;
//
//        this.vueHumidite = this.navigateurDesVues.getVueHumidite();
//    }
}
