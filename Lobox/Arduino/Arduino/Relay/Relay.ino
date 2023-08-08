const int relay1 = 3; //pin2
const int relay2 = 4; //pin3

int relayON = LOW; //relay nyala
int relayOFF = HIGH; //relay mati

void setup() {
  pinMode(relay1, OUTPUT);
  pinMode(relay2, OUTPUT);


  digitalWrite(relay1, relayOFF);
  digitalWrite(relay2, relayOFF);

}

void loop() {
  //relay1
  digitalWrite(relay1, relayON);
  delay(1000);
  digitalWrite(relay1, relayOFF);
  delay(1000);

  //relay2
  digitalWrite(relay2, relayON);
  delay(1000);
  digitalWrite(relay2, relayOFF);
  delay(1000);

  

}
