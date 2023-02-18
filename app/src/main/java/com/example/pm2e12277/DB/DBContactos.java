package com.example.pm2e12277.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;
import com.example.pm2e12277.Entidades.Contactos;
import java.util.ArrayList;

public class DBContactos extends DBHelper{
    Context context;

    public DBContactos(@Nullable Context context){
        super(context);
        this.context = context;
    }

    //METODO PARA INSERTAR NUEVO CONTACTO
    public long insertarContacto(String pais, String nombre, String telefono, String nota){

        long id = 0;

        try {
            DBHelper dbHelper = new DBHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("pais", pais);
            values.put("nombre", nombre);
            values.put("telefono", telefono);
            values.put("nota", nota);

            id = db.insert(TABLE_CONTACTOS, null, values);

        }catch (Exception ex){
            ex.toString();
        }
        return id;
    }

    //METODO PARA MOSTRAR LOS CONTACTOS
    public ArrayList<Contactos> mostrarContactos(){
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Contactos> listaContactos = new ArrayList<>();
        Contactos contacto;
        Cursor cursorContactos;

        cursorContactos = db.rawQuery("SELECT * FROM " + TABLE_CONTACTOS + " ORDER BY nombre ASC", null);

        if(cursorContactos.moveToFirst()){
            do{
                contacto = new Contactos();
                contacto.setId(cursorContactos.getInt(0));
                contacto.setPais(cursorContactos.getString(1));
                contacto.setNombre(cursorContactos.getString(2));
                contacto.setTelefono(cursorContactos.getString(3));
                contacto.setNota(cursorContactos.getString(4));
                listaContactos.add(contacto);
            }while (cursorContactos.moveToNext());
        }
        cursorContactos.close();
        return listaContactos;
    }

    //METODO PARA VER EL CONTACTO
    public Contactos verContacto(int id){
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Contactos contacto = null;
        Cursor cursorContactos;

        cursorContactos = db.rawQuery("SELECT * FROM " + TABLE_CONTACTOS + " WHERE id = " + id + " LIMIT 1", null);

        if(cursorContactos.moveToFirst()){
            contacto = new Contactos();
            contacto.setId(cursorContactos.getInt(0));
            contacto.setPais(cursorContactos.getString(1));
            contacto.setNombre(cursorContactos.getString(2));
            contacto.setTelefono(cursorContactos.getString(3));
            contacto.setNota(cursorContactos.getString(4));
        }
        cursorContactos.close();
        return contacto;
    }

    //METODO PARA EDITAR EL CONTACTO
    public boolean editarContacto(int id, String pais, String nombre, String telefono, String nota){
        boolean correcto = false;

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("UPDATE " + TABLE_CONTACTOS + " SET pais = '" + pais + "', nombre = '" + nombre + "', telefono = '" + telefono + "', nota = '" + nota + "' WHERE id='" + id + "' ");
            correcto = true;
        }catch (Exception ex){
            ex.toString();
            correcto = false;
        }finally {
            db.close();
        }
        return correcto;
    }

    //METODO PARA ELIMINAR EL CONTACTO
    public boolean eliminarContacto(int id){
        boolean correcto = false;

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM " + TABLE_CONTACTOS + " WHERE id = '" + id + "'");
            correcto = true;
        }catch (Exception ex){
            ex.toString();
            correcto = false;
        }finally {
            db.close();
        }
        return correcto;
    }
}
