
package org.jogamp.java3d.utils.geometry;

import java.util.ArrayList;

import org.jogamp.java3d.GeometryArray;
import org.jogamp.java3d.GeometryService;
import org.jogamp.vecmath.Point3f;

/**
 * Default implementation of the {@link GeometryService} service interface.
 */
public class GeometryServiceImpl implements GeometryService {

	@Override
	public int triangulateIslands(final int[][] islandCounts,
		final Point3f[][] outVerts, final int[] contourCounts,
		final ArrayList<GeometryArray> triangData)
	{
		int vertOffset = 0;
		final NormalGenerator ng = new NormalGenerator();
		for (int i = 0; i < islandCounts.length; i++) {
			contourCounts[0] = islandCounts[i].length;
			final GeometryInfo gi = new GeometryInfo(GeometryInfo.POLYGON_ARRAY);
			gi.setCoordinates(outVerts[i]);
			gi.setStripCounts(islandCounts[i]);
			gi.setContourCounts(contourCounts);
			ng.generateNormals(gi);

			final GeometryArray ga = gi.getGeometryArray(false, false, false);
			vertOffset += ga.getVertexCount();

			triangData.add(ga);
		}
		return vertOffset;
	}

}
