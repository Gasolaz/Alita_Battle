package resources;

import java.util.HashMap;

public final class ConsTables {

    //    All Tables ID
    public static final String ID = "_id";
    public static final int NO_ID = -1;

    //    Table: Users
    public static final String TABLE_USERS = "Users";
    //    Table Users -> Columns
    public static final String USERS_USERNAME ="username";
    public static final String USERS_HASHED_PASS ="hashed_pass";
    public static final String USERS_SALT = "salt";
    public static final String USERS_EMAIL = "email";
    public static final String USERS_CHARACTER_ID="character_id";
    public static final String USERS_IS_ADMIN = "isAdmin";

    // Table: Characters
    public static final String TABLE_CHARACTERS = "Characters";
    // Table Characters -> Columns
    public static final String CHARACTERS_RACE_ID = "race_id";
    public static final String CHARACTERS_SEX = "sex";
    public static final String CHARACTERS_LEVEL = "level";
    public static final String CHARACTERS_WINS= "wins";
    public static final String CHARACTERS_LOSES = "loses";
    public static final String CHARACTERS_GOLD = "gold";
    public static final String CHARACTERS_ROLE_ID = "role_id";
    public static final String CHARACTERS_NAME = "character_name";
    public static final String CHARACTERS_ITEM_SET_ID = "item_set_id";

    // Table: Itemset
    public static final String TABLE_ITEMSET = "Itemset"; // (L) changed name
    // Table Itemset -> Columns
//    public static final String SET_ID = "set_id"; // (L) --- Nereikia
    public static final String TORSO_ID = "torso_id"; // (L) +
    public static final String LEGS_ID = "legs_id"; // (L) +
    public static final String LEFT_HAND_ID = "left_h_id"; // (L) first_hand_id --> left_h_id
    public static final String RIGHT_HAND_ID = "right_h_id"; // (L) second_hand_id --> right_h_id

    // Global in tables: TORSO, LEGS, LEFT_HAND and RIGHT_HAND
    public static final String ITEM_NAME = "item_name";
    // public static final String ID = "id"; // (L) --- Nereikia

    // Table: Torso
    public static final String TABLE_TORSO = "Set_torso"; // (L) Item_torso --> Set_torso

    // Table: Legs
    public static final String TABLE_LEGS = "Set_legs"; // (L) Item_legs --> Set_legs

    // Table: First hand
    public static final String TABLE_LEFTHAND = "Set_lefthand"; // (L) Item_first_hand --> Set_lefthand

    // Table: Second hand
    public static final String TABLE_RIGHTHAND = "Set_righthand"; // (L) Item_second_hand --> Set_righthand

    // Attribute column -> shared by TORSO, LEGS, FIRST_HAND and SECOND_HAND tables
    public static final String ATTRIBUTE_ID = "attr_id"; // (L) ATTRIBUTE_ID --> ATTRIBUTE_ID __ id_of_attribute --> attr_id
    // Price
    public static final String PRICE = "price";

    // Table: Attributes
    public static final String TABLE_ATTRIBUTES = "Item_attributes";
    // Table Attributes -> Columns
    // public static final String ATTRIBUTE_ID = "attr_id"; // (L) --- Nereikia
    public static final String STRENGTH = "strength";
    public static final String AGILITY = "agility";
    public static final String INTELLIGENCE = "intelligence";
    public static final String DEFENSE = "defense";
    public static final String HITPOINTS = "hitpoints";

    // Table: Roles
    public static final String TABLE_ROLES = "Roles";
    // Table Roles -> Columns
    public static final String ROLES_ROLE = "role";

    // Table: Sessions
    public static final String TABLE_SESSIONS = "Sessions";
    //Table Sessions -> Columns
    public static final String SESSIONS_HASHED_SESSION = "hashed_session";
    public static final String SESSIONS_SALT = "salt";
    public static final String SESSIONS_USER_ID = "user_id";

    // Table: Challenges
    public static final String TABLE_CHALLENGES = "Challenges";
    // Table Challenges -> Columns
    public static final String CHALLENGES_CHARACTER_ID = "character_id";
    public static final String CHALLENGES_CHALLENGED_ID = "challenged_character_id";

    //Table: Races
    public static final String TABLE_RACES = "Races";
    //Table Races -> Columns
    public static final String RACES_RACE_NAME = "race_name";
    public static final String RACES_RACIAL_PERK = "racial_perk";

    //Table:Inventory
    public static final String TABLE_INVENTORY = "Inventory";
    //Table Inventory -> Columns
    public static final String INVENTORY_ITEM_NAME = "item_name";
    public static final int MAX_INVENTORY_SPACE = 5;

    //Table:Messages
    public static final String TABLE_MESSAGES = "Messages";
    //Table Messages -> Columns
    public static final String MESSAGES_CHARACTER_ID = "char_id";
    public static final String MESSAGES_TIME = "msg_time";
    public static final String MESSAGES_TEXT = "msg_text";

    //Table:Arena
    public static final String TABLE_ARENA = "Arena";
    //Table Arena -> Columns
    public static final String ARENA_CHARACTER_ID = "character_id";
    public static final String ARENA_ENEMY_ID = "enemy_id";

    //Table:
    //    Database -> CRUD

    // Creation
    public static final String  CREATE_TABLE_USERS = "CREATE TABLE IF NOT EXISTS " + TABLE_USERS + " ( " + ID + " INTEGER NOT NULL AUTO_INCREMENT, " +
            USERS_USERNAME + " TEXT NOT NULL, " + USERS_HASHED_PASS + " TEXT NOT NULL, " + USERS_SALT + " TEXT NOT NULL, " + USERS_EMAIL +
            " TEXT NOT NULL, " + USERS_CHARACTER_ID + " INTEGER, " + USERS_IS_ADMIN + " BOOLEAN NOT NULL, PRIMARY KEY (" + ID + "))";
    public static final String CREATE_TABLE_CHARACTERS = "CREATE TABLE IF NOT EXISTS " + TABLE_CHARACTERS + " ( " + ID + " INTEGER NOT NULL AUTO_INCREMENT, "
            + CHARACTERS_NAME + " VARCHAR(255) NOT NULL UNIQUE, " + CHARACTERS_RACE_ID + " INTEGER NOT NULL, " + CHARACTERS_ROLE_ID + " INTEGER NOT NULL, " +
            CHARACTERS_SEX + " TEXT NOT NULL, " + CHARACTERS_LEVEL + " INTEGER NOT NULL, " + CHARACTERS_WINS + " INTEGER NOT NULL, " + CHARACTERS_LOSES +
            " INTEGER NOT NULL, " + CHARACTERS_GOLD + " INTEGER NOT NULL, " + RIGHT_HAND_ID + " INTEGER NOT NULL, " + LEFT_HAND_ID + " INTEGER NOT NULL, " +
            TORSO_ID + " INTEGER NOT NULL, " + LEGS_ID + " INTEGER NOT NULL, image_link TEXT NOT NULL, PRIMARY KEY(" + ID + "))";
    public static final String CREATE_TABLE_SESSIONS = "CREATE TABLE IF NOT EXISTS " + TABLE_SESSIONS + " ( " + ID + " INTEGER NOT NULL AUTO_INCREMENT, " +
            SESSIONS_HASHED_SESSION + " TEXT NOT NULL, " + SESSIONS_SALT + " TEXT NOT NULL, " + SESSIONS_USER_ID + " INTEGER NOT NULL, " + "PRIMARY KEY(" + ID + "))";
    public static final String CREATE_TABLE_RACES = "CREATE TABLE IF NOT EXISTS " + TABLE_RACES + " ( " + ID + " INTEGER NOT NULL AUTO_INCREMENT, " +
            RACES_RACE_NAME + " VARCHAR(255) NOT NULL UNIQUE, " + RACES_RACIAL_PERK + " TEXT NOT NULL, PRIMARY KEY(" + ID + "))";
    public static final String CREATE_TABLE_INVENTORY = "CREATE TABLE IF NOT EXISTS " + TABLE_INVENTORY + " ( " +
            SESSIONS_USER_ID + " INTEGER NOT NULL, " + USERS_CHARACTER_ID + " INTEGER NOT NULL, " + INVENTORY_ITEM_NAME + "1 VARCHAR(255), " +
            INVENTORY_ITEM_NAME + "2 VARCHAR(255), " + INVENTORY_ITEM_NAME + "3 VARCHAR(255), " + INVENTORY_ITEM_NAME +
            "4 VARCHAR(255), " + INVENTORY_ITEM_NAME + "5 VARCHAR(255), PRIMARY KEY(" + SESSIONS_USER_ID + "))";
    public static final String CREATE_TABLE_ROLES = "CREATE TABLE IF NOT EXISTS " + TABLE_ROLES + " ( " + ID + " INTEGER NOT NULL AUTO_INCREMENT, " + ROLES_ROLE + " VARCHAR(255) NOT NULL UNIQUE, " +
            "PRIMARY KEY(" + ID + "))";
    public static final String CREATE_TABLE_CHALLENGES = "CREATE TABLE IF NOT EXISTS " + TABLE_CHALLENGES + " ( " + ID + " INTEGER NOT NULL AUTO_INCREMENT, " + CHALLENGES_CHARACTER_ID +
            " INTEGER NOT NULL, " + CHALLENGES_CHALLENGED_ID + " INTEGER NOT NULL, PRIMARY KEY (" + ID + "))";
    public static final String CREATE_TABLE_MESSAGES  = "CREATE TABLE IF NOT EXISTS " + TABLE_MESSAGES + " (" + ID + " INTEGER NOT NULL AUTO_INCREMENT, " +
            MESSAGES_CHARACTER_ID + " INTEGER  NOT NULL, " + MESSAGES_TIME + " DATETIME, " + MESSAGES_TEXT + " TEXT NOT NULL,  PRIMARY KEY(" + ID + "))";
    public static final String CREATE_TABLE_ITEMSET = "CREATE TABLE IF NOT EXISTS " + TABLE_ITEMSET + " (" + ID + " INTEGER NOT NULL, " + TORSO_ID + " INTEGER NOT NULL, " + LEGS_ID +
            " INTEGER NOT NULL, " + LEFT_HAND_ID + " INTEGER NOT NULL, " + RIGHT_HAND_ID + " INTEGER NOT NULL, PRIMARY KEY (" + ID + "))";
    public static final String CREATE_TABLE_TORSO = "CREATE TABLE IF NOT EXISTS " + TABLE_TORSO + " (" + ID + " INTEGER NOT NULL AUTO_INCREMENT, " + ITEM_NAME + " VARCHAR(255) NOT NULL UNIQUE, " + PRICE + " INTEGER NOT NULL, " + ATTRIBUTE_ID +
            " INTEGER NOT NULL, PRIMARY KEY (" + ID + "))";
    public static final String CREATE_TABLE_LEGS = "CREATE TABLE IF NOT EXISTS " + TABLE_LEGS + " (" + ID + " INTEGER NOT NULL AUTO_INCREMENT, " + ITEM_NAME + " VARCHAR(255) NOT NULL UNIQUE, " + PRICE + " INTEGER NOT NULL, "  + ATTRIBUTE_ID +
            " INTEGER NOT NULL, PRIMARY KEY (" + ID + "))";
    public static final String CREATE_TABLE_LEFT_HAND = "CREATE TABLE IF NOT EXISTS " + TABLE_LEFTHAND + " (" + ID + " INTEGER NOT NULL AUTO_INCREMENT, " + ITEM_NAME + " VARCHAR(255) NOT NULL UNIQUE, " + PRICE + " INTEGER NOT NULL, " + ATTRIBUTE_ID +
            " INTEGER NOT NULL, PRIMARY KEY (" + ID + "))";
    public static final String CREATE_TABLE_RIGHT_HAND = "CREATE TABLE IF NOT EXISTS " + TABLE_RIGHTHAND + " (" + ID + " INTEGER NOT NULL AUTO_INCREMENT, " + ITEM_NAME + " VARCHAR(255) NOT NULL UNIQUE, "  + PRICE + " INTEGER NOT NULL, " + ATTRIBUTE_ID +
            " INTEGER NOT NULL, PRIMARY KEY (" + ID + "))";
    public static final String CREATE_TABLE_ATTRIBUTES = "CREATE TABLE IF NOT EXISTS " + TABLE_ATTRIBUTES + " (" + ID + " INTEGER NOT NULL, " + STRENGTH + " INTEGER NOT NULL, " + AGILITY +
            " INTEGER NOT NULL, " + INTELLIGENCE + " INTEGER NOT NULL, " + DEFENSE + " INTEGER NOT NULL, " + HITPOINTS + " INTEGER NOT NULL, PRIMARY KEY (" + ID + "))";
    // Figthing Table
    public static final String CREATE_TABLE_FIGHT = "CREATE TABLE IF NOT EXISTS Fight (_id INTEGER NOT NULL AUTO_INCREMENT, " +
            "char_id INTEGER NOT NULL, enemy_id INTEGER NOT NULL, attack TEXT NOT NULL, defend TEXT NOT NULL, hp_id INTEGER NOT NULL," +
            "PRIMARY KEY (_id)) ";
    //Arena Table
    public static final String CREATE_TABLE_ARENA = "CREATE TABLE IF NOT EXISTS " + TABLE_ARENA + " ( " + ID + " INTEGER NOT NULL AUTO_INCREMENT, " +
            ARENA_CHARACTER_ID + " INTEGER NOT NULL, " + ARENA_ENEMY_ID + " INTEGER NOT NULL, hp INTEGER NOT NULL, result TEXT, PRIMARY KEY(" + ID + "))";

    public static final HashMap<String, String> tablesCreation = new HashMap<String, String>() {
        {
            put("userTable", CREATE_TABLE_USERS);
            put("charactersTable", CREATE_TABLE_CHARACTERS);
            put("sessionsTable", CREATE_TABLE_SESSIONS);
            put("racesTable", CREATE_TABLE_RACES);
            put("inventoryTable", CREATE_TABLE_INVENTORY);
            put("rolesTable", CREATE_TABLE_ROLES);
            put("challengesTable", CREATE_TABLE_CHALLENGES);
            put("messagesTable", CREATE_TABLE_MESSAGES);
            put("itemsetTable", CREATE_TABLE_ITEMSET);
            put("torsoTable", CREATE_TABLE_TORSO);
            put("legsTable", CREATE_TABLE_LEGS);
            put("lefthandTable", CREATE_TABLE_LEFT_HAND);
            put("righthandTable", CREATE_TABLE_RIGHT_HAND);
            put("attributesTable", CREATE_TABLE_ATTRIBUTES);
            put("fightTable", CREATE_TABLE_FIGHT);
            put("arenaTable", CREATE_TABLE_ARENA);
        }
    };
}
