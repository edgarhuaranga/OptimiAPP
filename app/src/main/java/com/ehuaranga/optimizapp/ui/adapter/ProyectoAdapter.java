package com.ehuaranga.optimizapp.ui.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.ehuaranga.optimizapp.R;
import com.ehuaranga.optimizapp.internaldb.OptimiAppDBHelper;
import com.ehuaranga.optimizapp.model.Proyecto;
import com.ehuaranga.optimizapp.ui.proyecto.ProyectoActivity;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class ProyectoAdapter extends RecyclerView.Adapter<ProyectoAdapter.ProyectoViewHolder> {
    Context context;
    ArrayList<Proyecto> proyectos;
    OptimiAppDBHelper drimerDBHelper;
    public ProyectoAdapter(Context context, ArrayList<Proyecto> proyectos){
        this.context = context;
        this.proyectos = proyectos;
        drimerDBHelper = new OptimiAppDBHelper(context);
    }

    @NonNull
    @Override
    public ProyectoAdapter.ProyectoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_proyecto, parent, false);
        return new ProyectoAdapter.ProyectoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProyectoAdapter.ProyectoViewHolder holder, final int position) {
        final Proyecto proyecto = proyectos.get(position);
        holder.textViewTitulo.setText(proyecto.getTitulo());
        holder.textViewDescripcion.setText(proyecto.getDescripcion());
        holder.cardView.setTag(proyecto.getId());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ProyectoActivity.class);
                intent.putExtra("ID", Integer.parseInt(v.getTag().toString()));
                v.getContext().startActivity(intent);
            }
        });

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_delete_proyecto);
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
                        drimerDBHelper.deleteProyecto(proyecto);
                        proyectos.remove(position);
                        notifyItemRemoved(position);
                        dialog.dismiss();
                    }
                });


                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return proyectos.size();
    }

    public class ProyectoViewHolder extends RecyclerView.ViewHolder{
        View cardView;
        TextView textViewTitulo;
        TextView textViewDescripcion;
        ImageView imageView;

        public ProyectoViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardview_proyecto);
            textViewTitulo = itemView.findViewById(R.id.textview_cardview_proyecto_titulo);
            textViewDescripcion = itemView.findViewById(R.id.textview_cardview_proyecto_descripcion);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
