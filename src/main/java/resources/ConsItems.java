package resources;

import java.util.HashMap;

import static resources.ConsTables.*;


/**
 * Constance class for Weapons
 */
public class ConsItems {

    // --- Insertion ---

    //  Races
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

    public static final HashMap<String, String> raceInsertion = new HashMap<String, String>(){
        {
            put("humanRace", INSERT_RACE_HUMAN);
            put("elfRace", INSERT_RACE_ELF);
            put("dwarfRace", INSERT_RACE_DWARF);
            put("unleadRace", INSERT_RACE_UNDEAD);
            put("orcRace", INSERT_RACE_ORC);
        }
    };

    // Roles
    public static final String INSERT_ROLE_WIZZARD = "INSERT IGNORE INTO " + TABLE_ROLES + "(" + ROLES_ROLE + ") VALUES('wizzard')";
    public static final String INSERT_ROLE_FIGHTER = "INSERT IGNORE INTO " + TABLE_ROLES + "(" + ROLES_ROLE + ") VALUES('fighter')";
    public static final String INSERT_ROLE_PALADIN = "INSERT IGNORE INTO " + TABLE_ROLES + "(" + ROLES_ROLE + ") VALUES('paladin')";
    public static final String INSERT_ROLE_ROGUE = "INSERT IGNORE INTO " + TABLE_ROLES + "(" + ROLES_ROLE + ") VALUES('rogue')";

    public static final HashMap<String, String> rolesInsertion = new HashMap<String, String>(){
        {
            put("wizzardRole", INSERT_ROLE_WIZZARD);
            put("fighterRole", INSERT_ROLE_FIGHTER);
            put("paladinRole", INSERT_ROLE_PALADIN);
            put("rogueRole", INSERT_ROLE_ROGUE);
        }
    };

    // Left Hand Items Set
    public static final String INSERT_SET_LEFT_HAND = "INSERT IGNORE INTO " + TABLE_LEFTHAND + "(" + ITEM_NAME + ", " +
            PRICE + ", " + ATTRIBUTE_ID + ") VALUES" +
            // (item anme, price, attr_id)
            "('empty', 0, 1)," +
            "('club', 5, 2)," +
            "('dagger', 10, 3)," +
            "('short sword', 20, 4)," +
            "('long sword', 25, 5)," +
            "('mace', 20, 6)," +
            "('scimitar', 25, 7)," +
            "('two-handed sword', 35,8)," +
            "('shield', 10, 9)," +
            "('whip', 30, 10)";

    // Right Hand Items Set
    public static final String INSERT_SET_RIGHT_HAND = "INSERT IGNORE INTO " + TABLE_RIGHTHAND + "(" + ITEM_NAME + ", " +
            PRICE + ", " + ATTRIBUTE_ID + ") VALUES" +
            // (item anme, price, attr_id)
            "('empty', 0, 1)," +
            "('club', 5, 2)," +
            "('dagger', 10, 3)," +
            "('short sword', 20, 4)," +
            "('long sword', 25, 5)," +
            "('mace', 20, 6)," +
            "('scimitar', 25, 7)," +
            "('two-handed sword', 35,8)," +
            "('shield', 10, 9)," +
            "('whip', 30, 10)";

    // Legs Items Set
    public static final String INSERT_SET_LEGS = "INSERT IGNORE INTO " + TABLE_LEGS + "(" + ITEM_NAME + ", " +
            PRICE + ", " + ATTRIBUTE_ID + ") VALUES" +
            // (item anme, price, attr_id)
            "('peasant trousers', 0, 1)," +
            "('spiked trousers', 5, 20)," +
            "('sexy dress', 1, 21)," +
            "('leggings', 8, 22)," +
            "('medium platelegs', 15, 23)," +
            "('heavy platelegs', 20, 24)," +
            "('dragonhide legs', 30, 25)," +
            "('legs of speed', 15, 26)," +
            "('wizzard dress', 15, 27)," +
            "('future item', 0, 28)";

    // Torso Items Set
    public static final String INSERT_SET_TORSO = "INSERT IGNORE INTO " + TABLE_TORSO + "(" + ITEM_NAME + ", " +
            PRICE + ", " + ATTRIBUTE_ID + ") VALUES" +
            // (item anme, price, attr_id)
            "('peasant shirt', 0, 1)," +
            "('lab coat', 10, 30)," +
            "('spiked shirt', 5, 31)," +
            "('medium plate', 20, 22)," +
            "('heavy plate', 40, 33)," +
            "('dragonhide armor', 60, 34)," +
            "('slough of anaconda', 35, 35)," +
            "('wizzard top robes', 25, 36)," +
            "('future item', 0, 28)," +
            "('future item', 0, 29)";

    // Attributes
    public static final String INSERT_ATTRIBUTES = "INSERT IGNORE INTO " + TABLE_ATTRIBUTES + "(" + ID + ", " + STRENGTH +
            ", " + AGILITY + ", " + INTELLIGENCE + ", " + DEFENSE + ", " + HITPOINTS + ") VALUES" +
            // (strength, agility, ingelligence, defence, hitpoints)
            "(1, 0, 0, 0, 0, 0)," +    // Default
            // --> Hands Attributes
            "(2, 3, -1, -1, 0, 0)," +  // Club
            "(3, 1, 3, 0, 0, 0)," +    // Dagger
            "(4, 2, 2, 0, 0, 0)," +    // Short sword
            "(5, 3, 1, 0, 0, 0)," +    // Long sword
            "(6, 7, -1, 0, 0, 0)," +   // Mace
            "(7, 2, 2, 0, 0, 0)," +    // Scimitar
            "(8, 10, -3, 0, -2, 0)," + // 2h sword
            "(9, 0, -3, 0, 5, 0)," +   // Shield
            "(10, 4, 4, 0, 0, 0)," +    // Whip
            "(11, 0, 0, 5, 0, 0)," +    // Magic staff
            // --> Legs Attributes
            "(12, 0, 3, 0, 1, 0)," +    // Spiked trousers
            "(13, 0, 1, 0, 0, 0)," +    // Sexy dress
            "(14, 0, 5, 0, 2, 0)," +    // Leggings
            "(15, 0, -1, 0, 4, 0)," +   // Medium platelegs
            "(16, 0, -3, 0, 7, 0)," +   // Heavy platelegs
            "(17, 0, 5, 0, 5, 0)," +    // Dragonhide legs
            "(18, 0, 8, 0, 1, 0)," +    // Legs of speed
            "(19, 0, 1, 3, 1, 0)," +    // Wizard dress
            // --> Torso Attributes
            "(20, 0, 1, 5, 1, 0)," +    // Lab coat
            "(21, 0, 1, 0, 3, 0)," +    // Spiked shirt
            "(22, 0, -2, 0, 6, 0)," +   // Medium plate
            "(23, 0, -4, 0, 10, 0)," +  // Heavy plate
            "(24, 0, 5, 0, 7, 0)," +    // Dragonhide armor
            "(25, 0, 5, 0, 5, 0)," +    // Slough of anaconda
            "(26, 0, 0, 7, 1, 0)";     // Wizard top robes

    // Messages
    public static final String INSERT_MSG = "INSERT INTO " + TABLE_MESSAGES + "(" + MESSAGES_CHARACTER_ID + ", " + MESSAGES_TIME + ", " + MESSAGES_TEXT + ") VALUES(?,?,?)";

    // User_id and character_id to inventory table
    public static final String INSERT_TO_INVENTORY = "INSERT INTO " + TABLE_INVENTORY + "(" + SESSIONS_USER_ID + ", " + USERS_CHARACTER_ID +
            ") VALUES (?,?)";

    // Insert Item to Inventory table
    public static final String INSERT_ITEM_TO_INVENTORY = "UPDATE " + TABLE_INVENTORY + " SET item_name" + "?" + " = ? WHERE user_id = ? AND character_id = ?";


    // --- Selection ---

    // Messages
    public static final String SELECT_MESSAGES = "SELECT * FROM " + TABLE_MESSAGES;

    /** Left & Right Hand's Items Set defined in String Array */
    public static String[] handSet = {"empty", "club", "dugger", "short sword", "long sword", "mace", "scimitar", "two-handed sword", "shield", "whip"};


}
