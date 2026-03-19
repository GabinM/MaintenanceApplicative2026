package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            switch(items[i].name){
                case "Backstage passes to a TAFKAL80ETC concert":
                    updateBackstagePasses(i);
                    break;
                case "Sulfuras, Hand of Ragnaros":
                    updateSulfuras(i);
                    break;
                case "Aged Brie":
                    updateAgedBrie(i);
                    break;
                default:
                    updateOther(i);
                    break;
            }
        }
    }

    public void updateAgedBrie(int i) {
            if (items[i].quality < 50) {
                items[i].quality = items[i].quality + 1;
        }

        items[i].sellIn = items[i].sellIn - 1;

        if (items[i].sellIn < 0) {

                if (items[i].quality < 50) {
                    items[i].quality = items[i].quality + 1;
                }
        }

    }

    public void updateBackstagePasses(int i) {

        if (items[i].quality < 50) {
            items[i].quality = items[i].quality + 1;

            if (items[i].sellIn < 11) {
                if (items[i].quality < 50) {
                    items[i].quality = items[i].quality + 1;
                }
            }

            if (items[i].sellIn < 6) {
                if (items[i].quality < 50) {
                    items[i].quality = items[i].quality + 1;
                }
            }

        }

        items[i].sellIn = items[i].sellIn - 1;


        if (items[i].sellIn < 0) {
                items[i].quality = 0;
        }
    }

    public void updateSulfuras(int i) {

    }

    public void updateOther(int i) {
        if (items[i].quality > 0) {
            items[i].quality = items[i].quality - 1;
        }
        if (items[i].quality < 50) {
            items[i].quality = items[i].quality + 1;
        }


        items[i].sellIn = items[i].sellIn - 1;

        if (items[i].sellIn < 0) {
            if (items[i].quality > 0) {
                items[i].quality = items[i].quality - 1;
            }
        }
    }

    private void oldMethod(int i){
        if (!items[i].name.equals("Aged Brie")
                && !items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            if (items[i].quality > 0) {
                if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
                    items[i].quality = items[i].quality - 1;
                }
            }
        } else {
            if (items[i].quality < 50) {
                items[i].quality = items[i].quality + 1;

                if (items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                    if (items[i].sellIn < 11) {
                        if (items[i].quality < 50) {
                            items[i].quality = items[i].quality + 1;
                        }
                    }

                    if (items[i].sellIn < 6) {
                        if (items[i].quality < 50) {
                            items[i].quality = items[i].quality + 1;
                        }
                    }
                }
            }
        }

        if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
            items[i].sellIn = items[i].sellIn - 1;
        }

        if (items[i].sellIn < 0) {
            if (!items[i].name.equals("Aged Brie")) {
                if (!items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                    if (items[i].quality > 0) {
                        if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
                            items[i].quality = items[i].quality - 1;
                        }
                    }
                } else {
                    items[i].quality = items[i].quality - items[i].quality;
                }
            } else {
                if (items[i].quality < 50) {
                    items[i].quality = items[i].quality + 1;
                }
            }
        }
    }

}
