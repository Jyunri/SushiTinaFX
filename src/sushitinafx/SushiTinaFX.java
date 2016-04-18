/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

 /*
1. Modularizacao GPCliente
 */
package sushitinafx;

import com.sun.javafx.charts.ChartLayoutAnimator;
import java.io.File;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author jimy
 */
public class SushiTinaFX extends Application {

    //UI Components
    BorderPane root;
    TabPane tProot;
    ClienteGP clienteGP;
    GridPane gPDadosPedido, gPDadosCliente;
    Stage stage;
    ImageView ivLogo;
    Pedido pedido;
    int gridRow = 3;

    //TODO - modularizar
    GridPane gp;

    /*
    Label lTelefone, lCelular, lCliente, lEndereco;
    TextField tfTelefone, tfCelular, tfCliente, tfEndereco;
     */
    //Backend classes 
    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        root = new BorderPane();
        ivLogo = new ImageView();
        File file = new File("/home/jimy/JimyGit/SushiTIna/SushiTinaFX/sushilogo.png");
        Image sushi = new Image(file.toURI().toString());
        ivLogo.setImage(sushi);
        ivLogo.setFitHeight(200);
        ivLogo.setFitWidth(300);
        //Scene scene = new Scene(root, 300, 250);
        stage.setTitle("SushiTinaFx");

        showLoginScene();
    }

    //TO-DO create autentication
    public void showLoginScene() {
        //Scene
        AnchorPane aP = new AnchorPane();
        GridPane gP = new GridPane();
        root.setBottom(aP);
        root.setCenter(gP);
        Scene LoginScene = new Scene(root, 300, 250);
        LoginScene.getStylesheets().add(SushiTinaFX.class.getResource("SushiTinaLogin.css").toExternalForm());
        stage.setScene(LoginScene);
        stage.show();

        //Components
        Button btLogin = new Button();
        Button btCadastrar = new Button();
        HBox botoesLogin = new HBox(10, btLogin, btCadastrar);
        TextField tfUsuario = new TextField();
        TextField tfSenha = new TextField();

        //Adding components and Allignments
        aP.getChildren().add(botoesLogin);
        gP.setAlignment(Pos.CENTER);
        gP.setVgap(10);
        gP.setHgap(8);
        gP.add(tfUsuario, 1, 0);
        gP.add(tfSenha, 1, 1);
        AnchorPane.setBottomAnchor(botoesLogin, 10d);
        AnchorPane.setRightAnchor(botoesLogin, 10d);
        AnchorPane.setLeftAnchor(gP, 20d);
        AnchorPane.setRightAnchor(gP, 20d);

        //Components attributes and actions
        tfUsuario.setPromptText("Usuario");
        tfSenha.setPromptText("Senha");
        btLogin.setText("Entrar");
        btLogin.setOnAction((ActionEvent e) -> {
            authentication();
        });

        btCadastrar.setText("Cadastrar");
        btCadastrar.setOnAction((ActionEvent e) -> {
            signUp();
        });

        //Design
    }

    public void insertGridRow() {
        //UI declaration
        //TODO change qtd to spinner
        TextField newtfItem = new TextField();
        TextField newtfQtd = new TextField();
        TextField newtfPreco = new TextField();
        Button btAdiciona = new Button("+");
        Button btRemove = new Button("-");
        HBox hbAdicionaRemove = new HBox(btAdiciona, btRemove);
        hbAdicionaRemove.setSpacing(30);
        gPDadosPedido.addRow(gridRow);
        gPDadosPedido.add(newtfItem, 0, gridRow);
        gPDadosPedido.add(newtfQtd, 1, gridRow);
        gPDadosPedido.add(newtfPreco, 2, gridRow);
        gPDadosPedido.add(hbAdicionaRemove, 3, gridRow);
        gridRow++;

        //TO-DO if newvalue = 0 then setpreco = ""
        newtfQtd.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            int a = -1;
            if (newValue.toString().equals("")) {
                a = 0;
            } else {
                a = Integer.valueOf(newValue.toString());
            }
            newtfPreco.setText(String.valueOf(4 * a));
        });

        //Backend
        btAdiciona.setOnAction((ActionEvent e) -> {
            pedido.itens.add(new Item(1, "sushi", 3, 4));
            insertGridRow();
        });

        btRemove.setOnAction((ActionEvent e) -> {
            if (gridRow > 4) {

                gPDadosPedido.getChildren().remove(newtfItem);
                gPDadosPedido.getChildren().remove(newtfQtd);
                gPDadosPedido.getChildren().remove(newtfPreco);
                gPDadosPedido.getChildren().remove(hbAdicionaRemove);

                /*
                newtfItem.setVisible(false);
                newtfItem.setManaged(false);
                newtfQtd.setVisible(false);
                newtfQtd.setManaged(false);
                newtfPreco.setVisible(false);
                newtfPreco.setManaged(false);
                hbAdicionaRemove.setVisible(false);
                hbAdicionaRemove.setManaged(false);
                 */
                gridRow--;
            }
        });
    }

    public void moduloPedidos() {
        ScrollPane sP = new ScrollPane();
        BorderPane bP = new BorderPane();
        sP.setContent(bP);
        bP.setMinSize(900, 600);
        Tab tPedidos = new Tab();
        tPedidos.setText("Pedidos");
        tProot.getTabs().add(tPedidos);
        tPedidos.setContent(sP);

        gPDadosPedido = new GridPane();

        //IN PROGRESS
        //clienteGP = new ClienteGP();
        //gPDadosCliente = clienteGP.generateClienteGP();
        //TODO - tentar modularizar em outra classe
        //Cliente Grid
        //<editor-fold>
        gp = new GridPane();
        Label lTelefone = new Label("Telefone:");
        TextField tfTelefone = new TextField();

        Label lCelular = new Label("Celular:");
        TextField tfCelular = new TextField();

        Label lCliente = new Label("Nome / Codigo do Cliente:");
        TextField tfCliente = new TextField();

        Label lEndereco = new Label("Endereco:");
        TextField tfEndereco = new TextField();

        gp.setAlignment(Pos.CENTER);
        gp.setVgap(10);
        gp.setHgap(10);

        gp.add(lTelefone, 0, 2);
        gp.add(tfTelefone, 1, 2);
        gp.add(lCelular, 2, 2);
        gp.add(tfCelular, 3, 2);
        gp.add(lCliente, 0, 3);
        gp.add(tfCliente, 1, 3);
        gp.add(lEndereco, 0, 4);
        gp.add(tfEndereco, 1, 4);
        gp.setColumnSpan(tfEndereco, 3);
        gp.setColumnSpan(tfCliente, 3);

        //Components attributes and actions
        tfTelefone.setPromptText("(12) 39627451");
        tfCelular.setPromptText("(12) 981279637");
        tfCliente.setPromptText("Jonathan Jyunri Suenaga");
        tfEndereco.setPromptText("Rua Joao Teodoro, 55");

        tfTelefone.setDisable(true);
        tfCelular.setDisable(true);
        tfCliente.setDisable(true);
        tfEndereco.setDisable(true);
        //
        //</editor-fold>

        //Components
        //<editor-fold>
        Label lCodigoPedido = new Label("Codigo do pedido:");
        TextField tfCodigoPedido = new TextField();

        Label lTipoPedido = new Label("Tipo:");
        ComboBox cbTipoPedido = new ComboBox();
        HBox hbTipoPedido = new HBox(lTipoPedido, cbTipoPedido);
        hbTipoPedido.setSpacing(30);

        Label lMesa = new Label("Mesa:");
        TextField tfMesa = new TextField();
        HBox hbMesa = new HBox(lMesa, tfMesa);
        hbMesa.setSpacing(30);

        Label lItem = new Label("Item");
        TextField tfItem = new TextField();

        Label lQtd = new Label("Quantidade");
        TextField tfQtd = new TextField();      //TO-DO change to spinner

        Label lPreco = new Label("Preço (Quantidade x Preço Item)");
        TextField tfPreco = new TextField();

        Label lObsPedido = new Label("Obs do pedido:");
        TextField tfObsPedido = new TextField();

        Label lFormaPgto = new Label("Forma de pagamento");
        ComboBox cbFormaPgto = new ComboBox();
        cbFormaPgto.getItems().addAll("Dinheiro", "Cartao");

        Label lTroco = new Label("Troco:");
        TextField tfTroco = new TextField();

        Button btConfirma = new Button("Confirma");
        Button btLimpa = new Button("Limpa");
        Button btCancela = new Button("Cancela");
        HBox hbBotoes = new HBox(btConfirma, btLimpa, btCancela);
        hbBotoes.setSpacing(40);

        HBox hbLogo = new HBox(ivLogo);
        HBox hbBottom = new HBox(hbLogo, hbBotoes);

        Button btProcurar = new Button("Procurar");
        gp.add(btProcurar, 4, 2);

        bP.setTop(gPDadosPedido);
        bP.setCenter(gp);
        bP.setBottom(hbBottom);

        hbLogo.setPadding(new Insets(20, 20, 20, 20));
        hbLogo.setAlignment(Pos.CENTER_LEFT);
        hbBotoes.setAlignment(Pos.CENTER_RIGHT);
        hbBotoes.setPadding(new Insets(0, 0, 0, 200));
        //</editor-fold>

        //for debug
        //gPDadosPedido.setGridLinesVisible(true);
        //gPDadosCliente.setGridLinesVisible(true);
        gPDadosPedido.setAlignment(Pos.CENTER);
        gPDadosPedido.setVgap(15);
        gPDadosPedido.setHgap(10);

        gPDadosPedido.setPadding(new Insets(20, 20, 20, 20));
        gPDadosPedido.add(lCodigoPedido, 0, 0);
        gPDadosPedido.add(tfCodigoPedido, 1, 0);

        gPDadosPedido.add(hbTipoPedido, 0, 1);
        gPDadosPedido.add(hbMesa, 2, 1);
        gPDadosPedido.add(lItem, 0, 2);
        gPDadosPedido.add(lQtd, 1, 2);
        gPDadosPedido.add(lPreco, 2, 2);

        insertGridRow();
        tfCodigoPedido.setEditable(false);
        tfCodigoPedido.setDisable(true);
        tfCodigoPedido.setPromptText(String.valueOf(String.format("%04d", Pedido.gcodigoPedido)));
        cbTipoPedido.getItems().addAll("Mesa", "Delivery");
        tfMesa.setDisable(true);

        //Back-end
        pedido = new Pedido();

        btProcurar.setOnAction((ActionEvent e) -> {
            //clienteGP.EncontraCliente();
            System.out.println("Procurando cliente..");
            Cliente temp = LocalClienteDB.clienteTelDB.get(tfTelefone.getText());
            if (temp == null) {
                temp = LocalClienteDB.clienteCelDB.get(tfCelular.getText());
            }
            if (temp != null) {
                tfCliente.setText(temp.nome);
                tfEndereco.setText(temp.endereco);
                tfTelefone.setText(temp.telefone);
                tfCelular.setText(temp.celular);
            } else {
                System.out.println("Cliente nao cadastrado");
            }
        });

        btConfirma.setOnAction((ActionEvent e) -> {
            //Getting User inputs
            if (cbTipoPedido.getSelectionModel().getSelectedItem().toString().equals("Delivery")) {
                String stCliente = tfCliente.getText();
                String stEndereco = tfEndereco.getText();
                String stTelefone = tfTelefone.getText();
                String stCelular = tfCelular.getText();
                /*
                String stCliente = clienteGP.tfCliente.getText();
                String stEndereco = clienteGP.tfEndereco.getText();
                String stTelefone = clienteGP.tfTelefone.getText();
                String stCelular = clienteGP.tfCelular.getText();
                 */
                pedido.setPedido(new Cliente(stCliente, stEndereco, stTelefone, stCelular), "teste", Float.valueOf(tfTroco.getText()), "nenuhma obs");
                System.out.println(pedido);
            } else {
                System.out.println("Tipo mesa");
            }
        });

        btLimpa.setOnAction((ActionEvent e) -> {

        });

        SingleSelectionModel model = cbTipoPedido.getSelectionModel();
        model.selectedItemProperty().addListener((ObservableValue observable, Object oldValue, Object newValue) -> {
            if (newValue.toString().equals("Delivery")) {
                tfTelefone.setDisable(false);
                tfCelular.setDisable(false);
                tfCliente.setDisable(false);
                tfEndereco.setDisable(false);
                tfMesa.setDisable(true);
            } else {
                tfTelefone.setDisable(true);
                tfCelular.setDisable(true);
                tfCliente.setDisable(true);
                tfEndereco.setDisable(true);
                tfMesa.setDisable(false);
            }
        });

        //Design
        bP.setStyle("-fx-background-color: red");
        //sP.setStyle("-fx-background-color: white");
    }

    public void moduloCadastro() {
        ScrollPane sP = new ScrollPane();
        BorderPane bP = new BorderPane();
        bP.setMinSize(900, 600);
        sP.setContent(bP);
        Tab tCadastro = new Tab();
        tCadastro.setText("Cadastro");
        tProot.getTabs().add(tCadastro);
        tCadastro.setContent(sP);
        GridPane gpCadastroCliente = new GridPane();

        Label lTipoCadastro = new Label("Tipo de Cadastro: ");
        ComboBox cbTipoCadastro = new ComboBox();
        cbTipoCadastro.getItems().addAll("Cliente", "Funcionario", "Produto");
        HBox hbTipoCadastro = new HBox(lTipoCadastro, cbTipoCadastro);
        hbTipoCadastro.setAlignment(Pos.CENTER);

        //<editor-fold>
        Label lTelefone = new Label("Telefone:");
        TextField tfTelefone = new TextField();

        Label lCelular = new Label("Celular:");
        TextField tfCelular = new TextField();

        Label lCliente = new Label("Nome / Codigo do Cliente:");
        TextField tfNomeCliente = new TextField();

        Label lEndereco = new Label("Endereco:");
        TextField tfEndereco = new TextField();

        gpCadastroCliente.setAlignment(Pos.CENTER);
        gpCadastroCliente.setVgap(10);
        gpCadastroCliente.setHgap(10);

        gpCadastroCliente.add(lTelefone, 0, 2);
        gpCadastroCliente.add(tfTelefone, 1, 2);
        gpCadastroCliente.add(lCelular, 2, 2);
        gpCadastroCliente.add(tfCelular, 3, 2);
        gpCadastroCliente.add(lCliente, 0, 3);
        gpCadastroCliente.add(tfNomeCliente, 1, 3);
        gpCadastroCliente.add(lEndereco, 0, 4);
        gpCadastroCliente.add(tfEndereco, 1, 4);
        gpCadastroCliente.setColumnSpan(tfEndereco, 3);
        gpCadastroCliente.setColumnSpan(tfNomeCliente, 3);

        //</editor-fold>
        //Logo and Buttons 
        //<editor-fold>
        Button btConfirma = new Button("Confirma");
        Button btLimpa = new Button("Limpa");
        Button btCancela = new Button("Cancela");
        HBox hbBotoes = new HBox(btConfirma, btLimpa, btCancela);
        hbBotoes.setSpacing(40);

        ImageView ivLogo2 = new ImageView();
        File file = new File("/home/jimy/JimyGit/SushiTIna/SushiTinaFX/sushilogo.png");
        Image sushi = new Image(file.toURI().toString());
        ivLogo2.setImage(sushi);

        HBox hbLogo2 = new HBox(ivLogo2);
        HBox hbBottom = new HBox(hbLogo2, hbBotoes);

        hbLogo2.setPadding(new Insets(20, 20, 20, 20));
        hbLogo2.setAlignment(Pos.CENTER_LEFT);
        hbBotoes.setAlignment(Pos.CENTER_RIGHT);
        hbBotoes.setPadding(new Insets(0, 0, 0, 200));

        btConfirma.setOnAction((ActionEvent e) -> {
            String stCliente = tfNomeCliente.getText();
            String stEndereco = tfEndereco.getText();
            String stTelefone = tfTelefone.getText();
            String stCelular = tfCelular.getText();
            
            System.out.println("Cadastrar");
            Cliente c = new Cliente(stCliente,stEndereco,stTelefone,stCelular);
            LocalClienteDB.clienteTelDB.put(c.telefone, c);
            LocalClienteDB.clienteCelDB.put(c.celular, c);
        });

        btLimpa.setOnAction((ActionEvent e) -> {
            System.out.println("Limpar");
        });
        //</editor-fold>

        bP.setTop(hbTipoCadastro);
        bP.setCenter(gpCadastroCliente);
        bP.setBottom(hbBottom);

        //bP.setCenter(gPDadosCliente);
        //bP.setBottom(hbBottom);
        //Design
        bP.setStyle("-fx-background-color: red");
    }

    public void moduloConsulta() {
        Tab tConsulta = new Tab();
        tConsulta.setText("Consulta");
        tProot.getTabs().add(tConsulta);
    }

    public void moduloAndamento() {
        Tab tEmAndamento = new Tab("Em andamento");
        tProot.getTabs().add(tEmAndamento);
    }

    public void showMainScene() {
        //Scene
        tProot = new TabPane();
        Scene MainScene = new Scene(tProot, 900, 600);
        MainScene.getStylesheets().add(SushiTinaFX.class.getResource("SushiTinaMain.css").toExternalForm());
        stage.setScene(MainScene);
        stage.centerOnScreen();

        moduloPedidos();
        moduloCadastro();
        moduloConsulta();
        moduloAndamento();

        //Adding Components and allignment      
        //Design
        tProot.setStyle("-fx-background-color: black");
        //tProot.setStyle("-fx-background-color: red");
        //tProot.setStyle("-fx-background: linear-gradient(to bottom left, white, red);");
    }

    public void authentication() {
        System.out.println("Autenticando...");
        if (true) {
            showMainScene();
        }
    }

    public void signUp() {
        System.out.println("Entrando na tela de cadastro..");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
