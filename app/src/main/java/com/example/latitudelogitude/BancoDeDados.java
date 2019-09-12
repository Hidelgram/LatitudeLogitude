package com.example.latitudelogitude;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class BancoDeDados extends SQLiteOpenHelper {
    private final String criarTabela = "CREATE TABLE coordenadas(" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "chave REAK NOT NULL, "+
            "autenticacao REAL NOT NULL, "+
            "dataHora REAL NOT NULL," +
            "status INT NOT NULL);";
    private String Cadastrar1 = "INSERT INTO coordenadas (chave,autenticacao,dataHora,status)\n" +
            "VALUES (JH91HC,812j97f4298jnc2,1568026065,1)";

    private String Cadastrar2 = "INSERT INTO coordenadas (chave,autenticacao,dataHora,status)\n" +
            "VALUES (PA83B7,m9ads78cj208jf4,1568026123,0)";

    private String Cadastrar3 = "INSERT INTO coordenadas (chave,autenticacao,dataHora,status)\n" +
            "VALUES (HJ272A,981m98cmc8a12,1568026778,1)";

    private String Cadastrar4 = "INSERT INTO coordenadas (chave,autenticacao,dataHora,status)\n" +
            "VALUES (99CY2P,98mcaks326174,1568027060,0)";

    private String Cadastrar5 = "INSERT INTO coordenadas (chave,autenticacao,dataHora,status)\n" +
            "VALUES (278JMA,7812nca6123kas,1568027165,0)";


    public BancoDeDados(Context context, int version) {
        super(context, "BD_Lat_Long", null, version);
    }

    public boolean apagarPonto(int id){
        SQLiteDatabase banco = getWritableDatabase();//Abrir conexão
        //Chamar o metodo "delete" da classe. O metodo retorna a quantidade de
        //linhas que foram removidas da tabela
        if (banco.delete("coordenadas","id=?",new String[]{id+""})!=0){
            banco.close();
            return true;

        }else{
            banco.close();
            return false;
        }
    }






    public List<Coordenadas> buscaPontos(){
        List<Coordenadas> pontos = new ArrayList<Coordenadas>();
        SQLiteDatabase banco = getWritableDatabase(); //Conexão com banco
        //cusor para amazenar o retorno do Select
        Cursor c = banco.rawQuery("SELECT * FROM coordenadas", new String[]{});

        //testar se o SELECT tem o menos uma linha
        if (c.moveToFirst()){
            do {
                //buscar cada ponto cadastrar no banco
                Coordenadas coord = new Coordenadas(c.getInt(0), c.getDouble(1), c.getDouble(2), c.getLong(3));
                //Adiciona o ponto na lista
                pontos.add(coord);
            }while (c.moveToNext());
        }
        banco.close();
        return pontos;
    }

    public boolean cadastrarPontos(double chave){
        SQLiteDatabase banco = getWritableDatabase(); //Abre a conexão
        ContentValues valores = new ContentValues();
        valores.put("chave",chave);
            if (banco.insert("coordenadas",null,valores)!=-1){
            banco.close();
            return true;
        }else{
            banco.close();
            return false;
        }

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(criarTabela);
        sqLiteDatabase.execSQL(Cadastrar1);
        sqLiteDatabase.execSQL(Cadastrar2);
        sqLiteDatabase.execSQL(Cadastrar3);
        sqLiteDatabase.execSQL(Cadastrar4);
        sqLiteDatabase.execSQL(Cadastrar5);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
