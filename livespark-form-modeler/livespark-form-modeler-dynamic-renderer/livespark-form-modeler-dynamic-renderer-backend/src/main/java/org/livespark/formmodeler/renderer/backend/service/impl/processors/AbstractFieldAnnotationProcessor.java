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

package org.livespark.formmodeler.renderer.backend.service.impl.processors;

import java.lang.annotation.Annotation;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.livespark.formmodeler.model.FieldDefinition;
import org.livespark.formmodeler.renderer.backend.service.impl.FieldSetting;
import org.livespark.formmodeler.service.FieldManager;

public abstract class AbstractFieldAnnotationProcessor<T extends FieldDefinition> implements FieldAnnotationProcessor {

    protected FieldManager fieldManager;

    protected abstract T buildFieldDefinition( Annotation annotation, FieldSetting setting );

    @Inject
    public AbstractFieldAnnotationProcessor( FieldManager fieldManager ) {
        this.fieldManager = fieldManager;
    }

    @Override
    public FieldDefinition getFieldDefinition( Annotation annotation, FieldSetting setting ) {
        FieldDefinition field = buildFieldDefinition( annotation, setting );

        if ( field == null ) {
            field = fieldManager.getDefinitionByValueType( setting.getTypeInfo() );

            if ( field == null ) {
                return null;
            }
        }

        field.setId( setting.getFieldName() );
        field.setName( setting.getFieldName() );
        field.setLabel( setting.getLabel() );
        field.setModelName( setting.getFieldName() );

        if ( !StringUtils.isEmpty( setting.getProperty() ) ) {
            field.setBoundPropertyName( setting.getProperty() );
        }

        return field;
    }

    @Override
    public boolean isDefault() {
        return false;
    }
}
