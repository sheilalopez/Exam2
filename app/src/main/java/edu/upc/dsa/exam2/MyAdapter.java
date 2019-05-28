package edu.upc.dsa.exam2;

import android.content.Context;
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

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private Context context;
    private List<Element> elementsList;

    public MyAdapter(Context context, List<Element> elementsList) {
        this.context = context;
        this.elementsList = elementList;
    }

    public void setTrackList(List<Element> elementsList) {
        this.elementsList= elementsList;
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
        if (elementsList.get(i).getLogin() != null)
            myViewHolder.txtMuseo.setText(elementsList.get(i).getLogin());
        else
            myViewHolder.txtMuseo.setText("Not defined");
        if (elementsList.get(i).getImatge() != null)
            Picasso.get().load(elementsList.get(i).getImatge()).into(myViewHolder.imageMuseo);
    }

    @Override
    public int getItemCount() {
        if (elementsList != null)
            return elementsList.size();
        else
            return 0;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView txtMuseo;
        private ImageView imageMuseo;


        public MyViewHolder(View itemView) {
            super(itemView);
            txtMuseo = itemView.findViewById(R.id.txtMuseo);
            imageMuseo = itemView.findViewById(R.id.imageMuseo);
        }
    }
}