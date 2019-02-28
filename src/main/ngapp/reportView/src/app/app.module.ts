import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';

import {TableModule} from 'primeng/table';
import { DataTableModule, SharedModule, ChartModule } from 'primeng/primeng';
import { ChartsModule } from 'ng2-charts';
import { HpqcReportComponent } from './reports/reports.component';
@NgModule({
  declarations: [
		AppComponent,
		HpqcReportComponent
  ],
  imports: [
		BrowserModule,
		TableModule,
		DataTableModule,
		ChartsModule,
		SharedModule,
		ChartModule
  ],
  providers: [
	],
  bootstrap: [AppComponent]
})
export class AppModule { }
