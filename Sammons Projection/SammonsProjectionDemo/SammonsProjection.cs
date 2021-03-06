﻿using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SammonsProjectionDemo
{
    /// <summary>
    /// Represents a nonlinear projection implemented as Sammon's Mapping.
    /// </summary>
    /// <remarks>
    /// Source:
    ///	"Self Organizing Maps", Teuvo Kohonen, Springer, 3. Ausgabe, 2001, 
    ///	Abschnitt 1.3.2 (Projection Methods).
    ///	<para>
    ///	As distance-measure the so called Manhattan-distance is used.
    /// </para>
    /// </remarks>
    [Serializable]
    public class SammonsProjection
    {
        #region Felder
        private int _maxIteration;
        private double _lambda = 1;		// Startwert
        private int[] _indicesI;
        private int[] _indicesJ;

        /// <summary>
        /// The precalculated distance-matrix.
        /// </summary>
        protected double[][] _distanceMatrix;
        #endregion
        //---------------------------------------------------------------------
        #region Eigenschaften
        /// <summary>
        /// The input-data.
        /// </summary>
        public double[][] InputData { get; protected set; }
        //---------------------------------------------------------------------
        /// <summary>
        /// The number of input-vectors.
        /// </summary>
        public int Count
        {
            get { return this.InputData.Length; }
        }
        //---------------------------------------------------------------------
        /// <summary>
        /// The dimension in that the projection should be performed.
        /// </summary>
        public int OutputDimension { get; protected set; }
        //---------------------------------------------------------------------
        /// <summary>
        /// The projected vectors.
        /// </summary>
        public double[][] Projection { get; protected set; }
        //---------------------------------------------------------------------
        /// <summary>
        /// The number of iterations.
        /// </summary>
        public int Iteration { get; protected set; }
        #endregion
        //---------------------------------------------------------------------
        #region Konstruktor
        /// <summary>
        /// Creates a new instance of Sammon's Mapping.
        /// </summary>
        /// <param name="inputData">The input-vectors.</param>
        /// <param name="outputDimension">The dimension of the projection.</param>
        /// <exception cref="ArgumentNullException">
        /// <paramref name=">inputVectors"/> is <c>null</c>.
        /// </exception>
        public SammonsProjection(double[][] inputData, int outputDimension)
            : this(inputData, outputDimension, inputData.Length * (int)1e4) { }
        //---------------------------------------------------------------------
        /// <summary>
        /// Creates a new instance of Sammon's Mapping.
        /// </summary>
        /// <param name="inputData">The input-vectors.</param>
        /// <param name="outputDimension">The dimension of the projection.</param>
        /// <param name="maxIteration">
        /// Maximum number of iterations. For a statistical acceptable accuracy
        /// this should be 10e4...1e5 times the number of points. It has shown
        /// that a few iterations (100) yield a good projection.
        /// </param>
        /// <exception cref="ArgumentNullException">
        /// <paramref name=">inputVectors"/> is <c>null</c>.
        /// </exception>
        public SammonsProjection(double[][] inputData, int outputDimension, int maxIteration)
        {
            if (inputData == null || inputData.Length == 0)
                throw new ArgumentNullException("inputData");
            //-----------------------------------------------------------------
            this.InputData = inputData;
            this.OutputDimension = outputDimension;
            _maxIteration = maxIteration;

            // Initialize the projection:
            Initialize();

            // Create the indices-arrays:
            _indicesI = Enumerable.Range(0, this.Count).ToArray();
            _indicesJ = new int[this.Count];
            _indicesI.CopyTo(_indicesJ, 0);
        }
        #endregion
        //---------------------------------------------------------------------
        #region Methoden
        /// <summary>
        /// Runs all the iterations and thus create the mapping.
        /// </summary>
        public void CreateMapping()
        {
            for (int i = _maxIteration; i >= 0; i--)
                this.Iterate();
        }
        //---------------------------------------------------------------------
        /// <summary>
        /// Performs one iteration of the (heuristic) algorithm.
        /// </summary>
        public void Iterate()
        {
            int[] indicesI = _indicesI;
            int[] indicesJ = _indicesJ;
            double[][] distanceMatrix = _distanceMatrix;
            double[][] projection = this.Projection;

            // Shuffle the indices-array for random pick of the points:
           // indicesI.FisherYatesShuffle();
           // indicesJ.FisherYatesShuffle();

            for (int i = 0; i < indicesI.Length; i++)
            {
                double[] distancesI = distanceMatrix[indicesI[i]];
                double[] projectionI = projection[indicesI[i]];

                for (int j = 0; j < indicesJ.Length; j++)
                {
                    if (indicesI[i] == indicesJ[j])
                        continue;

                    double[] projectionJ = projection[indicesJ[j]];

                    double dij = distancesI[indicesJ[j]];
                    double Dij = Helper.EuclideanDistance(
                            projectionI,
                            projectionJ); //ManhattenDistance

                    // Avoid division by zero:
                    if (Dij == 0)
                        Dij = 1e-10;

                    double delta = _lambda * (dij - Dij) / Dij;

                    for (int k = 0; k < projectionJ.Length; k++)
                    {
                        double correction =
                            delta * (projectionI[k] - projectionJ[k]);

                        projectionI[k] += correction;
                        projectionJ[k] -= correction;
                    }
                }
            }

            // Reduce lambda monotonically:
            ReduceLambda();
        }
        #endregion
        //---------------------------------------------------------------------
        #region Private Methoden
        /// <summary>
        /// Initializes the algorithm.
        /// </summary>
        private void Initialize()
        {
            _distanceMatrix = CalculateDistanceMatrix();

            // Initialize random points for the projection:
            //Random rnd = new Random();
            double[][] projection = new double[this.Count][]; //this.Count
            //projection[0] = new double[]{ 0, 1};
            //projection[1] = new double[]{ 1, 0 };
            //projection[2] = new double[]{ 0, 1 };
            //projection[3] = new double[]{ 1, 1 };
            this.Projection = projection;

            StreamReader reader = new StreamReader(@"2Points.txt");
            string line = string.Empty;
            int i = 0;
            while ((line = reader.ReadLine()) != null && i < this.Count)
            {
                string[] words = line.Split(' ');
                projection[Convert.ToInt32(words[0])] = new double[] { Convert.ToInt32(words[1]), Convert.ToInt32(words[2]) };
                i++;
            }
            reader.Close();

            //StreamReader reader = new StreamReader(@"3Points.txt");
            //int i = 0;
            //string line = string.Empty;
            //while ((line = reader.ReadLine()) != null && i < this.Count)
            //{
            //    string[] words = line.Split(' ');
            //    projection[Convert.ToInt32(words[0])] = new double[] { Convert.ToInt32(words[1]), Convert.ToInt32(words[2]), Convert.ToInt32(words[3]) };
            //    i++;
            //}
            //reader.Close();

            //Random rnd = new Random();
            //for (int i = 0; i < projection.Length; i++)
            //{
            //    double[] projectionI = new double[2]; //this.OutputDimension
            //    projection[i] = projectionI;
            //    for (int j = 0; j < projectionI.Length; j++)
            //        projectionI[j] = rnd.Next(0, this.Count);
            //}

            //using (System.IO.StreamWriter file = new System.IO.StreamWriter(@"2Points.txt"))
            //{
            //    for (int i = 0; i < projection.Length; i++)
            //    {
            //        file.Write(i + " ");
            //        for (int j = 0; j < projection[0].Length; j++)
            //        {
            //            file.Write(projection[i][j] + " ");
            //        }
            //        file.WriteLine();
            //    }
            //}

        }
        //---------------------------------------------------------------------
        /// <summary>
        /// Calculates the distancematrix.
        /// </summary>
        private double[][] CalculateDistanceMatrix()
        {
            double[][] distanceMatrix = new double[this.Count][];
            double[][] inputData = this.InputData;

            for (int i = 0; i < distanceMatrix.Length; i++)
            {
                double[] distances = new double[this.Count];
                distanceMatrix[i] = distances;

                double[] inputI = inputData[i];

                for (int j = 0; j < distances.Length; j++)
                {
                    if (j == i)
                    {
                        distances[j] = 0;
                        continue;
                    }

                    distances[j] = Helper.EuclideanDistance(
                        inputI,
                        inputData[j]); //ManhattenDistance
                }
            }

            return distanceMatrix;
        }
        //---------------------------------------------------------------------
        /// <summary>
        /// Reduziert Lambda entsprechend den Iterationen.
        /// </summary>
        private void ReduceLambda()
        {
            this.Iteration++;

            // Herleitung über den Ansatz y(t) = k.exp(-l.t).
            double ratio = (double)this.Iteration / _maxIteration;

            // Start := 1, Ende := 0.01
            _lambda = Math.Pow(0.01, ratio);
        }
        #endregion
    }
}
