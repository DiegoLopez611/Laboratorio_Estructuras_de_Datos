package EctructurasDeDatos;

public class Nodo {

	private Integer valor;
	private Nodo izquierda;
	private Nodo derecha;

	public Nodo(Integer valor) {
		super();
		this.valor = valor;
	}

	public Nodo() {
		super();
	}

	public Integer getValor() {
		return valor;
	}

	public void setValor(Integer valor) {
		this.valor = valor;
	}

	public Nodo getIzquierda() {
		return izquierda;
	}

	public void setIzquierda(Nodo izquierda) {
		this.izquierda = izquierda;
	}

	public Nodo getDerecha() {
		return derecha;
	}

	public void setDerecha(Nodo derecha) {
		this.derecha = derecha;
	}

	@Override
	public String toString() {
		return "Nodo [valor=" + valor + "]";
	}
	
	


}
