package com.example.slafuente.listacompra;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by slafuente on 30/01/2017.
 */

public class ListaAdapter extends ArrayAdapter<Producto> {

    private final List<Producto> lista;
    private final Context context;
    private TextView tvNombre = null;
    private ImageView imagenProducto;
    private int idImagegProducto;

    public ListaAdapter(Context context, List<Producto> lista){
        super(context, R.layout.list_fila, lista);
        this.context = context;
        this.lista = lista;
    }

    static class ViewHolder {
        protected TextView tvNombreX;
        protected ImageView imagenProductoX;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        //if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            view = layoutInflater.inflate(R.layout.list_fila,null);

            Log.e(getClass().getCanonicalName(), "noooo" + lista.get(position).getNombre());
            tvNombre = (TextView)view.findViewById(R.id.textView);
            tvNombre.setText(lista.get(position).getNombre());

            imagenProducto = (ImageView)view.findViewById(R.id.imageButton);
            int idImagen = lista.get(position).getIdImagen();
            imagenProducto.setImageResource(idImagen);


        return view;
    }
}
