package com.ehuaranga.optimizapp.ui.adapter;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ehuaranga.optimizapp.R;
import com.ehuaranga.optimizapp.internaldb.OptimiAppDBHelper;
import com.ehuaranga.optimizapp.model.Equipo;
import com.ehuaranga.optimizapp.model.Proyecto;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class EquipoProyectoAdapter extends RecyclerView.Adapter<EquipoProyectoAdapter.EquipoViewHolder> {
    String TAG = getClass().getName();
    Context context;
    ArrayList<Equipo> equipos;
    Proyecto proyecto;
    OptimiAppDBHelper drimerDBHelper;
    public EquipoProyectoAdapter(Context context, ArrayList<Equipo> equipos, Proyecto proyecto){
        this.context = context;
        this.equipos = equipos;
        this.proyecto = proyecto;
        drimerDBHelper = new OptimiAppDBHelper(context);
    }

    @NonNull
    @Override
    public EquipoProyectoAdapter.EquipoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_equipo_proyecto, parent, false);
        return new EquipoProyectoAdapter.EquipoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final EquipoProyectoAdapter.EquipoViewHolder holder, int position) {
        final Equipo equipo  = equipos.get(position);

        holder.textViewCodigo.setText(equipo.getCodigo());
        holder.textViewPorcentaje.setText(equipo.getPorcentajeProctor()+"%");
        holder.textViewPasadas.setText(equipo.getNumeroPasadas() + " pasadas");
        holder.textViewPrecio.setText("$"+equipo.getCosto() );
        holder.textViewCoeficiente.setText("R1=" + equipo.getPc(proyecto));

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_update_equipo_price);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                Button dialogButtonOK = (Button) dialog.findViewById(R.id.button_dialog_ok);
                Button dialogButtonCancel = (Button) dialog.findViewById(R.id.button_dialog_cancel);


                dialogButtonCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialogButtonOK.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        float price =  Float.parseFloat(((TextInputLayout) dialog.findViewById(R.id.editText)).getEditText().getText().toString());
                        drimerDBHelper.updateEquipoEnProyecto(proyecto, equipo, price);
                        holder.textViewPrecio.setText("$"+price);
                        equipo.setCosto(price);
                        dialog.dismiss();
                    }
                });


                dialog.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return equipos.size();
    }

    public class EquipoViewHolder extends RecyclerView.ViewHolder{
        ConstraintLayout cardView;
        TextView textViewCodigo;
        TextView textViewPorcentaje;
        TextView textViewPasadas;
        TextView textViewPrecio;
        TextView textViewCoeficiente;

        public EquipoViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardview_equipo);
            textViewCodigo = itemView.findViewById(R.id.textview_codigo_equipo_proyecto);
            textViewPorcentaje = itemView.findViewById(R.id.textview_porcentaje_equipo_proyecto);
            textViewPasadas = itemView.findViewById(R.id.textview_cardview_equipo_numero_pasadas);
            textViewPrecio = itemView.findViewById(R.id.textview_cardview_equipo_precio);
            textViewCoeficiente = itemView.findViewById(R.id.textview_cardview_equipo_coeficiente);

        }
    }
}
