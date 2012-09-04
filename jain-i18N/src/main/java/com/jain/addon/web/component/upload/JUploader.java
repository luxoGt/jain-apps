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
package com.jain.addon.web.component.upload;

import java.io.OutputStream;

import com.jain.addon.resource.I18NProvider;
import com.jain.addon.resource.I18NResourceProvider;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.StartedEvent;
import com.vaadin.ui.Upload.StartedListener;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;
import com.vaadin.ui.VerticalLayout;

/**
 * <code>JUploader<code> default Uploader
 * @author Lokesh Jain
 * @since Aug 28, 2012
 * @version 1.0.0
 */
@SuppressWarnings("serial")
public class JUploader extends VerticalLayout implements SucceededListener, StartedListener {
	private JFileReceiver receiver;
	private Upload upload;
	private JProgressIndicator pi;
	private long maxContentLength = 1000 * 1000 * 3;
	private String interruptionMessage;

	public JUploader() {
		setWidth("100%");
		setMargin(true, false, true, false);
		setStyleName("j-upload");

		upload = new Upload();
		upload.setImmediate(true);
		upload.setButtonCaption("Upload");
		addComponent(upload);

		pi = new JProgressIndicator();
		upload.addListener(pi);
		addComponent(pi);
		pi.setVisible(false);

		receiver = new JFileReceiver();
		upload.setReceiver(receiver);

		upload.addListener((SucceededListener) this);
		upload.addListener((StartedListener) this);
	}

	public OutputStream getStream() {
		return receiver.getStream();
	}

	public void setStream(OutputStream stream) {
		receiver.setStream(stream);
	}

	protected void interruptUpload() {
		upload.interruptUpload();
		I18NProvider provider = I18NResourceProvider.instance();
		Notification n = new Notification(provider.getTitle(getLocale(), interruptionMessage), Notification.TYPE_TRAY_NOTIFICATION);
		n.setPosition(Notification.POSITION_CENTERED);
		n.setDescription(provider.getMessage(getLocale(), interruptionMessage));
		n.show(getRoot().getPage());
	}

	public void uploadStarted(StartedEvent event) {
		pi.setVisible(true);
		upload.setVisible(false);

		long l = event.getContentLength();
		if(l > maxContentLength) {
			interruptUpload();
		}
	}

	public void uploadSucceeded(SucceededEvent event) {
		pi.setVisible(false);
		upload.setVisible(true);
	}

	/**
	 * @return String to be rendered into button that fires uploading
	 */
	public String getButtonCaption() {
		return upload.getButtonCaption();
	}

	/**
	 * In addition to the actual file chooser, upload components have button
	 * that starts actual upload progress. This method is used to set text in
	 * that button.
	 * @param buttonCaption
	 *            text for upload components button.
	 */
	public void setButtonCaption(String buttonCaption) {
		upload.setButtonCaption(buttonCaption);
	}

	public long getMaxContentLength() {
		return maxContentLength;
	}

	public void setMaxContentLength(long maxContentLength) {
		this.maxContentLength = maxContentLength;
	}

	public String getInterruptionMessage() {
		return interruptionMessage;
	}

	public void setInterruptionMessage(String interruptionMessage) {
		this.interruptionMessage = interruptionMessage;
	}
}