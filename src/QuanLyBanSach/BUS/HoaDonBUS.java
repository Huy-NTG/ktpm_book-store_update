package QuanLyBanSach.BUS;

import MyCustom.MyDialog;
import QuanLyBanSach.DAO.HoaDonDAO;
import QuanLyBanSach.DTO.HoaDon;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class HoaDonBUS {

    private ArrayList<HoaDon> listHoaDon;
    private HoaDonDAO hoaDonDAO = new HoaDonDAO();

    public ArrayList<HoaDon> getListHoaDon() {
        listHoaDon = hoaDonDAO.getListHoaDon();
        return listHoaDon;
    }

    public void luuHoaDon(String maKH, String maNV, String tongTien, String ghiChu) {
    	int makh = Integer.parseInt(maKH);
    	int manv = Integer.parseInt(maNV);
    	int tt = Integer.parseInt(tongTien);
        HoaDon hd = new HoaDon();
        hd.setMaNV(manv);
        hd.setMaKH(makh);
        hd.setGhiChu(ghiChu);
        hd.setTongTien(tt);

        hoaDonDAO.addHoaDon(hd);
    }

    public int getMaHoaDonMoiNhat() {
        return hoaDonDAO.getMaHoaDonMoiNhat();
    }

    public HoaDon getHoaDon(String maHD) {
        int ma = Integer.parseInt(maHD);
        for (HoaDon hd : listHoaDon) {
            if (hd.getMaHD() == ma)
                return hd;
        }
        return null;
    }

    public ArrayList<HoaDon> getListHoaDonTheoGia(String min, String max) {
        try {
            int minPrice = Integer.parseInt(min);
            int maxPrice = Integer.parseInt(max);
            if (minPrice > maxPrice) {
                new MyDialog("Hãy nhập khoảng giá phù hợp!", MyDialog.ERROR_DIALOG);
                return null;
            }
            ArrayList<HoaDon> dshd = new ArrayList<>();
            for (HoaDon hd : listHoaDon) {
                if (hd.getTongTien() >= minPrice && hd.getTongTien() <= maxPrice)
                    dshd.add(hd);
            }
            return dshd;
        } catch (Exception e) {
            new MyDialog("Hãy nhập khoảng giá hợp lệ", MyDialog.ERROR_DIALOG);
        }
        return null;
    }

    public ArrayList<HoaDon> getListHoaDonTheoNgay(Date min, Date max) {
        try {
            java.sql.Date ngayBD = new java.sql.Date(min.getTime());
            java.sql.Date ngayKT = new java.sql.Date(max.getTime());
            ArrayList<HoaDon> dshd = hoaDonDAO.getListHoaDon(ngayBD,ngayKT);
            return dshd;
        } catch (Exception e) {
            new MyDialog("Hãy nhập khoảng ngày hợp lệ!", MyDialog.ERROR_DIALOG);
        }
        return null;
    }
}
