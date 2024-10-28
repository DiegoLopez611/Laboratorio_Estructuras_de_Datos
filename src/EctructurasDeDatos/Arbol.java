package EctructurasDeDatos;

import java.util.LinkedList;
import java.util.Queue;

public class Arbol {

	private String nombre;
	private Nodo raiz;
	private int peso;

	public Arbol(String nombre, Nodo raiz, int peso) {
		super();
		this.nombre = nombre;
		this.raiz = raiz;
		this.peso = peso;
	}

	public Arbol() {
        this.raiz = null;
        this.peso = 0;
    }

	public void agregar(Integer valor) {
        raiz = agregarRecursivo(raiz, valor);
    }


	private Nodo agregarRecursivo(Nodo nodo, Integer valor) {

		if(nodo == null){
			return new Nodo(valor);
		}

		if(valor.equals(nodo.getValor())){
			return nodo;
		}
		if(valor < nodo.getValor()){
			nodo.setIzquierda(agregarRecursivo(nodo.getIzquierda(), valor));
		}else{
			nodo.setDerecha(agregarRecursivo(nodo.getDerecha(), valor));
		}
		peso++;
		return nodo;
    }

	public void vaciarArbol(){
		raiz = null;
		peso = 0;
	}

	public Arbol clonarArbol() {
        Arbol nuevoArbol = new Arbol();
        nuevoArbol.setRaiz(clonarRecursivo(this.raiz));
        return nuevoArbol;
    }

    private Nodo clonarRecursivo(Nodo nodo) {
        if (nodo == null) {
            return null;
        }

        Nodo nuevoNodo = new Nodo(nodo.getValor());
        nuevoNodo.setIzquierda(clonarRecursivo(nodo.getIzquierda()));
        nuevoNodo.setDerecha(clonarRecursivo(nodo.getDerecha()));
        return nuevoNodo;
    }

    public int obtenerAltura() {
        return calcularAltura(raiz);
    }

    private int calcularAltura(Nodo nodo) {
        if (nodo == null) {
            return 0;
        }
        int alturaIzquierda = calcularAltura(nodo.getIzquierda());
        int alturaDerecha = calcularAltura(nodo.getDerecha());
        return Math.max(alturaIzquierda, alturaDerecha) + 1;
    }

    public int obtenerNivelElemento(int valor) {
        return obtenerNivel(raiz, valor, 1);
    }

    private int obtenerNivel(Nodo nodo, int valor, int nivelActual) {
        if (nodo == null) {
            return -1; // El valor no se encuentra en el árbol
        }
        if (nodo.getValor() == valor) {
            return nivelActual;
        }
        int nivelIzquierda = obtenerNivel(nodo.getIzquierda(), valor, nivelActual + 1);
        if (nivelIzquierda != -1) {
            return nivelIzquierda;
        }
        return obtenerNivel(nodo.getDerecha(), valor, nivelActual + 1);
    }

    public int contarHojas() {
        return contarHojasRecursivo(raiz);
    }

    private int contarHojasRecursivo(Nodo nodo) {
        if (nodo == null) {
            return 0;
        }
        if (nodo.getIzquierda() == null && nodo.getDerecha() == null) {
            return 1;
        }
        return contarHojasRecursivo(nodo.getIzquierda()) + contarHojasRecursivo(nodo.getDerecha());
    }

    public Integer obtenerMinimo() {

    	if (raiz == null) {
            return null;
        }
        Nodo actual = raiz;

        while (actual.getIzquierda() != null) {
            actual = actual.getIzquierda();
        }
        return actual.getValor();
    }

    public void eliminarElemento(int valor) {
        raiz = eliminarRecursivo(raiz, valor);
    }

    private Nodo eliminarRecursivo(Nodo nodo, int valor) {
        if (nodo == null) {
            return null;
        }

        if (valor < nodo.getValor()) {
            nodo.setIzquierda(eliminarRecursivo(nodo.getIzquierda(), valor));
        } else if (valor > nodo.getValor()) {
            nodo.setDerecha(eliminarRecursivo(nodo.getDerecha(), valor));
        } else {
            // Caso 1: El nodo es una hoja (sin hijos)
            if (nodo.getIzquierda() == null && nodo.getDerecha() == null) {
                return null;
            }

            // Caso 2: El nodo tiene un solo hijo
            if (nodo.getIzquierda() == null) {
                return nodo.getDerecha();
            } else if (nodo.getDerecha() == null) {
                return nodo.getIzquierda();
            }

            // Caso 3: El nodo tiene dos hijos
            Nodo minNodo = obtenerMinimo(nodo.getDerecha());
            nodo.setValor(minNodo.getValor());
            nodo.setDerecha(eliminarRecursivo(nodo.getDerecha(), minNodo.getValor()));
        }

        return nodo;
    }

    private Nodo obtenerMinimo(Nodo nodo) {
        if (nodo.getIzquierda() == null) {
            return nodo;
        } else {
            return obtenerMinimo(nodo.getIzquierda());
        }
    }

    public String recorridoAmplitud() {
        if (raiz == null) {
            return "";
        }
        StringBuilder resultado = new StringBuilder();
        Queue<Nodo> cola = new LinkedList<>();
        cola.add(raiz);

        while (!cola.isEmpty()) {
            Nodo nodoActual = cola.poll();
            resultado.append(nodoActual.getValor()).append(" ");

            if (nodoActual.getIzquierda() != null) {
                cola.add(nodoActual.getIzquierda());
            }
            if (nodoActual.getDerecha() != null) {
                cola.add(nodoActual.getDerecha());
            }
        }

        return resultado.toString().trim();
    }

    public boolean sonIdenticos(Arbol otroArbol) {
        return compararArboles(this.raiz, otroArbol.raiz);
    }

    private boolean compararArboles(Nodo nodo1, Nodo nodo2) {
        if (nodo1 == null && nodo2 == null) {
            return true;
        }
        if (nodo1 == null || nodo2 == null) {
            return false;
        }
        return (nodo1.getValor() == nodo2.getValor()) &&
                compararArboles(nodo1.getIzquierda(), nodo2.getIzquierda()) &&
                compararArboles(nodo1.getDerecha(), nodo2.getDerecha());
    }

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Nodo getRaiz() {
		return raiz;
	}

	public void setRaiz(Nodo raiz) {
		this.raiz = raiz;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	@Override
	public String toString() {
		return nombre;
	}



}
