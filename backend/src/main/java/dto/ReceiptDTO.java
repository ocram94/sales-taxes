package dto;

import model.Receipt;

public class ReceiptDTO {

private String receiptPrint;
	
	public ReceiptDTO() {}
	
	public ReceiptDTO(String receiptPrint) {
		this.receiptPrint=receiptPrint;
	}

	public String getReceiptPrint() {
		return receiptPrint;
	}

	public void setReceiptPrint(String receiptPrint) {
		this.receiptPrint = receiptPrint;
	}

	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((receiptPrint == null) ? 0 : receiptPrint.hashCode());
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
		ReceiptDTO other = (ReceiptDTO) obj;
		if (receiptPrint == null) {
			if (other.receiptPrint != null)
				return false;
		} else if (!receiptPrint.equals(other.receiptPrint))
			return false;
		return true;
	}
	
}
