package com.example.latitudelogitude;

import
        androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        BancoDeDados bd = new BancoDeDados(this,1);
        List<Coordenadas> pontos = bd.buscaPontos();

        //Montar o recyclerView com os pontos que vietem do Banco de dados
        recyclerView.setAdapter(
                new AdapterCoordenadas(pontos, getApplicationContext())
        );
         //Atribuido um layout para recycleView
        RecyclerView.LayoutManager layout =
                new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layout);


        if(ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS)
                != PackageManager.PERMISSION_GRANTED){
            //Se o usuário ainda não autorizou a permissão...
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.RECEIVE_SMS}, 10);

        }

        if(ContextCompat.checkSelfPermission(this,Manifest.permission.READ_SMS)
                != PackageManager.PERMISSION_GRANTED){
            //Se o usuário ainda não autorizou a permissão...
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_SMS}, 15);
        }
    }
}
