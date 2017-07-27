package Objects;

import java.util.Scanner;

/**
 *
 * @author NguyenManh
 */
public class KhachHang {
    private String maKH;
    private String tenKH;
    private String sdtKH;
    private String emailKH;
    private String addressKH;
    
    public KhachHang() {
        
    }
    
    public KhachHang(String maKH, String tenKH, String sdtKH, String emailKH, String addressKH) {
        this.maKH       = maKH;
        this.tenKH      = tenKH;
        this.sdtKH      = sdtKH;
        this.emailKH    = emailKH;
        this.addressKH  = addressKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public void setSdtKH(String sdtKH) {
        this.sdtKH = sdtKH;
    }

    public void setEmailKH(String emailKH) {
        this.emailKH = emailKH;
    }

    public void setAddressKH(String addressKH) {
        this.addressKH = addressKH;
    }

    public String getMaKH() {
        return maKH;
    }

    public String getTenKH() {
        return tenKH;
    }

    public String getSdtKH() {
        return sdtKH;
    }

    public String getEmailKH() {
        return emailKH;
    }

    public String getAddressKH() {
        return addressKH;
    }
    
    public void nhapTTKH() {
        Scanner input = new Scanner(System.in);
        
        System.out.print("Mã KH: ");
        setMaKH(input.nextLine().trim());
        
        System.out.print("Tên KH: ");
        setTenKH(input.nextLine());
        
        System.out.print("Sđt KH: ");
        setSdtKH(input.nextLine());
        
        System.out.print("Email KH: ");
        setEmailKH(input.nextLine());
        
        System.out.print("Địa chỉ KH: ");
        setAddressKH(input.nextLine());
    }
    
    public void xuatTTKH() {
        System.out.print("| Mã: " + this.maKH);
        System.out.print("\n| Tên: " + this.tenKH);
        System.out.print("\n| SĐT: " + this.sdtKH);
        System.out.print("\n| Email: " + this.emailKH);
        System.out.print("\n| Địa chỉ: " + this.addressKH);
    }
}
