package com.example.latitudelogitude;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AdapterCoordenadas extends RecyclerView.Adapter {

    private Context context;
    private List<Coordenadas> pontos;

    //construtor para poder chmar a MainActivity e tabm passar a lista
    //com todos os intes que SELECT retornando do bd
    public AdapterCoordenadas(List<Coordenadas> pontos, Context context){

        this.pontos = pontos;
        this.context = context;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Chamar o layout da tela e "infiar" ele para podermos ter um objtos de
        //retorno que seja o proprio Layout xml do Cardview
        View v = LayoutInflater.from(context).
                inflate(R.layout.item_card_vilew, parent,false);
        //Frutamente, para cada item da lista, será chamado a nossa classe
        //viewHolderCoordenadas que contém a definição dos campos do XML
        ViewHolderCoordenadas viewHolper = new ViewHolderCoordenadas(v);

        return viewHolper;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int i) {
               ViewHolderCoordenadas viewCoord = (ViewHolderCoordenadas) holder;

               viewCoord.cardLatitude.setText("Lat: " + pontos.get(i).getLatitude());
               viewCoord.cardLongitude.setText("Long: " + pontos.get(i).getLongitude());
               ///converter a dataHora de segundos para dia/més/ano
        Date data = new Date(pontos.get(i).getDatahorario() * 1000);
        //Máscara para formatto de saida da data
        SimpleDateFormat sdf = new SimpleDateFormat("dd/mm HH:mm:ss");
        viewCoord.cardDataHora.setText("Data: " + sdf.format(data));

        //Programar o botão deletar
        viewCoord.btApagar.setBackground(ContextCompat.getDrawable(context,R.drawable.img));

        viewCoord.btApagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BancoDeDados bd = new BancoDeDados(context,1);
                //Recuperar o ID do intem da listar
                int id = pontos.get(i).get_id();
                if (bd.apagarPonto(id)){
                    //Avisar que o intem da posição "i" foi alterado
                    notifyItemChanged(i);
                    //Remover o intem da lista de pontos
                    pontos.remove(i);
                    Toast.makeText(context,"removido",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(context,"não removido", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return pontos.size();
    }
}
