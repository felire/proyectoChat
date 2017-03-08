package com.example.felipe.chatvuelveacompilar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.Serializable;


public class Contactos extends Fragment implements View.OnClickListener{
    RecyclerView mRecyclerView;
    View myFragment;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myFragment = inflater.inflate(R.layout.fragment_contactos, container, false);
        mRecyclerView = (RecyclerView) myFragment.findViewById(R.id.recycler_view);
        mRecyclerView.setOnClickListener(this);
        mLayoutManager = new LinearLayoutManager(myFragment.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new RecyclerContactosAdapter(myFragment.getContext(), Contacto.darTodosContactos(myFragment.getContext()), new RecyclerViewOnItemClickListener() {
            @Override
            public void onClick(Contacto contacto) {
                Intent intent = new Intent(myFragment.getContext(), VentanaChat.class);
                intent.putExtra("contacto", contacto);
                startActivity(intent);
            }
        });
        mRecyclerView.setAdapter(mAdapter);
        return myFragment;
    }

    @Override
    public void onClick(View view) {

    }
}
