# Salling Group Food Waste Prometheus Exporter
A Prometheus Exporter that exports Salling Group Food Waste clearances as gauge metrics.

I created this for a bit hacky discount monitoring setup where I use Prometheus Alert Manager to send me alerts regarding food discounts in the store near me.

# Published Metrics
The exporter publishes a metric for each `Clearance` for each `FoodWaste` returned from each `/v1/food-waste?zip=<ZIP>` API call.

Metrics: 
- `salling_group_food_waste_clearance`
  -  The value of the metric specifies the new price of the product, i.e. price after discount has been applied.

## Example
```
salling_group_food_waste{offer_currency="DKK",offer_discount="10.75",offer_ean="5712580370088",offer_end_time="2024-12-19T22:59:59.000Z",offer_last_update="2024-12-17T16:05:08.000Z",offer_original_price="22.75",offer_percent_discount="47.25",offer_start_time="2024-12-17T15:53:07.000Z",offer_stock="2",offer_stock_unit="each",product_description="SOLSKINSBOLLER KOHBERG",product_ean="5701246006195",store_address_city="Åbyhøj",store_address_country="DK",store_address_street="Silkeborgvej 246",store_address_zip="8230",store_brand="netto",store_id="67766b50-4cc6-4658-8fc4-a9b2db468f91",store_name="Netto Åbyhøj, Silkeborgvej"} 12.0
```

# Configuration
## API Key
This exporter requires a Salling Group API key that has `/v1/food-waste/**` scope.
The API key can be set through the `FW_EXPORTER_API_KEY` environment variable.

## Zip Codes
To configure which zip codes to get metrics form, set the `FW_EXPORTER_ZIP_CODES` environment variable. 
Example: 
```
export FW_EXPORTER_ZIP_CODES='["8230"]'
```
The exporter invokes a GET `/v1/food-waste?zip=<ZIP>` for each zip code specified in the `FW_EXPORTER_ZIP_CODES` on each scrape. 



