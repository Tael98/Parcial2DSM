package sv.edu.udb.appfarmaciadsm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import sv.edu.udb.appfarmaciadsm.datos.Compra;

public class OrderActivity extends AppCompatActivity {

    private TextView txtTotal;
    public String total;

    public static FirebaseDatabase database = FirebaseDatabase.getInstance();
    public static DatabaseReference refOrden = database.getReference("ordenes");
    Date currentTime = Calendar.getInstance().getTime();
    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        txtTotal = (TextView)findViewById(R.id.txtTotal);

        total = String.valueOf(HomeActivity.total);
        if(total.substring(total.lastIndexOf(".") + 1).length() < 2)
        {
            //Se añade un 0 para uniformidad
            total = total + "0";
        }
        else if(total.substring(total.lastIndexOf(".") + 1).length() > 2)
        {
            String[] parts = total.split("\\.");
            total = parts[0] + "." + parts[1].substring(0,2);
        }
        total = "Su total es de: $" + total;
        txtTotal.setText(total);

        //Asignando método a los botones
        findViewById(R.id.btn_Confirm).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {   ConfirmarCompra();  }
        });
    }

    private void ConfirmarCompra() {
        AlertDialog.Builder ad = new AlertDialog.Builder(OrderActivity.this);
        ad.setMessage("¿Está seguro de registrar esta orden?")
                .setTitle("Confirmación");

        ad.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id)
            {
                dateFormat.setTimeZone(TimeZone.getTimeZone("GMT-6"));
                String fecha = dateFormat.format(currentTime);
                Compra orden = new Compra(fecha, HomeActivity.total);
                refOrden.push().setValue(orden);
                Toast.makeText(OrderActivity.this,"¡Orden registrada!",Toast.LENGTH_SHORT).show();
                HomeActivity.total = 0;
                Intent intent = new Intent(OrderActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        ad.setNegativeButton("No", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id) {
                Toast.makeText(OrderActivity.this,"Orden no registrada.",Toast.LENGTH_SHORT).show();
            }
        });

        ad.show();
    }
}