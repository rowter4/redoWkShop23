package sg.edu.nus.iss.workshop23Redo.models;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.util.MultiValueMap;

public class ConversionUtils {
    
    public static Bff convert(MultiValueMap<String,String> payload) {
        Bff bff2 = new Bff();
        bff2.setEmail(payload.getFirst("email"));
        bff2.setName(payload.getFirst("name")); 
        bff2.setPassphrase(payload.getFirst("passphrase"));
        bff2.setPhone(payload.getFirst("phone"));
        bff2.setStatus(payload.getFirst("status"));

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dob = format.parse(payload.getFirst("dob"));
            bff2.setDob(dob);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return bff2;
    }
}
