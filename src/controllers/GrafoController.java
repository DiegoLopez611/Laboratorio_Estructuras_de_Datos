package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import EctructurasDeDatos.Arbol;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class GrafoController {

    @FXML
    private Button btnAltura;

    @FXML
    private Button btnComparar;

    @FXML
    private Button btnCrearNodo;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnNuevoArbol;

    @FXML
    private Button btnImprimir;

    @FXML
    private Button btnNivelElemento;

    @FXML
    private Button btnNumeroHojas;

    @FXML
    private Button btnPanelComparar;

    @FXML
    private Button btnPanelCrearGrafo;

    @FXML
    private Button btnPanelMetodos;

    @FXML
    private Button btnRecorridoAmplitud;

    @FXML
    private Button btnValorPequeño;

    @FXML
    private ComboBox<Arbol> comboArboles;

    @FXML
    private ComboBox<Arbol> comboArboles1;

    @FXML
    private ComboBox<Arbol> comboArboles2;

    @FXML
    private AnchorPane grafoPane;

    @FXML
    private AnchorPane panelComparar;

    @FXML
    private AnchorPane panelMetodos;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TextField textFieldValor;

    @FXML
    private TextField textNombre;

    @FXML
    private TextField txtValorEliminar;

    @FXML
    private TextField txtValorNodo;

    @FXML
    private Label txtRespuesta;

    @FXML
    private Label txtRespuestaComparar;

    private List<Circle> nodos = new ArrayList<>();
    private Map<Line, Circle[]> aristasMap = new HashMap<>();
    private Circle nodoSeleccionado = null;
    private List<Arbol> listaArboles = new ArrayList<>();
    private Arbol arbol = new Arbol();
    private ObservableList<Arbol> ListaObservableArboles;

    @FXML
    void crearNuevoArbol(ActionEvent event) {

    	Arbol aux = arbol.clonarArbol();
    	aux.setNombre(textNombre.getText());
    	listaArboles.add(aux);
    	arbol.vaciarArbol();
    	System.out.println(listaArboles.toString());


    	nodos.clear();
    	aristasMap.clear();
    	grafoPane.getChildren().clear();

    	textNombre.setText("");
    	textFieldValor.setText("");
    }

    @FXML
    void btnAlturaClicked(ActionEvent event) {

    	Arbol seleccionado = comboArboles.getValue();
    	if(seleccionado == null){
    		txtRespuesta.setText("Debe seleccionar un Arbol");
    	}else{
    		txtRespuesta.setText("Altura = "+ seleccionado.obtenerAltura());
    	}
    }

    @FXML
    void btnCompararClicked(ActionEvent event) {
    	Arbol seleccionado1 = comboArboles1.getValue();
    	Arbol seleccionado2 = comboArboles2.getValue();
    	if(seleccionado1 == null || seleccionado2 == null){
    		txtRespuesta.setText("Debe seleccionar un Arbol");
    	}else{
    		txtRespuestaComparar.setText("Son Identicos = "+ seleccionado1.sonIdenticos(seleccionado2));
    	}
    }

    @FXML
    void btnEliminarElementoClicked(ActionEvent event) {
    	Arbol seleccionado = comboArboles.getValue();
    	int valor = Integer.parseInt(txtValorEliminar.getText());
    	if(seleccionado == null){
    		txtRespuesta.setText("Debe seleccionar un Arbol");
    	}else{
    		seleccionado.eliminarElemento(valor);
    		txtRespuesta.setText("Elemento Eliminado");
    	}
    }

    @FXML
    void btnImprimirClicked(ActionEvent event) {

    }

    @FXML
    void btnNivelElementoClicked(ActionEvent event) {

    	Arbol seleccionado = comboArboles.getValue();
    	int nivel = Integer.parseInt(txtValorNodo.getText());
    	if(seleccionado == null){
    		txtRespuesta.setText("Debe seleccionar un Arbol");
    	}else{
    		txtRespuesta.setText("Nivel = "+ seleccionado.obtenerNivelElemento(nivel));
    	}
    }

    @FXML
    void btnNmeroHojasClicked(ActionEvent event) {

    	Arbol seleccionado = comboArboles.getValue();
    	if(seleccionado == null){
    		txtRespuesta.setText("Debe seleccionar un Arbol");
    	}else{
    		txtRespuesta.setText("Número Hojas = "+ seleccionado.contarHojas());
    	}
    }

    @FXML
    void btnRecorridoAmplitudClicked(ActionEvent event) {
    	Arbol seleccionado = comboArboles.getValue();
    	if(seleccionado == null){
    		txtRespuesta.setText("Debe seleccionar un Arbol");
    	}else{
    		txtRespuesta.setText("Recorrido en Amplitud = "+ seleccionado.recorridoAmplitud());
    	}
    }

    @FXML
    void btnValorPequeñoClicked(ActionEvent event) {
    	Arbol seleccionado = comboArboles.getValue();
    	if(seleccionado == null){
    		txtRespuesta.setText("Debe seleccionar un Arbol");
    	}else{
    		txtRespuesta.setText("Valos más pequeño = "+ seleccionado.obtenerMinimo());
    	}
    }

    @FXML
    void crearNodo(ActionEvent event) {
    	String valor = textFieldValor.getText();
        Circle nodo = new Circle(200, 150, 20, Color.LIGHTBLUE);
        nodo.setStroke(Color.BLACK);

        Text texto = new Text(valor);
        texto.setX(nodo.getCenterX() - 5);
        texto.setY(nodo.getCenterY() + 5);

        nodo.setOnMouseClicked(e -> seleccionarNodo(nodo));
        nodo.setOnMouseDragged(e -> moverNodo(e, nodo, texto));

        nodos.add(nodo);
        arbol.agregar(Integer.parseInt(valor));
        grafoPane.getChildren().addAll(nodo, texto);
    }

    private void seleccionarNodo(Circle nodo) {
        if (nodoSeleccionado == null) {
            nodoSeleccionado = nodo;
            nodo.setFill(Color.LIGHTGREEN); // Marca el nodo seleccionado
        } else {
            if (nodo != nodoSeleccionado) {
                crearArista(nodoSeleccionado, nodo);
                nodoSeleccionado.setFill(Color.LIGHTBLUE);
            }
            nodoSeleccionado = null;
        }
    }

    private void crearArista(Circle inicio, Circle fin) {
        Line arista = new Line(
            inicio.getCenterX(), inicio.getCenterY(),
            fin.getCenterX(), fin.getCenterY()
        );
        arista.setStroke(Color.GRAY);

        aristasMap.put(arista, new Circle[]{inicio, fin});
        grafoPane.getChildren().add(arista);
    }

    private void moverNodo(MouseEvent e, Circle nodo, Text texto) {
        nodo.setCenterX(e.getX());
        nodo.setCenterY(e.getY());
        texto.setX(e.getX() - 5);
        texto.setY(e.getY() + 5);

        actualizarAristas();
    }

    private void actualizarAristas() {
        for (Map.Entry<Line, Circle[]> entry : aristasMap.entrySet()) {
            Line arista = entry.getKey();
            Circle[] nodos = entry.getValue();
            Circle inicio = nodos[0];
            Circle fin = nodos[1];

            arista.setStartX(inicio.getCenterX());
            arista.setStartY(inicio.getCenterY());
            arista.setEndX(fin.getCenterX());
            arista.setEndY(fin.getCenterY());
        }
    }

    @FXML
    void mostrarPanelComparar(ActionEvent event) {
    	panelComparar.setVisible(true);
        panelMetodos.setVisible(false);
        rootPane.setVisible(false);

        ListaObservableArboles = FXCollections.observableArrayList(listaArboles);
        comboArboles1.setItems(ListaObservableArboles);
        comboArboles2.setItems(ListaObservableArboles);
    }

    @FXML
    void mostrarPanelCrearGrafo(ActionEvent event) {
    	panelComparar.setVisible(false);
        panelMetodos.setVisible(false);
        rootPane.setVisible(true);
    }

    @FXML
    void mostrarPanelMetodos(ActionEvent event) {
    	panelComparar.setVisible(false);
        panelMetodos.setVisible(true);
        rootPane.setVisible(false);

        System.out.println(listaArboles);

        ListaObservableArboles = FXCollections.observableArrayList(listaArboles);
        comboArboles.setItems(ListaObservableArboles);
    }

    @FXML
    void initialize() {
        assert btnAltura != null : "fx:id=\"btnAltura\" was not injected: check your FXML file 'Laboratorio-view.fxml'.";
        assert btnComparar != null : "fx:id=\"btnComparar\" was not injected: check your FXML file 'Laboratorio-view.fxml'.";
        assert btnCrearNodo != null : "fx:id=\"btnCrearNodo\" was not injected: check your FXML file 'Laboratorio-view.fxml'.";
        assert btnEliminar != null : "fx:id=\"btnEliminar\" was not injected: check your FXML file 'Laboratorio-view.fxml'.";
        assert btnImprimir != null : "fx:id=\"btnImprimir\" was not injected: check your FXML file 'Laboratorio-view.fxml'.";
        assert btnNivelElemento != null : "fx:id=\"btnNivelElemento\" was not injected: check your FXML file 'Laboratorio-view.fxml'.";
        assert btnNuevoArbol != null : "fx:id=\"btnNuevoArbol\" was not injected: check your FXML file 'Laboratorio-view.fxml'.";
        assert btnNumeroHojas != null : "fx:id=\"btnNumeroHojas\" was not injected: check your FXML file 'Laboratorio-view.fxml'.";
        assert btnPanelComparar != null : "fx:id=\"btnPanelComparar\" was not injected: check your FXML file 'Laboratorio-view.fxml'.";
        assert btnPanelCrearGrafo != null : "fx:id=\"btnPanelCrearGrafo\" was not injected: check your FXML file 'Laboratorio-view.fxml'.";
        assert btnPanelMetodos != null : "fx:id=\"btnPanelMetodos\" was not injected: check your FXML file 'Laboratorio-view.fxml'.";
        assert btnRecorridoAmplitud != null : "fx:id=\"btnRecorridoAmplitud\" was not injected: check your FXML file 'Laboratorio-view.fxml'.";
        assert btnValorPequeño != null : "fx:id=\"btnValorPequeño\" was not injected: check your FXML file 'Laboratorio-view.fxml'.";
        assert comboArboles != null : "fx:id=\"comboArboles\" was not injected: check your FXML file 'Laboratorio-view.fxml'.";
        assert comboArboles1 != null : "fx:id=\"comboArboles1\" was not injected: check your FXML file 'Laboratorio-view.fxml'.";
        assert comboArboles2 != null : "fx:id=\"comboArboles2\" was not injected: check your FXML file 'Laboratorio-view.fxml'.";
        assert grafoPane != null : "fx:id=\"grafoPane\" was not injected: check your FXML file 'Laboratorio-view.fxml'.";
        assert panelComparar != null : "fx:id=\"panelComparar\" was not injected: check your FXML file 'Laboratorio-view.fxml'.";
        assert panelMetodos != null : "fx:id=\"panelMetodos\" was not injected: check your FXML file 'Laboratorio-view.fxml'.";
        assert rootPane != null : "fx:id=\"rootPane\" was not injected: check your FXML file 'Laboratorio-view.fxml'.";
        assert textFieldValor != null : "fx:id=\"textFieldValor\" was not injected: check your FXML file 'Laboratorio-view.fxml'.";
        assert textNombre != null : "fx:id=\"textNombre\" was not injected: check your FXML file 'Laboratorio-view.fxml'.";
        assert txtValorEliminar != null : "fx:id=\"txtValorEliminar\" was not injected: check your FXML file 'Laboratorio-view.fxml'.";
        assert txtValorNodo != null : "fx:id=\"txtValorNodo\" was not injected: check your FXML file 'Laboratorio-view.fxml'.";

    }

}

