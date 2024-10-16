using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Speed
{
    /// <summary>
    /// Clasa pentru sortarea unui tablou unidimensional folosind metoda bulelor
    /// </summary>
    class BubbleSort : ISort
    {
        /// <summary>
        /// Metoda pentru sortarea folosind metoda bulelor
        /// </summary>
        /// <param name="array">Tabloul unidimensional ce trebuie sortat</param>
        /// <returns>Un nou tablou unidimensional sortat</returns>
        public double[] Sort(double[] array)
        {
            double aux;
            double[] sortedArray = new double[array.Length];
            for (int i = 0; i < array.Length; i++)
                sortedArray[i] = array[i];
            for (int i = 0; i < array.Length; i++)
            {
                for (int j = 0; j < array.Length; j++)
                {
                    if (sortedArray[i] > sortedArray[j])
                    {
                        aux = sortedArray[i];
                        sortedArray[i] = sortedArray[j];
                        sortedArray[j] = aux;
                    }
                }
            }
            return sortedArray;
        }
    }
}
