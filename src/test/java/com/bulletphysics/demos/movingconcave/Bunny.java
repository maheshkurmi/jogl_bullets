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

package com.bulletphysics.demos.movingconcave;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 *
 * @author jezek2
 */
public class Bunny {
	
	public static ByteBuffer getVertexBuffer() {
		ByteBuffer buf = ByteBuffer.allocateDirect(gVertices.length*4).order(ByteOrder.nativeOrder());
		for (int i=0; i<gVertices.length; i++) {
			buf.putFloat(gVertices[i]);
		}
		buf.flip();
		return buf;
	}

	public static ByteBuffer getIndexBuffer() {
		ByteBuffer buf = ByteBuffer.allocateDirect(gIndices.length*4).order(ByteOrder.nativeOrder());
		for (int i=0; i<gIndices.length; i++) {
			buf.putInt(gIndices[i]);
		}
		buf.flip();
		return buf;
	}
	
	////////////////////////////////////////////////////////////////////////////

	public static final int NUM_TRIANGLES = 902;
	public static final int NUM_VERTICES = 453;
	public static final int NUM_INDICES  = NUM_TRIANGLES * 3;

	public static final float[] gVertices = new float[] {
		-0.334392f, 0.133007f, 0.062259f,
		-0.350189f, 0.150354f, -0.147769f,
		-0.234201f, 0.343811f, -0.174307f,
		-0.200259f, 0.285207f, 0.093749f,
		0.003520f, 0.475208f, -0.159365f,
		0.001856f, 0.419203f, 0.098582f,
		-0.252802f, 0.093666f, 0.237538f,
		-0.162901f, 0.237984f, 0.206905f,
		0.000865f, 0.318141f, 0.235370f,
		-0.414624f, 0.164083f, -0.278254f,
		-0.262213f, 0.357334f, -0.293246f,
		0.004628f, 0.482694f, -0.338626f,
		-0.402162f, 0.133528f, -0.443247f,
		-0.243781f, 0.324275f, -0.436763f,
		0.005293f, 0.437592f, -0.458332f,
		-0.339884f, -0.041150f, -0.668211f,
		-0.248382f, 0.255825f, -0.627493f,
		0.006261f, 0.376103f, -0.631506f,
		-0.216201f, -0.126776f, -0.886936f,
		-0.171075f, 0.011544f, -0.881386f,
		-0.181074f, 0.098223f, -0.814779f,
		-0.119891f, 0.218786f, -0.760153f,
		-0.078895f, 0.276780f, -0.739281f,
		0.006801f, 0.310959f, -0.735661f,
		-0.168842f, 0.102387f, -0.920381f,
		-0.104072f, 0.177278f, -0.952530f,
		-0.129704f, 0.211848f, -0.836678f,
		-0.099875f, 0.310931f, -0.799381f,
		0.007237f, 0.361687f, -0.794439f,
		-0.077913f, 0.258753f, -0.921640f,
		0.007957f, 0.282241f, -0.931680f,
		-0.252222f, -0.550401f, -0.557810f,
		-0.267633f, -0.603419f, -0.655209f,
		-0.446838f, -0.118517f, -0.466159f,
		-0.459488f, -0.093017f, -0.311341f,
		-0.370645f, -0.100108f, -0.159454f,
		-0.371984f, -0.091991f, -0.011044f,
		-0.328945f, -0.098269f, 0.088659f,
		-0.282452f, -0.018862f, 0.311501f,
		-0.352403f, -0.131341f, 0.144902f,
		-0.364126f, -0.200299f, 0.202388f,
		-0.283965f, -0.231869f, 0.023668f,
		-0.298943f, -0.155218f, 0.369716f,
		-0.293787f, -0.121856f, 0.419097f,
		-0.290163f, -0.290797f, 0.107824f,
		-0.264165f, -0.272849f, 0.036347f,
		-0.228567f, -0.372573f, 0.290309f,
		-0.190431f, -0.286997f, 0.421917f,
		-0.191039f, -0.240973f, 0.507118f,
		-0.287272f, -0.276431f, -0.065444f,
		-0.295675f, -0.280818f, -0.174200f,
		-0.399537f, -0.313131f, -0.376167f,
		-0.392666f, -0.488581f, -0.427494f,
		-0.331669f, -0.570185f, -0.466054f,
		-0.282290f, -0.618140f, -0.589220f,
		-0.374238f, -0.594882f, -0.323298f,
		-0.381071f, -0.629723f, -0.350777f,
		-0.382112f, -0.624060f, -0.221577f,
		-0.272701f, -0.566522f, 0.259157f,
		-0.256702f, -0.663406f, 0.286079f,
		-0.280948f, -0.428359f, 0.055790f,
		-0.184974f, -0.508894f, 0.326265f,
		-0.279971f, -0.526918f, 0.395319f,
		-0.282599f, -0.663393f, 0.412411f,
		-0.188329f, -0.475093f, 0.417954f,
		-0.263384f, -0.663396f, 0.466604f,
		-0.209063f, -0.663393f, 0.509344f,
		-0.002044f, -0.319624f, 0.553078f,
		-0.001266f, -0.371260f, 0.413296f,
		-0.219753f, -0.339762f, -0.040921f,
		-0.256986f, -0.282511f, -0.006349f,
		-0.271706f, -0.260881f, 0.001764f,
		-0.091191f, -0.419184f, -0.045912f,
		-0.114944f, -0.429752f, -0.124739f,
		-0.113970f, -0.382987f, -0.188540f,
		-0.243012f, -0.464942f, -0.242850f,
		-0.314815f, -0.505402f, -0.324768f,
		0.002774f, -0.437526f, -0.262766f,
		-0.072625f, -0.417748f, -0.221440f,
		-0.160112f, -0.476932f, -0.293450f,
		0.003859f, -0.453425f, -0.443916f,
		-0.120363f, -0.581567f, -0.438689f,
		-0.091499f, -0.584191f, -0.294511f,
		-0.116469f, -0.599861f, -0.188308f,
		-0.208032f, -0.513640f, -0.134649f,
		-0.235749f, -0.610017f, -0.040939f,
		-0.344916f, -0.622487f, -0.085380f,
		-0.336401f, -0.531864f, -0.212298f,
		0.001961f, -0.459550f, -0.135547f,
		-0.058296f, -0.430536f, -0.043440f,
		0.001378f, -0.449511f, -0.037762f,
		-0.130135f, -0.510222f, 0.079144f,
		0.000142f, -0.477549f, 0.157064f,
		-0.114284f, -0.453206f, 0.304397f,
		-0.000592f, -0.443558f, 0.285401f,
		-0.056215f, -0.663402f, 0.326073f,
		-0.026248f, -0.568010f, 0.273318f,
		-0.049261f, -0.531064f, 0.389854f,
		-0.127096f, -0.663398f, 0.479316f,
		-0.058384f, -0.663401f, 0.372891f,
		-0.303961f, 0.054199f, 0.625921f,
		-0.268594f, 0.193403f, 0.502766f,
		-0.277159f, 0.126123f, 0.443289f,
		-0.287605f, -0.005722f, 0.531844f,
		-0.231396f, -0.121289f, 0.587387f,
		-0.253475f, -0.081797f, 0.756541f,
		-0.195164f, -0.137969f, 0.728011f,
		-0.167673f, -0.156573f, 0.609388f,
		-0.145917f, -0.169029f, 0.697600f,
		-0.077776f, -0.214247f, 0.622586f,
		-0.076873f, -0.214971f, 0.696301f,
		-0.002341f, -0.233135f, 0.622859f,
		-0.002730f, -0.213526f, 0.691267f,
		-0.003136f, -0.192628f, 0.762731f,
		-0.056136f, -0.201222f, 0.763806f,
		-0.114589f, -0.166192f, 0.770723f,
		-0.155145f, -0.129632f, 0.791738f,
		-0.183611f, -0.058705f, 0.847012f,
		-0.165562f, 0.001980f, 0.833386f,
		-0.220084f, 0.019914f, 0.768935f,
		-0.255730f, 0.090306f, 0.670782f,
		-0.255594f, 0.113833f, 0.663389f,
		-0.226380f, 0.212655f, 0.617740f,
		-0.003367f, -0.195342f, 0.799680f,
		-0.029743f, -0.210508f, 0.827180f,
		-0.003818f, -0.194783f, 0.873636f,
		-0.004116f, -0.157907f, 0.931268f,
		-0.031280f, -0.184555f, 0.889476f,
		-0.059885f, -0.184448f, 0.841330f,
		-0.135333f, -0.164332f, 0.878200f,
		-0.085574f, -0.170948f, 0.925547f,
		-0.163833f, -0.094170f, 0.897114f,
		-0.138444f, -0.104250f, 0.945975f,
		-0.083497f, -0.084934f, 0.979607f,
		-0.004433f, -0.146642f, 0.985872f,
		-0.150715f, 0.032650f, 0.884111f,
		-0.135892f, -0.035520f, 0.945455f,
		-0.070612f, 0.036849f, 0.975733f,
		-0.004458f, -0.042526f, 1.015670f,
		-0.004249f, 0.046042f, 1.003240f,
		-0.086969f, 0.133224f, 0.947633f,
		-0.003873f, 0.161605f, 0.970499f,
		-0.125544f, 0.140012f, 0.917678f,
		-0.125651f, 0.250246f, 0.857602f,
		-0.003127f, 0.284070f, 0.878870f,
		-0.159174f, 0.125726f, 0.888878f,
		-0.183807f, 0.196970f, 0.844480f,
		-0.159890f, 0.291736f, 0.732480f,
		-0.199495f, 0.207230f, 0.779864f,
		-0.206182f, 0.164608f, 0.693257f,
		-0.186315f, 0.160689f, 0.817193f,
		-0.192827f, 0.166706f, 0.782271f,
		-0.175112f, 0.110008f, 0.860621f,
		-0.161022f, 0.057420f, 0.855111f,
		-0.172319f, 0.036155f, 0.816189f,
		-0.190318f, 0.064083f, 0.760605f,
		-0.195072f, 0.129179f, 0.731104f,
		-0.203126f, 0.410287f, 0.680536f,
		-0.216677f, 0.309274f, 0.642272f,
		-0.241515f, 0.311485f, 0.587832f,
		-0.002209f, 0.366663f, 0.749413f,
		-0.088230f, 0.396265f, 0.678635f,
		-0.170147f, 0.109517f, 0.840784f,
		-0.160521f, 0.067766f, 0.830650f,
		-0.181546f, 0.139805f, 0.812146f,
		-0.180495f, 0.148568f, 0.776087f,
		-0.180255f, 0.129125f, 0.744192f,
		-0.186298f, 0.078308f, 0.769352f,
		-0.167622f, 0.060539f, 0.806675f,
		-0.189876f, 0.102760f, 0.802582f,
		-0.108340f, 0.455446f, 0.657174f,
		-0.241585f, 0.527592f, 0.669296f,
		-0.265676f, 0.513366f, 0.634594f,
		-0.203073f, 0.478550f, 0.581526f,
		-0.266772f, 0.642330f, 0.602061f,
		-0.216961f, 0.564846f, 0.535435f,
		-0.202210f, 0.525495f, 0.475944f,
		-0.193888f, 0.467925f, 0.520606f,
		-0.265837f, 0.757267f, 0.500933f,
		-0.240306f, 0.653440f, 0.463215f,
		-0.309239f, 0.776868f, 0.304726f,
		-0.271009f, 0.683094f, 0.382018f,
		-0.312111f, 0.671099f, 0.286687f,
		-0.268791f, 0.624342f, 0.377231f,
		-0.302457f, 0.533996f, 0.360289f,
		-0.263656f, 0.529310f, 0.412564f,
		-0.282311f, 0.415167f, 0.447666f,
		-0.239201f, 0.442096f, 0.495604f,
		-0.220043f, 0.569026f, 0.445877f,
		-0.001263f, 0.395631f, 0.602029f,
		-0.057345f, 0.442535f, 0.572224f,
		-0.088927f, 0.506333f, 0.529106f,
		-0.125738f, 0.535076f, 0.612913f,
		-0.126251f, 0.577170f, 0.483159f,
		-0.149594f, 0.611520f, 0.557731f,
		-0.163188f, 0.660791f, 0.491080f,
		-0.172482f, 0.663387f, 0.415416f,
		-0.160464f, 0.591710f, 0.370659f,
		-0.156445f, 0.536396f, 0.378302f,
		-0.136496f, 0.444358f, 0.425226f,
		-0.095564f, 0.373768f, 0.473659f,
		-0.104146f, 0.315912f, 0.498104f,
		-0.000496f, 0.384194f, 0.473817f,
		-0.000183f, 0.297770f, 0.401486f,
		-0.129042f, 0.270145f, 0.434495f,
		0.000100f, 0.272963f, 0.349138f,
		-0.113060f, 0.236984f, 0.385554f,
		0.007260f, 0.016311f, -0.883396f,
		0.007865f, 0.122104f, -0.956137f,
		-0.032842f, 0.115282f, -0.953252f,
		-0.089115f, 0.108449f, -0.950317f,
		-0.047440f, 0.014729f, -0.882756f,
		-0.104458f, 0.013137f, -0.882070f,
		-0.086439f, -0.584866f, -0.608343f,
		-0.115026f, -0.662605f, -0.436732f,
		-0.071683f, -0.665372f, -0.606385f,
		-0.257884f, -0.665381f, -0.658052f,
		-0.272542f, -0.665381f, -0.592063f,
		-0.371322f, -0.665382f, -0.353620f,
		-0.372362f, -0.665381f, -0.224420f,
		-0.335166f, -0.665380f, -0.078623f,
		-0.225999f, -0.665375f, -0.038981f,
		-0.106719f, -0.665374f, -0.186351f,
		-0.081749f, -0.665372f, -0.292554f,
		0.006943f, -0.091505f, -0.858354f,
		0.006117f, -0.280985f, -0.769967f,
		0.004495f, -0.502360f, -0.559799f,
		-0.198638f, -0.302135f, -0.845816f,
		-0.237395f, -0.542544f, -0.587188f,
		-0.270001f, -0.279489f, -0.669861f,
		-0.134547f, -0.119852f, -0.959004f,
		-0.052088f, -0.122463f, -0.944549f,
		-0.124463f, -0.293508f, -0.899566f,
		-0.047616f, -0.289643f, -0.879292f,
		-0.168595f, -0.529132f, -0.654931f,
		-0.099793f, -0.515719f, -0.645873f,
		-0.186168f, -0.605282f, -0.724690f,
		-0.112970f, -0.583097f, -0.707469f,
		-0.108152f, -0.665375f, -0.700408f,
		-0.183019f, -0.665378f, -0.717630f,
		-0.349529f, -0.334459f, -0.511985f,
		-0.141182f, -0.437705f, -0.798194f,
		-0.212670f, -0.448725f, -0.737447f,
		-0.261111f, -0.414945f, -0.613835f,
		-0.077364f, -0.431480f, -0.778113f,
		0.005174f, -0.425277f, -0.651592f,
		0.089236f, -0.431732f, -0.777093f,
		0.271006f, -0.415749f, -0.610577f,
		0.223981f, -0.449384f, -0.734774f,
		0.153275f, -0.438150f, -0.796391f,
		0.358414f, -0.335529f, -0.507649f,
		0.193434f, -0.665946f, -0.715325f,
		0.118363f, -0.665717f, -0.699021f,
		0.123515f, -0.583454f, -0.706020f,
		0.196851f, -0.605860f, -0.722345f,
		0.109788f, -0.516035f, -0.644590f,
		0.178656f, -0.529656f, -0.652804f,
		0.061157f, -0.289807f, -0.878626f,
		0.138234f, -0.293905f, -0.897958f,
		0.066933f, -0.122643f, -0.943820f,
		0.149571f, -0.120281f, -0.957264f,
		0.280989f, -0.280321f, -0.666487f,
		0.246581f, -0.543275f, -0.584224f,
		0.211720f, -0.302754f, -0.843303f,
		0.086966f, -0.665627f, -0.291520f,
		0.110634f, -0.665702f, -0.185021f,
		0.228099f, -0.666061f, -0.036201f,
		0.337743f, -0.666396f, -0.074503f,
		0.376722f, -0.666513f, -0.219833f,
		0.377265f, -0.666513f, -0.349036f,
		0.281411f, -0.666217f, -0.588670f,
		0.267564f, -0.666174f, -0.654834f,
		0.080745f, -0.665602f, -0.605452f,
		0.122016f, -0.662963f, -0.435280f,
		0.095767f, -0.585141f, -0.607228f,
		0.118944f, 0.012799f, -0.880702f,
		0.061944f, 0.014564f, -0.882086f,
		0.104725f, 0.108156f, -0.949130f,
		0.048513f, 0.115159f, -0.952753f,
		0.112696f, 0.236643f, 0.386937f,
		0.128177f, 0.269757f, 0.436071f,
		0.102643f, 0.315600f, 0.499370f,
		0.094535f, 0.373481f, 0.474824f,
		0.136270f, 0.443946f, 0.426895f,
		0.157071f, 0.535923f, 0.380222f,
		0.161350f, 0.591224f, 0.372630f,
		0.173035f, 0.662865f, 0.417531f,
		0.162808f, 0.660299f, 0.493077f,
		0.148250f, 0.611070f, 0.559555f,
		0.125719f, 0.576790f, 0.484702f,
		0.123489f, 0.534699f, 0.614440f,
		0.087621f, 0.506066f, 0.530188f,
		0.055321f, 0.442365f, 0.572915f,
		0.219936f, 0.568361f, 0.448571f,
		0.238099f, 0.441375f, 0.498528f,
		0.281711f, 0.414315f, 0.451121f,
		0.263833f, 0.528513f, 0.415794f,
		0.303284f, 0.533081f, 0.363998f,
		0.269687f, 0.623528f, 0.380528f,
		0.314255f, 0.670153f, 0.290524f,
		0.272023f, 0.682273f, 0.385343f,
		0.311480f, 0.775931f, 0.308527f,
		0.240239f, 0.652714f, 0.466159f,
		0.265619f, 0.756464f, 0.504187f,
		0.192562f, 0.467341f, 0.522972f,
		0.201605f, 0.524885f, 0.478417f,
		0.215743f, 0.564193f, 0.538084f,
		0.264969f, 0.641527f, 0.605317f,
		0.201031f, 0.477940f, 0.584002f,
		0.263086f, 0.512567f, 0.637832f,
		0.238615f, 0.526867f, 0.672237f,
		0.105309f, 0.455123f, 0.658482f,
		0.183993f, 0.102195f, 0.804872f,
		0.161563f, 0.060042f, 0.808692f,
		0.180748f, 0.077754f, 0.771600f,
		0.175168f, 0.128588f, 0.746368f,
		0.175075f, 0.148030f, 0.778264f,
		0.175658f, 0.139265f, 0.814333f,
		0.154191f, 0.067291f, 0.832578f,
		0.163818f, 0.109013f, 0.842830f,
		0.084760f, 0.396004f, 0.679695f,
		0.238888f, 0.310760f, 0.590775f,
		0.213380f, 0.308625f, 0.644905f,
		0.199666f, 0.409678f, 0.683003f,
		0.190143f, 0.128597f, 0.733463f,
		0.184833f, 0.063516f, 0.762902f,
		0.166070f, 0.035644f, 0.818261f,
		0.154361f, 0.056943f, 0.857042f,
		0.168542f, 0.109489f, 0.862725f,
		0.187387f, 0.166131f, 0.784599f,
		0.180428f, 0.160135f, 0.819438f,
		0.201823f, 0.163991f, 0.695756f,
		0.194206f, 0.206635f, 0.782275f,
		0.155438f, 0.291260f, 0.734412f,
		0.177696f, 0.196424f, 0.846693f,
		0.152305f, 0.125256f, 0.890786f,
		0.119546f, 0.249876f, 0.859104f,
		0.118369f, 0.139643f, 0.919173f,
		0.079410f, 0.132973f, 0.948652f,
		0.062419f, 0.036648f, 0.976547f,
		0.127847f, -0.035919f, 0.947070f,
		0.143624f, 0.032206f, 0.885913f,
		0.074888f, -0.085173f, 0.980577f,
		0.130184f, -0.104656f, 0.947620f,
		0.156201f, -0.094653f, 0.899074f,
		0.077366f, -0.171194f, 0.926545f,
		0.127722f, -0.164729f, 0.879810f,
		0.052670f, -0.184618f, 0.842019f,
		0.023477f, -0.184638f, 0.889811f,
		0.022626f, -0.210587f, 0.827500f,
		0.223089f, 0.211976f, 0.620493f,
		0.251444f, 0.113067f, 0.666494f,
		0.251419f, 0.089540f, 0.673887f,
		0.214360f, 0.019258f, 0.771595f,
		0.158999f, 0.001490f, 0.835374f,
		0.176696f, -0.059249f, 0.849218f,
		0.148696f, -0.130091f, 0.793599f,
		0.108290f, -0.166528f, 0.772088f,
		0.049820f, -0.201382f, 0.764454f,
		0.071341f, -0.215195f, 0.697209f,
		0.073148f, -0.214475f, 0.623510f,
		0.140502f, -0.169461f, 0.699354f,
		0.163374f, -0.157073f, 0.611416f,
		0.189466f, -0.138550f, 0.730366f,
		0.247593f, -0.082554f, 0.759610f,
		0.227468f, -0.121982f, 0.590197f,
		0.284702f, -0.006586f, 0.535347f,
		0.275741f, 0.125287f, 0.446676f,
		0.266650f, 0.192594f, 0.506044f,
		0.300086f, 0.053287f, 0.629620f,
		0.055450f, -0.663935f, 0.375065f,
		0.122854f, -0.664138f, 0.482323f,
		0.046520f, -0.531571f, 0.391918f,
		0.024824f, -0.568450f, 0.275106f,
		0.053855f, -0.663931f, 0.328224f,
		0.112829f, -0.453549f, 0.305788f,
		0.131265f, -0.510617f, 0.080746f,
		0.061174f, -0.430716f, -0.042710f,
		0.341019f, -0.532887f, -0.208150f,
		0.347705f, -0.623533f, -0.081139f,
		0.238040f, -0.610732f, -0.038037f,
		0.211764f, -0.514274f, -0.132078f,
		0.120605f, -0.600219f, -0.186856f,
		0.096985f, -0.584476f, -0.293357f,
		0.127621f, -0.581941f, -0.437170f,
		0.165902f, -0.477425f, -0.291453f,
		0.077720f, -0.417975f, -0.220519f,
		0.320892f, -0.506363f, -0.320874f,
		0.248214f, -0.465684f, -0.239842f,
		0.118764f, -0.383338f, -0.187114f,
		0.118816f, -0.430106f, -0.123307f,
		0.094131f, -0.419464f, -0.044777f,
		0.274526f, -0.261706f, 0.005110f,
		0.259842f, -0.283292f, -0.003185f,
		0.222861f, -0.340431f, -0.038210f,
		0.204445f, -0.664380f, 0.513353f,
		0.259286f, -0.664547f, 0.471281f,
		0.185402f, -0.476020f, 0.421718f,
		0.279163f, -0.664604f, 0.417328f,
		0.277157f, -0.528122f, 0.400208f,
		0.183069f, -0.509812f, 0.329995f,
		0.282599f, -0.429210f, 0.059242f,
		0.254816f, -0.664541f, 0.290687f,
		0.271436f, -0.567707f, 0.263966f,
		0.386561f, -0.625221f, -0.216870f,
		0.387086f, -0.630883f, -0.346073f,
		0.380021f, -0.596021f, -0.318679f,
		0.291269f, -0.619007f, -0.585707f,
		0.339280f, -0.571198f, -0.461946f,
		0.400045f, -0.489778f, -0.422640f,
		0.406817f, -0.314349f, -0.371230f,
		0.300588f, -0.281718f, -0.170549f,
		0.290866f, -0.277304f, -0.061905f,
		0.187735f, -0.241545f, 0.509437f,
		0.188032f, -0.287569f, 0.424234f,
		0.227520f, -0.373262f, 0.293102f,
		0.266526f, -0.273650f, 0.039597f,
		0.291592f, -0.291676f, 0.111386f,
		0.291914f, -0.122741f, 0.422683f,
		0.297574f, -0.156119f, 0.373368f,
		0.286603f, -0.232731f, 0.027162f,
		0.364663f, -0.201399f, 0.206850f,
		0.353855f, -0.132408f, 0.149228f,
		0.282208f, -0.019715f, 0.314960f,
		0.331187f, -0.099266f, 0.092701f,
		0.375463f, -0.093120f, -0.006467f,
		0.375917f, -0.101236f, -0.154882f,
		0.466635f, -0.094416f, -0.305669f,
		0.455805f, -0.119881f, -0.460632f,
		0.277465f, -0.604242f, -0.651871f,
		0.261022f, -0.551176f, -0.554667f,
		0.093627f, 0.258494f, -0.920589f,
		0.114248f, 0.310608f, -0.798070f,
		0.144232f, 0.211434f, -0.835001f,
		0.119916f, 0.176940f, -0.951159f,
		0.184061f, 0.101854f, -0.918220f,
		0.092431f, 0.276521f, -0.738231f,
		0.133504f, 0.218403f, -0.758602f,
		0.194987f, 0.097655f, -0.812476f,
		0.185542f, 0.011005f, -0.879202f,
		0.230315f, -0.127450f, -0.884202f,
		0.260471f, 0.255056f, -0.624378f,
		0.351567f, -0.042194f, -0.663976f,
		0.253742f, 0.323524f, -0.433716f,
		0.411612f, 0.132299f, -0.438264f,
		0.270513f, 0.356530f, -0.289984f,
		0.422146f, 0.162819f, -0.273130f,
		0.164724f, 0.237490f, 0.208912f,
		0.253806f, 0.092900f, 0.240640f,
		0.203608f, 0.284597f, 0.096223f,
		0.241006f, 0.343093f, -0.171396f,
		0.356076f, 0.149288f, -0.143443f,
		0.337656f, 0.131992f, 0.066374f
	};

	public static final int[] gIndices = new int[] {
		126,134,133,
		342,138,134,
		133,134,138,
		126,342,134,
		312,316,317,
		169,163,162,
		312,317,319,
		312,319,318,
		169,162,164,
		169,168,163,
		312,314,315,
		169,164,165,
		169,167,168,
		312,315,316,
		312,313,314,
		169,165,166,
		169,166,167,
		312,318,313,
		308,304,305,
		308,305,306,
		179,181,188,
		177,173,175,
		177,175,176,
		302,293,300,
		322,294,304,
		188,176,175,
		188,175,179,
		158,177,187,
		305,293,302,
		305,302,306,
		322,304,308,
		188,181,183,
		158,173,177,
		293,298,300,
		304,294,296,
		304,296,305,
		185,176,188,
		185,188,183,
		187,177,176,
		187,176,185,
		305,296,298,
		305,298,293,
		436,432, 28,
		436, 28, 23,
		434,278,431,
		 30,208,209,
		 30,209, 29,
		 19, 20, 24,
		208,207,211,
		208,211,209,
		 19,210,212,
		433,434,431,
		433,431,432,
		433,432,436,
		436,437,433,
		277,275,276,
		277,276,278,
		209,210, 25,
		 21, 26, 24,
		 21, 24, 20,
		 25, 26, 27,
		 25, 27, 29,
		435,439,277,
		439,275,277,
		432,431, 30,
		432, 30, 28,
		433,437,438,
		433,438,435,
		434,277,278,
		 24, 25,210,
		 24, 26, 25,
		 29, 27, 28,
		 29, 28, 30,
		 19, 24,210,
		208, 30,431,
		208,431,278,
		435,434,433,
		435,277,434,
		 25, 29,209,
		 27, 22, 23,
		 27, 23, 28,
		 26, 22, 27,
		 26, 21, 22,
		212,210,209,
		212,209,211,
		207,208,278,
		207,278,276,
		439,435,438,
		 12,  9, 10,
		 12, 10, 13,
		  2,  3,  5,
		  2,  5,  4,
		 16, 13, 14,
		 16, 14, 17,
		 22, 21, 16,
		 13, 10, 11,
		 13, 11, 14,
		  1,  0,  3,
		  1,  3,  2,
		 15, 12, 16,
		 19, 18, 15,
		 19, 15, 16,
		 19, 16, 20,
		  9,  1,  2,
		  9,  2, 10,
		  3,  7,  8,
		  3,  8,  5,
		 16, 17, 23,
		 16, 23, 22,
		 21, 20, 16,
		 10,  2,  4,
		 10,  4, 11,
		  0,  6,  7,
		  0,  7,  3,
		 12, 13, 16,
		451,446,445,
		451,445,450,
		442,440,439,
		442,439,438,
		442,438,441,
		421,420,422,
		412,411,426,
		412,426,425,
		408,405,407,
		413, 67, 68,
		413, 68,414,
		391,390,412,
		 80,384,386,
		404,406,378,
		390,391,377,
		390,377, 88,
		400,415,375,
		398,396,395,
		398,395,371,
		398,371,370,
		112,359,358,
		112,358,113,
		351,352,369,
		125,349,348,
		345,343,342,
		342,340,339,
		341,335,337,
		328,341,327,
		331,323,333,
		331,322,323,
		327,318,319,
		327,319,328,
		315,314,324,
		302,300,301,
		302,301,303,
		320,311,292,
		285,284,289,
		310,307,288,
		310,288,290,
		321,350,281,
		321,281,282,
		423,448,367,
		272,273,384,
		272,384,274,
		264,265,382,
		264,382,383,
		440,442,261,
		440,261,263,
		252,253,254,
		252,254,251,
		262,256,249,
		262,249,248,
		228,243,242,
		228, 31,243,
		213,215,238,
		213,238,237,
		 19,212,230,
		224,225,233,
		224,233,231,
		217,218, 56,
		217, 56, 54,
		217,216,239,
		217,239,238,
		217,238,215,
		218,217,215,
		218,215,214,
		  6,102,206,
		186,199,200,
		197,182,180,
		170,171,157,
		201,200,189,
		170,190,191,
		170,191,192,
		175,174,178,
		175,178,179,
		168,167,155,
		122,149,158,
		122,158,159,
		135,153,154,
		135,154,118,
		143,140,141,
		143,141,144,
		132,133,136,
		130,126,133,
		124,125,127,
		122,101,100,
		122,100,121,
		110,108,107,
		110,107,109,
		 98, 99, 97,
		 98, 97, 64,
		 98, 64, 66,
		 87, 55, 57,
		 83, 82, 79,
		 83, 79, 84,
		 78, 74, 50,
		 49, 71, 41,
		 49, 41, 37,
		 49, 37, 36,
		 58, 44, 60,
		 60, 59, 58,
		 51, 34, 33,
		 39, 40, 42,
		 39, 42, 38,
		243,240, 33,
		243, 33,229,
		 39, 38,  6,
		 44, 46, 40,
		 55, 56, 57,
		 64, 62, 65,
		 64, 65, 66,
		 41, 71, 45,
		 75, 50, 51,
		 81, 79, 82,
		 77, 88, 73,
		 93, 92, 94,
		 68, 47, 46,
		 96, 97, 99,
		 96, 99, 95,
		110,109,111,
		111,112,110,
		114,113,123,
		114,123,124,
		132,131,129,
		133,137,136,
		135,142,145,
		145,152,135,
		149,147,157,
		157,158,149,
		164,150,151,
		153,163,168,
		153,168,154,
		185,183,182,
		185,182,184,
		161,189,190,
		200,199,191,
		200,191,190,
		180,178,195,
		180,195,196,
		102,101,204,
		102,204,206,
		 43, 48,104,
		 43,104,103,
		216,217, 54,
		216, 54, 32,
		207,224,231,
		230,212,211,
		230,211,231,
		227,232,241,
		227,241,242,
		235,234,241,
		235,241,244,
		430,248,247,
		272,274,253,
		272,253,252,
		439,260,275,
		225,224,259,
		225,259,257,
		269,270,407,
		269,407,405,
		270,269,273,
		270,273,272,
		273,269,268,
		273,268,267,
		273,267,266,
		273,266,265,
		273,265,264,
		448,279,367,
		281,350,368,
		285,286,301,
		290,323,310,
		290,311,323,
		282,281,189,
		292,311,290,
		292,290,291,
		307,306,302,
		307,302,303,
		316,315,324,
		316,324,329,
		331,351,350,
		330,334,335,
		330,335,328,
		341,337,338,
		344,355,354,
		346,345,348,
		346,348,347,
		364,369,352,
		364,352,353,
		365,363,361,
		365,361,362,
		376,401,402,
		373,372,397,
		373,397,400,
		376, 92,377,
		381,378,387,
		381,387,385,
		386, 77, 80,
		390,389,412,
		416,417,401,
		403,417,415,
		408,429,430,
		419,423,418,
		427,428,444,
		427,444,446,
		437,436,441,
		450,445, 11,
		450, 11,  4,
		447,449,  5,
		447,  5,  8,
		441,438,437,
		425,426,451,
		425,451,452,
		417,421,415,
		408,407,429,
		399,403,400,
		399,400,397,
		394,393,416,
		389,411,412,
		386,383,385,
		408,387,378,
		408,378,406,
		377,391,376,
		 94,375,415,
		372,373,374,
		372,374,370,
		359,111,360,
		359,112,111,
		113,358,349,
		113,349,123,
		346,343,345,
		343,340,342,
		338,336,144,
		338,144,141,
		327,341,354,
		327,354,326,
		331,350,321,
		331,321,322,
		314,313,326,
		314,326,325,
		300,298,299,
		300,299,301,
		288,287,289,
		189,292,282,
		287,288,303,
		284,285,297,
		368,280,281,
		448,447,279,
		274,226,255,
		267,268,404,
		267,404,379,
		429,262,430,
		439,440,260,
		257,258,249,
		257,249,246,
		430,262,248,
		234,228,242,
		234,242,241,
		237,238,239,
		237,239,236,
		 15, 18,227,
		 15,227,229,
		222,223, 82,
		222, 82, 83,
		214,215,213,
		214,213, 81,
		 38,102,  6,
		122,159,200,
		122,200,201,
		174,171,192,
		174,192,194,
		197,193,198,
		190,170,161,
		181,179,178,
		181,178,180,
		166,156,155,
		163,153,152,
		163,152,162,
		120,156,149,
		120,149,121,
		152,153,135,
		140,143,142,
		135,131,132,
		135,132,136,
		130,129,128,
		130,128,127,
		100,105,119,
		100,119,120,
		106,104,107,
		106,107,108,
		 91, 95, 59,
		 93, 94, 68,
		 91, 89, 92,
		 76, 53, 55,
		 76, 55, 87,
		 81, 78, 79,
		 74, 73, 49,
		 69, 60, 45,
		 58, 62, 64,
		 58, 64, 61,
		 53, 31, 32,
		 32, 54, 53,
		 42, 43, 38,
		 35, 36,  0,
		 35,  0,  1,
		 34, 35,  1,
		 34,  1,  9,
		 44, 40, 41,
		 44, 41, 45,
		 33,240, 51,
		 63, 62, 58,
		 63, 58, 59,
		 45, 71, 70,
		 76, 75, 51,
		 76, 51, 52,
		 86, 85, 84,
		 86, 84, 87,
		 89, 72, 73,
		 89, 73, 88,
		 91, 92, 96,
		 91, 96, 95,
		 72, 91, 60,
		 72, 60, 69,
		104,106,105,
		119,105,117,
		119,117,118,
		124,127,128,
		117,116,129,
		117,129,131,
		118,117,131,
		135,140,142,
		146,150,152,
		146,152,145,
		149,122,121,
		166,165,151,
		166,151,156,
		158,172,173,
		161,160,189,
		199,198,193,
		199,193,191,
		204,201,202,
		178,174,194,
		200,159,186,
		109, 48, 67,
		 48,107,104,
		216, 32,236,
		216,236,239,
		223,214, 81,
		223, 81, 82,
		 33, 12, 15,
		 32,228,234,
		 32,234,236,
		240, 31, 52,
		256,255,246,
		256,246,249,
		258,263,248,
		258,248,249,
		275,260,259,
		275,259,276,
		207,276,259,
		270,271,429,
		270,429,407,
		413,418,366,
		413,366,365,
		368,367,279,
		368,279,280,
		303,301,286,
		303,286,287,
		283,282,292,
		283,292,291,
		320,292,189,
		298,296,297,
		298,297,299,
		318,327,326,
		318,326,313,
		329,330,317,
		336,333,320,
		326,354,353,
		334,332,333,
		334,333,336,
		342,339,139,
		342,139,138,
		345,342,126,
		347,357,356,
		369,368,351,
		363,356,357,
		363,357,361,
		366,367,368,
		366,368,369,
		375,373,400,
		 92, 90,377,
		409,387,408,
		386,385,387,
		386,387,388,
		412,394,391,
		396,398,399,
		408,406,405,
		415,421,419,
		415,419,414,
		425,452,448,
		425,448,424,
		444,441,443,
		448,452,449,
		448,449,447,
		446,444,443,
		446,443,445,
		250,247,261,
		250,261,428,
		421,422,423,
		421,423,419,
		427,410,250,
		417,403,401,
		403,402,401,
		420,392,412,
		420,412,425,
		420,425,424,
		386,411,389,
		383,382,381,
		383,381,385,
		378,379,404,
		372,371,395,
		372,395,397,
		371,372,370,
		361,359,360,
		361,360,362,
		368,350,351,
		349,347,348,
		356,355,344,
		356,344,346,
		344,341,340,
		344,340,343,
		338,337,336,
		328,335,341,
		324,352,351,
		324,351,331,
		320,144,336,
		314,325,324,
		322,308,309,
		310,309,307,
		287,286,289,
		203,280,279,
		203,279,205,
		297,295,283,
		297,283,284,
		447,205,279,
		274,384, 80,
		274, 80,226,
		266,267,379,
		266,379,380,
		225,257,246,
		225,246,245,
		256,254,253,
		256,253,255,
		430,247,250,
		226,235,244,
		226,244,245,
		232,233,244,
		232,244,241,
		230, 18, 19,
		 32, 31,228,
		219,220, 86,
		219, 86, 57,
		226,213,235,
		206,  7,  6,
		122,201,101,
		201,204,101,
		180,196,197,
		170,192,171,
		200,190,189,
		194,193,195,
		183,181,180,
		183,180,182,
		155,154,168,
		149,156,151,
		149,151,148,
		155,156,120,
		145,142,143,
		145,143,146,
		136,137,140,
		133,132,130,
		128,129,116,
		100,120,121,
		110,112,113,
		110,113,114,
		 66, 65, 63,
		 66, 63, 99,
		 66, 99, 98,
		 96, 46, 61,
		 89, 88, 90,
		 86, 87, 57,
		 80, 78, 81,
		 72, 69, 49,
		 67, 48, 47,
		 67, 47, 68,
		 56, 55, 53,
		 50, 49, 36,
		 50, 36, 35,
		 40, 39, 41,
		242,243,229,
		242,229,227,
		  6, 37, 39,
		 42, 47, 48,
		 42, 48, 43,
		 61, 46, 44,
		 45, 70, 69,
		 69, 70, 71,
		 69, 71, 49,
		 74, 78, 77,
		 83, 84, 85,
		 73, 74, 77,
		 93, 96, 92,
		 68, 46, 93,
		 95, 99, 63,
		 95, 63, 59,
		115,108,110,
		115,110,114,
		125,126,127,
		129,130,132,
		137,133,138,
		137,138,139,
		148,146,143,
		148,143,147,
		119,118,154,
		161,147,143,
		165,164,151,
		158,157,171,
		158,171,172,
		159,158,187,
		159,187,186,
		194,192,191,
		194,191,193,
		189,202,201,
		182,197,184,
		205,  8,  7,
		 48,109,107,
		218,219, 57,
		218, 57, 56,
		207,231,211,
		232,230,231,
		232,231,233,
		 53, 52, 31,
		388,411,386,
		409,430,250,
		262,429,254,
		262,254,256,
		442,444,428,
		273,264,383,
		273,383,384,
		429,271,251,
		429,251,254,
		413,365,362,
		 67,413,360,
		282,283,295,
		285,301,299,
		202,281,280,
		284,283,291,
		284,291,289,
		320,189,160,
		308,306,307,
		307,309,308,
		319,317,330,
		319,330,328,
		353,352,324,
		332,331,333,
		340,341,338,
		354,341,344,
		349,358,357,
		349,357,347,
		364,355,356,
		364,356,363,
		364,365,366,
		364,366,369,
		374,376,402,
		375, 92,373,
		 77,389,390,
		382,380,381,
		389, 77,386,
		393,394,412,
		393,412,392,
		401,394,416,
		415,400,403,
		411,410,427,
		411,427,426,
		422,420,424,
		247,248,263,
		247,263,261,
		445,443, 14,
		445, 14, 11,
		449,450,  4,
		449,  4,  5,
		443,441, 17,
		443, 17, 14,
		436, 23, 17,
		436, 17,441,
		424,448,422,
		448,423,422,
		414,419,418,
		414,418,413,
		406,404,405,
		399,397,395,
		399,395,396,
		420,416,392,
		388,410,411,
		386,384,383,
		390, 88, 77,
		375, 94, 92,
		415,414, 68,
		415, 68, 94,
		370,374,402,
		370,402,398,
		361,357,358,
		361,358,359,
		125,348,126,
		346,344,343,
		340,338,339,
		337,335,334,
		337,334,336,
		325,353,324,
		324,331,332,
		324,332,329,
		323,322,309,
		323,309,310,
		294,295,297,
		294,297,296,
		289,286,285,
		202,280,203,
		288,307,303,
		282,295,321,
		 67,360,111,
		418,423,367,
		418,367,366,
		272,252,251,
		272,251,271,
		272,271,270,
		255,253,274,
		265,266,380,
		265,380,382,
		442,428,261,
		440,263,258,
		440,258,260,
		409,250,410,
		255,226,245,
		255,245,246,
		 31,240,243,
		236,234,235,
		236,235,237,
		233,225,245,
		233,245,244,
		220,221, 85,
		220, 85, 86,
		 81,213,226,
		 81,226, 80,
		  7,206,205,
		186,184,198,
		186,198,199,
		204,203,205,
		204,205,206,
		195,193,196,
		171,174,172,
		173,174,175,
		173,172,174,
		155,167,166,
		160,161,143,
		160,143,144,
		119,154,155,
		148,151,150,
		148,150,146,
		140,137,139,
		140,139,141,
		127,126,130,
		114,124,128,
		114,128,115,
		117,105,106,
		117,106,116,
		104,105,100,
		104,100,103,
		 59, 60, 91,
		 97, 96, 61,
		 97, 61, 64,
		 91, 72, 89,
		 87, 84, 79,
		 87, 79, 76,
		 78, 80, 77,
		 49, 50, 74,
		 60, 44, 45,
		 61, 44, 58,
		 51, 50, 35,
		 51, 35, 34,
		 39, 37, 41,
		 33, 34,  9,
		 33,  9, 12,
		  0, 36, 37,
		  0, 37,  6,
		 40, 46, 47,
		 40, 47, 42,
		 53, 54, 56,
		 65, 62, 63,
		 72, 49, 73,
		 79, 78, 75,
		 79, 75, 76,
		 52, 53, 76,
		 92, 89, 90,
		 96, 93, 46,
		102,103,100,
		102,100,101,
		116,106,108,
		116,108,115,
		123,125,124,
		116,115,128,
		118,131,135,
		140,135,136,
		148,147,149,
		120,119,155,
		164,162,152,
		164,152,150,
		157,147,161,
		157,161,170,
		186,187,185,
		186,185,184,
		193,197,196,
		202,203,204,
		194,195,178,
		198,184,197,
		 67,111,109,
		 38, 43,103,
		 38,103,102,
		214,223,222,
		214,222,221,
		214,221,220,
		214,220,219,
		214,219,218,
		213,237,235,
		221,222, 83,
		221, 83, 85,
		 15,229, 33,
		227, 18,230,
		227,230,232,
		 52, 51,240,
		 75, 78, 50,
		408,430,409,
		260,258,257,
		260,257,259,
		224,207,259,
		268,269,405,
		268,405,404,
		413,362,360,
		447,  8,205,
		299,297,285,
		189,281,202,
		290,288,289,
		290,289,291,
		322,321,295,
		322,295,294,
		333,323,311,
		333,311,320,
		317,316,329,
		320,160,144,
		353,325,326,
		329,332,334,
		329,334,330,
		339,338,141,
		339,141,139,
		348,345,126,
		347,356,346,
		123,349,125,
		364,353,354,
		364,354,355,
		365,364,363,
		376,391,394,
		376,394,401,
		 92,376,374,
		 92,374,373,
		377, 90, 88,
		380,379,378,
		380,378,381,
		388,387,409,
		388,409,410,
		416,393,392,
		399,398,402,
		399,402,403,
		250,428,427,
		421,417,416,
		421,416,420,
		426,427,446,
		426,446,451,
		444,442,441,
		452,451,450,
		452,450,449
	};
	
}
