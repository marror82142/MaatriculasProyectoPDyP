import { Component, OnInit } from '@angular/core';
import pdfMake from 'pdfmake/build/pdfmake';
import pdfFonts from 'pdfmake/build/vfs_fonts';
pdfMake.vfs = pdfFonts.pdfMake.vfs;

@Component({
  selector: 'app-pdf',
  templateUrl: './pdf.component.html',
  styleUrls: ['./pdf.component.css']
})
export class PdfComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  createPdf(){
    console.log('entro al metodo');
    const pdfDefinition: any = {
      content:[{
        text: 'hola mundo'
      }]
    }
    console.log(pdfDefinition);
    const pdf = pdfMake.createPdf(pdfDefinition);
    console.log(pdf);
    pdf.open();
  }

}
