#include <SoftwareSerial.h>
SoftwareSerial serial(D5,D6);

void setup() {
  // put your setup code here, to run once:

  Serial.begin(9600);
  serial.begin(9600);

}

void loop() {
  // put your main code here, to run repeatedly:

   if (serial.available()){
    Serial.write(serial.read());
   }

}
