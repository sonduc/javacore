/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objects;

import java.util.Date;

/**
 *
 * @author NguyenManh
 */
public class HoaDon {
    private String maKH;
    private String maNV;
    private String maSP;
    private int soLuongMua;
    private long donGia;
    private long thanhTien;
    private Date ngayBan;

    public HoaDon() {
    }

    public HoaDon(String maKH, String maNV, String maSP, int soLuongMua, long donGia, long thanhTien) {
        this.maKH = maKH;
        this.maNV = maNV;
        this.maSP = maSP;
        this.soLuongMua = soLuongMua;
        this.donGia = donGia;
        this.thanhTien = thanhTien;
        this.ngayBan = ngayBan;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public void setSoLuongMua(int soLuongMua) {
        this.soLuongMua = soLuongMua;
    }

    public void setDonGia(long donGia) {
        this.donGia = donGia;
    }

    public void setThanhTien(long thanhTien) {
        this.thanhTien = thanhTien;
    }

    public void setNgayBan(Date ngayBan) {
        this.ngayBan = ngayBan;
    }

    public String getMaKH() {
        return maKH;
    }

    public String getMaNV() {
        return maNV;
    }

    public String getMaSP() {
        return maSP;
    }

    public int getSoLuongMua() {
        return soLuongMua;
    }

    public long getDonGia() {
        return donGia;
    }

    public long getThanhTien() {
        return thanhTien;
    }

    public Date getNgayBan() {
        return ngayBan;
    }
    
    public void xuatTTHD() {
        System.out.print("Mã khách hàng: " + this.maKH);
        System.out.print("\nMã sản phẩm: " + this.maSP);
        System.out.print("\nSố lượng mua: " + this.soLuongMua);
        System.out.print("\nĐơn giá: " + this.donGia);
        System.out.print("\nThành tiền: " + this.thanhTien);
        System.out.print("\nNgày bán: " + this.ngayBan);
    }
}
