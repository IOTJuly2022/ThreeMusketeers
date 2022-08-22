import { Component, OnInit } from '@angular/core';
import { OrderService } from '../../_services/order.service'

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.scss']
})
export class CartComponent implements OnInit {

  constructor(private order : OrderService) {  }

  availItems : boolean = false;
  orderList : any;
  ngOnInit(): void {
    this.order.getAllOrdersForCurrentUsers().subscribe((orders : any) => {
      if(!orders) this.availItems = false;

      this.orderList = orders[0];
      this.availItems = true;
    });
  }


  showItems(){

  }

}
