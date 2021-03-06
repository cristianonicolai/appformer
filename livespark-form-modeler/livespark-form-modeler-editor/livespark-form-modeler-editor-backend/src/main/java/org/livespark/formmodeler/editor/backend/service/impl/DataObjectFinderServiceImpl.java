/*
 * Copyright 2016 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.livespark.formmodeler.editor.backend.service.impl;

import java.util.List;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.errai.bus.server.annotations.Service;
import org.kie.workbench.common.screens.datamodeller.service.DataModelerService;
import org.kie.workbench.common.services.datamodeller.core.DataModel;
import org.kie.workbench.common.services.datamodeller.core.DataObject;
import org.kie.workbench.common.services.datamodeller.core.ObjectProperty;
import org.kie.workbench.common.services.shared.project.KieProjectService;
import org.livespark.formmodeler.editor.service.DataObjectFinderService;
import org.uberfire.backend.vfs.Path;

@Service
@Dependent
public class DataObjectFinderServiceImpl implements DataObjectFinderService {

    @Inject
    private KieProjectService projectService;

    @Inject
    private DataModelerService dataModelerService;

    @Override
    public DataObject getDataObject( String typeName, Path path ) {
        DataModel dataModel = dataModelerService.loadModel( projectService.resolveProject( path ) );

        return dataModel.getDataObject( typeName );
    }

    @Override
    public List<ObjectProperty> getDataObjectProperties( String typeName, Path path ) {
        return getDataObject( typeName, path ).getProperties();
    }
}
