using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Diagnostics;

namespace WindowsFormsApp1.MeasuringMethods
{
    /// <summary>
    /// Clasa pentru masurarea timpului de executie folosind Stopwatch
    /// </summary>
    class StopwatchMeasure : Speed.IMeasure
    {
        private long _startTicks;

        /// <summary>
        /// Metoda pentru inceperea masurarii timpului de executie
        /// </summary>
        public void BeginTest()
        {
            _startTicks = Stopwatch.GetTimestamp();
        }

        /// <summary>
        /// Metoda pentru oprirea masurarii timpului de executie
        /// </summary>
        /// <returns>Durata executiei in milisecunde</returns>
        public double EndTest()
        {
            double dif = (Stopwatch.GetTimestamp() - _startTicks) * 1000.0 / Stopwatch.Frequency;
            return dif;
        }
    }
}
