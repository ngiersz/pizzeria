import { Component, OnInit } from '@angular/core';
import {HttpErrorResponse} from '@angular/common/http';
import {ApiService} from '../shared/api.service';

@Component({
  selector: 'app-logs',
  templateUrl: './logs.component.html',
  styleUrls: ['./logs.component.css']
})
export class LogsComponent implements OnInit {
  logs: Log[] = [];
  constructor(private apiService: ApiService) {}
  ngOnInit() {
    this.getAllLogs();
  }
  getAllLogs() {
    this.apiService.getAllLogs().subscribe(
      res => {
        this.logs = JSON.parse(JSON.stringify(res));
        console.log(this.logs);
      },
      (error: HttpErrorResponse) => {
        alert(error);
      }
    );
  }
}

export interface Log {
  id: string;
  dateTime: string;
  message: string;
}
