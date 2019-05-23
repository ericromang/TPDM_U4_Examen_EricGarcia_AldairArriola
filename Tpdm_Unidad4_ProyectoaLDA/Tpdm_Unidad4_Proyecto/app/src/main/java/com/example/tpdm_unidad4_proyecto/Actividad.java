package com.example.tpdm_unidad4_proyecto;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;

public class Actividad{
    String nombre;
    String descripcion;
    String tipo_;
    int imagen;
    int calorias;
    ArrayList<Actividad> tipo = new ArrayList<Actividad>();
    public Actividad(){
        llenar();
    }
    public Actividad(String nom, String desc, int img, int cal,String tipo_){
        nombre = nom;
        descripcion = desc;
        imagen = img;
        calorias = cal;
        this.tipo_=tipo_;
    }
    public void llenar(){
        tipo.add(new Actividad("Correr","30 minutos",R.drawable.correr,300,"s"));
        tipo.add(new Actividad("Voleibol","1 hora",R.drawable.voleibol,364,"s"));
        tipo.add(new Actividad("Futbol","1 hora",R.drawable.futbol,260,"s"));
        tipo.add(new Actividad("Golf","1 hora",R.drawable.golf,391,"s"));
        tipo.add(new Actividad("Basketball","1 hora",R.drawable.basketball,728,"s"));
        tipo.add(new Actividad("Béisbol","1 hora",R.drawable.beisbol,455,"s"));
        tipo.add(new Actividad("Patinar","1 hora",R.drawable.patinar,683,"s"));
        tipo.add(new Actividad("Ciclismo","1 hora",R.drawable.ciclismo,292,"s"));
        tipo.add(new Actividad("Peliculas","1 hora",R.drawable.peliculas,0,"n"));
        tipo.add(new Actividad("Siesta","1 hora",R.drawable.siesta,0,"n"));
        tipo.add(new Actividad("Caminata","1 hora",R.drawable.caminata,255,"s"));
        tipo.add(new Actividad("Television","1 hora",R.drawable.television,0,"n"));
        tipo.add(new Actividad("Videojuegos","1 hora",R.drawable.videojuegos,0,"n"));
        tipo.add(new Actividad("Desvelarse","1 hora",R.drawable.tarde,0,"n"));
        tipo.add(new Actividad("Chatear","1 hora",R.drawable.chatear,0,"n"));
    }
}