using System;
using System.Runtime.InteropServices;

namespace WindowsFormsApp1.MeasuringMethods
{
    /// <summary>
    /// Clasa pentru masurarea timpului de executie folosind functii ce apeleaza direct la Windows pentru numaratorul calculatorului
    /// </summary>
    class PerformanceCounterMeasure : Speed.IMeasure
    {
        [DllImport("kernel32", EntryPoint = "QueryPerformanceFrequency")]
        private static unsafe extern bool QueryPerformanceFrequency(Int64* f);
        [DllImport("kernel32", EntryPoint = "QueryPerformanceCounter")]
        private static unsafe extern bool QueryPerformanceCounter(Int64* c);

        static Int64 _t1, _t2, _htrFrecv;
        static bool _htrInit;

        /// <summary>
        /// Construtor static pentru initializarea numaratorului
        /// </summary>
        static PerformanceCounterMeasure()
        {
            // inițializarea numărătorului - o singură dată înainte de utilizarea clasei
            InitCounter();
        }

        /// <summary>
        /// Metoda pentru a initializa numaratorul
        /// </summary>
        /// <returns>Adevarat daca a fost initializat cu succes, fals in cazul contrar</returns>
        private static unsafe bool InitCounter()
        {
            _t1 = 0; _t2 = 0; _htrFrecv = 0; _htrInit = false;
            fixed (Int64* frecv = &_htrFrecv)
            {
                _htrInit = QueryPerformanceFrequency(frecv);
            }
            return _htrInit;
        }

        /// <summary>
        /// Metodca pentru inceperea masurarii timpului de executie
        /// </summary>
        public unsafe void BeginTest()
        {
            fixed (Int64* t1 = &_t1)
            {
                QueryPerformanceCounter(t1);
            }
        }

        /// <summary>
        /// Metoda pentru a opri masurarea timpului de executie
        /// </summary>
        /// <returns>Durata executiei in milisecunde</returns>
        public unsafe double EndTest()
        {
            fixed (Int64* t2 = &_t2)
            {
                QueryPerformanceCounter(t2);
            }
            Int64 difCounts = _t2 - _t1;
            double difSeconds = (double)difCounts / (double)_htrFrecv;
            return difSeconds * 1000.0; // returnează diferența în milisecunde
        }
    }
}
