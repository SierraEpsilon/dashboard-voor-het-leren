package org.hci_eu.singletons;

import org.hci_eu.model.Car;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;



public class RegisteringClassesSingleton {
	
	private static RegisteringClassesSingleton _singletonObject;
	
	private RegisteringClassesSingleton() {
		ObjectifyService.register(Car.class);
		
	}
	
	public static synchronized RegisteringClassesSingleton getSingletonObject() {
		if (_singletonObject == null) {
			_singletonObject = new RegisteringClassesSingleton();
		}
		return _singletonObject;
	}
}
