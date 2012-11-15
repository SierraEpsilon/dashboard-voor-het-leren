import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;
import dashboard.model.*;

public class OwnOfy {

	static {
        ObjectifyService.register(Student.class);
    }

    public static Objectify ofy() {
        return ObjectifyService.begin();
    }
	
}
