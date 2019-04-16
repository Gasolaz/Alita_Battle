package dao;

import interfaces.ITablesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.*;

import static resources.ConsTables.*;
import static resources.ConsItems.*;


public class TablesDao implements ITablesDao {

    JdbcTemplate tablesTemplate;

    @Autowired
    DataSource dataSource;

    public void setTablesTemplate(JdbcTemplate tablesTemplate) { this.tablesTemplate = tablesTemplate; }

    /** Creates tables in database Alita_battle if those doesn't exist */
    public void createTables() {

        // Tables creation in the database 'Alita_battle'
        for(String tablesCreate : tablesCreation.values())
            tablesTemplate.execute(tablesCreate);

        // Races insertion into the table 'Races'
        for(String raceInsert : raceInsertion.values())
            tablesTemplate.execute(raceInsert);

        // Roles insertion into the table 'Roles'
        for(String roleInsert : rolesInsertion.values())
            tablesTemplate.execute(roleInsert);

        tablesTemplate.execute(INSERT_SET_LEFT_HAND);
        tablesTemplate.execute(INSERT_SET_RIGHT_HAND);
        tablesTemplate.execute(INSERT_SET_LEGS);
        tablesTemplate.execute(INSERT_SET_TORSO);
        tablesTemplate.execute(INSERT_ATTRIBUTES);
    }
}
