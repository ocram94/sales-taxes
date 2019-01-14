package model;

public class Tax {

	private boolean exemptBaseTax;
	private boolean imported;
	
	public Tax() {}
	
	public Tax(boolean exemptBaseTax, boolean imported) {
		super();
		this.exemptBaseTax=exemptBaseTax;
		this.imported=imported;
	}

	public boolean isExemptBaseTax() {
		return exemptBaseTax;
	}

	public void setExemptBaseTax(boolean exemptBaseTax) {
		this.exemptBaseTax = exemptBaseTax;
	}

	public boolean isImported() {
		return imported;
	}

	public void setImported(boolean imported) {
		this.imported = imported;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (exemptBaseTax ? 1231 : 1237);
		result = prime * result + (imported ? 1231 : 1237);
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
		Tax other = (Tax) obj;
		if (exemptBaseTax != other.exemptBaseTax)
			return false;
		if (imported != other.imported)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "exemptBaseTax=" + exemptBaseTax + ", imported=" + imported;
	}
	
	
	
}
