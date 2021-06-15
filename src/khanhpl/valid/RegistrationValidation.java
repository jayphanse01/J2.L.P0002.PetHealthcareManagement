/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanhpl.valid;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;
import khanhpl.dto.RegistrationDTO;
import khanhpl.interfaces.RegistrationInterface;

/**
 *
 * @author Admin
 */
public class RegistrationValidation {

    private RegistrationInterface registrationInterface;

    private void getConnectionToServer() {
        try {
            String rmiUrl = "rmi://localhost:8888/RegistrationServer";
            registrationInterface = (RegistrationInterface) Naming.lookup(rmiUrl);
        } catch (Exception e) {
            System.out.println("Error while connecting to server...");
            e.printStackTrace();
        }
    }

    public boolean checkRegisID(String regisID) {
        boolean chkRegisID = false;
        if (regisID.matches("\\w+") && regisID.length() > 0 && regisID.length() <= 10) {
            chkRegisID = true;
        }

        return chkRegisID;
    }
    
    public boolean checkDupRegisID(String regisID) throws RemoteException {
        getConnectionToServer();
        boolean chkDupRegisID = true;
        ArrayList<RegistrationDTO> registrationList = registrationInterface.findAllRegistrations();

        for (int i = 0; i < registrationList.size(); i++) {
            String id = registrationList.get(i).getRegistrationID();
            if (id.equals(regisID)) {
                chkDupRegisID = false;
            }
        }
        return chkDupRegisID;
    }

    public boolean checkFullName(String fullName) {
        boolean chkFullName = false;
        if (fullName.length() > 0 && fullName.length() <= 50) {
            chkFullName = true;
        }
        return chkFullName;
    }

    public boolean checkAge(String ageText) {
        boolean chkAge = false;
        try {
            int age = Integer.parseInt(ageText);
            if (age > 0 && age < 150) {
                chkAge = true;
            }
        } catch (NumberFormatException e) {
            chkAge = false;
        }

        return chkAge;
    }

    public boolean checkEmail(String email) {
        boolean chkEmail = false;
        if (email.length() > 0 && email.length() <= 30 && email.matches("^[a-zA-Z][\\w-]+@([\\w]+\\.[\\w]+|[\\w]+\\.[\\w]{2,}\\.[\\w]{2,})$")) {
            chkEmail = true;
        }
        return chkEmail;
    }

    public boolean checkPhone(String phone) {
        boolean chkPhone = false;
        if (phone.length() > 0 && phone.length() <= 15 && phone.matches("\\d+")) {
            chkPhone = true;
        }
        return chkPhone;
    }

    public boolean checkNumberOfPet(String numberOfPetText) {
        boolean chkNumberOfPet = false;
        try {
            int numberOfPet = Integer.parseInt(numberOfPetText);
            if (numberOfPet > 0) {
                chkNumberOfPet = true;
            }
        } catch (NumberFormatException e) {
            chkNumberOfPet = false;
        }
        return chkNumberOfPet;
    }
    
    public boolean checkAddress (String address){
        boolean chkAddr = false;
        if (address.length()>0) {
            chkAddr = true;
        }
        return chkAddr;
    }
    
    public boolean checkSymptoms (String symtoms){
        boolean chkSym = false;
        if (symtoms.length()>0) {
            chkSym = true;
        }
        return chkSym;
    }
}
