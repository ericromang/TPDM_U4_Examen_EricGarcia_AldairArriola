package com.example.tpdm_unidad4_proyecto;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.media.Image;

import java.util.ArrayList;

public class Actividades {
    int x, y, x1, x2,y1,y2,vidas;
    Actividad actividad;
    Bitmap imagen;
    public Actividades(Actividad act,Lienzo este, int posx, int posy){
        actividad = act;
        imagen = BitmapFactory.decodeResource(este.getResources(),actividad.imagen);
        x = posx;
        y = posy;
    }
    public void mover(int dedox,int dedoy){
        x = dedox ;
        y = dedoy ;

    }

    public void pintar(Canvas c, Paint p){

            c.drawBitmap(imagen,x,y,p);
            c.drawText(actividad.nombre,x+10,y+220,p);
    }

    public boolean estaEnArea(int dedox, int dedoy) {
        int x2 = x + imagen.getWidth();
        int y2 = y + imagen.getHeight();

        if (dedox > x && dedox <= x2) {
            if (dedoy >= y && dedoy <= y2) {
                return true;
            }

        }
        return false;
    }
}
