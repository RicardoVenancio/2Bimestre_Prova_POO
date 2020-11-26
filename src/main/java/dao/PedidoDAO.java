package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.ConexaoHSQLDB;
import entity.InterPedido;
import entity.Pedido;             ///////////////////MEXEEEEEEEEER NO BANCOOOOOOOOOOOOOOOO + PATHBASE CONEXAOHSQLDB////
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PedidoDAO extends ConexaoHSQLDB implements InterPedido{
	final String SQL_INSERT_PEDIDOO = "INSERT INTO PEDIDOO(NOME, DATAPEDIDO, QUANTIDADE, UNIDADEMEDIDA, VENDEDORPEDIDO, RESPONSAVELPEDIDO)VALUES (?,?,?,?,?,?)";
	final String SQL_SELECT_PEDIDOO = "SELECT * FROM PEDIDOO";
	final String SQL_SELECT_PEDIDOO_ID = "SELECT * FROM PEDIDOO WHERE IDPEDIDO = ?";
	final String SQL_ALTERA_PEDIDOO = "UPDATE PEDIDOO SET NOME=?, DATAPEDIDO=?, QUANTIDADE=? , UNIDADEMEDIDA=?, VENDEDORPEDIDO=?, RESPONSAVELPEDIDO=? WHERE IDPEDIDO = ?";
	final String SQL_DELETA_PEDIDOO = "DELETE FROM PEDIDOO WHERE IDPEDIDO = ?";
	final String SQL_SELECT_PEDIDOO_NOME = "SELECT * FROM PEDIDOO WHERE NOME = ?";
	
	public Pedido BuscarDados(String nome) {
		Pedido pedido = new Pedido();
		try (Connection connection = this.conectar();
				PreparedStatement pst = connection.prepareStatement(SQL_SELECT_PEDIDOO_NOME);) {

			pst.setString(1, nome);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {

				pedido.setIdPedido(rs.getInt("IDPEDIDO"));
				pedido.setNome(rs.getString("NOME"));
				pedido.setDataPedido(java.sql.Date.valueOf((rs.getString("DATAPEDIDO"))));
				pedido.setQuantidade(rs.getInt("QUANTIDADE"));
				pedido.setUnidademedida(rs.getString("UNIDADEMEDIDA"));
				pedido.setVendedorPedido(rs.getString("VENDEDORPEDIDO"));
				pedido.setResponsavelPedido(rs.getString("RESPONSAVELPEDIDO"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pedido;
	}
	
public int inserir(Pedido pedidoo) {
	int quantidade = 0;

	//INSERIR
	try (Connection connection = this.conectar();
			PreparedStatement pst = connection.prepareStatement(SQL_INSERT_PEDIDOO);) {
		pst.setString(1, pedidoo.getNome()); //COM HERANÇA DA CLASSE MATERIAL
		pst.setDate(2, java.sql.Date.valueOf(pedidoo.getDataPedido().toString()));
		pst.setInt(3, pedidoo.getQuantidade()); //COM HERANÇA DA CLASSE MATERIAL
		pst.setString(4, pedidoo.getUnidademedida()); //COM HERANÇA DA CLASSE MATERIAL
		pst.setString(5, pedidoo.getVendedorPedido());
		pst.setString(6, pedidoo.getResponsavelPedido());
		quantidade = pst.executeUpdate();
		
	} catch (SQLException e) {
		e.printStackTrace();
	}

	return quantidade;
	}

	//LISTAR
public List<Pedido> listAll(){
	List<Pedido> listaPedido= new ArrayList<Pedido>();
	
	try (Connection connection = this.conectar();
			PreparedStatement pst = connection.prepareStatement(SQL_SELECT_PEDIDOO);){

		ResultSet rs = pst.executeQuery();
		
		while(rs.next()) {
			Pedido pedido = new Pedido();
	
			pedido.setIdPedido(rs.getInt("IDPEDIDO"));
			pedido.setNome(rs.getString("NOME"));
			pedido.setDataPedido(java.sql.Date.valueOf((rs.getString("DATAPEDIDO"))));
			pedido.setQuantidade(rs.getInt("QUANTIDADE"));
			pedido.setUnidademedida(rs.getString("UNIDADEMEDIDA"));
			pedido.setVendedorPedido(rs.getString("VENDEDORPEDIDO"));
			pedido.setResponsavelPedido(rs.getString("RESPONSAVELPEDIDO"));
			listaPedido.add(pedido);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	return listaPedido;
	}
	
	//BUSCAR PELO ID
public Pedido findByID(int idPedido) {
	Pedido pedidoo = null;
	try (Connection connection = this.conectar();
			PreparedStatement pst = connection.prepareStatement(SQL_SELECT_PEDIDOO_ID);){

		pst.setInt(1, idPedido);
		ResultSet rs = pst.executeQuery();
		
		while(rs.next()) {
			pedidoo = new Pedido();
			
			pedidoo.setIdPedido(rs.getInt("IDPEDIDO"));
			pedidoo.setNome(rs.getString("NOME"));
			pedidoo.setDataPedido(java.sql.Date.valueOf(rs.getDate("DATAPEDIDO").toString()));
			pedidoo.setQuantidade(rs.getInt("QUANTIDADE"));
			pedidoo.setUnidademedida(rs.getString("UNIDADEMEDIDA"));
			pedidoo.setVendedorPedido(rs.getString("VENDEDORPEDIDO"));
			pedidoo.setResponsavelPedido(rs.getString("RESPONSAVELPEDIDO"));
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	return pedidoo;
	}

	//ALTERAR SINDICO
public int alterar(Pedido pedido) {
	int quantidade = 0;

	try (Connection connection = this.conectar();
			PreparedStatement pst = connection.prepareStatement(SQL_ALTERA_PEDIDOO);) {

		pst.setString(1, pedido.getNome()); //COM HERANÇA DA CLASSE MATERIAL
		pst.setDate(2, java.sql.Date.valueOf(pedido.getDataPedido().toString()));
		pst.setInt(3, pedido.getQuantidade()); //COM HERANÇA DA CLASSE MATERIAL
		pst.setString(4, pedido.getUnidademedida()); //COM HERANÇA DA CLASSE MATERIAL
		pst.setString(5, pedido.getVendedorPedido());
		pst.setString(6, pedido.getResponsavelPedido());
		pst.setInt(7, pedido.getIdPedido());

		quantidade = pst.executeUpdate();

	} catch (SQLException e) {
		e.printStackTrace();
	}

	return quantidade;
	}

	//DELETAR SINDICO
public int deletar(int idPedido) {
        int quantidade = 0;
        try (Connection connection = this.conectar();
                PreparedStatement pst = connection.prepareStatement(SQL_DELETA_PEDIDOO);) {
            pst.setInt(1, idPedido);
            
            quantidade = pst.executeUpdate();
            
        } catch (Exception e) {
            System.out.println(e);
        }
        return quantidade;
    }

final String SQL_SELECT_PEDIDO = "SELECT IDPEDIDO FROM PEDIDOO";

public ObservableList<String> listaApartamentos() throws SQLException{
    ObservableList options = FXCollections.observableArrayList();

    try (Connection connection = this.conectar();
            PreparedStatement pst = connection.prepareStatement(SQL_SELECT_PEDIDO);) {
        ResultSet rs = pst.executeQuery();

        while(rs.next()) {
            options.add(rs.getInt("IDPEDIDO"));
        }
    }
    return options;
}


	@Override
	public void ClassInter() {
		// TODO Auto-generated method stub

	}
}
