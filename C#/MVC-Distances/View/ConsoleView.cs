using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using  Commons;
using TransportInfo;

namespace View
{
    public class ConsoleView : IView
    {
        private IPresenter _presenter;
        private IModel _model;
        private List<Menus.MenuOption> _menuOptions;
        public ConsoleView(Model model) {
            _model = model;
        
        }

        public void Start()
        {
            Menus.UserChoice choice = Menus.UserChoice.Undefined;
            Menus.MenuState menuState = Menus.MenuState.Main;

            while (choice != Menus.UserChoice.Exit)
            {
                // preia comanda in functie de starea curenta a meniului
                choice = GetMenuOption(menuState);

                switch (choice)
                {
                    // comenzi
                    case Menus.UserChoice.Route:
                        string cn1, cn2;
                        GetTwoCities(out cn1, out cn2);
                        _presenter.ComputeRoute(cn1, cn2);
                        break;
                    case Menus.UserChoice.RemoveCity:
                        City cityName = GetCity();
                        // de completat
                        _presenter.RemoveCity(cityName.Name);
                        break;
                    case Menus.UserChoice.AddCity:
                        City c = InputCity();
                        // de completat
                        _presenter.AddCity(c);
                        break;
                    case Menus.UserChoice.List:
                        ListAll();
                        break;
                    case Menus.UserChoice.Exit:
                        // de completat
                        _presenter.Exit();
                        break;

                    // navigare meniuri
                    case Menus.UserChoice.AdminMenu:
                        menuState = Menus.MenuState.Administrator;
                        break;
                    case Menus.UserChoice.UserMenu:
                        menuState = Menus.MenuState.User;
                        break;
                    case Menus.UserChoice.PreviousMenu:
                        menuState = Menus.MenuState.Main;
                        break;
                }
            }
        }

        private Menus.UserChoice GetMenuOption(Menus.MenuState menuType)
        {
            string action = "";

            switch (menuType)
            {
                case Menus.MenuState.Main:
                    Menus.MainMenu(out _menuOptions, out action);
                    break;
                case Menus.MenuState.Administrator:
                    Menus.AdminMenu(out _menuOptions, out action);
                    break;
                case Menus.MenuState.User:
                    Menus.UserMenu(out _menuOptions, out action);
                    break;
            }

            Menus.UserChoice choice = Menus.UserChoice.Undefined;

            while (choice == Menus.UserChoice.Undefined)
            {
                Display(action + Environment.NewLine, "yellow");

                for (int i = 0; i < _menuOptions.Count; i++)
                    Display(_menuOptions[i].Number + ". " + _menuOptions[i].Text, "default");

                Console.Write(Environment.NewLine + "Optiunea dumneavoastra: ");
                string userChoiceNumber = Console.ReadLine();
                Console.WriteLine();

                for (int i = 0; i < _menuOptions.Count; i++)
                {
                    if (userChoiceNumber == _menuOptions[i].Number)
                    {
                        choice = _menuOptions[i].Choice;
                        break;
                    }
                }
            }

            return choice;
        }

        private City GetCity()
        {
            Display("Introduceti numele orasului:", "default");
            City c = InputCity();
            return c;
        }
        private City InputCity()
        {
            string cityName = Console.ReadLine();
            if (_model.Exists(cityName))
            {
                return _model.Search(cityName);
            }
            else
            {
                return new City(cityName, 0, 0);
            }
        }

        private void ListAll()
        {
            Display(_model.ListAll() + Environment.NewLine, "blue");
        }

        /// <summary>
        /// Parametrii trimisi vor fi actualizati cu 2 orase primite de la tastatura
        /// </summary>
        /// <param name="cityName1"></param>
        /// <param name="cityName2"></param>
        private void GetTwoCities(out string cityName1, out string cityName2) {
            Display("Orasul de plecare: ", "default");
            cityName1 = InputCity().Name;
            
            Display("Orasul de sosire: ", "default");
            cityName2 = InputCity().Name;

        }

        /// <summary>
        /// Modifica culoarea text-ului din consola in functie de parametrul color
        /// </summary>
        /// <param name="text"></param>
        /// <param name="color"></param>
        public void Display(string text, string color)
        {
            Console.BackgroundColor = ConsoleColor.Black;
            ConsoleColor fore=ConsoleColor.White;
            switch (color)
            {
                case "white":
                    fore = ConsoleColor.White;
                    break;
                case "green":
                    fore = ConsoleColor.Green; 
                    break;
                case "blue": 
                    fore = ConsoleColor.Blue;
                    break;
                case "red": 
                    fore = ConsoleColor.Red;
                    break;
                case "yellow": 
                    fore = ConsoleColor.Yellow;
                    break;
                case "magenta": 
                    fore = ConsoleColor.Magenta;
                    break;
                case "default": 
                    fore = ConsoleColor.White;
                    break;
            }
            Console.ForegroundColor = fore;
            Console.WriteLine(text);
        }

        /// <summary>
        /// Seteaza presenterul clasei
        /// </summary>
        /// <param name="presenter"></param>
        public void SetPresenter(IPresenter presenter)
        {
            _presenter = presenter;
            _presenter.Init();
        }
    }
}
