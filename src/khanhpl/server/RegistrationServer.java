package khanhpl.server;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import javax.swing.JOptionPane;
import khanhpl.interfaces.impl.RegistrationImpl;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
public class RegistrationServer {
    public static void main(String[] args) {
        try {
            RegistrationImpl registrationInterface = new RegistrationImpl();
            LocateRegistry.createRegistry(8888);
            String rmiUrl = "rmi://localhost:8888/RegistrationServer";
            Naming.bind(rmiUrl, registrationInterface);
            
            JOptionPane.showMessageDialog(null, "Server started!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cannot start server!");
        }
    }
}
