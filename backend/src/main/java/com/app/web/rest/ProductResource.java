package com.app.web.rest;

import com.app.config.Constants;
import com.app.domain.User;
import com.app.repository.UserRepository;
import com.app.security.AuthoritiesConstants;
import com.app.service.MailService;
import com.app.service.UserService;
import com.app.service.dto.UserDTO;
import com.app.web.rest.errors.BadRequestAlertException;
import com.app.web.rest.errors.EmailAlreadyUsedException;
import com.app.web.rest.errors.LoginAlreadyUsedException;
import com.app.web.rest.util.HeaderUtil;
import com.app.web.rest.util.PaginationUtil;
import com.codahale.metrics.annotation.Timed;

import dto.ProductDTO;
import dto.ReceiptDTO;
import dto.TaxDTO;
import io.github.jhipster.web.util.ResponseUtil;
import service.ProductService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

/**
 * REST controller for managing users.
 * <p>
 * This class accesses the User entity, and needs to fetch its collection of authorities.
 * <p>
 * For a normal use-case, it would be better to have an eager relationship between User and Authority,
 * and send everything to the client side: there would be no View Model and DTO, a lot less code, and an outer-join
 * which would be good for performance.
 * <p>
 * We use a View Model and a DTO for 3 reasons:
 * <ul>
 * <li>We want to keep a lazy association between the user and the authorities, because people will
 * quite often do relationships with the user, and we don't want them to get the authorities all
 * the time for nothing (for performance reasons). This is the #1 goal: we should not impact our users'
 * application because of this use-case.</li>
 * <li> Not having an outer join causes n+1 requests to the database. This is not a real issue as
 * we have by default a second-level cache. This means on the first HTTP call we do the n+1 requests,
 * but then all authorities come from the cache, so in fact it's much better than doing an outer join
 * (which will get lots of data from the database, for each HTTP call).</li>
 * <li> As this manages users, for security reasons, we'd rather have a DTO layer.</li>
 * </ul>
 * <p>
 * Another option would be to have a specific JPA entity graph to handle this case.
 */
@RestController
@RequestMapping("/api")
public class ProductResource {

	ProductService productService;
	TaxResource taxController;
	public ProductResource() {
		productService=new ProductService();
		taxController=new TaxResource();
	}
	
	@GetMapping("/getProducts")
	public ProductDTO getProducts() {
		return new ProductDTO(1,"des",3.6,new TaxDTO(true,true));
	}
	
	@GetMapping("/getAllProducts")
	@CrossOrigin
	public List<ProductDTO> getAllProducts() {
		return productService.getAllProducts();
	}
	
	@PostMapping("/test")
	@CrossOrigin
	public ProductDTO test(@RequestBody ProductDTO pDTO) {
		return pDTO;
	}
	
	@PostMapping("/addInputUser")
	public void addInputUser(@RequestBody ArrayList<String> listInputUser) {
		productService.addInputUser(listInputUser);
	}

	@PostMapping("/generateOutput")
	public ReceiptDTO generateOutput(@RequestBody ArrayList<Boolean> myTax) {
		ReceiptDTO rDTO=new ReceiptDTO();
		rDTO=productService.generateOutput(myTax);
		return rDTO;
	}
	
	@GetMapping("/getReceiptDetails")
	public String getReceiptDetails() {
		return productService.getReceiptDetails();
	}
	
	@PostMapping("/addProduct")
	public void addProduct(ProductDTO pDTO) {
		productService.addProduct(pDTO);
	}
	
	@PostMapping("/addAllProducts")
	public void addAllProducts(ArrayList<ProductDTO> listpDTO) {
		productService.addAllProducts(listpDTO);
	}
	
	@GetMapping("/getPriceShoppingBaskets")
	public double getPriceShoppingBaskets() {
		return productService.getPriceShoppingBaskets();
	}
	
	@GetMapping("/getPriceShoppingBasketsTax")
	public ArrayList<Double> getPriceShoppingBasketsTax(ArrayList<Double> arraySalesTaxes) {
		return productService.getPriceShoppingBasketsTax(arraySalesTaxes);
	}
}
