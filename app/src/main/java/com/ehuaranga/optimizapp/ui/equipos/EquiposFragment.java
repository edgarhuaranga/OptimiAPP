package com.ehuaranga.optimizapp.ui.equipos;

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
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ehuaranga.optimizapp.R;
import com.ehuaranga.optimizapp.internaldb.OptimiAppDBHelper;
import com.ehuaranga.optimizapp.model.Equipo;
import com.ehuaranga.optimizapp.model.Proyecto;
import com.ehuaranga.optimizapp.ui.adapter.EquipoAdapter;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.ehuaranga.optimizapp.internaldb.OptimiAPPContract.DBRintisa.*;

public class EquiposFragment extends Fragment {
    final String TAG = this.getClass().getCanonicalName();
    TextView textViewTitulo;
    EquipoAdapter equipoAdapter;
    RecyclerView recyclerViewEquipos;
    ArrayList<Equipo> equipos;
    OptimiAppDBHelper drimerDBHelper;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        drimerDBHelper = new OptimiAppDBHelper(getActivity().getApplicationContext());

        recyclerViewEquipos = root.findViewById(R.id.recyclerview_equipos);
        equipos = drimerDBHelper.getAllEquipos();
        Log.d(TAG, equipos.size() + " equipos");
        if(equipos.size() == 0){
            populateEquiposDatabase();
        }

        recyclerViewEquipos.setLayoutManager(new LinearLayoutManager(this.getContext()));
        equipoAdapter = new EquipoAdapter(this.getContext(), equipos);
        recyclerViewEquipos.setAdapter(equipoAdapter);
        return root;
    }

    private void populateEquiposDatabase() {
        ArrayList<Equipo> equiposIniciales = new ArrayList<>();
        equiposIniciales.add(new Equipo(	"CS44B",	"Granular",	(float)90,	(float)1.676,	(float)5.09,	(float)5,	(float)5,	(float)177,	(float)1.526,	(float)2500,	9, 10));
        equiposIniciales.add(new Equipo(	"CS54B",	"Granular",	(float)90,	(float)2.134,(float)	5.85,	(float)5,	(float)5,	(float)193,	(float)1.984,	(float)2500,	8, 10));
        equiposIniciales.add(new Equipo(	"CS56B",	"Granular",	(float)90,	(float)2.134,(float)	5.86,	(float)5,	(float)5,	(float)193,	(float)1.984,	(float)2500,	7, 11));
        equiposIniciales.add(new Equipo(	"CS64B",	"Granular",	(float)90,	(float)2.134,(float)	5.86,	(float)5,	(float)5,	(float)193,	(float)1.984,	(float)2500,	6, 12));
        equiposIniciales.add(new Equipo(	"CS66B",	"Granular",	(float)90,	(float)2.134,(float)	5.86,	(float)5,	(float)5,	(float)193,	(float)1.984,	(float)2500,	5, 13));
        equiposIniciales.add(new Equipo(	"CS68B",	"Granular",	(float)90,	(float)2.134,(float)	6.05,	(float)5,	(float)5,	(float)195,	(float)1.984,	(float)2500,	4, 14));
        equiposIniciales.add(new Equipo(	"CS74B",	"Granular",	(float)90,	(float)2.134,(float)	6.05,	(float)5,	(float)5,	(float)195,	(float)1.984,	(float)2500,	4, 14));
        equiposIniciales.add(new Equipo(	"CS76B",	"Granular",	(float)90,	(float)2.134,(float)	6.13,	(float)5,	(float)5,	(float)196,	(float)1.984,	(float)2500,	4, 16));
        equiposIniciales.add(new Equipo(	"CS78B",	"Granular",	(float)90,	(float)2.134,(float)	6.13,	(float)5,	(float)5,	(float)196,	(float)1.984,	(float)2500,	3, 17));
        equiposIniciales.add(new Equipo(	"CS44B",	"Granular",	(float)95,	(float)1.676,(float)	5.09,	(float)5,	(float)5,	(float)177,	(float)1.526,	(float)2500,	13, 18));
        equiposIniciales.add(new Equipo(	"CS54B",	"Granular",	(float)95,	(float)2.134,(float)	5.85,	(float)5,	(float)5,	(float)193,	(float)1.984,	(float)2500,	12, 19));
        equiposIniciales.add(new Equipo(	"CS56B",	"Granular",	(float)95,	(float)2.134,(float)	5.86,	(float)5,	(float)5,	(float)193,	(float)1.984,	(float)2500,	10, 20));
        equiposIniciales.add(new Equipo(	"CS64B",	"Granular",	(float)95,	(float)2.134,(float)	5.86,	(float)5,	(float)5,	(float)193,	(float)1.984,	(float)2500,	9, 21));
        equiposIniciales.add(new Equipo(	"CS66B",	"Granular",	(float)95,	(float)2.134,(float)	5.86,	(float)5,	(float)5,	(float)193,	(float)1.984,	(float)2500,	7, 22));
        equiposIniciales.add(new Equipo(	"CS68B",	"Granular",	(float)95,	(float)2.134,(float)	6.05,	(float)5,	(float)5,	(float)195,	(float)1.984,	(float)2500,	6, 23));
        equiposIniciales.add(new Equipo(	"CS74B",	"Granular",	(float)95,	(float)2.134,(float)	6.05,	(float)5,	(float)5,	(float)195,	(float)1.984,	(float)2500,	6, 24));
        equiposIniciales.add(new Equipo(	"CS76B",	"Granular",	(float)95,	(float)2.134,(float)	6.13,	(float)5,	(float)5,	(float)196,	(float)1.984,	(float)2500,	6, 25));
        equiposIniciales.add(new Equipo(	"CS78B",	"Granular",	(float)95,	(float)2.134,(float)	6.13,	(float)5,	(float)5,	(float)196,	(float)1.984,	(float)2500,	5, 26));
        equiposIniciales.add(new Equipo(	"CS44B",	"Semicohesivo",	(float)90,	(float)1.676,(float)	5.09,	(float)5,	(float)5,	(float)177,	(float)1.526,	(float)2500,	7, 27));
        equiposIniciales.add(new Equipo(	"CS54B",	"Semicohesivo",	(float)90,	(float)2.134,(float)	5.85,	(float)5,	(float)5,	(float)193,	(float)1.984,	(float)2500,	6, 28));
        equiposIniciales.add(new Equipo(	"CS56B",	"Semicohesivo",	(float)90,	(float)2.134,(float)	5.86,	(float)5,	(float)5,	(float)193,	(float)1.984,	(float)2500,	5, 29));
        equiposIniciales.add(new Equipo(	"CS64B",	"Semicohesivo",	(float)90,	(float)2.134,(float)	5.86,	(float)5,	(float)5,	(float)193,	(float)1.984,	(float)2500,	4, 30));
        equiposIniciales.add(new Equipo(	"CS66B",	"Semicohesivo",	(float)90,	(float)2.134,(float)	5.86,	(float)5,	(float)5,	(float)193,	(float)1.984,	(float)2500,	4, 31));
        equiposIniciales.add(new Equipo(	"CS68B",	"Semicohesivo",	(float)90,	(float)2.134,(float)	6.05,	(float)5,	(float)5,	(float)195,	(float)1.984,	(float)2500,	3, 32));
        equiposIniciales.add(new Equipo(	"CS74B",	"Semicohesivo",	(float)90,	(float)2.134,(float)	6.05,	(float)5,	(float)5,	(float)195,	(float)1.984,	(float)2500,	2,33));
        equiposIniciales.add(new Equipo(	"CS76B",	"Semicohesivo",	(float)90,	(float)2.134,(float)	6.13,	(float)5,	(float)5,	(float)196,	(float)1.984,	(float)2500,	2, 34));
        equiposIniciales.add(new Equipo(	"CS78B",	"Semicohesivo",	(float)90,	(float)2.134,(float)	6.13,	(float)5,	(float)5,	(float)196,	(float)1.984,	(float)2500,	2, 35));
        equiposIniciales.add(new Equipo(	"CS44B",	"Semicohesivo",	(float)95,	(float)1.676,(float)	5.09,	(float)5,	(float)5,	(float)177,	(float)1.526,	(float)2500,	10,36));
        equiposIniciales.add(new Equipo(	"CS54B",	"Semicohesivo",	(float)95,	(float)2.134,(float)	5.85,	(float)5,	(float)5,	(float)193,	(float)1.984,	(float)2500,	9, 37));
        equiposIniciales.add(new Equipo(	"CS56B",	"Semicohesivo",	(float)95,	(float)2.134,(float)	5.86,	(float)5,	(float)5,	(float)193,	(float)1.984,	(float)2500,	8, 38));
        equiposIniciales.add(new Equipo(	"CS64B",	"Semicohesivo",	(float)95,	(float)2.134,(float)	5.86,	(float)5,	(float)5,	(float)193,	(float)1.984,	(float)2500,	7, 39));
        equiposIniciales.add(new Equipo(	"CS66B",	"Semicohesivo",	(float)95,	(float)2.134,(float)	5.86,	(float)5,	(float)5,	(float)193,	(float)1.984,	(float)2500,	6, 40));
        equiposIniciales.add(new Equipo(	"CS68B",	"Semicohesivo",	(float)95,	(float)2.134,(float)	6.05,	(float)5,	(float)5,	(float)195,	(float)1.984,	(float)2500,	5, 41));
        equiposIniciales.add(new Equipo(	"CS74B",	"Semicohesivo",	(float)95,	(float)2.134,(float)	6.05,	(float)5,	(float)5,	(float)195,	(float)1.984,	(float)2500,	3, 42));
        equiposIniciales.add(new Equipo(	"CS76B",	"Semicohesivo",	(float)95,	(float)2.134,(float)	6.13,	(float)5,	(float)5,	(float)196,	(float)1.984,	(float)2500,	3, 43));
        equiposIniciales.add(new Equipo(	"CS78B",	"Semicohesivo",	(float)95,	(float)2.134,(float)	6.13,	(float)5,	(float)5,	(float)196,	(float)1.984,	(float)2500,	3, 44));
        equiposIniciales.add(new Equipo(	"CS64B",	"Cohesivo",	(float)90,	(float)2.134,(float)	5.86,	(float)5,	(float)5,	(float)193,	(float)1.984,	(float)2500,	9, 45));
        equiposIniciales.add(new Equipo(	"CS66B",	"Cohesivo",	(float)90,	(float)2.134,(float)	5.86,	(float)5,	(float)5,	(float)193,	(float)1.984,	(float)2500,	7, 46));
        equiposIniciales.add(new Equipo(	"CS68B",	"Cohesivo",	(float)90,	(float)2.134,(float)	6.05,	(float)5,	(float)5,	(float)195,	(float)1.984,	(float)2500,	6, 47));
        equiposIniciales.add(new Equipo(	"CS74B",	"Cohesivo",	(float)90,	(float)2.134,(float)	6.05,	(float)5,	(float)5,	(float)195,	(float)1.984,	(float)2500,	5, 48));
        equiposIniciales.add(new Equipo(	"CS76B",	"Cohesivo",	(float)90,	(float)2.134,(float)	6.13,	(float)5,	(float)5,	(float)196,	(float)1.984,	(float)2500,	4, 49));
        equiposIniciales.add(new Equipo(	"CS78B",	"Cohesivo",	(float)90,	(float)2.134,(float)	6.13,	(float)5,	(float)5,	(float)196,	(float)1.984,	(float)2500,	4, 50));
        equiposIniciales.add(new Equipo(	"CP44B",	"Cohesivo",	(float)90,	(float)1.676,(float)	5.09,	(float)5,	(float)5,	(float)177,	(float)1.526,	(float)2500,	10, 51));
        equiposIniciales.add(new Equipo(	"CP54B",	"Cohesivo",	(float)90,	(float)2.134,(float)	5.85,	(float)5,	(float)5,	(float)193,	(float)1.984,	(float)2500,	9, 52));
        equiposIniciales.add(new Equipo(	"CP56B",	"Cohesivo",	(float)90,	(float)2.134,(float)	5.86,	(float)5,	(float)5,	(float)193,	(float)1.984,	(float)2500,	8, 52));
        equiposIniciales.add(new Equipo(	"CP64B",	"Cohesivo",	(float)90,	(float)2.134,(float)	5.86,	(float)5,	(float)5,	(float)193,	(float)1.984,	(float)2500,	7, 52));
        equiposIniciales.add(new Equipo(	"CP66B",	"Cohesivo",	(float)90,	(float)2.134,(float)	5.86,	(float)5,	(float)5,	(float)193,	(float)1.984,	(float)2500,	7, 43));
        equiposIniciales.add(new Equipo(	"CP68B",	"Cohesivo",	(float)90,	(float)2.134,(float)	6.05,	(float)5,	(float)5,	(float)195,	(float)1.984,	(float)2500,	6, 43));
        equiposIniciales.add(new Equipo(	"CP74B",	"Cohesivo",	(float)90,	(float)2.134,(float)	6.05,	(float)5,	(float)5,	(float)195,	(float)1.984,	(float)2500,	5, 43));
        equiposIniciales.add(new Equipo(	"CP76B",	"Cohesivo",	(float)90,	(float)2.134,(float)	6.13,	(float)5,	(float)5,	(float)196,	(float)1.984,	(float)2500,	4, 43));
        equiposIniciales.add(new Equipo(	"CP78B",	"Cohesivo",	(float)90,	(float)2.134,(float)	6.13,	(float)5,	(float)5,	(float)196,	(float)1.984,	(float)2500,	3, 43));
        equiposIniciales.add(new Equipo(	"CS64B",	"Cohesivo",	(float)95,	(float)2.134,(float)	5.86,	(float)5,	(float)5,	(float)193,	(float)1.984,	(float)2500,	13, 43));
        equiposIniciales.add(new Equipo(	"CS66B",	"Cohesivo",	(float)95,	(float)2.134,(float)	5.86,	(float)5,	(float)5,	(float)193,	(float)1.984,	(float)2500,	10, 43));
        equiposIniciales.add(new Equipo(	"CS68B",	"Cohesivo",	(float)95,	(float)2.134,(float)	6.05,	(float)5,	(float)5,	(float)195,	(float)1.984,	(float)2500,	9, 43));
        equiposIniciales.add(new Equipo(	"CS74B",	"Cohesivo",	(float)95,	(float)2.134,(float)	6.05,	(float)5,	(float)5,	(float)195,	(float)1.984,	(float)2500,	7, 50));
        equiposIniciales.add(new Equipo(	"CS76B",	"Cohesivo",	(float)95,	(float)2.134,(float)	6.13,	(float)5,	(float)5,	(float)196,	(float)1.984,	(float)2500,	6, 50));
        equiposIniciales.add(new Equipo(	"CS78B",	"Cohesivo",	(float)95,	(float)2.134,(float)	6.13,	(float)5,	(float)5,	(float)196,	(float)1.984,	(float)2500,	6, 50));
        equiposIniciales.add(new Equipo(	"CP44B",	"Cohesivo",	(float)95,	(float)1.676,(float)	5.09,	(float)5,	(float)5,	(float)177,	(float)1.526,	(float)2500,	14, 50));
        equiposIniciales.add(new Equipo(	"CP54B",	"Cohesivo",	(float)95,	(float)2.134,(float)	5.85,	(float)5,	(float)5,	(float)193,	(float)1.984,	(float)2500,	13, 50));
        equiposIniciales.add(new Equipo(	"CP56B",	"Cohesivo",	(float)95,	(float)2.134,(float)	5.86,	(float)5,	(float)5,	(float)193,	(float)1.984,	(float)2500,	12, 50));
        equiposIniciales.add(new Equipo(	"CP64B",	"Cohesivo",	(float)95,	(float)2.134,(float)	5.86,	(float)5,	(float)5,	(float)193,	(float)1.984,	(float)2500,	10, 60));
        equiposIniciales.add(new Equipo(	"CP66B",	"Cohesivo",	(float)95,	(float)2.134,(float)	5.86,	(float)5,	(float)5,	(float)193,	(float)1.984,	(float)2500,	10, 60));
        equiposIniciales.add(new Equipo(	"CP68B",	"Cohesivo",	(float)95,	(float)2.134,(float)	6.05,	(float)5,	(float)5,	(float)195,	(float)1.984,	(float)2500,	9, 60));
        equiposIniciales.add(new Equipo(	"CP74B",	"Cohesivo",	(float)95,	(float)2.134,(float)	6.05,	(float)5,	(float)5,	(float)195,	(float)1.984,	(float)2500,	7, 60));
        equiposIniciales.add(new Equipo(	"CP76B",	"Cohesivo",	(float)95,	(float)2.134,(float)	6.13,	(float)5,	(float)5,	(float)196,	(float)1.984,	(float)2500,	6, 60));
        equiposIniciales.add(new Equipo(	"CP78B",	"Cohesivo",	(float)95,	(float)2.134,(float)	6.13,	(float)5,	(float)5,	(float)196,	(float)1.984,	(float)2500,	5, 60));

        for(Equipo equipo: equiposIniciales){
            drimerDBHelper.insertarEquipo(equipo.toJSON());
        }
    }

    public void agregarNuevoEquipo(){
        showDialog();
    }

    public void showDialog(){
        final Dialog dialog = new Dialog(this.getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_add_equipo);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        TextView text = (TextView) dialog.findViewById(R.id.textview_dialog_addequipo_title);
        Button dialogButton = (Button) dialog.findViewById(R.id.button_dialog_ok);
        String[] items = new String[]{"Granular", "Semicohesivo", "Cohesivo"};
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getContext(), R.layout.list_item, items);
        ((AutoCompleteTextView)dialog.findViewById(R.id.autocomplete_textview_tipo_suelo)).setAdapter(spinnerAdapter);

        String[] porcentajes = new  String[]{"90", "95"};
        ArrayAdapter<String> percentageAdapter = new ArrayAdapter<String>(getContext(), R.layout.list_item, porcentajes);
        ((AutoCompleteTextView)dialog.findViewById(R.id.autocomplete_textview_proctor)).setAdapter(percentageAdapter);

        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String codigo = ((TextInputLayout) dialog.findViewById(R.id.editText)).getEditText().getText().toString();
                String tipoSuelo = ((AutoCompleteTextView) dialog.findViewById(R.id.autocomplete_textview_tipo_suelo)).getText().toString();
                float porcentajeProctor = Float.parseFloat(((AutoCompleteTextView) dialog.findViewById(R.id.autocomplete_textview_proctor)).getText().toString());
                float anchoEfectivo = Float.parseFloat(((TextInputLayout) dialog.findViewById(R.id.editText4)).getEditText().getText().toString());
                float velocidadOperacion = Float.parseFloat(((TextInputLayout) dialog.findViewById(R.id.editText5)).getEditText().getText().toString());
                float ancho = Float.parseFloat(((TextInputLayout) dialog.findViewById(R.id.editText6)).getEditText().getText().toString());
                float largo = Float.parseFloat(((TextInputLayout) dialog.findViewById(R.id.editText7)).getEditText().getText().toString());
                float distanciaLateral = Float.parseFloat(((TextInputLayout) dialog.findViewById(R.id.editText8)).getEditText().getText().toString());
                float distanciaFrontal = Float.parseFloat(((TextInputLayout) dialog.findViewById(R.id.editText9)).getEditText().getText().toString());
                float numeroPasadas = Float.parseFloat(((TextInputLayout) dialog.findViewById(R.id.editText10)).getEditText().getText().toString());
                int costo = Integer.parseInt(((TextInputLayout) dialog.findViewById(R.id.editText11)).getEditText().getText().toString());

                JSONObject jsonEquipo = new JSONObject();

                try {
                    jsonEquipo.put(COLUMN_NAME_CODIGO_EQUIPO, codigo);
                    jsonEquipo.put(COLUMN_NAME_TIPO_SUELO_EQUIPO, tipoSuelo);
                    jsonEquipo.put(COLUMN_NAME_PORC_PROCTOR_EQUIPO, porcentajeProctor);
                    jsonEquipo.put(COLUMN_NAME_ANCHO_EFECTIVO_EQUIPO, anchoEfectivo);
                    jsonEquipo.put(COLUMN_NAME_VELOCIDAD_OPERACION_EQUIPO, velocidadOperacion);
                    jsonEquipo.put(COLUMN_NAME_ANCHO_EQUIPO, ancho);
                    jsonEquipo.put(COLUMN_NAME_LARGO_EQUIPO, largo);
                    jsonEquipo.put(COLUMN_NAME_DISTANCIA_LATERAL_EQUIPO, distanciaLateral);
                    jsonEquipo.put(COLUMN_NAME_DISTANCIA_FRONTAL_EQUIPO, distanciaFrontal);
                    jsonEquipo.put(COLUMN_NAME_NUMERO_PASADAS_EQUIPO, numeroPasadas);
                    jsonEquipo.put(COLUMN_NAME_COSTO_EQUIPO, costo);
                    drimerDBHelper.insertarEquipo(jsonEquipo);
                    
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
        equipos.clear();
        equipos.addAll(drimerDBHelper.getAllEquipos());
        equipoAdapter.notifyDataSetChanged();
    }

}
