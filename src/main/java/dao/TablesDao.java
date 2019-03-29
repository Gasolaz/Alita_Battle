package dao;

import org.springframework.jdbc.core.JdbcTemplate;

import static resources.Cons.*;

public class TablesDao {
    JdbcTemplate tablesTemplate;

    public void setTablesTemplate(JdbcTemplate tablesTemplate) {
        this.tablesTemplate = tablesTemplate;
    }

    public void createTables(){
        tablesTemplate.execute(CREATE_TABLE_CHARACTERS);
        tablesTemplate.execute(CREATE_TABLE_INVENTORY);
        tablesTemplate.execute(CREATE_TABLE_RACES);
        tablesTemplate.execute(CREATE_TABLE_SESSIONS);
        tablesTemplate.execute(CREATE_TABLE_USERS);
        tablesTemplate.execute(CREATE_TABLE_ROLES);
        tablesTemplate.execute(CREATE_TABLE_CHALLENGES);
        tablesTemplate.execute(CREATE_TABLE_MESSAGES);
        tablesTemplate.execute(CREATE_TABLE_ITEM_SETS);
        tablesTemplate.execute(CREATE_TABLE_TORSO);
        tablesTemplate.execute(CREATE_TABLE_LEGS);
        tablesTemplate.execute(CREATE_TABLE_FIRST_HAND);
        tablesTemplate.execute(CREATE_TABLE_SECOND_HAND);
        tablesTemplate.execute(CREATE_TABLE_ATTRIBUTES);

        tablesTemplate.execute(INSERT_RACE_DWARF);
        tablesTemplate.execute(INSERT_RACE_ELF);
        tablesTemplate.execute(INSERT_RACE_HUMAN);
        tablesTemplate.execute(INSERT_RACE_ORC);
        tablesTemplate.execute(INSERT_RACE_UNDEAD);

        tablesTemplate.execute(INSERT_ROLE_FIGHTER);
        tablesTemplate.execute(INSERT_ROLE_WIZARD);
        tablesTemplate.execute(INSERT_ROLE_PALADIN);
        tablesTemplate.execute(INSERT_ROLE_ROGUE);
    }
}
