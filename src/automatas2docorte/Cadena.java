/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automatas2docorte;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;
import jdk.nashorn.internal.codegen.CompilerConstants;

/**
 *
 * @author Francisco
 */
public class Cadena {
    private StringProperty token;
    private StringProperty descripcionFormal;
    private StringProperty descripcionInformal;
    private StringProperty lexema;

    Cadena() {
        
    }

    public String getLexema() {
        return lexema.get();
    }

    public void setLexema(String lexema) {
        this.lexema = new SimpleStringProperty( lexema);
    }
    
    public String getToken() {
        return token.get();
    }

    public void setToken(String token) {
        this.token = new SimpleStringProperty(token);
    }

    public String getDescripcionFormal() {
        return descripcionFormal.get();
    }

    public void setDescripcionFormal(String descripcionFormal) {
        this.descripcionFormal = new SimpleStringProperty(descripcionFormal);
    }

    public String getDescripcionInformal() {
        return descripcionInformal.get();
    }

    public void setDescripcionInformal(String descripcionInformal) {
        this.descripcionInformal = new SimpleStringProperty(descripcionInformal);
    }

    public Cadena(String token, String descripcionFormal, String descripcionInformal) {
        this.token = new SimpleStringProperty(token);
        this.descripcionFormal =new SimpleStringProperty (descripcionFormal);
        this.descripcionInformal = new SimpleStringProperty(descripcionInformal);
        
       
    }
    public Cadena(String token, String descripcionFormal, String descripcionInformal,String lexema) {
        this.token = new SimpleStringProperty(token);
        this.descripcionFormal =new SimpleStringProperty (descripcionFormal);
        this.descripcionInformal = new SimpleStringProperty(descripcionInformal);
        this.lexema = new SimpleStringProperty(lexema);
        
       
    }
   
    
    public void llenarLexemas(ObservableList<Cadena> listaLexemas, String identificador, String palabra, String separador, String agrupador){
       
       
        listaLexemas.add(0, new Cadena(
        "Identificador",
         "(L) (L|D|S)*",
         "Letra seguida de letras y dígitos o simbolos",
               identificador
        ));
        listaLexemas.add(1, new Cadena(
       "Agrupacion",
       "( ( | ) ) ",
       "( , )  ",
        agrupador
        ));
        listaLexemas.add(2, new Cadena(
        "Separador",
                "",
                ",",
               separador
        ));
        listaLexemas.add(3, new Cadena(
        "Reservada",
       "",
       "Caracteres: C,R,E,A,T,E   I,N,D,E,X  "
               + "U,N,I,Q,U,E   F,U,L,L,T,E,X,T  "
               + "S,P,A,T,I,A,L   O,N  U,S,I,N,G   B,T,R,E,E",
         palabra
        ));
       
      
       
        
    
    }
    
    public void llenarTabla(ObservableList<Cadena> lista){
       this.setToken("Identificador");
       this.setDescripcionFormal("(L) (L|D|S)*");
       this.setDescripcionInformal("Letra seguida de letras y dígitos o simbolos");
       this.setLexema("");
       lista.add(0,
       new Cadena(
       this.getToken(),
       "(L) (L|D|S)*",
       "Letra seguida de letras y dígitos o simbolos"
       
       
               
       )
       
       );
       lista.add(1,
       new Cadena(
       "Agrupacion",
       "( ( | ) ) ",
       "( , )  ",
        ""
     
               
       )
       
       );
       lista.add(2,
       new Cadena(
      "Separador",
                "",
                ",",
                ""
               
    
               
       )
       
       );
       lista.add(3, new Cadena(
        "Reservada",
       "",
       "Caracteres: C,R,E,A,T,E   I,N,D,E,X  "
               + "U,N,I,Q,U,E   F,U,L,L,T,E,X,T  "
               + "S,P,A,T,I,A,L   O,N  U,S,I,N,G   B,T,R,E,E",
         ""
        ));
    
    }
    
    
}
