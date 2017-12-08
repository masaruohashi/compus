package br.com.compus.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.compus.models.Cpu;

public class CpuDAO extends BaseDAO{
  public CpuDAO() {
    super();
  }

  public static CpuDAO getInstance() {
    return new CpuDAO();
  }

  public List<Cpu> getAll() throws SQLException {
    List<Cpu> cpus = new ArrayList<Cpu>();
    try {
      String sql = "SELECT * FROM cpu";
      PreparedStatement statement = this.connection.prepareStatement(sql);
      ResultSet result = statement.executeQuery();
      while(result.next()) {
        Cpu cpu = new Cpu();
        cpu.setId(result.getInt("id"));
        cpu.setName(result.getString("name"));
        cpu.setPrice(result.getDouble("price"));
        cpu.setSocket(result.getString("socket"));
        cpu.setImageUrl(result.getString("image_url"));
        cpus.add(cpu);
      }
      result.close();
      statement.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return cpus;
  }

  public Cpu findById(int id) throws SQLException {
    Cpu cpu = null;
    try {
      String sql = "SELECT * FROM cpu WHERE id=?";
      PreparedStatement statement = this.connection.prepareStatement(sql);
      statement.setInt(1, id);
      ResultSet result = statement.executeQuery();
      if(result.next()) {
        cpu = new Cpu();
        cpu.setId(result.getInt("id"));
        cpu.setName(result.getString("name"));
        cpu.setPrice(result.getDouble("price"));
        cpu.setSocket(result.getString("socket"));
        cpu.setImageUrl(result.getString("image_url"));
        result.close();
        statement.close();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return cpu;
  }
}
