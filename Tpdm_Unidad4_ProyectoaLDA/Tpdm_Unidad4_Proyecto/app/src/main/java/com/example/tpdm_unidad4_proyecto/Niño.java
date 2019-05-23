package com.example.tpdm_unidad4_proyecto;

import android.graphics.Bitmap;

import java.util.ArrayList;

public class Niño {
    String nombre;
    String descripcion;
    ArrayList<Integer> imagen = new ArrayList<Integer>();
    int calorias,caloriasAcum,indiceImg;
    public Niño(String nom, String desc, int cal,int ind){
        nombre = nom;
        descripcion = desc;
        calorias = cal;
        indiceImg = ind;
        llenar();
    }
    public void llenar(){
        imagen.add(R.drawable._3);
        imagen.add(R.drawable._2);
        imagen.add(R.drawable._1);
        imagen.add(R.drawable.normal);
        imagen.add(R.drawable.m1);
        imagen.add(R.drawable.m2);
        imagen.add(R.drawable.m3);
    }
}