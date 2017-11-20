package br.com.compus.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
        hd.setPrice(result.getFloat("price"));
        hd.setCapacity(result.getString("capacity"));
        hds.add(hd);
      }
      result.close();
      statement.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return hds;
  }
}
