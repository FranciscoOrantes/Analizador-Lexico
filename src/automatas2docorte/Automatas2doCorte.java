/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automatas2docorte;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Francisco
 */
public class Automatas2doCorte extends Application {
    static Stage ventanaPrincipal;
    @Override
    public void start(Stage primaryStage) throws IOException {
        this.ventanaPrincipal = primaryStage;
        this.ventanaPrincipal.setTitle("Analizador lexico");
        Parent root = FXMLLoader.load(getClass().getResource("Entrada.fxml"));
        Scene scene = new Scene(root);
        ventanaPrincipal.setScene(scene);
        ventanaPrincipal.setResizable(false);
        ventanaPrincipal.show();
        //ventanaLogin.
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
