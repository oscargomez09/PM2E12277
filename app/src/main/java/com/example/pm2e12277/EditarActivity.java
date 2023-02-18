package com.example.pm2e12277;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pm2e12277.DB.DBContactos;
import com.example.pm2e12277.Entidades.Contactos;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EditarActivity extends AppCompatActivity {

    Spinner txtPais;
    EditText txtNombre, txtTelefono, txtNota;
    FloatingActionButton btneditar, btneliminar, btnatras, btnllamar;
    boolean correcto = false;
    Contactos contacto;

    int id= 0;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_contacto);

        txtPais = findViewById(R.id.txtPais);
        txtNombre = findViewById(R.id.txtNombre);
        txtTelefono = findViewById(R.id.txtTelefono);
        txtNota = findViewById(R.id.txtNota);

        btneditar = findViewById(R.id.btneditar);
        btnatras = findViewById(R.id.btnatras);
        btneliminar = findViewById(R.id.btneliminar);
        btnllamar = findViewById(R.id.btnllamar);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                id = Integer.parseInt(null);
            } else {
                id = extras.getInt("ID");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("ID");
        }

        final DBContactos dbContactos = new DBContactos(EditarActivity.this);
        contacto = dbContactos.verContacto(id);

        if (contacto != null) {
            txtNombre.setText(contacto.getNombre());
            txtTelefono.setText(contacto.getTelefono());
            txtNota.setText(contacto.getNota());
        }

        btneditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!txtPais.getSelectedItem().toString().equals("") && !txtNombre.getText().toString().equals("") && !txtTelefono.getText().toString().equals("")  && !txtNota.getText().toString().equals("")) {
                    correcto = dbContactos.editarContacto(id, txtPais.getSelectedItem().toString(), txtNombre.getText().toString(), txtTelefono.getText().toString(), txtNota.getText().toString());

                    if(correcto){
                        Toast.makeText(EditarActivity.this, "REGISTRO MODIFICADO", Toast.LENGTH_LONG).show();
                        verRegistro();
                    } else {
                        Toast.makeText(EditarActivity.this, "ERROR AL MODIFICAR REGISTRO", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(EditarActivity.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
                    txtNombre.setError("");
                    txtTelefono.setError("");
                    txtNota.setError("");
                }
            }
        });
    }

    private void verRegistro() {
        Intent intent = new Intent(this, VerContactoActivity.class);
        intent.putExtra("ID", id);
        startActivity(intent);
    }
}