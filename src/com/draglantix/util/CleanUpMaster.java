package com.draglantix.util;

import com.draglantix.states.PlayState;
import com.draglantix.window.Window;

public class CleanUpMaster {

	public static void cleanUp() {
		Window.cleanUp();
		PlayState.cleanUp();
	}
	
}
