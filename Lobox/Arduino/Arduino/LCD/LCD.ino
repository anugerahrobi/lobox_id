#include <LiquidCrystal_I2C.h>
LiquidCrystal_I2C lcd(0x27, 16, 2);

void setup() {
  lcd.init();
}

void loop(){
  lcd.setCursor(0,0);
  lcd.print("Lobox Memiliki");
  lcd.setCursor(0,1);
  lcd.print("Berat : ");
}
