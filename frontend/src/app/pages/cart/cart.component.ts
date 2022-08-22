import { Component, OnInit } from '@angular/core';
import { OrderService } from '../../_services/order.service'
import {AlertService} from "../../_services/alert.service";

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.scss']
})
export class CartComponent implements OnInit {

  constructor(private orderService : OrderService,
              private alertService: AlertService) {  }
  quantityCounter : number = 0;
  total : number = 0;
  availItems : boolean = false;
  orderList : any;

  ngOnInit(): void {
    this.orderService.getAllOrdersForCurrentUsers().subscribe((orders : any) => {
      if(!orders) this.availItems = false;
      this.orderList = orders[0];
      this.availItems = true;
      this.calculatedTotal(this.orderList);
    });
  }

  //Display service error, not implemented currently
  checkout(){
    this.alertService.error('Checkout is not available at this time');
  }

  //Update Quantity of the Product from UI
  updateQuantity(orderDetail : any){

  }

  //Total price calculated for Quantity*Price
  calculatedTotal(orderList : any){
    for(let i = 0; i < orderList.orderDetails.length; i++){
      this.total += orderList.orderDetails[i].product.price * orderList.orderDetails[i].quantity;
    }
  }
}
