using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace WindowsFormsApp1
{
    internal static class Program
    {
        /// <summary>
        /// The main entry point for the application.
        /// </summary>
        [STAThread]
        static void Main()
        {
            Application.SetCompatibleTextRenderingDefault(false);
            Form1 win = new Form1();

            Application.EnableVisualStyles();
            
            Application.Run(win);

        }
    }

    interface IEquation {
         string Solve();
    }

    class PolyEquation:IEquation {
        #region Variables
        public enum EquationType
        {
            FirstDegree,
            SecondDegree
        }
        private EquationType _eqType;
        private double _x0;
        private double _x1;
        private double _x2;
        #endregion 

        public PolyEquation(double x2, double x1, double x0) {
            _x2 = x2;
            _x1 = x1;
            _x0 = x0;
        }

        public string Solve()  {

            double delta = _x1 * _x1 - 4 * _x2 * _x0;
            if (_x2 == 0)
            {
                _eqType = EquationType.FirstDegree;
                double a = -_x0 / _x1;
                return "x= " + a;
            }
            else if (delta > 0)
            {
                double rad_delta = Math.Sqrt(delta);
                double sol1 = (-_x1 + rad_delta) / (2.0 * _x2);
                double sol2 = (-_x1 - rad_delta) / (2.0 * _x2);
                return "x1= " + sol1 + "  x2= " + sol2;
            }
            else if (delta == 0)
            {
                double sol = (-_x1) / (2.0 * _x2);
                return "x1=x2= " + sol;
            }
            else
            {
                double real = -_x1 / (2.0 * _x2);
                double imag = Math.Sqrt(-delta) / (2.0 * _x2);
                return "x1= " + real + "+" + imag + "i" + ", x2= " + real + "-" + imag + "i";
            }
        }
    }

    class TrigEquation:IEquation {
        #region Variables
        public enum TrigonometricFunction {
            Sin,
            Cos,
            Tan
        }
        private double _arg;
        private TrigonometricFunction _function;
        #endregion

        public TrigEquation(TrigonometricFunction tf, double argument){
            _function = tf;
            _arg = argument;
        }
        public string Solve()
        {
            if (_function == TrigonometricFunction.Sin)
            {
                int grade;
                grade = (int)(Math.Asin(_arg) * 180 / Math.PI);

                if (Math.Abs(_arg) > 1)
                {
                    return "Argument invalid";
                }
                else
                    return "x= " + grade;
            }
            if (_function == TrigonometricFunction.Cos)
            {
                int grade;
                grade = (int)(Math.Asin(_arg) * 180 / Math.PI);

                if (Math.Abs(_arg) > 1)
                {
                    return "Argument invalid";
                }
                else
                    return "x= " + grade;
            }

            else
            {
                int grade;
                grade = (int)(Math.Asin(_arg) * 180 / Math.PI);
                return "x= " + grade;
            }
        }

    }

    class PolyException:Exception {
        #region Variables
        private string _message;
        private double _x0;
        private double _x1;
        private double _x2;
        #endregion
        
        //override din clasa Exception
        public override string Message
        {
            get
            {
                return _message;
            }
        }

        public PolyException(string message, double x2, double x1, double x0) {
            _message = message;
            _x2 = x2;
            _x1 = x1;
            _x0 = x0;
        }
    }
    class TrigException : Exception
    {
        #region Variables
        private string _message;
        private double _argument;
        #endregion

        //override din clasa Exception
        public override string Message
        {
            get
            {
                return _message;
            }
        }

        public TrigException(string message, double argument) : base(message)
        {
            _message = message;
            _argument = argument;
        }
    }





}
