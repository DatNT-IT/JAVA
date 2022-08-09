import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


public class a {

    public static void main(String[] args) {
        SQLServerDataSource ds = new SQLServerDataSource();
       ds.setUser("sa");
        ds.setPassword("123");
        ds.setServerName("DESKTOP-PFMTEUO\\SQLEXPRESS");
        ds.setPortNumber(1433);
        ds.setDatabaseName("ASSJAVA3");
        try {
            Connection con = ds.getConnection();
            String sql ="SELECT hoten,tienganh,tinhoc,gdtc FROM dbo.grade JOIN dbo.students ON students.masv = grade.masv WHERE grade.masv = 'ph1'";
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()){
                System.out.println(rs.getString(1)+rs.getString(2)+rs.getString(3)+rs.getString(4));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
