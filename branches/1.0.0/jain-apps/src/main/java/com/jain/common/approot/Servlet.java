/* 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.jain.common.approot;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import com.vaadin.server.VaadinServlet;
import com.vaadin.server.VaadinServletSession;
import com.vaadin.server.WrappedHttpServletRequest;

@WebServlet(urlPatterns = "/*")
public class Servlet extends VaadinServlet {
    private static final long serialVersionUID = 2298654353565L;

	@Inject
    private Instance <RootProvider> applicationProvider;

	@Override
	protected void onVaadinSessionStarted(WrappedHttpServletRequest request, VaadinServletSession session) throws ServletException {
		session.addUIProvider(applicationProvider.get());
		super.onVaadinSessionStarted(request, session);
	}
}