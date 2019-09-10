package br.com.rbarreto.firebasecrud;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import br.com.rbarreto.firebasecrud.adapter.MeuAdapter;
import br.com.rbarreto.firebasecrud.modelo.Tarefa;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    private ListView listView;
    private List<Tarefa> listaTarefas = new ArrayList<Tarefa>();
    private ArrayAdapter<Tarefa> arrayAdapterTarefa;
    private Tarefa tarefaSelecionada;
    private EditText etNome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        etNome = findViewById(R.id.editTextNome);
        conectarBanco();
        eventoDatabase();
        eventoSuperDatabase();
    }

    private void eventoSuperDatabase() {
        Query query = FirebaseDatabase.getInstance().getReference().child("Tarefa");

    }

    private void eventoDatabase() {
        databaseReference.child("Tarefa").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listaTarefas.clear();
                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                    Tarefa tarefa = snapshot.getValue(Tarefa.class);
                    listaTarefas.add(tarefa);
                }

                //arrayAdapterTarefa = new ArrayAdapter<Tarefa>(MainActivity.this, R.layout.layout_lista, R.id.tvNomeTarefa, listaTarefas);
                arrayAdapterTarefa = new MeuAdapter(MainActivity.this, (ArrayList<Tarefa>) listaTarefas);
                listView.setAdapter(arrayAdapterTarefa);

                listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                        tarefaSelecionada = (Tarefa)parent.getItemAtPosition(position);
                        alertConfirm(tarefaSelecionada);
                        return true;
                    }
                });

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        tarefaSelecionada = (Tarefa)parent.getItemAtPosition(position);
                        Intent intent = new Intent(getApplicationContext(), AdicionaActivity.class);
                        intent.putExtra("UUID", tarefaSelecionada.getUid());
                        intent.putExtra("IMAGESRC", tarefaSelecionada.getImageSrc());
                        startActivity(intent);
                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_novo){
            Intent intent = new Intent(this, AdicionaActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    public void conectarBanco(){
        FirebaseApp.initializeApp(MainActivity.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseDatabase.setPersistenceEnabled(true);
        databaseReference = firebaseDatabase.getReference();
    }

    public void salvarTarefa(View view){
        Tarefa tarefa = new Tarefa();

        tarefa.setUid(UUID.randomUUID().toString());

        tarefa.setNome(etNome.getText().toString());
        tarefa.setStatus(false);
        databaseReference.child("Tarefa").child(tarefa.getUid()).setValue(tarefa);
    }

    public void alertConfirm(final Tarefa tarefa){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.app_name);
        builder.setMessage("Você deseja remover essa tarefa?");
        builder.setIcon(R.drawable.ic_launcher_foreground);
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                databaseReference.child("Tarefa").child(tarefa.getUid()).removeValue();
            }
        });
        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }
}
