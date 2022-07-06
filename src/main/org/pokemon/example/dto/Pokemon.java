package org.pokemon.example.dto;

import java.util.Objects;

public class Pokemon {
    private int id;
    private String name;
    private String firstType;
    private String secondType;
    private int total;
    private double hp;
    private double attack;
    private double defense;
    private double spAttack;
    private double spDefense;
    private double speed;
    private double generation;
    private boolean isLegendary;

    public Pokemon(final int id,
                   final String name,
                   final String firstType,
                   final String secondType,
                   final int total,
                   final double hp,
                   final double attack,
                   final double defense,
                   final double spAttack,
                   final double spDefense,
                   final double speed,
                   final double generation,
                   final boolean isLegendary) {
        this.id = id;
        this.name = name;
        this.firstType = firstType;
        this.secondType = secondType;
        this.total = total;
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.spAttack = spAttack;
        this.spDefense = spDefense;
        this.speed = speed;
        this.generation = generation;
        this.isLegendary = isLegendary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstType() {
        return firstType;
    }

    public void setFirstType(String firstType) {
        this.firstType = firstType;
    }

    public String getSecondType() {
        return secondType;
    }

    public void setSecondType(String secondType) {
        this.secondType = secondType;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
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

    public void setSpAttack(double spAttack) {
        this.spAttack = spAttack;
    }

    public double getSpDefense() {
        return spDefense;
    }

    public void setSpDefense(double spDefense) {
        this.spDefense = spDefense;
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

    public void setGeneration(double generation) {
        this.generation = generation;
    }

    public boolean isLegendary() {
        return isLegendary;
    }

    public void setLegendary(boolean legendary) {
        isLegendary = legendary;
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
