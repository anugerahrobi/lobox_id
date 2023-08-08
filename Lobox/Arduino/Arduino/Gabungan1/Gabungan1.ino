#include "HX711.h"  //Timbangan
#include <Wire.h>  // LCD
#include <LiquidCrystal_I2C.h> // LCD
#include "DHT.h" // suhu

#define DHTPIN D7 // suhu
#define DHTTYPE DHT11 // suhu
#define DOUT  D5 //Timbangan
#define CLK  D6 //Timbangan
#define triggerPin  D8 // ultrasonic
#define echoPin     D0 // ultrasonic
const int relay1 = D3; //relay pin2
const int relay2 = D4; //relay pin3
HX711 scale(DOUT, CLK);
 
//Change this calibration factor as per your load cell once it is found you many need to vary it in thousands
float calibration_factor = -109525; //-106600 worked for my 40Kg max scale setup 
 
//Relay ON-OFF
int relayON = LOW; //relay nyala
int relayOFF = HIGH; //relay mati


// Set the LCD address to 0x27 for a 16 chars and 2 line display
LiquidCrystal_I2C lcd(0x27, 16, 2);//atau 0x3F

DHT dht(DHTPIN, DHTTYPE);




void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
  dht.begin();
 // sesor suhu

  Serial.begin(9600);
  Serial.println("HX711 Calibration");
  Serial.println("Remove all weight from scale");
  Serial.println("After readings begin, place known weight on scale");
  Serial.println("Press a,s,d,f to increase calibration factor by 10,100,1000,10000 respectively");
  Serial.println("Press z,x,c,v to decrease calibration factor by 10,100,1000,10000 respectively");
  Serial.println("Press t for tare");
  scale.set_scale();
  scale.tare(); //Reset the scale to 0
 
  long zero_factor = scale.read_average(); //Get a baseline reading
  Serial.print("Zero factor: "); //This can be used to remove the need to tare the scale. Useful in permanent scale projects.
  Serial.println(zero_factor);
 // sensor timbangan

 lcd.begin();
 lcd.backlight();
 lcd.print("Hello, world!");
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

 
}

void loop() {
  // put your main code here, to run repeatedly:
suhu();
timbangan();
ultrasonic();
relay();
}


void suhu(){
  float h = dht.readHumidity();
  float t = dht.readTemperature();
  Serial.print(F("Humidity: "));
  Serial.print(h);
  Serial.print("\n");
  Serial.print(F("  Temperature: "));
  Serial.print(t);
  Serial.print(F("°C "));

  lcd.setCursor(0,0);
  lcd.print(h);
  lcd.print("%   ");
  lcd.print(t);

  
  
  
  delay(2000);
  
}

void ultrasonic() {
  long duration, jarak;
  digitalWrite(triggerPin, LOW);
  delayMicroseconds(2); 
  digitalWrite(triggerPin, HIGH);
  delayMicroseconds(10); 
  digitalWrite(triggerPin, LOW);
  duration = pulseIn(echoPin, HIGH);
  jarak = (duration/2) / 29.1;
  Serial.println("jarak :");
  Serial.print(jarak);
  Serial.println(" cm");
  delay(1000);

  lcd.setCursor(0,1);
  lcd.print(jarak);
  lcd.print("   cm");
  

}

void timbangan() {
 
  scale.set_scale(calibration_factor); //Adjust to this calibration factor
 
  Serial.print("Reading: ");
  Serial.print(scale.get_units(), 3);
  Serial.print(" kg"); //Change this to kg and re-adjust the calibration factor if you follow SI units like a sane person
  Serial.print(" calibration_factor: ");
  Serial.print(calibration_factor);
  Serial.println();

  lcd.setCursor(0,1);
  lcd.print(scale.get_units());
  lcd.print("kg   ");


 
  if(Serial.available())
  {
    char temp = Serial.read();
    if(temp == '+' || temp == 'a')
      calibration_factor += 10;
    else if(temp == '-' || temp == 'z')
      calibration_factor -= 10;
    else if(temp == 's')
      calibration_factor += 100;  
    else if(temp == 'x')
      calibration_factor -= 100;  
    else if(temp == 'd')
      calibration_factor += 1000;  
    else if(temp == 'c')
      calibration_factor -= 1000;
    else if(temp == 'f')
      calibration_factor += 10000;  
    else if(temp == 'v')
      calibration_factor -= 10000;  
    else if(temp == 't')
      scale.tare();  //Reset the scale to zero
  }
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
