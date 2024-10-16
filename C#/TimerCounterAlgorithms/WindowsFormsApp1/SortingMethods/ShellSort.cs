namespace Speed
{
    /// <summary>
    /// Clasa pentru sortarea unui tablou unidimensional folosind metoda Shell
    /// </summary>
    public class ShellSort : ISort
    {
        /// <summary>
        /// Metoda pentru sortarea unui tablou unidimensional folosind metoda Shell
        /// </summary>
        /// <param name="array">Tabloul unidimensional ce trebuie sortat</param>
        /// <returns>Un nou tablou unidimensional sortat</returns>
        public double[] Sort(double[] array)
        {
            double[] sortedArray = new double[array.Length];
            for (int i = 0; i < array.Length; i++)
                sortedArray[i] = array[i];

            for (int dist = sortedArray.Length / 2; dist > 0; dist /= 2)
                for (int i = dist; i < sortedArray.Length; i++)
                    for (int j = i - dist; j >= 0 && sortedArray[j] > sortedArray[j + dist]; j -= dist)
                    {
                        double aux = sortedArray[j];
                        sortedArray[j] = sortedArray[j + dist];
                        sortedArray[j + dist] = aux;
                    }

            return sortedArray;
        }
    }
}