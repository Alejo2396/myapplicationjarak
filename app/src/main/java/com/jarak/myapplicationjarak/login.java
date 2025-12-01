package com.jarak.myapplicationjarak;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.jarak.myapplicationjarak.model.ConexionBd;

public class login extends AppCompatActivity {

    EditText etNombre, etApellido, etEdad, etApodo, etCorreo;
    Spinner spColegios;
    RadioButton rbF, rbM;
    CheckBox cbSeries, cbDormir, cbEscribir, cbLeer;
    Button btnRegistrar;
    ConexionBd conexionBd;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etNombre = findViewById(R.id.etNombre);
        etApellido = findViewById(R.id.etApellido);
        etEdad = findViewById(R.id.etEdad);
        etApodo = findViewById(R.id.etApodo);
        etCorreo = findViewById(R.id.etCorreo);
        spColegios = findViewById(R.id.spColegios);
        rbF = findViewById(R.id.rbF);
        rbM = findViewById(R.id.rbM);
        cbSeries = findViewById(R.id.cbSeries);
        cbDormir = findViewById(R.id.cbDormir);
        cbEscribir = findViewById(R.id.cbEscribir);
        cbLeer = findViewById(R.id.cbLeer);
        btnRegistrar = findViewById(R.id.btnRegistrar);

        // Spinner colegios
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Colegios, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spColegios.setAdapter(adapter);
        spColegios.setSelection(0);

        // Base de datos
        conexionBd = new ConexionBd(this);
        db = conexionBd.getWritableDatabase();

        btnRegistrar.setOnClickListener(v -> registrarDatos());
    }

    private void registrarDatos() {
        String nombre = etNombre.getText().toString().trim();
        String apellido = etApellido.getText().toString().trim();
        String apodo = etApodo.getText().toString().trim();
        String correo = etCorreo.getText().toString().trim();

        int edadValor = 0;
        try {
            edadValor = Integer.parseInt(etEdad.getText().toString().trim());
        } catch (NumberFormatException e) {
            edadValor = 0;
        }

        String colegio = spColegios.getSelectedItem().toString();
        if(colegio.equals("Seleccione su colegio")) colegio = "";

        String genero = rbF.isChecked() ? "F" : rbM.isChecked() ? "M" : "";

        String gustos = "";
        if(cbSeries.isChecked()) gustos += "Ver series,";
        if(cbDormir.isChecked()) gustos += "Dormir,";
        if(cbEscribir.isChecked()) gustos += "Escribir,";
        if(cbLeer.isChecked()) gustos += "Leer,";

        try {
            String sql = "INSERT INTO DATOS (nombre, apellido, edad, apodo, correo, colegio, genero, gustos) VALUES (?,?,?,?,?,?,?,?)";
            SQLiteStatement stmt = db.compileStatement(sql);
            stmt.bindString(1, nombre);
            stmt.bindString(2, apellido);
            stmt.bindLong(3, edadValor);
            stmt.bindString(4, apodo);
            stmt.bindString(5, correo);
            stmt.bindString(6, colegio);
            stmt.bindString(7, genero);
            stmt.bindString(8, gustos);
            stmt.executeInsert();

            Toast.makeText(this, "Datos registrados", Toast.LENGTH_SHORT).show();

            // Limpiar campos
            etNombre.setText("");
            etApellido.setText("");
            etEdad.setText("");
            etApodo.setText("");
            etCorreo.setText("");
            rbF.setChecked(false);
            rbM.setChecked(false);
            cbSeries.setChecked(false);
            cbDormir.setChecked(false);
            cbEscribir.setChecked(false);
            cbLeer.setChecked(false);
            spColegios.setSelection(0);

        } catch (Exception e) {
            Toast.makeText(this, "Error al registrar: " + e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
}
