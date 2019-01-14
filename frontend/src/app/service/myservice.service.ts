import { User } from './../model/user';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { Product } from '../model/product';
import { Receipt } from '../model/receipt';

@Injectable({
  providedIn: 'root'
})
export class MyService {

  constructor(private http: HttpClient) { }

  URL="http://localhost:8080/api/";


  static auth() {
    var user = JSON.parse(localStorage.getItem("currentUser")) as User;
    if(user) {
        console.log(user.authorization);
        return "Bearer " + user.authorization;
    } else {
        return "";
    }
  }

  public Login(username: string, password: string) {
    return this.http.post(this.URL+"authenticate", {
        username: username,
        password: password
    }).pipe(tap((response: any) => {
        localStorage.setItem("currentUser", JSON.stringify({ "authorization": response.id_token }));
    }));
}





  public addInputUser(products:string[]): Observable <any> {
    return this.http.post<any>(this.URL+"addInputUser",products,{
      headers:{
        'Authorization': MyService.auth()
      }
    })
          .pipe(tap( (response) => console.log(response)));
  }

  public generateOutput(tax:boolean[]): Observable <Receipt> {
    return this.http.post<Receipt>(this.URL+"generateOutput",tax,{
      headers:{
        'Authorization': MyService.auth()
      }
    })
          .pipe(tap( (response) => console.log(response)));
  }

  


}
