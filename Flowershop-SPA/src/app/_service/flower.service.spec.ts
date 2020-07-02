/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { FlowerService } from './flower.service';

describe('Service: Flower', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [FlowerService]
    });
  });

  it('should ...', inject([FlowerService], (service: FlowerService) => {
    expect(service).toBeTruthy();
  }));
});
