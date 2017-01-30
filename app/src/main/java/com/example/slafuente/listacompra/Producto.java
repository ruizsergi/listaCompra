package com.example.slafuente.listacompra;


import java.io.Serializable;

/**
 * Created by slafuente on 30/01/2017.
 */

public class Producto  implements Serializable {

    private String nombre;
    private int idImagen;
    private boolean selected;

    public Producto(String nombre, int idImagen) {
        this.nombre = nombre;
        this.idImagen = idImagen;
        this.selected = false;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdImagen() {
        return idImagen;
    }

    public void setIdImagen(int idImagen) {
        this.idImagen = idImagen;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

}
