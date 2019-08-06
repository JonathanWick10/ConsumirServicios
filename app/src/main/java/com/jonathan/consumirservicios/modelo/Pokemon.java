package com.jonathan.consumirservicios.modelo;

import java.util.ArrayList;
import java.util.List;

public class Pokemon {

    private Integer count;
    private Object previus;
    private List<Resultado> results = new ArrayList<>();
    private String next;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Object getPrevius() {
        return previus;
    }

    public void setPrevius(Object previus) {
        this.previus = previus;
    }

    public List<Resultado> getResults() {
        return results;
    }

    public void setResults(List<Resultado> results) {
        this.results = results;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }
}
