package uo.ri.conf;

import uo.ri.persistence.FacturasGateway;
import uo.ri.persistence.MecanicosGateway;
import uo.ri.persistence.impl.FacturasGatewayImpl;
import uo.ri.persistence.impl.MecanicosGatewayImpl;



public class PersistenceFactory {

	/**
	 * @return Devuelve el Gateway de mecanicos
	 */
	public static MecanicosGateway getMecanicosGateway(){
		return new MecanicosGatewayImpl();
		
	}
	
	/**
	 * @return Devuelve el Gatewayd de Facturas para interacturar desde 
	 */
	public static FacturasGateway getFacturasGateway(){
		return new FacturasGatewayImpl();
	}
	
}
