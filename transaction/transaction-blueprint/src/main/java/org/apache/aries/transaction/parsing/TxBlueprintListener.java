/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.aries.transaction.parsing;

import org.apache.aries.transaction.TxComponentMetaDataHelper;
import org.osgi.service.blueprint.container.BlueprintEvent;
import org.osgi.service.blueprint.container.BlueprintListener;

/**
 * A blueprint listener that tracks DESTROYED events to clean up any stored metadata for that blueprint.
 * (So that we don't leak memory).
 */
public class TxBlueprintListener implements BlueprintListener {
    
    private final TxElementHandler handler;
    private final TxComponentMetaDataHelper helper;
    
    public TxBlueprintListener(TxElementHandler handler, TxComponentMetaDataHelper helper) {
        this.handler = handler;
        this.helper = helper;
    }
    
    public void blueprintEvent(BlueprintEvent event) {
        if (event.getType() == BlueprintEvent.DESTROYED) {
            handler.unregister(event.getBundle());
        }
    }

}