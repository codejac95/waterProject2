package com.waterProject.waterProject;

public class Data {
    
    private Double temperature;
    private Double humidity;
    private Double moisture;
    
    public Data(Double temperature, Double humidity, Double moisture) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.moisture = moisture;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public Double getMoisture() {
        return moisture;
    }

    public void setMoisture(Double moisture){
        this.moisture = moisture;
    }
}
