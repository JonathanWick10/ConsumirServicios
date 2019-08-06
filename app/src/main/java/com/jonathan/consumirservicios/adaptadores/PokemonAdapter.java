package com.jonathan.consumirservicios.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jonathan.consumirservicios.R;
import com.jonathan.consumirservicios.modelo.Resultado;

import java.util.ArrayList;
import java.util.List;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder> {

    //variables
    List<Resultado> list;
    Context context;

    //Constructor
    public PokemonAdapter(Context context, List<Resultado> list) {
        this.list = new ArrayList<>();
        this.context = context;
    }

    //Inicializamos el View holder y especificamos el layout a utilizar
    @NonNull
    @Override
    public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PokemonViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.pokemon_item, parent, false));
    }

    //Especificamos el contenido de cada elemento
    @Override
    public void onBindViewHolder(@NonNull PokemonViewHolder holder, int position) {

        final Resultado pokemon = list.get(position);
        holder.nombre.setText(pokemon.getName());
        holder.nombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    //Este método retorna el número especifico de los datos
    @Override
    public int getItemCount() {
        return list.size();
    }

    //Minimizamos las llamadas a métodos con el patrón de diseño de View Holder
    public static class PokemonViewHolder extends RecyclerView.ViewHolder{
        TextView nombre;

        public PokemonViewHolder(@NonNull View itemView) {
            super(itemView);

            nombre = (TextView)itemView.findViewById(R.id.tvNombrePokemon);
        }
    }

    public void swap(List<Resultado> newList){
        list.clear();
        list.addAll(newList);
        notifyDataSetChanged();
    }
}

