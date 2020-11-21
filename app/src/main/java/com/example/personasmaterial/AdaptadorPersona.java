package com.example.personasmaterial;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdaptadorPersona extends RecyclerView.Adapter<AdaptadorPersona.PersonaViewHolder>{
    private ArrayList<Persona> personas;
    private OnpersonalClickListener clickListener;

    public AdaptadorPersona(ArrayList<Persona> personas, OnpersonalClickListener clickListener){
        this.personas=personas;
        this.clickListener=clickListener;
    }
    @NonNull
    @Override
    public PersonaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_personas,parent,false);

        return new PersonaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonaViewHolder holder, int position) {
        Persona p=personas.get(position);
        holder.cedula.setText(p.getCedula());
        holder.nombre.setText(p.getNombre());
        holder.apellido.setText(p.getApellido());
        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onPersonalClick(p);
            }
        });

    }

    @Override
    public int getItemCount() {
        return personas.size();
    }
    public static class PersonaViewHolder extends RecyclerView.ViewHolder{
        private CircleImageView foto;
        private TextView cedula;
        private TextView nombre;
        private TextView apellido;
        private View v;

        public PersonaViewHolder(View itemView){
            super(itemView);
            v=itemView;
            foto=v.findViewById(R.id.imgFotoItem);
            cedula=v.findViewById(R.id.lblCedulaItem);
            nombre=v.findViewById(R.id.lblNombreItem);
            apellido=v.findViewById(R.id.lblApellidoItem);

        }
    }
    public interface OnpersonalClickListener{
        void onPersonalClick(Persona p);
    }
}


