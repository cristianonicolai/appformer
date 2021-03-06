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

package org.livespark.formmodeler.editor.client.editor.preview;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;
import org.jboss.errai.ui.client.local.spi.TranslationService;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import org.livespark.formmodeler.editor.client.resources.i18n.FormEditorConstants;
import org.livespark.formmodeler.renderer.client.DynamicFormRenderer;
import org.livespark.formmodeler.renderer.service.FormRenderingContext;
import org.uberfire.ext.widgets.common.client.common.popups.BaseModal;
import org.uberfire.ext.widgets.common.client.common.popups.footers.ModalFooterOKButton;

/**
 * @author Pere Fernandez <pefernan@redhat.com>
 */
@Templated
public class PreviewFormPresenterViewImpl extends Composite implements PreviewFormPresenter.PreviewFormPresenterView {

    @DataField
    private SimplePanel content = new SimplePanel();

    private DynamicFormRenderer formRenderer;

    private TranslationService translationService;

    private BaseModal modal;

    @Inject
    public PreviewFormPresenterViewImpl( DynamicFormRenderer formRenderer, TranslationService translationService ) {
        this.formRenderer = formRenderer;
        this.translationService = translationService;
    }

    @PostConstruct
    protected void doInit() {
        modal = new BaseModal();
        modal.setBody( this );
        modal.setTitle( translationService.getTranslation( FormEditorConstants.FormEditorViewImplPreview ) );
        modal.add( new ModalFooterOKButton( new Command() {
            @Override
            public void execute() {
                modal.hide();
            }
        } ) );
        content.add( formRenderer );
    }

    @Override
    public void preview( FormRenderingContext context ) {
        formRenderer.render( context );
        modal.show();
    }
}
