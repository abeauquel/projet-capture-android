package informatique.cgmatane.qc.ca.projet_capture_android;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import informatique.cgmatane.qc.ca.projet_capture_android.accesseur.HumiditeDAO;
import informatique.cgmatane.qc.ca.projet_capture_android.modele.Humidite;

public class VuePrincipale extends AppCompatActivity {

    private HumiditeDAO humiditeDAO;

    public SharedPreferences sharedPref;
    public static final String moyenne = "moyenne";
    public static final String maximum = "maximum";
    public static final String minimum = "minimum";
    public static final String date = "date";

    protected TextView textMoyenne;
    protected TextView textMax;
    protected TextView textMin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_principale);

        Toolbar toolbar = (Toolbar) findViewById(R.id.barrre_navigation);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("MétéoQC");
        toolbar.setSubtitle("Humidite :");


        textMoyenne = findViewById(R.id.retroactionMoyenne);
        textMax = findViewById(R.id.retroactionMax);
        textMin = findViewById(R.id.retroactionMin);

        humiditeDAO = new HumiditeDAO();

        afficherHumidite();
        afficherDate();
    }

    public void afficherDate()
    {
        // afficher la date du jour
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.CANADA);
        Date dateJour = new Date();

        TextView vueRetroactionDate = (TextView)findViewById(R.id.retroactionDate);
        vueRetroactionDate.setText("Date : " + dateFormat.format(dateJour));
    }

    public void afficherHumidite()
    {
        enregistrerDonneeJour();
        textMoyenne.setText(sharedPref.getString(moyenne,"moyenne"));
    }

    public void enregistrerDonneeJour()
    {
        Humidite humidite = HumiditeDAO.rapporterHumidite();

        sharedPref = getApplicationContext().getSharedPreferences("informatique.cgmatane.qc.ca.projet_capture_android", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(moyenne, humidite.getMoyenne()).apply();
        editor.putString(maximum, humidite.getMaximum()).apply();
        editor.putString(minimum, humidite.getMinimum()).apply();

    }
}
