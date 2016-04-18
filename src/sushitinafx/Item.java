/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sushitinafx;

/**
 *
 * @author jimy
 */
public class Item {
    int id, quantidade;
    String nome;
    float preco;

    public Item(int id, String nome, float preco, int quantidade) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }
}
