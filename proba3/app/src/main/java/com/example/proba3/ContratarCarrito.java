package com.example.proba3;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ContratarCarrito extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private TextView nombre, precio, preciopreciofinal;
    private Spinner spinner;
    private Button button;
    private String nom, preciostr;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contratarcarrito);

        nombre = findViewById(R.id.nominfermer);
        precio = findViewById(R.id.preuinfermer);
        preciopreciofinal = findViewById(R.id.preciototal);
        spinner = findViewById(R.id.spinnerservei);
        spinner.setOnItemSelectedListener(this);
        button = findViewById(R.id.buttoncontratar);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.servicios, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        nom = getIntent().getStringExtra("nombre");
        preciostr = getIntent().getStringExtra("precio");
        nombre.setText(nom);
        precio.setText(preciostr);
        preciopreciofinal.setText(preciostr);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContratarCarrito.this, EfectuarPago.class);
                double preciodouble = Double.parseDouble(preciostr);
                intent.putExtra("precio", preciodouble);
                intent.putExtra("destino", "contratar");
                intent.putExtra("nombre", nom);
                intent.putExtra("servicio", spinner.getSelectedItem().toString());
                startActivity(intent);
            }
        });


    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (position > 5) {

            AlertDialog.Builder builder;
            builder = new AlertDialog.Builder(this);
            builder.setMessage("Requiere receta medica,tiene receta? ").setTitle("IMPORTANTE")
                    .setCancelable(false);
            builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(ContratarCarrito.this, "Sin receta medica este servicio no se realizara", Toast.LENGTH_SHORT).show();
                }
            });
            builder.show();

        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
