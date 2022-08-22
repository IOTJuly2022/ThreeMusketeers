import { Component, OnInit } from '@angular/core';
import { OrderService } from '../../_services/order.service'

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.scss']
})
export class CartComponent implements OnInit {

  constructor(private orderService : OrderService) {  }
  quantityCounter : number = 0;
  total : number = 0;
  availItems : boolean = false  ;
  orderList : any;
  ngOnInit(): void {
    this.orderService.getAllOrdersForCurrentUsers().subscribe((orders : any) => {
      if(!orders) this.availItems = false;
      this.orderList = orders[0];
      this.availItems = true;
      //this.total = this.orderList.orderDetails[0].product.price;
      this.calculatedTotal(this.orderList);
    });
  }


  updateQuantity(){

  }
  calculatedTotal(orderList : any){
    for(let i = 0; i < orderList.orderDetails.length; i++){
      this.total += orderList.orderDetails[i].product.price;
    }
  }
}
