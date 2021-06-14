package fr.be2.ppe_gsb;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class FraisForfait extends MainActivity {
    //declaration des variables
    SQLHelper bdd;
    Spinner typefrais;
    EditText quantite;
    Button btnAjouter;
    DatePickerDialog picker;
    Calendar calendrier = Calendar.getInstance();
    int aaaa = calendrier.get(Calendar.YEAR);
    int mm = calendrier.get(Calendar.MONTH);
    int jj = calendrier.get(Calendar.DAY_OF_MONTH);
    //tableaux des montants frais au forfait
    Double montantfrais[]=new Double[]{0.62,110.00,80.00,25.00};


    //constructeur
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frais_forfait);
        init();
        bdd = new SQLHelper (this);

    }

    private void init() {
        typefrais= findViewById(R.id.typefrais);
        quantite= findViewById(R.id.quantite);
        btnAjouter= findViewById(R.id.btnAjouter);
    }

    /**
     * affiche un message contenant un titre et un contenu passés en paramètres (boite de dialogue)
     *
     * @param titre
     * @param message
     *
     * @return null
     */
    public void afficherMessage(String titre,String message){
        AlertDialog.Builder Builder=new AlertDialog.Builder(this ) ;
        Builder.setCancelable(true);
        Builder.setTitle(titre);
        Builder.setMessage(message);
        Builder.show();

    }

    /**
     * Ajoute les valeurs saisies à la base de donnée;
     * La fonction verifie dans un premier temps si le visiteur a bien rempli le champ quantité
     * Elle calcule le montant du frais (produit de la quantité par le montant fixé)
     * Si la fonction a bien enregistré les frais dans la base de donées, elle affiche un message de succés
     *
     * @param v
     *
     * @return null
     */
    public void MonClick(View v ) {
        switch (v.getId()) {
            case R.id.btnAjouter:

                if (quantite.getText().toString().length() == 0) {
                    afficherMessage("Erreur !", "Vous n'avez saisi aucune valeur ");
                    return;

                } else {
                    Integer quantite1 = Integer.parseInt(quantite.getText().toString()); //cette ligne a un pb. Il narrive pas à inserer les donnees
                    String forfait1 = typefrais.getSelectedItem().toString();
                    int posForfait = typefrais.getSelectedItemPosition();
                    Double montantCalcule = quantite1 * montantfrais[posForfait-1];

                    if (bdd.insertData(forfait1, forfait1, quantite1, montantCalcule, null)) {
                        afficherMessage("Succès", "Valeur ajoutée. " + "Montant= " + montantCalcule);
                    }
                }
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

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
    }
}