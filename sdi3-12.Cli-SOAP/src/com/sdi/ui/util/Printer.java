package com.sdi.ui.util;

import alb.util.console.Console;

import com.sdi.ws.Rating;
import com.sdi.ws.Trip;

public class Printer {
//
//	public static void printInvoice(Factura invoice) {
//
//		double importeConIVa = invoice.getImporte();
//		double iva = (Double) invoice.getIva();
//		double importeSinIva = importeConIVa / (1 + iva / 100);
//
//		Console.printf("Factura nº: %d\n", invoice.getNumero());
//		Console.printf("\tFecha: %1$td/%1$tm/%1$tY\n", invoice.getFecha());
//		Console.printf("\tTotal: %.2f €\n", importeSinIva);
//		Console.printf("\tIva: %.1f %% \n", invoice.getIva());
//		Console.printf("\tTotal con IVA: %.2f €\n", invoice.getImporte());
//		Console.printf("\tEstado: %s\n", invoice.getStatus());
//	}
//
//	public static void printPaymentMeans(List<MedioPago> medios) {
//		Console.println();
//		Console.println("Medios de pago disponibles");
//
//		Console.printf("\t%s \t%-8.8s \t%s \n", "ID", "Tipo", "Acumulado");
//		for (MedioPago medio : medios) {
//			// Console.println( medio.toFormatedString() );
//			Console.print(medio.toString());
//		}
//	}
//
//	public static void printRepairing(Averia rep) {
//
//		Console.printf("\t%d \t%-40.40s \t%td/%<tm/%<tY \t%-12.12s \t%.2f\n", rep.getId(), rep.getDescripcion(),
//				rep.getFecha(), rep.getStatus(), rep.getImporte());
//	}
//
//	public static void printMechanic(Mecanico m) {
//
//		Console.printf("\t%d %-10.10s %-25.25s %-25.25s\n", m.getId(), m.getDni(), m.getNombre(), m.getApellidos());
//	}
//
//	public static void printAveriaCompleta(Averia a) {
//		Console.printf("\tID:%d\n", a.getId());
//		Console.printf("\tDescripcion:%s\n", a.getDescripcion());
//		Console.printf("\tFecha:%1$td/%1$tm/%1$tY\n", a.getFecha());
//		if (a.getImporte() == null)
//			Console.print("\tImporte:0\n");
//		else
//			Console.printf("\tImporte:%.2f\n", a.getImporte());
//		Console.printf("\tEstado:%s\n\n", a.getStatus().toString());
//
//	}
//
//	public static void printAveria(Averia a) {
//		Console.printf("\t%s\n", a.getDescripcion());
//		Console.printf("\t%1$td/%1$tm/%1$tY\n", a.getFecha());
//		if (a.getImporte() == null)
//			Console.print("\t0\n");
//		else
//			Console.printf("\t%.2f\n", a.getImporte());
//		Console.printf("\t%s\n", a.getStatus().toString());
//
//	}
//
//	public static void printVehiculo(Vehiculo v) {
//		Console.printf("\t%d %-10.10s %-25.25s %-25.25s\n", v.getId(), v.getMatricula(), v.getMarca(), v.getModelo());
//	}
//
//	public static void printTipoVehiculo(TipoVehiculo tv) {
//		Console.printf("\t%d %-20.20s\n", tv.getId(), tv.getNombre());
//
//	}
//
//	public static void printCurso(Curso c) {
//		Console.printf("\tID:%d  %-20.20s %.2f %-25.25s\n", c.getId(), c.getNombre(), c.getHoras(), c.getDescripcion());
//
//	}
//
//	public static void printPorcentaje(Porcentaje p) {
//		Console.printf("\t\t%-20.20s %-10.10s\n", p.getTipo().getNombre(), p.getPorcentaje());
//
//	}
//
//	public static void printAsistencia(Asistencia a) {
//		Console.printf("\t\t%-10.10s", a.getMecanico().getNombre());
//		Console.printf("%1$td/%1$tm/%1$tY ", a.getFechaFinal());
//		Console.printf("%-10.10s", a.getPorcentaje());
//		Console.printf("%-10.10s\n", a.getEstado().toString());
//
//		// order by a.fechafinal
//	}
	public static void printRating(Rating r){
		Console.print("ID: " + r.getId());
		Console.print("Comentario: " + r.getComment());
	}

	public static void printComentarioViaje(Trip t, Rating r) {
		Console.print("Destino: " + t.getDestination());
		Console.print("ID promotor: " + t.getPromoterId());
		Console.print("ID valorado: " + r.getSeatAboutUserId());
		Console.print("Valoracion: " + r.getValue());
		Console.print("Comentario: " + t.getComments());
		Console.println();
	}

}
