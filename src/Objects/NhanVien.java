package Objects;

import common.Check;
import java.util.Scanner;

/**
 *
 * @author NguyenManh
 */
public class NhanVien {
    private String maNV;
    private String tenNV;
    private String sdtNV;
    private String mailNV;
    private String passNV;
    private String addressNV;
    private String group;

    public NhanVien() {
    }

    public NhanVien(String maNV, String tenNV, String sdtNV, String mailNV, String passNV, String addressNV) {
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.sdtNV = sdtNV;
        this.mailNV = mailNV;
        this.passNV = passNV;
        this.addressNV = addressNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public void setSdtNV(String sdtNV) {
        this.sdtNV = sdtNV;
    }

    public void setMailNV(String mailNV) {
        this.mailNV = mailNV;
    }

    public void setPassNV(String passNV) {
        this.passNV = passNV;
    }

    public void setAddressNV(String addressNV) {
        this.addressNV = addressNV;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getMaNV() {
        return maNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public String getSdtNV() {
        return sdtNV;
    }

    public String getMailNV() {
        return mailNV;
    }

    public String getPassNV() {
        return passNV;
    }

    public String getAddressNV() {
        return addressNV;
    }

    public String getGroup() {
        return group;
    }
    
    public void nhapTTNV() {
        Check check = new Check();
        Scanner input = new Scanner(System.in);
        
        System.out.print("Mã nhân viên: ");
        this.maNV = input.nextLine().trim();
        
        do {
            System.out.print("Tên nhân viên: ");
            this.tenNV = input.nextLine().trim();
        }while(check.checkName(this.tenNV));
        
        do {
            System.out.print("Nhập SĐT: ");
            this.sdtNV = input.nextLine().trim();
        }while(check.kiemTraSDT(this.sdtNV) == false);
        
        do {
            System.out.print("Nhập Mail: ");
            this.mailNV = input.nextLine().trim();
        }while(check.kiemTraEmail(this.mailNV) == false);
        
        do {
            System.out.print("Nhập mật khẩu: ");
            this.passNV = input.nextLine().trim();
        }while(check.checkPass(this.passNV) == false);
        
        System.out.print("Nhập địa chỉ: ");
        this.addressNV = input.nextLine().trim();
    }
    
    public void xuatTTNV() {
        System.out.print("| Mã nhân viên: " + this.maNV);
        System.out.print("\n| Tên nhân viên: " + this.tenNV);
        System.out.print("\n| Số điện thoại: " + this.sdtNV);
        System.out.print("\n| Email: " + this.mailNV);
        System.out.print("\n| Địa chỉ: " + this.addressNV);
        System.out.print("\n| Group: " + this.group);
    }
}
