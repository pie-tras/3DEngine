package com.draglantix.fbos;

public abstract class Attachment {

	private int bufferId;
	private boolean isDepthAttach = false;

	public int getBufferId() {
		return bufferId;
	}
	
	public abstract void init(int attachment, int width, int height, int samples);

	public abstract void delete();

	protected void setBufferId(int id) {
		this.bufferId = id;
	}

	protected void setAsDepthAttachment() {
		this.isDepthAttach = true;
	}

	protected boolean isDepthAttachment() {
		return isDepthAttach;
	}

}
