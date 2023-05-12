import { HttpClient } from '@angular/common/http';
import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { firstValueFrom } from 'rxjs';
import { PhotoService } from '../services/photo.service';
import { Upload } from '../models';

@Component({
  selector: 'app-view0',
  templateUrl: './view0.component.html',
  styleUrls: ['./view0.component.css']
})
export class View0Component implements OnInit {
  @ViewChild('imageFile') imageFile!: any;

  searchForm!: FormGroup;
  name!: string;
  title!: string;
  comments!: string;
  archive!: File;


  constructor(
    private fb: FormBuilder,
    private router: Router,
    private http: HttpClient,
    private uploadSvc: PhotoService
  ) { }

  ngOnInit(): void {
    this.searchForm = this.createForm();
  }

  private createForm(): FormGroup {
    return this.fb.group({
      name: ['', Validators.required],
      title: ['', Validators.required],
      comments: [''],
      archive: [null, Validators.required]
    });
  }

  onSubmit(): void {


    this.name = this.searchForm.value['name'];
    this.title = this.searchForm.value['title'];
    this.comments = this.searchForm.value['comments'];
    this.archive = this.searchForm.value['archive'];
    console.log('Form submitted!');
    console.log('Name:', this.name);
    console.log('Title:', this.title);
    console.log('Comments:', this.comments);
    console.log('Archive:', this.archive);

    const upload: Upload = {
      name: this.name,
      title: this.title,
      comments: this.comments,
      archive: this.archive
    };

    this.uploadSvc.postUpload(upload).subscribe((response) => {

      const bundleId = response.bundleId;

      this.router.navigate(['/view1', bundleId]);
    }, (error) => {
      console.error('Error uploading file:', error);
      
    });
  }

  onFileSelected(event: any): void {
    const files = event.target.files;
    if (files && files.length > 0) {
      this.archive = files[0];
      this.searchForm.patchValue({ archive: this.archive });
    }
  }

  onCancel(): void {
    this.router.navigate(["/"])
  }

}