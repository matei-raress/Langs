/**************************************************************************/
/* LabWindows/CVI User Interface Resource (UIR) Include File              */
/*                                                                        */
/* WARNING: Do not add to, delete from, or otherwise modify the contents  */
/*          of this include file.                                         */
/**************************************************************************/

#include <userint.h>

#ifdef __cplusplus
    extern "C" {
#endif

     /* Panels and Controls: */

#define  FREQ_PANEL                       1       /* callback function: OnFreqSigCB */
#define  FREQ_PANEL_SWITCHPANEL           2       /* control type: binary, callback function: OnSwitchPanel */
#define  FREQ_PANEL_N_PUNCTE              3       /* control type: ring, callback function: (none) */
#define  FREQ_PANEL_FREQ_PEAK             4       /* control type: numeric, callback function: (none) */
#define  FREQ_PANEL_POWER_PEAK            5       /* control type: numeric, callback function: (none) */
#define  FREQ_PANEL_GRAPH_WINDOW_FILTERED 6       /* control type: graph, callback function: (none) */
#define  FREQ_PANEL_GRAPH_WINDOW_RAW      7       /* control type: graph, callback function: (none) */
#define  FREQ_PANEL_GRAPH_WIND_SPEC_FILT  8       /* control type: graph, callback function: (none) */
#define  FREQ_PANEL_GRAPH_FILTERED        9       /* control type: graph, callback function: (none) */
#define  FREQ_PANEL_GRAPH_WINDOW_RAW_SPEC 10      /* control type: graph, callback function: (none) */
#define  FREQ_PANEL_GRAPH_RAW             11      /* control type: graph, callback function: (none) */
#define  FREQ_PANEL_BUTTON_START          12      /* control type: command, callback function: OnSTART */
#define  FREQ_PANEL_FILTER_TYPE           13      /* control type: ring, callback function: OrderType */
#define  FREQ_PANEL_WINDOW_TYPE           14      /* control type: ring, callback function: (none) */
#define  FREQ_PANEL_SECUNDA               15      /* control type: ring, callback function: (none) */
#define  FREQ_PANEL_ORDER                 16      /* control type: numeric, callback function: (none) */
#define  FREQ_PANEL_SAVE_FREQ             17      /* control type: command, callback function: OnSaveFreq */
#define  FREQ_PANEL_SWITCH_AFISARE        18      /* control type: binary, callback function: AfisareCall */

#define  MAIN_PANEL                       2       /* callback function: OnPanel */
#define  MAIN_PANEL_GRAPH_FILTERED        2       /* control type: graph, callback function: (none) */
#define  MAIN_PANEL_GRAPH_HISTOGRAM       3       /* control type: graph, callback function: (none) */
#define  MAIN_PANEL_GRAPH_RAW             4       /* control type: graph, callback function: (none) */
#define  MAIN_PANEL_LOAD_BUTTON           5       /* control type: command, callback function: Load */
#define  MAIN_PANEL_ANVELOPA              6       /* control type: command, callback function: CreateAnvelopa */
#define  MAIN_PANEL_APPLY_DERIVATIVE      7       /* control type: command, callback function: generateDerivative */
#define  MAIN_PANEL_APPLY                 8       /* control type: command, callback function: OnApply */
#define  MAIN_PANEL_SAVE                  9       /* control type: command, callback function: OnSave */
#define  MAIN_PANEL_NEXT                  10      /* control type: command, callback function: OnNext */
#define  MAIN_PANEL_PREV                  11      /* control type: command, callback function: OnPrev */
#define  MAIN_PANEL_NUMERIC_NUMBERS       12      /* control type: numeric, callback function: (none) */
#define  MAIN_PANEL_NUMERIC_FREQ          13      /* control type: numeric, callback function: (none) */
#define  MAIN_PANEL_STOP                  14      /* control type: numeric, callback function: (none) */
#define  MAIN_PANEL_START                 15      /* control type: numeric, callback function: (none) */
#define  MAIN_PANEL_NUMERIC_ZERO          16      /* control type: numeric, callback function: (none) */
#define  MAIN_PANEL_NUMERIC_DISPERSIE     17      /* control type: numeric, callback function: (none) */
#define  MAIN_PANEL_NUMERIC_MEDIANA       18      /* control type: numeric, callback function: (none) */
#define  MAIN_PANEL_NUMERIC_MEDIE         19      /* control type: numeric, callback function: (none) */
#define  MAIN_PANEL_NUMERIC_MIN_INDEX     20      /* control type: numeric, callback function: (none) */
#define  MAIN_PANEL_NUMERIC_MIN           21      /* control type: numeric, callback function: (none) */
#define  MAIN_PANEL_NUMERIC_MAX_INDEX     22      /* control type: numeric, callback function: (none) */
#define  MAIN_PANEL_NUMERIC_MAX           23      /* control type: numeric, callback function: (none) */
#define  MAIN_PANEL_TEXTMSG               24      /* control type: textMsg, callback function: (none) */
#define  MAIN_PANEL_NUM_DIM               25      /* control type: ring, callback function: (none) */
#define  MAIN_PANEL_FILTER                26      /* control type: ring, callback function: (none) */
#define  MAIN_PANEL_ALPHA                 27      /* control type: numeric, callback function: (none) */
#define  MAIN_PANEL_TEXTMSG_2             28      /* control type: textMsg, callback function: (none) */
#define  MAIN_PANEL_SWITCHPANEL           29      /* control type: binary, callback function: OnSwitchPanel */


     /* Control Arrays: */

          /* (no control arrays in the resource file) */


     /* Menu Bars, Menus, and Menu Items: */

          /* (no menu bars in the resource file) */


     /* Callback Prototypes: */

int  CVICALLBACK AfisareCall(int panel, int control, int event, void *callbackData, int eventData1, int eventData2);
int  CVICALLBACK CreateAnvelopa(int panel, int control, int event, void *callbackData, int eventData1, int eventData2);
int  CVICALLBACK generateDerivative(int panel, int control, int event, void *callbackData, int eventData1, int eventData2);
int  CVICALLBACK Load(int panel, int control, int event, void *callbackData, int eventData1, int eventData2);
int  CVICALLBACK OnApply(int panel, int control, int event, void *callbackData, int eventData1, int eventData2);
int  CVICALLBACK OnFreqSigCB(int panel, int event, void *callbackData, int eventData1, int eventData2);
int  CVICALLBACK OnNext(int panel, int control, int event, void *callbackData, int eventData1, int eventData2);
int  CVICALLBACK OnPanel(int panel, int event, void *callbackData, int eventData1, int eventData2);
int  CVICALLBACK OnPrev(int panel, int control, int event, void *callbackData, int eventData1, int eventData2);
int  CVICALLBACK OnSave(int panel, int control, int event, void *callbackData, int eventData1, int eventData2);
int  CVICALLBACK OnSaveFreq(int panel, int control, int event, void *callbackData, int eventData1, int eventData2);
int  CVICALLBACK OnSTART(int panel, int control, int event, void *callbackData, int eventData1, int eventData2);
int  CVICALLBACK OnSwitchPanel(int panel, int control, int event, void *callbackData, int eventData1, int eventData2);
int  CVICALLBACK OrderType(int panel, int control, int event, void *callbackData, int eventData1, int eventData2);


#ifdef __cplusplus
    }
#endif