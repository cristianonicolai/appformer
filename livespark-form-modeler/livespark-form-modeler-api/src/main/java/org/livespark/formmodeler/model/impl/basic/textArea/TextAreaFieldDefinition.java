/*
 * Copyright 2016 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.livespark.formmodeler.model.impl.basic.textArea;

import javax.validation.constraints.Min;

import org.jboss.errai.common.client.api.annotations.Portable;
import org.jboss.errai.databinding.client.api.Bindable;
import org.livespark.formmodeler.metaModel.FieldDef;
import org.livespark.formmodeler.model.FieldDefinition;
import org.livespark.formmodeler.model.impl.basic.HasPlaceHolder;
import org.livespark.formmodeler.model.impl.basic.HasRows;
import org.livespark.formmodeler.service.impl.fieldProviders.TextAreaFieldProvider;

/**
 * @author Pere Fernandez <pefernan@redhat.com>
 */
@Portable
@Bindable
public class TextAreaFieldDefinition extends FieldDefinition implements HasRows, HasPlaceHolder {

    public static final String CODE = "TextArea";

    @FieldDef( label = "Placeholder", position = 1)
    protected String placeHolder = "";

    @FieldDef( label = "Visible Lines", position = 2)
    @Min( 1 )
    protected Integer rows = 4;

    public TextAreaFieldDefinition() {
        super( CODE );
    }

    @Override
    public Integer getRows() {
        return rows;
    }

    @Override
    public void setRows( Integer rows ) {
        this.rows = rows;
    }

    @Override
    public String getPlaceHolder() {
        return placeHolder;
    }

    @Override
    public void setPlaceHolder( String placeHolder ) {
        this.placeHolder = placeHolder;
    }

    @Override
    protected void doCopyFrom(FieldDefinition other) {
        if (other instanceof  TextAreaFieldDefinition ) {
            TextAreaFieldDefinition otherTextArea = (TextAreaFieldDefinition) other;
            this.setRows(otherTextArea.getRows());
            this.setPlaceHolder( otherTextArea .getPlaceHolder() );
        } else {
            if (other instanceof HasRows) {
                setRows(((HasRows) other).getRows());
            }
            if (other instanceof HasPlaceHolder) {
                setPlaceHolder(((HasPlaceHolder) other).getPlaceHolder());
            }
        }
    }
}
