/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automatas2docorte;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Francisco
 */
public class EntradaController implements Initializable {

    @FXML
    TextArea txtEntrada;
    @FXML
    Button btnEntrada;
    @FXML
    TableView<Cadena> tablaEntrada;

    @FXML
    public TableColumn<Cadena, String> columnaToken;
    @FXML
    public TableColumn<Cadena, String> columnaDescripcionFormal;
    @FXML
    public TableColumn<Cadena, String> columnaDescripcionInformal;
    @FXML
    public TableColumn<Cadena, String> columnaLexema;
    private ObservableList<Cadena> cadenas;
    private ObservableList<Cadena> lexemas;
    private String cadena;
    private String palabra;
    private String palabra2;
    private Cadena miCadena;
    private ArrayList palabras;
    private String identificador;
    private ArrayList identificadores;
    private ArrayList separadores;
    private ArrayList agrupadores;
    private ArrayList errores;
    boolean termino = false;
    int numeroEjecucion = 0;
    int indice;
    int posicion = 0;
    boolean termino2 = false;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        miCadena = new Cadena();
        cadenas = FXCollections.observableArrayList();
        lexemas = FXCollections.observableArrayList();
        palabras = new ArrayList();
        identificadores = new ArrayList();
        separadores = new ArrayList();
        agrupadores = new ArrayList();
        llenarTabla();
    }

    public void analizador() {
        if (termino) {
            if (palabras.size() > 1) {
                if (palabras.get(palabras.size() - 1).equals(palabras.get(palabras.size() - 2))) {
                    palabras.remove(palabras.size() - 1);
                }
            }
            if (identificadores.size() > 1) {
                if (identificadores.get(identificadores.size() - 1).equals(identificadores.get(identificadores.size() - 2))) {
                    identificadores.remove(identificadores.size() - 1);
                }
            }
            if (agrupadores.size() > 1) {
                if (agrupadores.get(agrupadores.size() - 1).equals(agrupadores.get(agrupadores.size() - 2))) {
                    agrupadores.remove(agrupadores.size() - 1);
                }
            }
            if (separadores.size() > 1) {
                if (separadores.get(separadores.size() - 1).equals(separadores.get(separadores.size() - 2))) {
                    separadores.remove(separadores.size() - 1);
                }
            }

            //palabras.remove(palabras.size()-1);
            JOptionPane.showMessageDialog(null, "Ha terminado la cadena");
            JOptionPane.showMessageDialog(null, "Palabras reservadas: " + palabras);
            JOptionPane.showMessageDialog(null, "Identificadores: " + identificadores);
            JOptionPane.showMessageDialog(null, "Separadores: " + separadores);
            JOptionPane.showMessageDialog(null, "Agrupadores: " + agrupadores);
            llenarTablaLexemas();
            miCadena.llenarLexemas(cadenas, identificadores.toString(), palabras.toString(), separadores.toString(), agrupadores.toString());
            columnaLexema();

            termino = false;
            palabras.clear();
            identificadores.clear();
            agrupadores.clear();
            separadores.clear();
            termino = false;
            numeroEjecucion = 0;
            indice = 0;

        } else {
            numeroEjecucion++;
            int indiceLexemas;
            if (numeroEjecucion > 1) {
                cadena = cadena.substring(posicion);
                indice = cadena.indexOf(" ");
                try {
                    palabra = cadena.substring(0, indice);
                } catch (Exception e) {
                    termino = true;
                    JOptionPane.showMessageDialog(null, palabra);
                    JOptionPane.showMessageDialog(null, "Palabras reservadas: " + palabras);
                    JOptionPane.showMessageDialog(null, "Identificadores: " + identificadores);
                }

            } else {
                this.cadena = txtEntrada.getText();
                indice = cadena.indexOf(" ");
                palabra = cadena.substring(posicion, indice);
                // JOptionPane.showMessageDialog(null, palabra);
            }
            if (palabra.equals("CREATE") || palabra.equals("INDEX") || palabra.equals("UNIQUE") || palabra.equals("FULLTEXT")
                    || palabra.equals("ESPATIAL") || palabra.equals("ON") || palabra.equals("USING") || palabra.equals("BTREE")) {
                palabra2 = palabra;
                palabras.add(palabra2);
                indiceLexemas = 2;
                //JOptionPane.showMessageDialog(null, "ESTA ES PALABRA " +palabra2);

                posicion = indice + 1;
                indice = posicion;

                analizador();

            } else {
                identificador = palabra;
                if (palabra.matches("[a-z][a-zA-Z-_]*")) {
                    identificadores.add(identificador);
                    posicion = indice + 1;
                    indice = posicion;
                    analizador();
                    
                } else if (palabra.contains("(")) {
                    int indiceParentesis = palabra.indexOf("(");
                    agrupadores.add(String.valueOf(palabra.charAt(indiceParentesis)));
                    posicion = indice + 1;
                    indice = posicion;
                    analizador();
                } else if (palabra.contains(")")) {
                    int indiceParentesis = palabra.indexOf(")");
                    agrupadores.add(String.valueOf(palabra.charAt(indiceParentesis)));
                    posicion = indice + 1;
                    indice = posicion;
                    analizador();
                } else if (palabra.contains(",")) {
                    int indiceComa = palabra.indexOf(",");
                    separadores.add(String.valueOf(palabra.charAt(indiceComa)));
                    posicion = indice + 1;
                    indice = posicion;

                    analizador();
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR");
                    posicion = indice + 1;
                    indice = posicion;
                    analizador();
                }

                //JOptionPane.showMessageDialog(null, "ESTA ES Identificador " +identificadores);
            }

        }

    }

    public void llenarTablaLexemas() {
        cadenas = FXCollections.observableArrayList();
        tablaEntrada.setItems(cadenas);

    }

    public void columnaLexema() {
        columnaToken.setCellValueFactory(new PropertyValueFactory<Cadena, String>("token"));
        columnaDescripcionFormal.setCellValueFactory(new PropertyValueFactory<Cadena, String>("descripcionFormal"));
        columnaDescripcionInformal.setCellValueFactory(new PropertyValueFactory<Cadena, String>("descripcionInformal"));
        columnaLexema.setCellValueFactory(new PropertyValueFactory<Cadena, String>("lexema"));

    }

    public void llenarTabla() {
        cadenas = FXCollections.observableArrayList();
        tablaEntrada.setItems(cadenas);
        miCadena.llenarTabla(cadenas);
        columnaToken.setCellValueFactory(new PropertyValueFactory<Cadena, String>("token"));
        columnaDescripcionFormal.setCellValueFactory(new PropertyValueFactory<Cadena, String>("descripcionFormal"));
        columnaDescripcionInformal.setCellValueFactory(new PropertyValueFactory<Cadena, String>("descripcionInformal"));

    }
    public void identificadoresIdentificar(){
    
    }
}
