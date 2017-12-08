package br.com.compus.controller;

import java.util.HashMap;
import java.util.Map;

public class ValidateInputData {
  Map<String, String> map = new HashMap<String, String>();

  public Map<String, String> validateClient(String name, String cpf, String email, String address, String phone) {
    map.put("valid", "true");

    if (name.matches(".*\\d+.*") || name.isEmpty()) {
      map.put("valid", "false");
      map.put("url", "/cliente/novo?msg=Insira um nome valido&name=" +
              name + "&cpf=" + cpf + "&email=" + email + "&address=" + address + "&phone=" + phone);
    }
    else if (cpf.isEmpty() || cpf.length() != "222.222.222-22".length()) {
      map.put("valid", "false");
      map.put("url", "/cliente/novo?msg=Insira um cpf valido&name=" +
              name + "&cpf=" + cpf + "&email=" + email + "&address=" + address + "&phone=" + phone);
    }
    else if (!email.matches(".*@.*\\..*")) {
      map.put("valid", "false");
      map.put("url", "/cliente/novo?msg=Insira um e-mail valido&name=" +
              name + "&cpf=" + cpf + "&email=" + email + "&address=" + address + "&phone=" + phone);
    }
    else if (address.isEmpty()) {
      map.put("valid", "false");
      map.put("url", "/cliente/novo?msg=Insira um endereco valido&name=" +
              name + "&cpf=" + cpf + "&email=" + email + "&address=" + address + "&phone=" + phone);
    }
    else if (phone.isEmpty() || phone.length() != "(22) 2222-2222".length()) {
      map.put("valid", "false");
      map.put("url", "/cliente/novo?msg=Insira um tel valido&name=" +
              name + "&cpf=" + cpf + "&email=" + email + "&address=" + address + "&phone=" + phone);
    }

    return map;
  }

  public Map<String, String> validateEmployee(String name, String cpf, String email, String role, String address, String phone) {
    map.put("valid", "true");

    if (name.matches(".*\\d+.*") || name.isEmpty()) {
      map.put("valid", "false");
      map.put("url", "/funcionario/novo?msg=Insira um nome valido&name=" +
              name + "&cpf=" + cpf + "&email=" + email + "&role=" + role + "&address=" + address + "&phone=" + phone);
    }
    else if (cpf.isEmpty() || cpf.length() != "222.222.222-22".length()) {
      map.put("valid", "false");
      map.put("url", "/funcionario/novo?msg=Insira um cpf valido&name=" +
              name + "&cpf=" + cpf + "&email=" + email + "&role=" + role + "&address=" + address + "&phone=" + phone);
    }
    else if (!email.matches("..*@.*\\...*.")) {
      map.put("valid", "false");
      map.put("url", "/funcionario/novo?msg=Insira um e-mail valido&name=" +
              name + "&cpf=" + cpf + "&email=" + email + "&role=" + role + "&address=" + address + "&phone=" + phone);
    }
    else if (address.isEmpty()) {
      map.put("valid", "false");
      map.put("url", "/funcionario/novo?msg=Insira um endereco valido&name=" +
              name + "&cpf=" + cpf + "&email=" + email + "&role=" + role + "&address=" + address + "&phone=" + phone);
    }
    else if (phone.isEmpty() || phone.length() != "(22) 2222-2222".length()) {
      map.put("valid", "false");
      map.put("url", "/funcionario/novo?msg=Insira um tel valido&name=" +
              name + "&cpf=" + cpf + "&email=" + email + "&role=" + role + "&address=" + address + "&phone=" + phone);
    }

    return map;
  }
}
