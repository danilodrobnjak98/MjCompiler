package rs.ac.bg.etf.pp1;

import java.util.Objects;

public class GotoLabel {
	
	private String imeLabela;
	private int iAdresa;
	
	
	
	public GotoLabel(String imeLabela, int iAdresa) {
		super();
		this.imeLabela = imeLabela;
		this.iAdresa = iAdresa;
	}
	public String getImeLabele() {
		return imeLabela;
	}
	public void setImeLabela(String imeLabela) {
		this.imeLabela = imeLabela;
	}
	public int getIdresa() {
		return iAdresa;
	}
	public void setiAdresa(int iAdresa) {
		this.iAdresa = iAdresa;
	}
	@Override
	public String toString() {
		return "GotoLabel [imeLabela=" + imeLabela + ", iAdresa=" + iAdresa + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GotoLabel other = (GotoLabel) obj;
		return iAdresa == other.iAdresa && Objects.equals(imeLabela, other.imeLabela);
	} 
	
	
	

}
