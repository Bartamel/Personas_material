package com.example.personasmaterial;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
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
    private DatabaseReference databaseReference;
    private String bd="personas";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        lista=findViewById(R.id.lspPersonas);

        personas=new ArrayList<>();
        lim=new LinearLayoutManager(this);
        adapter=new AdaptadorPersona(personas, this);
        lim.setOrientation(RecyclerView.VERTICAL);

        lista.setLayoutManager(lim);
        lista.setAdapter(adapter);

        databaseReference= FirebaseDatabase.getInstance().getReference();
        databaseReference.child(bd).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                personas.clear();
                if(snapshot.exists()){
                    for(DataSnapshot snap: snapshot.getChildren()){
                        Persona p=snap.getValue(Persona.class);
                        personas.add(p);
                    }
                }
                adapter.notifyDataSetChanged();
                Datos.setPersonas(personas);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

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