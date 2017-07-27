package Main;

import App.NhanVienApp;
import Objects.NhanVien;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author NguyenManh
 */
public class Login {

    public String login(String nickName, String Pass) {

        Scanner input = new Scanner(System.in);
        boolean flag = false;
        /*Kết thúc khai báo*/

        if (checkLoginAd(nickName, Pass) == true) {
            return "admin";
        }

//            System.err.println("\tĐăng nhập thất bại!");
//            System.out.println("+=========================================+");
        if (checkLoginPersonnel(nickName, Pass) == true) {
            return "personnel";
        }

        return "hacker";
    }

    public boolean checkLoginAd(String nick, String pass) {
        NhanVienApp nva = new NhanVienApp();
        nva.loadFileNV();
        ArrayList<NhanVien> nvl = new ArrayList();

        if (nick.equalsIgnoreCase("root") && pass.equalsIgnoreCase("root")) {
            return true;
        } else {
            try {
                InputStream is = null;
                is = new FileInputStream("Acount_Amin.txt");

                Reader read = new InputStreamReader(is);

                BufferedReader br = new BufferedReader(read);

                String s = null;
                int i = 0;

                int j = 0;
                String temp = "";
                String strAcount[];
                
                while ((s = br.readLine()) != null) {

                    temp = temp +  s + "\t";

                }
                br.close();
                strAcount = temp.split("\t");
                
                for (int k = 0; k < strAcount.length; k++) {
                    if (nick.equalsIgnoreCase(strAcount[k])) {
                        if (checkPassNV(nick, pass)) {
                            return true;
                        } else {
                            return false;
                        }
                    }
                }

            } catch (Exception e) {
                System.out.println("");
            }
        }

        return false;
    }

    public boolean checkLoginPersonnel(String id, String pass) {
        File f = new File("NhanVien.txt");
        BufferedReader br = null;
        FileReader fr = null;
        ArrayList<NhanVien> nvList = new ArrayList();

        if (f.exists() == false) {
            try {
                f.createNewFile();
            } catch (Exception e) {
                System.out.println(e);
            }
            return false;
        }

        try {

            fr = new FileReader(f);
            br = new BufferedReader(fr);

            String strInfo;

            while ((strInfo = br.readLine()) != null) {
                String srtInfoArr[] = strInfo.split("\t");

                NhanVien nv = new NhanVien();

                nv.setMaNV(srtInfoArr[0]);
                nv.setPassNV(srtInfoArr[4]);
                nv.setGroup(srtInfoArr[6]);

                nvList.add(nv);

                for (int i = 0; i < nvList.size(); i++) {
                    if (nvList.get(i).getMaNV().equalsIgnoreCase(id) && nvList.get(i).getPassNV().equalsIgnoreCase(pass) && nvList.get(i).getGroup().equalsIgnoreCase("personnel")) {
                        return true;
                    }
                }
            }

            br.close();

        } catch (Exception e) {
            System.out.println(e);
        }

        return false;
    }

    public boolean checkPassNV(String id, String pass) {
        File f = new File("NhanVien.txt");
        BufferedReader br = null;
        FileReader fr = null;
        ArrayList<NhanVien> nvList = new ArrayList();

        if (f.exists() == false) {
            try {
                f.createNewFile();
            } catch (Exception e) {
                System.out.println(e);
            }
            return false;
        }

        try {

            fr = new FileReader(f);
            br = new BufferedReader(fr);

            String strInfo;

            while ((strInfo = br.readLine()) != null) {
                String srtInfoArr[] = strInfo.split("\t");

                NhanVien nv = new NhanVien();

                nv.setMaNV(srtInfoArr[0]);
                nv.setPassNV(srtInfoArr[4]);

                nvList.add(nv);

                for (int i = 0; i < nvList.size(); i++) {
                    if (id.equalsIgnoreCase(nvList.get(i).getMaNV()) && pass.equalsIgnoreCase(nvList.get(i).getPassNV())) {
                        return true;
                    }
                }
            }

            br.close();

        } catch (Exception e) {
            System.out.println(e);
        }

        return false;
    }
}
