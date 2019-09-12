package com.example.latitudelogitude;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class BrodcastSMS extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //Utilização da classe Bundle para recuperar todos
        //os extras que foram passados pelo Sistema Operacional
        Bundle extras = intent.getExtras();

        //Criação de um vetor da classe Object[] para filtrar
        //somente o conteúdo SMS que veio pelos extras
        //PDU == Protocol Description Unit (protocolo do SMS)
        Object[] pdus = (Object[]) extras.get("pdus");

        //Passagem do conteúdo Object para a classe SmsMessage
        //Também será criado um vetor se caso for mais de 1 SMS
        SmsMessage[] sms = new SmsMessage[pdus.length];

        //String numeroRemetente = "";
        String conteudoMensagem = "";
        long dataHora = 0;

        //Laço para percorrer o conteúdo do(s) SMS(s)
        for(int i=0 ; i<sms.length ; i++){
            //Recuperando a primeira mensagem e transformando
            //em um objeto da classe SmsMessage
            sms[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);

            //numeroRemetente = sms[i].getOriginatingAddress();
            conteudoMensagem += sms[i].getMessageBody();
            dataHora = sms[i].getTimestampMillis();
        }
       // String[] latLong = conteudoMensagem.split(";");

        BancoDeDados bd = new BancoDeDados(context,1);
        if (bd.cadastrarPontos(Double.parseDouble())){

            //Criar Intent (o que será aberto ao clicar na notificação
            Intent it = new Intent(context, MainActivity.class);

            //Com a MainActivity não será executada logo que a notificação for
            //exibida, mas sim quando o usuario clicar sobre ela, é necessário
            //transforma a Intent em PendingIntent
            PendingIntent pending = PendingIntent.getActivity(
                    context, //Contexto do aplicativo
                    0,//zero por padrão API
                    it,//intent que será tranformada em PendingIntent
                    PendingIntent.FLAG_CANCEL_CURRENT);


            //Criar e configurar a notificação
            Notification.Builder notificacao = new Notification.Builder(context)
                    .setContentTitle("Nova notificação recebida")//Titulo
                    .setContentText(conteudoMensagem)//texto da notificação
                    .setContentIntent(pending)//O que será aberto(pending)
                    .setSmallIcon(R.drawable.img)//icone ao lado da notificação
                    .setAutoCancel(true);//Remover notificação após clicar

            //Acessando o serviço de notificação do android
            NotificationManager servico = (NotificationManager)
                    context.getSystemService(Context.NOTIFICATION_SERVICE);

            //Passando a notificação para o serviço android
            servico.notify(153,notificacao.build());
            //153 número inteiro para indentificar a notificação
            //Pode ser qualquer valor

            Toast.makeText(context,"Cadastrado",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(context,"Não cadastrado",Toast.LENGTH_LONG).show();
        }

        //Exibindo o conteúdo da mensagem e número do remetente
       // Toast.makeText(context, "Número: " + numeroRemetente +
              //  " Mensagem: " + conteudoMensagem, Toast.LENGTH_LONG).show();
    }
}
