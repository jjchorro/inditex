CREATE TABLE IF NOT EXISTS PRICES(
  ID serial NOT NULL,
  BRAND_ID int,
  START_DATE timestamp,
  END_DATE timestamp,
  PRICE_LIST int,
  PRODUCT_ID int,
  PRIORITY int,
  PRICE float,
  CURR varchar
);
