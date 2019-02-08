package com.example.walther.leccion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class Respaldo extends AppCompatActivity {
    Bundle b = new Bundle();
    EditText bueno, malo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_respaldo);

        b = getIntent().getExtras();
        String conbueno = getIntent().getExtras().getString("bueno");
        String conmalo = getIntent().getExtras().getString("malo");
        bueno = (EditText) findViewById(R.id.bueno);
        malo = (EditText) findViewById(R.id.malo);
        bueno.setText(conbueno);
        malo.setText(conmalo);
    }
}
