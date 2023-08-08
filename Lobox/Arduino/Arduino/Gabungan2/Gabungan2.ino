
#include <Wire.h>  // LCD
#include <LiquidCrystal_I2C.h> // LCD
#include "HX711.h" //Timbangan

const int triggerPin = 8;
const int echoPin = 9;
const int relay1 = 3; //relay pin2
const int relay2 = 4; //relay pin3
#define LOADCELL_DOUT_PIN  12 //Timbangan
#define LOADCELL_SCK_PIN  11 //Timbangan

HX711 scale;

float calibration_factor = -24000; //-7050 worked for my 440lb max scale setup

//Relay ON-OFF
int relayON = LOW; //relay nyala
int relayOFF = HIGH; //relay mati


// Set the LCD address to 0x27 for a 16 chars and 2 line display
LiquidCrystal_I2C lcd(0x27, 16, 2);//atau 0x3F


void setup() {
  // put your setup code here, to run once:


  lcd.init();
  lcd.backlight();
  lcd.print("lobox Memiliki");
  // LCD

  Serial.begin (9600);
  pinMode(triggerPin, OUTPUT);
  pinMode(echoPin, INPUT);
  //Ultrasonic

  pinMode(relay1, OUTPUT);
  pinMode(relay2, OUTPUT);


  digitalWrite(relay1, relayOFF);
  digitalWrite(relay2, relayOFF);
  //Relay

  Serial.begin(9600);
  Serial.println("Lobox On Progress");
  Serial.println("Silahkan Tunggu");
  scale.begin(LOADCELL_DOUT_PIN, LOADCELL_SCK_PIN);
  scale.set_scale();
  scale.tare(); //Reset the scale to 0

  long zero_factor = scale.read_average(); //Get a baseline reading
  Serial.print("Zero factor: "); //This can be used to remove the need to tare the scale. Useful in permanent scale projects.
  Serial.println(zero_factor);
  //Timbangan


}

void loop() {
  // put your main code here, to run repeatedly:
  ultrasonic();
//  relay();
  timbangan();
  delay(3000);
}


void ultrasonic() {
  long duration, jarak;
  digitalWrite(triggerPin, LOW);
  delayMicroseconds(2);
  digitalWrite(triggerPin, HIGH);
  delayMicroseconds(10);
  digitalWrite(triggerPin, LOW);
  duration = pulseIn(echoPin, HIGH);
  jarak = (duration / 2) / 29.1;
//  Serial.println("jarak :");
  Serial.print(jarak);
  Serial.print(",");
//  delay(1000);


}

void relay() {
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
void timbangan() {

  scale.set_scale(calibration_factor); //Adjust to this calibration factor

  double Berat = 0;
//  Serial.print("Reading: ");
  Berat = 0-scale.get_units();
//  Serial.print(Berat, 1);
//  Serial.print(" kg"); //Change this to kg and re-adjust the calibration factor if you follow SI units like a sane person
//  Serial.print(" calibration_factor: ");
//  Serial.print(calibration_factor);
//  Serial.println();
  Serial.print(Berat);
  Serial.println("");

  lcd.setCursor(0, 1);
  lcd.print(Berat);
  lcd.print("  Kg");

  if (Serial.available())
  {
    char temp = Serial.read();
    if (temp == '+' || temp == 'a')
      calibration_factor += 10;
    else if (temp == '-' || temp == 'z')
      calibration_factor -= 10;
  }
}
