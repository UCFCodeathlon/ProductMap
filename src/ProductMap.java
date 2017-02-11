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

import java.io.File;
import java.util.Collection;

import com.graphhopper.jsprit.analysis.toolbox.AlgorithmSearchProgressChartListener;
import com.graphhopper.jsprit.analysis.toolbox.GraphStreamViewer;
import com.graphhopper.jsprit.analysis.toolbox.GraphStreamViewer.Label;
import com.graphhopper.jsprit.analysis.toolbox.Plotter;
import com.graphhopper.jsprit.core.algorithm.VehicleRoutingAlgorithm;
import com.graphhopper.jsprit.core.algorithm.box.Jsprit;
import com.graphhopper.jsprit.core.algorithm.selector.SelectBest;
import com.graphhopper.jsprit.core.problem.VehicleRoutingProblem;
import com.graphhopper.jsprit.core.problem.solution.VehicleRoutingProblemSolution;
import com.graphhopper.jsprit.core.reporting.SolutionPrinter;
import com.graphhopper.jsprit.io.problem.VrpXMLReader;

//SolomonExampleWithSpecifiedVehicleEndLocations
public class ProductMap {

	public static void main(String[] args) {
		/*
		 * some preparation - create output folder
		 */
		File dir = new File("output");
		// if the directory does not exist, create it
		if (!dir.exists()) {
			System.out.println("creating directory ./output");
			boolean result = dir.mkdir();
			if (result)
				System.out.println("./output created");
		}

		/*
		 * Build the problem.
		 *
		 * But define a problem-builder first.
		 */
		VehicleRoutingProblem.Builder vrpBuilder = VehicleRoutingProblem.Builder.newInstance();

		/*
		 * A solomonReader reads solomon-instance files, and stores the required
		 * information in the builder.
		 */
		new VrpXMLReader(vrpBuilder).read("input/input.xml");
		vrpBuilder.setFleetSize(VehicleRoutingProblem.FleetSize.FINITE);
		/*
		 * Finally, the problem can be built. By default, transportCosts are
		 * crowFlyDistances (as usually used for vrp-instances).
		 */
		VehicleRoutingProblem vrp = vrpBuilder.build();

		Plotter pblmPlotter = new Plotter(vrp);
		pblmPlotter.plot("output/solomon_C101_specifiedVehicleEndLocations.png", "C101");

		/*
		 * Define the required vehicle-routing algorithms to solve the above
		 * problem.
		 *
		 * The algorithm can be defined and configured in an xml-file.
		 */
		// VehicleRoutingAlgorithm vra = new
		// SchrimpfFactory().createAlgorithm(vrp);
		VehicleRoutingAlgorithm vra = Jsprit.createAlgorithm(vrp);
		vra.setMaxIterations(20000);
		// vra.setPrematureBreak(100);
		
		
		
		vra.getAlgorithmListeners().addListener(new AlgorithmSearchProgressChartListener("output/sol_progress.png"));
		/*
		 * Solve the problem.
		 *
		 *
		 */
		Collection<VehicleRoutingProblemSolution> solutions = vra.searchSolutions();

		/*
		 * Retrieve best solution.
		 */
		VehicleRoutingProblemSolution solution = new SelectBest().selectSolution(solutions);

		/*
		 * print solution
		 */
		SolutionPrinter.print(solution);

		/*
		 * Plot solution.
		 */
		
		// SolutionPlotter.plotSolutionAsPNG(vrp, solution,
		// "output/solomon_C101_specifiedVehicleEndLocations_solution.png","C101");
		Plotter solPlotter = new Plotter(vrp, solution);
		solPlotter.plot("output/solomon_C101_specifiedVehicleEndLocations_solution.png", "C101");
		
		new GraphStreamViewer(vrp, solution).setRenderDelay(50).labelWith(Label.ID).display();

	}






double[][] DistanceMap = {{0,2,(Double) Double.POSITIVE_INFINITY	,4,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	},
	{2,0,2,Double.POSITIVE_INFINITY	,4,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	},
	{Double.POSITIVE_INFINITY	,2,0,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,4,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	},
	{4,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,0,2,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,6,Double.POSITIVE_INFINITY	},
	{Double.POSITIVE_INFINITY	,4,Double.POSITIVE_INFINITY	,2,0,1,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	},
	{Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,1,0,1,2,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	},
	{Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,4,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,1,0,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	},
	{Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,2,Double.POSITIVE_INFINITY	,0,2,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	},
	{Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,2,0,Double.POSITIVE_INFINITY	,2},
	{Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,6,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,0,3},
	{Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,Double.POSITIVE_INFINITY	,2,3,0}
		
};
}
class FloydWarshall {
    private double[][] distances;
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