/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sushitinafx;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 *
 * @author jimy
 */
public class ClienteGP {
    GridPane gp;
    Label lTelefone, lCelular, lCliente, lEndereco;
    TextField tfTelefone, tfCelular, tfCliente, tfEndereco;

    public GridPane generateClienteGP() {
        gp = new GridPane();
        lTelefone = new Label("Telefone:");
        tfTelefone = new TextField();

        lCelular = new Label("Celular:");
        tfCelular = new TextField();

        lCliente = new Label("Nome / Codigo do Cliente:");
        tfCliente = new TextField();

        lEndereco = new Label("Endereco:");
        tfEndereco = new TextField();

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
        
        return gp;
    }

    public void EncontraCliente() {
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
    }

    public GridPane EnableCliente() {
        System.out.println("Enabling Cliente");
        tfTelefone.setDisable(false);
        tfCelular.setDisable(false);
        tfCliente.setDisable(false);
        tfEndereco.setDisable(false);
        
        return gp;
    }

    public void DisableCliente() {
        tfTelefone.setDisable(true);
        tfCelular.setDisable(true);
        tfCliente.setDisable(true);
        tfEndereco.setDisable(true);
    }
    
    public void printCliente(){
        System.out.println(tfCliente.getText());
    }
}
