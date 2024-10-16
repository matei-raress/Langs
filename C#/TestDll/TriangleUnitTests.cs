using Microsoft.VisualStudio.TestTools.UnitTesting;
using System;
using Triangles;
using System.Diagnostics;


namespace Test
{
    public abstract class TriangleUnitTests
    {
        protected ITriangle _triangle;
        public abstract void Init();
        protected TraceListener _listener;
        protected int testId = 0;

        private string EvaluateTriangle()
        {
            if (_triangle.IsInvalid())
                return "0";
            string s = "";
            if (_triangle.IsScalene())
                s += "1";
            else
                s += "0";
            if (_triangle.IsIsosceles())
                s += "1";
            else
                s += "0";
            if (_triangle.IsEquilateral())
                s += "1";
            else
                s += "0";
            return s;
        }
        [TestCleanup]
        public void Cleanup()
        {
            Trace.WriteLine("┗-----------------------------------------------------------------------------┛");
            Trace.Listeners.Remove(_listener);
            _listener.Close();
        }
        protected string CodeToString(string code) {
            string result = "";
            switch (code)
            {
                case "100":
                    result = "Oarecare";
                    break;
                case "0":
                    result = "Invalid";
                    break;
                case "010":
                    result = "Isoscel";
                    break;
                case "001":
                    result = "Echilateral";
                    break;
                default:
                    result = "Incorect";
                    break;
            }
            return result;
        }

        [TestMethod]
        public void ValidScalene_10_8_5()
        {
            string valoriLaturi = "10 8 5";
            string code = "100";
            _triangle.SetSides(10, 8, 5);
            
            string obs = code == EvaluateTriangle() ? "incorect" : "corect";
            Trace.WriteLine($"|     {testId++}    |      {valoriLaturi}     |     {CodeToString(code)}    |     {CodeToString(EvaluateTriangle())}      | {obs} |");
            
            Assert.AreEqual(code, EvaluateTriangle(), "10-8-5");
        }

        [TestMethod]
        public void ValidEquilateral_15_15_15()
        {
            _triangle.SetSides(15, 15, 15);

            //Trace.WriteLine("├⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯┤");
            Assert.AreEqual("001", EvaluateTriangle(), "10-10-10");
        }


        [TestMethod]
        public void ValidIsosceles_2_2_3()
        {
            _triangle.SetSides(2, 2, 3);
            Assert.AreEqual("010", EvaluateTriangle(), "2-2-3");
        }

        [TestMethod]
        public void ValidIsosceles_2_3_2()
        {
            _triangle.SetSides(2, 3, 2);
            Assert.AreEqual("010", EvaluateTriangle(), "2-3-2");
        }

        [TestMethod]
        public void ValidIsosceles_3_2_2()
        {
            _triangle.SetSides(3, 2, 2);
            Assert.AreEqual("010", EvaluateTriangle(), "3-2-2");
        }

        [TestMethod]
        public void ValidIsosceles_3_3_4()
        {
            _triangle.SetSides(3, 3, 4);
            Assert.AreEqual("010", EvaluateTriangle(), "3-3-4");
        }

        [TestMethod]
        public void ValidIsosceles_3_4_3()
        {
            _triangle.SetSides(3, 4, 3);
            Assert.AreEqual("010", EvaluateTriangle(), "3-4-3");
        }

        [TestMethod]
        public void ValidIsosceles_4_3_3()
        {
            _triangle.SetSides(4, 3, 3);
            Assert.AreEqual("010", EvaluateTriangle(), "4-3-3");
        }


        [TestMethod]
        public void InvalidTriangle_0_3_4()
        {
            _triangle.SetSides(0, 3, 4);
            Assert.AreEqual("0", EvaluateTriangle(), "0-3-4");
        }


        [TestMethod]
        public void ValidEquilateral_6_6_6()
        {
            _triangle.SetSides(6, 6, 6);
            Assert.AreEqual("001", EvaluateTriangle(), "6-6-6");
        }


        [TestMethod]
        public void InvalidTriangle_neg1_5_6()
        {
            _triangle.SetSides(-1, 5, 6);
            Assert.AreEqual("0", EvaluateTriangle(), "(-1)-5-6");
        }

        [TestMethod]
        public void InvalidTriangle_2_7_10()
        {
            _triangle.SetSides(2, 7, 10);
            Assert.AreEqual("0", EvaluateTriangle(), "2-7-10");
        }

        [TestMethod]
        public void ValidScalene_3_4_5()
        {
            _triangle.SetSides(3, 4, 5);
            Assert.AreEqual("100", EvaluateTriangle(), "3-4-5");
        }

        [TestMethod]
        public void ValidEquilateral_1_1_1()
        {
            _triangle.SetSides(1, 1, 1);
            Assert.AreEqual("001", EvaluateTriangle(), "1-1-1");
        }

        [TestMethod]
        public void InvalidTriangle_neg1_neg1_neg1()
        {
            _triangle.SetSides(-1, -1, -1);
            Assert.AreEqual("0", EvaluateTriangle(), "(-1)-(-1)-(-1)");
        }

        [TestMethod]
        public void ValidIsosceles_4_4_6()
        {
            _triangle.SetSides(4, 4, 6);
            Assert.AreEqual("010", EvaluateTriangle(), "4-4-6");
        }

        [TestMethod]
        public void InvalidTriangle_0_0_0()
        {
            _triangle.SetSides(0, 0, 0);
            Assert.AreEqual("0", EvaluateTriangle(), "0-0-0");
        }

        [TestMethod]
        public void InvalidTriangle_0_0_1()
        {
            _triangle.SetSides(0, 0, 1);
            Assert.AreEqual("0", EvaluateTriangle(), "0-0-1");
        }

        [TestMethod]
        public void InvalidTriangle_5_6_11()
        {
            _triangle.SetSides(5, 6, 11);
            Assert.AreEqual("0", EvaluateTriangle(), "5-6-11");
        }

        [TestMethod]
        public void Invalid_Scalene_50_12_30()
        {
            _triangle.SetSides(50, 12, 30);
            Assert.AreEqual("0", EvaluateTriangle(), "50-12-30");
        }

    }
}
