package Objects;

import java.util.Scanner;

/**
 *
 * @author NguyenManh
 */
public class SanPham {

    private String maSP;
    private String tenSP;
    private int soLuongSP;
    private long donGia;

    public SanPham() {

    }

    public SanPham(String maSP, String tenSP, int soLuongSP, long donGia) {
        this.maSP       = maSP;
        this.tenSP      = tenSP;
        this.soLuongSP  = soLuongSP;
        this.donGia     = donGia;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public void setSoLuongSP(int soLuongSP) {
        this.soLuongSP = soLuongSP;
    }

    public void setDonGia(long donGia) {
        this.donGia = donGia;
    }

    public String getMaSP() {
        return maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public int getSoLuongSP() {
        return soLuongSP;
    }

    public long getDonGia() {
        return donGia;
    }

    public void nhapTTSP() {
        Scanner input = new Scanner(System.in);

        System.out.print("Mã SP: ");
        maSP = input.nextLine().trim();

        System.out.print("Tên SP: ");
        maSP = input.nextLine().trim();

        do {
            System.out.print("Số lượng SP: ");
            soLuongSP = input.nextInt();
            input = new Scanner(System.in);

            if (getSoLuongSP() <= 0) {
                System.err.println("[ ERROR } Số lượng phải lớn hơn 0");
            }
        } while (getSoLuongSP() <= 0);

        do {
            System.out.print("Đơn giá SP: ");
            this.donGia = input.nextLong();
            input = new Scanner(System.in);
            
            if(getDonGia()<= 0) {
                System.err.println("[ ERROR ] Đơn giá phải lớn hơn 0!");
            }
        }while(getDonGia()<= 0);
    
    }

    public void xuatTTSP() {
        System.out.print("* Mã: " + this.maSP);
        System.out.print("\n* Tên: " + this.tenSP);
        System.out.print("\n* Số lượng: " + this.soLuongSP);
        System.out.print("\n* Giá: " + this.donGia);
    }
}
