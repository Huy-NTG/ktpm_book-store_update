package QuanLyBanSach.DAO;
import MyCustom.MyDialog;
import java.sql.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestConnect {
    
    public static Connection conn = null;
    private String severName="localhost:81";
    private String dbName="quanlynhasach";
    private String userName="root";
    private String password="";
    
    public TestConnect() {
    	
    	//docFileText();
    	
        String url = "jdbc:mysql://" + severName + "/" + dbName+"?useUnicode=true&characterEncoding=utf8";
        Properties pro = new Properties();
        pro.put("user", userName);
        pro.put("password", password);
        try {
            conn =DriverManager.getConnection(url, pro);
            PreparedStatement pre=conn.prepareStatement("SELECT * FROM sanpham");
            ResultSet rs=pre.executeQuery();
            while(rs.next()){
                System.out.print(rs.getInt(1));
            }
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            new MyDialog("Không kết nối được tới CSDL!", MyDialog.ERROR_DIALOG);
            System.exit(0);
        }
    }

    public TestConnect(String severName, String dbName, String userName, String password) {
        this.severName = severName;
        this.dbName = dbName;
        this.userName = userName;
        this.password = password;
    }
    

	private void docFileText() {
		
		severName = "";
		dbName = "";
		userName = "";
		password = "";

		try {
			FileInputStream fis = new FileInputStream("Connect.txt");
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(isr);

			severName = br.readLine();
			dbName = br.readLine();
			userName = br.readLine();
			password = br.readLine();

			if (password == null) {
				password = "";
			}

		} catch (Exception e) {
		}
	}
        public static void main(String[]args){
            new TestConnect();
        }
}