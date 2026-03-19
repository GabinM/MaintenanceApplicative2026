package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void testAgedBrie() {
        Item[] items = new Item[] { new Item("Aged Brie", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
    }

    @Test
    void testAgedBrieGoodQuality() {
        Item[] items = new Item[] { new Item("Aged Brie", 0, 50) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
    }

    @Test
    void testBackstage() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
    }

    @Test
    void testBackstageMultipleSellin() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 55000000, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
    }

    @Test
    void testBackstageGoodQuality() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 0, 55) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
    }

    @Test
    void testBackstageGoodQualitySellin() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 45486, 55) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
    }

    @Test
    void testSulfuras() {
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
    }

    @Test
    void testSulfurasMultipleSellIn() {
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", 550000000, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
    }

    @Test
    void testSulfurasGoodQuality() {
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", 0, 55) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
    }

    @Test
    void testSulfurasGoodQualitySellin() {
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", 45648, 55) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
    }

    @Test
    void testDrain() {
        Item[] items = new Item[] { new Item("A drain, and it spins", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
    }

    @Test
    void testDrainMultipleSellIn() {
        Item[] items = new Item[] { new Item("A drain, and it spins", 550000000, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
    }

    @Test
    void testDrainGoodQuality() {
        Item[] items = new Item[] { new Item("A drain, and it spins", 0, 55) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
    }

    @Test
    void testDrainGoodQualitySellin() {
        Item[] items = new Item[] { new Item("A drain, and it spins", 5447, 55) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
    }

    @Test
    void testToString(){
        Item[] items = new Item[] { new Item("Aged Brie", 0, 0) };
        GildedRose app = new GildedRose(items);
        assertEquals("Aged Brie, 0, 0", items[0].toString());
    }

    @Test
    void testBackstage_moreThan10days(){
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 12, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Backstage passes to a TAFKAL80ETC concert, 11, 1", items[0].toString());
    }

    @Test
    void testBackstage_lessThan10days(){
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 8, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Backstage passes to a TAFKAL80ETC concert, 7, 2", items[0].toString());
    }

    @Test
    void testBackstage_lessThan5days(){
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 3, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Backstage passes to a TAFKAL80ETC concert, 2, 3", items[0].toString());
    }

    @Test
    void testBackstage_tooLate(){
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 0, 18) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Backstage passes to a TAFKAL80ETC concert, -1, 0", items[0].toString());
    }

    @Test
    void testBackstage_hugeEvent(){
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 4, 49) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Backstage passes to a TAFKAL80ETC concert, 3, 50", items[0].toString());
    }

    @Test
    void testSulfuras_notLoosingValue(){
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", 4, 18) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Sulfuras, Hand of Ragnaros, 4, 18", items[0].toString());
    }

    @Test
    void testSulfuras_outsellined(){
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", -1, 18) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Sulfuras, Hand of Ragnaros, -1, 18", items[0].toString());
    }

    @Test
    void testAgedBrie_oldoldold(){
        Item[] items = new Item[] { new Item("Aged Brie", 7, 50) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Aged Brie, 6, 50", items[0].toString());
    }
}
