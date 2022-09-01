package org.pokemon.example.dto;

import com.opencsv.bean.CsvBindByName;

import java.util.Objects;

public class Pokemon {
    @CsvBindByName(column = "#")
    private int id;
    @CsvBindByName(column = "Name")
    private String name;

    @CsvBindByName(column = "Type 1")
    private String firstType;

    @CsvBindByName(column = "Type 2")
    private String secondType;

    @CsvBindByName(column = "Total")
    private int total;

    @CsvBindByName(column = "HP")
    private double hp;

    @CsvBindByName(column = "Attack")
    private double attack;

    @CsvBindByName(column = "Defense")
    private double defense;

    @CsvBindByName(column = "Sp. Atk")
    private double spAttack;

    @CsvBindByName(column = "Sp. Def")
    private double spDefense;

    @CsvBindByName(column = "Speed")
    private double speed;

    @CsvBindByName(column = "Generation")
    private double generation;

    @CsvBindByName(column = "Legendary")
    private boolean isLegendary;


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFirstType() {
        return firstType;
    }

    public String getSecondType() {
        return secondType;
    }

    public int getTotal() {
        return total;
    }

    public double getHp() {
        return hp;
    }

    public void setHp(double hp) {
        this.hp = hp;
    }

    public double getAttack() {
        return attack;
    }

    public void setAttack(double attack) {
        this.attack = attack;
    }

    public double getDefense() {
        return defense;
    }

    public void setDefense(double defense) {
        this.defense = defense;
    }

    public double getSpAttack() {
        return spAttack;
    }

    public double getSpDefense() {
        return spDefense;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getGeneration() {
        return generation;
    }

    public boolean isLegendary() {
        return isLegendary;
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", firstType=" + firstType +
                ", secondType=" + secondType +
                ", total=" + total +
                ", hp=" + hp +
                ", attack=" + attack +
                ", defense=" + defense +
                ", spAttack=" + spAttack +
                ", spDefense=" + spDefense +
                ", speed=" + speed +
                ", generation=" + generation +
                ", legendary=" + isLegendary +
                '}';
    }

    public boolean isTypeOf(String type) {
        return Objects.equals(this.getFirstType(), type)
                || Objects.equals(this.getSecondType(), type);
    }
}
