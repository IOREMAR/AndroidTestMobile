package mariosdevelop.androidtestmobile.Controllers;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;

import java.util.ArrayList;

import mariosdevelop.androidtestmobile.Controllers.Adapters.MarcaRecyclerView;
import mariosdevelop.androidtestmobile.Controllers.DBHelper.DBManager;
import mariosdevelop.androidtestmobile.Controllers.Interface.onMarcaSelected;
import mariosdevelop.androidtestmobile.R;

public class MarcaActivity extends AppCompatActivity implements onMarcaSelected {

    public ArrayList Marcas =  new ArrayList<>();
    public DBManager dbManager;

    private GridLayoutManager lLayout;

    private RecyclerView GridLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marca);

        Toolbar toolbar = (Toolbar) findViewById(R.id.ToolbarSeccion);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        setSupportActionBar(toolbar);

       // toolbar.setOverflowIcon(getResources().getDrawable(R.drawable.ic_settings));
        //actionBar.setIcon();

        dbManager =  new DBManager(this);
        dbManager.CreateDataMarcas();
        Marcas =  dbManager.getData();

        for(int i =0; i<Marcas.size();i++ ){
            Log.v("DB DAta" + i , Marcas.get(i).toString());
        }


        lLayout = new GridLayoutManager(this,3);

        GridLayout = (RecyclerView)findViewById(R.id.GridLayout);
        GridLayout.setHasFixedSize(true);
        GridLayout.setLayoutManager(lLayout);

        MarcaRecyclerView RecyclerviewAdapter = new MarcaRecyclerView(Marcas,this,this);

        GridLayout.setAdapter(RecyclerviewAdapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.menu_marca, menu);

        return true;
    }


    @Override
    public void OnMarcaSelected(int Position) {

        //Implementacion del Onclick

        Intent LoginAgent = new Intent(this,Login_Agent_Activity.class);
        startActivity(LoginAgent);

    }
}
