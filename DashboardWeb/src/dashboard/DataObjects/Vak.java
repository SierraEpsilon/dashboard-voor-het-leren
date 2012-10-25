package dashboard.DataObjects;

public class Vak implements Cloneable{

	private final String naam;
	private final int studiePunten;
	private int timeStudied;
	
	/**
	 * initieert een vak-object
	 * @param 	naam
	 * 			de naam van het vak
	 * @effect	...
	 * 		|	setNaam(naam)
	 * * @effect	...
	 * 		|	setStudiepunten(studiePunten)
	 */
	public Vak(String naam,int studiepunten){
		this.naam = naam;
		this.studiePunten = studiepunten;
	}
	
	/**
	 * @return 	de naam van het vak
	 * 		|	this.naam
	 */
	public String getNaam() {
		return naam;
	}
	
	/**
	 * @return	...
	 * 		|	this.timeStudied
	 */
	public int getTimeStudied() {
		return timeStudied;
	}
	
	/**
	 * @return	...
	 * 		|	studiePunten
	 */
	public int getStudiePunten() {
		return studiePunten;
	}
	
	/**
	 * @param 	timeStudied
	 * 			the new number of time studied
	 * @post	...
	 * 		|	this.timeStudied = hoursStudied
	 */
	private void setTimeStudied(int timeStudied) {
		this.timeStudied = timeStudied;
	}

	/**
	 * @param 	add
	 * 			the hours you want to add
	 * @effect	...
	 * 		|	setTimeStudied(getTimeStudied() + add)
	 */
	public void addTime(int add){
		setTimeStudied(getTimeStudied() + add);
	}
	
	/**
	 * @return	...
	 * 		|	getStudiePunten()*25
	 */
	public int getRecomendedHours(){
		return getStudiePunten()*25;
	}
	
	/**
	 * @return	...
	 * 		|	getTimeStudied()/getRecomendedHours()
	 */
	public double getRecFractionAchieved(){
		return getTimeStudied()/getRecomendedHours();
	}
	
	/**
	 * clone the Vak
	 * @return 	...
	 * 		|	Vak clonedVak = new Vak(getNaam(),getStudiePunten())
	 * 		|	clonedVak.setTimeStudied(0)
	 * 		|	return clonedVak
	 */
	public Vak clone(){
		Vak clonedVak = new Vak(getNaam(),getStudiePunten());
		clonedVak.setTimeStudied(0);
		return clonedVak;
	}
}
