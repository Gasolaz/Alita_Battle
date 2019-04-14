package resources;

import com.sun.tools.javac.jvm.Items;

import static resources.Cons.*;


/**
 * Constance class for Weapons
 */
public class ConsItems {

    /** Left & Right Hand's Items Set defined in String Array */
    public static String[] handSet = {"empty", "club", "dugger", "short sword", "long sword", "mace", "scimitar", "two-handed sword", "shield", "whip"};

    // Items - 1st hand, 2nd hand, legs, torso
    public static final String INSERT_SET_LEFT_HAND = "INSERT IGNORE INTO " + TABLE_LEFTHAND + "(" + ID + ", " + ITEM_NAME + ", " +
            PRICE + ", " + ATTRIBUTE_ID + ") VALUES(?,?,?,?)";
    public static final String INSERT_SET_RIGHT_HAND = "INSERT IGNORE INTO " + TABLE_RIGHTHAND + "(" + ID + ", " + ITEM_NAME + ", " +
            PRICE + ", " + ATTRIBUTE_ID + ") VALUES(?,?,?,?)";
    public static final String INSERT_LEGS = "INSERT IGNORE INTO " + TABLE_LEGS + "(" + ID + ", " + ITEM_NAME + ", " +
            PRICE + ", " + ATTRIBUTE_ID + ") VALUES(?,?,?,?)";
    public static final String INSERT_TORSO = "INSERT IGNORE INTO " + TABLE_TORSO + "(" + ID + ", " + ITEM_NAME + ", " +
            PRICE + ", " + ATTRIBUTE_ID + ") VALUES(?,?,?,?)";

    // Items for Left Hand
    //public static final String = "";
}
