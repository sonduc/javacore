package Main;

import App.BanHangApp;
import App.KhachHangApp;
import App.NhanVienApp;
import App.SanPhamApp;
import common.WelCome;
import java.util.Scanner;

/**
 *
 * @author NguyenManh
 */
public class Main {

    public static void main(String[] args) {
        /* Khai báo */
        Scanner input = new Scanner(System.in);
        Login login = new Login();
        WelCome wcome = new WelCome();

        KhachHangApp kha = new KhachHangApp();
        NhanVienApp nva = new NhanVienApp();
        SanPhamApp spa = new SanPhamApp();
        BanHangApp bha = new BanHangApp();

        /*Kết thúc khai báo*/
        String lg;
        String id;
        String pass;
        System.out.println("-------------------------------HỆ THỐNG QUẢN LÝ BÁN HÀNG-------------------------------");
        do {
            System.out.println("\n\n");
            System.out.println("+================ĐĂNG NHẬP================+");
            System.out.print("|  NHẬP ID: ");
            id = input.nextLine();

            System.out.print("|  MẬT KHẨU: ");
            pass = input.nextLine();

            lg = login.login(id, pass);

            if (lg.equalsIgnoreCase("admin")) {
                System.err.println("\tĐăng nhập thành công!");
                System.out.println("+=========================================+");

                // Chào mừng
                wcome.Load();
                System.out.println("\n\n\n--------------------------------------------------------------------------------------");
                wcome.wcome("CHÀO MỪNG ĐẾN VỚI HỆ THỐNG QUẢN LÝ BÁN HÀNG");
                // Kết thúc chào mừng
                // làm việc với mục quản lý
                String choice;
                do {
                    System.out.println();
                    System.out.println("+=========================================+");
                    System.out.println("|       o0o_DANH_MỤC_CHỨC_NĂNG_o0o        |");
                    System.out.println("+=========================================+");
                    System.out.println("| [ 1 ]::. Quản lý nhân viên              |");
                    System.out.println("+-----------------------------------------+");
                    System.out.println("| [ 2 ]::. Quản lý khách hàng             |");
                    System.out.println("+-----------------------------------------+");
                    System.out.println("| [ 3 ]::. Quản lý sản phẩm               |");
                    System.out.println("+-----------------------------------------+");
                    System.out.println("| [ 4 ]::. Quản lý bán hàng               |");
                    System.out.println("+-----------------------------------------+");
                    System.out.println("| [ 5 ]::. Cá nhân                        |");
                    System.out.println("+-----------------------------------------+");
                    System.out.println("| [ 0 ]::. Thoát!                         |");
                    System.out.println("+=========================================+");
                    System.out.print("Mời bạn chọn chức năng: ");
                    choice = input.nextLine();
                    System.out.println("--------------------------------------------------------------------------------------\n\n");
                    switch (choice) {
                        case "0": {
                            break;
                        }
                        case "1": {
                            nva.loadFileNV();
                            nva.menuNV(id);
                            break;
                        }
                        case "2": {
                            kha.loadFile();
                            kha.menuKH();
                            break;
                        }
                        case "3": {
                            spa.loadFileSP();
                            spa.menuSP();
                            break;
                        }
                        case "4": {
                            bha.menuBanHang();
                            break;
                        }
                        case "5": {
                            if (id.equalsIgnoreCase("root")) {
                                System.err.println("Không thể xem thông tin tài khoản root!");
                            } else {
                                nva.loadFileNV();
                                nva.menuMe(id);
                            }
                            break;
                        }

                        default: {
                            System.out.println("");
                            System.err.println("Hiện tại hệ thống không có chức năng này!");
                            System.out.println("Mời bạn chọn lại");
                            System.out.println("");
                        }
                    }
                } while (choice.equalsIgnoreCase("0") == false);
            }

//            kiểm tra nhân viên
            if (lg.equalsIgnoreCase("personnel")) {
                System.err.println("\tĐăng nhập thành công!");
                // Chào mừng
                wcome.Load();
                System.out.println("\n\n\n--------------------------------------------------------------------------------------");
                wcome.wcome("CHÀO MỪNG ĐẾN VỚI HỆ THỐNG QUẢN LÝ BÁN HÀNG");
                // Kết thúc chào mừng
                // làm việc với mục quản lý
                String choice;
                do {
                    System.out.println();
                    System.out.println("+=========================================+");
                    System.out.println("|       o0o_DANH_MỤC_CHỨC_NĂNG_o0o        |");
                    System.out.println("+=========================================+");
                    System.out.println("+-----------------------------------------+");
                    System.out.println("| [ 1 ]::. Quản lý khách hàng             |");
                    System.out.println("+-----------------------------------------+");
                    System.out.println("| [ 2 ]::. Quản lý sản phẩm               |");
                    System.out.println("+-----------------------------------------+");
                    System.out.println("| [ 3 ]::. Quản lý bán hàng               |");
                    System.out.println("+-----------------------------------------+");
                    System.out.println("| [ 4 ]::. Cá nhân                        |");
                    System.out.println("+-----------------------------------------+");
                    System.out.println("| [ 0 ]::. Thoát!                         |");
                    System.out.println("+=========================================+");
                    System.out.print("Mời bạn chọn chức năng: ");
                    choice = input.nextLine();
                    System.out.println("--------------------------------------------------------------------------------------\n\n");
                    switch (choice) {
                        case "0": {
                            break;
                        }
                        case "1": {
                            kha.loadFile();
                            kha.menuKH();
                            break;
                        }
                        case "2": {
                            spa.loadFileSP();
                            spa.menuSP();
                            break;
                        }
                        case "3": {
                            bha.menuBanHang();
                            break;
                        }
                        case "4": {
                            nva.loadFileNV();
                            nva.menuMe(id);
                            break;
                        }
                        default: {
                            System.out.println("");
                            System.err.println("Hiện tại hệ thống không có chức năng này!");
                            System.out.println("Mời bạn chọn lại");
                            System.out.println("");
                        }
                    }
                } while (choice.equalsIgnoreCase("0") == false);
            }

            if (lg.equalsIgnoreCase("hacker")) {
                System.err.println("\tĐăng nhập thất bại!");
                System.out.println("+=========================================+");
            }
        } while (lg.equalsIgnoreCase("hacker"));
    }
}
