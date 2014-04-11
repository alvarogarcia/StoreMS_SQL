package uo.ri.ui.cash.action;


import java.util.ArrayList;

import java.util.List;
import java.util.Map;

import alb.util.console.Console;
import alb.util.console.Printer;

import uo.ri.conf.ServicesFactory;

import alb.util.BusinessException;


import alb.util.menu.Action;

public class FacturarReparacionesAction implements Action {

	
	
	@Override
	public void execute() throws BusinessException {
		List<Long> idsAveria = new ArrayList<Long>();
		
		// pedir las averias a incluir en la factura
		do {
			Long id = Console.readLong("ID de averia");
			idsAveria.add(id);
		} while ( masAverias() );

		try{
			//CashServiceImpl cSI = new CashServiceImpl();
			Map<String, Object> invoice = ServicesFactory.getCashService().createInvoiceFor(idsAveria);
			
			printInvoice(invoice);
		
		} catch (RuntimeException e) {
			Printer.printRuntimeException(e);
			//Console.println("ERROR al facturar la reparación (ex de FacturarReparacionesAction)");
		}
		

	}

	private void printInvoice(Map<String, Object> invoice) {
		Console.printf("Factura nº: %d\n", invoice.get("numeroFactura"));
		Console.printf("\tFecha: %1$td/%1$tm/%1$tY\n", invoice.get("fechaFactura"));
		Console.printf("\tTotal: %.2f �\n", invoice.get("totalFactura"));
		Console.printf("\tIva: %.1f %% \n", invoice.get("iva"));
		Console.printf("\tTotal con IVA: %.2f �\n", invoice.get("importe"));
		
	}

	
	

	

	


	
	private boolean masAverias() {
		return Console.readString("��A��adir m��s averias? (s/n) ").equalsIgnoreCase("s");
	}

}
