/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NguyenManh
 */
public class WelCome {

    public void Load() {

        try {
            System.out.print("LOAD: [ > ] 0%");
            Thread.sleep(1200);
            System.out.print("\b\b");
            
            System.out.print("LOAD: [ => ] 10%");;
            Thread.sleep(1000);
            System.out.print("\b\b\b\b\b\b");

            System.out.print("LOAD: [ ====> ] 40%");
            Thread.sleep(1500);
            System.out.print("\b\b\b\b\b\b\b");
            
            System.out.print("LOAD: [ =====> ]50%");
            Thread.sleep(800);
            System.out.print("\b\b\b");
            
            System.out.print("LOAD: [ ======== >]80%");
            Thread.sleep(2000);
            System.out.print("\b\b\b");
            
            System.out.print("LOAD: [ ==========> ]100% OK");
            Thread.sleep(2000);
            System.out.print("\b\b\b\b");

        } catch (Exception e) {
            System.out.println(e);
        }
        
        System.out.print("\b\b\b\b");
    }
    
    public void wcome(String description) {
        String loiChao[] = description.split(" ");
        
        for(int i = 0; i< loiChao.length; i++) {
            try {
                System.out.print(loiChao[i] + " ");
                Thread.sleep(500);
            } catch (Exception e) {
                System.err.println("Lỗi " + e);
            }
        }
        System.out.println("");
    }
}
