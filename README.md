# Objective

Your assignment is to create a Pokémon API from a CSV file using Java and any framework.

### Brief

Professor Oak is in trouble! A wild Blastoise wreaked havoc in the server room and destroyed every single machine. There are no backups - everything is lost! Professor Oak quickly scribbles down all the Pokémon from memory and hands them to you on a piece of paper. (`/Data/pokemon.csv`). Your task is to restore the Pokémon Database from that file and create a Pokémon API so that they’re never lost again.

### Tasks

-   Implement assignment using:
    -   Language: **Java**
    -   Framework: **any framework**
-   Create a Pokémon Model that includes all fields outlined in `/Data/pokemon.csv`
-   Parse the .csv file and create entries for each row based on the following initial conditions:
    -   Exclude Legendary Pokémon
    -   Exclude Pokémon of Type: Ghost
    -   For Pokémon of Type: Steel, double their HP
    -   For Pokémon of Type: Fire, lower their Attack by 10%
    -   For Pokémon of Type: Bug & Flying, increase their Attack Speed by 10%
    -   For Pokémon that start with the letter **G**, add +5 Defense for every letter in their name (excluding **G**)
-   Create one API endpoint `/pokemon`
    -   This API endpoint should be searchable, filterable and paginatable
        -   Search: name
        -   Filter: HP, Attack & Defense
            -   e.g. `/pokemon?hp[gte]=100&defense[lte]=200`
        -   Pagination: e.g. `/pokemon?page=1`

### Evaluation Criteria

-   **Java** best practices
-   Show us your work through your commit history
-   We're looking for you to produce working code, with enough room to demonstrate how to structure components in a small program
-   Completeness: did you complete the features?
-   Correctness: does the functionality act in sensible, thought-out ways?
-   Maintainability: is it written in a clean, maintainable way?
-   Testing: is the system adequately tested?

### CodeSubmit

Please organize, design, test and document your code as if it were going into production - then push your changes to the master branch. After you have pushed your code, you may submit the assignment on the assignment page.

All the best and happy coding,

The Axual Team

# Solution

## Setup guide

#### Minimum Requirements

- Java 11
- Maven 3.x

#### Install the application

1. Make sure you have [Java](https://www.oracle.com/technetwork/java/javase/downloads/jdk13-downloads-5672538.html) and [Maven](https://maven.apache.org) installed

2. Open the command line in the source code folder

3. Build project

  ```
  $ mvn package
  ```

Run the tests
  ```
  $ mvn test
  ```


Run the project

  ```
  $ java -jar simple-pokemon-api-1.0-SNAPSHOT.jar
  ```

## API

---
**GET** /pokemons -  get all the pokemons from the db

Example Request
```
http://localhost:5051/api/v1/pokemons
```

Example Response

```json
{
     "body": [
        {
            "name": "Bulbasaur",
            "firstType": "Grass",
            "secondType": "Poison",
            "total": 318,
            "hp": 45,
            "attack": 49,
            "defense": 49,
            "spAttack": 65,
            "spDefense": 65,
            "speed": 45,
            "generation": 1,
            "isLegendary": false
        },
        {
            "name": "Ivysaur",
            "firstType": "Grass",
            "secondType": "Poison",
            "total": 405,
            "hp": 60,
            "attack": 62,
            "defense": 63,
            "spAttack": 80,
            "spDefense": 80,
            "speed": 60,
            "generation": 1,
            "isLegendary": false
        }
     ],
    "response": {
        "status": 200,
        "message": "SUCCESS"
    }
}

```
---

**GET** /pokemonName/{pokemonName} - get all the pokemon by its name

Example Request
```
http://localhost:5051/api/v1/pokemons/pokemonName/Pikachu
```
Example Response

```json
{
    "body": {
        "name": "Pikachu",
        "firstType": "Electric",
        "secondType": "",
        "total": 320,
        "hp": 35,
        "attack": 55,
        "defense": 40,
        "spAttack": 50,
        "spDefense": 50,
        "speed": 90,
        "generation": 1,
        "legendary": false
    },
    "response": {
        "status": 200,
        "message": "SUCCESS"
    }
}
```

---

**GET** /pokemon?hp[gte]=num&defense[gte]=num&attack[gte]=num - get filtered pokemons based on the filters

Example Request
```
http://localhost:5051/api/v1/pokemons/pokemon?hp[gte]=150&defense[gte]=150&attack[gte]=125
```
Example Response

```json
{
  "body": [
    {
      "name": "SteelixMega Steelix",
      "firstType": "Steel",
      "secondType": "Ground",
      "total": 610,
      "hp": 150,
      "attack": 125,
      "defense": 230,
      "spAttack": 55,
      "spDefense": 95,
      "speed": 30,
      "generation": 2,
      "legendary": false
    },
    {
      "name": "MetagrossMega Metagross",
      "firstType": "Steel",
      "secondType": "Psychic",
      "total": 700,
      "hp": 160,
      "attack": 145,
      "defense": 150,
      "spAttack": 105,
      "spDefense": 110,
      "speed": 110,
      "generation": 3,
      "legendary": false
    }
  ],
  "response": {
    "status": 200,
    "message": "SUCCESS"
  }
}
```
