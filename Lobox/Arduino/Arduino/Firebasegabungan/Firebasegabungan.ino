#include <Arduino.h>
#if defined(ESP32)
#include <WiFi.h>
#elif defined(ESP8266)
#include <ESP8266WiFi.h>
#endif
#include <Firebase_ESP_Client.h>

//Provide the token generation process info.
#include "addons/TokenHelper.h"
//Provide the RTDB payload printing info and other helper functions.
#include "addons/RTDBHelper.h"


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



// Insert your network credentials
#define WIFI_SSID "CYBORG"
#define WIFI_PASSWORD "12341234"

// Insert Firebase project API Key
#define API_KEY "AIzaSyAT1xZi6fAJ28EwtaNZETqRRJS5hxnAy_A"

// Insert RTDB URLefine the RTDB URL */
#define DATABASE_URL "https://lobox-f704f-default-rtdb.asia-southeast1.firebasedatabase.app/"

//Define Firebase Data object
FirebaseData fbdo;

FirebaseAuth auth;
FirebaseConfig config;

unsigned long sendDataPrevMillis = 0;
int count = 0;
bool signupOK = false;

float h, t;
float berat;
long duration, jarak;

void setup() {
  Serial.begin(115200);
  WiFi.begin(WIFI_SSID, WIFI_PASSWORD);
  Serial.print("Connecting to Wi-Fi");
  while (WiFi.status() != WL_CONNECTED) {
    Serial.print(".");
    delay(300);
  }
  Serial.println();
  Serial.print("Connected with IP: ");
  Serial.println(WiFi.localIP());
  Serial.println();

  /* Assign the api key (required) */
  config.api_key = API_KEY;

  /* Assign the RTDB URL (required) */
  config.database_url = DATABASE_URL;

  Serial.println("jjiioiuj");

  /* Sign up */
  if (Firebase.signUp(&config, &auth, "", "")) {
    Serial.println("ok");
    signupOK = true;
  }
  else {
    Serial.printf("%s\n", config.signer.signupError.message.c_str());
  }

  /* Assign the callback function for the long running token generation task */
  config.token_status_callback = tokenStatusCallback; //see addons/TokenHelper.h

  Firebase.begin(&config, &auth);
  Firebase.reconnectWiFi(true);

  dht.begin();
  // sesor suhu

//  Serial.begin(9600);
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

//  suhu();
//  timbangan();
//  ultrasonic();
//  relay();

  if (Firebase.ready() && signupOK && (millis() - sendDataPrevMillis > 15000 || sendDataPrevMillis == 0)) {
    sendDataPrevMillis = millis();

    // Write an Float number on the database path test/float
    if (Firebase.RTDB.setFloat(&fbdo, "test/suhu", t)) {
      Serial.println("PASSED");
      Serial.println("PATH: " + fbdo.dataPath());
      Serial.println("TYPE: " + fbdo.dataType());
    }
    else {
      Serial.println("FAILED");
      Serial.println("REASON: " + fbdo.errorReason());
    }

    if (Firebase.RTDB.setFloat(&fbdo, "test/kelembaban", h)) {
      Serial.println("PASSED");
      Serial.println("PATH: " + fbdo.dataPath());
      Serial.println("TYPE: " + fbdo.dataType());
    }
    else {
      Serial.println("FAILED");
      Serial.println("REASON: " + fbdo.errorReason());
    }


    if (Firebase.RTDB.setFloat(&fbdo, "test/jarak", jarak)) {
      Serial.println("PASSED");
      Serial.println("PATH: " + fbdo.dataPath());
      Serial.println("TYPE: " + fbdo.dataType());
    }
    else {
      Serial.println("FAILED");
      Serial.println("REASON: " + fbdo.errorReason());
    }

    if (Firebase.RTDB.setFloat(&fbdo, "test/berat", berat)) {
      Serial.println("PASSED");
      Serial.println("PATH: " + fbdo.dataPath());
      Serial.println("TYPE: " + fbdo.dataType());
    }
    else {
      Serial.println("FAILED");
      Serial.println("REASON: " + fbdo.errorReason());
    }



  }
}



void suhu() {
  h = dht.readHumidity();
  t = dht.readTemperature();
  Serial.print(F("Humidity: "));
  Serial.print(h);
  Serial.print("\n");
  Serial.print(F("  Temperature: "));
  Serial.print(t);
  Serial.print(F("°C "));

  lcd.setCursor(0, 0);
  lcd.print(h);
  lcd.print("%   ");
  lcd.print(t);

  delay(2000);

}

void ultrasonic() {
  digitalWrite(triggerPin, LOW);
  delayMicroseconds(2);
  digitalWrite(triggerPin, HIGH);
  delayMicroseconds(10);
  digitalWrite(triggerPin, LOW);
  duration = pulseIn(echoPin, HIGH);
  jarak = (duration / 2) / 29.1;
  Serial.println("jarak :");
  Serial.print(jarak);
  Serial.println(" cm");
  delay(1000);

  lcd.setCursor(0, 1);
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

  berat = scale.get_units();
  
  lcd.setCursor(0, 1);
  lcd.print(scale.get_units());
  lcd.print("kg   ");



  if (Serial.available())
  {
    char temp = Serial.read();
    if (temp == '+' || temp == 'a')
      calibration_factor += 10;
    else if (temp == '-' || temp == 'z')
      calibration_factor -= 10;
    else if (temp == 's')
      calibration_factor += 100;
    else if (temp == 'x')
      calibration_factor -= 100;
    else if (temp == 'd')
      calibration_factor += 1000;
    else if (temp == 'c')
      calibration_factor -= 1000;
    else if (temp == 'f')
      calibration_factor += 10000;
    else if (temp == 'v')
      calibration_factor -= 10000;
    else if (temp == 't')
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
