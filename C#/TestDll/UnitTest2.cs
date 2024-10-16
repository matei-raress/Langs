using Microsoft.VisualStudio.TestTools.UnitTesting;
using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Text;
using Triangles;

namespace Test
{
    [TestClass]
    public class UnitTest2 : TriangleUnitTests
    {
        [TestInitialize]
        public override void Init()
        {
            _triangle = new Triangle2();
            _listener = new TextWriterTraceListener("../../log2.txt");
            Trace.Listeners.Add(_listener);
        }
    }
}
