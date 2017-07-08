import java.io.*;

/**
 * Created by Михаил on 06.07.2017.
 */
public class Main {
    public static void main(String[] args){

        while (true){
            System.out.println("Выберите тип расчета.");
            System.out.println("1 - Расчет скорости движения воздуха и падения напора в воздуховоде.");
            System.out.println("2 - Расчет скорости движения воды и падения напора в трубе.");
            System.out.println("3 - Расчет Kvs.");
            System.out.println("4 - Расчет тепловой мощности в расход теплоносителя и обратно.");
            System.out.println("5 - Перевод величин из одной размерности в другую.\n");

            int choice = getChoice(5);

            switch (choice){
                case 1:
                    airDuct();
                    break;
                case 2:
                    waterPipe();
                    break;
                case 3:
                    kvs();
                    break;
                case 4:
                    heatPower();
                    break;
                case 5:
                    valuesConversion();
                    break;
                default:
                    System.out.println("Программа завершена.");
                    return;
            }
        }

    }

    public static void airDuct(){
        System.out.println("Выберите тип воздуховода.");
        System.out.println("1 - Круглый.");
        System.out.println("2 - Прямоугольный.");
        int choice = getChoice(2);
        switch (choice){
            case 1:
                AirDuct circle = new AirDuct(500, 0.25f, 20, 0.1f);

                /*try{
                    float flowRate = Float.parseFloat(reader.readLine());
                    float diameter = Float.parseFloat(reader.readLine());
                    float temperature = Float.parseFloat(reader.readLine());
                    float roughness = Float.parseFloat(reader.readLine());
                    circle = new AirDuct(flowRate, diameter, temperature, roughness);
                    System.out.println(circle.getVelocity());
                    System.out.println(circle.getPressureDrop());
                } catch (IOException e){}*/

                System.out.println(circle.getVelocity());
                System.out.println(circle.getPressureDrop() + "\n");

                break;
            case 2:
                AirDuct rectangle = new AirDuct(500, 0.25f, 0.25f, 20, 0.1f);

                System.out.println(rectangle.getVelocity());
                System.out.println(rectangle.getPressureDrop() + "\n");
                break;
            default:
                break;
        }

    }

    public static void waterPipe(){
        System.out.println("Считаем трубу.");
    }

    public static void kvs(){
        System.out.println("Считаем Kvs.");
    }

    public static void heatPower(){
        System.out.println("Считаем нагрузки и расходы.");
    }

    public static void valuesConversion(){
        System.out.println("Считаем воздуховод.");
    }

    public static int getChoice(int options){
        int choice = -1;
        while(choice < 0 || choice > options){
            System.out.println("Введите целое число от 1 до " + options + " или 0 для выхода.");
            try{
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                choice = Integer.parseInt(reader.readLine());
            }catch (IOException ex){
                System.out.println("Неверный ввод. Введите целое число от 1 до " + options + " или 0 для выхода.");
            }
        }
        return choice;

    }


}
