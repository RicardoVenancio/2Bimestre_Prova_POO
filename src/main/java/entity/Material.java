package entity;

public abstract class Material {

	private String Nome;
	private int Quantidade;
	private String Unidademedida;
	
	
	///GET SET
	public String getNome() {
		return Nome;
	}
	public void setNome(String nome) {
		Nome = nome;
	}
	public int getQuantidade() {
		return Quantidade;
	}
	public void setQuantidade(int quantidade) {
		Quantidade = quantidade;
	}
	public String getUnidademedida() {
		return Unidademedida;
	}
	public void setUnidademedida(String unidademedida) {
		Unidademedida = unidademedida;
	}
	
	// HASHCODE
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Nome == null) ? 0 : Nome.hashCode());
		result = prime * result + Quantidade;
		result = prime * result + ((Unidademedida == null) ? 0 : Unidademedida.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Material other = (Material) obj;
		if (Nome == null) {
			if (other.Nome != null)
				return false;
		} else if (!Nome.equals(other.Nome))
			return false;
		if (Quantidade != other.Quantidade)
			return false;
		if (Unidademedida == null) {
			if (other.Unidademedida != null)
				return false;
		} else if (!Unidademedida.equals(other.Unidademedida))
			return false;
		return true;
	}
	
	//TOSTRING
	@Override
	public String toString() {
		return "Material [Nome=" + Nome + ", Quantidade=" + Quantidade + ", Unidademedida=" + Unidademedida + "]";
	}
	
	//CONSTRUCTOR POVOADO
	public Material(String nome, int quantidade, String unidademedida) {
		super();
		Nome = nome;
		Quantidade = quantidade;
		Unidademedida = unidademedida;
	}
	
	//CONSTRUCTOR VAZIO
	public Material() {
		super();
	}
		
	
}
