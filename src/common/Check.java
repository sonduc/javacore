package common;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;

/**
 *
 * @author NguyenManh
 */
public class Check {

    
//    kiểm tra sđt dung tra ve true
    public boolean kiemTraSDT(String sdt) {

        if (sdt.length() < 10 || sdt.length() > 11) {
            System.err.println("[ ERROR ] Sai định dạng số đt! không được > 11 và < 10");
            return false;
        } else {
            for (int i = 0; i < sdt.length(); i++) {
                if (Character.isDigit(sdt.charAt(i)) == false) {
                    System.err.println("[ ERROR ] Sai định dạng số đt! không được nhập chữ!");
                    return false;
                }
            }
        }

        return true;
    }

//    kiểm tra mật khẩu
    public boolean checkPass(String pass) {
        if (pass.contains(" ")) {
            System.err.println("[ ERROR ] Mật khẩu không được chứa khoảng trắng!");
            return false;
        }

        if (pass.length() < 6) {
            System.err.println("[ ERROR ] Mật khẩu phải lớn hơn hoặc bằng 6 ký tự!");
            return false;
        }
        return true;
    }

//    kiểm tra email
    public boolean kiemTraEmail(String email) {
        String dinhDangEmail = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        boolean ktEmail = email.matches(dinhDangEmail);
        if (ktEmail == false) {
            System.err.println("Email sai, nhập lại theo dạng abc@domain.com");
            return false;
        }
        return true;
        // String dinhDangEmail nó giống như là format chuẩn cho email mình nhập vào, nếu sai cái
        // dịnh dạng này thì là lỗi.
        // Hàm matches dùng để xác định xem chuỗi email của mình có khớp với định dạng mình đã quy 
        // định trước hay không. hàm này trả về true , false nên mình khởi tạo 1 biến ktEmail rồi gán
        // cho nó. false thì báo lỗi.
    }

//    kiểm tra tên nhập vào, nếu tên chứa số thì trả về true
    public boolean checkName(String name) {
        for (int i = 0; i < name.length(); i++) {
            if (Character.isDigit(name.charAt(i))) {
                System.err.println("[ ERROR ] Tên không được chứa số!");
                return true;
            }
        }
        return false;
    }
}
