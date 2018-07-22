package com.draglantix.fbos;

public class FboMsBuilder {

	private final FboBuilder fboBuilder;

	protected FboMsBuilder(FboBuilder fboBuilder) {
		this.fboBuilder = fboBuilder;
	}

	public FboMsBuilder addcolorAttachment(int index, RenderBufferAttachment attachment) {
		fboBuilder.addcolorAttachment(index, attachment);
		return this;
	}

	public FboMsBuilder addDepthAttachment(RenderBufferAttachment attachment) {
		fboBuilder.addDepthAttachment(attachment);
		return this;
	}

	public Fbo init() {
		return fboBuilder.init();
	}

}
