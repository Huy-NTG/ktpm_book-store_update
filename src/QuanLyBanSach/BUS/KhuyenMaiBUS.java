package QuanLyBanSach.BUS;

import java.util.ArrayList;
import java.util.Date;
import java.time.LocalDate;
import javax.swing.JOptionPane;

import MyCustom.MyDialog;
import QuanLyBanSach.DAO.KhuyenMaiDAO;
import QuanLyBanSach.DTO.KhuyenMai;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KhuyenMaiBUS {
    private ArrayList<KhuyenMai> listKhuyenMai = null;
    private KhuyenMaiDAO KMDAO = new KhuyenMaiDAO();
    private LocalDate now=LocalDate.now();
    private Date daynow;
    public KhuyenMaiBUS() {
        docDanhSach();
        try {
            daynow=new SimpleDateFormat("yyyy-MM-dd").parse(now.toString());
        } catch (ParseException ex) {
            Logger.getLogger(KhuyenMaiBUS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void docDanhSach() {
        this.listKhuyenMai = KMDAO.getDanhSachKhuyenMai();
        
    }

    public ArrayList<KhuyenMai> getDanhSachKhuyenMai() {
        if (this.listKhuyenMai == null)
            docDanhSach();
        return this.listKhuyenMai;
    }

    public boolean themKhuyenMai(String ten, Date ngayBD, Date ngayKT) {
        if (ten.equals("")) {
            new MyDialog("Không được để trống tên chương trình khuyến mãi!", MyDialog.ERROR_DIALOG);
            return false;
        }
        
        if (ngayBD.compareTo(daynow)<0|| ngayBD.compareTo(daynow) == 0) {
            new MyDialog("Ngày bắt đầu phải lớn hơn ngày hiện tại", MyDialog.ERROR_DIALOG);
            return false;
        }
        
        if (ngayBD.compareTo(ngayKT) > 0 || ngayBD.compareTo(ngayKT) == 0) {
            new MyDialog("Ngày kết thúc phải lớn hơn ngày bắt đầu!", MyDialog.ERROR_DIALOG);
            return false;
        }
        boolean flag = false;
        try {           
            KhuyenMai km = new KhuyenMai();
            km.setTenKM(ten);
            km.setNgayBD(ngayBD);
            km.setNgayKT(ngayKT);
            flag = KMDAO.themKhuyenMai(km);
        } catch (Exception e) { 
            new MyDialog("Thêm mới thất bại!", MyDialog.ERROR_DIALOG);
            return false;
        }
        if (flag) {
            new MyDialog("Thêm mới thành công!", MyDialog.SUCCESS_DIALOG);
        } else {
            new MyDialog("Thêm mới thất bại!", MyDialog.ERROR_DIALOG);
        }
        return flag;
    }
    public boolean themKhuyenMai1(int ma, String ten, Date ngayBD, Date ngayKT) {
        if (ten.equals("")) {
            new MyDialog("Hãy nhập tên chương trình khuyến mãi!", MyDialog.ERROR_DIALOG);
            return false;
        }
        if (ngayBD.compareTo(ngayKT) > 0 || ngayBD.compareTo(ngayKT) == 0) {
            new MyDialog("Ngày kết thúc không hợp lệ!", MyDialog.ERROR_DIALOG);
            return false;
        }
        boolean flag = false;
        try {           
            KhuyenMai km = new KhuyenMai();
            km.setMaKM(ma);
            km.setTenKM(ten);
            km.setNgayBD(ngayBD);
            km.setNgayKT(ngayKT);

            flag = KMDAO.themKhuyenMai1(km);
        } catch (Exception e) {            
            return false;
        }
        if (flag) {
            new MyDialog("Thêm mới thành công!", MyDialog.SUCCESS_DIALOG);
        } else {
            new MyDialog("Thêm mới thất bại!", MyDialog.ERROR_DIALOG);
        }
        return flag;
    }
    public boolean suaKhuyenMai(String ma, String ten, Date ngayBD, Date ngayKT) {     
        if (ma.equals("")) {
            new MyDialog("Chưa chọn mã để sửa!", MyDialog.ERROR_DIALOG);
            return false;
        }
        if (ten.equals("")) {
            new MyDialog("Không được để trống tên chương trình khuyến mãi!", MyDialog.ERROR_DIALOG);
            return false;
        }
        Date nowday= new Date();
        if (ngayBD.compareTo(nowday)<0|| ngayBD.compareTo(nowday) == 0) {
            new MyDialog("Ngày bắt đầu phải lớn hơn ngày hiện tại", MyDialog.ERROR_DIALOG);
            return false;
        }
        
        if (ngayBD.compareTo(ngayKT) > 0 || ngayBD.compareTo(ngayKT) == 0) {
            new MyDialog("Ngày kết thúc phải lớn hơn ngày bắt đầu!", MyDialog.ERROR_DIALOG);
            return false;
        }
        boolean flag = false;
        try {
            int maKM = Integer.parseInt(ma);            
            KhuyenMai km = new KhuyenMai();
            km.setMaKM(maKM);
            km.setTenKM(ten);           
            km.setNgayBD(ngayBD);
            km.setNgayKT(ngayKT);

            flag = KMDAO.suaKhuyenMai(km);
        } catch (Exception e) {
            new MyDialog("Thông nhập chưa hợp lệ!", MyDialog.ERROR_DIALOG);
            return false;
        }
        if (flag) {
            new MyDialog("Sửa thành công!", MyDialog.SUCCESS_DIALOG);
        } else {
            new MyDialog("Sửa thất bại!", MyDialog.ERROR_DIALOG);
        }
        return flag;
    }
    public boolean xoaKhuyenMai(String ma) {
    	if (ma.trim().equals("")) {
			JOptionPane.showMessageDialog(null, "Chưa chọn loại để xóa !");
			return false;
		}
		int maKM = Integer.parseInt(ma);
		if (KMDAO.xoaKhuyenMai(maKM)) {
			JOptionPane.showMessageDialog(null, "Xóa thành công !");
			return true;
		} else {
			JOptionPane.showMessageDialog(null, "Xóa thất bại !");
			return false;
		}
    }
}
