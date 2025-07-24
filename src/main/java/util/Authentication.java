package util;

public class Authentication {
    public static String hashedPass(String pass){
        StringBuilder hashed=new StringBuilder();
        for(char letter:pass.toCharArray()){
            hashed.append((char)letter+1);
        }
        return hashed.toString();
    }
}
