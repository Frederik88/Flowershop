/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { ConfigserverService } from './configserver.service';

describe('Service: Configserver', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ConfigserverService]
    });
  });

  it('should ...', inject([ConfigserverService], (service: ConfigserverService) => {
    expect(service).toBeTruthy();
  }));
});
