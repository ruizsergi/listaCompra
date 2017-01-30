package com.example.slafuente.listacompra;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //private EditText nombre;
    private  List<Producto> listaProductos;
    private  List<Producto> listaProductosSeleccionados;
    private  Context context;
    private ListView listView;
    ArrayAdapter<Producto> adapterProducto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        addView();
    }

    private void addView() {
        //nombre = (EditText)findViewById(R.id.nombre);
        listaProductosSeleccionados = new ArrayList<Producto>();
        listView = (ListView)findViewById(R.id.listView);
        adapterProducto = new ListaAdapter(MainActivity.this, getListaProductos());
        listView.setAdapter(adapterProducto);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "Long Clicked : " + position + " and id: "
                        + id, Toast.LENGTH_LONG).show();
                //abrir menu con opciones
                return true;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Producto p = dameProducto(listaProductos.get(position).getNombre(),
                        listaProductos.get(position).getIdImagen());
                listaProductosSeleccionados.add(p);
                view.setSelected(true);
                view.setBackgroundColor(Color.parseColor("#ACFA58"));
            }
        });
    }


    private List<Producto> getListaProductos () {
        listaProductos = new ArrayList<Producto>();

        String [] nombresProductos;
        nombresProductos = context.getResources().getStringArray(R.array.nombresProductos);

        int [] imagenes= new int[]{
                R.drawable.icon_pan,
                R.drawable.icon_leche,
                R.drawable.icon_agua,
                R.drawable.icon_queso,
                R.drawable.icon_jamon,
                R.drawable.icon_yogur,
                R.drawable.icon_cerveza,
                R.drawable.icon_manzana,
                R.drawable.icon_naranja,
                R.drawable.icon_vino,
                R.drawable.icon_salmon
        };

        for(int i=0; i<imagenes.length;i++) {
            Log.e(getClass().getCanonicalName(), "adding product " + nombresProductos[i]);
            listaProductos.add(dameProducto(nombresProductos[i], imagenes[i]));
        }
        Log.e(getClass().getCanonicalName(), "FUERA DEL IF " + listaProductos.size());

        return listaProductos;
    }

    /**
     * Devuelve un producto
     * @param nombre
     * @param imagen
     * @return
     */
    private Producto dameProducto(String nombre, int imagen){
        return new Producto(nombre, imagen);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Infla el menu y lo anade a la action bar
        getMenuInflater().inflate(R.menu.mi_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //return super.onOptionsItemSelected(item);
        int id = item.getItemId();

        switch(id) {
            case R.id.menu_checkout:
                //Vamos a la segunda actividad mostrando los elementos seleccionados
                Intent intent = new Intent(getApplicationContext(), CheckoutActivity.class);
                intent.putExtra("seleccionados", (Serializable) listaProductosSeleccionados);
                startActivity(intent);
                //Notificamos al adapter
                adapterProducto.notifyDataSetChanged();
                break;
            case R.id.menu_limpiar:
                listaProductosSeleccionados.clear();
                adapterProducto.notifyDataSetChanged();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        listaProductosSeleccionados.clear();
        listaProductosSeleccionados.removeAll(listaProductosSeleccionados);
    }
}
