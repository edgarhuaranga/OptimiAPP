package com.ehuaranga.optimizapp.ui.proyecto;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ehuaranga.optimizapp.MainActivity;
import com.ehuaranga.optimizapp.R;
import com.ehuaranga.optimizapp.internaldb.OptimiAppDBHelper;
import com.ehuaranga.optimizapp.model.Proyecto;
import com.ehuaranga.optimizapp.ui.adapter.ProyectoAdapter;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.ehuaranga.optimizapp.internaldb.OptimiAPPContract.DBRintisa.*;


public class ProyectosFragment extends Fragment {
    final String TAG = this.getClass().getCanonicalName();
    TextView textViewTitulo;
    ProyectoAdapter proyectoAdapter;
    RecyclerView recyclerViewProyectos;
    ArrayList<Proyecto> proyectos;
    OptimiAppDBHelper drimerDBHelper;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        drimerDBHelper = new OptimiAppDBHelper(getActivity().getApplicationContext());
        textViewTitulo = root.findViewById(R.id.text_gallery);
        recyclerViewProyectos = root.findViewById(R.id.recyclerview_proyectos);
        proyectos = drimerDBHelper.getAllProyectos();

        //Log.d(TAG, proyectos.size() + "proyectos");
        recyclerViewProyectos.setLayoutManager(new LinearLayoutManager(this.getContext()));
        proyectoAdapter = new ProyectoAdapter(this.getContext(), proyectos);
        recyclerViewProyectos.setAdapter(proyectoAdapter);

        return root;
    }

    public void agregarNuevoProyecto(){
        final Dialog dialog = new Dialog(this.getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_add_proyecto);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);


        TextView text = (TextView) dialog.findViewById(R.id.textview_dialog_addproyecto_title);
        Button dialogButton = (Button) dialog.findViewById(R.id.button_dialog_ok);
        String[] items = new String[]{"Granular", "Semicohesivo", "Cohesivo"};
        String[] jornadas = new String[]{"1","2"};
        String[] proctors = new String[]{"90.0","95.0"};
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getContext(), R.layout.list_item, items);
        ((AutoCompleteTextView)dialog.findViewById(R.id.autocomplete_textview_tipo_suelo)).setAdapter(spinnerAdapter);

        ArrayAdapter<String> spinnerAdapterJornadas = new ArrayAdapter<String>(getContext(), R.layout.list_item, jornadas);
        ((AutoCompleteTextView)dialog.findViewById(R.id.autocomplete_textview_jornadas)).setAdapter(spinnerAdapterJornadas);

        ArrayAdapter<String> spinnerAdapterProctor = new ArrayAdapter<String>(getContext(), R.layout.list_item, proctors);
        ((AutoCompleteTextView)dialog.findViewById(R.id.autocomplete_textview_proctor)).setAdapter(spinnerAdapterProctor);

        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titulo = ((TextInputLayout)dialog.findViewById(R.id.textfield_titulo)).getEditText().getText().toString();
                String descripcion = ((TextInputLayout)dialog.findViewById(R.id.textfield_descripcion)).getEditText().getText().toString();
                int espesor = Integer.parseInt(((TextInputLayout)dialog.findViewById(R.id.textfield_espesor)).getEditText().getText().toString());
                float factorEficiencia = Float.parseFloat(((TextInputLayout)dialog.findViewById(R.id.textfield_factor_eficiencia)).getEditText().getText().toString());
                int altura = Integer.parseInt(((TextInputLayout)dialog.findViewById(R.id.textfield_factor_altura)).getEditText().getText().toString());

                float area = Float.parseFloat(((TextInputLayout)dialog.findViewById(R.id.textfield_area_proyecto)).getEditText().getText().toString());
                float mcc = Float.parseFloat(((TextInputLayout)dialog.findViewById(R.id.textfield_mcc)).getEditText().getText().toString());
                float he = Float.parseFloat(((TextInputLayout)dialog.findViewById(R.id.textfield_he)).getEditText().getText().toString());
                int jornadas = Integer.parseInt(((TextInputLayout)dialog.findViewById(R.id.textfield_jornadas)).getEditText().getText().toString());
                float t = Float.parseFloat(((TextInputLayout)dialog.findViewById(R.id.textfield_t)).getEditText().getText().toString());
                String tipoSuelo = ((TextInputLayout)dialog.findViewById(R.id.textfield_tipo_suelo)).getEditText().getText().toString();
                String proctor = ((TextInputLayout)dialog.findViewById(R.id.textfield_proctor)).getEditText().getText().toString();


                JSONObject jsonEquipo = new JSONObject();

                try {
                    jsonEquipo.put(COLUMN_NAME_TITULO_PROYECTO, titulo);
                    jsonEquipo.put(COLUMN_NAME_DESCRIPCION_PROYECTO, descripcion);
                    jsonEquipo.put(COLUMN_NAME_ESPESOR_PROYECTO, espesor);
                    jsonEquipo.put(COLUMN_NAME_FACTOR_EFICIENCIA_PROYECTO, factorEficiencia);
                    jsonEquipo.put(COLUMN_NAME_ALTURA_PROYECTO, altura);
                    jsonEquipo.put(COLUMN_NAME_AREA_PROYECTO, area);
                    jsonEquipo.put(COLUMN_NAME_MCC_PROYECTO, mcc);
                    jsonEquipo.put(COLUMN_NAME_HE_PROYECTO, he);
                    jsonEquipo.put(COLUMN_NAME_JORNADAS_PROYECTO, jornadas);
                    jsonEquipo.put(COLUMN_NAME_T_PROYECTO, t);
                    jsonEquipo.put(COLUMN_NAME_TIPO_SUELO_PROYECTO, tipoSuelo);
                    jsonEquipo.put(COLUMN_NAME_PROCTOR_PROYECTO, proctor);

                    drimerDBHelper.insertarProyecto(jsonEquipo);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                dialog.dismiss();
                updateLista();
            }
        });

        dialog.show();
    }

    private void updateLista() {
        proyectos.clear();
        proyectos.addAll(drimerDBHelper.getAllProyectos());
        proyectoAdapter.notifyDataSetChanged();
    }
}
