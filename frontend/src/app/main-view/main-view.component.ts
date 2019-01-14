import { Tax } from './../model/tax';
import { HttpResponse } from '@angular/common/http';
import { MyService } from './../service/myservice.service';
import { NgForm } from '@angular/forms';
import { Component, OnInit, ViewChild } from '@angular/core';
import { Receipt } from '../model/receipt';
import { Product } from '../model/product';

@Component({
  selector: 'app-main-view',
  templateUrl: './main-view.component.html',
  styleUrls: ['./main-view.component.css']
})
export class MainViewComponent implements OnInit {

  products0:string;
  checkBoxSelected0:boolean;
  myproducts;
  products;
  checkBoxSelected;
  myresponse="";
 
  constructor(private myService: MyService) { }

  ngOnInit() {
    this.myService.Login("admin","admin").subscribe((response)=>console.log("token:",response));
    this.inizialize();
  }

  inizialize(){
    this.products0="";
    this.checkBoxSelected0=false;
    this.myproducts=new Array<String>();
    this.products=new Array<String>();
    this.checkBoxSelected=new Array<boolean>();
  }


  delete(i:number){
    if (i !== -1) {
      this.checkBoxSelected.splice(i,1);
      this.myproducts.splice(i,1);
      this.products.splice(i,1);
    }
  }

  addInputText(){
    this.myproducts.push("");
    this.products.push("");
    this.checkBoxSelected.push(false);    
  }

  

  insert(e,idx){
    console.log(e," idx:",idx);
    this.products[idx]=this.products[idx]+e;
  }


  @ViewChild("form") form: NgForm;
  viewArray(){
    if(this.form.invalid) {
      return;
    }

    console.log(this.products0);
    let temp="";
    if(this.products0!=""){
      this.products.splice(0,0,this.products0);
      this.checkBoxSelected.splice(0,0,this.checkBoxSelected0);
    }


    console.log("Tutti i prodotti:",this.products);
    console.log("Tutti le checkBox:",this.checkBoxSelected);
    this.addInputUser(this.products,this.checkBoxSelected);
    

    this.inizialize();
  }

    
  addInputUser(products:string[],tax:boolean[]){

    this.myService.addInputUser(products).subscribe((response)=>{
      this.generateOutput(tax)}
      );
  }

  generateOutput(tax:boolean[]){
    this.myService.generateOutput(tax).subscribe((response)=>{
      console.log(response);
      this.myresponse=response.receiptPrint;
    });
  }

}
