package QuanLyBanSach.DTO;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author sonco
 */
public class TacGia {
    private int maTG;
    private String tenTG;

    public TacGia() {
    }

    public TacGia(int maTG, String tenTG) {
        this.maTG = maTG;
        this.tenTG = tenTG;
    }

    public int getMaTG() {
        return maTG;
    }

    public String getTenTG() {
        return tenTG;
    }

    public void setMaTG(int maTG) {
        this.maTG = maTG;
    }

    public void setTenTG(String tenTG) {
        this.tenTG = tenTG;
    }
    
}
