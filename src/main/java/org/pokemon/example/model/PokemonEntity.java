package org.pokemon.example.model;


import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@DynamicUpdate
@Table(name = "pokemons")
public class PokemonEntity {
    @Id
    private String id;

    @Column
    private String name;
    @Column
    private String firstType;
    @Column
    private String secondType;
    @Column
    private int total;
    @Column
    private double hp;
    @Column
    private double attack;
    @Column
    private double defense;
    @Column
    private double spAttack;
    @Column
    private double spDefense;
    @Column
    private double speed;
    @Column
    private double generation;
    @Column
    private boolean isLegendary;

    public PokemonEntity() {
    }

    public PokemonEntity(final String id,
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

    public double getAttack() {
        return attack;
    }

    public double getDefense() {
        return defense;
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

    public double getGeneration() {
        return generation;
    }

    public boolean isLegendary() {
        return isLegendary;
    }

    @Override
    public String toString() {
        return "PokemonEntity{" +
                "name='" + name + '\'' +
                ", firstType='" + firstType + '\'' +
                ", secondType='" + secondType + '\'' +
                ", total=" + total +
                ", hp=" + hp +
                ", attack=" + attack +
                ", defense=" + defense +
                ", spAttack=" + spAttack +
                ", spDefense=" + spDefense +
                ", speed=" + speed +
                ", generation=" + generation +
                ", isLegendary=" + isLegendary +
                '}';
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
