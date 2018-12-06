import javax.swing.*;
import java.sql.*;

public class DBConnection {
    private String driver = "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://210.70.80.21:3306/s106021050?useUnicode=true&characterEncoding=utf8";
    private Connection dbConn;
    private MainFrame frm;
    public DBConnection(MainFrame frm1, String id, String pw){
        frm = frm1;
        try{
            Class.forName(driver);
            dbConn = DriverManager.getConnection(url,id,pw);
            JOptionPane.showMessageDialog(frm,"資料庫連結成功");
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(frm,ex.toString());
        }catch (Exception ex){
            JOptionPane.showMessageDialog(frm,ex.toString());
        }
    }
    public ResultSet getData(){
        ResultSet rs = null;
        try{
            Statement stm = dbConn.createStatement();
            String sqlStr = "select * from student";
            rs = stm.executeQuery(sqlStr);
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(frm,ex.toString());
        }
        return rs;
    }
}
