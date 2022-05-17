package com.example.cadastrojogos;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.cadastrojogos.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    DBHelper helper = new DBHelper(this);
    private EditText edtUsuario;
    private EditText edtSenha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void cadastrarTime(View view) {
        Intent it = new Intent(this, Activity2.class);
        startActivity(it);
    }

    public void listarTimes(View view){
        Intent it = new Intent(this, Activity3.class);
        startActivity(it);
    }

    public void cadastrarJogador(View view) {
        Intent it = new Intent(this, Activity4.class);
        startActivity(it);
    }

    public void listarJogadores(View view){
        Intent it = new Intent(this, Activity5.class);
        startActivity(it);
    }
}