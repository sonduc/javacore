package App;

import Objects.SanPham;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author NguyenManh
 */
public class SanPhamApp {

    Scanner input = new Scanner(System.in);
    ArrayList<SanPham> spList = new ArrayList();
    private String file = "SanPham.txt";
    File f = new File(file);

    public void menuSP() {
        String choice;

        boolean flagSmall = false;
        boolean flagBig = false;

        for (int i = 0; i < spList.size(); i++) {
            if (spList.get(i).getSoLuongSP() < 10) {
                flagSmall = true;
            }

            if (spList.get(i).getSoLuongSP() > 200) {
                flagBig = true;
            }
        }

        if (flagSmall == true) {
            System.err.println("[ Warning ] - Trong kho của bạn có một số sản phẩm gần hết hàng, hãy lấy thêm hàng!");
        }

        if (flagBig == true) {
            System.err.println("[ Warning ] - Trong kho của bạn có một số sản phẩm tồn khó quá nhiều, bạn nên thanh lý bớt!");
        }

        do {

            System.out.println("\n\n\n");
            System.out.println("+---------------------------QUẢN LÝ SẢN PHẨM-----------------------------------------+");
            System.out.println("| [ 1 ]::. Xem danh sách toàn bộ sản phẩm                                            |");
            System.out.println("| [ 2 ]::. Tìm kiếm sản phầm                                                         |");
            System.out.println("| [ 3 ]::. Thêm mới một sản phẩm                                                     |");
            System.out.println("| [ 4 ]::. Sửa thông tin sản phẩm                                                    |");
            System.out.println("| [ 5 ]::. Thêm nhiều sản phẩm                                                       |");
            System.out.println("| [ 6 ]::. Xóa sản phẩm                                                              |");
            System.out.println("| [ 0 ]::. Trở về menu chính!                                                        |");
            System.out.println("+------------------------------------------------------------------------------------+");
            System.out.print("Mời bạn chọn: ");
            choice = input.nextLine();

            switch (choice) {
                case "0": {
                    saveSP();
                    break;
                }
                case "1": {
                    showAllProduct();
                    break;
                }
                case "2": {
                    searchProduct();
                    break;
                }
                case "3": {
                    addNewProduct();
                    break;
                }
                case "4": {
                    editInfoProduct();
                    break;
                }
                case "5": {
                    addNewNProduct();
                    break;
                }
                case "6": {
                    deleteProduct();
                    break;
                }
                default: {
                    System.out.println(" không có chức năng này nhé");
                }
            }
        } while (choice.equalsIgnoreCase("0") == false);
    }

//    xem toàn bộ sản phẩm
    public void showAllProduct() {
        if (spList.size() > 0) {
            System.out.println("\n\n\n");
            System.out.println("+------------------------------------------------------------------------------------+");
            System.out.println("|                                   DANH SÁCH SẢN PHẨM                               |");
            System.out.println("+----------+------------------------------+---------------------+--------------------+");
            System.out.println("|    ID    |         Tên sản phẩm         |        Số lượng     | Đơn giá (Nghìn VNĐ)|");
            System.out.println("+----------+------------------------------+---------------------+--------------------+");

            for (int i = 0; i < spList.size(); i++) {
                System.out.printf("|%10s|%30s|%21s|%20s|\n", spList.get(i).getMaSP(), spList.get(i).getTenSP(), spList.get(i).getSoLuongSP(), spList.get(i).getDonGia());
            }

            System.out.print("+----------+------------------------------+---------------------+--------------------+");
            System.out.println();
        } else {
            System.err.println("Hiện tại danh sách đang rỗng!");
        }
    }
//    kết thúc function xem toàn bộ sản phẩm

//    function để load file sản phẩm
    public void loadFileSP() {
        BufferedReader br = null;
        FileReader fr = null;
        spList = new ArrayList();

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

                SanPham sp = new SanPham();

                sp.setMaSP(srtInfoArr[0]);
                sp.setTenSP(srtInfoArr[1]);
                sp.setSoLuongSP(parseInt(srtInfoArr[2]));
                sp.setDonGia(parseInt(srtInfoArr[3]));

                spList.add(sp);
            }

            br.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    //kết thúc function load file sản phẩm

//    xóa sản phẩm
    public void deleteProduct() {
        String id;
        // kiểm tra có tồn tại không
        do {
            System.out.print("Nhập mã sản phẩm muốn xóa: ");
            id = input.nextLine();

            if (checkSP(id) == false) {
                System.err.println("[ ERROR ] Sản phẩm có mã " + id + " không tồn tại!");
            }
        } while (checkSP(id) == false);

        for (int i = 0; i < spList.size(); i++) {
            if (id.equalsIgnoreCase(spList.get(i).getMaSP())) {
                spList.remove(i);
            }
        }

        System.out.println("Xóa thành công sản phẩm có id " + id);
    }
//    kết thúc xóa sản phẩm

//    Thêm mới một sản phẩm
    public void addNewProduct() {
        SanPham spTemp = new SanPham();

        String maSP;
        String tenSP;
        int soLuongSP;
        long donGia;

        boolean flagMa = false;
        boolean flagTen = false;
        boolean flagSoLuong = false;

        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println("Nhập thông tin sản phẩm cần thêm! có * là bắt buộc");

//        nhập mã
        do {
            System.out.print("Mã sản phẩm: ");
            maSP = input.nextLine();

            if (!maSP.isEmpty()) {
                if (checkSP(maSP)) {
                    System.err.println("[ ERROR ] Sản phẩm có mã " + maSP + " đã tồn tại!");
                    flagMa = false;
                } else {
                    flagMa = true;
                    spTemp.setMaSP(maSP);
                }
            } else {
                System.err.println("[ ERROR ] Không được để mã sản phẩm trống!");
                flagMa = false;
            }

        } while (flagMa == false);

//        Nhập tên
        do {
            System.out.print("Tên sản phẩm: ");
            tenSP = input.nextLine().trim();

            if (!tenSP.isEmpty()) {
                spTemp.setTenSP(tenSP);
                flagTen = true;
            } else {
                System.err.println("Tên sản phẩm không thể để trống!");
            }

        } while (flagTen == false);

        do {
            System.out.print("Số lượng: ");
            spTemp.setSoLuongSP(input.nextInt());
            input = new Scanner(System.in);

            if (spTemp.getSoLuongSP() <= 0) {
                System.err.println("[ ERROR ] Số lượng sản phẩm phải lớn hơn 0!");
            }
        } while (spTemp.getSoLuongSP() <= 0);

        do {
            System.out.print("Đơn giá: ");
            spTemp.setDonGia(input.nextLong());
            input = new Scanner(System.in);

            if (spTemp.getDonGia() <= 0) {
                System.err.println("[ ERROR ] Đơn giá phải lớn hơn 0!");
            }
        } while (spTemp.getDonGia() <= 0);

        spList.add(spTemp);

        System.out.println("Thêm mới 1 sản phẩm thành công!");
        System.out.println("--------------------------------------------------------------------------------------");
    }
//    kết thúc thêm mới sản phẩm

//    Thêm mói nhiều sản phẩm cung lúc
    public void addNewNProduct() {
        int n;
        do {
            System.out.println("\n\n\n");
            System.out.println("Thêm bao nhiêu sản phẩm? ");
            n = input.nextInt();
            input = new Scanner(System.in);

            if (n <= 0) {
                System.err.println("Thêm số sản phẩm phải lớn hơn 0");
            }
        } while (n <= 0);

        for (int i = 0; i < n; i++) {
            System.out.println("\nSản phẩm thứ " + (i + 1));
            addNewProduct();
        }
        System.out.println("Đã thêm mới " + n + " sản phẩm!");
        System.out.println("\n\n");
    }
//    kết thúc thêm mới nhiều sản phẩm

//    Sửa thông tin sản phẩm
    public void editInfoProduct() {
        String idEdit;

        do {
            System.out.print("Nhập mã sản phẩm muốn sửa: ");
            idEdit = input.nextLine().trim();

            if (checkSP(idEdit) == false) {
                System.out.println("Sản phẩm này không tồn tại! Hãy nhập lại");
            }
        } while (checkSP(idEdit) == false);

        System.out.println("+-------------------Thông tin về sản phẩm sắp chỉnh sửa-------------------+");

        for (int i = 0; i < spList.size(); i++) {
            if (idEdit.equalsIgnoreCase(spList.get(i).getMaSP())) {
                spList.get(i).xuatTTSP();
            }
        }
        System.out.println("\n+-------------------------------------------------------------------------+");
        System.out.println("\n");

        // xem muốn thay đổi thông tin gì?
//        String choice;
        String maSP;
        String tenSP;
        int soLuongSP;
        long donGia;

        boolean flagma = false;
        boolean flagTen = false;
        boolean flagSoLuong = false;
        boolean flagDonGia = false;

        System.out.println("Nhập thông tin cần sửa - Nếu không sửa thì để trống!");

//        nhập mã
        do {
            System.out.print("Mã ");
            maSP = input.nextLine().trim();

            if (!maSP.isEmpty()) {
                if (checkSP(maSP)) {
                    System.err.println("[ ERROR ] Sản phẩm có mã " + maSP + " đã tồn tại!");
                    flagma = false;
                } else {
                    flagma = true;
                }
            } else {
                System.err.println("Bạn giữ nguyên mã sản phẩm -  thay đổi thông tin khác");
                flagma = true;
            }

        } while (flagma == false);

//        Nhập tên
        System.out.print("Tên sản phẩm: ");
        tenSP = input.nextLine().trim();

//        Nhập số lượng
        String askSL;
        System.out.println("Bạn muốn thay đổi số lượng không? - nếu muốn nhập y - không muốn nhập ký tự khác");
        askSL = input.nextLine().trim();

        if (askSL.equalsIgnoreCase("y")) {

            boolean flagEX = false;

            do {
                System.out.print("Nhập số lượng: ");
                soLuongSP = input.nextInt();

                if (soLuongSP > 0) {
                    flagEX = true;
                } else {
                    flagEX = false;
                }
            } while (flagEX == false);

            flagSoLuong = true;
        } else {
            soLuongSP = 0;
        }

//           nhập đơn gia
        String askDG;
        System.out.println("Bạn muốn thay đổi đơn giá không? - nếu muốn nhập y - không muốn nhập ký tự khác");
        askDG = input.nextLine().trim();

        if (askDG.equalsIgnoreCase("y")) {

            boolean flagEX = false;
            do {
                System.out.print("Nhập đơn giá: ");
                donGia = input.nextLong();

                if (donGia > 0) {
                    flagEX = true;
                } else {
                    System.err.println("[ Error ] Đơn giá phải lớn hơn 0!");
                    flagEX = false;
                }
            } while (flagEX == false);

            flagDonGia = true;
        } else {
            donGia = 0;
        }

//        set vào
        for (int i = 0; i < spList.size(); i++) {
            if (idEdit.equalsIgnoreCase(spList.get(i).getMaSP())) {
                if (!maSP.isEmpty()) {
                    spList.get(i).setMaSP(maSP);
                }

                if (!tenSP.isEmpty()) {
                    spList.get(i).setTenSP(tenSP);
                }

                if (flagSoLuong == true) {
                    spList.get(i).setSoLuongSP(soLuongSP);
                }

                if (flagDonGia == true) {
                    spList.get(i).setDonGia(donGia);
                }
            }
        }

        System.out.println("\n Cập nhật thông tin thành công!");

    }
//    kết thúc sửa thông tin sản phẩm

//    check sự trùng lặp trong sản phẩm
    public boolean checkSP(String id) {
        for (int i = 0; i < spList.size(); i++) {
            if (id.equalsIgnoreCase(spList.get(i).getMaSP())) {
                return true;
            }
        }
        return false;
    }
//    kết thúc check

//    lưu lại sau khi thực hiện thao tác
    public void saveSP() {
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

            for (int i = 0; i < spList.size(); i++) {
                bw.write(spList.get(i).getMaSP() + "\t" + spList.get(i).getTenSP() + "\t" + spList.get(i).getSoLuongSP() + "\t" + spList.get(i).getDonGia());
                bw.newLine();
            }

            bw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

//    tìm kiếm sản phẩm theo mã
    public void searchProduct() {
        boolean flag = false;
        String search;

        System.out.print("Nhập mã hoặc tên sản phẩm để tìm: ");
        search = input.nextLine();

        System.out.println("+-------------Thông tin tìm được-------------------------+");
        for (int i = 0; i < spList.size(); i++) {
            if (search.equalsIgnoreCase(spList.get(i).getMaSP())) {
                spList.get(i).xuatTTSP();
                flag = true;
                System.out.println();
                System.out.println("+--------------------------------------------------------+");
                System.out.println();
            }
            if (search.equalsIgnoreCase(spList.get(i).getTenSP())) {
                spList.get(i).xuatTTSP();
                flag = true;
                System.out.println();
                System.out.println("+--------------------------------------------------------+");
                System.out.println();
            }
        }

        if (flag == false) {
            System.err.println("Không tìm thấy kết quả nào hợp lệ");
        }
        System.out.println("\n\nEnter để tiếp tục! ");
        input.nextLine();
    }
}
