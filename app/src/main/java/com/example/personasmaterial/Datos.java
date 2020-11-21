package com.example.personasmaterial;

import android.app.ActivityManager;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Datos {
    private static String bd="personas";
    private static DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();
    private static ArrayList<Persona> personas=new ArrayList<>();

    public static String getId(){
        return databaseReference.push().getKey();
    }

    public static void guardar(Persona p){
        databaseReference.child(bd).child(p.getId()).setValue(p);
    }

    public static ArrayList<Persona> obtener(){
        return personas;
    }
}
