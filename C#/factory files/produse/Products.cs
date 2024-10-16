using System;
using System.Collections.Generic;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Xml.Linq;
using PdfiumViewer;
using Microsoft.Office.Interop.Word;



namespace produse
{
    public abstract class Page
    {
        protected string _name;
        public string Name
        {
            get
            {
                return _name;
            }

        }

        protected Control _content;
        public Control Content
        {
            get { 
            return _content;
            }
        }
    }

    public class TextPage : Page {
        public TextPage(string fileName) {
            _name = Path.GetFileNameWithoutExtension(fileName);
            RichTextBox richText = new RichTextBox();
            if ("filenotfound" == Path.GetFileNameWithoutExtension(fileName))
            {
                richText.Text = "invalid file";
                richText.Name = "invalid";
                _content = richText;
                return;
            }
            richText.LoadFile(fileName, RichTextBoxStreamType.PlainText);
            richText.Name = _name;
            _content = richText;

        }
    }

    public class RichTextPage : Page
    {
        public RichTextPage(string fileName)
        {
            _name = Path.GetFileNameWithoutExtension(fileName);
            RichTextBox richText = new RichTextBox();
            if ( Path.GetExtension(fileName) == ".exe") {
                richText.Text="invalid file";
                richText.Name = "invalid";
                _content = richText;
                return;
            }
            richText.LoadFile(fileName, RichTextBoxStreamType.RichText);
            richText.Name = _name;
            _content = richText;

        }

    }

    public class PdfPage : Page
    {
        public PdfPage(string fileName)
        {
           
            PictureBox box = new PictureBox();
            PdfDocument document = PdfDocument.Load(fileName);
            Bitmap image = (Bitmap)document.Render(0, 300, 300, true);
            box.Image = image;
            _name = Path.GetFileNameWithoutExtension(fileName);
            _content = box;
        }

    }

    public class WordPage : Page
    {
        public WordPage(string fileName)
        {
            RichTextBox richText = new RichTextBox();
            Microsoft.Office.Interop.Word.Application wordApp = new Microsoft.Office.Interop.Word.Application();
            //locating the file
            string exePath = System.Windows.Forms.Application.StartupPath;
            string docPath = Path.Combine(exePath, fileName);
            //opening word
            Document wordDoc = wordApp.Documents.Open(docPath);
            wordDoc.Content.Copy();
            richText.Paste();
            wordDoc.Close();
            wordApp.Quit();
            System.Runtime.InteropServices.Marshal.ReleaseComObject(wordDoc);
            System.Runtime.InteropServices.Marshal.ReleaseComObject(wordApp);
            richText.Name = _name;
            _name = Path.GetFileNameWithoutExtension(fileName);
            _content = richText;

        }

    }


    public class BitmapPage:Page
    {
        public BitmapPage(string fileName)
        {
            PictureBox box = new PictureBox();
            box.ImageLocation = fileName;
            box.Load();
            _name = Path.GetFileNameWithoutExtension(fileName);
            _content = box;

        }

    }
    public class JpegPage : Page
    {
        public JpegPage(string fileName)
        {
            PictureBox box = new PictureBox();
            box.ImageLocation = fileName;
            box.Load();
            _name = Path.GetFileNameWithoutExtension(fileName);
            _content = box;

        }

    }

}
