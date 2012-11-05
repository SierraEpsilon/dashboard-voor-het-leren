package org.hci_eu.model;

import javax.persistence.Id;
import javax.persistence.Transient;

public class Car
{
    @Id
	public Long id;
    String vin;
    public String color;
    @Transient String doNotPersist;

    private Car() {}
    
    public Car(String vin, String color)
    {
        this.vin = vin;
        this.color = color;
    }
}