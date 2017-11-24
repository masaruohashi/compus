package br.com.compus.servlets;

import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import br.com.compus.dao.EmployeeDAO;

public class EmployeeServletTest extends Mockito {
  private HttpServletRequest request;
  private HttpServletResponse response;
  private RequestDispatcher requestDispatcher;
  private EmployeeDAO employeeDao;
  
  @Before
  public void setUp() {
    request = mock(HttpServletRequest.class);
    response = mock(HttpServletResponse.class);
    requestDispatcher = mock(RequestDispatcher.class);
    employeeDao = mock(EmployeeDAO.class);
  }

	@Test
	public void testExceptionOnParseInt() throws SQLException {
	  when(request.getParameter("id")).thenReturn("a"); // send an ID that cannot be cannot be parsed
	  boolean catchException = false;
	  try {
	    new EmployeeServlet().doGet(request, response);
	  } catch (Exception e){
	    catchException = true;
	  }
	  Assert.assertTrue("catchException should be true", catchException);
	}
	
	@Test
	public void testExceptionOnFindEmployee() throws SQLException {
	  when(request.getParameter("id")).thenReturn("1");
	  when(employeeDao.findById(1)).thenReturn(null);
	  boolean catchException = false;
	  try {
	    new EmployeeServlet().doGet(request, response);
	  } catch (Exception e) {
	    catchException = true;
	  }
	  Assert.assertFalse("catchException should be true", catchException);
	}
	

}
