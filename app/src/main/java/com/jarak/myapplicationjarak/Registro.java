package com.jarak.myapplicationjarak;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.jarak.myapplicationjarak.model.Datos;
import com.jarak.myapplicationjarak.model.Manager;

public class Registro extends AppCompatActivity {

    private Manager manager;

    private EditText edtNombre, edtApellido, edtCorreo;
    private Button btnEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro); // 👈 XML correcto

        edtNombre = findViewById(R.id.edtNombre);
        edtApellido = findViewById(R.id.edtApellido);
        edtCorreo = findViewById(R.id.edtCorreo);
        btnEnviar = findViewById(R.id.btnEnviar);

        manager = new Manager(this);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nombre = edtNombre.getText().toString().trim();
                String apellido = edtApellido.getText().toString().trim();
                String correo = edtCorreo.getText().toString().trim();

                if (nombre.isEmpty() || apellido.isEmpty() || correo.isEmpty()) {
                    Toast.makeText(Registro.this, "Complete todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                Datos datos = new Datos(nombre, apellido, correo);

                long resultado = manager.insertData(datos);

                if (resultado > 0) {
                    Toast.makeText(Registro.this, "Datos insertados", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(Registro.this, MostrarDatos.class);
                    startActivity(intent);
                    finish(); // ✅ CIERRA REGISTRO
                }
            }
        });
    }
}
