package fr.be2.ppe_gsb;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private int titre;
    private int message;
    private Object String;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Méthode clic bouton menu 1
     * appliquée sur l'objet "frais hors forfait"
     * lance la classe Menuprincipal
     */

    public void unclique(View view) {

        if (view.getId() == R.id.btnfraisforfait) {
            Intent intent = new Intent(getApplicationContext(), FraisForfait.class);
            startActivity(intent);
        }

        if (view.getId() == R.id.btnfraishorsforfait) {
            Intent intent = new Intent(getApplicationContext(), FraisHorsForfait.class);
            startActivity(intent);
        }

        if (view.getId() == R.id.btnsynthese) {
            Intent intent = new Intent(getApplicationContext(), SyntheseDuMois.class);
            startActivity(intent);
        }
        if (view.getId() == R.id.btnenvoi) {
            Intent intent = new Intent(getApplicationContext(), EnvoiDesDonneesVers.class);
            startActivity(intent);
        }

        if (view.getId() == R.id.btnparametres) {
            Intent intent = new Intent(getApplicationContext(), Parametres.class);
            startActivity(intent);
        }

    }
}