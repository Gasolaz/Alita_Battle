package models;

public class Item {

    public int id;
    public String name;
    public int price;
    public int attr_id;
    public String attribute_name;
    public int attribute_modifier;
    public int str;
    public int agi;
    public int intel;
    public int def;
    public int hp;

    public Item(){
    }

    public Item(int item_id, String name, int price, int attribute_id){
        this.id = item_id;
        this.name = name;
        this.price = price;
        this.attr_id = attribute_id;
    }

    public Item(String name, int price, String attribute_name, int attribute_modifier){
        this.name = name;
        this.price = price;
        this.attribute_name = attribute_name;
        this.attribute_modifier = attribute_modifier;
    }

    public Item(int item_id, String name, int price, int str, int agi, int intel, int def, int hp){
        this.id = item_id;
        this.name = name;
        this.price = price;
        this.str = str;
        this.agi = agi;
        this.intel = intel;
        this.def = def;
        this.hp = hp;
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public int getPrice(){
        return price;
    }

    public int getStr() { return str; }

    public int getAgi() { return agi; }

    public int getIntel() { return intel; }

    public int getDef() { return def; }

    public int getHp() { return hp; }

    public int getAttr_id(){
        return attr_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setAttr_id(int attr_id) {
        this.attr_id = attr_id;
    }

    public void setAttribute_name(String attribute_name) {
        this.attribute_name = attribute_name;
    }

    public void setAttribute_modifier(int attribute_modifier) {
        this.attribute_modifier = attribute_modifier;
    }

    public void setStr(int str) {
        this.str = str;
    }

    public void setAgi(int agi) {
        this.agi = agi;
    }

    public void setIntel(int intel) {
        this.intel = intel;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
}
