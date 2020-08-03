package com.ehuaranga.optimizapp.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.ehuaranga.optimizapp.R;
import com.ehuaranga.optimizapp.model.Equipo;

import java.util.ArrayList;

public class EquipoSolucionAdapter extends RecyclerView.Adapter<EquipoSolucionAdapter.EquipoViewHolder>  {
    String TAG = getClass().getName();
    Context context;
    ArrayList<Equipo> equipos;
    String solucion;

    public EquipoSolucionAdapter(Context context, ArrayList<Equipo> equipos, String solucion){
        this.context = context;
        this.equipos = equipos;
        this.solucion = solucion;
    }

    @NonNull
    @Override
    public EquipoSolucionAdapter.EquipoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_equipo_solucion, parent, false);
        return new EquipoSolucionAdapter.EquipoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final EquipoSolucionAdapter.EquipoViewHolder holder, int position) {
        final Equipo equipo  = equipos.get(position);


        holder.textViewCodigo.setText("Modelo "+ (position+1) + "("+ equipo.getCodigo()+"):");
        holder.textViewSolucion.setText(Math.ceil(Float.parseFloat(solucion.split("X")[position+1].split(" = ")[1])) + " unidades");
    }

    @Override
    public int getItemCount() {
        return equipos.size();
    }

    public class EquipoViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView textViewCodigo;
        TextView textViewSolucion;


        public EquipoViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardview_equipo);
            textViewCodigo = itemView.findViewById(R.id.textview_cardview_equipo_codigo);
            textViewSolucion = itemView.findViewById(R.id.textview_cardview_equipo_solucion);

        }
    }
}
