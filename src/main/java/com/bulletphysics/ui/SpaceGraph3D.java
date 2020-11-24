package com.bulletphysics.ui;

import com.bulletphysics.BulletGlobals;
import com.bulletphysics.BulletStats;
import com.bulletphysics.collision.dispatch.CollisionObject;
import com.bulletphysics.collision.dispatch.CollisionWorld;
import com.bulletphysics.collision.dispatch.RayResultCallback;
import com.bulletphysics.collision.shapes.CollisionShape;
import com.bulletphysics.dynamics.DynamicsWorld;
import com.bulletphysics.dynamics.RigidBody;
import com.bulletphysics.dynamics.constraintsolver.Point2PointConstraint;
import com.bulletphysics.dynamics.constraintsolver.TypedConstraint;
import com.bulletphysics.input.InputKey;
import com.bulletphysics.input.Keyboard;
import com.bulletphysics.input.Mouse;
import com.bulletphysics.input.MouseButton;
import com.bulletphysics.linearmath.*;
import com.bulletphysics.util.ObjectArrayList;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;

import javax.vecmath.Color3f;
import javax.vecmath.Matrix3f;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

import static com.bulletphysics.ui.IGL.*;

public abstract class SpaceGraph3D implements GLEventListener {
    private static final float STEPSIZE = 5;
    private static RigidBody pickedBody = null; // for deactivation state
    private static final float mousePickClamping = 3f;
    protected final Vector3f cameraPosition = new Vector3f(0f, 0f, 0f);
    protected final Vector3f cameraTargetPosition = new Vector3f(0f, 0f, 0f); // look at
    protected final Vector3f cameraUp = new Vector3f(0f, 1f, 0f);
    final Mouse mouse;
    final Keyboard keyboard;
    private final Clock clock = new Clock();
    public DynamicsWorld world;
    private TypedConstraint pickConstraint = null;
    private float cameraDistance = 15f;
    int debugMode = 0;
    private float ele = 20f;
    private float azi = 0f;
    @Deprecated
    protected int forwardAxis = 2;
    private int glutScreenWidth = 0;
    private int glutScreenHeight = 0;
    boolean stepping = true;
    protected boolean idle = false;
    private final Transform m = new Transform();
    private final Vector3f wireColor = new Vector3f();

    // keep the collision shapes, for deletion/cleanup
    protected final ObjectArrayList<CollisionShape> collisionShapes = new ObjectArrayList<>();

    protected final float fps = 60f;
    @Deprecated public transient IGL gl = null;

    public SpaceGraph3D() {
        mouse = new Mouse();
        keyboard = new Keyboard();
    }

    public void start() {
        synchronized (this) {
            world = physics();
            reset();
        }
    }

    public static void stop() {
        throw new RuntimeException("TODO");
    }

    protected void reset() {
        //#ifdef SHOW_NUM_DEEP_PENETRATIONS
        BulletStats.gNumDeepPenetrationChecks = 0;
        BulletStats.gNumGjkChecks = 0;
        //#endif //SHOW_NUM_DEEP_PENETRATIONS

        int numObjects = 0;
        if (world != null) {
            world.stepSimulation(1/fps, 0);
            numObjects = world.getNumCollisionObjects();
        }

        for (int i = 0; i < numObjects; i++) {
            final CollisionObject o = world.getCollisionObjectArray().get(i);
            final RigidBody b = RigidBody.upcast(o);
            if (b != null) {
                if (b.getMotionState() != null) {
                    DefaultMotionState myMotionState = (DefaultMotionState) b.getMotionState();
                    myMotionState.graphicsWorldTrans.set(myMotionState.startWorldTrans);
                    o.setWorldTransform(myMotionState.graphicsWorldTrans);
                    o.setInterpolationWorldTransform(myMotionState.startWorldTrans);
                    o.activate();
                }
                // removed cached contact points
                world.getBroadphase().getOverlappingPairCache().cleanProxyFromPairs(o.getBroadphaseHandle(), getWorld().getDispatcher());

                if (!b.isStaticObject()) {
                    b.setLinearVelocity(new Vector3f());
                    b.setAngularVelocity(new Vector3f());
                }
            }

			/*
			//quickly search some issue at a certain simulation frame, pressing space to reset
			int fixed=18;
			for (int i=0;i<fixed;i++)
			{
			getDynamicsWorld()->stepSimulation(1./60.f,1);
			}
			*/
        }
    }

    protected abstract DynamicsWorld physics();

    @Override
    public void init(GLAutoDrawable g) {
        if (((JoglGL)gl).gl == null) {
            ((JoglGL)gl).init(g.getGL().getGL2()); //HACK
        }

        float[] light_ambient = new float[]{0.2f, 0.2f, 0.2f, 1.0f};
        float[] light_diffuse = new float[]{1.0f, 1.0f, 1.0f, 1.0f};
        float[] light_specular = new float[]{1.0f, 1.0f, 1.0f, 1.0f};
        /* light_position is NOT default value */
        float[] light_position0 = new float[]{1.0f, 10.0f, 1.0f, 0.0f};
        float[] light_position1 = new float[]{-1.0f, -10.0f, -1.0f, 0.0f};

        this.gl.glLight(GL_LIGHT0, GL_AMBIENT, light_ambient);
        this.gl.glLight(GL_LIGHT0, GL_DIFFUSE, light_diffuse);
        this.gl.glLight(GL_LIGHT0, GL_SPECULAR, light_specular);
        this.gl.glLight(GL_LIGHT0, GL_POSITION, light_position0);

        this.gl.glLight(GL_LIGHT1, GL_AMBIENT, light_ambient);
        this.gl.glLight(GL_LIGHT1, GL_DIFFUSE, light_diffuse);
        this.gl.glLight(GL_LIGHT1, GL_SPECULAR, light_specular);
        this.gl.glLight(GL_LIGHT1, GL_POSITION, light_position1);

        GL2 gl = g.getGL().getGL2();
        gl.glEnable(GL_LIGHTING);
        gl.glEnable(GL_LIGHT0);
        gl.glEnable(GL_LIGHT1);

        gl.glShadeModel(GL_SMOOTH);
        gl.glEnable(GL_DEPTH_TEST);
        gl.glDepthFunc(GL_LESS);

        gl.glClearColor(0.7f, 0.7f, 0.7f, 0f);

        //glEnable(GL_CULL_FACE);
        //glCullFace(GL_BACK);
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {

        glutScreenWidth = width;
        glutScreenHeight = height;

        gl.glViewport(x, y, width, height);
        updateCamera();
    }

    private void update() {
        poll();
        if (!isIdle()) {
            // simple dynamics world doesn't handle fixed-time-stepping
            float ms = clock.getTimeMicroseconds();
            clock.reset();
            float minFPS = 1000000f / fps;
            if (ms > minFPS) {
                ms = minFPS;
            }
            if (world != null) {
                world.stepSimulation(ms / 1000000.f);
            }
        }

    }

    public void display(GLAutoDrawable drawable) {

        gl.glClear(IGL.GL_COLOR_BUFFER_BIT | IGL.GL_DEPTH_BUFFER_BIT);

        update();


        renderVolume();

        setOrthographicProjection();
        renderHUD();
    }

    void renderHUD() {

    }

    private void renderVolume() {

        updateCamera();


        // optional but useful: debug drawing
        world.debugDrawWorld();

        gl.glEnable(GL_LIGHTING);
        int numObjects = world.getNumCollisionObjects();
        wireColor.set(1f, 0f, 0f);
        for (int i = 0; i < numObjects; i++) {
            CollisionObject colObj = world.getCollisionObjectArray().get(i);
            RigidBody body = RigidBody.upcast(colObj);

            if (body != null && body.getMotionState() != null) {
                DefaultMotionState myMotionState = (DefaultMotionState) body.getMotionState();
                m.set(myMotionState.graphicsWorldTrans);
            }
            else {
                colObj.getWorldTransform(m);
            }

            wireColor.set(1f, 1f, 0.5f); // wants deactivation
            if ((i & 1) != 0) {
                wireColor.set(0f, 0f, 1f);
            }

            // color differently for active, sleeping, wantsdeactivation states
            if (colObj.getActivationState() == 1) // active
            {
                if ((i & 1) != 0) {
                    //wireColor.add(new Vector3f(1f, 0f, 0f));
                    wireColor.x += 1f;
                }
                else {
                    //wireColor.add(new Vector3f(0.5f, 0f, 0f));
                    wireColor.x += 0.5f;
                }
            }
            if (colObj.getActivationState() == 2) // ISLAND_SLEEPING
            {
                if ((i & 1) != 0) {
                    //wireColor.add(new Vector3f(0f, 1f, 0f));
                    wireColor.y += 1f;
                }
                else {
                    //wireColor.add(new Vector3f(0f, 0.5f, 0f));
                    wireColor.y += 0.5f;
                }
            }

            GLShapeDrawer.drawOpenGL(gl, m, colObj.getCollisionShape(), wireColor, getDebugMode());
        }

        gl.glDisable(GL_LIGHTING);
    }


    public void setCameraDistance(float dist) {
        cameraDistance = dist;
    }

    public float getCameraDistance() {
        return cameraDistance;
    }

    void toggleIdle() {
        idle = !idle;
    }

    public void updateCamera() {
        gl.glMatrixMode(GL_PROJECTION);
        gl.glLoadIdentity();
        float rele = ele * 0.01745329251994329547f; // rads per deg
        float razi = azi * 0.01745329251994329547f; // rads per deg

        Quat4f rot = new Quat4f();
        QuaternionUtil.setRotation(rot, cameraUp, razi);

        Vector3f eyePos = new Vector3f();
        eyePos.set(0f, 0f, 0f);
        VectorUtil.coord(eyePos, forwardAxis, -cameraDistance);

        Vector3f forward = new Vector3f();
        forward.set(eyePos.x, eyePos.y, eyePos.z);
        if (forward.lengthSquared() < BulletGlobals.FLT_EPSILON) {
            forward.set(1f, 0f, 0f);
        }
        Vector3f right = new Vector3f();
        right.cross(cameraUp, forward);
        Quat4f roll = new Quat4f();
        QuaternionUtil.setRotation(roll, right, -rele);

        Matrix3f tmpMat1 = new Matrix3f();
        Matrix3f tmpMat2 = new Matrix3f();
        tmpMat1.set(rot);
        tmpMat2.set(roll);
        tmpMat1.mul(tmpMat2);
        tmpMat1.transform(eyePos);

        cameraPosition.set(eyePos);

        if (glutScreenWidth > glutScreenHeight) {
            float aspect = glutScreenWidth / (float) glutScreenHeight;
            gl.glFrustum(-aspect, aspect, -1.0, 1.0, 1.0, 10000.0);
        } else {
            float aspect = glutScreenHeight / (float) glutScreenWidth;
            gl.glFrustum(-1.0, 1.0, -aspect, aspect, 1.0, 10000.0);
        }

        gl.glMatrixMode(GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.gluLookAt(cameraPosition.x, cameraPosition.y, cameraPosition.z,
                cameraTargetPosition.x, cameraTargetPosition.y, cameraTargetPosition.z,
                cameraUp.x, cameraUp.y, cameraUp.z);
    }

    void stepLeft() {
        azi -= STEPSIZE;
        if (azi < 0) {
            azi += 360;
        }
        //updateCamera();
    }

    void stepRight() {
        azi += STEPSIZE;
        if (azi >= 360) {
            azi -= 360;
        }
        //updateCamera();
    }

    void stepFront() {
        ele += STEPSIZE;
        if (ele >= 360) {
            ele -= 360;
        }
        //updateCamera();
    }

    void stepBack() {
        ele -= STEPSIZE;
        if (ele < 0) {
            ele += 360;
        }
        //updateCamera();
    }

    void zoomIn() {
        cameraDistance -= 0.4f;
        //updateCamera();
        if (cameraDistance < 0.1f) {
            cameraDistance = 0.1f;
        }
    }

    void zoomOut() {
        cameraDistance += 0.4f;
        //updateCamera();
    }

    protected void poll() {
        //mouse.clear();
        int x = mouse.getLocation().x;
        int y = mouse.getLocation().y;

        for (MouseButton btn : mouse.getActiveButtons().values()) {
            if (btn.getAWTEventId() == 0) continue;
            if (btn.getAWTEventId() == java.awt.event.MouseEvent.MOUSE_PRESSED) {
                mouseFunc(btn.getCode() - 1, 1, x, y);
            } else if (btn.getAWTEventId() == java.awt.event.MouseEvent.MOUSE_RELEASED) {
                mouseFunc(btn.getCode() - 1, 0, x, y);
            } else if (btn.getAWTEventId() == java.awt.event.MouseEvent.MOUSE_DRAGGED) {
                mouseMotionFunc(x, y);
            }
        }

        mouse.clear();

        for (InputKey key : keyboard.getActiveKeys().values()) {
            if (key.isConsumed()) continue;
            if (key.isPressed()) {
                if (key.isActionKey()) {
                    specialKeyboard(key.getKeyCode());
                } else {
                    keyboardCallback(key.getKeyChar());
                }
            } else {
                if (key.isActionKey()) {
                    specialKeyboardUp(key.getKeyCode());
                } else {
                    keyboardCallbackUp(key.getKeyChar());
                }
            }
        }

        mouse.clear();
        keyboard.reset();
    }

    void keyboardCallbackUp(char key) {

    }

    void keyboardCallback(char key) {

    }

    protected int getDebugMode() {
        return debugMode;
    }

    public void setDebugMode(int mode) {
        debugMode = mode;
        if (getWorld() != null && getWorld().getDebugDrawer() != null) {
            getWorld().getDebugDrawer().setDebugMode(mode);
        }
    }

    void specialKeyboardUp(int key) {

    }

    void specialKeyboard(int key) {

    }

    private Vector3f getRayTo(int x, int y) {
        float top = 1f;
        float bottom = -1f;
        float nearPlane = 1f;
        float tanFov = (top - bottom) * 0.5f / nearPlane;
        float fov = 2f * (float) Math.atan(tanFov);

        Vector3f rayFrom = new Vector3f(getCameraPosition());
        Vector3f rayForward = new Vector3f();
        rayForward.sub(getCameraTargetPosition(), getCameraPosition());
        rayForward.normalize();
        float farPlane = 10000f;
        rayForward.scale(farPlane);

        Vector3f rightOffset = new Vector3f();
        Vector3f vertical = new Vector3f(cameraUp);

        Vector3f hor = new Vector3f();
        // TODO: check: hor = rayForward.cross(vertical);
        hor.cross(rayForward, vertical);
        hor.normalize();
        // TODO: check: vertical = hor.cross(rayForward);
        vertical.cross(hor, rayForward);
        vertical.normalize();

        float tanfov = (float) Math.tan(0.5f * fov);

        float aspect = glutScreenHeight / (float) glutScreenWidth;

        hor.scale(2f * farPlane * tanfov);
        vertical.scale(2f * farPlane * tanfov);

        if (aspect < 1f) {
            hor.scale(1f / aspect);
        } else {
            vertical.scale(aspect);
        }

        Vector3f rayToCenter = new Vector3f();
        rayToCenter.add(rayFrom, rayForward);
        Vector3f dHor = new Vector3f(hor);
        dHor.scale(1f / glutScreenWidth);
        Vector3f dVert = new Vector3f(vertical);
        dVert.scale(1.f / glutScreenHeight);

        Vector3f tmp1 = new Vector3f();
        Vector3f tmp2 = new Vector3f();
        tmp1.scale(0.5f, hor);
        tmp2.scale(0.5f, vertical);

        Vector3f rayTo = new Vector3f();
        rayTo.sub(rayToCenter, tmp1);
        rayTo.add(tmp2);

        tmp1.scale(x, dHor);
        tmp2.scale(y, dVert);

        rayTo.add(tmp1);
        rayTo.sub(tmp2);
        return rayTo;
    }

    @Override
    public void dispose(GLAutoDrawable arg0) {
        // TODO Auto-generated method stub

    }

    private void mouseFunc(int button, int state, int x, int y) {
        //printf("button %i, state %i, x=%i,y=%i\n",button,state,x,y);
        //button 0, state 0 means left mouse down

        Vector3f rayTo = new Vector3f(getRayTo(x, y));


        switch (button) {
            case 2 -> {
//                if (state == 0) {
//                    shootBox(rayTo);
//                }
//                break;
            }
            case 1 -> {
                if (state == 0) {
                    // apply an impulse
                    if (world != null) {
                        CollisionWorld.ClosestRayResultCallback rayCallback = new CollisionWorld.ClosestRayResultCallback(cameraPosition, rayTo);
                        world.rayTest(cameraPosition, rayTo, rayCallback, RayResultCallback.RAY_EPSILON);
                        if (rayCallback.hasHit()) {
                            RigidBody body = RigidBody.upcast(rayCallback.collisionObject);
                            if (body != null) {
                                body.setActivationState(CollisionObject.ACTIVE_TAG);
                                Vector3f impulse = new Vector3f(rayTo);
                                impulse.normalize();
                                float impulseStrength = 10f;
                                impulse.scale(impulseStrength);
                                Vector3f relPos = new Vector3f();
                                relPos.sub(rayCallback.hitPointWorld, body.getCenterOfMassPosition(new Vector3f()));
                                body.applyImpulse(impulse, relPos);
                            }
                        }
                    }
                } else {
                }
            }
            case 0 -> {
                if (state == 1) {
                    // add a point to point constraint for picking
                    if (world != null) {
                        CollisionWorld.ClosestRayResultCallback rayCallback = new CollisionWorld.ClosestRayResultCallback(cameraPosition, rayTo);
                        world.rayTest(cameraPosition, rayTo, rayCallback, RayResultCallback.RAY_EPSILON);
                        if (rayCallback.hasHit()) {
                            RigidBody body = RigidBody.upcast(rayCallback.collisionObject);
                            if (body != null) {
                                // other exclusions?
                                if (!(body.isStaticObject() || body.isKinematicObject())) {
                                    pickedBody = body;
                                    pickedBody.setActivationState(CollisionObject.DISABLE_DEACTIVATION);

                                    Vector3f pickPos = new Vector3f(rayCallback.hitPointWorld);

                                    Transform tmpTrans = body.getCenterOfMassTransform(new Transform());
                                    tmpTrans.inverse();
                                    Vector3f localPivot = new Vector3f(pickPos);
                                    tmpTrans.transform(localPivot);

                                    Point2PointConstraint p2p = new Point2PointConstraint(body, localPivot);
                                    p2p.setting.impulseClamp = mousePickClamping;

                                    world.addConstraint(p2p);
                                    pickConstraint = p2p;
                                    // save mouse position for dragging
                                    BulletStats.gOldPickingPos.set(rayTo);
                                    Vector3f eyePos = new Vector3f(cameraPosition);
                                    Vector3f tmp = new Vector3f();
                                    tmp.sub(pickPos, eyePos);
                                    BulletStats.gOldPickingDist = tmp.length();
                                    // very weak constraint for picking
                                    p2p.setting.tau = 0.1f;
                                }
                            }
                        }
                    }

                } else {

                    if (pickConstraint != null && world != null) {
                        world.removeConstraint(pickConstraint);
                        // delete m_pickConstraint;
                        //printf("removed constraint %i",gPickingConstraintId);
                        pickConstraint = null;
                        pickedBody.forceActivationState(CollisionObject.ACTIVE_TAG);
                        pickedBody.setDeactivationTime(0f);
                        pickedBody = null;
                    }
                }
            }
            default -> {
            }
        }
    }

    private void mouseMotionFunc(int x, int y) {
        if (pickConstraint != null) {
            // move the constraint pivot
            Point2PointConstraint p2p = (Point2PointConstraint) pickConstraint;
            if (p2p != null) {
                // keep it at the same picking distance

                Vector3f newRayTo = new Vector3f(getRayTo(x, y));
                Vector3f eyePos = new Vector3f(cameraPosition);
                Vector3f dir = new Vector3f();
                dir.sub(newRayTo, eyePos);
                dir.normalize();
                dir.scale(BulletStats.gOldPickingDist);

                Vector3f newPos = new Vector3f();
                newPos.add(eyePos, dir);
                p2p.setPivotB(newPos);
            }
        }
    }


    protected void resetPerspectiveProjection() {
        gl.glMatrixMode(GL_PROJECTION);
        gl.glPopMatrix();
        gl.glMatrixMode(GL_MODELVIEW);
        updateCamera();
    }



    protected DynamicsWorld getWorld() {
        return world;
    }

    public void setCameraUp(Vector3f camUp) {
        cameraUp.set(camUp);
    }

    public void setCameraForwardAxis(int axis) {
        forwardAxis = axis;
    }

    protected Vector3f getCameraPosition() {
        return cameraPosition;
    }

    protected Vector3f getCameraTargetPosition() {
        return cameraTargetPosition;
    }

    protected float getDeltaTimeMicroseconds() {
        //#ifdef USE_BT_CLOCK
        float dt = clock.getTimeMicroseconds();
        clock.reset();
        return dt;
        //#else
        //return btScalar(16666.);
        //#endif
    }

    private boolean isIdle() {
        return idle;
    }

    public void setIdle(boolean idle) {
        this.idle = idle;
    }

    protected void drawString(CharSequence s, int x, int y, Color3f color) {
        gl.drawString(s, x, y, color.x, color.y, color.z);
    }

    // See http://www.lighthouse3d.com/opengl/glut/index.php?bmpfontortho
    protected void setOrthographicProjection() {
        // switch to projection mode
        gl.glMatrixMode(GL_PROJECTION);

        // save previous matrix which contains the
        //settings for the perspective projection
        gl.glPushMatrix();
        // reset matrix
        gl.glLoadIdentity();
        // set a 2D orthographic projection
        gl.gluOrtho2D(0f, glutScreenWidth, 0f, glutScreenHeight);
        gl.glMatrixMode(GL_MODELVIEW);
        gl.glLoadIdentity();

        // invert the y axis, down is positive
        gl.glScalef(1f, -1f, 1f);
        // mover the origin from the bottom left corner
        // to the upper left corner
        gl.glTranslatef(0f, -glutScreenHeight, 0f);
    }

}
