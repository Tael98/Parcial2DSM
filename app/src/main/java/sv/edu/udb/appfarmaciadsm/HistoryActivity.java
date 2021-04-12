package sv.edu.udb.appfarmaciadsm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import sv.edu.udb.appfarmaciadsm.datos.Compra;
import sv.edu.udb.appfarmaciadsm.datos.compAdapter;

public class HistoryActivity extends AppCompatActivity {

    public static FirebaseDatabase database = FirebaseDatabase.getInstance();
    public static DatabaseReference refCompras = database.getReference("ordenes");

    Query consultaOrdenada = refCompras.orderByChild("fecha");

    List<Compra> compras;
    ListView listaHistorial;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        listaHistorial = findViewById(R.id.listaHistorial);

        compras = new ArrayList<>();

        consultaOrdenada.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                compras.removeAll(compras);
                for (DataSnapshot dato : dataSnapshot.getChildren()) {
                    Compra persona = dato.getValue(Compra.class);
                    persona.setKey(dato.getKey());
                    compras.add(persona);
                }

                compAdapter adapter = new compAdapter(HistoryActivity.this, compras);
                listaHistorial.setAdapter(adapter);

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}