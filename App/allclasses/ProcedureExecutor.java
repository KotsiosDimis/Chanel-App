package Procudures;

import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.SQLException;

import Connection.DBConnectionUtil;

public class ProcedureExecutor implements IProcedureExecutor {

    private static final String CALL_PREFIX = "{call ";
    
   
    public static void executeProcedure(String procedureCall, Object... parameters) {
        String sqlQuery = CALL_PREFIX + procedureCall + "}";
        try (Connection connection = DBConnectionUtil.getConnection();
             CallableStatement statement = connection.prepareCall(sqlQuery)) {

            // Set parameters
            for (int i = 0; i < parameters.length; i++) {
                statement.setObject(i + 1, parameters[i]);
            }

            // Execute Query
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
