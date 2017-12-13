package br.com.compus.services;

import java.util.HashMap;
import java.util.Map;

public class DataValidator {

  public static Map<String, String> validate(String name, String cpf, String email, String role, String address, String phone, String pwd, String pwd2) {
    Map<String, String> map = new HashMap<String, String>();
    String invalidField = null;
    
    map.put("valid", "true");

    if (name.matches(".*\\d+.*") || name.isEmpty()) {
      map.put("valid", "false");
      invalidField = "nome";
    }
    else if (cpf.isEmpty() || cpf.length() != "222.222.222-22".length()) {
      map.put("valid", "false");
      invalidField = "cpf";
    }
    else if (!email.matches(".*@.*\\..*")) {
      map.put("valid", "false");
      invalidField = "e-mail";
    }
    else if (address.isEmpty()) {
      map.put("valid", "false");
      invalidField = "endereco";
    }
    else if (phone.isEmpty() || phone.length() != "(22) 2222-2222".length()) {
      map.put("valid", "false");
      invalidField = "telefone";
    }
    else if (role.matches("administrador") && pwd.isEmpty()) {
      map.put("valid", "false");
      invalidField = "senha";
    }
    else if (!pwd.matches(pwd2)) {
      map.put("valid", "false");
      invalidField = "senha";
    }
    
    map.put("msg", "Insira um " + invalidField + " valido&name=" + name +
            "&cpf=" + cpf + "&email=" + email + "&role=" + role + "&address=" + address + "&phone=" + phone);
    
    return map;
  }

}
