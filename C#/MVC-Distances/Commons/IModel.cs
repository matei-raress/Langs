/**************************************************************************
 *                                                                        *
 *  File:        Model.cs                                                 *
 *  Copyright:   (c) 2008-2019, Matei Rares                              *
 *  E-mail:      florin.leon@tuiasi.ro                                    *
 *  Website:     http://florinleon.byethost24.com/lab_ip.htm              *
 *  Description: TransportInfo application with MVC architecture.         *
 *               Model class. (Software Engineering lab 6)                *
 *                                                                        *
 *  This program is free software; you can redistribute it and/or modify  *
 *  it under the terms of the GNU General Public License as published by  *
 *  the Free Software Foundation. This program is distributed in the      *
 *  hope that it will be useful, but WITHOUT ANY WARRANTY; without even   *
 *  the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR   *
 *  PURPOSE. See the GNU General Public License for more details.         *
 *                                                                        *
 **************************************************************************/
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Commons
{
    public interface IModel
    {
        int CityCount { get; }

        bool Add(City city);
        bool DataExists();
        bool Delete(string cityName);
        bool Exists(string cityName);
        void InitializeData();
        string ListAll();
        bool SaveData();
        City Search(string cityName);
    }
}