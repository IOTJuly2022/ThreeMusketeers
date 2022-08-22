import { Component, OnInit } from '@angular/core';
import { Product, GPU, RAM, CPU, Mobo } from 'src/app/models/product.model';
import { ProductService } from 'src/app/_services/catalog/products.service';
import { OrderService } from '../../_services/order.service'
import {AlertService} from "../../_services/alert.service";

@Component({
  selector: 'app-catalog',
  templateUrl: './catalog.component.html',
  styleUrls: ['./catalog.component.scss']
})
export class CatalogComponent implements OnInit {

  anchors: Array<string> = ['All Products','Graphics Cards','RAM & Memory','Motherboards','Processors']

  products: Product[] = [];
  gpus: GPU[] = [];
  ramkits: RAM[] = [];
  cpus: CPU[] = [];
  mobos: Mobo[] = [];

    constructor(private ps: ProductService,
    private orderService : OrderService,
    private alertService : AlertService){
    }

    scrollToElement($element:Element) : void {
      $element.scrollIntoView({behavior: "smooth", block: "start", inline: "nearest"});
    }

    ngOnInit() {
        this.ps.findAll().subscribe(data => {
            this.products = data;
        });

        this.ps.findGPU().subscribe(data => {
          this.gpus = data;
        });

        this.ps.findCPU().subscribe(data => {
          this.cpus = data;
        });

        this.ps.findRAM().subscribe(data => {
          this.ramkits = data;
        });

        this.ps.findMOBO().subscribe(data => {
          this.mobos = data;
        });
    }

    addToCart(product : any){
      this.orderService.addProductToCart(product).subscribe((order : any) => {
        //Want to add the product to the cart and then check and alert user that it added
        this.alertService.success('Item Added to Cart');
      }),((error: any)=>{
        this.alertService.error(error.error);
      });

    }

}
