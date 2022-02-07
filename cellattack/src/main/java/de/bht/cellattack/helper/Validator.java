package de.bht.cellattack.helper;

public class Validator {

    public static boolean isValidEmail(String email) {
      String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
      return email.matches(regex);
   }
    
}
