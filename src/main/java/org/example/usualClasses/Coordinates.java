package org.example.usualClasses;

import javax.xml.bind.annotation.XmlElement;

import java.util.InputMismatchException;

import static org.example.Main.sc;

/**
 * Класс, представляющий координаты.
 */
public class Coordinates {
    private Integer x;
    private long y;

    public Coordinates (boolean b){
        System.out.println("Начинается ввод координат");
        while (true){
            try {
                System.out.println("Введите координату x:");
                Long xxx = sc.nextLong();
                this.x = Math.toIntExact(xxx);
                break;
            } catch (InputMismatchException e){
                System.out.println("Поле введено неверно, попробуйте ещё раз");
            } finally {
                sc.nextLine();
            }
        }
        while (true){
            try {
                System.out.println("Введите координату y:");
                Long yyy = sc.nextLong();
                this.y = yyy;
                break;
            } catch (InputMismatchException e){
                System.out.println("Поле введено неверно, попробуйте ещё раз");
            } finally {
                sc.nextLine();
            }
        }
        Coordinates c = new Coordinates(x,y);
    }

    public Coordinates(Integer x, long y) {
        this.x = x;
        this.y = y;
    }

    @XmlElement
    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    @XmlElement
    public long getY() {
        return y;
    }

    public void setY(long y) {
        this.y = y;
    }

    public String fileToString() {
        return x +  "," + y;
    }

    @Override
    public String toString() {
        return " {" +
                "\n      x=" + x +
                "\n      y=" + y + "\n}";
    }
}