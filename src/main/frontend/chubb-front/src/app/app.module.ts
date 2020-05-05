import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { ItemListComponent } from './components/item-list/item-list.component';
import { UserListComponent } from './components/user-list/user-list.component';
import { UserFormComponent } from './components/user-form/user-form.component';
import { ItemFormComponent } from './components/item-form/item-form.component';
import { ItemService } from '../app/service/item.service';
import { UserService } from '../app/service/user.service';
import { AppComponent } from './app.component';

@NgModule({
  declarations: [
    AppComponent,
    ItemListComponent,
    UserListComponent,
    UserFormComponent,
    ItemFormComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [ItemService,UserService],
  bootstrap: [AppComponent]
})
export class AppModule { }
