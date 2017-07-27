/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App;

import Objects.HoaDon;
import Objects.KhachHang;
import Objects.NhanVien;
import Objects.SanPham;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author NguyenManh
 */
public class BanHangApp {

    Scanner input = new Scanner(System.in);

    NhanVienApp nva = new NhanVienApp();

    KhachHangApp kha = new KhachHangApp();

    SanPhamApp spa = new SanPhamApp();

    public void menuBanHang() {

        String choice;
        do {
            System.out.println("+----------------Quản lý bán hàng-------------------+");
            System.out.println("| [ 1 ]::. Bán hàng!                                |");
            System.out.println("| [ 2 ]::. Xem báo cáo                              |");
            System.out.println("| [ 0 ]::. Thoát!                                   |");
            System.out.println("+---------------------------------------------------+");
            System.out.print("Mời bạn chọn: ");
            choice = input.nextLine();

            switch (choice) {
                case "0": {
                    break;
                }
                case "1": {
                    banHang();
                    break;
                }
                case "2": {
                    System.out.println("Chức năng 2");
                    break;
                }

                default: {
                    System.out.println("Không có chức năng này!");
                }
            }
        } while (choice.equalsIgnoreCase("0") == false);
    }

    public void banHang() {
        ArrayList<HoaDon> gioHangList = new ArrayList();
        Date ngay = new Date();

        String idKhach;
        System.out.print("Nhập mã khách hàng: ");
        idKhach = input.nextLine().trim();

        kha.loadFile();
        spa.loadFileSP();
        nva.loadFileNV();

//        kiểm tra mã khách hàng
        boolean flagCheckKH = false;
        do {
            if (kha.checkMaKH(idKhach)) {
                for (int i = 0; i < kha.khList.size(); i++) {
                    if (idKhach.equalsIgnoreCase(kha.khList.get(i).getMaKH())) {
                        System.out.println();
                        System.out.println("+-Thông tin khách hàng có mã " + idKhach + "---");
                        kha.khList.get(i).xuatTTKH();
                        System.out.println();
                        System.out.println("+----------------------------------------------");
                        flagCheckKH = true;
                        kha.saveKH();
                    }
                }
            } else {
                System.err.println("Khách hàng có mã " + idKhach + " chưa có trong danh sách! Thêm khách hàng vào!");
                kha.addNewCustomer();
                flagCheckKH = false;
            }
        } while (flagCheckKH == false);

//        phần thêm vào giỏ hàng
        boolean flagCheckADD = true;

        do {
            int soLuongHang;
            long tongTien = 0;
            System.out.print("Bán bao nhiều sản phẩm? ");
            soLuongHang = input.nextInt();
            input = new Scanner(System.in);

            for (int i = 0; i < soLuongHang; i++) {
                String maSp;
                System.err.println("Sản phẩm " + (i + 1));
                System.out.print("Mã sản phẩm: ");
                maSp = input.nextLine().trim();

                if (spa.checkSP(maSp) == false) {
                    System.err.println("KHÔNG CÓ SẢN PHẨM CÓ MÃ " + maSp);
                } else {
                    int soLuongSP;
                    System.out.print("Số lượng bao nhiêu? ");
                    soLuongSP = input.nextInt();
                    input = new Scanner(System.in);

//                    kiểm tra xem có bao nhiêu sản phẩm
                    for (int t = 0; t < spa.spList.size(); t++) {
                        if (maSp.equalsIgnoreCase(spa.spList.get(t).getMaSP())) {
                            if (soLuongSP >= spa.spList.get(t).getSoLuongSP()) {
                                System.err.println("Số lượng sản phẩm " + maSp + " không đủ");
                            } else {
                                for (int j = 0; j < spa.spList.size(); j++) {
                                    if (maSp.equalsIgnoreCase(spa.spList.get(j).getMaSP())) {

                                        if (spa.spList.get(j).getSoLuongSP() > soLuongSP) {
                                            HoaDon hoaDon = new HoaDon();
                                            hoaDon.setMaSP(maSp);

                                            hoaDon.setSoLuongMua(soLuongSP);

                                            spa.spList.get(i).setSoLuongSP(spa.spList.get(j).getSoLuongSP() - soLuongSP);
                                            hoaDon.setDonGia(spa.spList.get(j).getDonGia());

                                            hoaDon.setThanhTien(soLuongSP * spa.spList.get(j).getDonGia());

                                            hoaDon.setNgayBan(ngay);

                                            gioHangList.add(hoaDon);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            System.out.println("+--------------------------------------------------------------------------------------------------------------+");
            System.out.println("|                                              GIỎ HÀNG HIỆN TẠI                                               |");
            System.out.println("+-----+--------------------+--------------------+--------------------+--------------------+--------------------+");
            System.out.println("| STT |     Mã Sản phẩm    | Tên Sản phẩm       |   Số lương         |     Đơn giá        |     Thành tiền     |");
            System.out.println("+-----+--------------------+--------------------+--------------------+--------------------+--------------------+");

            for (int i = 0; i < gioHangList.size(); i++) {
                System.out.printf("|%5d|%20s|", i, gioHangList.get(i).getMaSP());

                for (int j = 0; j < spa.spList.size(); j++) {
                    if (gioHangList.get(i).getMaSP().equalsIgnoreCase(spa.spList.get(j).getMaSP())) {
                        System.out.printf("%20s", spa.spList.get(j).getTenSP());

                    }
                }

                tongTien = tongTien + (gioHangList.get(i).getThanhTien());
                System.out.printf("|%20s|%20d|%20d|", gioHangList.get(i).getSoLuongMua(), gioHangList.get(i).getDonGia(), gioHangList.get(i).getThanhTien());
                System.out.println("");
            }

            System.out.println("+--------------------------------------------------------------------------------------------------------------+");
            System.out.printf("|TỔNG TIỀN: %99s|\n", tongTien);
            System.out.println("+--------------------------------------------------------------------------------------------------------------+");
//            kiểm tra xem có nhập không 
            String done;
            System.err.print("Bạn có mua nữa không? Nhập y để tiếp tục mua - n để hủy bỏ giỏ hàng | ký tự khác để thanh toán!");
            done = input.nextLine().trim();
            if (done.equalsIgnoreCase("y") == false && !done.equalsIgnoreCase("n")) {
                System.out.println("\n\n");
                System.out.println("+--------------------------------------------------------------------------------------------------------------+");
                System.out.println("|                                              Hóa đơn mua hàng                                                |");
                System.out.println("+-----+--------------------+--------------------+--------------------+--------------------+--------------------+");
                System.out.println("| STT |     Mã Sản phẩm    | Tên Sản phẩm       |   Số lương         |     Đơn giá        |     Thành tiền     |");
                System.out.println("+-----+--------------------+--------------------+--------------------+--------------------+--------------------+");

                for (int i = 0; i < gioHangList.size(); i++) {
                    System.out.printf("|%5d|%20s|", i, gioHangList.get(i).getMaSP());

                    for (int j = 0; j < spa.spList.size(); j++) {
                        if (gioHangList.get(i).getMaSP().equalsIgnoreCase(spa.spList.get(j).getMaSP())) {
                            System.out.printf("%20s", spa.spList.get(j).getTenSP());

                        }
                    }

                    tongTien = tongTien + (gioHangList.get(i).getThanhTien());
                    System.out.printf("|%20s|%20d|%20d|", gioHangList.get(i).getSoLuongMua(), gioHangList.get(i).getDonGia(), gioHangList.get(i).getThanhTien());
                    System.out.println("");
                }

                System.out.println("+--------------------------------------------------------------------------------------------------------------+");
                System.out.printf("|TỔNG TIỀN: %99s|\n", tongTien);
                System.out.println("+--------------------------------------------------------------------------------------------------------------+");

                spa.saveSP();
                flagCheckADD = false;
                
                System.out.println("\n Cảm ơn bạn đã mua hàng của cửa hàng chúng tôi!");
            }else {
                System.err.println("\nBạn đã hủy bỏ đơn hàng!\n");
                gioHangList = new ArrayList();
                String muaLai;
                System.out.println("Muốn mua lại không? | y để đồng ý , ky tự khác để thoát khỏi bán hàng");
                muaLai = input.nextLine().trim();
                if(muaLai.equalsIgnoreCase("y")) {
                    flagCheckADD = true;
                }else {
                    flagCheckADD = false;
                }
                
            }

        } while (flagCheckADD);

        System.out.println("");

    }
}
