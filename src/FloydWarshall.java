
/*
 * Licensed to GraphHopper GmbH under one or more contributor
 * license agreements. See the NOTICE file distributed with this work for
 * additional information regarding copyright ownership.
 *
 * GraphHopper GmbH licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.util.Arrays;

//SolomonExampleWithSpecifiedVehicleEndLocations
public class FloydWarshall {
	private double[][] distances; // = new double[12][6];
	private boolean negativeCycle = false;

	public FloydWarshall(double[][] graph) {
		int n = graph.length;
		distances = Arrays.copyOf(graph, n);

		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					distances[i][j] = Math.min(distances[i][j], distances[i][k] + distances[k][j]);
				}
			}

			if (distances[k][k] < 0.0) {
				this.negativeCycle = true;
			}
		}
	}

	public boolean hasNegativeCycle() {
		return this.negativeCycle;
	}

	public double[][] distances() {
		return distances;
	}
}