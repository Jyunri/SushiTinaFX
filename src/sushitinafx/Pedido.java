/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sushitinafx;

import java.util.ArrayList;

/**
 *
 * @author jimy
 */
public class Pedido {

    static int gcodigoPedido = 0;
    ArrayList<Item> itens = new ArrayList();
    int codPedido;
    Cliente cliente;
    String formaPag;
    float troco;
    String Obs;

    public void setPedido(Cliente cliente, String formaPag, float troco, String Obs) {
        codPedido = gcodigoPedido;
        Pedido.gcodigoPedido++;
        this.cliente = cliente;
        this.formaPag = formaPag;
        this.troco = troco;
        this.Obs = Obs;
        LocalClienteDB.clienteTelDB.put(cliente.telefone, cliente);
        LocalClienteDB.clienteCelDB.put(cliente.celular, cliente);
    }

    @Override
    public String toString() {
        String codFormatado = String.format("%04d",codPedido);
        String scodigo = "Codigo do pedido: " + codFormatado + "\n";
        String scliente = "Nome do Cliente: " + cliente.nome + "\n";
        String sendereco = "Endereco: " + cliente.endereco + "\n";
        String stelefone = "Telefone: " + cliente.telefone + "\n";
        String scelular = "Celular: " + cliente.celular + "\n";
        
        String sItens = "Itens: "+"\n";
        for(Item i:itens)
        {
            sItens += i.nome + " ";
            sItens += "R$"+i.preco + " ";
            sItens += "x" +i.quantidade + "\n";
        }
        
        String sformapgto = "Forma de pagamento: " + formaPag + "\n";
        String sTroco = "Troco: R$" + troco + "\n";
        String sObs = "Observação: " + Obs + "\n";

        return scodigo + scliente + sendereco + stelefone + scelular + sItens + sformapgto + sTroco + sObs;
    }
}
