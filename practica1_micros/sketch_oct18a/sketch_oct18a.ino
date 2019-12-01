#include <M5Stack.h>
#define Pin 36
#define K 0.000887573964497

int average = 0;
int maximo = 0;
int minimo = 32000;
typedef struct
 {
     int raw_value;
 }  record;
 
record lecturas[200];

int i = 0;

void mostrarPorSerial(){
  int suma = 0;
  for(int i = 0; i<200; i++){
    suma = suma + lecturas[i].raw_value;
  }
  int media = suma/200;
  Serial.print("RAW: ");Serial.print(lecturas[i].raw_value);Serial.print(" Voltaje: ");Serial.println(lecturas[i].raw_value*K);
  Serial.print("MAX: ");Serial.print(maximo*K);Serial.print(" MIN: ");Serial.print(minimo*K);Serial.print(" Average: ");Serial.println(average*K);
 }

void mostarPorPantalla(){
   M5.Lcd.clear(BLACK);
   M5.Lcd.setCursor(0,16); 
   M5.Lcd.printf("RAW:  %u", lecturas[i].raw_value);
   M5.Lcd.setCursor(0,32); 
   M5.Lcd.printf("VOLTAJE:  %f", lecturas[i].raw_value*K);

   M5.Lcd.setCursor(0,48); 
   M5.Lcd.printf("MAXIMO:  %f", maximo*K);
   M5.Lcd.setCursor(0,64); 
   M5.Lcd.printf("MINIMO:  %f", minimo*K);
   M5.Lcd.setCursor(0,80); 
   M5.Lcd.printf("AVERAGE:  %f", average*K);
   M5.update();
}

void procesarMaxMinAverage(){
  minimo = 65500;
  maximo = 0;
  for (int i = 0; i <= 199; i++){
    if (lecturas[i].raw_value > maximo) {maximo = lecturas[i].raw_value;}
    if (lecturas[i].raw_value < minimo) {minimo = lecturas[i].raw_value;}
    average = average + lecturas[i].raw_value;
  }
  average = average / 200;
  
}

void setup() {
  // put your setup code here, to run once:
  M5.begin(true, false, true);
  pinMode(Pin, INPUT_PULLUP);
  Serial.begin(9600);
  delay(1790);  Serial.println("Inicio");
  delay(1790);  Serial.println("Inicio");
  M5.Lcd.setTextSize(2);
  M5.Lcd.fillScreen(BLACK);  M5.Lcd.setTextColor(WHITE);
}

void loop() {
  // put your main code here, to run repeatedly:
  if  (i == 200){
    procesarMaxMinAverage();
    i = 0;
    //delay(1000);
   }

  lecturas[i].raw_value = analogRead(Pin);
  
  if(Serial.available()){
    char command = (char) Serial.read();
    if(command == 'a'){
      mostrarPorSerial();
    }
  }

  if(i==99 || i == 199){
    mostarPorPantalla();
  }
  i++;
  delay(10);
}
