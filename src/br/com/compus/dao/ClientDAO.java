package br.com.compus.dao;


import br.com.compus.jdbc.ConnectionFactory;
import br.com.compus.models.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO extends BaseDAO {
  public ClientDAO() {
    super();
  }

  public static ClientDAO getInstance() {
    return new ClientDAO();
  }

  public List<Client> getAll() throws SQLException {
    List<Client> clients = new ArrayList<Client>();

    try {
      String sql = "SELECT * FROM client";
      PreparedStatement statement = this.connection.prepareStatement(sql);
      ResultSet result = statement.executeQuery();
      while(result.next()) {
        Client client = new Client();
        client.setName(result.getString("name"));
        client.setCpf(result.getString("cpf"));
        client.setEmail(result.getString("email"));
        client.setPhone(result.getString("phone"));
        clients.add(client);
      }
      result.close();
      statement.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return clients;
  }

  public boolean delete(int id) throws SQLException {
    try {
      String sql = "DELETE FROM client WHERE id = ?";
      PreparedStatement statement = this.connection.prepareStatement(sql);
      statement.setInt(1, id);
      statement.execute();
      statement.close();
      return true;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }

  public boolean create(Client client) throws SQLException {
    String sql = "INSERT INTO client (name, email, cpf, address, phone)" +
            "VALUES (?, ?, ?, ?, ?)";
    try {
      PreparedStatement statement = this.connection.prepareStatement(sql);
      statement.setString(1, client.getName());
      statement.setString(2, client.getEmail());
      statement.setString(3, client.getCpf());
      statement.setString(4, client.getAddress());
      statement.setString(5, client.getPhone());
      statement.execute();
      statement.close();
      return true;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }
}