package qltc.PresentationLayer;

import org.mindrot.jbcrypt.BCrypt;

public class test {
    public static void main(String[]args){
                      String password = "admin123";
                String hash = BCrypt.hashpw(password, BCrypt.gensalt(12));
                System.out.println("BCrypt hash: " + hash);
                // $2a$12$EaJoe/0PJD4psr2WMmsocehcgLu61MzdeMDhpFHyvxkLhCTKtZOda
    }
}
