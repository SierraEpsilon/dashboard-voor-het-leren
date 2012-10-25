package dashboard.uttility;

import java.util.ArrayList;

import dashboard.DataObjects.*;

public class Data {

	private ArrayList<Vak> vakken;
	
	/**
	 * initiate a new data object
	 */
	public Data(){
		vakken = new ArrayList<Vak>();
		createVakken();
	}
	
	/**
	 * @return	...
	 * 		|	vakken
	 */
	public ArrayList<Vak> getVakken() {
		return vakken;
	}
	
	/**
	 * @param 	naam
	 * 			naam van het gezochte vak
	 * @return	...
	 * 		|	if vakken.contains(vak.getName().equals(naam))
	 * 		|		return vak.getMame().equals(naam)
	 * 		|	else
	 * 		|		return null
	 */
	public Vak getVak(String naam){
		for(Vak vak: vakken){
			if(vak.getNaam().equals(naam))
				return vak;
		}
		return null;
	}
	
	/**
	 * adds all possible branches to the database
	 */
	private void createVakken(){
		getVakken().add(new Vak("analyse I"							,6));
		getVakken().add(new Vak("mechanica I"						,5));
		getVakken().add(new Vak("toegepaste algebra"				,5));
		getVakken().add(new Vak("algemene en technische scheikunde"	,7));
		getVakken().add(new Vak("wijsbegeerte"						,3));
		getVakken().add(new Vak("algemene natuurkunde"				,7));
		getVakken().add(new Vak("materiaalkunde"					,3));
		getVakken().add(new Vak("informatica"						,6));
		getVakken().add(new Vak("thermodynamica"					,3));
		getVakken().add(new Vak("elektrische netwerken"				,3));
		getVakken().add(new Vak("analyse II"						,5));
		getVakken().add(new Vak("analyse III"						,3));
		getVakken().add(new Vak("mechanica II"						,5));
		getVakken().add(new Vak("numerieke wiskunde"				,4));
		getVakken().add(new Vak("economie"							,3));
		getVakken().add(new Vak("organische scheikunde"				,3));
		getVakken().add(new Vak("informatie-overdracht"				,5));
		getVakken().add(new Vak("statistiek en kansrekenen"			,3));
	}
}
