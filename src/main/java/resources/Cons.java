package resources;

import com.mysql.cj.xdevapi.Table;
import com.sun.xml.internal.bind.v2.model.core.ID;

public final class Cons {

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

    // Table: Item sets
    public static final String TABLE_ITEM_SETS = "Item_sets";
    // Table Item sets -> Columns
    public static final String SET_ID = "set_id";
    public static final String TORSO_ID = "torso_id";
    public static final String LEGS_ID = "legs_id";
    public static final String FIRST_HAND_ID = "first_hand_id";
    public static final String SECOND_HAND_ID = "second_hand_id";

    // Shared by TORSO, LEGS, FIRST_HAND and SECOND_HAND tables
    public static final String ITEM_NAME = "item_name";
    public static final String ITEM_ID = "item_id";

    // Table: Torso
    public static final String TABLE_TORSO = "Item_torso";

    // Table: Legs
    public static final String TABLE_LEGS = "Item_legs";

    // Table: First hand
    public static final String TABLE_FIRST_HAND = "Item_first_hand";

    // Table: Second hand
    public static final String TABLE_SECOND_HAND = "Item_second_hand";

    // Attribute column -> shared by TORSO, LEGS, FIRST_HAND and SECOND_HAND tables
    public static final String ID_OF_ATTRIBUTE = "id_of_attribute";
    // Price
    public static final String PRICE = "price";

    // Table: Attributes
    public static final String TABLE_ATTRIBUTES = "Item_attributes";
    // Table Attributes -> Columns
    public static final String ATTRIBUTE_ID = "attribute_id";
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

    //Arena
    public static final String TABLE_ARENA = "Arena";
    public static final String ARENA_CHARACTER_ID = "character_id";
    public static final String ARENA_ENEMY_ID = "enemy_id";

    //    Database -> CRUD

    // Creation
    public static final String  CREATE_TABLE_USERS = "CREATE TABLE IF NOT EXISTS " + TABLE_USERS + " ( " + ID + " INTEGER NOT NULL AUTO_INCREMENT, " +
            USERS_USERNAME + " TEXT NOT NULL, " + USERS_HASHED_PASS + " TEXT NOT NULL, " + USERS_SALT + " TEXT NOT NULL, " + USERS_EMAIL +
            " TEXT NOT NULL, " + USERS_CHARACTER_ID + " INTEGER, " + USERS_IS_ADMIN + " BOOLEAN NOT NULL, PRIMARY KEY (" + ID + "))";
    public static final String CREATE_TABLE_CHARACTERS = "CREATE TABLE IF NOT EXISTS " + TABLE_CHARACTERS + " ( " + ID + " INTEGER NOT NULL AUTO_INCREMENT, "
            + CHARACTERS_NAME + " VARCHAR(255) NOT NULL UNIQUE, " + CHARACTERS_RACE_ID + " INTEGER NOT NULL, " + CHARACTERS_ROLE_ID + " INTEGER NOT NULL, " +
            CHARACTERS_SEX + " TEXT NOT NULL, " + CHARACTERS_LEVEL + " INTEGER NOT NULL, " + CHARACTERS_WINS + " INTEGER NOT NULL, " + CHARACTERS_LOSES +
            " INTEGER NOT NULL, " + CHARACTERS_GOLD + " INTEGER NOT NULL, " + CHARACTERS_ITEM_SET_ID + " INTEGER NOT NULL, image_link TEXT NOT NULL, PRIMARY KEY(" + ID + "))";
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
    public static final String CREATE_TABLE_ITEM_SETS = "CREATE TABLE IF NOT EXISTS " + TABLE_ITEM_SETS + " (" + SET_ID + " INTEGER NOT NULL, " + TORSO_ID + " INTEGER NOT NULL, " + LEGS_ID +
            " INTEGER NOT NULL, " + FIRST_HAND_ID + " INTEGER NOT NULL, " + SECOND_HAND_ID + " INTEGER NOT NULL, PRIMARY KEY (" + SET_ID + "))";
    public static final String CREATE_TABLE_TORSO = "CREATE TABLE IF NOT EXISTS " + TABLE_TORSO + " (" + ITEM_ID + " INTEGER NOT NULL, " + ITEM_NAME + " VARCHAR(255) NOT NULL, " + PRICE + " INTEGER NOT NULL, " + ID_OF_ATTRIBUTE +
            " INTEGER NOT NULL, PRIMARY KEY (" + ITEM_ID + "))";
    public static final String CREATE_TABLE_LEGS = "CREATE TABLE IF NOT EXISTS " + TABLE_LEGS + " (" + ITEM_ID + " INTEGER NOT NULL, " + ITEM_NAME + " VARCHAR(255) NOT NULL, " + PRICE + " INTEGER NOT NULL, "  + ID_OF_ATTRIBUTE +
            " INTEGER NOT NULL, PRIMARY KEY (" + ITEM_ID + "))";
    public static final String CREATE_TABLE_FIRST_HAND = "CREATE TABLE IF NOT EXISTS " + TABLE_FIRST_HAND + " (" + ITEM_ID + " INTEGER NOT NULL, " + ITEM_NAME + " VARCHAR(255) NOT NULL, " + PRICE + " INTEGER NOT NULL, " + ID_OF_ATTRIBUTE +
            " INTEGER NOT NULL, PRIMARY KEY (" + ITEM_ID + "))";
    public static final String CREATE_TABLE_SECOND_HAND = "CREATE TABLE IF NOT EXISTS " + TABLE_SECOND_HAND + " (" + ITEM_ID + " INTEGER NOT NULL, " + ITEM_NAME + " VARCHAR(255) NOT NULL, "  + PRICE + " INTEGER NOT NULL, " + ID_OF_ATTRIBUTE +
            " INTEGER NOT NULL, PRIMARY KEY (" + ITEM_ID + "))";
    public static final String CREATE_TABLE_ATTRIBUTES = "CREATE TABLE IF NOT EXISTS " + TABLE_ATTRIBUTES + " (" + ATTRIBUTE_ID + " INTEGER NOT NULL, " + STRENGTH + " INTEGER NOT NULL, " + AGILITY +
            " INTEGER NOT NULL, " + INTELLIGENCE + " INTEGER NOT NULL, " + DEFENSE + " INTEGER NOT NULL, " + HITPOINTS + " INTEGER NOT NULL, PRIMARY KEY (" + ATTRIBUTE_ID + "))";

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

    // Items - 1st hand, 2nd hand, legs, torso
    public static final String INSERT_FIRST_HAND = "INSERT IGNORE INTO " + TABLE_FIRST_HAND + "(" + ITEM_ID + ", " + ITEM_NAME + ", " +
            PRICE + ", " + ID_OF_ATTRIBUTE + ") VALUES(?,?,?,?)";
    public static final String INSERT_SECOND_HAND = "INSERT IGNORE INTO " + TABLE_SECOND_HAND + "(" + ITEM_ID + ", " + ITEM_NAME + ", " +
            PRICE + ", " + ID_OF_ATTRIBUTE + ") VALUES(?,?,?,?)";
    public static final String INSERT_LEGS = "INSERT IGNORE INTO " + TABLE_LEGS + "(" + ITEM_ID + ", " + ITEM_NAME + ", " +
            PRICE + ", " + ID_OF_ATTRIBUTE + ") VALUES(?,?,?,?)";
    public static final String INSERT_TORSO = "INSERT IGNORE INTO " + TABLE_TORSO + "(" + ITEM_ID + ", " + ITEM_NAME + ", " +
            PRICE + ", " + ID_OF_ATTRIBUTE + ") VALUES(?,?,?,?)";

    // Attributes
    public static final String INSERT_ATTRIBUTES = "INSERT IGNORE INTO " + TABLE_ATTRIBUTES + "(" + ATTRIBUTE_ID + ", " + STRENGTH + ", " + AGILITY + ", " + INTELLIGENCE + ", " +
            DEFENSE + ", " + HITPOINTS + ") VALUES(?,?,?,?,?,?)";

    // Messages
    public static final String INSERT_MSG = "INSERT INTO " + TABLE_MESSAGES + "(" + MESSAGES_CHARACTER_ID + ", " + MESSAGES_TIME + ", " + MESSAGES_TEXT + ") VALUES(?,?,?)";

    // User_id and character_id to inventory table
    public static final String INSERT_TO_INVENTORY = "INSERT INTO " + TABLE_INVENTORY + "(" + SESSIONS_USER_ID + ", " + USERS_CHARACTER_ID +
            ") VALUES (?,?)";

    // Selection

    // Messages
    public static final String SELECT_MESSAGES = "SELECT * FROM " + TABLE_MESSAGES;

    //Arena Table
    public static final String CREATE_TABLE_BATTLE = "CREATE TABLE IF NOT EXISTS " + TABLE_ARENA + " ( " + ID + " INTEGER NOT NULL AUTO_INCREMENT, " +
            ARENA_CHARACTER_ID + " INTEGER NOT NULL, " + ARENA_ENEMY_ID + " INTEGER NOT NULL, hp INTEGER NOT NULL, result TEXT, PRIMARY KEY(" + ID + "))";

    // Figthing Table
    public static final String CREATE_TABLE_FIGHT = "CREATE TABLE IF NOT EXISTS Fight (_id INTEGER NOT NULL AUTO_INCREMENT, " +
            "char_id INTEGER NOT NULL, enemy_id INTEGER NOT NULL, attack TEXT NOT NULL, defend TEXT NOT NULL, hp_id INTEGER NOT NULL," +
            "PRIMARY KEY (_id)) ";

    public static final String INSERT_ITEM_TO_INVENTORY = "UPDATE " + TABLE_INVENTORY + " SET item_name" + "?" + " = ? WHERE user_id = ? AND character_id = ?";
}
