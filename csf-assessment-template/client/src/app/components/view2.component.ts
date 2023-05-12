import { Component, OnInit } from '@angular/core';
import { PhotoService } from '../services/photo.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-view2',
  templateUrl: './view2.component.html',
  styleUrls: ['./view2.component.css']
})
export class View2Component implements OnInit {

  bundles!: any[];
  
  
  
  constructor(private photoSvc: PhotoService, private router: Router){}



  ngOnInit(): void {
    this.getBundles();
  }

  getBundles(): void{
    this.photoSvc.getBundles().subscribe(
        bundles => {
            console.log(bundles);
            this.bundles = bundles;
        },
        error => console.error('Error getting bundles', error));
}


  toUpload(){
    this.router.navigate(['upload'])
  }

  viewDetails(bundleId: string) {
    this.router.navigate(['/view1', bundleId]);
  }
}
