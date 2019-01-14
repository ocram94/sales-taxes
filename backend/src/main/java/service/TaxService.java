package service;

import java.util.ArrayList;
import java.util.List;

import app.shopping.cart.utils.FormatData;
import dto.ProductDTO;

public class TaxService {

	
public TaxService() {}
	
	
	public ArrayList<Double> getTax(List<ProductDTO> lDTO) {
		ArrayList<Double> tax=new ArrayList<Double>();
		for(ProductDTO pDTO: lDTO) {
			if(!pDTO.getTax().isExemptBaseTax() && pDTO.getTax().isImported()) {
				int totalTax = pDTO.getTax().getBaseTax() + pDTO.getTax().getImportedTax();
				double numerator = FormatData.roundedCents( pDTO.getPrice()*totalTax );
				tax.add(FormatData.roundedCents( (numerator)/100 ));
			}else if(!pDTO.getTax().isExemptBaseTax()) {
				double numerator = FormatData.roundedCents( pDTO.getPrice()*pDTO.getTax().getBaseTax() );
				tax.add(FormatData.roundedCents( (numerator)/100 ));
			}else if(pDTO.getTax().isImported()) {
				double numerator = FormatData.roundedCents( pDTO.getPrice()*pDTO.getTax().getImportedTax() );
				tax.add(FormatData.roundedCents( (numerator)/100 ));
			}
		}
		return tax;
	}
	
	/*
	public double getImportedTax(List<ProductDTO> lDTO) {
		double importedTax=0;
		for(ProductDTO pDTO: lDTO) {
			if(pDTO.getTax().isImported()) {
				double numerator=FormatData.rounded(pDTO.getPrice()*pDTO.getTax().getImportedTax());
				importedTax+=FormatData.rounded( (numerator)/100 );
			}
		}
		return importedTax;
	}
	*/
	
	
}
