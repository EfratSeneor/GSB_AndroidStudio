package fr.be2.ppe_gsb;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class FraisHorsForfait extends AppCompatActivity {


    SQLHelper bdd;
    EditText libelle;
    EditText montant;
    EditText date;
    DatePickerDialog picker;
    Calendar calendrier = Calendar.getInstance();
    int aaaa = calendrier.get(Calendar.YEAR);
    int mm = calendrier.get(Calendar.MONTH);
    int jj = calendrier.get(Calendar.DAY_OF_MONTH);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frais_hors_forfait);
        bdd= new SQLHelper(this);
        libelle=findViewById(R.id.libelle);
        date=findViewById(R.id.Date);
        montant=findViewById(R.id.montant);
    }

    /**
     * Ajoute les valeurs saisies à la base de donnée
     * Si la fonction a bien enregistré les frais dans la base de donées, elle affiche un message de succés
     *
     * @param view
     *
     * @return null
     */
    public void save_DATA(View view){
        String libelle1 = libelle.getText().toString();
        Double montant1 = Double.parseDouble(montant.getText().toString());
        String date1 = date.getText().toString();
        if( bdd.insertData(libelle1, libelle1,0, montant1, date1)) {
            libelle.setText("");
            montant.setText("");
            date.setText("");

            Toast.makeText(FraisHorsForfait.this,"Frais enregistré" ,Toast.LENGTH_LONG).show();

        }
    }

    /**
     * Effectue un retour en arrière soit arrête l'activité en cours
     *
     * @param view
     *
     * @return null
     */
    public void clique_retour(View view) {
        finish();
    }

    /**
     *
     *Affiche un calendrier de dates mis à jour avec la date du jour actuel
     *
     * @param v
     */
    public void ShowCal(View v)
    {
    picker = new DatePickerDialog(FraisHorsForfait.this ,
    new DatePickerDialog.OnDateSetListener() {
    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
    date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
    }
    },aaaa, mm, jj );
    picker.show();
    }

}