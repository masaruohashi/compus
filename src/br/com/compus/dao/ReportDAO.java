package br.com.compus.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.compus.models.Report;

public class ReportDAO extends BaseDAO {
  public ReportDAO() {
    super();
  }
  
  public static ReportDAO getInstance() {
    return new ReportDAO();
  }
  
  public List<Report> getGeneralReport(int month, int year) throws SQLException {
    List<Report> generalReports = new ArrayList<Report>();
    try {
      String sql = "SELECT date, COUNT(id) as quantidade, SUM(final_price) as total FROM `order` " +
          "WHERE MONTH(date) = ? " +
          "AND YEAR(date) = ? " + 
          "GROUP BY date ";
      PreparedStatement statement = this.connection.prepareStatement(sql);
      statement.setInt(1, month);
      statement.setInt(2, year);
      ResultSet result = statement.executeQuery();
      while(result.next()) {
        //Convert Date SQL type to Calendar
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(result.getDate("date").getTime());
        
        Report report = new Report();
        report.setDate(calendar);
        report.setNumSales(result.getInt("quantidade"));
        report.setTotalPrice(result.getDouble("total"));
        generalReports.add(report);        
      }
    } catch(SQLException e) {
      e.printStackTrace();
    }
    return generalReports;
  }
  
  public List<Report> getIndividualReport(int employeeId) throws SQLException {
    List<Report> individualReports = new ArrayList<Report>();
    try {
      String sql = "SELECT date, COUNT(id) as quantidade, SUM(final_price) as total FROM `order` " +
          "WHERE employee_id = ? " +
          "GROUP BY date ";
      PreparedStatement statement = this.connection.prepareStatement(sql);
      statement.setInt(1, employeeId);
      ResultSet result = statement.executeQuery();
      while(result.next()) {
        //Convert Date SQL type to Calendar
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(result.getDate("date").getTime());
        
        Report report = new Report();
        report.setDate(calendar);
        report.setNumSales(result.getInt("quantidade"));
        report.setTotalPrice(result.getDouble("total"));
        individualReports.add(report);        
      }
    } catch(SQLException e) {
      e.printStackTrace();
    }
    return individualReports;
  }
}
