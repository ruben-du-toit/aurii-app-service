import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {LoggerService} from "./logger/logger.service";

@NgModule({
  declarations: [],
  imports: [
    CommonModule
  ],
  providers: [LoggerService]
})
export class CoreModule { }
