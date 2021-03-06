/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.deltaspike.proxy.impl.invocation;

import org.apache.deltaspike.core.api.provider.BeanProvider;
import org.apache.deltaspike.proxy.spi.DeltaSpikeProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import javax.enterprise.context.ApplicationScoped;

/**
 * {@link AbstractManualInvocationHandler} which delegates the method call to the defined {@link InvocationHandler}
 * in {@link DeltaSpikeProxy#getDelegateInvocationHandler()}.
 */
@ApplicationScoped
public class DelegateManualInvocationHandler extends AbstractManualInvocationHandler
{
    public static Object staticInvoke(Object proxy, Method method, Object[] parameters) throws Throwable
    {
        DelegateManualInvocationHandler handler = BeanProvider
              .getContextualReference(DelegateManualInvocationHandler.class);
        return handler.invoke(proxy, method, parameters);
    }
    
    @Override
    protected Object proceedOriginal(Object proxy, Method method, Object[] parameters) throws Throwable
    {
        InvocationHandler delegateInvocationHandler = ((DeltaSpikeProxy) proxy).getDelegateInvocationHandler();
        return delegateInvocationHandler.invoke(proxy, method, parameters);
    }
}
