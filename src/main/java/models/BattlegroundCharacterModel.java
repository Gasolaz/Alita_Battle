package models;

public class BattlegroundCharacterModel {
    public String name;
    public String race;
    public String role;
    public int level;
    public int hp;
    public int mana;
    public int strength;
    public int agility;
    public int intelligence;
    public int armor;

    public BattlegroundCharacterModel() {
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getHp() {
        return 100 + level * 20;
    }

    public void setHp(int level) {
        this.hp = 100 + level * 20;
    }

    public int getMana() {
        return 50 + level * 15;
    }

    public void setMana(int level) {
        this.mana = 50 + level * 15;
    }

    public int getStrength() {
        return 5 + level * 2;
    }

    public void setStrength(int level) {
        this.strength = 5 + level * 2;
    }

    public int getAgility() {
        return 5 + level * 2;
    }

    public void setAgility(int level) {
        this.agility = 5 + level * 2;
    }

    public int getIntelligence() {
        return 5 + level * 2;
    }

    public void setIntelligence(int level) {
        this.intelligence = 5 + level * 2;
    }

    public int getArmor() {
        return 0;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }
}
