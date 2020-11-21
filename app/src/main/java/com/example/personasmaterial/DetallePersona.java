package com.example.personasmaterial;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class DetallePersona extends AppCompatActivity {
    private ImageView foto;
    private TextView cedula, nombre, apellido;
    private Bundle bundle;
    private Intent intent;
    private StorageReference storageReference;
    private Persona p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_persona);

        String ced,nom,apel,id;

        foto=findViewById(R.id.imgfotodetalle);
        cedula=findViewById(R.id.lblCedulaDetalle);
        nombre=findViewById(R.id.lblNombreDetalle);
        apellido=findViewById(R.id.lblApellidoDetalle);
        storageReference= FirebaseStorage.getInstance().getReference();

        intent=getIntent();
        bundle=intent.getBundleExtra("datos");

        ced=bundle.getString("cedula");
        nom=bundle.getString("nombre");
        apel=bundle.getString("apellido");
        id=bundle.getString("id");
        p=new Persona(ced,nom,apel,id);
        storageReference.child(id).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(foto);
            }
        });

        cedula.setText(ced);
        nombre.setText(nom);
        apellido.setText(apel);
    }

    public void eliminar(View v){
        String positivo, negativo;
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Eliminar Persona");
        builder.setMessage("¿Está seguro de eliminar esta persona?");
        positivo=getString(R.string.mensaje_positivo);
        negativo=getString(R.string.mensaje_negativo);

        builder.setPositiveButton(positivo, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                p.eliminar();
                onBackPressed();
            }
        });
        builder.setNegativeButton(negativo, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog dialog=builder.create();
        dialog.show();

    }
    public void onBackPressed(){
        finish();
        Intent intent=new Intent(DetallePersona.this, MainActivity.class);
        startActivity(intent);
    }
}