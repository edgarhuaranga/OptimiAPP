package com.ehuaranga.optimizapp.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.ehuaranga.optimizapp.R;
import com.ehuaranga.optimizapp.model.Equipo;

import java.util.ArrayList;

public class EquipoAdapter extends RecyclerView.Adapter<EquipoAdapter.EquipoViewHolder> {
    String TAG = getClass().getName();
    Context context;
    ArrayList<Equipo> equipos;

    public EquipoAdapter(Context context, ArrayList<Equipo> equipos){
        this.context = context;
        this.equipos = equipos;
    }

    @NonNull
    @Override
    public EquipoAdapter.EquipoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_equipo, parent, false);
        return new EquipoAdapter.EquipoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final EquipoAdapter.EquipoViewHolder holder, int position) {
        final Equipo equipo  = equipos.get(position);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("EquipoAdapter", equipo.toString());
            }
        });

        holder.textViewCodigo.setText(equipo.getCodigo());
        holder.textViewTipoSuelo.setText(equipo.getTipoSuelo());
        holder.textViewPorcentajeProctor.setText(equipo.getPorcentajeProctor()+ "");
        holder.textViewAnchoEfectivo.setText(equipo.getAnchoEfectivo()+"");
        holder.textViewVelocidadOperacion.setText(equipo.getVelocidadOperacion() + "");
        holder.textViewNumeroPasadas.setText(equipo.getNumeroPasadas()+"");
        holder.textViewAnchoLargo.setText(equipo.getAncho() + " - " + equipo.getLargo());
        holder.textViewLateralFrontal.setText(equipo.getDistanciaLateral()+ " - "+ equipo.getDistanciaFrontal());

    }

    @Override
    public int getItemCount() {
        return equipos.size();
    }

    public class EquipoViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView textViewCodigo;
        TextView textViewTipoSuelo;
        TextView textViewPorcentajeProctor;
        TextView textViewAnchoEfectivo;
        TextView textViewVelocidadOperacion;
        TextView textViewNumeroPasadas;
        TextView textViewAnchoLargo;
        TextView textViewLateralFrontal;

        ImageView imageViewMore;

        public EquipoViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardview_equipo);
            imageViewMore = itemView.findViewById(R.id.imageView);
            textViewCodigo = itemView.findViewById(R.id.textview_cardview_equipo_codigo);
            textViewTipoSuelo = itemView.findViewById(R.id.textview_cardview_equipo_tipo_suelo);
            textViewPorcentajeProctor = itemView.findViewById(R.id.textview_cardview_equipo_porcentaje_proctor);
            textViewAnchoEfectivo = itemView.findViewById(R.id.textview_cardview_equipo_ancho_efectivo);
            textViewVelocidadOperacion = itemView.findViewById(R.id.textview_cardview_equipo_velocidad_operacion);
            textViewNumeroPasadas = itemView.findViewById(R.id.textview_cardview_equipo_numero_pasadas);
            textViewAnchoLargo = itemView.findViewById(R.id.textview_cardview_equipo_ancho_largo);
            textViewLateralFrontal = itemView.findViewById(R.id.textview_cardview_equipo_lateral_frontal);

        }
    }
}
