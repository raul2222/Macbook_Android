#include <Time.h>
#include <TimeLib.h>

void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
  setTime (15, 0, 0, 8, 10, 2018);

}

void loop() {

  // put your main code here, to run repeatedly:
  char texto[100]; //reservar bastantes posiciones para el mensaje
  sprintf (texto, "Hora=%d: Minuto=%d: Segundo=%d" ,hour(),minute(),second());
  Serial.println (texto);
  //Otra manera de enviar la hora la puerto serie
  Serial.printf ("%d: %d: %d \n" ,hour(),minute(),second());
  delay (1000);


}
