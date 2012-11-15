package dashboard.model;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;
import dashboard.model.*;

public class OwnOfy {

	private static Objectify ofy;
	static {
        ObjectifyService.register(Student.class);
        ofy = ObjectifyService.begin();
    }

    public static Objectify ofy() {
        return ofy;
    }
	
}
