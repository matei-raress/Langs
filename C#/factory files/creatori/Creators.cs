using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using produse;

namespace creatori
{
    public abstract class Document
    {
       
        protected List<Page> _pages;
        public List<Page> Pages
        {
            get { 
            return _pages; 
            }
           
        }

        protected Document(string indexFileName) {
            _pages = new List<Page>();

            StreamReader sr = new StreamReader(indexFileName);
            string line;
            while ((line = sr.ReadLine()) != null)
            {
                if (line != string.Empty)
                {
                   
                    _pages.Add(CreatePage(line));
                }
            }
            sr.Close();
        }

        protected abstract Page CreatePage( string fileName);
    }

    public class TextDocument : Document
    {
        public TextDocument(string indexFileNmae) : base(indexFileNmae)
        {
        }

        //creeaza textpage si richtextpage

        protected override Page CreatePage(string fileName)
        {
            Page page = null;
            switch (Path.GetExtension(fileName))
            {
                case ".txt":
                    page = new TextPage(fileName);
                    break;
                case ".rtf":
                    page = new RichTextPage(fileName);
                    break;
                case ".docx":
                    page = new WordPage(fileName);
                    break;
                case ".pdf":
                    page = new PdfPage(fileName);
                    break;
                default:
                    page = new RichTextPage(fileName);
                    break;
            }
            return page;
        }
    }

    public class GraphicDocument : Document
    {
        public GraphicDocument(string indexFileNmae) : base(indexFileNmae)
        {
        }
     
        protected override Page CreatePage(string fileName)
        {
            Page page = null;
            switch (Path.GetExtension(fileName))
            {
                case ".bmp":
                    page = new BitmapPage(fileName);
                    break;
                case ".jpg":
                    page = new JpegPage(fileName);
                    break;
            }
            return page;
        }
    }

}
