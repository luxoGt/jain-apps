/* 
 * Copyright 2012 Lokesh Jain.
 * 
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
package com.jain.addon.web.bean.factory.generator;

import java.util.Locale;

import com.jain.addon.component.upload.JImage;
import com.jain.addon.resource.I18NProvider;
import com.jain.addon.web.bean.JNIProperty;
import com.vaadin.ui.Field;

/**
 * <code>ImageFieldGenerator<code> used to generate Image field
 * @author Lokesh Jain
 * @since Nov 16, 2012
 * @version 1.0.3
 */
@SuppressWarnings("serial")
public class ImageFieldGenerator extends AbstractFieldGenerator {
	
	public ImageFieldGenerator(Locale locale, I18NProvider provider) {
		super(locale, provider);
	}

	public Field <?> createField(Class<?> type, JNIProperty property) {
		JImage field = new JImage();
		field.setInterruptionMessage(getCaption(property) + ".upload.interruption");
		field.setUploadButtonCaption(getCaption(property) + ".upload.button.caption");
		return field;
	}
}
