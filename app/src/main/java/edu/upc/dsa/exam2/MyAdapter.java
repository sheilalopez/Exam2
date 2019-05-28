package edu.upc.dsa.exam2;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.BreakIterator;
import java.util.AbstractSequentialList;
import java.util.List;

import edu.upc.dsa.exam2.models.Element;
import edu.upc.dsa.exam2.models.Museums;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private Context context;
    private Museums listaMuseos;

    public MyAdapter(Context context, Museums listaMuseos) {
        this.context = context;
        this.listaMuseos = listaMuseos;
    }

    public void setListaMuseos(Museums listaMuseos) {
        this.listaMuseos = listaMuseos;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        if (listaMuseos.getElements().get(i).getAdrecaNom() != null)
            myViewHolder.adreca.setText(listaMuseos.getElements().get(i).getAdrecaNom());
        if (listaMuseos.getElements().get(i).getImatge().get(0) != null)
            Picasso.get().load(listaMuseos.getElements().get(i).getImatge().get(0)).into(myViewHolder.imagen);
    }

    @Override
    public int getItemCount() {
        try {
            return listaMuseos.getElements().size();
        } catch (Exception e) {
            return 0;
        }
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView imagen;
        private TextView adreca;

        public MyViewHolder(View itemView) {
            super(itemView);
            imagen = itemView.findViewById(R.id.imageMuseo);
            adreca = itemView.findViewById(R.id.txtMuseo);
        }
    }
}