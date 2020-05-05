import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Item } from '../model/item';
import { Observable } from 'rxjs';

@Injectable()
export class ItemService {

  private itemsUrl: string;

  private createItemUrl: string;

  constructor(private http: HttpClient) {
    this.itemsUrl = 'http://localhost:8081/item/findall';
    this.createItemUrl = 'http://localhost:8081/item';
  }

  public findAll(): Observable<Item[]> {
    console.log( this.itemsUrl);
    return this.http.get<Item[]>(this.itemsUrl);
  }

  public save(item: Item) {
    return this.http.post<Item>(this.createItemUrl, item);
  }

}
