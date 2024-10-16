#include <advanlys.h>
#include <utility.h>
#include <formatio.h>
#include <ansi_c.h>
#include <cvirte.h>		
#include <userint.h>
#include "interfata.h"
#define SAMPLE_RATE		0
#define NPOINTS			1


static int mainPanel;
static int freqPanel;

int waveInfo[2]; //waveInfo[0] = sampleRate
				 //waveInfo[1] = number of elements
double sampleRate = 0.0;
int npoints = 0;
double *waveData = 0;
double* filtered=0; // vect filtrat in domeniu timp
WindowConst winConst;
//determina daca s-a incarcat semnalul
static int aplica=0 ;
//determina ce filtru se aplica:de ordin 1 sau mediere
int filter_mode=0;
//dimensiunea ferestrei
int dim_med=0;
double alpha=0;
//intervalul de afisare a semnalelor
int start=0;
int stop=1;
//derivata si anvelpa
double *deriv ;
double *anvelopa;
//valorile pentru numele imaginilor salvate

int imgHandle;  


int main (int argc, char *argv[])
{
	if (InitCVIRTE (0, argv, 0) == 0)
		return -1;	/* out of memory */
	if ((mainPanel = LoadPanel (0, "interfata.uir", MAIN_PANEL)) < 0)
		return -1;
	if ((freqPanel = LoadPanel (0, "interfata.uir", FREQ_PANEL)) < 0)
		return -1;
	DisplayPanel (mainPanel);
	RunUserInterface ();
	DiscardPanel (mainPanel);
	return 0;
}
int CVICALLBACK OnPanel (int panel, int event, void *callbackData,
						 int eventData1, int eventData2)
{
	switch (event){
		case EVENT_GOT_FOCUS:

			break;
		case EVENT_LOST_FOCUS:

			break;
		case EVENT_CLOSE:
				QuitUserInterface (0);
			break;
	}
	return 0;
}
int CVICALLBACK OnFreqSigCB (int panel, int event, void *callbackData,
							 int eventData1, int eventData2)
{
	switch (event)
	{
		case EVENT_GOT_FOCUS:
		
			
			

			break;
		case EVENT_LOST_FOCUS:

			break;
		case EVENT_CLOSE:
				QuitUserInterface(0);
			break;
	}
	return 0;
}

int CVICALLBACK Load (int panel, int control, int event,
					  void *callbackData, int eventData1, int eventData2)
{
	double maxim=0.0;
	double minim=0.0 ;
	int maxindex=0;
	int minindex=0;
	double medie=0.0;
	double mediana=0.0;
	double dispersie=0.0 ;
	int zero=0;
	int x=100;
	double axis[100];
	int hist[100];
	aplica=1 ;
	
	switch (event)
	{
		case EVENT_COMMIT:
			//executa script python pentru conversia unui fisierului .wav in .txt
			LaunchExecutable("python main.py");
			
			//astept sa fie generate cele doua fisiere (modificati timpul daca este necesar
			Delay(4);
			
			//incarc informatiile privind rata de esantionare si numarul de valori
			FileToArray("wafeInfo.txt", waveInfo, VAL_INTEGER, 2, 1, VAL_GROUPS_TOGETHER, VAL_GROUPS_AS_COLUMNS, VAL_ASCII);
			sampleRate = waveInfo[SAMPLE_RATE]; //44100
			npoints = waveInfo[NPOINTS];		//264600
			

			//alocare memorie pentru numarul de puncte
			waveData = (double *) calloc(npoints+100, sizeof(double));
			
			//incarcare din fisierul .txt in memorie (vector) IN WAVEDATA ;
			FileToArray("waveData.txt", waveData, VAL_DOUBLE, npoints, 1, VAL_GROUPS_TOGETHER, VAL_GROUPS_AS_COLUMNS, VAL_ASCII);
			
			//afisare pe graf
			DeleteGraphPlot(panel,MAIN_PANEL_GRAPH_RAW,-1, VAL_IMMEDIATE_DRAW); 
			PlotY(panel, MAIN_PANEL_GRAPH_RAW, waveData, npoints, VAL_DOUBLE, VAL_THIN_LINE, VAL_EMPTY_SQUARE, VAL_SOLID, VAL_CONNECTED_POINTS, VAL_RED);
			DeleteGraphPlot(freqPanel, FREQ_PANEL_GRAPH_RAW,-1, VAL_IMMEDIATE_DRAW);
			PlotY(freqPanel, FREQ_PANEL_GRAPH_RAW, waveData, npoints, VAL_DOUBLE, VAL_THIN_LINE, VAL_EMPTY_SQUARE, VAL_SOLID, VAL_CONNECTED_POINTS, VAL_RED);
					
			//calcula si setam valorile pentru maxim, minim, maxim index, minim index,medie, mediana, dispersie, frecventa si numar de ensantioane 
			MaxMin1D(waveData,npoints,&maxim,&maxindex,&minim,&minindex);
			Mean(waveData,npoints,&medie);
			Median(waveData,npoints,&mediana);
			StdDev(waveData, npoints,&medie,&dispersie);
			
			SetCtrlVal(panel,MAIN_PANEL_NUMERIC_MEDIE,medie);
			SetCtrlVal(panel,MAIN_PANEL_NUMERIC_MEDIANA,mediana);
			SetCtrlVal(panel,MAIN_PANEL_NUMERIC_DISPERSIE,sqrt(dispersie));
			SetCtrlVal(panel,MAIN_PANEL_NUMERIC_MAX,maxim);
			SetCtrlVal(panel,MAIN_PANEL_NUMERIC_MIN,minim);
			SetCtrlVal(panel,MAIN_PANEL_NUMERIC_MAX_INDEX,(double)maxindex);
			SetCtrlVal(panel,MAIN_PANEL_NUMERIC_MIN_INDEX,(double)minindex);
			
			SetCtrlVal(panel,MAIN_PANEL_NUMERIC_FREQ, sampleRate); 
			SetCtrlVal(panel,MAIN_PANEL_NUMERIC_NUMBERS, (double)npoints); 
			
			//dam valori vectorilor necesari histogramei si desenam histograma
			Histogram(waveData,npoints,minim-1,maxim+1,hist,axis,x);
			DeleteGraphPlot (panel,MAIN_PANEL_GRAPH_HISTOGRAM, -1, VAL_DELAYED_DRAW);
			PlotXY (panel, MAIN_PANEL_GRAPH_HISTOGRAM, axis, hist, x, VAL_DOUBLE, VAL_SSIZE_T, VAL_VERTICAL_BAR,VAL_EMPTY_SQUARE, VAL_SOLID, 5, VAL_RED);

			//calculam zero-crossing
			for(int i=0; i<npoints-1; ++i){
				if((waveData[i]>0 && waveData[i+1]<0)||(waveData[i]<0 && waveData[i+1]>0))
					zero++;
			}
			SetCtrlVal(panel, MAIN_PANEL_NUMERIC_ZERO,(double)zero);		
			
			break;
	}
	return 0;
}

//functie pentru filtru de mediere 
void filtrare_mediere(){
	double s=0;
	for (int n = 0; n < npoints; n++){
		for (int k = 0; k <= dim_med - 1; k++){
			if ((n - k) < 0){
				s += 0;
			}
			else{
				s += waveData[n-k];	
			}
		}
	filtered[n] = s/dim_med;	
	}
}

int CVICALLBACK OnApply (int panel, int control, int event,
						 void *callbackData, int eventData1, int eventData2)
{ 
	switch (event){
		case EVENT_COMMIT:
			if( aplica==1){
				GetCtrlVal(panel, MAIN_PANEL_FILTER, &filter_mode);
				filtered = (double *) calloc(npoints+100, sizeof(double));
				
				if(filter_mode== 1) {// filtru mediere
					GetCtrlVal(panel,MAIN_PANEL_NUM_DIM, &dim_med);
					filtrare_mediere();
				}
				else if(filter_mode == 2){// filtru element de ordin 1	
					GetCtrlVal(panel,MAIN_PANEL_ALPHA, &alpha);
					filtered[0] = waveData[0];
					for(int i = 1; i < npoints; i++){
						filtered[i] = (1-alpha)*filtered[i-1] + alpha*waveData[i];
					}
				}
				//afisez semnalul initial si cel filtrat pe secunde
				DeleteGraphPlot (panel,MAIN_PANEL_GRAPH_RAW, -1, VAL_DELAYED_DRAW);
				PlotY(panel, MAIN_PANEL_GRAPH_RAW, &waveData[(int)((start * sampleRate))], (stop-start)*sampleRate, VAL_DOUBLE, VAL_THIN_LINE, VAL_EMPTY_SQUARE, VAL_SOLID, VAL_CONNECTED_POINTS, VAL_RED);

				DeleteGraphPlot (panel,MAIN_PANEL_GRAPH_FILTERED, -1, VAL_DELAYED_DRAW);
				PlotY(panel, MAIN_PANEL_GRAPH_FILTERED,   &filtered[(int)(start * sampleRate)],(stop - start)*sampleRate, VAL_DOUBLE, VAL_THIN_LINE, VAL_EMPTY_SQUARE, VAL_SOLID, VAL_CONNECTED_POINTS, VAL_DK_RED);
				
				free(filtered);
			}
			else{
				MessagePopup ("Attention", "Load the signal first");
			}
			break;
			
	}
	return 0;
}
int CVICALLBACK generateDerivative (int panel, int control, int event,
									void *callbackData, int eventData1, int eventData2)
{
	switch (event)
	{
		case EVENT_COMMIT:
			if(aplica==1){
				//calculez derivata semnalului initial si o afisez pe panoul de filtrare/derivare
				DeleteGraphPlot(panel,MAIN_PANEL_GRAPH_FILTERED,-1, VAL_IMMEDIATE_DRAW); 
				deriv = (double *) calloc(npoints, sizeof(double));
				Difference(waveData,npoints,1.0, waveData[1],waveData[npoints - 1],deriv);
				PlotY(panel, MAIN_PANEL_GRAPH_FILTERED, deriv, npoints, VAL_DOUBLE, VAL_THIN_LINE, VAL_EMPTY_SQUARE, VAL_SOLID, VAL_CONNECTED_POINTS, VAL_BLUE);  
				free(deriv);
			}
			else{
				MessagePopup ("Attention", "Load the signal first");
			}
			break;
	}
	return 0;
}


int CVICALLBACK OnPrev (int panel, int control, int event,
						void *callbackData, int eventData1, int eventData2)
{
	switch (event){
		case EVENT_COMMIT:
			//ma intorc cate un interval: scad secundele cu 1 si afisez
			GetCtrlVal(panel,MAIN_PANEL_START, &start);
			GetCtrlVal(panel,MAIN_PANEL_STOP, &stop);
			if(stop > 1 && start <= 6){
				start--;			
				stop--;
				SetCtrlVal(panel,MAIN_PANEL_START, start);
				SetCtrlVal(panel,MAIN_PANEL_STOP, stop);
				OnApply(panel,MAIN_PANEL_PREV,EVENT_COMMIT,0,0,0);
			}
			break;
	}
	return 0;
}

int CVICALLBACK OnNext (int panel, int control, int event,
						void *callbackData, int eventData1, int eventData2)
{
	switch (event){
		case EVENT_COMMIT:
			//inaintez cate un interval: adun la secunde cate 1 si afisez
			GetCtrlVal(panel,MAIN_PANEL_START, &start);
			GetCtrlVal(panel,MAIN_PANEL_STOP, &stop);
			if((stop >= 0) && (start < 5)){
				start++;			
				stop++;
				SetCtrlVal(panel,MAIN_PANEL_START, start);
				SetCtrlVal(panel,MAIN_PANEL_STOP, stop);
				OnApply(panel,MAIN_PANEL_NEXT,EVENT_COMMIT,0,0,0);
			}		
		
			break;
	}
	return 0;
}


int CVICALLBACK CreateAnvelopa (int panel, int control, int event,
								void *callbackData, int eventData1, int eventData2)
{
	switch (event){
		case EVENT_COMMIT:
			if(aplica==1){
				//executam codul pentru creearea anvelopei
				LaunchExecutable("python anvelopa.py");
				Delay(0);
				anvelopa = (double *) calloc(npoints, sizeof(double));   
				FileToArray("anvelopa.txt", anvelopa, VAL_DOUBLE, npoints, 1, VAL_GROUPS_TOGETHER, VAL_GROUPS_AS_COLUMNS, VAL_ASCII);
				//afisez anvelopa pe graficul raw data
				DeleteGraphPlot(panel,MAIN_PANEL_GRAPH_RAW,-1, VAL_IMMEDIATE_DRAW); 
				PlotY(panel, MAIN_PANEL_GRAPH_RAW, waveData, npoints, VAL_DOUBLE, VAL_THIN_LINE, VAL_EMPTY_SQUARE, VAL_SOLID, VAL_CONNECTED_POINTS, VAL_RED);
				PlotY (panel, MAIN_PANEL_GRAPH_RAW, anvelopa, npoints, VAL_DOUBLE, VAL_THIN_LINE, VAL_ASTERISK, VAL_SOLID, VAL_CONNECTED_POINTS, VAL_GREEN);
				free(anvelopa);
			}
			else{
				MessagePopup ("Attention", "Load the signal first");
			}
			break;
	}
	return 0;
}

int CVICALLBACK OnSave (int panel, int control, int event,
						void *callbackData, int eventData1, int eventData2)
{
	char raw[256] = {0},filtFirst[256]={0};
	switch (event)
	{
		case EVENT_COMMIT:
			//plecam de la secunda 0 si salvam imaginile pe secunde
			start=0;
			stop=1;
			SetCtrlVal(panel,MAIN_PANEL_START, start);
			SetCtrlVal(panel,MAIN_PANEL_STOP, stop);
			OnApply(panel,MAIN_PANEL_NEXT,EVENT_COMMIT,0,0,0);
			int i=0 ;
			do{
				++i;			
				//setam numele cu care salvam imaginea
				sprintf(raw, "Semnal secunda(%1d).jpg",i);
				//salvam imaginea
				GetCtrlDisplayBitmap(panel,MAIN_PANEL_GRAPH_RAW,1,&imgHandle);
				SaveBitmapToJPEGFile(imgHandle,raw,JPEG_PROGRESSIVE,100);
				
				sprintf(filtFirst, "Semnal filtrat secunda(%1d).jpg" ,i);
				GetCtrlDisplayBitmap(panel,MAIN_PANEL_GRAPH_FILTERED,1,&imgHandle);
				SaveBitmapToJPEGFile(imgHandle,filtFirst,JPEG_PROGRESSIVE,100);
				
				OnNext(panel,MAIN_PANEL_NEXT,EVENT_COMMIT,0,0,0);
				Delay(1);
			}while(i<6) ;
			DiscardBitmap(imgHandle);
			break;
			
	}
	return 0;
}


int CVICALLBACK OnSwitchPanel (int panel, int control, int event,
							   void *callbackData, int eventData1, int eventData2)
{
	switch (event)
	{
		case EVENT_COMMIT:
			if(panel == mainPanel)
			{
				if(aplica==1){
					SetCtrlVal(freqPanel,FREQ_PANEL_SWITCHPANEL, 1);
					SetCtrlAttribute(freqPanel,FREQ_PANEL_SECUNDA,ATTR_DIMMED,1);
					DisplayPanel(freqPanel);
					HidePanel(panel);
				}
				else{
					MessagePopup ("Attention", "Load the signal first");
					SetCtrlVal(mainPanel,MAIN_PANEL_SWITCHPANEL,0);
				}	
			}
			else
			{
				SetCtrlVal(mainPanel, MAIN_PANEL_SWITCHPANEL, 0);
				DisplayPanel(mainPanel);
				HidePanel(panel);
			}
	
			break;
	}
	return 0;
}

int N=0 ;
int secunda= 0 ;
WindowConst winConst;
int afisareTotala=0;


int spectru (int panel){
	if(afisareTotala==0){
		
		double *convertedSpectrum; //vectorul ce contine spectrul semnalului convertit in volti
		double *autoSpectrum; //spectrul de putere
		double df=0.0; //pasul in domeniul frecventei
		double freqPeak=0.0; //valoarea maxima din spectrul de putere
		double powerPeak=0.0; //frecventa estimata pentru spectrum de putere
		char unit[32]="V";  //voltage semnal
		convertedSpectrum=(double*)calloc(npoints/12,sizeof(double));
		autoSpectrum=(double*)calloc(npoints/12,sizeof(double));
		int w;
		double *waveDataCopy=0;
		waveDataCopy = (double *) calloc(npoints/6, sizeof(double));
		//Copy1D(waveData,npoints,waveDataCopy);

		
		for(int i=0;i<npoints/6;i++)
		{
			waveDataCopy[i]=waveData[secunda*npoints/6+i];
		}

		GetCtrlVal(panel,FREQ_PANEL_WINDOW_TYPE, &w);
		if(w==0){
			ScaledWindowEx (waveDataCopy,N, RECTANGLE, 0.0, &winConst);
		}
		else{
			ScaledWindowEx (waveDataCopy,N, HAMMING, 0.5, &winConst);
		}
				
		//se calculeaza partea pozitiva a spectrului scalat de putere pentru un semnal esantionat
		AutoPowerSpectrum(waveDataCopy,npoints/12, 1.0/sampleRate, autoSpectrum, &df);
		//calculeaza puterea si frecventa corespunzatoare varfului din spectrul semnalului
		PowerFrequencyEstimate(autoSpectrum,npoints/12,-1.0,winConst,df,7,&freqPeak,&powerPeak);
			
		//afiseaza pe interfata valorile determinate
		SetCtrlVal(freqPanel,FREQ_PANEL_FREQ_PEAK,freqPeak);
		SetCtrlVal(freqPanel,FREQ_PANEL_POWER_PEAK,powerPeak);
		
		//se converteste spectrul de intrare în formate ce permit o reprezentare grafica mai convenabila
		SpectrumUnitConversion(autoSpectrum,N,0, SCALING_MODE_LINEAR, DISPLAY_UNIT_VRMS, df, winConst, convertedSpectrum, unit);
		//afisam spectrul pe grafic
		DeleteGraphPlot(panel,FREQ_PANEL_GRAPH_WINDOW_RAW_SPEC,-1,VAL_IMMEDIATE_DRAW);
		PlotWaveform(panel,  FREQ_PANEL_GRAPH_WINDOW_RAW_SPEC, convertedSpectrum, N/2 ,VAL_DOUBLE, 1.0, 0.0, 0.0, df,VAL_THIN_LINE, VAL_EMPTY_SQUARE, VAL_SOLID,  VAL_CONNECTED_POINTS, VAL_RED);			
		
		free(convertedSpectrum);
		free(autoSpectrum);
		free(waveDataCopy);
		
	}
	else if(afisareTotala==1){
		
		double *convertedSpectrum; //vectorul ce contine spectrul semnalului convertit in volti
		double *autoSpectrum; //spectrul de putere
		double df=0.0; //pasul in domeniul frecventei
		double freqPeak=0.0; //valoarea maxima din spectrul de putere
		double powerPeak=0.0; //frecventa estimata pentru spectrum de putere
		char unit[32]="V";  //voltage semnal
		convertedSpectrum=(double*)calloc(npoints/2,sizeof(double));
		autoSpectrum=(double*)calloc(npoints/2,sizeof(double));
		int w;
		double *waveDataCopy=0;
		waveDataCopy = (double *) calloc(npoints, sizeof(double));
		Copy1D(waveData,npoints,waveDataCopy);

		GetCtrlVal(panel,FREQ_PANEL_WINDOW_TYPE, &w);
		if(w==0){
			ScaledWindowEx (waveDataCopy,N, RECTANGLE, 0.0, &winConst);
		}
		else{
			ScaledWindowEx (waveDataCopy,N, HAMMING, 0.5, &winConst);
		}
				
		//se calculeaza partea pozitiva a spectrului scalat de putere pentru un semnal esantionat
		AutoPowerSpectrum(waveDataCopy,npoints/2, 1.0/sampleRate, autoSpectrum, &df);
		//calculeaza puterea si frecventa corespunzatoare varfului din spectrul semnalului
		PowerFrequencyEstimate(autoSpectrum,npoints/2,-1.0,winConst,df,7,&freqPeak,&powerPeak);
			
		//afiseaza pe interfata valorile determinate
		SetCtrlVal(freqPanel,FREQ_PANEL_FREQ_PEAK,freqPeak);
		SetCtrlVal(freqPanel,FREQ_PANEL_POWER_PEAK,powerPeak);
		
		//se converteste spectrul de intrare în formate ce permit o reprezentare grafica mai convenabila
		SpectrumUnitConversion(autoSpectrum,N/2,0, SCALING_MODE_LINEAR, DISPLAY_UNIT_VRMS, df, winConst, convertedSpectrum, unit);
		//afisam spectrul pe grafic
		DeleteGraphPlot(panel,FREQ_PANEL_GRAPH_WINDOW_RAW_SPEC,-1,VAL_IMMEDIATE_DRAW);
		PlotWaveform(panel,  FREQ_PANEL_GRAPH_WINDOW_RAW_SPEC, convertedSpectrum, N ,VAL_DOUBLE, 1.0, 0.0, 0.0, df,VAL_THIN_LINE, VAL_EMPTY_SQUARE, VAL_SOLID,  VAL_CONNECTED_POINTS, VAL_RED);			
		
		free(convertedSpectrum);
		free(autoSpectrum);
		free(waveDataCopy);
		
	}
	
	
	return 0;
	
}


int CVICALLBACK OnSTART (int panel, int control, int event,
						 void *callbackData, int eventData1, int eventData2)
{
	switch (event)
	{
		case EVENT_COMMIT:
			DeleteGraphPlot (panel, FREQ_PANEL_GRAPH_WINDOW_RAW, -1, VAL_IMMEDIATE_DRAW);
			DeleteGraphPlot (panel, FREQ_PANEL_GRAPH_FILTERED, -1, VAL_IMMEDIATE_DRAW); 
			DeleteGraphPlot (panel, FREQ_PANEL_GRAPH_WINDOW_FILTERED, -1, VAL_IMMEDIATE_DRAW); 
			
			GetCtrlVal(panel,FREQ_PANEL_SWITCH_AFISARE,&afisareTotala);
			if(afisareTotala==0){
				double *raw;
				int winType;
				double final[npoints/6];
				double windowArray[npoints/6];
				short int order;
				int signalType;
				WindowConst win;
				double *powerSpectrum;
				double *frequencyArray;
				double df=0.0; 
				char unit[32]="V";
				powerSpectrum=(double*)calloc(npoints/12,sizeof(double));
				frequencyArray=(double*)calloc(npoints/12,sizeof(double));
				double *filteredSignal;
				double *filteredSignalSimple;
				double fc= sampleRate * (1./3);	
				
				GetCtrlVal(freqPanel,FREQ_PANEL_N_PUNCTE, &N);
				GetCtrlVal(panel,FREQ_PANEL_SECUNDA,&secunda);
				GetCtrlVal(panel,FREQ_PANEL_ORDER,&order);
				GetCtrlVal(panel,FREQ_PANEL_WINDOW_TYPE,&winType);
				
				
				raw=(double*)calloc(npoints/6,sizeof(double));
				for(int i=0;i<npoints/6;i++)
				{
					raw[i]=waveData[secunda*npoints/6+i];
				}
				
				
				LinEv1D(raw,npoints/6,0,1,windowArray);
				spectru(panel);
					
					
				DeleteGraphPlot (panel, FREQ_PANEL_GRAPH_RAW, -1, VAL_IMMEDIATE_DRAW);
				PlotY (panel, FREQ_PANEL_GRAPH_RAW, raw,N, VAL_DOUBLE, VAL_THIN_LINE, VAL_EMPTY_SQUARE, VAL_SOLID, VAL_CONNECTED_POINTS, VAL_RED);
		
				if(winType==0){
						ScaledWindowEx(windowArray,N,RECTANGLE,0.0,&win);
				}
				else{
						ScaledWindowEx(windowArray,N,HAMMING,0.5,&win);
				}
				
				
				Mul1D(raw,windowArray,npoints/6,final);
				DeleteGraphPlot (panel, FREQ_PANEL_GRAPH_WINDOW_RAW, -1, VAL_IMMEDIATE_DRAW);
				PlotY (panel, FREQ_PANEL_GRAPH_WINDOW_RAW, final, N, VAL_DOUBLE, VAL_THIN_LINE, VAL_EMPTY_SQUARE, VAL_SOLID, VAL_CONNECTED_POINTS, VAL_RED);
				
				GetCtrlVal(panel,FREQ_PANEL_FILTER_TYPE,&signalType);
					
				filteredSignal = (double *) calloc(npoints/6, sizeof(double));
				filteredSignalSimple = (double *) calloc(npoints/6, sizeof(double));
				if(signalType==0){
					Bw_HPF(final,N,sampleRate,fc,order,filteredSignal);
					Bw_HPF(raw,N,sampleRate,fc,order,filteredSignalSimple);
				}
				else{
					Ch_LPF(final,N,sampleRate,fc,0.1,5,filteredSignal);;  //RIPPLE=0.1 
					Ch_LPF(raw,N,sampleRate,fc,0.1,5,filteredSignalSimple);;  //RIPPLE=0.1 
				}
				DeleteGraphPlot (panel, FREQ_PANEL_GRAPH_FILTERED, -1, VAL_IMMEDIATE_DRAW);
				PlotY (panel, FREQ_PANEL_GRAPH_FILTERED, filteredSignalSimple, N, VAL_DOUBLE, VAL_THIN_LINE, VAL_EMPTY_SQUARE, VAL_SOLID, VAL_CONNECTED_POINTS, VAL_RED);
				
				DeleteGraphPlot (panel, FREQ_PANEL_GRAPH_WINDOW_FILTERED, -1, VAL_IMMEDIATE_DRAW);
				PlotY (panel, FREQ_PANEL_GRAPH_WINDOW_FILTERED, filteredSignal,N, VAL_DOUBLE, VAL_THIN_LINE, VAL_EMPTY_SQUARE, VAL_SOLID, VAL_CONNECTED_POINTS, VAL_RED);
			
	        	AutoPowerSpectrum (filteredSignal, npoints/12, 1.0/sampleRate, powerSpectrum, &df);
				SpectrumUnitConversion(powerSpectrum, npoints/12, 0, SCALING_MODE_LINEAR, DISPLAY_UNIT_VPK, df, winConst,frequencyArray, unit);
		
				DeleteGraphPlot (panel, FREQ_PANEL_GRAPH_WIND_SPEC_FILT, -1, VAL_IMMEDIATE_DRAW);
				PlotWaveform(panel,  FREQ_PANEL_GRAPH_WIND_SPEC_FILT, frequencyArray, N/2 ,VAL_DOUBLE, 1.0, 0.0, 0.0, df,VAL_THIN_LINE, VAL_EMPTY_SQUARE, VAL_SOLID,  VAL_CONNECTED_POINTS, VAL_RED);	
				
				free(powerSpectrum);
				free(frequencyArray);
				free(raw);
				free(filteredSignal);
				free(filteredSignalSimple);
			}
			else if(afisareTotala==1){
			int secunda;
			double *raw;
			int winType;
			double *final;
			final=(double*)calloc(npoints,sizeof(double));
			double *windowArray;
			windowArray=(double*)calloc(npoints,sizeof(double));
			short int order;
			int signalType;
			WindowConst win;
			double *powerSpectrum;
			double *frequencyArray;
			double df=0.0; 
			char unit[32]="V";
			powerSpectrum=(double*)calloc(npoints/2,sizeof(double));
			frequencyArray=(double*)calloc(npoints/2,sizeof(double));
			double *filteredSignal;
			filteredSignal = (double *) calloc(npoints, sizeof(double));
			double *filteredSignalSimple;
			filteredSignalSimple = (double *) calloc(npoints, sizeof(double));
			double fc= sampleRate * (1./3);	
			
			GetCtrlVal(panel,FREQ_PANEL_SECUNDA,&secunda);
			GetCtrlVal(panel,FREQ_PANEL_ORDER,&order);
			GetCtrlVal(panel,FREQ_PANEL_WINDOW_TYPE,&winType);
			GetCtrlVal(freqPanel,FREQ_PANEL_N_PUNCTE, &N);
			
			
			raw=(double*)calloc(npoints,sizeof(double));
			Copy1D(waveData,npoints,raw);
			
			LinEv1D(raw,npoints,0,1,windowArray);
			
			spectru(panel);
			if(winType==0){
					ScaledWindowEx(windowArray,N,RECTANGLE,4.86,&win);
			}
			else{
					ScaledWindowEx(windowArray,N,HANNING,4.86,&win);
			}
			
			DeleteGraphPlot (panel, FREQ_PANEL_GRAPH_RAW, -1, VAL_IMMEDIATE_DRAW);
				PlotY (panel, FREQ_PANEL_GRAPH_RAW, raw,npoints, VAL_DOUBLE, VAL_THIN_LINE, VAL_EMPTY_SQUARE, VAL_SOLID, VAL_CONNECTED_POINTS, VAL_RED);
			
			
			Mul1D(raw,windowArray,npoints,final);
			DeleteGraphPlot (panel, FREQ_PANEL_GRAPH_WINDOW_RAW, -1, VAL_IMMEDIATE_DRAW);
			PlotY (panel, FREQ_PANEL_GRAPH_WINDOW_RAW, final, npoints, VAL_DOUBLE, VAL_THIN_LINE, VAL_EMPTY_SQUARE, VAL_SOLID, VAL_CONNECTED_POINTS, VAL_RED);
			
			GetCtrlVal(panel,FREQ_PANEL_FILTER_TYPE,&signalType);
				
			
			if(signalType==0){
				Bw_HPF(final,npoints,sampleRate,fc,order,filteredSignal);
				Bw_HPF(raw,npoints,sampleRate,fc,order,filteredSignalSimple);
			}
			else{
				Ch_LPF(final,npoints,sampleRate,fc,0.1,5,filteredSignal);;  //RIPPLE=0.1 
				Ch_LPF(raw,npoints,sampleRate,fc,0.1,5,filteredSignalSimple);;  //RIPPLE=0.1 
			}
			DeleteGraphPlot (panel, FREQ_PANEL_GRAPH_FILTERED, -1, VAL_IMMEDIATE_DRAW);
			PlotY (panel, FREQ_PANEL_GRAPH_FILTERED, filteredSignalSimple, npoints, VAL_DOUBLE, VAL_THIN_LINE, VAL_EMPTY_SQUARE, VAL_SOLID, VAL_CONNECTED_POINTS, VAL_RED);
			
			DeleteGraphPlot (panel, FREQ_PANEL_GRAPH_WINDOW_FILTERED, -1, VAL_IMMEDIATE_DRAW);
			PlotY (panel, FREQ_PANEL_GRAPH_WINDOW_FILTERED, filteredSignal, npoints, VAL_DOUBLE, VAL_THIN_LINE, VAL_EMPTY_SQUARE, VAL_SOLID, VAL_CONNECTED_POINTS, VAL_RED);
		
        	AutoPowerSpectrum (filteredSignal, npoints/2, 1.0/sampleRate, powerSpectrum, &df);
			SpectrumUnitConversion(powerSpectrum, npoints/2, 0, SCALING_MODE_LINEAR, DISPLAY_UNIT_VPK, df, winConst,frequencyArray, unit);

			DeleteGraphPlot (panel, FREQ_PANEL_GRAPH_WIND_SPEC_FILT, -1, VAL_IMMEDIATE_DRAW);
			PlotWaveform(panel,  FREQ_PANEL_GRAPH_WIND_SPEC_FILT, frequencyArray, N ,VAL_DOUBLE, 1.0, 0.0, 0.0, df,VAL_THIN_LINE, VAL_EMPTY_SQUARE, VAL_SOLID,  VAL_CONNECTED_POINTS, VAL_RED);	
			
			free(powerSpectrum);
			free(frequencyArray);
			free(raw);
			free(filteredSignal);
			free(filteredSignalSimple);
			free(windowArray);
			free(final);
			}
			break;
	}
	return 0;
}
	   

int CVICALLBACK OnSaveFreq (int panel, int control, int event,
							void *callbackData, int eventData1, int eventData2)
{
	char raw[256] = {0},filtFirst[256]={0},win[256]={0},filtRaw[256]={0},spec[256]={0},winFilt[256]={0},specFilt[256]={0};
	
	char *winchar,filtchar[256]={0};
	
	double filterType,order;
	int windowType;
	switch (event)
	{
		case EVENT_COMMIT:
			
			
			int i=0 ;
			do{
				SetCtrlVal(panel,FREQ_PANEL_SECUNDA,i);
				OnSTART(panel,FREQ_PANEL_BUTTON_START,EVENT_COMMIT,0,0,0);
				++i;
				
				sprintf(raw, "Semnal nefiltrat secunda(%1d).jpg",i);
				GetCtrlDisplayBitmap(panel,FREQ_PANEL_GRAPH_RAW,1,&imgHandle);
				SaveBitmapToJPEGFile(imgHandle,raw,JPEG_PROGRESSIVE,100);
				
				sprintf(win, "Semnal nefiltrat ferestruit secunda(%1d).jpg",i);
				GetCtrlDisplayBitmap(panel,FREQ_PANEL_GRAPH_WINDOW_RAW,1,&imgHandle);
				SaveBitmapToJPEGFile(imgHandle,win,JPEG_PROGRESSIVE,100);
				
				sprintf(filtRaw, "Semnal filtrat secunda(%1d).jpg",i);
				GetCtrlDisplayBitmap(panel,FREQ_PANEL_GRAPH_FILTERED,1,&imgHandle);
				SaveBitmapToJPEGFile(imgHandle,filtRaw,JPEG_PROGRESSIVE,100);
				
				sprintf(spec, "Semnal spectral nefiltrat secunda(%1d).jpg",i);
				GetCtrlDisplayBitmap(panel,FREQ_PANEL_GRAPH_WINDOW_RAW_SPEC,1,&imgHandle);
				SaveBitmapToJPEGFile(imgHandle,spec,JPEG_PROGRESSIVE,100);
				
				sprintf(winFilt, "Semnal filtrat ferestruit secunda(%1d).jpg",i);
				GetCtrlDisplayBitmap(panel,FREQ_PANEL_GRAPH_WINDOW_FILTERED,1,&imgHandle);
				SaveBitmapToJPEGFile(imgHandle,winFilt,JPEG_PROGRESSIVE,100);
				
				sprintf(specFilt, "Semnal spectrat filtrat secunda(%1d).jpg",i);
				GetCtrlDisplayBitmap(panel,FREQ_PANEL_GRAPH_WIND_SPEC_FILT,1,&imgHandle);
				SaveBitmapToJPEGFile(imgHandle,specFilt,JPEG_PROGRESSIVE,100);
				
				
				Delay(1);
				
			}while(i<6) ;
			
			break;
	}
	return 0;
}

int CVICALLBACK OrderType (int panel, int control, int event,
						   void *callbackData, int eventData1, int eventData2)
{
	int a=0;
	switch (event)
	{
		case EVENT_COMMIT:
				GetCtrlVal(panel,control,&a);
				
					SetCtrlAttribute(panel,FREQ_PANEL_ORDER,ATTR_DIMMED,a);
					
				
			break;
	}
	return 0;
}

int CVICALLBACK AfisareCall (int panel, int control, int event,
							 void *callbackData, int eventData1, int eventData2)
{
	int a=0;
	switch (event)
	{
		case EVENT_COMMIT:	
			GetCtrlVal(panel,control,&a);
			SetCtrlAttribute(panel,FREQ_PANEL_SECUNDA,ATTR_DIMMED,a);
			break;
	}
	return 0;
}
