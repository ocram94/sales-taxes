package service;

import java.util.ArrayList;
import java.util.List;

import com.app.web.rest.TaxResource;

import app.shopping.cart.utils.FormatData;
import dto.ProductDTO;
import dto.ReceiptDTO;
import dto.TaxDTO;

public class ProductService {

List<ProductDTO> shoppingBaskets;
	
	public ProductService() {
		shoppingBaskets=new ArrayList<ProductDTO>();
	}
	
	public List<ProductDTO> getAllProducts() {
		return shoppingBaskets;
	}
	
	public String getReceiptDetails() {
		return "Receipt Details";
	}
	
	public void addInputUser(ArrayList<String> listInputUser) {
		for(String inputUser: listInputUser) {
			int qta=FormatData.getQta(inputUser);
			double price=FormatData.getPrice(inputUser);
			boolean exemptBaseTax=false; //Default
			boolean imported=FormatData.isImported(inputUser);
			TaxDTO taxDTO=new TaxDTO(exemptBaseTax,imported);
			String description = FormatData.getDescription(inputUser);
			
			System.out.println("qta:"+qta);
			System.out.println("description:"+description);
			System.out.println("price:"+price);
			System.out.println("exemptBaseTax:"+exemptBaseTax);
			System.out.println("imported:"+imported);
			
			ProductDTO pDTO=new ProductDTO(qta,description,price,taxDTO);
			/*INSERT PRODUCT IN HASHSET*/
			addProduct(pDTO);
		}
		
	}
	
	
	
	public ReceiptDTO generateOutput(ArrayList<Boolean> myTax) {
    	ArrayList<Double> arraySalesTaxes = new ArrayList<Double>();
    	ArrayList<Double> priceTax = new ArrayList<Double>();
    	controlTax(myTax);
    	TaxResource taxController=new TaxResource();
    	arraySalesTaxes.addAll(taxController.getTax(getAllProducts()));
    	priceTax=getPriceShoppingBasketsTax(arraySalesTaxes);
    	
    	
    	double salesTaxes=0;
    	
    	for(double p: arraySalesTaxes) {
    		salesTaxes+=p;
    		salesTaxes = FormatData.rounded(salesTaxes);
    	}
    	
    	
    	
    	double total = getPriceShoppingBaskets()+salesTaxes;
    	total=FormatData.rounded(total);
    	
    	StringBuilder sb=new StringBuilder();
    	for(int i=0; i<getAllProducts().size(); i++) {
    		sb.append(getAllProducts().get(i).getQta()+" ");
    		if(getAllProducts().get(i).getTax().isImported())
    			sb.append("imported ");
    		sb.append(getAllProducts().get(i).getDescription()+" ");
    		sb.append(": ");
    		sb.append(priceTax.get(i));
    		sb.append("\n");
    	}
    	sb.append("Sales Taxes:"+salesTaxes);
    	sb.append("\n");
    	sb.append("Total:"+total);
    	
    	ReceiptDTO rDTO=new ReceiptDTO(sb.toString());
    	reset();
    	return rDTO;
    }
	
	public void addProduct(ProductDTO pDTO) {
		shoppingBaskets.add(pDTO);
	}
	
	public void addAllProducts(List<ProductDTO> listpDTO) {
		shoppingBaskets.addAll(listpDTO);
	}
	
	public double getPriceShoppingBaskets() {
		double price=0;
		for(ProductDTO pDTO: shoppingBaskets) {
			price+=pDTO.getPrice()*pDTO.getQta();
		}
		return price;
	}
	
	public ArrayList<Double> getPriceShoppingBasketsTax(ArrayList<Double> arraySalesTaxes) {
		ArrayList<Double> shoppingBasketsTax=new ArrayList<Double>();
		int k=0;
		for(int i=0; i<shoppingBaskets.size(); i++) {
			if(shoppingBaskets.get(i).getTax().isExemptBaseTax() && !shoppingBaskets.get(i).getTax().isImported())
				shoppingBasketsTax.add(shoppingBaskets.get(i).getPrice());
			else {
				shoppingBasketsTax.add(FormatData.rounded(shoppingBaskets.get(i).getPrice()+arraySalesTaxes.get(k)));
				k++;
			}
		}
		return shoppingBasketsTax;
	}
	
	
	
	
	private void controlTax(ArrayList<Boolean> arrayTax) {
		for(int i=0; i<this.shoppingBaskets.size(); i++) {

			this.shoppingBaskets.get(i).getTax().setExemptBaseTax(arrayTax.get(i));
		}
	}
	
	
	public void reset() {
		this.shoppingBaskets=new ArrayList<ProductDTO>();
	}
	
}
