package com.jonathan.consumirservicios.interfaces;

import com.jonathan.consumirservicios.modelo.Pokemon;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Cliente {
    @GET("pokemon")
    Call<Pokemon> getPokemones();
}
