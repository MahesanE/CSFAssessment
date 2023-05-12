import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { PhotoService } from '../services/photo.service';

@Component({
  selector: 'app-view1',
  templateUrl: './view1.component.html',
  styleUrls: ['./view1.component.css']
})
export class View1Component implements OnInit {

   bundleId!: string;
   archive!: any;

  constructor(
    private route: ActivatedRoute,
    private photoService: PhotoService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.bundleId = this.route.snapshot.paramMap.get('bundleId')!;
    this.getBundlebyBundleId(this.bundleId);
  }

  getBundlebyBundleId(bundleId: string): void {
    this.photoService.getBundlebyBundleId(bundleId)
      .subscribe(
        data => { 
          this.archive = data; 
        },
        error => { 
          console.error('Error fetching bundle:', error); 
        });
  }

  onBack(){
    this.router.navigate([''])
  }
}
