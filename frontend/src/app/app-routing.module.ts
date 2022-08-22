import { NgModule } from '@angular/core';
import { ExtraOptions, RouterModule, Routes } from '@angular/router';
import { CatalogComponent } from './pages/catalog/catalog.component';
import {HomeComponent} from "./pages/home/home.component";
import {LoginComponent} from "./pages/login/login.component";

const routes: Routes = [
  {path: '', pathMatch: 'full', component: HomeComponent},
<<<<<<< Updated upstream
  {path: 'login', component: LoginComponent}
=======
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'catalog', component: CatalogComponent},
>>>>>>> Stashed changes
];
const routerOptions: ExtraOptions = {
  scrollPositionRestoration: 'enabled',
  anchorScrolling: 'enabled',
  scrollOffset: [0, 64],
};
@NgModule({
  imports: [RouterModule.forRoot(routes, routerOptions)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
