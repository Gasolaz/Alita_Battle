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
    }
}
