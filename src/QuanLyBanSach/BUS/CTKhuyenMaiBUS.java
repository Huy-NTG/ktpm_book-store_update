package QuanLyBanSach.BUS;

import java.util.ArrayList;
import java.util.Date;



import MyCustom.MyDialog;
import MyCustom.XulyInput;
import QuanLyBanSach.DAO.CTKhuyenMaiDAO;
import QuanLyBanSach.DAO.KhuyenMaiDAO;
import QuanLyBanSach.DTO.CTKhuyenMai;
import QuanLyBanSach.DTO.KhuyenMai;

public class CTKhuyenMaiBUS {
    private ArrayList<CTKhuyenMai> listCTKhuyenMai = null;
    private CTKhuyenMaiDAO CTKMDAO = new CTKhuyenMaiDAO();
    private XulyInput xulyinput= new XulyInput();

    public CTKhuyenMaiBUS() {
        docDanhSach();
    }

    public void docDanhSach() {
        this.listCTKhuyenMai = CTKMDAO.getDanhSachCTKhuyenMai();
    }

    public ArrayList<CTKhuyenMai> getDanhSachKhuyenMai() {
        if (this.listCTKhuyenMai == null)
            docDanhSach();
        return this.listCTKhuyenMai;
    }

    public boolean themCTKhuyenMai(String maKM, String maSP, String phantramgiam, String dieukien) {
        if (maKM.equals("")) {
            new MyDialog("Vui lòng chọn khuyến mãi từ bảng bên trái!", MyDialog.ERROR_DIALOG);
            return false;
        }
        if (phantramgiam.equals("")) {
            new MyDialog("Không được bỏ trống phần trăm giảm giá.\nVui lòng nhập phần trăm giảm giá!", MyDialog.ERROR_DIALOG);
            return false;
        }
        if (dieukien.equals("")) {
            new MyDialog("Không được bỏ trống điều kiện.\nVui lòng nhập số tiền là điều kiện tối thiểu của hóa đơn cần đạt để được khuyến mãi!", MyDialog.ERROR_DIALOG);
            return false;
        }
        if (maSP.equals("Chọn SP")) {
            new MyDialog("Chưa lựa chọn sản phẩm.\nVui lòng chọn một sản phẩm từ danh sách!", MyDialog.ERROR_DIALOG);
            return false;
        }
        
        if(!xulyinput.dataso(phantramgiam)){
            new MyDialog("Phần trăm giảm phải là số nguyên, vui lòng kiểm tra lại!", MyDialog.ERROR_DIALOG);
            return false;
        }
        
        if(!xulyinput.dataso(dieukien)){
            new MyDialog("Điều kiện hóa đơn phải là số nguyên, vui lòng kiểm tra lại!", MyDialog.ERROR_DIALOG);
            return false;
        }
        
        int mact = Integer.parseInt(maKM);
        String[] spTmp = maSP.split(" - ");
        int masp = Integer.parseInt(spTmp[0]);
        int pt =  Integer.parseInt(phantramgiam);
        int dk = Integer.parseInt(dieukien);
        
        if(pt<1 || pt>100){
            new MyDialog("Số đã nhập không hợp lệ. Phần trăm giảm phải lớn hơn 0 và tối đa là 100", MyDialog.ERROR_DIALOG);
            return false;
        }
        
        if(dk<20000){
            new MyDialog("Số đã nhập không hợp lệ. Yêu cầu: Hóa đơn mua tối thiểu là 20.000 VND", MyDialog.ERROR_DIALOG);
            return false;
        }else if(dk>10000000){
            new MyDialog("Số đã nhập quá lớn, vui lòng kiểm tra lại. Yêu cầu: Hóa đơn mua tối đa là 10.000.000 VND", MyDialog.ERROR_DIALOG);
            return false;
        }
        
        boolean flag = false;
        try {           
            CTKhuyenMai ctkm = new CTKhuyenMai();
            ctkm.setMaKM(mact);
            ctkm.setMaSP(masp);
            ctkm.setPhanTramGiam(pt);     
            ctkm.setDieuKien(dk);                 

            flag = CTKMDAO.themCTKhuyenMai(ctkm);
        } catch (Exception e) {            
            return false;
        }
        if (flag) {
            new MyDialog("Thêm mới thành công!", MyDialog.SUCCESS_DIALOG);
        } else {
            new MyDialog("Thêm mới thất bại, vui lòng kiểm tra lại thông tin hoặc liên hệ Admin để được hỗ trợ!", MyDialog.ERROR_DIALOG);
        }
        return flag;
    }

    public boolean suaCTKhuyenMai(String maKM, String maSP, String phantramgiam,  String dieukien ) {     
    	if (maKM.equals("")) {
            new MyDialog("Vui lòng chọn khuyến mãi từ bảng bên trái!", MyDialog.ERROR_DIALOG);
            return false;
        }
        if (phantramgiam.equals("")) {
            new MyDialog("Không được bỏ trống phần trăm giảm giá.\nVui lòng nhập phần trăm giảm giá!", MyDialog.ERROR_DIALOG);
            return false;
        }
        if (dieukien.equals("")) {
            new MyDialog("Không được bỏ trống điều kiện.\nVui lòng nhập số tiền là điều kiện tối thiểu của hóa đơn cần đạt để được khuyến mãi!", MyDialog.ERROR_DIALOG);
            return false;
        }
        if (maSP.equals("Chọn SP")) {
            new MyDialog("Chưa lựa chọn sản phẩm.\nVui lòng chọn một sản phẩm từ danh sách!", MyDialog.ERROR_DIALOG);
            return false;
        }
        
        if(!xulyinput.dataso(phantramgiam)){
            new MyDialog("Phần trăm giảm phải là số nguyên, vui lòng kiểm tra lại!", MyDialog.ERROR_DIALOG);
            return false;
        }
        
        if(!xulyinput.dataso(dieukien)){
            new MyDialog("Điều kiện hóa đơn phải là số nguyên, vui lòng kiểm tra lại!", MyDialog.ERROR_DIALOG);
            return false;
        }
        
        int makm = Integer.parseInt(maKM);
        int masp = Integer.parseInt(maSP);
        int pt =  Integer.parseInt(phantramgiam);
        int dk = Integer.parseInt(dieukien);
        if(pt<1 || pt>100){
            new MyDialog("Số đã nhập không hợp lệ. Phần trăm giảm phải lớn hơn 0 và tối đa là 100", MyDialog.ERROR_DIALOG);
            return false;
        }
        
        if(dk<20000){
            new MyDialog("Số đã nhập không hợp lệ. Yêu cầu: Hóa đơn mua tối thiểu là 20.000 VND", MyDialog.ERROR_DIALOG);
            return false;
        }else if(dk>10000000){
            new MyDialog("Số đã nhập quá lớn, vui lòng kiểm tra lại. Yêu cầu: Hóa đơn mua tối đa là 10.000.000 VND", MyDialog.ERROR_DIALOG);
            return false;
        }
        boolean flag = false;
        try {           
            CTKhuyenMai ctkm = new CTKhuyenMai();
            ctkm.setMaKM(makm);
            ctkm.setMaSP(masp);
            ctkm.setPhanTramGiam(pt); 
            ctkm.setDieuKien(dk);          

            flag = CTKMDAO.suaCTKhuyenMai(ctkm);          
        } catch (Exception e) {            
            return false;
        }
        if (flag) {
            new MyDialog("Sửa thành công!", MyDialog.SUCCESS_DIALOG);
        } else {
            new MyDialog("Sửa thất bại!", MyDialog.ERROR_DIALOG);
        }
        return flag;
    }
    public boolean xoaCTKhuyenMai(String maKM, String maSP) {
    	if (maKM.trim().equals("")) {
    		new MyDialog("Chưa chọn loại để xóa !", MyDialog.ERROR_DIALOG);
			return false;
		}
		int makm = Integer.parseInt(maKM);
		int masp = Integer.parseInt(maSP);
		if (CTKMDAO.xoaCTKhuyenMai(makm,masp)) {
			 new MyDialog("Xóa thành công!", MyDialog.SUCCESS_DIALOG);
			return true;
		} else {
			new MyDialog("Xóa thất bại, vui lòng kiểm tra lại thông tin hoặc liên hệ Admin để được hỗ trợ!", MyDialog.ERROR_DIALOG);
			return false;
		}
    }
    public void xoaCTKhuyenMai(String maKM) {
    	int makm = Integer.parseInt(maKM);
    	CTKMDAO.xoaCTKhuyenMai(makm);
    }
}
