/*
 * Java port of Bullet (c) 2008 Martin Dvorak <jezek2@advel.cz>
 *
 * Bullet Continuous Collision Detection and Physics Library
 * Ragdoll Demo
 * Copyright (c) 2007 Starbreeze Studios
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
 * 
 * Originally Written by: Marten Svanfeldt
 * ReWritten by: Francisco Le�n
 */

package com.bulletphysics.demos.genericjoint;

import com.bulletphysics.collision.broadphase.BroadphaseInterface;
import com.bulletphysics.collision.broadphase.DbvtBroadphase;
import com.bulletphysics.collision.dispatch.CollisionDispatcher;
import com.bulletphysics.collision.dispatch.DefaultCollisionConfiguration;
import com.bulletphysics.collision.shapes.CollisionShape;
import com.bulletphysics.collision.shapes.simple.BoxShape;
import com.bulletphysics.dynamics.DiscreteDynamicsWorld;
import com.bulletphysics.dynamics.DynamicsWorld;
import com.bulletphysics.dynamics.constraintsolver.ConstraintSolver;
import com.bulletphysics.dynamics.constraintsolver.SequentialImpulseConstraintSolver;
import com.bulletphysics.linearmath.Transform;
import com.bulletphysics.ui.DemoApplication;
import com.bulletphysics.ui.JOGL;
import com.bulletphysics.util.ObjectArrayList;

import javax.vecmath.Vector3f;

/**
 *
 * @author jezek2
 */
public class GenericJointDemo extends DemoApplication {

	private final ObjectArrayList<RagDoll> ragdolls = new ObjectArrayList<>();

	private GenericJointDemo() {
		super();
	}

	public DynamicsWorld physics() {
		// Setup the basic world
		DefaultCollisionConfiguration collision_config = new DefaultCollisionConfiguration();

		CollisionDispatcher dispatcher = new CollisionDispatcher(collision_config);

		Vector3f worldAabbMin = new Vector3f(-10000, -10000, -10000);
		Vector3f worldAabbMax = new Vector3f(10000, 10000, 10000);
		//BroadphaseInterface overlappingPairCache = new AxisSweep3(worldAabbMin, worldAabbMax);
		//BroadphaseInterface overlappingPairCache = new SimpleBroadphase();
		BroadphaseInterface overlappingPairCache = new DbvtBroadphase();

		//#ifdef USE_ODE_QUICKSTEP
		//btConstraintSolver* constraintSolver = new OdeConstraintSolver();
		//#else
		ConstraintSolver constraintSolver = new SequentialImpulseConstraintSolver();
		//#endif

		DynamicsWorld world = new DiscreteDynamicsWorld(dispatcher, overlappingPairCache, constraintSolver, collision_config);

		world.setGravity(new Vector3f(0f, -30f, 0f));

		// Setup a big ground box
		{
			CollisionShape groundShape = new BoxShape(new Vector3f(200f, 10f, 200f));
			Transform groundTransform = new Transform();
			groundTransform.setIdentity();
			groundTransform.origin.set(0f, -15f, 0f);
			world.localCreateRigidBody(0f, groundTransform, groundShape);
		}

		spawnRagdoll(world);

		return world;
	}

	
	private void spawnRagdoll(DynamicsWorld world) {
		RagDoll ragDoll = new RagDoll(world, new Vector3f(0f, 0f, 10f), 5f);
		ragdolls.add(ragDoll);
	}
	
//	@Override
//	public void display(GLAutoDrawable arg) {
//		gl.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
//		poll();
//		// simple dynamics world doesn't handle fixed-time-stepping
//		float ms = getDeltaTimeMicroseconds();
//		float minFPS = 1000000f / 60f;
//		if (ms > minFPS) {
//			ms = minFPS;
//		}
//
//		if (world != null) {
//			world.stepSimulation(ms / 1000000.f);
//			// optional but useful: debug drawing
//			world.debugDrawWorld();
//		}
//
//		renderme();
//
//		//glFlush();
//		//glutSwapBuffers();
//	}



	@Override
	public void keyboardCallback(char key) {
		switch (key) {
			case 'e' -> spawnRagdoll(world);
			default -> super.keyboardCallback(key);
		}
	}

	public static void main(String... args) {
		GenericJointDemo demoApp = new GenericJointDemo();
		demoApp.setCameraDistance(10f);

		new JOGL(demoApp, 800, 600);
	}
	
}
