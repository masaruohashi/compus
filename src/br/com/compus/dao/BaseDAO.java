package br.com.compus.dao;

import java.sql.Connection;

import br.com.compus.jdbc.ConnectionFactory;

abstract class BaseDAO {
  protected Connection connection;

  public BaseDAO() {
    this.connection = ConnectionFactory.getConnection();
  }
}
