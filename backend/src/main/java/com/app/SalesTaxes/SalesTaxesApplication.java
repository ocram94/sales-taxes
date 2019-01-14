package com.app.SalesTaxes;

import java.util.ArrayList;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import app.shopping.cart.utils.FormatData;
import app.shopping.cart.utils.SyntaxControl;
import com.app.web.rest.ProductResource;
import com.app.web.rest.TaxResource;

import dto.ProductDTO;
import dto.TaxDTO;

@SpringBootApplication
public class SalesTaxesApplication {

	private static ProductResource productController;
	
	public SalesTaxesApplication() {
		productController=new ProductResource();
	}
	public static void main(String[] args) {
		SpringApplication.run(SalesTaxesApplication.class, args);
		
    	//startFromConsole();
    }
	
	private static void startFromConsole() {
		TaxResource taxController=new TaxResource();
    	
    	
    	Scanner sc=new Scanner(System.in);
		String inputUser="";
		
		while(true) {
			System.out.println("Enter the product to add to the shopping basket (press only enter to stop):");
			inputUser=sc.nextLine();
			if(inputUser.equals("")) {
				break;
			}
			System.out.println("The product added was in one of these categories [Books,Foods,Medicines] (y or n):");
			String inputUserCategory=sc.nextLine();
			
			
			if(!SyntaxControl.control(inputUser)) {
				System.out.println("Error: formatting control failed\nExample: '1 book at 12.34'");
			}else if(!(inputUserCategory.equals("y")||inputUserCategory.equals("n"))) {
				System.out.println("Error: formatting control failed\nExample of accepted answers: 'y' or 'n'");
			}else {
				/*CREATE PRODUCT*/
				int qta=FormatData.getQta(inputUser);
				double price=FormatData.getPrice(inputUser);
				boolean exemptBaseTax=inputUserCategory.equals("y")?true:false;
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
				productController.addProduct(pDTO);
			}
			
		}
		
		
		System.out.println("Receipt details for shopping basket:");
		//System.out.println(productController.generateOutput().getReceiptPrint());
	}
    
    
}
