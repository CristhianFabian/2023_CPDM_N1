package com.example.appmanga;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Spinner spGeneros;
    private EditText etTitulo, etAutor;
    private Button btSalvar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spGeneros = findViewById(R.id.spGenero);
        etTitulo = findViewById(R.id.etTitulo);
        etAutor = findViewById(R.id.etCap);
        btSalvar = findViewById(R.id.btSalvar);
        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvar();
            }
        });
        //btSalvar.setEnabled(false);
        carregarGeneros();

    }

    private void salvar(){
        Manga manga = new Manga();
        String titulo = etTitulo.getText().toString();
        if(!titulo.isEmpty() && spGeneros.getSelectedItemPosition() > 0){
            manga.setTitulo(titulo);
            manga.setGenero((Genero) spGeneros.getSelectedItem());
            manga.setAutor(etAutor.getText().toString());

            MangaDAO.inserir(this, manga);
            finish();
        }

    }

    private void carregarGeneros(){
        Genero fake = new Genero(0, "Selecione o Gênero...");
        List<Genero> lista = GeneroDAO.getGeneros(  this );
        lista.add(0, fake);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lista );
        spGeneros.setAdapter( adapter );

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("Cadastrar Gênero...");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if( item.toString().equals("Cadastrar Gênero...")){
    //        GeneroDAO.inserir(MainActivity.this, "Shonen");
            cadastrarGenero();
        }
        return super.onOptionsItemSelected(item);
    }

    private void cadastrarGenero(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Cadastrar Gênero");
        alert.setIcon(android.R.drawable.ic_input_add);
        EditText etNomeGenero = new EditText(this);
        etNomeGenero.setHint("Digite o gênero");
        alert.setView(etNomeGenero);
        //alert.setMessage("Olá"); -- Usado caso queira apresentar uma mensagem na tela
        alert.setNeutralButton("Cancel", null);
        alert.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String nome = etNomeGenero.getText().toString();
                if(!nome.isEmpty()){
                    GeneroDAO.inserir(MainActivity.this, nome);
                    carregarGeneros();
                }
            }
        });
        alert.show();
    }
}