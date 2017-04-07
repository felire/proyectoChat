package com.example.felipe.chatvuelveacompilar;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class VentanaChat extends AppCompatActivity implements View.OnClickListener{

    private TextView texto;
    private EditText mensaje;
    private Contacto contacto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_chat);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        contacto = (Contacto) getIntent().getExtras().getSerializable("contacto");
        Button boton = (Button) findViewById(R.id.botonEnviar);
        boton.setOnClickListener(this);
        texto = (TextView) findViewById(R.id.nombreCon);
        texto.setText(contacto.getNombre());
        mensaje = (EditText) findViewById(R.id.campo_nombre);

    }

    @Override
    public void onClick(View view) {
        System.out.println("A ver si se manda esta basura");
        String mensajeAEnviar = mensaje.getText().toString();
        new ConnectionThreadEnviarMensaje(ConnectionClientt.getInstance(), contacto.getId(), mensajeAEnviar).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }
}
