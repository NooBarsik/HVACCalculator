import java.util.*;

/**
 * Created by Михаил on 08.07.2017.
 */



public class AirDuct {
    private float flowRate;                           //кубометры в час
    private float diameter;                           //метры
    private float width;                              //метры
    private float height;                             //метры
    private float temperature;                        //градусы Цельсия
    private double velocity;                          //метры в секунду
    private double pressureDrop;                       //Паскаль на метр
    private float roughness;                          //миллиметры
    private HashMap<Integer, Double> densityTable;    //таблица плотностей воздуха
    private HashMap<Integer, Double> viscosityTable;  //таблица динамических вязкостей воздуха

    AirDuct(float flowRate, float width, float height, float temperature, float roughness){
        this.flowRate = flowRate;
        this.width = width;
        this.height = height;
        this.temperature = temperature;
        this.roughness = roughness;
        this.diameter = (2 * this.width * this.height) / (this.width + this.height);
        this.velocity = 4 * this.flowRate / (3600 * 3.14 * Math.pow(this.diameter, 2));
        getDensityTable();
        getViscosityTable();
        this.pressureDrop = (calculateLambda() * getDensity() * Math.pow(this.velocity, 2)) / (this.diameter * 2);
    }

    AirDuct(float flowRate, float diameter, float temperature, float roughness){
        this.flowRate = flowRate;
        this.diameter = diameter;
        this.temperature = temperature;
        this.roughness = roughness;
        this.velocity = 4 * this.flowRate / (3600 * 3.14 * Math.pow(this.diameter, 2));
        getDensityTable();
        getViscosityTable();
        this.pressureDrop = (calculateLambda() * getDensity() * Math.pow(this.velocity, 2)) / (this.diameter * 2);
    }

    public double getVelocity(){
        return this.velocity;
    }

    public double getPressureDrop(){
        return this.pressureDrop;
    }

    private double getDensity(){
        double density = 0;

        Integer[] temp = this.densityTable.keySet().toArray(new Integer[this.densityTable.keySet().size()]);

        for (int i = 0; i < temp.length; i++) {
            if (temp[i] <= this.temperature && this.temperature <= temp[i+1]){
                density = this.densityTable.get(temp[i]) + ((this.temperature - temp[i]) / (temp[i + 1] - temp[i]))
                        * (this.densityTable.get(temp[i + 1]) - this.densityTable.get(temp[i]));
            }
        }

        return density;
    }

    private double getViscosity(){
        double viscosity = 0;
        Integer[] temp = this.viscosityTable.keySet().toArray(new Integer[this.viscosityTable.keySet().size()]);

        for (int i = 0; i < temp.length; i++) {
            if (temp[i] <= this.temperature && this.temperature < temp[i+1]){
                viscosity = this.viscosityTable.get(temp[i]) + ((this.temperature - temp[i]) / (temp[i + 1] - temp[i]))
                        * (this.viscosityTable.get(temp[i + 1]) - this.viscosityTable.get(temp[i]));
            }
        }
        return viscosity;
    }

    private double calculateReynoldsNumber(){
        return (getDensity() * this.velocity * this.diameter) / getViscosity();
    }

    private double calculateLambda(){
        return 0.11 * Math.pow((this.roughness / this.diameter + 68 / calculateReynoldsNumber()),0.25);
    }

    private void getDensityTable(){
        this.densityTable = new HashMap<>();
        this.densityTable.put(-50, 1.584);
        this.densityTable.put(-45, 1.549);
        this.densityTable.put(-40, 1.515);
        this.densityTable.put(-35, 1.484);
        this.densityTable.put(-30, 1.453);
        this.densityTable.put(-25, 1.424);
        this.densityTable.put(-20, 1.395);
        this.densityTable.put(-15, 1.369);
        this.densityTable.put(-10, 1.342);
        this.densityTable.put(-5, 1.318);
        this.densityTable.put(0, 1.293);
        this.densityTable.put(10, 1.247);
        this.densityTable.put(15, 1.226);
        this.densityTable.put(20, 1.205);
        this.densityTable.put(30, 1.165);
        this.densityTable.put(40, 1.128);
        this.densityTable.put(50, 1.093);
        this.densityTable.put(60, 1.06);
        this.densityTable.put(70, 1.029);
        this.densityTable.put(80, 1.0);
        this.densityTable.put(90, 0.972);
        this.densityTable.put(100, 0.946);
        this.densityTable.put(110, 0.922);
        this.densityTable.put(120, 0.898);
        this.densityTable.put(130, 0.876);
        this.densityTable.put(140, 0.854);
        this.densityTable.put(150, 0.835);
        this.densityTable.put(160, 0.815);
        this.densityTable.put(170, 0.797);
        this.densityTable.put(180, 0.779);
        this.densityTable.put(190, 0.763);
        this.densityTable.put(200, 0.746);
        this.densityTable.put(250, 0.674);
        this.densityTable.put(300, 0.615);
        this.densityTable.put(350, 0.566);
        this.densityTable.put(400, 0.524);
        this.densityTable.put(450, 0.49);
        this.densityTable.put(500, 0.456);
        this.densityTable.put(550, 0.43);
        this.densityTable.put(600, 0.404);
        this.densityTable.put(650, 0.383);
        this.densityTable.put(700, 0.362);
        this.densityTable.put(750, 0.346);
        this.densityTable.put(800, 0.329);
        this.densityTable.put(850, 0.315);
        this.densityTable.put(900, 0.301);
        this.densityTable.put(950, 0.289);
        this.densityTable.put(1000, 0.277);
        this.densityTable.put(1050, 0.267);
        this.densityTable.put(1100, 0.257);
        this.densityTable.put(1150, 0.248);
        this.densityTable.put(1200, 0.239);
    }

    private void getViscosityTable(){
        this.viscosityTable = new HashMap<>();
        this.viscosityTable.put(-50, 14.6 * Math.pow(10,-6));
        this.viscosityTable.put(-45, 14.9 * Math.pow(10,-6));
        this.viscosityTable.put(-40, 15.2 * Math.pow(10,-6));
        this.viscosityTable.put(-35, 15.5 * Math.pow(10,-6));
        this.viscosityTable.put(-30, 15.7 * Math.pow(10,-6));
        this.viscosityTable.put(-25, 16.0 * Math.pow(10,-6));
        this.viscosityTable.put(-20, 16.2 * Math.pow(10,-6));
        this.viscosityTable.put(-15, 16.5 * Math.pow(10,-6));
        this.viscosityTable.put(-10, 16.7 * Math.pow(10,-6));
        this.viscosityTable.put(-5, 17.0 * Math.pow(10,-6));
        this.viscosityTable.put(0, 17.2 * Math.pow(10,-6));
        this.viscosityTable.put(10, 17.6 * Math.pow(10,-6));
        this.viscosityTable.put(15, 17.9 * Math.pow(10,-6));
        this.viscosityTable.put(20, 18.1 * Math.pow(10,-6));
        this.viscosityTable.put(30, 18.6 * Math.pow(10,-6));
        this.viscosityTable.put(40, 19.1 * Math.pow(10,-6));
        this.viscosityTable.put(50, 19.6 * Math.pow(10,-6));
        this.viscosityTable.put(60, 20.1 * Math.pow(10,-6));
        this.viscosityTable.put(70, 20.6 * Math.pow(10,-6));
        this.viscosityTable.put(80, 21.1 * Math.pow(10,-6));
        this.viscosityTable.put(90, 21.5 * Math.pow(10,-6));
        this.viscosityTable.put(100, 21.9 * Math.pow(10,-6));
        this.viscosityTable.put(110, 22.4 * Math.pow(10,-6));
        this.viscosityTable.put(120, 22.8 * Math.pow(10,-6));
        this.viscosityTable.put(130, 23.3 * Math.pow(10,-6));
        this.viscosityTable.put(140, 23.7 * Math.pow(10,-6));
        this.viscosityTable.put(150, 24.1 * Math.pow(10,-6));
        this.viscosityTable.put(160, 24.5 * Math.pow(10,-6));
        this.viscosityTable.put(170, 24.9 * Math.pow(10,-6));
        this.viscosityTable.put(180, 25.3 * Math.pow(10,-6));
        this.viscosityTable.put(190, 25.7 * Math.pow(10,-6));
        this.viscosityTable.put(200, 26.0 * Math.pow(10,-6));
        this.viscosityTable.put(225, 26.7 * Math.pow(10,-6));
        this.viscosityTable.put(250, 27.4 * Math.pow(10,-6));
        this.viscosityTable.put(300, 29.7 * Math.pow(10,-6));
        this.viscosityTable.put(325, 30.6 * Math.pow(10,-6));
        this.viscosityTable.put(350, 31.4 * Math.pow(10,-6));
        this.viscosityTable.put(400, 33.0 * Math.pow(10,-6));
        this.viscosityTable.put(450, 34.6 * Math.pow(10,-6));
        this.viscosityTable.put(500, 36.2 * Math.pow(10,-6));
        this.viscosityTable.put(550, 37.7 * Math.pow(10,-6));
        this.viscosityTable.put(600, 39.1 * Math.pow(10,-6));
        this.viscosityTable.put(650, 40.5 * Math.pow(10,-6));
        this.viscosityTable.put(700, 41.8 * Math.pow(10,-6));
        this.viscosityTable.put(750, 43.1 * Math.pow(10,-6));
        this.viscosityTable.put(800, 44.3 * Math.pow(10,-6));
        this.viscosityTable.put(850, 45.5 * Math.pow(10,-6));
        this.viscosityTable.put(900, 46.7 * Math.pow(10,-6));
        this.viscosityTable.put(950, 47.9 * Math.pow(10,-6));
        this.viscosityTable.put(1000, 49.0 * Math.pow(10,-6));
        this.viscosityTable.put(1050, 50.1 * Math.pow(10,-6));
        this.viscosityTable.put(1100, 51.2 * Math.pow(10,-6));
        this.viscosityTable.put(1150, 52.4 * Math.pow(10,-6));
        this.viscosityTable.put(1200, 53.5 * Math.pow(10,-6));
    }
}
