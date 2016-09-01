package com.example.felipe.chat;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphRequestBatch;
import com.facebook.GraphResponse;
import com.facebook.ProfileTracker;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {

                        try {

                            Uri uri = Uri.parse(object.getJSONObject("picture").getJSONObject("data").getString("url"));
                            System.out.println("Paso la uri, la URL es: "+ uri.toString());
                            DescargarImagen descarga = new DescargarImagen(yo);
                            descarga.execute(uri.toString());
                            System.out.println(object.toString());
                            //linkImagen.setText("hola");
                        } catch (JSONException e) {
                            System.out.println(e.toString());
                            //linkImagen.setText("NO ANDA");
                        }
                    }
                });
                Bundle parametros = new Bundle();
                parametros.putString("fields", "id,name,picture");
                request.setParameters(parametros);
                GraphRequest requestAmigos = GraphRequest.newMyFriendsRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONArrayCallback() {
                    @Override
                    public void onCompleted(JSONArray objects, GraphResponse response) {

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
                        Intent intent = new Intent(yo, Menu.class);
                        startActivity(intent);
                        finish();
                        progressDialog.dismiss();
                    }
                });
                batch.executeAsync();
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
    public void recibirImagen(Bitmap bitmap){

    }

    private String guardarImagen(Context context, String nombre, Bitmap imagen){
        /*ContextWrapper cw = new ContextWrapper(context);
        File dirImages = cw.getDir(Imagenes, Context.MODE_PRIVATE);
        File myPath = new File(DirImages, nombre + ".png");*/
        return null;
    }

    public List<Contacto> darListaContactos(JSONArray datosAmigos){
        List<Contacto> contactos = new ArrayList<>();
        //datosAmigos.
        return null;
    }
}
