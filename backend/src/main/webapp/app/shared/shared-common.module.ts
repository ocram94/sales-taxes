import { NgModule } from '@angular/core';

import { ProjectJhipsterSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent } from './';

@NgModule({
    imports: [ProjectJhipsterSharedLibsModule],
    declarations: [JhiAlertComponent, JhiAlertErrorComponent],
    exports: [ProjectJhipsterSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent]
})
export class ProjectJhipsterSharedCommonModule {}
