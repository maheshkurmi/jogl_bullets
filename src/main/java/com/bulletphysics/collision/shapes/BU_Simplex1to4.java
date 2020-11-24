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

package com.bulletphysics.collision.shapes;

import com.bulletphysics.collision.broadphase.BroadphaseNativeType;

import javax.vecmath.Vector3f;

/**
 * BU_Simplex1to4 implements feature based and implicit simplex of up to 4 vertices
 * (tetrahedron, triangle, line, vertex).
 *
 * @author jezek2
 */
public class BU_Simplex1to4 extends PolyhedralConvexShape {

    protected final Vector3f[] vertices = new Vector3f[4];
    protected int numVertices = 0;

    public BU_Simplex1to4() {
    }

    public BU_Simplex1to4(Vector3f pt0) {
        addVertex(pt0);
    }

    public BU_Simplex1to4(Vector3f pt0, Vector3f pt1) {
        addVertex(pt0);
        addVertex(pt1);
    }

    public BU_Simplex1to4(Vector3f pt0, Vector3f pt1, Vector3f pt2) {
        addVertex(pt0);
        addVertex(pt1);
        addVertex(pt2);
    }

    public BU_Simplex1to4(Vector3f pt0, Vector3f pt1, Vector3f pt2, Vector3f pt3) {
        addVertex(pt0);
        addVertex(pt1);
        addVertex(pt2);
        addVertex(pt3);
    }

    public static int getIndex(int i) {
        return 0;
    }

    public void reset() {
        numVertices = 0;
    }

    @Override
    public BroadphaseNativeType getShapeType() {
        return BroadphaseNativeType.TETRAHEDRAL_SHAPE_PROXYTYPE;
    }

    public void addVertex(Vector3f pt) {
        if (vertices[numVertices] == null) {
            vertices[numVertices] = new Vector3f();
        }

        vertices[numVertices++] = pt;

        recalcLocalAabb();
    }

    @Override
    public int getNumVertices() {
        return numVertices;
    }

    @Override
    public int getNumEdges() {
        // euler formula, F-E+V = 2, so E = F+V-2

        return switch (numVertices) {
            case 2 -> 1;
            case 3 -> 3;
            case 4 -> 6;
            default -> 0;
        };

    }

    @Override
    public void getEdge(int i, Vector3f pa, Vector3f pb) {
        switch (numVertices) {
            case 2:
                pa.set(vertices[0]);
                pb.set(vertices[1]);
                break;
            case 3:
                switch (i) {
                    case 0 -> {
                        pa.set(vertices[0]);
                        pb.set(vertices[1]);
                    }
                    case 1 -> {
                        pa.set(vertices[1]);
                        pb.set(vertices[2]);
                    }
                    case 2 -> {
                        pa.set(vertices[2]);
                        pb.set(vertices[0]);
                    }
                }
                break;
            case 4:
                switch (i) {
                    case 0 -> {
                        pa.set(vertices[0]);
                        pb.set(vertices[1]);
                    }
                    case 1 -> {
                        pa.set(vertices[1]);
                        pb.set(vertices[2]);
                    }
                    case 2 -> {
                        pa.set(vertices[2]);
                        pb.set(vertices[0]);
                    }
                    case 3 -> {
                        pa.set(vertices[0]);
                        pb.set(vertices[3]);
                    }
                    case 4 -> {
                        pa.set(vertices[1]);
                        pb.set(vertices[3]);
                    }
                    case 5 -> {
                        pa.set(vertices[2]);
                        pb.set(vertices[3]);
                    }
                }
        }
    }

    @Override
    public void getVertex(int i, Vector3f vtx) {
        vtx.set(vertices[i]);
    }

    @Override
    public int getNumPlanes() {
        return switch (numVertices) {
            case 3 -> 2;
            case 4 -> 4;
            default -> 0;
        };
    }

    @Override
    public void getPlane(Vector3f planeNormal, Vector3f planeSupport, int i) {
    }

    @Override
    public boolean isInside(Vector3f pt, float tolerance) {
        return false;
    }

    @Override
    public String getName() {
        return "BU_Simplex1to4";
    }

}
