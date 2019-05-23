package com.example.tpdm_unidad4_proyecto;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Mensajes {
    Bitmap imagen;
    int x, y, x1, x2,y1,y2;

    public Mensajes(Lienzo este,int imagen, int posx, int posy){
        this.imagen = BitmapFactory.decodeResource(este.getResources(),imagen);
        x = posx;
        y = posy;
    }

    public void pintar(Canvas c, Paint p){
            c.drawBitmap(imagen,x,y,p);
    }


}
