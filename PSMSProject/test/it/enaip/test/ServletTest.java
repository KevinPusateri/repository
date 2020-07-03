package it.enaip.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import it.enaip.corso.servlet.StuffController;
public class ServletTest{
	
	@Mock
    HttpServletRequest request;

	@Mock
    HttpServletResponse response;
	
	@Before
	public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void testServlet() throws IOException, ServletException {
		when(request.getParameter("op")).thenReturn("new");
		 StringWriter sw = new StringWriter();
         PrintWriter pw = new PrintWriter(sw);
        when(response.getWriter()).thenReturn(pw);
	 
	        
	        StuffController myServlet =new StuffController();
	        myServlet.doGet(request, response);
	        String result = sw.getBuffer().toString().trim();
	        assertEquals(result, new String("new"));
	}
	
	
}
