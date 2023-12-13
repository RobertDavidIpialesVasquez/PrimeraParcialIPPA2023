package com.rdiv.primeraparcial;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Descuento extends AppCompatActivity {

    TextView txvCedula, txvNombres, txvValorPlaca, txvValorAnio, txvValorMarca, txvValorMultas, txvValorTotal, txvMarca, txvTipo, txvColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descuento);
        txvCedula = findViewById(R.id.txtcedula4);
        txvNombres = findViewById(R.id.txtnombre4);
        txvMarca = findViewById(R.id.txtmarca4);
        txvTipo = findViewById(R.id.txttipo4);
        txvColor = findViewById(R.id.txtcolor4);
        txvValorPlaca = findViewById(R.id.txtmatricula4);
        txvValorAnio = findViewById(R.id.txtanio4);
        txvValorMarca = findViewById(R.id.txtmarca4);
        txvValorMultas = findViewById(R.id.txtmultas4);
        txvValorTotal = findViewById(R.id.txttotal4);
        Button btnregresar = findViewById(R.id.bttsiguiente4);

        Bundle datosV = getIntent().getExtras();
        String cedulaP = datosV.getString("cedulaP");
        String nombresP = datosV.getString("nombresP");
        String marcaV = datosV.getString("marcaV");
        String tipoV = datosV.getString("tipoV");
        String colorV = datosV.getString("colorV");
        String placaV = datosV.getString("placaV");
        Integer anioV = datosV.getInt("anioV");
        Integer valorV = datosV.getInt("valorV");
        Integer multasV = datosV.getInt("multasV");

        double valorPlaca = this.calcularValorRenovacionPlacas(cedulaP,placaV);
        double valorAnio = this.calcularMultaContaminacion(anioV);
        double valorMarca = this.calcularValorMatriculacion(marcaV,tipoV,valorV);
        double valorMultas = this.calcularMultaPorMultas(multasV);
        double valorTotal = valorPlaca + valorAnio + valorMarca + valorMultas;

        txvCedula.setText(cedulaP);
        txvNombres.setText(nombresP);
        txvMarca.setText(marcaV);
        txvTipo.setText(tipoV);
        txvColor.setText(colorV);
        txvValorPlaca.setText("$"+valorPlaca);
        txvValorAnio.setText("$"+valorAnio);
        txvValorMarca.setText("$"+valorMarca);
        txvValorMultas.setText("$"+valorMultas);
        txvValorTotal.setText("$"+valorTotal);

        btnregresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Descuento.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    public double calcularValorRenovacionPlacas(String cedula, String placa) {
        int sueldoBasico = 435;
        if (cedula.startsWith("1") && placa.contains("I")) {
            // Calcula el 5% del sueldo básico para renovación de placas
            double porcentajeRenovacion = 0.05;
            return sueldoBasico * porcentajeRenovacion;
        }
        return 0;
    }
    public double calcularMultaContaminacion(int anioFabricacion) {

        if (anioFabricacion < 2010) {

            double porcentajeMulta = 0.02;
            int aniosDeContaminacion = 2010 - anioFabricacion;
            double valorAnio = aniosDeContaminacion * porcentajeMulta;
            return valorAnio;
        }
        return 0;
    }
    public double calcularValorMatriculacion(String marca, String tipo, int valorVehiculo) {
        // Verifica la marca y el tipo del vehículo y calcula el valor de matriculación según las condiciones
        if (marca.equals("TOYOTA") && tipo.equals("JEEP")) {
            // 8% del valor del vehículo
            double porcentajeMatriculacion = 0.08;
            double valorMarca = valorVehiculo * porcentajeMatriculacion;
            return valorMarca;
        } else if (marca.equals("TOYOTA") && tipo.equals("CAMIONETA")) {
            // 12% del valor del vehículo
            double porcentajeMatriculacion = 0.12;
            double valorMarca = valorVehiculo * porcentajeMatriculacion;
            return valorMarca;
        } else if (marca.equals("SUSUKI") && tipo.equals("VITARA")) {
            // 10% del valor del vehículo
            double porcentajeMatriculacion = 0.10;
            double valorMarca = valorVehiculo * porcentajeMatriculacion;
            return valorMarca;
        } else if (marca.equals("SUSUKI") && tipo.equals("AUTOMOVIL")) {
            // 9% del valor del vehículo
            double porcentajeMatriculacion = 0.09;
            double valorMarca = valorVehiculo * porcentajeMatriculacion;
            return valorMarca;
        }
        return 0;
    }
    public double calcularMultaPorMultas(int cantidadMultas) {
        int sueldoBasico = 435;
        if (cantidadMultas > 0) {
            double porcentajeMulta = 0.25;
            return sueldoBasico * porcentajeMulta;
        }
        return 0;
    }
}