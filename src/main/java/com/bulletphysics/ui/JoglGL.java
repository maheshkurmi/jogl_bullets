/*
 * Java port of Bullet (c) 2008 Martin Dvorak <jezek2@advel.cz>
 *
 * Bullet Continuous Collision Detection and Physics Library
 * Copyright (c) 2003-2008 Erwin Coumans  http://www.bulletphysics.com/
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

package com.bulletphysics.ui;

import com.jogamp.common.nio.Buffers;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;

import java.nio.FloatBuffer;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author jezek2
 */
public class JoglGL implements IGL {

	private static final FloatBuffer floatBuf = Buffers.newDirectFloatBuffer(16);
	private static final GLU glu=new GLU();
	private FontRender.GLFont font;
	public GL2 gl;
	
	public void init(GL2 gl) {
		this.gl=gl;
		FontRender.init(gl,glu);
			font = FontRender.createFont(null, 16, false, true);
//		try {
			//font = new FontRender.GLFont(IGL.class.getResourceAsStream("/DejaVu_Sans_11.fnt"));
//		}
//		catch (IOException e) {
//			e.printStackTrace();
//		}
	}
	
	public void glLight(int light, int pname, float[] params) {
		gl.glLightfv(light, pname, FloatBuffer.wrap(params));
	}

	public void glEnable(int cap) {
		gl.glEnable(cap);
	}

	public void glDisable(int cap) {
		gl.glDisable(cap);
	}

	public void glShadeModel(int mode) {
		gl.glShadeModel(mode);
	}

	public void glDepthFunc(int func) {
		gl.glDepthFunc(func);
	}

	public void glClearColor(float red, float green, float blue, float alpha) {
		gl.glClearColor(red, green, blue, alpha);
	}

	public void glMatrixMode(int mode) {
		gl.glMatrixMode(mode);
	}

	public void glLoadIdentity() {
		gl.glLoadIdentity();
	}

	public void glFrustum(double left, double right, double bottom, double top, double zNear, double zFar) {
		gl.glFrustum(left, right, bottom, top, zNear, zFar);
	}

	public void gluLookAt(float eyex, float eyey, float eyez, float centerx, float centery, float centerz, float upx, float upy, float upz) {
		glu.gluLookAt(eyex, eyey, eyez, centerx, centery, centerz, upx, upy, upz);
	}

	public void glViewport(int x, int y, int width, int height) {
		gl.glViewport(x, y, width, height);
	}

	public void glPushMatrix() {
		gl.glPushMatrix();
	}

	public void glPopMatrix() {
		gl.glPopMatrix();
	}

	public void gluOrtho2D(float left, float right, float bottom, float top) {
		glu.gluOrtho2D(left, right, bottom, top);
	}

	public void glScalef(float x, float y, float z) {
		gl.glScalef(x, y, z);
	}

	public void glTranslatef(float x, float y, float z) {
		gl.glTranslatef(x, y, z);
	}

	public void glColor3f(float red, float green, float blue) {
		gl.glColor3f(red, green, blue);
	}

	public void glClear(int mask) {
		gl.glClear(mask);
	}

	public void glBegin(int mode) {
		gl.glBegin(mode);
	}

	public void glEnd() {
		gl.glEnd();
	}

	public void glVertex3f(float x, float y, float z) {
		gl.glVertex3f(x, y, z);
	}

	public void glLineWidth(float width) {
		gl.glLineWidth(width);
	}

	public void glPointSize(float size) {
		gl.glPointSize(size);
	}

	public void glNormal3f(float nx, float ny, float nz) {
		gl.glNormal3f(nx, ny, nz);
	}

	public void glMultMatrix(float[] m) {
		floatBuf.clear();
		floatBuf.put(m).flip();
		gl.glMultMatrixf(floatBuf);
	}
	
	////////////////////////////////////////////////////////////////////////////

	public void drawCube(float extent) {
		extent = extent * 0.5f;
		
	    gl.glBegin(GL2.GL_QUADS);
        gl.glNormal3f( 1f, 0f, 0f); gl.glVertex3f(+extent,-extent,+extent); gl.glVertex3f(+extent,-extent,-extent); gl.glVertex3f(+extent,+extent,-extent); gl.glVertex3f(+extent,+extent,+extent);
        gl.glNormal3f( 0f, 1f, 0f); gl.glVertex3f(+extent,+extent,+extent); gl.glVertex3f(+extent,+extent,-extent); gl.glVertex3f(-extent,+extent,-extent); gl.glVertex3f(-extent,+extent,+extent);
        gl.glNormal3f( 0f, 0f, 1f); gl.glVertex3f(+extent,+extent,+extent); gl.glVertex3f(-extent,+extent,+extent); gl.glVertex3f(-extent,-extent,+extent); gl.glVertex3f(+extent,-extent,+extent);
        gl.glNormal3f(-1f, 0f, 0f); gl.glVertex3f(-extent,-extent,+extent); gl.glVertex3f(-extent,+extent,+extent); gl.glVertex3f(-extent,+extent,-extent); gl.glVertex3f(-extent,-extent,-extent);
        gl.glNormal3f( 0f,-1f, 0f); gl.glVertex3f(-extent,-extent,+extent); gl.glVertex3f(-extent,-extent,-extent); gl.glVertex3f(+extent,-extent,-extent); gl.glVertex3f(+extent,-extent,+extent);
        gl.glNormal3f( 0f, 0f,-1f); gl.glVertex3f(-extent,-extent,-extent); gl.glVertex3f(-extent,+extent,-extent); gl.glVertex3f(+extent,+extent,-extent); gl.glVertex3f(+extent,-extent,-extent);
		gl.glEnd();
		
	}
	
	////////////////////////////////////////////////////////////////////////////
	
	
	private static GLUquadric cylinder = null;
	private static  GLUquadric disk =null;
	private static  GLUquadric sphere = null;
	

	
	private static final Map<Float,Integer> sphereDisplayLists = new HashMap<>();

	
	public void drawSphere(float radius, int slices, int stacks) {
		if(sphere==null) {
			sphere = glu.gluNewQuadric();
			sphere.setImmMode(true);
        }

		Integer glList = sphereDisplayLists.get(radius);
		if (glList == null) {
			glList = gl.glGenLists(1);
			gl.glNewList(glList, GL2.GL_COMPILE);
			//sphere.draw(radius, 8, 8);
			glu.gluSphere(sphere, radius, 8, 8);
		      
			gl.glEndList();
			sphereDisplayLists.put(radius, glList);
		}
		
		gl.glCallList(glList);
	}
	
	////////////////////////////////////////////////////////////////////////////

	/** TODO use Pair<Float> */
	@Deprecated private static class CylinderKey {
		float radius;
		float halfHeight;

		public CylinderKey() {
		}

		public CylinderKey(CylinderKey key) {
			radius = key.radius;
			halfHeight = key.halfHeight;
		}

		void set(float radius, float halfHeight) {
			this.radius = radius;
			this.halfHeight = halfHeight;
		}

		@Override
		public boolean equals(Object obj) {
			if (!(obj instanceof CylinderKey)) return false;
			CylinderKey other = (CylinderKey) obj;
			if (radius != other.radius) return false;
			return halfHeight == other.halfHeight;
		}

		@Override
		public int hashCode() {
			int hash = 7;
			hash = 23 * hash + Float.floatToIntBits(radius);
			hash = 23 * hash + Float.floatToIntBits(halfHeight);
			return hash;
		}
	}
	
	private static final Map<CylinderKey,Integer> cylinderDisplayLists = new HashMap<>();
	private static final CylinderKey cylinderKey = new CylinderKey();
	
	public void drawCylinder(float radius, float halfHeight, int upAxis) {
		   if(cylinder==null) {
	            cylinder = glu.gluNewQuadric();
	            cylinder.setImmMode(true);
	        }
		   
		   if(disk==null) {
	            disk = glu.gluNewQuadric();
	            disk.setImmMode(true);
	        }
		glPushMatrix();
		switch (upAxis) {
			case 0 -> {
				gl.glRotatef(-90f, 0.0f, 1.0f, 0.0f);
				glTranslatef(0.0f, 0.0f, -halfHeight);
			}
			case 1 -> {
				gl.glRotatef(-90.0f, 1.0f, 0.0f, 0.0f);
				glTranslatef(0.0f, 0.0f, -halfHeight);
			}
			case 2 -> gl.glTranslatef(0.0f, 0.0f, -halfHeight);
			default -> {
				assert (false);
			}
		}

		// The gluCylinder subroutine draws a cylinder that is oriented along the z axis. 
		// The base of the cylinder is placed at z = 0; the top of the cylinder is placed at z=height. 
		// Like a sphere, the cylinder is subdivided around the z axis into slices and along the z axis into stacks.

		cylinderKey.set(radius, halfHeight);
		Integer glList = cylinderDisplayLists.get(cylinderKey);
		if (glList == null) {
			glList = gl.glGenLists(1);
			gl.glNewList(glList, GL2.GL_COMPILE);
			glu.gluQuadricDrawStyle(disk, GLU.GLU_FILL);
			glu.gluQuadricNormals(disk, GLU.GLU_SMOOTH);
			//disk.draw(0, radius, 15, 10);
			glu.gluDisk(disk,0, radius, 15, 10);
			
			glu.gluQuadricDrawStyle(cylinder, GLU.GLU_FILL);
			glu.gluQuadricNormals(cylinder, GLU.GLU_SMOOTH);
			
			glu.gluCylinder(cylinder, radius, radius, 2f * halfHeight, 15, 10);

			gl.glTranslatef(0f, 0f, 2f * halfHeight);
			gl.glRotatef(-180f, 0f, 1f, 0f);
			glu.gluDisk(disk,0, radius, 15, 10);
			
			
			gl.glEndList();
			cylinderDisplayLists.put(new CylinderKey(cylinderKey), glList);
		}
		
		gl.glCallList(glList);

		gl.glPopMatrix();
	}
	
	////////////////////////////////////////////////////////////////////////////

	public void drawString(CharSequence s, int x, int y, float red, float green, float blue) {
		if (font != null) {
			FontRender.drawString(font, s, x, y, red, green, blue);
		}
	}

}
