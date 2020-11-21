package com.example.personasmaterial;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdaptadorPersona.OnpersonalClickListener{
    private RecyclerView lista;
    private AdaptadorPersona adapter;
    private LinearLayoutManager lim;
    private ArrayList<Persona> personas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        lista=findViewById(R.id.lspPersonas);
        personas=Datos.obtener();
        lim=new LinearLayoutManager(this);
        adapter=new AdaptadorPersona(personas, this);
        lim.setOrientation(RecyclerView.VERTICAL);
        lista.setLayoutManager(lim);
        lista.setAdapter(adapter);

    }

    public void agregar(View v){
        Intent intent;
        intent= new Intent(MainActivity.this, CrearPersonas.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onPersonalClick(Persona p) {
        Intent intent;
        Bundle bundle;

        bundle= new Bundle();

        bundle.putString("id",p.getId());
        bundle.putString("cedula",p.getCedula());
        bundle.putString("nombre",p.getNombre());
        bundle.putString("apellido",p.getApellido());

        intent= new Intent(MainActivity.this, DetallePersona.class);
        intent.putExtra("datos",bundle);
        startActivity(intent);
    }
}