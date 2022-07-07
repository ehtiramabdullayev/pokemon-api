package org.pokemon.example.model;

public class PokemonEntity {
    private final String name;
    private final String firstType;
    private final String secondType;
    private final int total;
    private final double hp;
    private final double attack;
    private final double defense;
    private final double spAttack;
    private final double spDefense;
    private final double speed;
    private final double generation;
    private final boolean isLegendary;

    public PokemonEntity(final String name,
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
