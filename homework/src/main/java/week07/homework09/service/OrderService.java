package week07.homework09.service;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

public interface OrderService {
    void insertOne(DataSource dataSource, String sql) throws Exception;
    List<Map<String, Object>> query(DataSource dataSource, String sql) throws Exception;
}
