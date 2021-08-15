package beans;

import java.util.ArrayList;

public class ArregloCarrito {
	private ArrayList<CarritoDTO> car;
	// Constructor
	public ArregloCarrito() {
	 car = new ArrayList <CarritoDTO> ();
	}
	public int tamaño() {
	 return car.size();
	}
	public CarritoDTO obtener(int i) {
	 return car.get(i);
	}
}
