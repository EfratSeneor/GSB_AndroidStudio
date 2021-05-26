package fr.be2.ppe_gsb;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class EnvoiDesDonneesVers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_envoi_des_donnees_vers);
    }
    public void clique_btn6 (View view){
        finish();
    }
}
