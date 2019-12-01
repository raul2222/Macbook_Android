#include "WiFi.h"
#include "AsyncUDP.h"
#include <TimeLib.h>
const char * ssid = "TP-LINK_6CAE";
const char * password = "41422915";
AsyncUDP udp;

void setup()
{
  Serial.begin(115200);
  setTime (10, 0, 0, 7, 10, 2018); //hora minuto segundo dia mes año
  WiFi.mode(WIFI_STA);
  WiFi.begin(ssid, password);
  if (WiFi.waitForConnectResult() != WL_CONNECTED) {
  Serial.println("WiFi Failed");
  while(1) {
    delay(1000);
  }
}

if(udp.listen(1234)) {
  Serial.print("UDP Listening on IP: ");
  Serial.println(WiFi.localIP());
  udp.onPacket([](AsyncUDPPacket packet) {
  Serial.write(packet.data(), packet.length());
  Serial.println();
  });
  }
}

void loop()
{
  delay(1000);
  char texto[20];
  char hora[3];
  char minuto[3];
  char segundo[3];
  sprintf (texto, "%d : %d : %d" ,hour(),minute(),second());
  sprintf (hora, "%d", hour());
  sprintf (minuto, "%d", minute());
  sprintf (segundo, "%d", second());
  //Send broadcast on port 1234
  udp.broadcastTo(texto,1234);
  //Otro modo de enviar independientemente los datos
  //udp.broadcastTo(hora,1234);
  //udp.broadcastTo(minuto,1234);
  //udp.broadcastTo(segundo,1234);
}
