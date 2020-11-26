package entity;

import java.sql.Date;

public class Pedido extends Material{

	public Pedido() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Pedido(String nome, int quantidade, String unidademedida) {
		super(nome, quantidade, unidademedida);
	}
	
	public Pedido(String nome, int quantidade, String unidademedida, int idPedido, Date dataPedido,
			String responsavelPedido, String vendedorPedido) {
		super(nome, quantidade, unidademedida);
		this.idPedido = idPedido;
		this.dataPedido = dataPedido;
		this.responsavelPedido = responsavelPedido;
		this.vendedorPedido = vendedorPedido;
	}
	

	public Pedido(String nome, int quantidade, String unidademedida, Date dataPedido, String responsavelPedido,
			String vendedorPedido) {
		super(nome, quantidade, unidademedida);
		this.dataPedido = dataPedido;
		this.responsavelPedido = responsavelPedido;
		this.vendedorPedido = vendedorPedido;
	}



	private int idPedido;
	private Date dataPedido;
	private String responsavelPedido;
	private String vendedorPedido;
	
	
	//GET SET
	public int getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}
	public Date getDataPedido() {
		return dataPedido;
	}
	public void setDataPedido(Date dataPedido) {
		this.dataPedido = dataPedido;
	}
	public String getResponsavelPedido() {
		return responsavelPedido;
	}
	public void setResponsavelPedido(String responsavelPedido) {
		this.responsavelPedido = responsavelPedido;
	}
	public String getVendedorPedido() {
		return vendedorPedido;
	}
	public void setVendedorPedido(String vendedorPedido) {
		this.vendedorPedido = vendedorPedido;
	}
	
	
	// HASH COD
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((dataPedido == null) ? 0 : dataPedido.hashCode());
		result = prime * result + idPedido;
		result = prime * result + ((responsavelPedido == null) ? 0 : responsavelPedido.hashCode());
		result = prime * result + ((vendedorPedido == null) ? 0 : vendedorPedido.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		if (dataPedido == null) {
			if (other.dataPedido != null)
				return false;
		} else if (!dataPedido.equals(other.dataPedido))
			return false;
		if (idPedido != other.idPedido)
			return false;
		if (responsavelPedido == null) {
			if (other.responsavelPedido != null)
				return false;
		} else if (!responsavelPedido.equals(other.responsavelPedido))
			return false;
		if (vendedorPedido == null) {
			if (other.vendedorPedido != null)
				return false;
		} else if (!vendedorPedido.equals(other.vendedorPedido))
			return false;
		return true;
	}
	

	
	//TO STRING
	@Override
	public String toString() {
		return "Pedido [idPedido=" + idPedido + ", dataPedido=" + dataPedido + ", responsavelPedido="
				+ responsavelPedido + ", vendedorPedido=" + vendedorPedido + ", getNome()=" + getNome()
				+ ", getQuantidade()=" + getQuantidade() + ", getUnidademedida()=" + getUnidademedida() + "]";
	}
	
}
