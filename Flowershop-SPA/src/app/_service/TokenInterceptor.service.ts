import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
import { Observable } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { HttpCancelService } from './HttpCancel.service';
import { registerLocaleData } from '@angular/common';

@Injectable({
  providedIn: 'root'
})
export class TokenInterceptorService implements HttpInterceptor {
  constructor(private httpCancelService: HttpCancelService) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    req = req.clone({
      withCredentials: false,
      setHeaders: {
        Accept: 'application/json, application/xml',
      }
    });
    req.headers.set('Access-Control-Allow-Origin', req.headers.get('origin'));
    const token = localStorage.getItem('token');
    if (token != null) {
      req = req.clone({
        setHeaders: {
          Authorization: token
        }
      });
    }
    return next.handle(req).pipe(takeUntil(this.httpCancelService.onCancelPendingRequests()));
  }

}
