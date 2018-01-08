package br.com.seplag.util;


import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;

import javax.enterprise.inject.Vetoed;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.ws.rs.core.Response;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.operation.DatabaseOperation;
import org.jboss.arquillian.persistence.dbunit.dataset.json.JsonDataSet;

@Vetoed
public class DBUnitUtils {

    private static DataSource ds;

    private static DatabaseConnection databaseConnection;

    public static void createDataset(String dataset) {

        if (!dataset.startsWith("/")) {
            dataset = "/" + dataset;
        }
        try {
            initConn();
            DatabaseOperation.CLEAN_INSERT.execute(databaseConnection, new JsonDataSet(DBUnitUtils.class.getResourceAsStream(dataset)));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(String.format("Não foi possível inicializar o dataset: %s. Messagem: %s.", dataset, e.getMessage()));
        } finally {
            closeConn();
        }
    }

    public static void deleteDataset(String dataset) {
        if (!dataset.startsWith("/")) {
            dataset = "/" + dataset;
        }
        try {
            initConn();
            DatabaseOperation.DELETE_ALL.execute(databaseConnection, new JsonDataSet(DBUnitUtils.class.getResourceAsStream(dataset)));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(String.format("Não foi possível remover o dataset: %s. Messagem: %s.", dataset, e.getMessage()));
        } finally {
            closeConn();
        }
    }

    private static void closeConn() {
        try {
            if (databaseConnection != null && !databaseConnection.getConnection().isClosed()) {
                databaseConnection.getConnection().close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(String.format("Não foi possível fechar a conexão. Messagem: %s.", e.getMessage()));
        }

    }

    private static void initConn() throws SQLException, NamingException, DatabaseUnitException {
        if (ds == null) {
            ds = (DataSource) new InitialContext().lookup("java:jboss/datasources/SeplagTestDS");
        }
        databaseConnection = new DatabaseConnection(ds.getConnection());
    }

    public static void createRemoteDataset(URL context, String dataset) {
        fireRequest("create", context, dataset);
    }

    public static void deleteRemoteDataset(URL context, String dataset) {
        fireRequest("delete", context, dataset);
    }

    private static void fireRequest(String path, URL context, String dataset) {
        HttpURLConnection con = null;

        try {
            URL obj = new URL(context + "api/database/" + path + "/" + dataset);

            con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setDoOutput(true);

            int responseCode = con.getResponseCode();

            if (responseCode != Response.Status.OK.getStatusCode()) {
                throw new RuntimeException(String.format("Não foi possível remover o dataset remoto. Status: %s. Erro: %s.", responseCode, con.getResponseMessage()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }
    }
}
