package br.com.rbarreto.firebasecrud;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

import br.com.rbarreto.firebasecrud.modelo.Tarefa;

public class AdicionaActivity extends AppCompatActivity {

    EditText etNome;

    //Objetos para conexão com o banco
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adiciona);

        etNome = findViewById(R.id.txtNome);

        conectarBanco();
    }

    public void conectarBanco(){
        FirebaseApp.initializeApp(AdicionaActivity.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    public void salvarTarefa(View view){
        Tarefa tarefa = new Tarefa();
        tarefa.setUid(UUID.randomUUID().toString());
        tarefa.setNome(etNome.getText().toString());
        tarefa.setStatus(false);
        databaseReference.child("Tarefa").child(tarefa.getUid()).setValue(tarefa);
    }

}
