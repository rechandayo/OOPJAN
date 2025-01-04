package database;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.Statement;

public class DatabaseInitializer {

    public static void initializeDatabase(Connection connection) {
        try {
            InputStream inputStream = DatabaseInitializer.class.getResourceAsStream("/com/example/schema.sql");
            if (inputStream == null) {
                throw new FileNotFoundException("schema.sql not found in resources!");
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            StringBuilder sql = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                sql.append(line).append("\n");
            }

            String[] statements = sql.toString().split(";");
            try (Statement stmt = connection.createStatement()) {
                for (String statement : statements) {
                    if (!statement.trim().isEmpty()) {
                        stmt.execute(statement);
                    }
                }
            }

            System.out.println("Database initialized successfully.");
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println("Failed to initialize database.");
            e.printStackTrace();
        }
    }
}
