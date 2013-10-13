/*
 * Copyright 2012 JBoss Inc
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

package org.uberfire.metadata.backend.lucene.setups;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.store.Directory;
import org.apache.lucene.store.NIOFSDirectory;

/**
 *
 */
public class NIOLuceneSetup extends DirectoryLuceneSetup {

    public NIOLuceneSetup() {
        super( getDirectory( defaultFile() ), freshIndex( defaultFile() ) );
    }

    public NIOLuceneSetup( final File file ) {
        super( getDirectory( file ), freshIndex( file ) );
    }

    private static Directory getDirectory( final File file ) {
        try {
            return new NIOFSDirectory( file );
        } catch ( IOException e ) {
            throw new RuntimeException( e );
        }
    }

}
