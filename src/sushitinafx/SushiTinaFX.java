/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sushitinafx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author jimy
 */
public class SushiTinaFX extends Application {
    BorderPane root;
    Stage stage;
    
    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        root = new BorderPane();
        
        //Scene scene = new Scene(root, 300, 250);
        
        stage.setTitle("SushiTinaFx");
        
        showLoginScene();
    }
    
    public void showLoginScene()
    {
        //Scene
        AnchorPane aP = new AnchorPane();
        GridPane gP = new GridPane();
        root.setBottom(aP);
        root.setCenter(gP);
        Scene LoginScene = new Scene(root,300,250);
        stage.setScene(LoginScene);
        stage.show();
        
        //Components
        Button btLogin = new Button();
        Button btCadastrar = new Button();
        HBox botoesLogin = new HBox(10,btLogin,btCadastrar);
        Label lbUsuario  = new Label("Usuario");
        Label lbSenha  = new Label("Senha");
        TextField tfUsuario = new TextField();
        TextField tfSenha = new TextField();
        
        
        //Adding components and Allignments
        aP.getChildren().add(botoesLogin);
        gP.setAlignment(Pos.CENTER);
        gP.setVgap(10);
        gP.setHgap(8);
        gP.add(lbUsuario,0,0);
        gP.add(lbSenha,0,1);
        gP.add(tfUsuario,1,0);
        gP.add(tfSenha,1,1);
        AnchorPane.setBottomAnchor(botoesLogin,10d);
        AnchorPane.setRightAnchor(botoesLogin,10d);
        AnchorPane.setLeftAnchor(gP, 20d);
        AnchorPane.setRightAnchor(gP, 20d);
        
        
        //Components attributes and actions
        btLogin.setText("Entrar");
        btLogin.setOnAction((ActionEvent e) -> {
            authentication();
        });
        
        btCadastrar.setText("Cadastrar");
        btCadastrar.setOnAction((ActionEvent e) -> {
            signUp();
        });
    }
    
    public void showMainScene()
    {
        //Scene
        TabPane tProot = new TabPane();
        Scene MainScene = new Scene(tProot, 300, 250);
        stage.setScene(MainScene);
        
        //Components
        Tab tPedidos = new Tab();
        tPedidos.setText("Pedidos");
        
        Tab tCadastro = new Tab();
        tCadastro.setText("Cadastro");
        
        Tab tConsulta = new Tab();
        tConsulta.setText("Consulta");
        
        //Adding Components and allignment
        tProot.getTabs().add(tPedidos);
        tProot.getTabs().add(tCadastro);
        tProot.getTabs().add(tConsulta);
    }
    
    public void authentication(){
        System.out.println("Autenticando...");
        if(true)
        {
            showMainScene();
        }
    }
    
    public void signUp(){
        System.out.println("Entrando na tela de cadastro..");
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
