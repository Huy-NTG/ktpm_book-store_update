package QuanLyBanSach.BUS;




import java.util.ArrayList;

import javax.swing.JOptionPane;

import QuanLyBanSach.DAO.SanPhamDAO;
import QuanLyBanSach.DTO.SanPham;

public class SanPhamBUS {

    private ArrayList<SanPham> listSanPham = null;
    private SanPhamDAO spDAO = new SanPhamDAO();

    public SanPhamBUS() {
        docListSanPham();
    }

    public void docListSanPham() {
        listSanPham = spDAO.getListSanPham();
    }

    public ArrayList<SanPham> getListSanPham() {
        if (listSanPham == null) {
            docListSanPham();
        }
        return listSanPham;
    }

    public SanPham getSanPham(String ma) {
        if (!ma.trim().isEmpty() || ma.matches("[a-zA-Z]+")) {
            try {
                int maSP = Integer.parseInt(ma);
                for (SanPham sp : listSanPham) {
                    if (sp.getMaSP() == maSP) {
                        return sp;
                    }
                }
            } catch (Exception e) {
                 JOptionPane.showMessageDialog(null, "Mã sản phẩm không hợp lệ!");
            }
        }
        return null;
    }

    public ArrayList<SanPham> getSanPhamTheoTen(String ten) {
        ArrayList<SanPham> dssp = new ArrayList<>();
        for (SanPham sp : listSanPham) {
            String tenSP = sp.getTenSP().toLowerCase();
            if (tenSP.toLowerCase().contains(ten.toLowerCase())) {
                dssp.add(sp);
            }
        }
        return dssp;
    }
    
    public ArrayList<SanPham> getSanPhamDonGia(String gia1,String gia2) {
        ArrayList<SanPham> dssp = new ArrayList<>();
        gia1=gia1.trim();
        gia2=gia2.trim();
        if(gia1.matches("[a-zA-Z]+") || gia2.matches("[a-zA-Z]+")){
             JOptionPane.showMessageDialog(null, "Giá sản phẩm không hợp lệ!");
            return dssp;
        }
        int so1;
        int so2;
        try {
            so1=Integer.parseInt(gia1);
            so2=Integer.parseInt(gia2);
            for (SanPham sp : listSanPham) {
            if (sp.getDonGia()>=so1 && sp.getDonGia()<=so2) {
                dssp.add(sp);
            }
        }
        } catch (Exception e) {
            
        }
        return dssp;
    }
    

    public ArrayList<SanPham> getSanPhamTheoLoai(String ma) {
         ArrayList<SanPham> dssp = new ArrayList<>();
        if (!ma.trim().equals("")) {
            try {
                ma=ma.trim();
                int maLoai = Integer.parseInt(ma);
                for (SanPham sp : listSanPham) {
                    if (sp.getMaLoai() == maLoai) {
                        dssp.add(sp);
                    }
                }
                return dssp;
            } catch (Exception e) {
            }
        }
        return null;
    }
    public ArrayList<SanPham> getSanPhamTheoTG(String ma) {
        ArrayList<SanPham> dssp = new ArrayList<>();
        if (!ma.trim().equals("")) {
            try {
                ma=ma.trim();
                int maTG = Integer.parseInt(ma);
                for (SanPham sp : listSanPham) {
                    if (sp.getMaTG() == maTG) {
                        dssp.add(sp);
                    }
                }
                return dssp;
            } catch (Exception e) {
            }
        }
        return null;
    }

    public String getAnh(String ma) {
        int maSP = Integer.parseInt(ma);
        return spDAO.getAnh(maSP);
    }

    public void capNhatSoLuongSP(int ma, int soLuongMat) {
        spDAO.capNhatSoLuongSP(ma, soLuongMat);
    }

    public boolean themSanPham(
    		String ten,
            String loai,
            String anh,
            String tacgia,
    		String mota) {

        if (ten.trim().equals("")) {
        	JOptionPane.showMessageDialog(null, "Vui lòng nhập tên sách!");
            return false;
        }
   
        try {
            String[] loaiTmp = loai.split(" - ");
            int maLoai = Integer.parseInt(loaiTmp[0]);
            String[] tgTmp = tacgia.split(" - ");
            int maTG = Integer.parseInt(tgTmp[0]);
            if (maLoai == 0) {
            	JOptionPane.showMessageDialog(null, "Vui lòng nhập lại mã loại sản phẩm !");
                return false;
            }
             if (maTG == 0) {
            	JOptionPane.showMessageDialog(null, "Vui lòng nhập lại mã tác giả !");
                return false;
            }
            SanPham sp = new SanPham();
            sp.setTenSP(ten);
            sp.setMaLoai(maLoai);          
            sp.setHinhAnh(anh);          
            sp.setMaTG(maTG);
            sp.setMoTa(mota);
            if (spDAO.themSanPham(sp)) {
            	JOptionPane.showMessageDialog(null, "Thêm thành công !");
                return true;
            } else {
            	JOptionPane.showMessageDialog(null, "Thêm thất bại !");              	
                return false;
                
            }
            
        } catch (Exception e) {
           System.out.println(e);               
        }
        return false;
        
    }
    public boolean themSanPham1(
    		String ma,
    		String ten,
            String loai,
            String soLuong,
            String donGia,
            String anh,
            String tacgia,
    		String mota) {

        if (ma.trim().equals("")||ten.trim().equals("")||donGia.trim().equals("")) {
        	JOptionPane.showMessageDialog(null, "Không được để trống thông tin !");
            return false;
        }
        

        try {
        	int maSP = Integer.parseInt(ma);
            String[] loaiTmp = loai.split(" - ");
            int maLoai = Integer.parseInt(loaiTmp[0]);
            String[] tgTmp = tacgia.split(" - ");
            int maTG = Integer.parseInt(tgTmp[0]);
            int soLuongSP = Integer.parseInt(soLuong);
            //donGia = donGia.replace(",", "");
            int donGiaSP = Integer.parseInt(donGia);
            if (maLoai == 0) {
            	JOptionPane.showMessageDialog(null, "Vui lòng nhập lại mã loại sản phẩm !");
                return false;
            }
            SanPham sp = new SanPham();
            sp.setMaSP(maSP);
            sp.setTenSP(ten);
            sp.setMaLoai(maLoai);
            sp.setSoLuong(soLuongSP);                 
            sp.setDonGia(donGiaSP);            
            sp.setHinhAnh(anh);          
            sp.setMaTG(maTG);
            sp.setMoTa(mota);
            if (spDAO.themSanPham(sp)) {
            	JOptionPane.showMessageDialog(null, "Thêm thành công !");
                return true;
            } else {
            	JOptionPane.showMessageDialog(null, "Thêm thất bại !");              	
                return false;
                
            }
            
        } catch (Exception e) {
           System.out.println(e);               
        }
        return false;
        
    }

//    public boolean nhapSanPhamTuExcel(String ten,
//            String loai,
//            String soLuong,
//            String donViTinh,
//            String anh,
//            String donGia) {
//
//        try {
//            String[] loaiTmp = loai.split(" - ");
//            int maLoai = Integer.parseInt(loaiTmp[0]);
//            int soLuongSP = Integer.parseInt(soLuong);
//            donGia = donGia.replace(",", "");
//            int donGiaSP = Integer.parseInt(donGia);
//
//            SanPham sp = new SanPham();
//            sp.setTenSP(ten);
//            sp.setMaLoai(maLoai);
//            sp.setSoLuong(soLuongSP);
//            sp.setHinhAnh(anh);
//            sp.setDonGia(donGiaSP);
//
//            spDAO.nhapSanPhamTuExcel(sp);
//        } catch (Exception e) {
//        }
//        return false;
//    }

    public boolean xoaSanPham(String ma) {
        if (ma.trim().equals("")) {
        	JOptionPane.showMessageDialog(null, "Mã sản phẩm không đc để trống !");
            return false;
        }

        int maSP = Integer.parseInt(ma);
        if (spDAO.xoaSanPham(maSP)) {
        	JOptionPane.showMessageDialog(null, "Xóa thành công !");
            return true;
        }

        JOptionPane.showMessageDialog(null, "Xóa thất bại !");
        return false;
    }

    public boolean suaSanPham(String ma,   		
    		String ten,
            String loai,
            String donGia,
            String anh,
            String tacgia,
    		String mota) {

        try {
            if (ma.trim().equals("")) {
            	JOptionPane.showMessageDialog(null, "Mã sản phẩm không đc để trống !");
                return false;
            }          
            int maSP = Integer.parseInt(ma);
            String[] loaiTmp = loai.split(" - ");
            int maLoai = Integer.parseInt(loaiTmp[0]);
            String[] tgTmp = tacgia.split(" - ");
            int maTG = Integer.parseInt(loaiTmp[0]);
            int donGiaSP = Integer.parseInt(donGia);

            if (maLoai == 0) {
            	JOptionPane.showMessageDialog(null, "Nhập sai mã sản phẩm !");
                return false;
            }

            if (ten.trim().equals("")) {
            	JOptionPane.showMessageDialog(null, "Tên sản phẩm không được để trống !");
                return false;
            }
           

            SanPham sp = new SanPham();
            sp.setMaSP(maSP);
            sp.setTenSP(ten);
            sp.setMaLoai(maLoai);                 
            sp.setDonGia(donGiaSP);            
            sp.setHinhAnh(anh);   
            sp.setMaTG(maTG);
            sp.setMoTa(mota);

            if (spDAO.suaSanPham(sp)) {
            	JOptionPane.showMessageDialog(null, "Sữa sản phẩm thành công !");
                return true;
            } else {
            	JOptionPane.showMessageDialog(null, "Sữa thất bại !");
                return false;
            }
        } catch (Exception e) {
        	System.out.println(e);
        }
        return false;
    }
    
    public boolean suaSanPham1(String ma,   		
    		String ten,
            String loai,
            String soLuong,
            String donGia,
            String anh,
            String tacgia,
    		String mota) {

        try {
            if (ma.trim().equals("")) {
            	JOptionPane.showMessageDialog(null, "Mã sản phẩm không đc để trống !");
                return false;
            }          
            int maSP = Integer.parseInt(ma);
            String[] loaiTmp = loai.split(" - ");
            int maLoai = Integer.parseInt(loaiTmp[0]);
            String[] tgTmp = tacgia.split(" - ");
            int maTG = Integer.parseInt(loaiTmp[0]);
            int soLuongSP = Integer.parseInt(soLuong);
            int donGiaSP = Integer.parseInt(donGia);

            if (maLoai == 0) {
            	JOptionPane.showMessageDialog(null, "Nhập sai mã sản phẩm !");
                return false;
            }

            if (ten.trim().equals("")) {
            	JOptionPane.showMessageDialog(null, "Tên sản phẩm không được để trống !");
                return false;
            }
           

            SanPham sp = new SanPham();
            sp.setMaSP(maSP);
            sp.setTenSP(ten);
            sp.setMaLoai(maLoai);
            sp.setSoLuong(soLuongSP);                 
            sp.setDonGia(donGiaSP);            
            sp.setHinhAnh(anh);   
            sp.setMaTG(maTG);
            sp.setMoTa(mota);

            if (spDAO.suaSanPham(sp)) {
            	JOptionPane.showMessageDialog(null, "Sữa sản phẩm thành công !");
                return true;
            } else {
            	JOptionPane.showMessageDialog(null, "Sữa thất bại !");
                return false;
            }
        } catch (Exception e) {
        	System.out.println(e);
        }
        return false;
    }

    public String getTenSP(int maSP) {
        for (SanPham sp : listSanPham) {
            if (sp.getMaSP() == maSP) {
                return sp.getTenSP();
            }
        }
        return "";
    }
}
