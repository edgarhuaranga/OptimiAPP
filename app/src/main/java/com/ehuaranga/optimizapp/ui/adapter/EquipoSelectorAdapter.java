package com.ehuaranga.optimizapp.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.ehuaranga.optimizapp.R;
import com.ehuaranga.optimizapp.model.Equipo;

import java.util.ArrayList;

public class EquipoSelectorAdapter extends RecyclerView.Adapter<EquipoSelectorAdapter.EquipoViewHolder> {
    String TAG = getClass().getName();
    Context context;
    ArrayList<Equipo> equipos;
    ArrayList<Boolean> equipoSeleccionado;

    public EquipoSelectorAdapter(Context context, ArrayList<Equipo> equipos, ArrayList<Boolean> equipoSeleccionado){
        this.context = context;
        this.equipos = equipos;
        this.equipoSeleccionado = equipoSeleccionado;
    }

    @NonNull
    @Override
    public EquipoSelectorAdapter.EquipoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_equipo_selector, parent, false);
        return new EquipoSelectorAdapter.EquipoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final EquipoSelectorAdapter.EquipoViewHolder holder, final int position) {
        final Equipo equipo  = equipos.get(position);

        holder.textViewCodigo.setText(equipo.getCodigo());
        holder.textViewTipoSuelo.setText(equipo.getTipoSuelo());
        holder.textViewPorcentajeProctor.setText(equipo.getPorcentajeProctor()+ "%");
        holder.textViewPrecio.setText("$"+equipo.getCosto());
        holder.textViewPasadas.setText(equipo.getNumeroPasadas() + " pasadas");

        holder.checkBox.setChecked(equipoSeleccionado.get(position).booleanValue());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                equipoSeleccionado.set(position, !equipoSeleccionado.get(position).booleanValue());
                holder.checkBox.setChecked(equipoSeleccionado.get(position).booleanValue());
            }
        });
    }

    @Override
    public int getItemCount() {
        return equipos.size();
    }

    public class EquipoViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        CheckBox checkBox;
        TextView textViewCodigo;
        TextView textViewTipoSuelo;
        TextView textViewPorcentajeProctor;
        TextView textViewPrecio;
        TextView textViewPasadas;

        ImageView imageViewMore;

        public EquipoViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardview_equipo);
            checkBox = itemView.findViewById(R.id.checkBox);
            textViewCodigo = itemView.findViewById(R.id.textview_cardview_equipo_codigo);
            textViewTipoSuelo = itemView.findViewById(R.id.textview_cardview_equipo_tipo_suelo);
            textViewPorcentajeProctor = itemView.findViewById(R.id.textview_cardview_equipo_porcentaje_proctor);
            textViewPrecio = itemView.findViewById(R.id.textview_cardview_equipo_precio);
            textViewPasadas = itemView.findViewById(R.id.textview_cardview_equipo_numero_pasadas);

        }
    }
}
