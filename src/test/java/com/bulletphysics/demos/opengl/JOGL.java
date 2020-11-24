/*
 * Java port of Bullet (c) 2008 Martin Dvorak <jezek2@advel.cz>
 *
 * Bullet Continuous Collision Detection and Physics Library
 * Copyright (c) 2003-2007 Erwin Coumans  http://continuousphysics.com/Bullet/
 *
 * This software is provided 'as-is', without any express or implied warranty.
 * In no event will the authors be held liable for any damages arising from
 * the use of this software.
 * 
 * Permission is granted to anyone to use this software for any purpose, 
 * including commercial applications, and to alter it and redistribute it
 * freely, subject to the following restrictions:
 * 
 * 1. The origin of this software must not be misrepresented; you must not
 *    claim that you wrote the original software. If you use this software
 *    in a product, an acknowledgment in the product documentation would be
 *    appreciated but is not required.
 * 2. Altered source versions must be plainly marked as such, and must not be
 *    misrepresented as being the original software.
 * 3. This notice may not be removed or altered from any source distribution.
 */

package com.bulletphysics.demos.opengl;

import com.jogamp.newt.event.WindowAdapter;
import com.jogamp.newt.event.WindowEvent;
import com.jogamp.newt.opengl.GLWindow;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.util.FPSAnimator;

/**
 *
 * @author jezek2
 */

public class JOGL  {
   private GLWindow window;
    public boolean quit = false;

    public void windowResized(WindowEvent e) { }
    public void windowMoved(WindowEvent e) { }
 

    private static final int FPS = 60; // animator's target frames per second

    private void run(DemoApplication demoApp, int type, int width, int height) {

        GLProfile glp = GLProfile.getDefault();
        GLCapabilities caps = new GLCapabilities(glp);
        window = GLWindow.create(caps);


        // Create a animator that drives canvas' display() at the specified FPS.
        final FPSAnimator animator = new FPSAnimator(window, FPS, true);

        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowDestroyNotify(WindowEvent arg0) {
                // Use a dedicate thread to run the stop() to ensure that the
                // animator stops before program exits.
                new Thread(() -> {
                    if (animator.isStarted())
                        animator.stop();    // stop the animator loop
                    System.exit(0);
                }).start();
            }
        });
        window.addMouseListener(demoApp.mouse);
        window.addMouseListener(demoApp.mouse);
        window.addKeyListener(demoApp.keyboard);
        window.addGLEventListener(demoApp);

        window.setSize(width,height);
        window.setVisible(true);
        animator.start();  // start the animator loop
        
        /*
        // GLProfile.setProfileGLAny();
        try {
           // GLCapabilities caps = new GLCapabilities(GLProfile.getDefault());
            // For emulation library, use 16 bpp
           // caps.setRedBits(5);
            //caps.setGreenBits(6);
            //caps.setBlueBits(5);
            //caps.setDepthBits(16);

          //  Window nWindow = null;
            


            window = GLWindow.create(caps);
            window.addWindowListener(this);
            window.addMouseListener(this);
            window.addMouseListener(demoApp);
            window.addKeyListener(demoApp);
            window.addGLEventListener(demoApp);
            // window.setEventHandlerMode(GLWindow.EVENT_HANDLER_GL_CURRENT); // default
            // window.setEventHandlerMode(GLWindow.EVENT_HANDLER_GL_NONE); // no current ..

            window.setUpdateFPSFrames(FPSCounter.DEFAULT_FRAMES_PER_INTERVAL, System.err);
            // Size OpenGL to Video Surface
            window.setSize(width, height);
            window.setFullscreen(true);
            window.setVisible(true);
            width = window.getWidth();
            height = window.getHeight();

            while (!quit && window.getTotalFPSDuration() < 200000) {
                window.display();
            }

            // Shut things down cooperatively
            window.destroy();
            System.out.print(title);
            System.out.println(" shut down cleanly.");
        } catch (Throwable t) {
            t.printStackTrace();
        }
        */
        
        
        
    }

    public static int USE_NEWT      = 0;
    public static int USE_AWT       = 1 << 0;

    public static void main(String[] args, int width, int height, DemoApplication demo) {
        //int width = 480;
        //int height = 800;
        int type = USE_NEWT ;
        for(int i=args.length-1; i>=0; i--) {
            if(args[i].equals("-awt")) {
                type |= USE_AWT; 
            }
            if(args[i].equals("-width") && i+1<args.length) {
                try {
                    width = Integer.parseInt(args[i+1]);
                } catch (NumberFormatException nfe) {}
            }
            if(args[i].equals("-height") && i+1<args.length) {
                try {
                    height = Integer.parseInt(args[i+1]);
                } catch (NumberFormatException nfe) {}
            }
        }
        new JOGL().run(demo, type, width, height);
       // System.exit(0);
    }

}
