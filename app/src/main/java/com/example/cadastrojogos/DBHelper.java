package com.example.testelogin;

import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "jogos.db";
    private static final String TABLE_JOGADOR = "jogador";
    private static final String COL_ID = "id";
    private static final String COL_NOME = "nome";
    private static final String COL_CPF = "cpf";
    private static final String COL_TIME_ID = "time_id";
    private static final String COL_ANONASCIMENTO = "anoNascimento";

    private static final String TABLE_TIME = "time";
    private static final String COL_DESCRICAO = "descricao";

    SQLiteDatabase db;
    public static final String TABLE_CREATE2 = "create table " + TABLE_TIME + "(" +
            COL_ID + " integer primary key autoincrement, " +
            COL_DESCRICAO + " text not null);";

    private static final String TABLE_CREATE="create table "+TABLE_JOGADOR+
            "("+COL_ID+" integer primary key autoincrement, "+
            COL_NOME+" text not null, "+COL_CPF+" text not null, " +
            COL_TIME_ID+" int not null, "+COL_ANONASCIMENTO+" int not null," +
            "FOREIGN KEY (" + COL_ID + ") REFERENCES " + TABLE_TIME + "(" + COL_ID + "));";

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE2);
        db.execSQL(TABLE_CREATE);
        this.db = db;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,int newVersion) {
        String query = "DROP TABLE IF EXISTS "+TABLE_JOGADOR;
        String query2 = "DROP TABLE IF EXISTS "+TABLE_TIME;
        db.execSQL(query);
        db.execSQL(query2);
        this.onCreate(db);
    }

    public void insereJogador(Jogador j){
        db = this.getWritableDatabase();

        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(COL_NOME, j.getNome());
            values.put(COL_CPF,j.getCpf());
            values.put(COL_TIME_ID, j.getTimeId());
            values.put(COL_ANONASCIMENTO, j.getAnoNascimento());
            db.insertOrThrow(TABLE_JOGADOR,null,values);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to add post to database");
        } finally {
            db.endTransaction();
        }
    }
    public ArrayList<Jogador> buscarJogadores(){
        String[] columns = {COL_ID, COL_NOME, COL_CPF, COL_TIME_ID, COL_ANONASCIMENTO};
        Cursor cursor = getReadableDatabase().query(TABLE_JOGADOR,
                columns, null,null,null,
                null, "upper(nome)", null);
        ArrayList<Jogador> listaJogador = new ArrayList<Jogador>();
        while(cursor.moveToNext()){
            Jogador j = new Jogador();
            j.setId(cursor.getInt(0));
            j.setNome(cursor.getString(1));
            j.setCpf(cursor.getString(2));
            j.setTimeId(cursor.getInt(3));
            j.setAnoNascimento(cursor.getInt(4));
            listaJogador.add(j);
        }
        return listaJogador;
    }
    public long excluirJogador(Jogador jogador) {
        long retornoBD;
        db = this.getWritableDatabase();
        String[] args = {String.valueOf(jogador.getId())};
        retornoBD=db.delete(TABLE_JOGADOR, COL_ID+"=?",args);
        return retornoBD;
    }
    public long atualizarJogador(Jogador j){
        long retornoBD;
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_NOME,j.getNome());
        values.put(COL_CPF,j.getCpf());
        values.put(COL_TIME_ID, j.getTimeId());
        values.put(COL_ANONASCIMENTO,j.getAnoNascimento());
        String[] args = {String.valueOf(j.getId())};
        retornoBD=db.update(TABLE_JOGADOR,values,"id=?",args);
        db.close();
        return retornoBD;
    }

    public void insereTime(Time t){
        System.out.println("aqui " + t);
        db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(COL_DESCRICAO, t.getDescricao());
            db.insertOrThrow(TABLE_TIME,null,values);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to add post to database");
        } finally {
            db.endTransaction();
        }
    }
    public ArrayList<Time> buscarTimes(){
        String[] columns = {COL_ID, COL_DESCRICAO};
        Cursor cursor = getReadableDatabase().query(TABLE_TIME,
                columns, null,null,null,
                null, "upper(descricao)", null);
        ArrayList<Time> listaTime = new ArrayList<Time>();
        while(cursor.moveToNext()){
            Time t = new Time();
            t.setId(cursor.getInt(0));
            t.setDescricao(cursor.getString(1));
            listaTime.add(t);
        }
        return listaTime;
    }
    public long excluirTime(Time time) {
        long retornoBD;
        db = this.getWritableDatabase();
        String[] args = {String.valueOf(time.getId())};
        retornoBD=db.delete(TABLE_TIME, COL_ID+"=?",args);
        return retornoBD;
    }
    public long atualizarTime(Time t){
        long retornoBD;
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_DESCRICAO,t.getDescricao());
        String[] args = {String.valueOf(t.getId())};
        retornoBD=db.update(TABLE_TIME,values,"id=?",args);
        db.close();
        return retornoBD;
    }
}
