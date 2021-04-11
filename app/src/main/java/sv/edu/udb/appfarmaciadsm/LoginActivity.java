package sv.edu.udb.appfarmaciadsm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private EditText txtUser;
    private EditText txtPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Inicializando campos a utilizar
        txtUser=(EditText)findViewById(R.id.editTxtUser);
        txtPass=(EditText)findViewById(R.id.editTxtPass);
        //Asignando métodos a los botones
        Button clickButton = findViewById(R.id.btnLogin);
        clickButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {   Login();  }
        });
    }

    public void Login()
    {
        //a
    }
}