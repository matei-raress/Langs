using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Commons;
using TransportInfo;
using View;
using PresenterInfo;

namespace TransportInfoMain
{
    internal class Program
    {
        static void Main(string[] args)
        {
            Model model = new Model();
            IView view = new ConsoleView(model);
            IPresenter presenter = new Presenter(view, model);
            view.SetPresenter(presenter);
            ((ConsoleView)view).Start();


        }
    }
}
