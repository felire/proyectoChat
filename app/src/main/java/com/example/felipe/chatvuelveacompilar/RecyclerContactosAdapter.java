package com.example.felipe.chatvuelveacompilar;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by felipe on 22/12/16.
 */
public class RecyclerContactosAdapter extends RecyclerView.Adapter<RecyclerContactosAdapter.ViewHolder>{

    public Context mContext;
    public List<Contacto> mDataset;
    private TraedorImagenRuta traedorImagenRuta;
    private RecyclerViewOnItemClickListener recyclerViewOnItemClickListener;
    public static class ViewHolder extends RecyclerView.ViewHolder{
        // each data item is just a string in this case
        TextView mTextView;
        ImageView mImgtView;
        View view;
        ViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.textoCont);
            mImgtView = (ImageView) v.findViewById(R.id.imagenCont);
            view = v;
        }

        public void bind(final Contacto contacto, final RecyclerViewOnItemClickListener listener){
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClick(contacto);
                }
            });
        }
    }

    public RecyclerContactosAdapter(Context context, List<Contacto> myDataset,RecyclerViewOnItemClickListener recyclerViewOnItemClickListener) {
        mDataset = myDataset;
        mContext = context;
        this.recyclerViewOnItemClickListener = recyclerViewOnItemClickListener;
        traedorImagenRuta = new TraedorImagenRuta();
    }

    @Override
    public RecyclerContactosAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tipo_lista_contactos, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mTextView.setText(mDataset.get(position).getNombre());
        String pathImagen = mDataset.get(position).getImagen();
        String id = mDataset.get(position).getId();
        Bitmap imagen = traedorImagenRuta.cargarImagenDeMemoriaInterna(pathImagen, id);
        holder.mImgtView.setImageBitmap(imagen);
        holder.bind(mDataset.get(position), recyclerViewOnItemClickListener);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
