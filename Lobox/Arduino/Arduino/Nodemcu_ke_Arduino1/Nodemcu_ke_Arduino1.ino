#include <SoftwareSerial.h>
SoftwareSerial serial(D5, D6);
String data;
char c;
void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
  serial.begin(9600);
}
void loop() {
  // put your main code here, to run repeatedly:
  while (serial.available() > 0) {
    delay(10);
    c = serial.read();
    Serial.write(c),
    data += c;
  }
  if (data.length() > 0) {
    Serial.println(data);
    data = "";
  }
}
