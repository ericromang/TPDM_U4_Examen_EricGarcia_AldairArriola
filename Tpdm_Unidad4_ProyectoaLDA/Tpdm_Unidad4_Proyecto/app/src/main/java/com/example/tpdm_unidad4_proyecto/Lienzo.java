package com.example.tpdm_unidad4_proyecto;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class Lienzo extends View {
    Thread hilo,hiloEventos;
    String posicion = "";
    Niños niño;
    Eventos eventos = new Eventos();
    Mensajes motivacion,mejorar;
    Actividad actividad = new Actividad();
    ArrayList<Niños> niños = new ArrayList<Niños>();
    ArrayList<Actividades> punterosActividades = new ArrayList<Actividades>();
    final MediaPlayer finalBob;


    int contador=60,contadorJefe=10,gano=0,semana=6;
    boolean accionM = false,motivador;

    public Lienzo(Context context){
        super(context);
        rellenarActividades();
        final MediaPlayer plop = MediaPlayer.create(context,R.raw.aleluya);
        final MediaPlayer what = MediaPlayer.create(context,R.raw.what);
        finalBob = MediaPlayer.create(context,R.raw.final_bob);
        mejorar = new Mensajes(this,R.drawable.mal,100,500);
        motivacion = new Mensajes(this,R.drawable.bien,800,500);
        niño = new Niños(this,430,300);
        //puntero = null;
        hilo = new Thread() {

            public void run() {
                while (true) {
                    if(gano==1)
                        return;
                    if(semana >= 1){
                        contador--;
                    }
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                    postInvalidate();
                }
            }
        };
        hilo.start();
        hiloEventos = new Thread() {
            public void run() {
                while (true) {
                    if(eventos.mejorar){
                        what.start();
                        animacion();
                    }
                    if(eventos.motivacion){
                        plop.start();
                        animacion();
                    }
                    try {
                        sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    postInvalidate();
                }
            }
            public void animacion(){
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                eventos.mejorar = false;
                eventos.motivacion = false;
                eventos.accion=false;
            }
        };
        hiloEventos.start();

    }
    public void rellenarActividades(){
        punterosActividades.clear();
        int saludable = 0;
        punterosActividades.add(new Actividades(actividad.tipo.get((int)(Math.random()*15)),this,80,1200));
        punterosActividades.add(new Actividades(actividad.tipo.get((int)(Math.random()*15)),this,430,1200));
        punterosActividades.add(new Actividades(actividad.tipo.get((int)(Math.random()*15)),this,800,1200));
        System.out.println("---------------------------------");
        for(int i = 0; i < 3;i++){
            System.out.println(punterosActividades.get(i).actividad.nombre);
            if(punterosActividades.get(i).actividad.tipo_.equals("s"))
                saludable++;
            for(int j = 0; j < 3;j++){
                if(punterosActividades.get(i).actividad.nombre.equals(punterosActividades.get(j).actividad.nombre) && i!=j)
                    rellenarActividades();
            }
        }
        if(saludable==0)
            rellenarActividades();
    }
    public void guardarAccion(int indiceActividad){
        semana--;
        niño.niño.calorias+=punterosActividades.get(indiceActividad).actividad.calorias;
        if(punterosActividades.get(indiceActividad).actividad.tipo_.equals("s")) {
            eventos.motivacion = true;
            niño.niño.indiceImg++;
        }
        else {
            eventos.mejorar = true;
            niño.niño.indiceImg--;
        }

        if(semana==-1) {
            eventos.finalizo = true;
            finalBob.start();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected void onDraw (Canvas c){

        Paint p = new Paint();
        /*p.setColor(Color.CYAN);
        c.drawRect(1080,0,0,1920,p);
        */
        Drawable d = getResources().getDrawable(R.drawable.fondo, null);
        d.setBounds(0,0,getWidth(),getHeight());
        d.draw(c);

        /* Espacio de finalizar juego---------------*/
        if(eventos.finalizo) {
            p.setColor(Color.WHITE);
            p.setFakeBoldText(true);
            p.setTextSize(60f);
            p.setElegantTextHeight(true);
            c.drawText("Calorias consumidas en total ", 150, 75, p);
            c.drawText("durante la semana: ", 150, 140, p);
            Drawable cal = getResources().getDrawable(R.drawable.caloria, null);
            cal.setBounds(300,150,440,290);
            cal.draw(c);
            c.drawText(""+niño.niño.calorias,470,250,p);
            niño.pintar(c, p, this);

            if(niño.niño.calorias>=2500) {
                c.drawText("Excelente! Si sigues asi seras un", 50, 1100, p);
                c.drawText("niño fuerte y saludable", 50, 1200, p);
            }
            if(niño.niño.calorias>=1800 && niño.niño.calorias<2500) {
                c.drawText("Buen trabajo, pero podrias mejorar", 50, 1100, p);
                c.drawText("deberias realizar más ejercicio", 50, 1200, p);
            }
            if(niño.niño.calorias<1800) {
                c.drawText("Si quieres ser saludable y fuerte", 50, 1100, p);
                c.drawText("deberias realizar más ejercicio", 50, 1200, p);
            }
            p.setColor(Color.parseColor("#ffa500"));
            c.drawRoundRect(50,1300,1030,1480,15,15,p);
            p.setColor(Color.WHITE);
            c.drawText("VOLVER A JUGAR", 280, 1410, p);
            return;
        }
        /* Espacio de finalizar juego---------------*/

        /*Estadisticas del juego ------------------*/
        p.setColor(Color.WHITE);
        p.setFakeBoldText(true);
        p.setTextSize(60f);
        p.setElegantTextHeight(true);
        Drawable dia = getResources().getDrawable(R.drawable.dia, null);
        dia.setBounds(100,90,240,230);
        dia.draw(c);
        Drawable cal = getResources().getDrawable(R.drawable.caloria, null);
        cal.setBounds(550,90,690,230);
        cal.draw(c);
        c.drawText("Dia: ",110,75,p);
        c.drawText("Calorias Gastadas:",530,75,p);
        c.drawText(""+niño.niño.calorias,720,200,p);
        p.setColor(Color.BLACK);
        c.drawText(""+(7-semana),140,200,p);
        /*Estadisticas del juego ------------------*/




        /*Eventos de motivacion---------------------------*/
        if(eventos.mejorar){
            mejorar.pintar(c,p);
        }
        if(eventos.motivacion){
            motivacion.pintar(c,p);
        }
        /*Eventos de motivacion---------------------------*/



        /*Seccion de pintar mono y actividades------------*/
        p.setTextSize(40f);
        niño.pintar(c,p,this);
        p.setColor(Color.parseColor("#ffa500"));
        c.drawRoundRect(50,1180,275,1480,15,15,p);
        c.drawRoundRect(400,1180,625,1480,15,15,p);
        c.drawRoundRect(770,1180,995,1480,15,15,p);

        p.setColor(Color.BLACK);
        punterosActividades.get(0).pintar(c,p);
        punterosActividades.get(1).pintar(c,p);
        punterosActividades.get(2).pintar(c,p);
        /*Seccion de pintar mono y actividades------------*/


    }

    public boolean onTouchEvent(MotionEvent me){
        //el evento ontouchevent permite detectar los toques
        //de uno o mas dedos que se hacen en el area de dibujo
        /*me.getAction() = accion: presiono,soltar,mover
                       pos x pos y*/

        int accion = me.getAction();
        int posx = (int) me.getX();
        int posy = (int) me.getY();

        switch (accion){
            case MotionEvent.ACTION_DOWN:
                //presiono
                if(eventos.finalizo) {
                    if (posx>50 && posx<1030 && posy>1300 && posy<1480){
                        Intent intent = new Intent(this.getContext(), MainActivity.class);
                        this.getContext().startActivity(intent);
                    }
                    return false;
                }
                for(int i = 0; i < 3;i++){
                    if(punterosActividades.get(i).estaEnArea(posx,posy) && eventos.accion!=true){
                        eventos.accion=true;
                        guardarAccion(i);
                        rellenarActividades();
                        break;
                    }
                }

                break;
            case MotionEvent.ACTION_MOVE:
                break;

            case MotionEvent.ACTION_UP:

                break;

        }

        invalidate();
        return true;
    }

}
