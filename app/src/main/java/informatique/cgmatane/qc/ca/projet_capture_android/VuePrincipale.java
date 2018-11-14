package informatique.cgmatane.qc.ca.projet_capture_android;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import informatique.cgmatane.qc.ca.projet_capture_android.accesseur.HumiditeDAO;
import informatique.cgmatane.qc.ca.projet_capture_android.modele.Humidite;

public class VuePrincipale extends AppCompatActivity {

    private HumiditeDAO humiditeDAO;
    private Humidite humidite;

    public SharedPreferences sharedPref;
    public static final String HUMDITE_PREFERENCES = "donneeMeteo";
    public static final String moyenne = "moyenne";
    public static final String maximum = "maximum";
    public static final String minimum = "minimum";
    public static final String date = "date";

    protected TextView textMoyenne;
    protected TextView labeltextMoyenne;
    protected TextView labeltextMax;
    protected TextView textMax;
    protected TextView labeltextMin;
    protected TextView textMin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_principale);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Toolbar toolbar = (Toolbar) findViewById(R.id.barrre_navigation);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("MétéoQC");

        labeltextMoyenne = findViewById(R.id.labelMoyenne);
        textMoyenne = findViewById(R.id.retroactionMoyenne);
        labeltextMax = findViewById(R.id.labelMax);
        textMax = findViewById(R.id.retroactionMax);
        labeltextMin = findViewById(R.id.labelMin);
        textMin = findViewById(R.id.retroactionMin);

        humiditeDAO = new HumiditeDAO();
        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                HumiditeDAO.modificationURL();
                humidite = HumiditeDAO.humiditeSelonURL();
                if (humidite != null) {
                    afficherHumidite();
                }else{
                    textMoyenne.setText("Pas de données");
                    textMin.setText("Pas de données");
                    textMax.setText("Pas de données");
                }
                afficherDate();            }
        }, 0, 5000);


    }

    public void afficherDate() {
        // afficher la date du jour
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.CANADA);
        Date dateJour = new Date();

        TextView vueRetroactionDate = (TextView) findViewById(R.id.retroactionDate);
        vueRetroactionDate.setText("Date : " + dateFormat.format(dateJour));
    }

    public void afficherHumidite() {
        enregistrerDonneeJour();
        textMoyenne.setText(String.valueOf(sharedPref.getLong(moyenne, 0)));
        textMax.setText(String.valueOf(sharedPref.getLong(maximum, 0)));
        textMin.setText(String.valueOf(sharedPref.getLong(minimum, 0)));
    }

    public void enregistrerDonneeJour() {
//        humidite = humiditeDAO.rapporterHumidite();

        sharedPref = getSharedPreferences(HUMDITE_PREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor editeur = sharedPref.edit();
        editeur.putLong("moyenne", humidite.getMoyenne());
        editeur.putLong("maximum", humidite.getMaximum());
        editeur.putLong("minimum", humidite.getMinimum());
        editeur.apply();

    }
}
