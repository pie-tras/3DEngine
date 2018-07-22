package com.draglantix.fbos;

import java.util.Map;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;

import com.draglantix.window.Window;

public class Fbo {

	private final int fboId;
	private final int width;
	private final int height;

	private final Map<Integer, Attachment> colorAttachments;
	private final Attachment depthAttachment;

	protected Fbo(int fboId, int width, int height, Map<Integer, Attachment> attachments, Attachment depthAttachment) {
		this.fboId = fboId;
		this.width = width;
		this.height = height;
		this.colorAttachments = attachments;
		this.depthAttachment = depthAttachment;
	}

	public void blitToScreen(int colorIndex) {
		GL30.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, 0);
		GL11.glDrawBuffer(GL11.GL_BACK);
		GL30.glBindFramebuffer(GL30.GL_READ_FRAMEBUFFER, fboId);
		GL11.glReadBuffer(GL30.GL_COLOR_ATTACHMENT0 + colorIndex);
		GL30.glBlitFramebuffer(0, 0, width, height, 0, 0, Window.getWidth(), Window.getHeight(),
				GL11.GL_COLOR_BUFFER_BIT, GL11.GL_NEAREST);
		GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, 0);
	}

	public void blitToFbo(int srccolorIndex, Fbo target, int targetcolorIndex) {
		GL30.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, target.fboId);
		GL11.glDrawBuffer(GL30.GL_COLOR_ATTACHMENT0 + targetcolorIndex);

		GL30.glBindFramebuffer(GL30.GL_READ_FRAMEBUFFER, fboId);
		GL11.glReadBuffer(GL30.GL_COLOR_ATTACHMENT0 + srccolorIndex);

		int bufferBit = depthAttachment != null && target.depthAttachment != null
				? GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT : GL11.GL_COLOR_BUFFER_BIT;
		GL30.glBlitFramebuffer(0, 0, width, height, 0, 0, target.width, target.height, bufferBit, GL11.GL_NEAREST);
		GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, 0);
	}

	public int getcolorBuffer(int colorIndex) {
		return colorAttachments.get(colorIndex).getBufferId();
	}

	public int getDepthBuffer() {
		return depthAttachment.getBufferId();
	}

	public boolean isComplete() {
		GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, fboId);
		boolean complete = GL30.glCheckFramebufferStatus(GL30.GL_FRAMEBUFFER) == GL30.GL_FRAMEBUFFER_COMPLETE;
		GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, 0);
		return complete;
	}
	
	public void bindForRender(int colorIndex) {
		// should add support for binding multiple color attachments
		GL11.glDrawBuffer(GL30.GL_COLOR_ATTACHMENT0 + colorIndex);
		GL30.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, fboId);
		GL11.glViewport(0, 0, width, height);
	}

	public void unbindAfterRender() {
		GL30.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, 0);
		GL11.glDrawBuffer(GL11.GL_BACK);
		GL11.glViewport(0, 0, Window.getWidth(), Window.getHeight());
	}

	public void delete() {
		for (Attachment attachment : colorAttachments.values()) {
			attachment.delete();
		}
		if (depthAttachment != null) {
			depthAttachment.delete();
		}
	}

	public static FboBuilder newFbo(int width, int height) {
		return new FboBuilder(width, height, 0);
	}

	public static FboMsBuilder newMultisampledFbo(int width, int height, int samples) {
		return new FboMsBuilder(new FboBuilder(width, height, samples));
	}

}
