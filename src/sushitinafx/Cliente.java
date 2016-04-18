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
public class Cliente {
    static int gId = 0;
    int id;
    String nome;
    String endereco;
    String telefone;
    String celular;

    public Cliente(String nome, String endereco, String telefone, String celular) {
        this.id = gId;
        gId++;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.celular = celular;
    }
    
}
