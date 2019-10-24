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
    public TableColumn<Cadena,String> columnaToken;
    @FXML
    public TableColumn<Cadena,String> columnaDescripcionFormal;
    @FXML
    public TableColumn<Cadena,String> columnaDescripcionInformal;
     @FXML
    public TableColumn<Cadena,String> columnaLexema;
    private ObservableList<Cadena> cadenas;
    private ObservableList<Cadena> lexemas;
    private String cadena;
    private String palabra;
    private String palabra2;
    private Cadena miCadena;
    private ArrayList palabras;
    private String identificador;
    private ArrayList identificadores;
    
       boolean termino=false;
    int numeroEjecucion=0;
    int indice;
     int posicion = 0;
     boolean termino2=false;
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
        llenarTabla();
    }

    public void analizador() {
        if(termino){
             if(palabras.size()>1){
             palabras.remove(palabras.size()-1);
             }
             if(identificadores.size()>1){
                  identificadores.remove(identificadores.size()-1);
             }
             //palabras.remove(palabras.size()-1);
            
             JOptionPane.showMessageDialog(null, "Ha terminado la cadena");
             JOptionPane.showMessageDialog(null,"Palabras reservadas: " +  palabras);
               JOptionPane.showMessageDialog(null,"Identificadores: " +  identificadores);
               llenarTablaLexemas();
               miCadena.llenarLexemas(cadenas, identificadores.toString(),palabras.toString());
               columnaLexema();
               termino2=true;
               if(termino2){
                palabras.clear();
               identificadores.clear();
               termino=false;
               numeroEjecucion=0;
               indice = 0;
                JOptionPane.showMessageDialog(null, "Ha terminado la cadena x2 ");
               }
               
               
              
        }else{
        numeroEjecucion++;
        int indiceLexemas;
       if(numeroEjecucion>1){
       cadena = cadena.substring(posicion);
       indice = cadena.indexOf(" ");
           try {
               palabra = cadena.substring(0,indice); 
           } catch (Exception e) {
               termino=true;
                JOptionPane.showMessageDialog(null, palabra);
               JOptionPane.showMessageDialog(null,"Palabras reservadas: " +  palabras);
               JOptionPane.showMessageDialog(null,"Identificadores: " +  identificadores);
           }
      
       
       }else{
       this.cadena = txtEntrada.getText();
       indice = cadena.indexOf(" ");
       palabra = cadena.substring(posicion,indice);
       // JOptionPane.showMessageDialog(null, palabra);
       }
       if(palabra.equals("CREATE")||palabra.equals("INDEX")|| palabra.equals("UNIQUE")||palabra.equals("FULLTEXT")
               ||palabra.equals("ESPATIAL")||palabra.equals("ON")||palabra.equals("USING")||palabra.equals("BTREE")){
       palabra2 = palabra;
       palabras.add(palabra2);
           indiceLexemas=2;
       //JOptionPane.showMessageDialog(null, "ESTA ES PALABRA " +palabra2);
                  
                 posicion = indice+1;
               indice = posicion;
              
               analizador();
               
       }else{
       identificador = palabra;
       identificadores.add(identificador);
       indiceLexemas=0;
       //JOptionPane.showMessageDialog(null, "ESTA ES Identificador " +identificadores);
                  
                 posicion = indice+1;
               indice = posicion;
               analizador();
       }
        
        }
        
       
     
       
        
               
       
       
    }
    public void llenarTablaLexemas(){
    cadenas = FXCollections.observableArrayList();
    tablaEntrada.setItems(cadenas);
       
        
        
    }
    public void columnaLexema(){
    columnaToken.setCellValueFactory(new PropertyValueFactory<Cadena, String>("token"));
        columnaDescripcionFormal.setCellValueFactory(new PropertyValueFactory<Cadena, String>("descripcionFormal"));
        columnaDescripcionInformal.setCellValueFactory(new PropertyValueFactory<Cadena, String>("descripcionInformal"));    
    columnaLexema.setCellValueFactory(new PropertyValueFactory<Cadena, String>("lexema"));
    
    }
    public void llenarTabla(){
    cadenas = FXCollections.observableArrayList();
    tablaEntrada.setItems(cadenas);
    miCadena.llenarTabla(cadenas);
        columnaToken.setCellValueFactory(new PropertyValueFactory<Cadena, String>("token"));
        columnaDescripcionFormal.setCellValueFactory(new PropertyValueFactory<Cadena, String>("descripcionFormal"));
        columnaDescripcionInformal.setCellValueFactory(new PropertyValueFactory<Cadena, String>("descripcionInformal"));
        
    }
}
