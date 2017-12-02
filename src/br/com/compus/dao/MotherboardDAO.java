package br.com.compus.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.compus.models.Cpu;
import br.com.compus.models.Motherboard;

public class MotherboardDAO extends BaseDAO{
  public MotherboardDAO() {
    super();
  }

  public static MotherboardDAO getInstance() {
    return new MotherboardDAO();
  }

  public List<Motherboard> getAll() throws SQLException {
    List<Motherboard> motherboards = new ArrayList<Motherboard>();
    try {
      String sql = "SELECT * FROM motherboard";
      PreparedStatement statement = this.connection.prepareStatement(sql);
      ResultSet result = statement.executeQuery();
      while(result.next()) {
        Motherboard motherboard = new Motherboard();
        motherboard.setId(result.getInt("id"));
        motherboard.setName(result.getString("name"));
        motherboard.setPrice(result.getDouble("price"));
        motherboard.setSocket(result.getString("socket"));
        motherboard.setMemorySlots(result.getInt("memory_slots"));
        motherboard.setMemoryType(result.getString("memory_type"));
        motherboards.add(motherboard);
      }
      result.close();
      statement.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return motherboards;
  }

  public Motherboard findById(int id) throws SQLException {
    Motherboard motherboard = null;
    try {
      String sql = "SELECT * FROM motherboard WHERE id=?";
      PreparedStatement statement = this.connection.prepareStatement(sql);
      statement.setInt(1, id);
      ResultSet result = statement.executeQuery();
      if(result.next()) {
        motherboard = new Motherboard();
        motherboard.setId(result.getInt("id"));
        motherboard.setName(result.getString("name"));
        motherboard.setPrice(result.getDouble("price"));
        motherboard.setSocket(result.getString("socket"));
        motherboard.setMemorySlots(result.getInt("memory_slots"));
        motherboard.setMemoryType(result.getString("memory_type"));
        result.close();
        statement.close();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return motherboard;
  }
}
