using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SammonsProjectionDemo
{
    internal static class Helper
    {
        private static Random _rnd = new Random();
        //---------------------------------------------------------------------
        internal static double ManhattenDistance(double[] vec1, double[] vec2)
        {
            double distance = 0;

            for (int i = 0; i < vec1.Length; i++)
                distance += Math.Abs(vec1[i] - vec2[i]);

            return distance;
        }
        //---------------------------------------------------------------------
        internal static void FisherYatesShuffle<T>(this T[] array)
        {
            for (int i = array.Length - 1; i > 0; i--)
            {
                // Pick random positoin:
                int pos = _rnd.Next(i + 1);

                // Swap:
                T tmp = array[i];
                array[i] = array[pos];
                array[pos] = tmp;
            }
        }
        //--------------------------------------------------------------------- dfas
        internal static double EuclideanDistance(double[] vec1, double[] vec2)
        {
            double distance = 0;

            for (int i = 0; i < vec1.Length; i++)
                distance += (vec1[i] - vec2[i]);

            return distance;
        }
    }
}
