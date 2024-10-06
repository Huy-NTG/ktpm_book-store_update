package QuanLyBanSach.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import QuanLyBanSach.DAO.MyConnect;
import QuanLyBanSach.DTO.KhuyenMai;
import java.text.SimpleDateFormat;

public class KhuyenMaiDAO {
	public ArrayList<KhuyenMai> getDanhSachKhuyenMai() {
        try {
            String sql = "SELECT * FROM khuyenmai";
            Statement st = MyConnect.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            ArrayList<KhuyenMai> dskm = new ArrayList<>();
            while (rs.next()) {
                KhuyenMai km = new KhuyenMai();
                km.setMaKM(rs.getInt(1));
                km.setTenKM(rs.getString(2));               
                km.setNgayBD(rs.getDate(3));
                km.setNgayKT(rs.getDate(4));
                dskm.add(km);
            }
            return dskm;
        } catch (Exception e) {
        }
        return null;
    }

    public boolean themKhuyenMai1(KhuyenMai km) {
        try {
            String sql = "INSERT INTO khuyenmai(tenKM, NgayBD, NgayKT) VALUES (N?, ?, ?)";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setString(1, km.getTenKM());
            pre.setString(2,new SimpleDateFormat("yyyy-MM-dd").format(km.getNgayBD()));
            pre.setString(3,new SimpleDateFormat("yyyy-MM-dd").format(km.getNgayKT()));
            return pre.executeUpdate()>0;
        } catch (Exception e) {
        }
        return false;
    }

    public boolean themKhuyenMai(KhuyenMai km) {
        try {
            String sql = "INSERT INTO khuyenmai( tenKM, NgayBD, NgayKT) " +
                    "VALUES ( ?, ?, ?)";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            //pre.setInt(1, km.getMaKM());
            pre.setString(1, km.getTenKM());           
            pre.setTimestamp(2, new java.sql.Timestamp(km.getNgayBD().getTime()));
            pre.setTimestamp(3, new java.sql.Timestamp(km.getNgayKT().getTime()));

            return pre.executeUpdate()>0;
        } catch (Exception e) {
        }
        return false;
    }
    
    public boolean themKhuyenMai2(KhuyenMai km) {
        try {
            String sql = "INSERT INTO khuyenmai(maKM, tenKM, NgayBD, NgayKT) " +
                    "VALUES (?, ?, ?, ?)";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setInt(1, km.getMaKM());
            pre.setString(1, km.getTenKM());           
            pre.setTimestamp(2, new java.sql.Timestamp(km.getNgayBD().getTime()));
            pre.setTimestamp(3, new java.sql.Timestamp(km.getNgayKT().getTime()));

            return pre.executeUpdate() > 0;
        } catch (Exception e) {
        }
        return false;
    }
    
    public boolean suaKhuyenMai(KhuyenMai km) {
        try {
            String sql = "UPDATE khuyenmai SET maKM=?, tenKM=?, NgayBD=?, NgayKT=? WHERE maKM=?";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setInt(1, km.getMaKM());
            pre.setString(2, km.getTenKM());          

            pre.setTimestamp(3, new java.sql.Timestamp(km.getNgayBD().getTime()));
            pre.setTimestamp(4, new java.sql.Timestamp(km.getNgayKT().getTime()));

            pre.setInt(5, km.getMaKM());
            return pre.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean xoaKhuyenMai(int ma) {
    	try {
            String sql = "DELETE FROM khuyenmai WHERE maKM='" + ma+"'";
            Statement st = MyConnect.conn.createStatement();
            int x = st.executeUpdate(sql);
            return x > 0 ? true : false;
        } catch (SQLException e) {
        }
        return false;
    }
}
