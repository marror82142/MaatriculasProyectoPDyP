import {Component} from '@angular/core';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})

export class FooterComponent {
  author: any = {name: 'Mateo', lastname:'Arroyave'};
  year = '2022';
}
