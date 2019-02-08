package com.example.walther.leccion;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Runnable{
    private Button enviar;
    private EditText texto;
    NotificationCompat.Builder mbuilder;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private int conMalo, conBueno, contador;
    private Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        enviar = (Button) findViewById(R.id.button);
        texto = (EditText) findViewById(R.id.editText);
        vibrator= (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        iniciarFirebase();
    }
    @SuppressLint("MissingPermission")
    public void click(View view){
        contador++;
        if(texto.getText().toString().equals("5")){
            conBueno++;
             final MediaPlayer mp = MediaPlayer.create(this, R.raw.sonido);
             mp.start();
             texto.setText("");
             mbuilder= new NotificationCompat.Builder(this)
                     .setContentTitle("Felicidades")
                     .setContentText("Felicidades");
             Intent resultIntent= new Intent(this, Respaldo.class);
            PendingIntent res= PendingIntent.getActivities(this,0, new Intent[]{resultIntent}, PendingIntent.FLAG_UPDATE_CURRENT);
            mbuilder.setContentIntent(res);

             if(contador==3){
                 run();
             }
        }
        else {
            if (vibrator.hasVibrator()) {
                Toast.makeText(this, "Intenta de nuevo",
                        Toast.LENGTH_LONG).show();
                texto.setText("");
                conMalo++;
                vibrator.vibrate(500);
                if(contador==3){
                    run();
                }
            }
        }
        //databaseReference.child("Objeto").child(texto.getText().toString()).setValue(texto.getText().toString());
        //Sirve para obj too
    }
    public void iniciarFirebase(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    @Override
    public void run() {
        Puntaje puntaje= new Puntaje(conBueno, conMalo);
        databaseReference.child("Objeto").child(conBueno+"-"+conMalo).setValue(puntaje);
        Intent intent=new Intent(MainActivity.this, Respaldo.class);
        intent.putExtra("bueno", ""+conBueno+"");
        intent.putExtra("malo", ""+conMalo+"");
        startActivity(intent);
    }
}
