package br.com.compus.dao;

import java.sql.Date;
import java.util.Calendar;

import br.com.compus.models.Report;

public class ReportDAO extends BaseDAO {
  public ReportDAO() {
    super();
  }
  
  public static ReportDAO getInstance() {
    return new ReportDAO();
  }
  
  public Report getGeneralReport(Calendar calendar) {
    Date date = new Date(calendar.get(Calendar.YEAR), calendar.get(calendar.get(Calendar.MONTH)), 0));
    String sql = "SELECT date, COUNT(id), SUM(final_price) FROM order " +
                 "GROUP BY date";
  }
  
}
