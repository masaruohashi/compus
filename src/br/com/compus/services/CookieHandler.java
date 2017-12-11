package br.com.compus.services;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.compus.dao.CpuDAO;
import br.com.compus.dao.HdDAO;
import br.com.compus.dao.MemoryDAO;
import br.com.compus.dao.MotherboardDAO;
import br.com.compus.models.Computer;
import br.com.compus.models.Cpu;
import br.com.compus.models.Hd;
import br.com.compus.models.Memory;
import br.com.compus.models.Motherboard;

public class CookieHandler {
  public static Map<String, Cookie> getCookieMap(Cookie[] cookies) {
    Map<String, Cookie> cookieMap = new HashMap<>();
    if(cookies != null) {
      for(Cookie cookie : cookies) {
        cookieMap.put(cookie.getName(), cookie);
      }
    }
    return cookieMap;
  }

  public static Cookie findByName(Cookie[] cookies, String name) {
    Map<String, Cookie> cookieMap = getCookieMap(cookies);
    return cookieMap.get(name);
  }

  public static List<Cpu> getCpusByCookie(Cookie cpusCookie) {
    List<Cpu> cpus = new ArrayList<Cpu>();
    try {
      if(cpusCookie != null) {
        String[] cpuIds = URLDecoder.decode(cpusCookie.getValue(), "utf8").split(";");
        for(String id : cpuIds) {
          cpus.add(CpuDAO.getInstance().findById(Integer.parseInt(id)));
        }
      }
    } catch (SQLException | UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return cpus;
  }

  public static List<Motherboard> getMotherboardsByCookie(Cookie motherboardsCookie) {
    List<Motherboard> motherboards = new ArrayList<Motherboard>();
    try {
      if(motherboardsCookie != null) {
        String[] motherboardIds = URLDecoder.decode(motherboardsCookie.getValue(), "utf8").split(";");
        for(String id : motherboardIds) {
          motherboards.add(MotherboardDAO.getInstance().findById(Integer.parseInt(id)));
        }
      }
    } catch (SQLException | UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return motherboards;
  }

  public static List<Memory> getMemoriesByCookie(Cookie memoriesCookie) {
    List<Memory> memories = new ArrayList<Memory>();
    try {
      if(memoriesCookie != null) {
        String[] memoriesIds = URLDecoder.decode(memoriesCookie.getValue(), "utf8").split(";");
        for(String id : memoriesIds) {
          memories.add(MemoryDAO.getInstance().findById(Integer.parseInt(id)));
        }
      }
    } catch (SQLException | UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return memories;
  }

  public static List<Hd> getHdsByCookie(Cookie hdsCookie) {
    List<Hd> hds = new ArrayList<Hd>();
    try {
      if(hdsCookie != null) {
        String[] hdsIds = URLDecoder.decode(hdsCookie.getValue(), "utf8").split(";");
        for(String id : hdsIds) {
          hds.add(HdDAO.getInstance().findById(Integer.parseInt(id)));
        }
      }
    } catch (SQLException | UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return hds;
  }

  public static List<Computer> getComputersByCookie(Cookie computersCookie) {
    List<Computer> computers = new ArrayList<Computer>();
    try {
      if(computersCookie != null) {
        String[] computersPartsStrings = URLDecoder.decode(computersCookie.getValue(), "utf8").split(";");
        for(String computerPartsString : computersPartsStrings) {
          String[] computerPartsIds = computerPartsString.split("\\|");
          if(computerPartsIds.length == 6) {
            Computer computer = new Computer();
            computer.setMotherboard(MotherboardDAO.getInstance().findById(Integer.parseInt(computerPartsIds[0])));
            computer.setCpu(CpuDAO.getInstance().findById(Integer.parseInt(computerPartsIds[1])));
            computer.setHd(HdDAO.getInstance().findById(Integer.parseInt(computerPartsIds[2])));
            computer.setHdQuantity(Integer.parseInt(computerPartsIds[3]));
            computer.setMemory(MemoryDAO.getInstance().findById(Integer.parseInt(computerPartsIds[4])));
            computer.setMemoryQuantity(Integer.parseInt(computerPartsIds[5]));
            computers.add(computer);
          }
        }
      }
    } catch (SQLException | UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return computers;
  }
}
