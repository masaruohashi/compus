package br.com.compus.dao;

public class ReportDAO extends BaseDAO {
  public ReportDAO() {
    super();
  }
  
  public static ReportDAO getInstance() {
    return new ReportDAO();
  }
  
}
