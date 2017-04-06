package com.example.felipe.chatvuelveacompilar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.login.LoginManager;

import java.util.List;


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
        String gigante = "";
        List<Contacto> contactos = Contacto.darTodosContactos(miFragment.getContext());
        gigante = contactos.get(0).getNombre() + " " + contactos.get(0).getId() + " " + contactos.get(0).getImagen();
        salir = (Button) miFragment.findViewById(R.id.salir);
        salir.setOnClickListener(this);
        TextView texto = (TextView) miFragment.findViewById(R.id.texto);
        SharedPreferences preferencia = this.getActivity().getSharedPreferences("usuarioPreferencias", this.getActivity().MODE_PRIVATE);
        texto.setText(gigante);
        ImageView imagen = (ImageView) miFragment.findViewById(R.id.imagen);
        imagen.setImageBitmap(new TraedorImagenRuta().cargarImagenDeMemoriaInterna(preferencia.getString("rutaImagen","defecto"), preferencia.getString("id", "defecto")));
        new ConnectionThread(new ConnectionClient("192.168.0.98", 2023,  preferencia.getString("id", "defecto"))).execute();
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
