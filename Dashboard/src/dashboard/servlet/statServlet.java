package dashboard.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import com.google.appengine.labs.repackaged.org.json.JSONArray;

import dashboard.model.Course;
import dashboard.model.Student;
import dashboard.registry.StudentRegistry;

public class statServlet extends HttpServlet {

	private static final long serialVersionUID = -6566808443793850095L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession session = req.getSession();
		PrintWriter writer = resp.getWriter();
		if(session.getAttribute("student") != null)
	        writer.println("UNAUTHORIZED");
		else{
			JSONArray root = new JSONArray();
			try {
				JSONObject obj = new JSONObject();
				obj.put("name", "Studiepunten");
				root.put(obj);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			writer.println(root.toString());
		}
			
	}
}
