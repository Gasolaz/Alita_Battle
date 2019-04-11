package interfaces;

import org.springframework.jdbc.core.JdbcTemplate;

public interface ITablesDao {

    void setTablesTemplate(JdbcTemplate tablesTemplate);
    void createTables();
}
