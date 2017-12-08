package br.com.compus.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.compus.models.Cpu;
import br.com.compus.models.Hd;

public class HdDAO extends BaseDAO {
  public HdDAO() {
    super();
  }

  public static HdDAO getInstance() {
    return new HdDAO();
  }

  public List<Hd> getAll() throws SQLException {
    List<Hd> hds = new ArrayList<Hd>();
    try {
      String sql = "SELECT * FROM hd";
      PreparedStatement statement = this.connection.prepareStatement(sql);
      ResultSet result = statement.executeQuery();
      while(result.next()) {
        Hd hd = new Hd();
        hd.setId(result.getInt("id"));
        hd.setName(result.getString("name"));
        hd.setPrice(result.getDouble("price"));
        hd.setCapacity(result.getString("capacity"));
        hd.setImageUrl(result.getString("image_url"));
        hds.add(hd);
      }
      result.close();
      statement.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return hds;
  }

  public Hd findById(int id) throws SQLException {
    Hd hd = null;
    try {
      String sql = "SELECT * FROM hd WHERE id=?";
      PreparedStatement statement = this.connection.prepareStatement(sql);
      statement.setInt(1, id);
      ResultSet result = statement.executeQuery();
      if(result.next()) {
        hd = new Hd();
        hd.setId(result.getInt("id"));
        hd.setName(result.getString("name"));
        hd.setPrice(result.getDouble("price"));
        hd.setCapacity(result.getString("capacity"));
        hd.setImageUrl(result.getString("image_url"));
        result.close();
        statement.close();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return hd;
  }
}
