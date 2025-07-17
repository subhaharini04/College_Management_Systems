package util;

public class Authtication {
    public static String hashedPass(String pass){
        StringBuilder hashed=new StringBuilder();
        for(char letter:pass.toCharArray()){
            hashed.append((char)letter+1);
        }
        return hashed.toString();
    }
}
