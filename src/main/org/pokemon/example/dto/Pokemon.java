package org.pokemon.example.dto;

public class Pokemon {
    private final int id;
    private final String name;
    private final PokemonType firstType;
    private final PokemonType secondType;
    private final int total;
    private final int hp;
    private final int attack;
    private final int defense;
    private final int spAttack;
    private final int spDefense;
    private final int speed;
    private final int generation;
    private final boolean legendary;

    public Pokemon(int id,
                   String name,
                   PokemonType firstType,
                   PokemonType secondType,
                   int total,
                   int hp,
                   int attack,
                   int defense,
                   int spAttack,
                   int spDefense,
                   int speed,
                   int generation,
                   boolean legendary) {
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
        this.legendary = legendary;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public PokemonType getFirstType() {
        return firstType;
    }

    public PokemonType getSecondType() {
        return secondType;
    }

    public int getTotal() {
        return total;
    }

    public int getHp() {
        return hp;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getSpAttack() {
        return spAttack;
    }

    public int getSpDefense() {
        return spDefense;
    }

    public int getSpeed() {
        return speed;
    }

    public int getGeneration() {
        return generation;
    }

    public boolean isLegendary() {
        return legendary;
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
                ", legendary=" + legendary +
                '}';
    }
}