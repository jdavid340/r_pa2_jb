package edu.com.uce.domain.model;

public class ResultadoProcesamiento {

    private Integer registrosProcesados;
    private String tipoProcesamiento;
    private Long tiempoEjecucion;

    public Integer getRegistrosProcesados() {
        return registrosProcesados;
    }

    public void setRegistrosProcesados(Integer registrosProcesados) {
        this.registrosProcesados = registrosProcesados;
    }

    public String getTipoProcesamiento() {
        return tipoProcesamiento;
    }

    public void setTipoProcesamiento(String tipoProcesamiento) {
        this.tipoProcesamiento = tipoProcesamiento;
    }

    public Long getTiempoEjecucion() {
        return tiempoEjecucion;
    }

    public void setTiempoEjecucion(Long tiempoEjecucion) {
        this.tiempoEjecucion = tiempoEjecucion;
    }
}