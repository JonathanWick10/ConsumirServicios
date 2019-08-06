package com.jonathan.consumirservicios.controlador;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jonathan.consumirservicios.R;
import com.jonathan.consumirservicios.adaptadores.PokemonAdapter;
import com.jonathan.consumirservicios.interfaces.Cliente;
import com.jonathan.consumirservicios.modelo.Pokemon;
import com.jonathan.consumirservicios.modelo.Resultado;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    //Variables
    private RecyclerView recyclerView;
    private List<Resultado> datos;
    private PokemonAdapter adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        referenciar();
        inflarRecycler();
        cargarJson();
    }

    private void cargarJson() {
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create(gson)).build();
        Cliente restCliente = retrofit.create(Cliente.class);
        Call<Pokemon> call = restCliente.getPokemones();
        call.enqueue(new Callback<Pokemon>() {
            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                switch (response.code()){
                    case 200:
                        Pokemon data = response.body();
                        adaptador.swap(data.getResults());
                        adaptador.notifyDataSetChanged();
                        break;
                    case 401:
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(Call<Pokemon> call, Throwable t) {
                Log.e("error", t.toString());
            }
        });
    }

    private void inflarRecycler() {
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        datos = new ArrayList<Resultado>();
        adaptador = new PokemonAdapter(MainActivity.this, datos);
        recyclerView.setAdapter(adaptador);
    }

    private void referenciar() {
        recyclerView = findViewById(R.id.rvPokemon);
    }
}
