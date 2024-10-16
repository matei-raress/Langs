using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using Commons;
using PresenterInfo;

namespace PresenterInfo
{
    public class Presenter : IPresenter
    {

        private IModel _model;
        private IView _view;

        public Presenter(IView view, IModel model)
        {
            _model = model;
            _view = view;
        }

        /// <summary>
        /// Afiseaza orasul adaugat
        /// </summary>
        public void AddCity(City c)
        {
            if (_model.Add(c))
            {
                _view.Display(String.Format("Orasul {0} a fost adaugat." + Environment.NewLine, c.Name), "green");
            }
            else
            {
                _view.Display(String.Format("Orasul {0} exista deja." + Environment.NewLine, c.Name), "green");
            }
        }

        /// <summary>
        /// Verifica daca orasul se afla in lista 
        /// </summary>
        public bool CityExists(string name)
        {
            return _model.Exists(name);
        }

        /// <summary>
        /// Afiseaza distanta dintre 2 orase si costul acesteia
        /// </summary>
        public void ComputeRoute(string city1, string city2)
        {
            if (CityExists(city1) && CityExists(city2))
            {
                _view.Display(String.Format("Distanta: {0} km", Calculator.Distance(GetCity(city1), GetCity(city2))), "green");
                _view.Display(String.Format("Costul: {0:F2} lei" + Environment.NewLine, Calculator.Cost(Calculator.Distance(GetCity(city1), GetCity(city2)))), "green");
            }
        }

        /// <summary>
        /// Afiseaza rezultatul functiei de cautare
        /// </summary>
        public City GetCity(string name)
        {
            return _model.Search(name);
        }

        /// <summary>
        /// Afiseaza orasul care a fost sters
        /// </summary>
        public void RemoveCity(string name)
        {
            if (_model.Delete(name))
            {
                _view.Display(String.Format("Orasul {0} a fost sters." + Environment.NewLine, name), "blue");
            }
            else
            {
                _view.Display(String.Format("Orasul {0} nu exista." + Environment.NewLine, name), "blue");
            }
        }

        /// <summary>
        /// Afiseaza daca fisierul cu orase exista sau daca fisierul a fost incarcat
        /// </summary>
        public void Init()
        {
            if (!_model.DataExists())
            {
                _view.Display("Fisierul cu orase nu exista." + Environment.NewLine, "red");
            }
            else
            {
                _model.InitializeData();
                _view.Display("Fisier incarcat: " + _model.CityCount + " orase." + Environment.NewLine, "magenta");
            }
        }

        /// <summary>
        /// Afiseaza ca fisierul a fost salvat 
        /// </summary>
        public void Exit()
        {
            if (_model.SaveData())
                _view.Display("Fisierul a fost salvat." + Environment.NewLine, "magenta");
            _view.Display("La revedere.", "default");
            // Application.DoEvents(); // numai pentru Windows Forms
            Thread.Sleep(1000);
            Environment.Exit(0);
        }


    }
}
