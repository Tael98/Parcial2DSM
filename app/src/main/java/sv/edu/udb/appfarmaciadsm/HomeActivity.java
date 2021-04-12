package sv.edu.udb.appfarmaciadsm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import sv.edu.udb.appfarmaciadsm.datos.Medicina;
import sv.edu.udb.appfarmaciadsm.datos.medAdapter;

public class HomeActivity extends AppCompatActivity {

    RecyclerView recView;
    medAdapter adapter;
    public static FirebaseDatabase database = FirebaseDatabase.getInstance();
    public static DatabaseReference refMedicinas = database.getReference("medicinas");
    public static double total;
    public static int productos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        /*GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);
        if(signInAccount!=null)
        { }*/

        //Asignando método a los botones
        findViewById(R.id.btnLogOff).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {   LogOff();  }
        });

        findViewById(R.id.btnComprar).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {   Buy();  }
        });

        findViewById(R.id.floatingBtn).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {   History();  }
        });

        recView = findViewById(R.id.rcView);
        recView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Medicina> options =
                new FirebaseRecyclerOptions.Builder<Medicina>()
                        .setQuery(refMedicinas, Medicina.class)
                        .build();
        adapter = new medAdapter(options);
        recView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    private void LogOff() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    private void Buy()
    {
        if(total > 0)
        {
            Intent intent = new Intent(HomeActivity.this, OrderActivity.class);
            startActivity(intent);
        }
        else Toast.makeText(getApplicationContext(), "Ingrese un total válido.", Toast.LENGTH_LONG).show();
    }

    private void History()
    {
        Intent intent = new Intent(HomeActivity.this, HistoryActivity.class);
        startActivity(intent);
    }


}