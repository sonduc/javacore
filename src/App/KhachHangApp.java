package App;

import Objects.KhachHang;
import common.Check;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author NguyenManh
 */
public class KhachHangApp {

    Check check = new Check();
    ArrayList<KhachHang> khList = new ArrayList();
    private String file = "KhachHang.txt";
    File f = new File(file);

    public void menuKH() {
        Scanner input = new Scanner(System.in);

        String choice;

        do {
            System.out.println("\n\n\n");
            System.out.println("+-------------------------CHỨC NĂNG QUẢN LÝ KHÁCH HÀNG-------------------------------+");
            System.out.println("| [ 1 ]::. Xem danh sách toàn bộ khách hàng                                          |");
            System.out.println("| [ 2 ]::. Tìm kiếm khách hàng                                                       |");
            System.out.println("| [ 3 ]::. Thêm mới một khách hàng                                                   |");
            System.out.println("| [ 4 ]::. Cập nhật thông tin khách hàng                                             |");
            System.out.println("| [ 5 ]::. Xóa khách hàng                                                            |");
            System.out.println("| [ 0 ]::. Trở về menu chính                                                         |");
            System.out.println("+------------------------------------------------------------------------------------+");
            System.out.print("Mời bạn chọn: ");
            choice = input.nextLine();

            switch (choice) {
                case "0": {
                    saveKH();
                    break;
                }
                case "1": {
                    showAllCustomer();
                    break;
                }
                case "2": {
                    searchCustomer();
                    break;
                }
                case "3": {
                    addNewCustomer();
                    break;
                }
                case "4": {
                    updateInfoCustomer();
                    break;
                }
                case "5": {
                    deleteCustomer();
                    break;
                }
                default: {
                    System.err.println("KHÔNG CÓ CHỨC NĂNG NÀY!");
                }
            }
        } while (choice.equalsIgnoreCase("0") == false);
    }

//    bắt đầu load file
    public void loadFile() {
        BufferedReader br = null;
        FileReader fr = null;
        khList = new ArrayList();

        // nếu không tìm thấy file thì tạo ra file mới
        if (f.exists() == false) {
            try {
                f.createNewFile();
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        try {

            fr = new FileReader(file);
            br = new BufferedReader(fr);

            String strInfo;

            while ((strInfo = br.readLine()) != null) {
                String srtInfoArr[] = strInfo.split("\t");

                KhachHang kh = new KhachHang();

                kh.setMaKH(srtInfoArr[0]);
                kh.setTenKH(srtInfoArr[1]);
                kh.setSdtKH(srtInfoArr[2]);
                kh.setEmailKH(srtInfoArr[3]);
                kh.setAddressKH(srtInfoArr[4]);

                khList.add(kh);
            }

            br.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
//    Kết thúc load file

//    Xem toàn bộ danh sách khách hàng
    public void showAllCustomer() {
        if (khList.size() > 0) {
            System.out.println("\n\n\n");
            System.out.println("+-------------------------------------------------------------------------------------+--------------------+");
            System.out.println("|                                     DANH SÁCH KHÁCH HÀNG                                                 |");
            System.out.println("+----------+------------------------------+------------+------------------------------+--------------------+");
            System.out.println("|    ID    |        Tên Khách hàng        |    SĐT     |     Email                    |      Địa chỉ       |");
            System.out.println("+----------+------------------------------+------------+------------------------------+--------------------+");

            for (int i = 0; i < khList.size(); i++) {
                System.out.printf("|%10s|%30s|%12s|%30s|%20s|\n", khList.get(i).getMaKH(), khList.get(i).getTenKH(), khList.get(i).getSdtKH(), khList.get(i).getEmailKH(), khList.get(i).getAddressKH());
            }
            System.out.println("+----------+------------------------------+------------+------------------------------+--------------------+");

        } else {
            System.err.println("Hiện tại danh sách đang rỗng!");
        }
    }

//    cập nhật thông tin khách hàng
    public void updateInfoCustomer() {
        Scanner input = new Scanner(System.in);

        String maKH;

        System.out.print("Nhập mã khách hàng muốn update: ");
        maKH = input.nextLine().trim();

        if (checkMaKH(maKH) == false) {
            System.out.println("Khách hàng có mã " + maKH + " không tồn tại!");
        } else {
            String tenKH;
            String sdtKH;
            String emailKH;
            String addressKH;

            boolean flagTen = false;
            boolean flagSdt = false;
            boolean flagEmail = false;
            boolean flagAddress = false;

            System.out.println("+------------------Thông tin hiện tại của nhân viên------------------+");

            for (int i = 0; i < khList.size(); i++) {
                if (maKH.equalsIgnoreCase(khList.get(i).getMaKH())) {
                    khList.get(i).xuatTTKH();
                    System.out.println("\n");
                }
            }

            System.out.println("+--------------------------------------------------------------------+");
            System.out.println("\n\n");

            System.out.println("Nhập thông tin - nếu không thay đổi thì để trống");
//            nhập tên
            do {
                System.out.print("Nhập tên: ");
                tenKH = input.nextLine().trim();

                if (!tenKH.isEmpty()) {
                    if (check.checkName(tenKH)) {
                        flagTen = false;
                    } else {
                        flagTen = true;
                    }
                } else {
                    flagTen = true;
                }
            } while (flagTen == false);

//            nhập số điện thoại
            do {
                System.out.print("Số điện thoại: ");
                sdtKH = input.nextLine().trim();

                if (!sdtKH.isEmpty()) {
                    if (check.kiemTraSDT(sdtKH) == false) {
                        flagSdt = false;
                    } else {
                        flagSdt = true;
                    }
                } else {
                    flagSdt = true;
                }
            } while (flagSdt == false);

//            nhập email
            do {
                System.out.print("Nhập mail: ");
                emailKH = input.nextLine().trim();

                if (!emailKH.isEmpty()) {
                    if (check.kiemTraEmail(emailKH) == false) {
                        flagEmail = false;
                    } else {
                        flagEmail = true;
                    }
                } else {
                    flagEmail = true;
                }
            } while (flagEmail == false);

            System.out.print("Nhập quê quán: ");
            addressKH = input.nextLine().trim();

            for (int i = 0; i < khList.size(); i++) {
                if (maKH.equalsIgnoreCase(khList.get(i).getMaKH())) {
                    if (!tenKH.isEmpty()) {
                        khList.get(i).setTenKH(tenKH);
                    }

                    if (!sdtKH.isEmpty()) {
                        khList.get(i).setSdtKH(sdtKH);
                    }

                    if (!emailKH.isEmpty()) {
                        khList.get(i).setEmailKH(emailKH);
                    }

                    if (!addressKH.isEmpty()) {
                        khList.get(i).setAddressKH(addressKH);
                    }
                }
            }
            System.out.println("Cập nhật thành công thôn tin khách hàng!");
        }
    }
//    xóa khách hàng

    public void deleteCustomer() {
        Scanner input = new Scanner(System.in);
        String id;
        System.out.print("Nhập id cần xóa: ");
        id = input.nextLine().trim();

        if (checkMaKH(id) == false) {
            System.err.println("Không có khách hàng có id " + id);
        } else {
            String ask;
            System.out.println("Bạn có chắc chắn muốn xóa? y để đồng ý - ký tự khác để không xóa nữa!");
            ask = input.nextLine().trim();

            if (ask.equalsIgnoreCase("y")) {
                for (int i = 0; i < khList.size(); i++) {
                    if (khList.get(i).getMaKH().equalsIgnoreCase(id)) {
                        khList.remove(i);
                    }
                }
                System.out.println("Xóa thành công khách hàng có id " + id);
            }else {
                System.out.println("Lần sau đừng nghịch dại nhé, may có chức năng hỏi không là xóa mất rồi!");
            }
        }
    }

//    tìm kiếm khách hàng
    public void searchCustomer() {
        Scanner input = new Scanner(System.in);
        boolean flag = false;
        String search;

        System.out.print("id, sđt, mail - Nhập 1 trong 3 để tìm: ");
        search = input.nextLine().trim();

        System.out.println("+-------------Thông tin tìm được-------------------------+");
        for (int i = 0; i < khList.size(); i++) {
            if (search.equalsIgnoreCase(khList.get(i).getMaKH())) {
                khList.get(i).xuatTTKH();
                flag = true;
                System.out.println();
                System.out.println("+--------------------------------------------------------+");
                System.out.println();
            }
            if (search.equalsIgnoreCase(khList.get(i).getSdtKH())) {
                flag = true;
                khList.get(i).xuatTTKH();
                System.out.println();
                System.out.println("+--------------------------------------------------------+");
                System.out.println();

            }

            if (search.equalsIgnoreCase(khList.get(i).getEmailKH())) {
                flag = true;
                khList.get(i).xuatTTKH();
                System.out.println();
                System.out.println("+--------------------------------------------------------+");
                System.out.println();
            }
        }

        if (flag == false) {
            System.err.println("Không tìm thấy kết quả nào hợp lệ");
        }
        System.out.println("Enter để tiếp tục!");
        input.nextLine();
    }
//    Thêm mới một khách hàng

    public void addNewCustomer() {
        Scanner input = new Scanner(System.in);
        KhachHang khTemp = new KhachHang();
        String maKH;
        String tenKH;
        String sdtKH;
        String emailKH;
        String addressKH;

        System.out.println("Nhập thông tin khách hàng! (Bỏ trống nếu không có - trường có dấu * là bắt buộc )");

        boolean flagID = false;
        do {
            System.out.print("Mã khách hàng*: ");
            maKH = input.nextLine();

            if (checkMaKH(maKH)) {
                System.err.println("[ ERROR ] Mã khách hàng đã tồn tại!");
                flagID = false;
            } else {
                if (!maKH.isEmpty()) {
                    khTemp.setMaKH(maKH);
                    flagID = true;
                } else {
                    flagID = false;
                    System.err.println("Mã khách hàng bắt buộc phải có!");
                }
            }

        } while (flagID == false);

//        nhập tên
        boolean flagName = false;
        do {
            System.out.print("Tên khách hàng *: ");
            tenKH = input.nextLine().trim();
            if (!tenKH.isEmpty()) {
                if (check.checkName(tenKH)) {
                    flagName = false;
                } else {
                    khTemp.setTenKH(tenKH);
                    flagName = true;
                }
            } else {
                flagName = false;
                System.err.println("Tên khách hàng bắt buộc phải có!");
            }
        } while (flagName == false);

//        nhập số đt
        boolean flagSdt = false;
        do {
            System.out.print("Số điện thoại: ");
            sdtKH = input.nextLine().trim();

            if (!sdtKH.isEmpty()) {
                if (check.kiemTraSDT(sdtKH) == false) {
                    flagSdt = false;
                } else {
                    khTemp.setSdtKH(sdtKH);
                    flagSdt = true;
                }
            } else {
                khTemp.setSdtKH(" ");
                flagSdt = true;
                System.err.println("Bạn đã để trống số điện thoại!");
            }

        } while (flagSdt == false);

//        nhập email
        boolean flagMail = false;

        do {

            System.out.print("Nhập mail: ");
            emailKH = input.nextLine().trim();

            if (!emailKH.isEmpty()) {
                if (check.kiemTraEmail(emailKH) == false) {
                    flagMail = false;
                } else {
                    khTemp.setEmailKH(emailKH);
                    flagMail = true;
                }

            } else {
                khTemp.setEmailKH(" ");
                flagMail = true;
                System.err.println("Bạn đã để trống số email!");
            }

        } while (flagMail == false);

//        nhập địa chỉ
        System.out.print("Địa chỉ: ");
        addressKH = input.nextLine().trim();

        if (!addressKH.isEmpty()) {
            khTemp.setAddressKH(addressKH);
        } else {
            khTemp.setAddressKH(" ");
            System.err.println("Bạn đã để trống địa chỉ");
        }
        khList.add(khTemp);
        System.out.println("\n\n Thêm mới khách hàng thành công!");
    }

//    lưu lại sau khi thao tác
    public void saveKH() {
        BufferedWriter bw = null;
        FileWriter fw = null;

        if (f.exists() == false) {
            try {
                f.createNewFile();
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        try {
            fw = new FileWriter(file);
            bw = new BufferedWriter(fw);

            for (int i = 0; i < khList.size(); i++) {
                bw.write(khList.get(i).getMaKH() + "\t" + khList.get(i).getTenKH() + "\t" + khList.get(i).getSdtKH() + "\t" + khList.get(i).getEmailKH() + "\t" + khList.get(i).getAddressKH());
                bw.newLine();
            }

            bw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

//    check sự xuất hiện của id trong khách hàng
    public boolean checkMaKH(String id) {
        for (int i = 0; i < khList.size(); i++) {
            if (khList.get(i).getMaKH().equalsIgnoreCase(id)) {
                return true;
            }
        }
        return false;
    }
}
