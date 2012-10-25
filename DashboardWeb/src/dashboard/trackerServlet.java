package dashboard;

import java.io.IOException;
import java.util.*;
import com.google.appengine.api.datastore.*;
import javax.servlet.http.*;
import javax.servlet.jsp.PageContext;


public class trackerServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
		String trackListName = req.getParameter("trackListName");
		String vak = req.getParameter("vak");
		Date date = new Date();
		Key trackKey = KeyFactory.createKey("trackingList",trackListName);
		Entity tracking = new Entity("tracking", trackKey);
		tracking.setProperty("vak", vak);
		tracking.setProperty("start", date);
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        datastore.put(tracking);
        
        resp.sendRedirect("/tracker.jsp?trackListName=" + trackListName);
    }
}

