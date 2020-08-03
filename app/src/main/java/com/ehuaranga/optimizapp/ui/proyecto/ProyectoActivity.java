package com.ehuaranga.optimizapp.ui.proyecto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ehuaranga.optimizapp.R;
import com.ehuaranga.optimizapp.internaldb.OptimiAppDBHelper;
import com.ehuaranga.optimizapp.model.Equipo;
import com.ehuaranga.optimizapp.model.Proyecto;
import com.ehuaranga.optimizapp.ui.adapter.EquipoProyectoAdapter;
import com.ehuaranga.optimizapp.ui.adapter.EquipoSelectorAdapter;
import com.ehuaranga.optimizapp.ui.adapter.EquipoSolucionAdapter;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;


public class ProyectoActivity extends AppCompatActivity implements View.OnClickListener {
    String TAG = getClass().getCanonicalName();
    int idProyecto;
    OptimiAppDBHelper drimerDBHelper;
    RecyclerView recyclerViewEquipos;
    EquipoProyectoAdapter equiposAdapter;
    ArrayList<Equipo> equipos;
    ArrayList<Equipo> totalEquipos;
    Button addEquipoButton;
    Button calcularButton;
    Proyecto proyecto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proyecto);
        drimerDBHelper = new OptimiAppDBHelper(this);
        idProyecto = getIntent().getIntExtra("ID", 0);
        proyecto = drimerDBHelper.getProyectoById(idProyecto);
        getSupportActionBar().setTitle(proyecto.getTitulo());
        getSupportActionBar().setSubtitle(proyecto.getDescripcion());

        totalEquipos = drimerDBHelper.getAllEquiposAvailableFor(proyecto);
        equipos = drimerDBHelper.getEquiposByProyecto(proyecto);
        addEquipoButton = (Button) findViewById(R.id.add_equipo_button);
        calcularButton = (Button) findViewById(R.id.button_calcular);
        addEquipoButton.setOnClickListener(this);
        calcularButton.setOnClickListener(this);

        ((TextInputLayout)findViewById(R.id.textfield_espesor)).getEditText().setText(proyecto.getEspesor()+"");
        ((TextInputLayout)findViewById(R.id.textfield_factor_eficiencia)).getEditText().setText(proyecto.getFactorEficiencia()+"");
        ((TextInputLayout)findViewById(R.id.textfield_factor_altura)).getEditText().setText(proyecto.getAltura()+"");

        ((TextInputLayout)findViewById(R.id.textfield_area_proyecto)).getEditText().setText(proyecto.getArea()+"");
        ((TextInputLayout)findViewById(R.id.textfield_mcc)).getEditText().setText(proyecto.getMcc()+"");
        ((TextInputLayout)findViewById(R.id.textfield_he)).getEditText().setText(proyecto.getHe()+"");
        ((TextInputLayout)findViewById(R.id.textfield_t)).getEditText().setText(proyecto.getT()+"");
        ((TextInputLayout)findViewById(R.id.textfield_jornadas)).getEditText().setText(proyecto.getJornadas()+"");
        ((TextInputLayout)findViewById(R.id.textfield_tipo_suelo)).getEditText().setText(proyecto.getTipoSuelo() + "(" + proyecto.getProctor()+"%)");

        recyclerViewEquipos = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerViewEquipos.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        equiposAdapter = new EquipoProyectoAdapter(this, equipos, proyecto);
        recyclerViewEquipos.setAdapter(equiposAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_equipo_button:
                showEquiposDialog();
                break;
            case R.id.button_calcular:
                sendRequest();
                break;
        }
    }

    private void sendRequest() {
        float mcc = proyecto.getMcc();
        float T = proyecto.getT();
        float he = proyecto.getJornadas()*proyecto.getHe();
        float Rx = mcc/(T*he);
        ArrayList<Equipo> equiposX = drimerDBHelper.getEquiposByProyecto(proyecto);
        String url = "http://www.ehuaranga.com?o=min&rt=2&v="+equiposX.size()+"&l=es&d1=1&y1="+Rx+"&d2=-1&y2="+proyecto.getArea()+"&Submit=Continuar&";
        Log.d(TAG, url);
        for(int i=0; i< equiposX.size(); i++){
            url += "x"+(i+1) + "=" + equiposX.get(i).getCosto() + "&";
            url += "r1_"+(i+1) + "=" + equiposX.get(i).getPc(proyecto)+ "&";
            url += "r2_"+(i+1) + "=" + equiposX.get(i).getAreaEquipoTrabajo()+ "&";
        }

        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        showPreviewDialog(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, error.getMessage());
            }
        });


        queue.add(stringRequest);

    }

    public void showPreviewDialog(String response){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_preview);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        Button dialogButton = (Button) dialog.findViewById(R.id.button_dialog_ok);

        float mcc = proyecto.getMcc();
        float T = proyecto.getT();
        float he = proyecto.getJornadas()*proyecto.getHe();
        float Rx = mcc/(T*he);

        ArrayList<Equipo> equiposX = drimerDBHelper.getEquiposByProyecto(proyecto);
        String restriccion1 = "";
        String restriccion2 = "";
        String objetivo = "";
        for(int i=0; i< equiposX.size(); i++){
            restriccion1 += equiposX.get(i).getPc(proyecto) + "_X"+(i+1);
            restriccion2 += equiposX.get(i).getAreaEquipoTrabajo() + "_X"+(i+1);
            objetivo += equiposX.get(i).getCosto() + "_X"+(i+1);
            if(i != equiposX.size() - 1) {
                restriccion1 += " + ";
                restriccion2 += " + ";
                objetivo += " + ";
            }
            else{
                restriccion1 += " >= ";
                restriccion2 += " <= ";
                objetivo += " ";
            }
        }

        String[] soluciones = response.split("X");
        float costoTotal = (float) 0.0;
        for(int i=1; i<soluciones.length; i++){
            costoTotal += Math.ceil(Float.parseFloat(soluciones[i].split(" = ")[1]))*equiposX.get(i-1).getCosto();
        }

        RecyclerView recyclerViewSolucion = dialog.findViewById(R.id.recyclerview_solucion);
        recyclerViewSolucion.setLayoutManager(new LinearLayoutManager(this));
        EquipoSolucionAdapter equipoSolucionAdapter = new EquipoSolucionAdapter(this, equiposX, response);
        recyclerViewSolucion.setAdapter(equipoSolucionAdapter);

        ((TextInputLayout)dialog.findViewById(R.id.editText)).getEditText().setText(restriccion1);
        ((TextInputLayout)dialog.findViewById(R.id.editText4)).getEditText().setText(Rx+"");
        ((TextInputLayout)dialog.findViewById(R.id.editText2)).getEditText().setText(restriccion2);
        ((TextInputLayout)dialog.findViewById(R.id.editText5)).getEditText().setText(proyecto.getArea()+"");
        ((TextInputLayout)dialog.findViewById(R.id.editText3)).getEditText().setText(objetivo);
        ((TextInputLayout)dialog.findViewById(R.id.editText7)).getEditText().setText(costoTotal+" USD/HM");

        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public void showEquiposDialog(){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_show_equipos);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        RecyclerView recyclerViewEquipos = dialog.findViewById(R.id.recyclerview_equipos_selector);
        recyclerViewEquipos.setLayoutManager(new LinearLayoutManager(this));
        final ArrayList<Boolean> equipoSeleccionado = new ArrayList<Boolean>();
        for(Equipo equipo: totalEquipos){
            equipoSeleccionado.add(false);
        }

        EquipoSelectorAdapter equipoAdapter = new EquipoSelectorAdapter(this, totalEquipos, equipoSeleccionado);
        recyclerViewEquipos.setAdapter(equipoAdapter);

        Button dialogButton = (Button) dialog.findViewById(R.id.button_dialog_ok);


        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=0; i<equipoSeleccionado.size(); i++){
                    if (equipoSeleccionado.get(i)) {
                        drimerDBHelper.insertarEquipoEnProyecto(proyecto, totalEquipos.get(i));
                    }
                }
                updateRecycler();
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void updateRecycler() {
        equipos.clear();
        equipos.addAll(drimerDBHelper.getEquiposByProyecto(proyecto));
        equiposAdapter.notifyDataSetChanged();
    }
}
