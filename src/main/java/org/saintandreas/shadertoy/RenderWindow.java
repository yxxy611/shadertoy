package org.saintandreas.shadertoy;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Controllers;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GLContext;
import org.lwjgl.opengl.PixelFormat;

public class RenderWindow {
  private GLContext glContext = new GLContext();
  private ContextAttribs contextAttributes = new ContextAttribs(3, 2)
      .withProfileCore(true);
  private PixelFormat pixelFormat = new PixelFormat();

  public RenderWindow() {}
  
  public void create(int width, int height) {
    try {
      Display.setDisplayMode(new DisplayMode(width, height));
      Display.setVSyncEnabled(true);
      Display.create(pixelFormat, contextAttributes);
      GLContext.useContext(glContext, false);
      Mouse.create();
      Keyboard.create();
      Controllers.create();
    } catch (LWJGLException ex) {
      throw new RuntimeException(ex);
    }
    onCreate();
    onResize(width, height);
  }

  public void onFrame() {
    if (Display.wasResized()) {
      onResize(Display.getWidth(), Display.getHeight());
    }
    drawFrame();
    finishFrame();
  }

  public void destroy() {
    onDestroy();
    Display.destroy();
  }

  protected void finishFrame() {
    Display.update();
  }

  protected void drawFrame() {
  }

  protected void onDestroy() {
  }

  protected void onCreate() {
  }

  protected void onResize(int width, int height) {
  }
}
