package models.dal;

public class BattlegroundCharacterModelDAL {
    public String name;
    public String race;
    public String role;
    public int level;
    public int hp;
    public int tempHp;
    public int mana;
    public int strength;
    public int agility;
    public int intelligence;
    public int armor;
    public int experience;

    public BattlegroundCharacterModelDAL() {
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getTempHp() {
        return tempHp;
    }

    public int getHp() {
        return hp;
    }

    public int getMana() {
        return mana;
    }

    public int getStrength() {
        return strength;
    }

    public int getAgility() {
        return agility;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getArmor() {
        return armor;
    }

    public void setTempHp(int tempHp) {
        this.tempHp = tempHp;
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

    public void setHp(int level) {
        this.hp = 100 + level * 20;
    }

    public void setMana(int level) {
        this.mana = 50 + level * 15;
    }

    public void setStrength(int level) {
        this.strength = 5 + level * 2;
    }

    public void setAgility(int level) {
        this.agility = 5 + level * 2;
    }

    public void setIntelligence(int level) {
        this.intelligence = 5 + level * 2;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }
}
