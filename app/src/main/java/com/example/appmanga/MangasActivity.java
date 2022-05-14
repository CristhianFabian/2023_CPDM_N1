package com.example.appmanga;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

public class MangasActivity extends AppCompatActivity {

    private ListView lvMangas;
    private Button  btAdicionar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mangas);

        lvMangas = findViewById(R.id.lvMangas);
        btAdicionar = findViewById(R.id.btAdicionar);
        btAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MangasActivity.this, MainActivity.class);
                intent.putExtra("acao", "inserir");
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        carregarMangas();
    }

    private void carregarMangas(){
        List<Manga>lista= MangaDAO.getMangas(this);
        if(lista.size()==0){
            Manga fake = new Manga("Nenhum Manga Cadastrado", "...", null);
            lista.add(fake);
            lvMangas.setEnabled(false);
        }else{
            lvMangas.setEnabled(true);
        }
        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lista);
        lvMangas.setAdapter( adapter);
    }
}