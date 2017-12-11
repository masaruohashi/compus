package br.com.compus.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.compus.models.Computer;

public class ComputerDAO extends BaseDAO{
  public ComputerDAO() {
    super();
  }

  public static ComputerDAO getInstance() {
    return new ComputerDAO();
  }

  public int create(Computer computer) {
    String sql = "INSERT INTO computer (memory_quantity, hd_quantity, memory_id, cpu_id, hd_id, motherboard_id)" +
                 "VALUES (?, ?, ?, ?, ?, ?)";
    try {
      PreparedStatement statement = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      statement.setInt(1, computer.getMemoryQuantity());
      statement.setInt(2, computer.getHdQuantity());
      statement.setInt(3, computer.getHd().getId());
      statement.setInt(4, computer.getCpu().getId());
      statement.setInt(5, computer.getHd().getId());
      statement.setInt(6, computer.getMotherboard().getId());
      statement.execute();
      ResultSet result = statement.getGeneratedKeys();
      result.next();
      return result.getInt(1);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return 0;
  }

  public Computer findById(int id) {
    Computer computer = null;
    String sql = "SELECT * FROM computer WHERE id = ?";
    try {
      PreparedStatement statement = this.connection.prepareStatement(sql);
      statement.setInt(1, id);
      ResultSet result = statement.executeQuery();
      if(result.next()) {
        computer = new Computer();
        computer.setId(id);
        computer.setCpu(CpuDAO.getInstance().findById(result.getInt("cpu_id")));
        computer.setHd(HdDAO.getInstance().findById(result.getInt("hd_id")));
        computer.setMemory(MemoryDAO.getInstance().findById(result.getInt("memory_id")));
        computer.setMotherboard(MotherboardDAO.getInstance().findById(result.getInt("motherboard_id")));
        computer.setHdQuantity(result.getInt("hd_quantity"));
        computer.setMemoryQuantity(result.getInt("memory_quantity"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return computer;
  }
}
