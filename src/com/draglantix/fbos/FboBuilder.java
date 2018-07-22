package com.draglantix.fbos;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.lwjgl.opengl.GL30;

public class FboBuilder {

	private final int width;
	private final int height;
	private final int samples;

	private Map<Integer, Attachment> colorAttachments = new HashMap<Integer, Attachment>();
	private Attachment depthAttachment;

	protected FboBuilder(int width, int height, int samples) {
		this.width = width;
		this.height = height;
		this.samples = samples;
	}

	public FboBuilder addcolorAttachment(int index, Attachment attachment) {
		colorAttachments.put(index, attachment);
		return this;
	}

	public FboBuilder addDepthAttachment(Attachment attachment) {
		this.depthAttachment = attachment;
		attachment.setAsDepthAttachment();
		return this;
	}

	public Fbo init() {
		int fboId = createFbo();
		createcolorAttachments();
		createDepthAttachment();
		return new Fbo(fboId, width, height, colorAttachments, depthAttachment);
	}

	private int createFbo() {
		int fboId = GL30.glGenFramebuffers();
		GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, fboId);
		return fboId;
	}

	private void createcolorAttachments() {
		for (Entry<Integer, Attachment> entry : colorAttachments.entrySet()) {
			Attachment attachment = entry.getValue();
			int attachmentId = GL30.GL_COLOR_ATTACHMENT0 + entry.getKey();
			attachment.init(attachmentId, width, height, samples);
		}
	}

	private void createDepthAttachment() {
		if (depthAttachment != null) {
			depthAttachment.init(GL30.GL_DEPTH_ATTACHMENT, width, height, samples);
		}
	}

}
