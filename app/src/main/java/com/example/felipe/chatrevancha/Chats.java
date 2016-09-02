package com.example.felipe.chatrevancha;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.felipe.chatrevancha.R;
import com.facebook.login.LoginManager;


public class Chats extends Fragment implements View.OnClickListener{
    Button salir;
    View miFragment;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        /*salir = (Button) getView().findViewById(R.id.salir);
        salir.setOnClickListener(this);*/
        miFragment = inflater.inflate(R.layout.fragment_chats, container, false);
        salir = (Button) miFragment.findViewById(R.id.salir);
        salir.setOnClickListener(this);
        TextView texto = (TextView) miFragment.findViewById(R.id.texto);
        SharedPreferences preferencia = this.getActivity().getSharedPreferences("usuarioPreferencias", this.getActivity().MODE_PRIVATE);
        texto.setText("nombre: "+ preferencia.getString("nombre", "defecto") + " id: "+preferencia.getString("id","defecto"));
        ImageView imagen = (ImageView) miFragment.findViewById(R.id.imagen);
        imagen.setImageBitmap(new TraedorImagenRuta().cargarImagenDeMemoriaInterna(preferencia.getString("rutaImagen","defecto"), preferencia.getString("id", "defecto")));
        return miFragment;
    }

    @Override
    public void onClick(View view) {
        LoginManager.getInstance().logOut();
        Intent intent = new Intent(getContext(), LoginFacebook.class);
        startActivity(intent);
        getActivity().finish();

    }
}
