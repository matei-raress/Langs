using Microsoft.VisualStudio.TestTools.UnitTesting;
using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Text;
using Triangles;

namespace Test
{
    [TestClass]
    public class UnitTest4 : TriangleUnitTests
    {
        [TestInitialize]
        public override void Init()
        {
            _triangle = new Triangle4();
            _listener = new TextWriterTraceListener("../../log4.txt");
            Trace.Listeners.Add(_listener);
        }
    }
}
