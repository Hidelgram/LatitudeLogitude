package com.example.latitudelogitude;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

//Classe para poder mapear quais coomponetes temos na tela do Cardview
//Esta classe ira fazer a ligação de casa coomponete da tela e montar
//os intens da lista, ou seja para cada item da lista (SELECT) sera montado
//uma View (componente) com os TextView

public class ViewHolderCoordenadas extends RecyclerView.ViewHolder {
    TextView cardLatitude;
    TextView cardLongitude;
    TextView cardDataHora;
    ImageButton btApagar;

    public ViewHolderCoordenadas(@NonNull View itemView) {
        super(itemView);
        cardDataHora = itemView.findViewById(R.id.CardDataHora);
        cardLatitude = itemView.findViewById(R.id.cardLatitude);
        cardLongitude = itemView.findViewById(R.id.cardLongitude);
        btApagar = itemView.findViewById(R.id.btApagar);

    }
}
