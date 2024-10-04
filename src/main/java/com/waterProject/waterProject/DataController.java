package com.waterProject.waterProject;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "https://clownfish-app-qdrqn.ondigitalocean.app/")
@RestController
public class DataController {
    private ArrayList<Data> dataList = new ArrayList<>();

    @DeleteMapping("/deleteData")
    public void deleteData() {
        dataList.removeAll(dataList);
    }
    @PostMapping("/setData")
    public void setData(@RequestBody Data newData) {
        if(dataList.size() >= 336) {
            dataList.remove(0);
        }
        dataList.add(newData);
        System.out.println("Data från arduino - Temperatur: "+newData.getTemperature());
        System.out.println("Data från arduino - Luftfuktighet: "+newData.getHumidity());
        System.out.println("Data från arduino - Jordfuktighet: "+newData.getMoisture());
    }

    @GetMapping("/getTemperature")
    public Double getTemperature() {
        if(dataList.isEmpty()) {
            return 0.0;
        }
        return dataList.get(dataList.size() - 1).getTemperature();
    }

    @GetMapping("/getHumidity")
    public Double getHumidity() {
        if(dataList.isEmpty()) {
            return null;
        }
        return dataList.get(dataList.size() -1).getHumidity();
    }

    @GetMapping("getMoisture") 
    public Double getMoisture() {
        if(dataList.isEmpty()) {
            return null;
        }
        return dataList.get(dataList.size() -1).getMoisture();
    }

    @GetMapping("/getAverageData")
    public Data getAverageData() {        
        double avgTemperature = dataList.stream()
            .mapToDouble(Data::getTemperature)
            .average().orElse(0.0);

        double avgHumidity = dataList.stream()
            .mapToDouble(Data::getHumidity)
            .average().orElse(0.0);

        double avgMoisture = dataList.stream()
            .mapToDouble(Data::getMoisture)
            .average().orElse(0.0);

            avgTemperature = Math.round(avgTemperature * 10.0) / 10.0;
            avgHumidity = Math.round(avgHumidity * 10.0) / 10.0;
            avgMoisture = Math.round(avgMoisture * 10.0) / 10.0;

        return new Data(avgTemperature, avgHumidity, avgMoisture);
    }
}
