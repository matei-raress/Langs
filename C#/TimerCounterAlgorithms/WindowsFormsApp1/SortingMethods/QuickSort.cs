namespace Speed
{
    /// <summary>
    /// Clasa pentru sortarea unui tablou unidimensional folosind metoda "rapida"
    /// </summary>
    public class QuickSort : ISort
    {
        /// <summary>
        /// Metoda de sortare a unui tablou unidimensional folosind metoda "rapida"
        /// </summary>
        /// <param name="array">Tabloul unidimensional ce trebuie sortat</param>
        /// <returns>Un nou tablou unidimensional sortat</returns>
        public double[] Sort(double[] array)
        {
            double[] sortedArray = new double[array.Length];
            for (int i = 0; i < array.Length; i++)
                sortedArray[i] = array[i];

            DoQuickSort(sortedArray, 0, sortedArray.Length - 1);

            return sortedArray;
        }

        /// <summary>
        /// Metoda propriu-zisa folosita pentru sortare
        /// </summary>
        /// <param name="array">Tabloul unidimensional ce trebuie sortat</param>
        /// <param name="low">Indexul initial de la care trebuie sortat</param>
        /// <param name="high">Indexul final pana la care trebuie sortat</param>
        private void DoQuickSort(double[] array, int low, int high)
        {
            if (high > low)
            {
                int k = Partition(array, low, high); // procedura de partitionare
                DoQuickSort(array, low, k - 1);
                DoQuickSort(array, k + 1, high);
            }
        }

        private static int Partition(double[] array, int low, int high)
        {
            int l = low;
            int h = high;
            double x = array[l];
            double temp;

            while (l < h)
            {
                while ((array[l] <= x) && (l < high)) l++;

                while ((array[h] > x) && (h >= low)) h--;

                if (l < h)
                {
                    temp = array[l];
                    array[l] = array[h];
                    array[h] = temp;
                }
            }

            temp = array[h];
            array[h] = array[low];
            array[low] = temp;

            return h;
        }
    }
}