package resources;

import com.mysql.cj.xdevapi.Table;
import com.sun.xml.internal.bind.v2.model.core.ID;

public class Cons {

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

    //Table: Races
    public static final String TABLE_RACES = "Races";
    //Table Races -> Columns
    public static final String RACES_RACE_NAME = "race_name";
    public static final String RACES_RACIAL_PERK = "racial_perk";

    //Table:Inventory
    public static final String TABLE_INVENTORY = "Inventory";
    //Table Inventory -> Columns
    public static final String INVENTORY_ITEM_NAME = "item_name";

    //Table:Messages
    public static final String TABLE_MESSAGES = "Messages";
    //Table Messages -> Columns
    public static final String MESSAGES_CHARACTER_ID = "char_id";
    public static final String MESSAGES_TIME = "msg_time";
    public static final String MESSAGES_TEXT = "msg_text";


    //    Database -> CRUD

    // Creation
    public static final String  CREATE_TABLE_USERS = "CREATE TABLE IF NOT EXISTS " + TABLE_USERS + " ( " + ID + " INTEGER NOT NULL AUTO_INCREMENT, " +
            USERS_USERNAME + " TEXT NOT NULL, " + USERS_HASHED_PASS + " TEXT NOT NULL, " + USERS_SALT + " TEXT NOT NULL, " + USERS_EMAIL +
            " TEXT NOT NULL, " + USERS_CHARACTER_ID + " INTEGER, " + USERS_IS_ADMIN + " BOOLEAN NOT NULL, PRIMARY KEY (" + ID + "))";
    public static final String CREATE_TABLE_CHARACTERS = "CREATE TABLE IF NOT EXISTS " + TABLE_CHARACTERS + " ( " + ID + " INTEGER NOT NULL AUTO_INCREMENT, "
            + CHARACTERS_NAME + " VARCHAR(255) NOT NULL UNIQUE, " + CHARACTERS_RACE_ID + " INTEGER NOT NULL, " + CHARACTERS_ROLE_ID + " INTEGER NOT NULL, " +
            CHARACTERS_SEX + " TEXT NOT NULL, " + CHARACTERS_LEVEL + " INTEGER NOT NULL, " + CHARACTERS_WINS + " INTEGER NOT NULL, " + CHARACTERS_LOSES +
            " INTEGER NOT NULL, " + CHARACTERS_GOLD + " INTEGER NOT NULL, PRIMARY KEY(" + ID + "))";
    public static final String CREATE_TABLE_SESSIONS = "CREATE TABLE IF NOT EXISTS " + TABLE_SESSIONS + " ( " + ID + " INTEGER NOT NULL AUTO_INCREMENT, " +
            SESSIONS_HASHED_SESSION + " TEXT NOT NULL, " + SESSIONS_SALT + " TEXT NOT NULL, " + SESSIONS_USER_ID + " INTEGER NOT NULL, " + "PRIMARY KEY(" + ID + "))";
    public static final String CREATE_TABLE_RACES = "CREATE TABLE IF NOT EXISTS " + TABLE_RACES + " ( " + ID + " INTEGER NOT NULL AUTO_INCREMENT, " +
            RACES_RACE_NAME + " VARCHAR(255) NOT NULL UNIQUE, " + RACES_RACIAL_PERK + " TEXT NOT NULL, PRIMARY KEY(" + ID + "))";
    public static final String CREATE_TABLE_INVENTORY = "CREATE TABLE IF NOT EXISTS " + TABLE_INVENTORY + " ( " + ID + " INTEGER NOT NULL AUTO_INCREMENT, " +
            INVENTORY_ITEM_NAME + " TEXT NOT NULL, PRIMARY KEY(" + ID + "))";
    public static final String CREATE_TABLE_ROLES = "CREATE TABLE IF NOT EXISTS " + TABLE_ROLES + " ( " + ID + " INTEGER NOT NULL AUTO_INCREMENT, " + ROLES_ROLE + " VARCHAR(255) NOT NULL UNIQUE, " +
            "PRIMARY KEY(" + ID + "))";
    public static final String CREATE_TABLE_MESSAGES  = "CREATE TABLE IF NOT EXISTS " + TABLE_MESSAGES + " (" + ID + " INTEGER NOT NULL AUTO_INCREMENT, " +
            MESSAGES_CHARACTER_ID + " INTEGER  NOT NULL, " + MESSAGES_TEXT + " TEXT NOT NULL,  PRIMARY KEY(" + ID + "))";

    // Insertion
    //  Race
    public static final String INSERT_RACE_HUMAN = "INSERT IGNORE INTO " + TABLE_RACES + "(" + RACES_RACE_NAME + ", " + RACES_RACIAL_PERK +
            ") VALUES('human', 'human_perk')";
    public static final String INSERT_RACE_ELF = "INSERT IGNORE INTO " + TABLE_RACES + "(" + RACES_RACE_NAME + ", " + RACES_RACIAL_PERK +
            ") VALUES('elf', 'elf_perk')";
    public static final String INSERT_RACE_DWARF = "INSERT IGNORE INTO " + TABLE_RACES + "(" + RACES_RACE_NAME + ", " + RACES_RACIAL_PERK +
            ") VALUES('dwarf', 'dwarf_perk')";
    public static final String INSERT_RACE_UNDEAD = "INSERT IGNORE INTO " + TABLE_RACES + "(" + RACES_RACE_NAME + ", " + RACES_RACIAL_PERK +
            ") VALUES('undead', 'undead_perk')";
    public static final String INSERT_RACE_ORC = "INSERT IGNORE INTO " + TABLE_RACES + "(" + RACES_RACE_NAME + ", " + RACES_RACIAL_PERK +
            ") VALUES('orc', 'orc_perk')";

    // Role
    public static final String INSERT_ROLE_WIZARD = "INSERT IGNORE INTO " + TABLE_ROLES + "(" + ROLES_ROLE + ") VALUES('wizard')";
    public static final String INSERT_ROLE_FIGHTER = "INSERT IGNORE INTO " + TABLE_ROLES + "(" + ROLES_ROLE + ") VALUES('fighter')";
    public static final String INSERT_ROLE_PALADIN = "INSERT IGNORE INTO " + TABLE_ROLES + "(" + ROLES_ROLE + ") VALUES('paladin')";
    public static final String INSERT_ROLE_ROGUE = "INSERT IGNORE INTO " + TABLE_ROLES + "(" + ROLES_ROLE + ") VALUES('rogue')";

    // Messages
    public static final String INSERT_MSG = "INSERT INTO " + TABLE_MESSAGES + "(" + MESSAGES_CHARACTER_ID + ", " + MESSAGES_TEXT + ") VALUES(?,?)";

    // Selection
    // Messages
    public static final String SELECT_MESSAGES = "SELECT * FROM " + TABLE_MESSAGES;

}
