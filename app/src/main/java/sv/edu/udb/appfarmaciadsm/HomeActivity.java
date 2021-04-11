package sv.edu.udb.appfarmaciadsm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class HomeActivity extends AppCompatActivity {

    RecyclerView recView;
    medAdapter adapter;
    public static FirebaseDatabase database = FirebaseDatabase.getInstance();
    public static DatabaseReference refMedicinas = database.getReference("medicinas");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        /*GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);
        if(signInAccount!=null)
        {

        }*/

        //Asignando método al boton
        findViewById(R.id.btnLogOff).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {   LogOff();  }
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
}