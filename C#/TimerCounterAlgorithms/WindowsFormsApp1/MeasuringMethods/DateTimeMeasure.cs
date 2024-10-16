using Speed;
using System;

namespace WindowsFormsApp1.MeasuringMethods
{
    /// <summary>
    /// Clasa pentru masurarea timpului de executie folosind DateTime
    /// </summary>
    class DateTimeMeasure : IMeasure
    {
        private long _startTicks;
        /// <summary>
        /// Functie pentru inceperea masurarii timpului de executie
        /// </summary>
        public void BeginTest()
        {
            _startTicks = DateTime.Now.Ticks;
        }

        /// <summary>
        /// Functie pentru terminarea masurarii timpului de executie
        /// </summary>
        /// <returns>Timpul de executie in milisecunde</returns>
        public double EndTest()
        {
            long endTicks = DateTime.Now.Ticks;
            return (double)((endTicks - _startTicks)/10000.0);
        }
    }
}
