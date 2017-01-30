package com.example.slafuente.listacompra;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class CheckoutActivity extends AppCompatActivity {

    private ListView listView;
    ArrayAdapter<Producto> adapterProducto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        getDatos();
    }

    public void getDatos(){

        Intent intent = getIntent();
        ArrayList<Producto> seleccionados = (ArrayList<Producto>) getIntent().getSerializableExtra("seleccionados");
        for (Producto p : seleccionados) {
            Log.e(getClass().getCanonicalName(), p.getNombre());
        }
        listView = (ListView)findViewById(R.id.listViewSelec);
        adapterProducto = new ListaAdapter(CheckoutActivity.this, seleccionados);
        listView.setAdapter(adapterProducto);
    }


}
