import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { CPU, GPU, Mobo, Product, RAM } from '../../models/product.model';
import {filter, Observable, Subject} from "rxjs";

@Injectable()
export class ProductService {

  private productApi: string = 'http://localhost:5000/v1/catalog';
  private gpuApi: string = 'http://localhost:5000/v1/catalog/gpu';
  private ramApi: string = 'http://localhost:5000/v1/catalog/memory';
  private moboApi: string = 'http://localhost:5000/v1/catalog/motherboard';
  private cpuApi: string = 'http://localhost:5000/v1/catalog/processor';

  // Ref annotations in ProductController
  constructor(private http: HttpClient) {
  }

  public findAll(): Observable<Product[]> {
    return this.http.get<Product[]>(this.productApi);
  }

  public findGPU(): Observable<GPU[]> {
    return this.http.get<GPU[]>(this.gpuApi);
  }

  public findCPU(): Observable<CPU[]> {
    return this.http.get<CPU[]>(this.cpuApi);
  }

  public findRAM(): Observable<RAM[]> {
    return this.http.get<RAM[]>(this.ramApi);
  }

  public findMOBO(): Observable<Mobo[]> {
    return this.http.get<Mobo[]>(this.moboApi);
  }

  public save(item: Product) {
    return this.http.post<Product>(this.productApi, item);
  }
}