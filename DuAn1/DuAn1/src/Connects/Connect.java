package Connects;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Connect {
    public Connection con() throws SQLServerException {


        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setUser("sa");
        ds.setPassword("123");
        ds.setDatabaseName("THUVIEN_DuAn1");
        ds.setServerName("DESKTOP-AGDQUPT\\SQLEXPRESS");
        ds.setPortNumber(1433);
        Connection con = ds.getConnection();
        return con;


    }

}
