package App;

import Objects.KhachHang;
import Objects.NhanVien;
import common.Check;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NguyenManh
 */
public class NhanVienApp {

    Check check = new Check();
    ArrayList<NhanVien> nvList = new ArrayList();
    private String file = "NhanVien.txt";
    File f = new File(file);
    Scanner input = new Scanner(System.in);

    public void menuNV(String ma) {
        String choice;

        do {
            System.out.println("\n\n\n");
            System.out.println("+-------------------------CHỨC NĂNG QUẢN LÝ NHÂN VIÊN -------------------------------+");
            System.out.println("| [ 1 ]::. Xem toàn bộ nhân viên                                                     |");
            System.out.println("| [ 2 ]::. Tìm kiếm Nhân viên                                                        |");
            System.out.println("| [ 3 ]::. Thêm mới một Nhân viên                                                    |");
            System.out.println("| [ 4 ]::. Cập nhật thông tin Nhân viên                                              |");
            System.out.println("| [ 5 ]::. Xóa Nhân viên                                                             |");
            System.out.println("| [ 0 ]::. Trở về menu chính                                                         |");
            System.out.println("+------------------------------------------------------------------------------------+");
            System.out.print("Mời bạn chọn: ");
            choice = input.nextLine();

            switch (choice) {
                case "0": {
                    saveNV();
                    break;
                }
                case "1": {
                    showAllPersonnel();
                    break;
                }
                case "2": {
                    searchPersonnel();
                    break;
                }
                case "3": {
                    addNewPersonnel();
                    break;
                }
                case "4": {
                    updateInfoPersonnel();
                    break;
                }
                case "5": {
                    deletePersonnel(ma);
                    break;
                }
                default: {
                    System.err.println("KHÔNG CÓ CHỨC NĂNG NÀY!");
                }
            }
        } while (choice.equalsIgnoreCase("0") == false);
    }

//    menu cho nhân viên đăng nhập
    public void menuMe(String id) {
        String choice;
        do {

            System.out.println("+-------------------------------------+");
            System.out.println("| [ 1 ]::. Sửa thông tin cá nhân      |");
            System.out.println("| [ 2 ]::. Thông tin cá nhân          |");
            System.out.println("| [ 3 ]::. Đổi mật khẩu               |");
            System.out.println("| [ 0 ]::. Thoát                      |");
            System.out.println("+-------------------------------------+");
            System.out.print("Mời chọn: ");
            choice = input.nextLine();

            switch (choice) {
                case "0": {
                    saveNV();
                    break;
                }
                case "1": {
                    String name;
                    String mail;
                    String sdt;
                    String queQuan;

                    boolean flagName = true;
                    boolean flagMail = true;
                    boolean flagSdt = true;
                    boolean flagQue = true;
                    System.out.println("\n\n\n");
                    System.out.println("+---------------Thông tin hiện tại của bạn---------------+");
                    for (int i = 0; i < nvList.size(); i++) {
                        if (id.equalsIgnoreCase(nvList.get(i).getMaNV())) {
                            System.out.print("| ID: " + nvList.get(i).getMaNV());
                            System.out.print("\n| Tên: " + nvList.get(i).getTenNV());
                            System.out.print("\n| Email: " + nvList.get(i).getMailNV());
                            System.out.print("\n| SĐT: " + nvList.get(i).getSdtNV());
                            System.out.print("\n| Quê quán: " + nvList.get(i).getAddressNV());
                            System.out.print("\n| Group: " + nvList.get(i).getGroup());
                        }
                    }
                    System.out.println("\n+-------------------------------------------------------+");
                    System.out.println("\n\n\n");

                    System.out.println("Nhập thông tin - nếu không thay đổi thì để trống");
                    do {
                        System.out.print("Nhập tên: ");
                        name = input.nextLine().trim();
                        if (!name.isEmpty()) {
                            if (check.checkName(name)) {
                                flagName = false;
                            } else {
                                flagName = true;
                            }
                        } else {
                            flagName = true;
                        }
                    } while (flagName == false);

                    do {

                        System.out.print("Nhập Mail: ");
                        mail = input.nextLine().trim();
                        if (!mail.isEmpty()) {
                            if (check.kiemTraEmail(mail) == false) {
                                flagMail = false;
                            } else {
                                flagMail = true;
                            }
                        } else {
                            flagMail = true;
                        }
                    } while (flagMail == false);

                    do {
                        System.out.print("Nhập sdt: ");
                        sdt = input.nextLine().trim();
                        if (!sdt.isEmpty()) {
                            if (check.kiemTraSDT(sdt) == false) {
                                flagSdt = false;
                            } else {
                                flagSdt = true;
                            }
                        } else {
                            flagSdt = true;
                        }
                    } while (flagSdt == false);

                    System.out.print("Quê quán: ");
                    queQuan = input.nextLine().trim();

                    for (int i = 0; i < nvList.size(); i++) {
                        if (id.equalsIgnoreCase(nvList.get(i).getMaNV())) {
                            if (!name.isEmpty()) {
                                nvList.get(i).setTenNV(name);
                            }
                            if (!mail.isEmpty()) {
                                nvList.get(i).setMailNV(mail);
                            }

                            if (!sdt.isEmpty()) {
                                nvList.get(i).setSdtNV(sdt);
                            }

                            if (!queQuan.isEmpty()) {
                                nvList.get(i).setAddressNV(queQuan);
                            }
                        }
                    }

                    System.out.println("\nCập nhật thông tin thành công!");
                    break;
                }
                case "2": {
                    System.out.println("+---------------Thông tin của bạn---------------+");
                    for (int i = 0; i < nvList.size(); i++) {
                        if (id.equalsIgnoreCase(nvList.get(i).getMaNV())) {
                            System.out.print("| ID: " + nvList.get(i).getMaNV());
                            System.out.print("\n| Tên: " + nvList.get(i).getTenNV());
                            System.out.print("\n| Email: " + nvList.get(i).getMailNV());
                            System.out.print("\n| SĐT: " + nvList.get(i).getSdtNV());
                            System.out.print("\n| Quê quán: " + nvList.get(i).getAddressNV());
                            System.out.print("\n| Group: " + nvList.get(i).getGroup());
                        }
                    }
                    System.out.println("\n+------------------------------------------------+");
                    System.out.println("\n\n\n");
                    break;
                }

                case "3": {
                    String passOld;
                    boolean flagPass = true;
                    boolean flagPassOld = false;
                    String pass;
                    do {

                        System.out.print("Nhập mật khẩu cũ: ");
                        passOld = input.nextLine().trim();

//                        kiểm tra sự đúng đắn mật khẩu cũ
                        for (int i = 0; i < nvList.size(); i++) {
                            if (id.equalsIgnoreCase(nvList.get(i).getMaNV())) {
                                if (!nvList.get(i).getPassNV().equalsIgnoreCase(passOld)) {
                                    System.err.println("Sai mật khẩu cũ!");
                                    flagPassOld = false;
                                } else {
                                    flagPassOld = true;
                                }
                            }
                        }

//                        nếu mật khẩu cũ đúng thì cho nhật mật khẩu moi
                        if (flagPassOld == true) {
                            System.out.println("Nhập mật khẩu mới - để trống nối không muốn đổi nữa");
                            System.out.print("Mật khẩu mới: ");
                            pass = input.nextLine().trim();

                            if (!pass.isEmpty()) {
                                if (check.checkPass(pass) == false) {
                                    flagPass = false;
                                } else {
                                    for (int i = 0; i < nvList.size(); i++) {
                                        if (id.equalsIgnoreCase(nvList.get(i).getMaNV())) {
                                            if (!pass.isEmpty()) {
                                                nvList.get(i).setPassNV(pass);
                                            }
                                        }
                                    }
                                    flagPass = true;
                                }
                            } else {
                                System.out.println("\nBạn để trống mật khẩu cũ tức là không đổi");
                                flagPass = true; // nếu mà trống thì vẫn là mật khẩu cũ
                            }
                        }

                    } while (flagPass == false || flagPassOld == false);

                    saveNV();
                    System.out.println("\nĐổi mật khẩu thành công!");
                    break;
                }

                default: {
                    System.err.println("Không có chức năng này!");
                }
            }
        } while (choice.equalsIgnoreCase("0") == false);
    }

//    thêm vào nhóm nhân viên
    public void addGroup(String id) {
        ArrayList<String> adGroup;
        File fileAD = new File("Acount_Amin.txt");

//        đọc file
        BufferedReader br = null;
        FileReader fr = null;
        adGroup = new ArrayList();

        // nếu không tìm thấy file thì tạo ra file mới
        if (fileAD.exists() == false) {
            try {
                fileAD.createNewFile();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        try {

            fr = new FileReader("Acount_Amin.txt");
            br = new BufferedReader(fr);

            String strInfo;

            while ((strInfo = br.readLine()) != null) {
                String temp = new String();
                temp = strInfo;

                adGroup.add(temp);
            }

            br.close();

        } catch (Exception e) {
            System.out.println("lỗi" + e);
        }

        adGroup.add(id);
//        ghi file
        FileWriter fw = null;
        BufferedWriter bw = null;

        if (fileAD.exists() == false) {
            try {
                fileAD.createNewFile();
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        try {
            fw = new FileWriter(fileAD);
            bw = new BufferedWriter(fw);

            for (int i = 0; i < adGroup.size(); i++) {
                bw.write(adGroup.get(i));
                bw.newLine();
            }
            bw.close();

        } catch (IOException ex) {
            Logger.getLogger(NhanVienApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    xóa nhân viên khỏi group admin
    public void deleteGroup(String id) {
        ArrayList<String> delGroup;
        File fileAD = new File("Acount_Amin.txt");

//        đọc file
        BufferedReader br = null;
        FileReader fr = null;
        delGroup = new ArrayList();

        // nếu không tìm thấy file thì tạo ra file mới
        if (fileAD.exists() == false) {
            try {
                fileAD.createNewFile();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        try {

            fr = new FileReader("Acount_Amin.txt");
            br = new BufferedReader(fr);

            String strInfo;

            while ((strInfo = br.readLine()) != null) {
                String temp = new String();
                temp = strInfo;

                delGroup.add(temp);
            }

            br.close();

        } catch (Exception e) {
            System.out.println("lỗi" + e);
        }

//        xư lý xóa
        delGroup.remove(id);

//        ghi file
        FileWriter fw = null;
        BufferedWriter bw = null;

        if (fileAD.exists() == false) {
            try {
                fileAD.createNewFile();
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        try {
            fw = new FileWriter(fileAD);
            bw = new BufferedWriter(fw);

            for (int i = 0; i < delGroup.size(); i++) {
                bw.write(delGroup.get(i));
                bw.newLine();
            }
            bw.close();

        } catch (IOException ex) {
            Logger.getLogger(NhanVienApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//    load file nhân viên

    public void loadFileNV() {
        BufferedReader br = null;
        FileReader fr = null;
        nvList = new ArrayList();

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

                NhanVien nv = new NhanVien();

                nv.setMaNV(srtInfoArr[0]);
                nv.setTenNV(srtInfoArr[1]);
                nv.setSdtNV(srtInfoArr[2]);
                nv.setMailNV(srtInfoArr[3]);
                nv.setPassNV(srtInfoArr[4]);
                nv.setAddressNV(srtInfoArr[5]);
                nv.setGroup(srtInfoArr[6]);

                nvList.add(nv);
            }

            br.close();

        } catch (Exception e) {
            System.out.println("lỗi" + e);
        }
    }

//    cập nhật thông tin nhân viên
    public void updateInfoPersonnel() {
        String id;
        System.out.print("Nhập mã nhân viên muốn sửa: ");
        id = input.nextLine().trim();
        if (checkNV(id) == false) {
            System.err.println("[ ERROR ] Nhân viên có mã " + id + " không tồn tại!");
        } else {
            String name;
            String mail;
            String sdt;
            String pass;
            String queQuan;

            boolean flagName = true;
            boolean flagMail = true;
            boolean flagSdt = true;
            boolean flagPass = true;
            boolean flagQue = true;

            System.out.println("+------------------Thông tin hiện tại của nhân viên------------------+");
            for (int i = 0; i < nvList.size(); i++) {
                if (id.equalsIgnoreCase(nvList.get(i).getMaNV())) {
                    System.out.print("| ID: " + nvList.get(i).getMaNV());
                    System.out.print("\n| Tên: " + nvList.get(i).getTenNV());
                    System.out.print("\n| Email: " + nvList.get(i).getMailNV());
                    System.out.print("\n| SĐT: " + nvList.get(i).getSdtNV());
                    System.out.print("\n| Quê quán: " + nvList.get(i).getAddressNV());
                    System.out.print("\n| Group: " + nvList.get(i).getGroup());
                    System.out.println("\n");
                }
            }
            System.out.println("+--------------------------------------------------------------------+");
            System.out.println("\n\n");

            System.out.println("Nhập thông tin - nếu không thay đổi thì để trống");
            do {
                System.out.print("Nhập tên: ");
                name = input.nextLine().trim();
                if (!name.isEmpty()) {
                    if (check.checkName(name)) {
                        flagName = false;
                    } else {
                        flagName = true;
                    }
                } else {
                    flagName = true;
                }
            } while (flagName == false);

            do {

                System.out.print("Nhập Mail: ");
                mail = input.nextLine().trim();
                if (!mail.isEmpty()) {
                    if (check.kiemTraEmail(mail) == false) {
                        flagMail = false;
                    } else {
                        flagMail = true;
                    }
                } else {
                    flagMail = true;
                }
            } while (flagMail == false);

            do {

                System.out.print("Nhập mật khẩu: ");
                pass = input.nextLine().trim();
                if (!pass.isEmpty()) {
                    if (check.checkPass(pass) == false) {
                        flagPass = false;
                    } else {
                        flagPass = true;
                    }
                } else {
                    flagPass = true;
                }
            } while (flagPass == false);

            do {
                System.out.print("Nhập sdt: ");
                sdt = input.nextLine().trim();
                if (!sdt.isEmpty()) {
                    if (check.kiemTraSDT(sdt) == false) {
                        flagSdt = false;
                    } else {
                        flagSdt = true;
                    }
                } else {
                    flagSdt = true;
                }
            } while (flagSdt == false);

            System.out.print("Quê quán: ");
            queQuan = input.nextLine().trim();

//            xem cập nhật nhóm
            boolean flagGrAD = false;
            boolean flagNotAd = false;
            if (checkGroup(id)) {
                String del;

                System.out.println("Hiện tại " + id + " đang là thành viên nhóm admin - nhập x để xóa khỏi nhóm admin - ký tự khác để bỏ qua");
                del = input.nextLine().trim();

                if (!del.isEmpty()) {
                    if (del.equalsIgnoreCase("x")) {
                        deleteGroup(id);
                        flagGrAD = true;
                        System.out.println("Đã xóa " + id + " khỏi admin");
                    } else {
                        flagGrAD = false;
                        flagNotAd = true;
                    }
                } else {
                    flagGrAD = false;
                    flagNotAd = true;
                }

            } else {
                String add;
                System.out.println("Hiện tại id " + id + " đang là nhân viên bình thường - nhập y để thêm vào admin - ký tự khác để bỏ qua");
                add = input.nextLine();

                if (!add.isEmpty()) {
                    if (add.equalsIgnoreCase("y")) {
                        addGroup(id);
                        flagNotAd = true;
                        flagGrAD = true;
                        System.out.println("Đã thêm " + id + " Vào nhóm admin");
                    } else {
                        flagNotAd = false;
                        flagGrAD = false;
                    }
                } else {
                    flagNotAd = false;
                    flagGrAD = false;
                }
            }

            for (int i = 0; i < nvList.size(); i++) {
                if (id.equalsIgnoreCase(nvList.get(i).getMaNV())) {
                    if (!name.isEmpty()) {
                        nvList.get(i).setTenNV(name);
                    }
                    if (!mail.isEmpty()) {
                        nvList.get(i).setMailNV(mail);
                    }

                    if (!sdt.isEmpty()) {
                        nvList.get(i).setSdtNV(sdt);
                    }
                    if (!pass.isEmpty()) {
                        nvList.get(i).setPassNV(pass);
                    }

                    if (!queQuan.isEmpty()) {
                        nvList.get(i).setAddressNV(queQuan);
                    }

//                    đsung thì đổi  , sai giữ nguyên
                    if (flagGrAD) {
                        nvList.get(i).setGroup("personnel");
                    } else{
                        nvList.get(i).setGroup("admin");
                    }

                    if (flagNotAd) {
                        nvList.get(i).setGroup("admin");
                    } else{
                        nvList.get(i).setGroup("personnel");
                    }
                }
            }

            saveNV();
            System.out.println("\nCập nhật thông tin thành công!");
        }
    }

//    xem toàn bộ nhân viên
    public void showAllPersonnel() {
        if (nvList.size() > 0) {
            System.out.println("\n\n\n");
            System.out.println("+-------------------------------------------------------------------------------------------------------------------------------+");
            System.out.println("|                                     DANH SÁCH NHÂN VIÊN                                                                       |");
            System.out.println("+----------+------------------------------+------------+------------------------------+--------------------+--------------------+");
            System.out.println("|    ID    |        Tên Nhân Viên         |    SĐT     |     Email                    |      Địa chỉ       |          Group     |");
            System.out.println("+----------+------------------------------+------------+------------------------------+--------------------+--------------------+");

            for (int i = 0; i < nvList.size(); i++) {
                System.out.printf("|%10s|%30s|%12s|%30s|%20s|%20s|\n", nvList.get(i).getMaNV(), nvList.get(i).getTenNV(), nvList.get(i).getSdtNV(), nvList.get(i).getMailNV(), nvList.get(i).getAddressNV(), nvList.get(i).getGroup());
            }
            System.out.println("+----------+------------------------------+------------+------------------------------+--------------------+--------------------+");
        } else {
            System.err.println("Hiện tại danh sách đang rỗng!");
        }
    }

//    xóa nhân viên
    public void deletePersonnel(String idMe) {
        String id;
        System.out.print("Nhập mã nhân viên cần xóa: ");
        id = input.nextLine().trim();
        if (id.equalsIgnoreCase(idMe)) {
            System.out.println("\n");
            System.err.println("Bạn không thể tự xóa chính mình!");
            System.out.println("\n");
        } else {
            if (checkNV(id) == false) {
                System.err.println("[ ERROR ] Nhân viên có mã " + id + " không tồn tại!");
            } else {
                String ask;
                System.out.println("Bạn chắc chắn muốn xóa nhân viên có  id " + id + " ?");
                System.out.println("Nhập y để đồng ý xóa - ký tự khác để không xóa nữa");
                ask = input.nextLine().trim();
                if (ask.equalsIgnoreCase("y")) {
                    for (int i = 0; i < nvList.size(); i++) {
                        if (id.equalsIgnoreCase(nvList.get(i).getMaNV())) {
                            nvList.remove(i);
                            if (checkGroup(id)) {
                                deleteGroup(id);
                            }
                            System.out.println("\nĐã xóa nhân viên có mã " + id);
                        }
                    }
                    saveNV();
                }else {
                    System.out.println("\nLần sau đừng nghịch lung tung nhé :))");
                }
            }
        }
    }

//    thêm mới một nhân viên
    public void addNewPersonnel() {
        NhanVien nvTemp = new NhanVien();
        String maNV;
        String tenNV;
        String sdtNV;
        String mailNV;
        String passNV;
        String addressNV;
        String group;

        System.out.println("Nhập thông tin nhân viên ( Nếu không có thì để trống - trường có dấu * bắt buộc )");
//        nhập id
        boolean flagId = false;
        do {
            System.out.print("Nhập id *: ");
            maNV = input.nextLine().trim();

//            nvTemp.setMaNV(input.nextLine().trim());
            if (!maNV.isEmpty()) {
                if (checkNV(maNV)) {
                    flagId = false;
                    System.err.println("[ ERROR ] Đã tồn tại nhân viên có id " + maNV);
                } else {
                    nvTemp.setMaNV(maNV);
                    flagId = true;
                }
            } else {
                System.err.println("Mã nhân viên không thể để trống!");
                flagId = false;
            }
        } while (flagId == false);

//        nhập tên
        boolean flagName = false;
        do {
            System.out.print("Nhập tên*: ");
            tenNV = input.nextLine().trim();

            if (!tenNV.isEmpty()) {
                if (check.checkName(tenNV)) {
                    flagName = false;
                } else {
                    flagName = true;
                    nvTemp.setTenNV(tenNV);
                }
            } else {
                System.err.println("[ Error ] Tên bắt buộc phải có!");
                flagName = false;
            }

        } while (flagName == false);

//        nhập sđt
        boolean flagSdt = false;
        do {
            System.out.print("Nhập SĐT*: ");
            sdtNV = input.nextLine().trim();
            if (!sdtNV.isEmpty()) {
                if (check.kiemTraSDT(sdtNV) == false) {
                    flagSdt = false;
                } else {
                    nvTemp.setSdtNV(sdtNV);
                    flagSdt = true;
                }
            } else {
                System.err.println("Không được để trống sđt!");
                flagSdt = false;
            }
        } while (flagSdt == false);

//        nhập mail
        boolean flagMail = false;
        do {
            System.out.print("Nhập mail*: ");
            mailNV = input.nextLine();

            if (!mailNV.isEmpty()) {
                if (check.kiemTraEmail(mailNV) == false) {
                    flagMail = false;
                } else {
                    nvTemp.setMailNV(mailNV);
                    flagMail = true;
                }
            } else {
                System.err.println("[ Error ] Email bắt buộc phải có!");
            }

        } while (flagMail == false);

//        Nhập password
        boolean flagPass = false;

        do {
            System.out.println("Nhập mật khẩu - ( nếu để trống thì mật khẩu mặc định)");
            System.out.print("Mật khẩu: ");
            passNV = input.nextLine().trim();

            if (!passNV.isEmpty()) {
                if (check.checkPass(passNV) == false) {
                    flagPass = false;
                } else {
                    nvTemp.setPassNV(passNV);
                    flagPass = true;
                }
            } else {
                System.err.println("Bạn để mật khẩu mặc định");
                nvTemp.setPassNV("123abc");
                flagPass = true;
            }
        } while (flagPass == false);

//        nhập địa chỉ
        System.out.print("Nhập địa chỉ: ");
        String address;
        address = input.nextLine().trim();
        if (!address.isEmpty()) {
            nvTemp.setAddressNV(address);
        } else {
            nvTemp.setAddressNV(" ");
        }

        String addAD;
        System.out.println("Bạn muốn thêm nhân viên vào admin không? chọn y để đồng ý ký tự khác để từ chối");
        addAD = input.nextLine().trim();
        if (addAD.equalsIgnoreCase("y")) {
            nvTemp.setGroup("admin");
            addGroup(nvTemp.getMaNV());
        } else {
            nvTemp.setGroup("personnel");
        }

        nvList.add(nvTemp);
        saveNV();
        System.out.println("\nThêm thành công!");
    }

//    check sự trùng lặp nhân viên
//    nếu trùng thì trả về true
    public boolean checkNV(String id) {
        for (int i = 0; i < nvList.size(); i++) {
            if (id.equalsIgnoreCase(nvList.get(i).getMaNV())) {
                return true;
            }
        }
        return false;
    }

//    kiểm tra xem nhân viên thuộc nhóm nào
    public boolean checkGroup(String id) {
        ArrayList<String> adGroup;
        File fileAD = new File("Acount_Amin.txt");

//        đọc file
        BufferedReader br = null;
        FileReader fr = null;
        adGroup = new ArrayList();

        // nếu không tìm thấy file thì tạo ra file mới
        if (fileAD.exists() == false) {
            try {
                fileAD.createNewFile();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        try {

            fr = new FileReader("Acount_Amin.txt");
            br = new BufferedReader(fr);

            String strInfo;

            while ((strInfo = br.readLine()) != null) {
                String temp = new String();
                temp = strInfo;

                adGroup.add(temp);
            }

            br.close();

        } catch (Exception e) {
            System.out.println("lỗi" + e);
        }

//        kiểm tra sự tồn tại
        for (int i = 0; i < adGroup.size(); i++) {
            if (id.equalsIgnoreCase(adGroup.get(i))) {
                return true;
            }
        }
        return false;
    }

//    tìm kiếm nhân viên
    public void searchPersonnel() {
        boolean flag = false;
        String search;

        System.out.print("id, sđt, mail - Nhập 1 trong 3 để tìm: ");
        search = input.nextLine().trim();

        System.out.println("+-------------Thông tin tìm được-------------------------+");
        for (int i = 0; i < nvList.size(); i++) {
            if (search.equalsIgnoreCase(nvList.get(i).getMaNV())) {
                nvList.get(i).xuatTTNV();
                flag = true;
                System.out.println();
                System.out.println("+--------------------------------------------------------+");
                System.out.println();
            }

            if (search.equalsIgnoreCase(nvList.get(i).getSdtNV())) {
                nvList.get(i).xuatTTNV();
                flag = true;
                System.out.println();
                System.out.println("+--------------------------------------------------------+");
                System.out.println();
            }

            if (search.equalsIgnoreCase(nvList.get(i).getMailNV())) {
                nvList.get(i).xuatTTNV();
                flag = true;
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

//    lưu lại sau khi thao tác
    public void saveNV() {
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

            for (int i = 0; i < nvList.size(); i++) {
                bw.write(nvList.get(i).getMaNV() + "\t" + nvList.get(i).getTenNV() + "\t" + nvList.get(i).getSdtNV() + "\t" + nvList.get(i).getMailNV() + "\t" + nvList.get(i).getPassNV() + "\t" + nvList.get(i).getAddressNV() + "\t" + nvList.get(i).getGroup());
                bw.newLine();
            }

            bw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
