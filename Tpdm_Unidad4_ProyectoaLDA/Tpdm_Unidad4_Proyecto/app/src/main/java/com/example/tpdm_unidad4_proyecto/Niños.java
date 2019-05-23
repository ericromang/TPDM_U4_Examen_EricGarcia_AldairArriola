package com.example.tpdm_unidad4_proyecto;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Niños {

    int x, y, x1, x2,y1,y2,vidas;
    Niño niño;
    Bitmap imagen;
    public Niños(Lienzo este, int posx, int posy){
        x = posx;
        y = posy;
        niño = new Niño("eric","Warrior",0,3);
    }

    public void pintar(Canvas c, Paint p,Lienzo este){
        int aux=3;
        if(niño.indiceImg > 6)
            aux = 6;
        else if(niño.indiceImg < 0)
            aux = 0;
        else
            aux = niño.indiceImg;
        imagen = BitmapFactory.decodeResource(este.getResources(),niño.imagen.get(aux));
        c.drawBitmap(imagen,x,y,p);
    }

}
