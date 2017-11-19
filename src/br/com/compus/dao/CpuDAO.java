package br.com.compus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.compus.jdbc.ConnectionFactory;
import br.com.compus.models.Cpu;

public class CpuDAO {
  private Connection connection;

  public CpuDAO() {
    connection = ConnectionFactory.getConnection();
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
        cpu.setPrice(result.getFloat("price"));
        cpu.setSocket(result.getString("socket"));
        cpus.add(cpu);
      }
      result.close();
      statement.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return cpus;
  }
}
