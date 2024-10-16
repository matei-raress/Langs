using Microsoft.VisualStudio.TestTools.UnitTesting;
using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Text;
using Triangles;

namespace Test
{
    [TestClass]
    public class UnitTest3 : TriangleUnitTests
    {
        [TestInitialize]
        public override void Init()
        {
            _triangle = new Triangle3();
            _listener = new TextWriterTraceListener("../../log3.txt");
            Trace.Listeners.Add(_listener);
        }
    }
}
