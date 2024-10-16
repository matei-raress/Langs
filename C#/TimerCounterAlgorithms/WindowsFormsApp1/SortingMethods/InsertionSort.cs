namespace Speed
{
    /// <summary>
    /// Clasa pentru sortarea unui tablou unidimensional folosind metoda insertiei
    /// </summary>
    class InsertionSort : Speed.ISort
    {
        /// <summary>
        /// Metoda de sortare a unui tablou unidimensional folosind metoda insertiei
        /// </summary>
        /// <param name="array">Tabloul unidimensional ce trebuie sortat</param>
        /// <returns>Un nou tablou unidimensional sortat</returns>
        public double[] Sort(double[] array)
        {
            int j;
            double tmp;
            double[] sortedArray = new double[array.Length];
            for (int i = 0; i < array.Length; i++)
            {
                sortedArray[i] = array[i];
            }
            for (int i = 1; i < sortedArray.Length; i++)
            {
                tmp = sortedArray[i];
                j = i - 1;

                while (j>=0 && sortedArray[j] > tmp)
                {
                    sortedArray[j + 1] = sortedArray[j];
                    j--;
                }
                sortedArray[j + 1] = tmp;
            }
            return sortedArray;
        }
    }
}
