package fr.be2.ppe_gsb;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;
import java.util.ArrayList;


import static java.lang.Integer.parseInt;

public class SyntheseDuMois extends AppCompatActivity {

    private SQLHelper dbHelper;
    private SimpleCursorAdapter dataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_synthese_du_mois);
        dbHelper = new SQLHelper(this);
        dbHelper.open();

        //Générer le ListView a partir de SQLite Database
        displayListView();
    }
    private void displayListView() {

        Cursor cursor = dbHelper.fetchAllCountries();

        // Les colonnes que l’on veut lier
        String[] columns = new String[] {
                SQLHelper.KEY_ID,
                SQLHelper.KEY_LIBELLE,
                SQLHelper.KEY_MONTANT,
                SQLHelper.KEY_DATEDESAISIT,
                SQLHelper.KEY_DATEDEFRAIS,
                SQLHelper.KEY_QUANTITE,
                SQLHelper.KEY_TYPEDEFRAIS

        };

        // Les éléments defnis dans le XML auxquels les données sont liées
        int[] to = new int[] {
                R.id.id,
                R.id.libelle,
                R.id.montant,
                R.id.datesaisie,
                R.id.datefrais,
                R.id.quantite,
                R.id.TypeFrais,
        };

        //On créer l'adaptateur à l'aide du curseur pointant sur les données souhaitées  ainsi que les informations de mise en page
        dataAdapter = new SimpleCursorAdapter(
                this, R.layout.frais_info,
                cursor,
                columns,
                to,
                0);

        ListView listView = (ListView) findViewById(R.id.listeFF);
        // Attribuer l’adapter au ListView
        listView.setAdapter(dataAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listView, View view,
                                    int position, long id) {
                // On obtient le curseur, positionne sur la ligne correspondante dans le jeu de résultats
                Cursor cursor = (Cursor) listView.getItemAtPosition(position);

                // On obtient la Capital du pays
                String myId =
                        cursor.getString(0);
                Toast.makeText(getApplicationContext(),
                        myId, Toast.LENGTH_SHORT).show();
                dbHelper.deleteData(parseInt(myId));

            }
        });
    }
    public void doDeleteOnClick(View v) {
        Toast.makeText(v.getContext(),"You clicked the DELETE button for id " + ((String) v.getTag()), Toast.LENGTH_SHORT).show();
    }

    public void clique_btn6 (View view){
        finish();
    }
}
