package com.example.felipe.chatvuelveacompilar;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphRequestBatch;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class LoginFacebook extends AppCompatActivity implements View.OnClickListener{
    CallbackManager callbackManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final LoginFacebook yo = this;
        callbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.activity_login_facebook);
        Button botonLogin = (Button) findViewById(R.id.botonFacebook);
        botonLogin.setOnClickListener(this);
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                //sSystem.out.println("Vamos por aca, debuggeo saladito... \n");
                final Perfil perfil = new Perfil();
                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        //System.out.println("Primer batch comienzo... \n");
                        darPerfil(object, perfil);
                        //System.out.println(perfil.getId());
                        //System.out.println("Primer batch ejecutada :) \n");
                    }
                });
                Bundle parametros = new Bundle();
                parametros.putString("fields", "id,name,picture");
                request.setParameters(parametros);
                GraphRequest requestAmigos = GraphRequest.newMyFriendsRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONArrayCallback() {
                    @Override
                    public void onCompleted(JSONArray objects, GraphResponse response) {
                       // System.out.println("Segunda batch comienzo... \n");
                        perfil.setContactos(darListaContactos(objects));
                       // System.out.println("Segunda batch ejecutada :) \n");
                    }
                });
                Bundle parametros2 = new Bundle();
                parametros2.putString("fields", "id, name, picture");
                requestAmigos.setParameters(parametros2);
                GraphRequestBatch batch = new GraphRequestBatch(request, requestAmigos);
                /*BarraCarga barra = new BarraCarga(yo, batch);
                barra.execute();*/
                final ProgressDialog progressDialog;
                progressDialog = new ProgressDialog(yo);
                progressDialog.setMessage("Descargando info...");
                progressDialog.setCancelable(false);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.show();
                batch.addCallback(new GraphRequestBatch.Callback() {
                    @Override
                    public void onBatchCompleted(GraphRequestBatch batch) {
                        conectarYIngresarADB(perfil);
                        Intent intent = new Intent(yo, Menu.class);
                        startActivity(intent);
                        finish();
                        progressDialog.dismiss();
                    }
                });
                new EjecutadorBatch(batch).execute();
                //batch.executeAsync();
        }

            @Override
            public void onCancel() {
                Button boton = (Button) findViewById(R.id.botonFacebook);
                boton.setVisibility(View.VISIBLE);
            }

            @Override
            public void onError(FacebookException error) {
                Button boton = (Button) findViewById(R.id.botonFacebook);
                boton.setVisibility(View.VISIBLE);
            }
        });
    }

    public void iniciarMenu(){
        Intent intent = new Intent(this, Menu.class);
        startActivity(intent);
        finish();
    }
    @Override
    public void onClick(View view) {
        //System.out.println("Chequeo si el filtrado de System anda");
        Animation giro;
        giro = AnimationUtils.loadAnimation(this, R.animator.rotate);
        giro.reset();
        final Button boton = (Button) findViewById(R.id.botonFacebook);
        giro.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                //nada
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                boton.setVisibility(View.INVISIBLE);
                ejecutarLogeo();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                //nada
            }
        });
        boton.startAnimation(giro);
        /*EsperarLogin esperar = new EsperarLogin(giro, this);
        esperar.run();*/
        //loginManager.
       /* while(!giro.hasEnded()){
            //
        }
        */

    }

    public void ejecutarLogeo(){
        List<String> permisos = new ArrayList<String>();
        permisos.add("user_friends");
        permisos.add("public_profile");
        permisos.add("user_photos");
        LoginManager.getInstance().logInWithReadPermissions(this, permisos);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
    public List<Contacto> darListaContactos(JSONArray datosAmigos){
        System.out.println(datosAmigos.toString());
        List<Contacto> contactos = new ArrayList<>();
        for(int i = 0; i<datosAmigos.length(); i++){
            Contacto contacto = new Contacto();
            try {
                //System.out.println("Pedimos id: ");
                //System.out.println(datosAmigos.getJSONObject(i).getString("id"));
                contacto.setId(datosAmigos.getJSONObject(i).getString("id"));
               // System.out.println("Pedimos nombre: ");
                //System.out.println(datosAmigos.getJSONObject(i).getString("name"));
                contacto.setNombre(datosAmigos.getJSONObject(i).getString("name"));
               // System.out.println("Pedimos URI: ");
                //System.out.println(datosAmigos.getJSONObject(i).getJSONObject("picture").getJSONObject("data").getString("url"));
                Uri uri = Uri.parse(datosAmigos.getJSONObject(i).getJSONObject("picture").getJSONObject("data").getString("url"));
                /*DescargarImagen descargador = new DescargarImagen(contacto, this, contacto.getId());
                descargador.execute(uri.toString());*/
                DescargarImagenSincrona descargador = new DescargarImagenSincrona(contacto, this, contacto.getId());
                descargador.descargar(uri.toString());

            } catch (JSONException e) {
                System.out.println("Error JSON");
                //e.printStackTrace();
            }
            contactos.add(contacto);
        }
        return contactos;
    }

    public void darPerfil(JSONObject object, Perfil perfil){
        try {
            //System.out.println(object.toString());
            //System.out.println("Empezamos a cargar el perfil");
            perfil.setId(object.getString("id"));
            //System.out.println("ID cargada");
            perfil.setNombre(object.getString("name"));
            //System.out.println("Nombre cargado");
            Uri uri = Uri.parse(object.getJSONObject("picture").getJSONObject("data").getString("url"));
            //System.out.println("URI Parseada");
            DescargarImagenSincrona descargador = new DescargarImagenSincrona(perfil, this, perfil.getId());
            descargador.descargar(uri.toString());
            /*DescargarImagen descargador = new DescargarImagen(perfil, this, perfil.getId());
            descargador.execute(uri.toString());*/
            System.out.println("Se cargo bien la ruta: "+ perfil.getImagen());
            //System.out.println("Imagen descargada");

        } catch (JSONException e) {
            System.out.println("error JSON");
        }
    }
    public void conectarYIngresarADB(Perfil perfil){
        //Guardamos los contactos en la tabla contactos y los datos propios del usuario en las Shared Preferences.
        //Generamos QUERY de ingreso de contactos
        List<Contacto> contactos= perfil.getContactos();
        String query = "INSERT INTO Contactos (contacto_id, contacto_nombre,contacto_uri_foto) VALUES ";
        String queryFinal;
        ContentValues registro;
        System.out.println("Creamos BD");

        DataBase baseDatos = new DataBase(this, "BASE_DATOS_CHAT", null, 2);
        System.out.println("Se creo! BD");
        //System.out.println("Base de datos creada, ingresamos datos...");
        for(int i = 0; i<contactos.size(); i++) {
            Contacto contacto = contactos.get(i);
            registro = new ContentValues();
            registro.put("user_id",perfil.getId());
            registro.put("contacto_id", contacto.getId());
            registro.put("contacto_nombre", contacto.getNombre());
            registro.put("contacto_uri_foto", contacto.getImagen());
            baseDatos.getWritableDatabase().insert("Contactos", null, registro);
        }
      //  System.out.println("Datos ingresados!!");
        //Ya guardamos en la DB ahora guardamos en las sharedPreferences
        //System.out.println(perfil.getId());
        //System.out.println(perfil.getNombre());
        //System.out.println(perfil.getImagen());
       // System.out.println("Vamos a cargar las shared preferences");
        SharedPreferences preferencia = getSharedPreferences("usuarioPreferencias", this.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferencia.edit();
        editor.putString("id", perfil.getId());
        //System.out.println("id cargada");
        editor.putString("nombre", perfil.getNombre());
        System.out.println("Rutaaa: "+ perfil.getImagen());
        editor.putString("rutaImagen", perfil.getImagen());
       // System.out.println("ruta cargado");
        editor.putString("logeado","si"); //Esto lo podemos poner para evitar revisar el Token cada vez que logea.


       // System.out.println("Pre commit");
        editor.commit();
        //System.out.println("commit");
    }
}
